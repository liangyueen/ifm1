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
			UFDate purchasedate = vo.getPurchasedate();//��������
			UFDate enddate = vo.getEnddate();//��������
			UFDate interestdate = vo.getInterestdate();//��Ϣ����
			UFDouble money = vo.getMoney();//��ƽ��
			Integer paytype = vo.getPaytype();//��Ϣ����
			Integer payperiod = vo.getPayperiod();//��Ϣ����
			Integer limitday = vo.getLimitday();
		
			/** (һ)��֯״̬У�� **/
			new TMPublicUtil().verifyFinanceOrgVODOCEnable(vo.getPk_org());
			
			/** (��)����У�� **/
			if(purchasedate.after(interestdate)){
				ExceptionUtils.wrappBusinessException("��������Ӧ������Ϣ����֮ǰ��");
			}else if(interestdate.after(enddate)){
				ExceptionUtils.wrappBusinessException("��Ϣ����Ӧ���ڵ�������֮ǰ��");
			}else if(limitday == null || limitday <= 0) {
				ExceptionUtils.wrappBusinessException("�ڼ�������0��");
			}
			/** (��)��Ϣ����У�� **/
			if(paytype != null && payperiod != null && paytype == 1){
				if(payperiod < 1 || payperiod > 12){
					ExceptionUtils.wrappBusinessException("��Ϣ����Ӧ��1~12�·�Χ�ڣ�");
				}
			}else if(paytype != null && payperiod != null && paytype == 5){
				if(payperiod < 1 || payperiod > 31){
					ExceptionUtils.wrappBusinessException("��Ϣ����Ӧ��1~31�շ�Χ�ڣ�");
				}
			}
			/** (��)���У�� **/
			if(money.compareTo(UFDouble.ZERO_DBL)<=0){
				ExceptionUtils.wrappBusinessException("��ƽ���������㣡");
			}
		}
	}

	
}
