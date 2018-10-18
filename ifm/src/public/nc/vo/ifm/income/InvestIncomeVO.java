package nc.vo.ifm.income;

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
 *   此处添加累的描述信息
 * </p>
 *  创建日期:2018-9-6
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class InvestIncomeVO extends SuperVO {
	
	
/**
*ifm_income表主键
*/
public static final String PK_INCOME = "pk_income";
//来源单据四个字段
public static final String PK_SRCBILL = "pk_srcbill";
public static final String PK_SRCBILLTYPE = "pk_srcbilltype";
public static final String SRCBILLTYPECODE = "srcbilltypecode";
public static final String SRCBILLNO = "srcbillno";
/**
*申购编号
*/
public static final String VBILLNO="vbillno";
;
/**
*审批状态
*/
public static final String VBILLSTATUS="vbillstatus";
;
/**
*集团
*/
public static final String PK_GROUP="pk_group";
;
/**
*财务组织
*/
public static final String PK_ORG="pk_org";
;
/**
*财务组织版本
*/
public static final String PK_ORG_V="pk_org_v";
;
/**
*币种
*/
public static final String PK_CURRTYPE="pk_currtype";
;
/**
*组织本位币
*/
public static final String PK_OLCCURR="pk_olccurr";
;
/**
*备注
*/
public static final String REMARK="remark";
;
/**
*自定义项1
*/
public static final String VDEF1="vdef1";
;
/**
*自定义项2
*/
public static final String VDEF2="vdef2";
;
/**
*自定义项3
*/
public static final String VDEF3="vdef3";
;
/**
*自定义项4
*/
public static final String VDEF4="vdef4";
;
/**
*自定义项5
*/
public static final String VDEF5="vdef5";
;
/**
*自定义项6
*/
public static final String VDEF6="vdef6";
;
/**
*自定义项7
*/
public static final String VDEF7="vdef7";
;
/**
*自定义项8
*/
public static final String VDEF8="vdef8";
;
/**
*自定义项9
*/
public static final String VDEF9="vdef9";
;
/**
*自定义项10
*/
public static final String VDEF10="vdef10";
;
/**
*自定义项11
*/
public static final String VDEF11="vdef11";
;
/**
*自定义项12
*/
public static final String VDEF12="vdef12";
;
/**
*自定义项13
*/
public static final String VDEF13="vdef13";
;
/**
*自定义项14
*/
public static final String VDEF14="vdef14";
;
/**
*自定义项15
*/
public static final String VDEF15="vdef15";
;
/**
*自定义项16
*/
public static final String VDEF16="vdef16";
;
/**
*自定义项17
*/
public static final String VDEF17="vdef17";
;
/**
*自定义项18
*/
public static final String VDEF18="vdef18";
;
/**
*自定义项19
*/
public static final String VDEF19="vdef19";
;
/**
*自定义项20
*/
public static final String VDEF20="vdef20";
;
/**
*组织本币汇率
*/
public static final String OLCRATE="olcrate";
;
/**
*组织本币金额
*/
public static final String OLCMOENY="olcmoeny";
;
/**
*集团本币汇率
*/
public static final String GLCRATE="glcrate";
;
/**
*集团本币金额
*/
public static final String GLCMOENY="glcmoeny";
;
/**
*全局本币汇率
*/
public static final String GLLCRATE="gllcrate";
;
/**
*全局本比金额
*/
public static final String GLLMOENY="gllmoeny";
;
/**
*制单人
*/
public static final String BILLMAKER="billmaker";
;
/**
*制单日期
*/
public UFDate billmakedate;
/**
*制单时间
*/
public UFDateTime billmaketime;
/**
*审批人
*/
public static final String APPROVER="approver";
;
/**
*审批日期
*/
public UFDate approvedate;
/**
*审批批语
*/
public static final String APPROVENOTE="approvenote";
;
/**
*创建人
*/
public static final String CREATOR="creator";
;
/**
*创建时间
*/
public UFDateTime creationtime;
/**
*最后修改人
*/
public static final String MODIFIER="modifier";
;
/**
*最后修改时间
*/
public UFDateTime modifiedtime;
/**
*业务流程
*/
public static final String PK_BUSITYPE="pk_busitype";
;
/**
*单据类型主键
*/
public static final String PK_BILLTYPEID="pk_billtypeid";
;
/**
*单据类型
*/
public static final String PK_BILLTYPECODE="pk_billtypecode";
;
/**
*产品代码
*/
public static final String PRODUCTCODE="productcode";
;
/**
*产品名称
*/
public static final String PRODUCTNAME="productname";
;
/**
*发行银行
*/
public static final String ISSUEBANK="issuebank";
;
/**
*单据状态
*/
public static final String BILLSTATUS="billstatus";
;
/**
*收款银行账户
*/
public static final String GATHERING="gathering";
;
/**
*理财账户
*/
public static final String INVESTACCOUNT="investaccount";
;
/**
*利息天数
*/
public static final String INTERESTDAY="interestday";
;
/**
*预期收益率
*/
public static final String EXPECTEDRATE="expectedrate";
;
/**
*预期收益
*/
public static final String EXPECTEDMONEY="expectedmoney";
;
/**
*到期日期
*/
public UFDate enddate;
/**
*实际收益
*/
public static final String ACTUALMOENY="actualmoeny";
;
/**
*收益税率
*/
public static final String INCOMERATE="incomerate";
;
/**
*收益税额
*/
public static final String INCOMEMONEY="incomemoney";
;
/**
 *到账日期
 */
public static final String GATHERINGDATE="gatheringdate";
;

public static final String BILLMAKEDATE = "billmakedate";
public static final String BILLMAKETIME = "billmaketime";
public static final String APPROVEDATE = "approvedate";
public static final String CREATIONTIME = "creationtime";
public static final String MODIFIEDTIME = "modifiedtime";
public static final String ENDDATE = "enddate";
public static final String SOURCE = "source";
public static final String INCOMEFUNDPLANPRO = "incomefundplanpro";
public static final String TAXFUNDPLANPRO = "taxfundplanpro";
public static final String INVESTVARIETY = "Investvariety";
public static final String PK_REDEEM = "pk_redeem";//赎回主键，接口用

/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
 * @return the pkIncome
 */
public static String getPkIncome() {
	return PK_INCOME;
}

/**
 * @return the pkGroup
 */
public static String getPkGroup() {
	return PK_GROUP;
}

/**
 * @return the pkOrg
 */
public static String getPkOrg() {
	return PK_ORG;
}

/**
 * @return the pkOrgV
 */
public static String getPkOrgV() {
	return PK_ORG_V;
}

/**
 * @return the pkCurrtype
 */
public static String getPkCurrtype() {
	return PK_CURRTYPE;
}

/**
 * @return the pkOlccurr
 */
public static String getPkOlccurr() {
	return PK_OLCCURR;
}

/**
 * @return the pkBusitype
 */
public static String getPkBusitype() {
	return PK_BUSITYPE;
}

/**
 * @return the pkBilltypeid
 */
public static String getPkBilltypeid() {
	return PK_BILLTYPEID;
}

/**
 * @return the pkBilltypecode
 */
public static String getPkBilltypecode() {
	return PK_BILLTYPECODE;
}

/**
* 属性 vbillno的Getter方法.属性名：申购编号
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVbillno() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VBILLNO);
} 

/**
* 属性vbillno的Setter方法.属性名：申购编号
* 创建日期:2018-9-6
* @param newVbillno java.lang.String
*/
public void setVbillno ( String vbillno) {
this.setAttributeValue( InvestIncomeVO.VBILLNO,vbillno);
} 
 
/**
* 属性 vbillstatus的Getter方法.属性名：审批状态
*  创建日期:2018-9-6
* @return nc.vo.pub.pf.BillStatusEnum
*/
public Integer getVbillstatus() {
return (Integer) this.getAttributeValue( InvestIncomeVO.VBILLSTATUS);
} 

/**
* 属性vbillstatus的Setter方法.属性名：审批状态
* 创建日期:2018-9-6
* @param newVbillstatus nc.vo.pub.pf.BillStatusEnum
*/
public void setVbillstatus ( Integer vbillstatus) {
this.setAttributeValue( InvestIncomeVO.VBILLSTATUS,vbillstatus);
} 
/**
 * 属性 source的Getter方法.属性名：审批状态
 *  创建日期:2018-9-6
 * @return nc.vo.pub.pf.SourceEnum
 */
public Integer getSource() {
	return (Integer) this.getAttributeValue( InvestIncomeVO.SOURCE);
} 

/**
 * 属性source的Setter方法.属性名：审批状态
 * 创建日期:2018-9-6
 * @param source nc.vo.pub.pf.SourceEnum
 */
public void setSource ( Integer source) {
	this.setAttributeValue( InvestIncomeVO.SOURCE,source);
} 

/**
 * 属性 pk_income的Getter方法.属性名：ifm_income主键
 *  创建日期:2018-9-6
 * @return ifm_income
 */
public String getPk_income() {
	return (String) this.getAttributeValue( InvestIncomeVO.PK_INCOME);
} 

/**
 * 属性pk_income的Setter方法.属性名：ifm_income主键
 * 创建日期:2018-9-6
 * @param newPk_income 
 */
public void setPk_income ( String pk_income) {
	this.setAttributeValue( InvestIncomeVO.PK_INCOME,pk_income);
} 

public String getPk_srcbill() {
	return (String) this.getAttributeValue( InvestIncomeVO.PK_SRCBILL);
} 

public void setPk_srcbill ( String pk_srcbill) {
	this.setAttributeValue( InvestIncomeVO.PK_SRCBILL,pk_srcbill);
} 
public String getPk_srcbilltype() {
	return (String) this.getAttributeValue( InvestIncomeVO.PK_SRCBILLTYPE);
} 

public void setPk_srcbilltype ( String pk_srcbilltype) {
	this.setAttributeValue( InvestIncomeVO.PK_SRCBILLTYPE,pk_srcbilltype);
} 
public String getSrcbilltypecode() {
	return (String) this.getAttributeValue( InvestIncomeVO.SRCBILLTYPECODE);
} 

public void setSrcbilltypecode ( String srcbilltypecode) {
	this.setAttributeValue( InvestIncomeVO.SRCBILLTYPECODE,srcbilltypecode);
} 
public String getSrcbillno() {
	return (String) this.getAttributeValue( InvestIncomeVO.SRCBILLNO);
} 

public void setSrcbillno ( String srcbillno) {
	this.setAttributeValue( InvestIncomeVO.SRCBILLNO,srcbillno);
} 
 
/**
* 属性 pk_group的Getter方法.属性名：集团
*  创建日期:2018-9-6
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return (String) this.getAttributeValue( InvestIncomeVO.PK_GROUP);
} 

/**
* 属性pk_group的Setter方法.属性名：集团
* 创建日期:2018-9-6
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( InvestIncomeVO.PK_GROUP,pk_group);
} 
 
/**
* 属性 pk_org的Getter方法.属性名：财务组织
*  创建日期:2018-9-6
* @return nc.vo.org.FinanceOrgVO
*/
public String getPk_org() {
return (String) this.getAttributeValue( InvestIncomeVO.PK_ORG);
} 

/**
* 属性pk_org的Setter方法.属性名：财务组织
* 创建日期:2018-9-6
* @param newPk_org nc.vo.org.FinanceOrgVO
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( InvestIncomeVO.PK_ORG,pk_org);
} 
 
/**
* 属性 pk_org_v的Getter方法.属性名：财务组织版本
*  创建日期:2018-9-6
* @return nc.vo.vorg.FinanceOrgVersionVO
*/
public String getPk_org_v() {
return (String) this.getAttributeValue( InvestIncomeVO.PK_ORG_V);
} 

/**
* 属性pk_org_v的Setter方法.属性名：财务组织版本
* 创建日期:2018-9-6
* @param newPk_org_v nc.vo.vorg.FinanceOrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( InvestIncomeVO.PK_ORG_V,pk_org_v);
} 
 
/**
* 属性 pk_currtype的Getter方法.属性名：币种
*  创建日期:2018-9-6
* @return nc.vo.bd.currtype.CurrtypeVO
*/
public String getPk_currtype() {
return (String) this.getAttributeValue( InvestIncomeVO.PK_CURRTYPE);
} 

/**
* 属性pk_currtype的Setter方法.属性名：币种
* 创建日期:2018-9-6
* @param newPk_currtype nc.vo.bd.currtype.CurrtypeVO
*/
public void setPk_currtype ( String pk_currtype) {
this.setAttributeValue( InvestIncomeVO.PK_CURRTYPE,pk_currtype);
} 
 
/**
* 属性 pk_olccurr的Getter方法.属性名：组织本位币
*  创建日期:2018-9-6
* @return nc.vo.bd.currtype.CurrtypeVO
*/
public String getPk_olccurr() {
return (String) this.getAttributeValue( InvestIncomeVO.PK_OLCCURR);
} 

/**
* 属性pk_olccurr的Setter方法.属性名：组织本位币
* 创建日期:2018-9-6
* @param newPk_olccurr nc.vo.bd.currtype.CurrtypeVO
*/
public void setPk_olccurr ( String pk_olccurr) {
this.setAttributeValue( InvestIncomeVO.PK_OLCCURR,pk_olccurr);
} 
 
/**
* 属性 remark的Getter方法.属性名：备注
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getRemark() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.REMARK);
} 

/**
* 属性remark的Setter方法.属性名：备注
* 创建日期:2018-9-6
* @param newRemark java.lang.String
*/
public void setRemark ( String remark) {
this.setAttributeValue( InvestIncomeVO.REMARK,remark);
} 
 
/**
* 属性 vdef1的Getter方法.属性名：自定义项1
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef1() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF1);
} 

/**
* 属性vdef1的Setter方法.属性名：自定义项1
* 创建日期:2018-9-6
* @param newVdef1 java.lang.String
*/
public void setVdef1 ( String vdef1) {
this.setAttributeValue( InvestIncomeVO.VDEF1,vdef1);
} 
 
/**
* 属性 vdef2的Getter方法.属性名：自定义项2
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef2() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF2);
} 

/**
* 属性vdef2的Setter方法.属性名：自定义项2
* 创建日期:2018-9-6
* @param newVdef2 java.lang.String
*/
public void setVdef2 ( String vdef2) {
this.setAttributeValue( InvestIncomeVO.VDEF2,vdef2);
} 
 
/**
* 属性 vdef3的Getter方法.属性名：自定义项3
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef3() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF3);
} 

/**
* 属性vdef3的Setter方法.属性名：自定义项3
* 创建日期:2018-9-6
* @param newVdef3 java.lang.String
*/
public void setVdef3 ( String vdef3) {
this.setAttributeValue( InvestIncomeVO.VDEF3,vdef3);
} 
 
/**
* 属性 vdef4的Getter方法.属性名：自定义项4
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef4() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF4);
} 

/**
* 属性vdef4的Setter方法.属性名：自定义项4
* 创建日期:2018-9-6
* @param newVdef4 java.lang.String
*/
public void setVdef4 ( String vdef4) {
this.setAttributeValue( InvestIncomeVO.VDEF4,vdef4);
} 
 
/**
* 属性 vdef5的Getter方法.属性名：自定义项5
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef5() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF5);
} 

/**
* 属性vdef5的Setter方法.属性名：自定义项5
* 创建日期:2018-9-6
* @param newVdef5 java.lang.String
*/
public void setVdef5 ( String vdef5) {
this.setAttributeValue( InvestIncomeVO.VDEF5,vdef5);
} 
 
/**
* 属性 vdef6的Getter方法.属性名：自定义项6
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef6() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF6);
} 

/**
* 属性vdef6的Setter方法.属性名：自定义项6
* 创建日期:2018-9-6
* @param newVdef6 java.lang.String
*/
public void setVdef6 ( String vdef6) {
this.setAttributeValue( InvestIncomeVO.VDEF6,vdef6);
} 
 
/**
* 属性 vdef7的Getter方法.属性名：自定义项7
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef7() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF7);
} 

/**
* 属性vdef7的Setter方法.属性名：自定义项7
* 创建日期:2018-9-6
* @param newVdef7 java.lang.String
*/
public void setVdef7 ( String vdef7) {
this.setAttributeValue( InvestIncomeVO.VDEF7,vdef7);
} 
 
/**
* 属性 vdef8的Getter方法.属性名：自定义项8
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef8() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF8);
} 

/**
* 属性vdef8的Setter方法.属性名：自定义项8
* 创建日期:2018-9-6
* @param newVdef8 java.lang.String
*/
public void setVdef8 ( String vdef8) {
this.setAttributeValue( InvestIncomeVO.VDEF8,vdef8);
} 
 
/**
* 属性 vdef9的Getter方法.属性名：自定义项9
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef9() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF9);
} 

/**
* 属性vdef9的Setter方法.属性名：自定义项9
* 创建日期:2018-9-6
* @param newVdef9 java.lang.String
*/
public void setVdef9 ( String vdef9) {
this.setAttributeValue( InvestIncomeVO.VDEF9,vdef9);
} 
 
/**
* 属性 vdef10的Getter方法.属性名：自定义项10
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef10() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF10);
} 

/**
* 属性vdef10的Setter方法.属性名：自定义项10
* 创建日期:2018-9-6
* @param newVdef10 java.lang.String
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( InvestIncomeVO.VDEF10,vdef10);
} 
 
/**
* 属性 vdef11的Getter方法.属性名：自定义项11
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef11() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF11);
} 

/**
* 属性vdef11的Setter方法.属性名：自定义项11
* 创建日期:2018-9-6
* @param newVdef11 java.lang.String
*/
public void setVdef11 ( String vdef11) {
this.setAttributeValue( InvestIncomeVO.VDEF11,vdef11);
} 
 
/**
* 属性 vdef12的Getter方法.属性名：自定义项12
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef12() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF12);
} 

/**
* 属性vdef12的Setter方法.属性名：自定义项12
* 创建日期:2018-9-6
* @param newVdef12 java.lang.String
*/
public void setVdef12 ( String vdef12) {
this.setAttributeValue( InvestIncomeVO.VDEF12,vdef12);
} 
 
/**
* 属性 vdef13的Getter方法.属性名：自定义项13
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef13() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF13);
} 

/**
* 属性vdef13的Setter方法.属性名：自定义项13
* 创建日期:2018-9-6
* @param newVdef13 java.lang.String
*/
public void setVdef13 ( String vdef13) {
this.setAttributeValue( InvestIncomeVO.VDEF13,vdef13);
} 
 
/**
* 属性 vdef14的Getter方法.属性名：自定义项14
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef14() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF14);
} 

/**
* 属性vdef14的Setter方法.属性名：自定义项14
* 创建日期:2018-9-6
* @param newVdef14 java.lang.String
*/
public void setVdef14 ( String vdef14) {
this.setAttributeValue( InvestIncomeVO.VDEF14,vdef14);
} 
 
/**
* 属性 vdef15的Getter方法.属性名：自定义项15
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef15() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF15);
} 

/**
* 属性vdef15的Setter方法.属性名：自定义项15
* 创建日期:2018-9-6
* @param newVdef15 java.lang.String
*/
public void setVdef15 ( String vdef15) {
this.setAttributeValue( InvestIncomeVO.VDEF15,vdef15);
} 
 
/**
* 属性 vdef16的Getter方法.属性名：自定义项16
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef16() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF16);
} 

/**
* 属性vdef16的Setter方法.属性名：自定义项16
* 创建日期:2018-9-6
* @param newVdef16 java.lang.String
*/
public void setVdef16 ( String vdef16) {
this.setAttributeValue( InvestIncomeVO.VDEF16,vdef16);
} 
 
/**
* 属性 vdef17的Getter方法.属性名：自定义项17
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef17() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF17);
} 

/**
* 属性vdef17的Setter方法.属性名：自定义项17
* 创建日期:2018-9-6
* @param newVdef17 java.lang.String
*/
public void setVdef17 ( String vdef17) {
this.setAttributeValue( InvestIncomeVO.VDEF17,vdef17);
} 
 
/**
* 属性 vdef18的Getter方法.属性名：自定义项18
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef18() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF18);
} 

/**
* 属性vdef18的Setter方法.属性名：自定义项18
* 创建日期:2018-9-6
* @param newVdef18 java.lang.String
*/
public void setVdef18 ( String vdef18) {
this.setAttributeValue( InvestIncomeVO.VDEF18,vdef18);
} 
 
/**
* 属性 vdef19的Getter方法.属性名：自定义项19
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef19() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF19);
} 

/**
* 属性vdef19的Setter方法.属性名：自定义项19
* 创建日期:2018-9-6
* @param newVdef19 java.lang.String
*/
public void setVdef19 ( String vdef19) {
this.setAttributeValue( InvestIncomeVO.VDEF19,vdef19);
} 
 
/**
* 属性 vdef20的Getter方法.属性名：自定义项20
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getVdef20() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF20);
} 

/**
* 属性vdef20的Setter方法.属性名：自定义项20
* 创建日期:2018-9-6
* @param newVdef20 java.lang.String
*/
public void setVdef20 ( String vdef20) {
this.setAttributeValue( InvestIncomeVO.VDEF20,vdef20);
} 
 
/**
* 属性 olcrate的Getter方法.属性名：组织本币汇率
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getOlcrate() {
return (String) this.getAttributeValue( InvestIncomeVO.OLCRATE);
} 

/**
* 属性olcrate的Setter方法.属性名：组织本币汇率
* 创建日期:2018-9-6
* @param newOlcrate nc.vo.pub.lang.UFDouble
*/
public void setOlcrate ( String olcrate) {
this.setAttributeValue( InvestIncomeVO.OLCRATE,olcrate);
} 
 
/**
* 属性 olcmoeny的Getter方法.属性名：组织本币金额
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getOlcmoeny() {
	return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.OLCMOENY);
} 

/**
* 属性olcmoeny的Setter方法.属性名：组织本币金额
* 创建日期:2018-9-6
* @param newOlcmoeny nc.vo.pub.lang.UFDouble
*/
public void setOlcmoeny ( String olcmoeny) {
this.setAttributeValue( InvestIncomeVO.OLCMOENY,olcmoeny);
} 
 
/**
* 属性 glcrate的Getter方法.属性名：集团本币汇率
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getGlcrate() {
return (String) this.getAttributeValue( InvestIncomeVO.GLCRATE);
} 

/**
* 属性glcrate的Setter方法.属性名：集团本币汇率
* 创建日期:2018-9-6
* @param newGlcrate nc.vo.pub.lang.UFDouble
*/
public void setGlcrate ( String glcrate) {
this.setAttributeValue( InvestIncomeVO.GLCRATE,glcrate);
} 
 
/**
* 属性 glcmoeny的Getter方法.属性名：集团本币金额
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getGlcmoeny() {
	return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.GLCMOENY);
} 

/**
* 属性glcmoeny的Setter方法.属性名：集团本币金额
* 创建日期:2018-9-6
* @param newGlcmoeny nc.vo.pub.lang.UFDouble
*/
public void setGlcmoeny ( String glcmoeny) {
this.setAttributeValue( InvestIncomeVO.GLCMOENY,glcmoeny);
} 
 
/**
* 属性 gllcrate的Getter方法.属性名：全局本币汇率
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getGllcrate() {
return (String) this.getAttributeValue( InvestIncomeVO.GLLCRATE);
} 

/**
* 属性gllcrate的Setter方法.属性名：全局本币汇率
* 创建日期:2018-9-6
* @param newGllcrate nc.vo.pub.lang.UFDouble
*/
public void setGllcrate ( String gllcrate) {
this.setAttributeValue( InvestIncomeVO.GLLCRATE,gllcrate);
} 
 
/**
* 属性 gllmoeny的Getter方法.属性名：全局本比金额
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getGllmoeny() {
return (UFDouble) this.getAttributeValue( InvestIncomeVO.GLLMOENY);
} 

/**
* 属性gllmoeny的Setter方法.属性名：全局本比金额
* 创建日期:2018-9-6
* @param newGllmoeny nc.vo.pub.lang.UFDouble
*/
public void setGllmoeny ( String gllmoeny) {
this.setAttributeValue( InvestIncomeVO.GLLMOENY,gllmoeny);
} 
 
/**
* 属性 billmaker的Getter方法.属性名：制单人
*  创建日期:2018-9-6
* @return nc.vo.sm.UserVO
*/
public String getBillmaker() {
return (String) this.getAttributeValue( InvestIncomeVO.BILLMAKER);
} 

/**
* 属性billmaker的Setter方法.属性名：制单人
* 创建日期:2018-9-6
* @param newBillmaker nc.vo.sm.UserVO
*/
public void setBillmaker ( String billmaker) {
this.setAttributeValue( InvestIncomeVO.BILLMAKER,billmaker);
} 
 
/**
* 属性 billmakedate的Getter方法.属性名：制单日期
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getBillmakedate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue( InvestIncomeVO.BILLMAKEDATE);
} 

/**
* 属性billmakedate的Setter方法.属性名：制单日期
* 创建日期:2018-9-6
* @param newBillmakedate nc.vo.pub.lang.UFDate
*/
public void setBillmakedate ( UFDate billmakedate) {
this.setAttributeValue( InvestIncomeVO.BILLMAKEDATE,billmakedate);
} 
 
/**
* 属性 billmaketime的Getter方法.属性名：制单时间
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getBillmaketime() {
return (nc.vo.pub.lang.UFDateTime) this.getAttributeValue( InvestIncomeVO.BILLMAKETIME);
} 

/**
* 属性billmaketime的Setter方法.属性名：制单时间
* 创建日期:2018-9-6
* @param newBillmaketime nc.vo.pub.lang.UFDateTime
*/
public void setBillmaketime ( UFDateTime billmaketime) {
this.setAttributeValue( InvestIncomeVO.BILLMAKETIME,billmaketime);
} 
 
/**
* 属性 approver的Getter方法.属性名：审批人
*  创建日期:2018-9-6
* @return nc.vo.sm.UserVO
*/
public String getApprover() {
return (String) this.getAttributeValue( InvestIncomeVO.APPROVER);
} 

/**
* 属性approver的Setter方法.属性名：审批人
* 创建日期:2018-9-6
* @param newApprover nc.vo.sm.UserVO
*/
public void setApprover ( String approver) {
this.setAttributeValue( InvestIncomeVO.APPROVER,approver);
} 
 
/**
* 属性 approvedate的Getter方法.属性名：审批日期
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getApprovedate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue( InvestIncomeVO.APPROVEDATE);
} 

/**
* 属性approvedate的Setter方法.属性名：审批日期
* 创建日期:2018-9-6
* @param newApprovedate nc.vo.pub.lang.UFDate
*/
public void setApprovedate ( UFDate approvedate) {
this.setAttributeValue( InvestIncomeVO.APPROVEDATE,approvedate);
} 
public UFDate getGatheringdate() {
	return (nc.vo.pub.lang.UFDate) this.getAttributeValue( InvestIncomeVO.GATHERINGDATE);
} 

public void setGatheringdate ( UFDate gatheringdate) {
	this.setAttributeValue( InvestIncomeVO.GATHERINGDATE,gatheringdate);
} 
 
/**
* 属性 approvenote的Getter方法.属性名：审批批语
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getApprovenote() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.APPROVENOTE);
} 

/**
* 属性approvenote的Setter方法.属性名：审批批语
* 创建日期:2018-9-6
* @param newApprovenote java.lang.String
*/
public void setApprovenote ( String approvenote) {
this.setAttributeValue( InvestIncomeVO.APPROVENOTE,approvenote);
} 
 
/**
* 属性 creator的Getter方法.属性名：创建人
*  创建日期:2018-9-6
* @return nc.vo.sm.UserVO
*/
public String getCreator() {
return (String) this.getAttributeValue( InvestIncomeVO.CREATOR);
} 

/**
* 属性creator的Setter方法.属性名：创建人
* 创建日期:2018-9-6
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( String creator) {
this.setAttributeValue( InvestIncomeVO.CREATOR,creator);
} 
 
/**
* 属性 creationtime的Getter方法.属性名：创建时间
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return (nc.vo.pub.lang.UFDateTime) this.getAttributeValue( InvestIncomeVO.CREATIONTIME);
} 

/**
* 属性creationtime的Setter方法.属性名：创建时间
* 创建日期:2018-9-6
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( InvestIncomeVO.CREATIONTIME,creationtime);
} 
 
/**
* 属性 modifier的Getter方法.属性名：最后修改人
*  创建日期:2018-9-6
* @return nc.vo.sm.UserVO
*/
public String getModifier() {
return (String) this.getAttributeValue( InvestIncomeVO.MODIFIER);
} 

/**
* 属性modifier的Setter方法.属性名：最后修改人
* 创建日期:2018-9-6
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( String modifier) {
this.setAttributeValue( InvestIncomeVO.MODIFIER,modifier);
} 
 
/**
* 属性 modifiedtime的Getter方法.属性名：最后修改时间
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return (nc.vo.pub.lang.UFDateTime) this.getAttributeValue( InvestIncomeVO.MODIFIEDTIME);
} 

/**
* 属性modifiedtime的Setter方法.属性名：最后修改时间
* 创建日期:2018-9-6
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( InvestIncomeVO.MODIFIEDTIME,modifiedtime);
} 
 
/**
* 属性 pk_busitype的Getter方法.属性名：业务流程
*  创建日期:2018-9-6
* @return nc.vo.pf.pub.BusitypeVO
*/
public String getPk_busitype() {
return (String) this.getAttributeValue( InvestIncomeVO.PK_BUSITYPE);
} 

/**
* 属性pk_busitype的Setter方法.属性名：业务流程
* 创建日期:2018-9-6
* @param newPk_busitype nc.vo.pf.pub.BusitypeVO
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( InvestIncomeVO.PK_BUSITYPE,pk_busitype);
} 
 
/**
* 属性 pk_billtypeid的Getter方法.属性名：单据类型主键
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getPk_billtypeid() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.PK_BILLTYPEID);
} 

/**
* 属性pk_billtypeid的Setter方法.属性名：单据类型主键
* 创建日期:2018-9-6
* @param newPk_billtypeid java.lang.String
*/
public void setPk_billtypeid ( String pk_billtypeid) {
this.setAttributeValue( InvestIncomeVO.PK_BILLTYPEID,pk_billtypeid);
} 
 
/**
* 属性 pk_billtypecode的Getter方法.属性名：单据类型
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getPk_billtypecode() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.PK_BILLTYPECODE);
} 

/**
* 属性pk_billtypecode的Setter方法.属性名：单据类型
* 创建日期:2018-9-6
* @param newPk_billtypecode java.lang.String
*/
public void setPk_billtypecode ( String pk_billtypecode) {
this.setAttributeValue( InvestIncomeVO.PK_BILLTYPECODE,pk_billtypecode);
} 
 
/**
* 属性 productcode的Getter方法.属性名：产品代码
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getProductcode() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.PRODUCTCODE);
} 

/**
* 属性productcode的Setter方法.属性名：产品代码
* 创建日期:2018-9-6
* @param newProductcode java.lang.String
*/
public void setProductcode ( String productcode) {
this.setAttributeValue( InvestIncomeVO.PRODUCTCODE,productcode);
} 
 
/**
* 属性 productname的Getter方法.属性名：产品名称
*  创建日期:2018-9-6
* @return java.lang.String
*/
public String getProductname() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.PRODUCTNAME);
} 

