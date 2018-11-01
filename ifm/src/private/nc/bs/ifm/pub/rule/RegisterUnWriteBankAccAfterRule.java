package nc.bs.ifm.pub.rule;

import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.cm.prv.CmpConst;
import nc.itf.ifm.IIncomeCtrlService;
import nc.itf.ifm.IInvestRedeemQueryService;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.IFACIFMAccSuper;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class RegisterUnWriteBankAccAfterRule extends IFACIFMAccSuper implements
IRule<AggInvestApplyVO> {

	@Override
	public void process(AggInvestApplyVO[] vos){
		IIncomeCtrlService ctrlService = NCLocator.getInstance().lookup(IIncomeCtrlService.class);
		IInvestRedeemQueryService queryService = NCLocator.getInstance().lookup(IInvestRedeemQueryService.class);
		for (AggInvestApplyVO vo : vos) {
			InvestApplyVO parentVO = (InvestApplyVO) vo.getParent();
			//判断投资收益是否在使用当前单据(),true可以取消，false不可以取消
			boolean flag = ctrlService.isSaved(parentVO.getVbillno());
			//判断投资赎回是否在使用当前单据，true可以取消，false不可以取消
			boolean mark = queryService.ifCanDelete(parentVO.getVbillno());
			if(!flag || !mark){
				ExceptionUtils.wrappBusinessException("单据"+ parentVO.getVbillno() +"正在被使用，不能取消审批！");		
			}else if(parentVO.getVbillstatus() == 3) {
				try {
					super.delBankAcc(vo, new UFDate(parentVO.getPurchasedate().toLocalString()));
				} catch (BusinessException e) {
					ExceptionUtils.wrappException(e);
				}
			}
		}
	}

	@Override
	public String[] getBankSerialNOs(AggregatedValueObject aggvo)
			throws BusinessException {
		InvestApplyVO headvo = (InvestApplyVO) aggvo.getParentVO();
		ArrayList<String> serialnos = new ArrayList<String>();
			if (headvo.getVbillstatus()== 3) {
				if (null != headvo.getSettleaccount()) {
					serialnos.add(headvo.getVbillno()
							+ headvo.getPk_apply()
							+ headvo.getSettleaccount() + CmpConst.Direction_Pay);
				}
			}
		return serialnos.toArray(new String[0]);
	}
}
