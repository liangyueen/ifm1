package nccloud.web.tmifm.common.action;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.scmpub.page.PageQueryVO;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.ui.model.GridModel;
import nccloud.framework.web.ui.pattern.grid.Grid;
import nccloud.framework.web.ui.pattern.grid.GridOperator;
import nccloud.ifm.vo.OperatorParam;

/**
 * 列表翻页查询， 前端直接将pks传到后台
 * 
 * @author futao3
 * @date Aug 29, 2018
 */
public abstract class CommonQueryByPksAction<T extends AbstractBill> extends
		AbstractPfAction<T> {

	@Override
	public Object doAction(IRequest request) {

		// 获取前端请求参数
		OperatorParam operaParam = (OperatorParam) this.getRequestParam(request);
		// 前奏操作
		doBefore();
		// 根据pks查询出聚合VOs
		T[] resultVOs = null;
		if (operaParam.getPks() != null && operaParam.getPks().length > 0) {
			// 根据pk查询当前分页数据
			resultVOs = queryBillsByPks(operaParam.getPks());
		}
		// 后续操作
		doAfter();
		// 构建前端返回结果
		Map<String, Object> result = this.buildFontResult(operaParam, resultVOs);
		// 处理精度
		processDigit();
		return result;
	}

	/**
	 * 根据pk查询单据
	 * 
	 * @param currPks
	 * @return
	 */
	protected abstract T[] queryBillsByPks(String[] currPagePks);
	
	protected Map<String, Object> buildFontResult(OperatorParam operaParam,
			T[] resultVOs) {
		Map<String, Object> rtv = new HashMap<>();
		Grid grid = new Grid();
		// 处理查询结果, 封装在Grid对象中
		PageQueryVO qvo = new PageQueryVO(operaParam.getPks(), resultVOs);
		grid = this.convert(qvo, operaParam.getPks().length, operaParam);
		if (grid.getModel() == null) {
			grid.setModel(new GridModel());
		}
		grid.getModel().setPageinfo(null);
		rtv.put("grid", grid);
		return rtv;
	}

	/*
	 * 讲info和result封装到Grid中
	 */
	private Grid convert(PageQueryVO result, int size, OperatorParam info) {
		IBill[] bills = result.getCurrentPageBills();
		Grid grid = new Grid();
		GridModel model = new GridModel();
		model.setAreacode(this.getAreaCode());
		grid.setModel(model);
		grid.setPageid(info.getPageCode());
		if (bills != null) {
			Object[] heads = new Object[bills.length];
			for (int i = 0; i < bills.length && i < size; i++) {
				heads[i] = bills[i].getParent();
			}
			GridOperator operator = new GridOperator(info.getPageCode());
			if (heads.length > 0) {
				grid = operator.toGrid(heads);
			}
			grid.getModel().setAreacode(this.getAreaCode());
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

	@Override
	protected T[] processRequestParam(IRequest request) {
		return null;
	}

	/**
	 * 获取列表区域编码
	 * 
	 * @return
	 */
	protected abstract String getAreaCode();

}
