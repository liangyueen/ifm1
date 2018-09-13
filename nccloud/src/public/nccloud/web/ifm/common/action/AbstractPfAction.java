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
 * 投融资公共Action
 * 
 * @author wuzhwa
 * @version ncc1.0 
 * 2018年7月31日
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
	 * 获取单据动作
	 * @return
	 */
	protected abstract String getActionCode();
	
	/**
	 * 获取单据类型编码
	 * @return
	 */
	protected abstract String getBillTypeCode();
	
	
	/**
	 * 操作前
	 */
	protected abstract void doBefore();
	
	/**
	 * 操作后
	 */
	protected abstract void doAfter();
	
	/**
	 * 处理请求参数
	 * @return 
	 */
	protected abstract T[] processRequestParam(IRequest request);
	
	/**
	 * 获取前端请求参数
	 * @param request
	 * @return
	 */
	protected Object getRequestParam(IRequest request){
		// 解析request
		// 前端传递参数格式: {"pks":["xxx","xxx","xxx"...], "pagecode":"3661DEMO_L01"}
		String str = request.read();
		IJson json = JsonFactory.create();
		OperatorParam operaParam  = json.fromJson(str, OperatorParam.class);
		return operaParam;
				
	}
	protected Object callActionScript(String actionCode, String billType,
			T[] aggVOs) throws BusinessException {
		if (StringUtils.isEmpty(actionCode) || aggVOs == null
				|| aggVOs.length == 0) {
			throw new BusinessException("调用动作脚本时动作名称和处理数据不能为空！");
		}
		CloudPFlowContext context = new CloudPFlowContext();
		context.setActionName(actionCode);
		context.setBillType(billType);
		context.setBillVos(aggVOs);
		Logger.debug("开始调用动作脚本 ActionName[" + actionCode + "] BillType["
				+ billType + "]...");
		ICloudScriptPFlowService service = ServiceLocator
				.find(ICloudScriptPFlowService.class);
		Object[] result = service.exeScriptPFlow(context);
		Logger.debug("调用动作脚本 ActionName[" + actionCode + "] BillType["
				+ billType + "]结束");
		if (ArrayUtil.isNull(result)) {
			return null;
		} else {
			return result[0];
		}
	}
	
	/**
	 * 执行动作脚本（带额外参数）
	 * 
	 * @param actionCode
	 *            动作名称
	 * @param billType
	 *            单据类型
	 * @param eParam
	 *            额外参数
	 * @param aggVO
	 *            业务单据
	 * 
	 * @return
	 * @throws BusinessException
	 */
	protected Object callActionScript(String actionCode, String billType,
			Map<Object, Object> eParam, T[] aggVOs) throws BusinessException {
		if (StringUtils.isEmpty(actionCode) || aggVOs == null
				|| aggVOs.length == 0) {
			throw new BusinessException("调用动作脚本时动作名称和处理数据不能为空！");
		}
		CloudPFlowContext context = new CloudPFlowContext();
		context.setActionName(actionCode);
		context.setBillType(billType);
		context.setBillVos(aggVOs);
		context.seteParam(eParam);
		Logger.debug("开始调用动作脚本 ActionName[" + actionCode + "] BillType["
				+ billType + "]...");
		ICloudScriptPFlowService service = ServiceLocator
				.find(ICloudScriptPFlowService.class);
		Object[] result = service.exeScriptPFlow(context);
		Logger.debug("调用动作脚本 ActionName[" + actionCode + "] BillType["
				+ billType + "]结束");
		if (ArrayUtil.isNull(result)) {
			return null;
		} else {
			return result;
		}
	}
	/**
	 * 处理返回值
	 */
	protected abstract void doReturn();
	/**
	 * 消息处理
	 */
	protected abstract void processMsg();
	/**
	 * 精度处理
	 */
	protected abstract void processDigit();
}
