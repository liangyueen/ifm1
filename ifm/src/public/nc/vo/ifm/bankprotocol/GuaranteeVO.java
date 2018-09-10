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
 
public class GuaranteeVO extends SuperVO {
	
/**
*担保合约
*/
public static final String GUARANTEE="guarantee";
;
/**
*占用担保额度
*/
public static final String USEGUAMONEY="useguamoney";
;
/**
*行号
*/
public static final String ROWNO="rowno";
;
/**
*自定义项9
*/
public static final String VDED9="vded9";
;
/**
*上层单据主键
*/
public String pk_protocol;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 guarantee的Getter方法.属性名：担保合约
*  创建日期:2018-9-3
* @return java.lang.String
*/
public String getGuarantee() {
return (java.lang.String) this.getAttributeValue( GuaranteeVO.GUARANTEE);
} 

/**
* 属性guarantee的Setter方法.属性名：担保合约
* 创建日期:2018-9-3
* @param newGuarantee java.lang.String
*/
public void setGuarantee ( String guarantee) {
this.setAttributeValue( GuaranteeVO.GUARANTEE,guarantee);
} 
 
/**
* 属性 useguamoney的Getter方法.属性名：占用担保额度
*  创建日期:2018-9-3
* @return nc.vo.pub.lang.UFDouble
*/
public UFDouble getUseguamoney() {
return (nc.vo.pub.lang.UFDouble) this.getAttributeValue( GuaranteeVO.USEGUAMONEY);
} 

/**
* 属性useguamoney的Setter方法.属性名：占用担保额度
* 创建日期:2018-9-3
* @param newUseguamoney nc.vo.pub.lang.UFDouble
*/
public void setUseguamoney ( String useguamoney) {
this.setAttributeValue( GuaranteeVO.USEGUAMONEY,useguamoney);
} 
 
/**
* 属性 rowno的Getter方法.属性名：行号
*  创建日期:2018-9-3
* @return java.lang.String
*/
public String getRowno() {
return (java.lang.String) this.getAttributeValue( GuaranteeVO.ROWNO);
} 

/**
* 属性rowno的Setter方法.属性名：行号
* 创建日期:2018-9-3
* @param newRowno java.lang.String
*/
public void setRowno ( String rowno) {
this.setAttributeValue( GuaranteeVO.ROWNO,rowno);
} 
 
/**
* 属性 vded9的Getter方法.属性名：自定义项9
*  创建日期:2018-9-3
* @return java.lang.String
*/
public String getVded9() {
return (java.lang.String) this.getAttributeValue( GuaranteeVO.VDED9);
} 

/**
* 属性vded9的Setter方法.属性名：自定义项9
* 创建日期:2018-9-3
* @param newVded9 java.lang.String
*/
public void setVded9 ( String vded9) {
this.setAttributeValue( GuaranteeVO.VDED9,vded9);
} 
 
/**
* 属性 生成上层主键的Getter方法.属性名：上层主键
*  创建日期:2018-9-3
* @return String
*/
public String getPk_protocol(){
return this.pk_protocol;
}
/**
* 属性生成上层主键的Setter方法.属性名：上层主键
* 创建日期:2018-9-3
* @param newPk_protocol String
*/
public void setPk_protocol(String pk_protocol){
this.pk_protocol=pk_protocol;
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
    return VOMetaFactory.getInstance().getVOMeta("ccc.guarantee");
    }
   }
    