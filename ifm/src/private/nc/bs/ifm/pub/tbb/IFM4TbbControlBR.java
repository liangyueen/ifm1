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
 * Ͷ�����-Ԥ����ƺ�̨��
 *
 */
public  class IFM4TbbControlBR<T extends AggregatedValueObject> {
	public T writeTbbReadyData(T aggVO,int direction,String pk_billType)throws BusinessException{
		
		if(aggVO != null){
			if(!IFMQueryModulesUtil.isTBBEnable(StringUtil.toString(aggVO.getParentVO().getAttributeValue("pk_group")))){
				return aggVO;
			}
			
			try {
				/** ***********���½���Ԥ�����***************** */
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
				// ����Ԥ��ӿ�BO
				nc.vo.tb.control.NtbCtlInfoVO tpcontrolvo = IFMPub4TbbServiceProxy.
						getBudgetControl().getControlInfo(busiVOList.toArray(new IFMToTbbAccessableBusiVO[busiVOList.size()]));
					
				String ntbException = "";
				
				if ((tpcontrolvo != null) && tpcontrolvo.isControl()) // ����
				{
					String[] infos = tpcontrolvo.getControlInfos();
					for (int j = 0; j < infos.length; j++) {
						ntbException = ntbException  + infos[j]+ "\n"; // ������Ϣ
					}
	
					throw new BusinessException(ntbException);
	
				} else if ((tpcontrolvo != null) && tpcontrolvo.isAlarm())// Ԥ�����һ�û�õ��û�ȷ��
				{
					String[] infos = tpcontrolvo.getAlarmInfos();
					for (int j = 0; j < infos.length; j++) {
						ntbException = ntbException + "\n" + infos[j]; // Ԥ����Ϣ
					}
					aggVO.getParentVO().setAttributeValue(IIFM4TbbConst.NTBINFOFIELD, ntbException);
	
				} else if ((tpcontrolvo != null) && tpcontrolvo.isMayBeControl()) // ����
				{
					String[] infos = tpcontrolvo.getFlexibleControlInfos();
					for (int j = 0; j < infos.length; j++) {
						StringBuffer sb=new StringBuffer(infos[j]);
	//    				[,�Ƿ��ύ��������?]  10���ַ�
	    				ntbException = ntbException +sb.toString().replaceAll(IIFM4TbbConst.getFLEXMESSAGE(), "")+ "\n" ; // ����Ԥ�������Ϣ
					}
					ntbException=ntbException.trim();
					aggVO.getParentVO().setAttributeValue(IIFM4TbbConst.NTBINFOFIELD, ntbException);
	
				}
				/** ***********���Ͻ���Ԥ�����***************** */
			} catch (Exception ex) {
				if (ex instanceof SQLException) {
					throw new BusinessRuntimeException(ex.getMessage());
				} 
				else if (ex instanceof BusinessRuntimeException) {
					// ���ex�Ѿ���FIBusinessRuntimeException����ֱ���׳���
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
				/** ***********���½���Ԥ�����***************** */
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
				
				// ����Ԥ��ӿ�BO
				nc.vo.tb.control.NtbCtlInfoVO tpcontrolvo = IFMPub4TbbServiceProxy.
						getBudgetControl().getControlInfo(busiVOList.toArray(new IFMToTbbAccessableBusiVO[busiVOList.size()]));
					
				String ntbException = "";
				
				if ((tpcontrolvo != null) && tpcontrolvo.isControl()) // ����
				{
					String[] infos = tpcontrolvo.getControlInfos();
					for (int j = 0; j < infos.length; j++) {
						ntbException = ntbException  + infos[j]+ "\n"; // ������Ϣ
					}
	
					throw new BusinessException(ntbException);
	
				} else if ((tpcontrolvo != null) && tpcontrolvo.isAlarm())// Ԥ�����һ�û�õ��û�ȷ��
				{
					String[] infos = tpcontrolvo.getAlarmInfos();
					for (int j = 0; j < infos.length; j++) {
						ntbException = ntbException + "\n" + infos[j]; // Ԥ����Ϣ
					}
					aggVO.getParentVO().setAttributeValue(IIFM4TbbConst.NTBINFOFIELD, ntbException);
	
				} else if ((tpcontrolvo != null) && tpcontrolvo.isMayBeControl()) // ����
				{
					String[] infos = tpcontrolvo.getFlexibleControlInfos();
					for (int j = 0; j < infos.length; j++) {
						StringBuffer sb=new StringBuffer(infos[j]);
	//    				[,�Ƿ��ύ��������?]  10���ַ�
	    				ntbException = ntbException +sb.toString().replaceAll(IIFM4TbbConst.getFLEXMESSAGE(), "")+ "\n" ; // ����Ԥ�������Ϣ
					}
					ntbException=ntbException.trim();
					aggVO.getParentVO().setAttributeValue(IIFM4TbbConst.NTBINFOFIELD, ntbException);
	
				}
				/** ***********���Ͻ���Ԥ�����***************** */
			} catch (Exception ex) {
				if (ex instanceof SQLException) {
					throw new BusinessRuntimeException(ex.getMessage());
				} 
				else if (ex instanceof BusinessRuntimeException) {
					// ���ex�Ѿ���FIBusinessRuntimeException����ֱ���׳���
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
				/** ***********���½���Ԥ�����***************** */
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
				
				// ����Ԥ��ӿ�BO
				IFMPub4TbbServiceProxy.getBudgetControl().noCheckUpdateExe(ifmBillBusiVOs);
				
				
				/** ***********���Ͻ���Ԥ�����***************** */
			} catch (Exception ex) {
				if (ex instanceof SQLException) {
					throw new BusinessRuntimeException(ex.getMessage());
				} 
				else if (ex instanceof BusinessRuntimeException) {
					// ���ex�Ѿ���FIBusinessRuntimeException����ֱ���׳���
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
				/** ***********���½���Ԥ�����***************** */
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
				
				// ����Ԥ��ӿ�BO
				IFMPub4TbbServiceProxy.getBudgetControl().noCheckUpdateExe(ifmBillBusiVOs);
				
				
				/** ***********���Ͻ���Ԥ�����***************** */
			} catch (Exception ex) {
				if (ex instanceof SQLException) {
					throw new BusinessRuntimeException(ex.getMessage());
				} 
				else if (ex instanceof BusinessRuntimeException) {
					// ���ex�Ѿ���FIBusinessRuntimeException����ֱ���׳���
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
