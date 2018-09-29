package nc.vo.ifm.util;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.pf.pub.PfDataCache;
import nc.itf.cdm.pub.CDMPublicServiceProxy;
import nc.pubitf.bbd.CurrtypeQuery;
import nc.pubitf.fip.service.IFipMessageService;
import nc.pubitf.fip.service.IFipRelationQueryService;
import nc.vo.bill.pub.MiscUtil;
import nc.vo.fip.service.FipMessageVO;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.trade.pub.IBillStatus;


/**
 * <p>
 * ��֤������<br>
 *
 * @author lyen
 */
public class VoucherUtil {

	/**
	 * ���ͻ��ƽ̨����
	 * <p>
	 *
	 * @author LYE
	 * @version 1.0 2018-09-26
	 * @since NC6.0
	 */
	public static AggregatedValueObject sendDAPMessge(AggregatedValueObject bill)
			throws BusinessException {
		SuperVO headvo = (SuperVO) bill.getParentVO();
		UFBoolean isinitial = (UFBoolean) headvo.getAttributeValue("isinitial");
		if(isinitial!=null&&UFBoolean.TRUE.equals(isinitial)){
			return null;
		}
//		FbmPublicUtil.lockFbmbillno(new String[]{(String)headvo .getAttributeValue("vbillno")});
		CircularlyAccessibleValueObject[] childrenvos=bill.getChildrenVO();
		UFDate dvoucherdate= ((UFDate)headvo.getAttributeValue("dvoucherdate"));
		UFDate dapprovedate= ((UFDate)headvo.getAttributeValue("approvedate"));
//		bill=(AggregatedValueObject) FbmPrivateUtil.queryAggvoByPK(bill);
		headvo = (SuperVO) bill.getParentVO();
		UFBoolean isvoucher=(UFBoolean)headvo.getAttributeValue("voucherflag");
		String pk_tradetype = (String)headvo.getAttributeValue("pk_billtypecode");
//		if(isvoucher!=null&&isvoucher.booleanValue()){
//			throw new BusinessException("�õ����Ѿ��������޸ģ���ˢ�½��棬����ҵ��");
//		}
		Integer vbillstatus=(Integer)headvo.getAttributeValue("vbillstatus");
		if(vbillstatus!=null&&vbillstatus.intValue()!=IBillStatus.CHECKPASS&&!pk_tradetype.equalsIgnoreCase(TMIFMConst.CONST_BILLTYPE_REDEEM)
				&&!pk_tradetype.equalsIgnoreCase(TMIFMConst.CONST_BILLTYPE_APPLY)&&!pk_tradetype.equalsIgnoreCase(TMIFMConst.CONST_BILLTYPE_INCOME)){
//			throw new BusinessException("�õ����Ѿ��������޸ģ���ˢ�½��棬����ҵ��");
			return null;
		}
		UFDate effectdate =(dvoucherdate==null?dapprovedate:dvoucherdate);
		//�жϻ��ƽ̨�Ƿ�����
		if (IFMPublicUtil.isFIPEnable((String) bill.getParentVO().getAttributeValue("pk_group"))) {

			if(StringUtil.isEmptyWithTrim(pk_tradetype)){
				pk_tradetype=TMIFMConst.CONST_BILLTYPE_REDEEM;
			}			
			FipRelationInfoVO reVO = new FipRelationInfoVO();
			reVO.setPk_group((String)headvo.getAttributeValue("pk_group"));
			reVO.setPk_org((String)headvo.getAttributeValue("pk_org"));
			reVO.setRelationID(headvo.getPrimaryKey());
			reVO.setPk_system(PfDataCache.getBillType(pk_tradetype).getSystemcode());
			reVO.setBusidate(effectdate == null ? new UFDate() : effectdate);
			reVO.setPk_billtype(pk_tradetype);
			reVO.setPk_operator(InvocationInfoProxy.getInstance().getUserId());
			String freedef1=(String)headvo.getAttributeValue("vbillno");
			reVO.setFreedef1(freedef1);
			String note=headvo.getAttributeValue("remark")==null?
					(String)headvo.getAttributeValue("note"):(String)headvo.getAttributeValue("remark");
		    //20111102�Ѿ�֪ͨgbh������ͳһȥ����������󳤶���200������ȥͳһ����λ
			if(!nc.vo.jcom.lang.StringUtil.isEmptyWithTrim(note)){
		  		reVO.setFreedef2(note);
			}			
			//��ֹydx20120717�ſչ�ڵ�����£���������������������֤������
			FipRelationInfoVO[] desvos = getFipRelationQueryService().queryDesBill(reVO);
			if(desvos!=null){
				return bill;
			}		
			
			String pk_curr=(String)headvo.getAttributeValue("pk_currtype");
			if(StringUtil.isEmptyWithTrim(pk_curr)){
				pk_curr="1002Z0100000000001K1";
			}
			int currdigit = 2;
			currdigit = CurrtypeQuery.getInstance().getCurrdigit(pk_curr);
			UFDouble sendmoney=UFDouble.ZERO_DBL;
			if(headvo.getAttributeValue("redeemmoney")!=null){
				sendmoney=(UFDouble)(headvo.getAttributeValue("redeemmoney"));
			}else{
				sendmoney=new UFDouble(300);
			}
			sendmoney=sendmoney.setScale(currdigit, UFDouble.ROUND_HALF_UP);
			reVO.setFreedef3(sendmoney.toString());
			reVO.setFreedef4("vuserdef4");
			reVO.setFreedef5("vuserdef5");
			
			//������֤�ˣ���¼�û�
			headvo.setAttributeValue("vvoucherid", InvocationInfoProxy.getInstance().getUserId());
			//������֤���ڣ�ҵ������
			headvo.setAttributeValue("dvoucherdate", effectdate);
			//������֤��־������֤
			headvo.setAttributeValue("voucherflag", UFBoolean.TRUE);
			headvo.setStatus(VOStatus.UPDATED);
			FipMessageVO messageVO = new FipMessageVO();
			messageVO.setBillVO(headvo);
			messageVO.setMessagetype(FipMessageVO.MESSAGETYPE_ADD);
			messageVO.setMessageinfo(reVO);
//			AggregatedValueObject retaggvo=(AggregatedValueObject) FbmPrivateUtil.updateAggVO(bill, VOStatus.UPDATED);
			getFipMessageService().sendMessage(messageVO);
			return bill;
		}else{
			return bill;
		}

	}
	 public static IFipMessageService getFipMessageService()
	  {
	    return (IFipMessageService)NCLocator.getInstance().lookup(IFipMessageService.class.getName());
	  }
	 public static IFipRelationQueryService getFipRelationQueryService()
	    {
	     return (IFipRelationQueryService)NCLocator.getInstance().lookup(IFipRelationQueryService.class.getName());
	   }
	/**
	 * ���ͻ��ƽ̨����
	 * <p>
	 *
	 * @author LYE
	 * @version 1.0 2018-09-26
	 * @since NC6.0
	 */
	public static AggregatedValueObject sendDAPMessge_del(AggregatedValueObject bill)
			throws BusinessException {
		SuperVO headvo = (SuperVO) bill.getParentVO();
//		FbmPublicUtil.lockFbmbillno(new String[]{(String)headvo .getAttributeValue("vbillno")});
		CircularlyAccessibleValueObject[] childrenvos=bill.getChildrenVO();
		UFDate dvoucherdate= ((UFDate)headvo.getAttributeValue("dvoucherdate"));
		UFDate dapprovedate= ((UFDate)headvo.getAttributeValue("approvedate"));
//		bill=(AggregatedValueObject) FbmPrivateUtil.queryAggvoByPK(bill);
		headvo = (SuperVO) bill.getParentVO();
		UFBoolean isvoucher=(UFBoolean)headvo.getAttributeValue("voucherflag");
//		if(isvoucher!=null&&!isvoucher.booleanValue()){
//			return null;
//			throw new BusinessException("�õ����Ѿ��������޸ģ���ˢ�½��棬����ҵ��");
//		}
		Integer vbillstatus=(Integer)headvo.getAttributeValue("vbillstatus");
//		if(vbillstatus!=null&&vbillstatus.intValue()!=IBillStatus.CHECKPASS){
//			return null;
////			throw new BusinessException("�õ����Ѿ��������޸ģ���ˢ�½��棬����ҵ��");
//		}
		UFDate effectdate =(dvoucherdate==null?dapprovedate:dvoucherdate);
		//�жϻ��ƽ̨�Ƿ�����
		if (IFMPublicUtil.isFIPEnable((String) bill.getParentVO().getAttributeValue("pk_group"))) {
			String pk_tradetype = (String)headvo.getAttributeValue("pk_billtypecode");
			if(StringUtil.isEmptyWithTrim(pk_tradetype)){
				pk_tradetype=TMIFMConst.CONST_BILLTYPE_REDEEM;
			}
			FipRelationInfoVO reVO = new FipRelationInfoVO();
			reVO.setPk_group((String)headvo.getAttributeValue("pk_group"));
			reVO.setPk_org((String)headvo.getAttributeValue("pk_org"));
			reVO.setRelationID(headvo.getPrimaryKey());
			reVO.setPk_system(PfDataCache.getBillType(pk_tradetype).getSystemcode());
			reVO.setBusidate(effectdate == null ? new UFDate() : effectdate);
			reVO.setPk_billtype(pk_tradetype);
			reVO.setPk_operator(InvocationInfoProxy.getInstance().getUserId());
			String freedef1=(String)headvo.getAttributeValue("vbillno");
			reVO.setFreedef1(freedef1);
			String note=headvo.getAttributeValue("remark")==null?
					(String)headvo.getAttributeValue("note"):(String)headvo.getAttributeValue("remark");
		    //20111102�Ѿ�֪ͨgbh������ͳһȥ����������󳤶���200������ȥͳһ����λ
			if(!nc.vo.jcom.lang.StringUtil.isEmptyWithTrim(note)){
		  		reVO.setFreedef2(note);
			}
			String pk_curr=(String)headvo.getAttributeValue("pk_currtype");
			if(StringUtil.isEmptyWithTrim(pk_curr)){
				pk_curr="1002Z0100000000001K1";
			}
			int currdigit = 2;
			currdigit = CurrtypeQuery.getInstance().getCurrdigit(pk_curr);
			UFDouble sendmoney=UFDouble.ZERO_DBL;
			if(headvo.getAttributeValue("redeemmoney")!=null){
				sendmoney=(UFDouble)(headvo.getAttributeValue("redeemmoney"));
			}else{
				sendmoney=new UFDouble(300);
			}
			sendmoney=sendmoney.setScale(currdigit, UFDouble.ROUND_HALF_UP);
			reVO.setFreedef3(sendmoney.toString());
			reVO.setFreedef4("vuserdef4");
			reVO.setFreedef5("vuserdef5");
			FipMessageVO messageVO = new FipMessageVO();
			if(childrenvos==null||childrenvos.length==0){
				messageVO.setBillVO(headvo);
			}else{
				messageVO.setBillVO(bill);
			}

			messageVO.setMessagetype(FipMessageVO.MESSAGETYPE_DEL);
			messageVO.setMessageinfo(reVO);
			//������֤�ˣ���¼�û�
			headvo.setAttributeValue("vvoucherid", null);
			//������֤���ڣ�ҵ������
			headvo.setAttributeValue("dvoucherdate", null);
			//������֤��־������֤
			headvo.setAttributeValue("voucherflag", UFBoolean.FALSE);
			headvo.setStatus(VOStatus.UPDATED);
//			AggregatedValueObject retaggvo=(AggregatedValueObject) FbmPrivateUtil.updateAggVO(bill, VOStatus.UPDATED);
			getFipMessageService().sendMessage(messageVO);
			return bill;
		}else{
			return bill;
		}

	}
}