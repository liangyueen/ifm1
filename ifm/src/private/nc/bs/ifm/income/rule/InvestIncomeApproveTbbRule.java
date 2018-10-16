package nc.bs.ifm.income.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.pub.tbb.IFMTbbCommonUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.pf.BillStatusEnum;

public class InvestIncomeApproveTbbRule implements IRule<AggInvestIncomeVO>{

	@Override
	public void process(AggInvestIncomeVO[] vos) {
		for (AggInvestIncomeVO aggvo : vos) {
			try {
				Integer vbillstatus = aggvo.getParentVO().getVbillstatus();
				if(vbillstatus.equals(BillStatusEnum.APPROVED.value())){
					/**
					 * 进行预算控制，释放预占数
					 */
//					aggvo.getParentVO().setTbbmessage(null);
//					aggvo.getParentVO().setTbbmessage(IFMTbbCommonUtil.controlNtb(aggvo, true, aggvo.getParentVO().getBegindate(), IFMTbbCommonUtil.PREFIND));
					/**
					 * 进行预算控制，回写登记单执行数
					 */
					aggvo.getParentVO().setTbbmessage(IFMTbbCommonUtil.controlNtb(aggvo, false, new UFDate(), IFMTbbCommonUtil.UFIND));
				}
			} catch (BusinessException e) {
				throw new BusinessRuntimeException(e.getMessage(), e);
			}
		}
	}

}
