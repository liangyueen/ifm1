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
 * 贷款申请查询工具类
 * 
 * @author futao3
 * @date Aug 29, 2018
 */
public class ApplyQueryUtil {
	
	
	private static IInvestApplyQueryService eQueryService = null;
	private static IInvestApplyPrecisionService ePrecService = null;
	
	
	/**
	 * 根据组织主键获取组织默认币种
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
	 * 查询财务组织所在集团
	 * @param pk_org
	 * @return
	 */
	public static String getGroupByOrg(String pk_org) throws BusinessException {
		IOrgUnitPubService_C orgUnitQryService = ServiceLocator.find(IOrgUnitPubService_C.class);
		String pk_group = null;
		OrgVO[] orgVOs = orgUnitQryService.getOrgs(new String[] { pk_org }, new String[] { TMIMFConst.FIELD_PK_GROUP });
		if(orgVOs == null || orgVOs.length <= 0){
			ExceptionUtils.wrapBusinessException("获取财务组织对应的集团失败。");
		}
		pk_group = (String) orgVOs[0].getAttributeValue(TMIMFConst.FIELD_PK_GROUP);
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
	
	
	/**
	 * 获取需要计算精度的属性名称map
	 * @return
	 */
	private static HashMap<String, String> getFieldMap() {
		HashMap<String, String> field = new HashMap<String, String>();
		// 币种
		field.put(TMIMFConst.FIELD_PK_CURRTYPE, InvestApplyVO.PK_CURRTYPE);
		// 原币金额属性名称
		// String mnyField = field.get("mnyField");
		field.put(TMIMFConst.FIELD_MONEY, InvestApplyVO.MONEY);
		// 组织本币汇率属性名称
		field.put(TMIMFConst.FIELD_ORGRATE, InvestApplyVO.OLCRATE);
		// 组织本币金额属性名称
		field.put(TMIMFConst.FIELD_ORGMNY, InvestApplyVO.OLCMONEY);
		// 集团本币汇率属性名称
		field.put(TMIMFConst.FIELD_GROUPRATE, InvestApplyVO.GLCRATE);
		// 集团本币金额属性名称
		field.put(TMIMFConst.FIELD_GROUPMNY, InvestApplyVO.GLCMONEY);
		// 全局本币汇率属性名称
		field.put(TMIMFConst.FIELD_GLOBALRATE, InvestApplyVO.GLLCRATE);
		// 全局本币金额属性名称
		field.put(TMIMFConst.FIELD_GLOBALMNYFIELD, InvestApplyVO.GLLMONEY);
		return field;
	}
	
	
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
	
	/**
	 * 根据pk查询贷款申请聚合VO
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
				ExceptionUtils.wrapBusinessException("根据pk查询当前页数据失败："
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
				if (status[i].equals("1")) {// 审批中
					numSPZ++;
					continue;
				}
				if (status[i].equals("0")) {// 待提交
					numDTJ++;
					continue;
				}
			}
//			map.put("WZX",String.valueOf(numWZX));
//			map.put("ZXZ",String.valueOf(numZXZ));
			map.put("SPZ",String.valueOf(numSPZ));
			map.put("DTJ",String.valueOf(numDTJ));
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("查询单据失败：" + e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询用户默认的主组织
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public static String getUserDefaultOrgUnit() throws BusinessException {
		return ServiceLocator.find(IInvestApplyQueryService.class)
				.getDefaultOrgUnit();
	}
	
}
