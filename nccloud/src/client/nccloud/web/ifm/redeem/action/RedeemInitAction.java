package nccloud.web.ifm.redeem.action;


import java.util.HashMap;
import java.util.Map;

import nc.itf.ifm.IIFMApplyQueryService;
import nc.vo.ifm.RedeemStatusEnum;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.pf.BillStatusEnum;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.core.json.IJson;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.container.SessionContext;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
public class RedeemInitAction  implements ICommonAction {

	@Override
	public Object doAction(IRequest request) {
		BillCard billCard= null;
		try {
			String read = request.read();
			IJson json = JsonFactory.create();
			Map map = json.fromJson(read,HashMap.class);
			String pk_apply =map.get("newvalue").toString();
			Map page_id = (Map) map.get("form");
			Map pk_org = (Map) map.get("pkorg");
			String pkorg = pk_org.get("value").toString();
			String pageId = page_id.get("pageid").toString();
			
			AggInvestApplyVO[] resultVOs = null;
			AggInvestRedeemVO[] vos=null;
			AggInvestRedeemVO vo = null;
			IIFMApplyQueryService service=ServiceLocator.find(IIFMApplyQueryService.class);
			resultVOs=service.queryApplyByPks(new String[]{pk_apply});
			vo = setDefautValue(resultVOs);
			//vos = new AggInvestRedeemVO[]{vo};
			BillCardConvertProcessor processor = new BillCardConvertProcessor();
			billCard = new BillCard();
			if(resultVOs == null || resultVOs.length == 0){
				return billCard;
			}
			billCard = processor.convert(pageId, vo);
			//翻译
			//Translator translator = new Translator();
			//translator.translate(billCard);

		
		} catch (Exception e) {
			ExceptionUtils.wrapException(e);
		}
		return billCard;
	}

	private AggInvestRedeemVO setDefautValue(AggInvestApplyVO[] resultVOs) {
		AggInvestRedeemVO aggVO = new AggInvestRedeemVO();
		InvestRedeemVO parentVO = new InvestRedeemVO();
		String pk_group = SessionContext.getInstance().getClientInfo()
				.getPk_group();
		// 设置单据状态、日期默认值、制单人

		Integer vbillstatus = (Integer) BillStatusEnum.FREE.value();
		//需要改一下
		
		String issuebank = resultVOs[0].getParentVO().getIssuebank();
		
		Integer billstatus =   (Integer) RedeemStatusEnum.NOSUB.value();
		UFDate billmakedate = new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
	
		String billmaker = SessionContext.getInstance().getClientInfo().getUserid();
		parentVO.setAttributeValue("pk_org", resultVOs[0].getParentVO().getPk_org());
		parentVO.setAttributeValue("vbillstatus", vbillstatus);
		parentVO.setAttributeValue("billstatus", billstatus);
		parentVO.setAttributeValue("billmakedate", billmakedate);
		parentVO.setAttributeValue("billmaker", billmaker);
		parentVO.setAttributeValue("productcode", resultVOs[0].getParentVO().getProductcode());
		parentVO.setAttributeValue("productname", resultVOs[0].getParentVO().getProductname());
		parentVO.setAttributeValue("issuebank", pk_group);
		parentVO.setAttributeValue("expectedrate", resultVOs[0].getParentVO().getExpectedrate());
		parentVO.setAttributeValue("expectedrate", resultVOs[0].getParentVO().getExpectedrate());
		parentVO.setAttributeValue("interestday", resultVOs[0].getParentVO().getInterestday());
		parentVO.setAttributeValue("interestday", resultVOs[0].getParentVO().getInterestday());
		parentVO.setAttributeValue("invest", resultVOs[0].getParentVO().getInvest());
		parentVO.setAttributeValue("pk_currtype", resultVOs[0].getParentVO().getPk_currtype());
		parentVO.setAttributeValue("olcrate", resultVOs[0].getParentVO().getOlcrate());
		parentVO.setAttributeValue("olcmoney", resultVOs[0].getParentVO().getOlcmoney());
		
		aggVO.setParentVO(parentVO);
		return aggVO;
	}

	
	
	

}
