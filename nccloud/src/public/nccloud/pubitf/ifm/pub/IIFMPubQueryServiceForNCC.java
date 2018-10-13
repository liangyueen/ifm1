package nccloud.pubitf.ifm.pub;

import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 公共查询类
 * @author majfd
 *
 */
public interface IIFMPubQueryServiceForNCC {
	/**
	 * 按照单据状态分组查询
	 * @param wherePart
	 * @param queryScheme
	 * @return
	 */
	public Object queryBillStatusGroupByQueryScheme (String  wherePart,IQueryScheme queryScheme);

}
