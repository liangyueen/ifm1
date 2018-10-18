package nc.impl.ifm;

import java.util.HashMap;

import nc.itf.ifm.IFMRedeemPrecisionService;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tmpub.util.TMCurrencyUtil;

import org.apache.commons.lang.StringUtils;


public  class IFMRedeemPrecisionServiceImpl implements IFMRedeemPrecisionService {
	
	@Override
	public String getOrgStandardCurrtype(String pk_org) throws BusinessException {
		if (StringUtils.isBlank(pk_org)) {
			return null;
		}
		return TMCurrencyUtil.getOrgLocalCurrPK(pk_org);
	}

	@Override
	public String getGroupStandardCurrtype(String pk_group) throws BusinessException {
		if (StringUtils.isBlank(pk_group)) {
			return null;
		}
		return TMCurrencyUtil.getGroupLocalCurrPK(pk_group);
	}

	@Override
	public String getGlobalStandardCurrtype() throws BusinessException {
		return TMCurrencyUtil.getGLobalCurrPK();
	}

	@Override
	public ISuperVO processPrecision(ISuperVO vo, boolean isRecaculate, HashMap<String, String> field, UFDate busiDate) throws BusinessException {
		if (vo == null) {
			return null;
		}
		// 获取ISuperVO对应的属性名称
		// 币种
		String currtypeField = field.get(TMIFMConst.FIELD_PK_CURRTYPE);
		// 赎回金额属性名称

		String mnyField = field.get("redeemmoney");
		// 组织本币汇率属性名称
		String orgRateField = field.get("olcrate");
		// 组织本币金额属性名称
		String orgMnyField = field.get("olcmoney");
		// 集团本币汇率属性名称
		String groupRateField = field.get("glcrate");
		// 集团本币金额属性名称
		String groupMnyField = field.get("glcmoeny");
		// 全局本币汇率属性名称
		String globalRateField = field.get("gllcrate");
		// 全局本币金额属性名称
		String globalMnyField = field.get("gllmoeny");
		// 获取组织
		String pk_org = getValue2Str(vo.getAttributeValue("pk_org"));
		// 获取集团
		String pk_group = getValue2Str(vo.getAttributeValue("pk_group"));
		// 获取币种
		String pk_currtype = getValue2Str(vo.getAttributeValue(currtypeField));
		// 赎回金额字符串
		String mnyStr = getValue2Str(vo.getAttributeValue(mnyField));
		if (StringUtils.isBlank(pk_currtype)/* || StringUtils.isBlank(mnyStr)*/) {
			vo.setAttributeValue(orgRateField, null);
			vo.setAttributeValue(orgMnyField, null);
			vo.setAttributeValue(groupRateField, null);
			vo.setAttributeValue(groupMnyField, null);
			vo.setAttributeValue(globalRateField, null);
			vo.setAttributeValue(globalMnyField, null);
			return vo;
		}
		UFDouble mny = new UFDouble(mnyStr);
		// 处理赎回金额精度（根据币种获取相应的精度）
		processOriPrecision(vo, pk_currtype, mny, mnyField);
		// 处理组织本币金额精度，组织本币汇率精度
		processOrgPrecision(vo, pk_org, pk_currtype, mny, isRecaculate, orgRateField, orgMnyField, busiDate);
		// 处理集团本币金额精度、集团本币汇率精度
		processGroupPrecision(vo, pk_group, pk_org, pk_currtype, mny, isRecaculate, orgRateField, groupRateField, groupMnyField, busiDate);
		// 处理全局本币金额精度、全局本币汇率精度
		processGlobalPrecision(vo, pk_org, pk_currtype, mny, isRecaculate, orgRateField, globalRateField, globalMnyField, busiDate);
		
		return vo;
	}
	
	/**
	 * 处理组织本币汇率及金额
	 * @param vo
	 * @param pk_org
	 * @param pk_currtype
	 * @param mny
	 * @param isRecaculate
	 * @param orgRateField
	 * @param OrgMnyField
	 * @param busiDate
	 * @throws BusinessException
	 */
	private void processOrgPrecision(ISuperVO vo, String pk_org, String pk_currtype, UFDouble mny, boolean isRecaculate, String orgRateField, String OrgMnyField, UFDate busiDate) throws BusinessException {
		if (StringUtils.isBlank(pk_org)) {
			return;
		}
		// 获取组织本币汇率
		UFDouble orgRate = getOrgRate(vo, pk_org, pk_currtype, isRecaculate, orgRateField, busiDate);
		if (orgRate == null) {
			return;
		}
		// 处理组织本币汇率的精度
		processOrgRatePrecision(vo, pk_org, pk_currtype, orgRate, orgRateField);
		// 处理组织本币金额
		processOrgStdMny(vo, orgRate, pk_org, pk_currtype, mny, isRecaculate, OrgMnyField, busiDate);
	}
	
