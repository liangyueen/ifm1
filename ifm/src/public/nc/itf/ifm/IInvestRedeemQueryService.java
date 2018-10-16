package nc.itf.ifm;


import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;

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
	
	/**
	 * ��ѯ����ʵ����Ϣ
	 * @return
	 * @throws BusinessException
	 */
	public SuperVO[] querySuperVOByCondition(String condition, Class voClass) throws BusinessException ;
	
	
	/**
	 * ����������ѯִ�����������
	 * @return
	 * @throws BusinessException
	 */
	public AggInvestRedeemVO[] getAggVOsByPKs(String... pks) throws BusinessException ;
	
}
