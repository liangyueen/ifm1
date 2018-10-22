package nc.bs.ifm.redeem.rule;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.ifm.IIncomeCtrlService;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nccloud.framework.service.ServiceLocator;

public class DeleteRedeemRule implements IRule<AggInvestRedeemVO> {

	@Override
	public void process(AggInvestRedeemVO[] vos) {
		try {
		if (vos == null || vos.length == 0) {
			return;
		}
		if(checkIncome(vos)){
				if(!checkRedeemLastDate(vos)){
					throw new BusinessException("删除失败，此数据不是最新一条");
				}
			} 
		}
		catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 检查此数据是否为最后一次提交一条(只记审批通过的数据)
	 * @param vos
	 * @return
	 */
	private boolean checkRedeemLastDate(AggInvestRedeemVO[] vos) throws BusinessException {
		// TODO Auto-generated method stub
		for (AggInvestRedeemVO clientBill : vos) {
			InvestRedeemVO vo = clientBill.getParentVO();
			
			/*IInvestRedeemQueryService serviceRedeem=ServiceLocator.find(IInvestRedeemQueryService.class);
			String condition = "pk_srcbill = '" + vo.getPk_srcbill() + "' and vbillstatus ='1' ";
			SuperVO[] fvo = serviceRedeem.querySuperVOByCondition(condition, AggInvestRedeemVO.class);
*/			
			
			DataAccessUtils dao = new DataAccessUtils();
			IRowSet rowset = dao.query("select max(redeemdate)  from IFM_REDEEM where  pk_srcbill ='"+ vo.getPk_srcbill()+"' ");
			String[] keys = rowset.toOneDimensionStringArray();
			//不存在记录，返回true
			if(null == keys || keys.length == 0){
				return true;
			}
			
			String lastDate = keys[0];
			if(vo.getLastdate().toString()==lastDate){
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
