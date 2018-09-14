package nc.bs.pub.rule;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pub.rule.FillInsertDataRule;
import nc.pubitf.org.IOrgUnitPubService;
import nc.vo.cc.grpprotocol.GroupQuotaOrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.util.AuditInfoUtils;

public class IFMFillInsertDataRule extends FillInsertDataRule{
	
	@Override
	public void process(Object[] bills) {
		// 设置审计信息
		AuditInfoUtils.setAddAuditInfo((IBill[]) bills);
		// 把表头组织同步到表体
		this.fillItemOrgs((IBill[]) bills);
	}

	private void fillItemOrgs(IBill[] bills) {
		for (IBill bill : bills) {
			ISuperVO head = bill.getParent();
			head.setAttributeValue("billmaker", AppContext.getInstance()
					.getPkUser());

			String pk_group = (String) head.getAttributeValue("pk_group");
			String pk_org = (String) head.getAttributeValue("pk_org");
			String pk_org_v = (String) head.getAttributeValue("pk_org_v");
			if (pk_org_v == null) {
				// 组织的最新版本
				Map<String, String> map;
				try {

					UFDate busiDate = AppContext.getInstance().getBusiDate();
					map = NCLocator
							.getInstance()
							.lookup(IOrgUnitPubService.class)
							.getNewVIDSByOrgIDSAndDate(new String[] { pk_org },
									busiDate);
					pk_org_v = map.get(pk_org);
					head.setAttributeValue("pk_org_v", pk_org_v);
				} catch (BusinessException e) {
					ExceptionUtils.wrappException(e);
				}
			}

			for (IVOMeta meta : bill.getMetaData().getChildren()) {
				for (int i = 0; bill.getChildren(meta) != null
						&& i < bill.getChildren(meta).length; i++) {
					ISuperVO childvo = bill.getChildren(meta)[i];
					childvo.setAttributeValue("pk_group", pk_group);
					if (!(childvo instanceof GroupQuotaOrgVO)) {
						childvo.setAttributeValue("pk_org", pk_org);
						childvo.setAttributeValue("pk_org_v", pk_org_v);
					}

				}
			}
		}
	}

}
