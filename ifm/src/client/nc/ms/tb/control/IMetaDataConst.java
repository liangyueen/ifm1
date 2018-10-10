package nc.ms.tb.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import nc.itf.bd.pub.IBDMetaDataIDConst;
import nc.itf.org.IOrgConst;
import nc.itf.org.IOrgMetaDataIDConst;
import nc.itf.tb.sysmaintain.BdContrastCache;
import nc.vo.mdm.pub.NtbLogger;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.tb.control.IdBdcontrastVO;
import nc.vo.tb.control.IdSysregVO;
import nc.vo.tb.pubutil.IBusiTermConst;


/**
 * UAP���������ĳ�����
 * @author qiaoye
 * lrx 2011-3-30 �޸�bug���Զ��嵵�������δ���ù�getAllDefDocs()������������ȷ��ȡId/Name
 */
public class IMetaDataConst {
	
	// �Ƿ��Ѿ���ʼ�������е���
	private static boolean isDocInited = false;

	//�������ƺ�ID֮���ӳ���ϵ
	private static HashMap<String, String> hmName2Id = null;
	//����ID������֮���ӳ���ϵ
	private static HashMap<String, String> hmId2Name = null;
	//�������ƺ�����ID֮���ӳ���ϵ
	private static HashMap<String, String> hmName2TypeId = null;
	//����Ԫ����id�ͷ�������id��ӳ���ϵ
	private static HashMap<String, String> hmName2ClassId = null;
	//����ҵ��Ԫ�ĵ���
	private static ArrayList<String> arrOrgName = null;
	//��ҵ��ϵͳ���õĻ�����������
	private static String[] allTbNames = null;
	private static String[] allFepNames = null;
	private static String[] allFinanNames = null;
	private static String[] allPurchNames = null;
	private static String[] allLiaNames = null;
	private static String[] alldefdocs = null;
	private static String[] allUsableRefMdNames = null;
	
	// ������������
	/**ҵ��Ԫ/����/�ɱ�����*/
	public static String STR_NAME_GROUP = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000235")/*����*/;
	public static String STR_NAME_CORPORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000236")/*��˾*/;
	public static String STR_NAME_DEPT = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000021")/*����*/;
	public static String STR_NAME_HRORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000237")/*������Դ��֯*/;
	public static String STR_NAME_FINANCEORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000022")/*������֯*/;
	public static String STR_NAME_FUNDORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000238")/*�ʽ���֯*/;
	public static String STR_NAME_PURCHASEORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000018")/*�ɹ���֯*/;
	public static String STR_NAME_SALESORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000239")/*������֯*/;
	public static String STR_NAME_STOCKORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000240")/*�����֯*/;
	public static String STR_NAME_TRAFFICORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000241")/*������֯*/;
	public static String STR_NAME_QCCENTER = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000242")/*�ʼ�����*/;
	public static String STR_NAME_ASSETORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000243")/*�ʲ���֯*/;
	public static String STR_NAME_MAINTAINORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000244")/*ά����֯*/;
	public static String STR_NAME_LIABILITYCENTER = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000245")/*��������*/;
	public static String STR_NAME_FACTORY = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000246")/*��Ŀ��֯*/;
	public static String STR_NAME_PLANBUDGETORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000020")/*Ԥ����֯*/;
	public static String STR_NAME_REPORTORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000247")/*������֯*/;
	public static String STR_NAME_COSTCENTER = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000248")/*�ɱ�����*/;  //
	
	public static String STR_NAME_PSNCL = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000249")/*��Ա���*/;
	public static String STR_NAME_BUSIMAN = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000250")/*��Ա*/;
	public static String STR_NAME_AREACLASS = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000251")/*��������*/;
	public static String STR_NAME_ADDRESSDOC = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000252")/*�ص㵵��*/;
	public static String STR_NAME_CUSTCLASS = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000253")/*�ͻ���������*/;
	public static String STR_NAME_CUSTSALECLASS = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000254")/*�ͻ����۷���*/;
	public static String STR_NAME_CUSTOMER = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000255")/*�ͻ�����*/;
	public static String STR_NAME_SUPPLIERCLASS = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000256")/*��Ӧ�̻�������*/;
	public static String STR_NAME_SUPPLIER = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000257")/*��Ӧ�̵���*/;
	public static String STR_NAME_UNIT = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000258")/*������λ*/;
	public static String STR_NAME_OPPDIMEN = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000259")/*����*/;  //
	public static String STR_NAME_MARBASCLASS = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000260")/*���ϻ�������*/;
	public static String STR_NAME_MATERIAL = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000261")/*���ϣ���汾��*/;  //
	public static String STR_NAME_PRODLINE = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000262")/*��Ʒ��*/;
	public static String STR_NAME_ACCOUNTSYSTEM = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000263")/*��Ŀ��ϵ*/;
	public static String STR_NAME_ACCOUNTCHART = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000170")/*��Ŀ��*/;
	public static String STR_NAME_ACCOUNT = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000131")/*��ƿ�Ŀ*/; 
	public static String STR_NAME_ACCOUNTWITHSYSTEM = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000125")/*������ϵ�Ļ�ƿ�Ŀ*/;
	
	public static String STR_NAME_CASHFLOW = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000127")/*�ֽ�������Ŀ*/;
	public static String STR_NAME_COSTSUBJ = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000126")/*��֧��Ŀ*/;
	public static String STR_NAME_FUNDPLAN = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000128")/*�ʽ�ƻ���Ŀ*/;
	public static String STR_NAME_BANKTYPE = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000264")/*�������*/;
	public static String STR_NAME_BANKDOC = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000265")/*���е���*/;
	public static String STR_NAME_BANKACCBAS = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000266")/*�����˻�*/;
	public static String STR_NAME_BALATYPE = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000267")/*���㷽ʽ*/;
	public static String STR_NAME_NOTETYPE = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000268")/*Ʊ������*/;
	public static String STR_NAME_PROJECTCLASS = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000269")/*��Ŀ����*/;
	public static String STR_NAME_PROJECT = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000270")/*��Ŀ*/;
	public static String STR_NAME_PROJECTROUTE = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000271")/*��Ŀ·����Ϣ*/; //
	public static String STR_NAME_TRANSPORTTYPE = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000272")/*���䷽ʽ*/;
	public static String STR_NAME_SETOFBOOK = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000273")/*�˲�*/;
	public static String STR_NAME_ACCOUNTINGBOOK = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000169")/*��������˲�*/;
//	public static String STR_NAME_ASSETBOOK = "�ʲ������˲�";
	public static String STR_NAME_LIABILITYBOOK = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000129")/*���κ����˲�*/;
	public static String STR_NAME_COSTREGION = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000274")/*�ɱ���*/;
	public static String STR_NAME_CREDITCTLREGION = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000275")/*���ÿ�����*/;
	public static String STR_NAME_CURRTYPE = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000276")/*��ҵ���*/;
	public static String STR_NAME_STORDOC = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000277")/*�ֿ�*/;
//	public static String STR_NAME_RECEIVEANDSENDTYPE = "ҵ����Ϣ-�շ����";  //
//	public static String STR_NAME_FINANTYPE = "�ʽ�����(�Զ��嵵��)";
	public static String STR_NAME_DEFDOCLIST = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000278")/*�Զ��嵵���б�*/;
	public static String STR_NAME_ASSETCATEGORY = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000279")/*�ʲ����*/;
	public static String STR_NAME_LISTBOOK = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000280")/*���κ���Ҫ�ر�*/;
	public static String STR_NAME_ELEMENTSACCOUNT = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000130")/*����Ҫ��*/;
	public static String STR_NAME_ELEMENTSACCOUNTSYSTEM = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000124")/*������ϵ�ĺ���Ҫ��*/;
	public static String STR_NAME_BANKACCSUB=NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000281")/*ʹ��Ȩ����*/;
	public static String STR_NAME_KSYHZH = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000282")/*���������˻�*/;
	public static String STR_NAME_NBZH = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000283")/*�ڲ��˻�*/;
	public static String STR_NAME_XJZH = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000284")/*�ֽ��ʻ�*/;
	public static String STR_NAME_RY = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000250")/*��Ա*/;
	public static String STR_NAME_WLDBB = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000261")/*���ϣ���汾��*/;
	
