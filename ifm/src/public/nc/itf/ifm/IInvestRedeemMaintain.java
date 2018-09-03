package nc.itf.ifm;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.BusinessException;

public interface IInvestRedeemMaintain {

	public void delete(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException;

	public AggInvestRedeemVO[] insert(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException;

	public AggInvestRedeemVO[] update(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException;

	public AggInvestRedeemVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggInvestRedeemVO[] save(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException;

	public AggInvestRedeemVO[] unsave(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException;

	public AggInvestRedeemVO[] approve(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException;

	public AggInvestRedeemVO[] unapprove(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException;
}
