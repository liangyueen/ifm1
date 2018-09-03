package nc.impl.pub.ace;

import nc.bs.ifm.income.ace.bp.AceInvestIncomeInsertBP;
import nc.bs.ifm.income.ace.bp.AceInvestIncomeUpdateBP;
import nc.bs.ifm.income.ace.bp.AceInvestIncomeDeleteBP;
import nc.bs.ifm.income.ace.bp.AceInvestIncomeSendApproveBP;
import nc.bs.ifm.income.ace.bp.AceInvestIncomeUnSendApproveBP;
import nc.bs.ifm.income.ace.bp.AceInvestIncomeApproveBP;
import nc.bs.ifm.income.ace.bp.AceInvestIncomeUnApproveBP;
import nc.impl.pubapp.pattern.data.bill.BillLazyQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceInvestIncomePubServiceImpl {
	// 新增
	public AggInvestIncomeVO[] pubinsertBills(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<AggInvestIncomeVO> transferTool = new BillTransferTool<AggInvestIncomeVO>(
					clientFullVOs);
			// 调用BP
			AceInvestIncomeInsertBP action = new AceInvestIncomeInsertBP();
			AggInvestIncomeVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceInvestIncomeDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public AggInvestIncomeVO[] pubupdateBills(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<AggInvestIncomeVO> transferTool = new BillTransferTool<AggInvestIncomeVO>(
					clientFullVOs);
			AceInvestIncomeUpdateBP bp = new AceInvestIncomeUpdateBP();
			AggInvestIncomeVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public AggInvestIncomeVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		AggInvestIncomeVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<AggInvestIncomeVO> query = new BillLazyQuery<AggInvestIncomeVO>(
					AggInvestIncomeVO.class);
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
	public AggInvestIncomeVO[] pubsendapprovebills(
			AggInvestIncomeVO[] clientFullVOs, AggInvestIncomeVO[] originBills)
			throws BusinessException {
		AceInvestIncomeSendApproveBP bp = new AceInvestIncomeSendApproveBP();
		AggInvestIncomeVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public AggInvestIncomeVO[] pubunsendapprovebills(
			AggInvestIncomeVO[] clientFullVOs, AggInvestIncomeVO[] originBills)
			throws BusinessException {
		AceInvestIncomeUnSendApproveBP bp = new AceInvestIncomeUnSendApproveBP();
		AggInvestIncomeVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public AggInvestIncomeVO[] pubapprovebills(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceInvestIncomeApproveBP bp = new AceInvestIncomeApproveBP();
		AggInvestIncomeVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public AggInvestIncomeVO[] pubunapprovebills(AggInvestIncomeVO[] clientFullVOs,
			AggInvestIncomeVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceInvestIncomeUnApproveBP bp = new AceInvestIncomeUnApproveBP();
		AggInvestIncomeVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}