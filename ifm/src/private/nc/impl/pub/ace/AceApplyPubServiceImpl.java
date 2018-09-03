package nc.impl.pub.ace;

import nc.bs.ifm.apply.ace.bp.AceApplyInsertBP;
import nc.bs.ifm.apply.ace.bp.AceApplyUpdateBP;
import nc.bs.ifm.apply.ace.bp.AceApplyDeleteBP;
import nc.bs.ifm.apply.ace.bp.AceApplySendApproveBP;
import nc.bs.ifm.apply.ace.bp.AceApplyUnSendApproveBP;
import nc.bs.ifm.apply.ace.bp.AceApplyApproveBP;
import nc.bs.ifm.apply.ace.bp.AceApplyUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceApplyPubServiceImpl {
	// ����
	public AggInvestApplyVO[] pubinsertBills(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggInvestApplyVO> transferTool = new BillTransferTool<AggInvestApplyVO>(
					clientFullVOs);
			// ����BP
			AceApplyInsertBP action = new AceApplyInsertBP();
			AggInvestApplyVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceApplyDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggInvestApplyVO[] pubupdateBills(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggInvestApplyVO> transferTool = new BillTransferTool<AggInvestApplyVO>(
					clientFullVOs);
			AceApplyUpdateBP bp = new AceApplyUpdateBP();
			AggInvestApplyVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggInvestApplyVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggInvestApplyVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggInvestApplyVO> query = new BillLazyQuery<AggInvestApplyVO>(
					AggInvestApplyVO.class);
			bills = query.query(queryScheme, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return bills;
	}

	/**
	 * ������ʵ�֣���ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// ��ѯ֮ǰ��queryScheme���мӹ��������Լ����߼�
	}

	// �ύ
	public AggInvestApplyVO[] pubsendapprovebills(
			AggInvestApplyVO[] clientFullVOs, AggInvestApplyVO[] originBills)
			throws BusinessException {
		AceApplySendApproveBP bp = new AceApplySendApproveBP();
		AggInvestApplyVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggInvestApplyVO[] pubunsendapprovebills(
			AggInvestApplyVO[] clientFullVOs, AggInvestApplyVO[] originBills)
			throws BusinessException {
		AceApplyUnSendApproveBP bp = new AceApplyUnSendApproveBP();
		AggInvestApplyVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggInvestApplyVO[] pubapprovebills(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceApplyApproveBP bp = new AceApplyApproveBP();
		AggInvestApplyVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggInvestApplyVO[] pubunapprovebills(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceApplyUnApproveBP bp = new AceApplyUnApproveBP();
		AggInvestApplyVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}