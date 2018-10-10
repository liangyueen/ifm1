package nc.itf.ifm.pub;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.pubitf.org.IFundManaSystemPubService;
import nc.pubitf.org.IFundOrgPubService;

/**
 * SF �������ӿ�
 * @author wusib
 *
 */
public class IFMPubProxyUtil {

	/**
	 * �ʽ���֯ ��ѯ����ӿ�
	 * 
	 * @return
	 */
	public static IFundOrgPubService getFundOrgPubService() {
		try {
			return (IFundOrgPubService) NCLocator.getInstance().lookup(
					IFundOrgPubService.class.getName());
		} catch (Exception ex) {
			Logger.error(ex);
			return null;
		}
	}
	
	/**
	 * �ʽ��ϵ��ϵ��ѯ����
	 * 
	 * @return
	 */
	public static IFundManaSystemPubService getFundManaSystemPubService() {
		try {
			return (IFundManaSystemPubService) NCLocator.getInstance().lookup(
					IFundManaSystemPubService.class.getName());
		} catch (Exception ex) {
			Logger.error(ex);
			return null;
		}
	}
}
