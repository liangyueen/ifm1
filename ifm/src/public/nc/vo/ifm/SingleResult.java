package nc.vo.ifm;



/**
 * 单行操作结果
 * @author futao3
 * Aug 27, 2018
 */
public class SingleResult {
	/**
	 * 执行标志
	 */
	private int state;
	/**
	 * 消息
	 */
	private String msg;
	/**
	 * 处理结果
	 */
	private Object result;
	/**
	 * 主键
	 */
	private String pk;
	/**
	 * 单据编号
	 */
	private String vbillno;
	/**
	 * 行号
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
	 * 构建成功的单笔操作结果
	 * 
	 * @param pk
	 *            主键
	 * @param vbillno
	 *            单据编号
	 * @param rowIndex
	 *            行号
	 * @param result
	 *            操作结果
	 * @return
	 */
	public static SingleResult buildSuccessResult(String pk, String vbillno,
			Integer rowIndex, Object result) {
		return new SingleResult(OperatorResult.ResultState.SUCCESS, "成功",
				result, pk, vbillno, rowIndex);
	}

	/**
	 * 构建失败的单笔操作结果
	 * 
	 * @param pk
	 *            主键
	 * @param vbillno
	 *            单据编号
	 * @param rowIndex
	 *            行号
	 * @param msg
	 *            操作消息
	 * @return
	 */
	public static SingleResult buildErrResult(String pk, String vbillno,
			Integer rowIndex, String msg) {
		return new SingleResult(OperatorResult.ResultState.FAIL, msg, null, pk,
				vbillno, rowIndex);
	}
}