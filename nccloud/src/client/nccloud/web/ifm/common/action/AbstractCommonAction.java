package nccloud.web.ifm.common.action;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.logging.Logger;
import nc.bs.uif2.validation.DefaultValidationService;
import nc.bs.uif2.validation.Validator;
import nc.lightapp.pubapp.web.template.ref.util.StringUtils;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.transfer.bill.ServerBillCombinClient;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.tmpub.batch.validation.DefaultBatchValidationService;
import nc.vo.tmpub.batch.validation.IBatchValidator;
import nc.vo.tmpub.util.ArrayUtil;
import nc.vo.tmpub.util.StringUtil;
import nc.vo.uap.busibean.exception.BusiBeanException;
import nccloud.framework.core.json.IJson;
//import nccloud.framework.core.util.GridCompareUtils;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.processor.template.ExtBillCardConvertProcessor;
import nccloud.framework.web.processor.template.GridConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardFormulaHandler;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCard;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCardFormulaHandler;
import nccloud.framework.web.ui.pattern.grid.Grid;
import nccloud.pubitf.riart.pflow.CloudPFlowContext;
import nccloud.pubitf.riart.pflow.ICloudScriptPFlowService;
import nccloud.pubitf.tmpub.pub.ITMPrecisionServiceForNCC;
import nccloud.vo.tmpub.precison.PrecisionField;
import nccloud.web.cmp.apply.apply.common.AbstrCommMultiAction;
import nccloud.web.tmpub.util.NCCFrontPrecisionUtil;

/**
 * NCC 公共按钮
 * <p>
 * 旨在统一处理controller层与前端额交互 <br/>
 * 1、支持自定义request的请求数据模型 <br/>
 * 2、支持多种前端业务数据模型(Grid/BillCard/ExtBillCard) <br/>
 * 3、支持对响应前端的标准数据模型(Grid/BillCard/ExtBillCard)进行翻译和精度处理<br/>
 * 4、提供调用动作脚本的api<br/>
 * 5、提供根据业务校验器校验<br/>
 * 6、支持模板中的公式处理<br/>
 * 7、支持差异化处理<br/>
 * 8、支持合并数据<br/>
 * 9、提供MD元数据查询服务，获取泛型参数API<br/>
 * 
 * @author tangleic
 * @date 20180705
 * 
 * @param <T>
 *            NC业务单据数据模型(聚合VO)
 */
