package nccloud.ifm.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import nc.vo.obm.pay.PaymentRetMsg;

public class OperatorParam implements Serializable {
	private static final long serialVersionUID = 3950979857630396988L;
	/**
	 * ����
	 */
	private String pk;
	/**
	 * ��������
	 */
	private String[] pks;
	/**
	 * ҳ����� �ش�
	 */
	private String pageCode;
	/**
	 * ������ʱ�����ӳ���ϵ �ش�
	 */
	private Map<String, String> pkMapTs;
	/**
	 * �����뵥�ݱ�ŵ�ӳ���ϵ �ش�
	 */
	private Map<String, String> pkMapVbillno;
	/**
	 * �������к�ӳ���ϵ
	 */
	private Map<String, Integer> pkMapRowIndex;
	/**
	 * ������������(���ֳи�ʱʹ��)
	 */
	private String[] bodyPKs;
	/**
	 * �Ƿ���Ҫ�������� �ش�
	 */
	private boolean isRet = false;
	/**
	 * ���������ؽ��(������¼��������֧��ʱ�õ�)
	 */
	private PaymentRetMsg[] results;
	/**
	 * ��չ����
	 */
	private Map<String, String> extParam = new HashMap<>();
	/**
	 * ����
	 */
	private String data;
	/**
	 * ��ť����
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
	 * ��ȡ��չ����
	 * 
	 * @param key
	 *            ���Լ�
	 * @return ����ֵ
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
