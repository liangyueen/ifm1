package nc.bs.ifm.pub.tbb;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import nc.itf.ifm.pub.tbb.IIFM4TbbConst;
import nc.itf.tb.control.IAccessableBusiVO;
import nc.itf.tb.control.IBusiSysExecDataProvider;
import nc.vo.ifm.pub.tbb.IFMQueryVOFactory;
import nc.vo.ifm.pub.tbb.IFMToTbbQueryVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tb.obj.NtbParamVO;
import nc.vo.tmpub.util.StringUtil;

/**
 * 投资理财-预算取数接口实现类
 * 入口类
 */
public class IFMBillExecDataProvider implements IBusiSysExecDataProvider {

	public IFMBillExecDataProvider() {
	}

	@Override
	public int getCtlPoint(String pk_corp) throws RemoteException {
		return 1;
	}

	@Override
	public IAccessableBusiVO[] getCvtProvider(IAccessableBusiVO[] runvos) throws RemoteException {
		return null;
	}

	@Override
	public UFDouble[] getExecData(NtbParamVO param) throws BusinessException {
		return getExecDataBatch(new NtbParamVO[]{param})[0];
	}

	@Override
	public UFDouble[][] getExecDataBatch(NtbParamVO[] ntbparas) throws BusinessException {
		//参数合法性校验
		if (null == ntbparas || ntbparas.length == 0) {
			return null;
		}
		//{原币，组织本币，集团本币，全局本币}
		UFDouble[][] retValue = new UFDouble[ntbparas.length][4];

		try {
			IFMToTbbDMO querydmo = new IFMToTbbDMO();
			IFMToTbbQueryVO queryvo = null;
			for (int i = 0, iloop = ntbparas.length; i < iloop; i++) {
				//转换成查询VO
				queryvo = IFMQueryVOFactory.getInstance()
						.chgToIfmQueryVO(ntbparas[i],IIFM4TbbConst.Flag_Ufind);
				//查询汇总记录
				UFDouble[] returnvalue = querydmo.queryIfmData(queryvo);	
				//全局本币
				retValue[i][0] = StringUtil.toUFDoubleWithNull2O(returnvalue[3]);
				
				//集团本币
				retValue[i][1] = StringUtil.toUFDoubleWithNull2O(returnvalue[2]);
				
				//组织本币
				retValue[i][2] = StringUtil.toUFDoubleWithNull2O(returnvalue[1]);
								
				
				// 原币
				retValue[i][3] = StringUtil.toUFDoubleWithNull2O(returnvalue[0]);
				
				
			}
		} catch (Exception ex) {
			//日志异常
			nc.bs.logging.Logger.error(ex);
			//按规范抛出异常
			throw new BusinessException(ex.getMessage());
		}

		return retValue;
	}

	@Override
	public UFDouble[] getReadyData(NtbParamVO param) throws BusinessException {
		return getReadyDataBatch(new NtbParamVO[]{param})[0];
	}

	@Override
	public UFDouble[] getPointData(NtbParamVO param) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UFDouble[][] getReadyDataBatch(NtbParamVO[] ntbparas)
			throws BusinessException {
		//参数合法性校验
		if (null == ntbparas || ntbparas.length == 0) {
			return null;
		}
		//{原币，组织本币，集团本币，全局本币}
		UFDouble[][] retValue = new UFDouble[ntbparas.length][4];

		try {
			IFMToTbbDMO querydmo = new IFMToTbbDMO();
			IFMToTbbQueryVO queryvo = null;
			for (int i = 0, iloop = ntbparas.length; i < iloop; i++) {
				//转换成查询VO
				queryvo = IFMQueryVOFactory.getInstance()
						.chgToIfmQueryVO(ntbparas[i],IIFM4TbbConst.Flag_Prefind);
				//查询汇总记录
				UFDouble[] returnvalue = querydmo.queryIfmData(queryvo);	
				//全局本币
				retValue[i][0] = StringUtil.toUFDoubleWithNull2O(returnvalue[3]);
				
				//集团本币
				retValue[i][1] = StringUtil.toUFDoubleWithNull2O(returnvalue[2]);
				
				//组织本币
				retValue[i][2] = StringUtil.toUFDoubleWithNull2O(returnvalue[1]);
								
				
				// 原币
				retValue[i][3] = StringUtil.toUFDoubleWithNull2O(returnvalue[0]);
				

				
			}
		} catch (Exception ex) {
			//日志异常
			nc.bs.logging.Logger.error(ex);
			//按规范抛出异常
			throw new BusinessException(ex.getMessage());
		}

		return retValue;
	}

	@Override
	public UFDouble[][] getPointDataBatch(NtbParamVO[] param)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createBillType(NtbParamVO[] param) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<NtbParamVO, Map<String, UFDouble[]>> getExecDataGroupBatch(
			String groupDocType, Map<NtbParamVO, List<String>> groupParaVOs,Map<String, String[]> childGroupDocs)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<NtbParamVO, Map<String, UFDouble[]>> getReadyDataGroupBatch(
			String groupDocType, Map<NtbParamVO, List<String>> groupParaVOs,Map<String, String[]> childGroupDocs)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
