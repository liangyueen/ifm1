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
 * ��׼��������BP
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
	 * ���������
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggInvestApplyVO> processor) {
		// TODO ���������
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
	 * ����ǰ����
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggInvestApplyVO> processer) {
		// TODO ����ǰ����
		IRule<AggInvestApplyVO> rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("3641");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setCodeItem("vbillno");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
		
		// �����ֶγ��ȼ�����
		IRule<AggInvestApplyVO> lengthCheckRule = new FieldLengthCheckRule();
		processer.addBeforeRule(lengthCheckRule);
		
		// ����ҵ�����ù���
		IRule<AggInvestApplyVO> orgInitRule = new CheckOrgInitDateRule();
		processer.addBeforeRule(orgInitRule);
		
		// ����ҵ��У��
		IRule<AggInvestApplyVO> checkRule = new ApplyCheckRule();
		processer.addBeforeRule(checkRule);
		
		// ��֯��汾
		IRule<AggInvestApplyVO> orgRule = new IFMBillOrgVRule();
		processer.addBeforeRule(orgRule);
	}
}
