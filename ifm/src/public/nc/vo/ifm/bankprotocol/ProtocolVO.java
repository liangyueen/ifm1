package nc.vo.ifm.bankprotocol;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 * 此处添加累的描述信息
 * </p>
 * 创建日期:2018-9-4
 * 
 * @author YONYOU NC
 * @version NCPrj ??
 */

public class ProtocolVO extends SuperVO {

	public static final String PK_PROTOCOL = "pk_protocol";
	public static final String PK_GROUP = "pk_group";
	public static final String PK_ORG = "pk_org";
	public static final String PK_ORG_V = "pk_org_v";
	public static final String CONTROLMETHOD = "controlmethod";
	public static final String PROTOCOLCODE = "protocolcode";
	public static final String PROTOCOLTYPE = "protocoltype";
	public static final String VERSIONNO = "versionno";
	public static final String PROTOCOLSTATUS = "protocolstatus";
	public static final String PK_CREDITBANK = "pk_creditbank";
	public static final String ISINHERIT = "isinherit";
	public static final String PK_CURRTYPE = "pk_currtype";
	public static final String USETYPE = "usetype";
	public static final String REMARK = "remark";
	public static final String CREDITER = "crediter";
	public static final String CREATIONTIME = "creationtime";
	public static final String CREATOR = "creator";
	public static final String MODIFIEDTIME = "modifiedtime";
	public static final String MODIFIER = "modifier";
	public static final String VBILLSTATUS = "vbillstatus";
	public static final String APPROVER = "approver";
	public static final String APPROVEDATE = "approvedate";
	public static final String APPROVENOTE = "approvenote";
	public static final String FROZENDATE = "frozendate";
	public static final String FROZENER = "frozener";
	public static final String TERMINATOR = "terminator";
	public static final String TERMINATEDATE = "terminatedate";
	public static final String VDEF1 = "vdef1";
	public static final String VDEF2 = "vdef2";
	public static final String VDEF3 = "vdef3";
	public static final String VDEF4 = "vdef4";
	public static final String VDEF5 = "vdef5";
	public static final String VDEF6 = "vdef6";
	public static final String VDEF7 = "vdef7";
	public static final String VDEF8 = "vdef8";
	public static final String VDEF9 = "vdef9";
	public static final String VDEF10 = "vdef10";
	public static final String VDEF11 = "vdef11";
	public static final String VDEF12 = "vdef12";
	public static final String VDEF13 = "vdef13";
	public static final String VDEF14 = "vdef14";
	public static final String VDEF15 = "vdef15";
	public static final String VDEF16 = "vdef16";
	public static final String VDEF17 = "vdef17";
	public static final String VDEF18 = "vdef18";
	public static final String VDEF19 = "vdef19";
	public static final String VDEF20 = "vdef20";
	public static final String VDEF21 = "vdef21";
	public static final String VDEF22 = "vdef22";
	public static final String VDEF23 = "vdef23";
	public static final String VDEF24 = "vdef24";
	public static final String VDEF25 = "vdef25";
	public static final String VDEF26 = "vdef26";
	public static final String VDEF27 = "vdef27";
	public static final String VDEF28 = "vdef28";
	public static final String VDEF29 = "vdef29";
	public static final String VDEF30 = "vdef30";
	public static final String OLCRATE = "olcrate";
	public static final String GLCRATE = "glcrate";
	public static final String GLLCRATE = "gllcrate";
	public static final String MONEY = "money";
	public static final String OLCMONEY = "olcmoney";
	public static final String GLCMONEY = "glcmoney";
	public static final String GLLMONEY = "gllmoney";
	public static final String PERIODCOUNT = "periodcount";
	public static final String PERIODUNIT = "periodunit";
	public static final String BEGINDATE = "begindate";
	public static final String ENDDATE = "enddate";
	public static final String GUARANTEETYPE = "guaranteetype";
	public static final String ACTUALENDDATE = "actualenddate";
	public static final String PK_BILLTYPECODE = "pk_billtypecode";
	public static final String PK_BILLTYPEID = "pk_billtypeid";

	public static final String BILLMAKER = "billmaker";
	public static final String BILLMAKEDATE = "billmakedate";
	public static final String BILLMAKETIME = "billmaketime";
	public static final String VERSIONTIME = "versiontime";
	public static final String INHERITPROTOCOL = "inheritprotocol";
	public static final String PK_FUNDPLAN = "pk_fundplan";
	public static final String CREDITTYPECONTRAL = "credittypecontral";
	public static final String CREDITBANKCONTRAL = "creditbankcontral";
	public static final String CREDITUNITCONTRAL = "creditunitcontral";
	
	public nc.vo.pub.lang.UFDate getActualenddate() {
		return (nc.vo.pub.lang.UFDate) getAttributeValue("actualenddate");
	}
	public void setActualenddate(nc.vo.pub.lang.UFDate actualenddate) {
		setAttributeValue("actualenddate", actualenddate);
	}
	
	public nc.vo.pub.lang.UFBoolean getCreditunitcontral() {
		return (nc.vo.pub.lang.UFBoolean) getAttributeValue("creditunitcontral");
	}

	public void setCreditunitcontral(nc.vo.pub.lang.UFBoolean creditunitcontral) {
		setAttributeValue("creditunitcontral", creditunitcontral);
	}
	
	public nc.vo.pub.lang.UFBoolean getCreditbankcontral() {
		return (nc.vo.pub.lang.UFBoolean) getAttributeValue("creditbankcontral");
	}

	public void setCreditbankcontrall(nc.vo.pub.lang.UFBoolean creditbankcontral) {
		setAttributeValue("creditbankcontral", creditbankcontral);
	}

	public nc.vo.pub.lang.UFBoolean getCredittypecontral() {
		return (nc.vo.pub.lang.UFBoolean) getAttributeValue("credittypecontral");
	}

	public void setCredittypecontral(nc.vo.pub.lang.UFBoolean credittypecontral) {
		setAttributeValue("credittypecontral", credittypecontral);
	}
	public java.lang.String getUsetype() {
		return (java.lang.String) getAttributeValue("usetype");
	}

