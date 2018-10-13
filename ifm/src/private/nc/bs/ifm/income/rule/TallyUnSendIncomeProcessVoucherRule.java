package nc.bs.ifm.income.rule;


import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.ifm.redeem.process.RedeemProcessVoucherBS;
import nc.bs.pf.pub.PfDataCache;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.fip.service.IFipMessageService;

import nc.vo.fac.util.IFACPubUtil;
import nc.vo.fip.service.FipMessageVO;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class TallyUnSendIncomeProcessVoucherRule implements
		IRule<AggInvestIncomeVO> {
	@Override
	public void process(AggInvestIncomeVO[] vos) {
		AggInvestIncomeVO bill = vos[0];
		// 起初合同不生成凭证
		//if (bill.getParentVO().getBinit().booleanValue() == false) {
			if (bill.getParentVO().getBillstatus() == 3) {
				RedeemProcessVoucherBS bs = new RedeemProcessVoucherBS();
				bs.completeContStatusDel(vos);
			}
		//}

	}
}
