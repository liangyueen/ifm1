package nc.vo.ifm.bankprotocol;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 * �˴�����۵�������Ϣ
 * </p>
 * ��������:2018-9-4
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
	 * ����inheritprotocol��Getter����.���������̳�����Э�� ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getInheritprotocol() {
		return (java.lang.String) getAttributeValue("inheritprotocol");
	}

	/**
	 * ����inheritprotocol��Setter����.���������̳�����Э�� ��������:
	 * 
	 * @param newInheritprotocol
	 *            java.lang.String
	 */
	public void setInheritprotocol(java.lang.String newInheritprotocol) {
		setAttributeValue("inheritprotocol", newInheritprotocol);
	}

	/**
	 * ����pk_protocol��Getter����.��������Э������ ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_protocol() {
		return (java.lang.String) getAttributeValue("pk_protocol");
	}

	/**
	 * ����pk_bankprotocol��Setter����.��������Э������ ��������:
	 * 
	 * @param newPk_bankprotocol
	 *            java.lang.String
	 */
	public void setPk_protocol(java.lang.String newPk_protocol) {
		setAttributeValue("pk_protocol", newPk_protocol);
	}

	/**
	 * ����pk_group��Getter����.������������ ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_group() {
		return (java.lang.String) getAttributeValue("pk_group");
	}

	/**
	 * ����pk_group��Setter����.������������ ��������:
	 * 
	 * @param newPk_group
	 *            java.lang.String
	 */
	public void setPk_group(java.lang.String newPk_group) {
		setAttributeValue("pk_group", newPk_group);
	}

	/**
	 * ����pk_org��Getter����.��������������֯ ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org() {
		return (java.lang.String) getAttributeValue("pk_org");
	}

	/**
	 * ����pk_org��Setter����.��������������֯ ��������:
	 * 
	 * @param newPk_org
	 *            java.lang.String
	 */
	public void setPk_org(java.lang.String newPk_org) {
		setAttributeValue("pk_org", newPk_org);
	}

	/**
	 * ����pk_org_v��Getter����.��������������֯�汾 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org_v() {
		return (java.lang.String) getAttributeValue("pk_org_v");
	}

	/**
	 * ����pk_org_v��Setter����.��������������֯�汾 ��������:
	 * 
	 * @param newPk_org_v
	 *            java.lang.String
	 */
	public void setPk_org_v(java.lang.String newPk_org_v) {
		setAttributeValue("pk_org_v", newPk_org_v);
	}

	/**
	 * ����ctrlmethod��Getter����.�����������Ʒ�ʽ ��������:
	 * 
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getControlmethod() {
		return (java.lang.Integer) getAttributeValue("controlmethod");
	}

	/**
	 * ����controlmethod��Setter����.�����������Ʒ�ʽ ��������:
	 * 
	 * @param newCtrlmethod
	 *            java.lang.Integer
	 */
	public void setControlmethod(java.lang.Integer newCtrlmethod) {
		setAttributeValue("controlmethod", newCtrlmethod);
	}

	/**
	 * ����protocolcode��Getter����.��������Э���� ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getProtocolcode() {
		return (java.lang.String) getAttributeValue("protocolcode");
	}

	/**
	 * ����protocolcode��Setter����.��������Э���� ��������:
	 * 
	 * @param newProtocolcode
	 *            java.lang.String
	 */
	public void setProtocolcode(java.lang.String newProtocolcode) {
		setAttributeValue("protocolcode", newProtocolcode);
	}

	/**
	 * ����protocoltype��Getter����.��������Э������ ��������:
	 * 
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getProtocoltype() {
		return (java.lang.Integer) getAttributeValue("protocoltype");
	}

	/**
	 * ����protocoltype��Setter����.��������Э������ ��������:
	 * 
	 * @param newProtocoltype
	 *            java.lang.Integer
	 */
	public void setProtocoltype(java.lang.Integer newProtocoltype) {
		setAttributeValue("protocoltype", newProtocoltype);
	}

	/**
	 * ����versionno��Getter����.���������汾�� ��������:
	 * 
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getVersionno() {
		return (java.lang.Integer) getAttributeValue("versionno");
	}

	/**
	 * ����versionno��Setter����.���������汾�� ��������:
	 * 
	 * @param newVersionno
	 *            java.lang.Integer
	 */
	public void setVersionno(java.lang.Integer newVersionno) {
		setAttributeValue("versionno", newVersionno);
	}

	/**
	 * ����pk_version��Getter����.���������汾���� ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_version() {
		return (java.lang.String) getAttributeValue("pk_version");
	}

	/**
	 * ����pk_version��Setter����.���������汾���� ��������:
	 * 
	 * @param newPk_version
	 *            java.lang.String
	 */
	public void setPk_version(java.lang.String newPk_version) {
		setAttributeValue("pk_version", newPk_version);
	}

	/**
	 * ����protocolstatus��Getter����.��������Э��״̬ ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getProtocolstatus() {
		return (java.lang.String) getAttributeValue("protocolstatus");
	}

	/**
	 * ����protocolstatus��Setter����.��������Э��״̬ ��������:
	 * 
	 * @param newProtocolstatus
	 *            java.lang.String
	 */
	public void setProtocolstatus(java.lang.String newProtocolstatus) {
		setAttributeValue("protocolstatus", newProtocolstatus);
	}

	/**
	 * ����pk_creditbank��Getter����.���������������� ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_creditbank() {
		return (java.lang.String) getAttributeValue("pk_creditbank");
	}

	/**
	 * ����pk_creditbank��Setter����.���������������� ��������:
	 * 
	 * @param newPk_creditbank
	 *            java.lang.String
	 */
	public void setPk_creditbank(java.lang.String newPk_creditbank) {
		setAttributeValue("pk_creditbank", newPk_creditbank);
	}

	/**
	 * ����isinherit��Getter����.���������̳�����Э�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFBoolean
	 */
	public nc.vo.pub.lang.UFBoolean getIsinherit() {
		return (nc.vo.pub.lang.UFBoolean) getAttributeValue("isinherit");
	}

	/**
	 * ����isinherit��Setter����.���������̳�����Э�� ��������:
	 * 
	 * @param newIsinherit
	 *            nc.vo.pub.lang.UFBoolean
	 */
	public void setIsinherit(nc.vo.pub.lang.UFBoolean newIsinherit) {
		setAttributeValue("isinherit", newIsinherit);
	}

	/**
	 * ����isdiffcctype��Getter����.���������Ƿ��������� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFBoolean
	 */
	public nc.vo.pub.lang.UFBoolean getIsdiffcctype() {
		return (nc.vo.pub.lang.UFBoolean) getAttributeValue("isdiffcctype");
	}

	/**
	 * ����isdiffcctype��Setter����.���������Ƿ��������� ��������:
	 * 
	 * @param newIsdiffcctype
	 *            nc.vo.pub.lang.UFBoolean
	 */
	public void setIsdiffcctype(nc.vo.pub.lang.UFBoolean newIsdiffcctype) {
		setAttributeValue("isdiffcctype", newIsdiffcctype);
	}

	/**
	 * ����pk_currtype��Getter����.������������ ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_currtype() {
		return (java.lang.String) getAttributeValue("pk_currtype");
	}

	/**
	 * ����pk_currtype��Setter����.������������ ��������:
	 * 
	 * @param newPk_currtype
	 *            java.lang.String
	 */
	public void setPk_currtype(java.lang.String newPk_currtype) {
		setAttributeValue("pk_currtype", newPk_currtype);
	}

	/**
	 * ����remark��Getter����.����������ע ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getRemark() {
		return (java.lang.String) getAttributeValue("remark");
	}

	/**
	 * ����remark��Setter����.����������ע ��������:
	 * 
	 * @param newRemark
	 *            java.lang.String
	 */
	public void setRemark(java.lang.String newRemark) {
		setAttributeValue("remark", newRemark);
	}

	/**
	 * ����terminatedate��Getter����.���������쳣��ֹ���� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getTerminatedate() {
		return (nc.vo.pub.lang.UFDate) getAttributeValue("terminatedate");
	}

	/**
	 * ����terminatedate��Setter����.���������쳣��ֹ���� ��������:
	 * 
	 * @param newTerminatedate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setTerminatedate(nc.vo.pub.lang.UFDate newTerminatedate) {
		setAttributeValue("terminatedate", newTerminatedate);
	}

	/**
	 * ����creationtime��Getter����.������������ʱ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getCreationtime() {
		return (nc.vo.pub.lang.UFDateTime) getAttributeValue("creationtime");
	}

	/**
	 * ����creationtime��Setter����.������������ʱ�� ��������:
	 * 
	 * @param newCreationtime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setCreationtime(nc.vo.pub.lang.UFDateTime newCreationtime) {
		setAttributeValue("creationtime", newCreationtime);
	}

	/**
	 * ����creator��Getter����.�������������� ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getCreator() {
		return (java.lang.String) getAttributeValue("creator");
	}

	/**
	 * ����creator��Setter����.�������������� ��������:
	 * 
	 * @param newCreator
	 *            java.lang.String
	 */
	public void setCreator(java.lang.String newCreator) {
		setAttributeValue("creator", newCreator);
	}

	/**
	 * ����modifiedtime��Getter����.������������޸�ʱ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getModifiedtime() {
		return (nc.vo.pub.lang.UFDateTime) getAttributeValue("modifiedtime");
	}

	/**
	 * ����modifiedtime��Setter����.������������޸�ʱ�� ��������:
	 * 
	 * @param newModifiedtime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setModifiedtime(nc.vo.pub.lang.UFDateTime newModifiedtime) {
		setAttributeValue("modifiedtime", newModifiedtime);
	}

	/**
	 * ����modifier��Getter����.������������޸��� ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getModifier() {
		return (java.lang.String) getAttributeValue("modifier");
	}

	/**
	 * ����modifier��Setter����.������������޸��� ��������:
	 * 
	 * @param newModifier
	 *            java.lang.String
	 */
	public void setModifier(java.lang.String newModifier) {
		setAttributeValue("modifier", newModifier);
	}

	/**
	 * ����vbillstatus��Getter����.������������״̬ ��������:
	 * 
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getVbillstatus() {
		return (java.lang.Integer) getAttributeValue("vbillstatus");
	}

	/**
	 * ����vbillstatus��Setter����.������������״̬ ��������:
	 * 
	 * @param newVbillstatus
	 *            java.lang.Integer
	 */
	public void setVbillstatus(java.lang.Integer newVbillstatus) {
		setAttributeValue("vbillstatus", newVbillstatus);
	}

	/**
	 * ����approver��Getter����.�������������� ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getApprover() {
		return (java.lang.String) getAttributeValue("approver");
	}

	/**
	 * ����approver��Setter����.�������������� ��������:
	 * 
	 * @param newApprover
	 *            java.lang.String
	 */
	public void setApprover(java.lang.String newApprover) {
		setAttributeValue("approver", newApprover);
	}

	/**
	 * ����approvedate��Getter����.���������������� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getApprovedate() {
		return (nc.vo.pub.lang.UFDate) getAttributeValue("approvedate");
	}

	/**
	 * ����approvedate��Setter����.���������������� ��������:
	 * 
	 * @param newApprovedate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setApprovedate(nc.vo.pub.lang.UFDate newApprovedate) {
		setAttributeValue("approvedate", newApprovedate);
	}

	/**
	 * ����approvenote��Getter����.���������������� ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getApprovenote() {
		return (java.lang.String) getAttributeValue("approvenote");
	}

	/**
	 * ����approvenote��Setter����.���������������� ��������:
	 * 
	 * @param newApprovenote
	 *            java.lang.String
	 */
	public void setApprovenote(java.lang.String newApprovenote) {
		setAttributeValue("approvenote", newApprovenote);
	}

	/**
	 * ����vdef1��Getter����.���������Զ�����1 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef1() {
		return (java.lang.String) getAttributeValue("vdef1");
	}

	/**
	 * ����vdef1��Setter����.���������Զ�����1 ��������:
	 * 
	 * @param newVdef1
	 *            java.lang.String
	 */
	public void setVdef1(java.lang.String newVdef1) {
		setAttributeValue("vdef1", newVdef1);
	}

	/**
	 * ����vdef2��Getter����.���������Զ�����2 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef2() {
		return (java.lang.String) getAttributeValue("vdef2");
	}

	/**
	 * ����vdef2��Setter����.���������Զ�����2 ��������:
	 * 
	 * @param newVdef2
	 *            java.lang.String
	 */
	public void setVdef2(java.lang.String newVdef2) {
		setAttributeValue("vdef2", newVdef2);
	}

	/**
	 * ����vdef3��Getter����.���������Զ�����3 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef3() {
		return (java.lang.String) getAttributeValue("vdef3");
	}

	/**
	 * ����vdef3��Setter����.���������Զ�����3 ��������:
	 * 
	 * @param newVdef3
	 *            java.lang.String
	 */
	public void setVdef3(java.lang.String newVdef3) {
		setAttributeValue("vdef3", newVdef3);
	}

	/**
	 * ����vdef4��Getter����.���������Զ�����4 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef4() {
		return (java.lang.String) getAttributeValue("vdef4");
	}

	/**
	 * ����vdef4��Setter����.���������Զ�����4 ��������:
	 * 
	 * @param newVdef4
	 *            java.lang.String
	 */
	public void setVdef4(java.lang.String newVdef4) {
		setAttributeValue("vdef4", newVdef4);
	}

	/**
	 * ����vdef5��Getter����.���������Զ�����5 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef5() {
		return (java.lang.String) getAttributeValue("vdef5");
	}

	/**
	 * ����vdef5��Setter����.���������Զ�����5 ��������:
	 * 
	 * @param newVdef5
	 *            java.lang.String
	 */
	public void setVdef5(java.lang.String newVdef5) {
		setAttributeValue("vdef5", newVdef5);
	}

	/**
	 * ����vdef6��Getter����.���������Զ�����6 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef6() {
		return (java.lang.String) getAttributeValue("vdef6");
	}

	/**
	 * ����vdef6��Setter����.���������Զ�����6 ��������:
	 * 
	 * @param newVdef6
	 *            java.lang.String
	 */
	public void setVdef6(java.lang.String newVdef6) {
		setAttributeValue("vdef6", newVdef6);
	}

	/**
	 * ����vdef7��Getter����.���������Զ�����7 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef7() {
		return (java.lang.String) getAttributeValue("vdef7");
	}

	/**
	 * ����vdef7��Setter����.���������Զ�����7 ��������:
	 * 
	 * @param newVdef7
	 *            java.lang.String
	 */
	public void setVdef7(java.lang.String newVdef7) {
		setAttributeValue("vdef7", newVdef7);
	}

	/**
	 * ����vdef8��Getter����.���������Զ�����8 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef8() {
		return (java.lang.String) getAttributeValue("vdef8");
	}

	/**
	 * ����vdef8��Setter����.���������Զ�����8 ��������:
	 * 
	 * @param newVdef8
	 *            java.lang.String
	 */
	public void setVdef8(java.lang.String newVdef8) {
		setAttributeValue("vdef8", newVdef8);
	}

	/**
	 * ����vdef9��Getter����.���������Զ�����9 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef9() {
		return (java.lang.String) getAttributeValue("vdef9");
	}

	/**
	 * ����vdef9��Setter����.���������Զ�����9 ��������:
	 * 
	 * @param newVdef9
	 *            java.lang.String
	 */
	public void setVdef9(java.lang.String newVdef9) {
		setAttributeValue("vdef9", newVdef9);
	}

	/**
	 * ����vdef10��Getter����.���������Զ�����10 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef10() {
		return (java.lang.String) getAttributeValue("vdef10");
	}

	/**
	 * ����vdef10��Setter����.���������Զ�����10 ��������:
	 * 
	 * @param newVdef10
	 *            java.lang.String
	 */
	public void setVdef10(java.lang.String newVdef10) {
		setAttributeValue("vdef10", newVdef10);
	}

	/**
	 * ����vdef11��Getter����.���������Զ�����11 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef11() {
		return (java.lang.String) getAttributeValue("vdef11");
	}

	/**
	 * ����vdef11��Setter����.���������Զ�����11 ��������:
	 * 
	 * @param newVdef11
	 *            java.lang.String
	 */
	public void setVdef11(java.lang.String newVdef11) {
		setAttributeValue("vdef11", newVdef11);
	}

	/**
	 * ����vdef12��Getter����.���������Զ�����12 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef12() {
		return (java.lang.String) getAttributeValue("vdef12");
	}

	/**
	 * ����vdef12��Setter����.���������Զ�����12 ��������:
	 * 
	 * @param newVdef12
	 *            java.lang.String
	 */
	public void setVdef12(java.lang.String newVdef12) {
		setAttributeValue("vdef12", newVdef12);
	}

	/**
	 * ����vdef13��Getter����.���������Զ�����13 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef13() {
		return (java.lang.String) getAttributeValue("vdef13");
	}

	/**
	 * ����vdef13��Setter����.���������Զ�����13 ��������:
	 * 
	 * @param newVdef13
	 *            java.lang.String
	 */
	public void setVdef13(java.lang.String newVdef13) {
		setAttributeValue("vdef13", newVdef13);
	}

	/**
	 * ����vdef14��Getter����.���������Զ�����14 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef14() {
		return (java.lang.String) getAttributeValue("vdef14");
	}

	/**
	 * ����vdef14��Setter����.���������Զ�����14 ��������:
	 * 
	 * @param newVdef14
	 *            java.lang.String
	 */
	public void setVdef14(java.lang.String newVdef14) {
		setAttributeValue("vdef14", newVdef14);
	}

	/**
	 * ����vdef15��Getter����.���������Զ�����15 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef15() {
		return (java.lang.String) getAttributeValue("vdef15");
	}

	/**
	 * ����vdef15��Setter����.���������Զ�����15 ��������:
	 * 
	 * @param newVdef15
	 *            java.lang.String
	 */
	public void setVdef15(java.lang.String newVdef15) {
		setAttributeValue("vdef15", newVdef15);
	}

	/**
	 * ����olcrate��Getter����.����������֯���һ��� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getOlcrate() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("olcrate");
	}

	/**
	 * ����olcrate��Setter����.����������֯���һ��� ��������:
	 * 
	 * @param newOlcrate
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setOlcrate(nc.vo.pub.lang.UFDouble newOlcrate) {
		setAttributeValue("olcrate", newOlcrate);
	}

	/**
	 * ����glcrate��Getter����.�����������ű��һ��� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGlcrate() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("glcrate");
	}

	/**
	 * ����glcrate��Setter����.�����������ű��һ��� ��������:
	 * 
	 * @param newGlcrate
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGlcrate(nc.vo.pub.lang.UFDouble newGlcrate) {
		setAttributeValue("glcrate", newGlcrate);
	}

	/**
	 * ����gllcrate��Getter����.��������ȫ�ֱ��һ��� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGllcrate() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("gllcrate");
	}

	/**
	 * ����gllcrate��Setter����.��������ȫ�ֱ��һ��� ��������:
	 * 
	 * @param newGllcrate
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGllcrate(nc.vo.pub.lang.UFDouble newGllcrate) {
		setAttributeValue("gllcrate", newGllcrate);
	}

	/**
	 * ����pk_inputorg��Getter����.��������¼����֯ ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_inputorg() {
		return (java.lang.String) getAttributeValue("pk_inputorg");
	}

	/**
	 * ����pk_inputorg��Setter����.��������¼����֯ ��������:
	 * 
	 * @param newPk_inputorg
	 *            java.lang.String
	 */
	public void setPk_inputorg(java.lang.String newPk_inputorg) {
		setAttributeValue("pk_inputorg", newPk_inputorg);
	}

	/**
	 * ����pk_inputorg_v��Getter����.��������¼����֯�汾 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_inputorg_v() {
		return (java.lang.String) getAttributeValue("pk_inputorg_v");
	}

	/**
	 * ����pk_inputorg_v��Setter����.��������¼����֯�汾 ��������:
	 * 
	 * @param newPk_inputorg_v
	 *            java.lang.String
	 */
	public void setPk_inputorg_v(java.lang.String newPk_inputorg_v) {
		setAttributeValue("pk_inputorg_v", newPk_inputorg_v);
	}

	/**
	 * ����crediter��Getter����.�������������� ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getCrediter() {
		return (java.lang.String) getAttributeValue("crediter");
	}

	/**
	 * ����crediter��Setter����.�������������� ��������:
	 * 
	 * @param newCrediter
	 *            java.lang.String
	 */
	public void setCrediter(java.lang.String newCrediter) {
		setAttributeValue("crediter", newCrediter);
	}

	/**
	 * ����cdtlnamt��Getter����.��������ԭ�Ҷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getCdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("cdtlnamt");
	}

	/**
	 * ����cdtlnamt��Setter����.��������ԭ�Ҷ�� ��������:
	 * 
	 * @param newCdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setCdtlnamt(nc.vo.pub.lang.UFDouble newCdtlnamt) {
		setAttributeValue("cdtlnamt", newCdtlnamt);
	}

	/**
	 * ����olccdtlnamt��Getter����.����������֯���Ҷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getOlccdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("olccdtlnamt");
	}

	/**
	 * ����olccdtlnamt��Setter����.����������֯���Ҷ�� ��������:
	 * 
	 * @param newOlccdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setOlccdtlnamt(nc.vo.pub.lang.UFDouble newOlccdtlnamt) {
		setAttributeValue("olccdtlnamt", newOlccdtlnamt);
	}

	/**
	 * ����glccdtlnamt��Getter����.�����������ű��Ҷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGlccdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("glccdtlnamt");
	}

	/**
	 * ����glccdtlnamt��Setter����.�����������ű��Ҷ�� ��������:
	 * 
	 * @param newGlccdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGlccdtlnamt(nc.vo.pub.lang.UFDouble newGlccdtlnamt) {
		setAttributeValue("glccdtlnamt", newGlccdtlnamt);
	}

	/**
	 * ����gllccdtlnamt��Getter����.��������ȫ�ֱ��Ҷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGllccdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("gllccdtlnamt");
	}

	/**
	 * ����gllccdtlnamt��Setter����.��������ȫ�ֱ��Ҷ�� ��������:
	 * 
	 * @param newGllccdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGllccdtlnamt(nc.vo.pub.lang.UFDouble newGllccdtlnamt) {
		setAttributeValue("gllccdtlnamt", newGllccdtlnamt);
	}

	/**
	 * ����appusdcdtlnamt��Getter����.�������������������Ŷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getAppusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("appusdcdtlnamt");
	}

	/**
	 * ����appusdcdtlnamt��Setter����.�������������������Ŷ�� ��������:
	 * 
	 * @param newAppusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setAppusdcdtlnamt(nc.vo.pub.lang.UFDouble newAppusdcdtlnamt) {
		setAttributeValue("appusdcdtlnamt", newAppusdcdtlnamt);
	}

	/**
	 * ����olcappusdcdtlnamt��Getter����.������������������֯�������Ŷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getOlcappusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("olcappusdcdtlnamt");
	}

	/**
	 * ����olcappusdcdtlnamt��Setter����.������������������֯�������Ŷ�� ��������:
	 * 
	 * @param newOlcappusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setOlcappusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newOlcappusdcdtlnamt) {
		setAttributeValue("olcappusdcdtlnamt", newOlcappusdcdtlnamt);
	}

	/**
	 * ����glcappusdcdtlnamt��Getter����.���������������ü��ű������Ŷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGlcappusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("glcappusdcdtlnamt");
	}

	/**
	 * ����glcappusdcdtlnamt��Setter����.���������������ü��ű������Ŷ�� ��������:
	 * 
	 * @param newGlcappusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGlcappusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newGlcappusdcdtlnamt) {
		setAttributeValue("glcappusdcdtlnamt", newGlcappusdcdtlnamt);
	}

	/**
	 * ����gllcappusdcdtlnamt��Getter����.����������������ȫ�ֱ������Ŷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGllcappusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("gllcappusdcdtlnamt");
	}

	/**
	 * ����gllcappusdcdtlnamt��Setter����.����������������ȫ�ֱ������Ŷ�� ��������:
	 * 
	 * @param newGllcappusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGllcappusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newGllcappusdcdtlnamt) {
		setAttributeValue("gllcappusdcdtlnamt", newGllcappusdcdtlnamt);
	}

	/**
	 * ����opnusdcdtlnamt��Getter����.���������ڳ��������Ŷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getOpnusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("opnusdcdtlnamt");
	}

	/**
	 * ����opnusdcdtlnamt��Setter����.���������ڳ��������Ŷ�� ��������:
	 * 
	 * @param newOpnusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setOpnusdcdtlnamt(nc.vo.pub.lang.UFDouble newOpnusdcdtlnamt) {
		setAttributeValue("opnusdcdtlnamt", newOpnusdcdtlnamt);
	}

	/**
	 * ����olcopnusdcdtlnamt��Getter����.���������ڳ�������֯�������Ŷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getOlcopnusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("olcopnusdcdtlnamt");
	}

	/**
	 * ����olcopnusdcdtlnamt��Setter����.���������ڳ�������֯�������Ŷ�� ��������:
	 * 
	 * @param newOlcopnusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setOlcopnusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newOlcopnusdcdtlnamt) {
		setAttributeValue("olcopnusdcdtlnamt", newOlcopnusdcdtlnamt);
	}

	/**
	 * ����glcopnusdcdtlnamt��Getter����.���������ڳ����ü��ű������Ŷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGlcopnusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("glcopnusdcdtlnamt");
	}

	/**
	 * ����glcopnusdcdtlnamt��Setter����.���������ڳ����ü��ű������Ŷ�� ��������:
	 * 
	 * @param newGlcopnusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGlcopnusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newGlcopnusdcdtlnamt) {
		setAttributeValue("glcopnusdcdtlnamt", newGlcopnusdcdtlnamt);
	}

	/**
	 * ����gllcopnusdcdtlnamt��Getter����.���������ڳ�����ȫ�ֱ������Ŷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGllcopnusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("gllcopnusdcdtlnamt");
	}

	/**
	 * ����gllcopnusdcdtlnamt��Setter����.���������ڳ�����ȫ�ֱ������Ŷ�� ��������:
	 * 
	 * @param newGllcopnusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGllcopnusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newGllcopnusdcdtlnamt) {
		setAttributeValue("gllcopnusdcdtlnamt", newGllcopnusdcdtlnamt);
	}

	/**
	 * ����curusdcdtlnamt��Getter����.�������������������Ŷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getCurusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("curusdcdtlnamt");
	}

	/**
	 * ����curusdcdtlnamt��Setter����.�������������������Ŷ�� ��������:
	 * 
	 * @param newCurusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setCurusdcdtlnamt(nc.vo.pub.lang.UFDouble newCurusdcdtlnamt) {
		setAttributeValue("curusdcdtlnamt", newCurusdcdtlnamt);
	}

	/**
	 * ����olccurusdcdtlnamt��Getter����.������������������֯�������Ŷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getOlccurusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("olccurusdcdtlnamt");
	}

	/**
	 * ����olccurusdcdtlnamt��Setter����.������������������֯�������Ŷ�� ��������:
	 * 
	 * @param newOlccurusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setOlccurusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newOlccurusdcdtlnamt) {
		setAttributeValue("olccurusdcdtlnamt", newOlccurusdcdtlnamt);
	}

	/**
	 * ����glccurusdcdtlnamt��Getter����.���������������ü��ű������Ŷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGlccurusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("glccurusdcdtlnamt");
	}

	/**
	 * ����glccurusdcdtlnamt��Setter����.���������������ü��ű������Ŷ�� ��������:
	 * 
	 * @param newGlccurusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGlccurusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newGlccurusdcdtlnamt) {
		setAttributeValue("glccurusdcdtlnamt", newGlccurusdcdtlnamt);
	}

	/**
	 * ����gllccurusdcdtlnamt��Getter����.����������������ȫ�ֱ������Ŷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGllccurusdcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("gllccurusdcdtlnamt");
	}

	/**
	 * ����gllccurusdcdtlnamt��Setter����.����������������ȫ�ֱ������Ŷ�� ��������:
	 * 
	 * @param newGllccurusdcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGllccurusdcdtlnamt(
			nc.vo.pub.lang.UFDouble newGllccurusdcdtlnamt) {
		setAttributeValue("gllccurusdcdtlnamt", newGllccurusdcdtlnamt);
	}

	/**
	 * ����availcdtlnamt��Getter����.���������������Ŷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getAvailcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("availcdtlnamt");
	}

	/**
	 * ����availcdtlnamt��Setter����.���������������Ŷ�� ��������:
	 * 
	 * @param newAvailcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setAvailcdtlnamt(nc.vo.pub.lang.UFDouble newAvailcdtlnamt) {
		setAttributeValue("availcdtlnamt", newAvailcdtlnamt);
	}

	/**
	 * ����olcavailcdtlnamt��Getter����.��������������֯�������Ŷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getOlcavailcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("olcavailcdtlnamt");
	}

	/**
	 * ����olcavailcdtlnamt��Setter����.��������������֯�������Ŷ�� ��������:
	 * 
	 * @param newOlcavailcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setOlcavailcdtlnamt(nc.vo.pub.lang.UFDouble newOlcavailcdtlnamt) {
		setAttributeValue("olcavailcdtlnamt", newOlcavailcdtlnamt);
	}

	/**
	 * ����glcavailcdtlnamt��Getter����.�����������ü��ű������Ŷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGlcavailcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("glcavailcdtlnamt");
	}

	/**
	 * ����glcavailcdtlnamt��Setter����.�����������ü��ű������Ŷ�� ��������:
	 * 
	 * @param newGlcavailcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGlcavailcdtlnamt(nc.vo.pub.lang.UFDouble newGlcavailcdtlnamt) {
		setAttributeValue("glcavailcdtlnamt", newGlcavailcdtlnamt);
	}

	/**
	 * ����gllcavailcdtlnamt��Getter����.������������ȫ�ֱ������Ŷ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGllcavailcdtlnamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("gllcavailcdtlnamt");
	}

	/**
	 * ����gllcavailcdtlnamt��Setter����.������������ȫ�ֱ������Ŷ�� ��������:
	 * 
	 * @param newGllcavailcdtlnamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGllcavailcdtlnamt(
			nc.vo.pub.lang.UFDouble newGllcavailcdtlnamt) {
		setAttributeValue("gllcavailcdtlnamt", newGllcavailcdtlnamt);
	}

	/**
	 * ����periodcount��Getter����.���������ڼ� ��������:
	 * 
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getPeriodcount() {
		return (java.lang.Integer) getAttributeValue("periodcount");
	}

	/**
	 * ����periodcount��Setter����.���������ڼ� ��������:
	 * 
	 * @param newPeriodcount
	 *            java.lang.Integer
	 */
	public void setPeriodcount(java.lang.Integer newPeriodcount) {
		setAttributeValue("periodcount", newPeriodcount);
	}

	/**
	 * ����periodunit��Getter����.���������ڼ䵥λ ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPeriodunit() {
		return (java.lang.String) getAttributeValue("periodunit");
	}

	/**
	 * ����periodunit��Setter����.���������ڼ䵥λ ��������:
	 * 
	 * @param newPeriodunit
	 *            java.lang.String
	 */
	public void setPeriodunit(java.lang.String newPeriodunit) {
		setAttributeValue("periodunit", newPeriodunit);
	}

	/**
	 * ����begindate��Getter����.����������ʼ���� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getBegindate() {
		return (nc.vo.pub.lang.UFDate) getAttributeValue("begindate");
	}

	/**
	 * ����begindate��Setter����.����������ʼ���� ��������:
	 * 
	 * @param newBegindate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setBegindate(nc.vo.pub.lang.UFDate newBegindate) {
		setAttributeValue("begindate", newBegindate);
	}

	/**
	 * ����enddate��Getter����.���������������� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getEnddate() {
		return (nc.vo.pub.lang.UFDate) getAttributeValue("enddate");
	}

	/**
	 * ����enddate��Setter����.���������������� ��������:
	 * 
	 * @param newEnddate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setEnddate(nc.vo.pub.lang.UFDate newEnddate) {
		setAttributeValue("enddate", newEnddate);
	}

	/**
	 * ����initenddate��Getter����.����������ʼ�������� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getInitenddate() {
		return (nc.vo.pub.lang.UFDate) getAttributeValue("initenddate");
	}

	/**
	 * ����initenddate��Setter����.����������ʼ�������� ��������:
	 * 
	 * @param newInitenddate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setInitenddate(nc.vo.pub.lang.UFDate newInitenddate) {
		setAttributeValue("initenddate", newInitenddate);
	}

	/**
	 * ����pk_guarantee��Getter����.��������������ͬ ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_guarantee() {
		return (java.lang.String) getAttributeValue("pk_guarantee");
	}

	/**
	 * ����pk_guarantee��Setter����.��������������ͬ ��������:
	 * 
	 * @param newPk_guarantee
	 *            java.lang.String
	 */
	public void setPk_guarantee(java.lang.String newPk_guarantee) {
		setAttributeValue("pk_guarantee", newPk_guarantee);
	}

	/**
	 * ����guaranteetype��Getter����.��������������ʽ ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getGuaranteetype() {
		return (java.lang.String) getAttributeValue("guaranteetype");
	}

	/**
	 * ����guaranteetype��Setter����.��������������ʽ ��������:
	 * 
	 * @param newGuaranteetype
	 *            java.lang.String
	 */
	public void setGuaranteetype(java.lang.String newGuaranteetype) {
		setAttributeValue("guaranteetype", newGuaranteetype);
	}

	/**
	 * ����guaranteeamt��Getter����.��������ռ�õ������ ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGuaranteeamt() {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue("guaranteeamt");
	}

	/**
	 * ����guaranteeamt��Setter����.��������ռ�õ������ ��������:
	 * 
	 * @param newGuaranteeamt
	 *            nc.vo.pub.lang.UFDouble
	 */
	public void setGuaranteeamt(nc.vo.pub.lang.UFDouble newGuaranteeamt) {
		setAttributeValue("guaranteeamt", newGuaranteeamt);
	}

	/**
	 * ����frozendate��Getter����.���������������� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getFrozendate() {
		return (nc.vo.pub.lang.UFDate) getAttributeValue("frozendate");
	}

	/**
	 * ����frozendate��Setter����.���������������� ��������:
	 * 
	 * @param newFrozendate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setFrozendate(nc.vo.pub.lang.UFDate newFrozendate) {
		setAttributeValue("frozendate", newFrozendate);
	}

	/**
	 * ����frozener��Getter����.���������������Ա ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getFrozener() {
		return (java.lang.String) getAttributeValue("frozener");
	}

	/**
	 * ����frozener��Setter����.���������������Ա ��������:
	 * 
	 * @param newFrozener
	 *            java.lang.String
	 */
	public void setFrozener(java.lang.String newFrozener) {
		setAttributeValue("frozener", newFrozener);
	}

	/**
	 * ����terminator��Getter����.����������ֹ����Ա ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTerminator() {
		return (java.lang.String) getAttributeValue("terminator");
	}

	/**
	 * ����terminator��Setter����.����������ֹ����Ա ��������:
	 * 
	 * @param newTerminator
	 *            java.lang.String
	 */
	public void setTerminator(java.lang.String newTerminator) {
		setAttributeValue("terminator", newTerminator);
	}

	/**
	 * ����vdef16��Getter����.���������Զ�����16 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef16() {
		return (java.lang.String) getAttributeValue("vdef16");
	}

	/**
	 * ����vdef16��Setter����.���������Զ�����16 ��������:
	 * 
	 * @param newVdef16
	 *            java.lang.String
	 */
	public void setVdef16(java.lang.String newVdef16) {
		setAttributeValue("vdef16", newVdef16);
	}

	/**
	 * ����vdef17��Getter����.���������Զ�����17 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef17() {
		return (java.lang.String) getAttributeValue("vdef17");
	}

	/**
	 * ����vdef17��Setter����.���������Զ�����17 ��������:
	 * 
	 * @param newVdef17
	 *            java.lang.String
	 */
	public void setVdef17(java.lang.String newVdef17) {
		setAttributeValue("vdef17", newVdef17);
	}

	/**
	 * ����vdef18��Getter����.���������Զ�����18 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef18() {
		return (java.lang.String) getAttributeValue("vdef18");
	}

	/**
	 * ����vdef18��Setter����.���������Զ�����18 ��������:
	 * 
	 * @param newVdef18
	 *            java.lang.String
	 */
	public void setVdef18(java.lang.String newVdef18) {
		setAttributeValue("vdef18", newVdef18);
	}

	/**
	 * ����vdef19��Getter����.���������Զ�����19 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef19() {
		return (java.lang.String) getAttributeValue("vdef19");
	}

	/**
	 * ����vdef19��Setter����.���������Զ�����19 ��������:
	 * 
	 * @param newVdef19
	 *            java.lang.String
	 */
	public void setVdef19(java.lang.String newVdef19) {
		setAttributeValue("vdef19", newVdef19);
	}

	/**
	 * ����vdef20��Getter����.���������Զ�����20 ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVdef20() {
		return (java.lang.String) getAttributeValue("vdef20");
	}

	/**
	 * ����vdef20��Setter����.���������Զ�����20 ��������:
	 * 
	 * @param newVdef20
	 *            java.lang.String
	 */
	public void setVdef20(java.lang.String newVdef20) {
		setAttributeValue("vdef20", newVdef20);
	}

	/**
	 * ����pk_billtypecode��Getter����.���������������� ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_billtypecode() {
		return (java.lang.String) getAttributeValue("pk_billtypecode");
	}

	/**
	 * ����pk_billtypecode��Setter����.���������������� ��������:
	 * 
	 * @param newPk_billtypecode
	 *            java.lang.String
	 */
	public void setPk_billtypecode(java.lang.String newPk_billtypecode) {
		setAttributeValue("pk_billtypecode", newPk_billtypecode);
	}

	/**
	 * ����pk_billtypeid��Getter����.�������������������� ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_billtypeid() {
		return (java.lang.String) getAttributeValue("pk_billtypeid");
	}

	/**
	 * ����pk_billtypeid��Setter����.�������������������� ��������:
	 * 
	 * @param newPk_billtypeid
	 *            java.lang.String
	 */
	public void setPk_billtypeid(java.lang.String newPk_billtypeid) {
		setAttributeValue("pk_billtypeid", newPk_billtypeid);
	}

	/**
	 * ����billmaker��Getter����.���������Ƶ��� ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getBillmaker() {
		return (java.lang.String) getAttributeValue("billmaker");
	}

	/**
	 * ����billmaker��Setter����.���������Ƶ��� ��������:
	 * 
	 * @param newBillmaker
	 *            java.lang.String
	 */
	public void setBillmaker(java.lang.String newBillmaker) {
		setAttributeValue("billmaker", newBillmaker);
	}

	/**
	 * ����billmakedate��Getter����.���������Ƶ����� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getBillmakedate() {
		return (nc.vo.pub.lang.UFDate) getAttributeValue("billmakedate");
	}

	/**
	 * ����billmakedate��Setter����.���������Ƶ����� ��������:
	 * 
	 * @param newBillmakedate
	 *            nc.vo.pub.lang.UFDate
	 */
	public void setBillmakedate(nc.vo.pub.lang.UFDate newBillmakedate) {
		setAttributeValue("billmakedate", newBillmakedate);
	}

	/**
	 * ����billmaketime��Getter����.���������Ƶ�ʱ�� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getBillmaketime() {
		return (nc.vo.pub.lang.UFDateTime) getAttributeValue("billmaketime");
	}

	/**
	 * ����billmaketime��Setter����.���������Ƶ�ʱ�� ��������:
	 * 
	 * @param newBillmaketime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setBillmaketime(nc.vo.pub.lang.UFDateTime newBillmaketime) {
		setAttributeValue("billmaketime", newBillmaketime);
	}

	/**
	 * ����versionorigin��Getter����.���������汾��Դ ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getVersionorigin() {
		return (java.lang.String) getAttributeValue("versionorigin");
	}

	/**
	 * ����versionorigin��Setter����.���������汾��Դ ��������:
	 * 
	 * @param newVersionorigin
	 *            java.lang.String
	 */
	public void setVersionorigin(java.lang.String newVersionorigin) {
		setAttributeValue("versionorigin", newVersionorigin);
	}

	/**
	 * ����versiontime��Getter����.���������汾���� ��������:
	 * 
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDate getVersiontime() {
		return (nc.vo.pub.lang.UFDate) getAttributeValue("versiontime");
	}

	/**
	 * ����versiontime��Setter����.���������汾���� ��������:
	 * 
	 * @param newVersiontime
	 *            nc.vo.pub.lang.UFDateTime
	 */
	public void setVersiontime(nc.vo.pub.lang.UFDate newVersiontime) {
		setAttributeValue("versiontime", newVersiontime);
	}

//	/**
//	 * ����dr��Getter����.��������dr ��������:
//	 * 
//	 * @return java.lang.Integer
//	 */
//	public java.lang.Integer getDr() {
//		return (java.lang.Integer) getAttributeValue("dr");
//	}
//
//	/**
//	 * ����dr��Setter����.��������dr ��������:
//	 * 
//	 * @param newDr
//	 *            java.lang.Integer
//	 */
//	public void setDr(java.lang.Integer newDr) {
//		setAttributeValue("dr", newDr);
//	}

	/**
	 * <p>
	 * ȡ�ø�VO�����ֶ�.
	 * <p>
	 * ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getParentPKFieldName() {
		return null;
	}

	/**
	 * <p>
	 * ȡ�ñ�����.
	 * <p>
	 * ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPKFieldName() {
		return "pk_protocol";
	}


	/**
	 * ����pk_fundplan��Getter����.���������ʽ�ƻ���Ŀ ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPk_fundplan() {
		return (java.lang.String) getAttributeValue("pk_fundplan");
	}

	/**
	 * ����pk_fundplan��Setter����.���������ʽ�ƻ���Ŀ ��������:
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
	 * ���ر�����.
	 * <p>
	 * ��������:
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "ccc_bankprotocol";
	}
}
