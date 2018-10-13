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
 * �����ͬ����/�޸ı���
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
		// ��ֵ�����˵���Ϣ
		
		
		// ��ȡService
		
		try {
			this.doBefore(operaVO);
			operaVO= (AggInvestRedeemVO) callActionScript(TMIFMConst.CONST_ACTION_SAVEBASE,TMIFMConst.CONST_BILLTYPE_REDEEM,new AggInvestRedeemVO[]{operaVO});
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("���浥��ʧ�ܣ�" + e.getMessage());
		}
		return operaVO;
	}

	
	  @Override protected String getPageCode() {
		  return TMIFMConst.CONST_PAGECODE_CONTRACT_CARD; }
	 
	/**
	 * ����ǰ����
	 * 
	 * @param operaVO
	 */
	private AggInvestRedeemVO doBefore(AggInvestRedeemVO operaVO) throws BusinessException{
		
		if (operaVO == null) {
			throw new BusinessException("��������ݲ���Ϊ�գ�");
		}
		InvestRedeemVO vo=operaVO.getParentVO();
		// ����������У��
		
		// У����ض��Ƿ񳬹����н��
		AggInvestApplyVO[] resultVOs = null;
		if (vo.getHoldmoeny().sub(vo.getRedeemmoney()).compareTo(UFDouble.ZERO_DBL)<0) {
			throw new BusinessException("���н��С����ؽ�����ǰ�ĳ��н��Ϊ��"+vo.getHoldmoeny()+"");
		}
		ClientInfo clientInfo = SessionContext.getInstance().getClientInfo();
		// �����Ƿ���������Ϣ�ж����������滹���޸ı���
		if (StringUtils.isBlank(vo.getPk_redeem())) {
			// ���õ���Ĭ��ֵ
			vo.setPk_billtypecode(getBillTypeCode());
			
			vo.setPk_group(clientInfo.getPk_group());
			vo.setBillmakedate(getBusiDate());
			vo.setPk_billtypecode(TMIFMConst.CONST_BILLTYPE_REDEEM);
			vo.setBillmaketime(new UFDateTime(SessionContext.getInstance().getClientInfo().getBizDateTime()));
		} else {
			// У��������
			AggInvestRedeemVO[] oldvo = getEQueryService().queryRedeemByPks(new String[]{ vo.getPk_redeem() });
			if (oldvo == null || oldvo.length < 1) {
				throw new BusinessException("�޸ĵĵ�������������������ˢ�º����޸ı��棡");
			}
		}
		vo.setAttributeValue("pk_org_v", 1);
		operaVO.setParent(vo);
		operaVO = this.processDigit(operaVO);
		return operaVO;
	}
	
	/**
	 * ��ѯ������֯���ڼ���
	 * @param pk_org
	 * @return
	 */
	public static String getGroupByOrg(String pk_org) throws BusinessException {
		IOrgUnitPubService_C orgUnitQryService = ServiceLocator.find(IOrgUnitPubService_C.class);
		String pk_group = null;
		OrgVO[] orgVOs = orgUnitQryService.getOrgs(new String[] { pk_org }, new String[] { TMIFMConst.FIELD_PK_GROUP });
		if(orgVOs == null || orgVOs.length <= 0){
			ExceptionUtils.wrapBusinessException("��ȡ������֯��Ӧ�ļ���ʧ�ܡ�");
		}
		pk_group = (String) orgVOs[0].getAttributeValue(TMIFMConst.FIELD_PK_GROUP);
		return pk_group;
	}
	
	/**
	 * ��ȡ��ǰ��¼�û�����
	 * @return
	 */
	private String getPk_user() {
		return SessionContext.getInstance().getClientInfo().getUserid();
	}
	
	/**
	 * ��ȡ��ǰ��¼�û�����
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
	}
		

	/**
	 * �������ͱ���
	 * @return
	 */
	protected String getBillTypeCode() {
		return TMIFMConst.CONST_BILLTYPE_REDEEM;
	}
	/**
	 * �����ȴ���
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
			throw new BusinessException("���򾫶ȴ������");
		}
	}
	

}
