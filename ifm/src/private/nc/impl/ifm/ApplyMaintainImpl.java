package nc.impl.ifm;

import nc.impl.pub.ace.AceApplyPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.itf.ifm.IApplyMaintain;
import nc.vo.pub.BusinessException;

public class ApplyMaintainImpl extends AceApplyPubServiceImpl
		implements IApplyMaintain {

	@Override
	public void delete(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public InvestApplyVO[] insert(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public InvestApplyVO[] update(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public InvestApplyVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public InvestApplyVO[] save(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public InvestApplyVO[] unsave(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public InvestApplyVO[] approve(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public InvestApplyVO[] unapprove(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