	/**
	 * 处理集团本币汇率及金额
	 * @param vo
	 * @param pk_group
	 * @param pk_org
	 * @param pk_currtype
	 * @param mny
	 * @param isRecaculate
	 * @param orgRateField
	 * @param groupRateField
	 * @param groupMnyField
	 * @param busiDate
	 * @throws BusinessException
	 */
	private void processGroupPrecision(ISuperVO vo,	String pk_group, String pk_org, String pk_currtype, UFDouble mny, boolean isRecaculate, String orgRateField, String groupRateField, String groupMnyField, UFDate busiDate) throws BusinessException {
		if (StringUtils.isBlank(pk_org) || StringUtils.isBlank(pk_group)) {
			return;
		}
		// 获取集团本币汇率
		UFDouble groupRate = getGroupRate(vo, pk_org, pk_group, pk_currtype, isRecaculate, groupRateField, busiDate);
		if (groupRate == null) {
			return;
		}
		// 处理集团本币汇率精度
		processGroupRatePrecision(vo, groupRate, pk_org, pk_group, pk_currtype, groupRateField);
		// 获取组织本币汇率
		UFDouble orgRate = getOrgRate(vo, pk_org, pk_currtype, isRecaculate, orgRateField, busiDate);
		if (orgRate == null) {
			return;
		}
		// 处理集团本币金额
		processGroupStdMny(vo, groupRate, orgRate, pk_org, pk_group, pk_currtype, mny, isRecaculate, groupMnyField, busiDate);
	}
	
	/**
	 * 处理全局本币汇率及金额
	 * @param vo
	 * @param pk_org
	 * @param pk_currtype
	 * @param mny
	 * @param isRecaculate
	 * @param orgRateField
	 * @param globalRateField
	 * @param globalMnyField
	 * @param busiDate
	 * @throws BusinessException
	 */
	private void processGlobalPrecision(ISuperVO vo, String pk_org, String pk_currtype, UFDouble mny, boolean isRecaculate, String orgRateField, String globalRateField, String globalMnyField, UFDate busiDate) throws BusinessException {
		if (StringUtils.isBlank(pk_org)) {
			return;
		}
		// 获取全局本币汇率
		UFDouble globalRate = getGlobalRate(vo, pk_org, pk_currtype, isRecaculate, globalRateField, busiDate);
		if (globalRate == null) {
			return;
		}
		// 处理全局本币汇率精度
		processGlobalRatePrecision(vo, globalRate, pk_org, pk_currtype, globalRateField);
		// 获取组织本币汇率
		UFDouble orgRate = getOrgRate(vo, pk_org, pk_currtype, isRecaculate, orgRateField, busiDate);
		if (orgRate == null) {
			return;
		}
		// 处理全局本币金额
		processGlobalStdMny(vo, globalRate, orgRate, pk_org, pk_currtype, mny, isRecaculate, globalMnyField, busiDate);
	}
	
	/**
	 * 获取组织本币汇率
	 * @param vo
	 * @param pk_org
	 * @param pk_currtype
	 * @param isRecaculate
	 * @param orgRateField
	 * @param busiDate
	 * @return
	 * @throws BusinessException
	 */
	private UFDouble getOrgRate(ISuperVO vo, String pk_org, String pk_currtype, boolean isRecaculate, String orgRateField, UFDate busiDate) throws BusinessException {
		UFDouble orgRate = null;
		if (isRecaculate || StringUtils.isBlank(getValue2Str(vo.getAttributeValue(orgRateField)))) {
			orgRate = TMCurrencyUtil.getOrgCurrRate(pk_org, pk_currtype, busiDate);
		} else {
			orgRate = getValue2UFDouble(vo.getAttributeValue(orgRateField));
		}
		return orgRate;
	}
	
