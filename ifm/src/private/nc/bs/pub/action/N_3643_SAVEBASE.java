package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.income.plugin.bpplugin.InvestIncomePluginPoint;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.itf.ifm.IInvestIncomeMaintain;

public class N_3643_SAVEBASE extends AbstractPfAction<InvestIncomeVO> {

	@Override
	protected CompareAroundProcesser<InvestIncomeVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<InvestIncomeVO> processor = null;
		InvestIncomeVO[] clientFullVOs = (InvestIncomeVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<InvestIncomeVO>(
					InvestIncomePluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<InvestIncomeVO>(
					InvestIncomePluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<InvestIncomeVO> rule = null;

		return processor;
	}

	@Override
	protected InvestIncomeVO[] processBP(Object userObj,
			InvestIncomeVO[] clientFullVOs, InvestIncomeVO[] originBills) {

		InvestIncomeVO[] bills = null;
		try {
			IInvestIncomeMaintain operator = NCLocator.getInstance()
					.lookup(IInvestIncomeMaintain.class);
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
