package nc.ui.tb.sysmaintain.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import nc.bs.logging.Logger;
import nc.funcnode.ui.action.AbstractNCAction;
import nc.itf.tb.control.IBusiSysReg;
import nc.itf.tb.sysmaintain.BdContrastCache;
import nc.itf.tb.sysmaintain.BusiSysReg;
import nc.ms.mdm.dim.NtbSuperServiceGetter;
import nc.ms.tb.rule.RuleServiceGetter;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.border.UITitledBorder;
import nc.ui.pub.print.ide.basic.ImageButton;
import nc.ui.tb.panel.TbActionPanel;
import nc.ui.tb.sysmaintain.model.BdContrastTableModel;
import nc.ui.tb.sysmaintain.model.IRefreshBdcontrastListener;
import nc.ui.tb.sysmaintain.model.ISysTreeEnableListener;
import nc.ui.tb.table.NtbActionIconCtl;
import nc.um.tb.rule.fmlset.SmallIconButton;
import nc.vo.mdm.pub.NtbLogger;
import nc.vo.pub.BusinessException;
import nc.vo.tb.control.IdBdcontrastVO;
import nc.vo.tb.control.IdSysregVO;


public class RegisterBdContrastInfoListPanel extends UIPanel implements TableModelListener {

	/**
	 * 界面的三个状态
	 */
	private final static int _BROWSE_ = 0;
	private final static int _ADD_ = 1;
	private final static int _EDIT_ = 2;
	private final static int _NULL_ = 3;
	private int m_State = _BROWSE_;

	private UIPanel m_actionPanel;

	private BdContrastBasePnl cardPanel;
	private BdContrastListPanel listPanel;

//	private UIPanel m_northPnl = null;
//	private JButton btnDelRow = null; // 基本档案的删除
//	private JButton btnAddRow = null; // 基本档案的增加
//	private JButton btnUpdRow = null; // 基本档案的修改
//	private JButton btnSave = null; // 基本档案的保存
//	private JButton btnCancel = null; // 基本档案的取消
	private TbActionPanel actionPanel = null;
	private BDAddAction bdAddAction = null;
	private BDEditAction bdEditAction = null;
	private BDSaveAction bdSaveAction = null;
	private BDDeleteAction bdDeleteAction = null;
	private BDCancelAction bdCancelAction = null;

	private IBusiSysReg sysReg = null;
	private String sys_Id;

	private ISysTreeEnableListener listener = null;

	private IRefreshBdcontrastListener bdcontrastListener = null;
	
	//支持自定义档案的接口
	private String[] sysForNewContrast = {"GL", "ARAP", "erm", "FA", "CMP","TMC","IFM","CCC"};//tm weiyjc 支持司库云预算控制

	public RegisterBdContrastInfoListPanel(IBusiSysReg m_sysid) {
		super();
		sysReg = m_sysid;
		initialize();
	}

	public void initialize() {

		setName("ControlTacticsManageUI");
		setLayout(new BorderLayout());
		setSize(774, 419);
		add(getActionBdContrastDetailPane(), BorderLayout.CENTER);
		add(getActionPanel(), BorderLayout.NORTH);
		setBorder(new UITitledBorder(new EtchedBorder(),NCLangRes.getInstance().getStrByID("tbb_ctrl", "01420ctl_000078")/*扩展内容 */));
		// 设置按钮
		// setButtons(m_aryAddButtonGroup);
		// 初始为浏览态
		setState(_BROWSE_);
		initData();
		

	}

	public int getState() {
		return m_State;
	}
	
	public void initButtonState() {
		getBDAddAction().setEnabled(false);
		for(String sys : sysForNewContrast) {
			if(sysReg.getBusiSysID().equals(sys)) {
				getBDAddAction().setEnabled(true);
				break;
			}
		}
	}

	public void reloadData(IBusiSysReg sys) {
		sysReg = sys;
		initData();
	}

	public void reloadData(String sys_id) {
		this.sys_Id = sys_id;
		initData(sys_Id);
	}

	private void initData() {
		try {
			IdBdcontrastVO[] vos = BdContrastCache.getNewInstance()
					.getVoBySysid(sysReg.getBusiSysID());
			getListPanel().initTableModel(vos);
			getListPanel().getUITablePane().getTable().getModel()
					.addTableModelListener(this);
			initButtonState();

		} catch (BusinessException e) {
			NtbLogger.error(e);
		}
	}

