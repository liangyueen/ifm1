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
 * UAP基础档案的常量类
 * @author qiaoye
 * lrx 2011-3-30 修改bug：自定义档案，如果未调用过getAllDefDocs()方法，不能正确获取Id/Name
 */
public class IMetaDataConst {
	
	// 是否已经初始化过所有档案
	private static boolean isDocInited = false;

	//保存名称和ID之间的映射关系
	private static HashMap<String, String> hmName2Id = null;
	//保存ID和名称之间的映射关系
	private static HashMap<String, String> hmId2Name = null;
	//保存名称和类型ID之间的映射关系
	private static HashMap<String, String> hmName2TypeId = null;
	//保存元数据id和分类类型id的映射关系
	private static HashMap<String, String> hmName2ClassId = null;
	//所有业务单元的档案
	private static ArrayList<String> arrOrgName = null;
	//各业务系统可用的基础档案名称
	private static String[] allTbNames = null;
	private static String[] allFepNames = null;
	private static String[] allFinanNames = null;
	private static String[] allPurchNames = null;
	private static String[] allLiaNames = null;
	private static String[] alldefdocs = null;
	private static String[] allUsableRefMdNames = null;
	
	// 基本档案名称
	/**业务单元/部门/成本中心*/
	public static String STR_NAME_GROUP = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000235")/*集团*/;
	public static String STR_NAME_CORPORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000236")/*公司*/;
	public static String STR_NAME_DEPT = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000021")/*部门*/;
	public static String STR_NAME_HRORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000237")/*人力资源组织*/;
	public static String STR_NAME_FINANCEORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000022")/*财务组织*/;
	public static String STR_NAME_FUNDORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000238")/*资金组织*/;
	public static String STR_NAME_PURCHASEORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000018")/*采购组织*/;
	public static String STR_NAME_SALESORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000239")/*销售组织*/;
	public static String STR_NAME_STOCKORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000240")/*库存组织*/;
	public static String STR_NAME_TRAFFICORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000241")/*物流组织*/;
	public static String STR_NAME_QCCENTER = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000242")/*质检中心*/;
	public static String STR_NAME_ASSETORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000243")/*资产组织*/;
	public static String STR_NAME_MAINTAINORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000244")/*维修组织*/;
	public static String STR_NAME_LIABILITYCENTER = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000245")/*利润中心*/;
	public static String STR_NAME_FACTORY = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000246")/*项目组织*/;
	public static String STR_NAME_PLANBUDGETORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000020")/*预算组织*/;
	public static String STR_NAME_REPORTORG = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000247")/*报表组织*/;
	public static String STR_NAME_COSTCENTER = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000248")/*成本中心*/;  //
	
	public static String STR_NAME_PSNCL = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000249")/*人员类别*/;
	public static String STR_NAME_BUSIMAN = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000250")/*人员*/;
	public static String STR_NAME_AREACLASS = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000251")/*地区分类*/;
	public static String STR_NAME_ADDRESSDOC = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000252")/*地点档案*/;
	public static String STR_NAME_CUSTCLASS = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000253")/*客户基本分类*/;
	public static String STR_NAME_CUSTSALECLASS = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000254")/*客户销售分类*/;
	public static String STR_NAME_CUSTOMER = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000255")/*客户档案*/;
	public static String STR_NAME_SUPPLIERCLASS = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000256")/*供应商基本分类*/;
	public static String STR_NAME_SUPPLIER = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000257")/*供应商档案*/;
	public static String STR_NAME_UNIT = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000258")/*计量单位*/;
	public static String STR_NAME_OPPDIMEN = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000259")/*量纲*/;  //
	public static String STR_NAME_MARBASCLASS = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000260")/*物料基本分类*/;
	public static String STR_NAME_MATERIAL = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000261")/*物料（多版本）*/;  //
	public static String STR_NAME_PRODLINE = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000262")/*产品线*/;
	public static String STR_NAME_ACCOUNTSYSTEM = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000263")/*科目体系*/;
	public static String STR_NAME_ACCOUNTCHART = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000170")/*科目表*/;
	public static String STR_NAME_ACCOUNT = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000131")/*会计科目*/; 
	public static String STR_NAME_ACCOUNTWITHSYSTEM = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000125")/*带有体系的会计科目*/;
	
