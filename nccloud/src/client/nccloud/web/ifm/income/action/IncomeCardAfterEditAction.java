package nccloud.web.ifm.income.action;

import nc.bs.logging.Logger;
import nc.lightapp.pubapp.web.template.ref.util.StringUtils;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.BusinessException;
import nccloud.base.exception.ExceptionUtils;
import nccloud.framework.core.json.IJson;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.CardHeadAfterEditEvent;
import nccloud.web.ifm.income.handler.IncomeCurrtypeEditAfterHandler;
import nccloud.web.ifm.income.handler.IncomeOrgChangeEditAfterHandler;
import nccloud.web.tmpub.action.CommonAfterEditAction;
import nccloud.web.tmpub.afteredit.bean.UIProp;
import nccloud.web.tmpub.handler.AbstractCommonAfterEditHandler;

public class IncomeCardAfterEditAction extends CommonAfterEditAction {
	
	
	protected Object handleBillCardAfterEditEvent(CardHeadAfterEditEvent event, UIProp uiProp) throws BusinessException {
		AbstractCommonAfterEditHandler<CardHeadAfterEditEvent, BillCard> handler = null;
		switch (event.getAttrcode()) {
		// 财务组织
		case InvestIncomeVO.PK_ORG:
			handler = new IncomeOrgChangeEditAfterHandler();
			break;
		// 币种
		case InvestIncomeVO.PK_CURRTYPE:
			handler = new IncomeCurrtypeEditAfterHandler();
			break;
		default:
			break;
		}
		if(handler == null){
			return event.getCard();
		}
		return handler.doAfterEdit(event, uiProp);
	}

	@Override
	public Object doAction(IRequest request) {
		try {
			CardHeadAfterEditEvent reqParam = getReqParam(request);
			Object card = handleAfterEditEvent(reqParam);
			return card;
		} catch (BusinessException e) {
			Logger.error(e.getMessage(), e);
			ExceptionUtils.wrapBusinessException(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 获取请求参数
	 * 
	 * @param request
	 *            请求对象
	 * @return 请求参数
	 * @throws BusinessException
	 */
	private CardHeadAfterEditEvent getReqParam(IRequest request) throws BusinessException {
		String read = request.read();
		if (StringUtils.isEmpty(read)) {
			throw new BusinessException("前端请求为空！");
		}
		Logger.debug("前端请求[" + read + "]");
		IJson json = JsonFactory.create();
		CardHeadAfterEditEvent reqParam = json.fromJson(read, CardHeadAfterEditEvent.class);
		if (reqParam == null) {
			throw new BusinessException("前端请求数据不规范！");
		}
		return reqParam;
	}
	
	/**
	 * 处理编辑后事件
	 * 
	 * @param reqParam
	 *            前端请求数据
	 * @return 处理结果
	 * @throws BusinessException
	 */
	private Object handleAfterEditEvent(CardHeadAfterEditEvent reqParam) throws BusinessException {
		UIProp uiProp = new UIProp();
		Logger.debug("编辑后事件[" + reqParam + "]");
		Object resultObj = null;
		resultObj = handleBillCardAfterEditEvent(reqParam, uiProp);
		return resultObj;
	}
}
