package nccloud.web.ifm.investapply.action;

import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.imf.constants.TMIMFConst;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nccloud.web.ifm.investapply.util.ApplyQueryUtil;
import nccloud.web.tmifm.common.action.CommonQueryByPksAction;

public class ApplyQueryByPksAction extends CommonQueryByPksAction{

	@Override
	protected AggInvestApplyVO[] queryBillsByPks(String[] currPagePks) {
		
		return ApplyQueryUtil.getApplyVO(currPagePks);
	}

	@Override
	protected String getAreaCode() {
		return TMIMFConst.CONST_AREACODE_ADJUST_LIST_SEARCH;
	}

}
