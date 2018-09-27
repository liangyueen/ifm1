package nc.impl.ifm.apply;

import nc.ui.pub.print.IDataSource;
import nccloud.pubitf.platform.print.AbstractPrintService;
import nccloud.pubitf.platform.print.IPrintInfo;
import nccloud.pubitf.platform.print.vo.PrintInfo;
public class ApplyPrintService extends AbstractPrintService {
	@Override
	public IDataSource[] getDataSources(IPrintInfo info) {
		PrintInfo printinfo = (PrintInfo) info;
		ApplyPrintDataSource ds = new ApplyPrintDataSource(printinfo.getIds());
		return new IDataSource[] { ds };

	}
}