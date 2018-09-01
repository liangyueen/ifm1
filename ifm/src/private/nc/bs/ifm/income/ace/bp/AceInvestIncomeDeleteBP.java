package nc.bs.ifm.income.ace.bp;

import nc.bs.ifm.income.plugin.bpplugin.InvestIncomePluginPoint;
import nc.vo.ifm.income.InvestIncomeVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceInvestIncomeDeleteBP {

	public void delete(InvestIncomeVO[] bills) {

		DeleteBPTemplate<InvestIncomeVO> bp = new DeleteBPTemplate<InvestIncomeVO>(
				InvestIncomePluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<InvestIncomeVO> processer) {
		// TODO 前规则
		IRule<InvestIncomeVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<InvestIncomeVO> processer) {
		// TODO 后规则

	}
}
