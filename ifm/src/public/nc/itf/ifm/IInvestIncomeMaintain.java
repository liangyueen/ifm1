package nc.itf.ifm;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.BusinessException;

public interface IInvestIncomeMaintain {

	public void delete(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException;

	public AggInvestIncomeVO[] insert(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException;

	public AggInvestIncomeVO[] update(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException;

	public AggInvestIncomeVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggInvestIncomeVO[] save(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException;

	public AggInvestIncomeVO[] unsave(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException;

	public AggInvestIncomeVO[] approve(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException;

	public AggInvestIncomeVO[] unapprove(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException;
}
