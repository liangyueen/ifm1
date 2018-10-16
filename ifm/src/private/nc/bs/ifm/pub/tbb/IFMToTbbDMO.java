package nc.bs.ifm.pub.tbb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import nc.bs.pub.DataManageObject;
import nc.vo.ifm.pub.tbb.IFMToTbbQueryVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tmpub.util.SqlUtil;
import nc.vo.tmpub.util.StringUtil;
/**
 * Ͷ�����-ȡ���ӿ�ʵ�ֵĺ�̨��ѯ��,��ѯSQL�����
 * @author liglt
 * 2019-09-26
 */
public class IFMToTbbDMO extends DataManageObject {

	public IFMToTbbDMO() throws NamingException {
		super();
	}
	//{ԭ�ң���֯���ң����ű��ң�ȫ�ֱ���}
	public UFDouble[] queryIfmData(IFMToTbbQueryVO queryvo) throws SQLException{
		if(null == queryvo)
			return null;
		
		//ѡ���ֶ�
		StringBuilder sqlbuilder = new StringBuilder("select ");
		String[] selectkeys = queryvo.getToSelecltKeyName();
		int ilength = selectkeys.length;
		for(int i=0;i<ilength;i++){
			sqlbuilder.append(selectkeys[i]);
			if(i <ilength-1){
				sqlbuilder.append(",");
			}
		}
		
		//���
		String headtable = queryvo.getHeadTableName();
		String bodytable = queryvo.getBodyTableName();

		sqlbuilder.append(" from ").append(headtable);

		//�Ƿ��б���
		boolean isHaveBody = (bodytable != null && !"".equals(bodytable.trim())) ? true : false;
		if (isHaveBody) {
			sqlbuilder.append(" inner join ").append(bodytable);
			sqlbuilder.append(" on ").append(queryvo.getJoinPart());
		}
		
		sqlbuilder.append(" where isnull(").append(headtable).append(".dr, 0) = 0 ");
		
		if (isHaveBody) {
			sqlbuilder.append(" and isnull(").append(bodytable).append(".dr, 0) = 0 ");
		}
		
		//��������
		if(queryvo.getBilltypecode()!= null){
			sqlbuilder.append(" and ").append(headtable).append(".pk_billtypecode = '"+queryvo.getBilltypecode()+"'");
		}
		//���ݵĹ̶���������
//		if (queryvo.getFixCondition() != null){
//			sqlbuilder.append(queryvo.getFixCondition());
//		}
		
        //����
		appendDateSql(sqlbuilder, queryvo);
		//���Ƿ����δ��Ч���ݶ�Ӧ��SQL������ƴ��
		appendIncludingUneffectivePartSql(sqlbuilder, queryvo);
		//Ԥռ������ִ����
		appendUfindOrPrefindSql(sqlbuilder, queryvo);
		//���� 
		//Ԥ�㷵�صı������� 0��ȫ�ֱ��ң�1�����ű��ң�2����֯����, 3: ԭ��
		if(queryvo.getCurr_type() == 3){
			appendSql(sqlbuilder, queryvo.getCurrKeyName(), queryvo.getPk_curr());
		}
		//
		appendSql(sqlbuilder, queryvo.getOrgKeyName(), queryvo.getPk_org());
		if(StringUtil.isNotNull(queryvo.getBodyDisUsePart())){
			sqlbuilder.append(" and " + queryvo.getBodyDisUsePart());
		}
		//ȡ��ÿ�����ݵĵ�����Ŀ����������Ŀƴ��SQL
		String[] dataitemnames = queryvo.getDataItemNames();
		if (dataitemnames != null && dataitemnames.length > 0) {
			for (String attr : dataitemnames) {
				
				if (StringUtil.isNotNull(attr)) {
					Object obj = queryvo.getAttributeValue(attr);
					if(obj instanceof String[]){
						appendSql(sqlbuilder , queryvo.changeAttrToBillItemName(attr), (String[])obj );
					}else {
						appendSql(sqlbuilder , queryvo.changeAttrToBillItemName(attr), (String)obj );
					}
					
				}
			}
		}
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		UFDouble[] result = new UFDouble[ilength];
		try {
			con = getConnection();
			stmt = con.prepareStatement(sqlbuilder.toString());
			rs = stmt.executeQuery();
			if (rs.next()) {
				for(int i = 0; i < ilength; i++) {
					//sum��
					Object value = rs.getObject(i+1);
					
					result[i] = value == null ? UFDouble.ZERO_DBL : new UFDouble(value.toString());
				}
			}
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
			}
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
			}
		}
		return result;
	}

	private void appendDateSql(StringBuilder sqlbuilder,IFMToTbbQueryVO queryvo){
		String startdate = queryvo.getStartdate();
		if(!StringUtil.isNull(startdate)){
			sqlbuilder.append(" and ").append(queryvo.getDateKeyName()).append(" >= '").append(startdate).append("' ");
		}
		String enddate = queryvo.getEnddate();
		if(!StringUtil.isNull(enddate)){
			sqlbuilder.append(" and ").append(queryvo.getDateKeyName()).append(" <= '").append(enddate).append("'");
		}
	}
	
	/**
	 * ���Ƿ����δ��Ч���ݶ�Ӧ��SQL������ƴ��
	 * TODO ����˵��
	 * @param sqlbuilder
	 * @param queryvo
	 * @version 1.0 2010-10-19
	 * @since NC5.7
	 */
	private void appendIncludingUneffectivePartSql(StringBuilder sqlbuilder, IFMToTbbQueryVO queryvo) {		
		if (StringUtil.isNotNull(queryvo.getIncludingUneffectivePart())) {
			sqlbuilder.append(" and ").append(queryvo.getIncludingUneffectivePart());
		}
	}
	
	private void appendUfindOrPrefindSql(StringBuilder sqlbuilder, IFMToTbbQueryVO queryvo){
		if (StringUtil.isNotNull(queryvo.getUfindOrPrefindPart())) {
			sqlbuilder.append(" and ").append(queryvo.getUfindOrPrefindPart());
		}
	}
	/**
	 * ƴ��sql�������
	 * @param sqlbuilder
	 * @param name
	 * @param value
	 */
	private void appendSql(StringBuilder sqlbuilder, String name, String[] value){
		if(StringUtil.isNull(name)){
			return ;
		}
		else if (null == value  || value.length == 0) {
			return ;
		} 
		else if (value.length == 1) {
			appendSql(sqlbuilder, name, value[0]);
		}
		else {			
			sqlbuilder.append(" and ").append(SqlUtil.buildSqlForIn(name, value));
		}
	}
	
	private void appendSql(StringBuilder sqlbuilder, String name, String value) {
		if (StringUtil.isNotNull(value) && StringUtil.isNotNull(name)) {
			sqlbuilder.append(" and ").append(name).append(" = '").append(value).append("'");
		}
	}
}

