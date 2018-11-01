package nccloud.web.ifm.investapply.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.investapply.util.ApplyQueryUtil;
import nc.vo.imf.constants.TMIMFConst;
import nc.vo.pub.BusinessException;
import nccloud.ifm.vo.OperatorParam;
import nccloud.web.tmifm.common.action.CommonOperatorAction;

public class ApplyUnCommitAction extends CommonOperatorAction<AggInvestApplyVO> {

	private List<String> errList;
	
	@Override
	protected String[] getErrormessage() {
		return (String[]) errList.toArray(new String[0]);
	}

	@Override
	protected AggInvestApplyVO[] queryBillsByPks(String[] operaPks) {
		AggInvestApplyVO[] vos = ApplyQueryUtil.getApplyVO(operaPks);
		return vos;
	}

	@Override
	protected AggInvestApplyVO[] queryBillsByParam(OperatorParam operaParam) {
		Map<String, String> map = operaParam.getPkMapTs();
		Object[] pks=map.keySet().toArray();
		String[] newpks=new String[pks.length];
		for (int i=0;i<pks.length;i++) {
			newpks[i]=(String) pks[i];
		}
		AggInvestApplyVO[] vos = ApplyQueryUtil.getApplyVO(newpks);
		for (AggInvestApplyVO vo : vos) {
			vo.getParentVO().setTs(new nc.vo.pub.lang.UFDateTime(map.get(vo.getParentVO().getPk_apply())));
		}
		return vos;
	}

	@Override
	protected AggInvestApplyVO[] doBusinessProcess(AggInvestApplyVO[] operaVOs) {
 		errList = new ArrayList<String>();
		List<AggInvestApplyVO> list = new ArrayList<AggInvestApplyVO>();
		for (AggInvestApplyVO vo : operaVOs) {
			if (this.doBefore(vo)) {
				try {
					// 调用动作脚本，执行保存
					AggInvestApplyVO operaVO = (AggInvestApplyVO) callActionScript(
							TMIMFConst.CONST_ACTION_UNSAVEBILL,
							TMIMFConst.CONST_BILLTYPE_APPLY,
							new AggInvestApplyVO[] { vo });
					list.add(operaVO);
				} catch (BusinessException e) {
					errList.add("单据编号：" + vo.getParentVO().getVbillno()
							+ e.getMessage());
					continue;
				}
			}
		}
		return (AggInvestApplyVO[]) list.toArray(new AggInvestApplyVO[0]);

	}

	@Override
	protected String getActionCode() {
		return TMIMFConst.CONST_ACTION_UNSAVEBILL;
	}

	private boolean doBefore(AggInvestApplyVO vo) {
		return true;
//		InvestApplyVO head = vo.getParentVO();
//		if (!head.getBillstatus().equals(ProtocolStatusEnum.SUBMIT.value())) {
//			errList.add("协议编号：" + head.getProtocolcode() + "，只有协议状态为待提交的单据才可以进行删除操作！");
//		}
//		
//		return true;
	}


}
