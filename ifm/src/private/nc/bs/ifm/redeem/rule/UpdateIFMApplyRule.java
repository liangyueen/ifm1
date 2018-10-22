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
			Integer billstatus =   (Integer) RedeemStatusEnum.�������.value();//�����
			IIFMApplyQueryService service = ServiceLocator
					.find(IIFMApplyQueryService.class);
			AggInvestApplyVO[] resultVOs = null;
			resultVOs = service.queryApplyByPks(new String[] { vo.getPk_srcbill() });
			if(vo.getRedeemnumber()!=null){
				Integer lastNum = RedeemUtil.isApplyNumNoExists(vo,vo.getPk_srcbill(),resultVOs[0].getParentVO().getApplynumber());
				Integer holdNum =lastNum-vo.getRedeemnumber();
				if(holdNum<0){
					throw new BusinessException("��ط������ڳ��з�������ǰ�ĳ��з���ΪΪ��"+lastNum+"");
				}
				if(holdNum==0){
					billstatus = (Integer) RedeemStatusEnum.ȫ�����.value();
				}
				
				BaseDAO dao = new BaseDAO();
				String sql = "update ifm_apply set holdnumber='"+holdNum + "' where pk_apply='"
						+ vo.getPk_srcbill() + "' ";
				
					dao.executeUpdate(sql);
			}else {
				UFDouble holdMoney = RedeemUtil.isApplyMoneyNoExists(vo,vo.getPk_srcbill(),resultVOs[0].getParentVO().getMoney());
				UFDouble lastMoney =holdMoney.sub(vo.getRedeemmoney());
				if (lastMoney.compareTo(UFDouble.ZERO_DBL)==0 ) {
					billstatus = (Integer) RedeemStatusEnum.ȫ�����.value();
				}
				if(lastMoney.compareTo(UFDouble.ZERO_DBL)<0){
					throw new BusinessException("��ؽ����ڳ��У���ǰ�ĳ��з���ΪΪ��"+holdMoney+"");
				}
				BaseDAO dao = new BaseDAO();
				String sql = "update ifm_apply set holdmoney='"+lastMoney + "' where pk_apply='"
						+ vo.getPk_srcbill() + "' ";
				
					dao.executeUpdate(sql);
				
			} 
			clientBill.getParentVO().setAttributeValue("billstatus", billstatus);
			clientBill.getParentVO().setBillstatus(VOStatus.NEW);
			
			//������������������ӿ�����������
			if(vo.getRealreaning()!= null){
				IIncomeCtrlService incomeService = ServiceLocator
						.find(IIncomeCtrlService.class);
				boolean save = incomeService.WriteIncomeBill(vo);
				if(!save){
					throw new BusinessException("д������ʧ��");
				}
			}
		}
		} catch (BusinessException e) {
			throw new BusinessRuntimeException(e.getMessage(), e);
		}
	}
}
