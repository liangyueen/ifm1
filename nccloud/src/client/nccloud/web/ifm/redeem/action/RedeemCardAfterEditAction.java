package nccloud.web.ifm.redeem.action;

import nc.bs.logging.Logger;
import nc.lightapp.pubapp.web.template.ref.util.StringUtils;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.BusinessException;
import nccloud.base.exception.ExceptionUtils;
import nccloud.framework.core.json.IJson;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.CardHeadAfterEditEvent;
import nccloud.web.ifm.redeem.handler.RedeemCurrtypeEditAfterHandler;
import nccloud.web.ifm.redeem.handler.RedeemOrgChangeEditAfterHandler;
import nccloud.web.ifm.redeem.handler.RedeemProChangeEditAfterHandler;
import nccloud.web.tmpub.action.CommonAfterEditAction;
import nccloud.web.tmpub.afteredit.bean.UIProp;
import nccloud.web.tmpub.handler.AbstractCommonAfterEditHandler;


public class RedeemCardAfterEditAction extends CommonAfterEditAction {
	
	/**
	 * ���������¼�
	 */
	private final String EVENT_SAVEADD = "saveadd";

	protected Object handleBillCardAfterEditEvent(CardHeadAfterEditEvent event, UIProp uiProp) throws BusinessException {
		AbstractCommonAfterEditHandler<CardHeadAfterEditEvent, BillCard> handler = null;
		switch (event.getAttrcode()) {
		// ������֯
		case InvestRedeemVO.PK_ORG:
			handler = new RedeemOrgChangeEditAfterHandler();
			break;
//		��֯���һ���
		case InvestRedeemVO.OLCRATE:
			handler = new RedeemCurrtypeEditAfterHandler();
			break;
		// ���
		case InvestRedeemVO.REDEEMMONEY:
			handler = new RedeemCurrtypeEditAfterHandler();
			break;
		// ��λ��ֵ
		case InvestRedeemVO.UNITNETVALUE:
			handler = new RedeemCurrtypeEditAfterHandler();
			break;
		//��Ʒ����	
		case InvestRedeemVO.PRODUCTCODE:
			handler = new RedeemProChangeEditAfterHandler();
			break;
		//��ط���
		case InvestRedeemVO.REDEEMNUMBER:
			handler = new RedeemCurrtypeEditAfterHandler();
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

}
