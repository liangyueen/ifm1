package nccloud.web.ifm.income.approve;

import itf.approvecenter.util.DataExchangeBean;

import java.util.ArrayList;
import java.util.List;

import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.tmpub.batch.validation.IBatchValidator;
import nccloud.framework.web.container.SessionContext;
import nccloud.pubitf.platform.approve.AbstractApproveBusiHandlerImpl;

/**
 * �������ļ��ע��ʵ����
 * @author wuzhwa
 * @version nccloud v1.0
 * @date 2018��9��28��
 */
public class IncomeApprovePreCheck extends AbstractApproveBusiHandlerImpl{

	@Override
	public DataExchangeBean checkBeforeApprove(String billtype, String billno,
			Object billVO) throws Exception {
		AggInvestIncomeVO aggVO = (AggInvestIncomeVO)billVO;
		InvestIncomeVO parentVO = aggVO.getParentVO();
		Integer billstatus = parentVO.getBillstatus();
		if (billstatus == null) {
			throw new BusinessException("����״̬Ϊ�գ��޷�ִ�д˲�����");
		}
		long bizTimeL = SessionContext.getInstance().getClientInfo()
				.getBizDateTime();
		UFDate bizDate = new UFDate(bizTimeL);
//		//����ǰ����
//		SpePayApproveBusiUtil.beforeApprove(bizDate, aggVO);
//		//����ǰУ��
//		SpePayApproveBusiUtil.validate(getApproveValidators(), aggVO);
		DataExchangeBean retBean = new DataExchangeBean();
		retBean.setCode("200");
		return retBean;
	}

	@Override
	public DataExchangeBean handleApproveBusiException(String billtype,
			String billno, Object billVO, Exception ex) throws Exception {
		
		return null;
	}

	@Override
	public DataExchangeBean unProcessFlow(String billtype, String billno,
			Object billVO, Exception ex) throws Exception {
		AggInvestIncomeVO aggVO = (AggInvestIncomeVO)billVO;
		InvestIncomeVO parentVO = aggVO.getParentVO();
		Integer billstatus = parentVO.getBillstatus();
		if (billstatus == null) {
			throw new BusinessException("����״̬Ϊ�գ��޷�ִ�д˲�����");
		}
//		//ȡ������ǰ����
//		SpePayApproveBusiUtil.beforeUnApprove(aggVO);
//		//ȡ������ǰУ��
//		SpePayApproveBusiUtil.validate(getUnApproveValidators(), aggVO);
		DataExchangeBean retBean = new DataExchangeBean();
		retBean.setCode("200");
		return retBean;
	}
	
	/**
	 * ��ȡ����У��������
	 * 
	 * @return
	 */
	private List<IBatchValidator<AggInvestIncomeVO>> getApproveValidators() {
		List<IBatchValidator<AggInvestIncomeVO>> validators = new ArrayList<>();
//		validators.add(new SpePayApproveValidator());
//		BatchPowerValidatorForNCCClient validator = new BatchPowerValidatorForNCCClient();
//		validator.setActionCode("approve");
//		validator.setPermissionCode(CdmcPubConst.CONST_BILLTYPE_REPAYPRCPL);
//		validators.add(validator);
		return validators;
	}
	
	/**
	 * ��ȡȡ������У��������
	 * 
	 * @return
	 */
	private List<IBatchValidator<AggInvestIncomeVO>> getUnApproveValidators() {
		List<IBatchValidator<AggInvestIncomeVO>> validators = new ArrayList<>();
//		validators.add(new SpePayUnApproveValidator());
//		BatchPowerValidatorForNCCClient validator = new BatchPowerValidatorForNCCClient();
//		validator.setActionCode("approve");
//		validator.setPermissionCode("36JB");
//		validators.add(validator);
		return validators;
	}
}