/**
* 属性productname的Setter方法.属性名：产品名称
* 创建日期:2018-9-6
* @param newProductname java.lang.String
*/
public void setProductname ( String productname) {
this.setAttributeValue( InvestIncomeVO.PRODUCTNAME,productname);
} 
 
/**
* 属性 issuebank的Getter方法.属性名：发行银行
*  创建日期:2018-9-6
* @return nc.vo.bd.banktype.BankTypeVO
*/
public String getIssuebank() {
return (String) this.getAttributeValue( InvestIncomeVO.ISSUEBANK);
} 

/**
* 属性issuebank的Setter方法.属性名：发行银行
* 创建日期:2018-9-6
* @param newIssuebank nc.vo.bd.banktype.BankTypeVO
*/
public void setIssuebank ( String issuebank) {
this.setAttributeValue( InvestIncomeVO.ISSUEBANK,issuebank);
} 
 
/**
* 属性 billstatus的Getter方法.属性名：单据状态
*  创建日期:2018-9-6
* @return nc.vo.ifm.BillStatusEnum
*/
public Integer getBillstatus() {
return (Integer) this.getAttributeValue( InvestIncomeVO.BILLSTATUS);
} 

/**
* 属性billstatus的Setter方法.属性名：单据状态
* 创建日期:2018-9-6
* @param newBillstatus nc.vo.ifm.BillStatusEnum
*/
public void setBillstatus ( Integer billstatus) {
this.setAttributeValue( InvestIncomeVO.BILLSTATUS,billstatus);
} 
 
