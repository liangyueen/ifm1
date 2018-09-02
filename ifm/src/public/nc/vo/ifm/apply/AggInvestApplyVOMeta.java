package nc.vo.ifm.apply;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggInvestApplyVOMeta extends AbstractBillMeta{
	
	public AggInvestApplyVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.ifm.apply.InvestApplyVO.class);
	}
}