public abstract class AbstractCommonAction<T extends AbstractBill> implements
		ICommonAction {
	/**
	 * 合并工具
	 */
	private ServerBillCombinClient<T> combinUtil = null;
	/**
	 * MD元数据查询服务
	 */
	private static IMDPersistenceQueryService mdQryService;

	/**
	 * 获取前端请求数据
	 * 
	 * @param request
	 *            请求对象
	 * @return 请求数据
	 * @throws BusiBeanException
	 */
	protected Object getReqData(IRequest request) throws BusinessException {
		String dataStr = request.read();
		if (StringUtil.isNull(dataStr)) {
			throw new BusinessException("前端请求数据为空！");
		}
		Logger.debug("前端请求数据[" + dataStr + "]");
		IJson json = JsonFactory.create();
		Object queryParamObject = json.fromJson(dataStr, getReqDataClass());
		return queryParamObject;
	}

	/**
	 * 获取请求数据类型
	 * 
	 * @return
	 */
	protected abstract Class<?> getReqDataClass();

	/**
	 * 表头精度处理字段列表
	 * 
	 * @return
	 */
	protected List<PrecisionField> getHeadPrecisionFields() {
		return null;
	}

	/**
	 * 表体精度处理字段明细( key:表体区域编码,value:精度字段列表)
	 * 
	 * @return
	 */
	protected Map<String, List<PrecisionField>> getBodyPrecisionFields() {
		return null;
	}

	/**
	 * 转换成前端标准数据模型
	 * <p>
	 * 1、根据需要觉得是否处理精度<br/>
	 * 2、对数据进行翻译<br/>
	 * 3、将aggVO转换成前端标准的数据结构<br/>
	 * 
	 * @param reqData
	 *            请求数据
	 * @param targetClazz
	 *            目标数据类型(Grid/BillCard/ExtBillCard)
	 * @param pageCode
	 *            页面编码
	 * @param Objs
	 *            业务数据数组(聚合VO)
	 * 
	 * @return 标准前端数据（Grid/BillCard/ExtBillCard）
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	protected Object convert2FrontStdData(Object reqData, Class<?> targetClazz,
			String pageCode, T... aggVOs) throws BusinessException {
		if (ArrayUtil.isNull(aggVOs)) {
			return null;
		}
		if (StringUtil.isNull(pageCode)) {
			throw new BusinessException("页面编码为空!");
		}
		Translator translator = new Translator();
		// 响应数据类型为表格,则构建表格数据
		if (targetClazz == Grid.class) {
			GridConvertProcessor processor = new GridConvertProcessor();
			Grid grid = processor.convert(pageCode, fetchHeadVOs(aggVOs));
			// 精度处理
			NCCFrontPrecisionUtil.processGridPrecision(grid,
					getHeadPrecisionFields());
			// 翻译
			translator.translate(grid);
			return grid;
		}
		// 响应数据类型为单表数据，则构建单表数据
		else if (targetClazz == BillCard.class) {
			BillCardConvertProcessor processor = new BillCardConvertProcessor();
			BillCard billCard = processor.convert(pageCode, aggVOs[0]);
			// 精度处理
			NCCFrontPrecisionUtil.processBillCardPrecision(billCard,
					getHeadPrecisionFields(), getBodyPrecisionFields());
			// 翻译
			translator.translate(billCard);
			// 公式处理
			BillCardFormulaHandler handler = new BillCardFormulaHandler(
					billCard);
			handler.handleLoadFormula();
			handler.handleBodyLoadFormula();
			return billCard;
		}
		// 响应数据类型为多表体数据，则构建多表体数据
		else if (targetClazz == ExtBillCard.class) {
			ExtBillCardConvertProcessor processor = new ExtBillCardConvertProcessor();
			ExtBillCard extBillCard = processor.convert(pageCode, aggVOs[0]);
			// 精度处理
			NCCFrontPrecisionUtil.processExtBillCardPrecision(extBillCard,
					getHeadPrecisionFields(), getBodyPrecisionFields());
			// 翻译
			translator.translate(extBillCard);
			// 公式处理
			ExtBillCardFormulaHandler handler = new ExtBillCardFormulaHandler(
					extBillCard);
			handler.handleLoadFormula();
			handler.handleBodyLoadFormula();
			return extBillCard;
		} else {
			throw new BusinessException("不支持的数据类型[" + targetClazz.getName()
					+ "]!");
		}
	}

	/**
	 * 是否进行差异化处理
	 * 
	 * @return
	 */
	protected boolean needProcessDiff() {
		return true;
	}

	/**
	 * 处理差异化
	 * <p>
	 * 将新数据与原始数据进行比对，只保留变化的数据，降低网络流量
	 * 
	 * @param oldData
	 *            原始数据(BillCard/ExtBillCard)
	 * @param newData
	 *            新数据(BillCard/ExtBillCard)
	 * @return
	 * @throws BusinessException
	 */
	protected Object processDiffBillData(Object oldData, Object newData)
			throws BusinessException {
		/*if (oldData == null) {
			return newData;
		}
		if ((oldData instanceof BillCard) && (newData instanceof BillCard)) {
			// 没有表体即单表数据不用进行差异化处理
			if (((BillCard) oldData).getBody() == null) {
				return newData;
			}
			return GridCompareUtils.compareBillCardGrid((BillCard) oldData,
					(BillCard) newData);
		} else if ((oldData instanceof ExtBillCard)
				&& (newData instanceof ExtBillCard)) {
			return GridCompareUtils.compareExtBillCardGrid(
					(ExtBillCard) oldData, (ExtBillCard) newData);
		} else {
			throw new BusinessException("不支持的数据类型["
					+ oldData.getClass().getName() + ","
					+ newData.getClass().getName() + "]");
		}*/
		return newData;
	}

	/**
	 * 处理数据合并
	 * 
	 * @param clientData
	 *            业务处理之前的聚合VO数据
	 * @param dbData
	 *            业务处理完毕之后的聚合VO数据
	 * @throws BusinessException
	 */
	protected void processCombinBillData(T[] clientDatas, T[] dbDatas)
			throws BusinessException {
		getCombinUtil().combine(clientDatas, dbDatas);
	}

	/**
	 * 合并处理
	 * <p>
	 * 业务处理过程中一旦数据重新从数据库中查询，则需要对查询后的数据进行合并操作，避免丢失rowID
	 * 
	 * @param clientData
	 *            业务处理之前的聚合VO数据
	 * @param dbData
	 *            业务处理完毕之后的聚合VO数据
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	protected void processCombinBillData(T clientData, T dbData)
			throws BusinessException {
		Class<?> genericParamClass = getGenericParamClass(AbstractCommonAction.class);
		T[] dbDatas = (T[]) Array.newInstance(genericParamClass, 1);
		T[] clientDatas = (T[]) Array.newInstance(genericParamClass, 1);
		dbDatas[0] = dbData;
		clientDatas[0] = clientData;
		processCombinBillData(clientDatas, dbDatas);
	}

	/**
	 * 获取泛型参数类型
	 * 
	 * @param targetClazz
	 *            目标class(使用泛型的类)
	 * @return
	 */
	protected Class<?> getGenericParamClass(Class<?> targetClazz) {
		Type type = null;
		Class<?> clazz = this.getClass();
		// TOSEE 循环遍历基类，获取泛型的参数类型
		while (type == null || (!(type instanceof ParameterizedType))) {
			type = clazz.getGenericSuperclass();
			if (clazz == targetClazz) {
				break;
			}
			clazz = clazz.getSuperclass();
		}
		Type[] actualTypeArguments = ((ParameterizedType) type)
				.getActualTypeArguments();
		return (Class<?>) actualTypeArguments[0];
	}

	/**
	 * 获取数据合并工具
	 * 
	 * @return
	 */
	private ServerBillCombinClient<T> getCombinUtil() {
		if (combinUtil == null) {
			combinUtil = new ServerBillCombinClient<T>();
		}
		return combinUtil;
	}

	/**
	 * 获取查询数据服务(单例)
	 * 
	 * @return
	 */
	protected static synchronized IMDPersistenceQueryService getMDQryService() {
		if (mdQryService == null) {
			synchronized (AbstrCommMultiAction.class) {
				mdQryService = ServiceLocator
						.find(IMDPersistenceQueryService.class);
			}
		}
		return mdQryService;
	}

	/**
	 * 抓取表头VO
	 * 
	 * @param aggVOs
	 *            聚合VO数组
	 * @return 表头VO数组
	 * @throws BusinessException
	 */
	private ISuperVO[] fetchHeadVOs(T[] aggVOs) throws BusinessException {
		if (ArrayUtil.isNull(aggVOs)) {
			return null;
		}
		List<ISuperVO> headVOList = new ArrayList<>();
		for (T aggVO : aggVOs) {
			if (aggVO == null || aggVO.getParent() == null) {
				continue;
			}
			headVOList.add(aggVO.getParent());
		}
		if (headVOList.isEmpty()) {
			return null;
		}
		return headVOList.toArray(new ISuperVO[headVOList.size()]);
	}

	/**
	 * 调用动作脚本（单笔数据）
	 * 
	 * @param actionCode
	 *            动作编码
	 * @param billType
	 *            单据类型
	 * @param aggVO
	 *            业务单据
	 * @return
	 * @throws BusinessException
	 */
	protected Object callActionScript(String actionCode, String billType,
			T aggVO) throws BusinessException {
		if (StringUtils.isEmpty(actionCode) || aggVO == null
				|| aggVO.getParent() == null) {
			throw new BusinessException("调用动作脚本时动作名称和处理数据不能为空！");
		}
		CloudPFlowContext context = new CloudPFlowContext();
		context.setActionName(actionCode);
		context.setBillType(billType);
		context.setBillVos(new AbstractBill[] { aggVO });
		Logger.debug("开始调用动作脚本 ActionName[" + actionCode + "] BillType["
				+ billType + "]...");
		ICloudScriptPFlowService service = ServiceLocator
				.find(ICloudScriptPFlowService.class);
		Object[] result = service.exeScriptPFlow(context);
		Logger.debug("调用动作脚本 ActionName[" + actionCode + "] BillType["
				+ billType + "]结束");
		if (ArrayUtil.isNull(result)) {
			return null;
		} else {
			return result[0];
		}
	}

	/**
	 * 执行动作脚本（带额外参数）
	 * 
	 * @param actionCode
	 *            动作名称
	 * @param billType
	 *            单据类型
	 * @param eParam
	 *            额外参数
	 * @param aggVO
	 *            业务单据
	 * 
	 * @return
	 * @throws BusinessException
	 */
	protected Object callActionScript(String actionCode, String billType,
			Map<Object, Object> eParam, T aggVO) throws BusinessException {
		if (StringUtils.isEmpty(actionCode) || aggVO == null
				|| aggVO.getParent() == null) {
			throw new BusinessException("调用动作脚本时动作名称和处理数据不能为空！");
		}
		CloudPFlowContext context = new CloudPFlowContext();
		context.setActionName(actionCode);
		context.setBillType(billType);
		context.setBillVos(new AbstractBill[] { aggVO });
		context.seteParam(eParam);
		Logger.debug("开始调用动作脚本 ActionName[" + actionCode + "] BillType["
				+ billType + "]...");
		ICloudScriptPFlowService service = ServiceLocator
				.find(ICloudScriptPFlowService.class);
		Object[] result = service.exeScriptPFlow(context);
		Logger.debug("调用动作脚本 ActionName[" + actionCode + "] BillType["
				+ billType + "]结束");
		if (ArrayUtil.isNull(result)) {
			return null;
		} else {
			return result[0];
		}
	}

	/**
	 * 调用动作脚本（单笔数据）
	 * 
	 * @param actionName
	 *            动作编码
	 * @param billType
	 *            单据类型
	 * @param aggVO
	 *            业务单据
	 * @param userObj
	 *            额外参数对象
	 * @return
	 * @throws BusinessException
	 */
	protected Object callActionScript(String actionName, String billType,
			T aggVO, PfUserObject userObj) throws BusinessException {
		if (StringUtils.isEmpty(actionName) || aggVO == null
				|| aggVO.getParent() == null) {
			throw new BusinessException("调用动作脚本时动作名称和处理数据不能为空！");
		}
		CloudPFlowContext context = new CloudPFlowContext();
		context.setActionName(actionName);
		context.setBillType(billType);
		context.setBillVos(new AbstractBill[] { aggVO });
		context.setUserObj(new PfUserObject[] { userObj });
		Logger.debug("开始调用动作脚本 ActionName[" + actionName + "] BillType["
				+ billType + "]...");
		ICloudScriptPFlowService service = ServiceLocator
				.find(ICloudScriptPFlowService.class);
		Object[] result = service.exeScriptPFlow(context);
		Logger.debug("调用动作脚本 ActionName[" + actionName + "] BillType["
				+ billType + "]结束");
		if (ArrayUtil.isNull(result)) {
			return null;
		} else {
			return result[0];
		}
	}

	/**
	 * 获取精度处理服务
	 * 
	 * @return
	 */
	protected ITMPrecisionServiceForNCC getTMPrecisionService() {
		return ServiceLocator.find(ITMPrecisionServiceForNCC.class);
	}

	/**
	 * 获取校验器队列
	 * 
	 * @return
	 */
	protected List<Validator> getValidators() {
		return null;
	}

	/**
	 * 获取校验器队列
	 * 
	 * @return
	 */
	protected List<IBatchValidator<T>> getBatchValidators() {
		return null;
	}

	/**
	 * 获取校验器服务
	 * 
	 * @param validators
	 *            校验器队列
	 * @return
	 */
	private DefaultValidationService getValidateService(
			List<Validator> validators) {
		DefaultValidationService validateService = new DefaultValidationService();
		validateService.setValidators(validators);
		return validateService;
	}

	/**
	 * 获取批量校验服务
	 * 
	 * @param validators
	 *            校验器队列
	 * @return
	 */
	private DefaultBatchValidationService<T> getBatchValidateService(
			List<IBatchValidator<T>> validators) {
		DefaultBatchValidationService<T> batchValidateService = new DefaultBatchValidationService<>();
		batchValidateService.setValidators(validators);
		return batchValidateService;
	}

	/**
	 * 是否需要校验
	 * 
	 * @return
	 */
	protected boolean isValidate() {
		return true;
	}

	/**
	 * 业务校验
	 * 
	 * @param aggVOs
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	protected void validate(T... aggVOs) throws BusinessException {
		if (!isValidate() || ArrayUtil.isNull(getValidators())
				&& ArrayUtil.isNull(getBatchValidators())) {
			return;
		}
		if (!ArrayUtil.isNull(getValidators())
				&& getValidators().get(0) instanceof Validator) {
			getValidateService(getValidators()).validate(aggVOs[0]);
		} else if (!ArrayUtil.isNull(getBatchValidators())
				&& getBatchValidators().get(0) instanceof IBatchValidator) {
			getBatchValidateService(getBatchValidators()).validate(aggVOs);
		}
	}

}
