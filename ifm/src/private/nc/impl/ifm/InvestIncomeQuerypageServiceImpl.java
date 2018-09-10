package nc.impl.ifm;

import java.util.Collection;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.ifm.IInvestIncomeQuerypageService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.bill.pagination.util.PaginationUtils;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.tmpub.util.TmpubQueryUtil;

public class InvestIncomeQuerypageServiceImpl implements IInvestIncomeQuerypageService{

	@Override
	public AggInvestIncomeVO[] queryIncomeByPks(String[] pks)
			throws BusinessException {
		AggInvestIncomeVO[] bills = null;
		BillQuery<AggInvestIncomeVO> query = new BillQuery<AggInvestIncomeVO>(
				AggInvestIncomeVO.class);
		bills = query.query(pks);
		return PaginationUtils.filterNotExistBills(bills, pks);
	}

	@Override
	public String[] queryCCIncomeBySchema(IQueryScheme querySchema)
			throws BusinessException {
		StringBuffer sql = new StringBuffer();
		QuerySchemeProcessor processor = new QuerySchemeProcessor(querySchema);
		String mainAlias = processor.getMainTableAlias();
		processor.appendFuncPermissionOrgSql();
		sql.append(" select distinct ");
		sql.append(mainAlias);
		sql.append(".");
		sql.append("pk_income");
		sql.append(processor.getFinalFromWhere());
		sql.append(" and pk_billtypecode='3643' ");
		DataAccessUtils dao = new DataAccessUtils();
		IRowSet rowset = dao.query(sql.toString());
		String[] keys = rowset.toOneDimensionStringArray();
		
		// V63 增加待我审批查询方案
		keys = TmpubQueryUtil.filterForApprove(querySchema, keys,
				InvestIncomeVO.class, TMIFMConst.CONST_BILLTYPE_INCOME);
		return keys;
	}

	@Override
	public Boolean isBillNoDuplicate(String[] pk_income,
			String[] vbillno) throws BusinessException {
		if (vbillno == null || vbillno.length < 1) {
			return false;
		}
		StringBuffer condition = new StringBuffer();
		condition.append(" ").append(InvestIncomeVO.VBILLNO)
				.append(" in ").append(buildInCondition(vbillno))
				.append(" ");
		condition.append(" and dr=0 ");
		if (pk_income!= null && pk_income.length > 0) {
			condition.append(" and ").append(InvestIncomeVO.PK_INCOME)
					.append(" not in ")
					.append(buildInCondition(pk_income)).append(" ");
		}
		BaseDAO dao = new BaseDAO();
		Collection<?> coll = dao.retrieveByClause(InvestIncomeVO.class,
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



}
