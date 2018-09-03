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
	// 新增
	public AggInvestApplyVO[] pubinsertBills(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggInvestApplyVO> transferTool = new BillTransferTool<AggInvestApplyVO>(
					clientFullVOs);
			// 调用BP
			AceApplyInsertBP action = new AceApplyInsertBP();
			AggInvestApplyVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceApplyDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggInvestApplyVO[] pubupdateBills(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggInvestApplyVO> transferTool = new BillTransferTool<AggInvestApplyVO>(
					clientFullVOs);
			AceApplyUpdateBP bp = new AceApplyUpdateBP();
			AggInvestApplyVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
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
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggInvestApplyVO[] pubsendapprovebills(
			AggInvestApplyVO[] clientFullVOs, AggInvestApplyVO[] originBills)
			throws BusinessException {
		AceApplySendApproveBP bp = new AceApplySendApproveBP();
		AggInvestApplyVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggInvestApplyVO[] pubunsendapprovebills(
			AggInvestApplyVO[] clientFullVOs, AggInvestApplyVO[] originBills)
			throws BusinessException {
		AceApplyUnSendApproveBP bp = new AceApplyUnSendApproveBP();
		AggInvestApplyVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggInvestApplyVO[] pubapprovebills(AggInvestApplyVO[] clientFullVOs,
			AggInvestApplyVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceApplyApproveBP bp = new AceApplyApproveBP();
		AggInvestApplyVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

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