	// ������������ID
	public static String STR_ID_ORG = IOrgMetaDataIDConst.ORG;  //ҵ��Ԫ
	public static String STR_ID_GROUP = IOrgMetaDataIDConst.GROUP;    //����
	public static String STR_ID_CORPORG = IOrgMetaDataIDConst.CORP;  //��˾
	public static String STR_ID_DEPT = IOrgMetaDataIDConst.DEPT;     //����
	public static String STR_ID_HRORG = IOrgMetaDataIDConst.HRORG;    //������Դ��֯
	public static String STR_ID_FINANCEORG = IOrgMetaDataIDConst.FINANCEORG;  //������֯
	public static String STR_ID_FUNDORG = IOrgMetaDataIDConst.FUNDORG;   //�ʽ���֯
	public static String STR_ID_PURCHASEORG = IOrgMetaDataIDConst.PURCHASEORG;  //�ɹ���֯
	public static String STR_ID_SALESORG = IOrgMetaDataIDConst.SALESORG;  //������֯
	public static String STR_ID_STOCKORG = IOrgMetaDataIDConst.STOCKORG;  //�����֯
	public static String STR_ID_TRAFFICORG = IOrgMetaDataIDConst.TRAFFICORG;  //������֯
	public static String STR_ID_QCCENTER = IOrgMetaDataIDConst.QCCENTER;   //�ʼ�����
	public static String STR_ID_ASSETORG = IOrgMetaDataIDConst.ASSETORG;   //�ʲ���֯
	public static String STR_ID_MAINTAINORG = IOrgMetaDataIDConst.MAINTAINORG;  //ά����֯
	public static String STR_ID_LIABILITYCENTER = IOrgMetaDataIDConst.LIABILITYCENTER;  //��������
	public static String STR_ID_FACTORY = IOrgMetaDataIDConst.FACTORY;   //��Ŀ��֯
	public static String STR_ID_PLANBUDGETORG = IOrgMetaDataIDConst.PLANBUDGET;  //Ԥ����֯
	public static String STR_ID_REPORTORG = IOrgMetaDataIDConst.REPORTORG;  //������֯
	public static String STR_ID_COSTCENTER = IOrgMetaDataIDConst.COSTCENTER/*"�ɱ�����"*/;  //

	public static String STR_ID_PSNCL = IBDMetaDataIDConst.PSNCL;  //��Ա���
	public static String STR_ID_BUSIMAN = IBDMetaDataIDConst.PSNDOC;  //��Ա
	public static String STR_ID_AREACLASS = IBDMetaDataIDConst.AREACLASS;  //��������
	public static String STR_ID_ADDRESSDOC = IBDMetaDataIDConst.ADDRESSDOC;  //�ص㵵��
	public static String STR_ID_CUSTCLASS = IBDMetaDataIDConst.CUSTCLASS;  //�ͻ���������
	public static String STR_ID_CUSTSALECLASS = IBDMetaDataIDConst.CUSTSALECLASS;  //�ͻ����۷���
	public static String STR_ID_CUSTOMER = IBDMetaDataIDConst.CUSTOMER;  //�ͻ�����
	public static String STR_ID_SUPPLIERCLASS = IBDMetaDataIDConst.SUPPLIERCLASS;  //��Ӧ�̻�������
	public static String STR_ID_SUPPLIER = IBDMetaDataIDConst.SUPPLIER;  //��Ӧ�̵���
	public static String STR_ID_UNIT = IBDMetaDataIDConst.MEASDOC;   //������λ
	public static String STR_ID_OPPDIMEN = "ba120015-ed40-45e1-aa26-a513b9a3bb51"; //����//
	public static String STR_ID_MARBASCLASS = IBDMetaDataIDConst.MARBASCLASS;  //���ϻ�������
	public static String STR_ID_MATERIAL = IBDMetaDataIDConst.MATERIAL;  //���ϵ���
	public static String STR_ID_PRODLINE = IBDMetaDataIDConst.PRODLINE;  //��Ʒ��
	public static String STR_ID_ACCOUNTSYSTEM= IBDMetaDataIDConst.ACCSYSTEM;  //��Ŀ��ϵ
	public static String STR_ID_ACCOUNTCHART = IBDMetaDataIDConst.ACCCHART;  //��Ŀ��
	public static String STR_ID_ACCOUNT = IBDMetaDataIDConst.ACCOUNT;   //������ϵ�Ļ�ƿ�Ŀ
	public static String STR_ID_CASHFLOW = IBDMetaDataIDConst.CASHFLOW;  //�ֽ�������Ŀ
	public static String STR_ID_COSTSUBJ = IBDMetaDataIDConst.INOUTBUSICLASS;  //��֧��Ŀ
	public static String STR_ID_FUNDPLAN = IBDMetaDataIDConst.FUNDPLAN;  //�ʽ�ƻ���Ŀ
	public static String STR_ID_BANKTYPE = IBDMetaDataIDConst.BANKTYPE;  //�������
	public static String STR_ID_BANKDOC = IBDMetaDataIDConst.BANKDOC;   //���е���
	public static String STR_ID_BANKACCBAS = IBDMetaDataIDConst.BANKACCCTRL;  //�����ʻ�
	public static String STR_ID_BALATYPE = IBDMetaDataIDConst.BALATYPE;  //���㷽ʽ
	public static String STR_ID_NOTETYPE = IBDMetaDataIDConst.NOTETYPE;  //Ʊ������
	public static String STR_ID_PROJECTCLASS = "b0f3f52d-1582-4f8f-a466-753aa0d5462a";//IBDMetaDataIDConst.PROJECTCLASS;  //��Ŀ����
	public static String STR_ID_PROJECT = "2ee58f9b-781b-469f-b1d8-1816842515c3";//IBDMetaDataIDConst.PROJECT;   //��Ŀ
	public static String STR_ID_PROJECTROUTE = "fc347f58-4437-4f3f-bb3b-94901e58ad1d";  //��Ŀ·����Ϣ//
	public static String STR_ID_TRANSPORTTYPE = IBDMetaDataIDConst.TRANSPORTTYPE;  //���䷽ʽ
	public static String STR_ID_SETOFBOOK = IOrgMetaDataIDConst.SETOFBOOK;  //�˲�
	public static String STR_ID_ACCOUNTINGBOOK = IOrgMetaDataIDConst.ACCOUNTINGBOOK; //���˺����˲�
	public static String STR_ID_ASSETBOOK = IOrgMetaDataIDConst.ASSETBOOK;  //�ʲ������˲�
	public static String STR_ID_LIABILITYBOOK = IOrgMetaDataIDConst.LIABILITYBOOK;  //���κ����˲�
	public static String STR_ID_COSTREGION = IOrgMetaDataIDConst.COSTREGION;  //�ɱ���
	public static String STR_ID_CREDITCTLREGION = IOrgMetaDataIDConst.CREDITCTLREGION; //���ÿ�����
	public static String STR_ID_CURRTYPE = IBDMetaDataIDConst.CURRTYPE;  //���ֵ���//
	public static String STR_ID_STORDOC = IBDMetaDataIDConst.STORDOC;  //�ֿ�
	public static String STR_ID_RECEIVEANDSENDTYPE = IBDMetaDataIDConst.RECEIVEANDSENDTYPE; //ҵ����Ϣ-�շ����
//	public static String STR_ID_FINANTYPE = "1003ZZ10000000000669"; 
	public static String STR_ID_DEFDOCLIST = IBDMetaDataIDConst.DEFDOCLIST;  //�Զ��嵵���б�//
	public static String STR_ID_ASSETCATEGORY = "e681473c-5fb5-4aad-b14d-5a3db593bd64";  //�ʲ����
	public static String STR_ID_LISTBOOK="091b5f42-1340-4bc5-8edd-cc5574834f54";//���κ���Ҫ�ر�
	public static String STR_ID_ELEMENTSACCOUNT = "5b31b33c-3217-466a-9e25-c2ce5e4a9105"; //����Ҫ��
	public static String STR_ID_ELEMENTSACCOUNTSYSTEM = "5b31b33c-3217-466a-9e25-c2ce5e4a9105";  //����ϵ�ĺ���Ҫ��
    public static String STR_ID_BANKACCSUB=IBDMetaDataIDConst.BANKACCSUB;
	public static String STR_ID_KSYHZH = "08aca301-1a69-4257-b549-b29e992e35ea";  //YUYONGA
	public static String STR_ID_NBZH = "eee6ed75-8561-438d-8b43-427edca43716";
	public static String STR_ID_XJZH = "d5d5b39d-2c93-4686-be49-e08a0a5f5afd";
	public static String STR_ID_RY = "40d39c26-a2b6-4f16-a018-45664cac1a1f";
	public static String STR_ID_WLDBB = "c7dc0ccd-8872-4eee-8882-160e8f49dfad";
	public static String STR_ID_YWLZB = "39c3fd7d-68e7-4ef5-8401-5770188e3fa8"; // ҵ����ָ��

