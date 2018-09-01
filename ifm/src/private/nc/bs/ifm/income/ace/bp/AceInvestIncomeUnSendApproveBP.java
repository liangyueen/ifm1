package nc.bs.ifm.income.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceInvestIncomeUnSendApproveBP {

	public InvestIncomeVO[] unSend(InvestIncomeVO[] clientBills,
			InvestIncomeVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<InvestIncomeVO> update = new BillUpdate<InvestIncomeVO>();
		InvestIncomeVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(InvestIncomeVO[] clientBills) {
		for (InvestIncomeVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
