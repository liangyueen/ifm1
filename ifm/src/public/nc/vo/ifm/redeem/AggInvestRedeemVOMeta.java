package nc.vo.ifm.redeem;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggInvestRedeemVOMeta extends AbstractBillMeta{
	
	public AggInvestRedeemVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.ifm.income.InvestIncomeVO.class);
	}
}