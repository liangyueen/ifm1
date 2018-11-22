package nccloud.pubitf.ifm.link;

import java.util.List;
import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.fts.pub.bean.ListQryParam;
import nc.vo.fts.pub.bean.ListQryResult;
import nc.vo.fts.spepay.AggSpePayVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.tmpub.datemanage.DateManageQueryVO;

public interface IFMLinkBillServiceForNCC {

	/**
	 * �Զ���������
	 * 
	 * @param qryParam
	 * @return
	 * @throws BusinessException
	 */
	public ListQryResult<AggSpePayVO> autoLoadData(ListQryParam qryParam,
			Map<String, String> extParam) throws BusinessException;

	/**
	 * ��ȡҵ��ʱ��
	 * 
	 * @param queryParam
	 * @return
	 * @throws BusinessException
	 */
	public UFDate getProcessedBusiDate(DateManageQueryVO queryParam)
			throws BusinessException;

	/**
	 * ��ѯ������������
	 * 
	 * @param queryScheme��ѯ����
	 * @return ������������
	 */
	public Map<String, String> queryGroupCountData(IQueryScheme queryScheme)
			throws BusinessException;

	/**
	 * �б��ѯ ּ��ͨ��һ�����Ӵ������ϼ����ݣ���ҳ���ݣ��Լ���ҳ��Ϣ�Ĳ�ѯ����,���֮ǰ�Ĳ�ѯ���ж�����ӵ�����
	 * 
	 * @param qryParam
	 *            �б��ѯ����
	 * @return �б��ѯ���
	 * @throws BusinessException
	 */
	public ListQryResult<AggSpePayVO> listQuery(ListQryParam qryParam)
			throws BusinessException;

	/**
	 * ��Ƭ��ѯ ��ѯҵ�����ݺ���о��ȴ���
	 * 
	 * @param pk
	 * @return
	 * @throws BusinessException
	 */
	public AggSpePayVO cardQuery(String pk) throws BusinessException;

	/**
	 * �л�ҳ����ѯ ��ѯҵ�����ݺ���о��ȴ���
	 * 
	 * @param pk
	 * @return
	 * @throws BusinessException
	 */
	public AggSpePayVO[] queryPageChangeByPKS(String[] pks)
			throws BusinessException;

	/**
	 * ɾ��
	 * 
	 * @param pk
	 *            ����
	 * @param ts
	 *            ʱ���
	 * @throws BusinessException
	 */
	public AggSpePayVO delete(String pk, String ts) throws BusinessException;

	/**
	 * �ύ
	 * 
	 * @param pk
	 *            ����
	 * @param ts
	 *            ʱ���
	 */
	public AggSpePayVO commit(String pk, String ts) throws BusinessException;

	/**
	 * �ջ�
	 * 
	 * @param pk
	 *            ����
	 * @param ts
	 *            ʱ���
	 * @throws BusinessException
	 */
	public AggSpePayVO unCommit(String pk, String ts) throws BusinessException;

	/**
	 * ��֤
	 * 
	 * @param pk
	 *            ����
	 * @param ts
	 *            ʱ���
	 * @return ��֤������
	 * @throws BusinessException
	 */
	public AggSpePayVO voucher(String pk, String ts) throws BusinessException;

	/**
	 * ȡ����֤
	 * 
	 * @param pk
	 *            ����
	 * @param ts
	 *            ʱ���
	 * @return ȡ����֤������
	 * @throws BusinessException
	 */
	public AggSpePayVO unVoucher(String pk, String ts) throws BusinessException;

	/**
	 * �������ε���
	 * 
	 * @param pks
	 * @return
	 * @throws BusinessException
	 */
	public Map<String, List<String>> buildLinkAfterBillInfo(String[] pks)
			throws BusinessException;

	/**
	 * �˻�
	 * 
	 * @param pk
	 * @param ts
	 * @param reason
	 * @return
	 * @throws BusinessException
	 */
	public AggSpePayVO back(String pk, String ts, String reason)
			throws BusinessException;

	/**
	 * �ύʱ�����Ƿ����������ж��Ƿ���������У��
	 * 
	 * @param aggVO
	 * @param flag
	 * @throws BusinessException
	 */
	public void commitValidate(AggSpePayVO aggVO, boolean flag)
			throws BusinessException;

	/**
	 * ���������ύʱ�����Ƿ����������ж��Ƿ���������У��
	 * 
	 * @param aggVO
	 * @param flag
	 * @throws BusinessException
	 */
	public void saveCommitValidate(AggSpePayVO aggVO, boolean flag)
			throws BusinessException;

	/**
	 * �ջ�ʱ�����Ƿ����������ж��Ƿ���ȡ��������У��
	 * 
	 * @param aggVO
	 * @param flag
	 * @throws BusinessException
	 */
	public void unCommitValidate(AggSpePayVO aggVO, boolean flag)
			throws BusinessException;

	/**
	 * ���ؾ�����Ϣ
	 * 
	 * @param pk
	 *            ����
	 * @param ts
	 *            ʱ���
	 * @return
	 * @throws BusinessException
	 */
	public AggSpePayVO decide(String pk, String ts) throws BusinessException;

	/**
	 * ��������
	 * 
	 * @param aggVO
	 * @param isSubmit
	 *            �Ƿ񱣴��ύ
	 * @return
	 * @throws BusinessException
	 */
	public AggSpePayVO insert(AggSpePayVO aggVO, boolean hasSubmit)
			throws BusinessException;

	/**
	 * �޸ı���
	 * 
	 * @param aggVO
	 * @param hasSubmit
	 *            �Ƿ񱣴��ύ
	 * @return
	 * @throws BusinessException
	 */
	public AggSpePayVO update(AggSpePayVO aggVO, boolean hasSubmit)
			throws BusinessException;

	/**
	 * �����޸���Ϣ
	 * 
	 * @param pk
	 *            ����
	 * @return
	 * @throws BusinessException
	 */
	public AggSpePayVO edit(String pk) throws BusinessException;

}
