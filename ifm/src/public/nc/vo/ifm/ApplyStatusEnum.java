package nc.vo.ifm;

import nc.md.model.impl.MDEnum;
import nc.md.model.IEnumValue;
	
/**
 * <b> �ڴ˴���Ҫ��������Ĺ��� </b>
 * <p>
 *     �ڴ˴���Ӵ����������Ϣ
 * </p>
 * ��������:
 * @author 
 * @version NCPrj ??
 */
public class ApplyStatusEnum extends MDEnum{
	public ApplyStatusEnum(IEnumValue enumvalue){
		super(enumvalue);
	}
	public final static ApplyStatusEnum NOCOMMIT = MDEnum.valueOf(ApplyStatusEnum.class, "NOCOMMIT");//���ύ
	public final static ApplyStatusEnum NOAUDIT = MDEnum.valueOf(ApplyStatusEnum.class, "NOAUDIT");//������
	public final static ApplyStatusEnum FINISHED = MDEnum.valueOf(ApplyStatusEnum.class, "FINISHED");//�����
	
//	public final static ProtocolStatusEnum OVERED = MDEnum.valueOf(ProtocolStatusEnum.class, "OVERED");//����ֹ	
	

} 
