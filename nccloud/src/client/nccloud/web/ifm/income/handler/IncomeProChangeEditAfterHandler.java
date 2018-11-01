package nccloud.web.ifm.income.handler;

import nc.itf.ifm.IIFMApplyQueryService;
import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.pf.BillStatusEnum;
import nc.voifm.ifm.util.IncomeUtil;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.SessionContext;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardFormulaHandler;
import nccloud.framework.web.ui.pattern.billcard.CardHeadAfterEditEvent;
import nccloud.web.tmpub.afteredit.bean.UIProp;
import nccloud.web.tmpub.handler.AbstractCommonAfterEditHandler;

import org.apache.commons.lang.StringUtils;


public class IncomeProChangeEditAfterHandler extends AbstractCommonAfterEditHandler<CardHeadAfterEditEvent, BillCard>{

	@Override
	protected BillCard processAfterEdit(CardHeadAfterEditEvent event,
			UIProp uiProp) throws BusinessException {
		BillCard card = event.getCard();
		BillCardConvertProcessor processor = new BillCardConvertProcessor();
		AggInvestIncomeVO vo = processor.fromBillCard(card);
		loadBaseInforByPro(event,vo);
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
	protected AggInvestIncomeVO loadBaseInforByPro(CardHeadAfterEditEvent event,AggInvestIncomeVO vo) throws BusinessException {
		String pk_org = vo.getParentVO().getPk_org();
		if (StringUtils.isBlank(pk_org)) {
			return vo;
		}
		setDefautValue(event,vo);
		return vo;
	}
	
	
	/**
	 * 设置申购调整默认值
	 * @param card
	 * @param uiState
	 * @throws BusinessException
	 */
	protected void setDefautValue(CardHeadAfterEditEvent event,AggInvestIncomeVO vo) throws BusinessException {
		InvestIncomeVO pvo = vo.getParentVO();
		String pk_apply = event.getNewvalue().getValue().toString();
		IIFMApplyQueryService service = ServiceLocator
				.find(IIFMApplyQueryService.class);
		AggInvestApplyVO[] resultVOs = null;
		resultVOs = service.queryApplyByPks(new String[] { pk_apply });
		
		// 设置审批状态、单据状态、日期默认值、制单人
		Integer vbillstatus = (Integer) BillStatusEnum.FREE.value();
		Integer billstatus =   (Integer) nc.vo.ifm.IncomeBillStatusEnum.NOSUB.value();
		UFDate billmakedate = new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
		String billmaker = SessionContext.getInstance().getClientInfo().getUserid();
		
		pvo.setAttributeValue("pk_org", pvo.getPk_org());
		pvo.setAttributeValue("pk_group", getGroupByOrg(pvo.getPk_org()));
		pvo.setAttributeValue("vbillstatus", vbillstatus);
		pvo.setAttributeValue("billstatus", billstatus);
		pvo.setAttributeValue("billmakedate", billmakedate);
		pvo.setAttributeValue("billmaker", billmaker);
		
		pvo.setAttributeValue("productcode", resultVOs[0].getParentVO().getProductcode());
		pvo.setAttributeValue("productname", resultVOs[0].getParentVO().getProductname());
		pvo.setAttributeValue("issuebank", resultVOs[0].getParentVO().getIssuebank());
		pvo.setAttributeValue("expectedrate", resultVOs[0].getParentVO().getExpectedrate());
		Integer inday=0;
		if(resultVOs[0].getParentVO().getInterestdate().equals("1")){
			 inday = 365;
		}else{
			 inday = 360;
		}
		pvo.setAttributeValue("interestday",inday);
		pvo.setAttributeValue("investaccount", resultVOs[0].getParentVO().getInvest());
		pvo.setAttributeValue("gatheringaccount", resultVOs[0].getParentVO().getSettleaccount());
		pvo.setAttributeValue("pk_currtype", resultVOs[0].getParentVO().getPk_currtype());
		pvo.setAttributeValue("olcrate", resultVOs[0].getParentVO().getOlcrate());
		//pvo.setAttributeValue("olcmoney", resultVOs[0].getParentVO().getOlcmoney());
		pvo.setAttributeValue("glcrate", resultVOs[0].getParentVO().getGlcrate());
		pvo.setAttributeValue("gllcrate", resultVOs[0].getParentVO().getGllcrate());
		pvo.setAttributeValue("pk_srcbill", resultVOs[0].getParentVO().getPk_apply());
		pvo.setAttributeValue("pk_srcbilltype", resultVOs[0].getParentVO().getPk_billtypeid());
		pvo.setAttributeValue("srcbilltypecode", resultVOs[0].getParentVO().getPk_billtypecode());
		pvo.setAttributeValue("srcbillno", resultVOs[0].getParentVO().getVbillno());
		pvo.setAttributeValue("expectedmoney", resultVOs[0].getParentVO().getExpectedmoney());//预期收益金额
		pvo.setAttributeValue("gathering", resultVOs[0].getParentVO().getSettleaccount());//结算账户（收款银行账户）
		pvo.setAttributeValue(InvestIncomeVO.GATHERINGDATE, getBusiDate());//到账日期(默认为当前业务日期)
		pvo.setAttributeValue(InvestIncomeVO.SOURCE, 0);
		pvo.setAttributeValue(InvestIncomeVO.OLCMOENY, resultVOs[0].getParentVO().getOlcmoney());//组织本币金额
		pvo.setPk_olccurr(IncomeUtil.getOrgStandardCurrtype(pvo.getPk_org()));
		vo.setParentVO(pvo);
	}
	
	
	/**
	 * 处理返回值
	 * @param vos
	 * @return
	 */
	protected BillCard doReturn(AggInvestIncomeVO vo) {
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
	
	/**
	 * 查询财务组织所在集团
	 * @param pk_org
	 * @return
	 */
	public static String getGroupByOrg(String pk_org) throws BusinessException {
		IOrgUnitPubService_C orgUnitQryService = ServiceLocator.find(IOrgUnitPubService_C.class);
		String pk_group = null;
		OrgVO[] orgVOs = orgUnitQryService.getOrgs(new String[] { pk_org }, new String[] { TMIFMConst.FIELD_PK_GROUP });
		if(orgVOs == null || orgVOs.length <= 0){
			ExceptionUtils.wrapBusinessException("获取财务组织对应的集团失败。");
		}
		pk_group = (String) orgVOs[0].getAttributeValue(TMIFMConst.FIELD_PK_GROUP);
		return pk_group;
	}

	
}
