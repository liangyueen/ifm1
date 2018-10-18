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
 * 标准单据新增BP
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
	 * 新增后规则
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggInvestIncomeVO> processor) {
//		// TODO 新增后规则
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
	 * 新增前规则
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggInvestIncomeVO> processer) {
		// 创建单据规则
		@SuppressWarnings("unchecked")
		IRule<AggInvestIncomeVO> createBillCodeRule = new CreateBillCodeRule("3643","vbillno","pk_group","pk_org");
		processer.addBeforeRule(createBillCodeRule);
		
		// 补充默认值的规则
		@SuppressWarnings("unchecked")
		IRule<AggInvestIncomeVO> fillRule = new IncomeInsertDataRule();
		processer.addBeforeRule(fillRule);

		// 单据字段长度检查规则
		@SuppressWarnings("unchecked")
//		IRule<AggInvestIncomeVO> lengthCheckRule = new FieldLengthCheckRule();
//		processer.addBeforeRule(lengthCheckRule);

		// 新增业务启用规则
//		IRule<AggInvestIncomeVO> orgInitRule = new IncomeCheckOrgInitDateRule();
//		processer.addBeforeRule(orgInitRule);

		// 保存业务校验
		IRule<AggInvestIncomeVO> checkRule = new IncomeCheckRule();
		processer.addBeforeRule(checkRule);

		// 单据号规则验证
		IRule<AggInvestIncomeVO> billCodeDuplicateRule = new IncomeBillCodeCheckRule();
		processer.addBeforeRule(billCodeDuplicateRule);

		// 组织多版本
		IRule<AggInvestIncomeVO> orgRule = new IncomeBillOrgVRule();
		processer.addBeforeRule(orgRule);

		// 预算控制
//		IRule<AggInvestIncomeVO> tbbRule = new incomeTbbRule();
//		processer.addBeforeRule(tbbRule);
	}
}
