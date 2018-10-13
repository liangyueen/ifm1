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
	public static String CONST_MODULE="ifm";
	public static String CONST_MODULE_CODE="3667";
	
	// 资金管理
	public static final String TM_FUNCODE = "36"; // 资金管理
	public static final String TM_CMP_FUNCODE = "3607";// 现金管理
	public static final String TM_FTS_FUNCODE = "3630";// 资金结算
	public static final String FI_FIP_FUNCODE = "1017";// 会计平台
	public static final String TBB_FUNCODE = "1050";// 预算

	/**
	 * 单据类型常量
	 */
	
	public static String CONST_BILLTYPE_REDEEM="3642";
	public static String CONST_BILLTYPE_APPLY="3641";
	public static String CONST_BILLTYPE_INCOME="3643";
	/**
	 * 资金赎回常用动作
	 */
	public static String CONST_ACTION_SAVEBASE="SAVEBASE"; // 保存
	public static String CONST_ACTION_DELETE="DELETE"; // 删除
	public static String CONST_ACTION_SAVE="SAVE"; // 提交
	public static String CONST_ACTION_UNSAVEBILL="UNSAVEBILL"; // 收回
	public static String CONST_ACTION_APPROVE="APPROVE"; // 审批
	public static String CONST_ACTION_UNAPPROVE="UNAPPROVE";  // 取消审批
	
	// 资金赎回列表页面编码
	public static String CONST_PAGECODE_CONTRACT_LIST = "36670RED_LIST";
	// 资金赎回卡片页面编码
	public static String CONST_PAGECODE_CONTRACT_CARD = "36670RED_CARD";
	// 资金赎回应用编码
	public static String CONST_APPCODE_CONTRACT = "36670RED";
	// 资金赎回列表页面区域编码
	public static String CONST_AREACODE_REDEEM_LIST_TABLE = "table";
	// 资金赎回查询区域编码
	public static String CONST_AREACODE_CONTRACT_LIST_SEARCH = "search";
	
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
	public static String FIELD_PK_CURRTYPE = "pk_currtype";
	public static String FIELD_MONEY = "mnyField";
	public static String FIELD_ORGRATE = "olcrate";
	public static String FIELD_GROUPRATE = "glcrate";
	public static String FIELD_GLOBALRATE = "gllcrate";
	public static String FIELD_ORGMNY = "olcmoney";
	public static String FIELD_GROUPMNY = "glcmoeny";
	public static String FIELD_GLOBALMNYFIELD = "gllmoeny";
	public static String FIELD_REDEEMMONEY = "redeemmoney";
	public static String FIELD_REALREANING = "realreaning";
	/**
	 * 业务所需常量
	 */
	public static String CONST_PFLOW_ISRELOADBILL="IS_RELOADBILL";  
	public static String CONST_PFLOW_CONTENT="content";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
