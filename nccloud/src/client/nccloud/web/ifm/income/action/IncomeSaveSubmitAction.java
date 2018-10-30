package nccloud.web.ifm.income.action;

import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.imf.constants.TMIMFConst;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.SessionContext;
import nccloud.web.ifm.util.IncomeUtil;
import nccloud.web.tmifm.common.action.CommonSaveAction;

public class IncomeSaveSubmitAction extends CommonSaveAction<AggInvestIncomeVO> {

	@Override
	protected AggInvestIncomeVO doBusinessSave(AggInvestIncomeVO operaVO) {
		try {
			this.doBefore(operaVO);
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		try {
			// ���ö����ű���ִ�б���
			operaVO = (AggInvestIncomeVO) callActionScript(
					TMIFMConst.CONST_ACTION_SAVEBASE,
					TMIFMConst.CONST_BILLTYPE_INCOME,
					new AggInvestIncomeVO[] { operaVO });
			// ���ö����ű���ִ���ύ
			AggInvestIncomeVO[] aggVOs = new AggInvestIncomeVO[] { operaVO };
			operaVO = (AggInvestIncomeVO) super.doCommitProcess(aggVOs, null);
//			AggInvestIncomeVO[] aggVOs = new AggInvestIncomeVO[] { operaVO };
//			aggVOs = (AggInvestIncomeVO[]) super.doCommitProcess(aggVOs, null);
			operaVO = aggVOs[0];
			
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("���浥��ʧ�ܣ�" + e.getMessage());
		}
		return operaVO;
	}

	@Override
	protected String getActionCode() {
		return TMIFMConst.CONST_ACTION_SAVE;
	}

	private boolean doBefore(AggInvestIncomeVO vo) throws BusinessException {
		InvestIncomeVO headVO = vo.getParentVO();
		// ����Ĭ��ֵ
		if (StringUtil.isEmptyWithTrim(headVO.getPrimaryKey())) {
			// ���������Ϣ
			headVO.setAttributeValue("creator", SessionContext.getInstance()
					.getClientInfo().getUserid());
			headVO.setAttributeValue("creationtime", new UFDate(SessionContext
					.getInstance().getClientInfo().getBizDateTime()));
			headVO.setAttributeValue("version", 1);
			headVO.setAttributeValue("versiontime", new UFDate(SessionContext
					.getInstance().getClientInfo().getBizDateTime()));
			headVO.setPk_group(getGroupByOrg(headVO.getPk_org()));
			// �Ƶ��ˣ��Ƶ�ʱ��
			headVO.setAttributeValue("billmaker", SessionContext.getInstance()
					.getClientInfo().getUserid());
			headVO.setAttributeValue("billmakedate", new UFDate(SessionContext
					.getInstance().getClientInfo().getBizDateTime()));
			headVO.setAttributeValue("pk_billtypecode", "3643");
		}

		return true;
	}

	@Override
	protected String getBillTypeCode() {
		return TMIFMConst.CONST_BILLTYPE_INCOME;
	}

	@Override
	protected String getPageCode() {
		return TMIFMConst.CONST_PAGECODE_INCOME_CARD;
	}

	/**
	 * �����ȴ���
	 * 
	 * @param vos
	 * @return
	 * @throws BusinessException
	 */
	@Override
	protected AggInvestIncomeVO processDigit(AggInvestIncomeVO operaVO) {
		InvestIncomeVO vo = operaVO.getParentVO();

		try {
			vo = (InvestIncomeVO) IncomeUtil.processPrecision(vo, true,
					getBusiDate());
			operaVO.setParentVO(vo);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return operaVO;
	}

	/**
	 * ��ȡ��ǰҵ������
	 * 
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo()
				.getBizDateTime());
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
}
