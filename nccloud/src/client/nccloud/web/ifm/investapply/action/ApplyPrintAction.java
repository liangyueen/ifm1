package nccloud.web.ifm.investapply.action;

import nccloud.web.platform.print.AbstractPrintAction;


public class ApplyPrintAction extends AbstractPrintAction {

	@Override
	public String getPrintServiceModule() {
		return "ifm";
	}

	@Override
	public String getPrintServiceName() {
		return "nc.impl.ifm.apply.ApplyPrintService";
	}
	
}