/**
* 属性 gathering的Getter方法.属性名：收款银行账户
*  创建日期:2018-9-6
* @return nc.vo.bd.bankaccount.BankAccSubVO
*/
public String getGathering() {
return (String) this.getAttributeValue( InvestIncomeVO.GATHERING);
} 

/**
* 属性gathering的Setter方法.属性名：收款银行账户
* 创建日期:2018-9-6
* @param newGathering nc.vo.bd.bankaccount.BankAccSubVO
*/
public void setGathering ( String gathering) {
this.setAttributeValue( InvestIncomeVO.GATHERING,gathering);
} 
 
/**
* 属性 investaccount的Getter方法.属性名：理财账户
*  创建日期:2018-9-6
* @return nc.vo.bd.bankaccount.BankAccSubVO
*/
public String getInvestaccount() {
return (String) this.getAttributeValue( InvestIncomeVO.INVESTACCOUNT);
} 

/**
* 属性investaccount的Setter方法.属性名：理财账户
* 创建日期:2018-9-6
* @param newInvestaccount nc.vo.bd.bankaccount.BankAccSubVO
*/
public void setInvestaccount ( String investaccount) {
this.setAttributeValue( InvestIncomeVO.INVESTACCOUNT,investaccount);
} 
 
/**
* 属性 interestday的Getter方法.属性名：利息天数
*  创建日期:2018-9-6
* @return java.lang.Integer
*/
public Integer getInterestday() {
return (java.lang.Integer) this.getAttributeValue( InvestIncomeVO.INTERESTDAY);
} 

