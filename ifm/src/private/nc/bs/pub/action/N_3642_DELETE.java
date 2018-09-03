package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.redeem.plugin.bpplugin.InvestRedeemPluginPoint;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.itf.ifm.IInvestRedeemMaintain;

public class N_3642_DELETE extends AbstractPfAction<AggInvestRedeemVO> {

	@Override
	protected CompareAroundProcesser<AggInvestRedeemVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggInvestRedeemVO> processor = new CompareAroundProcesser<AggInvestRedeemVO>(
				InvestRedeemPluginPoint.SCRIPT_DELETE);
		// TODO �ڴ˴����ǰ�����
		return processor;
	}

	@Override
	protected AggInvestRedeemVO[] processBP(Object userObj,
			AggInvestRedeemVO[] clientFullVOs, AggInvestRedeemVO[] originBills) {
		IInvestRedeemMaintain operator = NCLocator.getInstance().lookup(
				IInvestRedeemMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
