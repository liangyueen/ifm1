package nc.ui.ifm.redeem.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.ifm.IInvestRedeemMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceInvestRedeemMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IInvestRedeemMaintain query = NCLocator.getInstance().lookup(
				IInvestRedeemMaintain.class);
		return query.query(queryScheme);
	}

}