	public void getUsetype(java.lang.String newValue) {
		setAttributeValue("usetype", newValue);
	}
	/**
	 * 属性inheritprotocol的Getter方法.属性名：继承授信协议 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getInheritprotocol() {
		return (java.lang.String) getAttributeValue("inheritprotocol");
	}

	/**
	 * 属性inheritprotocol的Setter方法.属性名：继承授信协议 创建日期:
	 * 
	 * @param newInheritprotocol
	 *            java.lang.String
	 */
	public void setInheritprotocol(java.lang.String newInheritprotocol) {
		setAttributeValue("inheritprotocol", newInheritprotocol);
	}

	/**
	 * 属性pk_protocol的Getter方法.属性名：协议主键 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_protocol() {
		return (java.lang.String) getAttributeValue("pk_protocol");
	}

	/**
	 * 属性pk_bankprotocol的Setter方法.属性名：协议主键 创建日期:
	 * 
	 * @param newPk_bankprotocol
	 *            java.lang.String
	 */
	public void setPk_protocol(java.lang.String newPk_protocol) {
		setAttributeValue("pk_protocol", newPk_protocol);
	}

	/**
	 * 属性pk_group的Getter方法.属性名：集团 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_group() {
		return (java.lang.String) getAttributeValue("pk_group");
	}

	/**
	 * 属性pk_group的Setter方法.属性名：集团 创建日期:
	 * 
	 * @param newPk_group
	 *            java.lang.String
	 */
	public void setPk_group(java.lang.String newPk_group) {
		setAttributeValue("pk_group", newPk_group);
	}

	/**
	 * 属性pk_org的Getter方法.属性名：财务组织 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org() {
		return (java.lang.String) getAttributeValue("pk_org");
	}

	/**
	 * 属性pk_org的Setter方法.属性名：财务组织 创建日期:
	 * 
	 * @param newPk_org
	 *            java.lang.String
	 */
	public void setPk_org(java.lang.String newPk_org) {
		setAttributeValue("pk_org", newPk_org);
	}

	/**
	 * 属性pk_org_v的Getter方法.属性名：财务组织版本 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org_v() {
		return (java.lang.String) getAttributeValue("pk_org_v");
	}

	/**
	 * 属性pk_org_v的Setter方法.属性名：财务组织版本 创建日期:
	 * 
	 * @param newPk_org_v
	 *            java.lang.String
	 */
	public void setPk_org_v(java.lang.String newPk_org_v) {
		setAttributeValue("pk_org_v", newPk_org_v);
	}

	/**
	 * 属性ctrlmethod的Getter方法.属性名：控制方式 创建日期:
	 * 
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getControlmethod() {
		return (java.lang.Integer) getAttributeValue("controlmethod");
	}

	/**
	 * 属性controlmethod的Setter方法.属性名：控制方式 创建日期:
	 * 
	 * @param newCtrlmethod
	 *            java.lang.Integer
	 */
	public void setControlmethod(java.lang.Integer newCtrlmethod) {
		setAttributeValue("controlmethod", newCtrlmethod);
	}

	/**
	 * 属性protocolcode的Getter方法.属性名：协议编号 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getProtocolcode() {
		return (java.lang.String) getAttributeValue("protocolcode");
	}

	/**
	 * 属性protocolcode的Setter方法.属性名：协议编号 创建日期:
	 * 
	 * @param newProtocolcode
	 *            java.lang.String
	 */
	public void setProtocolcode(java.lang.String newProtocolcode) {
		setAttributeValue("protocolcode", newProtocolcode);
	}

	/**
	 * 属性protocoltype的Getter方法.属性名：协议类型 创建日期:
	 * 
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getProtocoltype() {
		return (java.lang.Integer) getAttributeValue("protocoltype");
	}

	/**
	 * 属性protocoltype的Setter方法.属性名：协议类型 创建日期:
	 * 
	 * @param newProtocoltype
	 *            java.lang.Integer
	 */
	public void setProtocoltype(java.lang.Integer newProtocoltype) {
		setAttributeValue("protocoltype", newProtocoltype);
	}

	/**
	 * 属性versionno的Getter方法.属性名：版本号 创建日期:
	 * 
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getVersionno() {
		return (java.lang.Integer) getAttributeValue("versionno");
	}

	/**
	 * 属性versionno的Setter方法.属性名：版本号 创建日期:
	 * 
	 * @param newVersionno
	 *            java.lang.Integer
	 */
	public void setVersionno(java.lang.Integer newVersionno) {
		setAttributeValue("versionno", newVersionno);
	}

	/**
	 * 属性pk_version的Getter方法.属性名：版本主键 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_version() {
		return (java.lang.String) getAttributeValue("pk_version");
	}

	/**
	 * 属性pk_version的Setter方法.属性名：版本主键 创建日期:
	 * 
	 * @param newPk_version
	 *            java.lang.String
	 */
	public void setPk_version(java.lang.String newPk_version) {
		setAttributeValue("pk_version", newPk_version);
	}

	/**
	 * 属性protocolstatus的Getter方法.属性名：协议状态 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getProtocolstatus() {
		return (java.lang.String) getAttributeValue("protocolstatus");
	}

	/**
	 * 属性protocolstatus的Setter方法.属性名：协议状态 创建日期:
	 * 
	 * @param newProtocolstatus
	 *            java.lang.String
	 */
	public void setProtocolstatus(java.lang.String newProtocolstatus) {
		setAttributeValue("protocolstatus", newProtocolstatus);
	}

	/**
	 * 属性pk_creditbank的Getter方法.属性名：授信银行 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_creditbank() {
		return (java.lang.String) getAttributeValue("pk_creditbank");
	}

	/**
	 * 属性pk_creditbank的Setter方法.属性名：授信银行 创建日期:
	 * 
	 * @param newPk_creditbank
	 *            java.lang.String
	 */
	public void setPk_creditbank(java.lang.String newPk_creditbank) {
		setAttributeValue("pk_creditbank", newPk_creditbank);
	}

	/**
	 * 属性isinherit的Getter方法.属性名：继承授信协议 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFBoolean
	 */
	public nc.vo.pub.lang.UFBoolean getIsinherit() {
		return (nc.vo.pub.lang.UFBoolean) getAttributeValue("isinherit");
	}

	/**
	 * 属性isinherit的Setter方法.属性名：继承授信协议 创建日期:
	 * 
	 * @param newIsinherit
	 *            nc.vo.pub.lang.UFBoolean
	 */
	public void setIsinherit(nc.vo.pub.lang.UFBoolean newIsinherit) {
		setAttributeValue("isinherit", newIsinherit);
	}

