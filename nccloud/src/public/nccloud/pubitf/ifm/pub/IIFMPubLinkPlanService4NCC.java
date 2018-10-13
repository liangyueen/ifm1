package nccloud.pubitf.ifm.pub;

import java.util.Map;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

import com.alibaba.fastjson.JSONObject;

/**
 * 联查预算服务接口
 * 
 * @author wusib
 * 
 */
public interface IIFMPubLinkPlanService4NCC {

	/**
	 * 联查预算
	 * 
	 * @param pk
	 *            主键
	 * @param className
	 *            聚合vo类名
	 * @param pk_billtypecode
	 *            单据类型
	 * @return
	 * @throws BusinessException
	 */
	public JSONObject linkNtbPlan(String pk, String className,
			String pk_billtypecode) throws BusinessException;

	/**
	 * 预算联查单据
	 * 
	 * @param pk
	 *            主键
	 * @param className
	 *            聚合vo类名 例子: AggDeliveryVO
	 * @return
	 * @throws BusinessException
	 */
	public Map<String, AggregatedValueObject> doNtbQueryBill(String pk,
			String className) throws BusinessException;
}
