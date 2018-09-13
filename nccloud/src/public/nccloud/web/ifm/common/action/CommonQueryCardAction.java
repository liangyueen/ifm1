package nccloud.web.ifm.common.action;

import nc.vo.ifm.OperatorParam;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.processor.template.ExtBillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCard;
import nccloud.web.common.bean.CardOperatorParam;

/**
 * 卡片页面查询
 * @author futao3
 * @date Aug 29, 2018
 */
public abstract class CommonQueryCardAction<T extends AbstractBill> extends AbstractPfAction<T> {
	
	@Override
	public Object doAction(IRequest request) {
		//获取前端请求参数
		OperatorParam operaParam = (OperatorParam) this.getRequestParam(request);
		// 前操作
		doBefore();
		//根据pk查询数据
		T[] resultVOs = this.queryBillsByPk(operaParam.getPk());
		// 后续操作
		doAfter();
		//构建前端返回结果
		ExtBillCard billCard = this.buildFontResult(operaParam, resultVOs);
		// 处理精度
		processDigit();
		return billCard;
	}

	/**
	 * 根据pk查询单据
	 * 
	 * @param currPks
	 * @return
	 */
	protected abstract T[] queryBillsByPk(String pk);
	
	/**
	 * 构建前端返回结果
	 * @param operaParam
	 * @param resultVOs
	 * @return
	 */
	protected ExtBillCard buildFontResult(CardOperatorParam operaParam, T[] resultVOs){
		// 把结果进行封装返回
		ExtBillCardConvertProcessor processor = new ExtBillCardConvertProcessor();
		ExtBillCard billCard = new ExtBillCard();
		if(resultVOs == null || resultVOs.length == 0){
			return billCard;
		}
		billCard = processor.convert(operaParam.getPageCode(), resultVOs[0]);
		//翻译
		Translator translator = new Translator();
		translator.translate(billCard);
		return billCard;
	}
	/**
	 * 构建前端返回结果
	 * @param operaParam
	 * @param resultVOs
	 * @return
	 */
	protected ExtBillCard buildFontResult(OperatorParam operaParam, T[] resultVOs){
		// 把结果进行封装返回
		ExtBillCardConvertProcessor processor = new ExtBillCardConvertProcessor();
		ExtBillCard billCard = new ExtBillCard();
		if(resultVOs == null || resultVOs.length == 0){
			return billCard;
		}
		billCard = processor.convert(operaParam.getPageCode(), resultVOs[0]);
		//翻译
		Translator translator = new Translator();
		translator.translate(billCard);
		return billCard;
	}
	
	@Override
	protected String getActionCode() {
		return null;
	}

	@Override
	protected String getBillTypeCode() {
		return null;
	}

	@Override
	protected void doBefore() {
		
	}

	@Override
	protected void doReturn() {
		
	}

	@Override
	protected void processMsg() {
		
	}

	@Override
	protected void processDigit() {
		
	}

	@Override
	protected void doAfter() {
		
	}

	@Override
	protected T[] processRequestParam(IRequest request) {
		return null;
	}

}
