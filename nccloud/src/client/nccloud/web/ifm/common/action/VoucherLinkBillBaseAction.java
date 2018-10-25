package nccloud.web.ifm.common.action;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import nc.bs.logging.Logger;
import nc.itf.fip.opreatinglog.GenerateRelatedBillService;
import nc.vo.fip.operatinglogs.OperatingLogVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.tmpub.util.ArrayUtil;
import nc.vo.tmpub.util.StringUtil;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.ui.pattern.grid.Grid;

/**
 * ƾ֤����ҵ�񵥾�
 * @author majfd
 *
 * @param <T>
 */
public abstract class VoucherLinkBillBaseAction<T extends AbstractBill> extends
		AbstractCommonAction<T> {

	@Override
	public Object doAction(IRequest request) {
		// TODO Auto-generated method stub
		Grid grid = null;
		try {
			// ��ȡ��������
			VoucherLinkInfo param = (VoucherLinkInfo) getReqData(request);
			String pageCode = param.getPageCode();
			if (StringUtil.isNull(pageCode)) {
				throw new BusinessException("��������ȱʧ����[pageCode]ҳ����벻��Ϊ�գ�");
			}
			OperatingLogVO[] operLogVOs = param.getOperatingLogVO();
			if (operLogVOs == null || operLogVOs.length == 0) {
				return null;
			}
			// ��ѯҵ�񵥾�
			T[] aggVOs = queryBill(operLogVOs);
			// ������ѯ�������
			grid = (Grid) convert2FrontStdData(param, Grid.class, pageCode,
					aggVOs);
		} catch (Exception e) {
			Logger.error(e);
			ExceptionUtils.wrapException(e);
		}
		return grid;
	}

	@Override
	protected Class<?> getReqDataClass() {
		// TODO Auto-generated method stub
		return VoucherLinkInfo.class;
	}

	public class VoucherLinkInfo {
		private OperatingLogVO[] operatingLogVO;
		private String pageCode;

		public OperatingLogVO[] getOperatingLogVO() {
			return operatingLogVO;
		}

		public void setOperatingLogVO(OperatingLogVO[] oper) {
			this.operatingLogVO = oper;
		}

		public String getPageCode() {
			return pageCode;
		}

		public void setPageCode(String pageCode) {
			this.pageCode = pageCode;
		}
	}

	private Class<?> getGenericParamClass() {
		Type type = null;
		Class<?> clazz = this.getClass();
		// TOSEE ѭ���������࣬��ȡ���͵Ĳ�������
		while (type == null || (clazz != VoucherLinkBillBaseAction.class)
				|| (!(type instanceof ParameterizedType))) {
			type = clazz.getGenericSuperclass();
			clazz = clazz.getSuperclass();
		}
		Type[] actualTypeArguments = ((ParameterizedType) type)
				.getActualTypeArguments();
		return (Class<?>) actualTypeArguments[0];
	}

	/**
	 * ��ѯҵ�񵥾�
	 * ƾ֤�ӿڷ���ҵ�񵥾�����ƾ֤ʱ���������ͣ������Ͳ�һ�£���Ҫ��д�÷���
	 * 
	 * @param queryParam
	 *            ��ѯ����
	 * @return ҵ�񵥾�
	 */
	protected T[] queryBill(OperatingLogVO[] param) throws BusinessException {
		if (ArrayUtil.isNull(param)) {
			return null;
		}
		Object[] queryBill = getRelateBillService().queryBill(param, "src");
		Logger.error("ƾ֤���鵥�ݷ������ݣ�---------------------");
		Logger.error(queryBill);
		if (ArrayUtil.isNull(queryBill)) {
			return null;
		}
		Class<?> genericParamClazz = getGenericParamClass();
		T[] arr = (T[]) Array.newInstance(genericParamClazz, queryBill.length);
		for (int i = 0; i < queryBill.length; i++) {
			arr[i] = (T) queryBill[i];
		}
		
		return arr;
	}

	protected GenerateRelatedBillService getRelateBillService() {
		return ServiceLocator.find(GenerateRelatedBillService.class);
	}

}
