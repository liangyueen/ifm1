package nc.vo.ifm.pub.tbb;

import java.io.Serializable;
import java.util.ArrayList;

import nc.itf.ifm.pub.tbb.IIFM4TbbConst;
import nc.itf.tb.control.IBusiSysExecDataProvider;
import nc.itf.tb.control.IBusiSysNCcloudReg;
import nc.itf.tb.control.IBusiSysReg;
import nc.itf.tb.control.IDateType;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.tb.control.ControlBillType;
import nc.vo.tb.control.ControlObjectType;

/**
 * 投资理财-预算接口注册
 * 
 * @author liglt
 * @version 1.0 2018-09-26
 * @since NCC
 */
public class IFMBillBusiSysReg implements IBusiSysNCcloudReg, IBusiSysReg,
		Serializable, IDateType {


	private static final long serialVersionUID = 3850405262180087562L;

	@Override
	public String[] getDataType() {
		return new String[] { IIFM4TbbConst.ApplyApproveDateField,
				IIFM4TbbConst.IncomeAccountDateFiled, IIFM4TbbConst.RedeemAccountDateFiled};
	}

	@Override
	public String[] getDataTypeDesc() {
		return new String[] { IIFM4TbbConst.ApplyApproveDateName,
				IIFM4TbbConst.IncomeAccountDateName,
				IIFM4TbbConst.RedeemAccountDateName};
	}
	/**
	 * 根据单据类型返回应该取数的日期类型
	 */
	@Override
	public String[] getDateType(String billtype) {
		if (StringUtil.isEmptyWithTrim(billtype)
				|| billtype.equalsIgnoreCase("null")) {
			return null;
		}
		if (IIFM4TbbConst.pk_BillTypeCode_InvestApply.equalsIgnoreCase(billtype)) {// 申购审批
			return new String[] { IIFM4TbbConst.ApplyApproveDateField,InvestApplyVO.APPROVEDATE // 申购审批日期
			};
		} else if (IIFM4TbbConst.pk_BillTypeCode_InvestRedeem.equalsIgnoreCase(billtype)) {// 赎回审批
			return new String[] { IIFM4TbbConst.RedeemAccountDateFiled,InvestRedeemVO.APPROVEDATE // 到账日期
			};
		} else if (IIFM4TbbConst.pk_BillTypeCode_InvestIncome.equalsIgnoreCase(billtype)) {// 收益审批
			return new String[] { IIFM4TbbConst.IncomeAccountDateFiled,InvestIncomeVO.APPROVEDATE // 到账日期
			};
		}
		return null;
	}

	@Override
	public String getBusiSysID() {
		return IIFM4TbbConst.IFMMoudleID;
	}

	@Override
	public String getBusiSysDesc() {
		return IIFM4TbbConst.getIFMMoudelName();
	}
	
	/**
	 * 返回受控的单据类型，可设置预占执行数是否受控
	 */
	@Override
	public ArrayList<ControlBillType> getBillType() {
		ArrayList<ControlBillType> list = new ArrayList<ControlBillType>();
		/** 申购 */
		ControlBillType investApplyBillType = new ControlBillType();
		ArrayList<String> applyOrgs = new ArrayList<String>();
		applyOrgs.add("1001Z6IFMBuget000001");// 主组织对应的维度对应的ntb_id_bdcontrast.pk_obj
		investApplyBillType
				.setPk_billType(IIFM4TbbConst.pk_BillTypeId_InvestApply);
		investApplyBillType
				.setBillType_code(IIFM4TbbConst.pk_BillTypeCode_InvestApply);
		investApplyBillType
				.setBillType_name(IIFM4TbbConst.pk_BillTypeName_InvestApply);
		investApplyBillType.setReadyBillType(true);
		investApplyBillType.setRunBillType(true);
		investApplyBillType.setPk_orgs(applyOrgs);
		list.add(investApplyBillType);

		/** 赎回 */
		ControlBillType investRedeemBillType = new ControlBillType();
		ArrayList<String> redeemOrgs = new ArrayList<String>();
		redeemOrgs.add("1001Z6IFMBuget000007");// 主组织对应的维度对应的ntb_id_bdcontrast.pk_obj
		investRedeemBillType
		.setPk_billType(IIFM4TbbConst.pk_BillTypeId_InvestRedeem);
		investRedeemBillType
		.setBillType_code(IIFM4TbbConst.pk_BillTypeCode_InvestRedeem);
		investRedeemBillType
		.setBillType_name(IIFM4TbbConst.pk_BillTypeName_InvestRedeem);
		investRedeemBillType.setReadyBillType(true);
		investRedeemBillType.setRunBillType(true);
		investRedeemBillType.setPk_orgs(redeemOrgs);
		list.add(investRedeemBillType);
		
		/** 收益 */
		ControlBillType investIncomeBillType = new ControlBillType();
		ArrayList<String> incomeOrgs = new ArrayList<String>();
		incomeOrgs.add("1001Z6IFMBuget000013");// 主组织对应的维度对应的ntb_id_bdcontrast.pk_obj
		investIncomeBillType
		.setPk_billType(IIFM4TbbConst.pk_BillTypeId_InvestIncome);
//		investIncomeBillType
//		.setPk_billType(IIFM4TbbConst.pk_BillTypeCode_InvestIncome);// 将billtypeID更换为billtypeCode
		investIncomeBillType
		.setBillType_code(IIFM4TbbConst.pk_BillTypeCode_InvestIncome);
		investIncomeBillType
		.setBillType_name(IIFM4TbbConst.pk_BillTypeName_InvestIncome);
		investIncomeBillType.setReadyBillType(true);
		investIncomeBillType.setRunBillType(true);
		investIncomeBillType.setPk_orgs(incomeOrgs);
		list.add(investIncomeBillType);
		

		return list;
	}

	@Override
	public String[] getBusiType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSupportMutiSelect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getMainPkOrg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getBusiTypeDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getControlableDirections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getControlableDirectionsDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ControlObjectType> getControlableObjects() {
		ArrayList<ControlObjectType> list = new ArrayList<ControlObjectType>();
		/*ControlObjectType obj1 = new ControlObjectType("Z6SFTBALLOCATEAMOUNT",
				IIFM4TbbConst.AmountFiled, IIFM4TbbConst.getAmountName());

		list.add(obj1);

		ControlObjectType obj3 = new ControlObjectType("Z6SFTBBAGREEAMOUNT00",
				IIFM4TbbConst.AgreeAmountFiled,
				IIFM4TbbConst.getAgreeAmountName());

		list.add(obj3);*/

		return list;
	}
	
	/**
	 * 根据单据类型返回单据上的金额字段名称
	 */
	@Override
	public ArrayList<ControlObjectType> getControlableObjects(String billtype) {
		ArrayList<ControlObjectType> list = new ArrayList<ControlObjectType>();
		if (IIFM4TbbConst.pk_BillTypeCode_InvestApply.equalsIgnoreCase(billtype)) {
			ControlObjectType applymoney = new ControlObjectType(
					"Z6IFMTBAPPLLYMONEY00", IIFM4TbbConst.ApplyMoneyField,
					IIFM4TbbConst.ApplyMoneyName);
			list.add(applymoney);
		} else if (IIFM4TbbConst.pk_BillTypeCode_InvestRedeem.equalsIgnoreCase(billtype)) {

			ControlObjectType redeemmoney = new ControlObjectType(
					"Z6IFMTBREDEEMMONEY00", IIFM4TbbConst.RedeemMoneyFiled,
					IIFM4TbbConst.RedeemMoneyName);
			list.add(redeemmoney);
		} else {
			ControlObjectType incomemoney = new ControlObjectType(
					"Z6IFMTBINCOMEMONEY00", IIFM4TbbConst.IncomeActualMoneyFiled,
					IIFM4TbbConst.IncomeActualMoneyName);
			list.add(incomemoney);
		}
		return list;
	}
	
	/**
	 * 返回取数类
	 */
	@Override
	public IBusiSysExecDataProvider getExecDataProvider() {
		return (IBusiSysExecDataProvider) nc.bs.framework.core.util.ObjectCreator
				.newInstance("ifm", "nc.bs.ifm.pub.tbb.IFMBillExecDataProvider");
	}

	@Override
	public boolean isBudgetControling() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isUseAccountDate(String billtype) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getLinkUrl(String billtype) {
		/*String url = null;
		switch (billtype) {
		case "36K4":
			url = "/nccloud/resources/sf/delivery/delivery/main/index.html#/listlinkq";
		case "36K2":
			url = "/nccloud/resources/sf/allocation/allocate/main/index.html#/list";
		}*/

		return null;
	}

	@Override
	public boolean getShowModel(String billtype) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAppcode(String billtype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPagecode(String billtype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBilltype(String billtype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAppType(String billtype) {
		// TODO Auto-generated method stub
		return "2";
	}

}
