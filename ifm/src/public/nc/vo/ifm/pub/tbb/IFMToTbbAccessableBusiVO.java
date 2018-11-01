/*
 * @(#)SFToTbbAccessableBusiVO.java 2011-3-21
 * Copyright 2010 UFIDA Software CO.LTD. All rights reserved.
 */
package nc.vo.ifm.pub.tbb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nc.bs.logging.Logger;
import nc.itf.bd.pub.IBDMetaDataIDConst;
import nc.itf.ifm.pub.tbb.IIFM4TbbConst;
import nc.itf.sf.pub.tbb.ISF4TbbConst;
import nc.itf.tb.control.IAccessableBusiVO;
import nc.pubitf.bd.accessor.GeneralAccessorFactory;
import nc.vo.bd.accessor.IBDData;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 投资理财-预算控制VO基类
 *
 * @author  chengfei
 * @version 1.0 2011-3-21
 * @since   NC6.0
 */
public abstract class IFMToTbbAccessableBusiVO implements Serializable, IAccessableBusiVO,Cloneable{

	
	/** TODO 字段说明 */
	private static final long serialVersionUID = 6561283822365840393L;
	
	private CircularlyAccessibleValueObject parentVO;
	private CircularlyAccessibleValueObject childVO;
	// 预占数或者执行数,先只加执行数，不加预占数
	private String dataType = "UFIND"; 
	public boolean m_Isconverse = false; // 是否为逆向操作：逆向操作为true
	private int direction;
	public boolean isIsconverse() {
		return m_Isconverse;
	}

	public void setIsconverse(boolean newIsconverse) {
		m_Isconverse = newIsconverse;
	}
	// 根据注册字段名称，该字段对应的基础数据pk 查找所有上级PK
	@Override
	public String[] getAllUpLevels(String fieldname, String pk)
			throws Exception {
		if (fieldname.indexOf("plan") != -1) {
			// 查询资金计划项目档案
			List<IBDData> data = GeneralAccessorFactory.getAccessor(
					IBDMetaDataIDConst.FUNDPLAN).getFatherDocs(getPKOrg(), pk,
					false);
			if (data == null || data.size() == 0) {
				return null;
			}

			List<String> fundplankeys = new ArrayList<String>();
			for (IBDData dbdata : data) {
				fundplankeys.add(dbdata.getPk());
			}
			return fundplankeys.toArray(new String[] {});
		}
		return null;
	}

	@Override
	public String getBillType() {
		return getParentVO().getAttributeValue("pk_billtypecode").toString();
	}

	@Override
	public String getBusiDate() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getBusiSys() {
	
		return IIFM4TbbConst.IFMMoudleID;
	}

	
	@Override
	public String getBusiType() {
		// TODO Auto-generated method stub
		return "控制-投资理财";
	}


	
	@Override
	public String[] getHasLevelFlds() {
		return new String[] { "pk_fundplan" };
	}



	@Override
	public String getPkNcEntity() {
		
		return null;
	}

	
	@Override
	public boolean isUnInure() {
		// TODO Auto-generated method stub
		return false;
	}



	public CircularlyAccessibleValueObject getParentVO() {
		return parentVO;
	}



	public void setParentVO(CircularlyAccessibleValueObject parentVO) {
		this.parentVO = parentVO;
	}



	public CircularlyAccessibleValueObject getChildVO() {
		return childVO;
	}



	public void setChildVO(CircularlyAccessibleValueObject childVO) {
		this.childVO = childVO;
	}


	public int getDirection() {
		return direction;
	}
	

	public void setDirection(int direction) {
		this.direction = direction;
	}



	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Override
	public String getDataType() {
		
		return dataType;
	}
	
	public IFMToTbbAccessableBusiVO clone() {
		IFMToTbbAccessableBusiVO o = null;
		try {
			o = (IFMToTbbAccessableBusiVO) super.clone();
		} catch (CloneNotSupportedException e) {
			Logger.error(e);
			ExceptionUtils.wrappException(e);
		}
		return o;
	}

}
