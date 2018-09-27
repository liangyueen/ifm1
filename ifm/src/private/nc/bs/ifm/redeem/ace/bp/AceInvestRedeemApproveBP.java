package nc.bs.ifm.redeem.ace.bp;

import nc.bs.ifm.redeem.plugin.bpplugin.InvestRedeemPluginPoint;
import nc.bs.ifm.redeem.rule.TallySendRedeemProcessVoucherRule;
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
 * ��׼������˵�BP
 */
public class AceInvestRedeemApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggInvestRedeemVO[] approve(AggInvestRedeemVO[] clientBills,
			AggInvestRedeemVO[] originBills) {
		//UpdateBPTemplate<AggInvestRedeemVO> bp = new UpdateBPTemplate<AggInvestRedeemVO>(InvestRedeemPluginPoint.APPROVE);
		for (AggInvestRedeemVO clientBill : clientBills) {
			InvestRedeemVO vo = clientBill.getParentVO();
			Integer billstatus =   (Integer) RedeemStatusEnum.�������.value();//�����
			if ((vo.getHoldmoeny()).compareTo(UFDouble.ZERO_DBL)<1 ) {
				billstatus = (Integer) RedeemStatusEnum.ȫ�����.value();
			}
			clientBill.getParentVO().setAttributeValue("billstatus", billstatus);
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggInvestRedeemVO> update = new BillUpdate<AggInvestRedeemVO>();
		AggInvestRedeemVO[] returnVos = update.update(clientBills, originBills);
		this.addAfterRule(clientBills);
		//this.addAfterRule(bp.getAroundProcesser());
		return returnVos;
	}

	/**
	 * �޸ĺ����
	 * 
	 * @param processor
	 */
	private void addAfterRule(AggInvestRedeemVO[] vos) {
		IRule<AggInvestRedeemVO> rule = null;
		rule = new TallySendRedeemProcessVoucherRule();
		for (AggInvestRedeemVO clientBill : vos) {
			InvestRedeemVO vo = clientBill.getParentVO();
			rule.process(vos);
		}
	}
	/*private void addAfterRule(CompareAroundProcesser<AggInvestRedeemVO> processer) {
		
		IRule<AggInvestRedeemVO> terminateRule = new TallySendRedeemProcessVoucherRule();
		processer.addAfterRule(terminateRule);
	}
*/
}
