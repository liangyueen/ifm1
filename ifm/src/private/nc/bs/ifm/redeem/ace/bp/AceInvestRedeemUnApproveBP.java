package nc.bs.ifm.redeem.ace.bp;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.bs.ifm.redeem.rule.RegisterUnWriteBankAccAfterRule;
import nc.bs.ifm.redeem.rule.TallySendRedeemProcessVoucherRule;
import nc.bs.ifm.redeem.rule.TallyUnSendRedeemProcessVoucherRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据弃审的BP
 */
public class AceInvestRedeemUnApproveBP {

	public AggInvestRedeemVO[] unApprove(AggInvestRedeemVO[] clientBills,
			AggInvestRedeemVO[] originBills) {
		UpdateBPTemplate<AggInvestRedeemVO> bp = new UpdateBPTemplate<AggInvestRedeemVO>(
				ApplyPluginPoint.UNAPPROVE);
		for (AggInvestRedeemVO clientBill : clientBills) {
			clientBill.getParentVO().setBillstatus(VOStatus.DELETED);
		}
//		BillUpdate<AggInvestRedeemVO> update = new BillUpdate<AggInvestRedeemVO>();
//		AggInvestRedeemVO[] returnVos = update.update(clientBills, originBills);
		this.addAfterRule(bp.getAroundProcesser());
		AggInvestRedeemVO[] returnVos =bp.update(clientBills, originBills);
		this.addAfterRule(clientBills);
		return returnVos;
	}
	
	/**
	 * 修改后规则
	 * 
	 * @param processor
	 */
	private void addAfterRule(AggInvestRedeemVO[] vos) {
		IRule<AggInvestRedeemVO> rule = null;
		rule = new TallyUnSendRedeemProcessVoucherRule();
		for (AggInvestRedeemVO clientBill : vos) {
			InvestRedeemVO vo = clientBill.getParentVO();
			rule.process(vos);
		}
	}
	
	/**
	 * 新增后规则
	 * 
	 * @param processor
	 */

	private void addAfterRule(
			CompareAroundProcesser<AggInvestRedeemVO> aroundProcesser) {
		IRule<AggInvestRedeemVO> rwRule = new RegisterUnWriteBankAccAfterRule();
		aroundProcesser.addAfterRule(rwRule);
	}
}
