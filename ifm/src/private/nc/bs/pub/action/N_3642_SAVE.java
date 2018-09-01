package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.redeem.plugin.bpplugin.InvestRedeemPluginPoint;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.itf.ifm.IInvestRedeemMaintain;

public class N_3642_SAVE extends AbstractPfAction<InvestRedeemVO> {

	protected CompareAroundProcesser<InvestRedeemVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<InvestRedeemVO> processor = new CompareAroundProcesser<InvestRedeemVO>(
				InvestRedeemPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<InvestRedeemVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected InvestRedeemVO[] processBP(Object userObj,
			InvestRedeemVO[] clientFullVOs, InvestRedeemVO[] originBills) {
		IInvestRedeemMaintain operator = NCLocator.getInstance().lookup(
				IInvestRedeemMaintain.class);
		InvestRedeemVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
