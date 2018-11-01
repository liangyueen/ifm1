package nccloud.web.ifm.income.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.voifm.ifm.util.IncomeUtil;
import nccloud.ifm.vo.OperatorParam;
import nccloud.web.ifm.common.action.CommonOperatorAction;

public class IncomeUnsubmitAction extends CommonOperatorAction<AggInvestIncomeVO> {

	private List<String> errList;
	
	@Override
	protected AggInvestIncomeVO[] queryBillsByPks(String[] operaPks) {
		AggInvestIncomeVO[] vos = IncomeUtil.getIncomeVO(operaPks);
		return vos;
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

	@Override
	protected AggInvestIncomeVO[] doBusinessProcess(AggInvestIncomeVO[] operaVOs) {
		errList = new ArrayList<String>();
		List<AggInvestIncomeVO> list = new ArrayList<AggInvestIncomeVO>();
		for (AggInvestIncomeVO vo : operaVOs) {
			if (this.doBefore(vo)) {
				try {
					// 调用动作脚本，执行保存
					AggInvestIncomeVO operaVO = (AggInvestIncomeVO) callActionScript(
							getActionCode(),
							TMIFMConst.CONST_BILLTYPE_INCOME,
							new AggInvestIncomeVO[] { vo });
					list.add(operaVO);
				} catch (BusinessException e) {
					errList.add("单据编号：" + vo.getParentVO().getPk_income()
							+ e.getMessage());
					continue;
				}
			}
		}
		return (AggInvestIncomeVO[]) list.toArray(new AggInvestIncomeVO[0]);
	}

	@Override
	protected String getActionCode() {
		return TMIFMConst.CONST_ACTION_UNSAVEBILL;
	}
	
	@Override
	protected String[] getErrormessage() {
		return (String[]) errList.toArray(new String[0]);
	}
	
	private boolean doBefore(AggInvestIncomeVO vo) {
		/**/
		return true;
	}

	

}
