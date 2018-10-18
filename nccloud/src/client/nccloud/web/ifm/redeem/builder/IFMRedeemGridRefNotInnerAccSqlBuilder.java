package nccloud.web.ifm.redeem.builder;

import java.util.Map;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nccloud.framework.web.container.ClientInfo;
import nccloud.framework.web.container.SessionContext;
import nccloud.framework.web.processor.IRefSqlBuilder;
import nccloud.framework.web.processor.refgrid.RefQueryInfo;
import nccloud.framework.web.processor.reftree.TreeRefQueryInfo;
import nccloud.framework.web.ui.meta.RefMeta;
import nccloud.pubitf.platform.db.SqlParameterCollection;
import nccloud.web.cmp.ref.CrossRuleDepSqlBuilder;

public class IFMRedeemGridRefNotInnerAccSqlBuilder implements
		IRefSqlBuilder {

	@Override
	public String getExtraSql(RefQueryInfo para, RefMeta meta) {

		if (!meta.getTableName().equals("bd_banktype")) {
			TreeRefQueryInfo newpara = new TreeRefQueryInfo();
			newpara = (TreeRefQueryInfo) para;
			String pk_banktype = newpara.getPid();
			ClientInfo user = SessionContext.getInstance().getClientInfo();
			long bizDateTime = user.getBizDateTime();
			UFDate billdate = new UFDate(bizDateTime);
			String pk_group = user.getPk_group();
			Map<String, String> map = para.getQueryCondition();
			String pk_org = null;
			String pk_currtype = null;
			String isDisableDataShow = null;// falseĬ��ֻ�������õ��˻���true����ʾ�����˻�
			String noConditionOrg = "N";// �Ƿ����Զ���sql����:Y���Լ�sqlƴ�ӣ�N��ƽ̨����Ĭ������
			if (map != null && !map.isEmpty()) {
				pk_org = map.get("pk_org");
				pk_currtype = map.get("pk_currtype");
				//pk_bankdoc = map.get("pk_bankdoc");
				isDisableDataShow = map.get("isDisableDataShow");
				if (map.get("noConditionOrg") != null) {
					noConditionOrg = map.get("noConditionOrg");
				}
			}
			if (pk_org == null || pk_org.equals("")) {
				pk_org = pk_group;
			}
			String condition = "1=1 ";
			/**
			 * ���չ������Լ�ƴ��sql
			 */
			if (noConditionOrg.equals("Y")) {
				// ��ȡ����ͣ����䣬���뵽�Ӳ�ѯ��
				String disableWherePart = null;
				if (isDisableDataShow != null && !isDisableDataShow.equals("")) {
					disableWherePart = getDisableDataWherePart(UFBoolean.valueOf(
							disableWherePart).booleanValue());
				} else {
					disableWherePart = getDisableDataWherePart(false);// Ĭ������
				}
				if (pk_org != null && pk_org.length() > 0) {
					String[] arr = pk_org.split(",");
					// �����֯����
					if (arr.length > 1) {

						String pk_orgArr = "";
						for (int i = 0; i < arr.length; i++) {
							pk_orgArr += "'" + arr[i] + "',";
						}
						pk_orgArr = pk_orgArr.substring(0,
								pk_orgArr.length() - 1);
						condition += "and bd_bankaccsub.pk_bankaccsub in "
								+ " ( select pk_bankaccsub from bd_bankaccuse where pk_org in ("
								+ pk_orgArr + ") and " + disableWherePart + ")"
								+ " and bd_banktype.pk_banktype='"
								+ pk_banktype + "'";
					} else {
						// ������֯����
						if (pk_group.equals(pk_org)) {

							condition += "and bd_bankaccbas.pk_org = '"
									+ pk_org + "'"
									+ " and bd_bankaccbas.pk_banktype='"
									+ pk_banktype + "'";
						} else {
							// ʹ��Ȩ����
							condition += "and bd_bankaccsub.pk_bankaccsub in "
									+ " ( select pk_bankaccsub from bd_bankaccuse where pk_org = '"
									+ pk_org + "' and " + disableWherePart
									+ ")" + " and bd_bankaccbas.pk_banktype='"
									+ pk_banktype + "'";
						}

					}

				}

			}
			/**
			 * ���ֹ���
			 */
			if (pk_currtype != null && pk_currtype.length() > 0) {
				String[] curr_arr = pk_currtype.split(",");
				// ������ֹ���
				if (curr_arr.length > 1) {
					String pk_currArr = "";
					for (int i = 0; i < curr_arr.length; i++) {
						pk_currArr += "'" + curr_arr[i] + "',";
					}
					pk_currArr = pk_currArr.substring(0,
							pk_currArr.length() - 1);
					condition += " and bd_bankaccsub.pk_currtype in ("
							+ pk_currArr + ")";// 20180623�޸ģ����������ӱ���
				} else {
					// ������֯����
					// condition += " and pk_org='" + pk_org + "'";
					condition += " and bd_bankaccsub.pk_currtype='" + pk_currtype
							+ "'";// 20180623�޸ģ����������ӱ���
				}
			}

			
			
			// ���˽������Ĳ���ʾ����---��Ϊ�ڲ��˻���Ҫ��ʾ
			 condition = condition
			 + " and bd_banktype.pk_banktype not in ('0001Z01000000000036R')";
			// �˻�״̬,�Ƿ�����
			condition += " and bd_bankaccbas.accstate not in ('3') and bd_bankaccsub.acctype=0 and bd_bankaccbas.enablestate = 2";
			/**
			 * ���ݿ��ƹ���
			 */
			String extraCrossRuleSql = new CrossRuleDepSqlBuilder().getExtraSql(para, meta);
			if(extraCrossRuleSql!=null && extraCrossRuleSql.length()>0){
				condition +=extraCrossRuleSql;
			}
			return condition;
		} else {
			return null;
		}
	}

	/**
	 * �����˻��Ƿ��������˻�
	 * 
	 * @Package: nccloud.web.cmp.ref
	 * @author zhanghjr
	 * @date 2018-7-31����5:24:31
	 * @Description: TODO
	 * @version 1.0
	 */
	protected String getDisableDataWherePart(boolean isDisableDataShow) {
		if (isDisableDataShow) {
			return " (enablestate = 1 or enablestate = 2 or enablestate = 3)";
		} else {
			return " enablestate = 2";
		}
	}

	@Override
	public SqlParameterCollection getExtraSqlParameter(RefQueryInfo para,
			RefMeta meta) {
		return null;
	}

	@Override
	public String getOrderSql(RefQueryInfo para, RefMeta meta) {
		return null;
	}

	
}
