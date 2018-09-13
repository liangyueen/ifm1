package nccloud.web.ifm.redeem.action;

import nc.itf.ifm.IInvestRedeemQueryService;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.service.ServiceLocator;
import nccloud.web.ifm.common.action.CommonQueryCardAction;

public class RedeemCardQryAction extends CommonQueryCardAction<AggInvestRedeemVO> {

	@Override
	protected AggInvestRedeemVO[] queryBillsByPk(String pk) {
		AggInvestRedeemVO[] vos=null;
		try {
			IInvestRedeemQueryService service=ServiceLocator.find(IInvestRedeemQueryService.class);
			vos=service.queryRedeemByPks(new String[]{pk});
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vos;
	}

}
