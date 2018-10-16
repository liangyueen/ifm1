package nccloud.web.ifm.income.action;

import nc.bs.logging.Logger;
import nc.itf.ifm.IInvestIncomeQueryService;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.tmpub.util.StringUtil;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.IRequest;
import nccloud.ifm.vo.OperatorParam;
import nccloud.pubitf.ifm.pub.IIFMPubLinkPlanService4NCC;
import nccloud.web.ifm.common.action.AbstractCommonAction;

import com.alibaba.fastjson.JSONObject;

/**
 * 联查预算计划动作
 * 
 * @author tangleic
 * @date 20180802
 * @version 1.0
 * 
 */
public class IncomeLinkNtbPlanAction extends AbstractCommonAction<AggInvestIncomeVO> {
	@Override
	public Object doAction(IRequest request) {
		JSONObject jsonObject = null;
		try {
			OperatorParam param = (OperatorParam) getReqData(request);
			String pk = param.getPk();
			if (StringUtil.isNull(pk)) {
				throw new BusinessException("前端请求参数缺失[pk]不能为空！");
			}
			jsonObject = getFTSNtbService().linkqueryPlan(pk);
//			jsonObject = getFTSNtbService().linkNtbPlan(
//					AggInvestIncomeVO.class.getName(), pk,
//					TMIFMConst.CONST_BILLTYPE_INCOME);
		} catch (BusinessException e) {
			Logger.error(e);
			ExceptionUtils.wrapException(e);
		}
		return jsonObject;
	}

	@Override
	protected Class<?> getReqDataClass() {
		// TODO Auto-generated method stub
		return OperatorParam.class;
	}

	/**
	 * 获取联查预算计划服务
	 * 
	 * @return
	 */
	private IInvestIncomeQueryService getFTSNtbService() {
		return ServiceLocator.find(IInvestIncomeQueryService.class);
	}
//	private IIFMPubLinkPlanService4NCC getFTSNtbService() {
//		return ServiceLocator.find(IIFMPubLinkPlanService4NCC.class);
//	}
}
