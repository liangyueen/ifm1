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
 * 公共操作类，删除、提交、取消提交，前端传pk，后端先根据pk查询单据，再对查询结果做具体操作
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
		// 获取前端请求参数
		operaParam = (OperatorParam) getRequestParam(request);
		// 前台参数校验
		doBefore();
		// 根据pk或前台其他信息查询待操作单据
		getBillVObyParam();
		// 对查询结果执行具体业务处理
		resultVOs = this.doBusinessProcess(operaVOs);
		// 后续操作
		doAfter();
		// 卡片页面需要返回操作后的数据到前端，列表界面操作后，前端重新调用列表查询请求，无需返回操作后数据，所以此处按照卡片处理返回结果
		billCards = this.buildFontResult(operaParam, resultVOs);
		// 精度处理
		processDigit();
		// 结果处理
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
	 * 得到错误信息
	 * 
	 * @return
	 */
	protected abstract String[] getErrormessage();

	/**
	 * 根据PK查询待操作单据
	 * 
	 * @param operaPks
	 * @return
	 */
	protected abstract T[] queryBillsByPks(String[] operaPks);

	/**
	 * 根据前台其他参数查询
	 * 
	 * @param operaParam
	 * @return
	 */
	protected abstract T[] queryBillsByParam(OperatorParam operaParam);

	/**
	 * 具体得业务处理
	 * 
	 * @param operaPks
	 * @return
	 */
	protected abstract T[] doBusinessProcess(T[] operaVOs);

	/**
	 * 构建前端返回结果
	 * 
	 * @param operaParam
	 * @param resultVOs
	 * @return
	 */
	protected ExtBillCard[] buildFontResult(OperatorParam operaParam,
			T[] resultVOs) {
		// 把结果进行封装返回
		ExtBillCardConvertProcessor processor = new ExtBillCardConvertProcessor();
		ExtBillCard billCard = new ExtBillCard();
		if (resultVOs == null || resultVOs.length == 0) {
			return new ExtBillCard[]{billCard};
		}
		ExtBillCard[] billCards = new ExtBillCard[resultVOs.length];
		for (int i=0;i<resultVOs.length;i++) {
			billCard = processor.convert(operaParam.getPageCode(), resultVOs[i]);
			// 翻译
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
	 * 前台参数校验
	 */
	@Override
	protected void doBefore() {
		if (operaParam.getPkMapTs() == null
				&& (operaParam.getPks() == null || operaParam.getPks().length == 0)) {
			ExceptionUtils.wrapBusinessException("请求参数有误，操作数据pk为空");
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
