package nc.bs.ifm.apply.rule;

import nc.bs.ifm.redeem.process.RedeemProcessVoucherBS;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.pub.AggregatedValueObject;

public class TallySendApplyProcessVoucherRule implements
		IRule<AggInvestApplyVO> {
	@Override
	public void process(AggInvestApplyVO[] vos) {
		AggInvestApplyVO bill = vos[0];
			//if (bill.getParentVO().getBillstatus() == 2 || bill.getParentVO().getBillstatus() == 3) {
				RedeemProcessVoucherBS bs = new RedeemProcessVoucherBS();
				AggregatedValueObject[] ao = vos;
				bs.completeContStatus(ao);
			//}
	}
}
