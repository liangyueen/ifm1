package nccloud.web.tmifm.common.action;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.processor.template.ExtBillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCard;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCardOperator;

/**
 * ���ݱ���
 * @author futao3
 * @date Aug 29, 2018
 */
public abstract class CommonSaveAction<T extends AbstractBill> extends AbstractPfAction<T> {
	
	@Override
	public Object doAction(IRequest request) {
		//����ǰ�����󣬻�ȡVO����
		T operaVO = this.getVODate(request);
		// ǰ����
		doBefore();
		//ִ��ҵ�񱣴����
		T resultVO = this.doBusinessSave(operaVO);
		// ��������
		doAfter();
		//����ǰ�˷��ؽ��
		ExtBillCard billCard = this.buildFontResult(resultVO);
		// ���ȴ���
		processDigit();
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

	/**
	 * ��ȡ����VO���ݣ���Ƭ���棬����ֻ��һ������
	 * @param request
	 * @return
	 */
	protected T getVODate(IRequest request){
		// ����request
		ExtBillCardOperator operator = new ExtBillCardOperator();
		T aggvo =  operator.toBill(request);
		return aggvo;
	}
	
	@Override
	protected void doBefore() {
		/*// ��SessionContext�л�ȡ�ͻ�����Ϣ
		ClientInfo clientInfo = SessionContext.getInstance().getClientInfo();
		ContractVO head = ((ContractVO)vo.getParent());
		// ����һЩ��ʼ����Ϣ
		if(head.getPk_contract()==null){
			head.setCreator(clientInfo.getUserid());
			head.setPk_group(clientInfo.getPk_group());
			head.setPk_billtypecode(TMCFMConst.CONST_BILLTYPE_CONTRACT);
			head.setBusistatus(-1);
			head.setVbillstatus(-1);
		}else{
			head.setModifier(clientInfo.getUserid());
		}*/
	}
	
	
	
	
	/**
	 * ҵ�񱣴����
	 * @param operaVO
	 * @return
	 */
	protected abstract T doBusinessSave(T operaVO);

	@Override
	protected void doAfter() {
		
	}
	
	/**
	 * ����ǰ�˷��ؽ��
	 * 
	 * @param operaParam
	 * @param resultVOs
	 * @return
	 */
	protected ExtBillCard buildFontResult(T resultVO) {
		// �ѽ�����з�װ����
		ExtBillCard billCard = new ExtBillCard();
		if (resultVO == null) {
			return billCard;
		}
		ExtBillCardConvertProcessor processor = new ExtBillCardConvertProcessor();
		billCard = processor.convert(this.getPageCode(), resultVO);
	    //����
		Translator translator = new Translator();
		translator.translate(billCard);
		return billCard;
	}

	@Override
	protected T[] processRequestParam(IRequest request) {
		return null;
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
	
	/**
	 * ��ȡҳ�����
	 * @return
	 */
	protected abstract String getPageCode();
	


}
