package nccloud.web.ifm.redeem.handler;

import org.apache.commons.lang.StringUtils;

import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.imf.constants.TMIMFConst;
import nccloud.web.ifm.investapply.util.ApplyQueryUtil;
import nccloud.web.ifm.util.RedeemUtil;
import nccloud.web.tmpub.afteredit.bean.UIProp;
import nccloud.web.tmpub.handler.AbstractCommonAfterEditHandler;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardFormulaHandler;
import nccloud.framework.web.ui.pattern.billcard.CardHeadAfterEditEvent;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.container.SessionContext;

/**  
 * @Description: 申购执行调整-币种编辑后事件
 * @author wangjias 
 * @date 2018-09-14
 * @version V1.0  
 */ 
public class RedeemCurrtypeEditAfterHandler extends AbstractCommonAfterEditHandler<CardHeadAfterEditEvent, BillCard>{

	@Override
	protected BillCard processAfterEdit(CardHeadAfterEditEvent event, UIProp uiProp) throws BusinessException {
		BillCard card = event.getCard();
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		AggInvestRedeemVO vo = processor.fromBillCard(card);
		if (vo == null || vo.getParentVO() == null) {
			throw new BusinessException("编辑后事件处理错误！");
		}
		setExecAdjForRedeem(vo);
		card = doReturn(vo);
		return card;
	}
	
	/**
	 * 将授信协议上的字段带至调整单上
	 * @param vo
	 * @throws BusinessException
	 */
	private void setExecAdjForRedeem(AggInvestRedeemVO vo) throws BusinessException {
		InvestRedeemVO revo = vo.getParentVO();
		/*if (StringUtils.isBlank(revo.getPk_redeem())) {
			return;
		}*/
		String pk_olccurr = RedeemUtil.getOrgStandardCurrtype(revo.getPk_org());
		revo.setPk_olccurr(pk_olccurr);
		if(revo.getRedeemnumber()!=null && revo.getUnitnetvalue()!=null){
			UFDouble redeemnum = new UFDouble(revo.getRedeemnumber());
			revo.setRedeemmoney(redeemnum.multiply(revo.getUnitnetvalue()).toString());
			revo.setOlcmoney(revo.getRedeemmoney().multiply(revo.getOlcrate()).toString());
		}else  if(revo.getRedeemmoney()!=null && revo.getOlcrate()!=null){
			//组织的组织本位币
			
			//revo.setRedeemmoney(revo.getRedeemmoney().multiply(revo.getUnitnetvalue()).toString());
			revo.setOlcmoney(revo.getRedeemmoney().multiply(revo.getOlcrate()).toString());
		}
		vo.setParentVO(revo);
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
	 * 获取当前业务时间
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
	}
	
}
