package nc.bs.ifm.redeem.ace.bp;


import nc.bs.ifm.redeem.plugin.bpplugin.InvestRedeemPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.ifm.redeem.AggInvestRedeemVO;

/**
 * 标准单据新增BP
 */
public class AceInvestRedeemInsertBP {

	public AggInvestRedeemVO[] insert(AggInvestRedeemVO[] bills) {

		InsertBPTemplate<AggInvestRedeemVO> bp = new InsertBPTemplate<AggInvestRedeemVO>(
				InvestRedeemPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * 新增后规则
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggInvestRedeemVO> processor) {
		// TODO 新增后规则
		IRule<AggInvestRedeemVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("3642");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("redeemid");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processor.addAfterRule(rule);
	}

	/**
	 * 新增前规则
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggInvestRedeemVO> processer) {
		// TODO 新增前规则
		IRule<AggInvestRedeemVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		//processer.addBeforeRule(rule);
		rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("3642");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setCodeItem("redeemid");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
		/*	// 补充默认值的规则
		IRule<AggInvestRedeemVO> fillRule = new CCCFillInsertDataRule();
		processer.addBeforeRule(fillRule);

		// 单据字段长度检查规则
		IRule<AggInvestRedeemVO> lengthCheckRule = new FieldLengthCheckRule();
		processer.addBeforeRule(lengthCheckRule);

		// 单据号规则验证
		IRule<AggInvestRedeemVO> billCodeDuplicateRule = new ProtocolBillCodeCheckRule();
		processer.addBeforeRule(billCodeDuplicateRule);

		// 组织多版本
		IRule<AggInvestRedeemVO> orgRule = new CCBillOrgVRule();
		processer.addBeforeRule(orgRule);
		processer.addBeforeRule(rule);*/
	}
}
