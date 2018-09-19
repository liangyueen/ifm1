package nccloud.web.ifm.investapply.action;

import nc.vo.cc.execadj.ExecAdjVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.web.ui.pattern.form.Form;
import nccloud.framework.web.ui.pattern.form.FormAfterEditEvent;
import nccloud.web.ifm.investapply.handler.ApplyOrgChangeEditAfterHandler;
import nccloud.web.tmpub.action.CommonAfterEditAction;
import nccloud.web.tmpub.handler.AbstractCommonAfterEditHandler;

public class ApplyCardAfterAction  extends CommonAfterEditAction{

	protected Form handleFormAfterEditEvent(
			FormAfterEditEvent event) throws BusinessException {
		AbstractCommonAfterEditHandler<FormAfterEditEvent, Form> handler = null;
		switch (event.getAttrcode()) {
		// �ʽ���֯
		case InvestApplyVO.PK_ORG:
			handler = new ApplyOrgChangeEditAfterHandler();
			break;
//		// ����
//		case ExecAdjVO.PK_CURRTYPE:
//			handler = new ExecAdjCurrtypeEditAfterHandler();
//			break;
//		// ע����
//		case ExecAdjVO.CCAMOUNT:
//			handler = new ExecAdjAmountAfterEditHandler();
//			break;
//		//��������
//		case EVENT_SAVEADD:
//			handler = new ExecAdjOrgChangeEditAfterHandler();
//			break;
		default:
			break;
		}
		if(handler == null){
			return event.getForm();
		}
		return handler.doAfterEdit(event, getUiProp());
	}

}
