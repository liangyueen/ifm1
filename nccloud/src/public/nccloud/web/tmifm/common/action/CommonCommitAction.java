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
 * 提交业务公共处理类
 * @author futao3
 * @date Sep 4, 2018
 */
public abstract class CommonCommitAction<T extends AbstractBill>  extends CommonOperatorAction<T>{
	
	private final String PARAM_CONTENT = "content";
	//提交时报单据已被修改，加上此参数，值为true
	public static String IS_RELOADBILL="IS_RELOADBILL";  
	
	/**
	 * 提交处理
	 * @param commitVOs
	 * @return
	 * @throws BusinessException
	 */
	protected Object doCommitProcess(T[] commitVOs, Map<String, String> extParam) throws BusinessException{
		if(commitVOs == null || commitVOs.length == 0){
			return commitVOs;
		}
		Object result = null;
		//判断是否定义审批流
		boolean hasFlow = NCCFlowUtils.hasApproveflowDef(
				this.getBillTypeCode(), commitVOs[0]);
		//有，则走提交动作脚本
		if (hasFlow) {
			result = commitWithApproveFlow(commitVOs, extParam);
		}
		// 没有，则提交即审批
		else {
			result = commitAutoApprove(commitVOs);
		}
		return result;
	} 
	
	/**
	 * 带有审批流的审批
	 * 
	 * @param aggVO
	 * @param extParam
	 * @return
	 * @throws BusinessException
	 */
	private Object commitWithApproveFlow(T[] aggVOs,
			Map<String, String> extParam) throws BusinessException {
		// 从前端获取拓展参数，是否有content数据，如果有则表明是指派需要将指派参数注入到提交逻辑中
		JSONObject content = getContentObj(extParam);
		//动作 actioncode
		String actionCode = this.getActionCode();
		//单据类型
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
	 * 获取前端的拓展参数
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
	 * 提交即审批
	 * 
	 * @param aggVO
	 * @return
	 * @throws BusinessException
	 */
	private Object commitAutoApprove(T[] aggVOs)
			throws BusinessException {
//		String pk_user = SessionContext.getInstance().getClientInfo()
//				.getUserid();
		//动作 actioncode
		String actionCode = this.getActionCode();
		// 调用提交即审批接口
		CloudPFlowContext context = new CloudPFlowContext();
		Map<Object, Object> extParam = new HashMap<Object, Object>();
		extParam.put(IS_RELOADBILL, true);
		context.seteParam(extParam);
		context.setActionName(actionCode);
		context.setBillType(this.getBillTypeCode());
		context.setBillVos(aggVOs);
		//返回结果
		Object retObj = null;
		//提交
		if(TMIMFConst.CONST_ACTION_SAVE.equals(actionCode)){
			retObj = getFlowService().exeScriptPFlow_CommitNoFlowBatch(
					context);
		//收回,无审批流，收回即弃审收回
		}else if(TMIMFConst.CONST_ACTION_UNSAVEBILL.equals(actionCode)
				|| TMIMFConst.CONST_ACTION_UNAPPROVE.equals(actionCode)){
			//调用收回即弃审会先走弃审，再走收回，但是在弃审时，没有审批流，直接将审批状态置为自由态，再走收回时，状态有误
			//所以，直接调弃审动作脚本
//			retObj = getFlowService().exeScriptPFlow_UnSaveNoFlowBatch(
//					context);
			context.setActionName(TMIMFConst.CONST_ACTION_UNAPPROVE);
			retObj = getFlowService().exeScriptPFlow(context);
		}
//		if (!retObj.getClass().isArray()) {
//			throw new BusinessException("提交即审批接口返回数据错误！");
//		}
		// 处理返回结果
		Object obj = Array.get(retObj, 0);
		if (obj instanceof PfProcessBatchRetObject) {
			String errMsg = ((PfProcessBatchRetObject) obj).getExceptionMsg();
			// 没有异常信息则表明执行成功
			if (StringUtil.isNull(errMsg)) {
				return ArrayUtil.isNull(((PfProcessBatchRetObject) obj)
						.getRetObj()) ? null : ((PfProcessBatchRetObject) obj)
						.getRetObj();
			}
			// 表明执行异常！
			else {
				Logger.error(errMsg);
				throw new BusinessException(errMsg);
			}
		} 
//		else {
//			throw new BusinessException("提交即审批接口返回数据错误！");
//		}
		return retObj;
	}
	
	/**
	 * 获取流程服务
	 * 
	 * @return
	 */
	private ICloudScriptPFlowService getFlowService() {
		return ServiceLocator.find(ICloudScriptPFlowService.class);
	}
	
	
}
