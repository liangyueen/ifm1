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
	 * ���������¼�
	 */
	private final String EVENT_SAVEADD = "SaveAdd";

	protected Object handleBillCardAfterEditEvent(CardHeadAfterEditEvent event, UIProp uiProp) throws BusinessException {
		AbstractCommonAfterEditHandler<CardHeadAfterEditEvent, BillCard> handler = null;
		switch (event.getAttrcode()) {
		// ������֯
		case InvestApplyVO.PK_ORG:
			handler = new ApplyOrgChangeEditAfterHandler();
			break;
		// ����
		case InvestApplyVO.PK_CURRTYPE:
			handler = new ApplyCurrtypeEditAfterHandler();
			break;
		// ���
		case InvestApplyVO.MONEY:
			handler = new ApplyAmountAfterEditHandler();
			break;
		// ��λ��ֵ
		case InvestApplyVO.UNITNETVALUE:
			handler = new ApplyUnitnetvalueAfterEditHandler();
			break;
		//��֯���һ���
		case InvestApplyVO.OLCRATE:
			handler = new ApplyAmountAfterEditHandler();
			break;
		//��������
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
	 * ����༭���¼�
	 * 
	 * @param reqParam
	 *            ǰ����������
	 * @return ������
	 * @throws BusinessException
	 */
	private Object handleAfterEditEvent(CardHeadAfterEditEvent reqParam) throws BusinessException {
		UIProp uiProp = new UIProp();
		Logger.debug("�༭���¼�[" + reqParam + "]");
		Object resultObj = null;
		resultObj = handleBillCardAfterEditEvent(reqParam, uiProp);
		return resultObj;
	}
	
	/**
	 * ��ȡ�������
	 * 
	 * @param request
	 *            �������
	 * @return �������
	 * @throws BusinessException
	 */
	private CardHeadAfterEditEvent getReqParam(IRequest request) throws BusinessException {
		String read = request.read();
		if (StringUtils.isEmpty(read)) {
			throw new BusinessException("ǰ������Ϊ�գ�");
		}
		Logger.debug("ǰ������[" + read + "]");
		IJson json = JsonFactory.create();
		CardHeadAfterEditEvent reqParam = json.fromJson(read, CardHeadAfterEditEvent.class);
		if (reqParam == null) {
			throw new BusinessException("ǰ���������ݲ��淶��");
		}
		return reqParam;
	}
}