	static {	
		// ����Name��Id��ӳ��
		hmName2Id = new HashMap<String, String>();
		hmName2Id.put(STR_NAME_GROUP, STR_ID_GROUP);  //����
		hmName2Id.put(STR_NAME_CORPORG, STR_ID_CORPORG);  //��˾
		hmName2Id.put(STR_NAME_DEPT, STR_ID_DEPT);  //����
		hmName2Id.put(STR_NAME_HRORG, STR_ID_HRORG);  //������Դ��֯
		hmName2Id.put(STR_NAME_FINANCEORG, STR_ID_FINANCEORG);  //������֯
		hmName2Id.put(STR_NAME_FUNDORG, STR_ID_FUNDORG);  //�ʽ���֯
		hmName2Id.put(STR_NAME_PURCHASEORG, STR_ID_PURCHASEORG); //�ɹ���֯
		hmName2Id.put(STR_NAME_SALESORG, STR_ID_SALESORG); //������֯
		hmName2Id.put(STR_NAME_STOCKORG, STR_ID_STOCKORG);  //�����֯
		hmName2Id.put(STR_NAME_TRAFFICORG, STR_ID_TRAFFICORG);  //������֯
		hmName2Id.put(STR_NAME_QCCENTER, STR_ID_QCCENTER);  //�ʼ�����
		hmName2Id.put(STR_NAME_ASSETORG, STR_ID_ASSETORG);  //�ʲ���֯
		hmName2Id.put(STR_NAME_MAINTAINORG, STR_ID_MAINTAINORG);  //ά����֯
		hmName2Id.put(STR_NAME_LIABILITYCENTER, STR_ID_LIABILITYCENTER);  //��������
		hmName2Id.put(STR_NAME_FACTORY, STR_ID_FACTORY);  //��Ŀ��֯
		hmName2Id.put(STR_NAME_PLANBUDGETORG, STR_ID_PLANBUDGETORG);  //Ԥ����֯
		hmName2Id.put(STR_NAME_REPORTORG, STR_ID_REPORTORG);  //������֯
		
		hmName2Id.put(STR_NAME_PSNCL, STR_ID_PSNCL);  //��Ա���
		hmName2Id.put(STR_NAME_BUSIMAN, STR_ID_BUSIMAN);  //��Ա
		hmName2Id.put(STR_NAME_AREACLASS, STR_ID_AREACLASS);   //��������
		hmName2Id.put(STR_NAME_ADDRESSDOC, STR_ID_ADDRESSDOC); //�ص㵵��
		hmName2Id.put(STR_NAME_CUSTCLASS, STR_ID_CUSTCLASS);  //�ͻ���������
		hmName2Id.put(STR_NAME_CUSTSALECLASS, STR_ID_CUSTSALECLASS);  //�ͻ����۷���
		hmName2Id.put(STR_NAME_CUSTOMER, STR_ID_CUSTOMER);  //�ͻ�����
		hmName2Id.put(STR_NAME_SUPPLIERCLASS, STR_ID_SUPPLIERCLASS);  //��Ӧ�̻�������
		hmName2Id.put(STR_NAME_SUPPLIER, STR_ID_SUPPLIER);  //��Ӧ�̵���
		hmName2Id.put(STR_NAME_UNIT, STR_ID_UNIT);  //������λ
		hmName2Id.put(STR_NAME_OPPDIMEN, STR_ID_OPPDIMEN);  //����
		hmName2Id.put(STR_NAME_MARBASCLASS, STR_ID_MARBASCLASS);  //���ϻ�������
		hmName2Id.put(STR_NAME_MATERIAL, STR_ID_MATERIAL);  //���ϵ���
		hmName2Id.put(STR_NAME_PRODLINE, STR_ID_PRODLINE);  //��Ʒ��
		hmName2Id.put(STR_NAME_ACCOUNTSYSTEM, STR_ID_ACCOUNTSYSTEM);  //��Ŀ��ϵ
		hmName2Id.put(STR_NAME_ACCOUNTCHART, STR_ID_ACCOUNTCHART);  //��Ŀ��
		hmName2Id.put(STR_NAME_ACCOUNT, STR_ID_ACCOUNT);  //������ϵ�Ļ�ƿ�Ŀ
		hmName2Id.put(STR_NAME_ACCOUNTWITHSYSTEM, STR_ID_ACCOUNT);  //������ϵ�Ļ�ƿ�Ŀ
		hmName2Id.put(STR_NAME_CASHFLOW, STR_ID_CASHFLOW);  //�ֽ�������Ŀ
		hmName2Id.put(STR_NAME_COSTSUBJ, STR_ID_COSTSUBJ);  //��֧��Ŀ
		hmName2Id.put(STR_NAME_FUNDPLAN, STR_ID_FUNDPLAN);  //�ʽ�ƻ���Ŀ
		hmName2Id.put(STR_NAME_BANKTYPE, STR_ID_BANKTYPE);  //�������
		hmName2Id.put(STR_NAME_BANKDOC, STR_ID_BANKDOC);    //���е���
		hmName2Id.put(STR_NAME_BANKACCBAS, STR_ID_BANKACCBAS);  //�����˻�
		hmName2Id.put(STR_NAME_BALATYPE, STR_ID_BALATYPE);   //���㷽ʽ
		hmName2Id.put(STR_NAME_NOTETYPE, STR_ID_NOTETYPE);   //Ʊ�����
		hmName2Id.put(STR_NAME_PROJECTCLASS, STR_ID_PROJECTCLASS);  //��Ŀ����
		hmName2Id.put(STR_NAME_PROJECT, STR_ID_PROJECT);  //��Ŀ
		hmName2Id.put(STR_NAME_PROJECTROUTE, STR_ID_PROJECTROUTE);  //��Ŀ·����Ϣ
		hmName2Id.put(STR_NAME_TRANSPORTTYPE, STR_ID_TRANSPORTTYPE);  //���䷽ʽ
		hmName2Id.put(STR_NAME_SETOFBOOK, STR_ID_SETOFBOOK);  //�˲�
		hmName2Id.put(STR_NAME_ACCOUNTINGBOOK, STR_ID_ACCOUNTINGBOOK);  //���˺����˲�
//		hmName2Id.put(STR_NAME_ASSETBOOK, STR_ID_ASSETBOOK);  //�ʲ������˲�
		hmName2Id.put(STR_NAME_LIABILITYBOOK, STR_ID_LIABILITYBOOK);  //���κ����˲�
		hmName2Id.put(STR_NAME_COSTREGION, STR_ID_COSTREGION);  //�ɱ���
		hmName2Id.put(STR_NAME_CREDITCTLREGION, STR_ID_CREDITCTLREGION);  //���ÿ�����
		hmName2Id.put(STR_NAME_CURRTYPE, STR_ID_CURRTYPE);  //���ֵ���
		hmName2Id.put(STR_NAME_STORDOC, STR_ID_STORDOC);  //�ֿ�
//		hmName2Id.put(STR_NAME_RECEIVEANDSENDTYPE, STR_ID_RECEIVEANDSENDTYPE);  //ҵ����Ϣ-�շ����
		hmName2Id.put(STR_NAME_DEFDOCLIST, STR_ID_DEFDOCLIST);  //�Զ��嵵���б�
		hmName2Id.put(STR_NAME_ASSETCATEGORY, STR_ID_ASSETCATEGORY); //�ʲ����
		hmName2Id.put(STR_NAME_LISTBOOK, STR_ID_LISTBOOK); //���κ���Ҫ�ر�
		hmName2Id.put(STR_NAME_ELEMENTSACCOUNT, STR_ID_ELEMENTSACCOUNT); //����Ҫ��
		hmName2Id.put(STR_NAME_ELEMENTSACCOUNTSYSTEM,STR_ID_ELEMENTSACCOUNTSYSTEM);  //����ϵ�ĺ���Ҫ��
		hmName2Id.put(STR_NAME_BANKACCSUB, STR_ID_BANKACCSUB);//Ӧ���˻��ӻ�
		hmName2Id.put(STR_NAME_KSYHZH, STR_ID_KSYHZH);  //���������ʻ�
		hmName2Id.put(STR_NAME_NBZH,STR_ID_NBZH); //�ڲ��˻�
		hmName2Id.put(STR_NAME_XJZH,STR_ID_XJZH); //�ֽ��˻�
		hmName2Id.put(STR_NAME_RY, STR_ID_RY);  //��Ա
		hmName2Id.put(STR_NAME_WLDBB, STR_ID_WLDBB);  //���϶�汾
		

		// ����Id��Name��ӳ��
		hmId2Name = new HashMap<String, String>();
		hmId2Name.put(STR_ID_GROUP, STR_NAME_GROUP);  //����
		hmId2Name.put(STR_ID_CORPORG, STR_NAME_CORPORG);  //��˾
		hmId2Name.put(STR_ID_DEPT, STR_NAME_DEPT);  //����
		hmId2Name.put(STR_ID_HRORG, STR_NAME_HRORG);  //������Դ��֯
		hmId2Name.put(STR_ID_FINANCEORG, STR_NAME_FINANCEORG);  //������֯
		hmId2Name.put(STR_ID_FUNDORG, STR_NAME_FUNDORG);  //�ʽ���֯
		hmId2Name.put(STR_ID_PURCHASEORG, STR_NAME_PURCHASEORG); //�ɹ���֯
		hmId2Name.put(STR_ID_SALESORG, STR_NAME_SALESORG); //������֯
		hmId2Name.put(STR_ID_STOCKORG, STR_NAME_STOCKORG);  //���
		hmId2Name.put(STR_ID_TRAFFICORG, STR_NAME_TRAFFICORG);  //����
		hmId2Name.put(STR_ID_QCCENTER, STR_NAME_QCCENTER);  //�ʼ�
		hmId2Name.put(STR_ID_ASSETORG, STR_NAME_ASSETORG);  //�ʲ�
		hmId2Name.put(STR_ID_MAINTAINORG, STR_NAME_MAINTAINORG);  //ά����֯
		hmId2Name.put(STR_ID_LIABILITYCENTER, STR_NAME_LIABILITYCENTER);  //��������
		hmId2Name.put(STR_ID_FACTORY, STR_NAME_FACTORY);  //��Ŀ��֯
		hmId2Name.put(STR_ID_PLANBUDGETORG, STR_NAME_PLANBUDGETORG);  //Ԥ����֯
		hmId2Name.put(STR_ID_REPORTORG, STR_NAME_REPORTORG);  //������֯
		
		
		hmId2Name.put(STR_ID_PSNCL, STR_NAME_PSNCL);  //��Ա���
		hmId2Name.put(STR_ID_BUSIMAN, STR_NAME_BUSIMAN);  //��Ա
		hmId2Name.put(STR_ID_AREACLASS, STR_NAME_AREACLASS);   //��������
		hmId2Name.put(STR_ID_ADDRESSDOC, STR_NAME_ADDRESSDOC); //�ص㵵��
		hmId2Name.put(STR_ID_CUSTCLASS, STR_NAME_CUSTCLASS);  //�ͻ���������
		hmId2Name.put(STR_ID_CUSTSALECLASS, STR_NAME_CUSTSALECLASS);  //�ͻ����۷���
		hmId2Name.put(STR_ID_CUSTOMER, STR_NAME_CUSTOMER);  //�ͻ�����
		hmId2Name.put(STR_ID_SUPPLIERCLASS, STR_NAME_SUPPLIERCLASS);  //��Ӧ�̻�������
		hmId2Name.put(STR_ID_SUPPLIER, STR_NAME_SUPPLIER);  //��Ӧ�̵���
		hmId2Name.put(STR_ID_UNIT, STR_NAME_UNIT);  //������λ
		hmId2Name.put(STR_ID_OPPDIMEN, STR_NAME_OPPDIMEN);  //����
		hmId2Name.put(STR_ID_MARBASCLASS, STR_NAME_MARBASCLASS);  //���ϻ�������
		hmId2Name.put(STR_ID_MATERIAL, STR_NAME_MATERIAL);  //���ϵ���
		hmId2Name.put(STR_ID_PRODLINE, STR_NAME_PRODLINE);  //��Ʒ��
		hmId2Name.put(STR_ID_ACCOUNTSYSTEM, STR_NAME_ACCOUNTSYSTEM);  //��Ŀ��ϵ
		hmId2Name.put(STR_ID_ACCOUNTCHART, STR_NAME_ACCOUNTCHART);  //��Ŀ��
		hmId2Name.put(STR_ID_ACCOUNT, STR_NAME_ACCOUNT);  //������ϵ�Ļ�ƿ�Ŀ
		hmId2Name.put(STR_ID_CASHFLOW, STR_NAME_CASHFLOW);  //�ֽ�������Ŀ
		hmId2Name.put(STR_ID_COSTSUBJ, STR_NAME_COSTSUBJ);  //��֧��Ŀ
		hmId2Name.put(STR_ID_FUNDPLAN, STR_NAME_FUNDPLAN);  //�ʽ�ƻ���Ŀ
		hmId2Name.put(STR_ID_BANKTYPE, STR_NAME_BANKTYPE);  //�������
		hmId2Name.put(STR_ID_BANKDOC, STR_NAME_BANKDOC);    //���е���
		hmId2Name.put(STR_ID_BANKACCBAS, STR_NAME_BANKACCBAS);  //�����˻�
		hmId2Name.put(STR_ID_BALATYPE, STR_NAME_BALATYPE);   //���㷽ʽ
		hmId2Name.put(STR_ID_NOTETYPE, STR_NAME_NOTETYPE);   //Ʊ�����
		hmId2Name.put(STR_ID_PROJECTCLASS, STR_NAME_PROJECTCLASS);  //��Ŀ����
		hmId2Name.put(STR_ID_PROJECT, STR_NAME_PROJECT);  //��Ŀ
		hmId2Name.put(STR_ID_PROJECTROUTE, STR_NAME_PROJECTROUTE);  //��Ŀ·����Ϣ
		hmId2Name.put(STR_ID_TRANSPORTTYPE, STR_NAME_TRANSPORTTYPE);  //���䷽ʽ
		hmId2Name.put(STR_ID_SETOFBOOK, STR_NAME_SETOFBOOK);  //�˲�
		hmId2Name.put(STR_ID_ACCOUNTINGBOOK, STR_NAME_ACCOUNTINGBOOK);  //���˺����˲�
//		hmId2Name.put(STR_ID_ASSETBOOK, STR_NAME_ASSETBOOK);  //�ʲ������˲�
		hmId2Name.put(STR_ID_LIABILITYBOOK, STR_NAME_LIABILITYBOOK);  //���κ����˲�
		hmId2Name.put(STR_ID_COSTREGION, STR_NAME_COSTREGION);  //�ɱ���
		hmId2Name.put(STR_ID_CREDITCTLREGION, STR_NAME_CREDITCTLREGION);  //���ÿ�����
		hmId2Name.put(STR_ID_CURRTYPE, STR_NAME_CURRTYPE);  //���ֵ���
		hmId2Name.put(STR_ID_STORDOC, STR_NAME_STORDOC);  //�ֿ�
//		hmId2Name.put(STR_ID_RECEIVEANDSENDTYPE, STR_NAME_RECEIVEANDSENDTYPE);  //ҵ����Ϣ-�շ����
		hmId2Name.put(STR_ID_DEFDOCLIST, STR_NAME_DEFDOCLIST);  //�Զ��嵵���б�
		hmId2Name.put(STR_ID_ASSETCATEGORY, STR_NAME_ASSETCATEGORY); //�ʲ����
		hmId2Name.put(STR_ID_LISTBOOK,STR_NAME_LISTBOOK); //���κ���Ҫ�ر�
		hmId2Name.put(STR_ID_ELEMENTSACCOUNT,STR_NAME_ELEMENTSACCOUNT); //����Ҫ��
		hmId2Name.put(STR_ID_KSYHZH, STR_NAME_KSYHZH);  //���������ʻ�
		hmId2Name.put(STR_ID_NBZH,STR_NAME_NBZH); //�ڲ��˻�
		hmId2Name.put(STR_ID_XJZH,STR_NAME_XJZH); //�ֽ��˻�
		hmId2Name.put(STR_ID_RY,STR_NAME_RY);  //��Ա
		hmId2Name.put(STR_ID_RY,STR_NAME_WLDBB);  //���϶�汾
		
		
		//������֯���Ƶ���֯����ID��ӳ��
		hmName2TypeId = new HashMap<String, String>();
		hmName2TypeId.put(STR_NAME_GROUP, IOrgConst.GROUPORGTYPE);  //����
		hmName2TypeId.put(STR_NAME_CORPORG, IOrgConst.CORPORGTYPE);  //��˾
		hmName2TypeId.put(STR_NAME_DEPT, IOrgConst.DEPTORGTYPE);  //����
		hmName2TypeId.put(STR_NAME_HRORG, IOrgConst.HRORGORGTYPE);  //������Դ��֯
		hmName2TypeId.put(STR_NAME_FINANCEORG, IOrgConst.FINANCEORGTYPE);  //������֯
		hmName2TypeId.put(STR_NAME_FUNDORG, IOrgConst.FUNDORGTYPE);  //�ʽ���֯
		hmName2TypeId.put(STR_NAME_PURCHASEORG, IOrgConst.PURCHASEORGTYPE); //�ɹ���֯
		hmName2TypeId.put(STR_NAME_SALESORG, IOrgConst.SALEORGTYPE); //������֯
		hmName2TypeId.put(STR_NAME_STOCKORG, IOrgConst.STOCKORGTYPE);  //�����֯
		hmName2TypeId.put(STR_NAME_TRAFFICORG, IOrgConst.TRAFFICORGTYPE);  //������֯
		hmName2TypeId.put(STR_NAME_QCCENTER, IOrgConst.QCCENTERTYPE);  //�ʼ�����
		hmName2TypeId.put(STR_NAME_ASSETORG, IOrgConst.ASSETORGTYPE);  //�ʲ���֯
		hmName2TypeId.put(STR_NAME_MAINTAINORG, IOrgConst.MAINTAINORGTYPE);  //ά����֯
		hmName2TypeId.put(STR_NAME_LIABILITYCENTER, IOrgConst.LIACENTERTYPE);  //��������
		hmName2TypeId.put(STR_NAME_FACTORY, IOrgConst.ITEMORGTYPE);  //��Ŀ��֯
		hmName2TypeId.put(STR_NAME_PLANBUDGETORG, IOrgConst.PLANBUDGETTYPE);  //Ԥ����֯
		hmName2TypeId.put(STR_NAME_REPORTORG, IOrgConst.REPORTORGTYPE);  //������֯
		hmName2TypeId.put(STR_ID_BANKACCSUB,STR_NAME_BANKACCSUB);//Ӧ���˻��ӻ�
		
		//����Ԫ���ݵ�����ά��id��ӳ��
		hmName2ClassId = new HashMap<String, String>();
		hmName2ClassId.put(STR_NAME_ADDRESSDOC, STR_ID_AREACLASS);  //��ַ����
		hmName2ClassId.put(STR_NAME_BANKDOC, STR_NAME_BANKTYPE);  //���е���
		hmName2ClassId.put(STR_NAME_CUSTOMER, STR_NAME_CUSTCLASS);  //�ͻ�����
		hmName2ClassId.put(STR_NAME_MATERIAL, STR_NAME_MARBASCLASS);  //���ϵ���
		hmName2ClassId.put(STR_NAME_PROJECT, STR_NAME_PROJECTCLASS);  //��Ŀ
		hmName2ClassId.put(STR_NAME_BUSIMAN, STR_NAME_PSNCL);  //��Ա
		hmName2ClassId.put(STR_NAME_SUPPLIER, STR_NAME_SUPPLIERCLASS);  //��Ӧ�̷���
		
		//������֯���͵�UAP����
		arrOrgName = new ArrayList<String>();
		arrOrgName.add(STR_NAME_GROUP);
		arrOrgName.add(STR_NAME_CORPORG);
		arrOrgName.add(STR_NAME_DEPT);
		arrOrgName.add(STR_NAME_HRORG);
		arrOrgName.add(STR_NAME_FINANCEORG);
		arrOrgName.add(STR_NAME_FUNDORG);
		arrOrgName.add(STR_NAME_PURCHASEORG);
		arrOrgName.add(STR_NAME_SALESORG);
		arrOrgName.add(STR_NAME_STOCKORG);
		arrOrgName.add(STR_NAME_TRAFFICORG);
		arrOrgName.add(STR_NAME_QCCENTER);
		arrOrgName.add(STR_NAME_ASSETORG);
		arrOrgName.add(STR_NAME_MAINTAINORG);
		arrOrgName.add(STR_NAME_LIABILITYCENTER);
		arrOrgName.add(STR_NAME_FACTORY);
		arrOrgName.add(STR_NAME_PLANBUDGETORG);
		arrOrgName.add(STR_NAME_REPORTORG);
		arrOrgName.add(STR_NAME_COSTCENTER);
	}

	


