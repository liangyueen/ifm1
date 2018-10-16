package nccloud.web.ifm.util;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import nc.itf.ifm.IIFMPrecisionService;
import nc.itf.ifm.IInvestIncomeQueryService;
import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.imf.constants.IncomeConst;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFDate;
import nccloud.dto.baseapp.querytree.dataformat.QueryTreeFormatVO;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.core.json.IJson;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.pubitf.platform.query.INCCloudQueryService;

public class IncomeUtil {
	private static IInvestIncomeQueryService eQueryService = null;
	private static IIFMPrecisionService ePrecService = null;
	public static synchronized IInvestIncomeQueryService getEQueryService() {
		if (eQueryService == null) {
			eQueryService = ServiceLocator
					.find(IInvestIncomeQueryService.class);
		}
		return eQueryService;
	}
	/**
	 * �õ�������Ϣ
	 * 
	 * @param request
	 * @param type
	 * @return
	 */
	public static <T> T getRequestInfo(IRequest request, Type type) {
		String read = request.read();
		IJson json = JsonFactory.create();
		T info = json.fromJson(read, type);
		if (null == info) {
			return null;
		}
		return info;
	}
	
	public static AggInvestIncomeVO[] getIncomeVO(String[] pk_incomes){
		AggInvestIncomeVO[] vos=null;
		try {
			IInvestIncomeQueryService service=ServiceLocator.find(IInvestIncomeQueryService.class);
			vos=service.queryIncomeByPks(pk_incomes);
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("��ѯ����ʧ�ܣ�" + e.getMessage());
		}
		return vos;
	}
	public static Map<String, String> getGroupData(QueryTreeFormatVO queryParam) {
		Map<String, String> map=new HashMap<String, String>();
		try {
			INCCloudQueryService qservice = ServiceLocator
					.find(INCCloudQueryService.class);
			IQueryScheme scheme = qservice.convertCondition(queryParam);
			IInvestIncomeQueryService service = ServiceLocator
					.find(IInvestIncomeQueryService.class);
			String[] status = service.queryCCIncomeBySchema(scheme);
			int numSPZ = 0, numDTJ = 0;
			for (int i = 0; i < status.length; i++) {
				if (status[i].equals("1")) {// ���ύ
					numDTJ++;
					continue;
				}
				if (status[i].equals("2")) {// ������(������״̬��������)
					numSPZ++;
					continue;
				}
			}
			map.put("SPZ",String.valueOf(numSPZ));
			map.put("DTJ",String.valueOf(numDTJ));
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("��ѯ����ʧ�ܣ�" + e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	public static synchronized IIFMPrecisionService getEPrecService() {
		if (ePrecService == null) {
			ePrecService = ServiceLocator.find(IIFMPrecisionService.class);
		}
		return ePrecService;
	}
	/**
	 * ����ԭ�ұ��ҽ�����
	 * 
	 * @param vo
	 * @param isRecaculate
	 * @param busiDate
	 * @return
	 * @throws BusinessException
	 */
	public static ISuperVO processPrecision(ISuperVO vo, boolean isRecaculate,
			UFDate busiDate) throws BusinessException {
		ISuperVO newvo = getEPrecService().processPrecision(vo, true,
				getFieldMap(), busiDate);
		return newvo;
	}

	/**
	 * ��ȡ��Ҫ���㾫�ȵ���������map
	 * 
	 * @return
	 */
	private static HashMap<String, String> getFieldMap() {
		HashMap<String, String> field = new HashMap<String, String>();
		// ����
		field.put(IncomeConst.FIELD_PK_CCCURRTYPE, InvestIncomeVO.PK_CURRTYPE);
		// ԭ�ҽ����������
		field.put(IncomeConst.FIELD_MONEY, InvestIncomeVO.ACTUALMOENY);
		// ��֯���һ�����������
		field.put(IncomeConst.FIELD_ORGRATE, InvestIncomeVO.OLCRATE);
		// ��֯���ҽ����������
		field.put(IncomeConst.FIELD_ORGMNY, InvestIncomeVO.OLCMOENY);
		// ���ű��һ�����������
		field.put(IncomeConst.FIELD_GROUPRATE, InvestIncomeVO.GLCRATE);
		// ���ű��ҽ����������
		field.put(IncomeConst.FIELD_GROUPMNY, InvestIncomeVO.GLCRATE);
		// ȫ�ֱ��һ�����������
		field.put(IncomeConst.FIELD_GLOBALRATE, InvestIncomeVO.GLLCRATE);
		// ȫ�ֱ��ҽ����������
		field.put(IncomeConst.FIELD_GLOBALMNYFIELD, InvestIncomeVO.GLLMOENY);
		return field;
	}
	/**
	 * ��ѯ������֯���ڼ���
	 * 
	 * @param pk_org
	 * @return
	 */
	public static String getGroupByOrg(String pk_org) throws BusinessException {
		IOrgUnitPubService_C orgUnitQryService = ServiceLocator
				.find(IOrgUnitPubService_C.class);
		String pk_group = null;
		OrgVO[] orgVOs = orgUnitQryService.getOrgs(new String[] { pk_org },
				new String[] { "pk_group" });
		if (orgVOs == null || orgVOs.length <= 0) {
			ExceptionUtils.wrapBusinessException("��ȡ������֯��Ӧ�ļ���ʧ�ܡ�");
		}
		pk_group = (String) orgVOs[0]
				.getAttributeValue(IncomeConst.FIELD_PK_GROUP);
		return pk_group;
	}

	/**
	 * ������֯������ȡ��֯Ĭ�ϱ���
	 * 
	 * @param pk_org
	 * @return
	 * @throws BusinessException
	 */
	public static String getOrgStandardCurrtype(String pk_org)
			throws BusinessException {
		String pk_currtype = getEPrecService().getOrgStandardCurrtype(pk_org);
		return pk_currtype;
	}
	/**
	 * ��ѯ�û�Ĭ�ϵ�����֯
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public static String getUserDefaultOrgUnit() throws BusinessException {
		return ServiceLocator.find(IInvestIncomeQueryService.class)
				.getDefaultOrgUnit();
	}
}
