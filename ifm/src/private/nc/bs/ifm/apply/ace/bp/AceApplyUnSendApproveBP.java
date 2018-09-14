package nc.bs.ifm.apply.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceApplyUnSendApproveBP {

	public AggInvestApplyVO[] unSend(AggInvestApplyVO[] clientBills,
			AggInvestApplyVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggInvestApplyVO> update = new BillUpdate<AggInvestApplyVO>();
		AggInvestApplyVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggInvestApplyVO[] clientBills) {
		for (AggInvestApplyVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("vbillstatus",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
			clientBill.getParentVO().setAttributeValue("billstatus", 2);
		}
	}
}