	/**
	 * ͨ�����ƻ������ID
	 */
	public static String getTypeIdByName(String name){
		return hmName2TypeId.get(name);
	}
	
	/**
	 * ͨ�����ƻ�ö�Ӧ����ά�ȵ�ID
	 */
	public static String getClassIdByName(String name){
		return hmName2ClassId.get(name);
	}
	
	/**
	 * ͨ�������������ƻ�ò��յĽڵ���������
	 */
	public static String getDescByName(String name) {
		if (name == null)
			return "";
		else if (name.equals(STR_NAME_CURRTYPE))
			return NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000285")/*���ֵ���*/;
		/**yuyonga�޸� 2011-05-26,��֪��ʲôԭ��Ҫ������ת��,����ʹ�ò�������ʲ��޷�����*/
//		else if(name.equals(STR_NAME_ACCOUNTINGBOOK))
//			return "���˺����˲�";
		else
			return name;
	}
	
	/**
	 * ����������֯���͵Ļ�������
	 */
	public static ArrayList<String> getOrgMetaData(){
		return arrOrgName;
	}

	/**
	 * ����ҵ��ϵͳ��ÿ��õ�UAP����
	 * @param busicode
	 * @return
	 */
	public static String[] getEnableUapDoc(String busicode) {
		if(busicode == null || "".equals(busicode))
			return null;
		if(busicode.equals(IBusiTermConst.SYS_TB)||busicode.equals(IBusiTermConst.SYS_CTB))
			return getAllTbNames(busicode);
		else if(busicode.equals(IBusiTermConst.SYS_FEP))
			return getAllFepNames();
		else if(busicode.equals(IBusiTermConst.SYS_FINAN))
			return getAllFinanNames();
		else if(busicode.equals(IBusiTermConst.SYS_PURCH))
			return getAllPurchNames();
		else if(busicode.equals(IBusiTermConst.SYS_LIA))
			return getAllLiaNames();
		return null;
	}
	
