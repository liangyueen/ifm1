package nc.vo.ifm.bankprotocol;

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
 *  创建日期:2018-9-3
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class ProtocolDetailVO extends SuperVO {
	
/**
*原币额度
*/
public static final String MOENY="moeny";
;
/**
*行号
*/
public static final String ROWNO="rowno";
;
/**
*自定义项9
*/
public static final String VDEF9="vdef9";
;
/**
*自定义项30
*/
public static final String VDEF30="vdef30";
;
/**
*上层单据主键
*/
public String pk_guarantee;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 moeny的Getter方法.属性名：原币额度
*  创建日期:2018-9-3
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getMoeny() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( ProtocolDetailVO.MOENY);
} 

/**
* 属性moeny的Setter方法.属性名：原币额度
* 创建日期:2018-9-3
* @param newMoeny nc.vo.pub.lang.UFDouble
*/
public void setMoeny ( String moeny) {
this.setAttributeValue( ProtocolDetailVO.MOENY,moeny);
} 
 
/**
* 属性 rowno的Getter方法.属性名：行号
*  创建日期:2018-9-3
* @return java.lang.String
*/
public String getRowno() {
return (java.lang.String) this.getAttributeValue( ProtocolDetailVO.ROWNO);
} 

/**
* 属性rowno的Setter方法.属性名：行号
* 创建日期:2018-9-3
* @param newRowno java.lang.String
*/
public void setRowno ( String rowno) {
this.setAttributeValue( ProtocolDetailVO.ROWNO,rowno);
} 
 
/**
* 属性 vdef9的Getter方法.属性名：自定义项9
*  创建日期:2018-9-3
* @return java.lang.String
*/
public String getVdef9() {
return (java.lang.String) this.getAttributeValue( ProtocolDetailVO.VDEF9);
} 

/**
* 属性vdef9的Setter方法.属性名：自定义项9
* 创建日期:2018-9-3
* @param newVdef9 java.lang.String
*/
public void setVdef9 ( String vdef9) {
this.setAttributeValue( ProtocolDetailVO.VDEF9,vdef9);
} 
 
/**
* 属性 vdef30的Getter方法.属性名：自定义项30
*  创建日期:2018-9-3
* @return java.lang.String
*/
public String getVdef30() {
return (java.lang.String) this.getAttributeValue( ProtocolDetailVO.VDEF30);
} 

/**
* 属性vdef30的Setter方法.属性名：自定义项30
* 创建日期:2018-9-3
* @param newVdef30 java.lang.String
*/
public void setVdef30 ( String vdef30) {
this.setAttributeValue( ProtocolDetailVO.VDEF30,vdef30);
} 
 
/**
* 属性 生成上层主键的Getter方法.属性名：上层主键
*  创建日期:2018-9-3
* @return String
*/
public String getPk_guarantee(){
return this.pk_guarantee;
}
/**
* 属性生成上层主键的Setter方法.属性名：上层主键
* 创建日期:2018-9-3
* @param newPk_guarantee String
*/
public void setPk_guarantee(String pk_guarantee){
this.pk_guarantee=pk_guarantee;
} 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2018-9-3
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2018-9-3
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
    