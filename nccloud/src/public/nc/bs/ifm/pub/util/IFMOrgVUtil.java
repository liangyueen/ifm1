package nc.bs.ifm.pub.util;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.org.IOrgUnitPubService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.tmpub.util.StringUtil;

public class IFMOrgVUtil {
	public static void setOrgv(SuperVO vo ,String pkOrgField,String pkOrgVField,UFDate busiDate)throws BusinessException{
		String pk_org = (String) vo.getAttributeValue(pkOrgField);

		if(StringUtil.isNull(pk_org)){
			return;
		}

		if(busiDate == null){
			busiDate = new UFDate();
		}

		IOrgUnitPubService orgUnitSrv = NCLocator.getInstance().lookup(IOrgUnitPubService.class);
		Map orgVersionMap = orgUnitSrv.getNewVIDSByOrgIDSAndDate(new String[]{pk_org}, busiDate);

		String pk_org_v = (String)orgVersionMap.get(pk_org);

		if(pk_org_v == null){
			throw new BusinessException("查询组织版本错误，无可用版本");
		}
		vo.setAttributeValue(pkOrgVField, pk_org_v);
	}
}