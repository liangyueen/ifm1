package nc.itf.ifm;

import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.BusinessException;

public interface IIncomeCtrlService {
	/**
	 * 赎回完成后，写入收益数据
	 * @param redeemVO
	 * @return boolean 成功返回true
	 */
	public boolean WriteIncomeBill(InvestRedeemVO redeemVO);
	
	/**
	 * 赎回撤销后，同步删除写入的收益数据，前提是收益处于可删除状态
	 * @param redeemVO
	 * @throws BusinessException 
	 */
	public void RewriteIncomeBill(InvestRedeemVO redeemVO) throws BusinessException;
	/**
	 * 根据传入的申购编号判断是否可以对申购的单子进行弃审（为true时表示收益表中没有相应记录，可以弃审）
	 * @param apply_vbillno
	 * @return boolean 
	 */
	public boolean isSaved(String apply_vbillno);
}
