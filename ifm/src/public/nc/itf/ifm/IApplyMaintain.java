package nc.itf.ifm;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.pub.BusinessException;

public interface IApplyMaintain {

	public void delete(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException;

	public InvestApplyVO[] insert(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException;

	public InvestApplyVO[] update(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException;

	public InvestApplyVO[] query(IQueryScheme queryScheme)
			throws BusinessException;

	public InvestApplyVO[] save(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException;

	public InvestApplyVO[] unsave(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException;

	public InvestApplyVO[] approve(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException;

	public InvestApplyVO[] unapprove(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException;
}
