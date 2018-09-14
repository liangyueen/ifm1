package nccloud.web.ifm.income.action;

import nc.vo.ifm.BillStatusEnum;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.lang.UFDate;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.container.SessionContext;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardFormulaHandler;
import nccloud.ifm.vo.OperatorParam;
import nccloud.web.ifm.util.IncomeUtil;

public class IncomeInitAction implements ICommonAction {

	@Override
	public Object doAction(IRequest request) {
		BillCard billCard = null;
		try {
			OperatorParam info = IncomeUtil.getRequestInfo(request,
					OperatorParam.class);
			// ����Ĭ��ֵ
			AggInvestIncomeVO aggVO = setDefautValue();

			// vo ת���� BillCard
			BillCardConvertProcessor processor = new BillCardConvertProcessor();
			billCard = processor.convert(info.getPageCode(), aggVO);

			// ��ʽ����
			BillCardFormulaHandler handler = new BillCardFormulaHandler(
					billCard);
			handler.handleLoadFormula();
			handler.handleBodyLoadFormula();
			// ���ȴ���

			// ����
			Translator translator = new Translator();
			translator.translate(billCard);
			return billCard;
		} catch (Exception e) {
			ExceptionUtils.wrapException(e);
		}
		return billCard;
	}

	private AggInvestIncomeVO setDefautValue() {
		AggInvestIncomeVO aggVO = new AggInvestIncomeVO();
		InvestIncomeVO parentVO = new InvestIncomeVO();
		String pk_group = SessionContext.getInstance().getClientInfo()
				.getPk_group();
		//����״̬
		//Integer approvestatus = (Integer) ApproveStatusEnum.FREE.value();
		//����״̬
		String billstatus =  (String) BillStatusEnum.���ύ.value();
		//�Ƶ�����
		UFDate billmakedate = new UFDate(SessionContext.getInstance()
				.getClientInfo().getBizDateTime());
		//�Ƶ���
		String billmaker = SessionContext.getInstance().getClientInfo()
				.getUserid();
		
		parentVO.setAttributeValue("pk_group", pk_group);
//		parentVO.setAttributeValue("vbillstatus", vbillstatus);
		parentVO.setAttributeValue("billstatus", billstatus);
		parentVO.setAttributeValue("billmakedate", billmakedate);
		parentVO.setAttributeValue("billmaker", billmaker);
		aggVO.setParentVO(parentVO);
		return aggVO;
	}

}