	/**
	 * 属性isdiffcctype的Getter方法.属性名：是否分授信类别 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFBoolean
	 */
	public nc.vo.pub.lang.UFBoolean getIsdiffcctype() {
		return (nc.vo.pub.lang.UFBoolean) getAttributeValue("isdiffcctype");
	}

	/**
	 * 属性isdiffcctype的Setter方法.属性名：是否分授信类别 创建日期:
	 * 
	 * @param newIsdiffcctype
	 *            nc.vo.pub.lang.UFBoolean
	 */
	public void setIsdiffcctype(nc.vo.pub.lang.UFBoolean newIsdiffcctype) {
		setAttributeValue("isdiffcctype", newIsdiffcctype);
	}

	/**
	 * 属性pk_currtype的Getter方法.属性名：币种 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_currtype() {
		return (java.lang.String) getAttributeValue("pk_currtype");
	}

	/**
	 * 属性pk_currtype的Setter方法.属性名：币种 创建日期:
	 * 
	 * @param newPk_currtype
	 *            java.lang.String
	 */
	public void setPk_currtype(java.lang.String newPk_currtype) {
		setAttributeValue("pk_currtype", newPk_currtype);
	}

	/**
	 * 属性remark的Getter方法.属性名：备注 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getRemark() {
		return (java.lang.String) getAttributeValue("remark");
	}

	/**
	 * 属性remark的Setter方法.属性名：备注 创建日期:
	 * 
	 * @param newRemark
	 *            java.lang.String
	 */
	public void setRemark(java.lang.String newRemark) {
		setAttributeValue("remark", newRemark);
	}

	/**
	 * 属性terminatedate的Getter方法.属性名：异常终止日期 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getTerminatedate() {
		return (nc.vo.pub.lang.UFDate) getAttributeValue("terminatedate");
	}

	/**
	 * 属性terminatedate的Setter方法.属性名：异常终止日期 创建日期:
	 * 
	 * @param newTerminatedate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setTerminatedate(nc.vo.pub.lang.UFDate newTerminatedate) {
		setAttributeValue("terminatedate", newTerminatedate);
	}

	/**
	 * 属性creationtime的Getter方法.属性名：创建时间 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getCreationtime() {
		return (nc.vo.pub.lang.UFDateTime) getAttributeValue("creationtime");
	}

	/**
	 * 属性creationtime的Setter方法.属性名：创建时间 创建日期:
	 * 
	 * @param newCreationtime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setCreationtime(nc.vo.pub.lang.UFDateTime newCreationtime) {
		setAttributeValue("creationtime", newCreationtime);
	}

	/**
	 * 属性creator的Getter方法.属性名：创建人 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getCreator() {
		return (java.lang.String) getAttributeValue("creator");
	}

	/**
	 * 属性creator的Setter方法.属性名：创建人 创建日期:
	 * 
	 * @param newCreator
	 *            java.lang.String
	 */
	public void setCreator(java.lang.String newCreator) {
		setAttributeValue("creator", newCreator);
	}

	/**
	 * 属性modifiedtime的Getter方法.属性名：最后修改时间 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getModifiedtime() {
		return (nc.vo.pub.lang.UFDateTime) getAttributeValue("modifiedtime");
	}

	/**
	 * 属性modifiedtime的Setter方法.属性名：最后修改时间 创建日期:
	 * 
	 * @param newModifiedtime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setModifiedtime(nc.vo.pub.lang.UFDateTime newModifiedtime) {
		setAttributeValue("modifiedtime", newModifiedtime);
	}

	/**
	 * 属性modifier的Getter方法.属性名：最后修改人 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getModifier() {
		return (java.lang.String) getAttributeValue("modifier");
	}

	/**
	 * 属性modifier的Setter方法.属性名：最后修改人 创建日期:
	 * 
	 * @param newModifier
	 *            java.lang.String
	 */
	public void setModifier(java.lang.String newModifier) {
		setAttributeValue("modifier", newModifier);
	}

	/**
	 * 属性vbillstatus的Getter方法.属性名：审批状态 创建日期:
	 * 
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getVbillstatus() {
		return (java.lang.Integer) getAttributeValue("vbillstatus");
	}

	/**
	 * 属性vbillstatus的Setter方法.属性名：审批状态 创建日期:
	 * 
	 * @param newVbillstatus
	 *            java.lang.Integer
	 */
	public void setVbillstatus(java.lang.Integer newVbillstatus) {
		setAttributeValue("vbillstatus", newVbillstatus);
	}

	/**
	 * 属性approver的Getter方法.属性名：审批人 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getApprover() {
		return (java.lang.String) getAttributeValue("approver");
	}

	/**
	 * 属性approver的Setter方法.属性名：审批人 创建日期:
	 * 
	 * @param newApprover
	 *            java.lang.String
	 */
	public void setApprover(java.lang.String newApprover) {
		setAttributeValue("approver", newApprover);
	}

	/**
	 * 属性approvedate的Getter方法.属性名：审批日期 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getApprovedate() {
		return (nc.vo.pub.lang.UFDate) getAttributeValue("approvedate");
	}

	/**
	 * 属性approvedate的Setter方法.属性名：审批日期 创建日期:
	 * 
	 * @param newApprovedate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setApprovedate(nc.vo.pub.lang.UFDate newApprovedate) {
		setAttributeValue("approvedate", newApprovedate);
	}

	/**
	 * 属性approvenote的Getter方法.属性名：审批批语 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getApprovenote() {
		return (java.lang.String) getAttributeValue("approvenote");
	}

	/**
	 * 属性approvenote的Setter方法.属性名：审批批语 创建日期:
	 * 
	 * @param newApprovenote
	 *            java.lang.String
	 */
	public void setApprovenote(java.lang.String newApprovenote) {
		setAttributeValue("approvenote", newApprovenote);
	}

	/**
	 * 属性vdef1的Getter方法.属性名：自定义项1 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef1() {
		return (java.lang.String) getAttributeValue("vdef1");
	}

	/**
	 * 属性vdef1的Setter方法.属性名：自定义项1 创建日期:
	 * 
	 * @param newVdef1
	 *            java.lang.String
	 */
	public void setVdef1(java.lang.String newVdef1) {
		setAttributeValue("vdef1", newVdef1);
	}

