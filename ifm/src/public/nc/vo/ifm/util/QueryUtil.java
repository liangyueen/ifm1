package nc.vo.ifm.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.uif2.BusinessExceptionAdapter;
import nc.itf.uap.pf.metadata.IFlowBizItf;
import nc.jdbc.framework.processor.ResultSetProcessor;
import nc.md.data.access.NCObject;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.fts.util.OrderByManager;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.AssertUtils;
import nc.vo.pubapp.pflow.PfServiceUtil;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.tmpub.util.SqlUtil;
import nc.vo.tmpub.util.StringUtil;
import nc.vo.uap.busibean.exception.BusiBeanException;

public class QueryUtil {
	public static <T extends AggregatedValueObject> Collection<T> sortBillsWithPksOrder(
			T[] bills, String[] pks) throws BusinessException {
		Map<String, T> map = new LinkedHashMap(pks.length);
		for (String pk : pks) {
			map.put(pk, null);
		}
		for (T bill : bills) {
			String billpk = bill.getParentVO().getPrimaryKey();
			AssertUtils
					.assertValue(
							map.containsKey(billpk),
							"nc.vo.tmcfm.util.QueryUtil.sortBillsWithPksOrder(T[], String[])  map.containsKey(billpk)");

			map.put(billpk, bill);
		}
		return map.values();
	}

	public static Collection<String> fetchPKsByCondtion(String tableName,
			String pkName, String whereSql, String orderBySql)
			throws BusinessException {
		if ((StringUtil.isNull(tableName)) || (StringUtil.isNull(pkName))) {
			return null;
		}

		if (!StringUtil.isNull(whereSql)) {
			whereSql = whereSql + " and ";
		} else {
			whereSql = "";
		}
		whereSql = whereSql + " isnull(" + tableName + ".dr, 0) = 0 ";
		StringBuilder sb = new StringBuilder();

		sb.append("select ").append(tableName).append(".").append(pkName);
		if (whereSql.contains("from")) {
			sb.append(whereSql);
		} else {
			sb.append("select ").append(tableName).append(".").append(pkName)
					.append(" from ").append(tableName).append(" where ")
					.append(whereSql);
		}

		if (StringUtil.isNotNull(orderBySql)) {
			sb.append(orderBySql);
		}
		Collection<String> pkCollection = (Collection) new BaseDAO()
				.executeQuery(sb.toString(), new ResultSetProcessor() {
					private static final long serialVersionUID = 4218556669823483812L;

					public Object handleResultSet(ResultSet rs)
							throws SQLException {
						Collection<String> pkCollection = new ArrayList();
						while (rs.next()) {
							String pk = rs.getString(1);
							if (!pkCollection.contains(pk)) {
								pkCollection.add(pk);
							}
						}

						return pkCollection;
					}

				});
		return pkCollection;
	}

	public static Collection<String> fetchPKsByCondtion(
			Class<? extends SuperVO> clazz, String whereSql)
			throws BusinessException {
		String pkName = null;
		String tableName = null;
		try {
			Method getPKFieldName = clazz.getMethod("getPKFieldName",
					new Class[0]);

			Method getTableName = clazz.getMethod("getTableName", new Class[0]);
			Object obj = clazz.newInstance();

			pkName = StringUtil.toString(getPKFieldName.invoke(obj,
					new Object[0]));
			tableName = StringUtil.toString(getTableName.invoke(obj,
					new Object[0]));
		} catch (SecurityException e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessExceptionAdapter(new BusiBeanException(e));
		} catch (NoSuchMethodException e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessExceptionAdapter(new BusiBeanException(e));
		} catch (InstantiationException e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessExceptionAdapter(new BusiBeanException(e));
		} catch (IllegalAccessException e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessExceptionAdapter(new BusiBeanException(e));
		} catch (IllegalArgumentException e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessExceptionAdapter(new BusiBeanException(e));
		} catch (InvocationTargetException e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessExceptionAdapter(new BusiBeanException(e));
		}

		StringBuilder sb = new StringBuilder();
		sb.append(OrderByManager.getInstance().getOrderBySql(clazz));

		return fetchPKsByCondtion(tableName, pkName, whereSql, sb.toString());
	}

	public static Collection<String> fetchPKsByQueryScheme(
			Class<? extends SuperVO> clazz, IQueryScheme queryScheme)
			throws BusinessException {
		return fetchPKsByQuerySchemeAndOrgName(clazz, queryScheme, null);
	}

	public static Collection<String> fetchPKsByQuerySchemeAndOrgName(
			Class<? extends SuperVO> clazz, IQueryScheme queryScheme,
			String orgName) throws BusinessException {
		QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);

		processor.appendFuncPermissionOrgSql(orgName);
		Collection<String> pks = fetchPKsByCondtion(clazz,
				processor.getFinalFromWhere());
		return filterForApprove(queryScheme, pks, clazz);
	}

	public static Collection<String> filterForApprove(IQueryScheme queryScheme,
			Collection<String> billids, Class<? extends SuperVO> voClass) {
		QuerySchemeProcessor qsp = new QuerySchemeProcessor(queryScheme);
		QueryCondition condition = qsp.getQueryCondition("bisapproving");
		if ((condition != null) && (billids != null) && (billids.size() > 0)) {
			Object[] values = condition.getValues();
			if (UFBoolean.valueOf(values[0].toString()).booleanValue()) {
				try {
					String pkName = null;

					SuperVO superVO = (SuperVO) voClass.newInstance();
					pkName = superVO.getPKFieldName();
					String sql = SqlUtil.buildSqlForIn(pkName,
							(String[]) billids.toArray(new String[0]));
					IMDPersistenceQueryService service = (IMDPersistenceQueryService) NCLocator
							.getInstance().lookup(
									IMDPersistenceQueryService.class);

					Collection collection = service.queryBillOfVOByCond(
							voClass, sql, true, false);

					AggregatedValueObject aggregatedValueObject = (AggregatedValueObject) collection
							.iterator().next();

					NCObject ncObj = NCObject
							.newInstance(aggregatedValueObject);
					IFlowBizItf itf = (IFlowBizItf) ncObj
							.getBizInterface(IFlowBizItf.class);
					String billType = itf.getBilltype();

					AggregatedValueObject[] vos = PfServiceUtil
							.filterForApprove(
									(AggregatedValueObject[]) collection
											.toArray(new AggregatedValueObject[0]),
									billType, AppContext.getInstance()
											.getPkUser());

					List<String> pks = new ArrayList();
					for (AggregatedValueObject aggVO : vos) {
						pks.add(aggVO.getParentVO().getPrimaryKey());
					}

					billids = pks;
				} catch (Exception e) {
				}
			}
		}

		return billids;
	}

	public static Collection<String> fetchPKsByQuerySchemeAndProcessor(
			Class<? extends SuperVO> clazz, String wherePart,
			IQueryScheme queryScheme) throws BusinessException {
		QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);

		processor.appendFuncPermissionOrgSql();
		if (!StringUtil.isNull(wherePart)) {
			processor.appendWhere(wherePart);
		}

		return fetchPKsByCondtion(clazz, processor.getFinalFromWhere());
	}
}
