package nc.impl.ifm.pub.fip;

import nc.vo.fac.fixdepositprocess.FixDepositProcessVO;
import nc.vo.fac.pub.IFACConst;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.AggregatedValueObject;


public class ApplyProcessReflectorServiceImpl  extends IfmAbstractReflector<AggInvestApplyVO> {
	@Override
	public String getPKFieldName() {
		return InvestApplyVO.PK_APPLY;
	}

	@Override
	public Class<AggInvestApplyVO> getBillClass() {
		return AggInvestApplyVO.class;
	}
	@Override
	public AggregatedValueObject getVoucherBill(AggInvestApplyVO billVO) {
		return billVO;
	}

	@Override
	public boolean needRelect(FipRelationInfoVO relationInfoVO) {
		return TMIFMConst.CONST_BILLTYPE_APPLY.equals(relationInfoVO.getPk_billtype());
	}

}
