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
	 * 自动加载数据
	 * 
	 * @param qryParam
	 * @return
	 * @throws BusinessException
	 */
	public ListQryResult<AggSpePayVO> autoLoadData(ListQryParam qryParam,
			Map<String, String> extParam) throws BusinessException;

	/**
	 * 获取业务时间
	 * 
	 * @param queryParam
	 * @return
	 * @throws BusinessException
	 */
	public UFDate getProcessedBusiDate(DateManageQueryVO queryParam)
			throws BusinessException;

	/**
	 * 查询分组总数数据
	 * 
	 * @param queryScheme查询方案
	 * @return 分组总数数据
	 */
	public Map<String, String> queryGroupCountData(IQueryScheme queryScheme)
			throws BusinessException;

	/**
	 * 列表查询 旨在通过一次连接处理分组合计数据，单页数据，以及分页信息的查询处理,解决之前的查询会有多次连接的问题
	 * 
	 * @param qryParam
	 *            列表查询参数
	 * @return 列表查询结果
	 * @throws BusinessException
	 */
	public ListQryResult<AggSpePayVO> listQuery(ListQryParam qryParam)
			throws BusinessException;

	/**
	 * 卡片查询 查询业务数据后进行精度处理
	 * 
	 * @param pk
	 * @return
	 * @throws BusinessException
	 */
	public AggSpePayVO cardQuery(String pk) throws BusinessException;

	/**
	 * 切换页数查询 查询业务数据后进行精度处理
	 * 
	 * @param pk
	 * @return
	 * @throws BusinessException
	 */
	public AggSpePayVO[] queryPageChangeByPKS(String[] pks)
			throws BusinessException;

	/**
	 * 删除
	 * 
	 * @param pk
	 *            主键
	 * @param ts
	 *            时间戳
	 * @throws BusinessException
	 */
	public AggSpePayVO delete(String pk, String ts) throws BusinessException;

	/**
	 * 提交
	 * 
	 * @param pk
	 *            主键
	 * @param ts
	 *            时间戳
	 */
	public AggSpePayVO commit(String pk, String ts) throws BusinessException;

	/**
	 * 收回
	 * 
	 * @param pk
	 *            主键
	 * @param ts
	 *            时间戳
	 * @throws BusinessException
	 */
	public AggSpePayVO unCommit(String pk, String ts) throws BusinessException;

	/**
	 * 制证
	 * 
	 * @param pk
	 *            主键
	 * @param ts
	 *            时间戳
	 * @return 制证后数据
	 * @throws BusinessException
	 */
	public AggSpePayVO voucher(String pk, String ts) throws BusinessException;

	/**
	 * 取消制证
	 * 
	 * @param pk
	 *            主键
	 * @param ts
	 *            时间戳
	 * @return 取消制证后数据
	 * @throws BusinessException
	 */
	public AggSpePayVO unVoucher(String pk, String ts) throws BusinessException;

	/**
	 * 联查下游单据
	 * 
	 * @param pks
	 * @return
	 * @throws BusinessException
	 */
	public Map<String, List<String>> buildLinkAfterBillInfo(String[] pks)
			throws BusinessException;

	/**
	 * 退回
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
	 * 提交时根据是否有审批流判断是否走审批的校验
	 * 
	 * @param aggVO
	 * @param flag
	 * @throws BusinessException
	 */
	public void commitValidate(AggSpePayVO aggVO, boolean flag)
			throws BusinessException;

	/**
	 * 新增保存提交时根据是否有审批流判断是否走审批的校验
	 * 
	 * @param aggVO
	 * @param flag
	 * @throws BusinessException
	 */
	public void saveCommitValidate(AggSpePayVO aggVO, boolean flag)
			throws BusinessException;

	/**
	 * 收回时根据是否有审批流判断是否走取消审批的校验
	 * 
	 * @param aggVO
	 * @param flag
	 * @throws BusinessException
	 */
	public void unCommitValidate(AggSpePayVO aggVO, boolean flag)
			throws BusinessException;

	/**
	 * 加载经办信息
	 * 
	 * @param pk
	 *            主键
	 * @param ts
	 *            时间戳
	 * @return
	 * @throws BusinessException
	 */
	public AggSpePayVO decide(String pk, String ts) throws BusinessException;

	/**
	 * 新增保存
	 * 
	 * @param aggVO
	 * @param isSubmit
	 *            是否保存提交
	 * @return
	 * @throws BusinessException
	 */
	public AggSpePayVO insert(AggSpePayVO aggVO, boolean hasSubmit)
			throws BusinessException;

	/**
	 * 修改保存
	 * 
	 * @param aggVO
	 * @param hasSubmit
	 *            是否保存提交
	 * @return
	 * @throws BusinessException
	 */
	public AggSpePayVO update(AggSpePayVO aggVO, boolean hasSubmit)
			throws BusinessException;

	/**
	 * 加载修改信息
	 * 
	 * @param pk
	 *            主键
	 * @return
	 * @throws BusinessException
	 */
	public AggSpePayVO edit(String pk) throws BusinessException;

}
