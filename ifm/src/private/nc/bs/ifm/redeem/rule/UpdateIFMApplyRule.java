package nc.bs.ifm.redeem.rule;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ifm.RedeemStatusEnum;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;

public class UpdateIFMApplyRule implements IRule<AggInvestRedeemVO> {

	@Override
	public void process(AggInvestRedeemVO[] vos) {
		if (vos == null || vos.length == 0) {
			return;
		}
		checkAmount(vos);
	}

	private void checkAmount(AggInvestRedeemVO[] vos) {
		try {
		for (AggInvestRedeemVO clientBill : vos) {
			InvestRedeemVO vo = clientBill.getParentVO();
			Integer billstatus =   (Integer) RedeemStatusEnum.部分赎回.value();//待审核
			if(vo.getHoldmoeny()!=null){
				if ((vo.getHoldmoeny()).compareTo(UFDouble.ZERO_DBL)<1 ) {
					billstatus = (Integer) RedeemStatusEnum.全部赎回.value();
				}
				BaseDAO dao = new BaseDAO();
				String sql = "update ifm_apply set holdmoeny='"+vo.getHoldmoney() + "' where pk_apply='"
						+ vo.getPk_srcbill() + "' ";
				
					dao.executeUpdate(sql);
				
			}else if(vo.getRedeemnumber()!=null){
				Integer lastNum = vo.getApplynumber()-vo.getRedeemnumber();
				if(lastNum==0){
					billstatus = (Integer) RedeemStatusEnum.全部赎回.value();
				}
				BaseDAO dao = new BaseDAO();
				String sql = "update ifm_apply set holdnumber='"+lastNum + "' where pk_apply='"
						+ vo.getPk_srcbill() + "' ";
				
					dao.executeUpdate(sql);
			}
			clientBill.getParentVO().setAttributeValue("billstatus", billstatus);
			clientBill.getParentVO().setBillstatus(VOStatus.NEW);
			
			//若有收益金额，则调用收益接口添加赎回收益
			if(vo.getRealreaning()!= null){
				
			}
		}
		} catch (BusinessException e) {
			throw new BusinessRuntimeException(e.getMessage(), e);
		}
		
	}

}
