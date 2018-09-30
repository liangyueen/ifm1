package nc.vo.ifm.redeem;
import java.util.List;
import nc.bs.framework.common.NCLocator;
import nc.itf.bd.pub.IBDMetaDataIDConst;
import nc.itf.cm.prv.CmpConst;
import nc.pubitf.bd.accessor.GeneralAccessorFactory;
import nc.pubitf.cmp.bankaccbook.IBankAccService;
import nc.vo.bd.accessor.IBDData;
import nc.vo.cmp.bankaccbook.BankAccDetailVO;
import nc.vo.cmp.bankaccbook.constant.FundFormCodeEnum;
import nc.vo.cmp.bankaccbook.constant.UseFlagEnum;
import nc.vo.cmp.bankaccbook.constant.ValidateTypeEnum;
import nc.vo.fac.util.IFACPubUtil;
import nc.vo.fbm.register.RegisterVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.lang.UFLiteralDate;
import nc.vo.cmp.bankaccbook.constant.OperateTypeEnum;
import nc.vo.tmpub.util.ArrayUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;


/**
 * ������д�ֽ�ƽ̨�˻���
 * V6.1�ģ������˽ӿڱ䶯��2012-2-10
 */
public abstract class IFACIFMAccSuper {
	
	/**
	 * ���������ʻ��˽ӿ�
	 */
	protected IBankAccService bankTallyService = null;
	
	
	/**
	 * �����ʷ���
	 *
	 */
	public static IBankAccService getCmpBankAccService() {
		return (IBankAccService) NCLocator.getInstance().lookup(IBankAccService.class.getName());
	}


	protected IBankAccService getBankTallyService() {
		if (null == bankTallyService) {
			bankTallyService = IFACIFMAccSuper.getCmpBankAccService();
		}
		return bankTallyService;
	}
	
	/**
	 * ��ʼ�����ֽ�ƽ̨VO������
	 *
	 * @param vos
	 */
	protected void initBankAccVO(List<BankAccDetailVO> vos, SuperVO headvo, RegisterVO bodyvo,String tallyman, UFDate tallydate) {
		int len = vos.size();
		for (int i = 0; i < len; i++) {
			// ������ --����
			vos.get(i).setTallyperson(tallyman);
			// ����ʱ��--�������˵�ʱ�䣨ȡϵͳʱ�䣩--����
			vos.get(i).setTallytime(tallydate);
			// ��������--����
			vos.get(i).setTallydate(new UFLiteralDate(tallydate.toLocalString()));
			// ������־--����
			vos.get(i).setIsreverse(UFBoolean.FALSE);
			// ��;��־ --����
			vos.get(i).setUseflag(UseFlagEnum.USE.getOperateTypeValue());
			// �ʽ���̬ --����
			vos.get(i).setFundformcode(FundFormCodeEnum.BANK.getOperateTypeValue());
			IBDData bankName = GeneralAccessorFactory.getAccessor(IBDMetaDataIDConst.BANKDOC).getDocByPk(vos.get(i).getOppbank());
			//��������
			vos.get(i).setOppbank(bankName == null ? "" : bankName.getName().toString());
			//��������
			vos.get(i).setOpptradetype(CmpConst.TradeObjType_Bank);
			// ��������
			vos.get(i).setTranstype(CmpConst.OUTTER_TRADE);
			// ��Դϵͳ
			vos.get(i).setSystemcode("FAC");
			// ��������
			vos.get(i).setPk_bill(headvo.getPrimaryKey());
			//��������ID
			vos.get(i).setBilltypecode((String) headvo.getAttributeValue("pk_billtypeid"));
			// ����֯
			vos.get(i).setPk_org((String) headvo.getAttributeValue("pk_org"));
			// ����֯�汾
			vos.get(i).setPk_org_v((String) headvo.getAttributeValue("pk_org_v"));
			// ����
			vos.get(i).setPk_group((String) headvo.getAttributeValue("pk_group"));
			// ��֯����
			vos.get(i).setOlcrate((UFDouble) headvo.getAttributeValue("olcrate"));
			// ���Ż���
			vos.get(i).setGlcrate((UFDouble) headvo.getAttributeValue("glcrate"));
			// ȫ�ֻ���
			vos.get(i).setGllcrate((UFDouble) headvo.getAttributeValue("gllcrate"));
		}
	}
	