	private static void reloadUapDoc(String[] name) {
		IdBdcontrastVO[] vos = null;
		try{
			vos = BdContrastCache.getNewInstance().getAllUapVo();
		}catch(BusinessException ex){
			NtbLogger.error(ex);
		}
		ArrayList<String> nameList = new ArrayList<String> ();
		nameList.addAll(Arrays.asList(name));
		for(int n=0;n<vos.length;n++){
			IdBdcontrastVO vo = vos[n];
			if(!nameList.contains(vo.getBdinfo_type())){
				/**���ҽ������չ�ϵ*/
				hmName2Id.put(vo.getBdinfo_type(), vo.getPk_bdinfo());
				hmId2Name.put(vo.getPk_bdinfo(), vo.getBdinfo_type());
			}
		}
	}
	
	private static String[] getExtUapDoc(String busicode,String[] name) {
		if(busicode == null || "".equals(busicode))
			return null;
		reloadUapDoc(name);
		/**�ֽ���������ų̡���ҵ��Ʊ�������ո����ʽ���㡢�ʽ���ȡ�NC���ʡ��������Ľӿڡ�*/
		String[] tmpFP = new String[] {"CMP","PS","FBM","FTS","SF","GL","erm","ARAP","TMC","IFM","CCC"};//tm weiyjc ֧��˾����Ԥ�����
		String[] tmpMPP = new String[] {"PO","MPP"};
		String[] tmpFEP = new String[] {"GL","ARAP","erm","CMP"};
		
		ArrayList<String> nameList = new ArrayList<String> ();
		if(busicode.equals(IBusiTermConst.SYS_TB)||busicode.equals(IBusiTermConst.SYS_CTB)){
			IdBdcontrastVO[] vos = null;
			try{
				vos = BdContrastCache.getNewInstance().getAllUapVo();
			}catch(BusinessException ex){
				NtbLogger.error(ex);
			}

			nameList.addAll(Arrays.asList(name));
			for(int n=0;n<vos.length;n++){
				IdBdcontrastVO vo = vos[n];
				if(vo.getPk_bdinfo().equals(IBDMetaDataIDConst.CURRTYPE))
					continue;
				if(!nameList.contains(vo.getBdinfo_type())){
					/**˵��������������û��,��Ҫ����*/
					nameList.add(vo.getBdinfo_type());
				}
			}
		}else if(busicode.equals(IBusiTermConst.SYS_FEP)){
			IdBdcontrastVO[] vos = null;;
			try {
				vos = BdContrastCache.getNewInstance().getRealUapVo(tmpFEP);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				NtbLogger.error(e);
			}
			nameList.addAll(Arrays.asList(name));
			for(int n=0;n<vos.length;n++){
				IdBdcontrastVO vo = vos[n];
				if(vo.getPk_bdinfo().equals(IBDMetaDataIDConst.CURRTYPE))
					continue;
				if(!nameList.contains(vo.getBdinfo_type())){
					/**˵��������������û��,��Ҫ����*/
					nameList.add(vo.getBdinfo_type());
				}
			}
		}
		else if(busicode.equals(IBusiTermConst.SYS_FINAN)){
			IdBdcontrastVO[] vos = null;;
			try {
				vos = BdContrastCache.getNewInstance().getRealUapVo(tmpFP);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				NtbLogger.error(e);
			}
			nameList.addAll(Arrays.asList(name));
			for(int n=0;n<vos.length;n++){
				IdBdcontrastVO vo = vos[n];
				if(vo.getPk_bdinfo().equals(IBDMetaDataIDConst.CURRTYPE))
					continue;
				if(!nameList.contains(vo.getBdinfo_type())){
					/**˵��������������û��,��Ҫ����*/
					nameList.add(vo.getBdinfo_type());
				}
			}
		}
		else if(busicode.equals(IBusiTermConst.SYS_PURCH)){
			IdBdcontrastVO[] vos = null;
			try {
				vos = BdContrastCache.getNewInstance().getRealUapVo(tmpMPP);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				NtbLogger.error(e);
			}
			nameList.addAll(Arrays.asList(name));
			for(int n=0;n<vos.length;n++){
				IdBdcontrastVO vo = vos[n];
				if(vo.getPk_bdinfo().equals(IBDMetaDataIDConst.CURRTYPE))
					continue;
				if(!nameList.contains(vo.getBdinfo_type())){
					/**˵��������������û��,��Ҫ����*/
					nameList.add(vo.getBdinfo_type());
				}
			}
		}
		return nameList.toArray(new String[0]);
	}
	
