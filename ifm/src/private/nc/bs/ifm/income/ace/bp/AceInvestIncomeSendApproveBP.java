package nc.bs.ifm.income.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.ifm.IncomeBillStatusEnum;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceInvestIncomeSendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggInvestIncomeVO[] sendApprove(AggInvestIncomeVO[] clientBills,
			AggInvestIncomeVO[] originBills) {
		for (AggInvestIncomeVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("billstatus",
					IncomeBillStatusEnum.NOAUDIT.value());//������
			/*clientBill.getParentVO().setAttributeValue("vbillstatus",
					BillStatusEnum.APPROVING.value());//��������
*/			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggInvestIncomeVO[] returnVos = new BillUpdate<AggInvestIncomeVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
