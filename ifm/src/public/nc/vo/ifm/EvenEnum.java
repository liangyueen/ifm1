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
public class EvenEnum extends MDEnum{
	public EvenEnum(IEnumValue enumvalue){
		super(enumvalue);
	}

	
	
	public static final EvenEnum ��֤������ = MDEnum.valueOf(EvenEnum.class, Integer.valueOf(0));
	
	
	public static final EvenEnum �������������� = MDEnum.valueOf(EvenEnum.class, Integer.valueOf(1));
	
	
	public static final EvenEnum �Ǳ������������� = MDEnum.valueOf(EvenEnum.class, Integer.valueOf(2));
	

}