package nccloud.ifm.vo;

import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCard;

public class OperatorResult {
	/**
	 * 状态标识(0:全部成功，1：全部失败，2：部分成功)
	 */
	private int status;
	/**
	 * 信息
	 */
	private String msg;
	/**
	 * 成功个数
	 */
	private Integer successNum;
	/**
	 * 失败个数
	 */
	private Integer failNum;
	/**
	 * 总个数
	 */
	private Integer total;
	/**
	 * 返回数据
	 */
	private ExtBillCard[] billCards;
	/**
	 * 返回错误信息
	 */
	private String[] errormessages;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Integer getSuccessNum() {
		return successNum;
	}

	public void setSuccessNum(Integer successNum) {
		this.successNum = successNum;
	}

	public Integer getFailNum() {
		return failNum;
	}

	public void setFailNum(Integer failNum) {
		this.failNum = failNum;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public ExtBillCard[] getBillCards() {
		return billCards;
	}

	public void setBillCards(ExtBillCard[] billCards) {
		this.billCards = billCards;
	}

	public String[] getErrormessages() {
		return errormessages;
	}

	public void setErrormessages(String[] errormessages) {
		this.errormessages = errormessages;
	}

	public OperatorResult(int status, String msg, Integer total,
			Integer successNum,Integer failNum) {
		super();
		this.status = status;
		this.msg = msg;
		this.successNum = successNum;
		this.failNum = failNum;
		this.total = total;
	}

	private OperatorResult() {
		super();
	}

	/**
	 * 构建操作结果
	 * 
	 * @param hasSuccFlag
	 *            是否有成功
	 * @param hasErrFlag
	 *            是否有失败
	 * @param list
	 *            执行结果汇总列表
	 * @return
	 */
	public static OperatorResult buildResult(int totalNum, int sucNmu) {
		String msg = null;
		ResultState status = ResultState.FAIL;
		if (sucNmu > 0 && sucNmu < totalNum) {
			msg = "部分成功！";
			status = ResultState.PARTSUCCESS;
		} else if (sucNmu == totalNum) {
			msg = "全部成功！";
			status = ResultState.SUCCESS;
		} else if (sucNmu==0) {
			msg = "全部失败！";
			status = ResultState.FAIL;
		}
		return new OperatorResult(status.value, msg,totalNum,sucNmu,totalNum-sucNmu);
	}

	/**
	 * 结果枚举
	 * 
	 */
	public static enum ResultState {
		/**
		 * 成功
		 */
		SUCCESS(0),
		/**
		 * 失败
		 */
		FAIL(1),
		/**
		 * 部分成功
		 */
		PARTSUCCESS(2);
		private int value;

		private ResultState(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}
}