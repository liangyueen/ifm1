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
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.imf.constants.IncomeConst;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.pf.BillStatusEnum;
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
	
	public static AggInvestIncomeVO[] getIncomeVO(String[] pk_incomes){
		AggInvestIncomeVO[] vos=null;
		try {
			IInvestIncomeQueryService service=ServiceLocator.find(IInvestIncomeQueryService.class);
			vos=service.queryIncomeByPks(pk_incomes);
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
			IInvestIncomeQueryService service = ServiceLocator
					.find(IInvestIncomeQueryService.class);
			String[] status = service.queryCCIncomeBySchema(scheme);
			int numSPZ = 0, numDTJ = 0;
			for (int i = 0; i < status.length; i++) {
				if (status[i].equals("1")) {// 待提交
					numDTJ++;
					continue;
				}
				if (status[i].equals("2")) {// 待审批(即审批状态的审批中)
					numSPZ++;
					continue;
				}
			}
			map.put("SPZ",String.valueOf(numSPZ));
			map.put("DTJ",String.valueOf(numDTJ));
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("查询单据失败：" + e.getMessage());
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
	 * 处理原币本币金额及精度
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
	 * 获取需要计算精度的属性名称map
	 * 
	 * @return
	 */
	private static HashMap<String, String> getFieldMap() {
		HashMap<String, String> field = new HashMap<String, String>();
		// 币种
		field.put(IncomeConst.FIELD_PK_CCCURRTYPE, InvestIncomeVO.PK_CURRTYPE);
		// 原币金额属性名称
		field.put(IncomeConst.FIELD_MONEY, InvestIncomeVO.ACTUALMOENY);
		// 组织本币汇率属性名称
		field.put(IncomeConst.FIELD_ORGRATE, InvestIncomeVO.OLCRATE);
		// 组织本币金额属性名称
		field.put(IncomeConst.FIELD_ORGMNY, InvestIncomeVO.OLCMOENY);
		// 集团本币汇率属性名称
		field.put(IncomeConst.FIELD_GROUPRATE, InvestIncomeVO.GLCRATE);
		// 集团本币金额属性名称
		field.put(IncomeConst.FIELD_GROUPMNY, InvestIncomeVO.GLCRATE);
		// 全局本币汇率属性名称
		field.put(IncomeConst.FIELD_GLOBALRATE, InvestIncomeVO.GLLCRATE);
		// 全局本币金额属性名称
		field.put(IncomeConst.FIELD_GLOBALMNYFIELD, InvestIncomeVO.GLLMOENY);
		return field;
	}
	/**
	 * 查询财务组织所在集团
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
			ExceptionUtils.wrapBusinessException("获取财务组织对应的集团失败。");
		}
		pk_group = (String) orgVOs[0]
				.getAttributeValue(IncomeConst.FIELD_PK_GROUP);
		return pk_group;
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
	/**
	 * 查询用户默认的主组织
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public static String getUserDefaultOrgUnit() throws BusinessException {
		return ServiceLocator.find(IInvestIncomeQueryService.class)
				.getDefaultOrgUnit();
	}
	
	/**
	 * 复制RedeemVO同名属性值到IncomeVO
	 * @param redeemVO
	 * @param incomeVO
	 * @return
	 */
	public static InvestIncomeVO convertRedeemVO2IncomeVO(InvestRedeemVO redeemVO, InvestIncomeVO incomeVO){
		
		incomeVO.setPk_srcbill(redeemVO.getPk_redeem());
		incomeVO.setPk_srcbilltype(redeemVO.getPk_billtypeid());
		incomeVO.setSrcbillno(redeemVO.getVbillno());
		incomeVO.setSrcbilltypecode(redeemVO.getPk_billtypecode());
		incomeVO.setPk_group(redeemVO.getPk_group());
		incomeVO.setPk_org(redeemVO.getPk_org());
		incomeVO.setPk_org_v(redeemVO.getPk_org_v());
		incomeVO.setPk_currtype(redeemVO.getPk_currtype());
		incomeVO.setPk_olccurr(redeemVO.getPk_olccurr());
		incomeVO.setRemark(redeemVO.getRemark());
		incomeVO.setOlcrate(redeemVO.getOlcrate());
		incomeVO.setOlcmoeny(redeemVO.getOlcmoney());
		incomeVO.setGlcrate(redeemVO.getGlcrate());
		incomeVO.setGlcmoeny(redeemVO.getGlcmoeny());
		incomeVO.setGllcrate(redeemVO.getGllcrate());
		incomeVO.setGllmoeny(redeemVO.getGllmoeny());
		incomeVO.setBillmaker(redeemVO.getBillmaker());
		incomeVO.setBillmakedate(redeemVO.getBillmakedate());
		incomeVO.setBillmaketime(redeemVO.getBillmaketime());
		incomeVO.setCreator(redeemVO.getCreator());
		incomeVO.setCreationtime(redeemVO.getCreationtime());
		incomeVO.setIssuebank(redeemVO.getIssuebank());
		incomeVO.setGathering(redeemVO.getGatheringaccount());
		incomeVO.setInvestaccount(redeemVO.getInvestaccount());
		incomeVO.setInterestday(redeemVO.getInterestday());
		incomeVO.setExpectedrate(redeemVO.getExpectedrate());
//		incomeVO.setAttributeValue("expectedmoney", redeemVO.getExpectedmoney());//预期收益金额
		incomeVO.setGatheringdate(redeemVO.getIncomedate());
		incomeVO.setIncomerate(redeemVO.getIncomerate());
		incomeVO.setIncomemoney(redeemVO.getIncomemoney());
		incomeVO.setProductcode(redeemVO.getProductcode());
		incomeVO.setProductname(redeemVO.getProductname());
		incomeVO.setActualmoeny(redeemVO.getRealreaning());
		incomeVO.setEnddate(redeemVO.getEnddate());
		incomeVO.setInvestvariety(redeemVO.getInvestvariety());
		
		// 设置本地默认值:审批状态、单据状态
		Integer vbillstatus = (Integer) BillStatusEnum.FREE.value();
		Integer billstatus =   (Integer) nc.vo.ifm.IncomeBillStatusEnum.NOSUB.value();
		incomeVO.setBillstatus(billstatus);
		incomeVO.setVbillstatus(vbillstatus);
		incomeVO.setSource(1);
		incomeVO.setPk_billtypeid("0001Z61000000002M5PM");
		incomeVO.setPk_billtypecode("3643");
				
		return incomeVO;
	}
}
