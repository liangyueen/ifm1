package nccloud.web.ifm.investapply.action;

import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.pub.BusinessException;
import nc.lightapp.pubapp.web.template.ref.util.StringUtils;
import nccloud.web.ifm.apply.handler.ApplyAmountAfterEditHandler;
import nccloud.web.ifm.apply.handler.ApplyCurrtypeEditAfterHandler;
import nccloud.web.ifm.apply.handler.ApplyOrgChangeEditAfterHandler;
import nccloud.web.ifm.apply.handler.ApplyUnitnetvalueAfterEditHandler;
import nccloud.web.tmpub.action.CommonAfterEditAction;
import nccloud.web.tmpub.afteredit.bean.UIProp;
import nccloud.web.tmpub.handler.AbstractCommonAfterEditHandler;
import nccloud.framework.core.json.IJson;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.ui.pattern.billcard.CardHeadAfterEditEvent;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nc.bs.logging.Logger;
import nccloud.base.exception.ExceptionUtils;
import nccloud.framework.web.json.JsonFactory;

public class ApplyCardAfterAction extends CommonAfterEditAction{
	
	/**
	 * 保存新增事件
	 */
	private final String EVENT_SAVEADD = "SaveAdd";

	protected Object handleBillCardAfterEditEvent(CardHeadAfterEditEvent event, UIProp uiProp) throws BusinessException {
		AbstractCommonAfterEditHandler<CardHeadAfterEditEvent, BillCard> handler = null;
		switch (event.getAttrcode()) {
		// 财务组织
		case InvestApplyVO.PK_ORG:
			handler = new ApplyOrgChangeEditAfterHandler();
			break;
		// 币种
		case InvestApplyVO.PK_CURRTYPE:
			handler = new ApplyCurrtypeEditAfterHandler();
			break;
		// 金额
		case InvestApplyVO.MONEY:
			handler = new ApplyAmountAfterEditHandler();
			break;
		// 单位净值
		case InvestApplyVO.UNITNETVALUE:
			handler = new ApplyUnitnetvalueAfterEditHandler();
			break;
		//组织本币汇率
		case InvestApplyVO.OLCRATE:
			handler = new ApplyAmountAfterEditHandler();
			break;
		//保存新增
		case EVENT_SAVEADD:
			handler = new ApplyOrgChangeEditAfterHandler();
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
}
