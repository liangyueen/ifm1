package nc.itf.ifm;

import java.util.HashMap;

import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFDate;

/**  
 * @Description: 精度及金额处理接口
 * @author wangjias 
 * @date 2018-09-03
 * @version V1.0  
 */ 
public interface IIFMPrecisionService {
	
	/**
	 * 根据组织主键获取组织本位币币种
	 * @param pk_org
	 * @return
	 * @throws BusinessException
	 */
	String getOrgStandardCurrtype(String pk_org) throws BusinessException;

	/**
	 * 根据集团主键获取集团本位币币种
	 * @param pk_group
	 * @return
	 * @throws BusinessException
	 */
	String getGroupStandardCurrtype(String pk_group) throws BusinessException;

	/**
	 * 获取全局本位币币种
	 * @return
	 * @throws BusinessException
	 */
	String getGlobalStandardCurrtype() throws BusinessException;

	/**
	 * 【授信调整】
	 * 根据原币金额处理VO中的组织、集团、全局本币金额及数据精度
	 * 
	 * @param vo
	 * 			单独的VO或者聚合VO中的表头或表体VO
	 * @param isRecaculate
	 * 			是否重算
	 * @param field
	 * 			VO中要处理的属性的名称
	 * @param busiDate
	 * 			业务日期
	 * @throws BusinessException
	 */
	ISuperVO processPrecision(ISuperVO vo, boolean isRecaculate, HashMap<String, String> field, UFDate busiDate) throws BusinessException;
}
