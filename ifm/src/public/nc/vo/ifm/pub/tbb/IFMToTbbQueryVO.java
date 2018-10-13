package nc.vo.ifm.pub.tbb;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;

/**
 * Ͷ�����-VOת������
 */
public abstract class IFMToTbbQueryVO extends CircularlyAccessibleValueObject {
	private static final long serialVersionUID = 6672146546816792792L;
	// ������֯
	private String pk_org;
	// ����
	private String pk_currtype;
	// �ʽ�ƻ���Ŀ
	private String pk_fundplan;
	// ��������
	private String pk_bankacc_p;
	// Ͷ��Ʒ��
	private String pk_finvar;
	// �����˻�
	private String pk_bankacc_r;
	
	// ��ʼ����
	private String startdate;
	// ��������
	private String enddate;

	// �Ƿ����δ��ʽ��Ч����
	private boolean includinguneffective = false;
	// ѡ�����
	private String controlobj;
	// ��������
	private String dateType;
	//�������
	private String data_attr;
	// �ʽ�����
	private String pk_fundtype;

	/**���صı������� 0��ȫ�ֱ��ң�1�����ű��ң�2����֯����, 3: ԭ��*/
	private int curr_type;

	// Ԥռ��ִ�б�־
	private String flag_readyorrun;

	public static final String DISPOINT = ".";
	// �����ֶ���
	private static final String CURR_KEY = "pk_currtype";
	// ��˾�ֶ���
	private static final String ORG_KEY = "pk_org";

	public abstract String[] getDataItemNames();

	@Override
	public abstract String[] getAttributeNames();

