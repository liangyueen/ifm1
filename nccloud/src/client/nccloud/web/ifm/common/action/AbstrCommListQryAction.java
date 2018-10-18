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
 * �б��ѯ��������
 * 
 * @author tangleic
 * @date 20180713
 * @version 1.0
 * 
 * @param <T>
 *            �ۺ�VO����
 */
public abstract class AbstrCommListQryAction<T extends AbstractBill> extends
		AbstractCommonAction<T> {

	@Override
	public Object doAction(IRequest request) {
		TMGrid tmGrid = null;
		try {
			// ��ȡ�������
			OperatorParam param = (OperatorParam) getReqData(request);
			String qryDataStr = param.getData();
			if (StringUtil.isNull(qryDataStr)) {
				throw new BusinessException("��������ȱʧ����[data]����Ϊ�գ�");
			}
			String pageCode = param.getPageCode();
			if (StringUtil.isNull(pageCode)) {
				throw new BusinessException("��������ȱʧ����[pageCode]ҳ����벻��Ϊ�գ�");
			}
			// �����б��ѯ����
			ListQryParam qryParam = buildListQryParam(qryDataStr);
			// ҵ���ѯ
			ListQryResult<T> result = query(qryParam);
			// ������ѯ���
			tmGrid = buildQryResult(result, pageCode);
		} catch (BusinessException e) {
			String errMess = "�б��ѯʱ����"
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
	 * ��ȡ��ѯ����
	 * 
	 * @param QueryTreeFormatVO
	 *            ��ѯ����ģ��
	 * @return ��ѯ����
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
			throw new BusinessException("��ѯ����Ϊ�գ�");
		}
		return queryScheme;
	}

	/**
	 * ������ѯ����
	 * 
	 * @param queryVO
	 *            ��ѯ����ģ��
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
	 * �����б��ѯ����
	 * 
	 * @param info
	 *            ǰ����������
	 * @return �б��ѯ����
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
			throw new BusinessException("ǰ�������ҳ��ϢΪ�գ�");
		}
		if (info.getPageInfo().getPageSize() == null) {
			throw new BusinessException("ǰ�������ҳ��Ϣȱʧ����[pageSize]��");
		}
		if (info.getPageInfo().getPageIndex() == null) {
			throw new BusinessException("ǰ�������ҳ��Ϣȱʧ����[pageIndex]��");
		}
		int pageSize = Integer.parseInt(info.getPageInfo().getPageSize());
		int pageIndex = Integer.parseInt(info.getPageInfo().getPageIndex());
		return new ListQryParam(pageSize, pageIndex, queryScheme, custQryScheme);
	}

	/**
	 * ҵ���ѯ
	 * 
	 * @param param
	 *            �б��ѯ����
	 * @throws BusinessException
	 */
	protected abstract ListQryResult<T> query(ListQryParam param)
			throws BusinessException;

	/**
	 * ������ҳ����
	 * 
	 * @param queryVO
	 * @param allSize
	 * @return
	 * @throws BusinessException
	 */
	private PageInfo buildPageInfo(ListQryResult<T> result)
			throws BusinessException {
		// �����ҳ����
		PageInfo pageInfo = new PageInfo();
		pageInfo.setTotal(result.getTotalNum());
		pageInfo.setPageSize(result.getPageSize());
		pageInfo.setPageIndex(result.getPageIndex());
		pageInfo.setTotalPage(result.getTotalPageNum());
		return pageInfo;
	}

	/**
	 * ��������ǰ������
	 * 
	 * @param aggVOs
	 *            ҵ�񵥾ݼ���
	 * @param pageInfo
	 *            ��ҳ��Ϣ
	 * @return ��ѯ�������
	 * @throws BusinessException
	 */
	private Grid buildGridData(ListQryResult<T> result, PageInfo pageInfo,
			String pageCode) throws BusinessException {
		T[] aggVOs = result.getAggVOs();
		if (ArrayUtil.isNull(aggVOs)) {
			return null;
		}
		// �����������
		Grid grid = (Grid) super.convert2FrontStdData(null, Grid.class,
				pageCode, aggVOs);
		grid.getModel().setAllpks(result.getAllPKs());
		grid.getModel().setPageinfo(pageInfo);
		return grid;
	}

	/**
	 * ������ѯ���
	 * 
	 * @param grid
	 *            �б�����
	 * @param groupData
	 *            ��������
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
