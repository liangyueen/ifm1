package nc.ui.ifm.apply.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.ifm.IApplyMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceApplyMaintainProxy implements IQueryService {
	@Override
	public Object[] queryByQueryScheme(IQueryScheme queryScheme)
			throws Exception {
		IApplyMaintain query = NCLocator.getInstance().lookup(
				IApplyMaintain.class);
		return query.query(queryScheme);
	}

}