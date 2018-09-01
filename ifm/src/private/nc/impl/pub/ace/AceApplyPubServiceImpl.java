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
	// 新增
	public InvestApplyVO[] pubinsertBills(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<InvestApplyVO> transferTool = new BillTransferTool<InvestApplyVO>(
					clientFullVOs);
			// 调用BP
			AceApplyInsertBP action = new AceApplyInsertBP();
			InvestApplyVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceApplyDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public InvestApplyVO[] pubupdateBills(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<InvestApplyVO> transferTool = new BillTransferTool<InvestApplyVO>(
					clientFullVOs);
			AceApplyUpdateBP bp = new AceApplyUpdateBP();
			InvestApplyVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
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
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public InvestApplyVO[] pubsendapprovebills(
			InvestApplyVO[] clientFullVOs, InvestApplyVO[] originBills)
			throws BusinessException {
		AceApplySendApproveBP bp = new AceApplySendApproveBP();
		InvestApplyVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public InvestApplyVO[] pubunsendapprovebills(
			InvestApplyVO[] clientFullVOs, InvestApplyVO[] originBills)
			throws BusinessException {
		AceApplyUnSendApproveBP bp = new AceApplyUnSendApproveBP();
		InvestApplyVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public InvestApplyVO[] pubapprovebills(InvestApplyVO[] clientFullVOs,
			InvestApplyVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceApplyApproveBP bp = new AceApplyApproveBP();
		InvestApplyVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

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