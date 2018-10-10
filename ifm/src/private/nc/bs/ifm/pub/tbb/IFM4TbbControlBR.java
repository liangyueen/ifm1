package nc.bs.ifm.pub.tbb;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nc.bs.ifm.pub.util.IFMQueryModulesUtil;
import nc.itf.ifm.pub.tbb.IFMPub4TbbServiceProxy;
import nc.itf.ifm.pub.tbb.IIFM4TbbConst;
import nc.vo.ifm.pub.tbb.IFMTbbBusiVOFactory;
import nc.vo.ifm.pub.tbb.IFMToTbbAccessableBusiVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.VOStatus;
import nc.vo.tmpub.util.ArrayUtil;
import nc.vo.tmpub.util.StringUtil;

/**
 * 投资理财-预算控制后台类
 *
 */
public  class IFM4TbbControlBR<T extends AggregatedValueObject> {
	public T writeTbbReadyData(T aggVO,int direction,String pk_billType)throws BusinessException{
		
		if(aggVO != null){
			if(!IFMQueryModulesUtil.isTBBEnable(StringUtil.toString(aggVO.getParentVO().getAttributeValue("pk_group")))){
				return aggVO;
			}
			
			try {
				/** ***********以下进行预算控制***************** */
				List<IFMToTbbAccessableBusiVO> busiVOList = new ArrayList<IFMToTbbAccessableBusiVO>();
				if(ArrayUtil.isNull(aggVO.getChildrenVO())){
					IFMToTbbAccessableBusiVO ifmBillBusiVO = IFMTbbBusiVOFactory.getInstance().chgToIFMBusiVO(pk_billType);
					ifmBillBusiVO.setParentVO(aggVO.getParentVO());
					ifmBillBusiVO.setDirection(direction);
					ifmBillBusiVO.setDataType(IIFM4TbbConst.Flag_Prefind);
					busiVOList.add(ifmBillBusiVO);
				}
				else{
					for (int i = 0; i < aggVO.getChildrenVO().length; i++) {
						if(aggVO.getChildrenVO()[i].getStatus() == VOStatus.DELETED){
							continue;
						}
						IFMToTbbAccessableBusiVO ifmBillBusiVO = IFMTbbBusiVOFactory.getInstance().chgToIFMBusiVO(pk_billType);
						ifmBillBusiVO.setParentVO(aggVO.getParentVO());
						ifmBillBusiVO.setChildVO(aggVO.getChildrenVO()[i]);
						ifmBillBusiVO.setDirection(direction);
						ifmBillBusiVO.setDataType(IIFM4TbbConst.Flag_Prefind);
						busiVOList.add(ifmBillBusiVO);
						
					}
				}
				if(busiVOList.size() == 0){
					return aggVO;
				}
				// 调用预算接口BO
				nc.vo.tb.control.NtbCtlInfoVO tpcontrolvo = IFMPub4TbbServiceProxy.
						getBudgetControl().getControlInfo(busiVOList.toArray(new IFMToTbbAccessableBusiVO[busiVOList.size()]));
					
				String ntbException = "";
				
				if ((tpcontrolvo != null) && tpcontrolvo.isControl()) // 控制
				{
					String[] infos = tpcontrolvo.getControlInfos();
					for (int j = 0; j < infos.length; j++) {
						ntbException = ntbException  + infos[j]+ "\n"; // 控制信息
					}
	
					throw new BusinessException(ntbException);
	
				} else if ((tpcontrolvo != null) && tpcontrolvo.isAlarm())// 预警并且还没得到用户确认
				{
					String[] infos = tpcontrolvo.getAlarmInfos();
					for (int j = 0; j < infos.length; j++) {
						ntbException = ntbException + "\n" + infos[j]; // 预警信息
					}
					aggVO.getParentVO().setAttributeValue(IIFM4TbbConst.NTBINFOFIELD, ntbException);
	
				} else if ((tpcontrolvo != null) && tpcontrolvo.isMayBeControl()) // 柔性
				{
					String[] infos = tpcontrolvo.getFlexibleControlInfos();
					for (int j = 0; j < infos.length; j++) {
						StringBuffer sb=new StringBuffer(infos[j]);
	//    				[,是否提交特殊审批?]  10个字符
	    				ntbException = ntbException +sb.toString().replaceAll(IIFM4TbbConst.getFLEXMESSAGE(), "")+ "\n" ; // 柔性预算控制信息
					}
					ntbException=ntbException.trim();
					aggVO.getParentVO().setAttributeValue(IIFM4TbbConst.NTBINFOFIELD, ntbException);
	
				}
				/** ***********以上进行预算控制***************** */
			} catch (Exception ex) {
				if (ex instanceof SQLException) {
					throw new BusinessRuntimeException(ex.getMessage());
				} 
				else if (ex instanceof BusinessRuntimeException) {
					// 如果ex已经是FIBusinessRuntimeException，则直接抛出。
					throw (BusinessRuntimeException) ex;
				}
				else if (ex instanceof BusinessException) {
					throw (BusinessException)ex;
				}
			}
		}
		return aggVO;
	}
	
