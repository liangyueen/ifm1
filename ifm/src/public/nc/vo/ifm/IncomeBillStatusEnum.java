package nc.vo.ifm;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <b>�˴���Ҫ������ö�ٵĹ��� </b>
 * <p>
 *   �˴���Ӹ�ö�ٵ�������Ϣ
 * </p>
 *  ��������:2018-9-18
 * @author YONYOU NC
 * @version NCPrj ??
 */
public class IncomeBillStatusEnum extends MDEnum{
	public IncomeBillStatusEnum(IEnumValue enumvalue){
		super(enumvalue);
	}

	
	
	public static final IncomeBillStatusEnum ���ύ = MDEnum.valueOf(IncomeBillStatusEnum.class, String.valueOf("1"));
	
	
	public static final IncomeBillStatusEnum ������ = MDEnum.valueOf(IncomeBillStatusEnum.class, String.valueOf("2"));
	
	
	public static final IncomeBillStatusEnum ����� = MDEnum.valueOf(IncomeBillStatusEnum.class, String.valueOf("3"));
	

}