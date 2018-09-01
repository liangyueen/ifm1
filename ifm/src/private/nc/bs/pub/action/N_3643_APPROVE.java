package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.income.plugin.bpplugin.InvestIncomePluginPoint;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.itf.ifm.IInvestIncomeMaintain;

public class N_3643_APPROVE extends AbstractPfAction<InvestIncomeVO> {

	public N_3643_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<InvestIncomeVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<InvestIncomeVO> processor = new CompareAroundProcesser<InvestIncomeVO>(
				InvestIncomePluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected InvestIncomeVO[] processBP(Object userObj,
			InvestIncomeVO[] clientFullVOs, InvestIncomeVO[] originBills) {
		InvestIncomeVO[] bills = null;
		IInvestIncomeMaintain operator = NCLocator.getInstance().lookup(
				IInvestIncomeMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
