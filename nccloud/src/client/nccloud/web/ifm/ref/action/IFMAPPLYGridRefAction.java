package nccloud.web.ifm.ref.action;

import java.util.Map;

import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pf.pub.util.SQLUtil;
import nc.vo.tmpub.util.StringUtil;
import nccloud.framework.web.processor.refgrid.RefQueryInfo;
import nccloud.framework.web.ui.meta.RefMeta;
import nccloud.pubitf.platform.db.SqlParameterCollection;
import nccloud.web.refer.DefaultGridRefAction;

/**
 * 
 * 【资金赎回】：参照
 * 
 * @author shixin6
 * @version 1.0.0
 * @date 2018-9-9
 * 
 */
public class IFMAPPLYGridRefAction extends DefaultGridRefAction {

	@Override
	public RefMeta getRefMeta(RefQueryInfo refQueryInfo) {
		RefMeta refMeta = new RefMeta();
		refMeta.setCodeField(InvestApplyVO.PRODUCTCODE);
		refMeta.setNameField(InvestApplyVO.PRODUCTNAME);
		refMeta.setPkField(InvestApplyVO.PK_APPLY);
		refMeta.setExtraFields(getFieldCode());

		StringBuffer sql = new StringBuffer();
		sql.append("((");
		// 协议编号
		sql.append(" select a.pk_apply as PK_APPLY ,a.pk_apply as VBILLNO, a.productcode as PRODUCTCODE,a.productname as  PRODUCTNAME,a.enddate as ENDDATE,a.pk_org as PK_ORG from  ifm_apply a ");
		sql.append(")　ifm_apply)");
		refMeta.setTableName(sql.toString());
		return refMeta;
	}

	private String[] getFieldCode() {
		return new String[] {InvestRedeemVO.VBILLNO, InvestRedeemVO.PRODUCTCODE,
				InvestRedeemVO.PRODUCTNAME, InvestRedeemVO.ENDDATE,
				 };
	}

	@Override
	public String getExtraSql(RefQueryInfo refQueryInfo, RefMeta refMeta) {
		String pk_org = refQueryInfo.getQueryCondition().get(
				InvestRedeemVO.PK_ORG);
		String vbilltype = refQueryInfo.getQueryCondition().get(
				InvestRedeemVO.VBILLTYPE);
		// pk_org = refQueryInfo.getQueryCondition().get("PK_ORG");
		String enddate = refQueryInfo.getQueryCondition().get(
				InvestRedeemVO.ENDDATE);
		String productcode = refQueryInfo.getQueryCondition().get(
				InvestRedeemVO.PRODUCTCODE);
		String productname = refQueryInfo.getQueryCondition().get(
				InvestRedeemVO.PRODUCTNAME);
		

		StringBuffer wheresql = new StringBuffer("");
		// 组织
		if (!StringUtil.isNull(pk_org)) {
			String[] pk_orgs = pk_org.split(",");
			if (pk_orgs != null && pk_orgs.length > 0) {
				wheresql.append(" and ").append(
						SQLUtil.buildSqlForIn("PK_ORG",
								pk_orgs));
			}
		}
		
		// 协议状态
		/*if (!StringUtil.isNull(protocolstatus)) {
			String[] protocolstatuses = vbilltype.split(",");
			if (protocolstatuses != null && protocolstatuses.length > 0) {
				wheresql.append(" and ").append(
						SQLUtil.buildSqlForIn(
								"ccc_bankprotocol.protocolstatus",
								protocolstatuses));
			}
		}*/
		
		// 结束日期
		if (!StringUtil.isNull(vbilltype)) {
			wheresql.append(" and a.VBILLTYPE = '")
					.append(vbilltype).append("' ");
		}
		return wheresql.toString();
	}

	@Override
	public SqlParameterCollection getExtraSqlParameter(
			RefQueryInfo refQueryInfo, RefMeta refMeta) {
		return super.getExtraSqlParameter(refQueryInfo, refMeta);
	}

	@Override
	public String getOrderSql(RefQueryInfo refQueryInfo, RefMeta refMeta) {
		return super.getOrderSql(refQueryInfo, refMeta);
	}

	public boolean isRefMultiCorpFlag(RefQueryInfo refQueryInfo) {
		Map<String, String> queryCondition = refQueryInfo.getQueryCondition();
		String refMultiCorpFlag = queryCondition.get("refMultiCorpFlag");
		if (refMultiCorpFlag != null && "Y".equals(refMultiCorpFlag)) {
			return true;
		} else {
			return false;
		}
	}

}
