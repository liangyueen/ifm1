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
 *  创建日期:2018-9-1
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class InvestIncomeVO extends SuperVO {
	
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
public static final String OLCMOENY="olcmoeny";
;
/**
*集团本币金额
*/
public static final String GLCMOENY="glcmoeny";
;
/**
*全局本比金额
*/
public static final String GLLMOENY="gllmoeny";
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
public Boolean actualmoeny;
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
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 vdef8的Getter方法.属性名：自定义项8
*  创建日期:2018-9-1
* @return java.lang.String
*/
public String getVdef8() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF8);
} 

/**
* 属性vdef8的Setter方法.属性名：自定义项8
* 创建日期:2018-9-1
* @param newVdef8 java.lang.String
*/
public void setVdef8 ( String vdef8) {
this.setAttributeValue( InvestIncomeVO.VDEF8,vdef8);
} 
 
/**
* 属性 vdef9的Getter方法.属性名：自定义项9
*  创建日期:2018-9-1
* @return java.lang.String
*/
public String getVdef9() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF9);
} 

/**
* 属性vdef9的Setter方法.属性名：自定义项9
* 创建日期:2018-9-1
* @param newVdef9 java.lang.String
*/
public void setVdef9 ( String vdef9) {
this.setAttributeValue( InvestIncomeVO.VDEF9,vdef9);
} 
 
/**
* 属性 olcmoeny的Getter方法.属性名：组织本币金额
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getOlcmoeny() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.OLCMOENY);
} 

/**
* 属性olcmoeny的Setter方法.属性名：组织本币金额
* 创建日期:2018-9-1
* @param newOlcmoeny nc.vo.pub.lang.UFDouble
*/
public void setOlcmoeny ( String olcmoeny) {
this.setAttributeValue( InvestIncomeVO.OLCMOENY,olcmoeny);
} 
 
/**
* 属性 glcmoeny的Getter方法.属性名：集团本币金额
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getGlcmoeny() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.GLCMOENY);
} 

/**
* 属性glcmoeny的Setter方法.属性名：集团本币金额
* 创建日期:2018-9-1
* @param newGlcmoeny nc.vo.pub.lang.UFDouble
*/
public void setGlcmoeny ( String glcmoeny) {
this.setAttributeValue( InvestIncomeVO.GLCMOENY,glcmoeny);
} 
 
/**
* 属性 gllmoeny的Getter方法.属性名：全局本比金额
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getGllmoeny() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.GLLMOENY);
} 

/**
* 属性gllmoeny的Setter方法.属性名：全局本比金额
* 创建日期:2018-9-1
* @param newGllmoeny nc.vo.pub.lang.UFDouble
*/
public void setGllmoeny ( String gllmoeny) {
this.setAttributeValue( InvestIncomeVO.GLLMOENY,gllmoeny);
} 
 
/**
* 属性 productcode的Getter方法.属性名：产品代码
*  创建日期:2018-9-1
* @return java.lang.String
*/
public String getProductcode() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.PRODUCTCODE);
} 

/**
* 属性productcode的Setter方法.属性名：产品代码
* 创建日期:2018-9-1
* @param newProductcode java.lang.String
*/
public void setProductcode ( String productcode) {
this.setAttributeValue( InvestIncomeVO.PRODUCTCODE,productcode);
} 
 
/**
* 属性 productname的Getter方法.属性名：产品名称
*  创建日期:2018-9-1
* @return java.lang.String
*/
public String getProductname() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.PRODUCTNAME);
} 

/**
* 属性productname的Setter方法.属性名：产品名称
* 创建日期:2018-9-1
* @param newProductname java.lang.String
*/
public void setProductname ( String productname) {
this.setAttributeValue( InvestIncomeVO.PRODUCTNAME,productname);
} 
 
/**
* 属性 issuebank的Getter方法.属性名：发行银行
*  创建日期:2018-9-1
* @return nc.vo.bd.banktype.BankTypeVO
*/
public String getIssuebank() {
return (String) this.getAttributeValue( InvestIncomeVO.ISSUEBANK);
} 

/**
* 属性issuebank的Setter方法.属性名：发行银行
* 创建日期:2018-9-1
* @param newIssuebank nc.vo.bd.banktype.BankTypeVO
*/
public void setIssuebank ( String issuebank) {
this.setAttributeValue( InvestIncomeVO.ISSUEBANK,issuebank);
} 
 
/**
* 属性 billstatus的Getter方法.属性名：单据状态
*  创建日期:2018-9-1
* @return nc.vo.ifm.BillStatusEnum
*/
public Integer getBillstatus() {
return (Integer) this.getAttributeValue( InvestIncomeVO.BILLSTATUS);
} 

