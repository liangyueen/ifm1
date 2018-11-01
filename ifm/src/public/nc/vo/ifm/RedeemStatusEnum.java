package nc.vo.ifm;

import nc.md.model.impl.MDEnum;
import nc.md.model.IEnumValue;
	
public class RedeemStatusEnum extends MDEnum{
	public RedeemStatusEnum(IEnumValue enumvalue){
		super(enumvalue);
	}
	public final static RedeemStatusEnum NOSUB = MDEnum.valueOf(RedeemStatusEnum.class,  Integer.valueOf(1));//待提交
	public final static RedeemStatusEnum NOAUDIT = MDEnum.valueOf(RedeemStatusEnum.class,  Integer.valueOf(2));//待审批
	public final static RedeemStatusEnum ALLREDEEM = MDEnum.valueOf(RedeemStatusEnum.class,  Integer.valueOf(3));//全部赎回
	public final static RedeemStatusEnum PARTREDEEM = MDEnum.valueOf(RedeemStatusEnum.class,  Integer.valueOf(4));//部分赎回
	//public final static RedeemStatusEnum FINISHED = MDEnum.valueOf(RedeemStatusEnum.class, 5);//已结束
	//public final static RedeemStatusEnum FROZEN = MDEnum.valueOf(RedeemStatusEnum.class, 6);//已冻结
	
} 
