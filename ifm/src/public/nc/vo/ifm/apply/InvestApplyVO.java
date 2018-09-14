package nc.vo.ifm.apply;

import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加类的描述信息
 * </p>
 *  创建日期:2018-9-2
 * @author YONYOU NC
 * @version NCPrj ??
 */
public class InvestApplyVO extends nc.vo.pub.SuperVO{
	    
	
	
    public static final String PK_APPLY = "pk_apply";
    public static final String VBILLNO = "vbillno";
    public static final String VBILLSTATUS = "vbillstatus";
    public static final String PK_GROUP = "pk_group";
    public static final String PK_ORG = "pk_org";
    public static final String PK_ORG_V = "pk_org_v";
    public static final String PK_CURRTYPE = "pk_currtype";
    public static final String PK_OLCCURR = "pk_olccurr";
    public static final String REMARK = "remark";
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
    public static final String OLCRATE = "olcrate";
    public static final String OLCMONEY = "olcmoney";
    public static final String GLCRATE = "glcrate";
    public static final String GLCMONEY = "glcmoney";
    public static final String GLLCRATE = "gllcrate";
    public static final String GLLMONEY = "gllmoney";
    public static final String BILLMAKER = "billmaker";
    public static final String BILLMAKEDATE = "billmakedate";
    public static final String BILLMAKETIME = "billmaketime";
    public static final String APPROVER = "approver";
    public static final String APPROVEDATE = "approvedate";
    public static final String APPROVENOTE = "approvenote";
    public static final String CREATOR = "creator";
    public static final String CREATIONTIME = "creationtime";
    public static final String MODIFIER = "modifier";
    public static final String MODIFIEDTIME = "modifiedtime";
    public static final String PK_BUSITYPE = "pk_busitype";
    public static final String PK_BILLTYPEID = "pk_billtypeid";
    public static final String PK_BILLTYPECODE = "pk_billtypecode";
    public static final String ISSUEBANK = "issuebank";
    public static final String BOUNDARY = "boundary";
    public static final String BANKNETWORK = "banknetwork";
    public static final String BILLSTATUS = "billstatus";
    public static final String SETTLEACCOUNT = "settleaccount";
    public static final String INVEST = "invest";
    public static final String PRODUCTCODE = "productcode";
    public static final String PRODUCTNAME = "productname";
    public static final String MONEY = "money";
    public static final String EVENTYPE = "eventype";
    public static final String RISK = "risk";
    public static final String PURCHASEDATE = "purchasedate";
    public static final String ENDDATE = "enddate";
    public static final String INTERESTDATE = "interestdate";
    public static final String INTERESTDAY = "interestday";
    public static final String EXPECTEDRATE = "expectedrate";
    public static final String EXPECTEDMONEY = "expectedmoney";
    public static final String PAYTYPE = "paytype";
    public static final String PAYPERIOD = "payperiod";
    public static final String SETTLEDATE = "settledate";
    
  
	public static String getPkApply() {
		return PK_APPLY;
	}
	public static String getPkGroup() {
		return PK_GROUP;
	}
	public static String getPkOrg() {
		return PK_ORG;
	}
	public static String getPkOrgV() {
		return PK_ORG_V;
	}
	public static String getPkCurrtype() {
		return PK_CURRTYPE;
	}
	public static String getPkOlccurr() {
		return PK_OLCCURR;
	}
	public static String getPkBusitype() {
		return PK_BUSITYPE;
	}
	public static String getPkBilltypeid() {
		return PK_BILLTYPEID;
	}
	public static String getPkBilltypecode() {
		return PK_BILLTYPECODE;
	}

