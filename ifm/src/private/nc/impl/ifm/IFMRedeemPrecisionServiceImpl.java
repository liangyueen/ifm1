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
		// ��ȡISuperVO��Ӧ����������
		// ����
		String currtypeField = field.get(TMIFMConst.FIELD_PK_CURRTYPE);
		// ��ؽ����������

		String mnyField = field.get("redeemmoney");
		// ��֯���һ�����������
		String orgRateField = field.get("olcrate");
		// ��֯���ҽ����������
		String orgMnyField = field.get("olcmoney");
		// ���ű��һ�����������
		String groupRateField = field.get("glcrate");
		// ���ű��ҽ����������
		String groupMnyField = field.get("glcmoeny");
		// ȫ�ֱ��һ�����������
		String globalRateField = field.get("gllcrate");
		// ȫ�ֱ��ҽ����������
		String globalMnyField = field.get("gllmoeny");
		// ��ȡ��֯
		String pk_org = getValue2Str(vo.getAttributeValue("pk_org"));
		// ��ȡ����
		String pk_group = getValue2Str(vo.getAttributeValue("pk_group"));
		// ��ȡ����
		String pk_currtype = getValue2Str(vo.getAttributeValue(currtypeField));
		// ��ؽ���ַ���
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
		// ������ؽ��ȣ����ݱ��ֻ�ȡ��Ӧ�ľ��ȣ�
		processOriPrecision(vo, pk_currtype, mny, mnyField);
		// ������֯���ҽ��ȣ���֯���һ��ʾ���
		processOrgPrecision(vo, pk_org, pk_currtype, mny, isRecaculate, orgRateField, orgMnyField, busiDate);
		// �����ű��ҽ��ȡ����ű��һ��ʾ���
		processGroupPrecision(vo, pk_group, pk_org, pk_currtype, mny, isRecaculate, orgRateField, groupRateField, groupMnyField, busiDate);
		// ����ȫ�ֱ��ҽ��ȡ�ȫ�ֱ��һ��ʾ���
		processGlobalPrecision(vo, pk_org, pk_currtype, mny, isRecaculate, orgRateField, globalRateField, globalMnyField, busiDate);
		
		return vo;
	}
	
	/**
	 * ������֯���һ��ʼ����
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
		// ��ȡ��֯���һ���
		UFDouble orgRate = getOrgRate(vo, pk_org, pk_currtype, isRecaculate, orgRateField, busiDate);
		if (orgRate == null) {
			return;
		}
		// ������֯���һ��ʵľ���
		processOrgRatePrecision(vo, pk_org, pk_currtype, orgRate, orgRateField);
		// ������֯���ҽ��
		processOrgStdMny(vo, orgRate, pk_org, pk_currtype, mny, isRecaculate, OrgMnyField, busiDate);
	}
	
	/**
	 * �����ű��һ��ʼ����
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
		// ��ȡ���ű��һ���
		UFDouble groupRate = getGroupRate(vo, pk_org, pk_group, pk_currtype, isRecaculate, groupRateField, busiDate);
		if (groupRate == null) {
			return;
		}
		// �����ű��һ��ʾ���
		processGroupRatePrecision(vo, groupRate, pk_org, pk_group, pk_currtype, groupRateField);
		// ��ȡ��֯���һ���
		UFDouble orgRate = getOrgRate(vo, pk_org, pk_currtype, isRecaculate, orgRateField, busiDate);
		if (orgRate == null) {
			return;
		}
		// �����ű��ҽ��
		processGroupStdMny(vo, groupRate, orgRate, pk_org, pk_group, pk_currtype, mny, isRecaculate, groupMnyField, busiDate);
	}
	
	/**
	 * ����ȫ�ֱ��һ��ʼ����
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
		// ��ȡȫ�ֱ��һ���
		UFDouble globalRate = getGlobalRate(vo, pk_org, pk_currtype, isRecaculate, globalRateField, busiDate);
		if (globalRate == null) {
			return;
		}
		// ����ȫ�ֱ��һ��ʾ���
		processGlobalRatePrecision(vo, globalRate, pk_org, pk_currtype, globalRateField);
		// ��ȡ��֯���һ���
		UFDouble orgRate = getOrgRate(vo, pk_org, pk_currtype, isRecaculate, orgRateField, busiDate);
		if (orgRate == null) {
			return;
		}
		// ����ȫ�ֱ��ҽ��
		processGlobalStdMny(vo, globalRate, orgRate, pk_org, pk_currtype, mny, isRecaculate, globalMnyField, busiDate);
	}
	
	/**
	 * ��ȡ��֯���һ���
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
	 * ��ȡ���ű��һ���
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
	 * ��ȡȫ�ֱ��һ���
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
	 * ������ؽ�����
	 * @param vo
	 * @param pk_currtype
	 * @param mny
	 * @param mnyField
	 * @throws BusinessException
	 */
	private void processOriPrecision(ISuperVO vo, String pk_currtype, UFDouble mny, String mnyField) throws BusinessException {
		// ��ȡԭ�ұ��־���
		int digit = TMCurrencyUtil.getCurrtypeDigit(pk_currtype);
		// ������ؽ��
		vo.setAttributeValue(mnyField, mny.setScale(digit, UFDouble.ROUND_HALF_UP));
	}
	
	/**
	 * ������֯���һ��ʵľ���
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
		// ��ȡ��֯���һ��ʾ���
		int orgRateDigit = TMCurrencyUtil.getOrgCurrRateDigit(pk_org, pk_currtype);
		// ������֯���һ���
		vo.setAttributeValue(orgRateField, orgRate.setScale(orgRateDigit, UFDouble.ROUND_HALF_UP));
	}
	
	/**
	 * �����ű��һ��ʾ���
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
		// ��ȡ���ű��һ��ʾ���
		int groupRateDigit = TMCurrencyUtil.getGroupCurrRateDigit(pk_group, pk_org, pk_currtype);
		// ���¼��ű��һ���
		vo.setAttributeValue(groupRateField, groupRate.setScale(groupRateDigit, UFDouble.ROUND_HALF_UP));
	}
	
	/**
	 * ����ȫ�ֱ��һ��ʾ���
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
		// ��ȡȫ�ֱ��һ��ʾ���
		int globalRateDigit = TMCurrencyUtil.getGlobalCurrRateDigit(pk_org, pk_currtype);
		// ����ȫ�ֱ��һ���
		vo.setAttributeValue(globalRateField, globalRate.setScale(globalRateDigit, UFDouble.ROUND_HALF_UP));
	}
	
	/**
	 * ������֯���ҽ��
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
		// ��ȡ��֯���ҽ��
		UFDouble orgMny = isRecaculate ? TMCurrencyUtil.getOrgLocalMoney(pk_org, pk_currtype, mny, orgRate, busiDate): 
			getValue2UFDouble(vo.getAttributeValue(OrgMnyField));
		if (orgMny == null) {
			return;
		}
		// ��ȡ��֯���Ҿ���
		int orgMnyDigit = TMCurrencyUtil.getOrgCurrDigit(pk_org);
		// ������֯���ҽ��
		vo.setAttributeValue(OrgMnyField, orgMny.setScale(orgMnyDigit, UFDouble.ROUND_HALF_UP));
	}
	
	/**
	 * �����ű��ҽ��
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
		// ��ȡ���ű��ҽ��
		UFDouble groupMny = isRecaculate ? TMCurrencyUtil.getGroupLocalMoney(pk_group, pk_org, pk_currtype, mny, groupRate, orgRate,
				busiDate) : getValue2UFDouble(vo.getAttributeValue(groupMnyField));
		if (groupMny == null) {
			return;
		}
		// ��ȡ���ű��Ҿ���
		int groupMnyDigit = TMCurrencyUtil.getGroupCurrDigit(pk_group);
		// ���¼��ű��ҽ��
		vo.setAttributeValue(groupMnyField, groupMny.setScale(groupMnyDigit, UFDouble.ROUND_HALF_UP));
	}
	
	/**
	 * ����ȫ�ֱ��ҽ��
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
		// ��ȡȫ�ֱ��ҽ��
		UFDouble globalMny = isRecaculate ? TMCurrencyUtil.getGlobalLocalMoney(pk_org, pk_currtype, mny, globalRate, orgRate, busiDate)
				: getValue2UFDouble(vo.getAttributeValue(globalMnyField));
		if (globalMny == null) {
			return;
		}
		// ��ȡȫ�ֱ��Ҿ���
		int globalMnyDigit = TMCurrencyUtil.getGlobalCurrDigit();
		// ����ȫ�ֱ��ҽ��
		vo.setAttributeValue(globalMnyField, globalMny.setScale(globalMnyDigit, UFDouble.ROUND_HALF_UP));
	}

	/**
	 * ������ת��Ϊ�ַ�����ʽ
	 * @param obj
	 * @return
	 */
	private String getValue2Str(Object obj) {
		return obj == null ? null : String.valueOf(obj);
	}
	
	/**
	 * ���ַ�������ת��ΪUFDouble��ʽ
	 * @param obj
	 * @return
	 */
	private UFDouble getValue2UFDouble(Object obj) {
		return obj == null ? null : new UFDouble(String.valueOf(obj));
	}

}
