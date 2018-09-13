package nccloud.web.ifm.redeem.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nccloud.ifm.vo.OperatorParam;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nccloud.web.ifm.common.action.CommonCommitAction;
import nccloud.web.ifm.util.RedeemUtil;

public class RedeemSubmitAction extends CommonCommitAction<AggInvestRedeemVO> {
	
	private List<String> errList;
	
	@Override
	protected AggInvestRedeemVO[] queryBillsByPks(String[] operaPks) {
		AggInvestRedeemVO[] vos = RedeemUtil.getRedeemVO(operaPks);
		return vos;
	}

	@Override
	protected AggInvestRedeemVO[] queryBillsByParam(OperatorParam operaParam) {
		Map<String, String> map = operaParam.getPkMapTs();
		String[] newpks=map.keySet().toArray(new String[0]);
		AggInvestRedeemVO[] vos = RedeemUtil.getRedeemVO(newpks);
		for (AggInvestRedeemVO vo : vos) {
			vo.getParentVO().setTs(
					new UFDateTime(map.get(vo.getParentVO().getPk_redeem())));
		}
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
							TMIFMConst.CONST_ACTION_SAVE,
							TMIFMConst.CONST_BILLTYPE_REDEEM,
							new AggInvestRedeemVO[] { vo });
					list.add(operaVO);
				} catch (BusinessException e) {
					errList.add("单据编号：" + vo.getParentVO().getPk_redeem()
							+ e.getMessage());
					continue;
				}
			}
		}
		return (AggInvestRedeemVO[]) list.toArray(new AggInvestRedeemVO[0]);
	}

	@Override
	protected String getActionCode() {
		return TMIFMConst.CONST_ACTION_SAVE;
	}

	@Override
	protected String[] getErrormessage() {
		return (String[]) errList.toArray(new String[0]);
	}
	
	private boolean doBefore(AggInvestRedeemVO vo) {
		/*InvestRedeemVO head = vo.getParentVO();
		if (!head.getProtocolstatus().equals(ProtocolStatusEnum.NOCOMMIT.value())) {
			errList.add("协议编号：" + head.getProtocolcode() + "，不可以进行提交操作！");
			return false;
		}*/
		return true;
	}
	
	@Override
	protected String getBillTypeCode() {
		return TMIFMConst.CONST_BILLTYPE_REDEEM;
	}

}
