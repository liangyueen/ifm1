package nc.bs.ifm.redeem.rule;

import nc.bs.ifm.redeem.process.RedeemProcessVoucherBS;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class TallySendRedeemProcessRedVoucherRule implements
		IRule<AggInvestRedeemVO> {

	@Override
	public void process(AggInvestRedeemVO[] vos) {
		AggInvestRedeemVO bill = vos[0];
		// �����ͬ������ƾ֤
		//if (bill.getParentVO().getBinit().booleanValue() == false) {
			// ����״̬billstatus��Ϊ�ѳ�����4��ʱ�����ɺ��ֳ���ƾ֤
			if (bill.getParentVO().getBillstatus() == 4) {
				RedeemProcessVoucherBS bs = new RedeemProcessVoucherBS();
				/*try {
					RegisterBVO[] registerBVOs = (RegisterBVO[]) bill
							.getChildrenVO();
					
					bs.createFipRedVoucher(bill);
					for (RegisterBVO registerBVO : registerBVOs) {
						UFDouble money = registerBVO.getMoney();
						UFDouble olcamount = registerBVO.getOlcamount();
						registerBVO.setMoney(money.multiply(-1));
						registerBVO.setOlcamount(olcamount.multiply(-1));
					}
				} catch (BusinessException e) {
					ExceptionUtils.wrappBusinessException(e.getMessage());
				}*/
			}
		//}
	}

}
