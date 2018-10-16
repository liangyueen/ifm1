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
 * ��Ԥ����Ŀ����ࡿ
 * 
 * 
 */
public class IFMTbbCommonUtil {
	// Ԥռ����־
	public static final String PREFIND = IFormulaFuncName.PREFIND;
	// ִ������־
	public static final String UFIND = IFormulaFuncName.UFIND;
	// ģ�����
	public static String Product_IFM = "IFM";

	/**
	 * Ԥ����
	 * 
	 * @param billvo
	 * @throws BusinessException
	 */
	public static String checkNtbBeforeEff(AggregatedValueObject billvo)
			throws BusinessException {
		if (billvo == null || billvo.getParentVO() == null) {
			return null;
		}

		// �ж�Ԥ���Ƿ�����
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
		// ���ݼ����������롢ģ������ѯ�����ڼ�
		String[] periods = ((ICreateCorpQueryService) NCLocator.getInstance()
				.lookup(ICreateCorpQueryService.class.getName()))
				.queryEnabledPeriod(currgroup, Product_IFM);
		// ���Ԥ���Ƿ�װ
		if (currgroup == null || ArrayUtil.isNull(periods)) {
			return null;
		}

		// ����Ԥ��ӿ�BO
		NtbCtlInfoVO tpcontrolvo = getCheckinfo(hvo, bvos);
		// ��������Ϣ
		return IFMTbbCommonUtil.sendNtbMessage(tpcontrolvo, hvo);
	}

