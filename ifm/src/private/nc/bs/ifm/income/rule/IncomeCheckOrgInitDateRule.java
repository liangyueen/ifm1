package nc.bs.ifm.income.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.protocol.contents.ICCConst;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.tmpub.initdate.InitDateHelper;

public class IncomeCheckOrgInitDateRule implements IRule<AggInvestIncomeVO>{
	@Override
	public void process(AggInvestIncomeVO[] vos) {
		if (null == vos)
			return;
		for (int i = 0; i < vos.length; i++) {
			String pk_org = ((AggInvestIncomeVO) vos[i]).getParentVO()
					.getPk_org();
			checkOrgInitDateRule(pk_org);
		}
	}

	private void checkOrgInitDateRule(String pk_org) {
		try {
			UFDate initDate = InitDateHelper.getInitDate(pk_org,"3667");
			if (initDate == null) {
				ExceptionUtils
						.wrappBusinessException("当前财务组织未设置业务期间，单据不能保存！");
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}

	}
}
