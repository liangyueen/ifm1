package nc.impl.ifm;

import nc.impl.pub.ace.AceInvestRedeemPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.itf.ifm.IInvestRedeemMaintain;
import nc.vo.pub.BusinessException;

public class InvestRedeemMaintainImpl extends AceInvestRedeemPubServiceImpl
		implements IInvestRedeemMaintain {

	@Override
	public void delete(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestRedeemVO[] insert(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestRedeemVO[] update(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestRedeemVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggInvestRedeemVO[] save(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestRedeemVO[] unsave(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestRedeemVO[] approve(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestRedeemVO[] unapprove(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
