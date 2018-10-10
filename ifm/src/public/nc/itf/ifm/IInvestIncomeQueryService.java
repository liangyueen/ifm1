package nc.itf.ifm;

import com.alibaba.fastjson.JSONObject;

import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.BusinessException;

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
	 * 联查计划预算
	 * @param paramString
	 * @return
	 * @throws BusinessException
	 */
	public abstract JSONObject linkqueryPlan(String paramString)
		    throws BusinessException;
}
