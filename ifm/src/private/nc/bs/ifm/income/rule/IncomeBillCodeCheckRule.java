package nc.bs.ifm.income.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.ifm.IInvestIncomeQueryService;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;

public class IncomeBillCodeCheckRule implements IRule<AggInvestIncomeVO>  {

	@Override
	public void process(AggInvestIncomeVO[] vos) {
		billCodeCheck((AggInvestIncomeVO[])vos);
	}

	private void billCodeCheck(AggInvestIncomeVO[] vos){
		List<String> pkList = new ArrayList<String>();
		List<String> codeList = new ArrayList<String>();
		for(int i=0;i<vos.length;i++){
			pkList.add(vos[i].getParentVO().getPk_income());
			codeList.add(vos[i].getParentVO().getVbillno());
		}

		IInvestIncomeQueryService queryService = NCLocator.getInstance().lookup(IInvestIncomeQueryService.class);
		try {
			Boolean isDuplicate = queryService.isBillNoDuplicate(pkList.toArray(new String[0]), codeList.toArray(new String[0]));
			if(isDuplicate){
				throw new BusinessException("已存在相同的投资收益");
			}
		} catch (BusinessException e) {
			throw new BusinessRuntimeException(e.getMessage(), e);
		}
	}
}