	/**
	 * 属性vdef2的Getter方法.属性名：自定义项2 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef2() {
		return (java.lang.String) getAttributeValue("vdef2");
	}

	/**
	 * 属性vdef2的Setter方法.属性名：自定义项2 创建日期:
	 * 
	 * @param newVdef2
	 *            java.lang.String
	 */
	public void setVdef2(java.lang.String newVdef2) {
		setAttributeValue("vdef2", newVdef2);
	}

	/**
	 * 属性vdef3的Getter方法.属性名：自定义项3 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef3() {
		return (java.lang.String) getAttributeValue("vdef3");
	}

	/**
	 * 属性vdef3的Setter方法.属性名：自定义项3 创建日期:
	 * 
	 * @param newVdef3
	 *            java.lang.String
	 */
	public void setVdef3(java.lang.String newVdef3) {
		setAttributeValue("vdef3", newVdef3);
	}

	/**
	 * 属性vdef4的Getter方法.属性名：自定义项4 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef4() {
		return (java.lang.String) getAttributeValue("vdef4");
	}

	/**
	 * 属性vdef4的Setter方法.属性名：自定义项4 创建日期:
	 * 
	 * @param newVdef4
	 *            java.lang.String
	 */
	public void setVdef4(java.lang.String newVdef4) {
		setAttributeValue("vdef4", newVdef4);
	}

	/**
	 * 属性vdef5的Getter方法.属性名：自定义项5 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef5() {
		return (java.lang.String) getAttributeValue("vdef5");
	}

	/**
	 * 属性vdef5的Setter方法.属性名：自定义项5 创建日期:
	 * 
	 * @param newVdef5
	 *            java.lang.String
	 */
	public void setVdef5(java.lang.String newVdef5) {
		setAttributeValue("vdef5", newVdef5);
	}

	/**
	 * 属性vdef6的Getter方法.属性名：自定义项6 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef6() {
		return (java.lang.String) getAttributeValue("vdef6");
	}

	/**
	 * 属性vdef6的Setter方法.属性名：自定义项6 创建日期:
	 * 
	 * @param newVdef6
	 *            java.lang.String
	 */
	public void setVdef6(java.lang.String newVdef6) {
		setAttributeValue("vdef6", newVdef6);
	}

	/**
	 * 属性vdef7的Getter方法.属性名：自定义项7 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef7() {
		return (java.lang.String) getAttributeValue("vdef7");
	}

	/**
	 * 属性vdef7的Setter方法.属性名：自定义项7 创建日期:
	 * 
	 * @param newVdef7
	 *            java.lang.String
	 */
	public void setVdef7(java.lang.String newVdef7) {
		setAttributeValue("vdef7", newVdef7);
	}

	/**
	 * 属性vdef8的Getter方法.属性名：自定义项8 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef8() {
		return (java.lang.String) getAttributeValue("vdef8");
	}

	/**
	 * 属性vdef8的Setter方法.属性名：自定义项8 创建日期:
	 * 
	 * @param newVdef8
	 *            java.lang.String
	 */
	public void setVdef8(java.lang.String newVdef8) {
		setAttributeValue("vdef8", newVdef8);
	}

	/**
	 * 属性vdef9的Getter方法.属性名：自定义项9 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef9() {
		return (java.lang.String) getAttributeValue("vdef9");
	}

	/**
	 * 属性vdef9的Setter方法.属性名：自定义项9 创建日期:
	 * 
	 * @param newVdef9
	 *            java.lang.String
	 */
	public void setVdef9(java.lang.String newVdef9) {
		setAttributeValue("vdef9", newVdef9);
	}

	/**
	 * 属性vdef10的Getter方法.属性名：自定义项10 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef10() {
		return (java.lang.String) getAttributeValue("vdef10");
	}

	/**
	 * 属性vdef10的Setter方法.属性名：自定义项10 创建日期:
	 * 
	 * @param newVdef10
	 *            java.lang.String
	 */
	public void setVdef10(java.lang.String newVdef10) {
		setAttributeValue("vdef10", newVdef10);
	}

	/**
	 * 属性vdef11的Getter方法.属性名：自定义项11 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef11() {
		return (java.lang.String) getAttributeValue("vdef11");
	}

	/**
	 * 属性vdef11的Setter方法.属性名：自定义项11 创建日期:
	 * 
	 * @param newVdef11
	 *            java.lang.String
	 */
	public void setVdef11(java.lang.String newVdef11) {
		setAttributeValue("vdef11", newVdef11);
	}

	/**
	 * 属性vdef12的Getter方法.属性名：自定义项12 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef12() {
		return (java.lang.String) getAttributeValue("vdef12");
	}

	/**
	 * 属性vdef12的Setter方法.属性名：自定义项12 创建日期:
	 * 
	 * @param newVdef12
	 *            java.lang.String
	 */
	public void setVdef12(java.lang.String newVdef12) {
		setAttributeValue("vdef12", newVdef12);
	}

	/**
	 * 属性vdef13的Getter方法.属性名：自定义项13 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef13() {
		return (java.lang.String) getAttributeValue("vdef13");
	}

	/**
	 * 属性vdef13的Setter方法.属性名：自定义项13 创建日期:
	 * 
	 * @param newVdef13
	 *            java.lang.String
	 */
	public void setVdef13(java.lang.String newVdef13) {
		setAttributeValue("vdef13", newVdef13);
	}

	/**
	 * 属性vdef14的Getter方法.属性名：自定义项14 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef14() {
		return (java.lang.String) getAttributeValue("vdef14");
	}

	/**
	 * 属性vdef14的Setter方法.属性名：自定义项14 创建日期:
	 * 
	 * @param newVdef14
	 *            java.lang.String
	 */
	public void setVdef14(java.lang.String newVdef14) {
		setAttributeValue("vdef14", newVdef14);
	}

	/**
	 * 属性vdef15的Getter方法.属性名：自定义项15 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef15() {
		return (java.lang.String) getAttributeValue("vdef15");
	}

	/**
	 * 属性vdef15的Setter方法.属性名：自定义项15 创建日期:
	 * 
	 * @param newVdef15
	 *            java.lang.String
	 */
	public void setVdef15(java.lang.String newVdef15) {
		setAttributeValue("vdef15", newVdef15);
	}

