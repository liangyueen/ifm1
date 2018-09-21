package nccloud.web.ifm.common.action;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.processor.template.ExtBillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCard;
import nccloud.ifm.vo.OperatorParam;
import nccloud.ifm.vo.OperatorResult;

/**
 * ���������࣬ɾ�����ύ��ȡ���ύ��ǰ�˴�pk������ȸ���pk��ѯ���ݣ��ٶԲ�ѯ������������
 * 
 * @author futao3
 * @date Aug 29, 2018
 */
public abstract class CommonOperatorAction<T extends AbstractBill> extends
		AbstractPfAction<T> {

	private OperatorParam operaParam;

	private T[] operaVOs;

	private T[] resultVOs;

	private OperatorResult result;

	private ExtBillCard[] billCards;

	@Override
	public Object doAction(IRequest request) {
		// ��ȡǰ���������
		operaParam = (OperatorParam) getRequestParam(request);
		// ǰ̨����У��
		doBefore();
		// ����pk��ǰ̨������Ϣ��ѯ����������
		getBillVObyParam();
		// �Բ�ѯ���ִ�о���ҵ����
		resultVOs = this.doBusinessProcess(operaVOs);
		// ��������
		doAfter();
		// ��Ƭҳ����Ҫ���ز���������ݵ�ǰ�ˣ��б���������ǰ�����µ����б��ѯ�������践�ز��������ݣ����Դ˴����տ�Ƭ�����ؽ��
		billCards = this.buildFontResult(operaParam, resultVOs);
		// ���ȴ���
		processDigit();
		// �������
		doReturn();
		return result;
	}

	private void getBillVObyParam() {
		if (operaParam.getPks() != null) {
			operaVOs = this.queryBillsByPks(operaParam.getPks());
		} else {
			operaVOs = this.queryBillsByParam(operaParam);
		}
	}

	/**
	 * �õ�������Ϣ
	 * 
	 * @return
	 */
	protected abstract String[] getErrormessage();

	/**
	 * ����PK��ѯ����������
	 * 
	 * @param operaPks
	 * @return
	 */
	protected abstract T[] queryBillsByPks(String[] operaPks);

	/**
	 * ����ǰ̨����������ѯ
	 * 
	 * @param operaParam
	 * @return
	 */
	protected abstract T[] queryBillsByParam(OperatorParam operaParam);

	/**
	 * �����ҵ����
	 * 
	 * @param operaPks
	 * @return
	 */
	protected abstract T[] doBusinessProcess(T[] operaVOs);

	/**
	 * ����ǰ�˷��ؽ��
	 * 
	 * @param operaParam
	 * @param resultVOs
	 * @return
	 */
	protected ExtBillCard[] buildFontResult(OperatorParam operaParam,
			T[] resultVOs) {
		// �ѽ�����з�װ����
		ExtBillCardConvertProcessor processor = new ExtBillCardConvertProcessor();
		ExtBillCard billCard = new ExtBillCard();
		if (resultVOs == null || resultVOs.length == 0) {
			return new ExtBillCard[]{billCard};
		}
		ExtBillCard[] billCards = new ExtBillCard[resultVOs.length];
		for (int i=0;i<resultVOs.length;i++) {
			billCard = processor.convert(operaParam.getPageCode(), resultVOs[i]);
			// ����
			Translator translator = new Translator();
			translator.translate(billCard);
			billCards[i]=billCard;
		}
		return billCards;
	}

	@Override
	protected abstract String getActionCode();

	@Override
	protected String getBillTypeCode() {
		return null;
	}

	/**
	 * ǰ̨����У��
	 */
	@Override
	protected void doBefore() {
		if (operaParam.getPkMapTs() == null
				&& (operaParam.getPks() == null || operaParam.getPks().length == 0)) {
			ExceptionUtils.wrapBusinessException("����������󣬲�������pkΪ��");
		}
	}

	@Override
	protected void doAfter() {

	}

	@Override
	protected void doReturn() {
		String[] error = this.getErrormessage();
		result = OperatorResult.buildResult(operaVOs == null ? 0
				: operaVOs.length, resultVOs == null ? 0 : resultVOs.length);
		result.setBillCards(billCards);
		result.setErrormessages(error);
	}

	@Override
	protected void processMsg() {

	}

	@Override
	protected void processDigit() {

	}

	@Override
	protected T[] processRequestParam(IRequest request) {
		return null;
	}

	private OperatorParam getOperaParam() {
		return operaParam;
	}

	private void setOperaParam(OperatorParam operaParam) {
		this.operaParam = operaParam;
	}

	private T[] getOperaVOs() {
		return operaVOs;
	}

	private void setOperaVOs(T[] operaVOs) {
		this.operaVOs = operaVOs;
	}

}
