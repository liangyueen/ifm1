package nc.vo.ifm.pub.tbb;

import java.util.Vector;

import nc.bs.framework.common.NCLocator;
import nc.itf.ifm.pub.tbb.IIFM4TbbConst;
import nc.itf.tb.control.IAccessableBusiVO;
import nc.itf.tb.control.IBudgetControl;
import nc.itf.tb.control.IFormulaFuncName;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.sf.ICreateCorpQueryService;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.ifm.apply.tbb.Apply2TbbAccessableBusiVO;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.ifm.income.tbb.Income2TbbAccessableBusiVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.ifm.redeem.tbb.Redeem2TbbAccessableBusiVO;
import nc.vo.ifm.util.IFMPublicUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.tb.control.NtbCtlInfoVO;
import nc.vo.tmpub.util.ArrayUtil;
import nc.vo.tmpub.util.StringUtil;
import nc.vo.trade.pub.IBillStatus;
import nccloud.pubitf.ifm.pub.IFMConstInforNCC;

/**
 * 
 * 【预算核心控制类】
 * 
 * 
 */
public class IFMTbbCommonUtil {
	// 预占数标志
	public static final String PREFIND = IFormulaFuncName.PREFIND;
	// 执行数标志
	public static final String UFIND = IFormulaFuncName.UFIND;
	// 模块编码
	public static String Product_IFM = "IFM";

	/**
	 * 预算检查
	 * 
	 * @param billvo
	 * @throws BusinessException
	 */
	public static String checkNtbBeforeEff(AggregatedValueObject billvo)
			throws BusinessException {
		if (billvo == null || billvo.getParentVO() == null) {
			return null;
		}

		// 判断预算是否启用
		if (!IFMPublicUtil.isTBBEnable((String) billvo.getParentVO()
				.getAttributeValue(IFMConstInforNCC.PK_GROUP))) {
			return null;
		}

		CircularlyAccessibleValueObject hvo = (CircularlyAccessibleValueObject) billvo
				.getParentVO().clone();

		CircularlyAccessibleValueObject[] bvos = null;
		if (billvo.getChildrenVO() != null) {
			bvos = (CircularlyAccessibleValueObject[]) billvo.getChildrenVO()
					.clone();
		}

		String currgroup = getCurrgroup(hvo);
		// 根据集团主键编码、模块编码查询启用期间
		String[] periods = ((ICreateCorpQueryService) NCLocator.getInstance()
				.lookup(ICreateCorpQueryService.class.getName()))
				.queryEnabledPeriod(currgroup, Product_IFM);
		// 检查预算是否安装
		if (currgroup == null || ArrayUtil.isNull(periods)) {
			return null;
		}

		// 调用预算接口BO
		NtbCtlInfoVO tpcontrolvo = getCheckinfo(hvo, bvos);
		// 处理检查信息
		return IFMTbbCommonUtil.sendNtbMessage(tpcontrolvo, hvo);
	}

	private static CircularlyAccessibleValueObject changeBvos2BaseinfoVos(
			String billtype, CircularlyAccessibleValueObject vo)
			throws BusinessException {
		// 如果是新增单据直接返回VO，不需要补齐数据
		if (vo.getStatus() == VOStatus.NEW
				|| vo.getStatus() == VOStatus.UNCHANGED
				|| vo.getPrimaryKey() == null) {
			return vo;
		}
//		if (billtype.equals(ICCCConst.BILLTYPE)) {
//			return (InvestIncomeVO) ((IUAPQueryBS) NCLocator.getInstance()
//					.lookup(IUAPQueryBS.class.getName())).retrieveByPK(
//					InvestIncomeVO.class, vo.getPrimaryKey());
//		}
		return null;
	}

