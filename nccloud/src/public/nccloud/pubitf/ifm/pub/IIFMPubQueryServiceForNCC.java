package nccloud.pubitf.ifm.pub;

import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * ������ѯ��
 * @author majfd
 *
 */
public interface IIFMPubQueryServiceForNCC {
	/**
	 * ���յ���״̬�����ѯ
	 * @param wherePart
	 * @param queryScheme
	 * @return
	 */
	public Object queryBillStatusGroupByQueryScheme (String  wherePart,IQueryScheme queryScheme);

}
