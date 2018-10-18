package nc.bs.ifm.apply.ace.bp;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.bs.ifm.pub.rule.RegisterUnWriteBankAccAfterRule;
import nc.bs.ifm.pub.rule.RegisterWriteBankAccAfterRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.pub.VOStatus;

/**
 * ��׼���������BP
 */
public class AceApplyUnApproveBP {

	public AggInvestApplyVO[] unApprove(AggInvestApplyVO[] clientBills,
			AggInvestApplyVO[] originBills) {
		UpdateBPTemplate<AggInvestApplyVO> bp = new UpdateBPTemplate<AggInvestApplyVO>(
				ApplyPluginPoint.UNAPPROVE);
		for (AggInvestApplyVO clientBill : clientBills) {
			clientBill.getParentVO().setBillstatus(VOStatus.UPDATED);
		}
		
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		AggInvestApplyVO[] returnVos =bp.update(clientBills, originBills);
		return returnVos;
	}
	
	
	/**
	 * ���������
	 * 
	 * @param processor
	 */

	private void addAfterRule(
			CompareAroundProcesser<AggInvestApplyVO> aroundProcesser) {
		IRule<AggInvestApplyVO> rwRule = new RegisterUnWriteBankAccAfterRule();
		aroundProcesser.addAfterRule(rwRule);
	}
}