	/**
	 * 获取集团本币汇率
	 * @param vo
	 * @param pk_org
	 * @param pk_group
	 * @param currtype
	 * @param isRecaculate
	 * @param groupRateField
	 * @param busiDate
	 * @return
	 * @throws BusinessException
	 */
	private UFDouble getGroupRate(ISuperVO vo, String pk_org, String pk_group, String currtype, boolean isRecaculate, String groupRateField, UFDate busiDate) throws BusinessException {
		UFDouble groupRate = null;
		if (isRecaculate || StringUtils.isBlank(getValue2Str(vo.getAttributeValue(groupRateField)))) {
			groupRate = TMCurrencyUtil.getGroupCurrRate(pk_group, pk_org, currtype, busiDate);
		} else {
			groupRate = getValue2UFDouble(vo.getAttributeValue(groupRateField));
		}
		return groupRate;
	}
	
	/**
	 * 获取全局本币汇率
	 * @param vo
	 * @param pk_org
	 * @param pk_currtype
	 * @param isRecaculate
	 * @param globalRateField
	 * @param busiDate
	 * @return
	 * @throws BusinessException
	 */
	private UFDouble getGlobalRate(ISuperVO vo, String pk_org, String pk_currtype, boolean isRecaculate, String globalRateField, UFDate busiDate) throws BusinessException {
		UFDouble globalRate = null;
		if (isRecaculate || StringUtils.isBlank(getValue2Str(vo.getAttributeValue(globalRateField)))) {
			globalRate = TMCurrencyUtil.getGlobalCurrRate(pk_org, pk_currtype, busiDate);
		} else {
			globalRate = getValue2UFDouble(vo.getAttributeValue(globalRateField));
		}
		return globalRate;
	}
	
	/**
	 * 处理赎回金额金额精度
	 * @param vo
	 * @param pk_currtype
	 * @param mny
	 * @param mnyField
	 * @throws BusinessException
	 */
	private void processOriPrecision(ISuperVO vo, String pk_currtype, UFDouble mny, String mnyField) throws BusinessException {
		// 获取原币币种精度
		int digit = TMCurrencyUtil.getCurrtypeDigit(pk_currtype);
		// 更新赎回金额
		vo.setAttributeValue(mnyField, mny.setScale(digit, UFDouble.ROUND_HALF_UP));
	}
	
	/**
	 * 处理组织本币汇率的精度
	 * @param vo
	 * @param pk_org
	 * @param pk_currtype
	 * @param orgRate
	 * @param orgRateField
	 * @throws BusinessException
	 */
	private void processOrgRatePrecision(ISuperVO vo, String pk_org, String pk_currtype, UFDouble orgRate, String orgRateField) throws BusinessException {
		if (StringUtils.isBlank(orgRateField)) {
			return;
		}
		// 获取组织本币汇率精度
		int orgRateDigit = TMCurrencyUtil.getOrgCurrRateDigit(pk_org, pk_currtype);
		// 更新组织本币汇率
		vo.setAttributeValue(orgRateField, orgRate.setScale(orgRateDigit, UFDouble.ROUND_HALF_UP));
	}
	
	/**
	 * 处理集团本币汇率精度
	 * @param vo
	 * @param groupRate
	 * @param pk_org
	 * @param pk_group
	 * @param pk_currtype
	 * @param groupRateField
	 * @throws BusinessException
	 */
	private void processGroupRatePrecision(ISuperVO vo, UFDouble groupRate, String pk_org, String pk_group, String pk_currtype, String groupRateField) throws BusinessException {
		if (StringUtils.isBlank(groupRateField)) {
			return;
		}
		// 获取集团本币汇率精度
		int groupRateDigit = TMCurrencyUtil.getGroupCurrRateDigit(pk_group, pk_org, pk_currtype);
		// 更新集团本币汇率
		vo.setAttributeValue(groupRateField, groupRate.setScale(groupRateDigit, UFDouble.ROUND_HALF_UP));
	}
	
	/**
	 * 处理全局本币汇率精度
	 * @param vo
	 * @param globalRate
	 * @param pk_org
	 * @param pk_currtype
	 * @param globalRateField
	 * @throws BusinessException
	 */
	private void processGlobalRatePrecision(ISuperVO vo, UFDouble globalRate, String pk_org, String pk_currtype, String globalRateField)throws BusinessException {
		if (StringUtils.isBlank(globalRateField)) {
			return;
		}
		// 获取全局本币汇率精度
		int globalRateDigit = TMCurrencyUtil.getGlobalCurrRateDigit(pk_org, pk_currtype);
		// 更新全局本币汇率
		vo.setAttributeValue(globalRateField, globalRate.setScale(globalRateDigit, UFDouble.ROUND_HALF_UP));
	}
	
