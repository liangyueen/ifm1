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
 * <p>Ʊ�ݹ�ͬ������<br>
 * @author ydx
 */
public class IFMPublicUtil {

	/**
	 * �жϼ����Ƿ�Ϊ��
	 *
	 * @param coll
	 * @return true:�գ�false:�ǿ�
	 */
	public static boolean isEmpty(Collection coll) {
		return (coll == null || coll.isEmpty());
	}

	private static Map billTypemap = new HashMap();



	/**
	 * �ж��Ƿ�������ϵͳ����Ӧ��
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
	 * �жϼƻ�ƽ̨�Ƿ�����
	 * @param pk_group
	 * @throws BusinessException
	 */
	public static boolean isTBBEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, TMIFMConst.CONST_BILLTYPE_REDEEM);
	}
	/**
	 * �ж��Ƿ񵥾��Ƿ���뵽���������һ��
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
	 * ����ָ����billtypeȡ��Ӧ��parentBillType
	 */
	public static String getParentBillType(String tradtype) throws BusinessException {
		ArrayList<?> volist = null;
		try {
			volist = (ArrayList<?>) new BaseDAO().retrieveByClause(
					BilltypeVO.class, " pk_billtypecode = '" + tradtype + "' ");
		} catch (DAOException e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0303")/*@res "���ݵ������ͱ����ѯ����������Ϣ�쳣"*/);
		}
		if (volist == null || volist.size() == 0) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0304")/*@res "���ݵ������ͱ��룺"*/ + tradtype + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0305")/*@res "���޷���ѯ����Ӧ�ĵ���������Ϣ"*/);
		}
		return ((BilltypeVO) volist.get(0)).getParentbilltype();// �õ����ݴ���
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
	 * ��������ģ��API
	 *
	 * @param pk_group
	 *            ����
	 * @param funcode
	 *            ���ܽڵ� ������Դ��dap_dapsystem
	 * @return
	 * @throws BusinessException
	 */
	public static boolean isEnable(String pk_group, String funcode) throws BusinessException {
		try {
			return InitGroupQuery.isEnabled(pk_group == null ? InvocationInfoProxy.getInstance().getGroupId() : pk_group, funcode);
		} catch (BusinessException be) {
			Logger.error(be.getMessage(), be);
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0306")/*@res "��ѯģ���Ƿ�����ʱ�쳣"*/);
		}
	}

	/**
	 * �ж���֯�Ƿ������ֽ����
	 */
	public static boolean isCMPOrgEnable(String pk_org, UFDate busiDate)
			throws BusinessException {
		return isOrgEnabled(pk_org, CDMBusConstant.TM_CMP_FUNCODE, busiDate);
	}

	/**
	 * ��֯�Ƿ�����ģ��
	 *
	 * @param pk_org
	 * @param fundcode
	 * @return
	 * @throws BusinessException
	 */
	private static boolean isOrgEnabled(String pk_org, String fundcode,
			UFDate busiDate) throws BusinessException {
		if (null == busiDate)
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0307")/*@res "�����ҵ������Ϊ�գ��޷��ж���֯�Ƿ�����"*/);
		UFDate initDate = ModuleUtil.queryInitDateByOrg(pk_org, fundcode);
		if (null == initDate)
			return false;
		if (busiDate.beforeDate(initDate))
			return false;
		return true;
	}

	/**
	 * �жϻ��ƽ̨�Ƿ�����
	 * @param pk_group
	 * @throws BusinessException
	 */
	public static boolean isFIPEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, "1017");
	}
	
	/**
	 * �жϵ��������Ƿ�����
	 * @param pk_group
	 * @throws BusinessException
	 */
	public static boolean isGPMEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, TMBusConstant.OUTER_FUNCODE_GPM);
	}
	/**
	 * �ж��ֽ�����Ƿ�����
	 * @param pk_group
	 * @throws BusinessException
	 */
	public static boolean isCMPEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, TMBusConstant.OUTER_FUNCODE_CMP);
	}
	/**
	 * �ж����ŷ�����
	 * @param pk_group
	 * @throws BusinessException
	 */
	public static boolean isCCEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, TMBusConstant.OUTER_FUNCODE_CC);
	}

