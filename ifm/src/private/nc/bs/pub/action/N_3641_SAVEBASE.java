package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.itf.ifm.IApplyMaintain;

public class N_3641_SAVEBASE extends AbstractPfAction<AggInvestApplyVO> {

	@Override
	protected CompareAroundProcesser<AggInvestApplyVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggInvestApplyVO> processor = null;
		AggInvestApplyVO[] clientFullVOs = (AggInvestApplyVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggInvestApplyVO>(
					ApplyPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggInvestApplyVO>(
					ApplyPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggInvestApplyVO> rule = null;

		return processor;
	}

	@Override
	protected AggInvestApplyVO[] processBP(Object userObj,
			AggInvestApplyVO[] clientFullVOs, AggInvestApplyVO[] originBills) {

		AggInvestApplyVO[] bills = null;
		try {
			IApplyMaintain operator = NCLocator.getInstance()
					.lookup(IApplyMaintain.class);
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
