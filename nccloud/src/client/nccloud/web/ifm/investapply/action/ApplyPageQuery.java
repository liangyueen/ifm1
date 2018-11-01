package nccloud.web.ifm.investapply.action;

import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.investapply.util.ApplyQueryUtil;
import nc.vo.imf.constants.TMIMFConst;
import nccloud.web.tmifm.common.action.CommonQueryByPksAction;


public class ApplyPageQuery extends CommonQueryByPksAction<AggInvestApplyVO>{


	@Override
	protected AggInvestApplyVO[] queryBillsByPks(String[] currPagePks) {
		AggInvestApplyVO[] vos=ApplyQueryUtil.getApplyVO(currPagePks);
		return vos;
	}

	@Override
	protected String getAreaCode() {
		return TMIMFConst.CONST_AREACODE_ADJUST_LIST_TABLE;
	}

}