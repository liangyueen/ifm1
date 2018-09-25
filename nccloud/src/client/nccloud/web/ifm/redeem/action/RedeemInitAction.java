package nccloud.web.ifm.redeem.action;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.ifm.IIFMApplyQueryService;
import nc.itf.ifm.IInvestRedeemQueryService;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nccloud.ifm.vo.OperatorParam;
import nc.vo.ifm.RedeemStatusEnum;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nccloud.dto.baseapp.querytree.dataformat.QueryTreeFormatVO;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.core.json.IJson;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.container.SessionContext;
import nccloud.framework.web.convert.translate.Translator;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.processor.template.BillCardConvertProcessor;
import nccloud.framework.web.processor.template.ExtBillCardConvertProcessor;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardFormulaHandler;
import nccloud.pubitf.platform.query.INCCloudQueryService;
import nccloud.web.common.bean.CardOperatorParam;
import nccloud.web.ifm.common.action.CommonQueryCardAction;
import nccloud.web.ifm.util.RedeemUtil;
import nccloud.framework.web.ui.pattern.billcard.CardHeadAfterEditEvent;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCard;
import nccloud.framework.web.ui.pattern.form.Form;
import nccloud.framework.web.ui.pattern.grid.Grid;
public class RedeemInitAction  implements ICommonAction {

	@Override
	public Object doAction(IRequest request) {
		BillCard billCard= null;
		try {
			String read = request.read();
			IJson json = JsonFactory.create();
			
			AggInvestApplyVO[] resultVOs = null;
			AggInvestRedeemVO[] vos=null;
			AggInvestRedeemVO vo = new AggInvestRedeemVO();
			String pageId= null;
			// info����ת���Ϊservice���õ�QueryScheme
			//IQueryScheme scheme = qservice.convertCondition(operaParam);
			Map map = json.fromJson(read,HashMap.class);
			if (map.get("newvalue") != null) {
				String pk_apply = map.get("newvalue").toString();
				Map page_id = (Map) map.get("form");
				Map pk_org = (Map) map.get("pkorg");
				String pkorg = pk_org.get("value").toString();
				pageId = page_id.get("pageid").toString();
				IIFMApplyQueryService service = ServiceLocator
						.find(IIFMApplyQueryService.class);
				resultVOs = service.queryApplyByPks(new String[] { pk_apply });
				vo = setDefautValue(resultVOs);
			}else{
				InvestRedeemVO parentVO = new InvestRedeemVO();
				OperatorParam operaParam  = json.fromJson(read, OperatorParam.class);
				pageId = operaParam.getPageCode();
				Integer vbillstatus = (Integer) BillStatusEnum.FREE.value();
				Integer billstatus =   (Integer) RedeemStatusEnum.���ύ.value();
				parentVO.setAttributeValue("vbillstatus", vbillstatus);
				parentVO.setAttributeValue("billstatus", billstatus);
				vo.setParentVO(parentVO);
				
			}
			//vos = new AggInvestRedeemVO[]{vo};
			BillCardConvertProcessor processor = new BillCardConvertProcessor();
			billCard = new BillCard();
			if(vo == null ){
				return billCard;
			}
			billCard = processor.convert(pageId, vo);
			//����
			Translator translator = new Translator();
			translator.translate(billCard);
			
		
		} catch (Exception e) {
			ExceptionUtils.wrapException(e);
		}
		return billCard;
	}

	private AggInvestRedeemVO setDefautValue(AggInvestApplyVO[] resultVOs)  throws BusinessException{
		AggInvestRedeemVO aggVO = new AggInvestRedeemVO();
		InvestRedeemVO parentVO = new InvestRedeemVO();
		String pk_group = SessionContext.getInstance().getClientInfo()
				.getPk_group();
		// ���õ���״̬������Ĭ��ֵ���Ƶ���

		Integer vbillstatus = (Integer) BillStatusEnum.FREE.value();
		//��Ҫ��һ��
		
		//String issuebank = resultVOs[0].getParentVO().getIssuebank();
		
		Integer billstatus =   (Integer) RedeemStatusEnum.���ύ.value();
		UFDate billmakedate = new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
	
		String billmaker = SessionContext.getInstance().getClientInfo().getUserid();
		parentVO.setAttributeValue("pk_org", resultVOs[0].getParentVO().getPk_org());
		parentVO.setAttributeValue("vbillstatus", vbillstatus);
		parentVO.setAttributeValue("billstatus", billstatus);
		parentVO.setAttributeValue("billmakedate", billmakedate);
		parentVO.setAttributeValue("billmaker", billmaker);
		parentVO.setAttributeValue("productcode", resultVOs[0].getParentVO().getProductcode());
		parentVO.setAttributeValue("productname", resultVOs[0].getParentVO().getProductname());
		parentVO.setAttributeValue("issuebank", resultVOs[0].getParentVO().getIssuebank());
		parentVO.setAttributeValue("expectedrate", resultVOs[0].getParentVO().getExpectedrate());
		Integer inday=0;
		if(resultVOs[0].getParentVO().getInterestdate().equals("1")){
			 inday = 365;
		}else{
			 inday = 360;
		}
		parentVO.setAttributeValue("interestday",inday);
		parentVO.setAttributeValue("invest", resultVOs[0].getParentVO().getInvest());
		parentVO.setAttributeValue("pk_currtype", resultVOs[0].getParentVO().getPk_currtype());
		parentVO.setAttributeValue("olcrate", resultVOs[0].getParentVO().getOlcrate());
		parentVO.setAttributeValue("olcmoney", resultVOs[0].getParentVO().getOlcmoney());
		
		//����ѡ���Ʒ�ĳ��н��(��ƽ��-����ܶ�)
		UFDouble holdMoney = isApplyNoExists(resultVOs[0].getParentVO().getProductcode(),resultVOs[0].getParentVO().getMoney());
		parentVO.setAttributeValue("holdmoney", holdMoney);
		
		aggVO.setParentVO(parentVO);
		return aggVO;
	}

	
	/**
	 * �ж��������Ƿ����
	 * @param applycode
	 * @param money 
	 * @return
	 * @throws BusinessException
	 */
	private UFDouble isApplyNoExists(String applycode, UFDouble money) throws BusinessException {
	
		IInvestRedeemQueryService serviceRedeem=ServiceLocator.find(IInvestRedeemQueryService.class);
		String condition = "productcode = '" + applycode + "'";
		SuperVO[] fvo = serviceRedeem.querySuperVOByCondition(condition, AggInvestRedeemVO.class);
		UFDouble ALL_DBL = new UFDouble(0.0D);
		 UFDouble holdMoney = UFDouble.ZERO_DBL;
		 UFDouble allmoney = money == null ? UFDouble.ZERO_DBL : money;
		if (fvo != null && fvo.length > 0) {
			for(SuperVO svo:fvo){
				InvestRedeemVO vo =	(InvestRedeemVO) svo;
				if(vo.getRedeemmoney()!=null){
					ALL_DBL=vo.getRedeemmoney().add(ALL_DBL);
				}
			}
			if(money.sub(ALL_DBL) != null){
				holdMoney =allmoney.sub(ALL_DBL);
			}
			return holdMoney;
		} else {
			return	holdMoney =allmoney.sub(ALL_DBL);
		}
	}
	

}
