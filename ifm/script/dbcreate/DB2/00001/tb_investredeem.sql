/* tablename: ifm_redeem */
create table ifm_redeem (pk_redeem char(20) not null 
/*主键*/,
vbillno varchar(40) null 
/*申购编号*/,
vbillstatus integer null 
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
glcmoeny decimal(28,8) null 
/*集团本币金额*/,
gllcrate decimal(15,8) null 
/*全局本币汇率*/,
gllmoeny decimal(28,8) null 
/*全局本机金额*/,
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
/*业务流程0*/,
pk_billtypeid varchar(50) null 
/*单据类型主键*/,
pk_billtypecode varchar(50) null 
/*单据类型*/,
issuebank varchar(20) null default '~' 
/*发行银行*/,
billstatus integer null 
/*赎回状态*/,
gatheringaccount varchar(20) null default '~' 
/*收款账户*/,
investaccount varchar(20) null default '~' 
/*理财账户*/,
redeemdate char(19) null 
/*赎回日期*/,
redeemmoney decimal(28,8) null 
/*赎回金额*/,
interestday integer null 
/*利率天数*/,
expectedrate decimal(28,8) null 
/*预期收益率*/,
lastdate char(19) null 
/*上次赎回日期*/,
lastmoney decimal(28,8) null 
/*上次收益*/,
redeemplan varchar(50) null 
/*赎回计划*/,
incomedate char(19) null 
/*到账日期*/,
incomerate decimal(28,8) null 
/*收益税率*/,
incomemoney decimal(28,8) null 
/*收益税额*/,
balance decimal(28,8) null 
/*实际与预期差额*/,
productcode varchar(20) null default '~' 
/*产品代码*/,
productname varchar(50) null 
/*产品名称*/,
redeemnumber integer null 
/*赎回份数*/,
redeemid varchar(50) null 
/*赎回编码*/,
vbilldate char(19) null 
/*申购日期*/,
unitnetvalue decimal(28,8) null 
/*单位净值*/,
capitalproject varchar(50) null 
/*资金计划项目*/,
realreaning decimal(28,8) null 
/*实际收益*/,
vbilltype integer null 
/*申购状态*/,
enddate char(19) null 
/*到期日期*/,
holdmoney decimal(28,8) null 
/*持有金额*/,
 constraint pk_ifm_redeem primary key (pk_redeem),
 ts char(19) null,
dr smallint null default 0
)
;

