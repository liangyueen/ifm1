package nc.itf.ifm;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.BusinessException;

public interface IInvestIncomeMaintain {

	public void delete(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException;

	public InvestIncomeVO[] insert(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException;

	public InvestIncomeVO[] update(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException;

	public InvestIncomeVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public InvestIncomeVO[] save(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException;

	public InvestIncomeVO[] unsave(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException;

	public InvestIncomeVO[] approve(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException;

	public InvestIncomeVO[] unapprove(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException;
}
