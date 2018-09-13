package nccloud.web.ifm.redeem.action;

import nc.itf.ifm.IInvestRedeemQueryService;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nccloud.framework.service.ServiceLocator;
import nccloud.web.ifm.common.action.CommonListQueryAction;

import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;



public class RedeemListQueryAction extends CommonListQueryAction<AggInvestRedeemVO> {

	@Override
	protected AggInvestRedeemVO[] queryBillsByPks(String[] currPagePks) {
		AggInvestRedeemVO[] vos=null;
		try {
			IInvestRedeemQueryService service=ServiceLocator.find(IInvestRedeemQueryService.class);
			vos=service.queryRedeemByPks(currPagePks);
			} catch (BusinessException e) {
			e.printStackTrace();
		}
		return vos;
	}

	

	


	@Override
	protected Class<? extends SuperVO> getVOClass() {
		// TODO Auto-generated method stub
		return InvestRedeemVO.class;
	}






	@Override
	protected String getAreaCode() {
		// TODO Auto-generated method stub
		return TMIFMConst.CONST_AREACODE_REDEEM_LIST_TABLE;
	}

}
