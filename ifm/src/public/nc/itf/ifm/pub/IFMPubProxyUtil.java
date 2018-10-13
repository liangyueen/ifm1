package nc.itf.ifm.pub;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.pubitf.org.IFundManaSystemPubService;
import nc.pubitf.org.IFundOrgPubService;

/**
 * SF 代理服务接口
 * @author wusib
 *
 */
public class IFMPubProxyUtil {

	/**
	 * 资金组织 查询服务接口
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
	 * 资金关系体系查询服务
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
