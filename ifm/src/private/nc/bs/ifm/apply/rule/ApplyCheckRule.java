package nc.bs.ifm.apply.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.tm.pub.TMPublicUtil;

public class ApplyCheckRule implements IRule<AggInvestApplyVO> {
	@Override
	public void process(AggInvestApplyVO[] vos) {
		check(vos);
	}

	private void check(AggInvestApplyVO[] vos) {
		for (AggInvestApplyVO bill : vos) {
			InvestApplyVO vo = bill.getParentVO();
			UFDate purchasedate = vo.getPurchasedate();//购买日期
			UFDate enddate = vo.getEnddate();//到期日期
			UFDate interestdate = vo.getInterestdate();//起息日期
			UFDouble money = vo.getMoney();//理财金额
			Integer paytype = vo.getPaytype();//付息规则
			Integer payperiod = vo.getPayperiod();//付息周期
			Integer limitday = vo.getLimitday();
		
			/** (一)组织状态校验 **/
			new TMPublicUtil().verifyFinanceOrgVODOCEnable(vo.getPk_org());
			
			/** (二)日期校验 **/
			if(purchasedate.after(interestdate)){
				ExceptionUtils.wrappBusinessException("购买日期应该在起息日期之前！");
			}else if(interestdate.after(enddate)){
				ExceptionUtils.wrappBusinessException("起息日期应该在到期日期之前！");
			}else if(limitday == null || limitday <= 0) {
				ExceptionUtils.wrappBusinessException("期间必须大于0！");
			}
			/** (三)付息规则校验 **/
			if(paytype != null && payperiod != null && paytype == 1){
				if(payperiod < 1 || payperiod > 12){
					ExceptionUtils.wrappBusinessException("付息周期应在1~12月范围内！");
				}
			}else if(paytype != null && payperiod != null && paytype == 5){
				if(payperiod < 1 || payperiod > 31){
					ExceptionUtils.wrappBusinessException("付息周期应在1~31日范围内！");
				}
			}
			/** (四)金额校验 **/
			if(money.compareTo(UFDouble.ZERO_DBL)<=0){
				ExceptionUtils.wrappBusinessException("理财金额必须大于零！");
			}
		}
	}

	
}
