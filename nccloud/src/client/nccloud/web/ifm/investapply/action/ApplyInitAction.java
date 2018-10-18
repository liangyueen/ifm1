package nccloud.web.ifm.investapply.action;

import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.imf.constants.TMIMFConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.pf.BillStatusEnum;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.container.SessionContext;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardFormulaHandler;
import nccloud.ifm.vo.OperatorParam;
import nccloud.web.ifm.investapply.util.ApplyQueryUtil;

public class ApplyInitAction implements ICommonAction{
	
	@Override
	public Object doAction(IRequest request) {
		BillCard billCard = null;
		try {
			OperatorParam info = ApplyQueryUtil.getRequestInfo(request,
					OperatorParam.class);
			// 设置默认值
			AggInvestApplyVO aggVO = setDefautValue();
			
			// 判断登录用户是否有默认组织
			String defaultOrgUnit = ApplyQueryUtil.getUserDefaultOrgUnit();
			if (defaultOrgUnit != null) {
				setOrgRelatedValue(aggVO, defaultOrgUnit);
			}

			// vo 转化成 BillCard
			BillCardConvertProcessor processor = new BillCardConvertProcessor();
			billCard = processor.convert(info.getPageCode(), aggVO);

			// 公式处理
			BillCardFormulaHandler handler = new BillCardFormulaHandler(
					billCard);
			handler.handleLoadFormula();
			handler.handleBodyLoadFormula();
			// 精度处理

			// 翻译
			Translator translator = new Translator();
			translator.translate(billCard);
			return billCard;
		} catch (Exception e) {
			ExceptionUtils.wrapException(e);
		}
		return billCard;
	}

	private AggInvestApplyVO setDefautValue() throws BusinessException {
		AggInvestApplyVO aggVO = new AggInvestApplyVO();
		InvestApplyVO parentVO = new InvestApplyVO();
		String pk_group = SessionContext.getInstance().getClientInfo()
				.getPk_group();
		// 设置单据状态、日期默认值、制单人
		Integer vbillstatus = (Integer) BillStatusEnum.FREE.value();
		UFDate billmakedate = new UFDate(SessionContext.getInstance()
				.getClientInfo().getBizDateTime());
		String billmaker = SessionContext.getInstance().getClientInfo()
				.getUserid();
		parentVO.setPk_billtypecode(getBillTypeCode());
		parentVO.setBillstatus(0);
		parentVO.setPaytype(1);
		parentVO.setAttributeValue("pk_group", pk_group);
		parentVO.setPk_olccurr(ApplyQueryUtil.getOrgStandardCurrtype(pk_group));
		parentVO.setAttributeValue("protocoltype", 1);
		parentVO.setAttributeValue("vbillstatus", vbillstatus);
		parentVO.setAttributeValue("billmakedate", billmakedate);
		parentVO.setAttributeValue("billmaker", billmaker);
		aggVO.setParentVO(parentVO);
		return aggVO;
	}
	
	/**
	 * 设置默认组织相关字段
	 * 
	 * @param card
	 * @param uiState
	 * @throws BusinessException
	 */
	private void setOrgRelatedValue(AggInvestApplyVO vo, String pk_org)
			throws BusinessException {
		InvestApplyVO headVO = vo.getParentVO();
		headVO.setPk_org(pk_org);
		headVO.setPk_group(ApplyQueryUtil.getGroupByOrg(pk_org));
		headVO.setPk_currtype(ApplyQueryUtil.getOrgStandardCurrtype(pk_org));
		headVO = (InvestApplyVO) ApplyQueryUtil.processPrecision(headVO, true,
				getBusiDate());
		vo.setParentVO(headVO);
	}
	
	/**
	 * 获取当前业务时间
	 * 
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo()
				.getBizDateTime());
	}
	
	/**
	 * 单据类型编码
	 * @return
	 */
	protected String getBillTypeCode() {
		return TMIMFConst.CONST_BILLTYPE_APPLY;
	}

}
