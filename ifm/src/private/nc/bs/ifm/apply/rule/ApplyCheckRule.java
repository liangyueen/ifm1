package nc.bs.ifm.apply.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;

import java.util.ArrayList;
import java.util.List;
import nc.bs.logging.Logger;
import nc.md.model.MetaDataException;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.tm.pub.TMPublicUtil;

public class ApplyCheckRule implements IRule<AggInvestApplyVO> {
	@Override
	public void process(AggInvestApplyVO[] vos) {
		check(vos);
	}

	private void check(AggInvestApplyVO[] vos) {
		
	}

	
}
