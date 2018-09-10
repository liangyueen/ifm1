package nc.vo.ifm.bankprotocol;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.ccc.bankprotocol.ProtocolVO")

public class AggProtocolVO extends AbstractBill {
	
	  @Override
	  public IBillMeta getMetaData() {
	  	IBillMeta billMeta =BillMetaFactory.getInstance().getBillMeta(AggProtocolVOMeta.class);
	  	return billMeta;
	  }
	    
	  @Override
	  public ProtocolVO getParentVO(){
	  	return (ProtocolVO)this.getParent();
	  }
	  
}