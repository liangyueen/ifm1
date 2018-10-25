package nc.impl.ifm.pub.fip;

import nc.vo.fac.fixdepositprocess.FixDepositProcessVO;
import nc.vo.fac.pub.IFACConst;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.AggregatedValueObject;


public class IncomeProcessReflectorServiceImpl  extends IfmAbstractReflector<AggInvestIncomeVO> {
	@Override
	public String getPKFieldName() {
		return InvestIncomeVO.PK_INCOME;
	}

	@Override
	public Class<AggInvestIncomeVO> getBillClass() {
		return AggInvestIncomeVO.class;
	}
	@Override
	public AggregatedValueObject getVoucherBill(AggInvestIncomeVO billVO) {
		return billVO;
	}

	@Override
	public boolean needRelect(FipRelationInfoVO relationInfoVO) {
		return TMIFMConst.CONST_BILLTYPE_INCOME.equals(relationInfoVO.getPk_billtype());
	}

}
