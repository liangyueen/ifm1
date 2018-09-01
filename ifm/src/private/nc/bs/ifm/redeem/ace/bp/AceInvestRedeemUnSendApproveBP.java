package nc.bs.ifm.redeem.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceInvestRedeemUnSendApproveBP {

	public InvestRedeemVO[] unSend(InvestRedeemVO[] clientBills,
			InvestRedeemVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<InvestRedeemVO> update = new BillUpdate<InvestRedeemVO>();
		InvestRedeemVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(InvestRedeemVO[] clientBills) {
		for (InvestRedeemVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
