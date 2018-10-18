package nccloud.web.tmifm.common.action;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import nc.bs.logging.Logger;
import nc.vo.imf.constants.TMIMFConst;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.tmpub.util.ArrayUtil;
import nc.vo.tmpub.util.StringUtil;
import nc.vo.uap.pf.PfProcessBatchRetObject;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.processor.template.ExtBillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCard;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCardOperator;
import nccloud.pubitf.riart.pflow.CloudPFlowContext;
import nccloud.pubitf.riart.pflow.ICloudScriptPFlowService;
import nccloud.web.workflow.approve.util.NCCFlowUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 单据保存
 * @author futao3
 * @date Aug 29, 2018
 */
public abstract class CommonSaveAction<T extends AbstractBill> extends AbstractPfAction<T> {
	
	private final String PARAM_CONTENT = "content";
	//提交时报单据已被修改，加上此参数，值为true
	public static String IS_RELOADBILL="IS_RELOADBILL";
	
	@Override
	public Object doAction(IRequest request) {
		//处理前端请求，获取VO数据
		T operaVO = this.getVODate(request);
		//精度处理
		operaVO = processDigit(operaVO);	
		//保存前操作
		doBefore();
		//执行业务保存操作
		T resultVO = this.doBusinessSave(operaVO);
		// 后续操作
		doAfter();
		//构建前端返回结果
		ExtBillCard billCard = this.buildFontResult(resultVO);
		return billCard;
	}
	
	@Override
	protected String getActionCode() {
		return null;
	}

	@Override
	protected String getBillTypeCode() {
		return null;
	}

	/**
	 * 获取单据VO数据，卡片保存，所以只有一条数据
	 * @param request
	 * @return
	 */
	protected T getVODate(IRequest request){
		// 解析request
		ExtBillCardOperator operator = new ExtBillCardOperator();
		T aggvo =  operator.toBill(request);
		return aggvo;
	}
	
	@Override
	protected void doBefore() {
		/*// 从SessionContext中获取客户端信息
		ClientInfo clientInfo = SessionContext.getInstance().getClientInfo();
		ContractVO head = ((ContractVO)vo.getParent());
		// 设置一些初始化信息
		if(head.getPk_contract()==null){
			head.setCreator(clientInfo.getUserid());
			head.setPk_group(clientInfo.getPk_group());
			head.setPk_billtypecode(TMCFMConst.CONST_BILLTYPE_CONTRACT);
			head.setBusistatus(-1);
			head.setVbillstatus(-1);
		}else{
			head.setModifier(clientInfo.getUserid());
		}*/
	}
	
	
	
	
	/**
	 * 业务保存操作
	 * @param operaVO
	 * @return
	 */
	protected abstract T doBusinessSave(T operaVO);

	@Override
	protected void doAfter() {
		
	}
	
	/**
	 * 构建前端返回结果
	 * 
	 * @param operaParam
	 * @param resultVOs
	 * @return
	 */
	protected ExtBillCard buildFontResult(T resultVO) {
		// 把结果进行封装返回
		ExtBillCard billCard = new ExtBillCard();
		if (resultVO == null) {
			return billCard;
		}
		ExtBillCardConvertProcessor processor = new ExtBillCardConvertProcessor();
		billCard = processor.convert(this.getPageCode(), resultVO);
		
	    //翻译
		Translator translator = new Translator();
		translator.translate(billCard);
		return billCard;
	}

	@Override
	protected T[] processRequestParam(IRequest request) {
		return null;
	}
	

	@Override
	protected void doReturn() {
		
	}

	@Override
	protected void processMsg() {
		
	}

	@Override
	protected void processDigit() {
		
	}
	
	/**
	 * 精度处理
	 * 
	 * @return
	 */
	protected abstract T processDigit(T operaVO);

	
	/**
	 * 获取页面编码
	 * @return
	 */
	protected abstract String getPageCode();
	
	/**
	 * 提交处理
	 * @param commitVOs
	 * @return
	 * @throws BusinessException
	 */
	protected Object doCommitProcess(T[] commitVOs, Map<String, String> extParam) throws BusinessException{
		if(commitVOs == null || commitVOs.length == 0){
			return commitVOs;
		}
		Object result = null;
		//判断是否定义审批流
		boolean hasFlow = NCCFlowUtils.hasApproveflowDef(
				this.getBillTypeCode(), commitVOs[0]);
		//有，则走提交动作脚本
		if (hasFlow) {
			result = commitWithApproveFlow(commitVOs, extParam);
		}
		// 没有，则提交即审批
		else {
			result = commitAutoApprove(commitVOs);
		}
		return result;
	} 
	
