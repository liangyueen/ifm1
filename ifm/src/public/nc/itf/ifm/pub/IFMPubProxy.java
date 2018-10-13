/*
 * @(#)SFPubProxy.java 2012-2-4
 * Copyright 2010 UFIDA Software CO.LTD. All rights reserved.
 */
package nc.itf.ifm.pub;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.bd.bankdoc.IBankdocQueryService;
import nc.itf.bd.currtype.ICurrtypeQuery;
import nc.itf.corg.IFundManaSystemQryService;
import nc.itf.tmpub.uapbd.IUapPubSmartService;
import nc.itf.uap.rbac.IUserManageQuery_C;
import nc.pubitf.cmp.bankaccbook.IBankAccQueryService;
import nc.pubitf.cmp.bankaccbook.IBankAccService;
import nc.pubitf.obm.IObmLogManageService;
import nc.pubitf.org.IGroupPubService;
import nc.pubitf.tmpub.modulecheck.IModuleCheckService;
import nc.pubitf.tmpub.print.INCSignatureService;

/**
 * SF ����
 *
 * @author  chengfei
 * @version 1.0 2012-2-4
 * @since   NC6.1
 */
public class IFMPubProxy {
	
	
	/**
	 * ��ȡ����ǩ��ǩ������
	 * 
	 * @return
	 */
	public static INCSignatureService getNCSignatureService() {

		try {
			return (INCSignatureService) NCLocator.getInstance().lookup(
					INCSignatureService.class.getName());
		} catch (Exception e) {
			Logger.error(e);
			return null;
		}

	}
	
	public static IModuleCheckService getModuleCheckService() {

		try {
			return (IModuleCheckService) NCLocator.getInstance().lookup(
					IModuleCheckService.class.getName());
		} catch (Exception e) {
			Logger.error(e);
			return null;
		}

	}
	
	/**
	 * ��ȡ�����˷���
	 * 
	 * @return
	 */
	public static IBankAccService getBankAccService() {

		try {
			return (IBankAccService) NCLocator.getInstance().lookup(
					IBankAccService.class.getName());
		} catch (Exception e) {
			Logger.error(e);
			return null;
		}

	}
	
	/**
	 * ��ȡ�����˲�ѯ����
	 * 
	 * @return
	 */
	public static IBankAccQueryService getBankAccQueryService() {

		try {
			return (IBankAccQueryService) NCLocator.getInstance().lookup(
					IBankAccQueryService.class.getName());
		} catch (Exception e) {
			Logger.error(e);
			return null;
		}

	}
	
	/**
	 * 
	 * ��װһЩUAPû���ṩ�ķ���
	 * 
	 * @return
	 * @author chengfei
	 * @since NC6.0
	 */
	public static IUapPubSmartService getUapPubSmartService() {
		try {
			return (IUapPubSmartService) NCLocator.getInstance().lookup(
					IUapPubSmartService.class.getName());
		} catch (Exception e) {
			Logger.error(e);
			return null;
		}
	}
	
	/**
	 * 
	 * ��ѯ���е����ӿ�
	 * @return
	 * @author chengfei
	 * @since NC6.0
	 */
	public static IBankdocQueryService getBankdocQueryService() {
		try {
			return (IBankdocQueryService) NCLocator.getInstance().lookup(
					IBankdocQueryService.class.getName());
		} catch (Exception ex) {
			Logger.error(ex);
			return null;
		}
	}
	
	/**
	 * �õ������������ṩ����
	 * 
	 * @return
	 */
	public static IObmLogManageService getObmLogManageService() {
		try {
			return (IObmLogManageService) NCLocator.getInstance().lookup(
					IObmLogManageService.class.getName());
		} catch (Exception ex) {
			Logger.error(ex);
			return null;
		}
		
	}
	
	/**
	 * ��ȡ���Ų�ѯ�ӿ�
	 * @return
	 * @author chengfei
	 * @since NC6.1
	 */
	public static IGroupPubService getGroupPubService() {
		try {
			return (IGroupPubService) NCLocator.getInstance().lookup(
					IGroupPubService.class.getName());
		} catch (Exception ex) {
			Logger.error(ex);
			return null;
		}
		
	}
	
	/**
	 * ��ȡ�ʽ������ϵ ���ݹ������ӿ�
	 * @return
	 * @author chengfei
	 * @since NC6.1
	 */
	public static IFundManaSystemQryService getFMSQryService() {
		try {
			return (IFundManaSystemQryService) NCLocator.getInstance().lookup(
					IFundManaSystemQryService.class.getName());
		} catch (Exception ex) {
			Logger.error(ex);
			return null;
		}
		
	}
	/**
	 * ���ֲ�ѯ����
	 * @return
	 */
	public static ICurrtypeQuery getCurrtypeQuery(){
		try {
			return (ICurrtypeQuery) NCLocator.getInstance().lookup(
					ICurrtypeQuery.class.getName());
		} catch (Exception ex) {
			Logger.error(ex);
			return null;
		}
	}
	/**
	 * �û���ѯ����
	 * @return
	 */
	public static IUserManageQuery_C getUserManageQuery_C(){
		try {
			return (IUserManageQuery_C) NCLocator.getInstance().lookup(
					IUserManageQuery_C.class.getName());
		} catch (Exception ex) {
			Logger.error(ex);
			return null;
		}
	}
	
}
