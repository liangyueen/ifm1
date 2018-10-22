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
public class IFMAPPLYGridRefAction extends DefaultGridRefAction {

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
		sql.append(" select a.productcode as PRODUCTCODE,a.pk_apply as PK_APPLY ,a.pk_org as PK_ORG, a.productname as  PRODUCTNAME,a.enddate as ENDDATE from  ifm_apply a " +
				" where a.billstatus=2" +
				" and a.productcode is not null" +
				" and a.dr = 0 ");
		sql.append(")　ifm_apply)");
		refMeta.setTableName(sql.toString());
		return refMeta;
	}

	private String[] getFieldCode() {
		return new String[] {InvestApplyVO.PK_ORG,InvestApplyVO.PK_ORG, InvestApplyVO.PRODUCTCODE,
				InvestApplyVO.PRODUCTNAME, InvestApplyVO.ENDDATE,
				 };
	}

	@Override
	public String getExtraSql(RefQueryInfo refQueryInfo, RefMeta refMeta) {
		String pk_org = refQueryInfo.getQueryCondition().get(
				InvestApplyVO.PK_ORG);
		String productcode = refQueryInfo.getQueryCondition().get(
				InvestApplyVO.PRODUCTCODE);
		String productname = refQueryInfo.getQueryCondition().get(
				InvestApplyVO.PRODUCTNAME);
		String enddate = refQueryInfo.getQueryCondition().get(
				InvestApplyVO.ENDDATE);
		String billtype = refQueryInfo.getQueryCondition().get(
				"billtype");
		

		StringBuffer wheresql = new StringBuffer("");
		//根据单据类型判断参照过滤条件
		//参照对应财务组织购买过的状态为购买成功的产品，未全部赎回的
//		if(!StringUtil.isNull(billtype) && "3643".equals(billtype)){
//			wheresql.append(
//				" and exists(SELECT * from" +
//					" ifm_redeem b  " +
//					" WHERE ifm_apply.productcode = b.productcode" +
//					" and ifm_apply.pk_org = b.pk_org " +
//					" and b.billstatus =3" +
//					" and b.dr=0)" );
//		}
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
		// 产品名称
		if (!StringUtil.isNull(productname)) {
			String[] productnames = productname.split(",");
			if (productnames != null && productnames.length > 0) {
				wheresql.append(" and ").append(
						SQLUtil.buildSqlForIn(
								"ifm_apply.productname",
								productnames));
			}
		}
		// 结束日期
		if (!StringUtil.isNull(enddate)) {
			wheresql.append(" and ifm_apply.enddate <= '")
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
