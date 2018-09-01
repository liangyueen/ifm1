package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UncommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.itf.ifm.IApplyMaintain;

public class N_3641_UNSAVEBILL extends AbstractPfAction<InvestApplyVO> {

	@Override
	protected CompareAroundProcesser<InvestApplyVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<InvestApplyVO> processor = new CompareAroundProcesser<InvestApplyVO>(
				ApplyPluginPoint.UNSEND_APPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UncommitStatusCheckRule());

		return processor;
	}

	@Override
	protected InvestApplyVO[] processBP(Object userObj,
			InvestApplyVO[] clientFullVOs, InvestApplyVO[] originBills) {
		IApplyMaintain operator = NCLocator.getInstance().lookup(
				IApplyMaintain.class);
		InvestApplyVO[] bills = null;
		try {
			bills = operator.unsave(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
