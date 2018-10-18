package nccloud.web.ifm.redeem.action;

import java.util.HashMap;
import java.util.Map;

import nc.bs.logging.Logger;
import nc.itf.ifm.IInvestRedeemQueryService;
import nc.vo.ifm.RedeemStatusEnum;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.uap.pf.PfProcessBatchRetObject;
import nccloud.base.exception.ExceptionUtils;
import nccloud.framework.core.json.IJson;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.container.SessionContext;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardFormulaHandler;
import nccloud.pubitf.riart.pflow.CloudPFlowContext;
import nccloud.pubitf.riart.pflow.ICloudScriptPFlowService;
import nccloud.web.ifm.util.RedeemUtil;
import nccloud.web.workflow.approve.util.NCCFlowUtils;

import org.apache.commons.lang.StringUtils;

public class RedeemSaveSubmitAction implements ICommonAction {
	private static IInvestRedeemQueryService eQueryService = null;
	private static ICloudScriptPFlowService cPFlowService = null;

	public static synchronized IInvestRedeemQueryService getEQueryService() {
		if (eQueryService == null) {
			eQueryService = ServiceLocator
					.find(IInvestRedeemQueryService.class);
		}
		return eQueryService;
	}

	public static synchronized ICloudScriptPFlowService getCPFlowService() {
		if (cPFlowService == null) {
			cPFlowService = ServiceLocator.find(ICloudScriptPFlowService.class);
		}
		return cPFlowService;
	}

