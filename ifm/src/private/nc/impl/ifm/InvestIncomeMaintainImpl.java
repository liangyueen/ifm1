package nc.impl.ifm;

import nc.impl.pub.ace.AceInvestIncomePubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.itf.ifm.IInvestIncomeMaintain;
import nc.vo.pub.BusinessException;

public class InvestIncomeMaintainImpl extends AceInvestIncomePubServiceImpl
		implements IInvestIncomeMaintain {

	@Override
	public void delete(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public InvestIncomeVO[] insert(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public InvestIncomeVO[] update(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public InvestIncomeVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public InvestIncomeVO[] save(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public InvestIncomeVO[] unsave(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public InvestIncomeVO[] approve(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public InvestIncomeVO[] unapprove(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
