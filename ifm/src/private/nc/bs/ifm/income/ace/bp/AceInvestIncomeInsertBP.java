package nc.bs.ifm.income.ace.bp;

import nc.bs.ifm.income.plugin.bpplugin.InvestIncomePluginPoint;
import nc.bs.ifm.income.rule.IncomeBillCodeCheckRule;
import nc.bs.ifm.income.rule.IncomeBillOrgVRule;
import nc.bs.ifm.income.rule.IncomeCheckOrgInitDateRule;
import nc.bs.ifm.income.rule.IncomeCheckRule;
import nc.bs.ifm.income.rule.IncomeInsertDataRule;
import nc.bs.pubapp.pub.rule.CreateBillCodeRule;
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
//		// TODO ���������
//		IRule<AggInvestIncomeVO> rule = null;
//		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
//		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("3643");
//		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
//				.setCodeItem("vbillno");
//		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
//				.setGroupItem("pk_group");
//		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
//		processor.addAfterRule(rule);
	}

	/**
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggInvestIncomeVO> processer) {
		// �������ݹ���
		@SuppressWarnings("unchecked")
		IRule<AggInvestIncomeVO> createBillCodeRule = new CreateBillCodeRule("3643","vbillno","pk_group","pk_org");
		processer.addBeforeRule(createBillCodeRule);
		
		// ����Ĭ��ֵ�Ĺ���
		@SuppressWarnings("unchecked")
		IRule<AggInvestIncomeVO> fillRule = new IncomeInsertDataRule();
		processer.addBeforeRule(fillRule);

		// �����ֶγ��ȼ�����
		@SuppressWarnings("unchecked")
//		IRule<AggInvestIncomeVO> lengthCheckRule = new FieldLengthCheckRule();
//		processer.addBeforeRule(lengthCheckRule);

		// ����ҵ�����ù���
//		IRule<AggInvestIncomeVO> orgInitRule = new IncomeCheckOrgInitDateRule();
//		processer.addBeforeRule(orgInitRule);

		// ����ҵ��У��
		IRule<AggInvestIncomeVO> checkRule = new IncomeCheckRule();
		processer.addBeforeRule(checkRule);

		// ���ݺŹ�����֤
		IRule<AggInvestIncomeVO> billCodeDuplicateRule = new IncomeBillCodeCheckRule();
		processer.addBeforeRule(billCodeDuplicateRule);

		// ��֯��汾
		IRule<AggInvestIncomeVO> orgRule = new IncomeBillOrgVRule();
		processer.addBeforeRule(orgRule);

		// Ԥ�����
//		IRule<AggInvestIncomeVO> tbbRule = new incomeTbbRule();
//		processer.addBeforeRule(tbbRule);
	}
}
