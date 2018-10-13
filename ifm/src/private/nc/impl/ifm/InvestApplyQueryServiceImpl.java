package nc.impl.ifm;

import java.util.Collection;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.ifm.IInvestApplyQueryService;
import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.imf.constants.TMIMFConst;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.bill.pagination.util.PaginationUtils;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.tmpub.util.TmpubQueryUtil;

public class InvestApplyQueryServiceImpl implements IInvestApplyQueryService {
		
	@Override
	public AggInvestApplyVO[] queryApplyByPks(String[] pks)
			throws BusinessException {
		AggInvestApplyVO[] bills = null;
		BillQuery<AggInvestApplyVO> query = new BillQuery<AggInvestApplyVO>(
				AggInvestApplyVO.class);
		bills = query.query(pks);
		return PaginationUtils.filterNotExistBills(bills, pks);
	}

	@Override
	public String[] queryIFMApplyBySchema(IQueryScheme querySchema)
			throws BusinessException {
		StringBuffer sql = new StringBuffer();
		QuerySchemeProcessor processor = new QuerySchemeProcessor(querySchema);
		String mainAlias = processor.getMainTableAlias();
		processor.appendFuncPermissionOrgSql();
		sql.append(" select  ");
		sql.append(mainAlias);
		sql.append(".");
		sql.append("billstatus");
		sql.append(processor.getFinalFromWhere());
	
		DataAccessUtils dao = new DataAccessUtils();
		IRowSet rowset = dao.query(sql.toString());
		String[] keys = rowset.toOneDimensionStringArray();
		
		// V63 增加待我审批查询方案
		keys = TmpubQueryUtil.filterForApprove(querySchema, keys,
				InvestApplyVO.class, TMIMFConst.CONST_BILLTYPE_APPLY);
		return keys;
	}

	@Override
	public Boolean isBillNoDuplicate(String[] pk_apply,
			String[] protocolcode) throws BusinessException {
		if (protocolcode == null || protocolcode.length < 1) {
			return false;
		}
		StringBuffer condition = new StringBuffer();
		condition.append(" ").append(InvestApplyVO.VBILLNO)
				.append(" in ").append(buildInCondition(protocolcode))
				.append(" ");
		condition.append(" and dr=0 ");
		if (pk_apply!= null && pk_apply.length > 0) {
			condition.append(" and ").append(nc.vo.ifm.apply.InvestApplyVO.PK_APPLY)
					.append(" not in ")
					.append(buildInCondition(pk_apply)).append(" ");
		}
		BaseDAO dao = new BaseDAO();
		Collection<?> coll = dao.retrieveByClause(InvestApplyVO.class,
				condition.toString());
		if (coll != null && coll.size() > 0) {
			return true;
		}
		return false;
	}
	
	private String buildInCondition(String[] strs) {
		StringBuffer codeIn = new StringBuffer();
		for (int i = 0; i < strs.length; i++) {
			if (i == 0) {
				codeIn.append(" ( ");
			}
			codeIn.append("'").append(strs[i]).append("'");
			if (i == strs.length - 1) {
				codeIn.append(" ) ");
			} else {
				codeIn.append(", ");
			}
		}
		return codeIn.toString();
	}

	@Override
	public String getDefaultOrgUnit() {
		try {
			return OrgSettingAccessor.getDefaultOrgUnit();
		} catch (Exception e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return null;
	}
}