	public static String STR_NAME_CASHFLOW = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000127")/*现金流量项目*/;
	public static String STR_NAME_COSTSUBJ = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000126")/*收支项目*/;
	public static String STR_NAME_FUNDPLAN = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000128")/*资金计划项目*/;
	public static String STR_NAME_BANKTYPE = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000264")/*银行类别*/;
	public static String STR_NAME_BANKDOC = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000265")/*银行档案*/;
	public static String STR_NAME_BANKACCBAS = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000266")/*银行账户*/;
	public static String STR_NAME_BALATYPE = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000267")/*结算方式*/;
	public static String STR_NAME_NOTETYPE = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000268")/*票据类型*/;
	public static String STR_NAME_PROJECTCLASS = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000269")/*项目类型*/;
	public static String STR_NAME_PROJECT = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000270")/*项目*/;
	public static String STR_NAME_PROJECTROUTE = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000271")/*项目路线信息*/; //
	public static String STR_NAME_TRANSPORTTYPE = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000272")/*运输方式*/;
	public static String STR_NAME_SETOFBOOK = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000273")/*账簿*/;
	public static String STR_NAME_ACCOUNTINGBOOK = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000169")/*财务核算账簿*/;
//	public static String STR_NAME_ASSETBOOK = "资产核算账簿";
	public static String STR_NAME_LIABILITYBOOK = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000129")/*责任核算账簿*/;
	public static String STR_NAME_COSTREGION = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000274")/*成本域*/;
	public static String STR_NAME_CREDITCTLREGION = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000275")/*信用控制域*/;
	public static String STR_NAME_CURRTYPE = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000276")/*外币档案*/;
	public static String STR_NAME_STORDOC = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000277")/*仓库*/;
//	public static String STR_NAME_RECEIVEANDSENDTYPE = "业务信息-收发类别";  //
//	public static String STR_NAME_FINANTYPE = "资金类型(自定义档案)";
	public static String STR_NAME_DEFDOCLIST = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000278")/*自定义档案列表*/;
	public static String STR_NAME_ASSETCATEGORY = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000279")/*资产类别*/;
	public static String STR_NAME_LISTBOOK = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000280")/*责任核算要素表*/;
	public static String STR_NAME_ELEMENTSACCOUNT = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000130")/*核算要素*/;
	public static String STR_NAME_ELEMENTSACCOUNTSYSTEM = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000124")/*带有体系的核算要素*/;
	public static String STR_NAME_BANKACCSUB=NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000281")/*使用权参照*/;
	public static String STR_NAME_KSYHZH = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000282")/*客商银行账户*/;
	public static String STR_NAME_NBZH = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000283")/*内部账户*/;
	public static String STR_NAME_XJZH = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000284")/*现金帐户*/;
	public static String STR_NAME_RY = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000250")/*人员*/;
	public static String STR_NAME_WLDBB = NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000261")/*物料（多版本）*/;
	
	// 基本档案类型ID
	public static String STR_ID_ORG = IOrgMetaDataIDConst.ORG;  //业务单元
	public static String STR_ID_GROUP = IOrgMetaDataIDConst.GROUP;    //集团
	public static String STR_ID_CORPORG = IOrgMetaDataIDConst.CORP;  //公司
	public static String STR_ID_DEPT = IOrgMetaDataIDConst.DEPT;     //部门
	public static String STR_ID_HRORG = IOrgMetaDataIDConst.HRORG;    //人力资源组织
	public static String STR_ID_FINANCEORG = IOrgMetaDataIDConst.FINANCEORG;  //财务组织
	public static String STR_ID_FUNDORG = IOrgMetaDataIDConst.FUNDORG;   //资金组织
	public static String STR_ID_PURCHASEORG = IOrgMetaDataIDConst.PURCHASEORG;  //采购组织
	public static String STR_ID_SALESORG = IOrgMetaDataIDConst.SALESORG;  //销售组织
	public static String STR_ID_STOCKORG = IOrgMetaDataIDConst.STOCKORG;  //库存组织
	public static String STR_ID_TRAFFICORG = IOrgMetaDataIDConst.TRAFFICORG;  //物流组织
	public static String STR_ID_QCCENTER = IOrgMetaDataIDConst.QCCENTER;   //质检中心
	public static String STR_ID_ASSETORG = IOrgMetaDataIDConst.ASSETORG;   //资产组织
	public static String STR_ID_MAINTAINORG = IOrgMetaDataIDConst.MAINTAINORG;  //维修组织
	public static String STR_ID_LIABILITYCENTER = IOrgMetaDataIDConst.LIABILITYCENTER;  //利润中心
	public static String STR_ID_FACTORY = IOrgMetaDataIDConst.FACTORY;   //项目组织
	public static String STR_ID_PLANBUDGETORG = IOrgMetaDataIDConst.PLANBUDGET;  //预算组织
	public static String STR_ID_REPORTORG = IOrgMetaDataIDConst.REPORTORG;  //报表组织
	public static String STR_ID_COSTCENTER = IOrgMetaDataIDConst.COSTCENTER/*"成本中心"*/;  //

