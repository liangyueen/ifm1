package nccloud.web.tmifm.common.action;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import nc.bs.logging.Logger;
import nc.vo.imf.constants.TMIMFConst;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.tmpub.util.ArrayUtil;
import nc.vo.tmpub.util.StringUtil;
import nc.vo.uap.pf.PfProcessBatchRetObject;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.processor.template.ExtBillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCard;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCardOperator;
import nccloud.pubitf.riart.pflow.CloudPFlowContext;
import nccloud.pubitf.riart.pflow.ICloudScriptPFlowService;
import nccloud.web.workflow.approve.util.NCCFlowUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * ���ݱ���
 * @author futao3
 * @date Aug 29, 2018
 */
public abstract class CommonSaveAction<T extends AbstractBill> extends AbstractPfAction<T> {
	
	private final String PARAM_CONTENT = "content";
	//�ύʱ�������ѱ��޸ģ����ϴ˲�����ֵΪtrue
	public static String IS_RELOADBILL="IS_RELOADBILL";
	
	@Override
	public Object doAction(IRequest request) {
		//����ǰ�����󣬻�ȡVO����
		T operaVO = this.getVODate(request);
		//���ȴ���
		operaVO = processDigit(operaVO);	
		//����ǰ����
		doBefore();
		//ִ��ҵ�񱣴����
		T resultVO = this.doBusinessSave(operaVO);
		// ��������
		doAfter();
		//����ǰ�˷��ؽ��
		ExtBillCard billCard = this.buildFontResult(resultVO);
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
	 * ���ȴ���
	 * 
	 * @return
	 */
	protected abstract T processDigit(T operaVO);

	
	/**
	 * ��ȡҳ�����
	 * @return
	 */
	protected abstract String getPageCode();
	
	/**
	 * �ύ����
	 * @param commitVOs
	 * @return
	 * @throws BusinessException
	 */
	protected Object doCommitProcess(T[] commitVOs, Map<String, String> extParam) throws BusinessException{
		if(commitVOs == null || commitVOs.length == 0){
			return commitVOs;
		}
		Object result = null;
		//�ж��Ƿ���������
		boolean hasFlow = NCCFlowUtils.hasApproveflowDef(
				this.getBillTypeCode(), commitVOs[0]);
		//�У������ύ�����ű�
		if (hasFlow) {
			result = commitWithApproveFlow(commitVOs, extParam);
		}
		// û�У����ύ������
		else {
			result = commitAutoApprove(commitVOs);
		}
		return result;
	} 
	
	/**
	 * ����������������
	 * 
	 * @param aggVO
	 * @param extParam
	 * @return
	 * @throws BusinessException
	 */
	private Object commitWithApproveFlow(T[] aggVOs,
			Map<String, String> extParam) throws BusinessException {
		// ��ǰ�˻�ȡ��չ�������Ƿ���content���ݣ�������������ָ����Ҫ��ָ�ɲ���ע�뵽�ύ�߼���
		JSONObject content = getContentObj(extParam);
		//���� actioncode
		String actionCode = this.getActionCode();
		//��������
		String billType = this.getBillTypeCode();
		if (content == null) {
			return super.callActionScript(actionCode,
					billType, aggVOs);
		} else {
			Map<Object, Object> contentParam = new HashMap<>();
			contentParam.put(PARAM_CONTENT, content);
			return super.callActionScript(actionCode,
					billType, contentParam,
					aggVOs);
		}
	}
	
	/**
	 * �ύ������
	 * 
	 * @param aggVO
	 * @return
	 * @throws BusinessException
	 */
	private Object commitAutoApprove(T[] aggVOs)
			throws BusinessException {
//		String pk_user = SessionContext.getInstance().getClientInfo()
//				.getUserid();
		//���� actioncode
		String actionCode = this.getActionCode();
		// �����ύ�������ӿ�
		CloudPFlowContext context = new CloudPFlowContext();
		Map<Object, Object> extParam = new HashMap<Object, Object>();
		extParam.put(IS_RELOADBILL, true);
		context.seteParam(extParam);
		context.setActionName(actionCode);
		context.setBillType(this.getBillTypeCode());
		context.setBillVos(aggVOs);
		//���ؽ��
		Object retObj = null;
		//�ύ
		if(TMIMFConst.CONST_ACTION_SAVE.equals(actionCode)){
			retObj = getFlowService().exeScriptPFlow_CommitNoFlowBatch(
					context);
		//�ջ�,�����������ջؼ������ջ�
		}else if(TMIMFConst.CONST_ACTION_UNSAVEBILL.equals(actionCode)
				|| TMIMFConst.CONST_ACTION_UNAPPROVE.equals(actionCode)){
			//�����ջؼ�������������������ջأ�����������ʱ��û����������ֱ�ӽ�����״̬��Ϊ����̬�������ջ�ʱ��״̬����
			//���ԣ�ֱ�ӵ��������ű�
//			retObj = getFlowService().exeScriptPFlow_UnSaveNoFlowBatch(
//					context);
			context.setActionName(TMIMFConst.CONST_ACTION_UNAPPROVE);
			retObj = getFlowService().exeScriptPFlow(context);
		}
//		if (!retObj.getClass().isArray()) {
//			throw new BusinessException("�ύ�������ӿڷ������ݴ���");
//		}
		// �����ؽ��
		Object obj = Array.get(retObj, 0);
		if (obj instanceof PfProcessBatchRetObject) {
			String errMsg = ((PfProcessBatchRetObject) obj).getExceptionMsg();
			// û���쳣��Ϣ�����ִ�гɹ�
			if (StringUtil.isNull(errMsg)) {
				return ArrayUtil.isNull(((PfProcessBatchRetObject) obj)
						.getRetObj()) ? null : ((PfProcessBatchRetObject) obj)
						.getRetObj();
			}
			// ����ִ���쳣��
			else {
				Logger.error(errMsg);
				throw new BusinessException(errMsg);
			}
		} 
//		else {
//			throw new BusinessException("�ύ�������ӿڷ������ݴ���");
//		}
		return retObj;
	}
	
	
	/**
	 * ��ȡǰ�˵���չ����
	 * 
	 * @param extParam
	 * @return
	 */
	private JSONObject getContentObj(Map<String, String> extParam) {
		if(extParam == null){
			return null;
		}
		String contentStr = extParam.get(PARAM_CONTENT);
		if (StringUtil.isNull(contentStr)) {
			return null;
		}
		JSONObject obj = (JSONObject) JSON.parse(contentStr);
		return obj;
	}
	
	/**
	 * ��ȡ���̷���
	 * 
	 * @return
	 */
	private ICloudScriptPFlowService getFlowService() {
		return ServiceLocator.find(ICloudScriptPFlowService.class);
	}
	
}
