package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.redeem.plugin.bpplugin.InvestRedeemPluginPoint;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.itf.ifm.IInvestRedeemMaintain;

public class N_3642_SAVEBASE extends AbstractPfAction<InvestRedeemVO> {

	@Override
	protected CompareAroundProcesser<InvestRedeemVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<InvestRedeemVO> processor = null;
		InvestRedeemVO[] clientFullVOs = (InvestRedeemVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<InvestRedeemVO>(
					InvestRedeemPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<InvestRedeemVO>(
					InvestRedeemPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<InvestRedeemVO> rule = null;

		return processor;
	}

	@Override
	protected InvestRedeemVO[] processBP(Object userObj,
			InvestRedeemVO[] clientFullVOs, InvestRedeemVO[] originBills) {

		InvestRedeemVO[] bills = null;
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
