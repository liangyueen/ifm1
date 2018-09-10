package nc.vo.ifm.bankprotocol;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class AggProtocolVOMeta extends AbstractBillMeta{
	
	public AggProtocolVOMeta(){
		this.init();
	}
	
	private void init() {
		this.setParent(nc.vo.ifm.bankprotocol.ProtocolVO.class);
		this.addChildren(nc.vo.ifm.bankprotocol.GuaranteeVO.class);
		this.addChildren(nc.vo.ifm.bankprotocol.ProtocolDetailVO.class);
	}
}