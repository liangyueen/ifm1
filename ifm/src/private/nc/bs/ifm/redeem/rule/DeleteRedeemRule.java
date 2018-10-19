package nc.bs.ifm.redeem.rule;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.ifm.IIFMApplyQueryService;
import nc.itf.ifm.IIncomeCtrlService;
import nc.itf.ifm.IInvestRedeemQueryService;
import nc.vo.ifm.RedeemStatusEnum;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nccloud.framework.service.ServiceLocator;
import nccloud.web.ifm.util.RedeemUtil;

public class DeleteRedeemRule implements IRule<AggInvestRedeemVO> {

	@Override
	public void process(AggInvestRedeemVO[] vos) {
		try {
		if (vos == null || vos.length == 0) {
			return;
		}
		if(checkIncome(vos)){
			
				if(checkRedeemLastDate(vos)){
					
				}
			} 
		}
		catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 检查此数据是否为最后一次提交一条
	 * @param vos
	 * @return
	 */
	private boolean checkRedeemLastDate(AggInvestRedeemVO[] vos) throws BusinessException {
		// TODO Auto-generated method stub
		for (AggInvestRedeemVO clientBill : vos) {
			InvestRedeemVO vo = clientBill.getParentVO();
			
			IInvestRedeemQueryService serviceRedeem=ServiceLocator.find(IInvestRedeemQueryService.class);
			String condition = "pk_srcbill = '" + vo.getPk_srcbill() + "' ";
			SuperVO[] fvo = serviceRedeem.querySuperVOByCondition(condition, AggInvestRedeemVO.class);
			UFDate lastDate= new UFDate();
			if (fvo != null && fvo.length > 0) {
				for(SuperVO svo:fvo){
					InvestRedeemVO vor =	(InvestRedeemVO) svo;
					 lastDate = vor.getLastdate();
				}
			}
			if(vo.getLastdate()==lastDate){
				return true;
			}
		}
		return false;
	}
	/**
	 * 检查此数据是否生成了收益
	 * @param vos
	 * @return
	 */
	private boolean checkIncome(AggInvestRedeemVO[] vos) {
		// TODO Auto-generated method stub
		IIncomeCtrlService incomeService = ServiceLocator
				.find(IIncomeCtrlService.class);
		for (AggInvestRedeemVO clientBill : vos) {
			InvestRedeemVO vo = clientBill.getParentVO();
			incomeService.RewriteIncomeBill(vo);
		}
		
		return true;
	}

	
	public static void main(String[] args) {
		Double a =10.00;
		Double b=2.00;
		Double c =10.00;
		a.compareTo(b);//大于返回1 
		a.compareTo(c);//相等返回0
		b.compareTo(c);//小于返回-1
	}
}
