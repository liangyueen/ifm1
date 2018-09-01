package nc.itf.ifm;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.BusinessException;

public interface IInvestRedeemMaintain {

	public void delete(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException;

	public InvestRedeemVO[] insert(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException;

	public InvestRedeemVO[] update(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException;

	public InvestRedeemVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public InvestRedeemVO[] save(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException;

	public InvestRedeemVO[] unsave(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException;

	public InvestRedeemVO[] approve(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException;

	public InvestRedeemVO[] unapprove(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException;
}
