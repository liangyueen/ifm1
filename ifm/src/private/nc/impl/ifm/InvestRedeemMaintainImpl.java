package nc.impl.ifm;

import nc.impl.pub.ace.AceInvestRedeemPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.itf.ifm.IInvestRedeemMaintain;
import nc.vo.pub.BusinessException;

public class InvestRedeemMaintainImpl extends AceInvestRedeemPubServiceImpl
		implements IInvestRedeemMaintain {

	@Override
	public void delete(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public InvestRedeemVO[] insert(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public InvestRedeemVO[] update(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public InvestRedeemVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public InvestRedeemVO[] save(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public InvestRedeemVO[] unsave(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public InvestRedeemVO[] approve(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public InvestRedeemVO[] unapprove(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
