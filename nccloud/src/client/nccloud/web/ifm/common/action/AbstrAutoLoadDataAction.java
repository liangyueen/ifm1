package nccloud.web.ifm.common.action;

import java.util.Map;

import nc.bs.logging.Logger;
import nc.vo.fts.pub.bean.ListQryParam;
import nc.vo.fts.pub.bean.ListQryResult;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.tmpub.util.StringUtil;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.container.IRequest;
import nccloud.ifm.vo.OperatorParam;
import nccloud.web.fts.ui.pattern.grid.TMGrid;

/**
 * �Զ����ض���
 * 
 * @author tangleic
 * @date 2018918
 * @version 1.0
 * 
 * @param <T>
 *            ҵ��ۺ�VO
 */
public abstract class AbstrAutoLoadDataAction<T extends AbstractBill> extends
		AbstrCommListQryAction<T> {
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
			Map<String, String> extParam = param.getExtParam();
			// �����б��ѯ����
			ListQryParam qryParam = buildListQryParam(qryDataStr);
			// ҵ���ѯ
			ListQryResult<T> result = query(qryParam, extParam);
			// ������ѯ���
			tmGrid = buildQryResult(result, pageCode);
		} catch (BusinessException e) {
			Logger.error(e);
			ExceptionUtils.wrapException(e);
		}
		return tmGrid;
	}

	/**
	 * ҵ���ѯ
	 * 
	 * @param param
	 *            �б��ѯ����
	 * @param extParam
	 *            ��չ����
	 * @throws BusinessException
	 */
	protected abstract ListQryResult<T> query(ListQryParam param,
			Map<String, String> extParam) throws BusinessException;

	@Override
	protected ListQryResult<T> query(ListQryParam param)
			throws BusinessException {
		return null;
	}
}
