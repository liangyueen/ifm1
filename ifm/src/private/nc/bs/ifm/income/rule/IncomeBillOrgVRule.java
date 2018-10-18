package nc.bs.ifm.income.rule;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.org.IOrgUnitPubService;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.tmpub.util.StringUtil;

public class IncomeBillOrgVRule implements IRule<AggInvestIncomeVO>{

	public void process(AggInvestIncomeVO[] vos) {
		this.setOrgVs((IBill[])vos);
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
			String pkOrgField = "pk_org";
			String pkOrgVField = "pk_org_v";
			String pk_org = (String) parentVO.getAttributeValue(pkOrgField);

			if(StringUtil.isNull(pk_org)){
				return;
			}

			if(busiDate == null){
				busiDate = new UFDate();
			}

			IOrgUnitPubService orgUnitSrv = NCLocator.getInstance().lookup(IOrgUnitPubService.class);
			Map orgVersionMap = orgUnitSrv.getNewVIDSByOrgIDSAndDate(new String[]{pk_org}, busiDate);

			String pk_org_v = (String)orgVersionMap.get(pk_org);

			if(pk_org_v == null){
				throw new BusinessException("查询组织版本错误，无可用版本");
			}
			parentVO.setAttributeValue(pkOrgVField, pk_org_v);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException("设置组织多版本错误");
		}
	}
}