/**
* 属性interestday的Setter方法.属性名：利息天数
* 创建日期:2018-9-6
* @param newInterestday java.lang.Integer
*/
public void setInterestday ( Integer interestday) {
this.setAttributeValue( InvestIncomeVO.INTERESTDAY,interestday);
} 
 
/**
* 属性 expectedrate的Getter方法.属性名：预期收益率
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getExpectedrate() {
return (String) this.getAttributeValue( InvestIncomeVO.EXPECTEDRATE);
} 

/**
* 属性expectedrate的Setter方法.属性名：预期收益率
* 创建日期:2018-9-6
* @param newExpectedrate nc.vo.pub.lang.UFDouble
*/
public void setExpectedrate ( String expectedrate) {
this.setAttributeValue( InvestIncomeVO.EXPECTEDRATE,expectedrate);
} 
 
/**
* 属性 expectedmoney的Getter方法.属性名：预期收益
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getExpectedmoney() {
return (String) this.getAttributeValue( InvestIncomeVO.EXPECTEDMONEY);
} 

/**
* 属性expectedmoney的Setter方法.属性名：预期收益
* 创建日期:2018-9-6
* @param newExpectedmoney nc.vo.pub.lang.UFDouble
*/
public void setExpectedmoney ( String expectedmoney) {
this.setAttributeValue( InvestIncomeVO.EXPECTEDMONEY,expectedmoney);
} 
 