	public static String STR_ID_PSNCL = IBDMetaDataIDConst.PSNCL;  //人员类别
	public static String STR_ID_BUSIMAN = IBDMetaDataIDConst.PSNDOC;  //人员
	public static String STR_ID_AREACLASS = IBDMetaDataIDConst.AREACLASS;  //地区分类
	public static String STR_ID_ADDRESSDOC = IBDMetaDataIDConst.ADDRESSDOC;  //地点档案
	public static String STR_ID_CUSTCLASS = IBDMetaDataIDConst.CUSTCLASS;  //客户基本分类
	public static String STR_ID_CUSTSALECLASS = IBDMetaDataIDConst.CUSTSALECLASS;  //客户销售分类
	public static String STR_ID_CUSTOMER = IBDMetaDataIDConst.CUSTOMER;  //客户档案
	public static String STR_ID_SUPPLIERCLASS = IBDMetaDataIDConst.SUPPLIERCLASS;  //供应商基本分类
	public static String STR_ID_SUPPLIER = IBDMetaDataIDConst.SUPPLIER;  //供应商档案
	public static String STR_ID_UNIT = IBDMetaDataIDConst.MEASDOC;   //计量单位
	public static String STR_ID_OPPDIMEN = "ba120015-ed40-45e1-aa26-a513b9a3bb51"; //量纲//
	public static String STR_ID_MARBASCLASS = IBDMetaDataIDConst.MARBASCLASS;  //物料基本分类
	public static String STR_ID_MATERIAL = IBDMetaDataIDConst.MATERIAL;  //物料档案
	public static String STR_ID_PRODLINE = IBDMetaDataIDConst.PRODLINE;  //产品线
	public static String STR_ID_ACCOUNTSYSTEM= IBDMetaDataIDConst.ACCSYSTEM;  //科目体系
	public static String STR_ID_ACCOUNTCHART = IBDMetaDataIDConst.ACCCHART;  //科目表
	public static String STR_ID_ACCOUNT = IBDMetaDataIDConst.ACCOUNT;   //带有体系的会计科目
	public static String STR_ID_CASHFLOW = IBDMetaDataIDConst.CASHFLOW;  //现金流量项目
	public static String STR_ID_COSTSUBJ = IBDMetaDataIDConst.INOUTBUSICLASS;  //收支项目
	public static String STR_ID_FUNDPLAN = IBDMetaDataIDConst.FUNDPLAN;  //资金计划项目
	public static String STR_ID_BANKTYPE = IBDMetaDataIDConst.BANKTYPE;  //银行类别
	public static String STR_ID_BANKDOC = IBDMetaDataIDConst.BANKDOC;   //银行档案
	public static String STR_ID_BANKACCBAS = IBDMetaDataIDConst.BANKACCCTRL;  //银行帐户
	public static String STR_ID_BALATYPE = IBDMetaDataIDConst.BALATYPE;  //结算方式
	public static String STR_ID_NOTETYPE = IBDMetaDataIDConst.NOTETYPE;  //票据类型
	public static String STR_ID_PROJECTCLASS = "b0f3f52d-1582-4f8f-a466-753aa0d5462a";//IBDMetaDataIDConst.PROJECTCLASS;  //项目类型
	public static String STR_ID_PROJECT = "2ee58f9b-781b-469f-b1d8-1816842515c3";//IBDMetaDataIDConst.PROJECT;   //项目
	public static String STR_ID_PROJECTROUTE = "fc347f58-4437-4f3f-bb3b-94901e58ad1d";  //项目路线信息//
	public static String STR_ID_TRANSPORTTYPE = IBDMetaDataIDConst.TRANSPORTTYPE;  //运输方式
	public static String STR_ID_SETOFBOOK = IOrgMetaDataIDConst.SETOFBOOK;  //账簿
	public static String STR_ID_ACCOUNTINGBOOK = IOrgMetaDataIDConst.ACCOUNTINGBOOK; //总账核算账簿
	public static String STR_ID_ASSETBOOK = IOrgMetaDataIDConst.ASSETBOOK;  //资产核算账簿
	public static String STR_ID_LIABILITYBOOK = IOrgMetaDataIDConst.LIABILITYBOOK;  //责任核算账簿
	public static String STR_ID_COSTREGION = IOrgMetaDataIDConst.COSTREGION;  //成本域
	public static String STR_ID_CREDITCTLREGION = IOrgMetaDataIDConst.CREDITCTLREGION; //信用控制域
	public static String STR_ID_CURRTYPE = IBDMetaDataIDConst.CURRTYPE;  //币种档案//
	public static String STR_ID_STORDOC = IBDMetaDataIDConst.STORDOC;  //仓库
	public static String STR_ID_RECEIVEANDSENDTYPE = IBDMetaDataIDConst.RECEIVEANDSENDTYPE; //业务信息-收发类别
//	public static String STR_ID_FINANTYPE = "1003ZZ10000000000669"; 
	public static String STR_ID_DEFDOCLIST = IBDMetaDataIDConst.DEFDOCLIST;  //自定义档案列表//
	public static String STR_ID_ASSETCATEGORY = "e681473c-5fb5-4aad-b14d-5a3db593bd64";  //资产类别
	public static String STR_ID_LISTBOOK="091b5f42-1340-4bc5-8edd-cc5574834f54";//责任核算要素表
	public static String STR_ID_ELEMENTSACCOUNT = "5b31b33c-3217-466a-9e25-c2ce5e4a9105"; //核算要素
	public static String STR_ID_ELEMENTSACCOUNTSYSTEM = "5b31b33c-3217-466a-9e25-c2ce5e4a9105";  //带体系的核算要素
    public static String STR_ID_BANKACCSUB=IBDMetaDataIDConst.BANKACCSUB;
	public static String STR_ID_KSYHZH = "08aca301-1a69-4257-b549-b29e992e35ea";  //YUYONGA
	public static String STR_ID_NBZH = "eee6ed75-8561-438d-8b43-427edca43716";
	public static String STR_ID_XJZH = "d5d5b39d-2c93-4686-be49-e08a0a5f5afd";
	public static String STR_ID_RY = "40d39c26-a2b6-4f16-a018-45664cac1a1f";
	public static String STR_ID_WLDBB = "c7dc0ccd-8872-4eee-8882-160e8f49dfad";
	public static String STR_ID_YWLZB = "39c3fd7d-68e7-4ef5-8401-5770188e3fa8"; // 业务量指标

