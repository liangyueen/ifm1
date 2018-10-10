package nccloud.web.ifm.common.action;

import nc.bs.logging.Log;
import nc.lightapp.pubapp.web.template.ref.util.StringUtils;
import nc.vo.pub.BusinessException;
import nccloud.dto.tmpub.bean.LinkInfor;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.core.json.IJson;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;

/**
 * ���鵥��
 * @author majfd
 *
 */
public abstract class AbstractCommonLinkOperatorAction implements ICommonAction{
	private Log log = Log.getInstance(getClass());
	
	private IJson jsonTool = JsonFactory.create();

	@Override
	public Object doAction(IRequest request) {
		LinkInfor[] linkInfo = null;
		try {
			if (request == null) {
				throw new BusinessException("�������Ϊ�գ�");
			}
			LinkReqParam reqParam = getReqParam(request);
			linkInfo = buildLinkInfo(reqParam);
		} catch (Exception e) {
			log.error(e);
			ExceptionUtils.wrapException(e);
		}
		return linkInfo;
	}
	
	protected abstract LinkInfor[] buildLinkInfo(LinkReqParam reqParam) throws BusinessException;

	/**
	 * ��ȡ��������
	 * 
	 * @param request
	 *            �������
	 * @return ��������
	 * @throws BusinessException
	 */
	private LinkReqParam getReqParam(IRequest request) throws BusinessException {
		String str = request.read();
		if (StringUtils.isEmpty(str)) {
			throw new BusinessException("ǰ������Ϊ�գ�");
		}
		log.debug("ǰ������[" + str + "]");
		LinkReqParam reqParam = jsonTool.fromJson(str, LinkReqParam.class);
		return reqParam;
	}

	/**
	 * �����������
	 * 
	 * @author tangleic
	 * 
	 */
	protected class LinkReqParam {
		/**
		 * �������ͻ�������
		 */
		private String billTypeOrTransType;
		/**
		 * ��������
		 */
		private String pk;
		/**
		 * �ӱ�����
		 */
		private String bpk;
		

		public String getBillTypeOrTransType() {
			return billTypeOrTransType;
		}

		public void setBillTypeOrTransType(String billTypeOrTransType) {
			this.billTypeOrTransType = billTypeOrTransType;
		}

		public LinkReqParam(String billTypeOrTransType, String pk) {
			super();
			this.billTypeOrTransType = billTypeOrTransType;
			this.pk = pk;
		}

		public String getBpk() {
			return bpk;
		}

		public void setBpk(String bpk) {
			this.bpk = bpk;
		}

		public String getPk() {
			return pk;
		}

		public void setPk(String pk) {
			this.pk = pk;
		}

	}

}
