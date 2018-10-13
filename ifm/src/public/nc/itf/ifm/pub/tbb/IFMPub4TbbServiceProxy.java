/*
 * @(#)SfPub4TbbServiceProxy.java 2011-3-23
 * Copyright 2010 UFIDA Software CO.LTD. All rights reserved.
 */
package nc.itf.ifm.pub.tbb;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.tb.control.IBudgetControl;
import nc.itf.tb.control.ILinkQuery;

/**
 * �ʽ����Ԥ��ӿڴ���
 *
 * @author  chengfei
 * @version 1.0 2011-3-23
 * @since   NC6.0
 */
public class IFMPub4TbbServiceProxy {
	
	public static IBudgetControl getBudgetControl() {
		try {
			return (IBudgetControl) NCLocator.getInstance().lookup(
					IBudgetControl.class.getName());
		} catch (Exception e) {
			Logger.error(e);
			return null;
		}
	}
	/**
	 * �õ�Ԥ������ӿ�
	 * ydx20110324
	 * @return
	 */
	public static ILinkQuery  getBudgetLinkQuery() {
		try {
			return (ILinkQuery) NCLocator.getInstance().lookup(
					ILinkQuery.class.getName());
		} catch (Exception e) {
			Logger.error(e);
			return null;
		}
		
	}
}
