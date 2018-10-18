package nc.bs.ifm.redeem.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ifm.redeem.AggInvestRedeemVO;

public class InvestRedeemApproveTbbRule implements IRule<AggInvestRedeemVO>{

	@Override
	public void process(AggInvestRedeemVO[] vos) {
//		for (AggInvestRedeemVO aggvo : vos) {
//			try {
//				Integer vbillstatus = aggvo.getParentVO().getVBillstatus();
//				if(vbillstatus.equals(BillStatusEnum.APPROVED.value())){
//					/**
//					 * 进行预算控制，释放预占数
//					 */
////					aggvo.getParentVO().setTbbmessage(null);
////					aggvo.getParentVO().setTbbmessage(IFMTbbCommonUtil.controlNtb(aggvo, true, aggvo.getParentVO().getBegindate(), IFMTbbCommonUtil.PREFIND));
//					/**
//					 * 进行预算控制，回写登记单执行数
//					 */
//					aggvo.getParentVO().setTbbmessage(IFMTbbCommonUtil.controlNtb(aggvo, false, new UFDate(), IFMTbbCommonUtil.UFIND));
//				}
//			} catch (BusinessException e) {
//				throw new BusinessRuntimeException(e.getMessage(), e);
//			}
//		}
	}

}
