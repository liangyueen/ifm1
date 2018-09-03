package nc.impl.pub.ace;

import nc.bs.ifm.redeem.ace.bp.AceInvestRedeemInsertBP;
import nc.bs.ifm.redeem.ace.bp.AceInvestRedeemUpdateBP;
import nc.bs.ifm.redeem.ace.bp.AceInvestRedeemDeleteBP;
import nc.bs.ifm.redeem.ace.bp.AceInvestRedeemSendApproveBP;
import nc.bs.ifm.redeem.ace.bp.AceInvestRedeemUnSendApproveBP;
import nc.bs.ifm.redeem.ace.bp.AceInvestRedeemApproveBP;
import nc.bs.ifm.redeem.ace.bp.AceInvestRedeemUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceInvestRedeemPubServiceImpl {
	// ����
	public AggInvestRedeemVO[] pubinsertBills(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<AggInvestRedeemVO> transferTool = new BillTransferTool<AggInvestRedeemVO>(
					clientFullVOs);
			// ����BP
			AceInvestRedeemInsertBP action = new AceInvestRedeemInsertBP();
			AggInvestRedeemVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceInvestRedeemDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public AggInvestRedeemVO[] pubupdateBills(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<AggInvestRedeemVO> transferTool = new BillTransferTool<AggInvestRedeemVO>(
					clientFullVOs);
			AceInvestRedeemUpdateBP bp = new AceInvestRedeemUpdateBP();
			AggInvestRedeemVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggInvestRedeemVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggInvestRedeemVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggInvestRedeemVO> query = new BillLazyQuery<AggInvestRedeemVO>(
					AggInvestRedeemVO.class);
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
	public AggInvestRedeemVO[] pubsendapprovebills(
			AggInvestRedeemVO[] clientFullVOs, AggInvestRedeemVO[] originBills)
			throws BusinessException {
		AceInvestRedeemSendApproveBP bp = new AceInvestRedeemSendApproveBP();
		AggInvestRedeemVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public AggInvestRedeemVO[] pubunsendapprovebills(
			AggInvestRedeemVO[] clientFullVOs, AggInvestRedeemVO[] originBills)
			throws BusinessException {
		AceInvestRedeemUnSendApproveBP bp = new AceInvestRedeemUnSendApproveBP();
		AggInvestRedeemVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public AggInvestRedeemVO[] pubapprovebills(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceInvestRedeemApproveBP bp = new AceInvestRedeemApproveBP();
		AggInvestRedeemVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public AggInvestRedeemVO[] pubunapprovebills(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceInvestRedeemUnApproveBP bp = new AceInvestRedeemUnApproveBP();
		AggInvestRedeemVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}