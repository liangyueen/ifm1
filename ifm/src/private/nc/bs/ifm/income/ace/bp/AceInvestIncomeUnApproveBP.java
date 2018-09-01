package nc.bs.ifm.income.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceInvestIncomeUnApproveBP {

	public InvestIncomeVO[] unApprove(InvestIncomeVO[] clientBills,
			InvestIncomeVO[] originBills) {
		for (InvestIncomeVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<InvestIncomeVO> update = new BillUpdate<InvestIncomeVO>();
		InvestIncomeVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
