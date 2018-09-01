package nc.ui.ifm.income.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.ifm.IInvestIncomeMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceInvestIncomeMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IInvestIncomeMaintain query = NCLocator.getInstance().lookup(
				IInvestIncomeMaintain.class);
		return query.query(queryScheme);
	}

}