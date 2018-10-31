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
import nccloud.pubitf.riart.pflow.CloudPFlowContext;
import nccloud.pubitf.riart.pflow.ICloudScriptPFlowService;
import nccloud.web.workflow.approve.util.NCCFlowUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * �ύҵ�񹫹�������
 * @author futao3
 * @date Sep 4, 2018
 */
public abstract class CommonCommitAction<T extends AbstractBill>  extends CommonOperatorAction<T>{
	
	private final String PARAM_CONTENT = "content";
	//�ύʱ�������ѱ��޸ģ����ϴ˲�����ֵΪtrue
	public static String IS_RELOADBILL="IS_RELOADBILL";  
	
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
	 * ��ȡ���̷���
	 * 
	 * @return
	 */
	private ICloudScriptPFlowService getFlowService() {
		return ServiceLocator.find(ICloudScriptPFlowService.class);
	}
	
	
}
