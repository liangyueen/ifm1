package nc.impl.ifm;

import nc.bs.ifm.redeem.ace.bp.AceInvestRedeemQueryBP;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.ifm.IInvestRedeemQueryService;
import nc.pubitf.setting.defaultdata.OrgSettingAccessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.bill.pagination.util.PaginationUtils;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

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
		sql.append("billstatus");
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

	@Override
	public String getDefaultOrgUnit() {
		try {
			return OrgSettingAccessor.getDefaultOrgUnit();
		} catch (Exception e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return null;
	}

	@Override
	public boolean ifCanDelete(String apply_vbillno) {
			//根据传入的pk_apply查询所有符合条件的redeem
			DataAccessUtils dao = new DataAccessUtils();
			IRowSet rowset = dao.query("select pk_redeem from ifm_redeem where srcbilltypecode='3641' and srcbillno ='"+apply_vbillno+"'");
			//不存在记录，返回true
			if(null == rowset || rowset.size() == 0){
				return true;
			}
			//否则返回false
			return false;
	}
	

//	@Override
//	public String getDefaultOrgUnit() {
//		try {
//			return OrgSettingAccessor.getDefaultOrgUnit();
//		} catch (Exception e) {
//			ExceptionUtils.wrappBusinessException(e.getMessage());
//		}
//		return null;
//	}
	

}