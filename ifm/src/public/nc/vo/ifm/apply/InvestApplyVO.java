package nc.vo.ifm.apply;

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
 *  创建日期:2018-9-1
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class InvestApplyVO extends SuperVO {
	
/**
*自定义项9
*/
public static final String VDEF9="vdef9";
;
/**
*组织本机金额
*/
public static final String OLCMONEY="olcmoney";
;
/**
*集团本币金额
*/
public static final String GLCMONEY="glcmoney";
;
/**
*全局本比金额
*/
public static final String GLLMONEY="gllmoney";
;
/**
*发行银行
*/
public static final String ISSUEBANK="issuebank";
;
/**
*境内外
*/
public static final String BOUNDARY="boundary";
;
/**
*银行网点
*/
public static final String BANKNETWORK="banknetwork";
;
/**
*单据状态
*/
public static final String BILLSTATUS="billstatus";
;
/**
*结算账户
*/
public static final String SETTLEACCOUNT="settleaccount";
;
/**
*理财账户
*/
public static final String INVEST="invest";
;
/**
*产品编码
*/
public static final String PRODUCTCODE="productcode";
;
/**
*产品名称
*/
public static final String PRODUCTNAME="productname";
;
/**
*理财金额
*/
public static final String MONEY="money";
;
/**
*保本类型
*/
public static final String EVENTYPE="eventype";
;
/**
*风险类型
*/
public static final String RISK="risk";
;
/**
*购买日期
*/
public UFDate purchasedate;
/**
*到期日期
*/
public UFDate enddate;
/**
*起息日期
*/
public UFDate interestdate;
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
*付息规则
*/
public static final String PAYTYPE="paytype";
;
/**
*付息周期
*/
public static final String PAYPERIOD="payperiod";
;
/**
*结息日
*/
public UFDate settledate;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 vdef9的Getter方法.属性名：自定义项9
*  创建日期:2018-9-1
* @return java.lang.String
*/
public String getVdef9() {
return (java.lang.String) this.getAttributeValue( InvestApplyVO.VDEF9);
} 

/**
* 属性vdef9的Setter方法.属性名：自定义项9
* 创建日期:2018-9-1
* @param newVdef9 java.lang.String
*/
public void setVdef9 ( String vdef9) {
this.setAttributeValue( InvestApplyVO.VDEF9,vdef9);
} 
 
/**
* 属性 olcmoney的Getter方法.属性名：组织本机金额
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getOlcmoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestApplyVO.OLCMONEY);
} 

/**
* 属性olcmoney的Setter方法.属性名：组织本机金额
* 创建日期:2018-9-1
* @param newOlcmoney nc.vo.pub.lang.UFDouble
*/
public void setOlcmoney ( String olcmoney) {
this.setAttributeValue( InvestApplyVO.OLCMONEY,olcmoney);
} 
 
/**
* 属性 glcmoney的Getter方法.属性名：集团本币金额
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getGlcmoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestApplyVO.GLCMONEY);
} 

/**
* 属性glcmoney的Setter方法.属性名：集团本币金额
* 创建日期:2018-9-1
* @param newGlcmoney nc.vo.pub.lang.UFDouble
*/
public void setGlcmoney ( String glcmoney) {
this.setAttributeValue( InvestApplyVO.GLCMONEY,glcmoney);
} 
 
/**
* 属性 gllmoney的Getter方法.属性名：全局本比金额
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getGllmoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestApplyVO.GLLMONEY);
} 

/**
* 属性gllmoney的Setter方法.属性名：全局本比金额
* 创建日期:2018-9-1
* @param newGllmoney nc.vo.pub.lang.UFDouble
*/
public void setGllmoney ( String gllmoney) {
this.setAttributeValue( InvestApplyVO.GLLMONEY,gllmoney);
} 
 
/**
* 属性 issuebank的Getter方法.属性名：发行银行
*  创建日期:2018-9-1
* @return nc.vo.bd.banktype.BankTypeVO
*/
public String getIssuebank() {
return (java.lang.String) this.getAttributeValue( InvestApplyVO.ISSUEBANK);
} 

/**
* 属性issuebank的Setter方法.属性名：发行银行
* 创建日期:2018-9-1
* @param newIssuebank nc.vo.bd.banktype.BankTypeVO
*/
public void setIssuebank ( String issuebank) {
this.setAttributeValue( InvestApplyVO.ISSUEBANK,issuebank);
} 
 
/**
* 属性 boundary的Getter方法.属性名：境内外
*  创建日期:2018-9-1
* @return nc.vo.ifm.BoundaryEnum
*/
public Integer getBoundary() {
return (Integer) this.getAttributeValue( InvestApplyVO.BOUNDARY);
} 

/**
* 属性boundary的Setter方法.属性名：境内外
* 创建日期:2018-9-1
* @param newBoundary nc.vo.ifm.BoundaryEnum
*/
public void setBoundary ( Integer boundary) {
this.setAttributeValue( InvestApplyVO.BOUNDARY,boundary);
} 
 
/**
* 属性 banknetwork的Getter方法.属性名：银行网点
*  创建日期:2018-9-1
* @return nc.vo.bd.bankdoc.BankdocVO
*/
public String getBanknetwork() {
return (String) this.getAttributeValue( InvestApplyVO.BANKNETWORK);
} 

/**
* 属性banknetwork的Setter方法.属性名：银行网点
* 创建日期:2018-9-1
* @param newBanknetwork nc.vo.bd.bankdoc.BankdocVO
*/
public void setBanknetwork ( String banknetwork) {
this.setAttributeValue( InvestApplyVO.BANKNETWORK,banknetwork);
} 
 
/**
* 属性 billstatus的Getter方法.属性名：单据状态
*  创建日期:2018-9-1
* @return nc.vo.ifm.BillStatusEnum
*/
public Integer getBillstatus() {
return (Integer) this.getAttributeValue( InvestApplyVO.BILLSTATUS);
} 

/**
* 属性billstatus的Setter方法.属性名：单据状态
* 创建日期:2018-9-1
* @param newBillstatus nc.vo.ifm.BillStatusEnum
*/
public void setBillstatus ( Integer billstatus) {
this.setAttributeValue( InvestApplyVO.BILLSTATUS,billstatus);
} 
 
/**
* 属性 settleaccount的Getter方法.属性名：结算账户
*  创建日期:2018-9-1
* @return nc.vo.bd.bankaccount.BankAccSubVO
*/
public String getSettleaccount() {
return (String) this.getAttributeValue( InvestApplyVO.SETTLEACCOUNT);
} 

/**
* 属性settleaccount的Setter方法.属性名：结算账户
* 创建日期:2018-9-1
* @param newSettleaccount nc.vo.bd.bankaccount.BankAccSubVO
*/
public void setSettleaccount ( String settleaccount) {
this.setAttributeValue( InvestApplyVO.SETTLEACCOUNT,settleaccount);
} 
 
/**
* 属性 invest的Getter方法.属性名：理财账户
*  创建日期:2018-9-1
* @return nc.vo.bd.bankaccount.BankAccSubVO
*/
public String getInvest() {
return (String) this.getAttributeValue( InvestApplyVO.INVEST);
} 

/**
* 属性invest的Setter方法.属性名：理财账户
* 创建日期:2018-9-1
* @param newInvest nc.vo.bd.bankaccount.BankAccSubVO
*/
public void setInvest ( String invest) {
this.setAttributeValue( InvestApplyVO.INVEST,invest);
} 
 
/**
* 属性 productcode的Getter方法.属性名：产品编码
*  创建日期:2018-9-1
* @return java.lang.String
*/
public String getProductcode() {
return (java.lang.String) this.getAttributeValue( InvestApplyVO.PRODUCTCODE);
} 

/**
* 属性productcode的Setter方法.属性名：产品编码
* 创建日期:2018-9-1
* @param newProductcode java.lang.String
*/
public void setProductcode ( String productcode) {
this.setAttributeValue( InvestApplyVO.PRODUCTCODE,productcode);
} 
 
/**
* 属性 productname的Getter方法.属性名：产品名称
*  创建日期:2018-9-1
* @return java.lang.String
*/
public String getProductname() {
return (java.lang.String) this.getAttributeValue( InvestApplyVO.PRODUCTNAME);
} 

/**
* 属性productname的Setter方法.属性名：产品名称
* 创建日期:2018-9-1
* @param newProductname java.lang.String
*/
public void setProductname ( String productname) {
this.setAttributeValue( InvestApplyVO.PRODUCTNAME,productname);
} 
 
/**
* 属性 money的Getter方法.属性名：理财金额
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getMoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestApplyVO.MONEY);
} 

/**
* 属性money的Setter方法.属性名：理财金额
* 创建日期:2018-9-1
* @param newMoney nc.vo.pub.lang.UFDouble
*/
public void setMoney ( String money) {
this.setAttributeValue( InvestApplyVO.MONEY,money);
} 
 
/**
* 属性 eventype的Getter方法.属性名：保本类型
*  创建日期:2018-9-1
* @return nc.vo.ifm.EvenEnum
*/
public Integer getEventype() {
return (Integer) this.getAttributeValue( InvestApplyVO.EVENTYPE);
} 

/**
* 属性eventype的Setter方法.属性名：保本类型
* 创建日期:2018-9-1
* @param newEventype nc.vo.ifm.EvenEnum
*/
public void setEventype ( Integer eventype) {
this.setAttributeValue( InvestApplyVO.EVENTYPE,eventype);
} 
 
/**
* 属性 risk的Getter方法.属性名：风险类型
*  创建日期:2018-9-1
* @return nc.vo.ifm.RiskEnum
*/
public Integer getRisk() {
return (Integer) this.getAttributeValue( InvestApplyVO.RISK);
} 

/**
* 属性risk的Setter方法.属性名：风险类型
* 创建日期:2018-9-1
* @param newRisk nc.vo.ifm.RiskEnum
*/
public void setRisk ( Integer risk) {
this.setAttributeValue( InvestApplyVO.RISK,risk);
} 
 
/**
* 属性 purchasedate的Getter方法.属性名：购买日期
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getPurchasedate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue( "purchasedate");
} 

/**
* 属性purchasedate的Setter方法.属性名：购买日期
* 创建日期:2018-9-1
* @param newPurchasedate nc.vo.pub.lang.UFDate
*/
public void setPurchasedate ( UFDate purchasedate) {
this.setAttributeValue( "purchasedate",purchasedate);
} 
 
/**
* 属性 enddate的Getter方法.属性名：到期日期
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getEnddate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue( "enddate");
} 

/**
* 属性enddate的Setter方法.属性名：到期日期
* 创建日期:2018-9-1
* @param newEnddate nc.vo.pub.lang.UFDate
*/
public void setEnddate ( UFDate enddate) {
this.setAttributeValue( "enddate",enddate);
} 
 
/**
* 属性 interestdate的Getter方法.属性名：起息日期
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getInterestdate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue( "interestdate");
} 

/**
* 属性interestdate的Setter方法.属性名：起息日期
* 创建日期:2018-9-1
* @param newInterestdate nc.vo.pub.lang.UFDate
*/
public void setInterestdate ( UFDate interestdate) {
this.setAttributeValue( "interestdate",interestdate);
} 
 
/**
* 属性 interestday的Getter方法.属性名：利息天数
*  创建日期:2018-9-1
* @return java.lang.Integer
*/
public Integer getInterestday() {
return (java.lang.Integer) this.getAttributeValue( InvestApplyVO.INTERESTDAY);
} 

/**
* 属性interestday的Setter方法.属性名：利息天数
* 创建日期:2018-9-1
* @param newInterestday java.lang.Integer
*/
public void setInterestday ( Integer interestday) {
this.setAttributeValue( InvestApplyVO.INTERESTDAY,interestday);
} 
 
/**
* 属性 expectedrate的Getter方法.属性名：预期收益率
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getExpectedrate() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue("expectedrate");
} 

/**
* 属性expectedrate的Setter方法.属性名：预期收益率
* 创建日期:2018-9-1
* @param newExpectedrate nc.vo.pub.lang.UFDouble
*/
public void setExpectedrate ( String expectedrate) {
this.setAttributeValue( InvestApplyVO.EXPECTEDRATE,expectedrate);
} 
 
/**
* 属性 expectedmoney的Getter方法.属性名：预期收益
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getExpectedmoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestApplyVO.EXPECTEDMONEY);
} 

/**
* 属性expectedmoney的Setter方法.属性名：预期收益
* 创建日期:2018-9-1
* @param newExpectedmoney nc.vo.pub.lang.UFDouble
*/
public void setExpectedmoney ( String expectedmoney) {
this.setAttributeValue( InvestApplyVO.EXPECTEDMONEY,expectedmoney);
} 
 
/**
* 属性 paytype的Getter方法.属性名：付息规则
*  创建日期:2018-9-1
* @return nc.vo.ifm.PayTypeEnum
*/
public Integer getPaytype() {
return (Integer) this.getAttributeValue( InvestApplyVO.PAYTYPE);
} 

/**
* 属性paytype的Setter方法.属性名：付息规则
* 创建日期:2018-9-1
* @param newPaytype nc.vo.ifm.PayTypeEnum
*/
public void setPaytype ( Integer paytype) {
this.setAttributeValue( InvestApplyVO.PAYTYPE,paytype);
} 
 
/**
* 属性 payperiod的Getter方法.属性名：付息周期
*  创建日期:2018-9-1
* @return java.lang.Integer
*/
public Integer getPayperiod() {
return (java.lang.Integer) this.getAttributeValue( InvestApplyVO.PAYPERIOD);
} 

/**
* 属性payperiod的Setter方法.属性名：付息周期
* 创建日期:2018-9-1
* @param newPayperiod java.lang.Integer
*/
public void setPayperiod ( Integer payperiod) {
this.setAttributeValue( InvestApplyVO.PAYPERIOD,payperiod);
} 
 
/**
* 属性 settledate的Getter方法.属性名：结息日
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getSettledate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue("settledate");
} 

/**
* 属性settledate的Setter方法.属性名：结息日
* 创建日期:2018-9-1
* @param newSettledate nc.vo.pub.lang.UFDate
*/
public void setSettledate ( UFDate settledate) {
this.setAttributeValue("settledate",settledate);
} 
 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2018-9-1
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("ifm.InvestApplyVO");
    }
   }
    