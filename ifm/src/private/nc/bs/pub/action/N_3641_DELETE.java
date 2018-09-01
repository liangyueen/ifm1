package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.itf.ifm.IApplyMaintain;

public class N_3641_DELETE extends AbstractPfAction<InvestApplyVO> {

	@Override
	protected CompareAroundProcesser<InvestApplyVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<InvestApplyVO> processor = new CompareAroundProcesser<InvestApplyVO>(
				ApplyPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected InvestApplyVO[] processBP(Object userObj,
			InvestApplyVO[] clientFullVOs, InvestApplyVO[] originBills) {
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
