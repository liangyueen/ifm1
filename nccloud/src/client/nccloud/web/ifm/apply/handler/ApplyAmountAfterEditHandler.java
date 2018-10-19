package nccloud.web.ifm.apply.handler;

import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.imf.constants.TMIMFConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nccloud.framework.web.container.SessionContext;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardFormulaHandler;
import nccloud.framework.web.ui.pattern.billcard.CardHeadAfterEditEvent;
import nccloud.web.tmpub.afteredit.bean.UIProp;
import nccloud.web.tmpub.handler.AbstractCommonAfterEditHandler;

public class ApplyAmountAfterEditHandler extends AbstractCommonAfterEditHandler<CardHeadAfterEditEvent, BillCard>{

	@Override
	protected BillCard processAfterEdit(CardHeadAfterEditEvent event,
			UIProp uiProp) throws BusinessException {
		BillCard card = event.getCard();
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		AggInvestApplyVO vo = processor.fromBillCard(card);
		loadBaseInfor(vo);
		card = doReturn(vo);
		return card;
	}
	
	protected void loadBaseInfor(AggInvestApplyVO vo){
		InvestApplyVO pvo = vo.getParentVO();
		UFDouble money = pvo.getMoney();
		UFDouble olcrate = pvo.getOlcrate();
		Integer applynumber = pvo.getApplynumber();
		if(money != null && olcrate != null){
			pvo.setOlcmoney(money.multiply(olcrate));
		}
		if(money != null && applynumber != null){
			pvo.setUnitnetvalue(money.div(applynumber));
		}
		if(money != null && olcrate != null){
			pvo.setOlcmoney(money.multiply(olcrate));
		}	
	}

	/**
	 * ������ֵ
	 * @param vos
	 * @return
	 */
	protected BillCard doReturn(AggInvestApplyVO vo) {
		// �ѽ�����з�װ����
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		BillCard billCard = processor.convert(TMIMFConst.CONST_PAGECODE_ADJUST_CARD, vo);
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
		return TMIMFConst.CONST_BILLTYPE_APPLY;
	}
	
	/**
	 * ��ȡ��ǰҵ��ʱ��
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
	}
	
}
