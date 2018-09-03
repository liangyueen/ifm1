package nc.vo.ifm.redeem;

import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.ifm.income.InvestIncomeVO")

public class AggInvestRedeemVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggInvestRedeemVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public InvestIncomeVO getParentVO(){
	  	return (InvestIncomeVO)this.getParent();
	  }
	  
}