package nc.vo.ifm.constants;

/**
 * 投融资类
 * @author wuzhwa
 * @version ncc1.0
 */
public class TMIFMConst {
	/**
	 * 模块
	 */
	public static String CONST_MODULE="IFM";
	public static String CONST_MODULE_CODE="3667";
	
	// 资金管理
	public static final String TM_FUNCODE = "36"; // 资金管理
	public static final String TM_CMP_FUNCODE = "3607";// 现金管理
	public static final String TM_FTS_FUNCODE = "3630";// 资金结算
	public static final String FI_FIP_FUNCODE = "1017";// 会计平台
	public static final String TBB_FUNCODE = "1050";// 预算
	public static final String TM_IFM_FUNCODE = "3667";// 投资理财

	/**
	 * 单据类型常量
	 */
	public static String CONST_BILLTYPE_INCOME="3643";
	
	
	/**
	 * 常用动作
	 */
	public static String CONST_ACTION_SAVEBASE="SAVEBASE"; // 保存
	public static String CONST_ACTION_DELETE="DELETE"; // 删除
	public static String CONST_ACTION_SAVE="SAVE"; // 提交
	public static String CONST_ACTION_SUBMIT="SUBMIT"; // 提交下一层
	public static String CONST_ACTION_UNSAVEBILL="UNSAVEBILL"; // 收回
	public static String CONST_ACTION_APPROVE="APPROVE"; // 审批
	public static String CONST_ACTION_UNAPPROVE="UNAPPROVE";  // 取消审批
	
	// 投资收益列表页面编码
	public static String CONST_PAGECODE_INCOME_LIST = "36670IPR_LIST";
	// 投资收益卡片页面编码
	public static String CONST_PAGECODE_INCOME_CARD = "36670IPR_CARD";
	// 投资收益应用编码
	public static String CONST_APPCODE_INCOME = "36670IPR";
	// 投资收益列表页面区域编码
	public static String CONST_AREACODE_INCOME_LIST_TABLE = "table";
	// 投资收益列表页面区域编码
	public static String CONST_AREACODE_INCOME_LIST_SEARCH = "search";
	
	
	/**
	 * 常用属性名称
	 */
	public static String FIELD_PK_ORG = "pk_org";
	public static String FIELD_PK_GROUP = "pk_group";
	public static String FIELD_PK_CURRTYPE = "pk_currtype  ";
	public static String FIELD_MONEY = "mnyField";
	public static String FIELD_ORGRATE = "orgRateField";
	public static String FIELD_GROUPRATE = "groupRateField";
	public static String FIELD_GLOBALRATE = "globalRateField";
	public static String FIELD_ORGMNY = "orgMnyField";
	public static String FIELD_GROUPMNY = "groupMnyField";
	public static String FIELD_GLOBALMNYFIELD = "globalMnyField";
	
	
}
