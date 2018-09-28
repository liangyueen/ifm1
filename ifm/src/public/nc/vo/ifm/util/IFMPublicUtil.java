package nc.vo.ifm.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import nc.vo.pub.pf.BillStatusEnum;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.pf.pub.PfDataCache;
import nc.bs.uap.lock.PKLock;
import nc.itf.tam.outer.ITAMPubQueryService;
import nc.itf.uap.busibean.SysinitAccessor;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.pubitf.initgroup.InitGroupQuery;
import nc.pubitf.org.IOrgUnitPubService;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.org.FundOrgVO;
import nc.vo.org.OrgVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.constant.CDMBusConstant;
import nc.vo.pub.contract.ContractVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tam.account.accid.AccidPubQueryParamVO;
import nc.vo.tam.account.accid.IAccidouterConst;
import nc.vo.tm.pub.TMBusConstant;
import nc.vo.tmpub.util.ArrayUtil;
import nc.vo.tmpub.util.ModuleUtil;
import nc.vo.tmpub.util.StringUtil;
import nc.vo.tmpub.util.TMCurrencyUtil;
import nc.vo.trade.pub.IBillStatus;

/**
 * <p>票据共同工具类<br>
 * @author ydx
 */
public class IFMPublicUtil {

	/**
	 * 判断集合是否为空
	 *
	 * @param coll
	 * @return true:空；false:非空
	 */
	public static boolean isEmpty(Collection coll) {
		return (coll == null || coll.isEmpty());
	}

	private static Map billTypemap = new HashMap();



	/**
	 * 判断是否与其他系统集成应用
	 * @param pk_org
	 * @param para
	 * @return
	 * @throws BusinessException
	 */
	public static boolean isTogatherWithOther(String pk_org, String para) throws BusinessException {
		UFBoolean togather = SysinitAccessor.getInstance().getParaBoolean(pk_org, para);
		if (togather == null) {
			return false;
		}
		return togather.booleanValue();
	}

	/**
	 * 判断计划平台是否启用
	 * @param pk_group
	 * @throws BusinessException
	 */
	public static boolean isTBBEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, TMIFMConst.CONST_BILLTYPE_REDEEM);
	}
	/**
	 * 判断是否单据是否进入到审批流最后一步
	 * 
	 * @param aggvo
	 * @return
	 * @throws BusinessException
	 */
	public static boolean isEndStep4Audit(AggregatedValueObject aggvo)
			 {
		return BillStatusEnum.APPROVED.value() == (Integer) aggvo.getParentVO()
				.getAttributeValue("vbillstatus");
	}




	/**
	 * 根据指定的billtype取相应的parentBillType
	 */
	public static String getParentBillType(String tradtype) throws BusinessException {
		ArrayList<?> volist = null;
		try {
			volist = (ArrayList<?>) new BaseDAO().retrieveByClause(
					BilltypeVO.class, " pk_billtypecode = '" + tradtype + "' ");
		} catch (DAOException e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0303")/*@res "根据单据类型编码查询单据类型信息异常"*/);
		}
		if (volist == null || volist.size() == 0) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0304")/*@res "根据单据类型编码："*/ + tradtype + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0305")/*@res "，无法查询到相应的单据类型信息"*/);
		}
		return ((BilltypeVO) volist.get(0)).getParentbilltype();// 得到单据大类
	}

	public static String getModuleNodeByBillType(String billtype){
		 String node = (String)billTypemap.get(billtype);
		 if(node == null){
			 BilltypeVO billtypeVO = PfDataCache.getBillType(billtype);
			 if(billtypeVO!=null){
				 node = billtypeVO.getNodecode();
				 billTypemap.put(billtype, node);
			 }
//			 HYPubBO bo = new HYPubBO();
//			 try {
//				SuperVO[] vos = bo.queryByCondition(BilltypeVO.class,
//						" pk_billtypecode = '" + billtype + "' and isnull(dr,0) =0 ");
//				if(vos != null && vos.length > 0 ){
//					node = ((BilltypeVO)vos[0]).getNodecode();
//					billTypemap.put(billtype, node);
//				}
//
//			} catch (UifException e) {
//				throw new BusinessRuntimeException(e.getMessage(),e);
//			}

		 }
		 return node;
	 }

	/**
	 * 调用启用模块API
	 *
	 * @param pk_group
	 *            集团
	 * @param funcode
	 *            功能节点 数据来源于dap_dapsystem
	 * @return
	 * @throws BusinessException
	 */
	public static boolean isEnable(String pk_group, String funcode) throws BusinessException {
		try {
			return InitGroupQuery.isEnabled(pk_group == null ? InvocationInfoProxy.getInstance().getGroupId() : pk_group, funcode);
		} catch (BusinessException be) {
			Logger.error(be.getMessage(), be);
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0306")/*@res "查询模块是否启用时异常"*/);
		}
	}

	/**
	 * 判断组织是否启用现金管理
	 */
	public static boolean isCMPOrgEnable(String pk_org, UFDate busiDate)
			throws BusinessException {
		return isOrgEnabled(pk_org, CDMBusConstant.TM_CMP_FUNCODE, busiDate);
	}

	/**
	 * 组织是否启用模块
	 *
	 * @param pk_org
	 * @param fundcode
	 * @return
	 * @throws BusinessException
	 */
	private static boolean isOrgEnabled(String pk_org, String fundcode,
			UFDate busiDate) throws BusinessException {
		if (null == busiDate)
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0307")/*@res "传入的业务日期为空，无法判断组织是否启用"*/);
		UFDate initDate = ModuleUtil.queryInitDateByOrg(pk_org, fundcode);
		if (null == initDate)
			return false;
		if (busiDate.beforeDate(initDate))
			return false;
		return true;
	}

	/**
	 * 判断会计平台是否启用
	 * @param pk_group
	 * @throws BusinessException
	 */
	public static boolean isFIPEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, "1017");
	}
	
	/**
	 * 判断担保管理是否启用
	 * @param pk_group
	 * @throws BusinessException
	 */
	public static boolean isGPMEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, TMBusConstant.OUTER_FUNCODE_GPM);
	}
	/**
	 * 判断现金管理是否启用
	 * @param pk_group
	 * @throws BusinessException
	 */
	public static boolean isCMPEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, TMBusConstant.OUTER_FUNCODE_CMP);
	}
	/**
	 * 判断授信否启用
	 * @param pk_group
	 * @throws BusinessException
	 */
	public static boolean isCCEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, TMBusConstant.OUTER_FUNCODE_CC);
	}

