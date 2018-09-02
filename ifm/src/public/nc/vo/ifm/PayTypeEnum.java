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
public class PayTypeEnum extends MDEnum{
	public PayTypeEnum(IEnumValue enumvalue){
		super(enumvalue);
	}

	
	
	public static final PayTypeEnum 月 = MDEnum.valueOf(PayTypeEnum.class, Integer.valueOf(1));
	
	
	public static final PayTypeEnum 年 = MDEnum.valueOf(PayTypeEnum.class, Integer.valueOf(2));
	
	
	public static final PayTypeEnum 季 = MDEnum.valueOf(PayTypeEnum.class, Integer.valueOf(3));
	
	
	public static final PayTypeEnum 半年 = MDEnum.valueOf(PayTypeEnum.class, Integer.valueOf(4));
	
	
	public static final PayTypeEnum 日 = MDEnum.valueOf(PayTypeEnum.class, Integer.valueOf(5));
	

}