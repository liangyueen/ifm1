package nccloud.web.ifm.redeem.action;

import nccloud.web.platform.print.AbstractPrintAction;

public class RedeemPrintAction extends AbstractPrintAction {
	@Override
	public String getPrintServiceModule() {
		return "ifm";
	}

	@Override
	public String getPrintServiceName() {
		return "nc.impl.ifm.redeem.RedeemPrintService";
	}
	// html ½âÎö
	// @Override
	// public WebFileType getWebFileType() {
	// return WebFileType.Html;
	// }

}