	private static CircularlyAccessibleValueObject changeBvos2BaseinfoVos(
			CircularlyAccessibleValueObject vo) throws BusinessException {
		// 如果是新增单据直接返回VO，不需要补齐数据
		if (vo.getStatus() == VOStatus.NEW
				|| vo.getStatus() == VOStatus.UNCHANGED||vo.getPrimaryKey()==null) {
			return vo;
		}
		String billtype = (String) vo
				.getAttributeValue(IFMConstInforNCC.PK_BILLTYPECODE);
		if(billtype.equals(IIFM4TbbConst.pk_BillTypeCode_InvestApply)){
			return (InvestApplyVO)((IUAPQueryBS) NCLocator.getInstance().lookup(
					IUAPQueryBS.class.getName())).retrieveByPK(InvestApplyVO.class, vo.getPrimaryKey());
		}
		else if(billtype.equals(IIFM4TbbConst.pk_BillTypeCode_InvestIncome)){
			return (InvestIncomeVO)((IUAPQueryBS) NCLocator.getInstance().lookup(
					IUAPQueryBS.class.getName())).retrieveByPK(InvestIncomeVO.class, vo.getPrimaryKey());
		}
		else if(billtype.equals(IIFM4TbbConst.pk_BillTypeCode_InvestRedeem)){
			return (InvestRedeemVO)((IUAPQueryBS) NCLocator.getInstance().lookup(
					IUAPQueryBS.class.getName())).retrieveByPK(InvestRedeemVO.class, vo.getPrimaryKey());
		}
		return vo;
	}

	/**
	 * 取得预算控制VO信息
	 * 
	 * @param hvo
	 * @param bvos
	 * @return CcDefaultBillAccessableBusiVO[]
	 * @throws BusinessException
	 */
	public static IFMToTbbAccessableBusiVO[] getBillAccessableBusiVO(
			CircularlyAccessibleValueObject hvo,
			CircularlyAccessibleValueObject[] bvos) throws BusinessException {
		String billtype = (String) hvo
				.getAttributeValue(IFMConstInforNCC.PK_BILLTYPECODE);
		if (StringUtil.isNull(billtype)) {
			return null;
		}
		IFMToTbbAccessableBusiVO[] accessableBusiVOs = null;
		Vector<IAccessableBusiVO> vecAccessableBusiVO = new Vector<IAccessableBusiVO>();
		if (bvos == null || bvos.length == 0) {
			IFMToTbbAccessableBusiVO accessableBusiVO = getBillAccessableBusiVO(billtype);
			accessableBusiVO.setParentVO(hvo);
			// 对于单表头数据，因为没有子表所以需要特殊处理
			accessableBusiVO.setChildVO(changeBvos2BaseinfoVos(hvo));
			vecAccessableBusiVO.addElement(accessableBusiVO);
		} else {
			for (int i = 0; i < bvos.length; i++) {
				if (bvos[i] instanceof InvestIncomeVO) {
					IFMToTbbAccessableBusiVO accessableBusiVO = getBillAccessableBusiVO(billtype);
					accessableBusiVO.setParentVO(hvo);

					accessableBusiVO.setChildVO(changeBvos2BaseinfoVos(
							billtype, bvos[i]));
					vecAccessableBusiVO.addElement(accessableBusiVO);
				}
			}
		}
		accessableBusiVOs = getBillAccessableBusiVOArray(billtype);
		accessableBusiVOs = vecAccessableBusiVO.toArray(accessableBusiVOs);

		return accessableBusiVOs;
	}

