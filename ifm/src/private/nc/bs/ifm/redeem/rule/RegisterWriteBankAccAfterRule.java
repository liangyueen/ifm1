package nc.bs.ifm.redeem.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.bd.pub.IBDMetaDataIDConst;
import nc.itf.cm.prv.CmpConst;
import nc.pubitf.bd.accessor.GeneralAccessorFactory;
import nc.vo.bd.accessor.IBDData;
import nc.vo.cmp.bankaccbook.BankAccDetailVO;
import nc.vo.cmp.bankaccbook.constant.FundFormCodeEnum;
import nc.vo.cmp.bankaccbook.constant.UseFlagEnum;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.IFACIFMAccSuper;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.lang.UFLiteralDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class RegisterWriteBankAccAfterRule extends IFACIFMAccSuper implements
		IRule<AggInvestRedeemVO> {

	@Override
	public void process(AggInvestRedeemVO[] vos) {
		

		for (AggInvestRedeemVO vo : vos) {
			InvestRedeemVO parentVO = (InvestRedeemVO) vo.getParent();
			
			// ����״̬Ϊ����ͨ��,�ż���
			if (parentVO.getBillstatus() == 2) {
				try {
					this.writeBankAcc(vo, parentVO.getPk_org(), parentVO
							.getApprover(), parentVO.getApprovedate());			
				} catch (BusinessException e) {
					ExceptionUtils.wrappException(e);
				}
			}
		}	
	}
	
	
	/**
	 * д������
	 * 
	 * @param billvo
	 * @param org
	 * @param approveman
	 * @param date
	 * @throws BusinessException
	 */
	public void writeBankAcc(AggregatedValueObject aggVO, String org,
			String approveman, UFDate date) throws BusinessException {
		List<BankAccDetailVO> list = new ArrayList<BankAccDetailVO>();
		InvestRedeemVO headvo = (InvestRedeemVO) aggVO.getParentVO();

		BankAccDetailVO detailVO = null;//gatheringaccount
		//int flag = 0;
		if (null != headvo.getInvestaccount()) {
			detailVO = new BankAccDetailVO();
			// ��BankDeatailVO��ֵ
			initBankDeatailVO(detailVO, headvo,headvo.getInvestaccount(), CmpConst.Direction_Pay);
			// ���ù�ͬ��ֵ�߼�
			initBankAccVO(detailVO, headvo,approveman, date);

			list.add(detailVO);
		}
		if (list.size() == 0) {
			ExceptionUtils.wrappBusinessException("û���˻���Ҫ����");
		}
		// д������
		writeBankAcc(list, headvo, approveman, date, UFBoolean.valueOf(false));
		list.remove(detailVO);

	}
	
	
	private void initBankDeatailVO(BankAccDetailVO detailVO,
			InvestRedeemVO headvo, String account, int sf) {
		// ��ϸ������
		detailVO.setPk_bill_b(headvo.getPk_redeem());
		// �����˻�(֪ͨ�˻�/�����˻�)
		detailVO.setPk_account(headvo.getInvestaccount());
		// ��Դ��������
		detailVO.setPk_billtype(headvo.getPk_billtypecode());
		// ���ݱ��
		detailVO.setVbillno(headvo.getVbillno());
		// ��ע
		detailVO.setNote(headvo.getRemark());
		// ����
		detailVO.setPk_curr(headvo.getPk_currtype());
		// ����
		detailVO.setDirection(sf);
		// �ո�����
		detailVO.setFundstype(sf);
		// ����ҽ��
		detailVO.setPaymoney(headvo.getOlcmoney());
		// ������
		detailVO.setTallyperson(headvo.getBillmaker());
		// ����ʱ��
		detailVO.setTallytime(new UFDate(headvo.getBillmaketime().toLocalString()));
		// ��������
		detailVO.setTallydate(new UFLiteralDate(headvo.getBillmakedate().toLocalString()));
		// ����ԭ�ҽ��
		detailVO.setOlcpaymoney(headvo.getRedeemmoney());
		// Ψһ������:
		// ���ݱ�� + �������� + �ӱ�������û���ӱ�д����������+ �˻�PK +
		// �ո������ֽ��ṩ�ĳ�����+�����ʶ������һ���˻�д���֧ͬ����ͬ�շ���ģ��Լ����������ʶ������+flag
		detailVO.setOnlyclueno(headvo.getVbillno() + headvo.getPk_redeem()
				+ account + sf);
	}
	

	@Override
	public String[] getBankSerialNOs(AggregatedValueObject aggvo)
			throws BusinessException {
		return null;
	}
	
	
	/**
	 * ��ʼ�����ֽ�ƽ̨VO������
	 * 
	 * @param vos
	 */
	protected void initBankAccVO(BankAccDetailVO vos, SuperVO headvo,
			String tallyman, UFDate tallydate) {
		// ������ --����
		vos.setTallyperson(tallyman);
		// ����ʱ��--�������˵�ʱ�䣨ȡϵͳʱ�䣩--����
		vos.setTallytime(tallydate);
		// ��������--����
		vos.setTallydate(new UFLiteralDate(tallydate.toLocalString()));
		// ������־--����
		vos.setIsreverse(UFBoolean.FALSE);
		// ��;��־ --����
		vos.setUseflag(UseFlagEnum.USE.getOperateTypeValue());
		// �ʽ���̬ --����
		vos.setFundformcode(FundFormCodeEnum.BANK.getOperateTypeValue());
		IBDData bankName = GeneralAccessorFactory.getAccessor(
				IBDMetaDataIDConst.BANKDOC).getDocByPk(vos.getOppbank());
		// ��������
		vos.setOppbank(bankName == null ? "" : bankName.getName().toString());
		// ��������
		vos.setOpptradetype(CmpConst.TradeObjType_Bank);
		// ��������
		vos.setTranstype(CmpConst.OUTTER_TRADE);
		// ��Դϵͳ
		vos.setSystemcode("IFM");
		// ��������
		vos.setPk_bill(headvo.getPrimaryKey());
		// ��������ID
		vos.setBilltypecode("3642");
		// ����֯
		vos.setPk_org((String) headvo.getAttributeValue("pk_org"));
		// ����֯�汾
		vos.setPk_org_v((String) headvo.getAttributeValue("pk_org_v"));
		// ����
		vos.setPk_group((String) headvo.getAttributeValue("pk_group"));
		// ��֯����
		vos.setOlcrate((UFDouble) headvo.getAttributeValue("olcrate"));
		// ���Ż���
		vos.setGlcrate((UFDouble) headvo.getAttributeValue("glcrate"));
		// ȫ�ֻ���
		vos.setGllcrate((UFDouble) headvo .getAttributeValue("gllcrate"));

	}

	

}