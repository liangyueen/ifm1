package nccloud.web.ifm.income.action;


import java.util.HashMap;
import java.util.Map;

import nc.itf.ifm.IIFMApplyQueryService;
import nc.vo.ifm.VBillStatusEnum;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.pf.BillStatusEnum;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.core.json.IJson;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.container.SessionContext;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
public class IncomeInitAction  implements ICommonAction {

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
			AggInvestIncomeVO[] vos=null;
			AggInvestIncomeVO vo = null;
			IIFMApplyQueryService service=ServiceLocator.find(IIFMApplyQueryService.class);
			resultVOs=service.queryApplyByPks(new String[]{pk_apply});
			vo = setDefautValue(resultVOs);
			vos = new AggInvestIncomeVO[]{vo};
			BillCardConvertProcessor processor = new BillCardConvertProcessor();
			billCard = new BillCard();
			if(resultVOs == null || resultVOs.length == 0){
				return billCard;
			}
			billCard = processor.convert(pageId, vo);
			//翻译
			Translator translator = new Translator();
			translator.translate(billCard);

		
		} catch (Exception e) {
			ExceptionUtils.wrapException(e);
		}
		return billCard;
	}

	private AggInvestIncomeVO setDefautValue(AggInvestApplyVO[] resultVOs) {
		AggInvestIncomeVO aggVO = new AggInvestIncomeVO();
		InvestIncomeVO parentVO = new InvestIncomeVO();
		String pk_group = SessionContext.getInstance().getClientInfo()
				.getPk_group();

		// 设置单据状态、日期默认值、制单人
		Integer vbillstatus = 0;
//		Integer vbillstatus = (Integer)(nc.vo.ifm.VBillStatusEnum.自由.value());
		Integer billstatus = 1;
//		Integer billstatus =   (Integer) nc.vo.ifm.IncomeBillStatusEnum.待提交.value();
		UFDate billmakedate = new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
		String billmaker = SessionContext.getInstance().getClientInfo().getUserid();
		
		String issuebank = resultVOs[0].getParentVO().getIssuebank();
		
	
		parentVO.setAttributeValue(parentVO.PK_ORG, resultVOs[0].getParentVO().getPk_org());
		parentVO.setAttributeValue(parentVO.PRODUCTCODE, resultVOs[0].getParentVO().getProductcode());
		parentVO.setAttributeValue(parentVO.PRODUCTNAME, resultVOs[0].getParentVO().getProductname());
		parentVO.setAttributeValue("issuebank", resultVOs[0].getParentVO().getIssuebank());//发行银行
		parentVO.setAttributeValue(InvestIncomeVO.VBILLSTATUS, vbillstatus);
		parentVO.setAttributeValue("billstatus", billstatus);
		parentVO.setAttributeValue("expectedrate", resultVOs[0].getParentVO().getExpectedrate());//预期收益率
		parentVO.setAttributeValue("expectedmoney", resultVOs[0].getParentVO().getExpectedmoney());//预期收益金额
		parentVO.setAttributeValue("interestday", 365);//期限天数（利息计算天数）
		parentVO.setAttributeValue("invest", resultVOs[0].getParentVO().getInvest());//理财账户
		parentVO.setAttributeValue("settleaccount", resultVOs[0].getParentVO().getSettleaccount());//结算账户（收款银行账户）
		parentVO.setAttributeValue("pk_currtype", resultVOs[0].getParentVO().getPk_currtype());//币种
		//parentVO.setAttributeValue(parentVO.ENDDATE, billmakedate);//到账日期(默认为当前日期)
		
		//组织本币汇率
		//组织本币金额
		parentVO.setAttributeValue(parentVO.BILLMAKEDATE, billmakedate);
		parentVO.setAttributeValue(parentVO.BILLMAKER, billmaker);
		parentVO.setAttributeValue(parentVO.SOURCE, 0);
		parentVO.setAttributeValue(parentVO.OLCRATE, resultVOs[0].getParentVO().getOlcrate());//组织本币汇率
		parentVO.setAttributeValue(parentVO.OLCMOENY, resultVOs[0].getParentVO().getOlcmoney());//组织本币金额
		
		aggVO.setParentVO(parentVO);
		return aggVO;
	}

	
	
	

}
