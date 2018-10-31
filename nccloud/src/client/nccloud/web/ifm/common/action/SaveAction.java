package nccloud.web.ifm.common.action;

import nc.vo.ifm.constants.TMIFMConst;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;


/**
 * �ύ
 * @author wuzhwa
 * @version ncc1.0
 * 2018��7��31��
 */
public abstract class SaveAction<T  extends AbstractBill> extends AbstractPfAction<T> {


	@Override
	protected String getActionCode() {
		return TMIFMConst.CONST_ACTION_SAVE;
	}
}
