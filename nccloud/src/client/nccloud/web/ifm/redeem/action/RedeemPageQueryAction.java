package nccloud.web.ifm.redeem.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.scmpub.page.PageQueryVO;
import nc.voifm.ifm.util.RedeemUtil;
import nccloud.dto.baseapp.querytree.dataformat.QueryTreeFormatVO;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.core.json.IJson;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.ui.model.GridModel;
import nccloud.framework.web.ui.model.PageInfo;
import nccloud.framework.web.ui.pattern.grid.Grid;
import nccloud.framework.web.ui.pattern.grid.GridOperator;
import nccloud.pubitf.platform.query.INCCloudQueryService;
import nccloud.web.ifm.common.action.CommonQueryByPksAction;
/**
 * ≤È—Ø
 * @author suxch
 */
public class RedeemPageQueryAction extends CommonQueryByPksAction<AggInvestRedeemVO> {
	
	@Override
	protected AggInvestRedeemVO[] queryBillsByPks(String[] currPagePks) {
		AggInvestRedeemVO[] vos=RedeemUtil.getRedeemVO(currPagePks);
		return vos;
	}

	@Override
	protected String getAreaCode() {
		return TMIFMConst.CONST_AREACODE_REDEEM_LIST_TABLE;
	}

}
