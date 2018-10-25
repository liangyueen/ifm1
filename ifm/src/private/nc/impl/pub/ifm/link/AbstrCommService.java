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
 * ������������
 * <p>
 * ������NCC service��ҵ�������<br/>
 * 1�������б�ķ�ҳ��Ϣ��processAllQryData��<br/>
 * 2������������ts����ȡ�Ѽ�������������������ҵ��У���ҵ������(getBills)<br/>
 * 3�����ȷ���(getTMPrecisionService)<br/>
 * 4����ȡ��ǰ������(getCurUser)<br/>
 * 5����ȡ��ǰҵ������(getBizDate)<br/>
 * 
 * 
 * @author tangleic
 * @date 20180706
 * @version 1.0
 * 
 */
public abstract class AbstrCommService<T extends AbstractBill> {
	/**
	 * Ӧ�ñ���
	 */
	protected final String EXTPARAM_APPCODE = "appCode";

	/**
	 * ������ҳ��
	 * 
	 * @param pageSize
	 *            ��ҳ������
	 * @param count
	 *            ��������
	 * @return ��ҳ��
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
	 * �������е�ҵ�����ݣ�����Ҫ��ѯ�ĵ�ҳ��������
	 * 
	 * @param pageSize
	 *            ��ҳ������
	 * @param pageIndex
	 *            ��ǰҳ����
	 * @param allPKs
	 *            ���еĵ�����������
	 * @param qryResult
	 *            ��ѯ������
	 * @return Ҫ��ѯ�ĵ�ҳ��������
	 * @throws BusinessException
	 */
	protected String[] processAllQryData(int pageSize, int pageIndex,
			String[] allPKs, ListQryResult<T> qryResult)
			throws BusinessException {
		// �����ҳ����
		int allSize = ArrayUtil.isNull(allPKs) ? 0 : allPKs.length;
		// �����ܹ�ҳ��
		int totalPageNum = caculateTotalPage(pageSize, allSize);
		// ���㵱ǰҳ��
		pageIndex = pageIndex < totalPageNum ? pageIndex : 0;
		// ���·�ҳ��Ϣ
		qryResult.setTotalNum(allSize);
		qryResult.setPageSize(pageSize);
		qryResult.setPageIndex(pageIndex);
		qryResult.setTotalPageNum(totalPageNum);
		qryResult.setAllPKs(allPKs);
		if (allSize == 0) {
			return null;
		}
		// ������ʼλ��
		int startIndex = pageIndex * pageSize;
		int endIndex;
		// �������λ��
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
	 * ҵ�񵥾����ݼ��ϰ������������˳���������
	 * <p>
	 * ּ�ڽ����aggVO��˳����pk��˳��һ�¡�ǰ���б�������ݵ�˳��������˳��һ�£�
	 * �Ӷ��ڿ�Ƭ��������ҳ�л���ʱ���������б���������˳��һ�µ�����
	 * 
	 * @param pks
	 *            ��������
	 * @param collection
	 *            ҵ�񵥾ݼ���
	 * @return ҵ�񵥾����鼯��
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	protected T[] orderDataByPK(String[] pks, Collection<T> collection)
			throws BusinessException {
		// �����п�
		if (collection == null || collection.isEmpty() || ArrayUtil.isNull(pks)) {
			return null;
		}
		Map<String, T> map = new HashMap<>();
		String pk;
		// ����ҵ�񵥾ݣ�����������ҵ�����ݵ�ӳ��
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
		// ��ȡҵ�񵥾���������
		Class<?> generParamClazz = getGenericParamClass();
		// ��������
		Object array = Array.newInstance(generParamClazz, map.size());
		// �����������飬�������������˳�򹹽�ҵ�񵥾�����
		for (int i = 0; i < pks.length; i++) {
			pk = pks[i];
			T data = map.get(pk);
			Array.set(array, i, data);
		}
		return (T[]) array;
	}

	/**
	 * ��ȡ���Ͷ���Ĳ�������
	 * 
	 * @param targetClazz
	 * @return
	 */
	private Class<?> getGenericParamClass() {
		Type type = null;
		Class<?> clazz = this.getClass();
		// TOSEE ѭ���������࣬��ȡ���͵Ĳ�������
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
	 * ��ѯҵ�񵥾�
	 * 
	 * @param pks
	 *            ��������
	 * @return ҵ�񵥾�����
	 * @throws BusinessException
	 */
	protected abstract T[] queryBill(String[] pks) throws BusinessException;

	/**
	 * ��ȡҵ�񵥾�
	 * <p>
	 * �÷������ѯҵ�񵥾ݣ�ͬ��ǰ�˵�tsʱ��������ݿ����Ƿ���������ݿ����Ƿ�ʱ���У�飬���ݿ����Ƿ�ִ��У����У��
	 * 
	 * @param pkMapTs
	 *            ������ʱ���tsӳ��
	 * @param validators
	 *            У��������
	 * @param lock
	 *            �Ƿ����
	 * @param checkTs
	 *            �Ƿ�У��ʱ���
	 * @param validate
	 *            �Ƿ�У����У��
	 * @return
	 */
	protected T[] getBills(Map<String, String> pkMapTs, List<?> validators,
			boolean lock, boolean checkTs, boolean validate)
			throws BusinessException {
		if (pkMapTs == null || pkMapTs.isEmpty()) {
			throw new BusinessException("����ȱʧ,pkMapTs����Ϊ�գ�");
		}
		String[] pks = pkMapTs.keySet().toArray(new String[0]);
		// ����
		lock(lock, pks);
		// ��ѯҵ�񵥾�
		T[] aggVOs = queryBill(pks);
		if (ArrayUtil.isNull(aggVOs)) {
			throw new BusinessException("δ��ѯ��ҵ�񵥾����ݣ�");
		}
		// ��tsͬ����ҵ�񵥾�
		synchFrontTs2Bill(pkMapTs, aggVOs);
		// ʱ���У��
		checkTs(checkTs, aggVOs);
		// ҵ��У��
		validate(aggVOs, validators, validate);
		return aggVOs;
	}

	/**
	 * ��ǰ�˽���TSͬ����ҵ������
	 * 
	 * @param aggVOs
	 *            ҵ�񵥾�����
	 * @param pkMapTs
	 *            ������ts��ӳ���ϵ
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
				throw new BusinessException("����[" + pk + "]��tsʱ���Ϊ�գ�");
			}
			ts = new UFDateTime(tsStr);
			aggVO.getParent().setAttributeValue("ts", ts);
		}
	}

	/**
	 * ����
	 * 
	 * @param pks
	 *            ҵ������
	 * @param lock
	 *            �Ƿ����
	 * @throws BusinessException
	 */
	private static void lock(boolean lock, String... pks)
			throws BusinessException {
		if (!lock) {
			return;
		}
		boolean flag = PKLock.getInstance().addBatchDynamicLock(pks);
		if (!flag) {
			throw new BusinessException("��ǰ�������ڱ�ʹ�ã����Ժ��ٲ�����");
		}
	}

	/**
	 * ʱ���У��
	 * 
	 * @param aggVOs
	 *            ҵ�񵥾�����
	 * @param checkTs
	 *            �Ƿ�У��ʱ���
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
	 * ������ʱ���У��
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
	 * ��ȡУ��������
	 * 
	 * @param validators
	 *            У��������
	 * @return
	 */
	private DefaultValidationService getValidateService(
			List<Validator> validators) {
		DefaultValidationService validateService = new DefaultValidationService();
		validateService.setValidators(validators);
		return validateService;
	}

	/**
	 * ��ȡ����У�����
	 * 
	 * @param validators
	 *            У��������
	 * @return
	 */
	private DefaultBatchValidationService<T> getBatchValidateService(
			List<IBatchValidator<T>> validators) {
		DefaultBatchValidationService<T> batchValidateService = new DefaultBatchValidationService<>();
		batchValidateService.setValidators(validators);
		return batchValidateService;
	}

	/**
	 * У��
	 * 
	 * @param aggVOs
	 *            ҵ�񵥾�����
	 * @param validators
	 *            У��������
	 * @param validate
	 *            �Ƿ�У��
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
	 * ��ȡСӦ����Ȩ�޵���֯��sqlƬ��(û��and)
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
			throw new BusinessException("��ѯӦ��[" + appCode + "]��Ȩ�޵���֯ʱ����");
		}
		if (ArrayUtil.isNull(orgs)) {
			return " 1=2 ";
		}
		return SqlUtil.buildSqlForIn(InnerTransferVO.PK_ORG, orgs);
	}

	/**
	 * ͨ��У����������ҵ��У��
	 * <p>
	 * �����������ҵ������ֹ�����ҵ��У��ĳ���
	 * 
	 * @param aggVOs
	 *            ҵ�񵥾�����
	 * @param validators
	 *            У��������
	 * @throws BusinessException
	 */
	protected void validate(T[] aggVOs, List<?> validators)
			throws BusinessException {
		validate(aggVOs, validators, true);
	}

	/**
	 * ��ȡ��ǰ��½�û�
	 * 
	 * @return
	 */
	protected String getCurUser() {
		return AppContext.getInstance().getPkUser();
	}

	/**
	 * ��ȡҵ������
	 * 
	 * @return
	 */
	protected UFDate getBizDate() {
		return AppContext.getInstance().getBusiDate();
	}

	/**
	 * ��ȡ������ʱ��
	 * 
	 * @return
	 */
	protected UFDateTime getServerTime() {
		return AppContext.getInstance().getServerTime();
	}

	/**
	 * ��ȡ����������
	 * 
	 * @param obj
	 * @return
	 */
	protected UFDouble getValue2UFDouble(Object obj) {
		return obj == null ? null : new UFDouble((String.valueOf(obj)));
	}

	/**
	 * ��ȡ���ȴ������
	 * 
	 * @return
	 */
	protected ITMPrecisionServiceForNCC getTMPrecisionService() {
		return NCLocator.getInstance().lookup(ITMPrecisionServiceForNCC.class);
	}

	/**
	 * ��ȡЧӦ��Ȩ�޷���
	 * 
	 * @return �ӿڷ���
	 */
	protected IAppAndOrgPermQueryPubService getAppPermissionService() {
		return NCLocator.getInstance().lookup(
				IAppAndOrgPermQueryPubService.class);
	}
}
