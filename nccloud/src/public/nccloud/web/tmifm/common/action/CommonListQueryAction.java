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
 * �б��ѯ
 * 
 * @author futao3
 * @date Aug 29, 2018
 */
public abstract class CommonListQueryAction<T extends AbstractBill> extends
		AbstractPfAction<T> {
	
	@Override
	public Object doAction(IRequest request) {

		// ��ȡǰ���������
		// Object queryParam = super.getRequestParam(request)
		QueryTreeFormatVO queryParam = (QueryTreeFormatVO) this
				.getRequestParam(request);
		// ��ѯ��������
		Map<String, String> groupData = queryGroupData(queryParam);
		// ǰ����
		doBefore();
		List<String> allPks = this.qryAllPks(queryParam);
		// �Ȳ�ѯ����pk���ٸ���pk��ѯ��ҳ����
		List<String> currPks = this.getCurrPagePks(queryParam, allPks);
		// ����pk��ѯ��ǰ��ҳ����
		T[] resultVOs = queryBillsByPks(currPks.toArray(new String[0]));
		// ��������
		doAfter();
		// ��������ǰ�˽��
		Map<String, Object> result = this.buidFontResult(allPks, resultVOs,
				queryParam,groupData);
		// ������
		return result;
	}
	/**
	 * ��ѯ��������
	 * 
	 * @param queryScheme
	 *            ��ѯ����
	 * @return ��������
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
		// info����ת���Ϊservice���õ�QueryScheme
		IQueryScheme scheme = qservice.convertCondition(operaParam);
		List<Condition> custConditions = operaParam.getCustcondition().getConditions();
		List<String> allPks = null;
		// ��ѯ��������������������
		try {
			// ���淵�ص�ʵ����һ��ArrayList������ֱ��ת
			allPks = (List<String>) QueryUtil.fetchPKsByQueryScheme(
					this.getVOClass(), scheme,custConditions);
		} catch (BusinessException e) {
			ExceptionUtils
					.wrapBusinessException("��ѯ��������pkʧ�ܣ�" + e.getMessage());
		}
		return allPks;
	}

	/**
	 * ��ȡ��ǰ��ҳpk
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
		// ��ǰ��ҳ����pk
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
	 * ����pk��ѯ����
	 * 
	 * @param currPks
	 * @return
	 */
	protected abstract T[] queryBillsByPks(String[] currPagePks);

	/**
	 * ����ǰ�˷��ؽ��
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
		// ������allPks�����м���ó��ܼ�¼������ҳ��, ��װ��PageInfo������
		pageInfo.setTotal(allPks.size());
		pageInfo.setPageSize(Integer.valueOf(queryParam.getPageInfo()
				.getPageSize()));
		pageInfo.setPageIndex(Integer.valueOf(queryParam.getPageInfo()
				.getPageIndex()));
		int totalPage = (int) Math.ceil(allPks.size()
				/ Double.valueOf(queryParam.getPageInfo().getPageSize()));
		pageInfo.setTotalPage(totalPage);
		String[] ids = allPks.toArray(new String[0]);
		// �����ѯ���, ��װ��Grid������
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
	 * ��ȡ����VO��Ӧclass�࣬���Բ�ѯ����pk
	 * 
	 * @return
	 */
	protected abstract Class<? extends SuperVO> getVOClass();

	/**
	 * ��ȡ�б��������
	 * 
	 * @return
	 */
	protected abstract String getAreaCode();
	
	/**
	 * ���ȴ���
	 * 
	 * @return
	 */
	protected abstract void processDigit(Grid grid);

	@Override
	protected T[] processRequestParam(IRequest request) {
		return null;
	}
}
