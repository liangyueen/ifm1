package nc.vo.ifm;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <b>此处简要描述此枚举的功能 </b>
 * <p>
 *   此处添加该枚举的描述信息
 * </p>
 *  创建日期:2018-9-2
 * @author YONYOU NC
 * @version NCPrj ??
 */
public class EvenEnum extends MDEnum{
	public EvenEnum(IEnumValue enumvalue){
		super(enumvalue);
	}

	
	
	public static final EvenEnum 保证收益类 = MDEnum.valueOf(EvenEnum.class, Integer.valueOf(0));
	
	
	public static final EvenEnum 保本浮动收益类 = MDEnum.valueOf(EvenEnum.class, Integer.valueOf(1));
	
	
	public static final EvenEnum 非保本浮动收益类 = MDEnum.valueOf(EvenEnum.class, Integer.valueOf(2));
	

}