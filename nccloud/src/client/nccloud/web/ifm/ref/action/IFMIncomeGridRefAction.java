package nccloud.web.ifm.ref.action;

import java.util.Map;

import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pf.pub.util.SQLUtil;
import nc.vo.tmpub.util.StringUtil;
import nccloud.framework.web.processor.refgrid.RefQueryInfo;
import nccloud.framework.web.ui.meta.RefMeta;
import nccloud.pubitf.platform.db.SqlParameterCollection;
import nccloud.web.refer.DefaultGridRefAction;

/**
 * 
 * 【投资理财投资收益】：参照
 * 
 * @author liglt
 * @version 1.0.0
 * @date 2018-9-9
 * 
 */
public class IFMIncomeGridRefAction extends DefaultGridRefAction {

	@Override
	public RefMeta getRefMeta(RefQueryInfo refQueryInfo) {
		RefMeta refMeta = new RefMeta();
		refMeta.setCodeField(InvestIncomeVO.PRODUCTCODE);
		refMeta.setNameField(InvestIncomeVO.PRODUCTNAME);
		refMeta.setPkField(InvestIncomeVO.PK_INCOME);
		refMeta.setExtraFields(getFieldCode());

		StringBuffer sql = new StringBuffer();
		sql.append("((");
		sql.append(" select ifm_income.pk_income, ");
		sql.append(" ifm_income.vbillno, ");
		
		// 结束日期
		sql.append(" substr(ifm_income.enddate,0,10) as enddate, ");
		sql.append(" ifm_income.dr as dr ");
		sql.append(" from ifm_income ifm_income");
		sql.append(" INNER JOIN bd_currtype on bd_currtype.pk_currtype = ifm_income.pk_currtype ");
		sql.append(")　ifm_income)");
		refMeta.setTableName(sql.toString());
		return refMeta;
	}

	private String[] getFieldCode() {
		return new String[] { InvestIncomeVO.PRODUCTCODE,
				InvestIncomeVO.PRODUCTNAME,InvestIncomeVO.ENDDATE, };
	}

	@Override
	public String getExtraSql(RefQueryInfo refQueryInfo, RefMeta refMeta) {
		String pk_org = refQueryInfo.getQueryCondition().get(
				InvestIncomeVO.PK_ORG);
		String productcode = refQueryInfo.getQueryCondition().get(
				InvestIncomeVO.PRODUCTCODE);
		String productname = refQueryInfo.getQueryCondition().get(
				InvestIncomeVO.PRODUCTNAME);
		String enddate = refQueryInfo.getQueryCondition().get(
				InvestIncomeVO.ENDDATE);
		

		StringBuffer wheresql = new StringBuffer(
				" and isnull(ifm_income.dr, 0)=0 ");
		// 组织
		if (!StringUtil.isNull(pk_org)) {
			String[] pk_orgs = pk_org.split(",");
			if (pk_orgs != null && pk_orgs.length > 0) {
				wheresql.append(" and ").append(
						SQLUtil.buildSqlForIn("ifm_income.pk_org",
								pk_orgs));
			}
		}
		// 产品编码
		if (!StringUtil.isNull(productcode)) {
			String[] productcodes = productcode.split(",");
			if (productcodes != null && productcodes.length > 0) {
				wheresql.append(" and ").append(
						SQLUtil.buildSqlForIn("ifm_income.productcodes",
								productcodes));
			}
		}
		// 产品名称
		if (!StringUtil.isNull(productname)) {
			String[] productnames = productname.split(",");
			if (productnames != null && productnames.length > 0) {
				wheresql.append(" and ").append(
						SQLUtil.buildSqlForIn(
								"ifm_income.productnames",
								productnames));
			}
		}
		// 结束日期
		if (!StringUtil.isNull(enddate)) {
			wheresql.append(" and ifm_income.enddate <= '")
					.append(enddate).append("' ");
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
