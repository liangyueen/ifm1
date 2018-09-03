package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.itf.ifm.IApplyMaintain;

public class N_3641_DELETE extends AbstractPfAction<AggInvestApplyVO> {

	@Override
	protected CompareAroundProcesser<AggInvestApplyVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggInvestApplyVO> processor = new CompareAroundProcesser<AggInvestApplyVO>(
				ApplyPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggInvestApplyVO[] processBP(Object userObj,
			AggInvestApplyVO[] clientFullVOs, AggInvestApplyVO[] originBills) {
		IApplyMaintain operator = NCLocator.getInstance().lookup(
				IApplyMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
