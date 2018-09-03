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
	// 新增
	public AggInvestRedeemVO[] pubinsertBills(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggInvestRedeemVO> transferTool = new BillTransferTool<AggInvestRedeemVO>(
					clientFullVOs);
			// 调用BP
			AceInvestRedeemInsertBP action = new AceInvestRedeemInsertBP();
			AggInvestRedeemVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceInvestRedeemDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggInvestRedeemVO[] pubupdateBills(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggInvestRedeemVO> transferTool = new BillTransferTool<AggInvestRedeemVO>(
					clientFullVOs);
			AceInvestRedeemUpdateBP bp = new AceInvestRedeemUpdateBP();
			AggInvestRedeemVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
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
	 * 由子类实现，查询之前对queryScheme进行加工，加入自己的逻辑
	 * 
	 * @param queryScheme
	 */
	protected void preQuery(IQueryScheme queryScheme) {
		// 查询之前对queryScheme进行加工，加入自己的逻辑
	}

	// 提交
	public AggInvestRedeemVO[] pubsendapprovebills(
			AggInvestRedeemVO[] clientFullVOs, AggInvestRedeemVO[] originBills)
			throws BusinessException {
		AceInvestRedeemSendApproveBP bp = new AceInvestRedeemSendApproveBP();
		AggInvestRedeemVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggInvestRedeemVO[] pubunsendapprovebills(
			AggInvestRedeemVO[] clientFullVOs, AggInvestRedeemVO[] originBills)
			throws BusinessException {
		AceInvestRedeemUnSendApproveBP bp = new AceInvestRedeemUnSendApproveBP();
		AggInvestRedeemVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggInvestRedeemVO[] pubapprovebills(AggInvestRedeemVO[] clientFullVOs,
			AggInvestRedeemVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceInvestRedeemApproveBP bp = new AceInvestRedeemApproveBP();
		AggInvestRedeemVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

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