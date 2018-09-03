package nc.bs.ifm.income.ace.bp;

import nc.bs.ifm.income.plugin.bpplugin.InvestIncomePluginPoint;
import nc.vo.ifm.income.AggInvestIncomeVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceInvestIncomeDeleteBP {

	public void delete(AggInvestIncomeVO[] bills) {

		DeleteBPTemplate<AggInvestIncomeVO> bp = new DeleteBPTemplate<AggInvestIncomeVO>(
				InvestIncomePluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggInvestIncomeVO> processer) {
		// TODO ǰ����
		IRule<AggInvestIncomeVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggInvestIncomeVO> processer) {
		// TODO �����

	}
}
