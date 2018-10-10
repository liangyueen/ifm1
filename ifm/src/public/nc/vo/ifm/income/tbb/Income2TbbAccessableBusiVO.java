/*
 * @(#)Allocate2TbbAcceVO.java 2011-3-21
 * Copyright 2010 UFIDA Software CO.LTD. All rights reserved.
 */
package nc.vo.ifm.income.tbb;

import nc.itf.ifm.pub.tbb.IIFM4TbbConst;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.ifm.pub.tbb.IFMToTbbAccessableBusiVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.sf.allocateagree.IAllocateAgreeConst;
import nc.vo.tmpub.util.StringUtil;

/**
 * 投资收益-预算控制VO
 *
 */
public class Income2TbbAccessableBusiVO extends IFMToTbbAccessableBusiVO{

	/** TODO 字段说明 */
	private static final long serialVersionUID = 4192732018610773872L;


	@Override
	public String[] getAttributesValue(String[] attrs) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public String getAttributesValue(String name) {
		if(name.equals(IIFM4TbbConst.PK_ORG_3643)){
			return ((InvestIncomeVO)getParentVO()).getPk_org();
		}
		else if(name.equals(IIFM4TbbConst.PK_CURRTYPE_3643)){
			return ((InvestIncomeVO)getParentVO()).getPk_currtype();
		}
		else if(name.equals(IIFM4TbbConst.PK_FUNDPLAN_3643)){
			return ((InvestIncomeVO)getParentVO()).getIncomefundplanpro();
		}
		else if(name.equals(IIFM4TbbConst.PK_BANKACC_P_3643)){
			return ((InvestIncomeVO)getParentVO()).getIssuebank();
		}
		else if(name.equals(IIFM4TbbConst.PK_FINVAR_3643)){
			return ((InvestIncomeVO)getParentVO()).getInvestvariety();
		}
		else if(name.equals(IIFM4TbbConst.PK_BANKACC_R_3643)){
			return ((InvestIncomeVO)getParentVO()).getGathering();
		}
		
		
		return null;
	}
	
	
	@Override
	public UFDouble[] getExeData(String direction, String obj, String extObj) {
		
		UFDouble[] value = new nc.vo.pub.lang.UFDouble[4];
		UFDouble amount = UFDouble.ZERO_DBL, olcAmount = UFDouble.ZERO_DBL, glcAmount = UFDouble.ZERO_DBL,gllcAmount = UFDouble.ZERO_DBL;
		
		if(((InvestIncomeVO)getParentVO()).getBillstatus().equals(IAllocateAgreeConst.BillStatus_WAITCOMMIT)){
		    //生成时，核准数已经有了，此时可以取核准数，否则核准后也是待提交状态，此时取申请数就错
//			amount = StringUtil.toUFDoubleWithNull2O(((AllocateAgreeBVO)getChildVO()).getAgreeamount());
//			olcAmount = StringUtil.toUFDoubleWithNull2O(((AllocateAgreeBVO)getChildVO()).getAgreeolcamount());
//			glcAmount = StringUtil.toUFDoubleWithNull2O(((AllocateAgreeBVO)getChildVO()).getAgreeglcamount());
//			gllcAmount = StringUtil.toUFDoubleWithNull2O(((AllocateAgreeBVO)getChildVO()).getAgreeglcamount());
			
		}
		else{
//			if(StringUtil.isNotNull(((AllocateAgreeBVO)getChildVO()).getPayamount())){
//				try {
//					String pkOrg = StringUtil.toString(((InvestIncomeVO)getParentVO()).getPk_org());
//					String pkGroup = StringUtil.toString(((InvestIncomeVO)getParentVO()).getPk_group());
//					String pkCurrtype = StringUtil.toString(((InvestIncomeVO)getParentVO()).getPk_currtype());
//					UFDate exchangeDate = ((InvestIncomeVO)getParentVO()).getAgreedate();
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
		
		return "3643";
	}

	
	@Override
	public String getCurrency() {
		
		return ((InvestIncomeVO)getParentVO()).getPk_currtype();
	}

	
	@Override
	public String getPKOrg() {
		
		return ((InvestIncomeVO)getParentVO()).getPk_org();
	}

	
	@Override
	public String getPKGroup() {
		
		return ((InvestIncomeVO)getParentVO()).getPk_group();
	}

	
	@Override
	public String getDateType() {
		return InvestIncomeVO.GATHERINGDATE;
	}

	/**
	 * 取得全局本位币金额字段名称
	 * 
	 * @return
	 */
	protected String getGllcmoneyItemName(String obj) {
		return InvestIncomeVO.GLLMOENY;
	}

	/**
	 * 取得集团本位币金额字段名称
	 * 
	 * @return
	 */
	protected String getGlcmoneyItemName(String obj) {
		return InvestIncomeVO.GLCMOENY;
	}

	/**
	 * 取得组织本位币金额字段名称
	 * 
	 * @return
	 */
	protected String getOlcmoneyItemName(String obj) {
		return InvestIncomeVO.OLCMOENY;
	}

	/**
	 * 取得原币金额字段名称
	 * 
	 * @return
	 */
	protected String getMoneyItemName(String obj) {
		return InvestIncomeVO.ACTUALMOENY;
	}
}
