package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.income.plugin.bpplugin.InvestIncomePluginPoint;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.itf.ifm.IInvestIncomeMaintain;

public class N_3643_DELETE extends AbstractPfAction<InvestIncomeVO> {

	@Override
	protected CompareAroundProcesser<InvestIncomeVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<InvestIncomeVO> processor = new CompareAroundProcesser<InvestIncomeVO>(
				InvestIncomePluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected InvestIncomeVO[] processBP(Object userObj,
			InvestIncomeVO[] clientFullVOs, InvestIncomeVO[] originBills) {
		IInvestIncomeMaintain operator = NCLocator.getInstance().lookup(
				IInvestIncomeMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
