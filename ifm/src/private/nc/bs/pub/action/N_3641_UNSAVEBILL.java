package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.itf.ifm.IApplyMaintain;

public class N_3641_UNSAVEBILL extends AbstractPfAction<AggInvestApplyVO> {

	@Override
	protected CompareAroundProcesser<AggInvestApplyVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggInvestApplyVO> processor = new CompareAroundProcesser<AggInvestApplyVO>(
				ApplyPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected AggInvestApplyVO[] processBP(Object userObj,
			AggInvestApplyVO[] clientFullVOs, AggInvestApplyVO[] originBills) {
		IApplyMaintain operator = NCLocator.getInstance().lookup(
				IApplyMaintain.class);
		AggInvestApplyVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
