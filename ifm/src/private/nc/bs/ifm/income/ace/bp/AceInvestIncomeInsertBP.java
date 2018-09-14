package nc.bs.ifm.income.ace.bp;

import nc.bs.ifm.income.plugin.bpplugin.InvestIncomePluginPoint;
import nc.bs.pub.rule.CCBillOrgVRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.ifm.income.AggInvestIncomeVO;

/**
 * ��׼��������BP
 */
public class AceInvestIncomeInsertBP {

	public AggInvestIncomeVO[] insert(AggInvestIncomeVO[] bills) {

		InsertBPTemplate<AggInvestIncomeVO> bp = new InsertBPTemplate<AggInvestIncomeVO>(
				InvestIncomePluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggInvestIncomeVO> processor) {
		// TODO ���������
		IRule<AggInvestIncomeVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("3643");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processor.addAfterRule(rule);
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggInvestIncomeVO> processer) {
		// TODO ����ǰ����
		IRule<AggInvestIncomeVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
		rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("3643");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
		
//		// �����ֶγ��ȼ�����
//		IRule<AggInvestIncomeVO> lengthCheckRule = new FieldLengthCheckRule();
//		processer.addBeforeRule(lengthCheckRule);
//
//
//		// ��֯��汾
//		IRule<AggInvestIncomeVO> orgRule = new CCBillOrgVRule();
//		processer.addBeforeRule(orgRule);
//		
	}
}
