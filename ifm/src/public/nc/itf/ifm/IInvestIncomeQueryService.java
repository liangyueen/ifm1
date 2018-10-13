package nc.itf.ifm;

import com.alibaba.fastjson.JSONObject;

import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.BusinessException;

public interface IInvestIncomeQueryService {
	/**
	 * ��������:
	 * @return AggBankProtocolVO[]
	 */
	public AggInvestIncomeVO[] queryIncomeByPks (String[] pks) throws BusinessException;
	/**
	 * ��������:
	 * @return String[]
	 */
	public String[] queryCCIncomeBySchema (nc.ui.querytemplate.querytree.IQueryScheme querySchema) throws BusinessException;
	
	/**
	 * ��֤���ݺ��Ƿ��ظ�
	 * @param pk_income
	 * @param vbillno
	 * @return ture: �ظ���false�����ظ�
	 * @throws BusinessException
	 */
	Boolean isBillNoDuplicate(String[] pk_income, String[] vbillno) throws BusinessException;
	/**
	 * ����ƻ�Ԥ��
	 * @param paramString
	 * @return
	 * @throws BusinessException
	 */
	public abstract JSONObject linkqueryPlan(String paramString)
		    throws BusinessException;
}
