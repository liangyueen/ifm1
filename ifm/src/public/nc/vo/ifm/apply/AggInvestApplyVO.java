package nc.vo.ifm.apply;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.ifm.apply.InvestApplyVO")

public class AggInvestApplyVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggInvestApplyVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public InvestApplyVO getParentVO(){
	  	return (InvestApplyVO)this.getParent();
	  }
	  
}