	/***
	 * д�˷��������๹��������ü��ɡ�
	 *
	 * @param rvos
	 * @param writebank
	 * @throws Exception
	 */
	public void writeBankAcc(List<BankAccDetailVO> rvos, SuperVO headvo, String tallyman, UFDate tallydate, UFBoolean checkflag)
			throws BusinessException {

		// �ж��ֽ�����Ƿ�����,���˲����Ƿ�����
		if (IFACPubUtil.isCMPEnable((String) headvo.getAttributeValue("pk_group"))) {
			//���ù�ͬ��ֵ�߼�
//			initBankAccVO(rvos, headvo, bodyvo, tallyman, tallydate);
			//���˽��Ϊ������
			if (rvos == null || rvos.size() == 0) {
				filterZeroMoney(rvos);
				return;
			}

			if (checkflag == null || checkflag.booleanValue() == false) {
				getBankTallyService().writeBankAcc(rvos.toArray(new BankAccDetailVO[0]),
						OperateTypeEnum.APPLYANDUSE.getOperateTypeValue());
			} else {
				getBankTallyService().writeBankAccNoCheck(rvos.toArray(new BankAccDetailVO[0]),
						OperateTypeEnum.APPLYANDUSE.getOperateTypeValue());
			}
		}
	}
	
	
	/**
	 * ���˽��Ϊ0���߿յ�����  ���Ϊ0�Ĳ�����
	 *
	 * @param vos
	 * @return
	 */
	protected List<BankAccDetailVO> filterZeroMoney(List<BankAccDetailVO> vos) {
		BankAccDetailVO setvo = null;
		for (int i = vos.size() - 1; i >= 0; i--) {
			setvo = (BankAccDetailVO) vos.get(i);
			if (CmpConst.Direction_Pay == setvo.getDirection()
					&& (setvo.getPaymoney() == null || setvo.getPaymoney().compareTo(new UFDouble(0)) == 0)) {
				vos.remove(i);
			} else if (CmpConst.Direction_Receive == setvo.getDirection()
					&& (setvo.getRecmoney() == null || setvo.getRecmoney().compareTo(new UFDouble(0)) == 0)) {
				vos.remove(i);
			}
		}
		return vos;
	}
	
	/**
	 * ɾ��������
	 *
	 * @param aggvo
	 * @param tally_org
	 * @param operator
	 * @param operatedate
	 * @throws BusinessException
	 */
	public void delBankAcc(AggregatedValueObject aggvo, UFDate tallydate) throws BusinessException {
		// �ж��ֽ�����Ƿ�����
		if (IFACPubUtil.isCMPEnable((String) aggvo.getParentVO().getAttributeValue("pk_group"))) {
			SuperVO bizvo = (SuperVO)aggvo.getParentVO();
			String pk_org = (String)bizvo.getAttributeValue("pk_org");
			if (tallydate != null && IFACPubUtil.isCMPAccEnable(pk_org, tallydate)) {
				String[] serialnos = getBankSerialNOs(aggvo);
				if (ArrayUtil.isNull(serialnos)) {
					ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3614bankdeposit_0","03614bankdeposit-0230")/*@res "δ���˵ĵ��ݲ���ȡ������"*/);
				}
				getBankTallyService().deleteBankAccBySerialno(serialnos, ValidateTypeEnum.NORMAL.getOperateTypeValue());
			}
		}
	}
	
	public abstract String[] getBankSerialNOs(AggregatedValueObject aggvo) throws BusinessException;

}
