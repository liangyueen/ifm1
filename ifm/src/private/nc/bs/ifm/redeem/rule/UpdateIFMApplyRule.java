package nc.bs.ifm.redeem.rule;
import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.ifm.IIFMApplyQueryService;
import nc.itf.ifm.IIncomeCtrlService;
import nc.vo.ifm.RedeemStatusEnum;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nccloud.framework.service.ServiceLocator;
import nccloud.web.ifm.util.RedeemUtil;

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
			IIFMApplyQueryService service = ServiceLocator
					.find(IIFMApplyQueryService.class);
			AggInvestApplyVO[] resultVOs = null;
			resultVOs = service.queryApplyByPks(new String[] { vo.getPk_srcbill() });
			if(vo.getRedeemnumber()!=null){
				Integer lastNum = RedeemUtil.isApplyNumNoExists(vo,vo.getPk_srcbill(),resultVOs[0].getParentVO().getApplynumber());
				Integer holdNum =lastNum-vo.getRedeemnumber();
				if(holdNum<0){
					throw new BusinessException("赎回份数大于持有份数，当前的持有份数为为："+lastNum+"");
				}
				if(holdNum==0){
					billstatus = (Integer) RedeemStatusEnum.全部赎回.value();
				}
				
				BaseDAO dao = new BaseDAO();
				String sql = "update ifm_apply set holdnumber='"+holdNum + "' where pk_apply='"
						+ vo.getPk_srcbill() + "' ";
				
					dao.executeUpdate(sql);
			}else {
				UFDouble holdMoney = RedeemUtil.isApplyMoneyNoExists(vo,vo.getPk_srcbill(),resultVOs[0].getParentVO().getMoney());
				UFDouble lastMoney =holdMoney.sub(vo.getRedeemmoney());
				if (lastMoney.compareTo(UFDouble.ZERO_DBL)==0 ) {
					billstatus = (Integer) RedeemStatusEnum.全部赎回.value();
				}
				if(lastMoney.compareTo(UFDouble.ZERO_DBL)<0){
					throw new BusinessException("赎回金额大于持有，当前的持有份数为为："+holdMoney+"");
				}
				BaseDAO dao = new BaseDAO();
				String sql = "update ifm_apply set holdmoney='"+lastMoney + "' where pk_apply='"
						+ vo.getPk_srcbill() + "' ";
				
					dao.executeUpdate(sql);
				
			} 
			clientBill.getParentVO().setAttributeValue("billstatus", billstatus);
			clientBill.getParentVO().setBillstatus(VOStatus.NEW);
			
			//若有收益金额，则调用收益接口添加赎回收益
			if(vo.getRealreaning()!= null){
				IIncomeCtrlService incomeService = ServiceLocator
						.find(IIncomeCtrlService.class);
				boolean save = incomeService.WriteIncomeBill(vo);
				if(!save){
					throw new BusinessException("写入收益失败");
				}
			}
		}
		} catch (BusinessException e) {
			throw new BusinessRuntimeException(e.getMessage(), e);
		}
	}
}