	private static CircularlyAccessibleValueObject changeBvos2BaseinfoVos(
			String billtype, CircularlyAccessibleValueObject vo)
			throws BusinessException {
		// �������������ֱ�ӷ���VO������Ҫ��������
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
		// �������������ֱ�ӷ���VO������Ҫ��������
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
	 * ȡ��Ԥ�����VO��Ϣ
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
			// ���ڵ���ͷ���ݣ���Ϊû���ӱ�������Ҫ���⴦��
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
	 * ȡ��Ԥ������Ϣ
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
			// ���ڵ���ͷ���ݣ���Ϊû���ӱ�������Ҫ���⴦��
			accessableBusiVO.setChildVO(changeBvos2BaseinfoVos(hvo));
			vecAccessableBusiVO.addElement(accessableBusiVO);
		} else {
			for (int i = 0; i < bvos.length; i++) {

				if (bvos[i] instanceof InvestIncomeVO) {
					IFMToTbbAccessableBusiVO accessableBusiVO = getBillAccessableBusiVO(billtype);
					accessableBusiVO.setParentVO(hvo);

					// ����ǵ���ͷ����������������Ϊ������Ϣ����
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
	 * Ԥ�����
	 * 
	 * @param billvo
	 * @param reversedate
	 *            �����������
	 * @throws BusinessException
	 *             useflag: IFormulaFuncName.PREFIND:Ԥռ����־
	 *             IFormulaFuncName.UFIND:ִ������־
	 */
	public static String controlNtb(AggregatedValueObject billvo,
			boolean direction, UFDate reversedate, String useflag)
			throws BusinessException {
		if (billvo == null || billvo.getParentVO() == null) {
			return null;
		}

		// �ж�Ԥ���Ƿ�����
		if (!nc.vo.ifm.util.IFMPublicUtil.isTBBEnable((String) billvo.getParentVO()
				.getAttributeValue(IFMConstInforNCC.PK_GROUP))) {
			return null;
		}

		CircularlyAccessibleValueObject hvo = (CircularlyAccessibleValueObject) billvo
				.getParentVO().clone();
		if (direction) {// ����Ƿ�������������ϲ�������
			hvo.setAttributeValue("begindate", reversedate);
		}

		// �õ��������ݡ�����Э����ϸ��
		CircularlyAccessibleValueObject[] bvos = null;

		if (billvo instanceof AggInvestIncomeVO) {
			bvos = (CircularlyAccessibleValueObject[]) ((AbstractBill) billvo)
					.getChildren(InvestIncomeVO.class).clone();
		}

		// ȡ�ÿ�������ļ���
		String currgroup = getCurrgroup(hvo);

		// ���ݼ����������롢ģ������ѯ�����ڼ�
		String[] periods = ((ICreateCorpQueryService) NCLocator.getInstance()
				.lookup(ICreateCorpQueryService.class.getName()))
				.queryEnabledPeriod(currgroup, Product_IFM);
		// ���Ԥ���Ƿ�װ
		if (currgroup == null || ArrayUtil.isNull(periods)) {
			return null;
		}
		if (direction) {// �������ʱ�����ֱ�ӻ�д��������
			noCheckControl(hvo, bvos, true, useflag);
			return null;
		} else {
			if (hvo.getAttributeValue("haspassedntbcheck") != null// ���Կ��ƣ������ֱ�ӻ�д
					&& ((UFBoolean) hvo.getAttributeValue("haspassedntbcheck"))
							.booleanValue()) {
				noCheckControl(hvo, bvos, false, useflag);
			} else {
				// ����Ԥ��ӿ�BO
				NtbCtlInfoVO tpcontrolvo = getControlinfo(hvo, bvos, useflag);
				// ���������Ϣ
				return IFMTbbCommonUtil.sendNtbMessage(tpcontrolvo, hvo);
			}
		}
		return null;
	}

	/**
	 * ȡ��Ԥ�������Ϣ
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

			// ���ڵ���ͷ���ݣ���Ϊû���ӱ�������Ҫ���⴦��
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
		//���ݵ�������ȡ�ö�Ӧ���ݵ�Ԥ����ƽ���vo��ʵ��
		accessableBusiVOs = getBillAccessableBusiVOArray(billtype);
		accessableBusiVOs = vecAccessableBusiVO.toArray(accessableBusiVOs);

		return ((IBudgetControl) NCLocator.getInstance().lookup(
				IBudgetControl.class.getName()))
				.getControlInfo(accessableBusiVOs);
	}

	/**
	 * ȡ��Ԥ�������Ϣ
	 * 
	 * @param hvo
	 * @param bvos
	 * @param directiuon
	 *            :true������false������
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

			// ���ڵ���ͷ���ݣ���Ϊû���ӱ�������Ҫ���⴦��
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
	 * ȡ�ÿ�������ļ���
	 * 
	 * @param hvo
	 * @return
	 */
	private static String getCurrgroup(CircularlyAccessibleValueObject hvo) {
		return (String) hvo.getAttributeValue(IFMConstInforNCC.PK_GROUP);
	}

	/**
	 * ���ݵ�������ȡ�ö�Ӧ���ݵ�Ԥ����ƽ���vo��ʵ��
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
	 * ���ݵ�������ȡ�ö�Ӧ���ݵ�Ԥ����ƽ���vo��ʵ��
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
	 * ��ʾԤ����������Ϣ
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
		if ((tpcontrolvo != null) && tpcontrolvo.isControl()) {// ����
			String[] infos = tpcontrolvo.getControlInfos();
			for (int j = 0; j < infos.length; j++) {
				ntbException = ntbException + infos[j] + "\n"; // ������Ϣ
			}
			throw new BusinessException(ntbException);
		} else if ((tpcontrolvo != null) && tpcontrolvo.isAlarm()) {// Ԥ�����һ�û�õ��û�ȷ��
			String[] infos = tpcontrolvo.getAlarmInfos();
			for (int j = 0; j < infos.length; j++) {
				ntbException = ntbException + "\n" + infos[j]; // ������Ϣ
			}
			billvo.setAttributeValue("tbbmessage", ntbException);
			return ntbException.trim();
		} else if ((tpcontrolvo != null) && tpcontrolvo.isMayBeControl()) {// ����
			String[] infos = tpcontrolvo.getFlexibleControlInfos();
			for (int j = 0; j < infos.length; j++) {
				ntbException = ntbException + "\n" + infos[j]; // ������Ϣ
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
