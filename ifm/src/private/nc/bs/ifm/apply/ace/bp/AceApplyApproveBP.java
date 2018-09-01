package nc.bs.ifm.apply.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pub.VOStatus;
import nc.vo.ifm.apply.InvestApplyVO;

/**
 * 标准单据审核的BP
 */
public class AceApplyApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public InvestApplyVO[] approve(InvestApplyVO[] clientBills,
			InvestApplyVO[] originBills) {
		for (InvestApplyVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<InvestApplyVO> update = new BillUpdate<InvestApplyVO>();
		InvestApplyVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

}
