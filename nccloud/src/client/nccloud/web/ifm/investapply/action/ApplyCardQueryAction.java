package nccloud.web.ifm.investapply.action;

import nc.itf.ifm.IInvestApplyQueryService;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nccloud.framework.service.ServiceLocator;
import nccloud.web.tmifm.common.action.CommonQueryCardAction;
import nc.vo.pub.BusinessException;

public class ApplyCardQueryAction extends CommonQueryCardAction<AggInvestApplyVO> {

	@Override
	protected AggInvestApplyVO[] queryBillsByPk(String pk) {
		AggInvestApplyVO[] vos=null;
		try {
			IInvestApplyQueryService service=ServiceLocator.find(IInvestApplyQueryService.class);
			vos=service.queryApplyByPks(new String[]{pk});
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vos;
	}

}