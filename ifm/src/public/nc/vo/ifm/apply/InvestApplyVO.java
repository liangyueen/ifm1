package nc.vo.ifm.apply;

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
 *   �˴�����۵�������Ϣ
 * </p>
 *  ��������:2018-9-1
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class InvestApplyVO extends SuperVO {
	
/**
*�Զ�����9
*/
public static final String VDEF9="vdef9";
;
/**
*��֯�������
*/
public static final String OLCMONEY="olcmoney";
;
/**
*���ű��ҽ��
*/
public static final String GLCMONEY="glcmoney";
;
/**
*ȫ�ֱ��Ƚ��
*/
public static final String GLLMONEY="gllmoney";
;
/**
*��������
*/
public static final String ISSUEBANK="issuebank";
;
/**
*������
*/
public static final String BOUNDARY="boundary";
;
/**
*��������
*/
public static final String BANKNETWORK="banknetwork";
;
/**
*����״̬
*/
public static final String BILLSTATUS="billstatus";
;
/**
*�����˻�
*/
public static final String SETTLEACCOUNT="settleaccount";
;
/**
*����˻�
*/
public static final String INVEST="invest";
;
/**
*��Ʒ����
*/
public static final String PRODUCTCODE="productcode";
;
/**
*��Ʒ����
*/
public static final String PRODUCTNAME="productname";
;
/**
*��ƽ��
*/
public static final String MONEY="money";
;
/**
*��������
*/
public static final String EVENTYPE="eventype";
;
/**
*��������
*/
public static final String RISK="risk";
;
/**
*��������
*/
public UFDate purchasedate;
/**
*��������
*/
public UFDate enddate;
/**
*��Ϣ����
*/
public UFDate interestdate;
/**
*��Ϣ����
*/
public static final String INTERESTDAY="interestday";
;
/**
*Ԥ��������
*/
public static final String EXPECTEDRATE="expectedrate";
;
/**
*Ԥ������
*/
public static final String EXPECTEDMONEY="expectedmoney";
;
/**
*��Ϣ����
*/
public static final String PAYTYPE="paytype";
;
/**
*��Ϣ����
*/
public static final String PAYPERIOD="payperiod";
;
/**
*��Ϣ��
*/
public UFDate settledate;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� vdef9��Getter����.���������Զ�����9
*  ��������:2018-9-1
* @return java.lang.String
*/
public String getVdef9() {
return (java.lang.String) this.getAttributeValue( InvestApplyVO.VDEF9);
} 

/**
* ����vdef9��Setter����.���������Զ�����9
* ��������:2018-9-1
* @param newVdef9 java.lang.String
*/
public void setVdef9 ( String vdef9) {
this.setAttributeValue( InvestApplyVO.VDEF9,vdef9);
} 
 
/**
* ���� olcmoney��Getter����.����������֯�������
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getOlcmoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestApplyVO.OLCMONEY);
} 

/**
* ����olcmoney��Setter����.����������֯�������
* ��������:2018-9-1
* @param newOlcmoney nc.vo.pub.lang.UFDouble
*/
public void setOlcmoney ( String olcmoney) {
this.setAttributeValue( InvestApplyVO.OLCMONEY,olcmoney);
} 
 
/**
* ���� glcmoney��Getter����.�����������ű��ҽ��
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getGlcmoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestApplyVO.GLCMONEY);
} 

/**
* ����glcmoney��Setter����.�����������ű��ҽ��
* ��������:2018-9-1
* @param newGlcmoney nc.vo.pub.lang.UFDouble
*/
public void setGlcmoney ( String glcmoney) {
this.setAttributeValue( InvestApplyVO.GLCMONEY,glcmoney);
} 
 
/**
* ���� gllmoney��Getter����.��������ȫ�ֱ��Ƚ��
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getGllmoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestApplyVO.GLLMONEY);
} 

/**
* ����gllmoney��Setter����.��������ȫ�ֱ��Ƚ��
* ��������:2018-9-1
* @param newGllmoney nc.vo.pub.lang.UFDouble
*/
public void setGllmoney ( String gllmoney) {
this.setAttributeValue( InvestApplyVO.GLLMONEY,gllmoney);
} 
 
/**
* ���� issuebank��Getter����.����������������
*  ��������:2018-9-1
* @return nc.vo.bd.banktype.BankTypeVO
*/
public String getIssuebank() {
return (java.lang.String) this.getAttributeValue( InvestApplyVO.ISSUEBANK);
} 

/**
* ����issuebank��Setter����.����������������
* ��������:2018-9-1
* @param newIssuebank nc.vo.bd.banktype.BankTypeVO
*/
public void setIssuebank ( String issuebank) {
this.setAttributeValue( InvestApplyVO.ISSUEBANK,issuebank);
} 
 
/**
* ���� boundary��Getter����.��������������
*  ��������:2018-9-1
* @return nc.vo.ifm.BoundaryEnum
*/
public Integer getBoundary() {
return (Integer) this.getAttributeValue( InvestApplyVO.BOUNDARY);
} 

/**
* ����boundary��Setter����.��������������
* ��������:2018-9-1
* @param newBoundary nc.vo.ifm.BoundaryEnum
*/
public void setBoundary ( Integer boundary) {
this.setAttributeValue( InvestApplyVO.BOUNDARY,boundary);
} 
 
/**
* ���� banknetwork��Getter����.����������������
*  ��������:2018-9-1
* @return nc.vo.bd.bankdoc.BankdocVO
*/
public String getBanknetwork() {
return (String) this.getAttributeValue( InvestApplyVO.BANKNETWORK);
} 

/**
* ����banknetwork��Setter����.����������������
* ��������:2018-9-1
* @param newBanknetwork nc.vo.bd.bankdoc.BankdocVO
*/
public void setBanknetwork ( String banknetwork) {
this.setAttributeValue( InvestApplyVO.BANKNETWORK,banknetwork);
} 
 
/**
* ���� billstatus��Getter����.������������״̬
*  ��������:2018-9-1
* @return nc.vo.ifm.BillStatusEnum
*/
public Integer getBillstatus() {
return (Integer) this.getAttributeValue( InvestApplyVO.BILLSTATUS);
} 

/**
* ����billstatus��Setter����.������������״̬
* ��������:2018-9-1
* @param newBillstatus nc.vo.ifm.BillStatusEnum
*/
public void setBillstatus ( Integer billstatus) {
this.setAttributeValue( InvestApplyVO.BILLSTATUS,billstatus);
} 
 
/**
* ���� settleaccount��Getter����.�������������˻�
*  ��������:2018-9-1
* @return nc.vo.bd.bankaccount.BankAccSubVO
*/
public String getSettleaccount() {
return (String) this.getAttributeValue( InvestApplyVO.SETTLEACCOUNT);
} 

/**
* ����settleaccount��Setter����.�������������˻�
* ��������:2018-9-1
* @param newSettleaccount nc.vo.bd.bankaccount.BankAccSubVO
*/
public void setSettleaccount ( String settleaccount) {
this.setAttributeValue( InvestApplyVO.SETTLEACCOUNT,settleaccount);
} 
 
/**
* ���� invest��Getter����.������������˻�
*  ��������:2018-9-1
* @return nc.vo.bd.bankaccount.BankAccSubVO
*/
public String getInvest() {
return (String) this.getAttributeValue( InvestApplyVO.INVEST);
} 

/**
* ����invest��Setter����.������������˻�
* ��������:2018-9-1
* @param newInvest nc.vo.bd.bankaccount.BankAccSubVO
*/
public void setInvest ( String invest) {
this.setAttributeValue( InvestApplyVO.INVEST,invest);
} 
 
/**
* ���� productcode��Getter����.����������Ʒ����
*  ��������:2018-9-1
* @return java.lang.String
*/
public String getProductcode() {
return (java.lang.String) this.getAttributeValue( InvestApplyVO.PRODUCTCODE);
} 

/**
* ����productcode��Setter����.����������Ʒ����
* ��������:2018-9-1
* @param newProductcode java.lang.String
*/
public void setProductcode ( String productcode) {
this.setAttributeValue( InvestApplyVO.PRODUCTCODE,productcode);
} 
 
/**
* ���� productname��Getter����.����������Ʒ����
*  ��������:2018-9-1
* @return java.lang.String
*/
public String getProductname() {
return (java.lang.String) this.getAttributeValue( InvestApplyVO.PRODUCTNAME);
} 

/**
* ����productname��Setter����.����������Ʒ����
* ��������:2018-9-1
* @param newProductname java.lang.String
*/
public void setProductname ( String productname) {
this.setAttributeValue( InvestApplyVO.PRODUCTNAME,productname);
} 
 
/**
* ���� money��Getter����.����������ƽ��
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getMoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestApplyVO.MONEY);
} 

/**
* ����money��Setter����.����������ƽ��
* ��������:2018-9-1
* @param newMoney nc.vo.pub.lang.UFDouble
*/
public void setMoney ( String money) {
this.setAttributeValue( InvestApplyVO.MONEY,money);
} 
 
/**
* ���� eventype��Getter����.����������������
*  ��������:2018-9-1
* @return nc.vo.ifm.EvenEnum
*/
public Integer getEventype() {
return (Integer) this.getAttributeValue( InvestApplyVO.EVENTYPE);
} 

/**
* ����eventype��Setter����.����������������
* ��������:2018-9-1
* @param newEventype nc.vo.ifm.EvenEnum
*/
public void setEventype ( Integer eventype) {
this.setAttributeValue( InvestApplyVO.EVENTYPE,eventype);
} 
 
/**
* ���� risk��Getter����.����������������
*  ��������:2018-9-1
* @return nc.vo.ifm.RiskEnum
*/
public Integer getRisk() {
return (Integer) this.getAttributeValue( InvestApplyVO.RISK);
} 

/**
* ����risk��Setter����.����������������
* ��������:2018-9-1
* @param newRisk nc.vo.ifm.RiskEnum
*/
public void setRisk ( Integer risk) {
this.setAttributeValue( InvestApplyVO.RISK,risk);
} 
 
/**
* ���� purchasedate��Getter����.����������������
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getPurchasedate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue( "purchasedate");
} 

/**
* ����purchasedate��Setter����.����������������
* ��������:2018-9-1
* @param newPurchasedate nc.vo.pub.lang.UFDate
*/
public void setPurchasedate ( UFDate purchasedate) {
this.setAttributeValue( "purchasedate",purchasedate);
} 
 
/**
* ���� enddate��Getter����.����������������
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getEnddate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue( "enddate");
} 

/**
* ����enddate��Setter����.����������������
* ��������:2018-9-1
* @param newEnddate nc.vo.pub.lang.UFDate
*/
public void setEnddate ( UFDate enddate) {
this.setAttributeValue( "enddate",enddate);
} 
 
/**
* ���� interestdate��Getter����.����������Ϣ����
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getInterestdate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue( "interestdate");
} 

/**
* ����interestdate��Setter����.����������Ϣ����
* ��������:2018-9-1
* @param newInterestdate nc.vo.pub.lang.UFDate
*/
public void setInterestdate ( UFDate interestdate) {
this.setAttributeValue( "interestdate",interestdate);
} 
 
/**
* ���� interestday��Getter����.����������Ϣ����
*  ��������:2018-9-1
* @return java.lang.Integer
*/
public Integer getInterestday() {
return (java.lang.Integer) this.getAttributeValue( InvestApplyVO.INTERESTDAY);
} 

/**
* ����interestday��Setter����.����������Ϣ����
* ��������:2018-9-1
* @param newInterestday java.lang.Integer
*/
public void setInterestday ( Integer interestday) {
this.setAttributeValue( InvestApplyVO.INTERESTDAY,interestday);
} 
 
/**
* ���� expectedrate��Getter����.��������Ԥ��������
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getExpectedrate() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue("expectedrate");
} 

/**
* ����expectedrate��Setter����.��������Ԥ��������
* ��������:2018-9-1
* @param newExpectedrate nc.vo.pub.lang.UFDouble
*/
public void setExpectedrate ( String expectedrate) {
this.setAttributeValue( InvestApplyVO.EXPECTEDRATE,expectedrate);
} 
 
/**
* ���� expectedmoney��Getter����.��������Ԥ������
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getExpectedmoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestApplyVO.EXPECTEDMONEY);
} 

/**
* ����expectedmoney��Setter����.��������Ԥ������
* ��������:2018-9-1
* @param newExpectedmoney nc.vo.pub.lang.UFDouble
*/
public void setExpectedmoney ( String expectedmoney) {
this.setAttributeValue( InvestApplyVO.EXPECTEDMONEY,expectedmoney);
} 
 
/**
* ���� paytype��Getter����.����������Ϣ����
*  ��������:2018-9-1
* @return nc.vo.ifm.PayTypeEnum
*/
public Integer getPaytype() {
return (Integer) this.getAttributeValue( InvestApplyVO.PAYTYPE);
} 

/**
* ����paytype��Setter����.����������Ϣ����
* ��������:2018-9-1
* @param newPaytype nc.vo.ifm.PayTypeEnum
*/
public void setPaytype ( Integer paytype) {
this.setAttributeValue( InvestApplyVO.PAYTYPE,paytype);
} 
 
/**
* ���� payperiod��Getter����.����������Ϣ����
*  ��������:2018-9-1
* @return java.lang.Integer
*/
public Integer getPayperiod() {
return (java.lang.Integer) this.getAttributeValue( InvestApplyVO.PAYPERIOD);
} 

/**
* ����payperiod��Setter����.����������Ϣ����
* ��������:2018-9-1
* @param newPayperiod java.lang.Integer
*/
public void setPayperiod ( Integer payperiod) {
this.setAttributeValue( InvestApplyVO.PAYPERIOD,payperiod);
} 
 
/**
* ���� settledate��Getter����.����������Ϣ��
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getSettledate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue("settledate");
} 

/**
* ����settledate��Setter����.����������Ϣ��
* ��������:2018-9-1
* @param newSettledate nc.vo.pub.lang.UFDate
*/
public void setSettledate ( UFDate settledate) {
this.setAttributeValue("settledate",settledate);
} 
 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2018-9-1
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
    