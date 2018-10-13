/**
 *
 */
package nc.bs.ifm.pub.util;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.exception.ComponentException;
import nc.bs.logging.Logger;
import nc.itf.sf.pub.SFPubProxy;
import nc.itf.uap.sf.ICreateCorpQueryService;
import nc.itf.uif.pub.IUifService;
import nc.pubitf.initgroup.InitGroupQuery;
import nc.uif.pub.exception.UifException;
import nc.vo.fts.pub.exception.FTSBusinessException;
import nc.vo.fts.workdate.CalendarlogSuperVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.tmpub.util.ModuleUtil;

/**
 * @author yangxy
 *
 */
public class IFMQueryModulesUtil {

	public class FunCodeConst {

		// �ʽ����
		public static final String TM_FUNCODE = "36"; // �ʽ����
		public static final String TM_PS_FUNCODE = "3612"; // �����ų�
		public static final String TM_OBM_FUNCODE = "3610"; // ����ֱ��
		public static final String TM_FTS_FUNCODE = "3630";// �ʽ����
		public static final String FIP_FUNCODE = "1017";// ���ƽ̨
		public static final String TBB_FUNCODE = "1050";// Ԥ��
		public static final String TM_CMP_FUNCODE = "3607";// �ֽ����
	}

	/**
	 * ��������ģ��API
	 *
	 * @param pk_group
	 *            ����
	 * @param funcode
	 *            ���ܽڵ� ������Դ��dap_dapsystem
	 * @return
	 * @throws BusinessException
	 */
	private static boolean isEnable(String pk_group, String funcode)
			throws BusinessException {
		try {
			return InitGroupQuery.isEnabled(
					pk_group == null ? InvocationInfoProxy.getInstance()
							.getGroupId() : pk_group, funcode);
		} catch (BusinessException be) {
			Logger.error(be);
			return false;
		}

	}

	/**
	 * ��ѯ�����ڼ�
	 *
	 * @param pk_group
	 * @param funcode
	 * @return
	 * @throws BusinessException
	 */
	private static String[] queryEnabledPeriod(String pk_group, String funcode)
			throws BusinessException {
		return getICreateCorpQueryService().queryEnabledPeriod(pk_group,
				funcode);
	}

	/**
	 * �ж�����ֱ���Ƿ�װ
	 *
	 * @param pk_group
	 * @return
	 * @throws BusinessException
	 */
	public static boolean isOBMEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, FunCodeConst.TM_OBM_FUNCODE);
	}

	/**
	 * �ж��ʽ�����Ƿ�װ
	 *
	 * @param pk_group
	 * @return
	 * @throws BusinessException
	 */
	public static boolean isFTSEnable(String pk_group) throws BusinessException {
		return SFPubProxy.getModuleCheckService().isModuleInstalled(FunCodeConst.TM_FTS_FUNCODE);
	}

	/**
	 * �жϻ��ƽ̨�Ƿ�װ
	 *
	 * @param pk_group
	 * @return
	 * @throws BusinessException
	 */
	public static boolean isFIPEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, FunCodeConst.FIP_FUNCODE);
	}


	/**
	 * �ж�Ԥ���Ƿ�װ
	 *
	 * @param pk_group
	 * @return
	 * @throws BusinessException
	 */
	public static boolean isTBBEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, FunCodeConst.TBB_FUNCODE);
	}

	/**
	 * �ж���֯�Ƿ������ֽ����
	 */
	public static boolean isCMPOrgEnable(String pk_org,UFDate busiDate)
			throws BusinessException {
		return isOrgEnabled(pk_org, FunCodeConst.TM_CMP_FUNCODE,busiDate);
	}

	/**
	 * �ж��ֽ�����Ƿ�װ
	 *
	 * @param pk_group
	 * @return
	 * @throws BusinessException
	 */
	public static boolean isCMPEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, FunCodeConst.TM_CMP_FUNCODE);
	}


	/**
	 * ��֯�Ƿ�����ģ��
	 * @param pk_org
	 * @param fundcode
	 * @return
	 * @throws BusinessException
	 */
	private static boolean isOrgEnabled(String pk_org, String fundcode,UFDate busiDate) throws BusinessException {
		UFDate initDate = ModuleUtil.queryInitDateByOrg(pk_org, fundcode);
		if(null == busiDate)
			throw new  BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3632pub_0","03632pub-0075")/*@res "�����ҵ������Ϊ�գ��޷��ж���֯�Ƿ�����"*/);
		if(null == initDate)
			return false;
		if(busiDate.beforeDate(initDate))
			return false;
		return true;
	}
	public static ICreateCorpQueryService getICreateCorpQueryService()
			throws ComponentException {
		return ((ICreateCorpQueryService) NCLocator.getInstance().lookup(
				ICreateCorpQueryService.class.getName()));

	}
	
	/**
	 * 
	 * �ж��ʽ���֯ĳ���Ƿ��Ѿ���������
	 * @param pk_org
	 * @param date
	 * @return
	 * @author hanlua
	 * @throws BusinessException 
	 * @since NC6.1
	 */
	public  void checkClose(String pk_org,UFDate date) throws BusinessException {
		 
//			UFBoolean isEnd = isEndProcess(pk_org,date);
//			
//			if(isEnd.booleanValue()){
//	
//				throw new BusinessException("�ʽ���֯"+date.toStdString()+"�Ѿ����������޷�����ҵ�����л�ҵ�����ڻ���ϵ�ʽ���֯�ָ�����!");	
//			}	
	}
	/**
	 * �жϵ�ǰ��˾�����Ƿ��Ѿ��������� ��������ĵĵ�½���ڣ���������������������������ýڵ��и����ڣ�����ô���Ŀ������κζ�����
	 * ��������ĵĵ�½���ڣ����Ĳ���������������������ýڵ�û�и����ڣ�����ô���ĳ��˲�ѯ������������������������ 
	 *
	 * @param pk_corp
	 *            ��ǰ��˾������ curworkdate:˵Ҫ��������
	 * @return true:�Ѿ���ʼ���� false:��û�п�ʼ����
	 * @throws BusinessException
	 * @version 6.1
	 * @since NC6.1
	 * @author hanlua
	 */
	public UFBoolean isEndProcess(String pk_org, UFDate curworkdate)
			throws FTSBusinessException {
		// �Ƿ��Ѿ���������
		
		try {
			String strWhere = "  workdate ='" + curworkdate.toString()
					+ "' and pk_org ='" + pk_org
					+ "' and isnull(dr,0)=0 and isnull(downer, '~') <> '~' ";
			CalendarlogSuperVO[] calendarlogsupervos = (CalendarlogSuperVO[]) getUifService()
					.queryByCondition(CalendarlogSuperVO.class, strWhere);
			if (calendarlogsupervos != null && calendarlogsupervos.length > 0) {
				// ��ѯ�������Ѿ���������
				
				return  UFBoolean.TRUE;
			} else {
				
				return UFBoolean.FALSE;
			}
		} catch (UifException e) {
			String hint = new StringBuilder(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3630pub_0","03630pub-0109")/*@res "�ʽ�����־���������ݿ��ѯ�����޷�������ѯ"*/)
					.toString();
			Logger.error(hint, e);
			throw new FTSBusinessException(hint);
		}
	}
	private IUifService getUifService() {
		try {
			return (IUifService) NCLocator.getInstance().lookup(
					IUifService.class.getName());
		} catch (Exception e) {
			Logger.error(e);
			return null;
		}
	}	

}