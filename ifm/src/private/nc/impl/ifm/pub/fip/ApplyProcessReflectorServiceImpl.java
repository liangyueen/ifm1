package nc.impl.ifm.pub.fip;

import nc.vo.fac.fixdepositprocess.FixDepositProcessVO;
import nc.vo.fac.pub.IFACConst;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.ifm.constants.TMIFMConst;


public class ApplyProcessReflectorServiceImpl  extends IfmAbstractReflector<FixDepositProcessVO> {
	public static final String PK_DEPOSIT="pk_apply";
	@Override
	public String getPKFieldName() {
		return PK_DEPOSIT;
	}

	@Override
	public Class<FixDepositProcessVO> getBillClass() {
		return FixDepositProcessVO.class;
	}

	@Override
	public boolean needRelect(FipRelationInfoVO relationInfoVO) {
		return TMIFMConst.CONST_BILLTYPE_APPLY.equals(relationInfoVO.getPk_billtype());
	}

}
