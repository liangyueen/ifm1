package nccloud.web.ifm.income.action;

import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.org.FinanceOrgVO;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.web.ifm.common.action.CommonSaveAction;
import nc.bs.pub.action.N_3642_SAVE;
import nc.vo.ifm.constants.TMIFMConst;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.SessionContext;
/**
 * 贷款合同新增/修改保存
 * 
 * @author suxch
 */
public class IncomeSaveAction extends CommonSaveAction<AggInvestIncomeVO> {

	@Override
	protected AggInvestIncomeVO doBusinessSave(AggInvestIncomeVO operaVO) {
		// 获取Service
		
		try {
			// 赋值创建人等信息
			this.doBefore(operaVO);
			operaVO= (AggInvestIncomeVO) callActionScript(TMIFMConst.CONST_ACTION_SAVEBASE,TMIFMConst.CONST_BILLTYPE_INCOME,new AggInvestIncomeVO[]{operaVO});
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("保存单据失败：" + e.getMessage());
		}
		return operaVO;
	}

	/*
	 * @Override protected String getPageCode() { return
	 * TMCFMConst.CONST_PAGECODE_CONTRACT_CARD; }
	 */
	/**
	 * 保存前操作
	 * 
	 * @param operaVO
	 */
//	private void doBefore(AggInvestIncomeVO operaVO) {
//		/*
//		 * // 从SessionContext中获取客户端信息 ClientInfo clientInfo =
//		 * SessionContext.getInstance().getClientInfo(); ContractVO head =
//		 * ((ContractVO) operaVO.getParent()); // 设置一些初始化信息 if
//		 * (head.getPk_contract() == null) {
//		 * head.setCreator(clientInfo.getUserid());
//		 * head.setPk_group(clientInfo.getPk_group());
//		 * head.setPk_billtypecode(TMCFMConst.CONST_BILLTYPE_CONTRACT);
//		 * head.setBusistatus(-1); head.setVbillstatus(-1); } else {
//		 * head.setModifier(clientInfo.getUserid()); }
//		 */
//	}
	private void doBefore(AggInvestIncomeVO operaVO) throws BusinessException{
		InvestIncomeVO vo=operaVO.getParentVO();
		//设置审计信息
//		vo.setPk_group(getGroupByOrg(vo.getPk_org()));
//		暂时写为固定值，之后更改
		vo.setAttributeValue("pk_group","0001A110000000000EQ8" );
		vo.setPk_billtypecode("3643");
		vo.setAttributeValue("creator",SessionContext.getInstance().getClientInfo().getUserid());
		vo.setAttributeValue("creationtime",new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime()) );

	}
	/**
	 * 查询财务组织所在集团
	 * @param financeOrgVO
	 * @return
	 */
	/*public static String getGroupByOrg(FinanceOrgVO financeOrgVO) throws BusinessException {
		IOrgUnitPubService_C orgUnitQryService = ServiceLocator.find(IOrgUnitPubService_C.class);
		String pk_group = null;
		OrgVO[] orgVOs = orgUnitQryService.getOrgs(new String[] { financeOrgVO }, new String[] { TMIFMConst.FIELD_PK_GROUP });
		if(orgVOs == null || orgVOs.length <= 0){
			ExceptionUtils.wrapBusinessException("获取财务组织对应的集团失败。");
		}
		pk_group = (String) orgVOs[0].getAttributeValue(TMIFMConst.FIELD_PK_GROUP);
		return pk_group;
	}*/
	

	@Override
	protected String getPageCode() {
		return null;
	}


}