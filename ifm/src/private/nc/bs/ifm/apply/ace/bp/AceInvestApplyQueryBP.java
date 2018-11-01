package nc.bs.ifm.apply.ace.bp;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;

public class AceInvestApplyQueryBP {

	public AggInvestApplyVO[] queryBondApplyVOByCondition(String condition) throws BusinessException {
		if (StringUtils.isBlank(condition)) {
			condition = " 1 = 1";
		}
		Collection<?> results = NCLocator.getInstance().lookup(IMDPersistenceQueryService.class)
				.queryBillOfVOByCond(AggInvestApplyVO.class, condition + " and isnull(dr,0) = 0 ", true, false);
		if(results==null){
			return null;
		}
		return results.toArray(new AggInvestApplyVO[results.size()]);
	}
	
	public SuperVO[] querySuperVOByCondition(String condition, Class voClass) throws BusinessException {
		if (StringUtils.isBlank(condition)) {
			condition = "1 = 1";
		}
		Collection<?> results = NCLocator.getInstance().lookup(IMDPersistenceQueryService.class)
				.queryBillOfVOByCond(voClass, condition + " and isnull(dr,0) = 0 order by applydate asc ", false);
		if(results==null){
			return null;
		}
		AggInvestApplyVO[] vos = results.toArray(new AggInvestApplyVO[results.size()]);
		if (vos == null) {
			return null;
		}
		SuperVO[] res = new SuperVO[vos.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = (SuperVO) vos[i].getParentVO();
		}
		return res;
	}

	

	public AggInvestApplyVO[] getAggVOsByPKs(String[] billPKs) throws BusinessException {
		Collection<?> result = NCLocator.getInstance().lookup(IMDPersistenceQueryService.class)
				.queryBillOfVOByPKs(AggInvestApplyVO.class, billPKs, false);
		if(result==null){
			return null;
		}
		return result.toArray(new AggInvestApplyVO[result.size()]);
	}

}
