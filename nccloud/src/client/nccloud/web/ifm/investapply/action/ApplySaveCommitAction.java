package nccloud.web.ifm.investapply.action;


import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.vo.ifm.apply.AggInvestApplyVO;
import nc.vo.ifm.apply.InvestApplyVO;
import nc.vo.imf.constants.TMIMFConst;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.SessionContext;
import nccloud.web.tmifm.common.action.CommonSaveAction;


/**
 * 担保债务保存提交
 * @author wangzc
 *
 */
public class ApplySaveCommitAction extends CommonSaveAction<AggInvestApplyVO> {
	
	ApplyCommitAction ac = new ApplyCommitAction();
	
	
	@Override
	protected AggInvestApplyVO doBusinessSave(AggInvestApplyVO operaVO) {
		try {
			this.doBefore(operaVO);
		} catch (BusinessException e1) {
			e1.printStackTrace();
		}
		try {
			// 调用动作脚本，执行保存
			operaVO = (AggInvestApplyVO) callActionScript(
					TMIMFConst.CONST_ACTION_SAVEBASE,
					TMIMFConst.CONST_BILLTYPE_APPLY,
					new AggInvestApplyVO[] { operaVO });
			
			// 调用动作脚本，执行提交
			operaVO = (AggInvestApplyVO) callActionScript(
					TMIMFConst.CONST_ACTION_SAVE,
					TMIMFConst.CONST_BILLTYPE_APPLY,
					new AggInvestApplyVO[] { operaVO });
		} catch (BusinessException e) {
			ExceptionUtils.wrapBusinessException("保存提交失败：" + e.getMessage());
		}
			
		return operaVO;
	}
	
	
	
	

	@Override
	protected String getPageCode() {
		return TMIMFConst.CONST_PAGECODE_ADJUST_CARD;
	}

	/**
	 * 保存前：操作
	 * 
	 * @param operaVO
	 * @throws BusinessException 
	 */
	private void doBefore(AggInvestApplyVO operaVO) throws BusinessException {

		InvestApplyVO headVO = operaVO.getParentVO();
		if (StringUtil.isEmptyWithTrim(headVO.getPrimaryKey())) {
			// 设置审计信息
			headVO.setAttributeValue("creator", SessionContext.getInstance()
					.getClientInfo().getUserid());
			headVO.setAttributeValue("creationtime", new UFDate(SessionContext
					.getInstance().getClientInfo().getBizDateTime()));
			headVO.setAttributeValue("version", 1);
			headVO.setAttributeValue("versiontime", new UFDate(SessionContext
					.getInstance().getClientInfo().getBizDateTime()));
			headVO.setPk_group(getGroupByOrg(headVO.getPk_org()));
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
		OrgVO[] orgVOs = orgUnitQryService.getOrgs(new String[] { pk_org }, new String[] { TMIMFConst.FIELD_PK_GROUP });
		if(orgVOs == null || orgVOs.length <= 0){
			ExceptionUtils.wrapBusinessException("获取财务组织对应的集团失败。");
		}
		pk_group = (String) orgVOs[0].getAttributeValue(TMIMFConst.FIELD_PK_GROUP);
		return pk_group;
	}
	
}

//	@Override
//	protected AggInvestApplyVO doBusinessSave(AggInvestApplyVO operaVO) {
//		// 赋值创建人等信息
//		this.doBefore(operaVO);
//		// 获取Service
//		IApplyMaintain iApplyMaintain = ServiceLocator.find(IApplyMaintain.class);
//		IIFMApplyQueryService service = ServiceLocator.find(IIFMApplyQueryService.class);
//		AggInvestApplyVO[] saveVOs = new AggInvestApplyVO[] { operaVO };
//		try {
//			// 如果vo中没有主键, 就新增保存; 否则就是修改保存
//			if (operaVO.getParent().getPrimaryKey() == null) {
//				saveVOs = iApplyMaintain.insert(saveVOs, saveVOs);
//			} else {
//				// 修改保存前要把数据库中的原始信息查出来
//				AggInvestApplyVO[] vos = service.getAggVOsByPKs(saveVOs[0]
//						.getPrimaryKey());
//				// update方法要保证两个参数vo的内容不同, 否则不执行update语句
//				saveVOs = iApplyMaintain.update(saveVOs, vos);
//			}
//		} catch (BusinessException e) {
//			ExceptionUtils.wrapBusinessException("保存单据失败：" + e.getMessage());
//		}
//		// 提交
//		try {
//			saveVOs = iApplyMaintain.commit(saveVOs, saveVOs);
//		} catch (BusinessException e) {
//			ExceptionUtils.wrapBusinessException("提交单据失败：" + e.getMessage());
//		}
//		return saveVOs[0];
//	}
//
//	@Override
//	protected String getPageCode() {
//		//return "36620GP_CARD";
//		return TMIMFConst.CONST_PAGECODE_ADJUST_CARD;
//	}
//
//	/**
//	 * 保存前操作
//	 * 
//	 * @param operaVO
//	 */
//	private void doBefore(AggInvestApplyVO operaVO) {
//		// 从SessionContext中获取客户端信息
//		ClientInfo clientInfo = SessionContext.getInstance().getClientInfo();
//		InvestApplyVO head = ((InvestApplyVO) operaVO.getParent());
//		// 设置一些初始化信息
//		if (head.getPk_apply() == null) {
//			head.setCreator(clientInfo.getUserid());
//			head.setPk_group(clientInfo.getPk_group());
//			head.setPk_billtypecode(TMIMFConst.CONST_BILLTYPE_APPLY);
//			//head.setBusistatus(-1);
//			head.setVbillstatus(-1);
//		} else {
//			head.setModifier(clientInfo.getUserid()); 
//		}
//	}