	/**
	 * 带有审批流的审批
	 * 
	 * @param aggVO
	 * @param extParam
	 * @return
	 * @throws BusinessException
	 */
	private Object commitWithApproveFlow(T[] aggVOs,
			Map<String, String> extParam) throws BusinessException {
		// 从前端获取拓展参数，是否有content数据，如果有则表明是指派需要将指派参数注入到提交逻辑中
		JSONObject content = getContentObj(extParam);
		//动作 actioncode
		String actionCode = this.getActionCode();
		//单据类型
		String billType = this.getBillTypeCode();
		if (content == null) {
			return super.callActionScript(actionCode,
					billType, aggVOs);
		} else {
			Map<Object, Object> contentParam = new HashMap<>();
			contentParam.put(PARAM_CONTENT, content);
			return super.callActionScript(actionCode,
					billType, contentParam,
					aggVOs);
		}
	}
	
	/**
	 * 提交即审批
	 * 
	 * @param aggVO
	 * @return
	 * @throws BusinessException
	 */
	private Object commitAutoApprove(T[] aggVOs)
			throws BusinessException {
//		String pk_user = SessionContext.getInstance().getClientInfo()
//				.getUserid();
		//动作 actioncode
		String actionCode = this.getActionCode();
		// 调用提交即审批接口
		CloudPFlowContext context = new CloudPFlowContext();
		Map<Object, Object> extParam = new HashMap<Object, Object>();
		extParam.put(IS_RELOADBILL, true);
		context.seteParam(extParam);
		context.setActionName(actionCode);
		context.setBillType(this.getBillTypeCode());
		context.setBillVos(aggVOs);
		//返回结果
		Object retObj = null;
		//提交
		if(TMIMFConst.CONST_ACTION_SAVE.equals(actionCode)){
			retObj = getFlowService().exeScriptPFlow_CommitNoFlowBatch(
					context);
		//收回,无审批流，收回即弃审收回
		}else if(TMIMFConst.CONST_ACTION_UNSAVEBILL.equals(actionCode)
				|| TMIMFConst.CONST_ACTION_UNAPPROVE.equals(actionCode)){
			//调用收回即弃审会先走弃审，再走收回，但是在弃审时，没有审批流，直接将审批状态置为自由态，再走收回时，状态有误
			//所以，直接调弃审动作脚本
//			retObj = getFlowService().exeScriptPFlow_UnSaveNoFlowBatch(
//					context);
			context.setActionName(TMIMFConst.CONST_ACTION_UNAPPROVE);
			retObj = getFlowService().exeScriptPFlow(context);
		}
//		if (!retObj.getClass().isArray()) {
//			throw new BusinessException("提交即审批接口返回数据错误！");
//		}
		// 处理返回结果
		Object obj = Array.get(retObj, 0);
		if (obj instanceof PfProcessBatchRetObject) {
			String errMsg = ((PfProcessBatchRetObject) obj).getExceptionMsg();
			// 没有异常信息则表明执行成功
			if (StringUtil.isNull(errMsg)) {
				return ArrayUtil.isNull(((PfProcessBatchRetObject) obj)
						.getRetObj()) ? null : ((PfProcessBatchRetObject) obj)
						.getRetObj();
			}
			// 表明执行异常！
			else {
				Logger.error(errMsg);
				throw new BusinessException(errMsg);
			}
		} 
//		else {
//			throw new BusinessException("提交即审批接口返回数据错误！");
//		}
		return retObj;
	}
	
	
	/**
	 * 获取前端的拓展参数
	 * 
	 * @param extParam
	 * @return
	 */
	private JSONObject getContentObj(Map<String, String> extParam) {
		if(extParam == null){
			return null;
		}
		String contentStr = extParam.get(PARAM_CONTENT);
		if (StringUtil.isNull(contentStr)) {
			return null;
		}
		JSONObject obj = (JSONObject) JSON.parse(contentStr);
		return obj;
	}
	
	/**
	 * 获取流程服务
	 * 
	 * @return
	 */
	private ICloudScriptPFlowService getFlowService() {
		return ServiceLocator.find(ICloudScriptPFlowService.class);
	}
	
}
