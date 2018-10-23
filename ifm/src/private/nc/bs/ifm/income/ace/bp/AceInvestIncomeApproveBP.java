package nc.bs.ifm.income.ace.bp;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.bs.ifm.income.rule.InvestIncomeApproveTbbRule;
import nc.bs.ifm.income.rule.RegisterWriteBankAccAfterRule;
import nc.bs.ifm.income.rule.TallySendIncomeProcessVoucherRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.ifm.IncomeBillStatusEnum;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * 标准单据审核的BP
 */
public class AceInvestIncomeApproveBP {

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 */
	public AggInvestIncomeVO[] approve(AggInvestIncomeVO[] clientBills,
			AggInvestIncomeVO[] originBills) {
		UpdateBPTemplate<AggInvestIncomeVO> bp = new UpdateBPTemplate<AggInvestIncomeVO>(
				ApplyPluginPoint.APPROVE);
		for (AggInvestIncomeVO clientBill : clientBills) {	
			clientBill.getParentVO().setAttributeValue("billstatus",
					IncomeBillStatusEnum.FINISHED.value());//已完成
			clientBill.getParentVO().setAttributeValue("vbillstatus",
					BillStatusEnum.APPROVED.value());//已审批
//			clientBill.getParentVO().setBillstatus(VOStatus.NEW);
		}
//		BillUpdate<AggInvestIncomeVO> update = new BillUpdate<AggInvestIncomeVO>();
//		AggInvestIncomeVO[] returnVos = update.update(clientBills, originBills);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		AggInvestIncomeVO[] returnVos = bp.update(clientBills, originBills);
		//this.addAfterRule(returnVos);
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
		IRule<AggInvestIncomeVO> tbbRule = new InvestIncomeApproveTbbRule();
		aroundProcesser.addBeforeRule(tbbRule);
	}
	/**
	 * 新增后规则
	 * 
	 * @param processor
	 */

	private void addAfterRule(
			CompareAroundProcesser<AggInvestIncomeVO> aroundProcesser) {
		IRule<AggInvestIncomeVO> rwRule = new RegisterWriteBankAccAfterRule();
		IRule<AggInvestIncomeVO> rule = new TallySendIncomeProcessVoucherRule();
		aroundProcesser.addAfterRule(rwRule);
		aroundProcesser.addAfterRule(rule);
	}
	
	/*private void addAfterRule(AggInvestIncomeVO[] vos) {
		IRule<AggInvestIncomeVO> rule = null;
		rule = new TallySendIncomeProcessVoucherRule();
		rule.process(vos);
		
	}*/

}
