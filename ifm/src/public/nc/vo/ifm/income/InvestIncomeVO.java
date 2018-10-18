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
 *  ��������:2018-9-6
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class InvestIncomeVO extends SuperVO {
	
	
/**
*ifm_income������
*/
public static final String PK_INCOME = "pk_income";
//��Դ�����ĸ��ֶ�
public static final String PK_SRCBILL = "pk_srcbill";
public static final String PK_SRCBILLTYPE = "pk_srcbilltype";
public static final String SRCBILLTYPECODE = "srcbilltypecode";
public static final String SRCBILLNO = "srcbillno";
/**
*�깺���
*/
public static final String VBILLNO="vbillno";
;
/**
*����״̬
*/
public static final String VBILLSTATUS="vbillstatus";
;
/**
*����
*/
public static final String PK_GROUP="pk_group";
;
/**
*������֯
*/
public static final String PK_ORG="pk_org";
;
/**
*������֯�汾
*/
public static final String PK_ORG_V="pk_org_v";
;
/**
*����
*/
public static final String PK_CURRTYPE="pk_currtype";
;
/**
*��֯��λ��
*/
public static final String PK_OLCCURR="pk_olccurr";
;
/**
*��ע
*/
public static final String REMARK="remark";
;
/**
*�Զ�����1
*/
public static final String VDEF1="vdef1";
;
/**
*�Զ�����2
*/
public static final String VDEF2="vdef2";
;
/**
*�Զ�����3
*/
public static final String VDEF3="vdef3";
;
/**
*�Զ�����4
*/
public static final String VDEF4="vdef4";
;
/**
*�Զ�����5
*/
public static final String VDEF5="vdef5";
;
/**
*�Զ�����6
*/
public static final String VDEF6="vdef6";
;
/**
*�Զ�����7
*/
public static final String VDEF7="vdef7";
;
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
*�Զ�����10
*/
public static final String VDEF10="vdef10";
;
/**
*�Զ�����11
*/
public static final String VDEF11="vdef11";
;
/**
*�Զ�����12
*/
public static final String VDEF12="vdef12";
;
/**
*�Զ�����13
*/
public static final String VDEF13="vdef13";
;
/**
*�Զ�����14
*/
public static final String VDEF14="vdef14";
;
/**
*�Զ�����15
*/
public static final String VDEF15="vdef15";
;
/**
*�Զ�����16
*/
public static final String VDEF16="vdef16";
;
/**
*�Զ�����17
*/
public static final String VDEF17="vdef17";
;
/**
*�Զ�����18
*/
public static final String VDEF18="vdef18";
;
/**
*�Զ�����19
*/
public static final String VDEF19="vdef19";
;
/**
*�Զ�����20
*/
public static final String VDEF20="vdef20";
;
/**
*��֯���һ���
*/
public static final String OLCRATE="olcrate";
;
/**
*��֯���ҽ��
*/
public static final String OLCMOENY="olcmoeny";
;
/**
*���ű��һ���
*/
public static final String GLCRATE="glcrate";
;
/**
*���ű��ҽ��
*/
public static final String GLCMOENY="glcmoeny";
;
/**
*ȫ�ֱ��һ���
*/
public static final String GLLCRATE="gllcrate";
;
/**
*ȫ�ֱ��Ƚ��
*/
public static final String GLLMOENY="gllmoeny";
;
/**
*�Ƶ���
*/
public static final String BILLMAKER="billmaker";
;
/**
*�Ƶ�����
*/
public UFDate billmakedate;
/**
*�Ƶ�ʱ��
*/
public UFDateTime billmaketime;
/**
*������
*/
public static final String APPROVER="approver";
;
/**
*��������
*/
public UFDate approvedate;
/**
*��������
*/
public static final String APPROVENOTE="approvenote";
;
/**
*������
*/
public static final String CREATOR="creator";
;
/**
*����ʱ��
*/
public UFDateTime creationtime;
/**
*����޸���
*/
public static final String MODIFIER="modifier";
;
/**
*����޸�ʱ��
*/
public UFDateTime modifiedtime;
/**
*ҵ������
*/
public static final String PK_BUSITYPE="pk_busitype";
;
/**
*������������
*/
public static final String PK_BILLTYPEID="pk_billtypeid";
;
/**
*��������
*/
public static final String PK_BILLTYPECODE="pk_billtypecode";
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
public static final String ACTUALMOENY="actualmoeny";
;
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
 *��������
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
public static final String PK_REDEEM = "pk_redeem";//����������ӿ���

/**
*ʱ���
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
* ���� vbillno��Getter����.���������깺���
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVbillno() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VBILLNO);
} 

/**
* ����vbillno��Setter����.���������깺���
* ��������:2018-9-6
* @param newVbillno java.lang.String
*/
public void setVbillno ( String vbillno) {
this.setAttributeValue( InvestIncomeVO.VBILLNO,vbillno);
} 
 
/**
* ���� vbillstatus��Getter����.������������״̬
*  ��������:2018-9-6
* @return nc.vo.pub.pf.BillStatusEnum
*/
public Integer getVbillstatus() {
return (Integer) this.getAttributeValue( InvestIncomeVO.VBILLSTATUS);
} 

/**
* ����vbillstatus��Setter����.������������״̬
* ��������:2018-9-6
* @param newVbillstatus nc.vo.pub.pf.BillStatusEnum
*/
public void setVbillstatus ( Integer vbillstatus) {
this.setAttributeValue( InvestIncomeVO.VBILLSTATUS,vbillstatus);
} 
/**
 * ���� source��Getter����.������������״̬
 *  ��������:2018-9-6
 * @return nc.vo.pub.pf.SourceEnum
 */
public Integer getSource() {
	return (Integer) this.getAttributeValue( InvestIncomeVO.SOURCE);
} 

/**
 * ����source��Setter����.������������״̬
 * ��������:2018-9-6
 * @param source nc.vo.pub.pf.SourceEnum
 */
public void setSource ( Integer source) {
	this.setAttributeValue( InvestIncomeVO.SOURCE,source);
} 

/**
 * ���� pk_income��Getter����.��������ifm_income����
 *  ��������:2018-9-6
 * @return ifm_income
 */
public String getPk_income() {
	return (String) this.getAttributeValue( InvestIncomeVO.PK_INCOME);
} 

/**
 * ����pk_income��Setter����.��������ifm_income����
 * ��������:2018-9-6
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
* ���� pk_group��Getter����.������������
*  ��������:2018-9-6
* @return nc.vo.org.GroupVO
*/
public String getPk_group() {
return (String) this.getAttributeValue( InvestIncomeVO.PK_GROUP);
} 

/**
* ����pk_group��Setter����.������������
* ��������:2018-9-6
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( String pk_group) {
this.setAttributeValue( InvestIncomeVO.PK_GROUP,pk_group);
} 
 
/**
* ���� pk_org��Getter����.��������������֯
*  ��������:2018-9-6
* @return nc.vo.org.FinanceOrgVO
*/
public String getPk_org() {
return (String) this.getAttributeValue( InvestIncomeVO.PK_ORG);
} 

/**
* ����pk_org��Setter����.��������������֯
* ��������:2018-9-6
* @param newPk_org nc.vo.org.FinanceOrgVO
*/
public void setPk_org ( String pk_org) {
this.setAttributeValue( InvestIncomeVO.PK_ORG,pk_org);
} 
 
/**
* ���� pk_org_v��Getter����.��������������֯�汾
*  ��������:2018-9-6
* @return nc.vo.vorg.FinanceOrgVersionVO
*/
public String getPk_org_v() {
return (String) this.getAttributeValue( InvestIncomeVO.PK_ORG_V);
} 

/**
* ����pk_org_v��Setter����.��������������֯�汾
* ��������:2018-9-6
* @param newPk_org_v nc.vo.vorg.FinanceOrgVersionVO
*/
public void setPk_org_v ( String pk_org_v) {
this.setAttributeValue( InvestIncomeVO.PK_ORG_V,pk_org_v);
} 
 
/**
* ���� pk_currtype��Getter����.������������
*  ��������:2018-9-6
* @return nc.vo.bd.currtype.CurrtypeVO
*/
public String getPk_currtype() {
return (String) this.getAttributeValue( InvestIncomeVO.PK_CURRTYPE);
} 

/**
* ����pk_currtype��Setter����.������������
* ��������:2018-9-6
* @param newPk_currtype nc.vo.bd.currtype.CurrtypeVO
*/
public void setPk_currtype ( String pk_currtype) {
this.setAttributeValue( InvestIncomeVO.PK_CURRTYPE,pk_currtype);
} 
 
/**
* ���� pk_olccurr��Getter����.����������֯��λ��
*  ��������:2018-9-6
* @return nc.vo.bd.currtype.CurrtypeVO
*/
public String getPk_olccurr() {
return (String) this.getAttributeValue( InvestIncomeVO.PK_OLCCURR);
} 

/**
* ����pk_olccurr��Setter����.����������֯��λ��
* ��������:2018-9-6
* @param newPk_olccurr nc.vo.bd.currtype.CurrtypeVO
*/
public void setPk_olccurr ( String pk_olccurr) {
this.setAttributeValue( InvestIncomeVO.PK_OLCCURR,pk_olccurr);
} 
 
/**
* ���� remark��Getter����.����������ע
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getRemark() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.REMARK);
} 

/**
* ����remark��Setter����.����������ע
* ��������:2018-9-6
* @param newRemark java.lang.String
*/
public void setRemark ( String remark) {
this.setAttributeValue( InvestIncomeVO.REMARK,remark);
} 
 
/**
* ���� vdef1��Getter����.���������Զ�����1
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef1() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF1);
} 

/**
* ����vdef1��Setter����.���������Զ�����1
* ��������:2018-9-6
* @param newVdef1 java.lang.String
*/
public void setVdef1 ( String vdef1) {
this.setAttributeValue( InvestIncomeVO.VDEF1,vdef1);
} 
 
/**
* ���� vdef2��Getter����.���������Զ�����2
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef2() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF2);
} 

/**
* ����vdef2��Setter����.���������Զ�����2
* ��������:2018-9-6
* @param newVdef2 java.lang.String
*/
public void setVdef2 ( String vdef2) {
this.setAttributeValue( InvestIncomeVO.VDEF2,vdef2);
} 
 
/**
* ���� vdef3��Getter����.���������Զ�����3
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef3() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF3);
} 

/**
* ����vdef3��Setter����.���������Զ�����3
* ��������:2018-9-6
* @param newVdef3 java.lang.String
*/
public void setVdef3 ( String vdef3) {
this.setAttributeValue( InvestIncomeVO.VDEF3,vdef3);
} 
 
/**
* ���� vdef4��Getter����.���������Զ�����4
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef4() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF4);
} 

/**
* ����vdef4��Setter����.���������Զ�����4
* ��������:2018-9-6
* @param newVdef4 java.lang.String
*/
public void setVdef4 ( String vdef4) {
this.setAttributeValue( InvestIncomeVO.VDEF4,vdef4);
} 
 
/**
* ���� vdef5��Getter����.���������Զ�����5
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef5() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF5);
} 

/**
* ����vdef5��Setter����.���������Զ�����5
* ��������:2018-9-6
* @param newVdef5 java.lang.String
*/
public void setVdef5 ( String vdef5) {
this.setAttributeValue( InvestIncomeVO.VDEF5,vdef5);
} 
 
/**
* ���� vdef6��Getter����.���������Զ�����6
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef6() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF6);
} 

/**
* ����vdef6��Setter����.���������Զ�����6
* ��������:2018-9-6
* @param newVdef6 java.lang.String
*/
public void setVdef6 ( String vdef6) {
this.setAttributeValue( InvestIncomeVO.VDEF6,vdef6);
} 
 
/**
* ���� vdef7��Getter����.���������Զ�����7
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef7() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF7);
} 

/**
* ����vdef7��Setter����.���������Զ�����7
* ��������:2018-9-6
* @param newVdef7 java.lang.String
*/
public void setVdef7 ( String vdef7) {
this.setAttributeValue( InvestIncomeVO.VDEF7,vdef7);
} 
 
/**
* ���� vdef8��Getter����.���������Զ�����8
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef8() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF8);
} 

/**
* ����vdef8��Setter����.���������Զ�����8
* ��������:2018-9-6
* @param newVdef8 java.lang.String
*/
public void setVdef8 ( String vdef8) {
this.setAttributeValue( InvestIncomeVO.VDEF8,vdef8);
} 
 
/**
* ���� vdef9��Getter����.���������Զ�����9
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef9() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF9);
} 

/**
* ����vdef9��Setter����.���������Զ�����9
* ��������:2018-9-6
* @param newVdef9 java.lang.String
*/
public void setVdef9 ( String vdef9) {
this.setAttributeValue( InvestIncomeVO.VDEF9,vdef9);
} 
 
/**
* ���� vdef10��Getter����.���������Զ�����10
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef10() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF10);
} 

/**
* ����vdef10��Setter����.���������Զ�����10
* ��������:2018-9-6
* @param newVdef10 java.lang.String
*/
public void setVdef10 ( String vdef10) {
this.setAttributeValue( InvestIncomeVO.VDEF10,vdef10);
} 
 
/**
* ���� vdef11��Getter����.���������Զ�����11
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef11() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF11);
} 

/**
* ����vdef11��Setter����.���������Զ�����11
* ��������:2018-9-6
* @param newVdef11 java.lang.String
*/
public void setVdef11 ( String vdef11) {
this.setAttributeValue( InvestIncomeVO.VDEF11,vdef11);
} 
 
/**
* ���� vdef12��Getter����.���������Զ�����12
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef12() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF12);
} 

/**
* ����vdef12��Setter����.���������Զ�����12
* ��������:2018-9-6
* @param newVdef12 java.lang.String
*/
public void setVdef12 ( String vdef12) {
this.setAttributeValue( InvestIncomeVO.VDEF12,vdef12);
} 
 
/**
* ���� vdef13��Getter����.���������Զ�����13
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef13() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF13);
} 

/**
* ����vdef13��Setter����.���������Զ�����13
* ��������:2018-9-6
* @param newVdef13 java.lang.String
*/
public void setVdef13 ( String vdef13) {
this.setAttributeValue( InvestIncomeVO.VDEF13,vdef13);
} 
 
/**
* ���� vdef14��Getter����.���������Զ�����14
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef14() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF14);
} 

/**
* ����vdef14��Setter����.���������Զ�����14
* ��������:2018-9-6
* @param newVdef14 java.lang.String
*/
public void setVdef14 ( String vdef14) {
this.setAttributeValue( InvestIncomeVO.VDEF14,vdef14);
} 
 
/**
* ���� vdef15��Getter����.���������Զ�����15
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef15() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF15);
} 

/**
* ����vdef15��Setter����.���������Զ�����15
* ��������:2018-9-6
* @param newVdef15 java.lang.String
*/
public void setVdef15 ( String vdef15) {
this.setAttributeValue( InvestIncomeVO.VDEF15,vdef15);
} 
 
/**
* ���� vdef16��Getter����.���������Զ�����16
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef16() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF16);
} 

/**
* ����vdef16��Setter����.���������Զ�����16
* ��������:2018-9-6
* @param newVdef16 java.lang.String
*/
public void setVdef16 ( String vdef16) {
this.setAttributeValue( InvestIncomeVO.VDEF16,vdef16);
} 
 
/**
* ���� vdef17��Getter����.���������Զ�����17
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef17() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF17);
} 

/**
* ����vdef17��Setter����.���������Զ�����17
* ��������:2018-9-6
* @param newVdef17 java.lang.String
*/
public void setVdef17 ( String vdef17) {
this.setAttributeValue( InvestIncomeVO.VDEF17,vdef17);
} 
 
/**
* ���� vdef18��Getter����.���������Զ�����18
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef18() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF18);
} 

/**
* ����vdef18��Setter����.���������Զ�����18
* ��������:2018-9-6
* @param newVdef18 java.lang.String
*/
public void setVdef18 ( String vdef18) {
this.setAttributeValue( InvestIncomeVO.VDEF18,vdef18);
} 
 
/**
* ���� vdef19��Getter����.���������Զ�����19
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef19() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF19);
} 

/**
* ����vdef19��Setter����.���������Զ�����19
* ��������:2018-9-6
* @param newVdef19 java.lang.String
*/
public void setVdef19 ( String vdef19) {
this.setAttributeValue( InvestIncomeVO.VDEF19,vdef19);
} 
 
/**
* ���� vdef20��Getter����.���������Զ�����20
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getVdef20() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.VDEF20);
} 

/**
* ����vdef20��Setter����.���������Զ�����20
* ��������:2018-9-6
* @param newVdef20 java.lang.String
*/
public void setVdef20 ( String vdef20) {
this.setAttributeValue( InvestIncomeVO.VDEF20,vdef20);
} 
 
/**
* ���� olcrate��Getter����.����������֯���һ���
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getOlcrate() {
return (String) this.getAttributeValue( InvestIncomeVO.OLCRATE);
} 

/**
* ����olcrate��Setter����.����������֯���һ���
* ��������:2018-9-6
* @param newOlcrate nc.vo.pub.lang.UFDouble
*/
public void setOlcrate ( String olcrate) {
this.setAttributeValue( InvestIncomeVO.OLCRATE,olcrate);
} 
 
/**
* ���� olcmoeny��Getter����.����������֯���ҽ��
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getOlcmoeny() {
	return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.OLCMOENY);
} 

/**
* ����olcmoeny��Setter����.����������֯���ҽ��
* ��������:2018-9-6
* @param newOlcmoeny nc.vo.pub.lang.UFDouble
*/
public void setOlcmoeny ( String olcmoeny) {
this.setAttributeValue( InvestIncomeVO.OLCMOENY,olcmoeny);
} 
 
/**
* ���� glcrate��Getter����.�����������ű��һ���
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getGlcrate() {
return (String) this.getAttributeValue( InvestIncomeVO.GLCRATE);
} 

/**
* ����glcrate��Setter����.�����������ű��һ���
* ��������:2018-9-6
* @param newGlcrate nc.vo.pub.lang.UFDouble
*/
public void setGlcrate ( String glcrate) {
this.setAttributeValue( InvestIncomeVO.GLCRATE,glcrate);
} 
 
/**
* ���� glcmoeny��Getter����.�����������ű��ҽ��
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getGlcmoeny() {
	return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.GLCMOENY);
} 

/**
* ����glcmoeny��Setter����.�����������ű��ҽ��
* ��������:2018-9-6
* @param newGlcmoeny nc.vo.pub.lang.UFDouble
*/
public void setGlcmoeny ( String glcmoeny) {
this.setAttributeValue( InvestIncomeVO.GLCMOENY,glcmoeny);
} 
 
/**
* ���� gllcrate��Getter����.��������ȫ�ֱ��һ���
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getGllcrate() {
return (String) this.getAttributeValue( InvestIncomeVO.GLLCRATE);
} 

/**
* ����gllcrate��Setter����.��������ȫ�ֱ��һ���
* ��������:2018-9-6
* @param newGllcrate nc.vo.pub.lang.UFDouble
*/
public void setGllcrate ( String gllcrate) {
this.setAttributeValue( InvestIncomeVO.GLLCRATE,gllcrate);
} 
 
/**
* ���� gllmoeny��Getter����.��������ȫ�ֱ��Ƚ��
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getGllmoeny() {
return (UFDouble) this.getAttributeValue( InvestIncomeVO.GLLMOENY);
} 

/**
* ����gllmoeny��Setter����.��������ȫ�ֱ��Ƚ��
* ��������:2018-9-6
* @param newGllmoeny nc.vo.pub.lang.UFDouble
*/
public void setGllmoeny ( String gllmoeny) {
this.setAttributeValue( InvestIncomeVO.GLLMOENY,gllmoeny);
} 
 
/**
* ���� billmaker��Getter����.���������Ƶ���
*  ��������:2018-9-6
* @return nc.vo.sm.UserVO
*/
public String getBillmaker() {
return (String) this.getAttributeValue( InvestIncomeVO.BILLMAKER);
} 

/**
* ����billmaker��Setter����.���������Ƶ���
* ��������:2018-9-6
* @param newBillmaker nc.vo.sm.UserVO
*/
public void setBillmaker ( String billmaker) {
this.setAttributeValue( InvestIncomeVO.BILLMAKER,billmaker);
} 
 
/**
* ���� billmakedate��Getter����.���������Ƶ�����
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getBillmakedate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue( InvestIncomeVO.BILLMAKEDATE);
} 

/**
* ����billmakedate��Setter����.���������Ƶ�����
* ��������:2018-9-6
* @param newBillmakedate nc.vo.pub.lang.UFDate
*/
public void setBillmakedate ( UFDate billmakedate) {
this.setAttributeValue( InvestIncomeVO.BILLMAKEDATE,billmakedate);
} 
 
/**
* ���� billmaketime��Getter����.���������Ƶ�ʱ��
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getBillmaketime() {
return (nc.vo.pub.lang.UFDateTime) this.getAttributeValue( InvestIncomeVO.BILLMAKETIME);
} 

/**
* ����billmaketime��Setter����.���������Ƶ�ʱ��
* ��������:2018-9-6
* @param newBillmaketime nc.vo.pub.lang.UFDateTime
*/
public void setBillmaketime ( UFDateTime billmaketime) {
this.setAttributeValue( InvestIncomeVO.BILLMAKETIME,billmaketime);
} 
 
/**
* ���� approver��Getter����.��������������
*  ��������:2018-9-6
* @return nc.vo.sm.UserVO
*/
public String getApprover() {
return (String) this.getAttributeValue( InvestIncomeVO.APPROVER);
} 

/**
* ����approver��Setter����.��������������
* ��������:2018-9-6
* @param newApprover nc.vo.sm.UserVO
*/
public void setApprover ( String approver) {
this.setAttributeValue( InvestIncomeVO.APPROVER,approver);
} 
 
/**
* ���� approvedate��Getter����.����������������
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getApprovedate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue( InvestIncomeVO.APPROVEDATE);
} 

/**
* ����approvedate��Setter����.����������������
* ��������:2018-9-6
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
* ���� approvenote��Getter����.����������������
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getApprovenote() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.APPROVENOTE);
} 

/**
* ����approvenote��Setter����.����������������
* ��������:2018-9-6
* @param newApprovenote java.lang.String
*/
public void setApprovenote ( String approvenote) {
this.setAttributeValue( InvestIncomeVO.APPROVENOTE,approvenote);
} 
 
/**
* ���� creator��Getter����.��������������
*  ��������:2018-9-6
* @return nc.vo.sm.UserVO
*/
public String getCreator() {
return (String) this.getAttributeValue( InvestIncomeVO.CREATOR);
} 

/**
* ����creator��Setter����.��������������
* ��������:2018-9-6
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( String creator) {
this.setAttributeValue( InvestIncomeVO.CREATOR,creator);
} 
 
/**
* ���� creationtime��Getter����.������������ʱ��
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return (nc.vo.pub.lang.UFDateTime) this.getAttributeValue( InvestIncomeVO.CREATIONTIME);
} 

/**
* ����creationtime��Setter����.������������ʱ��
* ��������:2018-9-6
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.setAttributeValue( InvestIncomeVO.CREATIONTIME,creationtime);
} 
 
/**
* ���� modifier��Getter����.������������޸���
*  ��������:2018-9-6
* @return nc.vo.sm.UserVO
*/
public String getModifier() {
return (String) this.getAttributeValue( InvestIncomeVO.MODIFIER);
} 

/**
* ����modifier��Setter����.������������޸���
* ��������:2018-9-6
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( String modifier) {
this.setAttributeValue( InvestIncomeVO.MODIFIER,modifier);
} 
 
/**
* ���� modifiedtime��Getter����.������������޸�ʱ��
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return (nc.vo.pub.lang.UFDateTime) this.getAttributeValue( InvestIncomeVO.MODIFIEDTIME);
} 

/**
* ����modifiedtime��Setter����.������������޸�ʱ��
* ��������:2018-9-6
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.setAttributeValue( InvestIncomeVO.MODIFIEDTIME,modifiedtime);
} 
 
/**
* ���� pk_busitype��Getter����.��������ҵ������
*  ��������:2018-9-6
* @return nc.vo.pf.pub.BusitypeVO
*/
public String getPk_busitype() {
return (String) this.getAttributeValue( InvestIncomeVO.PK_BUSITYPE);
} 

/**
* ����pk_busitype��Setter����.��������ҵ������
* ��������:2018-9-6
* @param newPk_busitype nc.vo.pf.pub.BusitypeVO
*/
public void setPk_busitype ( String pk_busitype) {
this.setAttributeValue( InvestIncomeVO.PK_BUSITYPE,pk_busitype);
} 
 
/**
* ���� pk_billtypeid��Getter����.��������������������
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getPk_billtypeid() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.PK_BILLTYPEID);
} 

/**
* ����pk_billtypeid��Setter����.��������������������
* ��������:2018-9-6
* @param newPk_billtypeid java.lang.String
*/
public void setPk_billtypeid ( String pk_billtypeid) {
this.setAttributeValue( InvestIncomeVO.PK_BILLTYPEID,pk_billtypeid);
} 
 
/**
* ���� pk_billtypecode��Getter����.����������������
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getPk_billtypecode() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.PK_BILLTYPECODE);
} 

/**
* ����pk_billtypecode��Setter����.����������������
* ��������:2018-9-6
* @param newPk_billtypecode java.lang.String
*/
public void setPk_billtypecode ( String pk_billtypecode) {
this.setAttributeValue( InvestIncomeVO.PK_BILLTYPECODE,pk_billtypecode);
} 
 
/**
* ���� productcode��Getter����.����������Ʒ����
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getProductcode() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.PRODUCTCODE);
} 

/**
* ����productcode��Setter����.����������Ʒ����
* ��������:2018-9-6
* @param newProductcode java.lang.String
*/
public void setProductcode ( String productcode) {
this.setAttributeValue( InvestIncomeVO.PRODUCTCODE,productcode);
} 
 
/**
* ���� productname��Getter����.����������Ʒ����
*  ��������:2018-9-6
* @return java.lang.String
*/
public String getProductname() {
return (java.lang.String) this.getAttributeValue( InvestIncomeVO.PRODUCTNAME);
} 

/**
* ����productname��Setter����.����������Ʒ����
* ��������:2018-9-6
* @param newProductname java.lang.String
*/
public void setProductname ( String productname) {
this.setAttributeValue( InvestIncomeVO.PRODUCTNAME,productname);
} 
 
/**
* ���� issuebank��Getter����.����������������
*  ��������:2018-9-6
* @return nc.vo.bd.banktype.BankTypeVO
*/
public String getIssuebank() {
return (String) this.getAttributeValue( InvestIncomeVO.ISSUEBANK);
} 

/**
* ����issuebank��Setter����.����������������
* ��������:2018-9-6
* @param newIssuebank nc.vo.bd.banktype.BankTypeVO
*/
public void setIssuebank ( String issuebank) {
this.setAttributeValue( InvestIncomeVO.ISSUEBANK,issuebank);
} 
 
/**
* ���� billstatus��Getter����.������������״̬
*  ��������:2018-9-6
* @return nc.vo.ifm.BillStatusEnum
*/
public Integer getBillstatus() {
return (Integer) this.getAttributeValue( InvestIncomeVO.BILLSTATUS);
} 

/**
* ����billstatus��Setter����.������������״̬
* ��������:2018-9-6
* @param newBillstatus nc.vo.ifm.BillStatusEnum
*/
public void setBillstatus ( Integer billstatus) {
this.setAttributeValue( InvestIncomeVO.BILLSTATUS,billstatus);
} 
 
/**
* ���� gathering��Getter����.���������տ������˻�
*  ��������:2018-9-6
* @return nc.vo.bd.bankaccount.BankAccSubVO
*/
public String getGathering() {
return (String) this.getAttributeValue( InvestIncomeVO.GATHERING);
} 

/**
* ����gathering��Setter����.���������տ������˻�
* ��������:2018-9-6
* @param newGathering nc.vo.bd.bankaccount.BankAccSubVO
*/
public void setGathering ( String gathering) {
this.setAttributeValue( InvestIncomeVO.GATHERING,gathering);
} 
 
/**
* ���� investaccount��Getter����.������������˻�
*  ��������:2018-9-6
* @return nc.vo.bd.bankaccount.BankAccSubVO
*/
public String getInvestaccount() {
return (String) this.getAttributeValue( InvestIncomeVO.INVESTACCOUNT);
} 

/**
* ����investaccount��Setter����.������������˻�
* ��������:2018-9-6
* @param newInvestaccount nc.vo.bd.bankaccount.BankAccSubVO
*/
public void setInvestaccount ( String investaccount) {
this.setAttributeValue( InvestIncomeVO.INVESTACCOUNT,investaccount);
} 
 
/**
* ���� interestday��Getter����.����������Ϣ����
*  ��������:2018-9-6
* @return java.lang.Integer
*/
public Integer getInterestday() {
return (java.lang.Integer) this.getAttributeValue( InvestIncomeVO.INTERESTDAY);
} 

/**
* ����interestday��Setter����.����������Ϣ����
* ��������:2018-9-6
* @param newInterestday java.lang.Integer
*/
public void setInterestday ( Integer interestday) {
this.setAttributeValue( InvestIncomeVO.INTERESTDAY,interestday);
} 
 
/**
* ���� expectedrate��Getter����.��������Ԥ��������
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getExpectedrate() {
return (String) this.getAttributeValue( InvestIncomeVO.EXPECTEDRATE);
} 

/**
* ����expectedrate��Setter����.��������Ԥ��������
* ��������:2018-9-6
* @param newExpectedrate nc.vo.pub.lang.UFDouble
*/
public void setExpectedrate ( String expectedrate) {
this.setAttributeValue( InvestIncomeVO.EXPECTEDRATE,expectedrate);
} 
 
/**
* ���� expectedmoney��Getter����.��������Ԥ������
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getExpectedmoney() {
return (String) this.getAttributeValue( InvestIncomeVO.EXPECTEDMONEY);
} 

/**
* ����expectedmoney��Setter����.��������Ԥ������
* ��������:2018-9-6
* @param newExpectedmoney nc.vo.pub.lang.UFDouble
*/
public void setExpectedmoney ( String expectedmoney) {
this.setAttributeValue( InvestIncomeVO.EXPECTEDMONEY,expectedmoney);
} 
 
/**
* ���� enddate��Getter����.����������������
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getEnddate() {
return (nc.vo.pub.lang.UFDate) this.getAttributeValue( InvestIncomeVO.ENDDATE);
} 

/**
* ����enddate��Setter����.����������������
* ��������:2018-9-6
* @param newEnddate nc.vo.pub.lang.UFDate
*/
public void setEnddate ( UFDate enddate) {
this.setAttributeValue( InvestIncomeVO.ENDDATE,enddate);
} 
 
/**
* ���� actualmoeny��Getter����.��������ʵ������
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getActualmoeny() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( InvestIncomeVO.ACTUALMOENY);
} 

/**
* ����actualmoeny��Setter����.��������ʵ������
* ��������:2018-9-6
* @param newActualmoeny nc.vo.pub.lang.UFDouble
*/
public void setActualmoeny ( String actualmoeny) {
this.setAttributeValue( InvestIncomeVO.ACTUALMOENY,actualmoeny);
} 
 
/**
* ���� incomerate��Getter����.������������˰��
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getIncomerate() {
return (String) this.getAttributeValue( InvestIncomeVO.INCOMERATE);
} 

/**
* ����incomerate��Setter����.������������˰��
* ��������:2018-9-6
* @param newIncomerate nc.vo.pub.lang.UFDouble
*/
public void setIncomerate ( String incomerate) {
this.setAttributeValue( InvestIncomeVO.INCOMERATE,incomerate);
} 
 
/**
* ���� incomemoney��Getter����.������������˰��
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDouble
*/
public String getIncomemoney() {
return (String) this.getAttributeValue( InvestIncomeVO.INCOMEMONEY);
} 

/**
* ����incomemoney��Setter����.������������˰��
* ��������:2018-9-6
* @param newIncomemoney nc.vo.pub.lang.UFDouble
*/
public void setIncomemoney ( String incomemoney) {
this.setAttributeValue( InvestIncomeVO.INCOMEMONEY,incomemoney);
} 
/**
* �����ʽ�ƻ���Ŀ
*/
public String getIncomefundplanpro() {
	return (java.lang.String) this.getAttributeValue( InvestIncomeVO.INCOMEFUNDPLANPRO);
} 
public void setIncomefundplanpro ( String incomefundplanpro) {
	this.setAttributeValue( InvestIncomeVO.INCOMEFUNDPLANPRO,incomefundplanpro);
} 
/**
* ˰���ʽ�ƻ���Ŀ
*/
public String getTaxfundplanpro() {
	return (java.lang.String) this.getAttributeValue( InvestIncomeVO.TAXFUNDPLANPRO);
} 
public void setTaxfundplanpro ( String taxfundplanpro) {
	this.setAttributeValue( InvestIncomeVO.TAXFUNDPLANPRO,taxfundplanpro);
} 
/**
 * Ͷ��Ʒ��
 */
public String getInvestvariety() {
	return (java.lang.String) this.getAttributeValue( InvestIncomeVO.INVESTVARIETY);
} 
public void setInvestvariety ( String investvariety) {
	this.setAttributeValue( InvestIncomeVO.INVESTVARIETY,investvariety);
} 
/**
 * �������
 */
public String getk_redeem() {
	return (java.lang.String) this.getAttributeValue( InvestIncomeVO.PK_REDEEM);
} 
public void setk_redeem ( String pk_redeem) {
	this.setAttributeValue( InvestIncomeVO.PK_REDEEM,pk_redeem);
} 


public static final String TBBMESSAGE = "tbbmessage";
/**
 * ��ȡԤ����ʾ��Ϣ
 * 
 * @return Ԥ����ʾ��Ϣ
 */
public String getTbbmessage() {
	return (String) this.getAttributeValue("tbbmessage");
}

/**
 * ����Ԥ����ʾ��Ϣ
 * 
 * @param tbbmessage
 *            Ԥ����ʾ��Ϣ
 */
public void setTbbmessage(String tbbmessage) {
	this.setAttributeValue("tbbmessage", tbbmessage);
}
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2018-9-6
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2018-9-6
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
    