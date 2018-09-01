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
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public abstract class AceInvestIncomePubServiceImpl {
	// 新增
	public InvestIncomeVO[] pubinsertBills(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException {
		try {
			// 数据库中数据和前台传递过来的差异VO合并后的结果
			BillTransferTool<InvestIncomeVO> transferTool = new BillTransferTool<InvestIncomeVO>(
					clientFullVOs);
			// 调用BP
			AceInvestIncomeInsertBP action = new AceInvestIncomeInsertBP();
			InvestIncomeVO[] retvos = action.insert(clientFullVOs);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除
	public void pubdeleteBills(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException {
		try {
			// 调用BP
			new AceInvestIncomeDeleteBP().delete(clientFullVOs);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
	}

	// 修改
	public InvestIncomeVO[] pubupdateBills(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException {
		try {
			// 加锁 + 检查ts
			BillTransferTool<InvestIncomeVO> transferTool = new BillTransferTool<InvestIncomeVO>(
					clientFullVOs);
			AceInvestIncomeUpdateBP bp = new AceInvestIncomeUpdateBP();
			InvestIncomeVO[] retvos = bp.update(clientFullVOs, originBills);
			// 构造返回数据
			return transferTool.getBillForToClient(retvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	public InvestIncomeVO[] pubquerybills(IQueryScheme queryScheme)
			throws BusinessException {
		InvestIncomeVO[] bills = null;
		try {
			this.preQuery(queryScheme);
			BillLazyQuery<InvestIncomeVO> query = new BillLazyQuery<InvestIncomeVO>(
					InvestIncomeVO.class);
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
	public InvestIncomeVO[] pubsendapprovebills(
			InvestIncomeVO[] clientFullVOs, InvestIncomeVO[] originBills)
			throws BusinessException {
		AceInvestIncomeSendApproveBP bp = new AceInvestIncomeSendApproveBP();
		InvestIncomeVO[] retvos = bp.sendApprove(clientFullVOs, originBills);
		return retvos;
	}

	// 收回
	public InvestIncomeVO[] pubunsendapprovebills(
			InvestIncomeVO[] clientFullVOs, InvestIncomeVO[] originBills)
			throws BusinessException {
		AceInvestIncomeUnSendApproveBP bp = new AceInvestIncomeUnSendApproveBP();
		InvestIncomeVO[] retvos = bp.unSend(clientFullVOs, originBills);
		return retvos;
	};

	// 审批
	public InvestIncomeVO[] pubapprovebills(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceInvestIncomeApproveBP bp = new AceInvestIncomeApproveBP();
		InvestIncomeVO[] retvos = bp.approve(clientFullVOs, originBills);
		return retvos;
	}

	// 弃审

	public InvestIncomeVO[] pubunapprovebills(InvestIncomeVO[] clientFullVOs,
			InvestIncomeVO[] originBills) throws BusinessException {
		for (int i = 0; clientFullVOs != null && i < clientFullVOs.length; i++) {
			clientFullVOs[i].getParentVO().setStatus(VOStatus.UPDATED);
		}
		AceInvestIncomeUnApproveBP bp = new AceInvestIncomeUnApproveBP();
		InvestIncomeVO[] retvos = bp.unApprove(clientFullVOs, originBills);
		return retvos;
	}

}