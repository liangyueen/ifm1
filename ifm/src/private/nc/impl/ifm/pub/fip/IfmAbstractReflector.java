package nc.impl.ifm.pub.fip;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.pubitf.fip.external.IBillReflectorService;
import nc.vo.fip.external.FipExtendAggVO;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.tmpub.util.SqlUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public abstract class IfmAbstractReflector <T> implements IBillReflectorService{
	
	@Override
	public Collection<FipExtendAggVO> queryBillByRelations(
			Collection<FipRelationInfoVO> relationvos) throws BusinessException {
		if(relationvos == null || relationvos.size() == 0)  return null;
		
		List<String> list = new ArrayList<String>();
		for (Iterator<FipRelationInfoVO> iterator = relationvos.iterator(); iterator.hasNext();) {
			FipRelationInfoVO relationInfo = iterator.next();
			//ֻ�鵥��������ȵ�
			if(needRelect(relationInfo)) {
				list.add(relationInfo.getRelationID());
			}
		}
		AggregatedValueObject[] aggvos = getBusiBill(list.toArray(new String[list.size()]));
		
		List<FipExtendAggVO> ret = new ArrayList<FipExtendAggVO>(aggvos.length);
		for(AggregatedValueObject aggvo : aggvos){
			FipExtendAggVO vo = new FipExtendAggVO();
			vo.setBillVO(getVoucherBill((T)aggvo.getParentVO()));		
			vo.setRelationID(aggvo.getParentVO().getPrimaryKey());			
			ret.add(vo);			
		}
		return ret;		
	}
	
	/**
	 * �õ��ͻ��ƽ̨��VO��һ�㶼�ǵ���VOֱ���ͣ�
	 * @param billVO
	 * @return
	 */
	public T getVoucherBill(T billVO) {
		return billVO;
	}
	
	public AggregatedValueObject[] getBusiBill(String[] keys) throws BusinessException {
		if(null == keys || keys.length == 0) return null;
		String condition = SqlUtil.buildSqlForIn(getPKFieldName(), keys);
		
		//�޸�����ľۺ�VO��������
		Collection bills = getMDQueryService().queryBillOfVOByCond(getBillClass(), condition, true, false);
		
		return (AggregatedValueObject[]) bills.toArray(new AggregatedValueObject[bills.size()]);		
	}
	
	public abstract String getPKFieldName();

	public abstract boolean needRelect(FipRelationInfoVO relationInfoVO);
	
	public abstract Class<T> getBillClass();
	
	public static IMDPersistenceQueryService getMDQueryService() {
		return NCLocator.getInstance().lookup(IMDPersistenceQueryService.class);
	}

}
