package nccloud.web.ifm.redeem.handler;

import nccloud.web.tmpub.afteredit.bean.UIProp;
import nccloud.web.tmpub.handler.AbstractCommonAfterEditHandler;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardFormulaHandler;
import nccloud.framework.web.ui.pattern.billcard.CardHeadAfterEditEvent;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.investapply.util.ApplyQueryUtil;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.imf.constants.TMIMFConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.voifm.ifm.util.RedeemUtil;

import org.apache.commons.lang.StringUtils;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.container.SessionContext;


public class RedeemOrgChangeEditAfterHandler extends AbstractCommonAfterEditHandler<CardHeadAfterEditEvent, BillCard>{

	@Override
	protected BillCard processAfterEdit(CardHeadAfterEditEvent event,
			UIProp uiProp) throws BusinessException {
		BillCard card = event.getCard();
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		AggInvestRedeemVO vo = processor.fromBillCard(card);
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
	protected AggInvestRedeemVO loadBaseInforByOrg(AggInvestRedeemVO vo) throws BusinessException {
		String pk_org = vo.getParentVO().getPk_org();
		if (StringUtils.isBlank(pk_org)) {
			return vo;
		}
		setRedeemDefaultValue(vo);
		return vo;
	}
	
	
	/**
	 * 设置申购调整默认值
	 * @param card
	 * @param uiState
	 * @throws BusinessException
	 */
	protected void setRedeemDefaultValue(AggInvestRedeemVO vo) throws BusinessException {
		InvestRedeemVO pvo = vo.getParentVO();
		
		//pvo.setPk_org(pvo.getPk_org());		
		//pvo.setPk_group(RedeemUtil.getGroupByOrg(pvo.getPk_org()));//pk_currtype
		pvo.setPk_currtype(RedeemUtil.getOrgStandardCurrtype(pvo.getPk_org()));
		pvo = (InvestRedeemVO) RedeemUtil.processPrecision(pvo, true, getBusiDate());
		//newvo.setRedeemdate(getBusiDate());
		//newvo.setIncomedate(getBusiDate());
		vo.setParentVO(pvo);
	}
	
	
	/**
	 * 处理返回值
	 * @param vos
	 * @return
	 */
	protected BillCard doReturn(AggInvestRedeemVO vo) {
		// 把结果进行封装返回
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		BillCard billCard = processor.convert(TMIFMConst.CONST_PAGECODE_CONTRACT_CARD, vo);
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
		return TMIFMConst.CONST_BILLTYPE_REDEEM;
	}
	
	/**
	 * 获取当前业务时间
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
	}
	
	
	
}
