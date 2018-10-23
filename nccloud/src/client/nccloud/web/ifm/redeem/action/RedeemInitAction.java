package nccloud.web.ifm.redeem.action;


import java.util.HashMap;
import java.util.Map;

import nc.itf.ifm.IIFMApplyQueryService;
import nc.itf.ifm.IInvestRedeemQueryService;
import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.vo.ifm.RedeemStatusEnum;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
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
import nccloud.web.ifm.util.RedeemUtil;
public class RedeemInitAction  implements ICommonAction {

	@Override
	public Object doAction(IRequest request) {
		BillCard billCard= null;
		try {
			String read = request.read();
			IJson json = JsonFactory.create();
			// �жϵ�¼�û��Ƿ���Ĭ����֯
				
			AggInvestApplyVO[] resultVOs = null;
			AggInvestRedeemVO[] vos=null;
			AggInvestRedeemVO vo = new AggInvestRedeemVO();
			String pageId= null;
			
			// info����ת���Ϊservice���õ�QueryScheme
			//IQueryScheme scheme = qservice.convertCondition(operaParam);
			Map map = json.fromJson(read,HashMap.class);
			if (map.get("id") != null) {
				String pk_apply = map.get("id").toString();
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
				String defaultOrgUnit = RedeemUtil.getUserDefaultOrgUnit();
				if (defaultOrgUnit != null) {
					setOrgRelatedValue(vo, defaultOrgUnit);
				}	
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
		
		String pkorg = resultVOs[0].getParentVO().getPk_org();
		
		Integer billstatus =   (Integer) RedeemStatusEnum.���ύ.value();
		UFDate billmakedate = new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
	
		String billmaker = SessionContext.getInstance().getClientInfo().getUserid();
		String pk_olccurr = RedeemUtil.getOrgStandardCurrtype(pkorg);
		parentVO.setAttributeValue("pk_olccurr",pk_olccurr);
		parentVO.setAttributeValue("pk_org", pkorg);
		parentVO.setAttributeValue("pk_group", getGroupByOrg(pkorg));
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
		parentVO.setAttributeValue("investaccount", resultVOs[0].getParentVO().getInvest());
		parentVO.setAttributeValue("pk_currtype", resultVOs[0].getParentVO().getPk_currtype());
		parentVO.setAttributeValue("olcrate", resultVOs[0].getParentVO().getOlcrate());
		parentVO.setAttributeValue("olcmoney", resultVOs[0].getParentVO().getOlcmoney());
		parentVO.setAttributeValue("glcrate", resultVOs[0].getParentVO().getGlcrate());
		parentVO.setAttributeValue("gllcrate", resultVOs[0].getParentVO().getGllcrate());
		if(resultVOs[0].getParentVO().getApplynumber()!=null && resultVOs[0].getParentVO().getApplynumber()>0){
			Integer lastNum = isApplyNumNoExists(parentVO,resultVOs[0].getParentVO().getProductcode(),resultVOs[0].getParentVO().getApplynumber());
			parentVO.setAttributeValue("holdnumber", lastNum);		
		}else{
			//����ѡ���Ʒ�ĳ��н��(��ƽ��-����ܶ�)
			UFDouble holdMoney = isApplyMoneyNoExists(parentVO,resultVOs[0].getParentVO().getProductcode(),resultVOs[0].getParentVO().getMoney());
			parentVO.setAttributeValue("holdmoney", holdMoney);
		}
		parentVO.setAttributeValue("redeemdate", getBusiDate());
		parentVO.setAttributeValue("incomedate", getBusiDate());
		parentVO.setAttributeValue("pk_srcbill", resultVOs[0].getParentVO().getPk_apply());
		parentVO.setAttributeValue("pk_srcbilltype", resultVOs[0].getParentVO().getPk_billtypeid());
		parentVO.setAttributeValue("srcbilltypecode", resultVOs[0].getParentVO().getPk_billtypecode());
		parentVO.setAttributeValue("srcbillno", resultVOs[0].getParentVO().getVbillno());
		parentVO.setAttributeValue("gatheringaccount", resultVOs[0].getParentVO().getSettleaccount());
		parentVO.setAttributeValue("enddate", resultVOs[0].getParentVO().getEnddate());
		aggVO.setParentVO(parentVO);
		return aggVO;
	}
	/**
	 * ��ѯ������֯���ڼ���
	 * @param pk_org
	 * @return
	 */
	public static String getGroupByOrg(String pk_org) throws BusinessException {
		IOrgUnitPubService_C orgUnitQryService = ServiceLocator.find(IOrgUnitPubService_C.class);
		String pk_group = null;
		OrgVO[] orgVOs = orgUnitQryService.getOrgs(new String[] { pk_org }, new String[] { TMIFMConst.FIELD_PK_GROUP });
		if(orgVOs == null || orgVOs.length <= 0){
			ExceptionUtils.wrapBusinessException("��ȡ������֯��Ӧ�ļ���ʧ�ܡ�");
		}
		pk_group = (String) orgVOs[0].getAttributeValue(TMIFMConst.FIELD_PK_GROUP);
		return pk_group;
	}

	/**
	 * ��֤��ؽ���Ƿ���ڳ��н����س��н��
	 * @param parentVO
	 * @param applycode
	 * @param money
	 * @return
	 * @throws BusinessException
	 */

	private UFDouble isApplyMoneyNoExists(InvestRedeemVO parentVO,String applycode, UFDouble money) throws BusinessException {
	
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
				parentVO.setAttributeValue("lastdate",vo.getRedeemdate());
			}
			if(money.sub(ALL_DBL) != null){
				holdMoney =allmoney.sub(ALL_DBL);
			}
			return holdMoney;
		} else {
			return	holdMoney =allmoney.sub(ALL_DBL);
		}
	}
	/**
	 * ��֤��ط����Ƿ���ڳ��з��������س��з���
	 * @param parentVO
	 * @param applycode
	 * @param applyNum
	 * @return
	 * @throws BusinessException
	 */
	private Integer isApplyNumNoExists(InvestRedeemVO parentVO,String applycode,Integer applyNum) throws BusinessException {
		
		IInvestRedeemQueryService serviceRedeem=ServiceLocator.find(IInvestRedeemQueryService.class);
		String condition = "productcode = '" + applycode + "'";
		SuperVO[] fvo = serviceRedeem.querySuperVOByCondition(condition, AggInvestRedeemVO.class);
		Integer lastNum =0;
		Integer redeemSum=0;
		if (fvo != null && fvo.length > 0) {
			for(SuperVO svo:fvo){
				InvestRedeemVO vo =	(InvestRedeemVO) svo;
				if(vo.getRedeemnumber()!=null){
					redeemSum=redeemSum+vo.getRedeemnumber();
				}
				
				parentVO.setAttributeValue("lastdate",vo.getRedeemdate());
			}
			lastNum = applyNum-redeemSum;
			
			return lastNum;
		}else{
			return applyNum;
		}
		
	}
	/**
	 * ����Ĭ����֯����ֶ�
	 * 
	 * @param card
	 * @param uiState
	 * @throws BusinessException
	 */
	private void setOrgRelatedValue(AggInvestRedeemVO vo, String pk_org)
			throws BusinessException {
		InvestRedeemVO headVO = vo.getParentVO();
		headVO.setPk_org(pk_org);
		headVO.setPk_group(RedeemUtil.getGroupByOrg(pk_org));
		headVO.setPk_currtype(RedeemUtil.getOrgStandardCurrtype(pk_org));
		headVO = (InvestRedeemVO) RedeemUtil.processPrecision(headVO, true,
				getBusiDate());
		vo.setParentVO(headVO);
	}
	
	/**
	 * ��ȡ��ǰҵ��ʱ��
	 * 
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo()
				.getBizDateTime());
	}

}