	/**
	 * 属性olcrate的Getter方法.属性名：组织本币汇率 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getOlcrate() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("olcrate");
	}

	/**
	 * 属性olcrate的Setter方法.属性名：组织本币汇率 创建日期:
	 * 
	 * @param newOlcrate
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setOlcrate(nc.vo.pub.lang.UFDouble newOlcrate) {
		setAttributeValue("olcrate", newOlcrate);
	}

	/**
	 * 属性glcrate的Getter方法.属性名：集团本币汇率 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGlcrate() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("glcrate");
	}

	/**
	 * 属性glcrate的Setter方法.属性名：集团本币汇率 创建日期:
	 * 
	 * @param newGlcrate
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGlcrate(nc.vo.pub.lang.UFDouble newGlcrate) {
		setAttributeValue("glcrate", newGlcrate);
	}

	/**
	 * 属性gllcrate的Getter方法.属性名：全局本币汇率 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGllcrate() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("gllcrate");
	}

	/**
	 * 属性gllcrate的Setter方法.属性名：全局本币汇率 创建日期:
	 * 
	 * @param newGllcrate
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGllcrate(nc.vo.pub.lang.UFDouble newGllcrate) {
		setAttributeValue("gllcrate", newGllcrate);
	}

	/**
	 * 属性pk_inputorg的Getter方法.属性名：录入组织 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_inputorg() {
		return (java.lang.String) getAttributeValue("pk_inputorg");
	}

	/**
	 * 属性pk_inputorg的Setter方法.属性名：录入组织 创建日期:
	 * 
	 * @param newPk_inputorg
	 *            java.lang.String
	 */
	public void setPk_inputorg(java.lang.String newPk_inputorg) {
		setAttributeValue("pk_inputorg", newPk_inputorg);
	}

	/**
	 * 属性pk_inputorg_v的Getter方法.属性名：录入组织版本 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_inputorg_v() {
		return (java.lang.String) getAttributeValue("pk_inputorg_v");
	}

	/**
	 * 属性pk_inputorg_v的Setter方法.属性名：录入组织版本 创建日期:
	 * 
	 * @param newPk_inputorg_v
	 *            java.lang.String
	 */
	public void setPk_inputorg_v(java.lang.String newPk_inputorg_v) {
		setAttributeValue("pk_inputorg_v", newPk_inputorg_v);
	}

	/**
	 * 属性crediter的Getter方法.属性名：受信人 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getCrediter() {
		return (java.lang.String) getAttributeValue("crediter");
	}

	/**
	 * 属性crediter的Setter方法.属性名：受信人 创建日期:
	 * 
	 * @param newCrediter
	 *            java.lang.String
	 */
	public void setCrediter(java.lang.String newCrediter) {
		setAttributeValue("crediter", newCrediter);
	}

