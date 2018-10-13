package nc.bs.ifm.pub.tbb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.ifm.pub.util.IFMQueryModulesUtil;
import nc.bs.logging.Logger;
import nc.itf.ifm.pub.tbb.IIFM4TbbConst;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.ms.tb.control.NtbParadimNccloudCache;
import nc.vo.ifm.pub.tbb.IFMQueryVOFactory;
import nc.vo.ifm.pub.tbb.IFMTbbBusiVOFactory;
import nc.vo.ifm.pub.tbb.IFMToTbbAccessableBusiVO;
import nc.vo.ifm.pub.tbb.IFMToTbbQueryVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.sf.delivery.DeliveryVO;
import nc.vo.tb.obj.NtbParamVO;
import nc.vo.tmpub.util.ArrayUtil;
import nc.vo.tmpub.util.SqlUtil;
import nc.vo.tmpub.util.StringUtil;
import nccloud.base.exception.ExceptionUtils;
import nccloud.pubitf.ifm.pub.IIFMPubLinkPlanService4NCC;
import nccloud.pubitf.tbb.ctrl.ICtrlNccloud;

import com.alibaba.fastjson.JSONObject;

/**
 * ����Ԥ�����ʵ����
 * 
 */
public class IFMPubLinkPlanService4NCCImpl implements IIFMPubLinkPlanService4NCC {

