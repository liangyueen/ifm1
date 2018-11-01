package nccloud.web.ifm.income.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.ifm.IncomeBillStatusEnum;
import nc.vo.ifm.RedeemStatusEnum;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.pf.BillStatusEnum;
import nc.voifm.ifm.util.IncomeUtil;
import nccloud.ifm.vo.OperatorParam;
import nccloud.web.ifm.common.action.CommonCommitAction;
import nccloud.web.workflow.approve.util.NCCFlowUtils;

public class IncomeSubmitAction extends CommonCommitAction<AggInvestIncomeVO> {
	
	private List<String> errList;
	
	@Override
	protected AggInvestIncomeVO[] queryBillsByPks(String[] operaPks) {
		AggInvestIncomeVO[] vos = IncomeUtil.getIncomeVO(operaPks);
		return vos;
	}

	@Override
	protected AggInvestIncomeVO[] queryBillsByParam(OperatorParam operaParam) {
		Map<String, String> map = operaParam.getPkMapTs();
		String[] newpks=map.keySet().toArray(new String[0]);
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
				/*try {
					// ���ö����ű���ִ�б���
					AggInvestIncomeVO operaVO = (AggInvestIncomeVO) callActionScript(
							TMIFMConst.CONST_ACTION_SAVE,
							TMIFMConst.CONST_BILLTYPE_INCOME,
							new AggInvestIncomeVO[] { vo });
					list.add(operaVO);
//					Object result = super.doCommitProcess(new AggInvestIncomeVO[]{vo}, null);
//					AggInvestIncomeVO[] vos = (AggInvestIncomeVO[]) result;
//					list.add(vos[0]);
				} catch (BusinessException e) {
					errList.add("���ݱ�ţ�" + vo.getParentVO().getVbillno()
							+ e.getMessage());
					continue;
				}*/
				try {
					AggInvestIncomeVO[] vos=new AggInvestIncomeVO[operaVOs.length];
					
					
					Object result = super.doCommitProcess(new AggInvestIncomeVO[]{vo}, null);
					if(result instanceof AggInvestIncomeVO){
						AggInvestIncomeVO tempVo = (AggInvestIncomeVO) result;
						vos[0]=tempVo;
					}else{
						vos = (AggInvestIncomeVO[]) result;
					}
					list.add(vos[0]);
				} catch (BusinessException e) {
					errList.add("���ݱ�ţ�" + vo.getParentVO().getVbillno()
							+ e.getMessage());
					continue;
				}
			}
		}
		return (AggInvestIncomeVO[]) list.toArray(new AggInvestIncomeVO[0]);
	}

	@Override
	protected String getActionCode() {
		return TMIFMConst.CONST_ACTION_SAVE;
	}

	@Override
	protected String[] getErrormessage() {
		return (String[]) errList.toArray(new String[0]);
	}
	
	private boolean doBefore(AggInvestIncomeVO vo) {
		InvestIncomeVO head = vo.getParentVO();
//		if (!head.getBillstatus().equals(RedeemStatusEnum.���ύ.value())) {
//			errList.add("Э���ţ�" + head.getVbillno() + "�������Խ����ύ������");
//			return false;
//		}
		Integer vbillstatus = (Integer) BillStatusEnum.COMMIT.value();//�ύ
		Integer billstatus =   (Integer) IncomeBillStatusEnum.NOAUDIT.value();//�����
		//���û���������Ļ���״̬Ϊ���ͨ��
		head.setAttributeValue("vbillstatus", vbillstatus);
		head.setAttributeValue("billstatus", billstatus);
		//head.setAttributeValue("vbillno", getActionCode());
//		if (head.getHoldmoeny().sub(head.getRedeemmoney()).compareTo(UFDouble.ZERO_DBL)<1 && head.getHoldmoeny().compareTo(UFDouble.ZERO_DBL)>1) {
//			try {
//				throw new BusinessException("���н��С����ؽ�����ǰ�ĳ��н��Ϊ��"+head.getHoldmoeny()+"");
//			} catch (BusinessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		return true;
	}
	
	@Override
	protected String getBillTypeCode() {
		return TMIFMConst.CONST_BILLTYPE_INCOME;
	}

}
