package nc.bs.ifm.redeem.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.ifm.RedeemStatusEnum;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;

/**
 * 标准单据审核的BP
 */
public class AceInvestRedeemApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggInvestRedeemVO[] approve(AggInvestRedeemVO[] clientBills,
			AggInvestRedeemVO[] originBills) {
		for (AggInvestRedeemVO clientBill : clientBills) {
			InvestRedeemVO vo = clientBill.getParentVO();
			Integer billstatus =   (Integer) RedeemStatusEnum.部分赎回.value();//待审核
			if ((vo.getHoldmoeny()).compareTo(UFDouble.ZERO_DBL)<1 ) {
				billstatus = (Integer) RedeemStatusEnum.全部赎回.value();
			}
			clientBill.getParentVO().setAttributeValue("billstatus", billstatus);
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggInvestRedeemVO> update = new BillUpdate<AggInvestRedeemVO>();
		AggInvestRedeemVO[] returnVos = update.update(clientBills, originBills);
		return returnVos;
	}


}
