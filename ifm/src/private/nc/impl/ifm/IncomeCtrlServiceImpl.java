package nc.impl.ifm;

import nc.bs.ifm.income.ace.bp.AceInvestIncomeDeleteBP;
import nc.bs.ifm.income.ace.bp.AceInvestIncomeInsertBP;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.ifm.IIncomeCtrlService;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.uapec.uapecpub.util.BeanUtils;
import nccloud.web.ifm.util.IncomeUtil;

public class IncomeCtrlServiceImpl implements IIncomeCtrlService {
	@Override
	public boolean WriteIncomeBill(InvestRedeemVO redeemVO) {
		InvestIncomeVO incomeVO = new InvestIncomeVO();
//		BeanUtils.copyProperties(redeemVO, incomeVO);
		incomeVO = IncomeUtil.convertRedeemVO2IncomeVO(redeemVO, incomeVO);
		
		AggInvestIncomeVO aggIncomeVO = new AggInvestIncomeVO();
		aggIncomeVO.setParent(incomeVO);
		AggInvestIncomeVO[] vos = new AggInvestIncomeVO[1];
		vos[0] = aggIncomeVO;
		
		AceInvestIncomeInsertBP bp = new AceInvestIncomeInsertBP();
		AggInvestIncomeVO[] resVos=bp.insert(vos);
		if(null == resVos || resVos.length == 0){
			return false;
		}
		return true;
	}

	@Override
	public void RewriteIncomeBill(InvestRedeemVO redeemVO) {
		InvestIncomeVO incomeVO = new InvestIncomeVO();
//		BeanUtils.copyProperties(redeemVO, incomeVO);
		incomeVO = IncomeUtil.convertRedeemVO2IncomeVO(redeemVO, incomeVO);
		
		AggInvestIncomeVO aggIncomeVO = new AggInvestIncomeVO();
		aggIncomeVO.setParent(incomeVO);
		AggInvestIncomeVO[] vos = new AggInvestIncomeVO[1];
		vos[0] = aggIncomeVO;
		
		AceInvestIncomeDeleteBP bp = new AceInvestIncomeDeleteBP();
		bp.delete(vos);
	}

	@Override
	public boolean isSaved(String apply_vbillno) {
		//根据传入的pk_apply查询所有符合条件的incomeVOs
		DataAccessUtils dao = new DataAccessUtils();
		IRowSet rowset = dao.query("select pk_income from ifm_income where dr = 0 and srcbilltypecode='3641' and srcbillno ='"+apply_vbillno+"'");
		//不存在记录，返回true
		if(null == rowset || rowset.size() == 0){
			return true;
		}
		//否则返回false
		return false;
	}

	
}
