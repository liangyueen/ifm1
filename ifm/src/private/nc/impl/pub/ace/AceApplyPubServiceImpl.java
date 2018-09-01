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
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceApplyPubServiceImpl {
	// ����
	public InvestApplyVO[] pubinsertBills(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<InvestApplyVO> transferTool = new BillTransferTool<InvestApplyVO>(
					clientFullVOs);
			// ����BP
			AceApplyInsertBP action = new AceApplyInsertBP();
			InvestApplyVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceApplyDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public InvestApplyVO[] pubupdateBills(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<InvestApplyVO> transferTool = new BillTransferTool<InvestApplyVO>(
					clientFullVOs);
			AceApplyUpdateBP bp = new AceApplyUpdateBP();
			InvestApplyVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public InvestApplyVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		InvestApplyVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<InvestApplyVO> query = new BillLazyQuery<InvestApplyVO>(
					InvestApplyVO.class);
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
	public InvestApplyVO[] pubsendapprovebills(
			InvestApplyVO[] clientFullVOs, InvestApplyVO[] originBills)
			throws BusinessException {
		AceApplySendApproveBP bp = new AceApplySendApproveBP();
		InvestApplyVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public InvestApplyVO[] pubunsendapprovebills(
			InvestApplyVO[] clientFullVOs, InvestApplyVO[] originBills)
			throws BusinessException {
		AceApplyUnSendApproveBP bp = new AceApplyUnSendApproveBP();
		InvestApplyVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public InvestApplyVO[] pubapprovebills(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceApplyApproveBP bp = new AceApplyApproveBP();
		InvestApplyVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public InvestApplyVO[] pubunapprovebills(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceApplyUnApproveBP bp = new AceApplyUnApproveBP();
		InvestApplyVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}