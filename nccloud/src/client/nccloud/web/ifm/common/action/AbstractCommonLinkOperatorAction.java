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
 * 联查单据
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
				throw new BusinessException("请求参数为空！");
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
	 * 获取请求数据
	 * 
	 * @param request
	 *            请求对象
	 * @return 请求数据
	 * @throws BusinessException
	 */
	private LinkReqParam getReqParam(IRequest request) throws BusinessException {
		String str = request.read();
		if (StringUtils.isEmpty(str)) {
			throw new BusinessException("前端请求为空！");
		}
		log.debug("前端请求[" + str + "]");
		LinkReqParam reqParam = jsonTool.fromJson(str, LinkReqParam.class);
		return reqParam;
	}

	/**
	 * 联查请求参数
	 * 
	 * @author tangleic
	 * 
	 */
	protected class LinkReqParam {
		/**
		 * 单据类型或交易类型
		 */
		private String billTypeOrTransType;
		/**
		 * 主键数组
		 */
		private String pk;
		/**
		 * 子表主键
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
