package nc.bs.ifm.apply.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceApplyUnApproveBP {

	public InvestApplyVO[] unApprove(InvestApplyVO[] clientBills,
			InvestApplyVO[] originBills) {
		for (InvestApplyVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<InvestApplyVO> update = new BillUpdate<InvestApplyVO>();
		InvestApplyVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}
}
