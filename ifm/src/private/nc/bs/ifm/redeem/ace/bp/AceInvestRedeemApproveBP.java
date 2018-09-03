package nc.bs.ifm.redeem.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.VOStatus;

/**
 * ��׼������˵�BP
 */
public class AceInvestRedeemApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggInvestRedeemVO[] approve(AggInvestRedeemVO[] clientBills,
			AggInvestRedeemVO[] originBills) {
		for (AggInvestRedeemVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggInvestRedeemVO> update = new BillUpdate<AggInvestRedeemVO>();
		AggInvestRedeemVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
