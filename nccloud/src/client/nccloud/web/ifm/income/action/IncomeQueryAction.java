package nccloud.web.ifm.income.action;

import nc.itf.ifm.IInvestIncomeQueryService;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nccloud.framework.service.ServiceLocator;
import nccloud.web.ifm.common.action.CommonListQueryAction;

public class IncomeQueryAction extends CommonListQueryAction<AggInvestIncomeVO> {

	@Override
	protected AggInvestIncomeVO[] queryBillsByPks(String[] currPagePks) {
		AggInvestIncomeVO[] vos=null;
		try {
			IInvestIncomeQueryService service=ServiceLocator.find(IInvestIncomeQueryService.class);
			vos=service.queryIncomeByPks(currPagePks);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vos;
	}

	@Override
	protected Class<? extends SuperVO> getVOClass() {
		return InvestIncomeVO.class;
	}

	@Override
	protected String getAreaCode() {
		return TMIFMConst.CONST_AREACODE_INCOME_LIST_TABLE;
	}

}
