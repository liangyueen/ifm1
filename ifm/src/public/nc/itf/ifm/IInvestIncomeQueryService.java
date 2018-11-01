package nc.itf.ifm;

import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;

import com.alibaba.fastjson.JSONObject;

public interface IInvestIncomeQueryService {
	/**
	 * 创建日期:
	 * @return AggBankProtocolVO[]
	 */
	public AggInvestIncomeVO[] queryIncomeByPks (String[] pks) throws BusinessException;
	/**
	 * 创建日期:
	 * @return String[]
	 */
	public String[] queryCCIncomeBySchema (nc.ui.querytemplate.querytree.IQueryScheme querySchema) throws BusinessException;
	
	/**
	 * 验证单据号是否重复
	 * @param pk_income
	 * @param vbillno
	 * @return ture: 重复；false：不重复
	 * @throws BusinessException
	 */
	Boolean isBillNoDuplicate(String[] pk_income, String[] vbillno) throws BusinessException;
	/**
	 * 查询单个实体信息
	 * @return
	 * @throws BusinessException
	 */
	public SuperVO[] querySuperVOByCondition(String condition, Class voClass) throws BusinessException ;
	/**
	 * 联查计划预算
	 * @param paramString
	 * @return
	 * @throws BusinessException
	 */
	public abstract JSONObject linkqueryPlan(String paramString)
		    throws BusinessException;
	/**
	 * 用户默认组织
	 * @return
	 */
	public abstract String getDefaultOrgUnit();
	/**
	 * 根据主键查询执行情况调整单
	 * @return
	 * @throws BusinessException
	 */
	public AggInvestIncomeVO[] getAggVOsByPKs(String... pks) throws BusinessException ;
}
