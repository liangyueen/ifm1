package nccloud.web.ifm.income.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.voifm.ifm.util.IncomeUtil;
import nccloud.ifm.vo.OperatorParam;
import nccloud.web.ifm.common.action.CommonOperatorAction;


public class IncomeDeleteAction extends CommonOperatorAction<AggInvestIncomeVO> {

	private List<String> errList;

	@Override
	protected AggInvestIncomeVO[] queryBillsByPks(String[] operaPks) {
		AggInvestIncomeVO[] vos = IncomeUtil.getIncomeVO(operaPks);
		return vos;
	}

	@Override
	protected AggInvestIncomeVO[] doBusinessProcess(AggInvestIncomeVO[] operaVOs) {
		errList = new ArrayList<String>();
		List<AggInvestIncomeVO> list = new ArrayList<AggInvestIncomeVO>();
		for (AggInvestIncomeVO vo : operaVOs) {
			if (this.doBefore(vo)) {
				try {
					// 调用动作脚本，执行保存
					AggInvestIncomeVO operaVO = (AggInvestIncomeVO) callActionScript(
							TMIFMConst.CONST_ACTION_DELETE,
							TMIFMConst.CONST_BILLTYPE_INCOME,
							new AggInvestIncomeVO[] { vo });
					list.add(operaVO);
				} catch (BusinessException e) {
					errList.add("单据编号：" + vo.getParentVO().getVbillno()
							+ e.getMessage());
					continue;
				}
			}
		}
		return (AggInvestIncomeVO[]) list.toArray(new AggInvestIncomeVO[0]);

	}

	private boolean doBefore(AggInvestIncomeVO vo) {

//		InvestIncomeVO head = vo.getParentVO();
//		if (!head.getProtocolstatus().equals(ProtocolStatusEnum.NOCOMMIT.value())) {
//			errList.add("协议编号：" + head.getProtocolcode() + "，不可以进行删除操作！");
//			return false;
//		}
		return true;
	}

	@Override
	protected String getActionCode() {
		return TMIFMConst.CONST_ACTION_DELETE;
	}

	@Override
	protected String[] getErrormessage() {
		return (String[]) errList.toArray(new String[0]);
	}

	@Override
	protected AggInvestIncomeVO[] queryBillsByParam(OperatorParam operaParam) {
		Map<String, String> map = operaParam.getPkMapTs();
		Object[] pks=map.keySet().toArray();
		String[] newpks=new String[pks.length];
		for (int i=0;i<pks.length;i++) {
			newpks[i]=(String) pks[i];
		}
		AggInvestIncomeVO[] vos = IncomeUtil.getIncomeVO(newpks);
		for (AggInvestIncomeVO vo : vos) {
			vo.getParentVO().setTs(
					new UFDateTime(map.get(vo.getParentVO().getPk_income())));
		}
		return vos;
	}


	

}
