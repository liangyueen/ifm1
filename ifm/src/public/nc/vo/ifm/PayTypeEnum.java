package nc.vo.ifm;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <b>�˴���Ҫ������ö�ٵĹ��� </b>
 * <p>
 *   �˴���Ӹ�ö�ٵ�������Ϣ
 * </p>
 *  ��������:2018-9-2
 * @author YONYOU NC
 * @version NCPrj ??
 */
public class PayTypeEnum extends MDEnum{
	public PayTypeEnum(IEnumValue enumvalue){
		super(enumvalue);
	}

	
	
	public static final PayTypeEnum �� = MDEnum.valueOf(PayTypeEnum.class, Integer.valueOf(1));
	
	
	public static final PayTypeEnum �� = MDEnum.valueOf(PayTypeEnum.class, Integer.valueOf(2));
	
	
	public static final PayTypeEnum �� = MDEnum.valueOf(PayTypeEnum.class, Integer.valueOf(3));
	
	
	public static final PayTypeEnum ���� = MDEnum.valueOf(PayTypeEnum.class, Integer.valueOf(4));
	
	
	public static final PayTypeEnum �� = MDEnum.valueOf(PayTypeEnum.class, Integer.valueOf(5));
	

}