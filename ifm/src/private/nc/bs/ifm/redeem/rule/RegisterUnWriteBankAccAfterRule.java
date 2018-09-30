package nc.bs.ifm.redeem.rule;

import java.util.ArrayList;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.cm.prv.CmpConst;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.IFACIFMAccSuper;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class RegisterUnWriteBankAccAfterRule extends IFACIFMAccSuper implements
IRule<AggInvestRedeemVO> {

	@Override
	public void process(AggInvestRedeemVO[] vos) {
	
		for (AggInvestRedeemVO vo : vos) {
			InvestRedeemVO parentVO = (InvestRedeemVO) vo.getParent();
			if (parentVO.getBillstatus() == 3) {
				try {
					super.delBankAcc(vo, new UFDate(parentVO.getBillmaketime().toLocalString()));
				} catch (BusinessException e) {
					ExceptionUtils.wrappException(e);
				}
			}
		}
	}

	@Override
	public String[] getBankSerialNOs(AggregatedValueObject aggvo)
			throws BusinessException {
		InvestRedeemVO headvo = (InvestRedeemVO) aggvo.getParentVO();
		ArrayList<String> serialnos = new ArrayList<String>();
			if (headvo.getBillstatus() == 3) {
				if (null != headvo.getBillmaker()) {
					serialnos.add(headvo.getVbillno()
							+ headvo.getPk_redeem()
							+ headvo.getInvestaccount() + CmpConst.Direction_Pay);
				}
			}
		return serialnos.toArray(new String[0]);
	}
}