	/**
	 * 取得预算检查信息
	 * 
	 * @param hvo
	 * @param bvos
	 * @return
	 * @throws BusinessException
	 */
	public static NtbCtlInfoVO getCheckinfo(
			CircularlyAccessibleValueObject hvo,
			CircularlyAccessibleValueObject[] bvos) throws BusinessException {
		String billtype = (String) hvo
				.getAttributeValue(IFMConstInforNCC.PK_BILLTYPECODE);
		if (StringUtil.isNull(billtype)) {
			return null;
		}
		IFMToTbbAccessableBusiVO[] accessableBusiVOs = null;
		Vector<IAccessableBusiVO> vecAccessableBusiVO = new Vector<IAccessableBusiVO>();
		if (bvos == null || bvos.length == 0) {
			IFMToTbbAccessableBusiVO accessableBusiVO = getBillAccessableBusiVO(billtype);
			accessableBusiVO.setParentVO(hvo);
			// 对于单表头数据，因为没有子表所以需要特殊处理
			accessableBusiVO.setChildVO(changeBvos2BaseinfoVos(hvo));
			vecAccessableBusiVO.addElement(accessableBusiVO);
		} else {
			for (int i = 0; i < bvos.length; i++) {

				if (bvos[i] instanceof InvestIncomeVO) {
					IFMToTbbAccessableBusiVO accessableBusiVO = getBillAccessableBusiVO(billtype);
					accessableBusiVO.setParentVO(hvo);

					// 如果是单表头，将表体数据设置为基本信息数据
					accessableBusiVO.setChildVO(changeBvos2BaseinfoVos(
							billtype, bvos[i]));
					vecAccessableBusiVO.addElement(accessableBusiVO);
				}
			}
		}
		accessableBusiVOs = getBillAccessableBusiVOArray(billtype);
		accessableBusiVOs = vecAccessableBusiVO.toArray(accessableBusiVOs);

		return ((IBudgetControl) NCLocator.getInstance().lookup(
				IBudgetControl.class.getName()))
				.getCheckInfo(accessableBusiVOs);
	}

	/**
	 * 预算控制
	 * 
	 * @param billvo
	 * @param reversedate
	 *            反向操作日期
	 * @throws BusinessException
	 *             useflag: IFormulaFuncName.PREFIND:预占数标志
	 *             IFormulaFuncName.UFIND:执行数标志
	 */
	public static String controlNtb(AggregatedValueObject billvo,
			boolean direction, UFDate reversedate, String useflag)
			throws BusinessException {
		if (billvo == null || billvo.getParentVO() == null) {
			return null;
		}

		// 判断预算是否启用
		if (!nc.vo.ifm.util.IFMPublicUtil.isTBBEnable((String) billvo.getParentVO()
				.getAttributeValue(IFMConstInforNCC.PK_GROUP))) {
			return null;
		}

		CircularlyAccessibleValueObject hvo = (CircularlyAccessibleValueObject) billvo
				.getParentVO().clone();
		if (direction) {// 如果是反向操作，设置上操作日期
			hvo.setAttributeValue("begindate", reversedate);
		}

		// 得到表体数据【授信协议明细】
		CircularlyAccessibleValueObject[] bvos = null;

		if (billvo instanceof AggInvestIncomeVO) {
			bvos = (CircularlyAccessibleValueObject[]) ((AbstractBill) billvo)
					.getChildren(InvestIncomeVO.class).clone();
		}

		// 取得控制主体的集团
		String currgroup = getCurrgroup(hvo);

		// 根据集团主键编码、模块编码查询启用期间
		String[] periods = ((ICreateCorpQueryService) NCLocator.getInstance()
				.lookup(ICreateCorpQueryService.class.getName()))
				.queryEnabledPeriod(currgroup, Product_IFM);
		// 检查预算是否安装
		if (currgroup == null || ArrayUtil.isNull(periods)) {
			return null;
		}
		if (direction) {// 逆向操作时不检查直接回写（负数）
			noCheckControl(hvo, bvos, true, useflag);
			return null;
		} else {
			if (hvo.getAttributeValue("haspassedntbcheck") != null// 柔性控制，不检查直接回写
					&& ((UFBoolean) hvo.getAttributeValue("haspassedntbcheck"))
							.booleanValue()) {
				noCheckControl(hvo, bvos, false, useflag);
			} else {
				// 调用预算接口BO
				NtbCtlInfoVO tpcontrolvo = getControlinfo(hvo, bvos, useflag);
				// 处理控制信息
				return IFMTbbCommonUtil.sendNtbMessage(tpcontrolvo, hvo);
			}
		}
		return null;
	}

