package nc.impl.ifm;

import java.util.Collection;

import nc.bs.dao.BaseDAO;
import nc.bs.ifm.redeem.ace.bp.AceInvestRedeemQueryBP;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.ifm.IInvestRedeemQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.bill.pagination.util.PaginationUtils;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.tmpub.util.TmpubQueryUtil;

public class InvestRedeemQueryServiceImpl implements IInvestRedeemQueryService{
	
	private AceInvestRedeemQueryBP aceInvestRedeemQueryBP = new AceInvestRedeemQueryBP();

	@Override
	public AggInvestRedeemVO[] queryRedeemByPks(String[] pks)
			throws BusinessException {
		AggInvestRedeemVO[] bills = null;
		BillQuery<AggInvestRedeemVO> query = new BillQuery<AggInvestRedeemVO>(
				AggInvestRedeemVO.class);
		bills = query.query(pks);
		return PaginationUtils.filterNotExistBills(bills, pks);
	}

	@Override
	public String[] queryRedeemBySchema(IQueryScheme querySchema)
			throws BusinessException {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		QuerySchemeProcessor processor = new QuerySchemeProcessor(querySchema);
		String mainAlias = processor.getMainTableAlias();
		processor.appendFuncPermissionOrgSql();
		sql.append(" select  ");
		sql.append(mainAlias);
		sql.append(".");
		sql.append("vbillstatus");
		sql.append(processor.getFinalFromWhere());
		DataAccessUtils dao = new DataAccessUtils();
		IRowSet rowset = dao.query(sql.toString());
		String[] keys = rowset.toOneDimensionStringArray();
		return keys;
	}

	@Override
	public Boolean isBillNoDuplicate(String[] pk_redeemcol, String[] redeemcode)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SuperVO[] querySuperVOByCondition(String condition, Class voClass)
			throws BusinessException {
		return aceInvestRedeemQueryBP.querySuperVOByCondition(condition, voClass);
	}
	
	@Override
	public AggInvestRedeemVO[] getAggVOsByPKs(String... pks)
			throws BusinessException {
		return aceInvestRedeemQueryBP.getAggVOsByPKs(pks);
	}
	

}
