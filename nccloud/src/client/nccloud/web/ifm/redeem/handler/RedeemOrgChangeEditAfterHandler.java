package nccloud.web.ifm.redeem.handler;

import nccloud.web.tmpub.afteredit.bean.UIProp;
import nccloud.web.tmpub.handler.AbstractCommonAfterEditHandler;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardFormulaHandler;
import nccloud.framework.web.ui.pattern.billcard.CardHeadAfterEditEvent;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.investapply.util.ApplyQueryUtil;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.imf.constants.TMIMFConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.voifm.ifm.util.RedeemUtil;

import org.apache.commons.lang.StringUtils;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.container.SessionContext;


public class RedeemOrgChangeEditAfterHandler extends AbstractCommonAfterEditHandler<CardHeadAfterEditEvent, BillCard>{

	@Override
	protected BillCard processAfterEdit(CardHeadAfterEditEvent event,
			UIProp uiProp) throws BusinessException {
		BillCard card = event.getCard();
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		AggInvestRedeemVO vo = processor.fromBillCard(card);
		loadBaseInforByOrg(vo);
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
	protected AggInvestRedeemVO loadBaseInforByOrg(AggInvestRedeemVO vo) throws BusinessException {
		String pk_org = vo.getParentVO().getPk_org();
		if (StringUtils.isBlank(pk_org)) {
			return vo;
		}
		setRedeemDefaultValue(vo);
		return vo;
	}
	
	
	/**
	 * �����깺����Ĭ��ֵ
	 * @param card
	 * @param uiState
	 * @throws BusinessException
	 */
	protected void setRedeemDefaultValue(AggInvestRedeemVO vo) throws BusinessException {
		InvestRedeemVO pvo = vo.getParentVO();
		
		//pvo.setPk_org(pvo.getPk_org());		
		//pvo.setPk_group(RedeemUtil.getGroupByOrg(pvo.getPk_org()));//pk_currtype
		pvo.setPk_currtype(RedeemUtil.getOrgStandardCurrtype(pvo.getPk_org()));
		pvo = (InvestRedeemVO) RedeemUtil.processPrecision(pvo, true, getBusiDate());
		//newvo.setRedeemdate(getBusiDate());
		//newvo.setIncomedate(getBusiDate());
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
	
	
	
}
