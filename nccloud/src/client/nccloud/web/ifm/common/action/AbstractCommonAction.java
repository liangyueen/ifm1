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
 * NCC ������ť
 * <p>
 * ּ��ͳһ����controller����ǰ�˶�� <br/>
 * 1��֧���Զ���request����������ģ�� <br/>
 * 2��֧�ֶ���ǰ��ҵ������ģ��(Grid/BillCard/ExtBillCard) <br/>
 * 3��֧�ֶ���Ӧǰ�˵ı�׼����ģ��(Grid/BillCard/ExtBillCard)���з���;��ȴ���<br/>
 * 4���ṩ���ö����ű���api<br/>
 * 5���ṩ����ҵ��У����У��<br/>
 * 6��֧��ģ���еĹ�ʽ����<br/>
 * 7��֧�ֲ��컯����<br/>
 * 8��֧�ֺϲ�����<br/>
 * 9���ṩMDԪ���ݲ�ѯ���񣬻�ȡ���Ͳ���API<br/>
 * 
 * @author tangleic
 * @date 20180705
 * 
 * @param <T>
 *            NCҵ�񵥾�����ģ��(�ۺ�VO)
 */
public abstract class AbstractCommonAction<T extends AbstractBill> implements
		ICommonAction {
	/**
	 * �ϲ�����
	 */
	private ServerBillCombinClient<T> combinUtil = null;
	/**
	 * MDԪ���ݲ�ѯ����
	 */
	private static IMDPersistenceQueryService mdQryService;

	/**
	 * ��ȡǰ����������
	 * 
	 * @param request
	 *            �������
	 * @return ��������
	 * @throws BusiBeanException
	 */
	protected Object getReqData(IRequest request) throws BusinessException {
		String dataStr = request.read();
		if (StringUtil.isNull(dataStr)) {
			throw new BusinessException("ǰ����������Ϊ�գ�");
		}
		Logger.debug("ǰ����������[" + dataStr + "]");
		IJson json = JsonFactory.create();
		Object queryParamObject = json.fromJson(dataStr, getReqDataClass());
		return queryParamObject;
	}

	/**
	 * ��ȡ������������
	 * 
	 * @return
	 */
	protected abstract Class<?> getReqDataClass();

	/**
	 * ��ͷ���ȴ����ֶ��б�
	 * 
	 * @return
	 */
	protected List<PrecisionField> getHeadPrecisionFields() {
		return null;
	}

	/**
	 * ���徫�ȴ����ֶ���ϸ( key:�����������,value:�����ֶ��б�)
	 * 
	 * @return
	 */
	protected Map<String, List<PrecisionField>> getBodyPrecisionFields() {
		return null;
	}

	/**
	 * ת����ǰ�˱�׼����ģ��
	 * <p>
	 * 1��������Ҫ�����Ƿ�����<br/>
	 * 2�������ݽ��з���<br/>
	 * 3����aggVOת����ǰ�˱�׼�����ݽṹ<br/>
	 * 
	 * @param reqData
	 *            ��������
	 * @param targetClazz
	 *            Ŀ����������(Grid/BillCard/ExtBillCard)
	 * @param pageCode
	 *            ҳ�����
	 * @param Objs
	 *            ҵ����������(�ۺ�VO)
	 * 
	 * @return ��׼ǰ�����ݣ�Grid/BillCard/ExtBillCard��
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	protected Object convert2FrontStdData(Object reqData, Class<?> targetClazz,
			String pageCode, T... aggVOs) throws BusinessException {
		if (ArrayUtil.isNull(aggVOs)) {
			return null;
		}
		if (StringUtil.isNull(pageCode)) {
			throw new BusinessException("ҳ�����Ϊ��!");
		}
		Translator translator = new Translator();
		// ��Ӧ��������Ϊ���,�򹹽��������
		if (targetClazz == Grid.class) {
			GridConvertProcessor processor = new GridConvertProcessor();
			Grid grid = processor.convert(pageCode, fetchHeadVOs(aggVOs));
			// ���ȴ���
			NCCFrontPrecisionUtil.processGridPrecision(grid,
					getHeadPrecisionFields());
			// ����
			translator.translate(grid);
			return grid;
		}
		// ��Ӧ��������Ϊ�������ݣ��򹹽���������
		else if (targetClazz == BillCard.class) {
			BillCardConvertProcessor processor = new BillCardConvertProcessor();
			BillCard billCard = processor.convert(pageCode, aggVOs[0]);
			// ���ȴ���
			NCCFrontPrecisionUtil.processBillCardPrecision(billCard,
					getHeadPrecisionFields(), getBodyPrecisionFields());
			// ����
			translator.translate(billCard);
			// ��ʽ����
			BillCardFormulaHandler handler = new BillCardFormulaHandler(
					billCard);
			handler.handleLoadFormula();
			handler.handleBodyLoadFormula();
			return billCard;
		}
		// ��Ӧ��������Ϊ��������ݣ��򹹽����������
		else if (targetClazz == ExtBillCard.class) {
			ExtBillCardConvertProcessor processor = new ExtBillCardConvertProcessor();
			ExtBillCard extBillCard = processor.convert(pageCode, aggVOs[0]);
			// ���ȴ���
			NCCFrontPrecisionUtil.processExtBillCardPrecision(extBillCard,
					getHeadPrecisionFields(), getBodyPrecisionFields());
			// ����
			translator.translate(extBillCard);
			// ��ʽ����
			ExtBillCardFormulaHandler handler = new ExtBillCardFormulaHandler(
					extBillCard);
			handler.handleLoadFormula();
			handler.handleBodyLoadFormula();
			return extBillCard;
		} else {
			throw new BusinessException("��֧�ֵ���������[" + targetClazz.getName()
					+ "]!");
		}
	}

	/**
	 * �Ƿ���в��컯����
	 * 
	 * @return
	 */
	protected boolean needProcessDiff() {
		return true;
	}

	/**
	 * ������컯
	 * <p>
	 * ����������ԭʼ���ݽ��бȶԣ�ֻ�����仯�����ݣ�������������
	 * 
	 * @param oldData
	 *            ԭʼ����(BillCard/ExtBillCard)
	 * @param newData
	 *            ������(BillCard/ExtBillCard)
	 * @return
	 * @throws BusinessException
	 */
	protected Object processDiffBillData(Object oldData, Object newData)
			throws BusinessException {
		/*if (oldData == null) {
			return newData;
		}
		if ((oldData instanceof BillCard) && (newData instanceof BillCard)) {
			// û�б��弴�������ݲ��ý��в��컯����
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
			throw new BusinessException("��֧�ֵ���������["
					+ oldData.getClass().getName() + ","
					+ newData.getClass().getName() + "]");
		}*/
		return newData;
	}

	/**
	 * �������ݺϲ�
	 * 
	 * @param clientData
	 *            ҵ����֮ǰ�ľۺ�VO����
	 * @param dbData
	 *            ҵ�������֮��ľۺ�VO����
	 * @throws BusinessException
	 */
	protected void processCombinBillData(T[] clientDatas, T[] dbDatas)
			throws BusinessException {
		getCombinUtil().combine(clientDatas, dbDatas);
	}

	/**
	 * �ϲ�����
	 * <p>
	 * ҵ���������һ���������´����ݿ��в�ѯ������Ҫ�Բ�ѯ������ݽ��кϲ����������ⶪʧrowID
	 * 
	 * @param clientData
	 *            ҵ����֮ǰ�ľۺ�VO����
	 * @param dbData
	 *            ҵ�������֮��ľۺ�VO����
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
	 * ��ȡ���Ͳ�������
	 * 
	 * @param targetClazz
	 *            Ŀ��class(ʹ�÷��͵���)
	 * @return
	 */
	protected Class<?> getGenericParamClass(Class<?> targetClazz) {
		Type type = null;
		Class<?> clazz = this.getClass();
		// TOSEE ѭ���������࣬��ȡ���͵Ĳ�������
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
	 * ��ȡ���ݺϲ�����
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
	 * ��ȡ��ѯ���ݷ���(����)
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
	 * ץȡ��ͷVO
	 * 
	 * @param aggVOs
	 *            �ۺ�VO����
	 * @return ��ͷVO����
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
	 * ���ö����ű����������ݣ�
	 * 
	 * @param actionCode
	 *            ��������
	 * @param billType
	 *            ��������
	 * @param aggVO
	 *            ҵ�񵥾�
	 * @return
	 * @throws BusinessException
	 */
	protected Object callActionScript(String actionCode, String billType,
			T aggVO) throws BusinessException {
		if (StringUtils.isEmpty(actionCode) || aggVO == null
				|| aggVO.getParent() == null) {
			throw new BusinessException("���ö����ű�ʱ�������ƺʹ������ݲ���Ϊ�գ�");
		}
		CloudPFlowContext context = new CloudPFlowContext();
		context.setActionName(actionCode);
		context.setBillType(billType);
		context.setBillVos(new AbstractBill[] { aggVO });
		Logger.debug("��ʼ���ö����ű� ActionName[" + actionCode + "] BillType["
				+ billType + "]...");
		ICloudScriptPFlowService service = ServiceLocator
				.find(ICloudScriptPFlowService.class);
		Object[] result = service.exeScriptPFlow(context);
		Logger.debug("���ö����ű� ActionName[" + actionCode + "] BillType["
				+ billType + "]����");
		if (ArrayUtil.isNull(result)) {
			return null;
		} else {
			return result[0];
		}
	}

	/**
	 * ִ�ж����ű��������������
	 * 
	 * @param actionCode
	 *            ��������
	 * @param billType
	 *            ��������
	 * @param eParam
	 *            �������
	 * @param aggVO
	 *            ҵ�񵥾�
	 * 
	 * @return
	 * @throws BusinessException
	 */
	protected Object callActionScript(String actionCode, String billType,
			Map<Object, Object> eParam, T aggVO) throws BusinessException {
		if (StringUtils.isEmpty(actionCode) || aggVO == null
				|| aggVO.getParent() == null) {
			throw new BusinessException("���ö����ű�ʱ�������ƺʹ������ݲ���Ϊ�գ�");
		}
		CloudPFlowContext context = new CloudPFlowContext();
		context.setActionName(actionCode);
		context.setBillType(billType);
		context.setBillVos(new AbstractBill[] { aggVO });
		context.seteParam(eParam);
		Logger.debug("��ʼ���ö����ű� ActionName[" + actionCode + "] BillType["
				+ billType + "]...");
		ICloudScriptPFlowService service = ServiceLocator
				.find(ICloudScriptPFlowService.class);
		Object[] result = service.exeScriptPFlow(context);
		Logger.debug("���ö����ű� ActionName[" + actionCode + "] BillType["
				+ billType + "]����");
		if (ArrayUtil.isNull(result)) {
			return null;
		} else {
			return result[0];
		}
	}

	/**
	 * ���ö����ű����������ݣ�
	 * 
	 * @param actionName
	 *            ��������
	 * @param billType
	 *            ��������
	 * @param aggVO
	 *            ҵ�񵥾�
	 * @param userObj
	 *            �����������
	 * @return
	 * @throws BusinessException
	 */
	protected Object callActionScript(String actionName, String billType,
			T aggVO, PfUserObject userObj) throws BusinessException {
		if (StringUtils.isEmpty(actionName) || aggVO == null
				|| aggVO.getParent() == null) {
			throw new BusinessException("���ö����ű�ʱ�������ƺʹ������ݲ���Ϊ�գ�");
		}
		CloudPFlowContext context = new CloudPFlowContext();
		context.setActionName(actionName);
		context.setBillType(billType);
		context.setBillVos(new AbstractBill[] { aggVO });
		context.setUserObj(new PfUserObject[] { userObj });
		Logger.debug("��ʼ���ö����ű� ActionName[" + actionName + "] BillType["
				+ billType + "]...");
		ICloudScriptPFlowService service = ServiceLocator
				.find(ICloudScriptPFlowService.class);
		Object[] result = service.exeScriptPFlow(context);
		Logger.debug("���ö����ű� ActionName[" + actionName + "] BillType["
				+ billType + "]����");
		if (ArrayUtil.isNull(result)) {
			return null;
		} else {
			return result[0];
		}
	}

	/**
	 * ��ȡ���ȴ������
	 * 
	 * @return
	 */
	protected ITMPrecisionServiceForNCC getTMPrecisionService() {
		return ServiceLocator.find(ITMPrecisionServiceForNCC.class);
	}

	/**
	 * ��ȡУ��������
	 * 
	 * @return
	 */
	protected List<Validator> getValidators() {
		return null;
	}

	/**
	 * ��ȡУ��������
	 * 
	 * @return
	 */
	protected List<IBatchValidator<T>> getBatchValidators() {
		return null;
	}

	/**
	 * ��ȡУ��������
	 * 
	 * @param validators
	 *            У��������
	 * @return
	 */
	private DefaultValidationService getValidateService(
			List<Validator> validators) {
		DefaultValidationService validateService = new DefaultValidationService();
		validateService.setValidators(validators);
		return validateService;
	}

	/**
	 * ��ȡ����У�����
	 * 
	 * @param validators
	 *            У��������
	 * @return
	 */
	private DefaultBatchValidationService<T> getBatchValidateService(
			List<IBatchValidator<T>> validators) {
		DefaultBatchValidationService<T> batchValidateService = new DefaultBatchValidationService<>();
		batchValidateService.setValidators(validators);
		return batchValidateService;
	}

	/**
	 * �Ƿ���ҪУ��
	 * 
	 * @return
	 */
	protected boolean isValidate() {
		return true;
	}

	/**
	 * ҵ��У��
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
