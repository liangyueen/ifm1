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
 * ���������ѯ������
 * 
 * @author futao3
 * @date Aug 29, 2018
 */
public class ApplyQueryUtil {
	
	/**
	 * �õ�������Ϣ
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
	 * ����pk��ѯ��������ۺ�VO
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
				ExceptionUtils.wrapBusinessException("����pk��ѯ��ǰҳ����ʧ�ܣ�"
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
//				if (status[i].equals("NOEXECUTE") ) {// δִ��
//					numWZX++;
//					continue;
//				}
//				if (status[i].equals("EXECUTING")) {// ִ����
//					numZXZ++;
//					continue;
//				}
				if (status[i].equals("1")) {// ������
					numSPZ++;
					continue;
				}
				if (status[i].equals("0")) {// ���ύ
					numDTJ++;
					continue;
				}
			}
//			map.put("WZX",String.valueOf(numWZX));
//			map.put("ZXZ",String.valueOf(numZXZ));
			map.put("SPZ",String.valueOf(numSPZ));
			map.put("DTJ",String.valueOf(numDTJ));
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("��ѯ����ʧ�ܣ�" + e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
}
