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
	 * 得到请求信息
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
			ExceptionUtils.wrapBusinessException("查询单据失败：" + e.getMessage());
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
				if (status[i].equals("0") ) {// 未执行
					numWZX++;
					continue;
				}
				if (status[i].equals("1")) {// 执行中
					numZXZ++;
					continue;
				}
				
			}
			map.put("WZX",String.valueOf(numWZX));
			map.put("ZXZ",String.valueOf(numZXZ));
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("查询单据失败：" + e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询财务组织所在集团
	 * @param pk_org
	 * @return
	 */
	public static String getGroupByOrg(String pk_org) throws BusinessException {
		IOrgUnitPubService_C orgUnitQryService = ServiceLocator.find(IOrgUnitPubService_C.class);
		String pk_group = null;
		OrgVO[] orgVOs = orgUnitQryService.getOrgs(new String[] { pk_org }, new String[] { "pk_group" });
		if(orgVOs == null || orgVOs.length <= 0){
			ExceptionUtils.wrapBusinessException("获取财务组织对应的集团失败。");
		}
		pk_group = (String) orgVOs[0].getAttributeValue("pk_group");
		return pk_group;
	}
	/**
	 * 处理原币本币金额及精度
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
	 * 获取需要计算精度的属性名称map
	 * @return
	 */
	private static HashMap<String, String> getFieldMap() {
		HashMap<String, String> field = new HashMap<String, String>();
		// 币种
		field.put(TMIFMConst.FIELD_PK_CURRTYPE, InvestRedeemVO.PK_CURRTYPE);
		// 原币金额属性名称
		// String mnyField = field.get("mnyField");
		//field.put(TMIFMConst.FIELD_MONEY, InvestRedeemVO.CCAMOUNT);
		// 组织本币汇率属性名称
		field.put(TMIFMConst.FIELD_ORGRATE, InvestRedeemVO.OLCRATE);
		// 组织本币金额属性名称
		field.put(TMIFMConst.FIELD_ORGMNY, InvestRedeemVO.OLCMNY);
		// 集团本币汇率属性名称
		field.put(TMIFMConst.FIELD_GROUPRATE, InvestRedeemVO.GLCRATE);
		// 集团本币金额属性名称
		field.put(TMIFMConst.FIELD_GROUPMNY, InvestRedeemVO.GLCMNY);
		// 全局本币汇率属性名称
		field.put(TMIFMConst.FIELD_GLOBALRATE, InvestRedeemVO.GLLCRATE);
		// 全局本币金额属性名称
		field.put(TMIFMConst.FIELD_GLOBALMNYFIELD, InvestRedeemVO.GLLCMNY);
		// 赎回金额
		field.put("redeemmoney", InvestRedeemVO.REDEEMMONEY);
		
		field.put(TMIFMConst.FIELD_PK_ORG, InvestRedeemVO.PK_ORG);
		field.put(TMIFMConst.FIELD_PK_GROUP, InvestRedeemVO.PK_GROUP);
		return field;
	}
	
	/**
	 * 查询用户默认的主组织
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public static String getUserDefaultOrgUnit() throws BusinessException {
		return ServiceLocator.find(IInvestRedeemQueryService.class)
				.getDefaultOrgUnit();
	}
	/**
	 * 根据组织主键获取组织默认币种
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
