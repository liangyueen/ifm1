package nc.bs.ifm.income.ace.bp;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.bs.ifm.income.rule.RegisterUnWriteBankAccAfterRule;
import nc.bs.ifm.income.rule.TallyUnSendIncomeProcessVoucherRule;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.VOStatus;

/**
 * ��׼���������BP
 */
public class AceInvestIncomeUnApproveBP {

	public AggInvestIncomeVO[] unApprove(AggInvestIncomeVO[] clientBills,
			AggInvestIncomeVO[] originBills) {
		
		UpdateBPTemplate<AggInvestIncomeVO> bp = new UpdateBPTemplate<AggInvestIncomeVO>(
				ApplyPluginPoint.UNAPPROVE);
		for (AggInvestIncomeVO clientBill : clientBills) {
//			clientBill.getParentVO().setAttributeValue("billstatus",
//					IncomeBillStatusEnum.NOAUDIT.value());//δ����
//			clientBill.getParentVO().setAttributeValue("vbillstatus",
//					BillStatusEnum.APPROVING.value());//��������
//			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
			clientBill.getParentVO().setBillstatus(VOStatus.DELETED);
		}
//		BillUpdate<AggInvestIncomeVO> update = new BillUpdate<AggInvestIncomeVO>();
//		AggInvestIncomeVO[] returnVos = update.update(clientBills, originBills);
		this.addAfterRule(bp.getAroundProcesser());
		AggInvestIncomeVO[] returnVos = bp.update(clientBills, originBills);
		this.addAfterRule(clientBills);
		return returnVos;
	}
	/**
	 * �޸ĺ����
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
	 * ���������
	 * 
	 * @param processor
	 */

	private void addAfterRule(
			CompareAroundProcesser<AggInvestIncomeVO> aroundProcesser) {
		IRule<AggInvestIncomeVO> rwRule = new RegisterUnWriteBankAccAfterRule();
		aroundProcesser.addAfterRule(rwRule);
	}
}
