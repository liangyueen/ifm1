package nc.impl.pub.ifm.link;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.bs.uap.lock.PKLock;
import nc.bs.uif2.validation.DefaultValidationService;
import nc.bs.uif2.validation.Validator;
import nc.uap.cpb.baseservice.util.BDVersionValidationUtil;
import nc.vo.fts.innertransfer.InnerTransferVO;
import nc.vo.fts.pub.bean.ListQryResult;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.tmpub.batch.validation.DefaultBatchValidationService;
import nc.vo.tmpub.batch.validation.IBatchValidator;
import nc.vo.tmpub.util.ArrayUtil;
import nc.vo.tmpub.util.SqlUtil;
import nc.vo.tmpub.util.StringUtil;
import nccloud.pubitf.baseapp.apprbac.IAppAndOrgPermQueryPubService;
import nccloud.pubitf.tmpub.pub.ITMPrecisionServiceForNCC;

/**
 * 公共基础服务
 * <p>
 * 适用于NCC service层业务服务处理<br/>
 * 1、计算列表的分页信息（processAllQryData）<br/>
 * 2、根据主键和ts来获取已加锁处理，防并发并经过业务校验的业务数据(getBills)<br/>
 * 3、精度服务(getTMPrecisionService)<br/>
 * 4、获取当前操作人(getCurUser)<br/>
 * 5、获取当前业务日期(getBizDate)<br/>
 * 
 * 
 * @author tangleic
 * @date 20180706
 * @version 1.0
 * 
 */
public abstract class AbstrCommService<T extends AbstractBill> {
	/**
	 * 应用编码
	 */
	protected final String EXTPARAM_APPCODE = "appCode";

	/**
	 * 计算总页数
	 * 
	 * @param pageSize
	 *            单页数据量
	 * @param count
	 *            总数据量
	 * @return 总页数
	 */
	private int caculateTotalPage(int pageSize, int count) {
		int total = 0;
		if (count % pageSize == 0) {
			total = count / pageSize;
		} else {
			total = count / pageSize + 1;
		}
		return total;
	}

	/**
	 * 处理所有的业务数据，返回要查询的单页主键数组
	 * 
	 * @param pageSize
	 *            单页数据量
	 * @param pageIndex
	 *            当前页索引
	 * @param allPKs
	 *            所有的单据主键数组
	 * @param qryResult
	 *            查询处理结果
	 * @return 要查询的单页主键数组
	 * @throws BusinessException
	 */
	protected String[] processAllQryData(int pageSize, int pageIndex,
			String[] allPKs, ListQryResult<T> qryResult)
			throws BusinessException {
		// 处理分页数据
		int allSize = ArrayUtil.isNull(allPKs) ? 0 : allPKs.length;
		// 计算总共页数
		int totalPageNum = caculateTotalPage(pageSize, allSize);
		// 重算当前页码
		pageIndex = pageIndex < totalPageNum ? pageIndex : 0;
		// 更新分页信息
		qryResult.setTotalNum(allSize);
		qryResult.setPageSize(pageSize);
		qryResult.setPageIndex(pageIndex);
		qryResult.setTotalPageNum(totalPageNum);
		qryResult.setAllPKs(allPKs);
		if (allSize == 0) {
			return null;
		}
		// 计算起始位置
		int startIndex = pageIndex * pageSize;
		int endIndex;
		// 计算结束位置
		if (totalPageNum == (pageIndex + 1)) {
			endIndex = (allSize % pageSize);
			endIndex = endIndex == 0 ? pageSize : endIndex;
		} else {
			endIndex = startIndex + pageSize;
		}
		List<String> pkList = Arrays.asList(allPKs);
		return pkList.subList(startIndex, endIndex).toArray(new String[0]);
	}

