package nc.impl.ifm.pub.fip;

import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.fts.commissionpayment.AggPaymentVO;
import nc.vo.fts.commissionpayment.util.AggPaymentVOUtil;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.AggregatedValueObject;


public class RedeemProcessReflectorServiceImpl  extends IfmAbstractReflector<AggInvestRedeemVO> {
	
	@Override
	public String getPKFieldName() {
		return InvestRedeemVO.PK_REDEEM;
	}

	@Override
	public Class<AggInvestRedeemVO> getBillClass() {
		return AggInvestRedeemVO.class;
	}
	@Override
	public AggregatedValueObject getVoucherBill(AggInvestRedeemVO billVO) {
		return billVO;
	}
	
	

	@Override
	public boolean needRelect(FipRelationInfoVO relationInfoVO) {
		return TMIFMConst.CONST_BILLTYPE_REDEEM.equals(relationInfoVO.getPk_billtype());
	}

}
