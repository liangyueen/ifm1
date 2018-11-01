package nc.bs.ifm.redeem.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.ifm.RedeemStatusEnum;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceInvestRedeemSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public AggInvestRedeemVO[] sendApprove(AggInvestRedeemVO[] clientBills,
			AggInvestRedeemVO[] originBills) {
		for (AggInvestRedeemVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("billstatus",
					RedeemStatusEnum.NOAUDIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		AggInvestRedeemVO[] returnVos = new BillUpdate<AggInvestRedeemVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