	/**
	 * 属性 pk_apply的Getter方法.属性名：主键
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getPk_apply () {
		return (java.lang.String) getAttributeValue(PK_APPLY);
	}   
	public void setPk_apply (java.lang.String newPk_apply ) {
		setAttributeValue(PK_APPLY, newPk_apply);
	}
	/**
	 * 属性 vbillno的Getter方法.属性名：申购编号
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVbillno () {
		return (java.lang.String) getAttributeValue(VBILLNO);
	}   
	public void setVbillno (java.lang.String newVbillno ) {
		setAttributeValue(VBILLNO, newVbillno);
	}
	/**
	 * 属性 vbillstatus的Getter方法.属性名：审批状态
	 *  创建日期:2018-9-2
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getVbillstatus () {
		return (java.lang.Integer) getAttributeValue(VBILLSTATUS);
	}   
	public void setVbillstatus (java.lang.Integer newVbillstatus ) {
		setAttributeValue(VBILLSTATUS, newVbillstatus);
	}
	/**
	 * 属性 pk_group的Getter方法.属性名：集团
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getPk_group () {
		return (java.lang.String) getAttributeValue(PK_GROUP);
	}   
	public void setPk_group (java.lang.String newPk_group ) {
		setAttributeValue(PK_GROUP, newPk_group);
	}
	/**
	 * 属性 pk_org的Getter方法.属性名：财务组织
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org () {
		return (java.lang.String) getAttributeValue(PK_ORG);
	}   
	public void setPk_org (java.lang.String newPk_org ) {
		setAttributeValue(PK_ORG, newPk_org);
	}
	/**
	 * 属性 pk_org_v的Getter方法.属性名：财务组织版本
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getPk_org_v () {
		return (java.lang.String) getAttributeValue(PK_ORG_V);
	}   
	public void setPk_org_v (java.lang.String newPk_org_v ) {
		setAttributeValue(PK_ORG_V, newPk_org_v);
	}
	/**
	 * 属性 pk_currtype的Getter方法.属性名：币种
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getPk_currtype () {
		return (java.lang.String) getAttributeValue(PK_CURRTYPE);
	}   
	public void setPk_currtype (java.lang.String newPk_currtype ) {
		setAttributeValue(PK_CURRTYPE, newPk_currtype);
	}
	/**
	 * 属性 pk_olccurr的Getter方法.属性名：组织本位币
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getPk_olccurr () {
		return (java.lang.String) getAttributeValue(PK_OLCCURR);
	}   
	public void setPk_olccurr (java.lang.String newPk_olccurr ) {
		setAttributeValue(PK_OLCCURR, newPk_olccurr);
	}
	/**
	 * 属性 remark的Getter方法.属性名：备注
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getRemark () {
		return (java.lang.String) getAttributeValue(REMARK);
	}   
	public void setRemark (java.lang.String newRemark ) {
		setAttributeValue(REMARK, newRemark);
	}
	/**
	 * 属性 vdef1的Getter方法.属性名：自定义项1
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef1 () {
		return (java.lang.String) getAttributeValue(VDEF1);
	}   
	public void setVdef1 (java.lang.String newVdef1 ) {
		setAttributeValue(VDEF1, newVdef1);
	}
	/**
	 * 属性 vdef2的Getter方法.属性名：自定义项2
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef2 () {
		return (java.lang.String) getAttributeValue(VDEF2);
	}   
	public void setVdef2 (java.lang.String newVdef2 ) {
		setAttributeValue(VDEF2, newVdef2);
	}
	/**
	 * 属性 vdef3的Getter方法.属性名：自定义项3
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef3 () {
		return (java.lang.String) getAttributeValue(VDEF3);
	}   
	public void setVdef3 (java.lang.String newVdef3 ) {
		setAttributeValue(VDEF3, newVdef3);
	}
	/**
	 * 属性 vdef4的Getter方法.属性名：自定义项4
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef4 () {
		return (java.lang.String) getAttributeValue(VDEF4);
	}   
	public void setVdef4 (java.lang.String newVdef4 ) {
		setAttributeValue(VDEF4, newVdef4);
	}
	/**
	 * 属性 vdef5的Getter方法.属性名：自定义项5
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef5 () {
		return (java.lang.String) getAttributeValue(VDEF5);
	}   
	public void setVdef5 (java.lang.String newVdef5 ) {
		setAttributeValue(VDEF5, newVdef5);
	}
	/**
	 * 属性 vdef6的Getter方法.属性名：自定义项6
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef6 () {
		return (java.lang.String) getAttributeValue(VDEF6);
	}   
	public void setVdef6 (java.lang.String newVdef6 ) {
		setAttributeValue(VDEF6, newVdef6);
	}
	/**
	 * 属性 vdef7的Getter方法.属性名：自定义项7
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef7 () {
		return (java.lang.String) getAttributeValue(VDEF7);
	}   
	public void setVdef7 (java.lang.String newVdef7 ) {
		setAttributeValue(VDEF7, newVdef7);
	}
	/**
	 * 属性 vdef8的Getter方法.属性名：自定义项8
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef8 () {
		return (java.lang.String) getAttributeValue(VDEF8);
	}   
	public void setVdef8 (java.lang.String newVdef8 ) {
		setAttributeValue(VDEF8, newVdef8);
	}
	/**
	 * 属性 vdef9的Getter方法.属性名：自定义项9
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef9 () {
		return (java.lang.String) getAttributeValue(VDEF9);
	}   
	public void setVdef9 (java.lang.String newVdef9 ) {
		setAttributeValue(VDEF9, newVdef9);
	}
	/**
	 * 属性 vdef10的Getter方法.属性名：自定义项10
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef10 () {
		return (java.lang.String) getAttributeValue(VDEF10);
	}   
	public void setVdef10 (java.lang.String newVdef10 ) {
		setAttributeValue(VDEF10, newVdef10);
	}
	/**
	 * 属性 vdef11的Getter方法.属性名：自定义项11
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef11 () {
		return (java.lang.String) getAttributeValue(VDEF11);
	}   
	public void setVdef11 (java.lang.String newVdef11 ) {
		setAttributeValue(VDEF11, newVdef11);
	}
	/**
	 * 属性 vdef12的Getter方法.属性名：自定义项12
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef12 () {
		return (java.lang.String) getAttributeValue(VDEF12);
	}   
	public void setVdef12 (java.lang.String newVdef12 ) {
		setAttributeValue(VDEF12, newVdef12);
	}
	/**
	 * 属性 vdef13的Getter方法.属性名：自定义项13
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef13 () {
		return (java.lang.String) getAttributeValue(VDEF13);
	}   
	public void setVdef13 (java.lang.String newVdef13 ) {
		setAttributeValue(VDEF13, newVdef13);
	}
	/**
	 * 属性 vdef14的Getter方法.属性名：自定义项14
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef14 () {
		return (java.lang.String) getAttributeValue(VDEF14);
	}   
	public void setVdef14 (java.lang.String newVdef14 ) {
		setAttributeValue(VDEF14, newVdef14);
	}
	/**
	 * 属性 vdef15的Getter方法.属性名：自定义项15
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef15 () {
		return (java.lang.String) getAttributeValue(VDEF15);
	}   
	public void setVdef15 (java.lang.String newVdef15 ) {
		setAttributeValue(VDEF15, newVdef15);
	}
	/**
	 * 属性 vdef16的Getter方法.属性名：自定义项16
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef16 () {
		return (java.lang.String) getAttributeValue(VDEF16);
	}   
	public void setVdef16 (java.lang.String newVdef16 ) {
		setAttributeValue(VDEF16, newVdef16);
	}
	/**
	 * 属性 vdef17的Getter方法.属性名：自定义项17
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef17 () {
		return (java.lang.String) getAttributeValue(VDEF17);
	}   
	public void setVdef17 (java.lang.String newVdef17 ) {
		setAttributeValue(VDEF17, newVdef17);
	}
	/**
	 * 属性 vdef18的Getter方法.属性名：自定义项18
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef18 () {
		return (java.lang.String) getAttributeValue(VDEF18);
	}   
	public void setVdef18 (java.lang.String newVdef18 ) {
		setAttributeValue(VDEF18, newVdef18);
	}
	/**
	 * 属性 vdef19的Getter方法.属性名：自定义项19
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef19 () {
		return (java.lang.String) getAttributeValue(VDEF19);
	}   
	public void setVdef19 (java.lang.String newVdef19 ) {
		setAttributeValue(VDEF19, newVdef19);
	}
	/**
	 * 属性 vdef20的Getter方法.属性名：自定义项20
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getVdef20 () {
		return (java.lang.String) getAttributeValue(VDEF20);
	}   
	public void setVdef20 (java.lang.String newVdef20 ) {
		setAttributeValue(VDEF20, newVdef20);
	}
	/**
	 * 属性 olcrate的Getter方法.属性名：组织本币汇率
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getOlcrate () {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue(OLCRATE);
	}   
	public void setOlcrate (nc.vo.pub.lang.UFDouble newOlcrate ) {
		setAttributeValue(OLCRATE, newOlcrate);
	}
	/**
	 * 属性 olcmoney的Getter方法.属性名：组织本币金额
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getOlcmoney () {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue(OLCMONEY);
	}   
	public void setOlcmoney (nc.vo.pub.lang.UFDouble newOlcmoney ) {
		setAttributeValue(OLCMONEY, newOlcmoney);
	}
	/**
	 * 属性 glcrate的Getter方法.属性名：集团本币汇率
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGlcrate () {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue(GLCRATE);
	}   
	public void setGlcrate (nc.vo.pub.lang.UFDouble newGlcrate ) {
		setAttributeValue(GLCRATE, newGlcrate);
	}
	/**
	 * 属性 glcmoney的Getter方法.属性名：集团本币金额
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGlcmoney () {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue(GLCMONEY);
	}   
	public void setGlcmoney (nc.vo.pub.lang.UFDouble newGlcmoney ) {
		setAttributeValue(GLCMONEY, newGlcmoney);
	}
	/**
	 * 属性 gllcrate的Getter方法.属性名：全局本币汇率
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGllcrate () {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue(GLLCRATE);
	}   
	public void setGllcrate (nc.vo.pub.lang.UFDouble newGllcrate ) {
		setAttributeValue(GLLCRATE, newGllcrate);
	}
	/**
	 * 属性 gllmoney的Getter方法.属性名：全局本币金额
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getGllmoney () {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue(GLLMONEY);
	}   
	public void setGllmoney (nc.vo.pub.lang.UFDouble newGllmoney ) {
		setAttributeValue(GLLMONEY, newGllmoney);
	}
	/**
	 * 属性 billmaker的Getter方法.属性名：制单人
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getBillmaker () {
		return (java.lang.String) getAttributeValue(BILLMAKER);
	}   
	public void setBillmaker (java.lang.String newBillmaker ) {
		setAttributeValue(BILLMAKER, newBillmaker);
	}
	/**
	 * 属性 billmakedate的Getter方法.属性名：制单日期
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getBillmakedate () {
		return (nc.vo.pub.lang.UFDate) getAttributeValue(BILLMAKEDATE);
	}   
	public void setBillmakedate (nc.vo.pub.lang.UFDate newBillmakedate ) {
		setAttributeValue(BILLMAKEDATE, newBillmakedate);
	}
	/**
	 * 属性 billmaketime的Getter方法.属性名：制单时间
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getBillmaketime () {
		return (nc.vo.pub.lang.UFDateTime) getAttributeValue(BILLMAKETIME);
	}   
	public void setBillmaketime (nc.vo.pub.lang.UFDateTime newBillmaketime ) {
		setAttributeValue(BILLMAKETIME, newBillmaketime);
	}
	/**
	 * 属性 approver的Getter方法.属性名：审批人
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getApprover () {
		return (java.lang.String) getAttributeValue(APPROVER);
	}   
	public void setApprover (java.lang.String newApprover ) {
		setAttributeValue(APPROVER, newApprover);
	}
	/**
	 * 属性 approvedate的Getter方法.属性名：审批日期
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getApprovedate () {
		return (nc.vo.pub.lang.UFDate) getAttributeValue(APPROVEDATE);
	}   
	public void setApprovedate (nc.vo.pub.lang.UFDate newApprovedate ) {
		setAttributeValue(APPROVEDATE, newApprovedate);
	}
	/**
	 * 属性 approvenote的Getter方法.属性名：审批批语
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getApprovenote () {
		return (java.lang.String) getAttributeValue(APPROVENOTE);
	}   
	public void setApprovenote (java.lang.String newApprovenote ) {
		setAttributeValue(APPROVENOTE, newApprovenote);
	}
	/**
	 * 属性 creator的Getter方法.属性名：创建人
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getCreator () {
		return (java.lang.String) getAttributeValue(CREATOR);
	}   
	public void setCreator (java.lang.String newCreator ) {
		setAttributeValue(CREATOR, newCreator);
	}
	/**
	 * 属性 creationtime的Getter方法.属性名：创建时间
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getCreationtime () {
		return (nc.vo.pub.lang.UFDateTime) getAttributeValue(CREATIONTIME);
	}   
	public void setCreationtime (nc.vo.pub.lang.UFDateTime newCreationtime ) {
		setAttributeValue(CREATIONTIME, newCreationtime);
	}
	/**
	 * 属性 modifier的Getter方法.属性名：最后修改人
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getModifier () {
		return (java.lang.String) getAttributeValue(MODIFIER);
	}   
	public void setModifier (java.lang.String newModifier ) {
		setAttributeValue(MODIFIER, newModifier);
	}
	/**
	 * 属性 modifiedtime的Getter方法.属性名：最后修改时间
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getModifiedtime () {
		return (nc.vo.pub.lang.UFDateTime) getAttributeValue(MODIFIEDTIME);
	}   
	public void setModifiedtime (nc.vo.pub.lang.UFDateTime newModifiedtime ) {
		setAttributeValue(MODIFIEDTIME, newModifiedtime);
	}
	/**
	 * 属性 pk_busitype的Getter方法.属性名：业务流程
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getPk_busitype () {
		return (java.lang.String) getAttributeValue(PK_BUSITYPE);
	}   
	public void setPk_busitype (java.lang.String newPk_busitype ) {
		setAttributeValue(PK_BUSITYPE, newPk_busitype);
	}
	/**
	 * 属性 pk_billtypeid的Getter方法.属性名：单据类型主键
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getPk_billtypeid () {
		return (java.lang.String) getAttributeValue(PK_BILLTYPEID);
	}   
	public void setPk_billtypeid (java.lang.String newPk_billtypeid ) {
		setAttributeValue(PK_BILLTYPEID, newPk_billtypeid);
	}
	/**
	 * 属性 pk_billtypecode的Getter方法.属性名：单据类型
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getPk_billtypecode () {
		return (java.lang.String) getAttributeValue(PK_BILLTYPECODE);
	}   
	public void setPk_billtypecode (java.lang.String newPk_billtypecode ) {
		setAttributeValue(PK_BILLTYPECODE, newPk_billtypecode);
	}
	/**
	 * 属性 issuebank的Getter方法.属性名：发行银行
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getIssuebank () {
		return (java.lang.String) getAttributeValue(ISSUEBANK);
	}   
	public void setIssuebank (java.lang.String newIssuebank ) {
		setAttributeValue(ISSUEBANK, newIssuebank);
	}
	/**
	 * 属性 boundary的Getter方法.属性名：境内外
	 *  创建日期:2018-9-2
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getBoundary () {
		return (java.lang.Integer) getAttributeValue(BOUNDARY);
	}   
	public void setBoundary (java.lang.Integer newBoundary ) {
		setAttributeValue(BOUNDARY, newBoundary);
	}
	/**
	 * 属性 banknetwork的Getter方法.属性名：银行网点
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getBanknetwork () {
		return (java.lang.String) getAttributeValue(BANKNETWORK);
	}   
	public void setBanknetwork (java.lang.String newBanknetwork ) {
		setAttributeValue(BANKNETWORK, newBanknetwork);
	}
	/**
	 * 属性 billstatus的Getter方法.属性名：单据状态
	 *  创建日期:2018-9-2
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getBillstatus () {
		return (java.lang.Integer) getAttributeValue(BILLSTATUS);
	}   
	public void setBillstatus (java.lang.Integer newBillstatus ) {
		setAttributeValue(BILLSTATUS, newBillstatus);
	}
	/**
	 * 属性 settleaccount的Getter方法.属性名：结算账户
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getSettleaccount () {
		return (java.lang.String) getAttributeValue(SETTLEACCOUNT);
	}   
	public void setSettleaccount (java.lang.String newSettleaccount ) {
		setAttributeValue(SETTLEACCOUNT, newSettleaccount);
	}
	/**
	 * 属性 invest的Getter方法.属性名：理财账户
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getInvest () {
		return (java.lang.String) getAttributeValue(INVEST);
	}   
	public void setInvest (java.lang.String newInvest ) {
		setAttributeValue(INVEST, newInvest);
	}
	/**
	 * 属性 productcode的Getter方法.属性名：产品编码
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getProductcode () {
		return (java.lang.String) getAttributeValue(PRODUCTCODE);
	}   
	public void setProductcode (java.lang.String newProductcode ) {
		setAttributeValue(PRODUCTCODE, newProductcode);
	}
	/**
	 * 属性 productname的Getter方法.属性名：产品名称
	 *  创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getProductname () {
		return (java.lang.String) getAttributeValue(PRODUCTNAME);
	}   
	public void setProductname (java.lang.String newProductname ) {
		setAttributeValue(PRODUCTNAME, newProductname);
	}
	/**
	 * 属性 money的Getter方法.属性名：理财金额
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getMoney () {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue(MONEY);
	}   
	public void setMoney (nc.vo.pub.lang.UFDouble newMoney ) {
		setAttributeValue(MONEY, newMoney);
	}
	/**
	 * 属性 eventype的Getter方法.属性名：保本类型
	 *  创建日期:2018-9-2
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getEventype () {
		return (java.lang.Integer) getAttributeValue(EVENTYPE);
	}   
	public void setEventype (java.lang.Integer newEventype ) {
		setAttributeValue(EVENTYPE, newEventype);
	}
	/**
	 * 属性 risk的Getter方法.属性名：风险类型
	 *  创建日期:2018-9-2
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getRisk () {
		return (java.lang.Integer) getAttributeValue(RISK);
	}   
	public void setRisk (java.lang.Integer newRisk ) {
		setAttributeValue(RISK, newRisk);
	}
	/**
	 * 属性 purchasedate的Getter方法.属性名：购买日期
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getPurchasedate () {
		return (nc.vo.pub.lang.UFDate) getAttributeValue(PURCHASEDATE);
	}   
	public void setPurchasedate (nc.vo.pub.lang.UFDate newPurchasedate ) {
		setAttributeValue(PURCHASEDATE, newPurchasedate);
	}
	/**
	 * 属性 enddate的Getter方法.属性名：到期日期
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getEnddate () {
		return (nc.vo.pub.lang.UFDate) getAttributeValue(ENDDATE);
	}   
	public void setEnddate (nc.vo.pub.lang.UFDate newEnddate ) {
		setAttributeValue(ENDDATE, newEnddate);
	}
	/**
	 * 属性 interestdate的Getter方法.属性名：起息日期
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getInterestdate () {
		return (nc.vo.pub.lang.UFDate) getAttributeValue(INTERESTDATE);
	}   
	public void setInterestdate (nc.vo.pub.lang.UFDate newInterestdate ) {
		setAttributeValue(INTERESTDATE, newInterestdate);
	}
	/**
	 * 属性 interestday的Getter方法.属性名：利息天数
	 *  创建日期:2018-9-2
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getInterestday () {
		return (java.lang.Integer) getAttributeValue(INTERESTDAY);
	}   
	public void setInterestday (java.lang.Integer newInterestday ) {
		setAttributeValue(INTERESTDAY, newInterestday);
	}
	/**
	 * 属性 expectedrate的Getter方法.属性名：预期收益率
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getExpectedrate () {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue(EXPECTEDRATE);
	}   
	public void setExpectedrate (nc.vo.pub.lang.UFDouble newExpectedrate ) {
		setAttributeValue(EXPECTEDRATE, newExpectedrate);
	}
	/**
	 * 属性 expectedmoney的Getter方法.属性名：预期收益
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDouble
	 */
	public nc.vo.pub.lang.UFDouble getExpectedmoney () {
		return (nc.vo.pub.lang.UFDouble) getAttributeValue(EXPECTEDMONEY);
	}   
	public void setExpectedmoney (nc.vo.pub.lang.UFDouble newExpectedmoney ) {
		setAttributeValue(EXPECTEDMONEY, newExpectedmoney);
	}
	/**
	 * 属性 paytype的Getter方法.属性名：付息规则
	 *  创建日期:2018-9-2
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getPaytype () {
		return (java.lang.Integer) getAttributeValue(PAYTYPE);
	}   
	public void setPaytype (java.lang.Integer newPaytype ) {
		setAttributeValue(PAYTYPE, newPaytype);
	}
	/**
	 * 属性 payperiod的Getter方法.属性名：付息周期
	 *  创建日期:2018-9-2
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getPayperiod () {
		return (java.lang.Integer) getAttributeValue(PAYPERIOD);
	}   
	public void setPayperiod (java.lang.Integer newPayperiod ) {
		setAttributeValue(PAYPERIOD, newPayperiod);
	}
	/**
	 * 属性 settledate的Getter方法.属性名：结息日
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDate
	 */
	public nc.vo.pub.lang.UFDate getSettledate () {
		return (nc.vo.pub.lang.UFDate) getAttributeValue(SETTLEDATE);
	}   
	public void setSettledate (nc.vo.pub.lang.UFDate newSettledate ) {
		setAttributeValue(SETTLEDATE, newSettledate);
	}
	/**
	 * 属性 dr的Getter方法.属性名：dr
	 *  创建日期:2018-9-2
	 * @return java.lang.Integer
	 */
	public java.lang.Integer getDr () {
		return (java.lang.Integer) getAttributeValue("dr");
	}   
	public void setDr (java.lang.Integer newDr ) {
		setAttributeValue("dr", newDr);
	}
	/**
	 * 属性 ts的Getter方法.属性名：ts
	 *  创建日期:2018-9-2
	 * @return nc.vo.pub.lang.UFDateTime
	 */
	public nc.vo.pub.lang.UFDateTime getTs () {
		return (nc.vo.pub.lang.UFDateTime) getAttributeValue("ts");
	}   
	public void setTs (nc.vo.pub.lang.UFDateTime newTs ) {
		setAttributeValue("ts", newTs);
	}


	
	/**
	  * <p>取得父VO主键字段.
	  * <p>
	  * 创建日期:2018-9-2
	  * @return java.lang.String
	  */
	public java.lang.String getParentPKFieldName() {
	    return null;
	}   
    
	/**
	  * <p>取得表主键.
	  * <p>
	  * 创建日期:2018-9-2
	  * @return java.lang.String
	  */
	public java.lang.String getPKFieldName() {
			
		return "pk_apply";
	}
    
	/**
	 * <p>返回表名称
	 * <p>
	 * 创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "ifm_apply";
	}    
	
	/**
	 * <p>返回表名称.
	 * <p>
	 * 创建日期:2018-9-2
	 * @return java.lang.String
	 */
	public static java.lang.String getDefaultTableName() {
		return "ifm_apply";
	}    
    
    /**
	  * 按照默认方式创建构造子.
	  *
	  * 创建日期:2018-9-2
	  */
     public InvestApplyVO() {
		super();	
	}    
	
	
	@nc.vo.annotation.MDEntityInfo(beanFullclassName = "nc.vo.ifm.apply.InvestApplyVO" )
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta("ifm.InvestApplyVO");
		
   	}
     
}