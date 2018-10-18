package nccloud.web.ifm.income.handler;

import nccloud.web.ifm.util.IncomeUtil;
import nccloud.web.tmpub.afteredit.bean.UIProp;
import nccloud.web.tmpub.handler.AbstractCommonAfterEditHandler;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardFormulaHandler;
import nccloud.framework.web.ui.pattern.billcard.CardHeadAfterEditEvent;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

import org.apache.commons.lang.StringUtils;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.container.SessionContext;


public class IncomeOrgChangeEditAfterHandler extends AbstractCommonAfterEditHandler<CardHeadAfterEditEvent, BillCard>{

	@Override
	protected BillCard processAfterEdit(CardHeadAfterEditEvent event,
			UIProp uiProp) throws BusinessException {
		BillCard card = event.getCard();
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		AggInvestIncomeVO vo = processor.fromBillCard(card);
		loadBaseInforByOrg(vo);
		card = doReturn(vo);
		return card;
	}
	
	/**
	 * 加载和组织相关的默认的值
	 * @param form 前端页面数据
	 * @param uiState 界面的状态
	 * @return 变更后前端页面的数据
	 * @throws BusinessException
	 */
	protected AggInvestIncomeVO loadBaseInforByOrg(AggInvestIncomeVO vo) throws BusinessException {
		String pk_org = vo.getParentVO().getPk_org();
		if (StringUtils.isBlank(pk_org)) {
			return vo;
		}
		setIncomeDefaultValue(vo);
		return vo;
	}
	
	
	/**
	 * 设置默认值
	 * @param card
	 * @param uiState
	 * @throws BusinessException
	 */
	protected void setIncomeDefaultValue(AggInvestIncomeVO vo) throws BusinessException {
		InvestIncomeVO pvo = vo.getParentVO();
		InvestIncomeVO newvo = new InvestIncomeVO();
		newvo.setPk_org(pvo.getPk_org());
		if (StringUtils.isBlank(pvo.getPk_income())) {
			String billmaker = SessionContext.getInstance().getClientInfo().getUserid();
			UFDate billmakedate = new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
			newvo.setBillmaker(billmaker);
			newvo.setBillmakedate(billmakedate);
			newvo.setPk_billtypecode(getBillTypeCode());
		}
		newvo.setVbillstatus(-1);
		newvo.setBillstatus(1);
		newvo.setPk_group(IncomeUtil.getGroupByOrg(pvo.getPk_org()));//pk_currtype
		newvo.setPk_currtype(IncomeUtil.getOrgStandardCurrtype(pvo.getPk_org()));
		newvo = (InvestIncomeVO) IncomeUtil.processPrecision(newvo, true, getBusiDate());
		newvo.setGatheringdate(getBusiDate());//到账日期默认值
		vo.setParentVO(newvo);
	}
	
	
	/**
	 * 处理返回值
	 * @param vos
	 * @return
	 */
	protected BillCard doReturn(AggInvestIncomeVO vo) {
		// 把结果进行封装返回
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		BillCard billCard = processor.convert("36670IPR_CARD", vo);
		// 卡片参照翻译
		Translator translator = new Translator();
		translator.translate(billCard);
		// 处理模板显示公式
		BillCardFormulaHandler handler = new BillCardFormulaHandler(billCard);
		handler.handleLoadFormula();
		// handler.handleBodyLoadFormula();
		return billCard;
	}
	
	/**
	 * 单据类型编码
	 * @return
	 */
	protected String getBillTypeCode() {
		return "3643";
	}
	
	/**
	 * 获取当前业务时间
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
	}
	
	
	
}