	public T writeTbbRunData(T aggVO,int direction,String pk_billType)throws BusinessException{
		if(aggVO != null){
			if(!IFMQueryModulesUtil.isTBBEnable(StringUtil.toString(aggVO.getParentVO().getAttributeValue("pk_group")))){
				return aggVO;
			}
			try {
				/** ***********以下进行预算控制***************** */
				List<IFMToTbbAccessableBusiVO> busiVOList = new ArrayList<IFMToTbbAccessableBusiVO>();
				if(ArrayUtil.isNull(aggVO.getChildrenVO())){
					IFMToTbbAccessableBusiVO ifmBillBusiVO = IFMTbbBusiVOFactory.getInstance().chgToIFMBusiVO(pk_billType);
					ifmBillBusiVO.setParentVO(aggVO.getParentVO());
					ifmBillBusiVO.setDirection(direction);
					ifmBillBusiVO.setDataType(IIFM4TbbConst.Flag_Ufind);
					busiVOList.add(ifmBillBusiVO);
				}
				else{
					for (int i = 0; i < aggVO.getChildrenVO().length; i++) {
						if(aggVO.getChildrenVO()[i].getStatus() == VOStatus.DELETED){
							continue;
						}
						IFMToTbbAccessableBusiVO ifmBillBusiVO = IFMTbbBusiVOFactory.getInstance().chgToIFMBusiVO(pk_billType);
						ifmBillBusiVO.setParentVO(aggVO.getParentVO());
						ifmBillBusiVO.setChildVO(aggVO.getChildrenVO()[i]);
						ifmBillBusiVO.setDirection(direction);
						ifmBillBusiVO.setDataType(IIFM4TbbConst.Flag_Ufind);
						busiVOList.add(ifmBillBusiVO);
					}
				}
				if(busiVOList.size() == 0){
					return aggVO;
				}
				
				// 调用预算接口BO
				nc.vo.tb.control.NtbCtlInfoVO tpcontrolvo = IFMPub4TbbServiceProxy.
						getBudgetControl().getControlInfo(busiVOList.toArray(new IFMToTbbAccessableBusiVO[busiVOList.size()]));
					
				String ntbException = "";
				
				if ((tpcontrolvo != null) && tpcontrolvo.isControl()) // 控制
				{
					String[] infos = tpcontrolvo.getControlInfos();
					for (int j = 0; j < infos.length; j++) {
						ntbException = ntbException  + infos[j]+ "\n"; // 控制信息
					}
	
					throw new BusinessException(ntbException);
	
				} else if ((tpcontrolvo != null) && tpcontrolvo.isAlarm())// 预警并且还没得到用户确认
				{
					String[] infos = tpcontrolvo.getAlarmInfos();
					for (int j = 0; j < infos.length; j++) {
						ntbException = ntbException + "\n" + infos[j]; // 预警信息
					}
					aggVO.getParentVO().setAttributeValue(IIFM4TbbConst.NTBINFOFIELD, ntbException);
	
				} else if ((tpcontrolvo != null) && tpcontrolvo.isMayBeControl()) // 柔性
				{
					String[] infos = tpcontrolvo.getFlexibleControlInfos();
					for (int j = 0; j < infos.length; j++) {
						StringBuffer sb=new StringBuffer(infos[j]);
	//    				[,是否提交特殊审批?]  10个字符
	    				ntbException = ntbException +sb.toString().replaceAll(IIFM4TbbConst.getFLEXMESSAGE(), "")+ "\n" ; // 柔性预算控制信息
					}
					ntbException=ntbException.trim();
					aggVO.getParentVO().setAttributeValue(IIFM4TbbConst.NTBINFOFIELD, ntbException);
	
				}
				/** ***********以上进行预算控制***************** */
			} catch (Exception ex) {
				if (ex instanceof SQLException) {
					throw new BusinessRuntimeException(ex.getMessage());
				} 
				else if (ex instanceof BusinessRuntimeException) {
					// 如果ex已经是FIBusinessRuntimeException，则直接抛出。
					throw (BusinessRuntimeException) ex;
				}
				else if (ex instanceof BusinessException) {
					throw (BusinessException)ex;
				}
			}
		}
		return aggVO;
	}
	
