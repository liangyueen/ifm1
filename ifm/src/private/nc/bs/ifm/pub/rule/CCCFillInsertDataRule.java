package nc.bs.ifm.pub.rule;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pub.rule.FillInsertDataRule;
import nc.pubitf.org.IOrgUnitPubService;
import nc.vo.ifm.bankprotocol.ProtocolDetailVO;
import nc.vo.ifm.bankprotocol.ProtocolVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.util.AuditInfoUtils;

public class CCCFillInsertDataRule extends FillInsertDataRule {

	@Override
	public void process(Object[] bills) {
		// ���Ŷ������Ĭ��ֵ
		this.setProtocolMoney((IBill[]) bills);
		// ���������Ϣ
		AuditInfoUtils.setAddAuditInfo((IBill[]) bills);
		// �ѱ�ͷ��֯ͬ��������
		this.fillItemOrgs((IBill[]) bills);
	}

	/**
	 * ���Ŷ������Ĭ��ֵ
	 * 
	 * @param bills
	 */
	private void setProtocolMoney(IBill[] bills) {
		for (IBill bill : bills) {
			ProtocolVO head = (ProtocolVO) bill.getParent();
			// ��֯���Ҷ��
			if (head.getOlccdtlnamt() == null) {
				head.setOlccdtlnamt(UFDouble.ZERO_DBL);
			}
			// ���ű��Ҷ��
			if (head.getGlccdtlnamt() == null) {
				head.setGlccdtlnamt(UFDouble.ZERO_DBL);
			}
			// ȫ�ֱ��Ҷ��
			if (head.getGllccdtlnamt() == null) {
				head.setGllccdtlnamt(UFDouble.ZERO_DBL);
			}
			// ���Ŷ��
			UFDouble Protocolcdtlnamt = head.getCdtlnamt();

			// ���ö��=���Ŷ��
			head.setAvailcdtlnamt(Protocolcdtlnamt);
			// ������֯�������Ŷ��=��֯���Ҷ��
			head.setOlcavailcdtlnamt(head.getOlccdtlnamt());
			// ���ü��ű������Ŷ��=���ű��Ҷ��
			head.setGlcavailcdtlnamt(head.getGlccdtlnamt());
			// ����ȫ�ֱ������Ŷ��=ȫ�ֱ��Ҷ��
			head.setGllcavailcdtlnamt(head.getGllccdtlnamt());

			// �����������Ŷ�� =0
			head.setCurusdcdtlnamt(UFDouble.ZERO_DBL);
			// ����������֯�������Ŷ��=0
			head.setOlccurusdcdtlnamt(UFDouble.ZERO_DBL);
			// �������ü��ű������Ŷ�� =0
			head.setGlccurusdcdtlnamt(UFDouble.ZERO_DBL);
			// ��������ȫ�ֱ������Ŷ��=0
			head.setGllccurusdcdtlnamt(UFDouble.ZERO_DBL);

			// �����������Ŷ��=0
			head.setAppusdcdtlnamt(UFDouble.ZERO_DBL);
			// ����������֯�������Ŷ��=0
			head.setOlcappusdcdtlnamt(UFDouble.ZERO_DBL);
			// �������ü��ű������Ŷ��=0
			head.setGlcappusdcdtlnamt(UFDouble.ZERO_DBL);
			// ��������ȫ�ֱ������Ŷ��=0
			head.setGllcappusdcdtlnamt(UFDouble.ZERO_DBL);

			// �ڳ��������Ŷ��=0
			head.setOpnusdcdtlnamt(UFDouble.ZERO_DBL);
			// �ڳ�������֯�������Ŷ��=0
			head.setOlcopnusdcdtlnamt(UFDouble.ZERO_DBL);
			// �ڳ����ü��ű������Ŷ�� =0
			head.setGlcopnusdcdtlnamt(UFDouble.ZERO_DBL);
			// �ڳ�����ȫ�ֱ������Ŷ��=0
			head.setGllcopnusdcdtlnamt(UFDouble.ZERO_DBL);
		}
	}

