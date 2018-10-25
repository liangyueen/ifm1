package nccloud.web.ifm.redeem.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nccloud.base.exception.ExceptionUtils;
import nccloud.ifm.vo.OperatorParam;
import nc.bs.logging.Logger;
import nc.vo.ifm.RedeemStatusEnum;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.pf.BillStatusEnum;
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
		String[] newpks = map.keySet().toArray(new String[0]);
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
				/*
				 * try { // ���ö����ű���ִ�б��� AggInvestRedeemVO operaVO =
				 * (AggInvestRedeemVO) callActionScript(
				 * TMIFMConst.CONST_ACTION_SAVE,
				 * TMIFMConst.CONST_BILLTYPE_REDEEM, new AggInvestRedeemVO[] {
				 * vo }); list.add(operaVO); } catch (BusinessException e) {
				 * errList.add("���ݱ�ţ�" + vo.getParentVO().getPk_redeem() +
				 * e.getMessage()); continue; }
				 */
				try {
					AggInvestRedeemVO[] vos = new AggInvestRedeemVO[operaVOs.length];

					Object result = super.doCommitProcess(
							new AggInvestRedeemVO[] { vo }, null);
					if (result instanceof AggInvestRedeemVO) {
						AggInvestRedeemVO tempVo = (AggInvestRedeemVO) result;
						vos[0] = tempVo;
					} else {
						vos = (AggInvestRedeemVO[]) result;
					}
					list.add(vos[0]);
				} catch (BusinessException e) {
					errList.add("���ݱ�ţ�" + vo.getParentVO().getVbillno()
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
		InvestRedeemVO head = vo.getParentVO();
		try {
			if (!head.getBillstatus().equals(RedeemStatusEnum.���ύ.value())) {
				errList.add("Э���ţ�" + head.getVbillno() + "�������Խ����ύ������");
				return false;
			}
			// Integer vbillstatus = (Integer)
			// BillStatusEnum.COMMIT.value();//�ύ
			// Integer billstatus = (Integer) RedeemStatusEnum.�����.value();//�����
			// ���û���������Ļ���״̬Ϊ���ͨ��
			// head.setAttributeValue("vbillstatus", vbillstatus);
			// head.setAttributeValue("billstatus", billstatus);
			// head.setAttributeValue("vbillno", getActionCode());
			if (head.getHoldmoney().sub(head.getRedeemmoney()).compareTo(UFDouble.ZERO_DBL) <0
					|| head.getHoldmoney().compareTo(UFDouble.ZERO_DBL) <= 0) {

				throw new BusinessException("���н��С����ؽ�����ǰ�ĳ��н��Ϊ��"
						+ head.getHoldmoney() + "");
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			Logger.error(e.getMessage(), e);
			ExceptionUtils.wrapBusinessException(e.getMessage(), e);
		}
		return true;
	}

	@Override
	protected String getBillTypeCode() {
		return TMIFMConst.CONST_BILLTYPE_REDEEM;
	}
	public static void main(String[] args) {
		Double a =10.00;
		Double b=2.00;
		Double c =10.00;
		a.compareTo(b);
		a.compareTo(c);
		b.compareTo(c);
	}

}
