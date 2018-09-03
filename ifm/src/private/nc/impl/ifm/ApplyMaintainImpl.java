package nc.impl.ifm;

import nc.impl.pub.ace.AceApplyPubServiceImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.itf.ifm.IApplyMaintain;
import nc.vo.pub.BusinessException;

public class ApplyMaintainImpl extends AceApplyPubServiceImpl
		implements IApplyMaintain {

	@Override
	public void delete(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestApplyVO[] insert(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestApplyVO[] update(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestApplyVO[] query(IQueryScheme queryScheme)
			throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggInvestApplyVO[] save(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestApplyVO[] unsave(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestApplyVO[] approve(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggInvestApplyVO[] unapprove(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

}
