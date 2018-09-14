package nc.itf.ifm;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.pub.BusinessException;

public interface IApplyMaintain {

	public void delete(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException;

	public AggInvestApplyVO[] insert(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException;

	public AggInvestApplyVO[] update(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException;

	public AggInvestApplyVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public AggInvestApplyVO[] save(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException;

	public AggInvestApplyVO[] unsave(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException;

	public AggInvestApplyVO[] approve(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException;

	public AggInvestApplyVO[] unapprove(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException;
	
}
