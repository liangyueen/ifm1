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

		// 资金管理
		public static final String TM_FUNCODE = "36"; // 资金管理
		public static final String TM_PS_FUNCODE = "3612"; // 付款排程
		public static final String TM_OBM_FUNCODE = "3610"; // 银企直连
		public static final String TM_FTS_FUNCODE = "3630";// 资金结算
		public static final String FIP_FUNCODE = "1017";// 会计平台
		public static final String TBB_FUNCODE = "1050";// 预算
		public static final String TM_CMP_FUNCODE = "3607";// 现金管理
	}

	/**
	 * 调用启用模块API
	 *
	 * @param pk_group
	 *            集团
	 * @param funcode
	 *            功能节点 数据来源于dap_dapsystem
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
	 * 查询启用期间
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
	 * 判断银企直连是否安装
	 *
	 * @param pk_group
	 * @return
	 * @throws BusinessException
	 */
	public static boolean isOBMEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, FunCodeConst.TM_OBM_FUNCODE);
	}

	/**
	 * 判断资金结算是否安装
	 *
	 * @param pk_group
	 * @return
	 * @throws BusinessException
	 */
	public static boolean isFTSEnable(String pk_group) throws BusinessException {
		return SFPubProxy.getModuleCheckService().isModuleInstalled(FunCodeConst.TM_FTS_FUNCODE);
	}

	/**
	 * 判断会计平台是否安装
	 *
	 * @param pk_group
	 * @return
	 * @throws BusinessException
	 */
	public static boolean isFIPEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, FunCodeConst.FIP_FUNCODE);
	}


	/**
	 * 判断预算是否安装
	 *
	 * @param pk_group
	 * @return
	 * @throws BusinessException
	 */
	public static boolean isTBBEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, FunCodeConst.TBB_FUNCODE);
	}

	/**
	 * 判断组织是否启用现金管理
	 */
	public static boolean isCMPOrgEnable(String pk_org,UFDate busiDate)
			throws BusinessException {
		return isOrgEnabled(pk_org, FunCodeConst.TM_CMP_FUNCODE,busiDate);
	}

	/**
	 * 判断现金管理是否安装
	 *
	 * @param pk_group
	 * @return
	 * @throws BusinessException
	 */
	public static boolean isCMPEnable(String pk_group) throws BusinessException {
		return isEnable(pk_group, FunCodeConst.TM_CMP_FUNCODE);
	}


	/**
	 * 组织是否启用模块
	 * @param pk_org
	 * @param fundcode
	 * @return
	 * @throws BusinessException
	 */
	private static boolean isOrgEnabled(String pk_org, String fundcode,UFDate busiDate) throws BusinessException {
		UFDate initDate = ModuleUtil.queryInitDateByOrg(pk_org, fundcode);
		if(null == busiDate)
			throw new  BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3632pub_0","03632pub-0075")/*@res "传入的业务日期为空，无法判断组织是否启用"*/);
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
	 * 判断资金组织某天是否已经结束受理
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
//				throw new BusinessException("资金组织"+date.toStdString()+"已经结束受理，无法受理业务，请切换业务日期或联系资金组织恢复受理!");	
//			}	
	}
	/**
	 * 判断当前公司当天是否已经结束受理 如果在中心的登陆日期，中心曾经受理过（受理日期设置节点有该日期），那么中心可以做任何动作。
	 * 如果在中心的登陆日期，中心不曾受理过（受理日期设置节点没有该日期），那么中心除了查询以外其他动作都不可以做。 
	 *
	 * @param pk_corp
	 *            当前公司主键， curworkdate:说要检查的日期
	 * @return true:已经开始受理 false:还没有开始受理
	 * @throws BusinessException
	 * @version 6.1
	 * @since NC6.1
	 * @author hanlua
	 */
	public UFBoolean isEndProcess(String pk_org, UFDate curworkdate)
			throws FTSBusinessException {
		// 是否已经结束受理
		
		try {
			String strWhere = "  workdate ='" + curworkdate.toString()
					+ "' and pk_org ='" + pk_org
					+ "' and isnull(dr,0)=0 and isnull(downer, '~') <> '~' ";
			CalendarlogSuperVO[] calendarlogsupervos = (CalendarlogSuperVO[]) getUifService()
					.queryByCondition(CalendarlogSuperVO.class, strWhere);
			if (calendarlogsupervos != null && calendarlogsupervos.length > 0) {
				// 查询工作日已经结束受理
				
				return  UFBoolean.TRUE;
			} else {
				
				return UFBoolean.FALSE;
			}
		} catch (UifException e) {
			String hint = new StringBuilder(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3630pub_0","03630pub-0109")/*@res "资金工作日志，由于数据库查询错误，无法正常查询"*/)
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