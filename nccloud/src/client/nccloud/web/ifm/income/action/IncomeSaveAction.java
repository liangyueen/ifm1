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
 * �����ͬ����/�޸ı���
 * 
 * @author suxch
 */
public class IncomeSaveAction extends CommonSaveAction<AggInvestIncomeVO> {

	@Override
	protected AggInvestIncomeVO doBusinessSave(AggInvestIncomeVO operaVO) {
		// ��ȡService
		
		try {
			// ��ֵ�����˵���Ϣ
			this.doBefore(operaVO);
			operaVO= (AggInvestIncomeVO) callActionScript(TMIFMConst.CONST_ACTION_SAVEBASE,TMIFMConst.CONST_BILLTYPE_INCOME,new AggInvestIncomeVO[]{operaVO});
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("���浥��ʧ�ܣ�" + e.getMessage());
		}
		return operaVO;
	}

	/*
	 * @Override protected String getPageCode() { return
	 * TMCFMConst.CONST_PAGECODE_CONTRACT_CARD; }
	 */
	/**
	 * ����ǰ����
	 * 
	 * @param operaVO
	 */
//	private void doBefore(AggInvestIncomeVO operaVO) {
//		/*
//		 * // ��SessionContext�л�ȡ�ͻ�����Ϣ ClientInfo clientInfo =
//		 * SessionContext.getInstance().getClientInfo(); ContractVO head =
//		 * ((ContractVO) operaVO.getParent()); // ����һЩ��ʼ����Ϣ if
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
		//���������Ϣ
//		vo.setPk_group(getGroupByOrg(vo.getPk_org()));
//		��ʱдΪ�̶�ֵ��֮�����
		vo.setAttributeValue("pk_group","0001A110000000000EQ8" );
		vo.setPk_billtypecode("3643");
		vo.setAttributeValue("creator",SessionContext.getInstance().getClientInfo().getUserid());
		vo.setAttributeValue("creationtime",new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime()) );

	}
	/**
	 * ��ѯ������֯���ڼ���
	 * @param financeOrgVO
	 * @return
	 */
	/*public static String getGroupByOrg(FinanceOrgVO financeOrgVO) throws BusinessException {
		IOrgUnitPubService_C orgUnitQryService = ServiceLocator.find(IOrgUnitPubService_C.class);
		String pk_group = null;
		OrgVO[] orgVOs = orgUnitQryService.getOrgs(new String[] { financeOrgVO }, new String[] { TMIFMConst.FIELD_PK_GROUP });
		if(orgVOs == null || orgVOs.length <= 0){
			ExceptionUtils.wrapBusinessException("��ȡ������֯��Ӧ�ļ���ʧ�ܡ�");
		}
		pk_group = (String) orgVOs[0].getAttributeValue(TMIFMConst.FIELD_PK_GROUP);
		return pk_group;
	}*/
	

	@Override
	protected String getPageCode() {
		return null;
	}


}