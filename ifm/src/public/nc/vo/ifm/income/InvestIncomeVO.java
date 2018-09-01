package nc.vo.ifm.income;

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
 
public class InvestIncomeVO extends SuperVO {
	
/**
*�Զ�����8
*/
public static final String VDEF8="vdef8";
;
/**
*�Զ�����9
*/
public static final String VDEF9="vdef9";
;
/**
*��֯���ҽ��
*/
public static final String OLCMOENY="olcmoeny";
;
/**
*���ű��ҽ��
*/
public static final String GLCMOENY="glcmoeny";
;
/**
*ȫ�ֱ��Ƚ��
*/
public static final String GLLMOENY="gllmoeny";
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
*��������
*/
public static final String ISSUEBANK="issuebank";
;
/**
*����״̬
*/
public static final String BILLSTATUS="billstatus";
;
/**
*�տ������˻�
*/
public static final String GATHERING="gathering";
;
/**
*����˻�
*/
public static final String INVESTACCOUNT="investaccount";
;
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
*��������
*/
public UFDate enddate;
/**
*ʵ������
*/
public Boolean actualmoeny;
/**
*����˰��
*/
public static final String INCOMERATE="incomerate";
;
/**
*����˰��
*/
public static final String INCOMEMONEY="incomemoney";
;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� vdef8��Getter����.���������Զ�����8
*  ��������:2018-9-1
* @return java.lang.String
*/
public String getVdef8() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF8);
} 

/**
* ����vdef8��Setter����.���������Զ�����8
* ��������:2018-9-1
* @param newVdef8 java.lang.String
*/
public void setVdef8 ( String vdef8) {
this.setAttributeValue( InvestIncomeVO.VDEF8,vdef8);
} 
 
/**
* ���� vdef9��Getter����.���������Զ�����9
*  ��������:2018-9-1
* @return java.lang.String
*/
public String getVdef9() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF9);
} 

/**
* ����vdef9��Setter����.���������Զ�����9
* ��������:2018-9-1
* @param newVdef9 java.lang.String
*/
public void setVdef9 ( String vdef9) {
this.setAttributeValue( InvestIncomeVO.VDEF9,vdef9);
} 
 
/**
* ���� olcmoeny��Getter����.����������֯���ҽ��
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getOlcmoeny() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.OLCMOENY);
} 

/**
* ����olcmoeny��Setter����.����������֯���ҽ��
* ��������:2018-9-1
* @param newOlcmoeny nc.vo.pub.lang.UFDouble
*/
public void setOlcmoeny ( String olcmoeny) {
this.setAttributeValue( InvestIncomeVO.OLCMOENY,olcmoeny);
} 
 
/**
* ���� glcmoeny��Getter����.�����������ű��ҽ��
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getGlcmoeny() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.GLCMOENY);
} 

/**
* ����glcmoeny��Setter����.�����������ű��ҽ��
* ��������:2018-9-1
* @param newGlcmoeny nc.vo.pub.lang.UFDouble
*/
public void setGlcmoeny ( String glcmoeny) {
this.setAttributeValue( InvestIncomeVO.GLCMOENY,glcmoeny);
} 
 
/**
* ���� gllmoeny��Getter����.��������ȫ�ֱ��Ƚ��
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getGllmoeny() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.GLLMOENY);
} 

/**
* ����gllmoeny��Setter����.��������ȫ�ֱ��Ƚ��
* ��������:2018-9-1
* @param newGllmoeny nc.vo.pub.lang.UFDouble
*/
public void setGllmoeny ( String gllmoeny) {
this.setAttributeValue( InvestIncomeVO.GLLMOENY,gllmoeny);
} 
 
/**
* ���� productcode��Getter����.����������Ʒ����
*  ��������:2018-9-1
* @return java.lang.String
*/
public String getProductcode() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.PRODUCTCODE);
} 

/**
* ����productcode��Setter����.����������Ʒ����
* ��������:2018-9-1
* @param newProductcode java.lang.String
*/
public void setProductcode ( String productcode) {
this.setAttributeValue( InvestIncomeVO.PRODUCTCODE,productcode);
} 
 
/**
* ���� productname��Getter����.����������Ʒ����
*  ��������:2018-9-1
* @return java.lang.String
*/
public String getProductname() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.PRODUCTNAME);
} 

/**
* ����productname��Setter����.����������Ʒ����
* ��������:2018-9-1
* @param newProductname java.lang.String
*/
public void setProductname ( String productname) {
this.setAttributeValue( InvestIncomeVO.PRODUCTNAME,productname);
} 
 
/**
* ���� issuebank��Getter����.����������������
*  ��������:2018-9-1
* @return nc.vo.bd.banktype.BankTypeVO
*/
public String getIssuebank() {
return (String) this.getAttributeValue( InvestIncomeVO.ISSUEBANK);
} 

/**
* ����issuebank��Setter����.����������������
* ��������:2018-9-1
* @param newIssuebank nc.vo.bd.banktype.BankTypeVO
*/
public void setIssuebank ( String issuebank) {
this.setAttributeValue( InvestIncomeVO.ISSUEBANK,issuebank);
} 
 
