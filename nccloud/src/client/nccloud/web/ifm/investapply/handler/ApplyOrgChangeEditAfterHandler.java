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
		// 加载组织相关数据
		form = loadBaseInforByOrg(form, uiState);
		return form;
	}
	
	/**
	 * 加载和组织相关的默认的值
	 * @param form 前端页面数据
	 * @param uiState 界面的状态
	 * @return 变更后前端页面的数据
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
	 * 设置投资申购默认值
	 * @param card
	 * @param uiState
	 * @throws BusinessException
	 */
	protected void setApplyDefaultValue(Form form, String uiState) throws BusinessException {
		
		form.setValue(InvestApplyVO.VBILLSTATUS, -1);
		form.setValue(InvestApplyVO.BILLSTATUS, 0);
//		// 新增状态
//		if ("ADD".equals(uiState)) {
//			// 单据状态
//			form.setValue(InvestApplyVO.VBILLSTATUS, -1);//待提交
//		}
//		String pk_org = (String) form.getValue(InvestApplyVO.PK_ORG);
//		if (StringUtils.isBlank(pk_org)) {
//			return;
//		}
//		// 所属集团
//		form.setValue(TMIMFConst.FIELD_PK_GROUP, ApplyQueryUtil.getGroupByOrg((String)form.getValue(ExecAdjConst.FIELD_PK_ORG)));
//		// 币种
//		form.setValue(InvestApplyVO.PK_CCCURRTYPE, ApplyQueryUtil.getOrgStandardCurrtype(pk_org));
	}
}
