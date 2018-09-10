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
 
public class ProtocolDetailVO extends SuperVO {
	
/**
*ԭ�Ҷ��
*/
public static final String MOENY="moeny";
;
/**
*�к�
*/
public static final String ROWNO="rowno";
;
/**
*�Զ�����9
*/
public static final String VDEF9="vdef9";
;
/**
*�Զ�����30
*/
public static final String VDEF30="vdef30";
;
/**
*�ϲ㵥������
*/
public String pk_guarantee;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� moeny��Getter����.��������ԭ�Ҷ��
*  ��������:2018-9-3
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getMoeny() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( ProtocolDetailVO.MOENY);
} 

/**
* ����moeny��Setter����.��������ԭ�Ҷ��
* ��������:2018-9-3
* @param newMoeny nc.vo.pub.lang.UFDouble
*/
public void setMoeny ( String moeny) {
this.setAttributeValue( ProtocolDetailVO.MOENY,moeny);
} 
 
/**
* ���� rowno��Getter����.���������к�
*  ��������:2018-9-3
* @return java.lang.String
*/
public String getRowno() {
return (java.lang.String) this.getAttributeValue( ProtocolDetailVO.ROWNO);
} 

/**
* ����rowno��Setter����.���������к�
* ��������:2018-9-3
* @param newRowno java.lang.String
*/
public void setRowno ( String rowno) {
this.setAttributeValue( ProtocolDetailVO.ROWNO,rowno);
} 
 
/**
* ���� vdef9��Getter����.���������Զ�����9
*  ��������:2018-9-3
* @return java.lang.String
*/
public String getVdef9() {
return (java.lang.String) this.getAttributeValue( ProtocolDetailVO.VDEF9);
} 

/**
* ����vdef9��Setter����.���������Զ�����9
* ��������:2018-9-3
* @param newVdef9 java.lang.String
*/
public void setVdef9 ( String vdef9) {
this.setAttributeValue( ProtocolDetailVO.VDEF9,vdef9);
} 
 
/**
* ���� vdef30��Getter����.���������Զ�����30
*  ��������:2018-9-3
* @return java.lang.String
*/
public String getVdef30() {
return (java.lang.String) this.getAttributeValue( ProtocolDetailVO.VDEF30);
} 

/**
* ����vdef30��Setter����.���������Զ�����30
* ��������:2018-9-3
* @param newVdef30 java.lang.String
*/
public void setVdef30 ( String vdef30) {
this.setAttributeValue( ProtocolDetailVO.VDEF30,vdef30);
} 
 
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2018-9-3
* @return String
*/
public String getPk_guarantee(){
return this.pk_guarantee;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2018-9-3
* @param newPk_guarantee String
*/
public void setPk_guarantee(String pk_guarantee){
this.pk_guarantee=pk_guarantee;
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
    return VOMetaFactory.getInstance().getVOMeta("ccc.protocoldetail");
    }
   }
    