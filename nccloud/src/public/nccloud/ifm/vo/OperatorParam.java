package nccloud.ifm.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nc.vo.obm.pay.PaymentRetMsg;

public class OperatorParam implements Serializable {
	private static final long serialVersionUID = 3950979857630396988L;
	/**
	 * 主键
	 */
	private String pk;
	/**
	 * 主键数组
	 */
	private String[] pks;
	/**
	 * 页面编码 必传
	 */
	private String pageCode;
	/**
	 * 主键与时间戳的映射关系 必传
	 */
	private Map<String, String> pkMapTs;
	/**
	 * 主键与单据编号的映射关系 必传
	 */
	private Map<String, String> pkMapVbillno;
	/**
	 * 主键与行号映射关系
	 */
	private Map<String, Integer> pkMapRowIndex;
	/**
	 * 表体主键数组(部分承付时使用)
	 */
	private String[] bodyPKs;
	/**
	 * 是否需要返回数据 必传
	 */
	private boolean isRet = false;
	/**
	 * 网银处理返回结果(网银补录或者网银支付时用到)
	 */
	private PaymentRetMsg[] results;
	/**
	 * 拓展属性
	 */
	private Map<String, String> extParam = new HashMap<>();
	/**
	 * 数据
	 */
	private String data;
	/**
	 * 按钮编码
	 * 
	 * @return
	 */
	private String actionCode;

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String[] getPks() {
		return pks;
	}

	public void setPks(String[] pks) {
		this.pks = pks;
	}

	public Map<String, String> getPkMapTs() {
		return pkMapTs;
	}

	public void setPkMapTs(Map<String, String> pkMapTs) {
		this.pkMapTs = pkMapTs;
	}

	public boolean isRet() {
		return isRet;
	}

	public void setRet(boolean isRet) {
		this.isRet = isRet;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public PaymentRetMsg[] getResults() {
		return results;
	}

	public void setResults(PaymentRetMsg[] results) {
		this.results = results;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String[] getBodyPKs() {
		return bodyPKs;
	}

	public void setBodyPKs(String[] bodyPKs) {
		this.bodyPKs = bodyPKs;
	}

	public Map<String, String> getPkMapVbillno() {
		return pkMapVbillno;
	}

	public void setPkMapVbillno(Map<String, String> pkMapVbillno) {
		this.pkMapVbillno = pkMapVbillno;
	}

	public Map<String, Integer> getPkMapRowIndex() {
		return pkMapRowIndex;
	}

	public void setPkMapRowIndex(Map<String, Integer> pkMapRowIndex) {
		this.pkMapRowIndex = pkMapRowIndex;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	/**
	 * 获取拓展属性
	 * 
	 * @param key
	 *            属性键
	 * @return 属性值
	 */
	public String getExtParam(String key) {
		if (extParam == null || extParam.isEmpty()
				|| !extParam.containsKey(key)) {
			return null;
		}
		return extParam.get(key);
	}

	public Map<String, String> getExtParam() {
		return extParam;
	}

	public void setExtParam(Map<String, String> extParam) {
		this.extParam = extParam;
	}

	public boolean isBatchOperator() {
		if (pkMapTs == null || pkMapTs.isEmpty()) {
			return false;
		}
		return pkMapTs.size() > 1 ? true : false;
	}
}