	@Override
	public Object doAction(IRequest request) {
		AggInvestRedeemVO[] vos = null;
		try {
			vos = this.processRequestParam(request);
			vos = this.doBefore(vos);
			// vos = this.processDigit(vos);
			// 构建动作脚本执行所需参数
			CloudPFlowContext context = new CloudPFlowContext();
			context.setActionName(getSaveActionCode());
			context.setBillType(getBillTypeCode());
			context.setBillVos(vos);
			Logger.debug("开始调用动作脚本 ActionName[" + getSaveActionCode()
					+ "] BillType[" + getBillTypeCode() + "]...");
			vos = (AggInvestRedeemVO[]) getCPFlowService().exeScriptPFlow(
					context);
			Logger.debug("调用动作脚本 ActionName[" + getSaveActionCode()
					+ "] BillType[" + getBillTypeCode() + "]结束");
			vos = getEQueryService().getAggVOsByPKs(
					vos[0].getParentVO().getPk_redeem());

			// 提交处理
			// 判断单据是否配置有审批流选择执行对应的动作脚本动作脚本
			boolean hasFlow = NCCFlowUtils.hasApproveflowDef(getBillTypeCode(),
					vos[0]);
			// 构建动作脚本执行所需参数
			CloudPFlowContext commitcontext = new CloudPFlowContext();
			commitcontext.setActionName(getSubmitActionCode());
			commitcontext.setBillType(getBillTypeCode());
			commitcontext.setBillVos(vos);
			Logger.debug("开始调用动作脚本 ActionName[" + getSubmitActionCode()
					+ "] BillType[" + getBillTypeCode() + "]...");
			Object[] results = null;
			if (hasFlow) {
				// 有审批流的单据提交
				results = getCPFlowService().exeScriptPFlow(commitcontext);
			} else {
				// 无审批流的单据提交
				// 无审批流提交后即审批，无此代码ts会校验不通过
				Map<Object, Object> eParam = new HashMap<Object, Object>();
				eParam.put(TMIFMConst.CONST_PFLOW_ISRELOADBILL, true);
				commitcontext.seteParam(eParam);
				results = getCPFlowService().exeScriptPFlow_CommitNoFlowBatch(
						commitcontext);
				PfProcessBatchRetObject retObject = (PfProcessBatchRetObject) results[0];
				// 执行动作脚本出错捕获出错信息
				if (retObject.getRetObj() == null
						|| retObject.getRetObj().length == 0) {
					String errMsg = retObject.getExceptionMsg();
					Logger.error(errMsg);
					throw new BusinessException(errMsg);
				} else {
					results = retObject.getRetObj();
				}
			}
			Logger.debug("调用动作脚本 ActionName[" + getSubmitActionCode()
					+ "] BillType[" + getBillTypeCode() + "]结束");
			vos = (AggInvestRedeemVO[]) results;
			BillCard billCard = doReturn(vos);
			return billCard;
		} catch (BusinessException e) {
			Logger.error(e.getMessage(), e);
			ExceptionUtils.wrapBusinessException(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 处理接收的前台参数
	 * 
	 * @param request
	 * @return
	 */
	protected AggInvestRedeemVO[] processRequestParam(IRequest request) {
		String read = request.read();
		IJson json = JsonFactory.create();
		BillCard card = json.fromJson(read, BillCard.class);
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		AggInvestRedeemVO vo = processor.fromBillCard(card);
		AggInvestRedeemVO[] vos = new AggInvestRedeemVO[] { vo };
		return vos;
	}

	/**
	 * 前规则
	 * 
	 * @param pks
	 * @return
	 */
	protected AggInvestRedeemVO[] doBefore(AggInvestRedeemVO[] vos)
			throws BusinessException {
		if (vos == null || vos.length < 1) {
			throw new BusinessException("保存的数据不能为空！");
		}
		try {
			InvestRedeemVO vo = vos[0].getParentVO();
			if(vo.getRedeemnumber()!=null){
				Integer lastNum =vo.getApplynumber()-vo.getRedeemnumber();
				if(lastNum<0){
					throw new BusinessException("赎回份数大于您的申购份数，您当前的持有份数为为："+vo.getApplynumber()+"");
				}
				UFDouble UFlastNum = new UFDouble(vo.getRedeemmoney());
				if(vo.getRedeemmoney()==null){
					vo.setRedeemmoney(UFlastNum.multiply(vo.getUnitnetvalue()).toString());
				}
			}
			else if(vo.getHoldmoeny()!=null){
				if (vo.getHoldmoeny().sub(vo.getRedeemmoney()).compareTo(UFDouble.ZERO_DBL) < 0|| vo.getHoldmoeny().compareTo(UFDouble.ZERO_DBL) <= 0) {
					throw new BusinessException("持有金额小于赎回金额，您当前的持有金额为："
							+ vo.getHoldmoeny() + "");
			}
				
			}
			// 根据是否有主键信息判断是新增保存还是修改保存
			if (StringUtils.isBlank(vo.getPk_redeem())) {
				// 设置单据默认值
				// vo.setVbillno(getBillTypeCode());
				// Integer vbillstatus = (Integer)
				// BillStatusEnum.COMMIT.value();//提交
				Integer billstatus = (Integer) RedeemStatusEnum.待审核.value();// 待审核
				// vo.setAttributeValue("vbillstatus", vbillstatus);
				vo.setAttributeValue("billstatus", billstatus);
			

				vo.setPk_group(RedeemUtil.getGroupByOrg(vo.getPk_org()));
				vo.setPk_billtypecode(getBillTypeCode());
				vo.setBillmakedate(getBusiDate());
				vo.setBillmaketime(new UFDateTime(SessionContext.getInstance()
						.getClientInfo().getBizDateTime()));
			} else {
				// 校验申请编号
				AggInvestRedeemVO[] oldvo = getEQueryService().getAggVOsByPKs(
						new String[] { vo.getPk_redeem() });
				if (oldvo == null || oldvo.length < 1) {
					throw new BusinessException("修改的单据已有其他操作，请刷新后再修改保存！");
				}
			}
			vos[0].setParent(vo);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			Logger.error(e.getMessage(), e);
			ExceptionUtils.wrapBusinessException(e.getMessage(), e);
		}
		return vos;
	}

	/**
	 * 处理返回值
	 * 
	 * @param vos
	 * @return
	 */
	protected BillCard doReturn(AggInvestRedeemVO[] vos) {
		// 把结果进行封装返回
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		BillCard billCard = processor.convert(
				TMIFMConst.CONST_PAGECODE_CONTRACT_CARD, vos[0]);
		// 卡片参照翻译
		Translator translator = new Translator();
		translator.translate(billCard);
		// 处理模板显示公式
		BillCardFormulaHandler handler = new BillCardFormulaHandler(billCard);
		handler.handleLoadFormula();
		// handler.handleBodyLoadFormula();
		return billCard;
	}

	/**
	 * 获取当前登录用户主键
	 * 
	 * @return
	 */
	private String getPk_user() {
		return SessionContext.getInstance().getClientInfo().getUserid();
	}

	/**
	 * 获取当前登录用户主键
	 * 
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo()
				.getBizDateTime());
	}

	/**
	 * 单据类型编码
	 * 
	 * @return
	 */
	protected String getBillTypeCode() {
		return TMIFMConst.CONST_BILLTYPE_REDEEM;
	}

	/**
	 * 保存动作编码
	 * 
	 * @return
	 */
	protected String getSaveActionCode() {
		return TMIFMConst.CONST_ACTION_SAVEBASE;
	}

	/**
	 * 提交动作编码
	 * 
	 * @return
	 */
	protected String getSubmitActionCode() {
		return TMIFMConst.CONST_ACTION_SAVE;
	}
	/*
	 * @Override protected AggInvestRedeemVO doBusinessSave(AggInvestRedeemVO
	 * operaVO) { // 赋值创建人等信息
	 * 
	 * // 获取Service
	 * 
	 * try { this.doBefore(operaVO); operaVO= (AggInvestRedeemVO)
	 * callActionScript
	 * (TMIFMConst.CONST_ACTION_SAVEBASE,TMIFMConst.CONST_BILLTYPE_REDEEM,new
	 * AggInvestRedeemVO[]{operaVO}); try { Object result =
	 * super.doCommitProcess(new AggInvestRedeemVO[]{vo}, null);
	 * AggInvestRedeemVO[] vos = (AggInvestRedeemVO[]) result; list.add(vos[0]);
	 * } catch (BusinessException e) { errList.add("单据编号：" +
	 * vo.getParentVO().getVbillno() + e.getMessage()); continue; } } catch
	 * (BusinessException e) { ExceptionUtils.wrapBusinessException("保存单据失败：" +
	 * e.getMessage()); } return operaVO; }
	 * 
	 * 
	 * @Override protected String getPageCode() { return
	 * TMIFMConst.CONST_PAGECODE_CONTRACT_CARD; }
	 *//**
	 * 保存前操作
	 * 
	 * @param operaVO
	 */
	/*
	 * private void doBefore(AggInvestRedeemVO operaVO) throws
	 * BusinessException{ InvestRedeemVO vo=operaVO.getParentVO(); //设置审计信息
	 * Integer vbillstatus = (Integer) BillStatusEnum.COMMIT.value();//提交
	 * Integer billstatus = (Integer) RedeemStatusEnum.待审核.value();//待审核
	 * vo.setAttributeValue("vbillstatus", vbillstatus);
	 * vo.setAttributeValue("billstatus", billstatus);
	 * vo.setPk_group(getGroupByOrg(vo.getPk_org()));
	 * vo.setAttributeValue("creator"
	 * ,SessionContext.getInstance().getClientInfo().getUserid());
	 * vo.setAttributeValue("creationtime",new
	 * UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime()) );
	 * if
	 * (vo.getHoldmoeny().sub(vo.getRedeemmoney()).compareTo(UFDouble.ZERO_DBL
	 * )<1) { throw new
	 * BusinessException("持有金额小于赎回金额，您当前的持有金额为："+vo.getHoldmoeny()+""); } }
	 *//**
	 * 查询财务组织所在集团
	 * 
	 * @param pk_org
	 * @return
	 */
	/*
	 * public static String getGroupByOrg(String pk_org) throws
	 * BusinessException { IOrgUnitPubService_C orgUnitQryService =
	 * ServiceLocator.find(IOrgUnitPubService_C.class); String pk_group = null;
	 * OrgVO[] orgVOs = orgUnitQryService.getOrgs(new String[] { pk_org }, new
	 * String[] { TMIFMConst.FIELD_PK_GROUP }); if(orgVOs == null ||
	 * orgVOs.length <= 0){
	 * ExceptionUtils.wrapBusinessException("获取财务组织对应的集团失败。"); } pk_group =
	 * (String) orgVOs[0].getAttributeValue(TMIFMConst.FIELD_PK_GROUP); return
	 * pk_group; }
	 *//**
	 * 获取当前登录用户主键
	 * 
	 * @return
	 */
	/*
	 * private String getPk_user() { return
	 * SessionContext.getInstance().getClientInfo().getUserid(); }
	 *//**
	 * 获取当前登录用户主键
	 * 
	 * @return
	 */
	/*
	 * private UFDate getBusiDate() { return new
	 * UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime()); }
	 * 
	 * 
	 * @Override public Object doAction(IRequest request) { // TODO
	 * Auto-generated method stub return null; }
	 */

}