	static {	
		// 建立Name到Id的映射
		hmName2Id = new HashMap<String, String>();
		hmName2Id.put(STR_NAME_GROUP, STR_ID_GROUP);  //集团
		hmName2Id.put(STR_NAME_CORPORG, STR_ID_CORPORG);  //公司
		hmName2Id.put(STR_NAME_DEPT, STR_ID_DEPT);  //部门
		hmName2Id.put(STR_NAME_HRORG, STR_ID_HRORG);  //人力资源组织
		hmName2Id.put(STR_NAME_FINANCEORG, STR_ID_FINANCEORG);  //财务组织
		hmName2Id.put(STR_NAME_FUNDORG, STR_ID_FUNDORG);  //资金组织
		hmName2Id.put(STR_NAME_PURCHASEORG, STR_ID_PURCHASEORG); //采购组织
		hmName2Id.put(STR_NAME_SALESORG, STR_ID_SALESORG); //销售组织
		hmName2Id.put(STR_NAME_STOCKORG, STR_ID_STOCKORG);  //库存组织
		hmName2Id.put(STR_NAME_TRAFFICORG, STR_ID_TRAFFICORG);  //物流组织
		hmName2Id.put(STR_NAME_QCCENTER, STR_ID_QCCENTER);  //质检中心
		hmName2Id.put(STR_NAME_ASSETORG, STR_ID_ASSETORG);  //资产组织
		hmName2Id.put(STR_NAME_MAINTAINORG, STR_ID_MAINTAINORG);  //维修组织
		hmName2Id.put(STR_NAME_LIABILITYCENTER, STR_ID_LIABILITYCENTER);  //利润中心
		hmName2Id.put(STR_NAME_FACTORY, STR_ID_FACTORY);  //项目组织
		hmName2Id.put(STR_NAME_PLANBUDGETORG, STR_ID_PLANBUDGETORG);  //预算组织
		hmName2Id.put(STR_NAME_REPORTORG, STR_ID_REPORTORG);  //报表组织
		
		hmName2Id.put(STR_NAME_PSNCL, STR_ID_PSNCL);  //人员类别
		hmName2Id.put(STR_NAME_BUSIMAN, STR_ID_BUSIMAN);  //人员
		hmName2Id.put(STR_NAME_AREACLASS, STR_ID_AREACLASS);   //地区分类
		hmName2Id.put(STR_NAME_ADDRESSDOC, STR_ID_ADDRESSDOC); //地点档案
		hmName2Id.put(STR_NAME_CUSTCLASS, STR_ID_CUSTCLASS);  //客户基本分类
		hmName2Id.put(STR_NAME_CUSTSALECLASS, STR_ID_CUSTSALECLASS);  //客户销售分类
		hmName2Id.put(STR_NAME_CUSTOMER, STR_ID_CUSTOMER);  //客户档案
		hmName2Id.put(STR_NAME_SUPPLIERCLASS, STR_ID_SUPPLIERCLASS);  //供应商基本分类
		hmName2Id.put(STR_NAME_SUPPLIER, STR_ID_SUPPLIER);  //供应商档案
		hmName2Id.put(STR_NAME_UNIT, STR_ID_UNIT);  //计量单位
		hmName2Id.put(STR_NAME_OPPDIMEN, STR_ID_OPPDIMEN);  //量纲
		hmName2Id.put(STR_NAME_MARBASCLASS, STR_ID_MARBASCLASS);  //物料基本分类
		hmName2Id.put(STR_NAME_MATERIAL, STR_ID_MATERIAL);  //物料档案
		hmName2Id.put(STR_NAME_PRODLINE, STR_ID_PRODLINE);  //产品线
		hmName2Id.put(STR_NAME_ACCOUNTSYSTEM, STR_ID_ACCOUNTSYSTEM);  //科目体系
		hmName2Id.put(STR_NAME_ACCOUNTCHART, STR_ID_ACCOUNTCHART);  //科目表
		hmName2Id.put(STR_NAME_ACCOUNT, STR_ID_ACCOUNT);  //带有体系的会计科目
		hmName2Id.put(STR_NAME_ACCOUNTWITHSYSTEM, STR_ID_ACCOUNT);  //带有体系的会计科目
		hmName2Id.put(STR_NAME_CASHFLOW, STR_ID_CASHFLOW);  //现金流量项目
		hmName2Id.put(STR_NAME_COSTSUBJ, STR_ID_COSTSUBJ);  //收支项目
		hmName2Id.put(STR_NAME_FUNDPLAN, STR_ID_FUNDPLAN);  //资金计划项目
		hmName2Id.put(STR_NAME_BANKTYPE, STR_ID_BANKTYPE);  //银行类别
		hmName2Id.put(STR_NAME_BANKDOC, STR_ID_BANKDOC);    //银行档案
		hmName2Id.put(STR_NAME_BANKACCBAS, STR_ID_BANKACCBAS);  //银行账户
		hmName2Id.put(STR_NAME_BALATYPE, STR_ID_BALATYPE);   //结算方式
		hmName2Id.put(STR_NAME_NOTETYPE, STR_ID_NOTETYPE);   //票据类别
		hmName2Id.put(STR_NAME_PROJECTCLASS, STR_ID_PROJECTCLASS);  //项目类型
		hmName2Id.put(STR_NAME_PROJECT, STR_ID_PROJECT);  //项目
		hmName2Id.put(STR_NAME_PROJECTROUTE, STR_ID_PROJECTROUTE);  //项目路线信息
		hmName2Id.put(STR_NAME_TRANSPORTTYPE, STR_ID_TRANSPORTTYPE);  //运输方式
		hmName2Id.put(STR_NAME_SETOFBOOK, STR_ID_SETOFBOOK);  //账簿
		hmName2Id.put(STR_NAME_ACCOUNTINGBOOK, STR_ID_ACCOUNTINGBOOK);  //总账核算账簿
//		hmName2Id.put(STR_NAME_ASSETBOOK, STR_ID_ASSETBOOK);  //资产核算账簿
		hmName2Id.put(STR_NAME_LIABILITYBOOK, STR_ID_LIABILITYBOOK);  //责任核算账簿
		hmName2Id.put(STR_NAME_COSTREGION, STR_ID_COSTREGION);  //成本域
		hmName2Id.put(STR_NAME_CREDITCTLREGION, STR_ID_CREDITCTLREGION);  //信用控制域
		hmName2Id.put(STR_NAME_CURRTYPE, STR_ID_CURRTYPE);  //币种档案
		hmName2Id.put(STR_NAME_STORDOC, STR_ID_STORDOC);  //仓库
//		hmName2Id.put(STR_NAME_RECEIVEANDSENDTYPE, STR_ID_RECEIVEANDSENDTYPE);  //业务信息-收发类别
		hmName2Id.put(STR_NAME_DEFDOCLIST, STR_ID_DEFDOCLIST);  //自定义档案列表
		hmName2Id.put(STR_NAME_ASSETCATEGORY, STR_ID_ASSETCATEGORY); //资产类别
		hmName2Id.put(STR_NAME_LISTBOOK, STR_ID_LISTBOOK); //责任核算要素表
		hmName2Id.put(STR_NAME_ELEMENTSACCOUNT, STR_ID_ELEMENTSACCOUNT); //核算要素
		hmName2Id.put(STR_NAME_ELEMENTSACCOUNTSYSTEM,STR_ID_ELEMENTSACCOUNTSYSTEM);  //带体系的核算要素
		hmName2Id.put(STR_NAME_BANKACCSUB, STR_ID_BANKACCSUB);//应行账户子户
		hmName2Id.put(STR_NAME_KSYHZH, STR_ID_KSYHZH);  //客商银行帐户
		hmName2Id.put(STR_NAME_NBZH,STR_ID_NBZH); //内部账户
		hmName2Id.put(STR_NAME_XJZH,STR_ID_XJZH); //现金账户
		hmName2Id.put(STR_NAME_RY, STR_ID_RY);  //人员
		hmName2Id.put(STR_NAME_WLDBB, STR_ID_WLDBB);  //物料多版本
		

		// 建立Id到Name的映射
		hmId2Name = new HashMap<String, String>();
		hmId2Name.put(STR_ID_GROUP, STR_NAME_GROUP);  //集团
		hmId2Name.put(STR_ID_CORPORG, STR_NAME_CORPORG);  //公司
		hmId2Name.put(STR_ID_DEPT, STR_NAME_DEPT);  //部门
		hmId2Name.put(STR_ID_HRORG, STR_NAME_HRORG);  //人力资源组织
		hmId2Name.put(STR_ID_FINANCEORG, STR_NAME_FINANCEORG);  //财务组织
		hmId2Name.put(STR_ID_FUNDORG, STR_NAME_FUNDORG);  //资金组织
		hmId2Name.put(STR_ID_PURCHASEORG, STR_NAME_PURCHASEORG); //采购组织
		hmId2Name.put(STR_ID_SALESORG, STR_NAME_SALESORG); //销售组织
		hmId2Name.put(STR_ID_STOCKORG, STR_NAME_STOCKORG);  //库存
		hmId2Name.put(STR_ID_TRAFFICORG, STR_NAME_TRAFFICORG);  //物流
		hmId2Name.put(STR_ID_QCCENTER, STR_NAME_QCCENTER);  //质检
		hmId2Name.put(STR_ID_ASSETORG, STR_NAME_ASSETORG);  //资产
		hmId2Name.put(STR_ID_MAINTAINORG, STR_NAME_MAINTAINORG);  //维修组织
		hmId2Name.put(STR_ID_LIABILITYCENTER, STR_NAME_LIABILITYCENTER);  //利润中心
		hmId2Name.put(STR_ID_FACTORY, STR_NAME_FACTORY);  //项目组织
		hmId2Name.put(STR_ID_PLANBUDGETORG, STR_NAME_PLANBUDGETORG);  //预算组织
		hmId2Name.put(STR_ID_REPORTORG, STR_NAME_REPORTORG);  //报表组织
		
		
		hmId2Name.put(STR_ID_PSNCL, STR_NAME_PSNCL);  //人员类别
		hmId2Name.put(STR_ID_BUSIMAN, STR_NAME_BUSIMAN);  //人员
		hmId2Name.put(STR_ID_AREACLASS, STR_NAME_AREACLASS);   //地区分类
		hmId2Name.put(STR_ID_ADDRESSDOC, STR_NAME_ADDRESSDOC); //地点档案
		hmId2Name.put(STR_ID_CUSTCLASS, STR_NAME_CUSTCLASS);  //客户基本分类
		hmId2Name.put(STR_ID_CUSTSALECLASS, STR_NAME_CUSTSALECLASS);  //客户销售分类
		hmId2Name.put(STR_ID_CUSTOMER, STR_NAME_CUSTOMER);  //客户档案
		hmId2Name.put(STR_ID_SUPPLIERCLASS, STR_NAME_SUPPLIERCLASS);  //供应商基本分类
		hmId2Name.put(STR_ID_SUPPLIER, STR_NAME_SUPPLIER);  //供应商档案
		hmId2Name.put(STR_ID_UNIT, STR_NAME_UNIT);  //计量单位
		hmId2Name.put(STR_ID_OPPDIMEN, STR_NAME_OPPDIMEN);  //量纲
		hmId2Name.put(STR_ID_MARBASCLASS, STR_NAME_MARBASCLASS);  //物料基本分类
		hmId2Name.put(STR_ID_MATERIAL, STR_NAME_MATERIAL);  //物料档案
		hmId2Name.put(STR_ID_PRODLINE, STR_NAME_PRODLINE);  //产品线
		hmId2Name.put(STR_ID_ACCOUNTSYSTEM, STR_NAME_ACCOUNTSYSTEM);  //科目体系
		hmId2Name.put(STR_ID_ACCOUNTCHART, STR_NAME_ACCOUNTCHART);  //科目表
		hmId2Name.put(STR_ID_ACCOUNT, STR_NAME_ACCOUNT);  //带有体系的会计科目
		hmId2Name.put(STR_ID_CASHFLOW, STR_NAME_CASHFLOW);  //现金流量项目
		hmId2Name.put(STR_ID_COSTSUBJ, STR_NAME_COSTSUBJ);  //收支项目
		hmId2Name.put(STR_ID_FUNDPLAN, STR_NAME_FUNDPLAN);  //资金计划项目
		hmId2Name.put(STR_ID_BANKTYPE, STR_NAME_BANKTYPE);  //银行类别
		hmId2Name.put(STR_ID_BANKDOC, STR_NAME_BANKDOC);    //银行档案
		hmId2Name.put(STR_ID_BANKACCBAS, STR_NAME_BANKACCBAS);  //银行账户
		hmId2Name.put(STR_ID_BALATYPE, STR_NAME_BALATYPE);   //结算方式
		hmId2Name.put(STR_ID_NOTETYPE, STR_NAME_NOTETYPE);   //票据类别
		hmId2Name.put(STR_ID_PROJECTCLASS, STR_NAME_PROJECTCLASS);  //项目类型
		hmId2Name.put(STR_ID_PROJECT, STR_NAME_PROJECT);  //项目
		hmId2Name.put(STR_ID_PROJECTROUTE, STR_NAME_PROJECTROUTE);  //项目路线信息
		hmId2Name.put(STR_ID_TRANSPORTTYPE, STR_NAME_TRANSPORTTYPE);  //运输方式
		hmId2Name.put(STR_ID_SETOFBOOK, STR_NAME_SETOFBOOK);  //账簿
		hmId2Name.put(STR_ID_ACCOUNTINGBOOK, STR_NAME_ACCOUNTINGBOOK);  //总账核算账簿
//		hmId2Name.put(STR_ID_ASSETBOOK, STR_NAME_ASSETBOOK);  //资产核算账簿
		hmId2Name.put(STR_ID_LIABILITYBOOK, STR_NAME_LIABILITYBOOK);  //责任核算账簿
		hmId2Name.put(STR_ID_COSTREGION, STR_NAME_COSTREGION);  //成本域
		hmId2Name.put(STR_ID_CREDITCTLREGION, STR_NAME_CREDITCTLREGION);  //信用控制域
		hmId2Name.put(STR_ID_CURRTYPE, STR_NAME_CURRTYPE);  //币种档案
		hmId2Name.put(STR_ID_STORDOC, STR_NAME_STORDOC);  //仓库
//		hmId2Name.put(STR_ID_RECEIVEANDSENDTYPE, STR_NAME_RECEIVEANDSENDTYPE);  //业务信息-收发类别
		hmId2Name.put(STR_ID_DEFDOCLIST, STR_NAME_DEFDOCLIST);  //自定义档案列表
		hmId2Name.put(STR_ID_ASSETCATEGORY, STR_NAME_ASSETCATEGORY); //资产类别
		hmId2Name.put(STR_ID_LISTBOOK,STR_NAME_LISTBOOK); //责任核算要素表
		hmId2Name.put(STR_ID_ELEMENTSACCOUNT,STR_NAME_ELEMENTSACCOUNT); //核算要素
		hmId2Name.put(STR_ID_KSYHZH, STR_NAME_KSYHZH);  //客商银行帐户
		hmId2Name.put(STR_ID_NBZH,STR_NAME_NBZH); //内部账户
		hmId2Name.put(STR_ID_XJZH,STR_NAME_XJZH); //现金账户
		hmId2Name.put(STR_ID_RY,STR_NAME_RY);  //人员
		hmId2Name.put(STR_ID_RY,STR_NAME_WLDBB);  //物料多版本
		
		
		//保存组织名称到组织类型ID的映射
		hmName2TypeId = new HashMap<String, String>();
		hmName2TypeId.put(STR_NAME_GROUP, IOrgConst.GROUPORGTYPE);  //集团
		hmName2TypeId.put(STR_NAME_CORPORG, IOrgConst.CORPORGTYPE);  //公司
		hmName2TypeId.put(STR_NAME_DEPT, IOrgConst.DEPTORGTYPE);  //部门
		hmName2TypeId.put(STR_NAME_HRORG, IOrgConst.HRORGORGTYPE);  //人力资源组织
		hmName2TypeId.put(STR_NAME_FINANCEORG, IOrgConst.FINANCEORGTYPE);  //财务组织
		hmName2TypeId.put(STR_NAME_FUNDORG, IOrgConst.FUNDORGTYPE);  //资金组织
		hmName2TypeId.put(STR_NAME_PURCHASEORG, IOrgConst.PURCHASEORGTYPE); //采购组织
		hmName2TypeId.put(STR_NAME_SALESORG, IOrgConst.SALEORGTYPE); //销售组织
		hmName2TypeId.put(STR_NAME_STOCKORG, IOrgConst.STOCKORGTYPE);  //库存组织
		hmName2TypeId.put(STR_NAME_TRAFFICORG, IOrgConst.TRAFFICORGTYPE);  //物流组织
		hmName2TypeId.put(STR_NAME_QCCENTER, IOrgConst.QCCENTERTYPE);  //质检中心
		hmName2TypeId.put(STR_NAME_ASSETORG, IOrgConst.ASSETORGTYPE);  //资产组织
		hmName2TypeId.put(STR_NAME_MAINTAINORG, IOrgConst.MAINTAINORGTYPE);  //维修组织
		hmName2TypeId.put(STR_NAME_LIABILITYCENTER, IOrgConst.LIACENTERTYPE);  //利润中心
		hmName2TypeId.put(STR_NAME_FACTORY, IOrgConst.ITEMORGTYPE);  //项目组织
		hmName2TypeId.put(STR_NAME_PLANBUDGETORG, IOrgConst.PLANBUDGETTYPE);  //预算组织
		hmName2TypeId.put(STR_NAME_REPORTORG, IOrgConst.REPORTORGTYPE);  //报表组织
		hmName2TypeId.put(STR_ID_BANKACCSUB,STR_NAME_BANKACCSUB);//应行账户子户
		
		//保存元数据到分类维度id的映射
		hmName2ClassId = new HashMap<String, String>();
		hmName2ClassId.put(STR_NAME_ADDRESSDOC, STR_ID_AREACLASS);  //地址档案
		hmName2ClassId.put(STR_NAME_BANKDOC, STR_NAME_BANKTYPE);  //银行档案
		hmName2ClassId.put(STR_NAME_CUSTOMER, STR_NAME_CUSTCLASS);  //客户档案
		hmName2ClassId.put(STR_NAME_MATERIAL, STR_NAME_MARBASCLASS);  //物料档案
		hmName2ClassId.put(STR_NAME_PROJECT, STR_NAME_PROJECTCLASS);  //项目
		hmName2ClassId.put(STR_NAME_BUSIMAN, STR_NAME_PSNCL);  //人员
		hmName2ClassId.put(STR_NAME_SUPPLIER, STR_NAME_SUPPLIERCLASS);  //供应商分类
		
		//保存组织类型的UAP档案
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
	 * 通过名称获得类型ID
	 */
	public static String getTypeIdByName(String name){
		return hmName2TypeId.get(name);
	}
	
	/**
	 * 通过名称获得对应分类维度的ID
	 */
	public static String getClassIdByName(String name){
		return hmName2ClassId.get(name);
	}
	
	/**
	 * 通过基础数据名称获得参照的节点类型名称
	 */
	public static String getDescByName(String name) {
		if (name == null)
			return "";
		else if (name.equals(STR_NAME_CURRTYPE))
			return NCLangRes4VoTransl.getNCLangRes().getStrByID("tbb_dim", "01801dim_000285")/*币种档案*/;
		/**yuyonga修改 2011-05-26,不知道什么原因要做这种转换,导致使用财务核算帐簿无法参照*/
//		else if(name.equals(STR_NAME_ACCOUNTINGBOOK))
//			return "总账核算账簿";
		else
			return name;
	}
	
	/**
	 * 返回所有组织类型的基础档案
	 */
	public static ArrayList<String> getOrgMetaData(){
		return arrOrgName;
	}

	/**
	 * 根据业务系统获得可用的UAP档案
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
				/**并且建立对照关系*/
				hmName2Id.put(vo.getBdinfo_type(), vo.getPk_bdinfo());
				hmId2Name.put(vo.getPk_bdinfo(), vo.getBdinfo_type());
			}
		}
	}
	
	private static String[] getExtUapDoc(String busicode,String[] name) {
		if(busicode == null || "".equals(busicode))
			return null;
		reloadUapDoc(name);
		/**现金管理、付款排程、商业汇票、财务收付、资金结算、资金调度、NC总帐、财务报销的接口。*/
		String[] tmpFP = new String[] {"CMP","PS","FBM","FTS","SF","GL","erm","ARAP","TMC","IFM","CCC"};//tm weiyjc 支持司库云预算控制
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
					/**说明基本档案里面没有,需要对照*/
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
					/**说明基本档案里面没有,需要对照*/
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
					/**说明基本档案里面没有,需要对照*/
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
					/**说明基本档案里面没有,需要对照*/
					nameList.add(vo.getBdinfo_type());
				}
			}
		}
		return nameList.toArray(new String[0]);
	}
	
	/**根据业务系统获取可用的取数和控制系统 yuyonga增加*/
	public static IdSysregVO[] getEnableSysReg(String busicode,IdSysregVO[] vos) {
		if(busicode == null || "".equals(busicode))
			return null;
		/**现金管理、付款排程、商业汇票、财务收付、资金结算、资金调度、NC总帐、财务报销的接口。*/
		String[] tmpFP = new String[] {"CMP","PS","FBM","FTS","SF","GL","erm","ARAP","CDM","ICDM","CFBM","LCM","TMPUB","CC","BGM","TMC","IFM"};//tm weiyjc 支持司库云预算控制
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
	 * 返回所有的基础数据类型名称
	 * @return
	 */
	private static synchronized String[] getAllTbNames(String syscode) {
		if(allTbNames == null){
			// 所有可映射的全部基础数据名称
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
					//有两个人员档案 去掉一个 modify by chenleid STR_NAME_RY
					};
			String[] docs = getExtUapDoc(syscode,tmp);
			allTbNames = docs/*new String[tmp.length + docs.length]*/;
			System.arraycopy(tmp, 0, allTbNames, 0, tmp.length);
//			System.arraycopy(docs, 0, allTbNames, tmp.length, docs.length);
		}
		return allTbNames;
	}
	
    /**
     * 返回费用计划可用基础数据档案
     * @return
     */
	private static synchronized String[] getAllFepNames(){
		if(allFepNames == null){
			//费用计划  :包括公司、部门、业务员、客户、供应商、产品线档案、科目、收支项目、项目阶段*、项目
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
     * 返回资金计划可用基础数据档案
     * @return
     */
	private static synchronized String[] getAllFinanNames(){
		if(allFinanNames == null){
			//资金计划: 部门、财务组织、采购组织、客户、供应商、物料基本分类、现金流量项目、收支项目、资金计划项目、
			//         银行类型、银行档案、银行账户、结算方式、票据类型、项目类型、项目档案、自定义项档案*  资金类型、
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
     * 返回采购计划可用基础数据档案
     * @return
     */
	private static synchronized String[] getAllPurchNames(){
		if(allPurchNames == null){
			//采购计划:由系统配置文件确定，具体包括：公司、部门档案、 采购组织、库存组织、物料基本分类、物料档案、 项目档案。
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
     * 返回责任会计可用基础数据档案
     * @return
     */
	private static synchronized String[] getAllLiaNames(){
		if(allLiaNames == null){
			//责任会计
			allLiaNames = new String[]{};
		}
		return allLiaNames;
	}
	
	/**
	 * 返回所有可用来导入的基础数据类型名称
	 * @return
	 */
	private static synchronized String[] getAllUsableRefMdNames() {
		if(allUsableRefMdNames == null){
			// 所有可导入的基础数据名称
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
	 * 通过名称获得Id
	 */
	public static String getIdByName(String name) {
		String rtn = hmName2Id.get(name);
		return rtn/*hmName2Id.get(name)*/;
	}
	
	
	

	
}