	public T writeTbbRunDataWithoutCheck(T aggVO,int direction,String pk_billType)throws BusinessException{
		if(aggVO != null && !ArrayUtil.isNull(aggVO.getChildrenVO())){
			if(!IFMQueryModulesUtil.isTBBEnable(StringUtil.toString(aggVO.getParentVO().getAttributeValue("pk_group")))){
				return aggVO;
			}
			try {
				/** ***********以下进行预算控制***************** */
				IFMToTbbAccessableBusiVO[] ifmBillBusiVOs = new IFMToTbbAccessableBusiVO[aggVO.getChildrenVO().length];
				for (int i = 0; i < aggVO.getChildrenVO().length; i++) {
					if(aggVO.getChildrenVO()[i].getStatus() == VOStatus.DELETED){
						continue;
					}
					IFMToTbbAccessableBusiVO ifmBillBusiVO = IFMTbbBusiVOFactory.getInstance().chgToIFMBusiVO(pk_billType);
					ifmBillBusiVO.setParentVO(aggVO.getParentVO());
					ifmBillBusiVO.setChildVO(aggVO.getChildrenVO()[i]);
					ifmBillBusiVO.setDirection(direction);
					ifmBillBusiVO.setDataType(IIFM4TbbConst.Flag_Ufind);
					ifmBillBusiVOs[i] = ifmBillBusiVO;
				}
				
				// 调用预算接口BO
				IFMPub4TbbServiceProxy.getBudgetControl().noCheckUpdateExe(ifmBillBusiVOs);
				
				
				/** ***********以上进行预算控制***************** */
			} catch (Exception ex) {
				if (ex instanceof SQLException) {
					throw new BusinessRuntimeException(ex.getMessage());
				} 
				else if (ex instanceof BusinessRuntimeException) {
					// 如果ex已经是FIBusinessRuntimeException，则直接抛出。
					throw (BusinessRuntimeException) ex;
				}
				else if (ex instanceof BusinessException) {
					throw (BusinessException)ex;
				}
			}
		}
		return aggVO;
	}
	
	public T writeTbbReadyDataWithoutCheck(T aggVO,int direction,String pk_billType)throws BusinessException{
		if(aggVO != null && !ArrayUtil.isNull(aggVO.getChildrenVO())){
			if(!IFMQueryModulesUtil.isTBBEnable(StringUtil.toString(aggVO.getParentVO().getAttributeValue("pk_group")))){
				return aggVO;
			}
			try {
				/** ***********以下进行预算控制***************** */
				IFMToTbbAccessableBusiVO[] ifmBillBusiVOs = new IFMToTbbAccessableBusiVO[aggVO.getChildrenVO().length];
				for (int i = 0; i < aggVO.getChildrenVO().length; i++) {
					if(aggVO.getChildrenVO()[i].getStatus() == VOStatus.DELETED){
						continue;
					}
					IFMToTbbAccessableBusiVO ifmBillBusiVO = IFMTbbBusiVOFactory.getInstance().chgToIFMBusiVO(pk_billType);
					ifmBillBusiVO.setParentVO(aggVO.getParentVO());
					ifmBillBusiVO.setChildVO(aggVO.getChildrenVO()[i]);
					ifmBillBusiVO.setDirection(direction);
					ifmBillBusiVO.setDataType(IIFM4TbbConst.Flag_Prefind);
					ifmBillBusiVOs[i] = ifmBillBusiVO;
				}
				
				// 调用预算接口BO
				IFMPub4TbbServiceProxy.getBudgetControl().noCheckUpdateExe(ifmBillBusiVOs);
				
				
				/** ***********以上进行预算控制***************** */
			} catch (Exception ex) {
				if (ex instanceof SQLException) {
					throw new BusinessRuntimeException(ex.getMessage());
				} 
				else if (ex instanceof BusinessRuntimeException) {
					// 如果ex已经是FIBusinessRuntimeException，则直接抛出。
					throw (BusinessRuntimeException) ex;
				}
				else if (ex instanceof BusinessException) {
					throw (BusinessException)ex;
				}
			}
		}
		return aggVO;
	}
}
