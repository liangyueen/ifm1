package nc.vo.ifm.constants;

/**
 * Ͷ���ʳ���
 * @version ncc1.0
 */
public class TMIFMConst {
	/**
	 * ģ��
	 */
	public static String CONST_MODULE="TMIFM";
	public static String CONST_MODULE_CODE="3667";
	
	/**
	 * �������ͳ���
	 */
	public static String CONST_BILLTYPE_REDEEM="3642";
	public static String CONST_BILLTYPE_APPLY="3641";
	public static String CONST_BILLTYPE_INCOME="3643";
	/**
	 * ���ö���
	 */
	public static String CONST_ACTION_SAVEBASE="SAVEBASE"; // ����
	public static String CONST_ACTION_DELETE="DELETE"; // ɾ��
	public static String CONST_ACTION_SAVE="SAVE"; // �ύ
	public static String CONST_ACTION_UNSAVEBILL="UNSAVEBILL"; // �ջ�
	public static String CONST_ACTION_APPROVE="APPROVE"; // ����
	public static String CONST_ACTION_UNAPPROVE="UNAPPROVE";  // ȡ������
	
	// �ʽ�����б�ҳ�����
	public static String CONST_PAGECODE_CONTRACT_LIST = "36670RED_LIST";
	// �ʽ���ؿ�Ƭҳ�����
	public static String CONST_PAGECODE_CONTRACT_CARD = "36670RED_CARD";
	// �ʽ����Ӧ�ñ���
	public static String CONST_APPCODE_CONTRACT = "36670RED";
	// �ʽ�����б�ҳ���������
	public static String CONST_AREACODE_REDEEM_LIST_TABLE = "table";
	// �ʽ���ز�ѯ�������
	public static String CONST_AREACODE_CONTRACT_LIST_SEARCH = "search";
	
	// Ͷ�������б�ҳ�����
	public static String CONST_PAGECODE_INCOME_LIST = "36670IPR_LIST";
	// Ͷ�����濨Ƭҳ�����
	public static String CONST_PAGECODE_INCOME_CARD = "36670IPR_CARD";
	// Ͷ������Ӧ�ñ���
	public static String CONST_APPCODE_INCOME = "36670IPR";
	// Ͷ�������б�ҳ���������
	public static String CONST_AREACODE_INCOME_LIST_TABLE = "table";
	// Ͷ�������б�ҳ���������
	public static String CONST_AREACODE_INCOME_LIST_SEARCH = "search";
	
	//��س�����������
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
	 * ҵ�����賣��
	 */
	public static String CONST_PFLOW_ISRELOADBILL="IS_RELOADBILL";  
	public static String CONST_PFLOW_CONTENT="content";
	
	/**
	 * Ԥ����������
	 */
	public static String HeadTableName_apply="ifm_apply";
	public static String HeadTableName_redeem="ifm_redeem";
	public static String HeadTableName_income="ifm_income";
	
	/**
	 * �깺����״̬
	 */
	public static Integer BS_APPLY_WAITCOMMIT = 0; // ���ύ
	public static Integer BS_APPLY_WAITAPPROVE = 1; // ������
	public static Integer BS_APPLY_APPROVED = 2; // �������
	public static Integer BS_APPLY_CANCELED = 3; // �ѳ���
	/**
	 * ��ص���״̬
	 */
	public static Integer BS_REDEEM_WAITCOMMIT = 0; // ���ύ
	public static Integer BS_REDEEM_WAITAPPROVE = 1; // ������
	public static Integer BS_REDEEM_ALL = 2; // ȫ�����
	public static Integer BS_REDEEM_SUB = 3; // �������
	/**
	 * ���浥��״̬
	 */
	public static Integer BS_INCOME_WAITCOMMIT = 1; // ���ύ
	public static Integer BS_INCOME_WAITAPPROVE = 2; // ������
	public static Integer BS_INCOME_FINSHED = 3; // ȫ�����
	
	
	
	
}
