package nc.bs.ifm.redeem.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ifm.pub.tbb.IFMTbbCommonUtil;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.pf.BillStatusEnum;

public class InvestRedeemUnApproveTbbRule implements IRule<AggInvestRedeemVO>{

	@Override
	public void process(AggInvestRedeemVO[] vos) {
//		for (AggInvestRedeemVO aggvo : vos) {
//			try {
//				Integer vbillstatus = aggvo.getParentVO().getVBillstatus();
//				for (AggInvestRedeemVO aggBankProtocolVO : vos) {
//					String pk_bankprotocol = aggvo.getParentVO().getPk_redeem();
//					String pk_oribankprotocol = aggBankProtocolVO.getParentVO().getPk_redeem();
//					
//					if(!StringUtil.isEmptyWithTrim(pk_bankprotocol)&&!StringUtil.isEmptyWithTrim(pk_oribankprotocol)&&pk_bankprotocol.equals(pk_oribankprotocol)){
//						Integer oldvbillstatus = aggBankProtocolVO.getParentVO().getVBillstatus();
//						if(!BillStatusEnum.APPROVED.value().equals(vbillstatus)&&BillStatusEnum.APPROVED.value().equals(oldvbillstatus)){
//							aggvo.getParentVO().setTbbmessage(null);
//							/**
//							 * 进行预算控制，回写登记单执行数
//							 */
////							aggvo.getParentVO().setTbbmessage(IFMTbbCommonUtil.controlNtb(aggvo, true, aggvo.getParentVO().getBegindate(), IFMTbbCommonUtil.UFIND));
//							/**
//							 * 进行预算控制，释放预占数
//							 */
//							aggvo.getParentVO().setTbbmessage(IFMTbbCommonUtil.controlNtb(aggvo, false, new UFDate(), IFMTbbCommonUtil.PREFIND));
//						}
//						break;
//					}
//				}
//			} catch (BusinessException e) {
//				throw new BusinessRuntimeException(e.getMessage(), e);
//			}
//		}	
	}

}
