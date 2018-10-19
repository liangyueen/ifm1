package nccloud.web.ifm.redeem.handler;

import nccloud.web.ifm.investapply.util.ApplyQueryUtil;
import nccloud.web.ifm.util.RedeemUtil;
import nccloud.web.tmpub.afteredit.bean.UIProp;
import nccloud.web.tmpub.handler.AbstractCommonAfterEditHandler;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardFormulaHandler;
import nccloud.framework.web.ui.pattern.billcard.CardHeadAfterEditEvent;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.ifm.IIFMApplyQueryService;
import nc.itf.ifm.IInvestRedeemQueryService;
import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.vo.ifac.fixdepositapply.VbillStateEnum;
import nc.vo.ifm.RedeemStatusEnum;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.imf.constants.TMIMFConst;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.pattern.data.IRowSet;

import org.apache.commons.lang.StringUtils;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.container.SessionContext;


public class RedeemProChangeEditAfterHandler extends AbstractCommonAfterEditHandler<CardHeadAfterEditEvent, BillCard>{

	@Override
	protected BillCard processAfterEdit(CardHeadAfterEditEvent event,
			UIProp uiProp) throws BusinessException {
		BillCard card = event.getCard();
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		AggInvestRedeemVO vo = processor.fromBillCard(card);
		loadBaseInforByPro(event,vo);
		card = doReturn(vo);
		return card;
	}
	
	/**
	 * ���غ���֯��ص�Ĭ�ϵ�ֵ
	 * @param form ǰ��ҳ������
	 * @param uiState �����״̬
	 * @return �����ǰ��ҳ�������
	 * @throws BusinessException
	 */
	protected AggInvestRedeemVO loadBaseInforByPro(CardHeadAfterEditEvent event,AggInvestRedeemVO vo) throws BusinessException {
		String pk_org = vo.getParentVO().getPk_org();
		if (StringUtils.isBlank(pk_org)) {
			return vo;
		}
		setRedeemDefaultValue(event,vo);
		return vo;
	}
	
	
	/**
	 * �����깺����Ĭ��ֵ
	 * @param card
	 * @param uiState
	 * @throws BusinessException
	 */
	protected void setRedeemDefaultValue(CardHeadAfterEditEvent event,AggInvestRedeemVO vo) throws BusinessException {
		InvestRedeemVO pvo = vo.getParentVO();
		String pk_apply = event.getNewvalue().getValue().toString();
		IIFMApplyQueryService service = ServiceLocator
				.find(IIFMApplyQueryService.class);
		AggInvestApplyVO[] resultVOs = null;
		resultVOs = service.queryApplyByPks(new String[] { pk_apply });
		
		Integer vbillstatus = (Integer) BillStatusEnum.FREE.value();
		
		Integer billstatus =   (Integer) RedeemStatusEnum.���ύ.value();
		UFDate billmakedate = new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
	
		String billmaker = SessionContext.getInstance().getClientInfo().getUserid();
		String pk_olccurr = RedeemUtil.getOrgStandardCurrtype(pvo.getPk_org());
		pvo.setAttributeValue("pk_olccurr",pk_olccurr);
		pvo.setAttributeValue("pk_org", pvo.getPk_org());
		pvo.setAttributeValue("pk_group", getGroupByOrg(pvo.getPk_org()));
		pvo.setAttributeValue("vbillstatus", vbillstatus);
		pvo.setAttributeValue("billstatus", billstatus);
		pvo.setAttributeValue("billmakedate", billmakedate);
		pvo.setAttributeValue("billmaker", billmaker);
		
		pvo.setAttributeValue("productcode", resultVOs[0].getParentVO().getProductcode());
		pvo.setAttributeValue("productname", resultVOs[0].getParentVO().getProductname());
		pvo.setAttributeValue("issuebank", resultVOs[0].getParentVO().getIssuebank());
		pvo.setAttributeValue("expectedrate", resultVOs[0].getParentVO().getExpectedrate());
		Integer inday=0;
		if(resultVOs[0].getParentVO().getInterestdate().equals("1")){
			 inday = 365;
		}else{
			 inday = 360;
		}
		pvo.setAttributeValue("interestday",inday);
		pvo.setAttributeValue("investaccount", resultVOs[0].getParentVO().getInvest());
		pvo.setAttributeValue("gatheringaccount", resultVOs[0].getParentVO().getSettleaccount());
		pvo.setAttributeValue("pk_currtype", resultVOs[0].getParentVO().getPk_currtype());
		pvo.setAttributeValue("olcrate", resultVOs[0].getParentVO().getOlcrate());
		//pvo.setAttributeValue("olcmoney", resultVOs[0].getParentVO().getOlcmoney());
		pvo.setAttributeValue("glcrate", resultVOs[0].getParentVO().getGlcrate());
		pvo.setAttributeValue("gllcrate", resultVOs[0].getParentVO().getGllcrate());
		if(resultVOs[0].getParentVO().getApplynumber()!=null && resultVOs[0].getParentVO().getApplynumber()>0){
			Integer lastNum = RedeemUtil.isApplyNumNoExists(pvo,pk_apply,resultVOs[0].getParentVO().getApplynumber());
			pvo.setAttributeValue("holdnumber", lastNum);		
		}else{
			//����ѡ���Ʒ�ĳ��н��(��ƽ��-����ܶ�)
			UFDouble holdMoney = RedeemUtil.isApplyMoneyNoExists(pvo,pk_apply,resultVOs[0].getParentVO().getMoney());
			pvo.setAttributeValue("holdmoney", holdMoney);
		}
		pvo.setAttributeValue("redeemdate", getBusiDate());
		pvo.setAttributeValue("incomedate", getBusiDate());
		pvo.setAttributeValue("pk_srcbill", resultVOs[0].getParentVO().getPk_apply());
		pvo.setAttributeValue("pk_srcbilltype", resultVOs[0].getParentVO().getPk_billtypeid());
		pvo.setAttributeValue("srcbilltypecode", resultVOs[0].getParentVO().getPk_billtypecode());
		pvo.setAttributeValue("srcbillno", resultVOs[0].getParentVO().getVbillno());
		pvo.setPk_olccurr(RedeemUtil.getOrgStandardCurrtype(pvo.getPk_org()));
		vo.setParentVO(pvo);
	}
	
	
	/**
	 * ������ֵ
	 * @param vos
	 * @return
	 */
	protected BillCard doReturn(AggInvestRedeemVO vo) {
		// �ѽ�����з�װ����
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		BillCard billCard = processor.convert(TMIFMConst.CONST_PAGECODE_CONTRACT_CARD, vo);
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
	 * �������ͱ���
	 * @return
	 */
	protected String getBillTypeCode() {
		return TMIFMConst.CONST_BILLTYPE_REDEEM;
	}
	
	/**
	 * ��ȡ��ǰҵ��ʱ��
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
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

	
}
