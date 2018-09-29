package nc.bs.ifm.apply.ace.bp;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.bs.ifm.apply.rule.TallySendApplyProcessVoucherRule;
import nc.bs.ifm.pub.rule.RegisterWriteBankAccAfterRule;
import nc.bs.ifm.redeem.rule.TallySendRedeemProcessVoucherRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.VOStatus;

/**
 * ��׼������˵�BP
 */
public class AceApplyApproveBP {

	/**
	 * ��˶���
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggInvestApplyVO[] approve(AggInvestApplyVO[] clientBills,
			AggInvestApplyVO[] originBills) {
		
		UpdateBPTemplate<AggInvestApplyVO> bp = new UpdateBPTemplate<AggInvestApplyVO>(
				ApplyPluginPoint.APPROVE);
		for (AggInvestApplyVO clientBill : clientBills) {
			clientBill.getParentVO().setBillstatus(VOStatus.NEW);
			//clientBill.getParentVO().setAttributeValue("billstatus", 1);
		}
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		this.addAfterRule(clientBills);
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
		IRule<AggInvestApplyVO> rwRule = new RegisterWriteBankAccAfterRule();
		aroundProcesser.addAfterRule(rwRule);
		
	}
	private void addAfterRule(AggInvestApplyVO[] vos) {
		IRule<AggInvestApplyVO> rule = null;
		rule = new TallySendApplyProcessVoucherRule();
		rule.process(vos);
		
	}
}
