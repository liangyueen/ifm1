package nccloud.web.ifm.ref.action;

import java.util.Map;

import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.pf.pub.util.SQLUtil;
import nc.vo.tmpub.util.StringUtil;
import nccloud.framework.web.processor.refgrid.RefQueryInfo;
import nccloud.framework.web.ui.meta.RefMeta;
import nccloud.pubitf.platform.db.SqlParameterCollection;
import nccloud.web.refer.DefaultGridRefAction;

/**
 * 
 * 【投资收益】：参照
 * 
 * @author shixin6
 * @version 1.0.0
 * @date 2018-9-9
 * 
 */
public class IFMRedeemGridRefAction extends DefaultGridRefAction {

	@Override
	public RefMeta getRefMeta(RefQueryInfo refQueryInfo) {
		RefMeta refMeta = new RefMeta();
		refMeta.setCodeField(InvestApplyVO.PRODUCTCODE);
		refMeta.setNameField(InvestApplyVO.PRODUCTCODE);
		refMeta.setPkField(InvestApplyVO.PK_APPLY);
		refMeta.setExtraFields(getFieldCode());

		StringBuffer sql = new StringBuffer();
		sql.append("((");
		// 协议编号
		sql.append(" select distinct a.pk_apply as PK_APPLY ,a.pk_org as PK_ORG, a.productcode as PRODUCTCODE,a.productname as  PRODUCTNAME,a.enddate as ENDDATE from ifm_apply a");
		sql.append(" left join ifm_redeem b" +
				" on a.productcode = b.productcode" +
				" and a.pk_org = b.pk_org" +
				" and b.billstatus in (0,1,3)" +//非全部赎回
				" where a.billstatus=2" +
				" and a.productcode is not null" +
				" and a.dr = 0" +
				" and b.dr = 0");
		sql.append(")　ifm_apply)");
		refMeta.setTableName(sql.toString());
		return refMeta;
	}

	private String[] getFieldCode() {
		return new String[] {InvestApplyVO.PK_ORG,InvestApplyVO.PRODUCTCODE,
				InvestApplyVO.PRODUCTNAME, InvestApplyVO.ENDDATE,
				 };
	}

	@Override
	public String getExtraSql(RefQueryInfo refQueryInfo, RefMeta refMeta) {
		String pk_org = refQueryInfo.getQueryCondition().get(
				InvestApplyVO.PK_ORG);
		String productcode = refQueryInfo.getQueryCondition().get(
				InvestApplyVO.PRODUCTCODE);
//		String productname = refQueryInfo.getQueryCondition().get(
//				InvestApplyVO.PRODUCTNAME);
//		String enddate = refQueryInfo.getQueryCondition().get(
//				InvestApplyVO.ENDDATE);
		

		StringBuffer wheresql = new StringBuffer("");
		// 组织
		if (!StringUtil.isNull(pk_org)) {
			String[] pk_orgs = pk_org.split(",");
			if (pk_orgs != null && pk_orgs.length > 0) {
				wheresql.append(" and ").append(
						SQLUtil.buildSqlForIn("ifm_apply.pk_org",
								pk_orgs));
			}
		}
		
		// 产品编码
		if (!StringUtil.isNull(productcode)) {
			String[] productcodes = productcode.split(",");
			if (productcodes != null && productcodes.length > 0) {
				wheresql.append(" and ").append(
						SQLUtil.buildSqlForIn("ifm_apply.productcode",
								productcodes));
			}
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
