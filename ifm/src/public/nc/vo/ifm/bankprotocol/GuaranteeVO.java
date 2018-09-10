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
 *   �˴�����۵�������Ϣ
 * </p>
 *  ��������:2018-9-3
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class GuaranteeVO extends SuperVO {
	
/**
*������Լ
*/
public static final String GUARANTEE="guarantee";
;
/**
*ռ�õ������
*/
public static final String USEGUAMONEY="useguamoney";
;
/**
*�к�
*/
public static final String ROWNO="rowno";
;
/**
*�Զ�����9
*/
public static final String VDED9="vded9";
;
/**
*�ϲ㵥������
*/
public String pk_protocol;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� guarantee��Getter����.��������������Լ
*  ��������:2018-9-3
* @return java.lang.String
*/
public String getGuarantee() {
return (java.lang.String) this.getAttributeValue( GuaranteeVO.GUARANTEE);
} 

/**
* ����guarantee��Setter����.��������������Լ
* ��������:2018-9-3
* @param newGuarantee java.lang.String
*/
public void setGuarantee ( String guarantee) {
this.setAttributeValue( GuaranteeVO.GUARANTEE,guarantee);
} 
 
/**
* ���� useguamoney��Getter����.��������ռ�õ������
*  ��������:2018-9-3
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getUseguamoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( GuaranteeVO.USEGUAMONEY);
} 

/**
* ����useguamoney��Setter����.��������ռ�õ������
* ��������:2018-9-3
* @param newUseguamoney nc.vo.pub.lang.UFDouble
*/
public void setUseguamoney ( String useguamoney) {
this.setAttributeValue( GuaranteeVO.USEGUAMONEY,useguamoney);
} 
 
/**
* ���� rowno��Getter����.���������к�
*  ��������:2018-9-3
* @return java.lang.String
*/
public String getRowno() {
return (java.lang.String) this.getAttributeValue( GuaranteeVO.ROWNO);
} 

/**
* ����rowno��Setter����.���������к�
* ��������:2018-9-3
* @param newRowno java.lang.String
*/
public void setRowno ( String rowno) {
this.setAttributeValue( GuaranteeVO.ROWNO,rowno);
} 
 
/**
* ���� vded9��Getter����.���������Զ�����9
*  ��������:2018-9-3
* @return java.lang.String
*/
public String getVded9() {
return (java.lang.String) this.getAttributeValue( GuaranteeVO.VDED9);
} 

/**
* ����vded9��Setter����.���������Զ�����9
* ��������:2018-9-3
* @param newVded9 java.lang.String
*/
public void setVded9 ( String vded9) {
this.setAttributeValue( GuaranteeVO.VDED9,vded9);
} 
 
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2018-9-3
* @return String
*/
public String getPk_protocol(){
return this.pk_protocol;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2018-9-3
* @param newPk_protocol String
*/
public void setPk_protocol(String pk_protocol){
this.pk_protocol=pk_protocol;
} 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2018-9-3
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2018-9-3
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("ccc.guarantee");
    }
   }
    