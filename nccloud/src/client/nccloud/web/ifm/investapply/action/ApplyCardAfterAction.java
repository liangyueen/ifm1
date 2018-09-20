package nccloud.web.ifm.investapply.action;

import nc.vo.ifm.RedeemStatusEnum;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.pub.pf.BillStatusEnum;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.core.json.IJson;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.ifm.vo.OperatorParam;

public class ApplyCardAfterAction implements ICommonAction {

	@Override
	public Object doAction(IRequest request) {
		BillCard billCard = null;
		try {
			String read = request.read();
			IJson json = JsonFactory.create();
			AggInvestApplyVO vo = new AggInvestApplyVO();

			String pageId = null;
			InvestApplyVO parentVO = new InvestApplyVO();
			OperatorParam operaParam = json.fromJson(read, OperatorParam.class);
			pageId = operaParam.getPageCode();
			Integer vbillstatus = (Integer) BillStatusEnum.FREE.value();
			Integer billstatus = 0;
			parentVO.setAttributeValue("vbillstatus", vbillstatus);
			parentVO.setAttributeValue("billstatus", billstatus);
			
			vo.setParentVO(parentVO);

			BillCardConvertProcessor processor = new BillCardConvertProcessor();
			billCard = new BillCard();
			billCard = processor.convert(pageId, vo);
			// 翻译
			Translator translator = new Translator();
			translator.translate(billCard);

		} catch (Exception e) {
			ExceptionUtils.wrapException(e);
		}
		return billCard;
	}

}

// public class ApplyCardAfterAction extends CommonAfterEditAction{
//
// protected Form handleFormCardHeadAfterEditEvent(
// FormAfterEditEvent event) throws BusinessException {
// AbstractCommonAfterEditHandler<FormAfterEditEvent, Form> handler = null;
// switch (event.getAttrcode()) {
// // 资金组织
// case InvestApplyVO.PK_ORG:
// handler = new ApplyOrgChangeEditAfterHandler();
// break;
// // 币种
// case ExecAdjVO.PK_CURRTYPE:
// handler = new ExecAdjCurrtypeEditAfterHandler();
// break;
// // 注册金额
// case ExecAdjVO.CCAMOUNT:
// handler = new ExecAdjAmountAfterEditHandler();
// break;
// //保存新增
// case EVENT_SAVEADD:
// handler = new ExecAdjOrgChangeEditAfterHandler();
// break;
// default:
// break;
// }
// if(handler == null){
// return event.getForm();
// }
// return handler.doAfterEdit(event, getUiProp());
// }
//
// }