	/**
	 * 取得预算控制信息
	 * 
	 * @param hvo
	 * @param bvos
	 * @return
	 * @throws BusinessException
	 */
	private static NtbCtlInfoVO getControlinfo(
			CircularlyAccessibleValueObject hvo,
			CircularlyAccessibleValueObject[] bvos, String useflag)
			throws BusinessException {
		String billtype = (String) hvo
				.getAttributeValue(IFMConstInforNCC.PK_BILLTYPECODE);
		if (StringUtil.isNull(billtype)) {
			return null;
		}
		IFMToTbbAccessableBusiVO[] accessableBusiVOs = null;
		Vector<IAccessableBusiVO> vecAccessableBusiVO = new Vector<IAccessableBusiVO>();
		if (bvos == null || bvos.length == 0) {
			IFMToTbbAccessableBusiVO accessableBusiVO = getBillAccessableBusiVO(billtype);
			accessableBusiVO.setParentVO(hvo);

			accessableBusiVO.setDataType(useflag);

			// 对于单表头数据，因为没有子表所以需要特殊处理
			accessableBusiVO.setChildVO(changeBvos2BaseinfoVos(hvo));
			vecAccessableBusiVO.addElement(accessableBusiVO);
		} else {
			for (int i = 0; i < bvos.length; i++) {
				if (bvos[i] instanceof InvestIncomeVO) {

					IFMToTbbAccessableBusiVO accessableBusiVO = getBillAccessableBusiVO(billtype);
					accessableBusiVO.setParentVO(hvo);
					accessableBusiVO.setChildVO(changeBvos2BaseinfoVos(
							billtype, bvos[i]));
					accessableBusiVO.setDataType(useflag);
					vecAccessableBusiVO.addElement(accessableBusiVO);
				}
			}
		}
		//根据单据类型取得对应单据的预算控制交互vo的实例
		accessableBusiVOs = getBillAccessableBusiVOArray(billtype);
		accessableBusiVOs = vecAccessableBusiVO.toArray(accessableBusiVOs);

		return ((IBudgetControl) NCLocator.getInstance().lookup(
				IBudgetControl.class.getName()))
				.getControlInfo(accessableBusiVOs);
	}

	/**
	 * 取得预算控制信息
	 * 
	 * @param hvo
	 * @param bvos
	 * @param directiuon
	 *            :true，逆向；false，正向
	 * @return
	 * @throws BusinessException
	 */
	private static void noCheckControl(CircularlyAccessibleValueObject hvo,
			CircularlyAccessibleValueObject[] bvos, boolean directiuon,
			String useflag) throws BusinessException {
		String billtype = (String) hvo
				.getAttributeValue(IFMConstInforNCC.PK_BILLTYPECODE);
		if (StringUtil.isNull(billtype)) {
			return;
		}
		IFMToTbbAccessableBusiVO[] accessableBusiVOs = null;
		Vector<IAccessableBusiVO> vecAccessableBusiVO = new Vector<IAccessableBusiVO>();
		if (bvos == null || bvos.length == 0) {
			IFMToTbbAccessableBusiVO accessableBusiVO = getBillAccessableBusiVO(billtype);
			accessableBusiVO.setParentVO(hvo);
			accessableBusiVO.setIsconverse(directiuon);

			accessableBusiVO.setDataType(useflag);

			// 对于单表头数据，因为没有子表所以需要特殊处理
			accessableBusiVO.setChildVO(changeBvos2BaseinfoVos(hvo));
			vecAccessableBusiVO.addElement(accessableBusiVO);
		} else {
			for (int i = 0; i < bvos.length; i++) {
				if (bvos[i] instanceof InvestIncomeVO) {

					IFMToTbbAccessableBusiVO accessableBusiVO = getBillAccessableBusiVO(billtype);
					accessableBusiVO.setParentVO(hvo);
					accessableBusiVO.setChildVO(changeBvos2BaseinfoVos(
							billtype, bvos[i]));
					accessableBusiVO.setIsconverse(directiuon);

					accessableBusiVO.setDataType(useflag);

					vecAccessableBusiVO.addElement(accessableBusiVO);
				}
			}
		}
		accessableBusiVOs = getBillAccessableBusiVOArray(billtype);
		accessableBusiVOs = vecAccessableBusiVO.toArray(accessableBusiVOs);

		((IBudgetControl) NCLocator.getInstance().lookup(
				IBudgetControl.class.getName()))
				.noCheckUpdateExe(accessableBusiVOs);
	}

