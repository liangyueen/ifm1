package nc.bs.ifm.income.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.ifm.IncomeBillStatusEnum;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据送审的BP
 */
public class AceInvestIncomeSendApproveBP {
	/**
	 * 送审动作
	 * 
	 * @param vos
	 *            单据VO数组
	 * @param script
	 *            单据动作脚本对象
	 * @return 送审后的单据VO数组
	 */

	public AggInvestIncomeVO[] sendApprove(AggInvestIncomeVO[] clientBills,
			AggInvestIncomeVO[] originBills) {
		for (AggInvestIncomeVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("billstatus",
					IncomeBillStatusEnum.NOAUDIT.value());//待审批
			/*clientBill.getParentVO().setAttributeValue("vbillstatus",
					BillStatusEnum.APPROVING.value());//正在审批
*/			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// 数据持久化
		AggInvestIncomeVO[] returnVos = new BillUpdate<AggInvestIncomeVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
