package nc.bs.ifm.income.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.ifm.income.AggInvestIncomeVO;

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
	public AggInvestIncomeVO[] approve(AggInvestIncomeVO[] clientBills,
			AggInvestIncomeVO[] originBills) {
		for (AggInvestIncomeVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					2);
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggInvestIncomeVO> update = new BillUpdate<AggInvestIncomeVO>();
		AggInvestIncomeVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
