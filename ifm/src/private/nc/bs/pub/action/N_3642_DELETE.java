package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.redeem.plugin.bpplugin.InvestRedeemPluginPoint;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.itf.ifm.IInvestRedeemMaintain;

public class N_3642_DELETE extends AbstractPfAction<InvestRedeemVO> {

	@Override
	protected CompareAroundProcesser<InvestRedeemVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<InvestRedeemVO> processor = new CompareAroundProcesser<InvestRedeemVO>(
				InvestRedeemPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected InvestRedeemVO[] processBP(Object userObj,
			InvestRedeemVO[] clientFullVOs, InvestRedeemVO[] originBills) {
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
