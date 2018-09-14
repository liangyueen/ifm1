package nccloud.ifm.vo;

import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCard;

public class OperatorResult {
	/**
	 * ״̬��ʶ(0:ȫ���ɹ���1��ȫ��ʧ�ܣ�2�����ֳɹ�)
	 */
	private int status;
	/**
	 * ��Ϣ
	 */
	private String msg;
	/**
	 * �ɹ�����
	 */
	private Integer successNum;
	/**
	 * ʧ�ܸ���
	 */
	private Integer failNum;
	/**
	 * �ܸ���
	 */
	private Integer total;
	/**
	 * ��������
	 */
	private ExtBillCard[] billCards;
	/**
	 * ���ش�����Ϣ
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
	 * �����������
	 * 
	 * @param hasSuccFlag
	 *            �Ƿ��гɹ�
	 * @param hasErrFlag
	 *            �Ƿ���ʧ��
	 * @param list
	 *            ִ�н�������б�
	 * @return
	 */
	public static OperatorResult buildResult(int totalNum, int sucNmu) {
		String msg = null;
		ResultState status = ResultState.FAIL;
		if (sucNmu > 0 && sucNmu < totalNum) {
			msg = "���ֳɹ���";
			status = ResultState.PARTSUCCESS;
		} else if (sucNmu == totalNum) {
			msg = "ȫ���ɹ���";
			status = ResultState.SUCCESS;
		} else if (sucNmu==0) {
			msg = "ȫ��ʧ�ܣ�";
			status = ResultState.FAIL;
		}
		return new OperatorResult(status.value, msg,totalNum,sucNmu,totalNum-sucNmu);
	}

	/**
	 * ���ö��
	 * 
	 */
	public static enum ResultState {
		/**
		 * �ɹ�
		 */
		SUCCESS(0),
		/**
		 * ʧ��
		 */
		FAIL(1),
		/**
		 * ���ֳɹ�
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