	/**
	 * 属性cdtlnamt的Getter方法.属性名：原币额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getCdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("cdtlnamt");
	}

	/**
	 * 属性cdtlnamt的Setter方法.属性名：原币额度 创建日期:
	 * 
	 * @param newCdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setCdtlnamt(nc.vo.pub.lang.UFDouble newCdtlnamt) {
		setAttributeValue("cdtlnamt", newCdtlnamt);
	}

	/**
	 * 属性olccdtlnamt的Getter方法.属性名：组织本币额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getOlccdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("olccdtlnamt");
	}

	/**
	 * 属性olccdtlnamt的Setter方法.属性名：组织本币额度 创建日期:
	 * 
	 * @param newOlccdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setOlccdtlnamt(nc.vo.pub.lang.UFDouble newOlccdtlnamt) {
		setAttributeValue("olccdtlnamt", newOlccdtlnamt);
	}

	/**
	 * 属性glccdtlnamt的Getter方法.属性名：集团本币额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGlccdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("glccdtlnamt");
	}

	/**
	 * 属性glccdtlnamt的Setter方法.属性名：集团本币额度 创建日期:
	 * 
	 * @param newGlccdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGlccdtlnamt(nc.vo.pub.lang.UFDouble newGlccdtlnamt) {
		setAttributeValue("glccdtlnamt", newGlccdtlnamt);
	}

	/**
	 * 属性gllccdtlnamt的Getter方法.属性名：全局本币额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGllccdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("gllccdtlnamt");
	}

	/**
	 * 属性gllccdtlnamt的Setter方法.属性名：全局本币额度 创建日期:
	 * 
	 * @param newGllccdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGllccdtlnamt(nc.vo.pub.lang.UFDouble newGllccdtlnamt) {
		setAttributeValue("gllccdtlnamt", newGllccdtlnamt);
	}

	/**
	 * 属性appusdcdtlnamt的Getter方法.属性名：申请已用授信额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getAppusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("appusdcdtlnamt");
	}

	/**
	 * 属性appusdcdtlnamt的Setter方法.属性名：申请已用授信额度 创建日期:
	 * 
	 * @param newAppusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setAppusdcdtlnamt(nc.vo.pub.lang.UFDouble newAppusdcdtlnamt) {
		setAttributeValue("appusdcdtlnamt", newAppusdcdtlnamt);
	}

	/**
	 * 属性olcappusdcdtlnamt的Getter方法.属性名：申请已用组织本币授信额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getOlcappusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("olcappusdcdtlnamt");
	}

	/**
	 * 属性olcappusdcdtlnamt的Setter方法.属性名：申请已用组织本币授信额度 创建日期:
	 * 
	 * @param newOlcappusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setOlcappusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newOlcappusdcdtlnamt) {
		setAttributeValue("olcappusdcdtlnamt", newOlcappusdcdtlnamt);
	}

	/**
	 * 属性glcappusdcdtlnamt的Getter方法.属性名：申请已用集团本币授信额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGlcappusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("glcappusdcdtlnamt");
	}

	/**
	 * 属性glcappusdcdtlnamt的Setter方法.属性名：申请已用集团本币授信额度 创建日期:
	 * 
	 * @param newGlcappusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGlcappusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newGlcappusdcdtlnamt) {
		setAttributeValue("glcappusdcdtlnamt", newGlcappusdcdtlnamt);
	}

	/**
	 * 属性gllcappusdcdtlnamt的Getter方法.属性名：申请已用全局本币授信额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGllcappusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("gllcappusdcdtlnamt");
	}

	/**
	 * 属性gllcappusdcdtlnamt的Setter方法.属性名：申请已用全局本币授信额度 创建日期:
	 * 
	 * @param newGllcappusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGllcappusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newGllcappusdcdtlnamt) {
		setAttributeValue("gllcappusdcdtlnamt", newGllcappusdcdtlnamt);
	}

	/**
	 * 属性opnusdcdtlnamt的Getter方法.属性名：期初已用授信额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getOpnusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("opnusdcdtlnamt");
	}

	/**
	 * 属性opnusdcdtlnamt的Setter方法.属性名：期初已用授信额度 创建日期:
	 * 
	 * @param newOpnusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setOpnusdcdtlnamt(nc.vo.pub.lang.UFDouble newOpnusdcdtlnamt) {
		setAttributeValue("opnusdcdtlnamt", newOpnusdcdtlnamt);
	}

	/**
	 * 属性olcopnusdcdtlnamt的Getter方法.属性名：期初已用组织本币授信额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getOlcopnusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("olcopnusdcdtlnamt");
	}

	/**
	 * 属性olcopnusdcdtlnamt的Setter方法.属性名：期初已用组织本币授信额度 创建日期:
	 * 
	 * @param newOlcopnusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setOlcopnusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newOlcopnusdcdtlnamt) {
		setAttributeValue("olcopnusdcdtlnamt", newOlcopnusdcdtlnamt);
	}

	/**
	 * 属性glcopnusdcdtlnamt的Getter方法.属性名：期初已用集团本币授信额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGlcopnusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("glcopnusdcdtlnamt");
	}

	/**
	 * 属性glcopnusdcdtlnamt的Setter方法.属性名：期初已用集团本币授信额度 创建日期:
	 * 
	 * @param newGlcopnusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGlcopnusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newGlcopnusdcdtlnamt) {
		setAttributeValue("glcopnusdcdtlnamt", newGlcopnusdcdtlnamt);
	}

	/**
	 * 属性gllcopnusdcdtlnamt的Getter方法.属性名：期初已用全局本币授信额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGllcopnusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("gllcopnusdcdtlnamt");
	}

	/**
	 * 属性gllcopnusdcdtlnamt的Setter方法.属性名：期初已用全局本币授信额度 创建日期:
	 * 
	 * @param newGllcopnusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGllcopnusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newGllcopnusdcdtlnamt) {
		setAttributeValue("gllcopnusdcdtlnamt", newGllcopnusdcdtlnamt);
	}

	/**
	 * 属性curusdcdtlnamt的Getter方法.属性名：本期已用授信额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getCurusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("curusdcdtlnamt");
	}

	/**
	 * 属性curusdcdtlnamt的Setter方法.属性名：本期已用授信额度 创建日期:
	 * 
	 * @param newCurusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setCurusdcdtlnamt(nc.vo.pub.lang.UFDouble newCurusdcdtlnamt) {
		setAttributeValue("curusdcdtlnamt", newCurusdcdtlnamt);
	}

	/**
	 * 属性olccurusdcdtlnamt的Getter方法.属性名：本期已用组织本币授信额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getOlccurusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("olccurusdcdtlnamt");
	}

	/**
	 * 属性olccurusdcdtlnamt的Setter方法.属性名：本期已用组织本币授信额度 创建日期:
	 * 
	 * @param newOlccurusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setOlccurusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newOlccurusdcdtlnamt) {
		setAttributeValue("olccurusdcdtlnamt", newOlccurusdcdtlnamt);
	}

	/**
	 * 属性glccurusdcdtlnamt的Getter方法.属性名：本期已用集团本币授信额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGlccurusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("glccurusdcdtlnamt");
	}

	/**
	 * 属性glccurusdcdtlnamt的Setter方法.属性名：本期已用集团本币授信额度 创建日期:
	 * 
	 * @param newGlccurusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGlccurusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newGlccurusdcdtlnamt) {
		setAttributeValue("glccurusdcdtlnamt", newGlccurusdcdtlnamt);
	}

	/**
	 * 属性gllccurusdcdtlnamt的Getter方法.属性名：本期已用全局本币授信额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGllccurusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("gllccurusdcdtlnamt");
	}

	/**
	 * 属性gllccurusdcdtlnamt的Setter方法.属性名：本期已用全局本币授信额度 创建日期:
	 * 
	 * @param newGllccurusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGllccurusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newGllccurusdcdtlnamt) {
		setAttributeValue("gllccurusdcdtlnamt", newGllccurusdcdtlnamt);
	}

	/**
	 * 属性availcdtlnamt的Getter方法.属性名：可用授信额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getAvailcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("availcdtlnamt");
	}

	/**
	 * 属性availcdtlnamt的Setter方法.属性名：可用授信额度 创建日期:
	 * 
	 * @param newAvailcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setAvailcdtlnamt(nc.vo.pub.lang.UFDouble newAvailcdtlnamt) {
		setAttributeValue("availcdtlnamt", newAvailcdtlnamt);
	}

	/**
	 * 属性olcavailcdtlnamt的Getter方法.属性名：可用组织本币授信额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getOlcavailcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("olcavailcdtlnamt");
	}

	/**
	 * 属性olcavailcdtlnamt的Setter方法.属性名：可用组织本币授信额度 创建日期:
	 * 
	 * @param newOlcavailcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setOlcavailcdtlnamt(nc.vo.pub.lang.UFDouble newOlcavailcdtlnamt) {
		setAttributeValue("olcavailcdtlnamt", newOlcavailcdtlnamt);
	}

	/**
	 * 属性glcavailcdtlnamt的Getter方法.属性名：可用集团本币授信额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGlcavailcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("glcavailcdtlnamt");
	}

	/**
	 * 属性glcavailcdtlnamt的Setter方法.属性名：可用集团本币授信额度 创建日期:
	 * 
	 * @param newGlcavailcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGlcavailcdtlnamt(nc.vo.pub.lang.UFDouble newGlcavailcdtlnamt) {
		setAttributeValue("glcavailcdtlnamt", newGlcavailcdtlnamt);
	}

	/**
	 * 属性gllcavailcdtlnamt的Getter方法.属性名：可用全局本币授信额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGllcavailcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("gllcavailcdtlnamt");
	}

	/**
	 * 属性gllcavailcdtlnamt的Setter方法.属性名：可用全局本币授信额度 创建日期:
	 * 
	 * @param newGllcavailcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGllcavailcdtlnamt(
			nc.vo.pub.lang.UFDouble newGllcavailcdtlnamt) {
		setAttributeValue("gllcavailcdtlnamt", newGllcavailcdtlnamt);
	}

	/**
	 * 属性periodcount的Getter方法.属性名：期间 创建日期:
	 * 
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getPeriodcount() {
		return (java.lang.Integer) getAttributeValue("periodcount");
	}

	/**
	 * 属性periodcount的Setter方法.属性名：期间 创建日期:
	 * 
	 * @param newPeriodcount
	 *            java.lang.Integer
	 */
	public void setPeriodcount(java.lang.Integer newPeriodcount) {
		setAttributeValue("periodcount", newPeriodcount);
	}

