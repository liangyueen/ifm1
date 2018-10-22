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
					throw new BusinessException("ɾ��ʧ�ܣ������ݲ�������һ��");
				}
			} 
		}
		catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ���������Ƿ�Ϊ���һ���ύһ��(ֻ������ͨ��������)
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
			//�����ڼ�¼������true
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
	 * ���������Ƿ�����������
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
		a.compareTo(b);//���ڷ���1 
		a.compareTo(c);//��ȷ���0
		b.compareTo(c);//С�ڷ���-1
		
	}
}