//	/**
//	 * �жϼƻ�ƽ̨�Ƿ�����
//	 * @param pk_group
//	 * @throws BusinessException
//	 */
//	public static boolean isTBBEnable(String pk_group) throws BusinessException {
//		return isEnable(pk_group, FbmBusConstant.OUTER_FUNCODE_TBB);
//	}
//
//	/**
//	 * �ж�Ӧ�������Ƿ�����
//	 * @param pk_group
//	 * @throws BusinessException
//	 */
//	public static boolean isAPEnable(String pk_group) throws BusinessException {
//		return isEnable(pk_group, FbmBusConstant.OUTER_FUNCODE_AP);
//	}
//
//	/**
//	 * �ж�Ӧ�չ����Ƿ�����
//	 * @param pk_group
//	 * @throws BusinessException
//	 */
//	public static boolean isAREnable(String pk_group) throws BusinessException {
//		return isEnable(pk_group, FbmBusConstant.OUTER_FUNCODE_AR);
//	}

//	/**
//	 * �ж��ֽ�����Ƿ�����
//	 * @param pk_group
//	 * @throws BusinessException
//	 */
//	public static boolean isCMPEnable(String pk_group) throws BusinessException {
//		return isEnable(pk_group, FbmBusConstant.OUTER_FUNCODE_CMP);
//	}

//	/**
//	 * �ж��ʽ�����Ƿ�����
//	 * @param pk_group
//	 * @throws BusinessException
//	 */
//	public static boolean isFTSEnable(String pk_group) throws BusinessException {
//		return isEnable(pk_group, FbmBusConstant.OUTER_FUNCODE_FTS);
//	}

	/**
	 * ��Ʊ�ݱ�ż���
	 * @param billno
	 * @param userid
	 * @throws BusinessException
	 */
	public static void lockProtocolbillno(String[] billnos) throws BusinessException {
		if(null == billnos || billnos.length == 0) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0308")/*@res "�޷�������ǰƱ�ݣ�ȡ�õĵ��ݺ�Ϊ�ա�"*/);
		}
		//��������µĴ�����ʱ���ѡ������£���������Σ��ͻ��������⣩����һ��Ʊ�ݺ�Ϊ��
		for(String billno:billnos){
			if(nc.vo.jcom.lang.StringUtil.isEmptyWithTrim(billno)){
				return;
			}
		}
		boolean isLock = PKLock.getInstance().addBatchDynamicLock(billnos);
		if(!isLock){
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0309")/*@res "����ʧ�ܣ��������ڱ�ʹ�ã����Ժ����ԡ�"*/);
		}
	}

