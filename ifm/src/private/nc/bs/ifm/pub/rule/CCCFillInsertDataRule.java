package nc.bs.ifm.pub.rule;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pub.rule.FillInsertDataRule;
import nc.pubitf.org.IOrgUnitPubService;
import nc.vo.ifm.bankprotocol.ProtocolDetailVO;
import nc.vo.ifm.bankprotocol.ProtocolVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.util.AuditInfoUtils;

public class CCCFillInsertDataRule extends FillInsertDataRule {

	@Override
	public void process(Object[] bills) {
		// 授信额度设置默认值
		this.setProtocolMoney((IBill[]) bills);
		// 设置审计信息
		AuditInfoUtils.setAddAuditInfo((IBill[]) bills);
		// 把表头组织同步到表体
		this.fillItemOrgs((IBill[]) bills);
	}

	/**
	 * 授信额度设置默认值
	 * 
	 * @param bills
	 */
	private void setProtocolMoney(IBill[] bills) {
		for (IBill bill : bills) {
			ProtocolVO head = (ProtocolVO) bill.getParent();
			// 组织本币额度
			if (head.getOlccdtlnamt() == null) {
				head.setOlccdtlnamt(UFDouble.ZERO_DBL);
			}
			// 集团本币额度
			if (head.getGlccdtlnamt() == null) {
				head.setGlccdtlnamt(UFDouble.ZERO_DBL);
			}
			// 全局本币额度
			if (head.getGllccdtlnamt() == null) {
				head.setGllccdtlnamt(UFDouble.ZERO_DBL);
			}
			// 授信额度
			UFDouble Protocolcdtlnamt = head.getCdtlnamt();

			// 可用额度=授信额度
			head.setAvailcdtlnamt(Protocolcdtlnamt);
			// 可用组织本币授信额度=组织本币额度
			head.setOlcavailcdtlnamt(head.getOlccdtlnamt());
			// 可用集团本币授信额度=集团本币额度
			head.setGlcavailcdtlnamt(head.getGlccdtlnamt());
			// 可用全局本币授信额度=全局本币额度
			head.setGllcavailcdtlnamt(head.getGllccdtlnamt());

			// 本期已用授信额度 =0
			head.setCurusdcdtlnamt(UFDouble.ZERO_DBL);
			// 本期已用组织本币授信额度=0
			head.setOlccurusdcdtlnamt(UFDouble.ZERO_DBL);
			// 本期已用集团本币授信额度 =0
			head.setGlccurusdcdtlnamt(UFDouble.ZERO_DBL);
			// 本期已用全局本币授信额度=0
			head.setGllccurusdcdtlnamt(UFDouble.ZERO_DBL);

			// 申请已用授信额度=0
			head.setAppusdcdtlnamt(UFDouble.ZERO_DBL);
			// 申请已用组织本币授信额度=0
			head.setOlcappusdcdtlnamt(UFDouble.ZERO_DBL);
			// 申请已用集团本币授信额度=0
			head.setGlcappusdcdtlnamt(UFDouble.ZERO_DBL);
			// 申请已用全局本币授信额度=0
			head.setGllcappusdcdtlnamt(UFDouble.ZERO_DBL);

			// 期初已用授信额度=0
			head.setOpnusdcdtlnamt(UFDouble.ZERO_DBL);
			// 期初已用组织本币授信额度=0
			head.setOlcopnusdcdtlnamt(UFDouble.ZERO_DBL);
			// 期初已用集团本币授信额度 =0
			head.setGlcopnusdcdtlnamt(UFDouble.ZERO_DBL);
			// 期初已用全局本币授信额度=0
			head.setGllcopnusdcdtlnamt(UFDouble.ZERO_DBL);
		}
	}

