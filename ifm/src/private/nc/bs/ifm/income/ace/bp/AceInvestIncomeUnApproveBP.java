package nc.bs.ifm.income.ace.bp;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.bs.ifm.income.rule.InvestIncomeUnApproveTbbRule;
import nc.bs.ifm.income.rule.RegisterUnWriteBankAccAfterRule;
import nc.bs.ifm.income.rule.TallyUnSendIncomeProcessVoucherRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.ifm.IncomeBillStatusEnum;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceInvestIncomeUnApproveBP {

	public AggInvestIncomeVO[] unApprove(AggInvestIncomeVO[] clientBills,
			AggInvestIncomeVO[] originBills) {
		
		UpdateBPTemplate<AggInvestIncomeVO> bp = new UpdateBPTemplate<AggInvestIncomeVO>(
				ApplyPluginPoint.UNAPPROVE);
		for (AggInvestIncomeVO clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue("billstatus",
					IncomeBillStatusEnum.NOAUDIT.value());//未审批
//			clientBill.getParentVO().setAttributeValue("vbillstatus",
//					BillStatusEnum.APPROVING.value());//正在审批
//			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
			clientBill.getParentVO().setBillstatus(VOStatus.DELETED);
		}
//		BillUpdate<AggInvestIncomeVO> update = new BillUpdate<AggInvestIncomeVO>();
//		AggInvestIncomeVO[] returnVos = update.update(clientBills, originBills);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		AggInvestIncomeVO[] returnVos = bp.update(clientBills, originBills);
		this.addAfterRule(clientBills);
		return returnVos;
	}
	/**
	 * 执行前规则
	 * 
	 * @param aroundProcesser
	 */
	private void addBeforeRule(
			CompareAroundProcesser<AggInvestIncomeVO> aroundProcesser) {
		/** 进行预算控制，释放预占数,进行预算控制，回写登记单执行数 **/
		IRule<AggInvestIncomeVO> tbbRule = new InvestIncomeUnApproveTbbRule();
		aroundProcesser.addBeforeRule(tbbRule);
	}
	/**
	 * 修改后规则
	 * 
	 * @param processor
	 */
	private void addAfterRule(AggInvestIncomeVO[] vos) {
		IRule<AggInvestIncomeVO> rule = null;
		rule = new TallyUnSendIncomeProcessVoucherRule();
		for (AggInvestIncomeVO clientBill : vos) {
			InvestIncomeVO vo = clientBill.getParentVO();
			rule.process(vos);
		}
	}
	/**
	 * 新增后规则
	 * 
	 * @param processor
	 */

	private void addAfterRule(
			CompareAroundProcesser<AggInvestIncomeVO> aroundProcesser) {
		IRule<AggInvestIncomeVO> rwRule = new RegisterUnWriteBankAccAfterRule();
		aroundProcesser.addAfterRule(rwRule);
	}
}
