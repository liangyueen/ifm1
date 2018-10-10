/*
 * @(#)IFMTbbBusiVOFactory.java 2011-3-24
 * Copyright 2010 UFIDA Software CO.LTD. All rights reserved.
 */
package nc.vo.ifm.pub.tbb;

import nc.itf.ifm.pub.tbb.IIFM4TbbConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValidationException;

/**
 * 投资理财-预算控制VO工厂类
 *
 * @author  chengfei
 * @version 1.0 2011-3-24
 * @since   NC6.0
 */
public class IFMTbbBusiVOFactory {
	private static IFMTbbBusiVOFactory instance = new IFMTbbBusiVOFactory();

	public static IFMTbbBusiVOFactory getInstance() {
		return IFMTbbBusiVOFactory.instance;
	}

	public IFMToTbbAccessableBusiVO chgToIFMBusiVO(String billtype)
			throws ValidationException, BusinessException {


		IFMToTbbAccessableBusiVO busiVO = null;


		try {
			if(billtype.equals(IIFM4TbbConst.pk_BillTypeCode_InvestApply)){
				busiVO = (IFMToTbbAccessableBusiVO) Class.forName("nc.vo.ifm.apply.tbb.Apply2TbbAccessableBusiVO").newInstance();
			}
			else if(billtype.equals(IIFM4TbbConst.pk_BillTypeCode_InvestIncome)){
				busiVO = (IFMToTbbAccessableBusiVO) Class.forName("nc.vo.ifm.income.tbb.Income2TbbAccessableBusiVO").newInstance();
			}
			else if(billtype.equals(IIFM4TbbConst.pk_BillTypeCode_InvestRedeem)){
				busiVO = (IFMToTbbAccessableBusiVO) Class.forName("nc.vo.ifm.redeem.tbb.Redeem2TbbAccessableBusiVO").newInstance();
			}

		}
		catch (Exception e) {
			//e.printStackTrace();
		}
		/*catch (InstantiationException e) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3632pub_0","03632pub-1001",null,new String[]{getBillTypeName(billtype)})@res "预算取数类");
		}
		catch (IllegalAccessException e) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3632pub_0","03632pub-1001",null,new String[]{getBillTypeName(billtype)})@res "预算取数类");
		}
		catch (ClassNotFoundException e) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3632pub_0","03632pub-1001",null,new String[]{getBillTypeName(billtype)})@res "预算取数类");
		}*/


		return busiVO;
	}

	private String getBillTypeName(String pk_BillType){
		if(pk_BillType.equals(IIFM4TbbConst.pk_BillTypeCode_InvestApply)){
			return IIFM4TbbConst.pk_BillTypeName_InvestApply;
		}
		else if(pk_BillType.equals(IIFM4TbbConst.pk_BillTypeCode_InvestIncome)){
			return IIFM4TbbConst.pk_BillTypeName_InvestIncome;
		}
		else if(pk_BillType.equals(IIFM4TbbConst.pk_BillTypeCode_InvestRedeem)){
			return IIFM4TbbConst.pk_BillTypeName_InvestRedeem;
		}
		else{
			return "";
		}
	}
}