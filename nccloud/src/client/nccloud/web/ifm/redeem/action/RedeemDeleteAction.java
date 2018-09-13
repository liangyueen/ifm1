package nccloud.web.ifm.redeem.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nccloud.ifm.vo.OperatorParam;
import nccloud.web.ifm.common.action.CommonOperatorAction;
import nccloud.web.ifm.util.RedeemUtil;


public class RedeemDeleteAction extends CommonOperatorAction<AggInvestRedeemVO> {

	private List<String> errList;

	@Override
	protected AggInvestRedeemVO[] queryBillsByPks(String[] operaPks) {
		AggInvestRedeemVO[] vos = RedeemUtil.getRedeemVO(operaPks);
		return vos;
	}

	@Override
	protected AggInvestRedeemVO[] doBusinessProcess(AggInvestRedeemVO[] operaVOs) {
		errList = new ArrayList<String>();
		List<AggInvestRedeemVO> list = new ArrayList<AggInvestRedeemVO>();
		for (AggInvestRedeemVO vo : operaVOs) {
			if (this.doBefore(vo)) {
				try {
					// 调用动作脚本，执行保存
					AggInvestRedeemVO operaVO = (AggInvestRedeemVO) callActionScript(
							TMIFMConst.CONST_ACTION_DELETE,
							TMIFMConst.CONST_BILLTYPE_REDEEM,
							new AggInvestRedeemVO[] { vo });
					list.add(operaVO);
				} catch (BusinessException e) {
					errList.add("单据编号：" + vo.getTableCodes()
							+ e.getMessage());
					continue;
				}
			}
		}
		return (AggInvestRedeemVO[]) list.toArray(new AggInvestRedeemVO[0]);

	}

	
	@Override
	protected String getActionCode() {
		return TMIFMConst.CONST_ACTION_DELETE;
	}

	@Override
	protected String[] getErrormessage() {
		return (String[]) errList.toArray(new String[0]);
	}

	private boolean doBefore(AggInvestRedeemVO vo) {
		
		return true;
	}

	@Override
	protected AggInvestRedeemVO[] queryBillsByParam(OperatorParam operaParam) {
		Map<String, String> map = operaParam.getPkMapTs();
		Object[] pks=map.keySet().toArray();
		String[] newpks=new String[pks.length];
		for (int i=0;i<pks.length;i++) {
			newpks[i]=(String) pks[i];
		}
		AggInvestRedeemVO[] vos = RedeemUtil.getRedeemVO(newpks);
		for (AggInvestRedeemVO vo : vos) {
			vo.getParentVO().setTs(
					new UFDateTime(map.get(vo.getParentVO().getPk_redeem())));
		}
		// TODO Auto-generated method stub
		return vos;
	}


	

}
