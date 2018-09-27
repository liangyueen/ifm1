package nc.bs.ifm.redeem.ace.bp;

import nc.bs.ifm.redeem.rule.TallySendRedeemProcessVoucherRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceInvestRedeemUnApproveBP {

	public AggInvestRedeemVO[] unApprove(AggInvestRedeemVO[] clientBills,
			AggInvestRedeemVO[] originBills) {
		for (AggInvestRedeemVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggInvestRedeemVO> update = new BillUpdate<AggInvestRedeemVO>();
		AggInvestRedeemVO[] returnVos = update.update(clientBills, originBills);
		this.addAfterRule(clientBills);
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
			if(vo.getVbilltype().equals(BillStatusEnum.APPROVED.value())){
				rule.process(vos);
			}
		}
	}
}
