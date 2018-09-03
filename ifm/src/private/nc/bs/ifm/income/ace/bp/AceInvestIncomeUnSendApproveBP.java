package nc.bs.ifm.income.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceInvestIncomeUnSendApproveBP {

	public AggInvestIncomeVO[] unSend(AggInvestIncomeVO[] clientBills,
			AggInvestIncomeVO[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggInvestIncomeVO> update = new BillUpdate<AggInvestIncomeVO>();
		AggInvestIncomeVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggInvestIncomeVO[] clientBills) {
		for (AggInvestIncomeVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
