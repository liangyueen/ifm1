package nccloud.web.ifm.investapply.action;

import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.ifm.investapply.util.ApplyQueryUtil;
import nc.vo.imf.constants.TMIMFConst;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.SessionContext;
import nccloud.web.tmifm.common.action.CommonSaveAction;

/**
 * ����ծ�񱣴��ύ
 * 
 * @author wangzc
 * 
 */
public class ApplySaveCommitAction extends CommonSaveAction<AggInvestApplyVO> {
		
	@Override
	protected AggInvestApplyVO doBusinessSave(AggInvestApplyVO operaVO) {
		try {
			this.doBefore(operaVO);
			// ���ö����ű���ִ�б���
			operaVO = (AggInvestApplyVO) callActionScript(
					TMIMFConst.CONST_ACTION_SAVEBASE,
					TMIMFConst.CONST_BILLTYPE_APPLY,
					new AggInvestApplyVO[] { operaVO });
		
			// ���ö����ű���ִ���ύ
			operaVO = (AggInvestApplyVO) super.doCommitProcess(
					new AggInvestApplyVO[] { operaVO }, null);
			
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("�����ύʧ�ܣ�" + e.getMessage());
		}

		return operaVO;
	}
	
	@Override
	protected String getBillTypeCode() {
		return TMIMFConst.CONST_BILLTYPE_APPLY;
	}
	
	@Override
	protected String getActionCode() {
		return TMIMFConst.CONST_ACTION_SAVE;
	}

	@Override
	protected String getPageCode() {
		return TMIMFConst.CONST_PAGECODE_ADJUST_CARD;
	}

	/**
	 * ����ǰ������
	 * 
	 * @param operaVO
	 * @throws BusinessException
	 */
	private void doBefore(AggInvestApplyVO operaVO) throws BusinessException {

		InvestApplyVO headVO = operaVO.getParentVO();
		if (StringUtil.isEmptyWithTrim(headVO.getPrimaryKey())) {
			// ���������Ϣ
			headVO.setAttributeValue("creator", SessionContext.getInstance()
					.getClientInfo().getUserid());
			headVO.setAttributeValue("creationtime", new UFDate(SessionContext
					.getInstance().getClientInfo().getBizDateTime()));
			headVO.setAttributeValue("version", 1);
			headVO.setAttributeValue("versiontime", new UFDate(SessionContext
					.getInstance().getClientInfo().getBizDateTime()));
			headVO.setAttributeValue("holdmoney", headVO.getMoney());
			if(headVO.getUnitnetvalue() != null && headVO.getApplynumber() != null){
				headVO.setAttributeValue("holdnumber", headVO.getApplynumber());
			}
			headVO.setPk_group(getGroupByOrg(headVO.getPk_org()));
		}
	}

	/**
	 * ��ѯ������֯���ڼ���
	 * 
	 * @param pk_org
	 * @return
	 */
	public static String getGroupByOrg(String pk_org) throws BusinessException {
		IOrgUnitPubService_C orgUnitQryService = ServiceLocator
				.find(IOrgUnitPubService_C.class);
		String pk_group = null;
		OrgVO[] orgVOs = orgUnitQryService.getOrgs(new String[] { pk_org },
				new String[] { TMIMFConst.FIELD_PK_GROUP });
		if (orgVOs == null || orgVOs.length <= 0) {
			ExceptionUtils.wrapBusinessException("��ȡ������֯��Ӧ�ļ���ʧ�ܡ�");
		}
		pk_group = (String) orgVOs[0]
				.getAttributeValue(TMIMFConst.FIELD_PK_GROUP);
		return pk_group;
	}

	/**
	 * �����ȴ���
	 * 
	 * @param vos
	 * @return
	 * @throws BusinessException
	 */
	@Override
	protected AggInvestApplyVO processDigit(AggInvestApplyVO operaVO) {
		InvestApplyVO vo = operaVO.getParentVO();

		try {
			vo = (InvestApplyVO) ApplyQueryUtil.processPrecision(vo, true,
					getBusiDate());
			operaVO.setParentVO(vo);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return operaVO;
	}

	/**
	 * ��ȡ��ǰ��¼�û�����
	 * 
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo()
				.getBizDateTime());
	}

}
