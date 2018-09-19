package nccloud.web.ifm.investapply.util;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import nc.itf.ifm.IIFMApplyQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.pub.BusinessException;
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
		IIFMApplyQueryService service = ServiceLocator
				.find(IIFMApplyQueryService.class);
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
			IIFMApplyQueryService service = ServiceLocator
					.find(IIFMApplyQueryService.class);
			String[] status = service.queryIFMApplyBySchema(scheme);
			int  numSPZ = 0, numDTJ = 0;
			for (int i = 0; i < status.length; i++) {
//				if (status[i].equals("NOEXECUTE") ) {// 未执行
//					numWZX++;
//					continue;
//				}
//				if (status[i].equals("EXECUTING")) {// 执行中
//					numZXZ++;
//					continue;
//				}
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
	
}
