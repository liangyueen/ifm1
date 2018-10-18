package nccloud.web.ifm.income.handler;

import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.imf.constants.TMIMFConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nccloud.framework.web.container.SessionContext;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardFormulaHandler;
import nccloud.framework.web.ui.pattern.billcard.CardHeadAfterEditEvent;
import nccloud.web.ifm.investapply.util.ApplyQueryUtil;
import nccloud.web.tmpub.afteredit.bean.UIProp;
import nccloud.web.tmpub.handler.AbstractCommonAfterEditHandler;

public class IncomeAmountAfterEditHandler extends AbstractCommonAfterEditHandler<CardHeadAfterEditEvent, BillCard>{

	@Override
	protected BillCard processAfterEdit(CardHeadAfterEditEvent event, UIProp uiProp) throws BusinessException {
		BillCard card = event.getCard();
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		AggInvestApplyVO vo = processor.fromBillCard(card);
		if (vo == null || vo.getParentVO() == null) {
			throw new BusinessException("编辑后事件处理错误！");
		}
		InvestApplyVO pvo = (InvestApplyVO) ApplyQueryUtil.processPrecision(vo.getParent(), true, getBusiDate());
		vo.setParentVO(pvo);
		card = doReturn(vo);
		return card;
	}
	
	/**
	 * 获取当前业务时间
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
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
}
