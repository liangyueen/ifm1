package nc.impl.ifm;

import nc.impl.pub.ace.AceInvestIncomePubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.itf.ifm.IInvestIncomeMaintain;
import nc.vo.pub.BusinessException;

public class InvestIncomeMaintainImpl extends AceInvestIncomePubServiceImpl
		implements IInvestIncomeMaintain {

	@Override
	public void delete(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestIncomeVO[] insert(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestIncomeVO[] update(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestIncomeVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggInvestIncomeVO[] save(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestIncomeVO[] unsave(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestIncomeVO[] approve(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestIncomeVO[] unapprove(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
