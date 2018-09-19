package nc.bs.ifm.redeem.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceInvestRedeemUnSendApproveBP {

	public AggInvestRedeemVO[] unSend(AggInvestRedeemVO[] clientBills,
			AggInvestRedeemVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggInvestRedeemVO> update = new BillUpdate<AggInvestRedeemVO>();
		AggInvestRedeemVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(AggInvestRedeemVO[] clientBills) {
		for (AggInvestRedeemVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("vbillstatus",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
