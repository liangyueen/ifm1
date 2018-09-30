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
 * 银行帐写现金平台账基类
 * V6.1改，银行账接口变动，2012-2-10
 */
public abstract class IFACIFMAccSuper {
	
	/**
	 * 操作银行帐户账接口
	 */
	protected IBankAccService bankTallyService = null;
	
	
	/**
	 * 银行帐服务
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
	 * 初始化传现金平台VO对象组
	 *
	 * @param vos
	 */
	protected void initBankAccVO(List<BankAccDetailVO> vos, SuperVO headvo, RegisterVO bodyvo,String tallyman, UFDate tallydate) {
		int len = vos.size();
		for (int i = 0; i < len; i++) {
			// 记账人 --必填
			vos.get(i).setTallyperson(tallyman);
			// 记账时间--触发登账的时间（取系统时间）--必填
			vos.get(i).setTallytime(tallydate);
			// 记账日期--必填
			vos.get(i).setTallydate(new UFLiteralDate(tallydate.toLocalString()));
			// 核销标志--必填
			vos.get(i).setIsreverse(UFBoolean.FALSE);
			// 在途标志 --必填
			vos.get(i).setUseflag(UseFlagEnum.USE.getOperateTypeValue());
			// 资金形态 --必填
			vos.get(i).setFundformcode(FundFormCodeEnum.BANK.getOperateTypeValue());
			IBDData bankName = GeneralAccessorFactory.getAccessor(IBDMetaDataIDConst.BANKDOC).getDocByPk(vos.get(i).getOppbank());
			//对象内容
			vos.get(i).setOppbank(bankName == null ? "" : bankName.getName().toString());
			//对象类型
			vos.get(i).setOpptradetype(CmpConst.TradeObjType_Bank);
			// 交易种类
			vos.get(i).setTranstype(CmpConst.OUTTER_TRADE);
			// 来源系统
			vos.get(i).setSystemcode("FAC");
			// 单据主键
			vos.get(i).setPk_bill(headvo.getPrimaryKey());
			//单据类型ID
			vos.get(i).setBilltypecode((String) headvo.getAttributeValue("pk_billtypeid"));
			// 主组织
			vos.get(i).setPk_org((String) headvo.getAttributeValue("pk_org"));
			// 主组织版本
			vos.get(i).setPk_org_v((String) headvo.getAttributeValue("pk_org_v"));
			// 集团
			vos.get(i).setPk_group((String) headvo.getAttributeValue("pk_group"));
			// 组织汇率
			vos.get(i).setOlcrate((UFDouble) headvo.getAttributeValue("olcrate"));
			// 集团汇率
			vos.get(i).setGlcrate((UFDouble) headvo.getAttributeValue("glcrate"));
			// 全局汇率
			vos.get(i).setGllcrate((UFDouble) headvo.getAttributeValue("gllcrate"));
		}
	}
	
	/***
	 * 写账方法，子类构造参数调用即可。
	 *
	 * @param rvos
	 * @param writebank
	 * @throws Exception
	 */
	public void writeBankAcc(List<BankAccDetailVO> rvos, SuperVO headvo, String tallyman, UFDate tallydate, UFBoolean checkflag)
			throws BusinessException {

		// 判断现金管理是否启用,登账参数是否启用
		if (IFACPubUtil.isCMPEnable((String) headvo.getAttributeValue("pk_group"))) {
			//调用共同赋值逻辑
//			initBankAccVO(rvos, headvo, bodyvo, tallyman, tallydate);
			//过滤金额为空数据
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
	 * 过滤金额为0或者空的内容  金额为0的不过滤
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
	 * 删除银行帐
	 *
	 * @param aggvo
	 * @param tally_org
	 * @param operator
	 * @param operatedate
	 * @throws BusinessException
	 */
	public void delBankAcc(AggregatedValueObject aggvo, UFDate tallydate) throws BusinessException {
		// 判断现金管理是否启用
		if (IFACPubUtil.isCMPEnable((String) aggvo.getParentVO().getAttributeValue("pk_group"))) {
			SuperVO bizvo = (SuperVO)aggvo.getParentVO();
			String pk_org = (String)bizvo.getAttributeValue("pk_org");
			if (tallydate != null && IFACPubUtil.isCMPAccEnable(pk_org, tallydate)) {
				String[] serialnos = getBankSerialNOs(aggvo);
				if (ArrayUtil.isNull(serialnos)) {
					ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3614bankdeposit_0","03614bankdeposit-0230")/*@res "未记账的单据不能取消记账"*/);
				}
				getBankTallyService().deleteBankAccBySerialno(serialnos, ValidateTypeEnum.NORMAL.getOperateTypeValue());
			}
		}
	}
	
	public abstract String[] getBankSerialNOs(AggregatedValueObject aggvo) throws BusinessException;

}
