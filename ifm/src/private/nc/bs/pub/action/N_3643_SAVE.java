package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.income.plugin.bpplugin.InvestIncomePluginPoint;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.itf.ifm.IInvestIncomeMaintain;

public class N_3643_SAVE extends AbstractPfAction<AggInvestIncomeVO> {

	protected CompareAroundProcesser<AggInvestIncomeVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggInvestIncomeVO> processor = new CompareAroundProcesser<AggInvestIncomeVO>(
				InvestIncomePluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
//		IRule<AggInvestIncomeVO> rule = new CommitStatusCheckRule();
//		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggInvestIncomeVO[] processBP(Object userObj,
			AggInvestIncomeVO[] clientFullVOs, AggInvestIncomeVO[] originBills) {
		IInvestIncomeMaintain operator = NCLocator.getInstance().lookup(
				IInvestIncomeMaintain.class);
		AggInvestIncomeVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
