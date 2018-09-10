package nccloud.web.ifm.income.action;

import nc.itf.ifm.IInvestIncomeQuerypageService;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nccloud.framework.service.ServiceLocator;
import nccloud.web.ifm.common.action.CommonListQueryAction;
import nccloud.web.ifm.common.action.CommonQueryByPksAction;

public class IncomeQueryPageAction extends CommonQueryByPksAction<AggInvestIncomeVO> {

	@Override
	protected AggInvestIncomeVO[] queryBillsByPks(String[] currPagePks) {
		AggInvestIncomeVO[] vos=null;
		try {
			IInvestIncomeQuerypageService service=ServiceLocator.find(IInvestIncomeQuerypageService.class);
			vos=service.queryIncomeByPks(currPagePks);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vos;
	}

	@Override
	protected String getAreaCode() {
		return TMIFMConst.CONST_AREACODE_INCOME_LIST_TABLE;
	}

}