	@Override
	public abstract Object getAttributeValue(String name);

	
	public String getPk_org() {
		return pk_org;
	}
	
	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}
	/**
	 *
	 * ���������������������������
	 */
	public abstract String getHeadTableName();

	/**
	 *
	 * �������������������ӱ������
	 */
	public abstract String getBodyTableName();

	/**
	 *
	 * ������������:��������ֶ�������
	 */
	public abstract String getJoinPart();

	/**
	 *
	 * ������������:�����Ƿ����δ��Ч���ݶ�Ӧ��SQL��䡣
	 */
	public abstract String getIncludingUneffectivePart();

	/**
	 *
	 * ���ظ�����ʵ�ʵ���Ŀ����
	 */
	public abstract String changeAttrToBillItemName(String attr);

	/**
	 * ����  ȥ���������ϵ��� SQL
	 * @return
	 * @author chengfei
	 * @since NC6.0
	 */
	public  String getBodyDisUsePart(){
		return null;
	};

	/**
	 * ���� ��ѯִ������Ԥռ����SqlƬ��
	 * ��Ե��ݼ�дԤռ����дִ��������Ҫ���ش˷���
	 * @return
	 * @author chengfei
	 * @since NC6.0
	 */
	public  String getUfindOrPrefindPart(){
		return null;
	};

	/**
	 *
	 * ����������������������֯�ֶ�����
	 */
	public String getOrgKeyName() {
		return getHeadTableName() + DISPOINT + ORG_KEY;
	}

	/**
	 *
	 * �����������������ر����ֶ�����
	 */
	public String getCurrKeyName() {
		return getHeadTableName() + DISPOINT + CURR_KEY;
	}

	/**
	 *
	 * �����������������ص��������ֶ�����
	 */
	public String getDateKeyName() {
		return getHeadTableName() + DISPOINT + dateType;
	}

	/**
	 *
	 * ������������������ѡ���ֶ����� ����ԭ�ҽ���֯���ҽ����ű��ҽ�ȫ�ֱ��ҽ��
	 */
	public String[] getToSelecltKeyName() {
		String[] selectkeys = null;
		selectkeys = new String[4];
		selectkeys[0] = "sum(" + getBodyTableName() + DISPOINT
				+ getMoneyName() + ")";
		selectkeys[1] = "sum(" + getBodyTableName() + DISPOINT
				+ getOlcmoneyName() + ")";
		selectkeys[2] = "sum(" + getBodyTableName() + DISPOINT
		+ getGlcmoneyName() + ")";
		selectkeys[3] = "sum(" + getBodyTableName() + DISPOINT
		+ getGllcmoneyName() + ")";
		return selectkeys;
	}
   /**
    * ����ԭ�ҵ��ֶ�����
    * @return
    */
	public String getMoneyName() {
		return "actualmoeny";
	}
   /**
    * ������֯��λ�ҵ��ֶ�����
    * @return
    */
	public String getOlcmoneyName() {
		return "olcmoney";
	}
   /**
    * ���ؼ��ű�λ�ҵ��ֶ�����
    * @return
    */
	public String getGlcmoneyName() {
		return "glcmoney";
	}
   /**
    * ����ȫ�ֱ�λ�ҵ��ֶ�����
    * @return
    */
	public String getGllcmoneyName() {
		return "gllmoney";
	}
	/**
	 *
	 * ������������������/���ؿ�ʼ�����ֶ�ֵ��
	 */
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getStartdate() {
		return this.startdate;
	}

	/**
	 *
	 * ������������������/���ؽ��������ֶ�ֵ��
	 */
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getEnddate() {
		return this.enddate;
	}

	/**
	 *
	 * ������������������/����ȡ�������ֶ�ֵ��
	 */
	public void setControlobj(String controlobj) {
		String[] ctrobj = controlobj.split(";");
		this.controlobj = ctrobj[0];
	}

	public String getControlobj() {
		return this.controlobj;
	}

	@Override
	public String getEntityName() {
		return null;
	}

	@Override
	public void validate() throws ValidationException {
		if (StringUtil.isEmpty(pk_org)) {
//			throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3632pub_0","03632pub-0081")/*@res "Ԥ�㴫���������֯����Ϊ�գ�"*/);
			throw new ValidationException("Ԥ�㴫���������֯����Ϊ�գ�"/*@res "Ԥ�㴫���������֯����Ϊ�գ�"*/);
		}
	}

	public boolean isLevelCtrl(String key) {
		return false;
	}



	/**
	 *
	 * ������������������/�������������ֶ�ֵ��
	 */
	public void setDateType(String datetype) {
		this.dateType = datetype;
	}

	public String getDateType() {
		return this.dateType;
	}

	public boolean isIncludinguneffective() {
		return includinguneffective;
	}

	public void setIncludinguneffective(boolean includinguneffective) {
		this.includinguneffective = includinguneffective;
	}

	/**
	 *
	 * ������������������/���ر����ֶ�ֵ��
	 */
	public String getPk_curr() {
		return pk_currtype;
	}

	public void setPk_curr(String pk_curr) {
		this.pk_currtype = pk_curr;
	}

	public String getPk_fundtype() {
		return pk_fundtype;
	}

	public void setPk_fundtype(String pk_fundtype) {
		this.pk_fundtype = pk_fundtype;
	}

	public String getFlag_readyorrun() {
		return flag_readyorrun;
	}

	public void setFlag_readyorrun(String flag_readyorrun) {
		this.flag_readyorrun = flag_readyorrun;
	}

	/**
	 * TODO �ֶ�����
	 * @return the curr_type
	 */
	public int getCurr_type() {
		return curr_type;
	}

	/**
	 * TODO �ֶ�����
	 * @param curr_type the curr_type to set
	 */
	public void setCurr_type(int curr_type) {
		this.curr_type = curr_type;
	}

	/**
	 * ���ص�������
	 * @return
	 */
	public String getBilltypecode(){
		return null;
	}

	/**
	 * @return the pk_currtype
	 */
	public String getPk_currtype() {
		return pk_currtype;
	}

	/**
	 * @param pk_currtype the pk_currtype to set
	 */
	public void setPk_currtype(String pk_currtype) {
		this.pk_currtype = pk_currtype;
	}

	/**
	 * @return the pk_fundplan
	 */
	public String getPk_fundplan() {
		return pk_fundplan;
	}

	/**
	 * @param pk_fundplan the pk_fundplan to set
	 */
	public void setPk_fundplan(String pk_fundplan) {
		this.pk_fundplan = pk_fundplan;
	}

	/**
	 * @return the pk_bankacc_p
	 */
	public String getPk_bankacc_p() {
		return pk_bankacc_p;
	}

	/**
	 * @param pk_bankacc_p the pk_bankacc_p to set
	 */
	public void setPk_bankacc_p(String pk_bankacc_p) {
		this.pk_bankacc_p = pk_bankacc_p;
	}

	/**
	 * @return the pk_finvar
	 */
	public String getPk_finvar() {
		return pk_finvar;
	}

	/**
	 * @param pk_finvar the pk_finvar to set
	 */
	public void setPk_finvar(String pk_finvar) {
		this.pk_finvar = pk_finvar;
	}

	/**
	 * @return the pk_bankacc_r
	 */
	public String getPk_bankacc_r() {
		return pk_bankacc_r;
	}

	/**
	 * @param pk_bankacc_r the pk_bankacc_r to set
	 */
	public void setPk_bankacc_r(String pk_bankacc_r) {
		this.pk_bankacc_r = pk_bankacc_r;
	}


	/**
	 * @return the data_attr
	 */
	public String getData_attr() {
		return data_attr;
	}

	/**
	 * @param data_attr the data_attr to set
	 */
	public void setData_attr(String data_attr) {
		this.data_attr = data_attr;
	}

	/**
	 * @return the currKey
	 */
	public static String getCurrKey() {
		return CURR_KEY;
	}

	/**
	 * @return the orgKey
	 */
	public static String getOrgKey() {
		return ORG_KEY;
	}

}