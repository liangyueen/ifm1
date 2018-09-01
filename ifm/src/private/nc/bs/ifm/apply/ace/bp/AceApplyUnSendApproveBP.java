package nc.bs.ifm.apply.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据收回的BP
 */
public class AceApplyUnSendApproveBP {

	public InvestApplyVO[] unSend(InvestApplyVO[] clientBills,
			InvestApplyVO[] originBills) {
		// 把VO持久化到数据库中
		this.setHeadVOStatus(clientBills);
		BillUpdate<InvestApplyVO> update = new BillUpdate<InvestApplyVO>();
		InvestApplyVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}

	private void setHeadVOStatus(InvestApplyVO[] clientBills) {
		for (InvestApplyVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