	/**
	 * 业务单据数据集合按照主键数组的顺序进行排序
	 * <p>
	 * 旨在解决：aggVO的顺序与pk的顺序不一致。前端列表界面数据的顺序与主键顺序不一致，
	 * 从而在卡片界面上下页切换的时候主键与列表看到的数据顺序不一致的问题
	 * 
	 * @param pks
	 *            主键数组
	 * @param collection
	 *            业务单据集合
	 * @return 业务单据数组集合
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	protected T[] orderDataByPK(String[] pks, Collection<T> collection)
			throws BusinessException {
		// 参数判空
		if (collection == null || collection.isEmpty() || ArrayUtil.isNull(pks)) {
			return null;
		}
		Map<String, T> map = new HashMap<>();
		String pk;
		// 遍历业务单据，构建主键与业务数据的映射
		for (T data : collection) {
			if (data == null || StringUtil.isNull(data.getPrimaryKey())) {
				continue;
			}
			pk = data.getPrimaryKey();
			map.put(pk, data);
		}
		if (map.isEmpty()) {
			return null;
		}
		// 获取业务单据数据类型
		Class<?> generParamClazz = getGenericParamClass();
		// 构建数组
		Object array = Array.newInstance(generParamClazz, map.size());
		// 遍历主键数组，按照主键数组的顺序构建业务单据数组
		for (int i = 0; i < pks.length; i++) {
			pk = pks[i];
			T data = map.get(pk);
			Array.set(array, i, data);
		}
		return (T[]) array;
	}

	/**
	 * 获取泛型定义的参数类型
	 * 
	 * @param targetClazz
	 * @return
	 */
	private Class<?> getGenericParamClass() {
		Type type = null;
		Class<?> clazz = this.getClass();
		// TOSEE 循环遍历基类，获取泛型的参数类型
		while (type == null || (!(type instanceof ParameterizedType))) {
			type = clazz.getGenericSuperclass();
			if (clazz == AbstrCommService.class) {
				break;
			}
			clazz = clazz.getSuperclass();
		}
		Type[] actualTypeArguments = ((ParameterizedType) type)
				.getActualTypeArguments();
		return (Class<?>) actualTypeArguments[0];
	}

	/**
	 * 查询业务单据
	 * 
	 * @param pks
	 *            主键数组
	 * @return 业务单据数组
	 * @throws BusinessException
	 */
	protected abstract T[] queryBill(String[] pks) throws BusinessException;

	/**
	 * 获取业务单据
	 * <p>
	 * 该方法会查询业务单据，同步前端的ts时间戳，根据开关是否加锁，根据开关是否时间戳校验，根据开关是否执行校验器校验
	 * 
	 * @param pkMapTs
	 *            主键与时间戳ts映射
	 * @param validators
	 *            校验器队列
	 * @param lock
	 *            是否加锁
	 * @param checkTs
	 *            是否校验时间戳
	 * @param validate
	 *            是否校验器校验
	 * @return
	 */
	protected T[] getBills(Map<String, String> pkMapTs, List<?> validators,
			boolean lock, boolean checkTs, boolean validate)
			throws BusinessException {
		if (pkMapTs == null || pkMapTs.isEmpty()) {
			throw new BusinessException("参数缺失,pkMapTs不能为空！");
		}
		String[] pks = pkMapTs.keySet().toArray(new String[0]);
		// 加锁
		lock(lock, pks);
		// 查询业务单据
		T[] aggVOs = queryBill(pks);
		if (ArrayUtil.isNull(aggVOs)) {
			throw new BusinessException("未查询到业务单据数据！");
		}
		// 将ts同步到业务单据
		synchFrontTs2Bill(pkMapTs, aggVOs);
		// 时间戳校验
		checkTs(checkTs, aggVOs);
		// 业务校验
		validate(aggVOs, validators, validate);
		return aggVOs;
	}

	/**
	 * 将前端界面TS同步到业务数据
	 * 
	 * @param aggVOs
	 *            业务单据数组
	 * @param pkMapTs
	 *            主键与ts的映射关系
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	private void synchFrontTs2Bill(Map<String, String> pkMapTs, T... aggVOs)
			throws BusinessException {
		if (ArrayUtil.isNull(aggVOs) || pkMapTs == null || pkMapTs.isEmpty()) {
			return;
		}
		String pk;
		String tsStr;
		UFDateTime ts;
		for (T aggVO : aggVOs) {
			if (aggVO == null || aggVO.getParent() == null
					|| StringUtil.isNull(aggVO.getPrimaryKey())) {
				continue;
			}
			pk = aggVO.getPrimaryKey();
			tsStr = pkMapTs.get(pk);
			if (StringUtil.isNull(tsStr)) {
				throw new BusinessException("主键[" + pk + "]的ts时间戳为空！");
			}
			ts = new UFDateTime(tsStr);
			aggVO.getParent().setAttributeValue("ts", ts);
		}
	}

	/**
	 * 加锁
	 * 
	 * @param pks
	 *            业务主键
	 * @param lock
	 *            是否加锁
	 * @throws BusinessException
	 */
	private static void lock(boolean lock, String... pks)
			throws BusinessException {
		if (!lock) {
			return;
		}
		boolean flag = PKLock.getInstance().addBatchDynamicLock(pks);
		if (!flag) {
			throw new BusinessException("当前单据正在被使用，请稍后再操作！");
		}
	}

