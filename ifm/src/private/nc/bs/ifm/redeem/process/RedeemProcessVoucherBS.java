package nc.bs.ifm.redeem.process;

import nc.bs.logging.Logger;
import nc.itf.uap.busibean.SysinitAccessor;
import nc.pubitf.bbd.CurrtypeQuery;
import nc.vo.fac.fixdepositprocess.AggFixDepositProcessVO;
import nc.vo.fac.fixdepositprocess.FixDepositProcessVO;
import nc.vo.fac.pub.IFACConst;
import nc.vo.fac.util.IFACPubUtil;
import nc.vo.fip.service.FipMessageVO;
import nc.vo.fip.service.FipMsgResultVO;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.ifm.util.VoucherUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.payreceipt.PayReceiptVO;
import nc.vo.pub.pf.BillStatusEnum;
import nc.vo.pubapp.AppContext;
import nc.vo.tmpub.util.StringUtil;

/**
 * ��Ʋ�Ʒ�ǼǴ����ƽ̨����
 * 
 * @author shixin6
 */
public class RedeemProcessVoucherBS {
	

	

	public void completeContStatusDel(AggregatedValueObject[] vos){
		for(AggregatedValueObject vo: vos){
			try {
				int vbillstatus=-1;
				if (vo.getParentVO().getAttributeValue("vbillstatus")!=null&&
						((Integer)vo.getParentVO().getAttributeValue("vbillstatus")).intValue() == BillStatusEnum.APPROVED.toIntValue()) {
					vbillstatus=1;
				}
				if(vbillstatus==1){
					//�ڳ�������
					UFBoolean isinitial = (UFBoolean) vo.getParentVO().getAttributeValue(InvestRedeemVO.ISINITIAL);
					if(isinitial != null && isinitial.booleanValue() == true){
						continue;
					}
					VoucherUtil.sendDAPMessge_del(vo);
				}				
				
			} catch (BusinessException e) {
				Logger.error(e);
				throw new RuntimeException(e.getMessage(),e);
			}			

		}
	}
	
	
	public void completeContStatus(AggregatedValueObject[] vos){
		for(AggregatedValueObject vo: vos){						
			try {
				UFBoolean isinitial = (UFBoolean) vo.getParentVO().getAttributeValue(InvestRedeemVO.ISINITIAL);
				if(isinitial != null && isinitial.booleanValue() == true){
					continue;
				}
				
				/*//xwq 2015-07-17 
				Integer versionno =  (Integer)vo.getParentVO().getAttributeValue(PayReceiptVO.VERSIONNO);
				if(versionno!= null && versionno!=1){
					continue;
				}*/
				VoucherUtil.sendDAPMessge(vo);
			} catch (BusinessException e) {
				Logger.error(e);
				throw new RuntimeException(e.getMessage(),e);
			}			
		}
	}

	/**
	 * �������û��ƽ̨����ƾ֤
	 * 
	 * @param bill
	 * @return
	 * @throws BusinessException
	 */
	/*public FipMsgResultVO createFipVoucher(AggInvestRedeemVO bill)
			throws BusinessException {
		FipMessageVO messageVO = createFipMessageVO(bill,
				FipMessageVO.MESSAGETYPE_ADD, false);
		return VoucherUtil.sendDAPMessge(messageVO);
	}
*/
	/**
	 * ������û��ƽ̨����ƾ֤
	 * 
	 * @param bill
	 * @return
	 * @throws BusinessException
	 */
	public FipMsgResultVO deleteFipVoucher(AggInvestRedeemVO bill)
			throws BusinessException {
		FipMessageVO messageVO = createFipMessageVO(bill,
				FipMessageVO.MESSAGETYPE_DEL, false);
		return IFACPubUtil.sendFipMessage(messageVO);
	}

	/**
	 * ���������ɺ���ƾ֤
	 * 
	 * @param bill
	 * @return
	 * @throws BusinessException
	 */
	public FipMsgResultVO createFipRedVoucher(AggInvestRedeemVO bill)
			throws BusinessException {
		FipMessageVO messageVO = createFipMessageVO(bill,
				FipMessageVO.MESSAGETYPE_ADD, true);
		return IFACPubUtil.sendFipMessage(messageVO);
	}

	private FipMessageVO createFipMessageVO(AggInvestRedeemVO bill,
			int messageType, boolean flag) {
		FipRelationInfoVO reVO = new FipRelationInfoVO();
		InvestRedeemVO head = bill.getParentVO();

		UFDouble money = UFDouble.ZERO_DBL;
		
		reVO.setPk_group(head.getPk_group());
		reVO.setPk_org(head.getPk_org());
		reVO.setRelationID(head.getPrimaryKey());
		reVO.setPk_system(TMIFMConst.CONST_MODULE);
		reVO.setBusidate(AppContext.getInstance().getBusiDate());
		reVO.setPk_billtype(TMIFMConst.CONST_BILLTYPE_REDEEM);
		reVO.setPk_operator(AppContext.getInstance().getPkUser());
		reVO.setFreedef1(head.getVbillno());// ���ݺ�
		reVO.setFreedef2(head.getRemark());// ժҪ��ע
		if (flag) {
			money = money.multiply(-1);
			// ���������ɺ���ƾ֤�����Ϊ��ֵ
			/*for (int i=0;i<registerBVOs.length;i++) {
				registerBVOs[i].setMoney(registerBVOs[i].getMoney()
						.multiply(-1));
				registerBVOs[i].setOlcamount(registerBVOs[i].getOlcamount()
						.multiply(-1));
			}*/
		}
		reVO.setFreedef3(money.toString());// ���
		reVO.setDefdoc1(TMIFMConst.CONST_MODULE);

		FipMessageVO messageVO = new FipMessageVO();
		messageVO.setBillVO(bill);
		messageVO.setMessageinfo(reVO);
		messageVO.setMessagetype(messageType);
		return messageVO;
	}

	/**
	 * �жϻ��ƽ̨�Ƿ�װ
	 * 
	 * @param pk_org
	 * @param pk_group
	 * @param auto_voucher
	 * @return
	 * @throws BusinessException
	 */
	public boolean isAutoVoucher(String pk_org, String pk_group,
			String auto_voucher) throws BusinessException {
		// �жϻ��ƽ̨�Ƿ�װ
		boolean isFipEnable = IFACPubUtil.isFIPEnable(pk_group);
		if (!isFipEnable) {
			return false;// ������ƽ̨δ��װ���������Զ���֤
		}
		UFBoolean isAutoVoucher = SysinitAccessor.getInstance().getParaBoolean(
				pk_org, auto_voucher);
		if (isAutoVoucher == null) {
			return false;
		}
		return isAutoVoucher.booleanValue();
	}

	/**
	 * �õ���ʽ���Ľ��
	 * 
	 * @param amount
	 * @param pkCurrType
	 * @return
	 */
	public static String getFormateAmount(UFDouble amount, String pkCurrType) {
		if (amount == null) {
			amount = UFDouble.ZERO_DBL;
		}
		if (!StringUtil.isNull(pkCurrType)) {
			int currdigit = CurrtypeQuery.getInstance()
					.getCurrdigit(pkCurrType);
			amount = amount.setScale(currdigit, UFDouble.ROUND_HALF_UP);
		}
		return amount.toString();
	}
}
