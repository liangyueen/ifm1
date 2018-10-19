package nc.vo.ifm;

import nc.md.model.impl.MDEnum;
import nc.md.model.IEnumValue;
	
/**
 * <b> 在此处简要描述此类的功能 </b>
 * <p>
 *     在此处添加此类的描述信息
 * </p>
 * 创建日期:
 * @author 
 * @version NCPrj ??
 */
public class ApplyStatusEnum extends MDEnum{
	public ApplyStatusEnum(IEnumValue enumvalue){
		super(enumvalue);
	}
	public final static ApplyStatusEnum NOCOMMIT = MDEnum.valueOf(ApplyStatusEnum.class, "NOCOMMIT");//待提交
	public final static ApplyStatusEnum NOAUDIT = MDEnum.valueOf(ApplyStatusEnum.class, "NOAUDIT");//待审批
	public final static ApplyStatusEnum FINISHED = MDEnum.valueOf(ApplyStatusEnum.class, "FINISHED");//已完成
	
//	public final static ProtocolStatusEnum OVERED = MDEnum.valueOf(ProtocolStatusEnum.class, "OVERED");//已终止	
	

} 
