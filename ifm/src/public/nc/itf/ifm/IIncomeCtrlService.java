package nc.itf.ifm;

import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.BusinessException;

public interface IIncomeCtrlService {
	/**
	 * �����ɺ�д����������
	 * @param redeemVO
	 * @return boolean �ɹ�����true
	 */
	public boolean WriteIncomeBill(InvestRedeemVO redeemVO);
	
	/**
	 * ��س�����ͬ��ɾ��д����������ݣ�ǰ�������洦�ڿ�ɾ��״̬
	 * @param redeemVO
	 * @throws BusinessException 
	 */
	public void RewriteIncomeBill(InvestRedeemVO redeemVO) throws BusinessException;
	/**
	 * ���ݴ�����깺����ж��Ƿ���Զ��깺�ĵ��ӽ�������Ϊtrueʱ��ʾ�������û����Ӧ��¼����������
	 * @param apply_vbillno
	 * @return boolean 
	 */
	public boolean isSaved(String apply_vbillno);
}