//	/**
//	 * 判断计划平台是否启用
//	 * @param pk_group
//	 * @throws BusinessException
//	 */
//	public static boolean isTBBEnable(String pk_group) throws BusinessException {
//		return isEnable(pk_group, FbmBusConstant.OUTER_FUNCODE_TBB);
//	}
//
//	/**
//	 * 判断应付管理是否启用
//	 * @param pk_group
//	 * @throws BusinessException
//	 */
//	public static boolean isAPEnable(String pk_group) throws BusinessException {
//		return isEnable(pk_group, FbmBusConstant.OUTER_FUNCODE_AP);
//	}
//
//	/**
//	 * 判断应收管理是否启用
//	 * @param pk_group
//	 * @throws BusinessException
//	 */
//	public static boolean isAREnable(String pk_group) throws BusinessException {
//		return isEnable(pk_group, FbmBusConstant.OUTER_FUNCODE_AR);
//	}

//	/**
//	 * 判断现金管理是否启用
//	 * @param pk_group
//	 * @throws BusinessException
//	 */
//	public static boolean isCMPEnable(String pk_group) throws BusinessException {
//		return isEnable(pk_group, FbmBusConstant.OUTER_FUNCODE_CMP);
//	}

//	/**
//	 * 判断资金结算是否启用
//	 * @param pk_group
//	 * @throws BusinessException
//	 */
//	public static boolean isFTSEnable(String pk_group) throws BusinessException {
//		return isEnable(pk_group, FbmBusConstant.OUTER_FUNCODE_FTS);
//	}

	/**
	 * 对票据编号加锁
	 * @param billno
	 * @param userid
	 * @throws BusinessException
	 */
	public static void lockProtocolbillno(String[] billnos) throws BusinessException {
		if(null == billnos || billnos.length == 0) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0308")/*@res "无法锁定当前票据，取得的单据号为空。"*/);
		}
		//特殊情况下的处理，有时候多选的情况下，会调用两次（客户化的问题），第一次票据号为空
		for(String billno:billnos){
			if(nc.vo.jcom.lang.StringUtil.isEmptyWithTrim(billno)){
				return;
			}
		}
		boolean isLock = PKLock.getInstance().addBatchDynamicLock(billnos);
		if(!isLock){
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0309")/*@res "加锁失败，单据正在被使用，请稍候再试。"*/);
		}
	}

