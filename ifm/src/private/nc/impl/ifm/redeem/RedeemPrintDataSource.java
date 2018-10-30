package nc.impl.ifm.redeem;

import nc.bs.framework.common.NCLocator;
import nc.itf.ifm.IInvestRedeemQueryService;
import nc.ui.pub.print.IMetaDataDataSource;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.vo.ifm.redeem.InvestRedeemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tmpub.util.TMCurrencyUtil;
import nccloud.base.exception.ExceptionUtils;

public class RedeemPrintDataSource implements IMetaDataDataSource {

	private static final long serialVersionUID = 1L;
	private final String oids;

	/**
	 * ¹¹Ôìº¯Êý
	 * 
	 * @param oids
	 */
	public RedeemPrintDataSource(String oids) {
		this.oids = oids;
	}

	@Override
	public String[] getAllDataItemExpress() {
		return null;
	}

	@Override
	public String[] getAllDataItemNames() {
		return null;
	}

	@Override
	public String[] getDependentItemExpressByExpress(String itemExpress) {
		return null;
	}

	@Override
	public String[] getItemValuesByExpress(String itemExpress) {
		return null;
	}

	@Override
	public Object[] getMDObjects() {

		AggInvestRedeemVO[] vos = null;
		try {
			IInvestRedeemQueryService service = NCLocator.getInstance()
					.lookup(IInvestRedeemQueryService.class);

			vos = service.getAggVOsByPKs(oids);
			for(AggInvestRedeemVO vo:vos){
				InvestRedeemVO headVO=vo.getParentVO();
				/*int digit = getDigit(headVO.getPk_org());
				UFDouble payLocal = vo.getParentVO().getCdtlnamt()==null?UFDouble.ZERO_DBL:headVO.getCdtlnamt();
				payLocal = new UFDouble(payLocal.doubleValue(),digit);
				headVO.setCdtlnamt(payLocal);*/
			}

		} catch (Exception e) {
			ExceptionUtils.wrapException(e);
		}
		return vos;
	}

	@Override
	public String getModuleName() {
		return null;
	}

	@Override
	public boolean isNumber(String itemExpress) {
		return false;
	}
	
	private int getDigit(String pk_org) throws BusinessException {
		String pk_curr = TMCurrencyUtil.getOrgLocalCurrPK(pk_org);
		int digit = TMCurrencyUtil.getCurrtypeDigit(pk_curr);
		return digit;

	}

}