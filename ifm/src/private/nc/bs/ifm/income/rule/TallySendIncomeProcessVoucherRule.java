package nc.bs.ifm.income.rule;

import nc.bs.ifm.redeem.process.RedeemProcessVoucherBS;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class TallySendIncomeProcessVoucherRule implements
		IRule<AggInvestIncomeVO> {
	@Override
	public void process(AggInvestIncomeVO[] vos) {
		AggInvestIncomeVO bill = vos[0];
			if (bill.getParentVO().getBillstatus() == 2 || bill.getParentVO().getBillstatus() == 3) {
				RedeemProcessVoucherBS bs = new RedeemProcessVoucherBS();
				AggregatedValueObject[] ao = vos;
				bs.completeContStatus(ao);
			}

	}
}