	private void initData(String sys_Id) {
		try {
			IdBdcontrastVO[] vos = BdContrastCache.getNewInstance()
					.getVoBySysid(sys_Id);
			getListPanel().initTableModel(vos);
			getListPanel().getUITablePane().getTable().getModel()
					.addTableModelListener(this);
			initButtonState();

		} catch (BusinessException e) {
			NtbLogger.error(e);
		}
	}

	public void showListBdContrast() {
		((CardLayout) getActionBdContrastDetailPane().getLayout()).show(
				getActionBdContrastDetailPane(), getListPanel().getName());
	}
	
	class BDDeleteAction extends AbstractNCAction {

		public BDDeleteAction() {
			super();
			setCode(NtbActionIconCtl.ACTION_DELETELINE);
			setName(NtbActionIconCtl.getName(NtbActionIconCtl.ACTION_DELETELINE));
			NtbActionIconCtl.setIcon(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				doDelete();
			} catch (Exception e1) {
				NtbLogger.print(e1);
			}
		}
	}
	
	class BDAddAction extends AbstractNCAction {

		public BDAddAction() {
			super();
			setCode(NtbActionIconCtl.ACTION_ADDLINE);
			setName(NtbActionIconCtl.getName(NtbActionIconCtl.ACTION_ADDLINE));
			NtbActionIconCtl.setIcon(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			doAdd();
		}
		
	}
	
	class BDEditAction extends AbstractNCAction {

		public BDEditAction() {
			super();
			setCode(NtbActionIconCtl.ACTION_UPDATE);
			setName(NtbActionIconCtl.getName(NtbActionIconCtl.ACTION_UPDATE));
			NtbActionIconCtl.setIcon(this);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			doEdit();
		}
		
	}
	
	class BDSaveAction extends AbstractNCAction {
		
		public BDSaveAction() {
			super();
			setCode(NtbActionIconCtl.ACTION_SAVE);
			setName(NtbActionIconCtl.getName(NtbActionIconCtl.ACTION_SAVE));
			NtbActionIconCtl.setIcon(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				doSave();
			} catch (Exception e1) {
				NtbLogger.print(e1);
			}
		}
		
	}
	
	class BDCancelAction extends AbstractNCAction {
		
		public BDCancelAction() {
			super();
			setCode(NtbActionIconCtl.ACTION_CANCEL);
			setName(NtbActionIconCtl.getName(NtbActionIconCtl.ACTION_CANCEL));
			NtbActionIconCtl.setIcon(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			doCancel();
		}
		
	}
	
	protected AbstractNCAction getBDAddAction() {
		if(bdAddAction == null)
			bdAddAction = new BDAddAction();
		return bdAddAction;
	}
	
	protected AbstractNCAction getBDEditAction() {
		if(bdEditAction == null)
			bdEditAction = new BDEditAction();
		return bdEditAction;
	}
	
	protected AbstractNCAction getBDSaveAction() {
		if(bdSaveAction == null)
			bdSaveAction = new BDSaveAction();
		return bdSaveAction;
	}
	
	protected AbstractNCAction getBDDeleteAction() {
		if(bdDeleteAction == null)
			bdDeleteAction = new BDDeleteAction();
		return bdDeleteAction;
	}
	
	protected AbstractNCAction getBDCancelAction() {
		if(bdCancelAction == null)
			bdCancelAction = new BDCancelAction();
		return bdCancelAction;
	}
	
	protected TbActionPanel getActionPanel() {
		if(actionPanel == null) {
			actionPanel = new TbActionPanel();
			List<Action> actionList = new ArrayList<Action>();
			actionList.add(getBDAddAction());
			actionList.add(getBDEditAction());
			actionList.add(getBDSaveAction());
			actionList.add(getBDDeleteAction());
			actionList.add(getBDCancelAction());
			actionPanel.setActions(actionList);
		}
		return actionPanel;
	}
	

	private UIPanel getActionBdContrastDetailPane() {
		BdContrastListPanel tempDataRuleListPanel = null;
		BdContrastBasePnl tempDataRuleCardPanel = null;
		if (m_actionPanel == null) {
			m_actionPanel = new UIPanel();
			m_actionPanel.setName(NCLangRes.getInstance().getStrByID("tbb_ctrl", "01420ctl_000072")/*基本档案维护 */);
			tempDataRuleListPanel = getListPanel();
			tempDataRuleCardPanel = getCardPanel();
			m_actionPanel.setLayout(new CardLayout());
			m_actionPanel.add(tempDataRuleListPanel,
					tempDataRuleListPanel.getName());
			m_actionPanel.add(tempDataRuleCardPanel,
					tempDataRuleCardPanel.getName());

		}
		return m_actionPanel;
	}

