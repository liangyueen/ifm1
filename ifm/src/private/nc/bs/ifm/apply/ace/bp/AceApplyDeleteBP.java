package nc.bs.ifm.apply.ace.bp;

import nc.bs.ifm.apply.plugin.bpplugin.ApplyPluginPoint;
import nc.vo.ifm.apply.AggInvestApplyVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceApplyDeleteBP {

	public void delete(AggInvestApplyVO[] bills) {

		DeleteBPTemplate<AggInvestApplyVO> bp = new DeleteBPTemplate<AggInvestApplyVO>(
				ApplyPluginPoint.DELETE);
		// ����ִ��ǰ����
		// ����ִ�к�ҵ�����
		//this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggInvestApplyVO> processer) {
		// TODO ǰ����
		IRule<AggInvestApplyVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ����� 
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggInvestApplyVO> processer) {
		// TODO �����

	}
}
