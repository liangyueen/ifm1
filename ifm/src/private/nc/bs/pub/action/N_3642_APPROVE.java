package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.redeem.plugin.bpplugin.InvestRedeemPluginPoint;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.itf.ifm.IInvestRedeemMaintain;

public class N_3642_APPROVE extends AbstractPfAction<AggInvestRedeemVO> {

	public N_3642_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggInvestRedeemVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggInvestRedeemVO> processor = new CompareAroundProcesser<AggInvestRedeemVO>(
				InvestRedeemPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggInvestRedeemVO[] processBP(Object userObj,
			AggInvestRedeemVO[] clientFullVOs, AggInvestRedeemVO[] originBills) {
		AggInvestRedeemVO[] bills = null;
		IInvestRedeemMaintain operator = NCLocator.getInstance().lookup(
				IInvestRedeemMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
