package nccloud.pubitf.ifm.link;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.fts.spepay.SpePayBVO;
import nc.vo.fts.spepay.SpePayVO;
import nc.vo.pub.ISuperVO;
import nccloud.pubitf.fts.pub.FTSConstInforNCC;
import nccloud.vo.tmpub.precison.PrecisionField;

public class IFMLinkBillConstantForNCC {

	/**
	 * 表头精度处理字段
	 * 
	 * @return
	 */
	public static List<PrecisionField> getHeadPrecisionFields() {
		List<PrecisionField> list = new ArrayList<>();
		// 字段 组织本币汇率
		String olcrate = SpePayBVO.OLCRATE;
		// 字段 集团本币汇率
		String glcrate = SpePayBVO.GLCRATE;
		// 字段 全局本币汇率
		String gllcrate = SpePayBVO.GLLCRATE;
		// 字段 币种
		String pk_currtype = SpePayVO.PK_CURRTYPE;
		// 原币总金额字段精度处理
		PrecisionField totalamount = PrecisionField.buildHeadPrecisionField(
				SpePayVO.TOTALAMOUNT, SpePayVO.OLCTOTALAMOUNT, olcrate,
				SpePayVO.GLCTOTALAMOUNT, glcrate, SpePayVO.GLLCTOTALAMOUNT,
				gllcrate, pk_currtype);
		list.add(totalamount);
		return list;
	}

	/**
	 * 重算表体金额时获取的字段
	 * 
	 * @return@return key:表体class,value:表体字段
	 */
	public static Map<Class<? extends ISuperVO>, List<PrecisionField>> getBodyPrecisionFields() {
		List<PrecisionField> list = new ArrayList<>();
		// 字段 组织本币汇率
		String olcrate = SpePayBVO.OLCRATE;
		// 字段 集团本币汇率
		String glcrate = SpePayBVO.GLCRATE;
		// 字段 全局本币汇率
		String gllcrate = SpePayBVO.GLLCRATE;
		// 字段 币种
		String pk_currtype = SpePayVO.PK_CURRTYPE;
		// 原币总金额字段精度处理
		PrecisionField amount = PrecisionField.buildHeadPrecisionField(
				SpePayBVO.AMOUNT, SpePayBVO.OLCAMOUNT, olcrate,
				SpePayBVO.GLCAMOUNT, glcrate, SpePayBVO.GLLCAMOUNT, gllcrate,
				pk_currtype);
		list.add(amount);

		Map<Class<? extends ISuperVO>, List<PrecisionField>> map = new HashMap<>();
		map.put(SpePayBVO.class, list);
		return map;
	}

	/**
	 * 表体精度处理字段
	 * 
	 * @return
	 */
	public static Map<String, List<PrecisionField>> getBodyPrecisionFieldMap() {
		List<PrecisionField> list = new ArrayList<>();
		// 字段 组织本币汇率
		String olcrate = SpePayBVO.OLCRATE;
		// 字段 集团本币汇率
		String glcrate = SpePayBVO.GLCRATE;
		// 字段 全局本币汇率
		String gllcrate = SpePayBVO.GLLCRATE;
		// 字段 币种
		String pk_currtype = SpePayVO.PK_CURRTYPE;
		// 原币总金额字段精度处理
		PrecisionField amount = PrecisionField.buildHeadPrecisionField(
				SpePayBVO.AMOUNT, SpePayBVO.OLCAMOUNT, olcrate,
				SpePayBVO.GLCAMOUNT, glcrate, SpePayBVO.GLLCAMOUNT, gllcrate,
				pk_currtype);
		list.add(amount);
		Map<String, List<PrecisionField>> map = new HashMap<>();
		map.put(FTSConstInforNCC.AREACODE_C_SPECIALTRANSFERSPEPAY_BODY, list);
		return map;
	}

}
