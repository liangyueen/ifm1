package nc.vo.ifm;

import nc.md.model.impl.MDEnum;
import nc.md.model.IEnumValue;
	
public class RedeemStatusEnum extends MDEnum{
	public RedeemStatusEnum(IEnumValue enumvalue){
		super(enumvalue);
	}
	public final static RedeemStatusEnum NOSUB = MDEnum.valueOf(RedeemStatusEnum.class,  Integer.valueOf(1));//���ύ
	public final static RedeemStatusEnum NOAUDIT = MDEnum.valueOf(RedeemStatusEnum.class,  Integer.valueOf(2));//������
	public final static RedeemStatusEnum ALLREDEEM = MDEnum.valueOf(RedeemStatusEnum.class,  Integer.valueOf(3));//ȫ�����
	public final static RedeemStatusEnum PARTREDEEM = MDEnum.valueOf(RedeemStatusEnum.class,  Integer.valueOf(4));//�������
	//public final static RedeemStatusEnum FINISHED = MDEnum.valueOf(RedeemStatusEnum.class, 5);//�ѽ���
	//public final static RedeemStatusEnum FROZEN = MDEnum.valueOf(RedeemStatusEnum.class, 6);//�Ѷ���
	
} 
