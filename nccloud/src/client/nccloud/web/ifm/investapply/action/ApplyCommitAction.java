package nccloud.web.ifm.investapply.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.imf.constants.TMIMFConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nccloud.ifm.vo.OperatorParam;
import nccloud.web.ifm.investapply.util.ApplyQueryUtil;
import nccloud.web.tmifm.common.action.CommonCommitAction;
import nccloud.web.tmifm.common.action.CommonOperatorAction;

public class ApplyCommitAction extends CommonCommitAction<AggInvestApplyVO> {
	

	private List<String> errList;
	
	@Override
	protected AggInvestApplyVO[] queryBillsByPks(String[] operaPks) {
		AggInvestApplyVO[] vos = ApplyQueryUtil.getApplyVO(operaPks);
		return vos;
	}

	@Override
	protected AggInvestApplyVO[] queryBillsByParam(OperatorParam operaParam) {
		Map<String, String> map = operaParam.getPkMapTs();
		String[] newpks=map.keySet().toArray(new String[0]);
		AggInvestApplyVO[] vos = ApplyQueryUtil.getApplyVO(newpks);
		for (AggInvestApplyVO vo : vos) {
			vo.getParentVO().setTs(
					new UFDateTime(map.get(vo.getParentVO().getPk_apply())));
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
					Object result = super.doCommitProcess(new AggInvestApplyVO[]{vo}, null);
					if(result instanceof AggInvestApplyVO){
						AggInvestApplyVO tempvo = (AggInvestApplyVO) result;
						list.add(tempvo);
					}else{
						AggInvestApplyVO[] vos = (AggInvestApplyVO[]) result;
						list.add(vos[0]);
					}
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
		return TMIMFConst.CONST_ACTION_SAVE;
	}

	@Override
	protected String[] getErrormessage() {
		return (String[]) errList.toArray(new String[0]);
	}
	
	private boolean doBefore(AggInvestApplyVO vo) {
//		InvestApplyVO head = vo.getParentVO();
//		if (!head.getProtocolstatus().equals(ProtocolStatusEnum.NOEXECUTE.value())) {
//			errList.add("协议编号：" + head.getProtocolcode() + "，可以进行收回操作！");
//		}
		return true;
	}
	
	@Override
	protected String getBillTypeCode() {
		return TMIMFConst.CONST_BILLTYPE_APPLY;
	}
}
	
	
//private List<String> errList;
//	
//	@Override
//	protected AggInvestApplyVO[] queryBillsByPks(String[] operaPks) {
//		AggInvestApplyVO[] vos = ApplyQueryUtil.getApplyVO(operaPks);
//		return vos;
//	}
//
//	@Override
//	protected AggInvestApplyVO[] queryBillsByParam(OperatorParam operaParam) {
//		Map<String, String> map = operaParam.getPkMapTs();
//		String[] newpks=map.keySet().toArray(new String[0]);
//		AggInvestApplyVO[] vos = ApplyQueryUtil.getApplyVO(newpks);
//		for (AggInvestApplyVO vo : vos) {
//			vo.getParentVO().setTs(
//					new UFDateTime(map.get(vo.getParentVO().getPk_apply())));
//		}
//		return vos;
//	}
//
//	@Override
//	protected AggInvestApplyVO[] doBusinessProcess(AggInvestApplyVO[] operaVOs) {
//		errList = new ArrayList<String>();
//		List<AggInvestApplyVO> list = new ArrayList<AggInvestApplyVO>();
//		for (AggInvestApplyVO vo : operaVOs) {
//			if (this.doBefore(vo)) {
//				try {
//					// 调用动作脚本，执行保存
//					AggInvestApplyVO operaVO = (AggInvestApplyVO) callActionScript(
//							TMIMFConst.CONST_ACTION_SAVE,
//							TMIMFConst.CONST_BILLTYPE_APPLY,
//							new AggInvestApplyVO[] { vo });
//					list.add(operaVO);
//				} catch (BusinessException e) {
//					errList.add("单据编号：" + vo.getParentVO().getPk_apply()
//							+ e.getMessage());
//					continue;
//				}
//			}
//		}
//		return (AggInvestApplyVO[]) list.toArray(new AggInvestApplyVO[0]);
//	}
//
//	@Override
//	protected String getActionCode() {
//		return TMIMFConst.CONST_ACTION_SAVE;
//	}
//
//	@Override
//	protected String[] getErrormessage() {
//		return (String[]) errList.toArray(new String[0]);
//	}
//	
//	private boolean doBefore(AggInvestApplyVO vo) {
//		/*InvestRedeemVO head = vo.getParentVO();
//		if (!head.getProtocolstatus().equals(ProtocolStatusEnum.NOCOMMIT.value())) {
//			errList.add("协议编号：" + head.getProtocolcode() + "，不可以进行提交操作！");
//			return false;
//		}*/
//		return true;
//	}
//	
//	@Override
//	protected String getBillTypeCode() {
//		return TMIMFConst.CONST_BILLTYPE_APPLY;
//	}
//}

