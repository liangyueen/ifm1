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
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceInvestRedeemPubServiceImpl {
	// ����
	public InvestRedeemVO[] pubinsertBills(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException {
		try {
			// ���ݿ������ݺ�ǰ̨���ݹ����Ĳ���VO�ϲ���Ľ��
			BillTransferTool<InvestRedeemVO> transferTool = new BillTransferTool<InvestRedeemVO>(
					clientFullVOs);
			// ����BP
			AceInvestRedeemInsertBP action = new AceInvestRedeemInsertBP();
			InvestRedeemVO[] retvos = action.insert(clientFullVOs);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// ɾ��
	public void pubdeleteBills(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException {
		try {
			// ����BP
			new AceInvestRedeemDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// �޸�
	public InvestRedeemVO[] pubupdateBills(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException {
		try {
			// ���� + ���ts
			BillTransferTool<InvestRedeemVO> transferTool = new BillTransferTool<InvestRedeemVO>(
					clientFullVOs);
			AceInvestRedeemUpdateBP bp = new AceInvestRedeemUpdateBP();
			InvestRedeemVO[] retvos = bp.update(clientFullVOs, originBills);
			// ���췵������
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public InvestRedeemVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		InvestRedeemVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<InvestRedeemVO> query = new BillLazyQuery<InvestRedeemVO>(
					InvestRedeemVO.class);
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
	public InvestRedeemVO[] pubsendapprovebills(
			InvestRedeemVO[] clientFullVOs, InvestRedeemVO[] originBills)
			throws BusinessException {
		AceInvestRedeemSendApproveBP bp = new AceInvestRedeemSendApproveBP();
		InvestRedeemVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// �ջ�
	public InvestRedeemVO[] pubunsendapprovebills(
			InvestRedeemVO[] clientFullVOs, InvestRedeemVO[] originBills)
			throws BusinessException {
		AceInvestRedeemUnSendApproveBP bp = new AceInvestRedeemUnSendApproveBP();
		InvestRedeemVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// ����
	public InvestRedeemVO[] pubapprovebills(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceInvestRedeemApproveBP bp = new AceInvestRedeemApproveBP();
		InvestRedeemVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// ����

	public InvestRedeemVO[] pubunapprovebills(InvestRedeemVO[] clientFullVOs,
			InvestRedeemVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceInvestRedeemUnApproveBP bp = new AceInvestRedeemUnApproveBP();
		InvestRedeemVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}