/**
* 属性 enddate的Getter方法.属性名：到期日期
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getEnddate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue( InvestIncomeVO.ENDDATE);
} 

/**
* 属性enddate的Setter方法.属性名：到期日期
* 创建日期:2018-9-6
* @param newEnddate nc.vo.pub.lang.UFDate
*/
public void setEnddate ( UFDate enddate) {
this.setAttributeValue( InvestIncomeVO.ENDDATE,enddate);
} 
 
/**
* 属性 actualmoeny的Getter方法.属性名：实际收益
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getActualmoeny() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.ACTUALMOENY);
} 

/**
* 属性actualmoeny的Setter方法.属性名：实际收益
* 创建日期:2018-9-6
* @param newActualmoeny nc.vo.pub.lang.UFDouble
*/
public void setActualmoeny ( String actualmoeny) {
this.setAttributeValue( InvestIncomeVO.ACTUALMOENY,actualmoeny);
} 
 
/**
* 属性 incomerate的Getter方法.属性名：收益税率
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getIncomerate() {
return (String) this.getAttributeValue( InvestIncomeVO.INCOMERATE);
} 

/**
* 属性incomerate的Setter方法.属性名：收益税率
* 创建日期:2018-9-6
* @param newIncomerate nc.vo.pub.lang.UFDouble
*/
public void setIncomerate ( String incomerate) {
this.setAttributeValue( InvestIncomeVO.INCOMERATE,incomerate);
} 
 
