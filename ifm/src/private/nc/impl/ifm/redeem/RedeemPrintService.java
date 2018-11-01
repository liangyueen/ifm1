package nc.impl.ifm.redeem;

import nc.ui.pub.print.IDataSource;
import nccloud.pubitf.platform.print.AbstractPrintService;
import nccloud.pubitf.platform.print.IPrintInfo;
import nccloud.pubitf.platform.print.vo.PrintInfo;

public class RedeemPrintService extends AbstractPrintService {
	@Override
	public IDataSource[] getDataSources(IPrintInfo info) {
		PrintInfo printinfo = (PrintInfo) info;
		String[] ids = printinfo.getIds();
		RedeemPrintDataSource[] ds = new RedeemPrintDataSource[ids.length];
		for (int i = 0; i < ids.length; i++) {
			ds[i] = new RedeemPrintDataSource(ids[i]);
		}
		return ds;

	}
}