//	/**
//	 * 得到客商的默认银行账户
//	 * @throws BusinessException
//	 */
//	public static BankAccSubVO getCustSupplierDefaultBankAcc(String pk_custsup, String pk_curr) throws BusinessException {
//		if (StringUtil.isNull(pk_custsup)) {
//			return null;
//		}
//		String pkDefaultBankAcc = FBMPublicServiceProxy.getCustsupPubService().getDefaultBankAcc(pk_custsup);
//		if (StringUtil.isNotNull(pkDefaultBankAcc)) {
//			BankAccSubVO accvo = FBMPublicServiceProxy.getMDQueryService().queryBillOfVOByPK(BankAccSubVO.class, pkDefaultBankAcc, false);
//			if (accvo != null) {
//				if (StringUtil.isNotNull(pk_curr)) {
//					if (pk_curr.equals(accvo.getPk_currtype())) {
//						return accvo;
//					}
//					return null;
//				}
//				return accvo;
//			}
//		}
//		return null;
//	}

//	/**
//	 * 根据银行账户基本信息PK得到银行档案基本信息
//	 * @throws BusinessException
//	 */
//	public static BankdocVO getBankDocInfoByPkaccbas(String pk_accbas) throws BusinessException {
//		if (StringUtil.isNull(pk_accbas)) {
//			return null;
//		}
//		BankAccbasVO[] accvos = FBMPublicServiceProxy.getBankaccPubQueryService().queryBankaccsByPks(new String[]{pk_accbas});
//
//		if (accvos != null && accvos.length > 0) {
//			return FBMPublicServiceProxy.getBankdocQueryService().getBankdocVOByPk(accvos[0].getPk_bankdoc());
//		}
//		return null;
//	}
//
	/**
	 * 将原币金额设置为币种对应的精度
	 * @param pk_curr
	 * @param money
	 * @return
	 */
	public static UFDouble getDigitmoney(String pk_curr, UFDouble money) throws BusinessException {
		if (StringUtil.isNotNull(pk_curr) && money != null) {
			// 取得原币币种精度
			int digit = 2;
			try {
				digit = TMCurrencyUtil.getCurrtypeDigit(pk_curr);
			} catch (BusinessException e) {
				Logger.error(e.getMessage(), e);
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0310")/*@res "取得币种精度时异常"*/);
			}
			// 设置原币金额精度
			money = new UFDouble(StringUtil.toString(money), digit);
		}
		return money;
	}

	/**
	 * 将组织本币金额设置为组织本币对应的精度
	 * @param pk_curr
	 * @param money
	 * @return
	 */
	public static UFDouble getOrgDigitmoney(String pk_org, String pk_curr, UFDouble money) throws BusinessException {
		if (StringUtil.isNotNull(pk_org) && StringUtil.isNotNull(pk_curr) && money != null) {
			// 取得组织本币精度
			int orgdigit = 2;
			try {
			    orgdigit = TMCurrencyUtil.getCurrtypeDigit(TMCurrencyUtil.getOrgLocalCurrPK(pk_org));
				//orgdigit = TMCurrencyUtil.getOrgCurrRateDigit(pk_org, pk_curr);
			} catch (BusinessException e) {
				Logger.error(e.getMessage(), e);
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0311")/*@res "取得组织本币精度时异常"*/);
			}
			// 设置组织本币金额精度
			money = new UFDouble(StringUtil.toString(money), orgdigit);
		}
		return money;
	}

	/**
	 * 将集团本币金额设置为集团本币对应的精度
	 * @param pk_curr
	 * @param money
	 * @return
	 */
	public static UFDouble getGroupDigitmoney(String pk_group, String pk_org, String pk_curr, UFDouble money) throws BusinessException {
		if (StringUtil.isNotNull(pk_group) && StringUtil.isNotNull(pk_org) && StringUtil.isNotNull(pk_curr) && money != null) {
			// 取得集团本币精度
			int groupdigit = 2;
			try {
//				groupdigit = TMCurrencyUtil.getGroupCurrRateDigit(pk_group, pk_org, pk_curr);
				groupdigit = TMCurrencyUtil.getCurrtypeDigit(TMCurrencyUtil.getGroupLocalCurrPK(pk_org));
			} catch (BusinessException e) {
				Logger.error(e.getMessage(), e);
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0312")/*@res "取得集团本币精度时异常"*/);
			}
			// 设置集团本币金额精度
			money = new UFDouble(StringUtil.toString(money), groupdigit);
		}
		return money;
	}

	/**
	 * 将全局本币金额设置为全局本币对应的精度
	 * @param pk_curr
	 * @param money
	 * @return
	 */
	public static UFDouble getGlobalDigitmoney(String pk_org, String pk_curr, UFDouble money) throws BusinessException {
		if (StringUtil.isNotNull(pk_org) && StringUtil.isNotNull(pk_curr) && money != null) {
			// 取得全局本币精度
			int globaldigit = 2;
			try {
//				globaldigit = TMCurrencyUtil.getGlobalCurrRateDigit(pk_org, pk_curr);
				globaldigit = TMCurrencyUtil.getCurrtypeDigit(TMCurrencyUtil.getGLobalCurrPK());
			} catch (BusinessException e) {
				Logger.error(e.getMessage(), e);
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0313")/*@res "取得全局本币精度时异常"*/);
			}
			// 设置全局本币金额精度
			money = new UFDouble(StringUtil.toString(money), globaldigit);
		}
		return money;
	}

	/**
	 * 对票据PK加锁
	 *
	 * @param billno
	 * @param userid
	 * @throws BusinessException
	 */
	public static void lockCDMPK(String[] pks) throws BusinessException {
		if (null == pks || pks.length == 0) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0314")/*@res "无法锁定当前单据，取得的单据主键为空。"*/);
		}
		boolean isLock = PKLock.getInstance().addBatchDynamicLock(pks);
		if (!isLock) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0309")/*@res "加锁失败，单据正在被使用，请稍候再试。"*/);
		}
	}

	/**
	 * 根据上级资金组织pk和子资金组织pk查询上级资金组织直接下级资金组织
	 * @param pk_father
	 * @param pk_child
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public static FundOrgVO getNextFundOrgByFatherAndChild(String pk_father, String pk_child) throws BusinessException {
		if (StringUtil.isNull(pk_father) || StringUtil.isNull(pk_child)) {// 上级资金组织pk或者子资金组织pk之一为空则返回空
			return null;
		}
		IMDPersistenceQueryService pubquery = (IMDPersistenceQueryService) NCLocator.getInstance().lookup(IMDPersistenceQueryService.class.getName());
		Collection<FundOrgVO> result = pubquery.queryBillOfVOByCond(FundOrgVO.class,
				"pk_fatherorg = '" + pk_father + "'", false);
		if (result == null || result.isEmpty()) {// 上级资金组织的直接下级资金组织为空则返回空
			return null;
		}
		FundOrgVO childorg = (FundOrgVO) new BaseDAO().retrieveByPK(FundOrgVO.class, pk_child);
		if (childorg == null || StringUtil.isNull(childorg.getInnercode())) {// 子资金组织为空或者innercode为空则返回空
			return null;
		}
		for (FundOrgVO fundorg : result) {
			if (fundorg == null || StringUtil.isNull(fundorg.getInnercode())) {
				continue;
			}
			if (childorg.getInnercode().contains(fundorg.getInnercode())) {// 既是上级资金组织的直接下级，又是子资金组织的上级组织
				return fundorg;
			}
		}
		return null;
	}

	/**
	 * 取得默认清算账户
	 * @param pk_ownerorg
	 * @param pk_bankorg
	 * @param pk_currtype
	 * @param checkNoneAccount
	 * @return
	 * @throws BusinessException
	 */
	public static String getDefaultClearAcc(String pk_ownerorg, String pk_bankorg,
			String pk_currtype, boolean checkNoneAccount) throws BusinessException {
		// 默认清算账户的账户类型是活期
		String accType = IAccidouterConst.ACCTYPE_CURRENT;
		// 构造查询VO
		AccidPubQueryParamVO queryVO = new AccidPubQueryParamVO();
		queryVO.setPk_ownerorg(pk_ownerorg);
		queryVO.setPk_org(pk_bankorg);
		queryVO.setPk_currtype(pk_currtype);
		queryVO.setAcctype(new String[] { accType });
		// 按账户类型取默认清算账户
		ITAMPubQueryService tampubquery = (ITAMPubQueryService) NCLocator.getInstance().lookup(ITAMPubQueryService.class.getName());
		HashMap<String, String> hmAccid = tampubquery.queryDefaultClearAcc(queryVO);
		if (hmAccid != null && hmAccid.containsKey(accType)) {
			return hmAccid.get(accType);
		} else {
			return null;
		}
	}

	/**
	 * 计算返回相关本币金额信息
	 * @time 2011-5-17 下午08:25:08
	 * @param pk_currtype
	 * @param pk_org
	 * @param pk_group
	 * @param money
	 * @param date
	 * @return
	 * @throws BusinessException
	 */
	public static UFDouble[] calculateCurrMoney(String pk_currtype, String pk_org,
			String pk_group, UFDouble money, UFDate date)
			throws BusinessException {

		UFDouble[] moneyGrp = new UFDouble[7];// 原币金额，组织本币汇率，金额，集团本币汇率，金额，全局本币汇率，金额
		moneyGrp[0] = money;

		// 获得组织本币汇率
		UFDouble olcrate = TMCurrencyUtil.getOrgCurrRate(pk_org, pk_currtype, date);
		if(olcrate == null || olcrate.compareTo(UFDouble.ZERO_DBL) <= 0)
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0315")/*@res "获取组织本币汇率失败，请设置相应汇率"*/);
		moneyGrp[1] = olcrate;

		// 获得组织折本金额
		UFDouble olcmoney = TMCurrencyUtil.getOrgLocalMoney(pk_org, pk_currtype,
				money, olcrate, date);
		moneyGrp[2] = olcmoney;

		// 获得集团本币汇率
		UFDouble glcrate = TMCurrencyUtil.getGroupCurrRate(pk_group, pk_org,
				pk_currtype, date);
		moneyGrp[3] = glcrate;

		// 获得集团折本金额
		UFDouble glcmoney = TMCurrencyUtil.getGroupLocalMoney(pk_group, pk_org,
				pk_currtype, money, glcrate, olcrate, date);
		moneyGrp[4] = glcmoney;

		// 获得全局本币汇率
		UFDouble gllcrate = TMCurrencyUtil.getGlobalCurrRate(pk_org, pk_currtype, date);
		moneyGrp[5] = gllcrate;

		// 获得全局折本金额
		UFDouble gllcmoney = TMCurrencyUtil.getGlobalLocalMoney(pk_org,
				pk_currtype, money, gllcrate, olcrate, date);
		moneyGrp[6] = gllcmoney;

		return moneyGrp;
	}
	
	/**
	 * 计算返回合同相关本币金额信息<br>
	 * 支持按合同固定汇率计算<br>
	 * @time 2011-5-17 下午08:25:08
	 * @param pk_currtype
	 * @param pk_org
	 * @param pk_group
	 * @param money
	 * @param date
	 * @return
	 * @throws BusinessException
	 */
	public static UFDouble[] calculateCurrMoneyByContInfo(ContractVO contVO,
			UFDouble money, UFDate date) throws BusinessException {
		String pk_currtype = contVO.getPk_currtype();
		String pk_org = contVO.getPk_org();
		String pk_group = contVO.getPk_group();
		// 是否固定汇率
		UFBoolean isfixrate = contVO.getIsfixrate();
		UFDouble olcrate = null;
		UFDouble glcrate = null;
		UFDouble gllcrate = null;

		UFDouble[] moneyGrp = new UFDouble[7];// 原币金额，组织本币汇率，金额，集团本币汇率，金额，全局本币汇率，金额
		moneyGrp[0] = money;

		// 获得组织本币汇率
		if (isfixrate != null && isfixrate.booleanValue()) {
			olcrate = contVO.getOlcrate();
		} else {
			olcrate = TMCurrencyUtil.getOrgCurrRate(pk_org, pk_currtype, date);
			if (olcrate == null || olcrate.compareTo(UFDouble.ZERO_DBL) <= 0)
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
						.getNCLangRes()
						.getStrByID("3615pub_0", "03615pub-0315")/*
																 * @res
																 * "获取组织本币汇率失败，请设置相应汇率"
																 */);
		}
		moneyGrp[1] = olcrate;

		// 获得组织折本金额
		UFDouble olcmoney = TMCurrencyUtil.getOrgLocalMoney(pk_org, pk_currtype,
				money, olcrate, date);
		moneyGrp[2] = olcmoney;

		// 获得集团本币汇率
		if (isfixrate != null && isfixrate.booleanValue()) {
			glcrate = contVO.getGlcrate();
		} else {
			glcrate = TMCurrencyUtil.getGroupCurrRate(pk_group, pk_org,
					pk_currtype, date);
		}
		// 获得集团折本金额
		UFDouble glcmoney = TMCurrencyUtil.getGroupLocalMoney(pk_group, pk_org,
				pk_currtype, money, glcrate, olcrate, date);
		
		if (glcrate != null && glcrate.compareTo(UFDouble.ZERO_DBL) <= 0) {
			glcrate = null;
		}
		if (glcmoney != null && glcmoney.compareTo(UFDouble.ZERO_DBL) <= 0) {
			glcmoney = null;
		}
		moneyGrp[3] = glcrate;
		moneyGrp[4] = glcmoney;

		// 获得全局本币汇率
		if (isfixrate != null && isfixrate.booleanValue()) {
			gllcrate = contVO.getGllcrate();
		} else {
			gllcrate = TMCurrencyUtil.getGlobalCurrRate(pk_org, pk_currtype,
					date);
		}
		// 获得全局折本金额
		UFDouble gllcmoney = TMCurrencyUtil.getGlobalLocalMoney(pk_org,
				pk_currtype, money, gllcrate, olcrate, date);
	
		if (gllcrate != null && gllcrate.compareTo(UFDouble.ZERO_DBL) <= 0) {
			gllcrate = null;
		}
		if (gllcmoney != null && gllcmoney.compareTo(UFDouble.ZERO_DBL) <= 0) {
			gllcmoney = null;
		}
		moneyGrp[5] = gllcrate;
		moneyGrp[6] = gllcmoney;

		return moneyGrp;
	}
	
	/**
	 * 根据指定汇率计算相关本币信息
	 * @param pk_currtype
	 * @param pk_org
	 * @param pk_group
	 * @param olcrate
	 * @param glcrate
	 * @param gllcrate
	 * @param money
	 * @param date
	 * @return
	 * @throws BusinessException
	 * @date 2012-6-8
	 * @time 下午03:00:18
	 */
	public static UFDouble[] calculateCurrMoney(String pk_currtype,
			String pk_org, String pk_group, UFDouble olcrate, UFDouble glcrate,
			UFDouble gllcrate, UFDouble money, UFDate date)
			throws BusinessException {
		UFDouble[] moneyGrp = new UFDouble[7];// 原币金额，组织本币汇率，金额，集团本币汇率，金额，全局本币汇率，金额
		moneyGrp[0] = money;
		moneyGrp[1] = olcrate;
		
		// 获得组织折本金额
		UFDouble olcmoney = TMCurrencyUtil.getOrgLocalMoney(pk_org, pk_currtype,
				money, olcrate, date);
		moneyGrp[2] = olcmoney;
		
		moneyGrp[3] = glcrate;

		// 获得集团折本金额
		UFDouble glcmoney = TMCurrencyUtil.getGroupLocalMoney(pk_group, pk_org,
				pk_currtype, money, glcrate, olcrate, date);
		moneyGrp[4] = glcmoney;

		moneyGrp[5] = gllcrate;

		// 获得全局折本金额
		UFDouble gllcmoney = TMCurrencyUtil.getGlobalLocalMoney(pk_org,
				pk_currtype, money, gllcrate, olcrate, date);
		moneyGrp[6] = gllcmoney;

		return moneyGrp;
	}
	
	public static String getOrgWorkCalendar(String pk_org) throws BusinessException {
		String pk_workcalendar = null;
		IOrgUnitPubService qryService = NCLocator.getInstance().lookup(IOrgUnitPubService.class);
		//取所属组织的日期
		OrgVO[] orgVOs = qryService.getOrgs(new String[] { pk_org },
				new String[] { OrgVO.PK_ORG, OrgVO.PK_VID, OrgVO.NAME,
						OrgVO.PK_GROUP, OrgVO.WORKCALENDAR });
		if (!ArrayUtil.isNull(orgVOs)) {
			OrgVO orgVO = orgVOs[0];
			pk_workcalendar = orgVO.getWorkcalendar();
		}
		return pk_workcalendar;
	}

}