	/**
	 * 把表头组织同步到表体
	 * 
	 * @param bills
	 */
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
            //给表体设置默认值
			for (IVOMeta meta : bill.getMetaData().getChildren()) {
				for (int i = 0; bill.getChildren(meta) != null
						&& i < bill.getChildren(meta).length; i++) {
					ISuperVO childvo = bill.getChildren(meta)[i];
					childvo.setAttributeValue("pk_group", pk_group);
					childvo.setAttributeValue("pk_org", pk_org);
					childvo.setAttributeValue("pk_org_v", pk_org_v);
					//表体金额设置默认值
					if (childvo instanceof ProtocolDetailVO) {
						//组织本币额度
						if (((ProtocolDetailVO) childvo).getOlccdtlnamt() == null) {
							((ProtocolDetailVO) childvo).setOlccdtlnamt(UFDouble.ZERO_DBL);
						}
						//集团本币额度
						if (((ProtocolDetailVO) childvo).getGlccdtlnamt() == null) {
							((ProtocolDetailVO) childvo).setGlccdtlnamt(UFDouble.ZERO_DBL);
						}
						//全局本币额度
						if (((ProtocolDetailVO) childvo).getGllccdtlnamt() == null) {
							((ProtocolDetailVO) childvo).setGllccdtlnamt(UFDouble.ZERO_DBL);
						}
						//可用授信额度=授信额度
						((ProtocolDetailVO) childvo).setAvailcdtlnamt(((ProtocolDetailVO) childvo).getCdtlnamt());
						//可用组织本币授信额度=组织本币授信额度
						((ProtocolDetailVO) childvo).setOlcavailcdtlnamt(((ProtocolDetailVO) childvo)
								.getOlccdtlnamt());
						//可用集团本币授信额度=集团本币授信额度
						((ProtocolDetailVO) childvo).setGlcavailcdtlnamt(((ProtocolDetailVO) childvo)
								.getGlccdtlnamt());
						//可用全局本币授信额度=全局本币授信额度
						((ProtocolDetailVO) childvo).setGllcavailcdtlnamt(((ProtocolDetailVO) childvo)
								.getGllccdtlnamt());

						//本期已用授信额度=0
						((ProtocolDetailVO) childvo).setCurusdcdtlnamt(UFDouble.ZERO_DBL);
						//本期已用组织本币授信额度=0
						((ProtocolDetailVO) childvo).setOlccurusdcdtlnamt(UFDouble.ZERO_DBL);
						//本期已用集团本币授信额度=0
						((ProtocolDetailVO) childvo).setGlccurusdcdtlnamt(UFDouble.ZERO_DBL);
						//本期已用全局本币授信额度=0
						((ProtocolDetailVO) childvo).setGllccurusdcdtlnamt(UFDouble.ZERO_DBL);

						//期初已用授信额度=0
						((ProtocolDetailVO) childvo).setOpnusdcdtlnamt(UFDouble.ZERO_DBL);
						//期初已用组织本币授信额度=0
						((ProtocolDetailVO) childvo).setOlcopnusdcdtlnamt(UFDouble.ZERO_DBL);
						//期初已用组集团币授信额度=0
						((ProtocolDetailVO) childvo).setGlcopnusdcdtlnamt(UFDouble.ZERO_DBL);
						//期初已用组全局币授信额度=0
						((ProtocolDetailVO) childvo).setGllcopnusdcdtlnamt(UFDouble.ZERO_DBL);

						//申请已用授信额度=0
						((ProtocolDetailVO) childvo).setAppusdcdtlnamt(UFDouble.ZERO_DBL);
						//申请已用组织本币授信额度=0
						((ProtocolDetailVO) childvo).setOlcappusdcdtlnamt(UFDouble.ZERO_DBL);
						//申请已用集团本币授信额度=0
						((ProtocolDetailVO) childvo).setGlcappusdcdtlnamt(UFDouble.ZERO_DBL);
						//申请已用全局本币授信额度=0
						((ProtocolDetailVO) childvo).setGllcappusdcdtlnamt(UFDouble.ZERO_DBL);
					}
				}
			}
		}
	}

}
