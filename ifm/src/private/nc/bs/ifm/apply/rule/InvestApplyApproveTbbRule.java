package nc.bs.ifm.apply.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.pub.tbb.IFMTbbCommonUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.pf.BillStatusEnum;

public class InvestApplyApproveTbbRule implements IRule<AggInvestApplyVO>{

	@Override
	public void process(AggInvestApplyVO[] vos) {
		for (AggInvestApplyVO aggvo : vos) {
			try {
				Integer vbillstatus = aggvo.getParentVO().getVbillstatus();
				if(vbillstatus.equals(BillStatusEnum.APPROVED.value())){
					/**
					 * ����Ԥ����ƣ��ͷ�Ԥռ��
					 */
//					aggvo.getParentVO().setTbbmessage(null);
//					aggvo.getParentVO().setTbbmessage(IFMTbbCommonUtil.controlNtb(aggvo, true, aggvo.getParentVO().getBegindate(), IFMTbbCommonUtil.PREFIND));
					/**
					 * ����Ԥ����ƣ���д�Ǽǵ�ִ����
					 */
					aggvo.getParentVO().setTbbmessage(IFMTbbCommonUtil.controlNtb(aggvo, false, new UFDate(), IFMTbbCommonUtil.UFIND));
				}
			} catch (BusinessException e) {
				throw new BusinessRuntimeException(e.getMessage(), e);
			}
		}
	}

}