	/**
	 * 属性periodunit的Getter方法.属性名：期间单位 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPeriodunit() {
		return (java.lang.String) getAttributeValue("periodunit");
	}

	/**
	 * 属性periodunit的Setter方法.属性名：期间单位 创建日期:
	 * 
	 * @param newPeriodunit
	 *            java.lang.String
	 */
	public void setPeriodunit(java.lang.String newPeriodunit) {
		setAttributeValue("periodunit", newPeriodunit);
	}

	/**
	 * 属性begindate的Getter方法.属性名：起始日期 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getBegindate() {
		return (nc.vo.pub.lang.UFDate) getAttributeValue("begindate");
	}

	/**
	 * 属性begindate的Setter方法.属性名：起始日期 创建日期:
	 * 
	 * @param newBegindate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setBegindate(nc.vo.pub.lang.UFDate newBegindate) {
		setAttributeValue("begindate", newBegindate);
	}

	/**
	 * 属性enddate的Getter方法.属性名：结束日期 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getEnddate() {
		return (nc.vo.pub.lang.UFDate) getAttributeValue("enddate");
	}

	/**
	 * 属性enddate的Setter方法.属性名：结束日期 创建日期:
	 * 
	 * @param newEnddate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setEnddate(nc.vo.pub.lang.UFDate newEnddate) {
		setAttributeValue("enddate", newEnddate);
	}

	/**
	 * 属性initenddate的Getter方法.属性名：初始结束日期 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getInitenddate() {
		return (nc.vo.pub.lang.UFDate) getAttributeValue("initenddate");
	}

	/**
	 * 属性initenddate的Setter方法.属性名：初始结束日期 创建日期:
	 * 
	 * @param newInitenddate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setInitenddate(nc.vo.pub.lang.UFDate newInitenddate) {
		setAttributeValue("initenddate", newInitenddate);
	}

	/**
	 * 属性pk_guarantee的Getter方法.属性名：担保合同 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_guarantee() {
		return (java.lang.String) getAttributeValue("pk_guarantee");
	}

	/**
	 * 属性pk_guarantee的Setter方法.属性名：担保合同 创建日期:
	 * 
	 * @param newPk_guarantee
	 *            java.lang.String
	 */
	public void setPk_guarantee(java.lang.String newPk_guarantee) {
		setAttributeValue("pk_guarantee", newPk_guarantee);
	}

	/**
	 * 属性guaranteetype的Getter方法.属性名：担保方式 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getGuaranteetype() {
		return (java.lang.String) getAttributeValue("guaranteetype");
	}

	/**
	 * 属性guaranteetype的Setter方法.属性名：担保方式 创建日期:
	 * 
	 * @param newGuaranteetype
	 *            java.lang.String
	 */
	public void setGuaranteetype(java.lang.String newGuaranteetype) {
		setAttributeValue("guaranteetype", newGuaranteetype);
	}

	/**
	 * 属性guaranteeamt的Getter方法.属性名：占用担保额度 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGuaranteeamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("guaranteeamt");
	}

	/**
	 * 属性guaranteeamt的Setter方法.属性名：占用担保额度 创建日期:
	 * 
	 * @param newGuaranteeamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGuaranteeamt(nc.vo.pub.lang.UFDouble newGuaranteeamt) {
		setAttributeValue("guaranteeamt", newGuaranteeamt);
	}

	/**
	 * 属性frozendate的Getter方法.属性名：冻结日期 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getFrozendate() {
		return (nc.vo.pub.lang.UFDate) getAttributeValue("frozendate");
	}

	/**
	 * 属性frozendate的Setter方法.属性名：冻结日期 创建日期:
	 * 
	 * @param newFrozendate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setFrozendate(nc.vo.pub.lang.UFDate newFrozendate) {
		setAttributeValue("frozendate", newFrozendate);
	}

	/**
	 * 属性frozener的Getter方法.属性名：冻结操作员 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getFrozener() {
		return (java.lang.String) getAttributeValue("frozener");
	}

	/**
	 * 属性frozener的Setter方法.属性名：冻结操作员 创建日期:
	 * 
	 * @param newFrozener
	 *            java.lang.String
	 */
	public void setFrozener(java.lang.String newFrozener) {
		setAttributeValue("frozener", newFrozener);
	}

	/**
	 * 属性terminator的Getter方法.属性名：终止操作员 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTerminator() {
		return (java.lang.String) getAttributeValue("terminator");
	}

	/**
	 * 属性terminator的Setter方法.属性名：终止操作员 创建日期:
	 * 
	 * @param newTerminator
	 *            java.lang.String
	 */
	public void setTerminator(java.lang.String newTerminator) {
		setAttributeValue("terminator", newTerminator);
	}

	/**
	 * 属性vdef16的Getter方法.属性名：自定义项16 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef16() {
		return (java.lang.String) getAttributeValue("vdef16");
	}

	/**
	 * 属性vdef16的Setter方法.属性名：自定义项16 创建日期:
	 * 
	 * @param newVdef16
	 *            java.lang.String
	 */
	public void setVdef16(java.lang.String newVdef16) {
		setAttributeValue("vdef16", newVdef16);
	}

	/**
	 * 属性vdef17的Getter方法.属性名：自定义项17 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef17() {
		return (java.lang.String) getAttributeValue("vdef17");
	}

	/**
	 * 属性vdef17的Setter方法.属性名：自定义项17 创建日期:
	 * 
	 * @param newVdef17
	 *            java.lang.String
	 */
	public void setVdef17(java.lang.String newVdef17) {
		setAttributeValue("vdef17", newVdef17);
	}

