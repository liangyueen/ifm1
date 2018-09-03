package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.redeem.plugin.bpplugin.InvestRedeemPluginPoint;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.itf.ifm.IInvestRedeemMaintain;

public class N_3642_SAVEBASE extends AbstractPfAction<AggInvestRedeemVO> {

	@Override
	protected CompareAroundProcesser<AggInvestRedeemVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggInvestRedeemVO> processor = null;
		AggInvestRedeemVO[] clientFullVOs = (AggInvestRedeemVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggInvestRedeemVO>(
					InvestRedeemPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggInvestRedeemVO>(
					InvestRedeemPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggInvestRedeemVO> rule = null;

		return processor;
	}

	@Override
	protected AggInvestRedeemVO[] processBP(Object userObj,
			AggInvestRedeemVO[] clientFullVOs, AggInvestRedeemVO[] originBills) {

		AggInvestRedeemVO[] bills = null;
		try {
			IInvestRedeemMaintain operator = NCLocator.getInstance()
					.lookup(IInvestRedeemMaintain.class);
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
