package nc.bs.ifm.income.rule;

import nc.bs.pubapp.pub.rule.FillInsertDataRule;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.util.AuditInfoUtils;

public class IncomeInsertDataRule extends FillInsertDataRule {

	@Override
	public void process(Object[] bills) {
		
		// 设置审计信息
		AuditInfoUtils.setAddAuditInfo((IBill[]) bills);
	}
	
	/**
	 * 金额设置默认值
	 * 
	 * @param bills
	 */
	private void setIncomeMoney(IBill[] bills) {
		for (IBill bill : bills) {
			InvestIncomeVO head = (InvestIncomeVO) bill.getParent();
			
		}
	}

}
