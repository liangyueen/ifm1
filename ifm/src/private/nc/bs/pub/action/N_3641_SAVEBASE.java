package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.itf.ifm.IApplyMaintain;

public class N_3641_SAVEBASE extends AbstractPfAction<InvestApplyVO> {

	@Override
	protected CompareAroundProcesser<InvestApplyVO> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<InvestApplyVO> processor = null;
		InvestApplyVO[] clientFullVOs = (InvestApplyVO[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<InvestApplyVO>(
					ApplyPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<InvestApplyVO>(
					ApplyPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<InvestApplyVO> rule = null;

		return processor;
	}

	@Override
	protected InvestApplyVO[] processBP(Object userObj,
			InvestApplyVO[] clientFullVOs, InvestApplyVO[] originBills) {

		InvestApplyVO[] bills = null;
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
