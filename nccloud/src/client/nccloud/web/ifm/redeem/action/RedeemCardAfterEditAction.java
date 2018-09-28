package nccloud.web.ifm.redeem.action;

import nc.bs.logging.Logger;
import nc.lightapp.pubapp.web.template.ref.util.StringUtils;
import nc.vo.cc.execadj.ExecAdjVO;
import nc.vo.pub.BusinessException;
import nccloud.base.exception.ExceptionUtils;
import nccloud.framework.core.json.IJson;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.CardHeadAfterEditEvent;
import nccloud.web.tmpub.action.CommonAfterEditAction;
import nccloud.web.tmpub.afteredit.bean.UIProp;
import nccloud.web.tmpub.handler.AbstractCommonAfterEditHandler;

/**  
 * @Description: ����ִ�е���-��Ƭ�༭���¼�
 * @author wangjias 
 * @date 2018-09-13
 * @version V1.0  
 */ 
public class RedeemCardAfterEditAction extends CommonAfterEditAction {
	
	/**
	 * ���������¼�
	 */
	private final String EVENT_SAVEADD = "saveadd";

	protected Object handleBillCardAfterEditEvent(CardHeadAfterEditEvent event, UIProp uiProp) throws BusinessException {
		AbstractCommonAfterEditHandler<CardHeadAfterEditEvent, BillCard> handler = null;
		switch (event.getAttrcode()) {
		// ������֯
//		case ExecAdjVO.PK_ORG:
//			handler = new ExecAdjOrgChangeEditAfterHandler();
//			break;
//		// ����
//		case ExecAdjVO.PK_CCCURRTYPE:
//			handler = new ExecAdjCurrtypeEditAfterHandler();
//			break;
//		// ���
//		case ExecAdjVO.CCAMOUNT:
//			handler = new ExecAdjAmountAfterEditHandler();
//			break;
//		// ����Э��
//		case ExecAdjVO.PK_PROTOCOL:
//			handler = new ExecAdjProtocolEditAfterHandler();
//			break;
//		//��������
//		case EVENT_SAVEADD:
//			handler = new ExecAdjOrgChangeEditAfterHandler();
//			break;
//		default:
//			break;
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
