package nccloud.web.ifm.common.action;

import nc.bs.logging.Logger;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.fts.pub.bean.ListQryParam;
import nc.vo.fts.pub.bean.ListQryResult;
import nc.vo.ifm.TMGrid;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.tmpub.util.ArrayUtil;
import nc.vo.tmpub.util.StringUtil;
import nccloud.dto.baseapp.querytree.dataformat.CustCondition;
import nccloud.dto.baseapp.querytree.dataformat.QueryCondition;
import nccloud.dto.baseapp.querytree.dataformat.QueryTreeFormatVO;
import nccloud.dto.fts.common.bean.OperatorParam;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.core.json.IJson;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.ui.model.PageInfo;
import nccloud.framework.web.ui.pattern.grid.Grid;
import nccloud.pubitf.platform.query.INCCloudQueryService;

/**
 * 列表查询公共动作
 * 
 * @author tangleic
 * @date 20180713
 * @version 1.0
 * 
 * @param <T>
 *            聚合VO类型
 */
public abstract class AbstrCommListQryAction<T extends AbstractBill> extends
		AbstractCommonAction<T> {

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
			// 构建列表查询参数
			ListQryParam qryParam = buildListQryParam(qryDataStr);
			// 业务查询
			ListQryResult<T> result = query(qryParam);
			// 构建查询结果
			tmGrid = buildQryResult(result, pageCode);
		} catch (BusinessException e) {
			String errMess = "列表查询时出错！"
					+ (e.getMessage() == null ? "" : e.getMessage());
			Logger.error(errMess, e);
			ExceptionUtils.wrapException(errMess, e);
		}
		return tmGrid;
	}

	@Override
	protected Class<?> getReqDataClass() {
		// TODO Auto-generated method stub
		return OperatorParam.class;
	}

	/**
	 * 获取查询方案
	 * 
	 * @param QueryTreeFormatVO
	 *            查询数据模型
	 * @return 查询方案
	 * @throws BusinessException
	 */
	private IQueryScheme getQueryScheme(QueryTreeFormatVO info)
			throws BusinessException {
		if (info.getQuerycondition() == null) {
			info.setQuerycondition(new QueryCondition());
			return null;
		}
		INCCloudQueryService qservice = ServiceLocator
				.find(INCCloudQueryService.class);
		IQueryScheme queryScheme = qservice.convertCondition(info);
		if (queryScheme == null) {
			throw new BusinessException("查询方案为空！");
		}
		return queryScheme;
	}

	/**
	 * 构建查询方案
	 * 
	 * @param queryVO
	 *            查询数据模型
	 * @throws BusinessException
	 */
	private IQueryScheme buildQueryScheme(QueryTreeFormatVO queryVO)
			throws BusinessException {
		CustCondition custCondition = queryVO.getCustcondition();
		if (custCondition == null
				|| ArrayUtil.isNull(custCondition.getConditions())) {
			return getQueryScheme(queryVO);
		}
		queryVO.getQuerycondition()
				.setConditions(custCondition.getConditions());
		queryVO.getQuerycondition().setLogic(custCondition.getLogic());
		IQueryScheme custScheme = getQueryScheme(queryVO);
		return custScheme;
	}

	/**
	 * 构建列表查询参数
	 * 
	 * @param info
	 *            前端请求数据
	 * @return 列表查询参数
	 * @throws BusinessException
	 */
	protected ListQryParam buildListQryParam(String querDataStr)
			throws BusinessException {
		IJson json = JsonFactory.create();
		QueryTreeFormatVO info = json.fromJson(querDataStr,
				QueryTreeFormatVO.class);
		IQueryScheme queryScheme = getQueryScheme(info);
		IQueryScheme custQryScheme = buildQueryScheme(info);
		if (info.getPageInfo() == null) {
			throw new BusinessException("前端请求分页信息为空！");
		}
		if (info.getPageInfo().getPageSize() == null) {
			throw new BusinessException("前端请求分页信息缺失参数[pageSize]！");
		}
		if (info.getPageInfo().getPageIndex() == null) {
			throw new BusinessException("前端请求分页信息缺失参数[pageIndex]！");
		}
		int pageSize = Integer.parseInt(info.getPageInfo().getPageSize());
		int pageIndex = Integer.parseInt(info.getPageInfo().getPageIndex());
		return new ListQryParam(pageSize, pageIndex, queryScheme, custQryScheme);
	}

	/**
	 * 业务查询
	 * 
	 * @param param
	 *            列表查询参数
	 * @throws BusinessException
	 */
	protected abstract ListQryResult<T> query(ListQryParam param)
			throws BusinessException;

	/**
	 * 构建分页数据
	 * 
	 * @param queryVO
	 * @param allSize
	 * @return
	 * @throws BusinessException
	 */
	private PageInfo buildPageInfo(ListQryResult<T> result)
			throws BusinessException {
		// 处理分页数据
		PageInfo pageInfo = new PageInfo();
		pageInfo.setTotal(result.getTotalNum());
		pageInfo.setPageSize(result.getPageSize());
		pageInfo.setPageIndex(result.getPageIndex());
		pageInfo.setTotalPage(result.getTotalPageNum());
		return pageInfo;
	}

	/**
	 * 构建返回前端数据
	 * 
	 * @param aggVOs
	 *            业务单据集合
	 * @param pageInfo
	 *            分页信息
	 * @return 查询动作结果
	 * @throws BusinessException
	 */
	private Grid buildGridData(ListQryResult<T> result, PageInfo pageInfo,
			String pageCode) throws BusinessException {
		T[] aggVOs = result.getAggVOs();
		if (ArrayUtil.isNull(aggVOs)) {
			return null;
		}
		// 构建表格数据
		Grid grid = (Grid) super.convert2FrontStdData(null, Grid.class,
				pageCode, aggVOs);
		grid.getModel().setAllpks(result.getAllPKs());
		grid.getModel().setPageinfo(pageInfo);
		return grid;
	}

	/**
	 * 构建查询结果
	 * 
	 * @param grid
	 *            列表数据
	 * @param groupData
	 *            分组数据
	 * @return
	 * @throws BusinessException
	 */
	protected TMGrid buildQryResult(ListQryResult<T> result, String pageCode)
			throws BusinessException {
		PageInfo pageInfo = buildPageInfo(result);
		Grid grid = buildGridData(result, pageInfo, pageCode);
		TMGrid tmGrid = new TMGrid();
		tmGrid.setGrid(grid);
		tmGrid.setNumValues(result.getGroupCountInfo());
		return tmGrid;
	}
}