/**
* 属性 incomemoney的Getter方法.属性名：收益税额
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getIncomemoney() {
return (String) this.getAttributeValue( InvestIncomeVO.INCOMEMONEY);
} 

/**
* 属性incomemoney的Setter方法.属性名：收益税额
* 创建日期:2018-9-6
* @param newIncomemoney nc.vo.pub.lang.UFDouble
*/
public void setIncomemoney ( String incomemoney) {
this.setAttributeValue( InvestIncomeVO.INCOMEMONEY,incomemoney);
} 
/**
* 收益资金计划项目
*/
public String getIncomefundplanpro() {
	return (java.lang.String) this.getAttributeValue( InvestIncomeVO.INCOMEFUNDPLANPRO);
} 
public void setIncomefundplanpro ( String incomefundplanpro) {
	this.setAttributeValue( InvestIncomeVO.INCOMEFUNDPLANPRO,incomefundplanpro);
} 
/**
* 税额资金计划项目
*/
public String getTaxfundplanpro() {
	return (java.lang.String) this.getAttributeValue( InvestIncomeVO.TAXFUNDPLANPRO);
} 
public void setTaxfundplanpro ( String taxfundplanpro) {
	this.setAttributeValue( InvestIncomeVO.TAXFUNDPLANPRO,taxfundplanpro);
} 
/**
 * 投资品种
 */
public String getInvestvariety() {
	return (java.lang.String) this.getAttributeValue( InvestIncomeVO.INVESTVARIETY);
} 
public void setInvestvariety ( String investvariety) {
	this.setAttributeValue( InvestIncomeVO.INVESTVARIETY,investvariety);
} 
/**
 * 赎回主键
 */
public String getk_redeem() {
	return (java.lang.String) this.getAttributeValue( InvestIncomeVO.PK_REDEEM);
} 
public void setk_redeem ( String pk_redeem) {
	this.setAttributeValue( InvestIncomeVO.PK_REDEEM,pk_redeem);
} 


public static final String TBBMESSAGE = "tbbmessage";
/**
 * 获取预算提示信息
 * 
 * @return 预算提示信息
 */
public String getTbbmessage() {
	return (String) this.getAttributeValue("tbbmessage");
}

/**
 * 设置预算提示信息
 * 
 * @param tbbmessage
 *            预算提示信息
 */
public void setTbbmessage(String tbbmessage) {
	this.setAttributeValue("tbbmessage", tbbmessage);
}
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2018-9-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2018-9-6
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("ifm.InvestIncomeVO");
    }

   }
    