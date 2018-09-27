package nc.bs.ifm.apply.ace.bp;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.vo.ifm.apply.AggInvestApplyVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceApplyDeleteBP {

	public void delete(AggInvestApplyVO[] bills) {

		DeleteBPTemplate<AggInvestApplyVO> bp = new DeleteBPTemplate<AggInvestApplyVO>(
				ApplyPluginPoint.DELETE);
		// 增加执行前规则
		// 增加执行后业务规则
		//this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggInvestApplyVO> processer) {
		// TODO 前规则
		IRule<AggInvestApplyVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则 
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggInvestApplyVO> processer) {
		// TODO 后规则

	}
}
