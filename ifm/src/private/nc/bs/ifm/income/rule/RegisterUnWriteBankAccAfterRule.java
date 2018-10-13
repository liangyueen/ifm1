package nc.bs.ifm.income.rule;

import java.util.ArrayList;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.cm.prv.CmpConst;
import nc.vo.ifm.apply.IFACIFMAccSuper;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class RegisterUnWriteBankAccAfterRule extends IFACIFMAccSuper implements
IRule<AggInvestIncomeVO> {

	@Override
	public void process(AggInvestIncomeVO[] vos) {
	
		for (AggInvestIncomeVO vo : vos) {
			InvestIncomeVO parentVO = (InvestIncomeVO) vo.getParent();
			if (parentVO.getBillstatus() == 3) {
				try {
					super.delBankAcc(vo, new UFDate(parentVO.getBillmakedate().toLocalString()));
				} catch (BusinessException e) {
					ExceptionUtils.wrappException(e);
				}
			}
		}
	}

	@Override
	public String[] getBankSerialNOs(AggregatedValueObject aggvo)
			throws BusinessException {
		InvestIncomeVO headvo = (InvestIncomeVO) aggvo.getParentVO();
		ArrayList<String> serialnos = new ArrayList<String>();
			if (headvo.getBillstatus() == 3) {
				if (null != headvo.getGathering()) {
					serialnos.add(headvo.getVbillno()
							+ headvo.getPk_income()
							+ headvo.getGathering() + CmpConst.Direction_Pay);
				}
			}
		return serialnos.toArray(new String[0]);
	}
}