	/**
	 * 处理组织本币金额
	 * @param vo
	 * @param orgRate
	 * @param pk_org
	 * @param pk_currtype
	 * @param mny
	 * @param isRecaculate
	 * @param OrgMnyField
	 * @param busiDate
	 * @throws BusinessException
	 */
	private void processOrgStdMny(ISuperVO vo, UFDouble orgRate, String pk_org, String pk_currtype, UFDouble mny, boolean isRecaculate, String OrgMnyField, UFDate busiDate) throws BusinessException {
		if (StringUtils.isBlank(OrgMnyField)) {
			return;
		}
		// 获取组织本币金额
		UFDouble orgMny = isRecaculate ? TMCurrencyUtil.getOrgLocalMoney(pk_org, pk_currtype, mny, orgRate, busiDate): 
			getValue2UFDouble(vo.getAttributeValue(OrgMnyField));
		if (orgMny == null) {
			return;
		}
		// 获取组织本币精度
		int orgMnyDigit = TMCurrencyUtil.getOrgCurrDigit(pk_org);
		// 更新组织本币金额
		vo.setAttributeValue(OrgMnyField, orgMny.setScale(orgMnyDigit, UFDouble.ROUND_HALF_UP));
	}
	
	/**
	 * 处理集团本币金额
	 * @param vo
	 * @param groupRate
	 * @param orgRate
	 * @param pk_org
	 * @param pk_group
	 * @param pk_currtype
	 * @param mny
	 * @param isRecaculate
	 * @param groupMnyField
	 * @param busiDate
	 * @throws BusinessException
	 */
	private void processGroupStdMny(ISuperVO vo, UFDouble groupRate, UFDouble orgRate, String pk_org, String pk_group, String pk_currtype, UFDouble mny, boolean isRecaculate, String groupMnyField, UFDate busiDate) throws BusinessException {
		if (StringUtils.isBlank(groupMnyField)) {
			return;
		}
		// 获取集团本币金额
		UFDouble groupMny = isRecaculate ? TMCurrencyUtil.getGroupLocalMoney(pk_group, pk_org, pk_currtype, mny, groupRate, orgRate,
				busiDate) : getValue2UFDouble(vo.getAttributeValue(groupMnyField));
		if (groupMny == null) {
			return;
		}
		// 获取集团本币精度
		int groupMnyDigit = TMCurrencyUtil.getGroupCurrDigit(pk_group);
		// 更新集团本币金额
		vo.setAttributeValue(groupMnyField, groupMny.setScale(groupMnyDigit, UFDouble.ROUND_HALF_UP));
	}
	
	/**
	 * 处理全局本币金额
	 * @param vo
	 * @param globalRate
	 * @param orgRate
	 * @param pk_org
	 * @param pk_currtype
	 * @param mny
	 * @param isRecaculate
	 * @param globalMnyField
	 * @param busiDate
	 * @throws BusinessException
	 */
	private void processGlobalStdMny(ISuperVO vo, UFDouble globalRate, UFDouble orgRate, String pk_org, String pk_currtype, UFDouble mny, boolean isRecaculate, String globalMnyField, UFDate busiDate) throws BusinessException {
		if (StringUtils.isBlank(globalMnyField)) {
			return;
		}
		// 获取全局本币金额
		UFDouble globalMny = isRecaculate ? TMCurrencyUtil.getGlobalLocalMoney(pk_org, pk_currtype, mny, globalRate, orgRate, busiDate)
				: getValue2UFDouble(vo.getAttributeValue(globalMnyField));
		if (globalMny == null) {
			return;
		}
		// 获取全局本币精度
		int globalMnyDigit = TMCurrencyUtil.getGlobalCurrDigit();
		// 更新全局本币金额
		vo.setAttributeValue(globalMnyField, globalMny.setScale(globalMnyDigit, UFDouble.ROUND_HALF_UP));
	}

	/**
	 * 将数据转换为字符串格式
	 * @param obj
	 * @return
	 */
	private String getValue2Str(Object obj) {
		return obj == null ? null : String.valueOf(obj);
	}
	
	/**
	 * 将字符串数据转换为UFDouble格式
	 * @param obj
	 * @return
	 */
	private UFDouble getValue2UFDouble(Object obj) {
		return obj == null ? null : new UFDouble(String.valueOf(obj));
	}

}