/**
* ���� billstatus��Getter����.������������״̬
*  ��������:2018-9-1
* @return nc.vo.ifm.BillStatusEnum
*/
public Integer getBillstatus() {
return (Integer) this.getAttributeValue( InvestIncomeVO.BILLSTATUS);
} 

/**
* ����billstatus��Setter����.������������״̬
* ��������:2018-9-1
* @param newBillstatus nc.vo.ifm.BillStatusEnum
*/
public void setBillstatus ( Integer billstatus) {
this.setAttributeValue( InvestIncomeVO.BILLSTATUS,billstatus);
} 
 
/**
* ���� gathering��Getter����.���������տ������˻�
*  ��������:2018-9-1
* @return nc.vo.bd.bankaccount.BankAccSubVO
*/
public String getGathering() {
return (String) this.getAttributeValue( InvestIncomeVO.GATHERING);
} 

/**
* ����gathering��Setter����.���������տ������˻�
* ��������:2018-9-1
* @param newGathering nc.vo.bd.bankaccount.BankAccSubVO
*/
public void setGathering ( String gathering) {
this.setAttributeValue( InvestIncomeVO.GATHERING,gathering);
} 
 
/**
* ���� investaccount��Getter����.������������˻�
*  ��������:2018-9-1
* @return nc.vo.bd.bankaccount.BankAccSubVO
*/
public String getInvestaccount() {
return (String) this.getAttributeValue( InvestIncomeVO.INVESTACCOUNT);
} 

/**
* ����investaccount��Setter����.������������˻�
* ��������:2018-9-1
* @param newInvestaccount nc.vo.bd.bankaccount.BankAccSubVO
*/
public void setInvestaccount ( String investaccount) {
this.setAttributeValue( InvestIncomeVO.INVESTACCOUNT,investaccount);
} 
 
/**
* ���� interestday��Getter����.����������Ϣ����
*  ��������:2018-9-1
* @return java.lang.Integer
*/
public Integer getInterestday() {
return (java.lang.Integer) this.getAttributeValue( InvestIncomeVO.INTERESTDAY);
} 

/**
* ����interestday��Setter����.����������Ϣ����
* ��������:2018-9-1
* @param newInterestday java.lang.Integer
*/
public void setInterestday ( Integer interestday) {
this.setAttributeValue( InvestIncomeVO.INTERESTDAY,interestday);
} 
 
/**
* ���� expectedrate��Getter����.��������Ԥ��������
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getExpectedrate() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.EXPECTEDRATE);
} 

/**
* ����expectedrate��Setter����.��������Ԥ��������
* ��������:2018-9-1
* @param newExpectedrate nc.vo.pub.lang.UFDouble
*/
public void setExpectedrate ( String expectedrate) {
this.setAttributeValue( InvestIncomeVO.EXPECTEDRATE,expectedrate);
} 
 
/**
* ���� expectedmoney��Getter����.��������Ԥ������
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getExpectedmoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.EXPECTEDMONEY);
} 

/**
* ����expectedmoney��Setter����.��������Ԥ������
* ��������:2018-9-1
* @param newExpectedmoney nc.vo.pub.lang.UFDouble
*/
public void setExpectedmoney ( String expectedmoney) {
this.setAttributeValue( InvestIncomeVO.EXPECTEDMONEY,expectedmoney);
} 
 
/**
* ���� enddate��Getter����.����������������
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getEnddate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue("enddate");
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
* ���� actualmoeny��Getter����.��������ʵ������
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFBoolean
*/
public UFBoolean getActualmoeny() {
return (nc.vo.pub.lang.UFBoolean) this.getAttributeValue("actualmoeny");
} 

/**
* ����actualmoeny��Setter����.��������ʵ������
* ��������:2018-9-1
* @param newActualmoeny nc.vo.pub.lang.UFBoolean
*/
public void setActualmoeny ( Boolean actualmoeny) {
this.setAttributeValue( "actualmoeny",actualmoeny);
} 
 
/**
* ���� incomerate��Getter����.������������˰��
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getIncomerate() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.INCOMERATE);
} 

/**
* ����incomerate��Setter����.������������˰��
* ��������:2018-9-1
* @param newIncomerate nc.vo.pub.lang.UFDouble
*/
public void setIncomerate ( String incomerate) {
this.setAttributeValue( InvestIncomeVO.INCOMERATE,incomerate);
} 
 
/**
* ���� incomemoney��Getter����.������������˰��
*  ��������:2018-9-1
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getIncomemoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.INCOMEMONEY);
} 

/**
* ����incomemoney��Setter����.������������˰��
* ��������:2018-9-1
* @param newIncomemoney nc.vo.pub.lang.UFDouble
*/
public void setIncomemoney ( String incomemoney) {
this.setAttributeValue( InvestIncomeVO.INCOMEMONEY,incomemoney);
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
    return VOMetaFactory.getInstance().getVOMeta("ifm.InvestIncomeVO");
    }
   }
    