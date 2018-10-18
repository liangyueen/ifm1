package nc.bs.ifm.income.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.tm.pub.TMPublicUtil;

public class IncomeCheckRule implements IRule<AggInvestIncomeVO> {

	@Override
	public void process(AggInvestIncomeVO[] vos) {
		check(vos);
	}

	private void check(AggInvestIncomeVO[] vos) {
		for (AggInvestIncomeVO bill : vos) {
			InvestIncomeVO vo = bill.getParentVO();

			/** (һ)��֯״̬У�飺 ������֯ͣ��У�� **/
			new TMPublicUtil().verifyFinanceOrgVODOCEnable(vo.getPk_org());
		}
	}

	
}
