package nccloud.web.ifm.util;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import nc.itf.ifm.IInvestRedeemQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.BusinessException;
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
				if (status[i].equals("-1") ) {// 未执行
					numWZX++;
					continue;
				}
				if (status[i].equals("0")) {// 执行中
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
}
