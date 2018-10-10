package nc.vo.ifm;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <b>此处简要描述此枚举的功能 </b>
 * <p>
 *   此处添加该枚举的描述信息
 * </p>
 *  创建日期:2018-9-18
 * @author YONYOU NC
 * @version NCPrj ??
 */
public class IncomeBillStatusEnum extends MDEnum{
	public IncomeBillStatusEnum(IEnumValue enumvalue){
		super(enumvalue);
	}

	
	
	public static final IncomeBillStatusEnum NOSUB = MDEnum.valueOf(IncomeBillStatusEnum.class, String.valueOf("1"));
	
	
	public static final IncomeBillStatusEnum NOAUDIT = MDEnum.valueOf(IncomeBillStatusEnum.class, String.valueOf("2"));
	
	
	public static final IncomeBillStatusEnum FINISHED = MDEnum.valueOf(IncomeBillStatusEnum.class, String.valueOf("3"));
	

}