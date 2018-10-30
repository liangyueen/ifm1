package nccloud.web.ifm.income.action;


import java.util.HashMap;
import java.util.Map;

import nc.itf.ifm.IIFMApplyQueryService;
import nc.vo.ifm.IncomeBillStatusEnum;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.BusinessException;
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
import nccloud.ifm.vo.OperatorParam;
import nccloud.web.ifm.util.IncomeUtil;

public class IncomeInitAction  implements ICommonAction {

	@Override
	public Object doAction(IRequest request) {
		BillCard billCard= null;
		try {
			String read = request.read();
			IJson json = JsonFactory.create();
			Map map = json.fromJson(read,HashMap.class);
			String pageId= null;
			
			AggInvestApplyVO[] resultVOs = null;
			AggInvestIncomeVO[] vos=null;
			AggInvestIncomeVO vo = new AggInvestIncomeVO();
			if (map.get("newvalue") != null) {
				String pk_apply =map.get("newvalue").toString();
				Map page_id = (Map) map.get("form");
				Map pk_org = (Map) map.get("pkorg");
				String pkorg = pk_org.get("value").toString();
				pageId = page_id.get("pageid").toString();
				
				IIFMApplyQueryService service=ServiceLocator.find(IIFMApplyQueryService.class);
				resultVOs=service.queryApplyByPks(new String[]{pk_apply});
				vo = setDefautValue(resultVOs);
			}else{
				InvestIncomeVO parentVO = new InvestIncomeVO();
				OperatorParam operaParam  = json.fromJson(read, OperatorParam.class);
				pageId = operaParam.getPageCode();
				Integer vbillstatus = (Integer) BillStatusEnum.FREE.value();
				Integer billstatus =   (Integer) IncomeBillStatusEnum.NOSUB.value();
				parentVO.setAttributeValue("vbillstatus", vbillstatus);
				parentVO.setAttributeValue("billstatus", billstatus);
				vo.setParentVO(parentVO);
				
				
			}
			//判断是否已经填写财务组织，如果未填写，就取默认值
			String pre_pkorg = vo.getParentVO().getPk_org();
			if(null == pre_pkorg){
				// 设置默认值
//				AggInvestIncomeVO aggVO = setDefautValue();
				// 判断登录用户是否有默认组织
				String pk_group = SessionContext.getInstance().getClientInfo().getPk_group();
				String defaultOrgUnit = IncomeUtil.getUserDefaultOrgUnit();//查询用户默认的主组织
				if (defaultOrgUnit != null && !pk_group.equals(defaultOrgUnit)) {
					setOrgRelatedValue(vo, defaultOrgUnit);
				}
			}
			
			vos = new AggInvestIncomeVO[]{vo};
			BillCardConvertProcessor processor = new BillCardConvertProcessor();
			billCard = new BillCard();
			if(vos == null || vos.length == 0){
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

		// 设置审批状态、单据状态、日期默认值、制单人
		Integer vbillstatus = (Integer) BillStatusEnum.FREE.value();
		Integer billstatus =   (Integer) nc.vo.ifm.IncomeBillStatusEnum.NOSUB.value();
		UFDate billmakedate = new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
		String billmaker = SessionContext.getInstance().getClientInfo().getUserid();
		
		parentVO.setAttributeValue("pk_group", pk_group);
		parentVO.setAttributeValue("pk_srcbill", resultVOs[0].getParentVO().getPk_apply());
		parentVO.setAttributeValue("pk_srcbilltype", resultVOs[0].getParentVO().getPk_billtypeid());
		parentVO.setAttributeValue("srcbilltypecode", resultVOs[0].getParentVO().getPk_billtypecode());
		parentVO.setAttributeValue("srcbillno", resultVOs[0].getParentVO().getVbillno());
		parentVO.setAttributeValue(InvestIncomeVO.VBILLSTATUS, vbillstatus);
		parentVO.setAttributeValue("billstatus", billstatus);
		parentVO.setAttributeValue(InvestIncomeVO.BILLMAKEDATE, billmakedate);
		parentVO.setAttributeValue(InvestIncomeVO.BILLMAKER, billmaker);
//		parentVO.setAttributeValue(InvestIncomeVO.PK_ORG, resultVOs[0].getParentVO().getPk_org());
		parentVO.setAttributeValue(InvestIncomeVO.PRODUCTCODE, resultVOs[0].getParentVO().getProductcode());
		parentVO.setAttributeValue(InvestIncomeVO.PRODUCTNAME, resultVOs[0].getParentVO().getProductname());
		parentVO.setAttributeValue("issuebank", resultVOs[0].getParentVO().getIssuebank());//发行银行
		parentVO.setAttributeValue("expectedrate", resultVOs[0].getParentVO().getExpectedrate());//预期收益率
		parentVO.setAttributeValue("expectedmoney", resultVOs[0].getParentVO().getExpectedmoney());//预期收益金额
		parentVO.setAttributeValue("interestday", resultVOs[0].getParentVO().getInterestday());//期限天数（利息计算天数）
		parentVO.setAttributeValue("investaccount", resultVOs[0].getParentVO().getInvest());//理财账户
		parentVO.setAttributeValue("gathering", resultVOs[0].getParentVO().getSettleaccount());//结算账户（收款银行账户）
		parentVO.setAttributeValue("pk_currtype", resultVOs[0].getParentVO().getPk_currtype());//币种
		parentVO.setAttributeValue(InvestIncomeVO.ENDDATE, billmakedate);//到账日期(默认为当前日期)
		
		//组织本币汇率
		//组织本币金额
		parentVO.setAttributeValue(InvestIncomeVO.SOURCE, 0);
		parentVO.setAttributeValue(InvestIncomeVO.OLCRATE, resultVOs[0].getParentVO().getOlcrate());//组织本币汇率
		parentVO.setAttributeValue(InvestIncomeVO.OLCMOENY, resultVOs[0].getParentVO().getOlcmoney());//组织本币金额
		
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
	private void setOrgRelatedValue(AggInvestIncomeVO vo, String pk_org)
			throws BusinessException {
		InvestIncomeVO headVO = vo.getParentVO();
		headVO.setPk_org(pk_org);
		headVO.setPk_group(IncomeUtil.getGroupByOrg(pk_org));//查询财务组织所在集团
		headVO.setPk_currtype(IncomeUtil.getOrgStandardCurrtype(pk_org));
		headVO = (InvestIncomeVO) IncomeUtil.processPrecision(headVO, true,
				getBusiDate());//处理原币本币金额及精度
		headVO.setGatheringdate(getBusiDate());//到账日期默认值
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
	
	

}
