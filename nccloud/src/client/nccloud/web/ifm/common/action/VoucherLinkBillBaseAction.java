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
 * 凭证联查业务单据
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
			// 获取请求数据
			VoucherLinkInfo param = (VoucherLinkInfo) getReqData(request);
			String pageCode = param.getPageCode();
			if (StringUtil.isNull(pageCode)) {
				throw new BusinessException("请求数据缺失参数[pageCode]页面编码不能为空！");
			}
			OperatingLogVO[] operLogVOs = param.getOperatingLogVO();
			if (operLogVOs == null || operLogVOs.length == 0) {
				return null;
			}
			// 查询业务单据
			T[] aggVOs = queryBill(operLogVOs);
			// 构建查询动作结果
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
		// TOSEE 循环遍历基类，获取泛型的参数类型
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
	 * 查询业务单据
	 * 凭证接口返回业务单据生成凭证时的数据类型，若类型不一致，需要复写该方法
	 * 
	 * @param queryParam
	 *            查询参数
	 * @return 业务单据
	 */
	protected T[] queryBill(OperatingLogVO[] param) throws BusinessException {
		if (ArrayUtil.isNull(param)) {
			return null;
		}
		Object[] queryBill = getRelateBillService().queryBill(param, "src");
		Logger.error("凭证联查单据返回数据：---------------------");
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
