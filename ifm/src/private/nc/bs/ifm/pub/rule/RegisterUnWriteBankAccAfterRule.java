package nc.bs.ifm.pub.rule;

import java.util.ArrayList;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.cm.prv.CmpConst;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.IFACIFMAccSuper;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class RegisterUnWriteBankAccAfterRule extends IFACIFMAccSuper implements
IRule<AggInvestApplyVO> {

	@Override
	public void process(AggInvestApplyVO[] vos) {
	
		for (AggInvestApplyVO vo : vos) {
			InvestApplyVO parentVO = (InvestApplyVO) vo.getParent();
			if(parentVO.getHoldmoney().equals(parentVO.getMoney()) ){
				try {
					throw new BusinessException("单据"+ parentVO.getVbillno() +"正在被使用，不能取消审批！");
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}
			if (parentVO.getBillstatus() == 3) {
				try {
					super.delBankAcc(vo, new UFDate(parentVO.getPurchasedate().toLocalString()));
				} catch (BusinessException e) {
					ExceptionUtils.wrappException(e);
				}
			}
		}
	}

	@Override
	public String[] getBankSerialNOs(AggregatedValueObject aggvo)
			throws BusinessException {
		InvestApplyVO headvo = (InvestApplyVO) aggvo.getParentVO();
		ArrayList<String> serialnos = new ArrayList<String>();
			if (headvo.getBillstatus() == 3) {
				if (null != headvo.getSettleaccount()) {
					serialnos.add(headvo.getVbillno()
							+ headvo.getPk_apply()
							+ headvo.getSettleaccount() + CmpConst.Direction_Pay);
				}
			}
		return serialnos.toArray(new String[0]);
	}
}