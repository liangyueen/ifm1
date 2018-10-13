package nc.itf.ifm;

import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.pub.BusinessException;

public interface IInvestApplyQueryService {	
	/**
	 * ��������:
	 * @return AggBankApplyVO[]
	 */
	public AggInvestApplyVO[] queryApplyByPks (String[] pks) throws BusinessException;
	/**
	 * ��������:
	 * @return String[]
	 */
	public String[] queryIFMApplyBySchema (nc.ui.querytemplate.querytree.IQueryScheme querySchema) throws BusinessException;
	
	/**
	 * ��֤���ݺ��Ƿ��ظ�
	 * @param pk_apply
	 * @param contractcode
	 * @return ture: �ظ���false�����ظ�
	 * @throws BusinessException
	 */
	Boolean isBillNoDuplicate(String[] pk_apply, String[] applycode) throws BusinessException;
	
	/**
	 * �û�Ĭ����֯
	 * @return
	 */
	public abstract String getDefaultOrgUnit();
	
}
