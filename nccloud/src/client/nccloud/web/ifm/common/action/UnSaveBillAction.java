package nccloud.web.ifm.common.action;

import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;


/**
 * �ջ�
 * @author wuzhwa
 * @version ncc1.0
 * 2018��7��31��
 */
public abstract class UnSaveBillAction<T  extends AbstractBill> extends AbstractPfAction<T> {

	@Override
	protected String getActionCode() {
		return TMIFMConst.CONST_ACTION_UNSAVEBILL;
	}
}