	public void setState(int newM_State) {
		m_State = newM_State;
		// 根据状态设置按钮的可用性
		switch (m_State) {
		case _BROWSE_:
			getBDAddAction().setEnabled(true);
			getBDEditAction().setEnabled(false);
			getBDDeleteAction().setEnabled(false);
			getBDSaveAction().setEnabled(false);
			getBDCancelAction().setEnabled(false);
			if (listener != null) {
				listener.setSysTreeEnable(true);
			}
			break;
		case _ADD_:
			getBDAddAction().setEnabled(false);
			getBDEditAction().setEnabled(false);
			getBDDeleteAction().setEnabled(false);
			getBDSaveAction().setEnabled(true);
			getBDCancelAction().setEnabled(true);
			if (listener != null) {
				listener.setSysTreeEnable(false);
			}
			break;
		case _EDIT_:
			getBDAddAction().setEnabled(false);
			getBDEditAction().setEnabled(false);
			getBDDeleteAction().setEnabled(false);
			getBDSaveAction().setEnabled(true);
			getBDCancelAction().setEnabled(true);
			if (listener != null) {
				listener.setSysTreeEnable(false);
			}
			break;
		case _NULL_:
			getBDAddAction().setEnabled(false);
			getBDEditAction().setEnabled(false);
			getBDDeleteAction().setEnabled(false);
			getBDSaveAction().setEnabled(false);
			getBDCancelAction().setEnabled(false);
			if (listener != null) {
				listener.setSysTreeEnable(true);
			}
		default:
			break;
		}
	}

	public BdContrastListPanel getListPanel() {
		if (listPanel == null) {
			listPanel = new BdContrastListPanel();
		}
		return listPanel;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = getListPanel().getUITablePane().getTable().getSelectedRow();
		/** 检查所有的行中是否有主组织,如果存在,就不能够删除 */
		boolean isUseDel = true;
		if (row == -1)
			return;

		IdBdcontrastVO[] bvos = ((BdContrastTableModel) getListPanel()
				.getUITablePane().getTable().getModel()).getSelectVOS();
		for (int n = 0; n < bvos.length; n++) {
			if (bvos[n].getIsSystem() == null
					|| bvos[n].getIsSystem().booleanValue()) {
				isUseDel = false;
				break;
			}
		}
		if (!isUseDel || bvos == null || bvos.length == 0) {
			getBDDeleteAction().setEnabled(false);
			getBDEditAction().setEnabled(false);
		} else {
			getBDDeleteAction().setEnabled(true);
			getBDEditAction().setEnabled(true);
		}
	}

	private void doAdd() {
		/* 切换至‘增加’模式 */
		setState(_ADD_);

		BdContrastBasePnl tempDataRuleCardPanel = getCardPanel();

		/* 初始清空卡片模板 */
		tempDataRuleCardPanel.addNew();

		/* 切换至卡片模板 */
		((CardLayout) getActionBdContrastDetailPane().getLayout()).show(
				getActionBdContrastDetailPane(),
				tempDataRuleCardPanel.getName());
		//tempDataRuleCardPanel.getreplyBdContrast().setText("SB");
	}

	private void doEdit() {
		IdBdcontrastVO[] vos = getSelectedVO();
		if (vos == null || vos.length == 0) {
			MessageDialog.showWarningDlg(
					this,
					NCLangRes.getInstance().getStrByID("tbb_ctrl", "01420ctl_000011")/*警告 */,
					NCLangRes.getInstance().getStrByID("tbb_ctrl", "01420ctl_000073")/*请选择需要修改的业务系统基本档案 */);
			return;
		} else if (vos.length > 1) {

			MessageDialog.showWarningDlg(
					this,
					NCLangRes.getInstance().getStrByID("tbb_ctrl", "01420ctl_000011")/*警告 */,
					NCLangRes.getInstance().getStrByID("tbb_ctrl", "01420ctl_000074")/*请选择一条业务系统基本档案修改 */);
			return;
		}
		setState(_EDIT_);
		BdContrastBasePnl tempBillCardPanel = getCardPanel();

		/* clear contents */
		/* add to address panel */
		((CardLayout) getActionBdContrastDetailPane().getLayout()).show(
				getActionBdContrastDetailPane(), tempBillCardPanel.getName());
		tempBillCardPanel.setBusiObj(vos[0]);
	}