	/**
	 * 取得控制主体的集团
	 * 
	 * @param hvo
	 * @return
	 */
	private static String getCurrgroup(CircularlyAccessibleValueObject hvo) {
		return (String) hvo.getAttributeValue(IFMConstInforNCC.PK_GROUP);
	}

	/**
	 * 根据单据类型取得对应单据的预算控制交互vo的实例
	 * 
	 * @param billtype
	 * @return
	 */
	private static IFMToTbbAccessableBusiVO getBillAccessableBusiVO(
			String billtype) {
		if(null == billtype){
			return null;
		}
		IFMToTbbAccessableBusiVO busiVO = null;
			if(billtype.equals(IIFM4TbbConst.pk_BillTypeCode_InvestApply)){
				busiVO = new Apply2TbbAccessableBusiVO();
			}
			else if(billtype.equals(IIFM4TbbConst.pk_BillTypeCode_InvestIncome)){
				busiVO = new Income2TbbAccessableBusiVO();
			}
			else if(billtype.equals(IIFM4TbbConst.pk_BillTypeCode_InvestRedeem)){
				busiVO = new Redeem2TbbAccessableBusiVO();
			}

		return busiVO;
	}

	/**
	 * 根据单据类型取得对应单据的预算控制交互vo的实例
	 * 
	 * @param billtype
	 * @return
	 */
	private static IFMToTbbAccessableBusiVO[] getBillAccessableBusiVOArray(
			String billtype) {
		if(null == billtype){
			return null;
		}
		IFMToTbbAccessableBusiVO[] busiVO = null;
		if(billtype.equals(IIFM4TbbConst.pk_BillTypeCode_InvestApply)){
			busiVO = new Apply2TbbAccessableBusiVO[] {};
		}
		else if(billtype.equals(IIFM4TbbConst.pk_BillTypeCode_InvestIncome)){
			busiVO = new Income2TbbAccessableBusiVO[] {};
		}
		else if(billtype.equals(IIFM4TbbConst.pk_BillTypeCode_InvestRedeem)){
			busiVO = new Redeem2TbbAccessableBusiVO[] {};
		}

		return busiVO;
	}

	/**
	 * 提示预算检查或控制信息
	 * 
	 * @param tpcontrolvo
	 * @param billvo
	 * @throws BusinessException
	 */
	private static String sendNtbMessage(NtbCtlInfoVO tpcontrolvo,
			CircularlyAccessibleValueObject billvo) throws BusinessException {
		Integer vbillstatus = (Integer) billvo
				.getAttributeValue(IFMConstInforNCC.VBILLSTATUS);
		String ntbException = "";
		if ((tpcontrolvo != null) && tpcontrolvo.isControl()) {// 控制
			String[] infos = tpcontrolvo.getControlInfos();
			for (int j = 0; j < infos.length; j++) {
				ntbException = ntbException + infos[j] + "\n"; // 控制信息
			}
			throw new BusinessException(ntbException);
		} else if ((tpcontrolvo != null) && tpcontrolvo.isAlarm()) {// 预警并且还没得到用户确认
			String[] infos = tpcontrolvo.getAlarmInfos();
			for (int j = 0; j < infos.length; j++) {
				ntbException = ntbException + "\n" + infos[j]; // 控制信息
			}
			billvo.setAttributeValue("tbbmessage", ntbException);
			return ntbException.trim();
		} else if ((tpcontrolvo != null) && tpcontrolvo.isMayBeControl()) {// 柔性
			String[] infos = tpcontrolvo.getFlexibleControlInfos();
			for (int j = 0; j < infos.length; j++) {
				ntbException = ntbException + "\n" + infos[j]; // 控制信息
			}
			if (billvo.getAttributeValue("haspassedntbcheck") != null
					&& ((UFBoolean) billvo
							.getAttributeValue("haspassedntbcheck"))
							.booleanValue()) {
				billvo.setAttributeValue("tbbmessage", ntbException);
			} else if (vbillstatus.intValue() == IBillStatus.CHECKPASS) {
				billvo.setAttributeValue("tbbmessage", ntbException);
				// } else {
				// throw new BusinessException(ntbException);
			}
			return ntbException.trim();
		}
		return null;
	}
}
