package nc.bs.ifm.redeem.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceInvestRedeemUnApproveBP {

	public InvestRedeemVO[] unApprove(InvestRedeemVO[] clientBills,
			InvestRedeemVO[] originBills) {
		for (InvestRedeemVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<InvestRedeemVO> update = new BillUpdate<InvestRedeemVO>();
		InvestRedeemVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
