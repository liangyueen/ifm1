package nccloud.web.ifm.apply.handler;

import nccloud.web.tmpub.afteredit.bean.UIProp;
import nccloud.web.tmpub.handler.AbstractCommonAfterEditHandler;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardFormulaHandler;
import nccloud.framework.web.ui.pattern.billcard.CardHeadAfterEditEvent;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.ifm.investapply.util.ApplyQueryUtil;
import nc.vo.imf.constants.TMIMFConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import org.apache.commons.lang.StringUtils;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.container.SessionContext;


public class ApplyOrgChangeEditAfterHandler extends AbstractCommonAfterEditHandler<CardHeadAfterEditEvent, BillCard>{

	@Override
	protected BillCard processAfterEdit(CardHeadAfterEditEvent event,
			UIProp uiProp) throws BusinessException {
		BillCard card = event.getCard();
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		AggInvestApplyVO vo = processor.fromBillCard(card);
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
	protected AggInvestApplyVO loadBaseInforByOrg(AggInvestApplyVO vo) throws BusinessException {
		String pk_org = vo.getParentVO().getPk_org();
		if (StringUtils.isBlank(pk_org)) {
			return vo;
		}
		setApplyDefaultValue(vo);
		return vo;
	}
	
	
	/**
	 * 设置申购调整默认值
	 * @param card
	 * @param uiState
	 * @throws BusinessException
	 */
	protected void setApplyDefaultValue(AggInvestApplyVO vo) throws BusinessException {
		InvestApplyVO pvo = vo.getParentVO();
		InvestApplyVO newvo = new InvestApplyVO();
		newvo.setPk_org(pvo.getPk_org());
		if (StringUtils.isBlank(pvo.getPk_apply())) {
			String billmaker = SessionContext.getInstance().getClientInfo().getUserid();
			UFDate billmakedate = new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
			newvo.setBillmaker(billmaker);
			newvo.setBillmakedate(billmakedate);
			newvo.setPk_billtypecode(getBillTypeCode());
		}
		newvo.setInterestday(2);
		newvo.setVbillstatus(-1);
		newvo.setBillstatus(0);
		newvo.setPaytype(1);
		newvo.setPk_group(ApplyQueryUtil.getGroupByOrg(pvo.getPk_org()));//pk_currtype
		newvo.setPk_olccurr(ApplyQueryUtil.getOrgStandardCurrtype(pvo.getPk_org()));
		newvo.setPk_currtype(ApplyQueryUtil.getOrgStandardCurrtype(pvo.getPk_org()));
		newvo = (InvestApplyVO) ApplyQueryUtil.processPrecision(newvo, true, getBusiDate());
		vo.setParentVO(newvo);
	}
	
	
	/**
	 * 处理返回值
	 * @param vos
	 * @return
	 */
	protected BillCard doReturn(AggInvestApplyVO vo) {
		// 把结果进行封装返回
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		BillCard billCard = processor.convert(TMIMFConst.CONST_PAGECODE_ADJUST_CARD, vo);
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
		return TMIMFConst.CONST_BILLTYPE_APPLY;
	}
	
	/**
	 * 获取当前业务时间
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
	}
	
	
	
}