	/**
	 * ����Ԥ��
	 * 
	 * @param pk
	 *            ����
	 * @param className
	 *            �ۺ�vo����
	 * @param pk_billtypecode
	 *            ��������
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public JSONObject linkNtbPlan(String pk, String className,
			String pk_billtypecode) throws BusinessException {
		AggregatedValueObject aggVO = null;
		try {
			aggVO = getAggVOByPk(className, pk);
		} catch (ClassNotFoundException e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage());
		}
		if (aggVO == null) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("3632pub_0", "03632pub-0005")/*
																			 * @res
																			 * "δѡ�е��ݣ��޷�����"
																			 */);
		}
		if (ArrayUtil.isNull(aggVO.getChildrenVO())
				&& StringUtil.isNull(aggVO.getParentVO().getAttributeValue(
						DeliveryVO.VBILLNO))) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("3632pub_0", "03632pub-0006")/*
																			 * @res
																			 * "����������������"
																			 */);
		}
		if (!IFMQueryModulesUtil.isTBBEnable(StringUtil.toString(aggVO
				.getParentVO().getAttributeValue(DeliveryVO.PK_GROUP)))) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("3632pub_0", "03632pub-0007")/*
																			 * @res
																			 * "Ԥ��ƻ�ƽ̨δ���ã��޷�����"
																			 */);
		}
		List<IFMToTbbAccessableBusiVO> busiVOList = new ArrayList<IFMToTbbAccessableBusiVO>();
		if (ArrayUtil.isNull(aggVO.getChildrenVO())) {
			IFMToTbbAccessableBusiVO ifmBillBusiVO = IFMTbbBusiVOFactory
					.getInstance().chgToIFMBusiVO(pk_billtypecode);
			ifmBillBusiVO.setParentVO(aggVO.getParentVO());

			busiVOList.add(ifmBillBusiVO);
			IFMToTbbAccessableBusiVO vo2 = ifmBillBusiVO.clone();
			vo2.setDataType(IIFM4TbbConst.Flag_Ufind);
			busiVOList.add(vo2);

		} else {
			for (int i = 0; i < aggVO.getChildrenVO().length; i++) {
				IFMToTbbAccessableBusiVO ifmBillBusiVO = IFMTbbBusiVOFactory
						.getInstance().chgToIFMBusiVO(pk_billtypecode);
				ifmBillBusiVO.setParentVO(aggVO.getParentVO());
				ifmBillBusiVO.setChildVO(aggVO.getChildrenVO()[i]);

				busiVOList.add(ifmBillBusiVO);
				IFMToTbbAccessableBusiVO vo2 = ifmBillBusiVO.clone();
				vo2.setDataType(IIFM4TbbConst.Flag_Ufind);
				busiVOList.add(vo2);
			}
		}

		if (busiVOList.size() == 0) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("3632pub_0", "03632pub-0005")/*
																			 * @res
																			 * "δѡ�е��ݣ��޷�����"
																			 */);
		}

		ICtrlNccloud ICtrlNccloud = NCLocator.getInstance().lookup(
				ICtrlNccloud.class);
		JSONObject jSONObject = ICtrlNccloud.getLinkQueryMessJson(busiVOList
				.toArray(new IFMToTbbAccessableBusiVO[busiVOList.size()]));
		//{"error":"û��ƥ���Ԥ��"}
		if(jSONObject.get("hint") != null){
			ExceptionUtils.wrapBusinessException(jSONObject.get("hint").toString());
		}
		return jSONObject;
	}

	/**
	 * ����������ѯvo
	 * 
	 * @param className
	 * @param pk
	 * @return
	 * @throws ClassNotFoundException
	 * @throws MetaDataException
	 */
	private AggregatedValueObject getAggVOByPk(String className, String pk)
			throws ClassNotFoundException, MetaDataException {
		Object aggVO = getMDQueryService().queryBillOfVOByPK(
				Class.forName(className), pk, false);
		return (AggregatedValueObject) aggVO;
	}

	/**
	 * Ԥ�����鵥��
	 * 
	 * @param pk
	 *            ����
	 * @param className
	 *            �ۺ�vo���� ����: AggDeliveryVO
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, AggregatedValueObject> doNtbQueryBill(String pk,
			String className) throws BusinessException {
		if (pk != null) {
			NtbParamVO ntbParamVO = NtbParadimNccloudCache.getNewInstance()
					.getNtbParamVos(pk);

			IFMToTbbQueryVO queryvo = null;
			queryvo = IFMQueryVOFactory.getInstance().chgToIfmQueryVO(ntbParamVO,
					ntbParamVO.getMethodCode());
			if (null == queryvo) {
				return null;
			}
			// ѡ���ֶ�
			StringBuilder sqlbuilder = new StringBuilder("  ");

			String fromTable = "";
			// ���
			String headtable = queryvo.getHeadTableName();
			fromTable = headtable + " " + headtable;
			String bodytable = queryvo.getBodyTableName();

			// �Ƿ��б���
			boolean isHaveBody = StringUtil.isNotNull(bodytable);

			sqlbuilder.append(headtable).append(".dr = 0 ");

			if (isHaveBody) {
				sqlbuilder.append(" and ").append(bodytable).append(".dr = 0 ");
				sqlbuilder.append(" and ").append(queryvo.getJoinPart());
				fromTable += ", " + bodytable + " " + bodytable;
			}

			// ����
			appendDateSql(sqlbuilder, queryvo);

			appendUfindOrPrefindSql(sqlbuilder, queryvo);

			// ����
			if (queryvo.getCurr_type() == 3) {
				appendSql(sqlbuilder, queryvo.getCurrKeyName(),
						queryvo.getPk_curr());
			}
			//
			appendSql(sqlbuilder, queryvo.getOrgKeyName(), queryvo.getPk_org());
			if (StringUtil.isNotNull(queryvo.getBodyDisUsePart())) {
				sqlbuilder.append(" and " + queryvo.getBodyDisUsePart());
			}

			// ȡ��ÿ�����ݵĵ�����Ŀ����������Ŀƴ��SQL
			String[] dataitemnames = queryvo.getDataItemNames();

			if (dataitemnames != null && dataitemnames.length > 0) {
				for (String attr : dataitemnames) {
					if (StringUtil.isNotNull(attr)) {
						Object obj = queryvo.getAttributeValue(attr);
						if (obj instanceof String[]) {
							appendSql(sqlbuilder,
									queryvo.changeAttrToBillItemName(attr),
									(String[]) obj);
						} else {
							appendSql(sqlbuilder,
									queryvo.changeAttrToBillItemName(attr),
									(String) obj);
						}

					}
				}
			}

			String sql = sqlbuilder.toString();
			try {
				Class cls = null;
				cls = Class.forName(className);
				Collection<AggregatedValueObject> collection = getMDQueryService()
						.queryBusiVOByFromAndCond(cls, fromTable, sql, false,
								null);
				HashMap<String, AggregatedValueObject> resultSet = new HashMap<String, AggregatedValueObject>();
				Iterator<AggregatedValueObject> iterator = collection
						.iterator();
				while (iterator.hasNext()) {
					AggregatedValueObject aggVO = iterator.next();
					resultSet.put(aggVO.getParentVO().getPrimaryKey(), aggVO);
				}
				return resultSet;
			} catch (ClassNotFoundException e) {
				Logger.error(e.getMessage(), e);
				throw new BusinessException(e.getMessage());
			}
		}
		return null;
	}

	/**
	 * ����Ԫ���ݲ�ѯ�������
	 */
	private IMDPersistenceQueryService getMDQueryService() {
		return MDPersistenceService.lookupPersistenceQueryService();
	}

	/**
	 * ƴ�����ڹ�������
	 * 
	 * @param sqlbuilder
	 * @param queryvo
	 */
	private void appendDateSql(StringBuilder sqlbuilder, IFMToTbbQueryVO queryvo) {
		String startdate = queryvo.getStartdate();
		if (StringUtil.isNotNull(startdate)) {
			sqlbuilder.append(" and ").append(queryvo.getDateKeyName())
					.append(" >= '").append(startdate).append("' ");
		}
		String enddate = queryvo.getEnddate();
		if (StringUtil.isNotNull(enddate)) {
			sqlbuilder.append(" and ").append(queryvo.getDateKeyName())
					.append(" <= '").append(enddate).append("'");
		}
	}

	private void appendUfindOrPrefindSql(StringBuilder sqlbuilder,
			IFMToTbbQueryVO queryvo) {
		if (StringUtil.isNotNull(queryvo.getUfindOrPrefindPart())) {
			sqlbuilder.append(" and ").append(queryvo.getUfindOrPrefindPart());
		}
	}

	private void appendSql(StringBuilder sqlbuilder, String name, String value) {
		if (StringUtil.isNotNull(value) && StringUtil.isNotNull(name)) {
			sqlbuilder.append(" and ").append(name).append(" = '")
					.append(value).append("'");
		}
	}

	private void appendSql(StringBuilder sqlbuilder, String name, String[] value) {
		if (null == value || value.length == 0) {
			return;
		} else if (StringUtil.toString(name).length() == 0) {
			return;
		} else if (value.length == 0) {
			sqlbuilder.append(" and ").append(name).append(" = '")
					.append(value).append("'");
		} else {
			sqlbuilder.append(" and ").append(
					SqlUtil.buildSqlForIn(name, value));
		}
	}

}
