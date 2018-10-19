package nc.vo.ifm.constants;

/**
 * 投融资常量
 * @version ncc1.0
 */
public class TMIFMConst {
	/**
	 * 模块
	 */
	public static String CONST_MODULE="TMIFM";
	public static String CONST_MODULE_CODE="3667";
	
	/**
	 * 单据类型常量
	 */
	public static String CONST_BILLTYPE_REDEEM="3642";
	public static String CONST_BILLTYPE_APPLY="3641";
	public static String CONST_BILLTYPE_INCOME="3643";
	/**
	 * 常用动作
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
	
	//赎回常用属性名称
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
	
	/**
	 * 预算所需属性
	 */
	public static String HeadTableName_apply="ifm_apply";
	public static String HeadTableName_redeem="ifm_redeem";
	public static String HeadTableName_income="ifm_income";
	
	/**
	 * 申购单据状态
	 */
	public static Integer BS_APPLY_WAITCOMMIT = 0; // 待提交
	public static Integer BS_APPLY_WAITAPPROVE = 1; // 待审批
	public static Integer BS_APPLY_APPROVED = 2; // 审批完成
	public static Integer BS_APPLY_CANCELED = 3; // 已撤单
	/**
	 * 赎回单据状态
	 */
	public static Integer BS_REDEEM_WAITCOMMIT = 0; // 待提交
	public static Integer BS_REDEEM_WAITAPPROVE = 1; // 待审批
	public static Integer BS_REDEEM_ALL = 2; // 全部赎回
	public static Integer BS_REDEEM_SUB = 3; // 部分赎回
	/**
	 * 收益单据状态
	 */
	public static Integer BS_INCOME_WAITCOMMIT = 1; // 待提交
	public static Integer BS_INCOME_WAITAPPROVE = 2; // 待审批
	public static Integer BS_INCOME_FINSHED = 3; // 全部赎回
	
	
	
	
}
