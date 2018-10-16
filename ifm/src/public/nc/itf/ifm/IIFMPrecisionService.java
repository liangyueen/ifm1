package nc.itf.ifm;

import java.util.HashMap;

import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFDate;

/**  
 * @Description: ���ȼ�����ӿ�
 * @author wangjias 
 * @date 2018-09-03
 * @version V1.0  
 */ 
public interface IIFMPrecisionService {
	
	/**
	 * ������֯������ȡ��֯��λ�ұ���
	 * @param pk_org
	 * @return
	 * @throws BusinessException
	 */
	String getOrgStandardCurrtype(String pk_org) throws BusinessException;

	/**
	 * ���ݼ���������ȡ���ű�λ�ұ���
	 * @param pk_group
	 * @return
	 * @throws BusinessException
	 */
	String getGroupStandardCurrtype(String pk_group) throws BusinessException;

	/**
	 * ��ȡȫ�ֱ�λ�ұ���
	 * @return
	 * @throws BusinessException
	 */
	String getGlobalStandardCurrtype() throws BusinessException;

	/**
	 * �����ŵ�����
	 * ����ԭ�ҽ���VO�е���֯�����š�ȫ�ֱ��ҽ����ݾ���
	 * 
	 * @param vo
	 * 			������VO���߾ۺ�VO�еı�ͷ�����VO
	 * @param isRecaculate
	 * 			�Ƿ�����
	 * @param field
	 * 			VO��Ҫ��������Ե�����
	 * @param busiDate
	 * 			ҵ������
	 * @throws BusinessException
	 */
	ISuperVO processPrecision(ISuperVO vo, boolean isRecaculate, HashMap<String, String> field, UFDate busiDate) throws BusinessException;
}
