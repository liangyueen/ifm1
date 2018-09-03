package nc.vo.ifm.income;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggInvestIncomeVOMeta extends AbstractBillMeta{
	
	public AggInvestIncomeVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.ifm.income.InvestIncomeVO.class);
	}
}