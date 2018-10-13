package nccloud.web.ifm.redeem.action;

import org.apache.commons.lang.StringUtils;

import nc.bs.logging.Logger;
import nc.bs.pub.action.N_3642_SAVE;
import nc.itf.ifm.IIFMApplyQueryService;
import nc.itf.ifm.IInvestRedeemQueryService;
import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.cc.execadj.ExecAdjVO;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nccloud.dto.baseapp.querytree.dataformat.QueryTreeFormatVO;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.ClientInfo;
import nccloud.framework.web.container.SessionContext;
import nccloud.pubitf.platform.query.INCCloudQueryService;
import nccloud.pubitf.riart.pflow.ICloudScriptPFlowService;
import nccloud.web.ifm.common.action.CommonSaveAction;
import nccloud.web.ifm.util.RedeemUtil;
import nccloud.framework.core.exception.ExceptionUtils;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
/**
 * 贷款合同新增/修改保存
 * 
 * @author suxch
 */
public class RedeemSaveAction extends CommonSaveAction<AggInvestRedeemVO> {
	private static IInvestRedeemQueryService eQueryService = null;
	
	public static synchronized IInvestRedeemQueryService getEQueryService() {
		if (eQueryService == null) {
			eQueryService =  ServiceLocator.find(IInvestRedeemQueryService.class);
		}
		return eQueryService;
	}
	

	@Override
	protected AggInvestRedeemVO doBusinessSave(AggInvestRedeemVO operaVO) {
		// 赋值创建人等信息
		
		
		// 获取Service
		
		try {
			this.doBefore(operaVO);
			operaVO= (AggInvestRedeemVO) callActionScript(TMIFMConst.CONST_ACTION_SAVEBASE,TMIFMConst.CONST_BILLTYPE_REDEEM,new AggInvestRedeemVO[]{operaVO});
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("保存单据失败：" + e.getMessage());
		}
		return operaVO;
	}

	
	  @Override protected String getPageCode() {
		  return TMIFMConst.CONST_PAGECODE_CONTRACT_CARD; }
	 
	/**
	 * 保存前操作
	 * 
	 * @param operaVO
	 */
	private AggInvestRedeemVO doBefore(AggInvestRedeemVO operaVO) throws BusinessException{
		
		if (operaVO == null) {
			throw new BusinessException("保存的数据不能为空！");
		}
		InvestRedeemVO vo=operaVO.getParentVO();
		// 基础必输项校验
		
		// 校验赎回额是否超过持有金额
		AggInvestApplyVO[] resultVOs = null;
		if (vo.getHoldmoeny().sub(vo.getRedeemmoney()).compareTo(UFDouble.ZERO_DBL)<0) {
			throw new BusinessException("持有金额小于赎回金额，您当前的持有金额为："+vo.getHoldmoeny()+"");
		}
		ClientInfo clientInfo = SessionContext.getInstance().getClientInfo();
		// 根据是否有主键信息判断是新增保存还是修改保存
		if (StringUtils.isBlank(vo.getPk_redeem())) {
			// 设置单据默认值
			vo.setPk_billtypecode(getBillTypeCode());
			
			vo.setPk_group(clientInfo.getPk_group());
			vo.setBillmakedate(getBusiDate());
			vo.setPk_billtypecode(TMIFMConst.CONST_BILLTYPE_REDEEM);
			vo.setBillmaketime(new UFDateTime(SessionContext.getInstance().getClientInfo().getBizDateTime()));
		} else {
			// 校验申请编号
			AggInvestRedeemVO[] oldvo = getEQueryService().queryRedeemByPks(new String[]{ vo.getPk_redeem() });
			if (oldvo == null || oldvo.length < 1) {
				throw new BusinessException("修改的单据已有其他操作，请刷新后再修改保存！");
			}
		}
		vo.setAttributeValue("pk_org_v", 1);
		operaVO.setParent(vo);
		operaVO = this.processDigit(operaVO);
		return operaVO;
	}
	
	/**
	 * 查询财务组织所在集团
	 * @param pk_org
	 * @return
	 */
	public static String getGroupByOrg(String pk_org) throws BusinessException {
		IOrgUnitPubService_C orgUnitQryService = ServiceLocator.find(IOrgUnitPubService_C.class);
		String pk_group = null;
		OrgVO[] orgVOs = orgUnitQryService.getOrgs(new String[] { pk_org }, new String[] { TMIFMConst.FIELD_PK_GROUP });
		if(orgVOs == null || orgVOs.length <= 0){
			ExceptionUtils.wrapBusinessException("获取财务组织对应的集团失败。");
		}
		pk_group = (String) orgVOs[0].getAttributeValue(TMIFMConst.FIELD_PK_GROUP);
		return pk_group;
	}
	
	/**
	 * 获取当前登录用户主键
	 * @return
	 */
	private String getPk_user() {
		return SessionContext.getInstance().getClientInfo().getUserid();
	}
	
	/**
	 * 获取当前登录用户主键
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
	}
		

	/**
	 * 单据类型编码
	 * @return
	 */
	protected String getBillTypeCode() {
		return TMIFMConst.CONST_BILLTYPE_REDEEM;
	}
	/**
	 * 金额及精度处理
	 * @param vos
	 * @return
	 * @throws BusinessException
	 */
	protected AggInvestRedeemVO processDigit(AggInvestRedeemVO vos) throws BusinessException {
		try {
			InvestRedeemVO vo = vos.getParentVO();
			vo = (InvestRedeemVO)RedeemUtil.processPrecision(vo, true, getBusiDate());
			vos.setParentVO(vo);
			return vos;
		} catch (BusinessException e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessException("金额或精度处理错误！");
		}
	}
	

}
