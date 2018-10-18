/* tablename: ifm_apply */
create table ifm_apply (pk_apply char(20) not null 
/*主键*/,
vbillno varchar(40) null 
/*申购编号*/,
vbillstatus varchar(50) null 
/*审批状态*/,
pk_group varchar(20) null default '~' 
/*集团*/,
pk_org varchar(20) null default '~' 
/*财务组织*/,
pk_org_v varchar(20) null default '~' 
/*财务组织版本*/,
pk_currtype varchar(20) null default '~' 
/*币种*/,
pk_olccurr varchar(20) null default '~' 
/*组织本位币*/,
remark varchar(181) null 
/*备注*/,
vdef1 varchar(101) null 
/*自定义项1*/,
vdef2 varchar(101) null 
/*自定义项2*/,
vdef3 varchar(101) null 
/*自定义项3*/,
vdef4 varchar(101) null 
/*自定义项4*/,
vdef5 varchar(101) null 
/*自定义项5*/,
vdef6 varchar(101) null 
/*自定义项6*/,
vdef7 varchar(101) null 
/*自定义项7*/,
vdef8 varchar(101) null 
/*自定义项8*/,
vdef9 varchar(101) null 
/*自定义项9*/,
vdef10 varchar(101) null 
/*自定义项10*/,
vdef11 varchar(101) null 
/*自定义项11*/,
vdef12 varchar(101) null 
/*自定义项12*/,
vdef13 varchar(101) null 
/*自定义项13*/,
vdef14 varchar(101) null 
/*自定义项14*/,
vdef15 varchar(101) null 
/*自定义项15*/,
vdef16 varchar(101) null 
/*自定义项16*/,
vdef17 varchar(101) null 
/*自定义项17*/,
vdef18 varchar(101) null 
/*自定义项18*/,
vdef19 varchar(101) null 
/*自定义项19*/,
vdef20 varchar(101) null 
/*自定义项20*/,
olcrate decimal(15,8) null 
/*组织本币汇率*/,
olcmoney decimal(28,8) null 
/*组织本币金额*/,
glcrate decimal(15,8) null 
/*集团本币汇率*/,
glcmoney decimal(28,8) null 
/*集团本币金额*/,
gllcrate decimal(15,8) null 
/*全局本币汇率*/,
gllmoney decimal(28,8) null 
/*全局本币金额*/,
billmaker varchar(20) null default '~' 
/*制单人*/,
billmakedate char(19) null 
/*制单日期*/,
billmaketime char(19) null 
/*制单时间*/,
approver varchar(20) null default '~' 
/*审批人*/,
approvedate char(19) null 
/*审批日期*/,
approvenote varchar(1024) null 
/*审批批语*/,
creator varchar(20) null default '~' 
/*创建人*/,
creationtime char(19) null 
/*创建时间*/,
modifier varchar(20) null default '~' 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
pk_busitype varchar(20) null default '~' 
/*业务流程*/,
pk_billtypeid varchar(50) null 
/*单据类型主键*/,
pk_billtypecode varchar(50) null 
/*单据类型*/,
issuebank varchar(20) null default '~' 
/*发行银行*/,
boundary integer null 
/*境内外*/,
banknetwork varchar(20) null default '~' 
/*银行网点*/,
billstatus integer null 
/*单据状态*/,
settleaccount varchar(20) null default '~' 
/*结算账户*/,
invest varchar(20) null default '~' 
/*理财账户*/,
productcode varchar(50) null 
/*产品编码*/,
productname varchar(50) null 
/*产品名称*/,
money decimal(28,8) null 
/*理财金额*/,
eventype integer null 
/*保本类型*/,
risk integer null 
/*风险类型*/,
purchasedate char(19) null 
/*购买日期*/,
enddate char(19) null 
/*到期日期*/,
interestdate char(19) null 
/*起息日期*/,
interestday varchar(50) null 
/*利率天数*/,
expectedrate decimal(28,8) null 
/*预期收益率*/,
expectedmoney decimal(28,8) null 
/*预期收益*/,
paytype integer null 
/*付息规则*/,
payperiod integer null 
/*付息周期*/,
settledate char(19) null 
/*结息日*/,
applynumber integer null 
/*申购份数*/,
unitnetvalue decimal(28,8) null 
/*单位净值*/,
capitalproject varchar(20) null default '~' 
/*资金计划项目*/,
investvariety varchar(20) null default '~' 
/*投资品种*/,
limitday integer null 
/*期限*/,
holdmoney decimal(28,8) null 
/*持有金额*/,
holdnumber integer null 
/*持有份数*/,
 constraint pk_ifm_apply primary key (pk_apply),
 ts char(19) null,
dr smallint null default 0
)
;

