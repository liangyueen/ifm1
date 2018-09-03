package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.redeem.plugin.bpplugin.InvestRedeemPluginPoint;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.itf.ifm.IInvestRedeemMaintain;

public class N_3642_UNAPPROVE extends AbstractPfAction<AggInvestRedeemVO> {

	@Override
	protected CompareAroundProcesser<AggInvestRedeemVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggInvestRedeemVO> processor = new CompareAroundProcesser<AggInvestRedeemVO>(
				InvestRedeemPluginPoint.UNAPPROVE);
		// TODO �ڴ˴����ǰ�����
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected AggInvestRedeemVO[] processBP(Object userObj,
			AggInvestRedeemVO[] clientFullVOs, AggInvestRedeemVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AggInvestRedeemVO[] bills = null;
		try {
			IInvestRedeemMaintain operator = NCLocator.getInstance()
					.lookup(IInvestRedeemMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
