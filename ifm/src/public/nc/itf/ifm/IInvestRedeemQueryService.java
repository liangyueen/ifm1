package nc.itf.ifm;


import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.BusinessException;

public interface IInvestRedeemQueryService {
	/**
	 * ��������:
	 * @return AggBankProtocolVO[]
	 */
	public AggInvestRedeemVO[] queryRedeemByPks (String[] pks) throws BusinessException;
	/**
	 * ��������:
	 * @return String[]
	 */
	public String[] queryRedeemBySchema (nc.ui.querytemplate.querytree.IQueryScheme querySchema) throws BusinessException;
	
	/**
	 * ��֤���ݺ��Ƿ��ظ�
	 * @param pk_bankprotocol
	 * @param contractcode
	 * @return ture: �ظ���false�����ظ�
	 * @throws BusinessException
	 */
	Boolean isBillNoDuplicate(String[] pk_redeemcol, String[] redeemcode) throws BusinessException;
}
