package nc.bs.ifm.redeem.ace.bp;

import nc.bs.ifm.redeem.plugin.bpplugin.InvestRedeemPluginPoint;
import nc.bs.ifm.redeem.rule.RegisterWriteBankAccAfterRule;
import nc.bs.ifm.redeem.rule.TallySendRedeemProcessVoucherRule;
import nc.bs.ifm.redeem.rule.UpdateIFMApplyRule;
import nc.bs.pub.contract.rule.UpdateApplyRule;
import nc.bs.pub.payreceipt.rule.PayReceiptVoucherRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.FillUpdateDataRule;
import nc.bs.pubapp.pub.rule.UpdateBillCodeRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.cdm.payrcptbankcredit.AggBankPayReceiptVO;
import nc.vo.ifm.RedeemStatusEnum;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.vo.pub.pf.BillStatusEnum;
/**
 * 标准单据审核的BP
 */
public class AceInvestRedeemApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggInvestRedeemVO[] approve(AggInvestRedeemVO[] clientBills,
			AggInvestRedeemVO[] originBills) {
		UpdateBPTemplate<AggInvestRedeemVO> bp = new UpdateBPTemplate<AggInvestRedeemVO>(InvestRedeemPluginPoint.APPROVE);
	/*	for (AggInvestRedeemVO clientBill : clientBills) {
			InvestRedeemVO vo = clientBill.getParentVO();
			Integer billstatus =   (Integer) RedeemStatusEnum.部分赎回.value();//待审核
			if(vo.getHoldmoeny()!=null){
				if ((vo.getHoldmoeny()).compareTo(UFDouble.ZERO_DBL)<1 ) {
					billstatus = (Integer) RedeemStatusEnum.全部赎回.value();
				}
			}else if(vo.getRedeemnumber()!=null){
				Integer lastNum = vo.getHoldnumber()-vo.getRedeemnumber();
				if(lastNum==0){
					billstatus = (Integer) RedeemStatusEnum.全部赎回.value();
				}
			}
			clientBill.getParentVO().setAttributeValue("billstatus", billstatus);
			clientBill.getParentVO().setBillstatus(VOStatus.NEW);
			
			//若有收益金额，则调用收益接口添加赎回收益
			if(vo.getRealreaning()!= null){
				
			}
		}*/
		this.addBeforeRule(bp.getAroundProcesser());
//		BillUpdate<AggInvestRedeemVO> update = new BillUpdate<AggInvestRedeemVO>();
//		AggInvestRedeemVO[] returnVos = update.update(clientBills, originBills);
		this.addAfterRule(bp.getAroundProcesser());
		AggInvestRedeemVO[] returnVos =bp.update(clientBills, originBills);
		//this.addAfterRule(clientBills);
		return returnVos;
	}
	
	
	
	private void addAfterRule(
			CompareAroundProcesser<AggInvestRedeemVO> aroundProcesser) {
		IRule<AggInvestRedeemVO> rwRule = new RegisterWriteBankAccAfterRule();
		IRule<AggInvestRedeemVO> rule = new TallySendRedeemProcessVoucherRule();
		aroundProcesser.addAfterRule(rwRule);
		aroundProcesser.addAfterRule(rule);
		
	}
	
	
	
	private void addBeforeRule(
			CompareAroundProcesser<AggInvestRedeemVO> aroundProcesser) {
		IRule<AggInvestRedeemVO> checkCCAmountRule = new UpdateIFMApplyRule();
		aroundProcesser.addBeforeRule(checkCCAmountRule);
		
	}

	/**
	 * 修改后规则
	 * 
	 * @param processor
	 */
	/*private void addAfterRule(AggInvestRedeemVO[] vos) {
		IRule<AggInvestRedeemVO> rule = null;
		rule = new TallySendRedeemProcessVoucherRule();
		rule.process(vos);
		
	}*/
	/*private void addAfterRule(CompareAroundProcesser<AggInvestRedeemVO> processer) {
		
		IRule<AggInvestRedeemVO> terminateRule = new TallySendRedeemProcessVoucherRule();
		processer.addAfterRule(terminateRule);
	}
*/
}
