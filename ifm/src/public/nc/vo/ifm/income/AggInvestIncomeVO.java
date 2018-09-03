package nc.vo.ifm.income;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.ifm.income.InvestIncomeVO")

public class AggInvestIncomeVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggInvestIncomeVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public InvestIncomeVO getParentVO(){
	  	return (InvestIncomeVO)this.getParent();
	  }
	  
}