	private void doDelete() throws BusinessException {
		
		IdBdcontrastVO[] vos = getSelectedVO();
		if (vos == null||vos.length==0) {
			MessageDialog.showWarningDlg(this,NCLangRes.getInstance().getStrByID("tbb_ctrl", "01420ctl_000075")/*警告*/, NCLangRes.getInstance().getStrByID("tbb_ctrl", "01420ctl_000076")/*请选择需要删除的业务系统基本档案*/);
			return;
		}
		/* 删除确认 */
		if (MessageDialog.showYesNoDlg(this,NCLangRes.getInstance().getStrByID("tbb_ctrl", "01801ctl_000000")/*提示*/,NCLangRes.getInstance().getStrByID("tbb_ctrl", "01420ctl_000077")/*确认删除改已注册的基本档案吗?*/) == MessageDialog.ID_YES) {
			try {
				NtbSuperServiceGetter.getINtbSuper().deleteArray(vos);
			} catch (Exception e) {
				Logger.error(e.getMessage(), e);
			}
		} else
			return;
		setState(_BROWSE_);
		RuleServiceGetter.getIBusiRuleManager().refreshBdContrastCache();
		loadListPanel();
	}

	private void doSave() throws BusinessException {
		IdBdcontrastVO vo = getCardPanel().getBusiObj();
		if (vo == null)
			return;

		//非空校验
		String  message = getCardPanel().dataNotNullValidate();
		if(message!=null&&!"".equals(message)){
			MessageDialog.showHintDlg(this, NCLangRes.getInstance().getStrByID("tbb_ctrl", "01420ctl_000075")/*警告*/, message);
			return ;
		}
		switch (getState()) {
			case _ADD_:
				try{
				    NtbSuperServiceGetter.getINtbSuper().insert(vo);
				}catch(BusinessException ex){
					NtbLogger.error(ex);
				}
				break;
			case _EDIT_:
				try{
				    NtbSuperServiceGetter.getINtbSuper().update(vo);
				}catch(BusinessException ex){
					NtbLogger.error(ex);
				}
				break;
			default:
				break;
		}
		setState(_BROWSE_);
		RuleServiceGetter.getIBusiRuleManager().refreshBdContrastCache();
		loadListPanel();
	}

	private void doCancel() {
		setState(_BROWSE_);
		loadListPanel();
	}

	private IdBdcontrastVO[] getSelectedVO() {
		IdBdcontrastVO[] vos = null;
		vos = ((BdContrastTableModel) getListPanel().getUITablePane()
				.getTable().getModel()).getSelectVOS();
		return vos;
	}

	private void loadListPanel() {
		BdContrastListPanel tempBillListPanel = getListPanel();
		try {
			BdContrastCache.getNewInstance().reloadContrastFromDB();
			IdBdcontrastVO[] vos = BdContrastCache.getNewInstance()
					.getVoBySysid(sysReg.getBusiSysID());
			setState(_BROWSE_);
			bdcontrastListener.refreshBdcontrast(sysReg.getBusiSysID());
			getListPanel().initTableModel(vos);
			getListPanel().getUITablePane().getTable().getModel()
			.addTableModelListener(this);
		} catch (BusinessException e) {
			NtbLogger.error(e);
		}
		((CardLayout) getActionBdContrastDetailPane().getLayout()).show(
				getActionBdContrastDetailPane(), tempBillListPanel.getName());

	}

	public void setSysTreeEnableListener(ISysTreeEnableListener listener) {
		this.listener = listener;
	}

	public void setRefreshBdcontrastListener(IRefreshBdcontrastListener listener) {
		this.bdcontrastListener = listener;
	}

	private BdContrastBasePnl getCardPanel() {

		if (this.cardPanel == null) {
			cardPanel = new BdContrastBasePnl(sysReg);
		}
		return cardPanel;
	}

	public void setCardPanel(String sysid) {
		IdSysregVO[] busiSys;
		try {
			busiSys = BusiSysReg.getSharedInstance().getAllSysVOs();
			IdSysregVO currenySys = null;
			for (int n = 0; n < (busiSys == null ? 0 : busiSys.length); n++) {
				if (sysid.equals(busiSys[n].getSysid())) {
					currenySys = busiSys[n];
					break;
				}
			}
			sysReg = (IBusiSysReg) Class.forName(currenySys.getRegclass())
					.newInstance();
		} catch (Exception e) {
			NtbLogger.error(e);
		}
		getCardPanel().setSysReg(sysReg);
	}

}
