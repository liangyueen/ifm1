/* tablename: ifm_apply */
create table ifm_apply (pk_apply char(20) not null 
/*主键*/,
vbillno varchar2(40) null 
/*申购编号*/,
vbillstatus varchar2(50) null 
/*审批状态*/,
pk_group varchar2(20) default '~' null 
/*集团*/,
pk_org varchar2(20) default '~' null 
/*财务组织*/,
pk_org_v varchar2(20) default '~' null 
/*财务组织版本*/,
pk_currtype varchar2(20) default '~' null 
/*币种*/,
pk_olccurr varchar2(20) default '~' null 
/*组织本位币*/,
remark varchar2(181) null 
/*备注*/,
vdef1 varchar2(101) null 
/*自定义项1*/,
vdef2 varchar2(101) null 
/*自定义项2*/,
vdef3 varchar2(101) null 
/*自定义项3*/,
vdef4 varchar2(101) null 
/*自定义项4*/,
vdef5 varchar2(101) null 
/*自定义项5*/,
vdef6 varchar2(101) null 
/*自定义项6*/,
vdef7 varchar2(101) null 
/*自定义项7*/,
vdef8 varchar2(101) null 
/*自定义项8*/,
vdef9 varchar2(101) null 
/*自定义项9*/,
vdef10 varchar2(101) null 
/*自定义项10*/,
vdef11 varchar2(101) null 
/*自定义项11*/,
vdef12 varchar2(101) null 
/*自定义项12*/,
vdef13 varchar2(101) null 
/*自定义项13*/,
vdef14 varchar2(101) null 
/*自定义项14*/,
vdef15 varchar2(101) null 
/*自定义项15*/,
vdef16 varchar2(101) null 
/*自定义项16*/,
vdef17 varchar2(101) null 
/*自定义项17*/,
vdef18 varchar2(101) null 
/*自定义项18*/,
vdef19 varchar2(101) null 
/*自定义项19*/,
vdef20 varchar2(101) null 
/*自定义项20*/,
olcrate number(15,8) null 
/*组织本币汇率*/,
olcmoney number(28,8) null 
/*组织本币金额*/,
glcrate number(15,8) null 
/*集团本币汇率*/,
glcmoney number(28,8) null 
/*集团本币金额*/,
gllcrate number(15,8) null 
/*全局本币汇率*/,
gllmoney number(28,8) null 
/*全局本币金额*/,
billmaker varchar2(20) default '~' null 
/*制单人*/,
billmakedate char(19) null 
/*制单日期*/,
billmaketime char(19) null 
/*制单时间*/,
approver varchar2(20) default '~' null 
/*审批人*/,
approvedate char(19) null 
/*审批日期*/,
approvenote varchar2(1024) null 
/*审批批语*/,
creator varchar2(20) default '~' null 
/*创建人*/,
creationtime char(19) null 
/*创建时间*/,
modifier varchar2(20) default '~' null 
/*最后修改人*/,
modifiedtime char(19) null 
/*最后修改时间*/,
pk_busitype varchar2(20) default '~' null 
/*业务流程*/,
pk_billtypeid varchar2(50) null 
/*单据类型主键*/,
pk_billtypecode varchar2(50) null 
/*单据类型*/,
issuebank varchar2(20) default '~' null 
/*发行机构*/,
boundary integer null 
/*境内外*/,
banknetwork varchar2(20) default '~' null 
/*银行网点*/,
billstatus integer null 
/*单据状态*/,
settleaccount varchar2(20) default '~' null 
/*结算账户*/,
invest varchar2(20) default '~' null 
/*理财账户*/,
productcode varchar2(50) null 
/*产品编码*/,
productname varchar2(50) null 
/*产品名称*/,
money number(28,8) null 
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
interestday varchar2(50) null 
/*利率天数*/,
expectedrate number(28,8) null 
/*预期收益率*/,
expectedmoney number(28,8) null 
/*预期收益*/,
paytype integer null 
/*付息规则*/,
payperiod integer null 
/*付息周期*/,
settledate varchar2(20) default '~' null 
/*结息日*/,
applynumber integer null 
/*申购份数*/,
capitalproject varchar2(20) default '~' null 
/*资金计划项目*/,
investvariety varchar2(20) default '~' null 
/*投资品种*/,
limitday integer null 
/*期限*/,
holdmoney number(28,8) null 
/*持有金额*/,
holdnumber integer null 
/*持有份数*/,
unitnetvalue number(28,8) null 
/*单位净值*/,
 constraint pk_ifm_apply primary key (pk_apply),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

