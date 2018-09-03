package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.itf.ifm.IApplyMaintain;

public class N_3641_SAVE extends AbstractPfAction<AggInvestApplyVO> {

	protected CompareAroundProcesser<AggInvestApplyVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggInvestApplyVO> processor = new CompareAroundProcesser<AggInvestApplyVO>(
				ApplyPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<AggInvestApplyVO> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggInvestApplyVO[] processBP(Object userObj,
			AggInvestApplyVO[] clientFullVOs, AggInvestApplyVO[] originBills) {
		IApplyMaintain operator = NCLocator.getInstance().lookup(
				IApplyMaintain.class);
		AggInvestApplyVO[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
