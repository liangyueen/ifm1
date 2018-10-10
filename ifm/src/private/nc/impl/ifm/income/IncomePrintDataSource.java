package nc.impl.ifm.income;

import nc.bs.framework.common.NCLocator;
import nc.itf.ifm.IInvestIncomeQueryService;
import nc.ui.pub.print.IMetaDataDataSource;
import nc.vo.ifm.income.AggInvestIncomeVO;
import nc.vo.ifm.income.InvestIncomeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tmpub.util.TMCurrencyUtil;
import nccloud.base.exception.ExceptionUtils;

public class IncomePrintDataSource implements IMetaDataDataSource {

	private static final long serialVersionUID = 1L;
	private final String[] oids;

	/**
	 * ¹¹Ôìº¯Êý
	 * 
	 * @param oids
	 */
	public IncomePrintDataSource(String[] oids) {
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

		AggInvestIncomeVO[] vos = null;
		try {
			IInvestIncomeQueryService service = NCLocator.getInstance()
					.lookup(IInvestIncomeQueryService.class);

			vos = service.queryIncomeByPks(oids);
			for(AggInvestIncomeVO vo:vos){
				InvestIncomeVO headVO=vo.getParentVO();
//				int digit = getDigit(headVO.getPk_org());
//				UFDouble payLocal = vo.getParentVO().getCdtlnamt()==null?UFDouble.ZERO_DBL:headVO.getCdtlnamt();
//				payLocal = new UFDouble(payLocal.doubleValue(),digit);
//				headVO.setCdtlnamt(payLocal);
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
	
	/*private int getDigit(String pk_org) throws BusinessException {
		String pk_curr = TMCurrencyUtil.getOrgLocalCurrPK(pk_org);
		int digit = TMCurrencyUtil.getCurrtypeDigit(pk_curr);
		return digit;

	}*/

}