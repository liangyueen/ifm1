package nc.bs.ifm.income.ace.bp;

import java.util.Collection;

import nc.bs.framework.common.NCLocator;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;

import org.apache.commons.lang.StringUtils;

public class AceInvestIncomeQueryBP {

	public AggInvestIncomeVO[] queryBondApplyVOByCondition(String condition) throws BusinessException {
		if (StringUtils.isBlank(condition)) {
			condition = " 1 = 1";
		}
		Collection<?> results = NCLocator.getInstance().lookup(IMDPersistenceQueryService.class)
				.queryBillOfVOByCond(AggInvestIncomeVO.class, condition + " and isnull(dr,0) = 0 ", true, false);
		if(results==null){
			return null;
		}
		return results.toArray(new AggInvestIncomeVO[results.size()]);
	}
	
	public SuperVO[] querySuperVOByCondition(String condition, Class voClass) throws BusinessException {
		if (StringUtils.isBlank(condition)) {
			condition = "1 = 1";
		}
		Collection<?> results = NCLocator.getInstance().lookup(IMDPersistenceQueryService.class)
				.queryBillOfVOByCond(voClass, condition + " and isnull(dr,0) = 0 ", false);
		if(results==null){
			return null;
		}
		AggInvestIncomeVO[] vos = results.toArray(new AggInvestIncomeVO[results.size()]);
		if (vos == null) {
			return null;
		}
		SuperVO[] res = new SuperVO[vos.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = (SuperVO) vos[i].getParentVO();
		}
		return res;
	}

	

	public AggInvestIncomeVO[] getAggVOsByPKs(String[] billPKs) throws BusinessException {
		Collection<?> result = NCLocator.getInstance().lookup(IMDPersistenceQueryService.class)
				.queryBillOfVOByPKs(AggInvestIncomeVO.class, billPKs, false);
		if(result==null){
			return null;
		}
		return result.toArray(new AggInvestIncomeVO[result.size()]);
	}

}
