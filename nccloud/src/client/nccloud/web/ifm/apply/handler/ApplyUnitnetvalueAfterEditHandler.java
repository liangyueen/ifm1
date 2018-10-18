package nccloud.web.ifm.apply.handler;

import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.imf.constants.TMIMFConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nccloud.framework.web.container.SessionContext;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardFormulaHandler;
import nccloud.framework.web.ui.pattern.billcard.CardHeadAfterEditEvent;
import nccloud.web.tmpub.afteredit.bean.UIProp;
import nccloud.web.tmpub.handler.AbstractCommonAfterEditHandler;

public class ApplyUnitnetvalueAfterEditHandler extends AbstractCommonAfterEditHandler<CardHeadAfterEditEvent, BillCard>{
	@Override
	protected BillCard processAfterEdit(CardHeadAfterEditEvent event,
			UIProp uiProp) throws BusinessException {
		BillCard card = event.getCard();
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		AggInvestApplyVO vo = processor.fromBillCard(card);
		if(vo.getParentVO().getUnitnetvalue() != null && vo.getParentVO().getOlcrate() != null && vo.getParentVO().getApplynumber() != null){
			vo.getParentVO().setMoney(vo.getParentVO().getUnitnetvalue().multiply(vo.getParentVO().getApplynumber()));
			vo.getParentVO().setOlcmoney(vo.getParentVO().getMoney().multiply(vo.getParentVO().getOlcrate()));
		}
		card = doReturn(vo);
		return card;
	}

	/**
	 * 处理返回值
	 * @param vos
	 * @return
	 */
	protected BillCard doReturn(AggInvestApplyVO vo) {
		// 把结果进行封装返回
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		BillCard billCard = processor.convert(TMIMFConst.CONST_PAGECODE_ADJUST_CARD, vo);
		// 卡片参照翻译
		Translator translator = new Translator();
		translator.translate(billCard);
		// 处理模板显示公式
		BillCardFormulaHandler handler = new BillCardFormulaHandler(billCard);
		handler.handleLoadFormula();
		// handler.handleBodyLoadFormula();
		return billCard;
	}
	
	/**
	 * 单据类型编码
	 * @return
	 */
	protected String getBillTypeCode() {
		return TMIMFConst.CONST_BILLTYPE_APPLY;
	}
	
	/**
	 * 获取当前业务时间
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
	}
}
