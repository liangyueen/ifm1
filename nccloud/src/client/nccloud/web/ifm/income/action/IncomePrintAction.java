package nccloud.web.ifm.income.action;

import nccloud.web.platform.print.AbstractPrintAction;

public class IncomePrintAction extends AbstractPrintAction {
	@Override
	public String getPrintServiceModule() {
		return "ifm";
	}

	@Override
	public String getPrintServiceName() {
		return "nc.impl.ifm.income.IncomePrintService";
	}
	// html ½âÎö
	// @Override
	// public WebFileType getWebFileType() {
	// return WebFileType.Html;
	// }

}
