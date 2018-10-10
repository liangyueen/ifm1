package nccloud.pubitf.ifm.pub;

import java.util.Map;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

import com.alibaba.fastjson.JSONObject;

/**
 * ����Ԥ�����ӿ�
 * 
 * @author wusib
 * 
 */
public interface IIFMPubLinkPlanService4NCC {

	/**
	 * ����Ԥ��
	 * 
	 * @param pk
	 *            ����
	 * @param className
	 *            �ۺ�vo����
	 * @param pk_billtypecode
	 *            ��������
	 * @return
	 * @throws BusinessException
	 */
	public JSONObject linkNtbPlan(String pk, String className,
			String pk_billtypecode) throws BusinessException;

	/**
	 * Ԥ�����鵥��
	 * 
	 * @param pk
	 *            ����
	 * @param className
	 *            �ۺ�vo���� ����: AggDeliveryVO
	 * @return
	 * @throws BusinessException
	 */
	public Map<String, AggregatedValueObject> doNtbQueryBill(String pk,
			String className) throws BusinessException;
}
