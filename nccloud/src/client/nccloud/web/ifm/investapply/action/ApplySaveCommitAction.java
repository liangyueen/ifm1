package nccloud.web.ifm.investapply.action;


import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
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
 * @author wangzc
 *
 */
public class ApplySaveCommitAction extends CommonSaveAction<AggInvestApplyVO> {
	
	ApplyCommitAction ac = new ApplyCommitAction();
	
	
	@Override
	protected AggInvestApplyVO doBusinessSave(AggInvestApplyVO operaVO) {
		try {
			this.doBefore(operaVO);
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		try {
			// ���ö����ű���ִ�б���
			operaVO = (AggInvestApplyVO) callActionScript(
					TMIMFConst.CONST_ACTION_SAVEBASE,
					TMIMFConst.CONST_BILLTYPE_APPLY,
					new AggInvestApplyVO[] { operaVO });
			
			// ���ö����ű���ִ���ύ
			operaVO = (AggInvestApplyVO) callActionScript(
					TMIMFConst.CONST_ACTION_SAVE,
					TMIMFConst.CONST_BILLTYPE_APPLY,
					new AggInvestApplyVO[] { operaVO });
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("�����ύʧ�ܣ�" + e.getMessage());
		}
			
		return operaVO;
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
			headVO.setPk_group(getGroupByOrg(headVO.getPk_org()));
		}
	}
	
	
	/**
	 * ��ѯ������֯���ڼ���
	 * @param pk_org
	 * @return
	 */
	public static String getGroupByOrg(String pk_org) throws BusinessException {
		IOrgUnitPubService_C orgUnitQryService = ServiceLocator.find(IOrgUnitPubService_C.class);
		String pk_group = null;
		OrgVO[] orgVOs = orgUnitQryService.getOrgs(new String[] { pk_org }, new String[] { TMIMFConst.FIELD_PK_GROUP });
		if(orgVOs == null || orgVOs.length <= 0){
			ExceptionUtils.wrapBusinessException("��ȡ������֯��Ӧ�ļ���ʧ�ܡ�");
		}
		pk_group = (String) orgVOs[0].getAttributeValue(TMIMFConst.FIELD_PK_GROUP);
		return pk_group;
	}
	
}

//	@Override
//	protected AggInvestApplyVO doBusinessSave(AggInvestApplyVO operaVO) {
//		// ��ֵ�����˵���Ϣ
//		this.doBefore(operaVO);
//		// ��ȡService
//		IApplyMaintain iApplyMaintain = ServiceLocator.find(IApplyMaintain.class);
//		IIFMApplyQueryService service = ServiceLocator.find(IIFMApplyQueryService.class);
//		AggInvestApplyVO[] saveVOs = new AggInvestApplyVO[] { operaVO };
//		try {
//			// ���vo��û������, ����������; ��������޸ı���
//			if (operaVO.getParent().getPrimaryKey() == null) {
//				saveVOs = iApplyMaintain.insert(saveVOs, saveVOs);
//			} else {
//				// �޸ı���ǰҪ�����ݿ��е�ԭʼ��Ϣ�����
//				AggInvestApplyVO[] vos = service.getAggVOsByPKs(saveVOs[0]
//						.getPrimaryKey());
//				// update����Ҫ��֤��������vo�����ݲ�ͬ, ����ִ��update���
//				saveVOs = iApplyMaintain.update(saveVOs, vos);
//			}
//		} catch (BusinessException e) {
//			ExceptionUtils.wrapBusinessException("���浥��ʧ�ܣ�" + e.getMessage());
//		}
//		// �ύ
//		try {
//			saveVOs = iApplyMaintain.commit(saveVOs, saveVOs);
//		} catch (BusinessException e) {
//			ExceptionUtils.wrapBusinessException("�ύ����ʧ�ܣ�" + e.getMessage());
//		}
//		return saveVOs[0];
//	}
//
//	@Override
//	protected String getPageCode() {
//		//return "36620GP_CARD";
//		return TMIMFConst.CONST_PAGECODE_ADJUST_CARD;
//	}
//
//	/**
//	 * ����ǰ����
//	 * 
//	 * @param operaVO
//	 */
//	private void doBefore(AggInvestApplyVO operaVO) {
//		// ��SessionContext�л�ȡ�ͻ�����Ϣ
//		ClientInfo clientInfo = SessionContext.getInstance().getClientInfo();
//		InvestApplyVO head = ((InvestApplyVO) operaVO.getParent());
//		// ����һЩ��ʼ����Ϣ
//		if (head.getPk_apply() == null) {
//			head.setCreator(clientInfo.getUserid());
//			head.setPk_group(clientInfo.getPk_group());
//			head.setPk_billtypecode(TMIMFConst.CONST_BILLTYPE_APPLY);
//			//head.setBusistatus(-1);
//			head.setVbillstatus(-1);
//		} else {
//			head.setModifier(clientInfo.getUserid()); 
//		}
//	}