//	/**
//	 * �õ����̵�Ĭ�������˻�
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
//	 * ���������˻�������ϢPK�õ����е���������Ϣ
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
	 * ��ԭ�ҽ������Ϊ���ֶ�Ӧ�ľ���
	 * @param pk_curr
	 * @param money
	 * @return
	 */
	public static UFDouble getDigitmoney(String pk_curr, UFDouble money) throws BusinessException {
		if (StringUtil.isNotNull(pk_curr) && money != null) {
			// ȡ��ԭ�ұ��־���
			int digit = 2;
			try {
				digit = TMCurrencyUtil.getCurrtypeDigit(pk_curr);
			} catch (BusinessException e) {
				Logger.error(e.getMessage(), e);
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0310")/*@res "ȡ�ñ��־���ʱ�쳣"*/);
			}
			// ����ԭ�ҽ���
			money = new UFDouble(StringUtil.toString(money), digit);
		}
		return money;
	}

	/**
	 * ����֯���ҽ������Ϊ��֯���Ҷ�Ӧ�ľ���
	 * @param pk_curr
	 * @param money
	 * @return
	 */
	public static UFDouble getOrgDigitmoney(String pk_org, String pk_curr, UFDouble money) throws BusinessException {
		if (StringUtil.isNotNull(pk_org) && StringUtil.isNotNull(pk_curr) && money != null) {
			// ȡ����֯���Ҿ���
			int orgdigit = 2;
			try {
			    orgdigit = TMCurrencyUtil.getCurrtypeDigit(TMCurrencyUtil.getOrgLocalCurrPK(pk_org));
				//orgdigit = TMCurrencyUtil.getOrgCurrRateDigit(pk_org, pk_curr);
			} catch (BusinessException e) {
				Logger.error(e.getMessage(), e);
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0311")/*@res "ȡ����֯���Ҿ���ʱ�쳣"*/);
			}
			// ������֯���ҽ���
			money = new UFDouble(StringUtil.toString(money), orgdigit);
		}
		return money;
	}

	/**
	 * �����ű��ҽ������Ϊ���ű��Ҷ�Ӧ�ľ���
	 * @param pk_curr
	 * @param money
	 * @return
	 */
	public static UFDouble getGroupDigitmoney(String pk_group, String pk_org, String pk_curr, UFDouble money) throws BusinessException {
		if (StringUtil.isNotNull(pk_group) && StringUtil.isNotNull(pk_org) && StringUtil.isNotNull(pk_curr) && money != null) {
			// ȡ�ü��ű��Ҿ���
			int groupdigit = 2;
			try {
//				groupdigit = TMCurrencyUtil.getGroupCurrRateDigit(pk_group, pk_org, pk_curr);
				groupdigit = TMCurrencyUtil.getCurrtypeDigit(TMCurrencyUtil.getGroupLocalCurrPK(pk_org));
			} catch (BusinessException e) {
				Logger.error(e.getMessage(), e);
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0312")/*@res "ȡ�ü��ű��Ҿ���ʱ�쳣"*/);
			}
			// ���ü��ű��ҽ���
			money = new UFDouble(StringUtil.toString(money), groupdigit);
		}
		return money;
	}

	/**
	 * ��ȫ�ֱ��ҽ������Ϊȫ�ֱ��Ҷ�Ӧ�ľ���
	 * @param pk_curr
	 * @param money
	 * @return
	 */
	public static UFDouble getGlobalDigitmoney(String pk_org, String pk_curr, UFDouble money) throws BusinessException {
		if (StringUtil.isNotNull(pk_org) && StringUtil.isNotNull(pk_curr) && money != null) {
			// ȡ��ȫ�ֱ��Ҿ���
			int globaldigit = 2;
			try {
//				globaldigit = TMCurrencyUtil.getGlobalCurrRateDigit(pk_org, pk_curr);
				globaldigit = TMCurrencyUtil.getCurrtypeDigit(TMCurrencyUtil.getGLobalCurrPK());
			} catch (BusinessException e) {
				Logger.error(e.getMessage(), e);
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0313")/*@res "ȡ��ȫ�ֱ��Ҿ���ʱ�쳣"*/);
			}
			// ����ȫ�ֱ��ҽ���
			money = new UFDouble(StringUtil.toString(money), globaldigit);
		}
		return money;
	}

	/**
	 * ��Ʊ��PK����
	 *
	 * @param billno
	 * @param userid
	 * @throws BusinessException
	 */
	public static void lockCDMPK(String[] pks) throws BusinessException {
		if (null == pks || pks.length == 0) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0314")/*@res "�޷�������ǰ���ݣ�ȡ�õĵ�������Ϊ�ա�"*/);
		}
		boolean isLock = PKLock.getInstance().addBatchDynamicLock(pks);
		if (!isLock) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0309")/*@res "����ʧ�ܣ��������ڱ�ʹ�ã����Ժ����ԡ�"*/);
		}
	}

	/**
	 * �����ϼ��ʽ���֯pk�����ʽ���֯pk��ѯ�ϼ��ʽ���ֱ֯���¼��ʽ���֯
	 * @param pk_father
	 * @param pk_child
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public static FundOrgVO getNextFundOrgByFatherAndChild(String pk_father, String pk_child) throws BusinessException {
		if (StringUtil.isNull(pk_father) || StringUtil.isNull(pk_child)) {// �ϼ��ʽ���֯pk�������ʽ���֯pk֮һΪ���򷵻ؿ�
			return null;
		}
		IMDPersistenceQueryService pubquery = (IMDPersistenceQueryService) NCLocator.getInstance().lookup(IMDPersistenceQueryService.class.getName());
		Collection<FundOrgVO> result = pubquery.queryBillOfVOByCond(FundOrgVO.class,
				"pk_fatherorg = '" + pk_father + "'", false);
		if (result == null || result.isEmpty()) {// �ϼ��ʽ���֯��ֱ���¼��ʽ���֯Ϊ���򷵻ؿ�
			return null;
		}
		FundOrgVO childorg = (FundOrgVO) new BaseDAO().retrieveByPK(FundOrgVO.class, pk_child);
		if (childorg == null || StringUtil.isNull(childorg.getInnercode())) {// ���ʽ���֯Ϊ�ջ���innercodeΪ���򷵻ؿ�
			return null;
		}
		for (FundOrgVO fundorg : result) {
			if (fundorg == null || StringUtil.isNull(fundorg.getInnercode())) {
				continue;
			}
			if (childorg.getInnercode().contains(fundorg.getInnercode())) {// �����ϼ��ʽ���֯��ֱ���¼����������ʽ���֯���ϼ���֯
				return fundorg;
			}
		}
		return null;
	}

	/**
	 * ȡ��Ĭ�������˻�
	 * @param pk_ownerorg
	 * @param pk_bankorg
	 * @param pk_currtype
	 * @param checkNoneAccount
	 * @return
	 * @throws BusinessException
	 */
	public static String getDefaultClearAcc(String pk_ownerorg, String pk_bankorg,
			String pk_currtype, boolean checkNoneAccount) throws BusinessException {
		// Ĭ�������˻����˻������ǻ���
		String accType = IAccidouterConst.ACCTYPE_CURRENT;
		// �����ѯVO
		AccidPubQueryParamVO queryVO = new AccidPubQueryParamVO();
		queryVO.setPk_ownerorg(pk_ownerorg);
		queryVO.setPk_org(pk_bankorg);
		queryVO.setPk_currtype(pk_currtype);
		queryVO.setAcctype(new String[] { accType });
		// ���˻�����ȡĬ�������˻�
		ITAMPubQueryService tampubquery = (ITAMPubQueryService) NCLocator.getInstance().lookup(ITAMPubQueryService.class.getName());
		HashMap<String, String> hmAccid = tampubquery.queryDefaultClearAcc(queryVO);
		if (hmAccid != null && hmAccid.containsKey(accType)) {
			return hmAccid.get(accType);
		} else {
			return null;
		}
	}

	/**
	 * ���㷵����ر��ҽ����Ϣ
	 * @time 2011-5-17 ����08:25:08
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

		UFDouble[] moneyGrp = new UFDouble[7];// ԭ�ҽ���֯���һ��ʣ������ű��һ��ʣ���ȫ�ֱ��һ��ʣ����
		moneyGrp[0] = money;

		// �����֯���һ���
		UFDouble olcrate = TMCurrencyUtil.getOrgCurrRate(pk_org, pk_currtype, date);
		if(olcrate == null || olcrate.compareTo(UFDouble.ZERO_DBL) <= 0)
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3615pub_0","03615pub-0315")/*@res "��ȡ��֯���һ���ʧ�ܣ���������Ӧ����"*/);
		moneyGrp[1] = olcrate;

		// �����֯�۱����
		UFDouble olcmoney = TMCurrencyUtil.getOrgLocalMoney(pk_org, pk_currtype,
				money, olcrate, date);
		moneyGrp[2] = olcmoney;

		// ��ü��ű��һ���
		UFDouble glcrate = TMCurrencyUtil.getGroupCurrRate(pk_group, pk_org,
				pk_currtype, date);
		moneyGrp[3] = glcrate;

		// ��ü����۱����
		UFDouble glcmoney = TMCurrencyUtil.getGroupLocalMoney(pk_group, pk_org,
				pk_currtype, money, glcrate, olcrate, date);
		moneyGrp[4] = glcmoney;

		// ���ȫ�ֱ��һ���
		UFDouble gllcrate = TMCurrencyUtil.getGlobalCurrRate(pk_org, pk_currtype, date);
		moneyGrp[5] = gllcrate;

		// ���ȫ���۱����
		UFDouble gllcmoney = TMCurrencyUtil.getGlobalLocalMoney(pk_org,
				pk_currtype, money, gllcrate, olcrate, date);
		moneyGrp[6] = gllcmoney;

		return moneyGrp;
	}
	
	/**
	 * ���㷵�غ�ͬ��ر��ҽ����Ϣ<br>
	 * ֧�ְ���ͬ�̶����ʼ���<br>
	 * @time 2011-5-17 ����08:25:08
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
		// �Ƿ�̶�����
		UFBoolean isfixrate = contVO.getIsfixrate();
		UFDouble olcrate = null;
		UFDouble glcrate = null;
		UFDouble gllcrate = null;

		UFDouble[] moneyGrp = new UFDouble[7];// ԭ�ҽ���֯���һ��ʣ������ű��һ��ʣ���ȫ�ֱ��һ��ʣ����
		moneyGrp[0] = money;

		// �����֯���һ���
		if (isfixrate != null && isfixrate.booleanValue()) {
			olcrate = contVO.getOlcrate();
		} else {
			olcrate = TMCurrencyUtil.getOrgCurrRate(pk_org, pk_currtype, date);
			if (olcrate == null || olcrate.compareTo(UFDouble.ZERO_DBL) <= 0)
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
						.getNCLangRes()
						.getStrByID("3615pub_0", "03615pub-0315")/*
																 * @res
																 * "��ȡ��֯���һ���ʧ�ܣ���������Ӧ����"
																 */);
		}
		moneyGrp[1] = olcrate;

		// �����֯�۱����
		UFDouble olcmoney = TMCurrencyUtil.getOrgLocalMoney(pk_org, pk_currtype,
				money, olcrate, date);
		moneyGrp[2] = olcmoney;

		// ��ü��ű��һ���
		if (isfixrate != null && isfixrate.booleanValue()) {
			glcrate = contVO.getGlcrate();
		} else {
			glcrate = TMCurrencyUtil.getGroupCurrRate(pk_group, pk_org,
					pk_currtype, date);
		}
		// ��ü����۱����
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

		// ���ȫ�ֱ��һ���
		if (isfixrate != null && isfixrate.booleanValue()) {
			gllcrate = contVO.getGllcrate();
		} else {
			gllcrate = TMCurrencyUtil.getGlobalCurrRate(pk_org, pk_currtype,
					date);
		}
		// ���ȫ���۱����
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
	 * ����ָ�����ʼ�����ر�����Ϣ
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
	 * @time ����03:00:18
	 */
	public static UFDouble[] calculateCurrMoney(String pk_currtype,
			String pk_org, String pk_group, UFDouble olcrate, UFDouble glcrate,
			UFDouble gllcrate, UFDouble money, UFDate date)
			throws BusinessException {
		UFDouble[] moneyGrp = new UFDouble[7];// ԭ�ҽ���֯���һ��ʣ������ű��һ��ʣ���ȫ�ֱ��һ��ʣ����
		moneyGrp[0] = money;
		moneyGrp[1] = olcrate;
		
		// �����֯�۱����
		UFDouble olcmoney = TMCurrencyUtil.getOrgLocalMoney(pk_org, pk_currtype,
				money, olcrate, date);
		moneyGrp[2] = olcmoney;
		
		moneyGrp[3] = glcrate;

		// ��ü����۱����
		UFDouble glcmoney = TMCurrencyUtil.getGroupLocalMoney(pk_group, pk_org,
				pk_currtype, money, glcrate, olcrate, date);
		moneyGrp[4] = glcmoney;

		moneyGrp[5] = gllcrate;

		// ���ȫ���۱����
		UFDouble gllcmoney = TMCurrencyUtil.getGlobalLocalMoney(pk_org,
				pk_currtype, money, gllcrate, olcrate, date);
		moneyGrp[6] = gllcmoney;

		return moneyGrp;
	}
	
	public static String getOrgWorkCalendar(String pk_org) throws BusinessException {
		String pk_workcalendar = null;
		IOrgUnitPubService qryService = NCLocator.getInstance().lookup(IOrgUnitPubService.class);
		//ȡ������֯������
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