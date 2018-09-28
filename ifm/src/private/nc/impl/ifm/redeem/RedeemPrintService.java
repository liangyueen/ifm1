package nc.impl.ifm.redeem;

import nc.ui.pub.print.IDataSource;
import nccloud.pubitf.platform.print.AbstractPrintService;
import nccloud.pubitf.platform.print.IPrintInfo;
import nccloud.pubitf.platform.print.vo.PrintInfo;

public class RedeemPrintService extends AbstractPrintService {
	@Override
	public IDataSource[] getDataSources(IPrintInfo info) {
		PrintInfo printinfo = (PrintInfo) info;

		RedeemPrintDataSource ds = new RedeemPrintDataSource(
				printinfo.getIds());
		return new IDataSource[] { ds };

	}
}