	/**����ҵ��ϵͳ��ȡ���õ�ȡ���Ϳ���ϵͳ yuyonga����*/
	public static IdSysregVO[] getEnableSysReg(String busicode,IdSysregVO[] vos) {
		if(busicode == null || "".equals(busicode))
			return null;
		/**�ֽ���������ų̡���ҵ��Ʊ�������ո����ʽ���㡢�ʽ���ȡ�NC���ʡ��������Ľӿڡ�*/
		String[] tmpFP = new String[] {"CMP","PS","FBM","FTS","SF","GL","erm","ARAP","CDM","ICDM","CFBM","LCM","TMPUB","CC","BGM","TMC","IFM"};//tm weiyjc ֧��˾����Ԥ�����
		String[] tmpMPP = new String[] {"PO","MPP"};
		String[] tmpFEP = new String[] {"GL","ARAP","erm","CMP"};
		String[] tmpSAP = new String[] {"IC","PO","SO","TO","MMPAC"};
		
		ArrayList<IdSysregVO> vosList = new ArrayList<IdSysregVO> ();
		if(busicode.equals(IBusiTermConst.SYS_TB)||busicode.equals(IBusiTermConst.SYS_CTB)){
			vosList.addAll(Arrays.asList(vos));
		}else if(busicode.equals(IBusiTermConst.SYS_FEP)){
			ArrayList<String> sysid = new ArrayList<String> ();
			sysid.addAll(Arrays.asList(tmpFEP));
			for(int n=0;n<vos.length;n++){
				String sys_id = vos[n].getSysid();
				if(sysid.contains(sys_id)){
					vosList.add(vos[n]);
				}
			}
		}
		else if(busicode.equals(IBusiTermConst.SYS_FINAN)){
			ArrayList<String> sysid = new ArrayList<String> ();
			sysid.addAll(Arrays.asList(tmpFP));
			for(int n=0;n<vos.length;n++){
				String sys_id = vos[n].getSysid();
				if(sysid.contains(sys_id)){
					vosList.add(vos[n]);
				}
			}
		}
		else if(busicode.equals(IBusiTermConst.SYS_PURCH)){
			ArrayList<String> sysid = new ArrayList<String> ();
			sysid.addAll(Arrays.asList(tmpMPP));
			for(int n=0;n<vos.length;n++){
				String sys_id = vos[n].getSysid();
				if(sysid.contains(sys_id)){
					vosList.add(vos[n]);
				}
			}
		}
		else if(busicode.equals(IBusiTermConst.SYS_SOP)){
			ArrayList<String> sysid = new ArrayList<String> ();
			sysid.addAll(Arrays.asList(tmpSAP));
			for(int n=0;n<vos.length;n++){
				String sys_id = vos[n].getSysid();
				if(sysid.contains(sys_id)){
					vosList.add(vos[n]);
				}
			}
		}
		return vosList.toArray(new IdSysregVO[0]);
	}
	

	
	/**
	 * �������еĻ���������������
	 * @return
	 */
	private static synchronized String[] getAllTbNames(String syscode) {
		if(allTbNames == null){
			// ���п�ӳ���ȫ��������������
			String[] tmp = new String[] { STR_NAME_GROUP, STR_NAME_CORPORG, STR_NAME_DEPT,
					STR_NAME_HRORG, STR_NAME_FINANCEORG, STR_NAME_FUNDORG, STR_NAME_PURCHASEORG,
					STR_NAME_SALESORG, STR_NAME_STOCKORG, STR_NAME_TRAFFICORG,
					STR_NAME_QCCENTER, STR_NAME_ASSETORG, STR_NAME_MAINTAINORG, 
					STR_NAME_LIABILITYCENTER, STR_NAME_FACTORY, STR_NAME_PLANBUDGETORG,
					STR_NAME_REPORTORG, STR_NAME_PSNCL, STR_NAME_BUSIMAN, STR_NAME_AREACLASS,
					STR_NAME_ADDRESSDOC, STR_NAME_CUSTCLASS, STR_NAME_CUSTSALECLASS, 
					STR_NAME_CUSTOMER, STR_NAME_SUPPLIERCLASS, STR_NAME_SUPPLIER,
					STR_NAME_MARBASCLASS, STR_NAME_MATERIAL, STR_NAME_PRODLINE,
					STR_NAME_CASHFLOW, STR_NAME_COSTSUBJ, STR_NAME_FUNDPLAN, 
					STR_NAME_BANKTYPE, STR_NAME_BANKDOC, STR_NAME_BANKACCBAS,
					STR_NAME_BALATYPE, STR_NAME_NOTETYPE, STR_NAME_PROJECTCLASS, STR_NAME_PROJECT,
					/*STR_NAME_PROJECTROUTE,*/ STR_NAME_TRANSPORTTYPE,STR_NAME_SETOFBOOK, 
					STR_NAME_ACCOUNTINGBOOK, /*STR_NAME_ASSETBOOK,*/ STR_NAME_LIABILITYBOOK, 
					STR_NAME_COSTREGION, STR_NAME_CREDITCTLREGION, /*STR_NAME_CURRTYPE, */STR_NAME_ASSETCATEGORY,
					STR_NAME_STORDOC,/*, STR_NAME_RECEIVEANDSENDTYPE*/STR_NAME_ACCOUNTWITHSYSTEM,
//					STR_NAME_ACCOUNT, STR_NAME_FINANTYPE, STR_NAME_DEFDOCLIST,
					STR_NAME_ELEMENTSACCOUNT,STR_NAME_BANKACCSUB,STR_NAME_NBZH,STR_NAME_XJZH,
					//��������Ա���� ȥ��һ�� modify by chenleid STR_NAME_RY
					};
			String[] docs = getExtUapDoc(syscode,tmp);
			allTbNames = docs/*new String[tmp.length + docs.length]*/;
			System.arraycopy(tmp, 0, allTbNames, 0, tmp.length);
//			System.arraycopy(docs, 0, allTbNames, tmp.length, docs.length);
		}
		return allTbNames;
	}
	
