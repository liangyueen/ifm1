package nc.bs.ifm.apply.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ifm.apply.AggInvestApplyVO;

public class InvestApplyUnApproveTbbRule implements IRule<AggInvestApplyVO>{

	@Override
	public void process(AggInvestApplyVO[] vos) {
//		for (AggInvestApplyVO aggvo : vos) {
//			try {
//				Integer vbillstatus = aggvo.getParentVO().getVbillstatus();
//				for (AggInvestApplyVO aggBankProtocolVO : vos) {
//					String pk_bankprotocol = aggvo.getParentVO().getPk_apply();
//					String pk_oribankprotocol = aggBankProtocolVO.getParentVO().getPk_apply();
//					
//					if(!StringUtil.isEmptyWithTrim(pk_bankprotocol)&&!StringUtil.isEmptyWithTrim(pk_oribankprotocol)&&pk_bankprotocol.equals(pk_oribankprotocol)){
//						Integer oldvbillstatus = aggBankProtocolVO.getParentVO().getVbillstatus();
//						if(!BillStatusEnum.APPROVED.value().equals(vbillstatus)&&BillStatusEnum.APPROVED.value().equals(oldvbillstatus)){
//							aggvo.getParentVO().setTbbmessage(null);
//							/**
//							 * ����Ԥ����ƣ���д�Ǽǵ�ִ����
//							 */
////							aggvo.getParentVO().setTbbmessage(IFMTbbCommonUtil.controlNtb(aggvo, true, aggvo.getParentVO().getBegindate(), IFMTbbCommonUtil.UFIND));
//							/**
//							 * ����Ԥ����ƣ��ͷ�Ԥռ��
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
