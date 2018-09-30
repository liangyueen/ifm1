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
			
			// 审批状态为审批通过,才记账
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
	 * 写银行账
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
			// 给BankDeatailVO赋值
			initBankDeatailVO(detailVO, headvo,headvo.getInvestaccount(), CmpConst.Direction_Pay);
			// 调用共同赋值逻辑
			initBankAccVO(detailVO, headvo,approveman, date);

			list.add(detailVO);
		}
		if (list.size() == 0) {
			ExceptionUtils.wrappBusinessException("没有账户需要记账");
		}
		// 写银行账
		writeBankAcc(list, headvo, approveman, date, UFBoolean.valueOf(false));
		list.remove(detailVO);

	}
	
	
	private void initBankDeatailVO(BankAccDetailVO detailVO,
			InvestRedeemVO headvo, String account, int sf) {
		// 明细表主键
		detailVO.setPk_bill_b(headvo.getPk_redeem());
		// 本方账户(通知账户/结算账户)
		detailVO.setPk_account(headvo.getInvestaccount());
		// 来源单据类型
		detailVO.setPk_billtype(headvo.getPk_billtypecode());
		// 单据编号
		detailVO.setVbillno(headvo.getVbillno());
		// 备注
		detailVO.setNote(headvo.getRemark());
		// 币种
		detailVO.setPk_curr(headvo.getPk_currtype());
		// 方向
		detailVO.setDirection(sf);
		// 收付类型
		detailVO.setFundstype(sf);
		// 付款本币金额
		detailVO.setPaymoney(headvo.getOlcmoney());
		// 记账人
		detailVO.setTallyperson(headvo.getBillmaker());
		// 记账时间
		detailVO.setTallytime(new UFDate(headvo.getBillmaketime().toLocalString()));
		// 记账日期
		detailVO.setTallydate(new UFLiteralDate(headvo.getBillmakedate().toLocalString()));
		// 付款原币金额
		detailVO.setOlcpaymoney(headvo.getRedeemmoney());
		// 唯一线索号:
		// 单据编号 + 主表主键 + 子表主键（没有子表写主表主键）+ 账户PK +
		// 收付方向（现金提供的常量）+特殊标识（出现一个账户写多笔同支或者同收方向的，自己定义特殊标识常量）+flag
		detailVO.setOnlyclueno(headvo.getVbillno() + headvo.getPk_redeem()
				+ account + sf);
	}
	

	@Override
	public String[] getBankSerialNOs(AggregatedValueObject aggvo)
			throws BusinessException {
		return null;
	}
	
	
	/**
	 * 初始化传现金平台VO对象组
	 * 
	 * @param vos
	 */
	protected void initBankAccVO(BankAccDetailVO vos, SuperVO headvo,
			String tallyman, UFDate tallydate) {
		// 记账人 --必填
		vos.setTallyperson(tallyman);
		// 记账时间--触发登账的时间（取系统时间）--必填
		vos.setTallytime(tallydate);
		// 记账日期--必填
		vos.setTallydate(new UFLiteralDate(tallydate.toLocalString()));
		// 核销标志--必填
		vos.setIsreverse(UFBoolean.FALSE);
		// 在途标志 --必填
		vos.setUseflag(UseFlagEnum.USE.getOperateTypeValue());
		// 资金形态 --必填
		vos.setFundformcode(FundFormCodeEnum.BANK.getOperateTypeValue());
		IBDData bankName = GeneralAccessorFactory.getAccessor(
				IBDMetaDataIDConst.BANKDOC).getDocByPk(vos.getOppbank());
		// 对象内容
		vos.setOppbank(bankName == null ? "" : bankName.getName().toString());
		// 对象类型
		vos.setOpptradetype(CmpConst.TradeObjType_Bank);
		// 交易种类
		vos.setTranstype(CmpConst.OUTTER_TRADE);
		// 来源系统
		vos.setSystemcode("IFM");
		// 单据主键
		vos.setPk_bill(headvo.getPrimaryKey());
		// 单据类型ID
		vos.setBilltypecode("3642");
		// 主组织
		vos.setPk_org((String) headvo.getAttributeValue("pk_org"));
		// 主组织版本
		vos.setPk_org_v((String) headvo.getAttributeValue("pk_org_v"));
		// 集团
		vos.setPk_group((String) headvo.getAttributeValue("pk_group"));
		// 组织汇率
		vos.setOlcrate((UFDouble) headvo.getAttributeValue("olcrate"));
		// 集团汇率
		vos.setGlcrate((UFDouble) headvo.getAttributeValue("glcrate"));
		// 全局汇率
		vos.setGllcrate((UFDouble) headvo .getAttributeValue("gllcrate"));

	}

	

}