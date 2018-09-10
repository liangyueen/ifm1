package nccloud.web.ifm.income.action;

import nc.itf.ifm.IInvestIncomeQueryService;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.service.ServiceLocator;
import nccloud.web.ifm.common.action.CommonQueryCardAction;

public class IncomeCardQueryAction extends CommonQueryCardAction<AggInvestIncomeVO> {

	@Override
	protected AggInvestIncomeVO[] queryBillsByPk(String pk) {
		AggInvestIncomeVO[] vos=null;
		try {
			IInvestIncomeQueryService service=ServiceLocator.find(IInvestIncomeQueryService.class);
			vos=service.queryIncomeByPks(new String[]{pk});
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vos;
	}

}
