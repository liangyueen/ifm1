package nc.impl.ifm;

import nc.bs.ifm.income.ace.bp.AceInvestIncomeDeleteBP;
import nc.bs.ifm.income.ace.bp.AceInvestIncomeInsertBP;
import nc.itf.ifm.IIncomeCtrlService;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.uapec.uapecpub.util.BeanUtils;

public class IncomeCtrlServiceImpl implements IIncomeCtrlService {

	@Override
	public boolean WriteIncomeBill(InvestRedeemVO redeemVO) {
		InvestIncomeVO incomeVO = new InvestIncomeVO();
		BeanUtils.copyProperties(redeemVO, incomeVO);
		
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
		BeanUtils.copyProperties(redeemVO, incomeVO);
		
		AggInvestIncomeVO aggIncomeVO = new AggInvestIncomeVO();
		aggIncomeVO.setParent(incomeVO);
		AggInvestIncomeVO[] vos = new AggInvestIncomeVO[1];
		vos[0] = aggIncomeVO;
		
		AceInvestIncomeDeleteBP bp = new AceInvestIncomeDeleteBP();
		bp.delete(vos);
	}

	
}
