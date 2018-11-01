package nc.vo.ifm.investapply.util;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import nc.itf.ifm.IInvestApplyPrecisionService;
import nc.itf.ifm.IInvestApplyQueryService;
import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.imf.constants.TMIMFConst;
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

/**
 * ���������ѯ������
 * 
 * @author futao3
 * @date Aug 29, 2018
 */
public class ApplyQueryUtil {
	
	
	private static IInvestApplyQueryService eQueryService = null;
	private static IInvestApplyPrecisionService ePrecService = null;
	
	
	/**
	 * ������֯������ȡ��֯Ĭ�ϱ���
	 * @param pk_org
	 * @return
	 * @throws BusinessException
	 */
	public static String getOrgStandardCurrtype(String pk_org) throws BusinessException {
		String pk_currtype = getEPrecService().getOrgStandardCurrtype(pk_org);
		return pk_currtype;
	}
	
	public static synchronized IInvestApplyPrecisionService getEPrecService() {
		if (ePrecService == null) {
			ePrecService =  ServiceLocator.find(IInvestApplyPrecisionService.class);
		}
		return ePrecService;
	}
	
	/**
	 * ��ѯ������֯���ڼ���
	 * @param pk_org
	 * @return
	 */
	public static String getGroupByOrg(String pk_org) throws BusinessException {
		IOrgUnitPubService_C orgUnitQryService = ServiceLocator.find(IOrgUnitPubService_C.class);
		String pk_group = null;
		OrgVO[] orgVOs = orgUnitQryService.getOrgs(new String[] { pk_org }, new String[] { TMIMFConst.FIELD_PK_GROUP });
		if(orgVOs == null || orgVOs.length <= 0){
			ExceptionUtils.wrapBusinessException("��ȡ������֯��Ӧ�ļ���ʧ�ܡ�");
		}
		pk_group = (String) orgVOs[0].getAttributeValue(TMIMFConst.FIELD_PK_GROUP);
		return pk_group;
	}
	
	
	/**
	 * ����ԭ�ұ��ҽ�����
	 * @param vo
	 * @param isRecaculate
	 * @param busiDate
	 * @return
	 * @throws BusinessException
	 */
	public static ISuperVO processPrecision(ISuperVO vo, boolean isRecaculate, UFDate busiDate) throws BusinessException {
		ISuperVO newvo = getEPrecService().processPrecision(vo, true, getFieldMap(), busiDate);
		return newvo;
	}
	
	
	/**
	 * ��ȡ��Ҫ���㾫�ȵ���������map
	 * @return
	 */
	private static HashMap<String, String> getFieldMap() {
		HashMap<String, String> field = new HashMap<String, String>();
		// ����
		field.put(TMIMFConst.FIELD_PK_CURRTYPE, InvestApplyVO.PK_CURRTYPE);
		// ԭ�ҽ����������
		// String mnyField = field.get("mnyField");
		field.put(TMIMFConst.FIELD_MONEY, InvestApplyVO.MONEY);
		// ��֯���һ�����������
		field.put(TMIMFConst.FIELD_ORGRATE, InvestApplyVO.OLCRATE);
		// ��֯���ҽ����������
		field.put(TMIMFConst.FIELD_ORGMNY, InvestApplyVO.OLCMONEY);
		// ���ű��һ�����������
		field.put(TMIMFConst.FIELD_GROUPRATE, InvestApplyVO.GLCRATE);
		// ���ű��ҽ����������
		field.put(TMIMFConst.FIELD_GROUPMNY, InvestApplyVO.GLCMONEY);
		// ȫ�ֱ��һ�����������
		field.put(TMIMFConst.FIELD_GLOBALRATE, InvestApplyVO.GLLCRATE);
		// ȫ�ֱ��ҽ����������
		field.put(TMIMFConst.FIELD_GLOBALMNYFIELD, InvestApplyVO.GLLMONEY);
		return field;
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
	
	/**
	 * ����pk��ѯ��������ۺ�VO
	 * 
	 * @param currPagePks
	 * @return
	 */
	public static AggInvestApplyVO[] getApplyVO(String[] pks) {
		IInvestApplyQueryService service = ServiceLocator
				.find(IInvestApplyQueryService.class);
		AggInvestApplyVO[] resultVOs = null;
		if (pks != null && pks.length > 0) {
			try {
				resultVOs = service.queryApplyByPks(pks);
			} catch (BusinessException e) {
				ExceptionUtils.wrapBusinessException("����pk��ѯ��ǰҳ����ʧ�ܣ�"
						+ e.getMessage());
			}
		}
		return resultVOs;
	}
	
	
	public static Map<String, String> getGroupData(QueryTreeFormatVO queryParam) {
		Map<String, String> map=new HashMap<String, String>();
		try {
			INCCloudQueryService qservice = ServiceLocator
					.find(INCCloudQueryService.class);
			IQueryScheme scheme = qservice.convertCondition(queryParam);
			IInvestApplyQueryService service = ServiceLocator
					.find(IInvestApplyQueryService.class);
			String[] status = service.queryIFMApplyBySchema(scheme);
			int  numSPZ = 0, numDTJ = 0, numSPWC = 0, numYCD = 0;
			for (int i = 0; i < status.length; i++) {
				if (status[i].equals("1")) {// ������
					numSPZ++;
					continue;
				}
				if (status[i].equals("0")) {// ���ύ
					numDTJ++;
					continue;
				}
			}
//			map.put("WZX",String.valueOf(numWZX));
//			map.put("ZXZ",String.valueOf(numZXZ));
			map.put("SPZ",String.valueOf(numSPZ));
			map.put("DTJ",String.valueOf(numDTJ));
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("��ѯ����ʧ�ܣ�" + e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * ��ѯ�û�Ĭ�ϵ�����֯
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public static String getUserDefaultOrgUnit() throws BusinessException {
		return ServiceLocator.find(IInvestApplyQueryService.class)
				.getDefaultOrgUnit();
	}
	
}
