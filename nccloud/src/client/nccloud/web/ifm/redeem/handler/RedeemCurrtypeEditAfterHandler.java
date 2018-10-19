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
 * @Description: �깺ִ�е���-���ֱ༭���¼�
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
			throw new BusinessException("�༭���¼��������");
		}
		setExecAdjForRedeem(vo);
		card = doReturn(vo);
		return card;
	}
	
	/**
	 * ������Э���ϵ��ֶδ�����������
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
			//��֯����֯��λ��
			
			//revo.setRedeemmoney(revo.getRedeemmoney().multiply(revo.getUnitnetvalue()).toString());
			revo.setOlcmoney(revo.getRedeemmoney().multiply(revo.getOlcrate()).toString());
		}
		vo.setParentVO(revo);
	}
	/**
	 * ������ֵ
	 * @param vos
	 * @return
	 */
	protected BillCard doReturn(AggInvestRedeemVO vo) {
		// �ѽ�����з�װ����
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		BillCard billCard = processor.convert(TMIFMConst.CONST_PAGECODE_CONTRACT_CARD, vo);
		// ��Ƭ���շ���
		Translator translator = new Translator();
		translator.translate(billCard);
		// ����ģ����ʾ��ʽ
		BillCardFormulaHandler handler = new BillCardFormulaHandler(billCard);
		handler.handleLoadFormula();
		// handler.handleBodyLoadFormula();
		return billCard;
	}
	
	
	/**
	 * ��ȡ��ǰҵ��ʱ��
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
	}
	
}
