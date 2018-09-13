package nccloud.web.ifm.common.action;

import java.util.Map;

import nc.vo.ifm.OperatorParam;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nccloud.framework.core.json.IJson;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nc.vo.pub.BusinessException;
import nccloud.web.common.bean.CardOperatorParam;
import nc.lightapp.pubapp.web.template.ref.util.StringUtils;
import nccloud.pubitf.riart.pflow.CloudPFlowContext;
import nc.bs.logging.Logger;
import nccloud.framework.service.ServiceLocator;
import nccloud.pubitf.riart.pflow.ICloudScriptPFlowService;
import nc.vo.tmpub.util.ArrayUtil;
/**
 * Ͷ���ʹ���Action
 * 
 * @author wuzhwa
 * @version ncc1.0 
 * 2018��7��31��
 */
public abstract class AbstractPfAction<T extends AbstractBill> extends AbstractAction<T> {
	
	@Override
	public Object doAction(IRequest request) {
		T[] t = processRequestParam(request);
		
		doBefore();
//		doaction();
		if(t.length>0){
			// batch
		}else{
			// single
		}
		doAfter();
		
		return null;
	}
	
	/**
	 * ��ȡ���ݶ���
	 * @return
	 */
	protected abstract String getActionCode();
	
	/**
	 * ��ȡ�������ͱ���
	 * @return
	 */
	protected abstract String getBillTypeCode();
	
	
	/**
	 * ����ǰ
	 */
	protected abstract void doBefore();
	
	/**
	 * ������
	 */
	protected abstract void doAfter();
	
	/**
	 * �����������
	 * @return 
	 */
	protected abstract T[] processRequestParam(IRequest request);
	
	/**
	 * ��ȡǰ���������
	 * @param request
	 * @return
	 */
	protected Object getRequestParam(IRequest request){
		// ����request
		// ǰ�˴��ݲ�����ʽ: {"pks":["xxx","xxx","xxx"...], "pagecode":"3661DEMO_L01"}
		String str = request.read();
		IJson json = JsonFactory.create();
		OperatorParam operaParam  = json.fromJson(str, OperatorParam.class);
		return operaParam;
				
	}
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
	 * ִ�ж����ű��������������
	 * 
	 * @param actionCode
	 *            ��������
	 * @param billType
	 *            ��������
	 * @param eParam
	 *            �������
	 * @param aggVO
	 *            ҵ�񵥾�
	 * 
	 * @return
	 * @throws BusinessException
	 */
	protected Object callActionScript(String actionCode, String billType,
			Map<Object, Object> eParam, T[] aggVOs) throws BusinessException {
		if (StringUtils.isEmpty(actionCode) || aggVOs == null
				|| aggVOs.length == 0) {
			throw new BusinessException("���ö����ű�ʱ�������ƺʹ������ݲ���Ϊ�գ�");
		}
		CloudPFlowContext context = new CloudPFlowContext();
		context.setActionName(actionCode);
		context.setBillType(billType);
		context.setBillVos(aggVOs);
		context.seteParam(eParam);
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
			return result;
		}
	}
	/**
	 * ������ֵ
	 */
	protected abstract void doReturn();
	/**
	 * ��Ϣ����
	 */
	protected abstract void processMsg();
	/**
	 * ���ȴ���
	 */
	protected abstract void processDigit();
}
