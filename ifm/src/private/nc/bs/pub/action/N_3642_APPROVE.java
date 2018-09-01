package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.redeem.plugin.bpplugin.InvestRedeemPluginPoint;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.itf.ifm.IInvestRedeemMaintain;

public class N_3642_APPROVE extends AbstractPfAction<InvestRedeemVO> {

	public N_3642_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<InvestRedeemVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<InvestRedeemVO> processor = new CompareAroundProcesser<InvestRedeemVO>(
				InvestRedeemPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected InvestRedeemVO[] processBP(Object userObj,
			InvestRedeemVO[] clientFullVOs, InvestRedeemVO[] originBills) {
		InvestRedeemVO[] bills = null;
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
