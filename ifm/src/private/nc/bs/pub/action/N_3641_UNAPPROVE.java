package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.UnapproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.itf.ifm.IApplyMaintain;

public class N_3641_UNAPPROVE extends AbstractPfAction<InvestApplyVO> {

	@Override
	protected CompareAroundProcesser<InvestApplyVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<InvestApplyVO> processor = new CompareAroundProcesser<InvestApplyVO>(
				ApplyPluginPoint.UNAPPROVE);
		// TODO 在此处添加前后规则
		processor.addBeforeRule(new UnapproveStatusCheckRule());

		return processor;
	}

	@Override
	protected InvestApplyVO[] processBP(Object userObj,
			InvestApplyVO[] clientFullVOs, InvestApplyVO[] originBills) {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		InvestApplyVO[] bills = null;
		try {
			IApplyMaintain operator = NCLocator.getInstance()
					.lookup(IApplyMaintain.class);
			bills = operator.unapprove(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
