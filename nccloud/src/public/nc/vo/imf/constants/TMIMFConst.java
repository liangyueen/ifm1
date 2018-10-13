package nc.vo.imf.constants;

/**
 * 投融资投资申购常量类
 * 
 */
public class TMIMFConst {
	//投资申购单据类型
	public static String CONST_BILLTYPE_APPLY="3641";

	//常用动作
	public static String CONST_ACTION_SAVEBASE="SAVEBASE"; // 保存
	public static String CONST_ACTION_DELETE="DELETE"; // 删除
	public static String CONST_ACTION_SAVE="SAVE"; // 提交
	public static String CONST_ACTION_UNSAVEBILL="UNSAVEBILL"; // 收回
	public static String CONST_ACTION_APPROVE="APPROVE"; // 审批
	public static String CONST_ACTION_UNAPPROVE="UNAPPROVE";  // 取消审批
	
	/**
	 * 【投资申购相关编码】
	 */
	// 银行授信调整列表页面编码
	public static String CONST_PAGECODE_ADJUST_LIST = "36670AP_LIST";
	// 贷款申请卡片页面编码
	public static String CONST_PAGECODE_ADJUST_CARD = "36670AP_CARD";
	// 贷款申请应用编码
	public static String CONST_APPCODE_ADJUST = "36670AP";
	// 贷款申请列表页面区域编码
	public static String CONST_AREACODE_ADJUST_LIST_TABLE = "table";
	// 贷款申请列表页面区域编码
	public static String CONST_AREACODE_ADJUST_LIST_SEARCH = "search";
	
	/**
	 * 常用属性名称
	 */
	public static String FIELD_PK = "pk";
	public static String FIELD_PKS = "pks";
	public static String FIELD_PK_ORG = "pk_org";
	public static String FIELD_PK_GROUP = "pk_group";
	public static String FIELD_PK_CURRTYPE = "pk_currtype";
	public static String FIELD_MONEY = "mnyField";
	public static String FIELD_ORGRATE = "orgRateField";
	public static String FIELD_GROUPRATE = "groupRateField";
	public static String FIELD_GLOBALRATE = "globalRateField";
	public static String FIELD_ORGMNY = "orgMnyField";
	public static String FIELD_GROUPMNY = "groupMnyField";
	public static String FIELD_GLOBALMNYFIELD = "globalMnyField";
		
}
