package nc.bs.ifm.apply.ace.bp;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.bs.ifm.apply.rule.InvestApplyApproveTbbRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据审核的BP
 */
public class AceApplyApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggInvestApplyVO[] approve(AggInvestApplyVO[] clientBills,
			AggInvestApplyVO[] originBills) {
		for (AggInvestApplyVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggInvestApplyVO> update = new BillUpdate<AggInvestApplyVO>();
		AggInvestApplyVO[] returnVos = update.update(clientBills, originBills);
		UpdateBPTemplate<AggInvestApplyVO> bp = new UpdateBPTemplate<AggInvestApplyVO>(
				ApplyPluginPoint.APPROVE);
		this.addBeforeRule(bp.getAroundProcesser());
		return returnVos;
	}
	/**
	 * 执行前规则
	 * 
	 * @param aroundProcesser
	 */
	private void addBeforeRule(
			CompareAroundProcesser<AggInvestApplyVO> aroundProcesser) {
		/** 进行预算控制，释放预占数,进行预算控制，回写登记单执行数 **/
		IRule<AggInvestApplyVO> tbbRule = new InvestApplyApproveTbbRule();
		aroundProcesser.addBeforeRule(tbbRule);
	
	
	}
}
