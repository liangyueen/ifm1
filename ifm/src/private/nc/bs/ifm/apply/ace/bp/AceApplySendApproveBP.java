package nc.bs.ifm.apply.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼���������BP
 */
public class AceApplySendApproveBP {
	/**
	 * ������
	 * 
	 * @param vos
	 *            ����VO����
	 * @param script
	 *            ���ݶ����ű�����
	 * @return �����ĵ���VO����
	 */

	public AggInvestApplyVO[] sendApprove(AggInvestApplyVO[] clientBills,
			AggInvestApplyVO[] originBills) {
		for (AggInvestApplyVO clientFullVO : clientBills) {
			clientFullVO.getParentVO().setAttributeValue("${vmObject.billstatus}",
					BillStatusEnum.COMMIT.value());
			clientFullVO.getParentVO().setStatus(VOStatus.UPDATED);
		}
		// ���ݳ־û�
		AggInvestApplyVO[] returnVos = new BillUpdate<AggInvestApplyVO>().update(
				clientBills, originBills);
		return returnVos;
	}
}
