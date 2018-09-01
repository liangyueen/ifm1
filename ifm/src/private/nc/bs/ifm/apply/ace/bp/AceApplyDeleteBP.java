package nc.bs.ifm.apply.ace.bp;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.vo.ifm.apply.InvestApplyVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceApplyDeleteBP {

	public void delete(InvestApplyVO[] bills) {

		DeleteBPTemplate<InvestApplyVO> bp = new DeleteBPTemplate<InvestApplyVO>(
				ApplyPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<InvestApplyVO> processer) {
		// TODO 前规则
		IRule<InvestApplyVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<InvestApplyVO> processer) {
		// TODO 后规则

	}
}