/**
* 属性billstatus的Setter方法.属性名：单据状态
* 创建日期:2018-9-1
* @param newBillstatus nc.vo.ifm.BillStatusEnum
*/
public void setBillstatus ( Integer billstatus) {
this.setAttributeValue( InvestIncomeVO.BILLSTATUS,billstatus);
} 
 
/**
* 属性 gathering的Getter方法.属性名：收款银行账户
*  创建日期:2018-9-1
* @return nc.vo.bd.bankaccount.BankAccSubVO
*/
public String getGathering() {
return (String) this.getAttributeValue( InvestIncomeVO.GATHERING);
} 

/**
* 属性gathering的Setter方法.属性名：收款银行账户
* 创建日期:2018-9-1
* @param newGathering nc.vo.bd.bankaccount.BankAccSubVO
*/
public void setGathering ( String gathering) {
this.setAttributeValue( InvestIncomeVO.GATHERING,gathering);
} 
 
/**
* 属性 investaccount的Getter方法.属性名：理财账户
*  创建日期:2018-9-1
* @return nc.vo.bd.bankaccount.BankAccSubVO
*/
public String getInvestaccount() {
return (String) this.getAttributeValue( InvestIncomeVO.INVESTACCOUNT);
} 

/**
* 属性investaccount的Setter方法.属性名：理财账户
* 创建日期:2018-9-1
* @param newInvestaccount nc.vo.bd.bankaccount.BankAccSubVO
*/
public void setInvestaccount ( String investaccount) {
this.setAttributeValue( InvestIncomeVO.INVESTACCOUNT,investaccount);
} 
 
/**
* 属性 interestday的Getter方法.属性名：利息天数
*  创建日期:2018-9-1
* @return java.lang.Integer
*/
public Integer getInterestday() {
return (java.lang.Integer) this.getAttributeValue( InvestIncomeVO.INTERESTDAY);
} 

/**
* 属性interestday的Setter方法.属性名：利息天数
* 创建日期:2018-9-1
* @param newInterestday java.lang.Integer
*/
public void setInterestday ( Integer interestday) {
this.setAttributeValue( InvestIncomeVO.INTERESTDAY,interestday);
} 
 
/**
* 属性 expectedrate的Getter方法.属性名：预期收益率
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getExpectedrate() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.EXPECTEDRATE);
} 

/**
* 属性expectedrate的Setter方法.属性名：预期收益率
* 创建日期:2018-9-1
* @param newExpectedrate nc.vo.pub.lang.UFDouble
*/
public void setExpectedrate ( String expectedrate) {
this.setAttributeValue( InvestIncomeVO.EXPECTEDRATE,expectedrate);
} 
 
/**
* 属性 expectedmoney的Getter方法.属性名：预期收益
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getExpectedmoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.EXPECTEDMONEY);
} 

/**
* 属性expectedmoney的Setter方法.属性名：预期收益
* 创建日期:2018-9-1
* @param newExpectedmoney nc.vo.pub.lang.UFDouble
*/
public void setExpectedmoney ( String expectedmoney) {
this.setAttributeValue( InvestIncomeVO.EXPECTEDMONEY,expectedmoney);
} 
 
/**
* 属性 enddate的Getter方法.属性名：到期日期
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getEnddate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue("enddate");
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
* 属性 actualmoeny的Getter方法.属性名：实际收益
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getActualmoeny() {
return (nc.vo.pub.lang.UFBoolean) this.getAttributeValue("actualmoeny");
} 

/**
* 属性actualmoeny的Setter方法.属性名：实际收益
* 创建日期:2018-9-1
* @param newActualmoeny nc.vo.pub.lang.UFBoolean
*/
public void setActualmoeny ( Boolean actualmoeny) {
this.setAttributeValue( "actualmoeny",actualmoeny);
} 
 
/**
* 属性 incomerate的Getter方法.属性名：收益税率
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getIncomerate() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.INCOMERATE);
} 

/**
* 属性incomerate的Setter方法.属性名：收益税率
* 创建日期:2018-9-1
* @param newIncomerate nc.vo.pub.lang.UFDouble
*/
public void setIncomerate ( String incomerate) {
this.setAttributeValue( InvestIncomeVO.INCOMERATE,incomerate);
} 
 
/**
* 属性 incomemoney的Getter方法.属性名：收益税额
*  创建日期:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getIncomemoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.INCOMEMONEY);
} 

/**
* 属性incomemoney的Setter方法.属性名：收益税额
* 创建日期:2018-9-1
* @param newIncomemoney nc.vo.pub.lang.UFDouble
*/
public void setIncomemoney ( String incomemoney) {
this.setAttributeValue( InvestIncomeVO.INCOMEMONEY,incomemoney);
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
    return VOMetaFactory.getInstance().getVOMeta("ifm.InvestIncomeVO");
    }
   }
    