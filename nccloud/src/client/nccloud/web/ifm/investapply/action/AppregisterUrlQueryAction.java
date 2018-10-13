package nccloud.web.ifm.investapply.action;

import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.core.json.IJson;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.json.JsonFactory;
import nccloud.pubitf.platform.workbench.IAppPage;
import nccloud.web.common.bean.AppregisterJson;

/**
 * @Description: ����pagecode��ȡСӦ�õ�ҳ��·��
 * @author tianhaok
 * @date 2018-8-9 ����2:57:23
 */
public class AppregisterUrlQueryAction implements ICommonAction {

	@Override
	public Object doAction(IRequest request) {
		// 1����ʽ������
		String str = request.read();
		IJson json = JsonFactory.create();
		AppregisterJson info = json.fromJson(str, AppregisterJson.class);
		String url = null;
		try {

			IAppPage qservice = ServiceLocator.find(IAppPage.class);

			url = qservice.queryPageUrl(info.getAppCode(), info.getPageCode());

		} catch (Exception e) {
			ExceptionUtils.wrapBusinessException(e.getMessage());
		}
		return url;
	}

}
