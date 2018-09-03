package nc.bs.ifm.income.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.VOStatus;

/**
 * ��׼���������BP
 */
public class AceInvestIncomeUnApproveBP {

	public AggInvestIncomeVO[] unApprove(AggInvestIncomeVO[] clientBills,
			AggInvestIncomeVO[] originBills) {
		for (AggInvestIncomeVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggInvestIncomeVO> update = new BillUpdate<AggInvestIncomeVO>();
		AggInvestIncomeVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
