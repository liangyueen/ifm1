package nc.bs.ifm.redeem.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.ifm.redeem.InvestRedeemVO;

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
	public InvestRedeemVO[] approve(InvestRedeemVO[] clientBills,
			InvestRedeemVO[] originBills) {
		for (InvestRedeemVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<InvestRedeemVO> update = new BillUpdate<InvestRedeemVO>();
		InvestRedeemVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
