package nc.bs.pub.rule;

import nc.bs.ifm.pub.util.IFMOrgVUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

public class IFMBillOrgVRule implements IRule{

	public void process(Object[] vos) {
		// TODO Auto-generated method stub
		this.setOrgVs((IBill[]) vos);
	}
	private void setOrgVs(IBill[] bills){
		if(bills==null||bills.length==0){
			return;
		}
		for(IBill bill:bills){
			this.setOrgV(bill);
		}
	}
	private void setOrgV(IBill bill){
		if(bill==null){
			return;
		}
		SuperVO parentVO=(SuperVO) bill.getParent();
		UFDate busiDate=null;
		if((UFDate) parentVO.getAttributeValue("begindate")!=null){
			busiDate=(UFDate) parentVO.getAttributeValue("begindate");
		}else{
			busiDate=(UFDate) parentVO.getAttributeValue("billmakedate");
		}
		try {
			IFMOrgVUtil.setOrgv(parentVO, "pk_org", "pk_org_v", busiDate);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException("设置组织多版本错误");
		}
	}
	
}
