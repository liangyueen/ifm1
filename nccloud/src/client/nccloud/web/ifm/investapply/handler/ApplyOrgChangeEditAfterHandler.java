package nccloud.web.ifm.investapply.handler;

import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.web.ui.pattern.form.Form;
import nccloud.framework.web.ui.pattern.form.FormAfterEditEvent;
import nccloud.web.tmpub.afteredit.bean.UIProp;
import nccloud.web.tmpub.handler.AbstractFormAfterEditHandler;
import org.apache.commons.lang.StringUtils;

public class ApplyOrgChangeEditAfterHandler extends AbstractFormAfterEditHandler{
	
	@Override
	public Form processAfterEdit(FormAfterEditEvent event, UIProp uiProp)
			throws BusinessException {
		Form form = event.getForm();
		String uiState = uiProp.getReqExtParam("uiState");
		// ������֯�������
		form = loadBaseInforByOrg(form, uiState);
		return form;
	}
	
	/**
	 * ���غ���֯��ص�Ĭ�ϵ�ֵ
	 * @param form ǰ��ҳ������
	 * @param uiState �����״̬
	 * @return �����ǰ��ҳ�������
	 * @throws BusinessException
	 */
	protected Form loadBaseInforByOrg(Form form, String uiState)
			throws BusinessException {
		String pk_org = (String) form.getValue(InvestApplyVO.PK_ORG);
		if (StringUtils.isBlank(pk_org)) {
			return form;
		}
		setApplyDefaultValue(form, uiState);
		return form;
	}
	
	/**
	 * ����Ͷ���깺Ĭ��ֵ
	 * @param card
	 * @param uiState
	 * @throws BusinessException
	 */
	protected void setApplyDefaultValue(Form form, String uiState) throws BusinessException {
		
		form.setValue(InvestApplyVO.VBILLSTATUS, -1);
		form.setValue(InvestApplyVO.BILLSTATUS, 0);
//		// ����״̬
//		if ("ADD".equals(uiState)) {
//			// ����״̬
//			form.setValue(InvestApplyVO.VBILLSTATUS, -1);//���ύ
//		}
//		String pk_org = (String) form.getValue(InvestApplyVO.PK_ORG);
//		if (StringUtils.isBlank(pk_org)) {
//			return;
//		}
//		// ��������
//		form.setValue(TMIMFConst.FIELD_PK_GROUP, ApplyQueryUtil.getGroupByOrg((String)form.getValue(ExecAdjConst.FIELD_PK_ORG)));
//		// ����
//		form.setValue(InvestApplyVO.PK_CCCURRTYPE, ApplyQueryUtil.getOrgStandardCurrtype(pk_org));
	}
}
