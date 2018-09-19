package nc.vo.ifm;

import nc.md.model.impl.MDEnum;
import nc.md.model.IEnumValue;
	
public class RedeemStatusEnum extends MDEnum{
	public RedeemStatusEnum(IEnumValue enumvalue){
		super(enumvalue);
	}
	public final static RedeemStatusEnum 待提交 = MDEnum.valueOf(RedeemStatusEnum.class,  Integer.valueOf(0));//待提交
	public final static RedeemStatusEnum 待审核 = MDEnum.valueOf(RedeemStatusEnum.class,  Integer.valueOf(1));//待审批
	public final static RedeemStatusEnum 全部赎回 = MDEnum.valueOf(RedeemStatusEnum.class,  Integer.valueOf(2));//全部赎回
	public final static RedeemStatusEnum 部分赎回 = MDEnum.valueOf(RedeemStatusEnum.class,  Integer.valueOf(3));//部分赎回
	//public final static RedeemStatusEnum FINISHED = MDEnum.valueOf(RedeemStatusEnum.class, 5);//已结束
	//public final static RedeemStatusEnum FROZEN = MDEnum.valueOf(RedeemStatusEnum.class, 6);//已冻结
	
} 