    /**
     * ���ط��üƻ����û������ݵ���
     * @return
     */
	private static synchronized String[] getAllFepNames(){
		if(allFepNames == null){
			//���üƻ�  :������˾�����š�ҵ��Ա���ͻ�����Ӧ�̡���Ʒ�ߵ�������Ŀ����֧��Ŀ����Ŀ�׶�*����Ŀ
			String[] tmp =  new String[] { STR_NAME_CORPORG, STR_NAME_DEPT, 
					STR_NAME_BUSIMAN, STR_NAME_CUSTOMER, STR_NAME_SUPPLIER,
					STR_NAME_PRODLINE,STR_NAME_ACCOUNTWITHSYSTEM, STR_NAME_COSTSUBJ, STR_NAME_PROJECT
					};
			String[] docs = getExtUapDoc(IBusiTermConst.SYS_FEP,tmp);
			allFepNames = docs;/*new String[tmp.length + docs.length];*/
//			System.arraycopy(tmp, 0, allFepNames, 0, tmp.length);
//			System.arraycopy(docs, 0, allFepNames, tmp.length, docs.length);
		}

		return allFepNames;
	}
	
	/**
     * �����ʽ�ƻ����û������ݵ���
     * @return
     */
	private static synchronized String[] getAllFinanNames(){
		if(allFinanNames == null){
			//�ʽ�ƻ�: ���š�������֯���ɹ���֯���ͻ�����Ӧ�̡����ϻ������ࡢ�ֽ�������Ŀ����֧��Ŀ���ʽ�ƻ���Ŀ��
			//         �������͡����е����������˻������㷽ʽ��Ʊ�����͡���Ŀ���͡���Ŀ�������Զ������*  �ʽ����͡�
			String[] tmp = new String[] { STR_NAME_DEPT, STR_NAME_FINANCEORG, /**STR_NAME_FINANTYPE,*/ STR_NAME_PURCHASEORG,
					STR_NAME_CUSTOMER, STR_NAME_SUPPLIER, STR_NAME_MARBASCLASS, 
					STR_NAME_CASHFLOW, STR_NAME_COSTSUBJ, STR_NAME_FUNDPLAN, 
					STR_NAME_BANKTYPE, STR_NAME_BANKDOC, STR_NAME_BANKACCBAS,
					STR_NAME_BALATYPE, STR_NAME_NOTETYPE,
					STR_NAME_PROJECTCLASS, STR_NAME_PROJECT, 
					STR_NAME_DEFDOCLIST ,STR_NAME_BANKACCSUB,STR_NAME_NBZH,STR_NAME_XJZH,STR_NAME_RY,STR_NAME_WLDBB};
			String[] docs = getExtUapDoc(IBusiTermConst.SYS_FINAN,tmp);
			allFinanNames = docs; /* new String[tmp.length + docs.length];*/
//			System.arraycopy(tmp, 0, allFinanNames, 0, tmp.length);
//			System.arraycopy(docs, 0, allFinanNames, tmp.length, docs.length);
		}
		return allFinanNames;
	}
	
