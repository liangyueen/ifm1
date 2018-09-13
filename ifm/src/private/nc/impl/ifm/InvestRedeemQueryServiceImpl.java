package nc.impl.ifm;

import java.util.Collection;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.ifm.IInvestRedeemQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.bill.pagination.util.PaginationUtils;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.tmpub.util.TmpubQueryUtil;

public class InvestRedeemQueryServiceImpl implements IInvestRedeemQueryService{

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
		return null;
	}

	@Override
	public Boolean isBillNoDuplicate(String[] pk_redeemcol, String[] redeemcode)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
