package nc.bs.ifm.apply.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.pub.tbb.IFMTbbCommonUtil;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.pf.BillStatusEnum;

public class InvestApplyUnApproveTbbRule implements IRule<AggInvestApplyVO>{

	@Override
	public void process(AggInvestApplyVO[] vos) {
		for (AggInvestApplyVO aggvo : vos) {
			try {
				Integer vbillstatus = aggvo.getParentVO().getVbillstatus();
				for (AggInvestApplyVO aggBankProtocolVO : vos) {
					String pk_bankprotocol = aggvo.getParentVO().getPk_apply();
					String pk_oribankprotocol = aggBankProtocolVO.getParentVO().getPk_apply();
					
					if(!StringUtil.isEmptyWithTrim(pk_bankprotocol)&&!StringUtil.isEmptyWithTrim(pk_oribankprotocol)&&pk_bankprotocol.equals(pk_oribankprotocol)){
						Integer oldvbillstatus = aggBankProtocolVO.getParentVO().getVbillstatus();
						if(!BillStatusEnum.APPROVED.value().equals(vbillstatus)&&BillStatusEnum.APPROVED.value().equals(oldvbillstatus)){
							aggvo.getParentVO().setTbbmessage(null);
							/**
							 * 进行预算控制，回写登记单执行数
							 */
//							aggvo.getParentVO().setTbbmessage(IFMTbbCommonUtil.controlNtb(aggvo, true, aggvo.getParentVO().getBegindate(), IFMTbbCommonUtil.UFIND));
							/**
							 * 进行预算控制，释放预占数
							 */
							aggvo.getParentVO().setTbbmessage(IFMTbbCommonUtil.controlNtb(aggvo, false, new UFDate(), IFMTbbCommonUtil.PREFIND));
						}
						break;
					}
				}
			} catch (BusinessException e) {
				throw new BusinessRuntimeException(e.getMessage(), e);
			}
		}	
	}

}
