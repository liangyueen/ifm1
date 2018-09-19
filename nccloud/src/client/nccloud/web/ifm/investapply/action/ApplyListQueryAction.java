package nccloud.web.ifm.investapply.action;

import java.util.Map;

import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.imf.constants.TMIMFConst;
import nccloud.dto.baseapp.querytree.dataformat.QueryTreeFormatVO;
import nccloud.web.ifm.investapply.util.ApplyQueryUtil;
import nccloud.web.tmifm.common.action.CommonListQueryAction;

public class ApplyListQueryAction extends CommonListQueryAction{
	
	@Override
	protected AggInvestApplyVO[] queryBillsByPks(String[] currPagePks) {
		AggInvestApplyVO[] vos=ApplyQueryUtil.getApplyVO(currPagePks);
		return vos;
	}

	@Override
	protected Class getVOClass() {
		return InvestApplyVO.class;
	}

	@Override
	protected String getAreaCode() {
		return TMIMFConst.CONST_AREACODE_ADJUST_LIST_SEARCH;
	}

	@Override
	protected Map<String, String> queryGroupData(QueryTreeFormatVO queryParam) {
		Map<String, String> map=ApplyQueryUtil.getGroupData(queryParam);
		return map;
	}
}
