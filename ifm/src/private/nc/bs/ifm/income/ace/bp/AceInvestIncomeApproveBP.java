package nc.bs.ifm.income.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.ifm.income.InvestIncomeVO;

/**
 * 标准单据审核的BP
 */
public class AceInvestIncomeApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public InvestIncomeVO[] approve(InvestIncomeVO[] clientBills,
			InvestIncomeVO[] originBills) {
		for (InvestIncomeVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<InvestIncomeVO> update = new BillUpdate<InvestIncomeVO>();
		InvestIncomeVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