	/**
	 * �ѱ�ͷ��֯ͬ��������
	 * 
	 * @param bills
	 */
	private void fillItemOrgs(IBill[] bills) {
		for (IBill bill : bills) {
			ISuperVO head = bill.getParent();
			head.setAttributeValue("billmaker", AppContext.getInstance()
					.getPkUser());

			String pk_group = (String) head.getAttributeValue("pk_group");
			String pk_org = (String) head.getAttributeValue("pk_org");
			String pk_org_v = (String) head.getAttributeValue("pk_org_v");
			if (pk_org_v == null) {
				// ��֯�����°汾
				Map<String, String> map;
				try {

					UFDate busiDate = AppContext.getInstance().getBusiDate();
					map = NCLocator
							.getInstance()
							.lookup(IOrgUnitPubService.class)
							.getNewVIDSByOrgIDSAndDate(new String[] { pk_org },
									busiDate);
					pk_org_v = map.get(pk_org);
					head.setAttributeValue("pk_org_v", pk_org_v);
				} catch (BusinessException e) {
					ExceptionUtils.wrappException(e);
				}
			}
            //����������Ĭ��ֵ
			for (IVOMeta meta : bill.getMetaData().getChildren()) {
				for (int i = 0; bill.getChildren(meta) != null
						&& i < bill.getChildren(meta).length; i++) {
					ISuperVO childvo = bill.getChildren(meta)[i];
					childvo.setAttributeValue("pk_group", pk_group);
					childvo.setAttributeValue("pk_org", pk_org);
					childvo.setAttributeValue("pk_org_v", pk_org_v);
					//����������Ĭ��ֵ
					if (childvo instanceof ProtocolDetailVO) {
						//��֯���Ҷ��
						if (((ProtocolDetailVO) childvo).getOlccdtlnamt() == null) {
							((ProtocolDetailVO) childvo).setOlccdtlnamt(UFDouble.ZERO_DBL);
						}
						//���ű��Ҷ��
						if (((ProtocolDetailVO) childvo).getGlccdtlnamt() == null) {
							((ProtocolDetailVO) childvo).setGlccdtlnamt(UFDouble.ZERO_DBL);
						}
						//ȫ�ֱ��Ҷ��
						if (((ProtocolDetailVO) childvo).getGllccdtlnamt() == null) {
							((ProtocolDetailVO) childvo).setGllccdtlnamt(UFDouble.ZERO_DBL);
						}
						//�������Ŷ��=���Ŷ��
						((ProtocolDetailVO) childvo).setAvailcdtlnamt(((ProtocolDetailVO) childvo).getCdtlnamt());
						//������֯�������Ŷ��=��֯�������Ŷ��
						((ProtocolDetailVO) childvo).setOlcavailcdtlnamt(((ProtocolDetailVO) childvo)
								.getOlccdtlnamt());
						//���ü��ű������Ŷ��=���ű������Ŷ��
						((ProtocolDetailVO) childvo).setGlcavailcdtlnamt(((ProtocolDetailVO) childvo)
								.getGlccdtlnamt());
						//����ȫ�ֱ������Ŷ��=ȫ�ֱ������Ŷ��
						((ProtocolDetailVO) childvo).setGllcavailcdtlnamt(((ProtocolDetailVO) childvo)
								.getGllccdtlnamt());

						//�����������Ŷ��=0
						((ProtocolDetailVO) childvo).setCurusdcdtlnamt(UFDouble.ZERO_DBL);
						//����������֯�������Ŷ��=0
						((ProtocolDetailVO) childvo).setOlccurusdcdtlnamt(UFDouble.ZERO_DBL);
						//�������ü��ű������Ŷ��=0
						((ProtocolDetailVO) childvo).setGlccurusdcdtlnamt(UFDouble.ZERO_DBL);
						//��������ȫ�ֱ������Ŷ��=0
						((ProtocolDetailVO) childvo).setGllccurusdcdtlnamt(UFDouble.ZERO_DBL);

						//�ڳ��������Ŷ��=0
						((ProtocolDetailVO) childvo).setOpnusdcdtlnamt(UFDouble.ZERO_DBL);
						//�ڳ�������֯�������Ŷ��=0
						((ProtocolDetailVO) childvo).setOlcopnusdcdtlnamt(UFDouble.ZERO_DBL);
						//�ڳ������鼯�ű����Ŷ��=0
						((ProtocolDetailVO) childvo).setGlcopnusdcdtlnamt(UFDouble.ZERO_DBL);
						//�ڳ�������ȫ�ֱ����Ŷ��=0
						((ProtocolDetailVO) childvo).setGllcopnusdcdtlnamt(UFDouble.ZERO_DBL);

						//�����������Ŷ��=0
						((ProtocolDetailVO) childvo).setAppusdcdtlnamt(UFDouble.ZERO_DBL);
						//����������֯�������Ŷ��=0
						((ProtocolDetailVO) childvo).setOlcappusdcdtlnamt(UFDouble.ZERO_DBL);
						//�������ü��ű������Ŷ��=0
						((ProtocolDetailVO) childvo).setGlcappusdcdtlnamt(UFDouble.ZERO_DBL);
						//��������ȫ�ֱ������Ŷ��=0
						((ProtocolDetailVO) childvo).setGllcappusdcdtlnamt(UFDouble.ZERO_DBL);
					}
				}
			}
		}
	}

}
