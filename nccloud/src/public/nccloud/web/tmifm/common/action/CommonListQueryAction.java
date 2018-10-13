package nccloud.web.tmifm.common.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.ifm.pub.util.QueryUtil;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.scmpub.page.PageQueryVO;
import nccloud.dto.baseapp.querytree.dataformat.QueryTreeFormatVO;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.core.json.IJson;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.ui.model.GridModel;
import nccloud.framework.web.ui.model.PageInfo;
import nccloud.framework.web.ui.pattern.grid.Grid;
import nccloud.framework.web.ui.pattern.grid.GridOperator;
import nccloud.pubitf.platform.query.INCCloudQueryService;
import nccloud.dto.baseapp.querytree.dataformat.Condition;

/**
 * 列表查询
 * 
 * @author futao3
 * @date Aug 29, 2018
 */
public abstract class CommonListQueryAction<T extends AbstractBill> extends
		AbstractPfAction<T> {
	
	@Override
	public Object doAction(IRequest request) {

		// 获取前端请求参数
		// Object queryParam = super.getRequestParam(request)
		QueryTreeFormatVO queryParam = (QueryTreeFormatVO) this
				.getRequestParam(request);
		// 查询分组数据
		Map<String, String> groupData = queryGroupData(queryParam);
		// 前操作
		doBefore();
		List<String> allPks = this.qryAllPks(queryParam);
		// 先查询主键pk，再根据pk查询本页数据
		List<String> currPks = this.getCurrPagePks(queryParam, allPks);
		// 根据pk查询当前分页数据
		T[] resultVOs = queryBillsByPks(currPks.toArray(new String[0]));
		// 后续操作
		doAfter();
		// 构建返回前端结果
		Map<String, Object> result = this.buidFontResult(allPks, resultVOs,
				queryParam,groupData);
		// 处理精度
		return result;
	}
	/**
	 * 查询分组数据
	 * 
	 * @param queryScheme
	 *            查询方案
	 * @return 分组数据
	 */
	protected abstract Map<String, String> queryGroupData(QueryTreeFormatVO queryParam);

	@Override
	protected Object getRequestParam(IRequest request) {
		String str = request.read();
		IJson json = JsonFactory.create();
		QueryTreeFormatVO operaParam = json.fromJson(str,
				QueryTreeFormatVO.class);
		return operaParam;

	}

	protected List<String> qryAllPks(QueryTreeFormatVO operaParam) {
		INCCloudQueryService qservice = ServiceLocator
				.find(INCCloudQueryService.class);
		// info经过转变成为service可用的QueryScheme
		IQueryScheme scheme = qservice.convertCondition(operaParam);
		List<Condition> custConditions = operaParam.getCustcondition().getConditions();
		List<String> allPks = null;
		// 查询出所有满足条件的主键
		try {
			// 里面返回的实际是一个ArrayList，可以直接转
			allPks = (List<String>) QueryUtil.fetchPKsByQueryScheme(
					this.getVOClass(), scheme,custConditions);
		} catch (BusinessException e) {
			ExceptionUtils
					.wrapBusinessException("查询所有主键pk失败：" + e.getMessage());
		}
		return allPks;
	}

	/**
	 * 获取当前分页pk
	 * 
	 * @param queryParam
	 * @param allPks
	 * @return
	 */
	protected List<String> getCurrPagePks(QueryTreeFormatVO queryParam,
			List<String> allPks) {
		if (allPks == null || allPks.size() == 0) {
			return new ArrayList<String>();
		}
		int pageSize = Integer.valueOf(queryParam.getPageInfo().getPageSize());
		int pageIndex = Integer
				.valueOf(queryParam.getPageInfo().getPageIndex());
		// 当前分页数据pk
		List<String> currPks = new ArrayList<>();
		int allPkCnt = allPks.size();
		for (int i = pageIndex * pageSize; i < (pageIndex + 1) * pageSize; i++) {
			if (i < allPkCnt) {
				currPks.add(allPks.get(i));
			}
		}
		return currPks;
	}

	/**
	 * 根据pk查询单据
	 * 
	 * @param currPks
	 * @return
	 */
	protected abstract T[] queryBillsByPks(String[] currPagePks);

	/**
	 * 构建前端返回结果
	 * 
	 * @param allPks
	 * @param resultVOs
	 * @param queryParam
	 * @return
	 */
	protected Map<String, Object> buidFontResult(List<String> allPks,
			T[] resultVOs, QueryTreeFormatVO queryParam,Map<String, String> groupData) {
		Map<String, Object> rtv = new HashMap<>();
		Grid grid = new Grid();
		PageInfo pageInfo = new PageInfo();
		// 从主键allPks集合中计算得出总记录数和总页数, 封装在PageInfo对象中
		pageInfo.setTotal(allPks.size());
		pageInfo.setPageSize(Integer.valueOf(queryParam.getPageInfo()
				.getPageSize()));
		pageInfo.setPageIndex(Integer.valueOf(queryParam.getPageInfo()
				.getPageIndex()));
		int totalPage = (int) Math.ceil(allPks.size()
				/ Double.valueOf(queryParam.getPageInfo().getPageSize()));
		pageInfo.setTotalPage(totalPage);
		String[] ids = allPks.toArray(new String[0]);
		// 处理查询结果, 封装在Grid对象中
		PageQueryVO qvo = new PageQueryVO(ids, resultVOs);
		grid = this.convert(qvo, pageInfo.getPageSize(), queryParam);
		if (grid.getModel() == null) {
			grid.setModel(new GridModel());
		}
		grid.getModel().setPageinfo(pageInfo);
		processDigit(grid);
		rtv.put("grid", grid);
		rtv.put("groupData", groupData);
		return rtv;
	}

	private Grid convert(PageQueryVO result, int size,
			QueryTreeFormatVO queryParam) {
		IBill[] bills = result.getCurrentPageBills();
		Grid grid = new Grid();
		GridModel model = new GridModel();
		model.setAreacode(this.getAreaCode());
		grid.setModel(model);
		grid.setPageid(queryParam.getPageCode());
		if (bills != null) {
			Object[] heads = new Object[bills.length];
			for (int i = 0; i < bills.length && i < size; i++) {
				heads[i] = bills[i].getParent();
			}
			GridOperator operator = new GridOperator(queryParam.getPageCode());
			if (heads.length > 0) {
				grid = operator.toGrid(heads);
			}
			grid.getModel().setAreacode(this.getAreaCode());
			grid.getModel().setAllpks(result.getPks());
		}
		return grid;
	}

	@Override
	protected String getActionCode() {
		return null;
	}

	@Override
	protected String getBillTypeCode() {
		return null;
	}

	@Override
	protected void doBefore() {

	}

	@Override
	protected void doAfter() {
	}

	@Override
	protected void doReturn() {

	}

	@Override
	protected void processMsg() {

	}

	@Override
	protected void processDigit() {

	}

	/**
	 * 获取主表VO对应class类，用以查询所有pk
	 * 
	 * @return
	 */
	protected abstract Class<? extends SuperVO> getVOClass();

	/**
	 * 获取列表区域编码
	 * 
	 * @return
	 */
	protected abstract String getAreaCode();
	
	/**
	 * 精度处理
	 * 
	 * @return
	 */
	protected abstract void processDigit(Grid grid);

	@Override
	protected T[] processRequestParam(IRequest request) {
		return null;
	}
}
