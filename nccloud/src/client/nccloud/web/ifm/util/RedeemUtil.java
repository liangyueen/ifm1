package nccloud.web.ifm.util;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import nc.itf.ifm.IFMRedeemPrecisionService;
import nc.itf.ifm.IInvestRedeemQueryService;
import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
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

public class RedeemUtil {
	
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
	
	public static AggInvestRedeemVO[] getRedeemVO(String[] pk_redeems){
		AggInvestRedeemVO[] vos=null;
		try {
			IInvestRedeemQueryService service=ServiceLocator.find(IInvestRedeemQueryService.class);
			vos=service.queryRedeemByPks(pk_redeems);
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
			IInvestRedeemQueryService service=ServiceLocator.find(IInvestRedeemQueryService.class);
			String[] status = service.queryRedeemBySchema(scheme);
			int numWZX = 0, numZXZ = 0;
			for (int i = 0; i < status.length; i++) {
				if (status[i].equals("0") ) {// δִ��
					numWZX++;
					continue;
				}
				if (status[i].equals("1")) {// ִ����
					numZXZ++;
					continue;
				}
				
			}
			map.put("WZX",String.valueOf(numWZX));
			map.put("ZXZ",String.valueOf(numZXZ));
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("��ѯ����ʧ�ܣ�" + e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * ��ѯ������֯���ڼ���
	 * @param pk_org
	 * @return
	 */
	public static String getGroupByOrg(String pk_org) throws BusinessException {
		IOrgUnitPubService_C orgUnitQryService = ServiceLocator.find(IOrgUnitPubService_C.class);
		String pk_group = null;
		OrgVO[] orgVOs = orgUnitQryService.getOrgs(new String[] { pk_org }, new String[] { "pk_group" });
		if(orgVOs == null || orgVOs.length <= 0){
			ExceptionUtils.wrapBusinessException("��ȡ������֯��Ӧ�ļ���ʧ�ܡ�");
		}
		pk_group = (String) orgVOs[0].getAttributeValue("pk_group");
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
	
	
	private static IFMRedeemPrecisionService ePrecService = null;
	
	public static synchronized IFMRedeemPrecisionService getEPrecService() {
		if (ePrecService == null) {
			ePrecService =  ServiceLocator.find(IFMRedeemPrecisionService.class);
		}
		return ePrecService;
	}
	
	/**
	 * ��ȡ��Ҫ���㾫�ȵ���������map
	 * @return
	 */
	private static HashMap<String, String> getFieldMap() {
		HashMap<String, String> field = new HashMap<String, String>();
		// ����
		field.put(TMIFMConst.FIELD_PK_CURRTYPE, InvestRedeemVO.PK_CURRTYPE);
		// ԭ�ҽ����������
		// String mnyField = field.get("mnyField");
		//field.put(TMIFMConst.FIELD_MONEY, InvestRedeemVO.CCAMOUNT);
		// ��֯���һ�����������
		field.put(TMIFMConst.FIELD_ORGRATE, InvestRedeemVO.OLCRATE);
		// ��֯���ҽ����������
		field.put(TMIFMConst.FIELD_ORGMNY, InvestRedeemVO.OLCMNY);
		// ���ű��һ�����������
		field.put(TMIFMConst.FIELD_GROUPRATE, InvestRedeemVO.GLCRATE);
		// ���ű��ҽ����������
		field.put(TMIFMConst.FIELD_GROUPMNY, InvestRedeemVO.GLCMNY);
		// ȫ�ֱ��һ�����������
		field.put(TMIFMConst.FIELD_GLOBALRATE, InvestRedeemVO.GLLCRATE);
		// ȫ�ֱ��ҽ����������
		field.put(TMIFMConst.FIELD_GLOBALMNYFIELD, InvestRedeemVO.GLLCMNY);
		// ��ؽ��
		field.put("redeemmoney", InvestRedeemVO.REDEEMMONEY);
		
		field.put(TMIFMConst.FIELD_PK_ORG, InvestRedeemVO.PK_ORG);
		field.put(TMIFMConst.FIELD_PK_GROUP, InvestRedeemVO.PK_GROUP);
		return field;
	}
	
	/**
	 * ��ѯ�û�Ĭ�ϵ�����֯
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public static String getUserDefaultOrgUnit() throws BusinessException {
		return ServiceLocator.find(IInvestRedeemQueryService.class)
				.getDefaultOrgUnit();
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
}
