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
			// ���������ű�ִ���������
			CloudPFlowContext context = new CloudPFlowContext();
			context.setActionName(getSaveActionCode());
			context.setBillType(getBillTypeCode());
			context.setBillVos(vos);
			Logger.debug("��ʼ���ö����ű� ActionName[" + getSaveActionCode()
					+ "] BillType[" + getBillTypeCode() + "]...");
			vos = (AggInvestRedeemVO[]) getCPFlowService().exeScriptPFlow(
					context);
			Logger.debug("���ö����ű� ActionName[" + getSaveActionCode()
					+ "] BillType[" + getBillTypeCode() + "]����");
			vos = getEQueryService().getAggVOsByPKs(
					vos[0].getParentVO().getPk_redeem());

			// �ύ����
			// �жϵ����Ƿ�������������ѡ��ִ�ж�Ӧ�Ķ����ű������ű�
			boolean hasFlow = NCCFlowUtils.hasApproveflowDef(getBillTypeCode(),
					vos[0]);
			// ���������ű�ִ���������
			CloudPFlowContext commitcontext = new CloudPFlowContext();
			commitcontext.setActionName(getSubmitActionCode());
			commitcontext.setBillType(getBillTypeCode());
			commitcontext.setBillVos(vos);
			Logger.debug("��ʼ���ö����ű� ActionName[" + getSubmitActionCode()
					+ "] BillType[" + getBillTypeCode() + "]...");
			Object[] results = null;
			if (hasFlow) {
				// ���������ĵ����ύ
				results = getCPFlowService().exeScriptPFlow(commitcontext);
			} else {
				// ���������ĵ����ύ
				// ���������ύ���������޴˴���ts��У�鲻ͨ��
				Map<Object, Object> eParam = new HashMap<Object, Object>();
				eParam.put(TMIFMConst.CONST_PFLOW_ISRELOADBILL, true);
				commitcontext.seteParam(eParam);
				results = getCPFlowService().exeScriptPFlow_CommitNoFlowBatch(
						commitcontext);
				PfProcessBatchRetObject retObject = (PfProcessBatchRetObject) results[0];
				// ִ�ж����ű������������Ϣ
				if (retObject.getRetObj() == null
						|| retObject.getRetObj().length == 0) {
					String errMsg = retObject.getExceptionMsg();
					Logger.error(errMsg);
					throw new BusinessException(errMsg);
				} else {
					results = retObject.getRetObj();
				}
			}
			Logger.debug("���ö����ű� ActionName[" + getSubmitActionCode()
					+ "] BillType[" + getBillTypeCode() + "]����");
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
	 * ������յ�ǰ̨����
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
	 * ǰ����
	 * 
	 * @param pks
	 * @return
	 */
	protected AggInvestRedeemVO[] doBefore(AggInvestRedeemVO[] vos)
			throws BusinessException {
		if (vos == null || vos.length < 1) {
			throw new BusinessException("��������ݲ���Ϊ�գ�");
		}
		try {
			InvestRedeemVO vo = vos[0].getParentVO();
			if(vo.getRedeemnumber()!=null){
				Integer lastNum =vo.getApplynumber()-vo.getRedeemnumber();
				if(lastNum<0){
					throw new BusinessException("��ط������������깺����������ǰ�ĳ��з���ΪΪ��"+vo.getApplynumber()+"");
				}
				UFDouble UFlastNum = new UFDouble(vo.getRedeemmoney());
				if(vo.getRedeemmoney()==null){
					vo.setRedeemmoney(UFlastNum.multiply(vo.getUnitnetvalue()).toString());
				}
			}
			else if(vo.getHoldmoeny()!=null){
				if (vo.getHoldmoeny().sub(vo.getRedeemmoney()).compareTo(UFDouble.ZERO_DBL) < 0|| vo.getHoldmoeny().compareTo(UFDouble.ZERO_DBL) <= 0) {
					throw new BusinessException("���н��С����ؽ�����ǰ�ĳ��н��Ϊ��"
							+ vo.getHoldmoeny() + "");
			}
				
			}
			// �����Ƿ���������Ϣ�ж����������滹���޸ı���
			if (StringUtils.isBlank(vo.getPk_redeem())) {
				// ���õ���Ĭ��ֵ
				// vo.setVbillno(getBillTypeCode());
				// Integer vbillstatus = (Integer)
				// BillStatusEnum.COMMIT.value();//�ύ
				Integer billstatus = (Integer) RedeemStatusEnum.�����.value();// �����
				// vo.setAttributeValue("vbillstatus", vbillstatus);
				vo.setAttributeValue("billstatus", billstatus);
			

				vo.setPk_group(RedeemUtil.getGroupByOrg(vo.getPk_org()));
				vo.setPk_billtypecode(getBillTypeCode());
				vo.setBillmakedate(getBusiDate());
				vo.setBillmaketime(new UFDateTime(SessionContext.getInstance()
						.getClientInfo().getBizDateTime()));
			} else {
				// У��������
				AggInvestRedeemVO[] oldvo = getEQueryService().getAggVOsByPKs(
						new String[] { vo.getPk_redeem() });
				if (oldvo == null || oldvo.length < 1) {
					throw new BusinessException("�޸ĵĵ�������������������ˢ�º����޸ı��棡");
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
	 * ������ֵ
	 * 
	 * @param vos
	 * @return
	 */
	protected BillCard doReturn(AggInvestRedeemVO[] vos) {
		// �ѽ�����з�װ����
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		BillCard billCard = processor.convert(
				TMIFMConst.CONST_PAGECODE_CONTRACT_CARD, vos[0]);
		// ��Ƭ���շ���
		Translator translator = new Translator();
		translator.translate(billCard);
		// ����ģ����ʾ��ʽ
		BillCardFormulaHandler handler = new BillCardFormulaHandler(billCard);
		handler.handleLoadFormula();
		// handler.handleBodyLoadFormula();
		return billCard;
	}

	/**
	 * ��ȡ��ǰ��¼�û�����
	 * 
	 * @return
	 */
	private String getPk_user() {
		return SessionContext.getInstance().getClientInfo().getUserid();
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

	/**
	 * �������ͱ���
	 * 
	 * @return
	 */
	protected String getBillTypeCode() {
		return TMIFMConst.CONST_BILLTYPE_REDEEM;
	}

	/**
	 * ���涯������
	 * 
	 * @return
	 */
	protected String getSaveActionCode() {
		return TMIFMConst.CONST_ACTION_SAVEBASE;
	}

	/**
	 * �ύ��������
	 * 
	 * @return
	 */
	protected String getSubmitActionCode() {
		return TMIFMConst.CONST_ACTION_SAVE;
	}
	/*
	 * @Override protected AggInvestRedeemVO doBusinessSave(AggInvestRedeemVO
	 * operaVO) { // ��ֵ�����˵���Ϣ
	 * 
	 * // ��ȡService
	 * 
	 * try { this.doBefore(operaVO); operaVO= (AggInvestRedeemVO)
	 * callActionScript
	 * (TMIFMConst.CONST_ACTION_SAVEBASE,TMIFMConst.CONST_BILLTYPE_REDEEM,new
	 * AggInvestRedeemVO[]{operaVO}); try { Object result =
	 * super.doCommitProcess(new AggInvestRedeemVO[]{vo}, null);
	 * AggInvestRedeemVO[] vos = (AggInvestRedeemVO[]) result; list.add(vos[0]);
	 * } catch (BusinessException e) { errList.add("���ݱ�ţ�" +
	 * vo.getParentVO().getVbillno() + e.getMessage()); continue; } } catch
	 * (BusinessException e) { ExceptionUtils.wrapBusinessException("���浥��ʧ�ܣ�" +
	 * e.getMessage()); } return operaVO; }
	 * 
	 * 
	 * @Override protected String getPageCode() { return
	 * TMIFMConst.CONST_PAGECODE_CONTRACT_CARD; }
	 *//**
	 * ����ǰ����
	 * 
	 * @param operaVO
	 */
	/*
	 * private void doBefore(AggInvestRedeemVO operaVO) throws
	 * BusinessException{ InvestRedeemVO vo=operaVO.getParentVO(); //���������Ϣ
	 * Integer vbillstatus = (Integer) BillStatusEnum.COMMIT.value();//�ύ
	 * Integer billstatus = (Integer) RedeemStatusEnum.�����.value();//�����
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
	 * BusinessException("���н��С����ؽ�����ǰ�ĳ��н��Ϊ��"+vo.getHoldmoeny()+""); } }
	 *//**
	 * ��ѯ������֯���ڼ���
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
	 * ExceptionUtils.wrapBusinessException("��ȡ������֯��Ӧ�ļ���ʧ�ܡ�"); } pk_group =
	 * (String) orgVOs[0].getAttributeValue(TMIFMConst.FIELD_PK_GROUP); return
	 * pk_group; }
	 *//**
	 * ��ȡ��ǰ��¼�û�����
	 * 
	 * @return
	 */
	/*
	 * private String getPk_user() { return
	 * SessionContext.getInstance().getClientInfo().getUserid(); }
	 *//**
	 * ��ȡ��ǰ��¼�û�����
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
