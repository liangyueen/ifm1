package nc.bs.ifm.redeem.ace.bp;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.bs.ifm.redeem.rule.InvestRedeemApproveTbbRule;
import nc.bs.ifm.redeem.rule.InvestRedeemUnApproveTbbRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.VOStatus;

/**
 * ��׼���������BP
 */
public class AceInvestRedeemUnApproveBP {

	public AggInvestRedeemVO[] unApprove(AggInvestRedeemVO[] clientBills,
			AggInvestRedeemVO[] originBills) {
		for (AggInvestRedeemVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggInvestRedeemVO> update = new BillUpdate<AggInvestRedeemVO>();
		AggInvestRedeemVO[] returnVos = update.update(clientBills, originBills);
		UpdateBPTemplate<AggInvestRedeemVO> bp = new UpdateBPTemplate<AggInvestRedeemVO>(
				ApplyPluginPoint.APPROVE);
		this.addBeforeRule(bp.getAroundProcesser());
		return returnVos;
	}
	/**
	 * ִ��ǰ����
	 * 
	 * @param aroundProcesser
	 */
	private void addBeforeRule(
			CompareAroundProcesser<AggInvestRedeemVO> aroundProcesser) {
		/** ����Ԥ����ƣ��ͷ�Ԥռ��,����Ԥ����ƣ���д�Ǽǵ�ִ���� **/
		IRule<AggInvestRedeemVO> tbbRule = new InvestRedeemUnApproveTbbRule();
		aroundProcesser.addBeforeRule(tbbRule);
	
	
	}
}
