package nccloud.web.ifm.common.action;


import nc.bs.logging.Logger;
import nc.lightapp.pubapp.web.template.ref.util.StringUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.tmpub.util.ArrayUtil;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCard;
import nccloud.pubitf.riart.pflow.CloudPFlowContext;
import nccloud.pubitf.riart.pflow.ICloudScriptPFlowService;

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
		BillCard billCard = this.buildFontResult(resultVO);
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
		BillCardOperator operator = new BillCardOperator();
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
	 * ���ö����ű����������ݣ�
	 * 
	 * @param actionCode
	 *            ��������
	 * @param billType
	 *            ��������
	 * @param aggVO
	 *            ҵ�񵥾�
	 * @return
	 * @throws BusinessException
	 */
	protected Object callActionScript(String actionCode, String billType,
			T[] aggVOs) throws BusinessException {
		if (StringUtils.isEmpty(actionCode) || aggVOs == null
				|| aggVOs.length == 0) {
			throw new BusinessException("���ö����ű�ʱ�������ƺʹ������ݲ���Ϊ�գ�");
		}
		CloudPFlowContext context = new CloudPFlowContext();
		context.setActionName(actionCode);
		context.setBillType(billType);
		context.setBillVos(aggVOs);
		Logger.debug("��ʼ���ö����ű� ActionName[" + actionCode + "] BillType["
				+ billType + "]...");
		ICloudScriptPFlowService service = ServiceLocator
				.find(ICloudScriptPFlowService.class);
		Object[] result = service.exeScriptPFlow(context);
		Logger.debug("���ö����ű� ActionName[" + actionCode + "] BillType["
				+ billType + "]����");
		if (ArrayUtil.isNull(result)) {
			return null;
		} else {
			return result[0];
		}
	}
	
	/**
	 * ����ǰ�˷��ؽ��
	 * 
	 * @param operaParam
	 * @param resultVOs
	 * @return
	 */
	protected BillCard buildFontResult(T resultVO) {
		// �ѽ�����з�װ����
		BillCard billCard = new BillCard();
		if (resultVO == null) {
			return billCard;
		}
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
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
