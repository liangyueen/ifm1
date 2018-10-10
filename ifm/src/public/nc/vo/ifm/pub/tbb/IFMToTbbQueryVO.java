package nc.vo.ifm.pub.tbb;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;

/**
 * 投资理财-VO转换基类
 */
public abstract class IFMToTbbQueryVO extends CircularlyAccessibleValueObject {
	private static final long serialVersionUID = 6672146546816792792L;
	// 财务组织
	private String pk_org;
	// 币种
	private String pk_currtype;
	// 资金计划项目
	private String pk_fundplan;
	// 发行银行
	private String pk_bankacc_p;
	// 投资品种
	private String pk_finvar;
	// 银行账户
	private String pk_bankacc_r;
	
	// 开始日期
	private String startdate;
	// 结束日期
	private String enddate;

	// 是否包含未正式生效单据
	private boolean includinguneffective = false;
	// 选择对象
	private String controlobj;
	// 日期类型
	private String dateType;
	//金额类型
	private String data_attr;
	// 资金类型
	private String pk_fundtype;

	/**返回的币种类型 0：全局本币，1：集团本币，2：组织本币, 3: 原币*/
	private int curr_type;

	// 预占和执行标志
	private String flag_readyorrun;

	public static final String DISPOINT = ".";
	// 币种字段名
	private static final String CURR_KEY = "pk_currtype";
	// 公司字段名
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
	 * 方法功能描述：返回主表表名。
	 */
	public abstract String getHeadTableName();

	/**
	 *
	 * 方法功能描述：返回子表表名。
	 */
	public abstract String getBodyTableName();

	/**
	 *
	 * 方法功能描述:返回外键字段名。。
	 */
	public abstract String getJoinPart();

	/**
	 *
	 * 方法功能描述:返回是否包含未生效单据对应的SQL语句。
	 */
	public abstract String getIncludingUneffectivePart();

	/**
	 *
	 * 返回各单据实际的项目名称
	 */
	public abstract String changeAttrToBillItemName(String attr);

	/**
	 * 返回  去除表体作废的行 SQL
	 * @return
	 * @author chengfei
	 * @since NC6.0
	 */
	public  String getBodyDisUsePart(){
		return null;
	};

	/**
	 * 返回 查询执行数或预占数的Sql片段
	 * 针对单据既写预占数又写执行数的需要重载此方法
	 * @return
	 * @author chengfei
	 * @since NC6.0
	 */
	public  String getUfindOrPrefindPart(){
		return null;
	};

	/**
	 *
	 * 方法功能描述：返回主组织字段名。
	 */
	public String getOrgKeyName() {
		return getHeadTableName() + DISPOINT + ORG_KEY;
	}

	/**
	 *
	 * 方法功能描述：返回币种字段名。
	 */
	public String getCurrKeyName() {
		return getHeadTableName() + DISPOINT + CURR_KEY;
	}

	/**
	 *
	 * 方法功能描述：返回单据日期字段名。
	 */
	public String getDateKeyName() {
		return getHeadTableName() + DISPOINT + dateType;
	}

	/**
	 *
	 * 方法功能描述：返回选择字段名。 返回原币金额，组织本币金额，集团本币金额，全局本币金额
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
    * 返回原币的字段名称
    * @return
    */
	public String getMoneyName() {
		return "actualmoeny";
	}
   /**
    * 返回组织本位币的字段名称
    * @return
    */
	public String getOlcmoneyName() {
		return "olcmoney";
	}
   /**
    * 返回集团本位币的字段名称
    * @return
    */
	public String getGlcmoneyName() {
		return "glcmoney";
	}
   /**
    * 返回全局本位币的字段名称
    * @return
    */
	public String getGllcmoneyName() {
		return "gllmoney";
	}
	/**
	 *
	 * 方法功能描述：设置/返回开始日期字段值。
	 */
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getStartdate() {
		return this.startdate;
	}

	/**
	 *
	 * 方法功能描述：设置/返回结束日期字段值。
	 */
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getEnddate() {
		return this.enddate;
	}

	/**
	 *
	 * 方法功能描述：设置/返回取数对象字段值。
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
//			throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3632pub_0","03632pub-0081")/*@res "预算传入参数主组织不能为空！"*/);
			throw new ValidationException("预算传入参数主组织不能为空！"/*@res "预算传入参数主组织不能为空！"*/);
		}
	}

	public boolean isLevelCtrl(String key) {
		return false;
	}



	/**
	 *
	 * 方法功能描述：设置/返回日期类型字段值。
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
	 * 方法功能描述：设置/返回币种字段值。
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
	 * TODO 字段名称
	 * @return the curr_type
	 */
	public int getCurr_type() {
		return curr_type;
	}

	/**
	 * TODO 字段名称
	 * @param curr_type the curr_type to set
	 */
	public void setCurr_type(int curr_type) {
		this.curr_type = curr_type;
	}

	/**
	 * 返回单据类型
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