package nc.bs.ifm.income.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.ifm.IncomeBillStatusEnum;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceInvestIncomeUnSendApproveBP {

	public AggInvestIncomeVO[] unSend(AggInvestIncomeVO[] clientBills,
			AggInvestIncomeVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggInvestIncomeVO> update = new BillUpdate<AggInvestIncomeVO>();
		AggInvestIncomeVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggInvestIncomeVO[] clientBills) {
		for (AggInvestIncomeVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("billstatus",
					IncomeBillStatusEnum.NOSUB.value());//未提交
			clientBill.getParentVO().setAttributeValue("vbillstatus",
					BillStatusEnum.FREE.value());//自由
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
