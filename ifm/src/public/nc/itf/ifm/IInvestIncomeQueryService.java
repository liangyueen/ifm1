package nc.itf.ifm;

import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;

import com.alibaba.fastjson.JSONObject;

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
	 * ��ѯ����ʵ����Ϣ
	 * @return
	 * @throws BusinessException
	 */
	public SuperVO[] querySuperVOByCondition(String condition, Class voClass) throws BusinessException ;
	/**
	 * ����ƻ�Ԥ��
	 * @param paramString
	 * @return
	 * @throws BusinessException
	 */
	public abstract JSONObject linkqueryPlan(String paramString)
		    throws BusinessException;
	/**
	 * �û�Ĭ����֯
	 * @return
	 */
	public abstract String getDefaultOrgUnit();
	/**
	 * ����������ѯִ�����������
	 * @return
	 * @throws BusinessException
	 */
	public AggInvestIncomeVO[] getAggVOsByPKs(String... pks) throws BusinessException ;
}
