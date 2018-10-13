package nccloud.web.ifm.common.action;

import java.util.Map;

import nc.bs.logging.Logger;
import nc.vo.fts.pub.bean.ListQryParam;
import nc.vo.fts.pub.bean.ListQryResult;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.tmpub.util.StringUtil;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.container.IRequest;
import nccloud.ifm.vo.OperatorParam;
import nccloud.web.fts.ui.pattern.grid.TMGrid;

/**
 * 自动加载动作
 * 
 * @author tangleic
 * @date 2018918
 * @version 1.0
 * 
 * @param <T>
 *            业务聚合VO
 */
public abstract class AbstrAutoLoadDataAction<T extends AbstractBill> extends
		AbstrCommListQryAction<T> {
	@Override
	public Object doAction(IRequest request) {
		TMGrid tmGrid = null;
		try {
			// 获取请求参数
			OperatorParam param = (OperatorParam) getReqData(request);
			String qryDataStr = param.getData();
			if (StringUtil.isNull(qryDataStr)) {
				throw new BusinessException("请求数据缺失参数[data]不能为空！");
			}
			String pageCode = param.getPageCode();
			if (StringUtil.isNull(pageCode)) {
				throw new BusinessException("请求数据缺失参数[pageCode]页面编码不能为空！");
			}
			Map<String, String> extParam = param.getExtParam();
			// 构建列表查询参数
			ListQryParam qryParam = buildListQryParam(qryDataStr);
			// 业务查询
			ListQryResult<T> result = query(qryParam, extParam);
			// 构建查询结果
			tmGrid = buildQryResult(result, pageCode);
		} catch (BusinessException e) {
			Logger.error(e);
			ExceptionUtils.wrapException(e);
		}
		return tmGrid;
	}

	/**
	 * 业务查询
	 * 
	 * @param param
	 *            列表查询参数
	 * @param extParam
	 *            拓展属性
	 * @throws BusinessException
	 */
	protected abstract ListQryResult<T> query(ListQryParam param,
			Map<String, String> extParam) throws BusinessException;

	@Override
	protected ListQryResult<T> query(ListQryParam param)
			throws BusinessException {
		return null;
	}
}
