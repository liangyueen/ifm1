package nccloud.web.ifm.redeem.action;

import nc.itf.ifm.IInvestRedeemQueryService;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.service.ServiceLocator;
import nccloud.web.ifm.common.action.CommonQueryCardAction;
import nccloud.base.exception.ExceptionUtils;
import nc.bs.logging.Logger;
public class RedeemCardQryAction extends CommonQueryCardAction<AggInvestRedeemVO> {

	@Override
	protected AggInvestRedeemVO[] queryBillsByPk(String pk) {
		AggInvestRedeemVO[] vos=null;
		try {
			IInvestRedeemQueryService service=ServiceLocator.find(IInvestRedeemQueryService.class);
			vos=service.queryRedeemByPks(new String[]{pk});
		} catch (BusinessException e) {
			Logger.error(e.getMessage(), e);
			ExceptionUtils.wrapBusinessException(e.getMessage(), e);
		}
		return vos;
	}

}
