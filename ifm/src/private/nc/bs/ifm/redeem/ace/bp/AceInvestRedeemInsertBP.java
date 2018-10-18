package nc.bs.ifm.redeem.ace.bp;


import nc.bs.ifm.redeem.plugin.bpplugin.InvestRedeemPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.ifm.redeem.AggInvestRedeemVO;

/**
 * ��׼��������BP
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
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggInvestRedeemVO> processor) {
		// TODO ���������
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
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggInvestRedeemVO> processer) {
		// TODO ����ǰ����
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
		/*	// ����Ĭ��ֵ�Ĺ���
		IRule<AggInvestRedeemVO> fillRule = new CCCFillInsertDataRule();
		processer.addBeforeRule(fillRule);

		// �����ֶγ��ȼ�����
		IRule<AggInvestRedeemVO> lengthCheckRule = new FieldLengthCheckRule();
		processer.addBeforeRule(lengthCheckRule);

		// ���ݺŹ�����֤
		IRule<AggInvestRedeemVO> billCodeDuplicateRule = new ProtocolBillCodeCheckRule();
		processer.addBeforeRule(billCodeDuplicateRule);

		// ��֯��汾
		IRule<AggInvestRedeemVO> orgRule = new CCBillOrgVRule();
		processer.addBeforeRule(orgRule);
		processer.addBeforeRule(rule);*/
	}
}
