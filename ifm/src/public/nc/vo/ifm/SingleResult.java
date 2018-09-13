package nc.vo.ifm;



/**
 * ���в������
 * @author futao3
 * Aug 27, 2018
 */
public class SingleResult {
	/**
	 * ִ�б�־
	 */
	private int state;
	/**
	 * ��Ϣ
	 */
	private String msg;
	/**
	 * ������
	 */
	private Object result;
	/**
	 * ����
	 */
	private String pk;
	/**
	 * ���ݱ��
	 */
	private String vbillno;
	/**
	 * �к�
	 */
	private Integer rowIndex;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getVbillno() {
		return vbillno;
	}

	public void setVbillno(String vbillno) {
		this.vbillno = vbillno;
	}

	public Integer getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}

	private SingleResult(OperatorResult.ResultState state, String msg,
			Object result, String pk, String vbillno, Integer rowIndex) {
		super();
		this.state = state.getValue();
		this.msg = msg;
		this.result = result;
		this.pk = pk;
		this.vbillno = vbillno;
		this.rowIndex = rowIndex;
	}

	public SingleResult() {
		super();
	}

	/**
	 * �����ɹ��ĵ��ʲ������
	 * 
	 * @param pk
	 *            ����
	 * @param vbillno
	 *            ���ݱ��
	 * @param rowIndex
	 *            �к�
	 * @param result
	 *            �������
	 * @return
	 */
	public static SingleResult buildSuccessResult(String pk, String vbillno,
			Integer rowIndex, Object result) {
		return new SingleResult(OperatorResult.ResultState.SUCCESS, "�ɹ�",
				result, pk, vbillno, rowIndex);
	}

	/**
	 * ����ʧ�ܵĵ��ʲ������
	 * 
	 * @param pk
	 *            ����
	 * @param vbillno
	 *            ���ݱ��
	 * @param rowIndex
	 *            �к�
	 * @param msg
	 *            ������Ϣ
	 * @return
	 */
	public static SingleResult buildErrResult(String pk, String vbillno,
			Integer rowIndex, String msg) {
		return new SingleResult(OperatorResult.ResultState.FAIL, msg, null, pk,
				vbillno, rowIndex);
	}
}