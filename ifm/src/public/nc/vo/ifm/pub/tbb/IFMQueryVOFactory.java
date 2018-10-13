package nc.vo.ifm.pub.tbb;

import java.util.Map;

import nc.itf.ifm.pub.tbb.IIFM4TbbConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValidationException;
import nc.vo.tb.obj.NtbParamVO;
/**
 * Ͷ�����-Ԥ�����VO������ 
 *
 */
public class IFMQueryVOFactory {

	private static IFMQueryVOFactory instance = new IFMQueryVOFactory();

	public static IFMQueryVOFactory getInstance() {
		return IFMQueryVOFactory.instance;
	}

	public IFMToTbbQueryVO chgToIfmQueryVO(NtbParamVO paravo,String flag_ReadyOrRun)
			throws ValidationException, BusinessException {
		if (null == paravo)
			return null;
		String billtype = paravo.getBill_type();

		if (billtype == null || "".equals(billtype.trim())) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3620public57","UPP3620public57-000005")/*@res "ȡ����ʽû��ѡ�񵥾�����"*/);
		}

		IFMToTbbQueryVO queryvo = null;


		try {
			if (billtype.equals(IIFM4TbbConst.pk_BillTypeCode_InvestApply)) {
				queryvo = (IFMToTbbQueryVO) Class.forName("nc.vo.ifm.apply.tbb.Apply2TbbQueryVO").newInstance();
			} else if (billtype.equals(IIFM4TbbConst.pk_BillTypeCode_InvestRedeem)) {
				queryvo = (IFMToTbbQueryVO) Class.forName("nc.vo.ifm.redeem.tbb.Redeem2TbbQueryVO").newInstance();
			} else if (billtype.equals(IIFM4TbbConst.pk_BillTypeCode_InvestIncome)) {
				queryvo = (IFMToTbbQueryVO) Class.forName("nc.vo.ifm.income.tbb.Income2TbbQueryVO").newInstance();
			}

			queryvo.setFlag_readyorrun(flag_ReadyOrRun);

		}
		catch (InstantiationException e) {
//			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3632pub_0","03632pub-0078")/*@res "�Ҳ���"*/+getBillTypeName(billtype) + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3632pub_0","03632pub-0079")/*@res "Ԥ��ȡ����"*/);
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3632pub_0","03632pub-1001",null,new String[]{getBillTypeName(billtype)})/*@res "Ԥ��ȡ����"*/);
		}
		catch (IllegalAccessException e) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3632pub_0","03632pub-1001",null,new String[]{getBillTypeName(billtype)})/*@res "Ԥ��ȡ����"*/);
		}
		catch (ClassNotFoundException e) {
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("3632pub_0","03632pub-1001",null,new String[]{getBillTypeName(billtype)})/*@res "Ԥ��ȡ����"*/);
		}




		// ��˾
		queryvo.setPk_org(paravo.getPk_Org());


		// ��������
		queryvo.setDateType(paravo.getDateType());
		// ȡ������
		queryvo.setControlobj(paravo.getData_attr());
		// ��ʼ����
		queryvo.setStartdate(paravo.getBegDate()==null?null:paravo.getBegDate());
		// ��������
		queryvo.setEnddate(paravo.getEndDate()==null?null:paravo.getEndDate());
		// ����δ��Ч�ĵ���
		queryvo.setIncludinguneffective(paravo.isUnInure());
		// �ʽ�����
		queryvo.setData_attr(paravo.getData_attr());
		// ԭ�ұ���
		queryvo.setPk_curr(paravo.getPk_currency());
		// ��������
		queryvo.setCurr_type(paravo.getCurr_type());

		// ����ID
		String[] docpks = paravo.getPkDim();
		// ������ʶ
		String[] busiAttrs = paravo.getBusiAttrs();
		// �����¼�
		Map<String, String[]> mapLowerArrays = paravo.getLowerArrays();

		if (null != busiAttrs && null != docpks
				&& busiAttrs.length == docpks.length) {
			for (int i = 0, iloop = busiAttrs.length; i < iloop; i++) {
				String key = busiAttrs[i];
				if (mapLowerArrays!=null&&mapLowerArrays.containsKey(key)&&mapLowerArrays.get(key)!=null && queryvo.isLevelCtrl(key)) {
					queryvo.setAttributeValue(busiAttrs[i], mapLowerArrays
							.get(key));
				} else if (queryvo.isLevelCtrl(key)) {
					queryvo.setAttributeValue(busiAttrs[i],
							new String[] { docpks[i] });
				} else {
					queryvo.setAttributeValue(busiAttrs[i], docpks[i]);
				}
			}
		}

		// ����
//		queryvo.setPk_curr(paravo.getPk_actcurrency());

		// ת����VO�Ϸ���У��
		queryvo.validate();

		return queryvo;
	}

	private String getBillTypeName(String pk_BillType){
		if(pk_BillType.equals(IIFM4TbbConst.pk_BillTypeCode_InvestApply)){
			return IIFM4TbbConst.pk_BillTypeName_InvestApply;
		}else if (pk_BillType.equals(IIFM4TbbConst.pk_BillTypeCode_InvestIncome)) {
			return IIFM4TbbConst.pk_BillTypeName_InvestIncome;
		}else if (pk_BillType.equals(IIFM4TbbConst.pk_BillTypeCode_InvestRedeem)) {
			return IIFM4TbbConst.pk_BillTypeName_InvestRedeem;
		}else{
			return "";
		}
	}

}