package nc.itf.ifm;

import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.pub.BusinessException;

public interface IInvestApplyQueryService {	
	/**
	 * 创建日期:
	 * @return AggBankApplyVO[]
	 */
	public AggInvestApplyVO[] queryApplyByPks (String[] pks) throws BusinessException;
	/**
	 * 创建日期:
	 * @return String[]
	 */
	public String[] queryIFMApplyBySchema (nc.ui.querytemplate.querytree.IQueryScheme querySchema) throws BusinessException;
	
	/**
	 * 验证单据号是否重复
	 * @param pk_apply
	 * @param contractcode
	 * @return ture: 重复；false：不重复
	 * @throws BusinessException
	 */
	Boolean isBillNoDuplicate(String[] pk_apply, String[] applycode) throws BusinessException;
	
	/**
	 * 用户默认组织
	 * @return
	 */
	public abstract String getDefaultOrgUnit();
	
}