	/**
     * ���زɹ��ƻ����û������ݵ���
     * @return
     */
	private static synchronized String[] getAllPurchNames(){
		if(allPurchNames == null){
			//�ɹ��ƻ�:��ϵͳ�����ļ�ȷ���������������˾�����ŵ����� �ɹ���֯�������֯�����ϻ������ࡢ���ϵ����� ��Ŀ������
			String[] tmp = new String[] { STR_NAME_CORPORG,  STR_NAME_DEPT, STR_NAME_PURCHASEORG, 
					STR_NAME_STOCKORG, STR_NAME_MARBASCLASS, STR_NAME_MATERIAL, STR_NAME_PROJECT };
			String[] docs = getExtUapDoc(IBusiTermConst.SYS_PURCH,tmp);
			allPurchNames = docs;/*new String[tmp.length + docs.length];*/
//			System.arraycopy(tmp, 0, allPurchNames, 0, tmp.length);
//			System.arraycopy(docs, 0, allPurchNames, tmp.length, docs.length);
		}
		return allPurchNames;
	}
	
	/**
     * �������λ�ƿ��û������ݵ���
     * @return
     */
	private static synchronized String[] getAllLiaNames(){
		if(allLiaNames == null){
			//���λ��
			allLiaNames = new String[]{};
		}
		return allLiaNames;
	}
	
	/**
	 * �������п���������Ļ���������������
	 * @return
	 */
	private static synchronized String[] getAllUsableRefMdNames() {
		if(allUsableRefMdNames == null){
			// ���пɵ���Ļ�����������
			allUsableRefMdNames = new String[] { STR_NAME_GROUP, STR_NAME_CORPORG, STR_NAME_DEPT,
					STR_NAME_HRORG, STR_NAME_FINANCEORG, STR_NAME_FUNDORG,  STR_NAME_PURCHASEORG,
					STR_NAME_SALESORG, STR_NAME_STOCKORG, STR_NAME_TRAFFICORG, STR_NAME_QCCENTER, 
					STR_NAME_ASSETORG, STR_NAME_MAINTAINORG,  STR_NAME_LIABILITYCENTER, STR_NAME_FACTORY, 
					STR_NAME_PLANBUDGETORG, STR_NAME_REPORTORG, STR_NAME_PSNCL, STR_NAME_BUSIMAN,
					STR_NAME_AREACLASS, STR_NAME_ADDRESSDOC, STR_NAME_CUSTCLASS, STR_NAME_CUSTSALECLASS, STR_NAME_CUSTOMER,
					STR_NAME_SUPPLIERCLASS, STR_NAME_SUPPLIER, STR_NAME_MARBASCLASS, STR_NAME_MATERIAL, 
					STR_NAME_PRODLINE, STR_NAME_CASHFLOW, STR_NAME_COSTSUBJ,STR_NAME_FUNDPLAN, 
					STR_NAME_BANKTYPE, STR_NAME_BANKDOC, STR_NAME_BANKACCBAS, STR_NAME_BALATYPE, STR_NAME_NOTETYPE,
					STR_NAME_PROJECTCLASS, STR_NAME_PROJECT, /* STR_NAME_PROJECTROUTE */ STR_NAME_TRANSPORTTYPE,
					STR_NAME_SETOFBOOK, STR_NAME_ACCOUNTINGBOOK, /*STR_NAME_ASSETBOOK,*/ STR_NAME_LIABILITYBOOK,  
					STR_NAME_COSTREGION, STR_NAME_CREDITCTLREGION, 
					STR_NAME_STORDOC/*, STR_NAME_RECEIVEANDSENDTYPE*/
					/*STR_NAME_ACCOUNT, STR_NAME_FINANTYPE*/};
		}
		return allUsableRefMdNames;
	}
	
	/**
	 * ͨ�����ƻ��Id
	 */
	public static String getIdByName(String name) {
		String rtn = hmName2Id.get(name);
		return rtn/*hmName2Id.get(name)*/;
	}
	
	
	

	
}
