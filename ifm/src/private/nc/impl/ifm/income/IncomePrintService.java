package nc.impl.ifm.income;

import nc.ui.pub.print.IDataSource;
import nccloud.pubitf.platform.print.AbstractPrintService;
import nccloud.pubitf.platform.print.IPrintInfo;
import nccloud.pubitf.platform.print.vo.PrintInfo;

public class IncomePrintService extends AbstractPrintService {
	@Override
	public IDataSource[] getDataSources(IPrintInfo info) {
		PrintInfo printinfo = (PrintInfo) info;

		IncomePrintDataSource ds = new IncomePrintDataSource(
				printinfo.getIds());
		return new IDataSource[] { ds };

	}
}
