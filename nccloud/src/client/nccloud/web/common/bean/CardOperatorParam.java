package nccloud.web.common.bean;

import java.io.Serializable;
import java.util.Arrays;


/**
 * ��Ƭ��������
 * @author futao3
 * @date Aug 29, 2018
 */
public class CardOperatorParam implements Serializable {
	private static final long serialVersionUID = 8347783245875207920L;
	/**
	 * ����
	 */
	private String pk;
	/**
	 * ��������
	 */
	private String[] pks;
	/**
	 * ʱ���
	 */
	private String ts;
	/**
	 * ҳ�����
	 */
	private String pageCode;

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String[] getPks() {
		return pks;
	}

	public void setPks(String[] pks) {
		this.pks = pks;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	@Override
	public String toString() {
		return "CardOperatorParam [pk=" + pk + ", pks=" + Arrays.toString(pks)
				+ ", ts=" + ts + ", pageCode=" + pageCode + "]";
	}

}
