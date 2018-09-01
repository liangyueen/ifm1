package nc.vo.ifm.redeem;

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
 
public class InvestRedeemVO extends SuperVO {
	
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
*组织本币金额
*/
public static final String OLCMONEY="olcmoney";
;
/**
*集团本币金额
*/
public static final String GLCMOENY="glcmoeny";
;
/**
*全局本机金额
*/
public static final String GLLMOENY="gllmoeny";
;
/**
*发行银行
*/
public static final String ISSUEBANK="issuebank";
;
/**
*赎回状态
*/
public static final String BILLSTATUS="billstatus";
;
/**
*收款账户
*/
public static final String GATHERINGACCOUNT="gatheringaccount";
;
/**
*理财账户
*/
public static final String INVESTACCOUNT="investaccount";
;
/**
*赎回日期
*/
public UFDate redeemdate;
/**
*赎回金额
*/
public static final String REDEEMMONEY="redeemmoney";
;
/**
*利率天数
*/
public static final String INTERESTDAY="interestday";
;
/**
*预期收益率
*/
public static final String EXPECTEDRATE="expectedrate";
;
/**
*上次赎回日期
*/
public UFDate lastdate;
/**
*上次收益
*/
public static final String LASTMONEY="lastmoney";
;
/**
*赎回计划
*/
public static final String REDEEMPLAN="redeemplan";
;
/**
*到账日期
*/
public UFDate incomedate;
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
*实际与预期差额
*/
public static final String BALANCE="balance";
;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 vdef8的Getter方法.属性名：自定义项8
*  创建日期:2018-9-1
* @return java.lang.String
*/
public String getVdef8() {
return (java.lang.String) this.getAttributeValue( InvestRedeemVO.VDEF8);
} 

/**
* 属性vdef8的Setter方法.属性名：自定义项8
* 创建日期:2018-9-1
* @param newVdef8 java.lang.String
*/
public void setVdef8 ( String vdef8) {
this.setAttributeValue( InvestRedeemVO.VDEF8,vdef8);
} 
 
/**
* 属性 vdef9的Getter方法.属性名：自定义项9
*  创建日期:2018-9-1
* @return java.lang.String
*/
public String getVdef9() {
return (java.lang.String) this.getAttributeValue( InvestRedeemVO.VDEF9);
} 

/**
* 属性vdef9的Setter方法.属性名：自定义项9
* 创建日期:2018-9-1
* @param newVdef9 java.lang.String
*/
public void setVdef9 ( String vdef9) {
this.setAttributeValue( InvestRedeemVO.VDEF9,vdef9);
} 
 
/**
* 属性 olcmoney的Getter方法.属性名：组织本币金额
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getOlcmoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestRedeemVO.OLCMONEY);
} 

/**
* 属性olcmoney的Setter方法.属性名：组织本币金额
* 创建日期:2018-9-1
* @param newOlcmoney nc.vo.pub.lang.UFDouble
*/
public void setOlcmoney ( String olcmoney) {
this.setAttributeValue( InvestRedeemVO.OLCMONEY,olcmoney);
} 
 
/**
* 属性 glcmoeny的Getter方法.属性名：集团本币金额
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getGlcmoeny() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestRedeemVO.GLCMOENY);
} 

/**
* 属性glcmoeny的Setter方法.属性名：集团本币金额
* 创建日期:2018-9-1
* @param newGlcmoeny nc.vo.pub.lang.UFDouble
*/
public void setGlcmoeny ( String glcmoeny) {
this.setAttributeValue( InvestRedeemVO.GLCMOENY,glcmoeny);
} 
 
/**
* 属性 gllmoeny的Getter方法.属性名：全局本机金额
*  创建日期:2018-9-1
* @return java.lang.String
*/
public String getGllmoeny() {
return (java.lang.String) this.getAttributeValue( InvestRedeemVO.GLLMOENY);
} 

/**
* 属性gllmoeny的Setter方法.属性名：全局本机金额
* 创建日期:2018-9-1
* @param newGllmoeny java.lang.String
*/
public void setGllmoeny ( String gllmoeny) {
this.setAttributeValue( InvestRedeemVO.GLLMOENY,gllmoeny);
} 
 
/**
* 属性 issuebank的Getter方法.属性名：发行银行
*  创建日期:2018-9-1
* @return nc.vo.bd.banktype.BankTypeVO
*/
public String getIssuebank() {
return (String) this.getAttributeValue( InvestRedeemVO.ISSUEBANK);
} 

/**
* 属性issuebank的Setter方法.属性名：发行银行
* 创建日期:2018-9-1
* @param newIssuebank nc.vo.bd.banktype.BankTypeVO
*/
public void setIssuebank ( String issuebank) {
this.setAttributeValue( InvestRedeemVO.ISSUEBANK,issuebank);
} 
 
/**
* 属性 billstatus的Getter方法.属性名：赎回状态
*  创建日期:2018-9-1
* @return nc.vo.ifm.RedeemStatusEnum
*/
public Integer getBillstatus() {
return (Integer) this.getAttributeValue( InvestRedeemVO.BILLSTATUS);
} 

/**
* 属性billstatus的Setter方法.属性名：赎回状态
* 创建日期:2018-9-1
* @param newBillstatus nc.vo.ifm.RedeemStatusEnum
*/
public void setBillstatus ( Integer billstatus) {
this.setAttributeValue( InvestRedeemVO.BILLSTATUS,billstatus);
} 
 
/**
* 属性 gatheringaccount的Getter方法.属性名：收款账户
*  创建日期:2018-9-1
* @return nc.vo.bd.bankaccount.BankAccSubVO
*/
public String getGatheringaccount() {
return (String) this.getAttributeValue( InvestRedeemVO.GATHERINGACCOUNT);
} 

/**
* 属性gatheringaccount的Setter方法.属性名：收款账户
* 创建日期:2018-9-1
* @param newGatheringaccount nc.vo.bd.bankaccount.BankAccSubVO
*/
public void setGatheringaccount ( String gatheringaccount) {
this.setAttributeValue( InvestRedeemVO.GATHERINGACCOUNT,gatheringaccount);
} 
 
/**
* 属性 investaccount的Getter方法.属性名：理财账户
*  创建日期:2018-9-1
* @return nc.vo.bd.bankaccount.BankAccSubVO
*/
public String getInvestaccount() {
return (String) this.getAttributeValue( InvestRedeemVO.INVESTACCOUNT);
} 

/**
* 属性investaccount的Setter方法.属性名：理财账户
* 创建日期:2018-9-1
* @param newInvestaccount nc.vo.bd.bankaccount.BankAccSubVO
*/
public void setInvestaccount ( String investaccount) {
this.setAttributeValue( InvestRedeemVO.INVESTACCOUNT,investaccount);
} 
 
/**
* 属性 redeemdate的Getter方法.属性名：赎回日期
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getRedeemdate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue("redeemdate");
} 

/**
* 属性redeemdate的Setter方法.属性名：赎回日期
* 创建日期:2018-9-1
* @param newRedeemdate nc.vo.pub.lang.UFDate
*/
public void setRedeemdate ( UFDate redeemdate) {
this.setAttributeValue("redeemdate",redeemdate);
} 
 
/**
* 属性 redeemmoney的Getter方法.属性名：赎回金额
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getRedeemmoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestRedeemVO.REDEEMMONEY);
} 

/**
* 属性redeemmoney的Setter方法.属性名：赎回金额
* 创建日期:2018-9-1
* @param newRedeemmoney nc.vo.pub.lang.UFDouble
*/
public void setRedeemmoney ( String redeemmoney) {
this.setAttributeValue( InvestRedeemVO.REDEEMMONEY,redeemmoney);
} 
 
/**
* 属性 interestday的Getter方法.属性名：利率天数
*  创建日期:2018-9-1
* @return java.lang.Integer
*/
public Integer getInterestday() {
return (java.lang.Integer) this.getAttributeValue( InvestRedeemVO.INTERESTDAY);
} 

/**
* 属性interestday的Setter方法.属性名：利率天数
* 创建日期:2018-9-1
* @param newInterestday java.lang.Integer
*/
public void setInterestday ( Integer interestday) {
this.setAttributeValue( InvestRedeemVO.INTERESTDAY,interestday);
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
this.setAttributeValue( InvestRedeemVO.EXPECTEDRATE,expectedrate);
} 
 
/**
* 属性 lastdate的Getter方法.属性名：上次赎回日期
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getLastdate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue("lastdate");
} 

/**
* 属性lastdate的Setter方法.属性名：上次赎回日期
* 创建日期:2018-9-1
* @param newLastdate nc.vo.pub.lang.UFDate
*/
public void setLastdate ( UFDate lastdate) {
this.setAttributeValue("lastdate",lastdate);
} 
 
/**
* 属性 lastmoney的Getter方法.属性名：上次收益
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getLastmoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestRedeemVO.LASTMONEY);
} 

/**
* 属性lastmoney的Setter方法.属性名：上次收益
* 创建日期:2018-9-1
* @param newLastmoney nc.vo.pub.lang.UFDouble
*/
public void setLastmoney ( String lastmoney) {
this.setAttributeValue( InvestRedeemVO.LASTMONEY,lastmoney);
} 
 
/**
* 属性 redeemplan的Getter方法.属性名：赎回计划
*  创建日期:2018-9-1
* @return java.lang.String
*/
public String getRedeemplan() {
return (java.lang.String) this.getAttributeValue( InvestRedeemVO.REDEEMPLAN);
} 

/**
* 属性redeemplan的Setter方法.属性名：赎回计划
* 创建日期:2018-9-1
* @param newRedeemplan java.lang.String
*/
public void setRedeemplan ( String redeemplan) {
this.setAttributeValue( InvestRedeemVO.REDEEMPLAN,redeemplan);
} 
 
/**
* 属性 incomedate的Getter方法.属性名：到账日期
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getIncomedate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue( "incomedate");
} 

/**
* 属性incomedate的Setter方法.属性名：到账日期
* 创建日期:2018-9-1
* @param newIncomedate nc.vo.pub.lang.UFDate
*/
public void setIncomedate ( UFDate incomedate) {
this.setAttributeValue("incomedate",incomedate);
} 
 
/**
* 属性 incomerate的Getter方法.属性名：收益税率
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getIncomerate() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestRedeemVO.INCOMERATE);
} 

/**
* 属性incomerate的Setter方法.属性名：收益税率
* 创建日期:2018-9-1
* @param newIncomerate nc.vo.pub.lang.UFDouble
*/
public void setIncomerate ( String incomerate) {
this.setAttributeValue( InvestRedeemVO.INCOMERATE,incomerate);
} 
 
/**
* 属性 incomemoney的Getter方法.属性名：收益税额
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getIncomemoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestRedeemVO.INCOMEMONEY);
} 

/**
* 属性incomemoney的Setter方法.属性名：收益税额
* 创建日期:2018-9-1
* @param newIncomemoney nc.vo.pub.lang.UFDouble
*/
public void setIncomemoney ( String incomemoney) {
this.setAttributeValue( InvestRedeemVO.INCOMEMONEY,incomemoney);
} 
 
/**
* 属性 balance的Getter方法.属性名：实际与预期差额
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getBalance() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestRedeemVO.BALANCE);
} 

/**
* 属性balance的Setter方法.属性名：实际与预期差额
* 创建日期:2018-9-1
* @param newBalance nc.vo.pub.lang.UFDouble
*/
public void setBalance ( String balance) {
this.setAttributeValue( InvestRedeemVO.BALANCE,balance);
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
    return VOMetaFactory.getInstance().getVOMeta("ifm.redeem");
    }
   }
    