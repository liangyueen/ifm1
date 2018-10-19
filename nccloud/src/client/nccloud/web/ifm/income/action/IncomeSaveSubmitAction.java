package nccloud.web.ifm.income.action;

import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.container.SessionContext;
import nccloud.web.ifm.util.IncomeUtil;
import nccloud.web.tmifm.common.action.CommonSaveAction;

public class IncomeSaveSubmitAction extends CommonSaveAction<AggInvestIncomeVO> {

	@Override
	protected AggInvestIncomeVO doBusinessSave(AggInvestIncomeVO operaVO) {
		this.doBefore(operaVO);
		try {
			// 调用动作脚本，执行保存
			operaVO = (AggInvestIncomeVO) callActionScript(
					TMIFMConst.CONST_ACTION_SAVEBASE,
					TMIFMConst.CONST_BILLTYPE_INCOME,
					new AggInvestIncomeVO[] { operaVO });
			// 调用动作脚本，执行提交
			operaVO = (AggInvestIncomeVO) super.doCommitProcess(
					new AggInvestIncomeVO[] { operaVO }, null);
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("保存单据失败：" + e.getMessage());
		}
		return operaVO;
	}

	@Override
	protected String getActionCode() {
		return TMIFMConst.CONST_ACTION_SAVE;
	}

	private boolean doBefore(AggInvestIncomeVO vo) {
		InvestIncomeVO headVO = vo.getParentVO();
		// 设置默认值
		if (StringUtil.isEmptyWithTrim(headVO.getPrimaryKey())) {
			// 设置审计信息
			headVO.setAttributeValue("creator", SessionContext.getInstance()
					.getClientInfo().getUserid());
			headVO.setAttributeValue("creationtime", new UFDate(SessionContext
					.getInstance().getClientInfo().getBizDateTime()));
			// 制单人，制单时间
			headVO.setAttributeValue("billmaker", SessionContext.getInstance()
					.getClientInfo().getUserid());
			headVO.setAttributeValue("billmakedate", new UFDate(SessionContext
					.getInstance().getClientInfo().getBizDateTime()));
			headVO.setAttributeValue("pk_billtypecode", "3643");
		}

		return true;
	}

	@Override
	protected String getBillTypeCode() {
		return TMIFMConst.CONST_BILLTYPE_INCOME;
	}

	@Override
	protected String getPageCode() {
		return TMIFMConst.CONST_PAGECODE_INCOME_CARD;
	}

	/**
	 * 金额及精度处理
	 * 
	 * @param vos
	 * @return
	 * @throws BusinessException
	 */
	@Override
	protected AggInvestIncomeVO processDigit(AggInvestIncomeVO operaVO) {
		InvestIncomeVO vo = operaVO.getParentVO();

		try {
			vo = (InvestIncomeVO) IncomeUtil.processPrecision(vo, true,
					getBusiDate());
			operaVO.setParentVO(vo);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return operaVO;
	}

	/**
	 * 获取当前业务日期
	 * 
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo()
				.getBizDateTime());
	}
}
