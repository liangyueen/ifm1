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
 * 单据保存
 * @author futao3
 * @date Aug 29, 2018
 */
public abstract class CommonSaveAction<T extends AbstractBill> extends AbstractPfAction<T> {
	
	@Override
	public Object doAction(IRequest request) {
		//处理前端请求，获取VO数据
		T operaVO = this.getVODate(request);
		// 前操作
		doBefore();
		//执行业务保存操作
		T resultVO = this.doBusinessSave(operaVO);
		// 后续操作
		doAfter();
		//构建前端返回结果
		BillCard billCard = this.buildFontResult(resultVO);
		// 精度处理
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
	 * 获取单据VO数据，卡片保存，所以只有一条数据
	 * @param request
	 * @return
	 */
	protected T getVODate(IRequest request){
		// 解析request
		BillCardOperator operator = new BillCardOperator();
		T aggvo =  operator.toBill(request);
		return aggvo;
	}
	
	@Override
	protected void doBefore() {
		/*// 从SessionContext中获取客户端信息
		ClientInfo clientInfo = SessionContext.getInstance().getClientInfo();
		ContractVO head = ((ContractVO)vo.getParent());
		// 设置一些初始化信息
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
	 * 业务保存操作
	 * @param operaVO
	 * @return
	 */
	protected abstract T doBusinessSave(T operaVO);

	@Override
	protected void doAfter() {
		
	}
	
	/**
	 * 调用动作脚本（单笔数据）
	 * 
	 * @param actionCode
	 *            动作编码
	 * @param billType
	 *            单据类型
	 * @param aggVO
	 *            业务单据
	 * @return
	 * @throws BusinessException
	 */
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
	 * 构建前端返回结果
	 * 
	 * @param operaParam
	 * @param resultVOs
	 * @return
	 */
	protected BillCard buildFontResult(T resultVO) {
		// 把结果进行封装返回
		BillCard billCard = new BillCard();
		if (resultVO == null) {
			return billCard;
		}
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		billCard = processor.convert(this.getPageCode(), resultVO);
	    //翻译
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
	 * 获取页面编码
	 * @return
	 */
	protected abstract String getPageCode();
	


}
