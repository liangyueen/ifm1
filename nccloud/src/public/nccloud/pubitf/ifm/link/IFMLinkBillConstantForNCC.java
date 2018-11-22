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
	 * ��ͷ���ȴ����ֶ�
	 * 
	 * @return
	 */
	public static List<PrecisionField> getHeadPrecisionFields() {
		List<PrecisionField> list = new ArrayList<>();
		// �ֶ� ��֯���һ���
		String olcrate = SpePayBVO.OLCRATE;
		// �ֶ� ���ű��һ���
		String glcrate = SpePayBVO.GLCRATE;
		// �ֶ� ȫ�ֱ��һ���
		String gllcrate = SpePayBVO.GLLCRATE;
		// �ֶ� ����
		String pk_currtype = SpePayVO.PK_CURRTYPE;
		// ԭ���ܽ���ֶξ��ȴ���
		PrecisionField totalamount = PrecisionField.buildHeadPrecisionField(
				SpePayVO.TOTALAMOUNT, SpePayVO.OLCTOTALAMOUNT, olcrate,
				SpePayVO.GLCTOTALAMOUNT, glcrate, SpePayVO.GLLCTOTALAMOUNT,
				gllcrate, pk_currtype);
		list.add(totalamount);
		return list;
	}

	/**
	 * ���������ʱ��ȡ���ֶ�
	 * 
	 * @return@return key:����class,value:�����ֶ�
	 */
	public static Map<Class<? extends ISuperVO>, List<PrecisionField>> getBodyPrecisionFields() {
		List<PrecisionField> list = new ArrayList<>();
		// �ֶ� ��֯���һ���
		String olcrate = SpePayBVO.OLCRATE;
		// �ֶ� ���ű��һ���
		String glcrate = SpePayBVO.GLCRATE;
		// �ֶ� ȫ�ֱ��һ���
		String gllcrate = SpePayBVO.GLLCRATE;
		// �ֶ� ����
		String pk_currtype = SpePayVO.PK_CURRTYPE;
		// ԭ���ܽ���ֶξ��ȴ���
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
	 * ���徫�ȴ����ֶ�
	 * 
	 * @return
	 */
	public static Map<String, List<PrecisionField>> getBodyPrecisionFieldMap() {
		List<PrecisionField> list = new ArrayList<>();
		// �ֶ� ��֯���һ���
		String olcrate = SpePayBVO.OLCRATE;
		// �ֶ� ���ű��һ���
		String glcrate = SpePayBVO.GLCRATE;
		// �ֶ� ȫ�ֱ��һ���
		String gllcrate = SpePayBVO.GLLCRATE;
		// �ֶ� ����
		String pk_currtype = SpePayVO.PK_CURRTYPE;
		// ԭ���ܽ���ֶξ��ȴ���
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
