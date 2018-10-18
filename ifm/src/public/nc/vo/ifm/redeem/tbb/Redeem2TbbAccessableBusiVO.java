/*
 * @(#)Allocate2TbbAcceVO.java 2011-3-21
 * Copyright 2010 UFIDA Software CO.LTD. All rights reserved.
 */
package nc.vo.ifm.redeem.tbb;

import nc.itf.ifm.pub.tbb.IIFM4TbbConst;
import nc.vo.ifm.pub.tbb.IFMToTbbAccessableBusiVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.sf.allocateagree.AllocateAgreeBVO;
import nc.vo.sf.allocateagree.IAllocateAgreeConst;
import nc.vo.tmpub.util.StringUtil;

/**
 * Ͷ������-Ԥ�����VO
 *
 * @author  chengfei
 * @version 1.0 2011-3-21
 * @since   NC6.0
 */
public class Redeem2TbbAccessableBusiVO extends IFMToTbbAccessableBusiVO{

	/** TODO �ֶ�˵�� */
	private static final long serialVersionUID = 4192732018610773872L;


	@Override
	public String[] getAttributesValue(String[] attrs) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String getAttributesValue(String name) {
		if(name.equals(IIFM4TbbConst.PK_ORG_3642)){
			return ((InvestRedeemVO)getParentVO()).getPk_org();
		}
		else if(name.equals(IIFM4TbbConst.PK_CURRTYPE_3642)){
			return ((InvestRedeemVO)getParentVO()).getPk_currtype();
		}
//		else if(name.equals(IIFM4TbbConst.PK_FUNDPLAN_3642)){
//			return ((InvestRedeemVO)getParentVO()).getCapitalproject();//�ʽ�ƻ���Ŀ
//		}
		else if(name.equals(IIFM4TbbConst.PK_BANKACC_P_3642)){
			return ((InvestRedeemVO)getParentVO()).getIssuebank();
		}
		else if(name.equals(IIFM4TbbConst.PK_FINVAR_3642)){
			return ((InvestRedeemVO)getParentVO()).getInvestvariety();//Ͷ��Ʒ��
		}
		else if(name.equals(IIFM4TbbConst.PK_BANKACC_R_3642)){
			return ((InvestRedeemVO)getParentVO()).getGatheringaccount();//�տ��˻�
		}
		
		
		return null;
	}
	
	
	/**
	 * ����׼�ĵ��ݰ���������Ԥռ
	 * ��׼֮��ĵ��ݰ����������-���²����Ԥռ
	 * �����²����Ľ����²���Ԥռ�����²�����˺�س�Ԥռ
	 */
	@Override
	public UFDouble[] getExeData(String direction, String obj, String extObj) {
		
		UFDouble[] value = new nc.vo.pub.lang.UFDouble[4];
		UFDouble amount = UFDouble.ZERO_DBL, olcAmount = UFDouble.ZERO_DBL, glcAmount = UFDouble.ZERO_DBL,gllcAmount = UFDouble.ZERO_DBL;
		
		if(((InvestRedeemVO)getParentVO()).getBillstatus().equals(IAllocateAgreeConst.BillStatus_WAITCOMMIT)){
		    //����ʱ����׼���Ѿ����ˣ���ʱ����ȡ��׼���������׼��Ҳ�Ǵ��ύ״̬����ʱȡ�������ʹ�
//			amount = StringUtil.toUFDoubleWithNull2O(((AllocateAgreeBVO)getChildVO()).getApplyamount());
//			olcAmount = StringUtil.toUFDoubleWithNull2O(((AllocateAgreeBVO)getChildVO()).getApplyolcamount());
//			glcAmount = StringUtil.toUFDoubleWithNull2O(((AllocateAgreeBVO)getChildVO()).getApplyglcamount());
//			gllcAmount = StringUtil.toUFDoubleWithNull2O(((AllocateAgreeBVO)getChildVO()).getApplygllcamount());
			amount = StringUtil.toUFDoubleWithNull2O(((AllocateAgreeBVO)getChildVO()).getAgreeamount());
			olcAmount = StringUtil.toUFDoubleWithNull2O(((AllocateAgreeBVO)getChildVO()).getAgreeolcamount());
			glcAmount = StringUtil.toUFDoubleWithNull2O(((AllocateAgreeBVO)getChildVO()).getAgreeglcamount());
			gllcAmount = StringUtil.toUFDoubleWithNull2O(((AllocateAgreeBVO)getChildVO()).getAgreeglcamount());
			
		}
		else{
//			if(StringUtil.isNotNull(((AllocateAgreeBVO)getChildVO()).getPayamount())){
//				try {
//					String pkOrg = StringUtil.toString(((InvestRedeemVO)getParentVO()).getPk_org());
//					String pkGroup = StringUtil.toString(((InvestRedeemVO)getParentVO()).getPk_group());
//					String pkCurrtype = StringUtil.toString(((InvestRedeemVO)getParentVO()).getPk_currtype());
//					UFDate exchangeDate = ((InvestRedeemVO)getParentVO()).getAgreedate();
//					UFDouble currOlcrate = ((AllocateAgreeBVO)getChildVO()).getOlcrate();
//					UFDouble currGlcrate = ((AllocateAgreeBVO)getChildVO()).getGlcrate();
//					UFDouble currGllcrate = ((AllocateAgreeBVO)getChildVO()).getGllcrate();
//	
//					amount = ((AllocateAgreeBVO)getChildVO()).getAgreeamount().sub(((AllocateAgreeBVO)getChildVO()).getPayamount());
//					olcAmount = TMCurrencyUtil.getOrgLocalMoney(pkOrg, pkCurrtype, amount, currOlcrate, exchangeDate);
//					glcAmount = TMCurrencyUtil.getGroupLocalMoney
//	    						(pkGroup, pkOrg, pkCurrtype, amount, currGlcrate,currOlcrate, exchangeDate);
//					gllcAmount = TMCurrencyUtil.getGlobalLocalMoney
//	    						(pkOrg, pkCurrtype, amount, currGllcrate, currOlcrate, exchangeDate);
//				} catch (BusinessException e1) {
//					Logger.error(e1.getMessage(), e1);
//					throw new BusinessRuntimeException(e1.getMessage());
//				}
//			}
//			else{
//				amount = StringUtil.toUFDoubleWithNull2O(((AllocateAgreeBVO)getChildVO()).getAgreeamount());
//				olcAmount = StringUtil.toUFDoubleWithNull2O(((AllocateAgreeBVO)getChildVO()).getAgreeolcamount());
//				glcAmount = StringUtil.toUFDoubleWithNull2O(((AllocateAgreeBVO)getChildVO()).getAgreeglcamount());
//				gllcAmount = StringUtil.toUFDoubleWithNull2O(((AllocateAgreeBVO)getChildVO()).getAgreeglcamount());
//			}
			
		}
		if(getDirection() < 0){
	        value[0] = gllcAmount.multiply(-1);
	    	value[1] = glcAmount.multiply(-1);
	    	value[2] = olcAmount.multiply(-1);
	    	value[3] = amount.multiply(-1);
	    }
	    else{
	        value[0] = gllcAmount;
	    	value[1] = glcAmount;
	    	value[2] = olcAmount;
	    	value[3] = amount;
	    }
			
		
	
		return value;
	}
	
	@Override
	public String getBillType() {
		
		return "3642";
	}

	
	@Override
	public String getCurrency() {
		
		return ((InvestRedeemVO)getParentVO()).getPk_currtype();
	}

	
	@Override
	public String getPKOrg() {
		
		return ((InvestRedeemVO)getParentVO()).getPk_org();
	}

	
	@Override
	public String getPKGroup() {
		
		return ((InvestRedeemVO)getParentVO()).getPk_group();
	}

	
	@Override
	public String getDateType() {
		// TODO Auto-generated method stub
		return null;
	}

}
