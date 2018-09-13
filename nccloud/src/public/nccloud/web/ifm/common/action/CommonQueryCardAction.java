package nccloud.web.ifm.common.action;

import nc.vo.ifm.OperatorParam;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.processor.template.ExtBillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCard;
import nccloud.web.common.bean.CardOperatorParam;

/**
 * ��Ƭҳ���ѯ
 * @author futao3
 * @date Aug 29, 2018
 */
public abstract class CommonQueryCardAction<T extends AbstractBill> extends AbstractPfAction<T> {
	
	@Override
	public Object doAction(IRequest request) {
		//��ȡǰ���������
		OperatorParam operaParam = (OperatorParam) this.getRequestParam(request);
		// ǰ����
		doBefore();
		//����pk��ѯ����
		T[] resultVOs = this.queryBillsByPk(operaParam.getPk());
		// ��������
		doAfter();
		//����ǰ�˷��ؽ��
		ExtBillCard billCard = this.buildFontResult(operaParam, resultVOs);
		// ������
		processDigit();
		return billCard;
	}

	/**
	 * ����pk��ѯ����
	 * 
	 * @param currPks
	 * @return
	 */
	protected abstract T[] queryBillsByPk(String pk);
	
	/**
	 * ����ǰ�˷��ؽ��
	 * @param operaParam
	 * @param resultVOs
	 * @return
	 */
	protected ExtBillCard buildFontResult(CardOperatorParam operaParam, T[] resultVOs){
		// �ѽ�����з�װ����
		ExtBillCardConvertProcessor processor = new ExtBillCardConvertProcessor();
		ExtBillCard billCard = new ExtBillCard();
		if(resultVOs == null || resultVOs.length == 0){
			return billCard;
		}
		billCard = processor.convert(operaParam.getPageCode(), resultVOs[0]);
		//����
		Translator translator = new Translator();
		translator.translate(billCard);
		return billCard;
	}
	/**
	 * ����ǰ�˷��ؽ��
	 * @param operaParam
	 * @param resultVOs
	 * @return
	 */
	protected ExtBillCard buildFontResult(OperatorParam operaParam, T[] resultVOs){
		// �ѽ�����з�װ����
		ExtBillCardConvertProcessor processor = new ExtBillCardConvertProcessor();
		ExtBillCard billCard = new ExtBillCard();
		if(resultVOs == null || resultVOs.length == 0){
			return billCard;
		}
		billCard = processor.convert(operaParam.getPageCode(), resultVOs[0]);
		//����
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
