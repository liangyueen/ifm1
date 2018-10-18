package nc.bs.ifm.apply.ace.bp;


import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.bs.ifm.apply.rule.ApplyCheckRule;
import nc.bs.ifm.apply.rule.CheckOrgInitDateRule;
import nc.bs.pub.rule.IFMBillOrgVRule;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.ifm.apply.AggInvestApplyVO;

/**
 * 标准单据新增BP
 */
public class AceApplyInsertBP {

	public AggInvestApplyVO[] insert(AggInvestApplyVO[] bills) {

 		InsertBPTemplate<AggInvestApplyVO> bp = new InsertBPTemplate<AggInvestApplyVO>(
				ApplyPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * 新增后规则
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggInvestApplyVO> processor) {
		// TODO 新增后规则
		IRule<AggInvestApplyVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("3641");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setCodeItem("vbillno");
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
	private void addBeforeRule(AroundProcesser<AggInvestApplyVO> processer) {
		// TODO 新增前规则
		IRule<AggInvestApplyVO> rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("3641");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
		
		// 单据字段长度检查规则
		IRule<AggInvestApplyVO> lengthCheckRule = new FieldLengthCheckRule();
		processer.addBeforeRule(lengthCheckRule);
		
		// 新增业务启用规则
		IRule<AggInvestApplyVO> orgInitRule = new CheckOrgInitDateRule();
		processer.addBeforeRule(orgInitRule);
		
		// 保存业务校验
		IRule<AggInvestApplyVO> checkRule = new ApplyCheckRule();
		processer.addBeforeRule(checkRule);
		
		// 组织多版本
		IRule<AggInvestApplyVO> orgRule = new IFMBillOrgVRule();
		processer.addBeforeRule(orgRule);
	}
}