	/**
	 * 属性vdef18的Getter方法.属性名：自定义项18 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef18() {
		return (java.lang.String) getAttributeValue("vdef18");
	}

	/**
	 * 属性vdef18的Setter方法.属性名：自定义项18 创建日期:
	 * 
	 * @param newVdef18
	 *            java.lang.String
	 */
	public void setVdef18(java.lang.String newVdef18) {
		setAttributeValue("vdef18", newVdef18);
	}

	/**
	 * 属性vdef19的Getter方法.属性名：自定义项19 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef19() {
		return (java.lang.String) getAttributeValue("vdef19");
	}

	/**
	 * 属性vdef19的Setter方法.属性名：自定义项19 创建日期:
	 * 
	 * @param newVdef19
	 *            java.lang.String
	 */
	public void setVdef19(java.lang.String newVdef19) {
		setAttributeValue("vdef19", newVdef19);
	}

	/**
	 * 属性vdef20的Getter方法.属性名：自定义项20 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef20() {
		return (java.lang.String) getAttributeValue("vdef20");
	}

	/**
	 * 属性vdef20的Setter方法.属性名：自定义项20 创建日期:
	 * 
	 * @param newVdef20
	 *            java.lang.String
	 */
	public void setVdef20(java.lang.String newVdef20) {
		setAttributeValue("vdef20", newVdef20);
	}

	/**
	 * 属性pk_billtypecode的Getter方法.属性名：单据类型 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_billtypecode() {
		return (java.lang.String) getAttributeValue("pk_billtypecode");
	}

	/**
	 * 属性pk_billtypecode的Setter方法.属性名：单据类型 创建日期:
	 * 
	 * @param newPk_billtypecode
	 *            java.lang.String
	 */
	public void setPk_billtypecode(java.lang.String newPk_billtypecode) {
		setAttributeValue("pk_billtypecode", newPk_billtypecode);
	}

	/**
	 * 属性pk_billtypeid的Getter方法.属性名：单据类型主键 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_billtypeid() {
		return (java.lang.String) getAttributeValue("pk_billtypeid");
	}

	/**
	 * 属性pk_billtypeid的Setter方法.属性名：单据类型主键 创建日期:
	 * 
	 * @param newPk_billtypeid
	 *            java.lang.String
	 */
	public void setPk_billtypeid(java.lang.String newPk_billtypeid) {
		setAttributeValue("pk_billtypeid", newPk_billtypeid);
	}

	/**
	 * 属性billmaker的Getter方法.属性名：制单人 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getBillmaker() {
		return (java.lang.String) getAttributeValue("billmaker");
	}

	/**
	 * 属性billmaker的Setter方法.属性名：制单人 创建日期:
	 * 
	 * @param newBillmaker
	 *            java.lang.String
	 */
	public void setBillmaker(java.lang.String newBillmaker) {
		setAttributeValue("billmaker", newBillmaker);
	}

	/**
	 * 属性billmakedate的Getter方法.属性名：制单日期 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getBillmakedate() {
		return (nc.vo.pub.lang.UFDate) getAttributeValue("billmakedate");
	}

	/**
	 * 属性billmakedate的Setter方法.属性名：制单日期 创建日期:
	 * 
	 * @param newBillmakedate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setBillmakedate(nc.vo.pub.lang.UFDate newBillmakedate) {
		setAttributeValue("billmakedate", newBillmakedate);
	}

	/**
	 * 属性billmaketime的Getter方法.属性名：制单时间 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getBillmaketime() {
		return (nc.vo.pub.lang.UFDateTime) getAttributeValue("billmaketime");
	}

	/**
	 * 属性billmaketime的Setter方法.属性名：制单时间 创建日期:
	 * 
	 * @param newBillmaketime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setBillmaketime(nc.vo.pub.lang.UFDateTime newBillmaketime) {
		setAttributeValue("billmaketime", newBillmaketime);
	}

	/**
	 * 属性versionorigin的Getter方法.属性名：版本来源 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVersionorigin() {
		return (java.lang.String) getAttributeValue("versionorigin");
	}

	/**
	 * 属性versionorigin的Setter方法.属性名：版本来源 创建日期:
	 * 
	 * @param newVersionorigin
	 *            java.lang.String
	 */
	public void setVersionorigin(java.lang.String newVersionorigin) {
		setAttributeValue("versionorigin", newVersionorigin);
	}

	/**
	 * 属性versiontime的Getter方法.属性名：版本日期 创建日期:
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDate getVersiontime() {
		return (nc.vo.pub.lang.UFDate) getAttributeValue("versiontime");
	}

	/**
	 * 属性versiontime的Setter方法.属性名：版本日期 创建日期:
	 * 
	 * @param newVersiontime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setVersiontime(nc.vo.pub.lang.UFDate newVersiontime) {
		setAttributeValue("versiontime", newVersiontime);
	}

//	/**
//	 * 属性dr的Getter方法.属性名：dr 创建日期:
//	 * 
//	 * @return java.lang.Integer
//	 */
//	public java.lang.Integer getDr() {
//		return (java.lang.Integer) getAttributeValue("dr");
//	}
//
//	/**
//	 * 属性dr的Setter方法.属性名：dr 创建日期:
//	 * 
//	 * @param newDr
//	 *            java.lang.Integer
//	 */
//	public void setDr(java.lang.Integer newDr) {
//		setAttributeValue("dr", newDr);
//	}

	/**
	 * <p>
	 * 取得父VO主键字段.
	 * <p>
	 * 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getParentPKFieldName() {
		return null;
	}

	/**
	 * <p>
	 * 取得表主键.
	 * <p>
	 * 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPKFieldName() {
		return "pk_protocol";
	}


	/**
	 * 属性pk_fundplan的Getter方法.属性名：资金计划项目 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_fundplan() {
		return (java.lang.String) getAttributeValue("pk_fundplan");
	}

	/**
	 * 属性pk_fundplan的Setter方法.属性名：资金计划项目 创建日期:
	 * 
	 * @param newPk_fundplan
	 *            java.lang.String
	 */
	public void setPk_fundplan(java.lang.String newPk_fundplan) {
		setAttributeValue("pk_fundplan", newPk_fundplan);
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("ccc.bankprotocol");
	}

	/**
	 * <p>
	 * 返回表名称.
	 * <p>
	 * 创建日期:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "ccc_bankprotocol";
	}
}
