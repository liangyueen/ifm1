package nccloud.web.ifm.redeem.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.vo.ifm.OperatorParam;
import nc.vo.ifm.RedeemStatusEnum;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.pf.BillStatusEnum;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.SessionContext;
import nccloud.web.ifm.common.action.CommonCommitAction;
import nccloud.web.ifm.common.action.CommonSaveAction;
import nccloud.web.ifm.util.RedeemUtil;

public class RedeemSaveSubmitAction extends CommonSaveAction<AggInvestRedeemVO> {
	
	@Override
	protected AggInvestRedeemVO doBusinessSave(AggInvestRedeemVO operaVO) {
		// 赋值创建人等信息
		
		// 获取Service
		
		try {
			this.doBefore(operaVO);
			 operaVO= (AggInvestRedeemVO) callActionScript(TMIFMConst.CONST_ACTION_SAVEBASE,TMIFMConst.CONST_BILLTYPE_REDEEM,new AggInvestRedeemVO[]{operaVO});
			 operaVO = (AggInvestRedeemVO) callActionScript(
					TMIFMConst.CONST_ACTION_SAVE,
					TMIFMConst.CONST_BILLTYPE_REDEEM,
					new AggInvestRedeemVO[] { operaVO });
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("保存单据失败：" + e.getMessage());
		}
		return operaVO;
	}

	
	  @Override protected String getPageCode() {
		  return TMIFMConst.CONST_PAGECODE_CONTRACT_CARD; }
	 
	/**
	 * 保存前操作
	 * 
	 * @param operaVO
	 */
	private void doBefore(AggInvestRedeemVO operaVO) throws BusinessException{
		InvestRedeemVO vo=operaVO.getParentVO();
		//设置审计信息
		Integer vbillstatus = (Integer) BillStatusEnum.COMMIT.value();//提交
		Integer billstatus =   (Integer) RedeemStatusEnum.待审核.value();//待审核
		vo.setAttributeValue("vbillstatus", vbillstatus);
		vo.setAttributeValue("billstatus", billstatus);
		vo.setPk_group(getGroupByOrg(vo.getPk_org()));
		vo.setAttributeValue("creator",SessionContext.getInstance().getClientInfo().getUserid());
		vo.setAttributeValue("creationtime",new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime()) );
		if (vo.getHoldmoeny().sub(vo.getRedeemmoney()).compareTo(UFDouble.ZERO_DBL)<1) {
			throw new BusinessException("持有金额小于赎回金额，您当前的持有金额为："+vo.getHoldmoeny()+"");
		}
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
	
	/**
	 * 获取当前登录用户主键
	 * @return
	 */
	private String getPk_user() {
		return SessionContext.getInstance().getClientInfo().getUserid();
	}
	
	/**
	 * 获取当前登录用户主键
	 * @return
	 */
	private UFDate getBusiDate() {
		return new UFDate(SessionContext.getInstance().getClientInfo().getBizDateTime());
	}
		

}
