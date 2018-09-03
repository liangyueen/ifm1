package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.redeem.plugin.bpplugin.InvestRedeemPluginPoint;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.itf.ifm.IInvestRedeemMaintain;

public class N_3642_UNSAVEBILL extends AbstractPfAction<AggInvestRedeemVO> {

	@Override
	protected CompareAroundProcesser<AggInvestRedeemVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggInvestRedeemVO> processor = new CompareAroundProcesser<AggInvestRedeemVO>(
				InvestRedeemPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected AggInvestRedeemVO[] processBP(Object userObj,
			AggInvestRedeemVO[] clientFullVOs, AggInvestRedeemVO[] originBills) {
		IInvestRedeemMaintain operator = NCLocator.getInstance().lookup(
				IInvestRedeemMaintain.class);
		AggInvestRedeemVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