	/**
	 * 时间戳校验
	 * 
	 * @param aggVOs
	 *            业务单据数组
	 * @param checkTs
	 *            是否校验时间戳
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	private void checkTs(boolean checkTs, T... aggVOs) throws BusinessException {
		if (!checkTs) {
			return;
		}
		BDVersionValidationUtil.validateObject(aggVOs);
	}

	/**
	 * 加锁和时间戳校验
	 * 
	 * @param aggVOs
	 * @throws BusinessException
	 */
	protected void lockAndCheckTs(T... aggVOs) throws BusinessException {
		if (ArrayUtil.isNull(aggVOs)) {
			return;
		}
		List<String> pks = new ArrayList<>();
		for (T aggVO : aggVOs) {
			String pk = aggVO.getPrimaryKey();
			pks.add(pk);
		}
		lock(true, pks.toArray(new String[0]));
		checkTs(true, aggVOs);
	}

	/**
	 * 获取校验器服务
	 * 
	 * @param validators
	 *            校验器队列
	 * @return
	 */
	private DefaultValidationService getValidateService(
			List<Validator> validators) {
		DefaultValidationService validateService = new DefaultValidationService();
		validateService.setValidators(validators);
		return validateService;
	}

	/**
	 * 获取批量校验服务
	 * 
	 * @param validators
	 *            校验器队列
	 * @return
	 */
	private DefaultBatchValidationService<T> getBatchValidateService(
			List<IBatchValidator<T>> validators) {
		DefaultBatchValidationService<T> batchValidateService = new DefaultBatchValidationService<>();
		batchValidateService.setValidators(validators);
		return batchValidateService;
	}

	/**
	 * 校验
	 * 
	 * @param aggVOs
	 *            业务单据数组
	 * @param validators
	 *            校验器队列
	 * @param validate
	 *            是否校验
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	private void validate(T[] aggVOs, List<?> validators, boolean validate)
			throws BusinessException {
		if (!validate || validators == null || validators.isEmpty()) {
			return;
		}
		if (validators.get(0) instanceof Validator) {
			getValidateService((List<Validator>) validators)
					.validate(aggVOs[0]);
		} else if (validators.get(0) instanceof IBatchValidator) {
			getBatchValidateService((List<IBatchValidator<T>>) validators)
					.validate(aggVOs);
		}
	}

	/**
	 * 获取小应用有权限的组织的sql片段(没有and)
	 * 
	 * @param appCode
	 * @return
	 * @throws BusinessException
	 */
	protected String getPermiOrgSqlPart(String appCode)
			throws BusinessException {
		String userid = getCurUser();
		String pk_group = AppContext.getInstance().getPkGroup();
		String[] orgs = null;
		try {
			orgs = getAppPermissionService().queryUserPermOrgPksByApp(userid,
					appCode, pk_group);
		} catch (Exception e) {
			Logger.error(e);
			throw new BusinessException("查询应用[" + appCode + "]有权限的组织时出错！");
		}
		if (ArrayUtil.isNull(orgs)) {
			return " 1=2 ";
		}
		return SqlUtil.buildSqlForIn(InnerTransferVO.PK_ORG, orgs);
	}

	/**
	 * 通过校验器队列做业务校验
	 * <p>
	 * 适用于子类的业务服务手工触发业务校验的场景
	 * 
	 * @param aggVOs
	 *            业务单据数组
	 * @param validators
	 *            校验器队列
	 * @throws BusinessException
	 */
	protected void validate(T[] aggVOs, List<?> validators)
			throws BusinessException {
		validate(aggVOs, validators, true);
	}

	/**
	 * 获取当前登陆用户
	 * 
	 * @return
	 */
	protected String getCurUser() {
		return AppContext.getInstance().getPkUser();
	}

	/**
	 * 获取业务日期
	 * 
	 * @return
	 */
	protected UFDate getBizDate() {
		return AppContext.getInstance().getBusiDate();
	}

	/**
	 * 获取服务器时间
	 * 
	 * @return
	 */
	protected UFDateTime getServerTime() {
		return AppContext.getInstance().getServerTime();
	}

	/**
	 * 获取浮点型数据
	 * 
	 * @param obj
	 * @return
	 */
	protected UFDouble getValue2UFDouble(Object obj) {
		return obj == null ? null : new UFDouble((String.valueOf(obj)));
	}

	/**
	 * 获取精度处理服务
	 * 
	 * @return
	 */
	protected ITMPrecisionServiceForNCC getTMPrecisionService() {
		return NCLocator.getInstance().lookup(ITMPrecisionServiceForNCC.class);
	}

	/**
	 * 获取效应以权限服务
	 * 
	 * @return 接口服务
	 */
	protected IAppAndOrgPermQueryPubService getAppPermissionService() {
		return NCLocator.getInstance().lookup(
				IAppAndOrgPermQueryPubService.class);
	}
}
