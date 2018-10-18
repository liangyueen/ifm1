/* tablename: ifm_apply */
create table ifm_apply (pk_apply char(20) not null 
/*����*/,
vbillno varchar(40) null 
/*�깺���*/,
vbillstatus varchar(50) null 
/*����״̬*/,
pk_group varchar(20) null default '~' 
/*����*/,
pk_org varchar(20) null default '~' 
/*������֯*/,
pk_org_v varchar(20) null default '~' 
/*������֯�汾*/,
pk_currtype varchar(20) null default '~' 
/*����*/,
pk_olccurr varchar(20) null default '~' 
/*��֯��λ��*/,
remark varchar(181) null 
/*��ע*/,
vdef1 varchar(101) null 
/*�Զ�����1*/,
vdef2 varchar(101) null 
/*�Զ�����2*/,
vdef3 varchar(101) null 
/*�Զ�����3*/,
vdef4 varchar(101) null 
/*�Զ�����4*/,
vdef5 varchar(101) null 
/*�Զ�����5*/,
vdef6 varchar(101) null 
/*�Զ�����6*/,
vdef7 varchar(101) null 
/*�Զ�����7*/,
vdef8 varchar(101) null 
/*�Զ�����8*/,
vdef9 varchar(101) null 
/*�Զ�����9*/,
vdef10 varchar(101) null 
/*�Զ�����10*/,
vdef11 varchar(101) null 
/*�Զ�����11*/,
vdef12 varchar(101) null 
/*�Զ�����12*/,
vdef13 varchar(101) null 
/*�Զ�����13*/,
vdef14 varchar(101) null 
/*�Զ�����14*/,
vdef15 varchar(101) null 
/*�Զ�����15*/,
vdef16 varchar(101) null 
/*�Զ�����16*/,
vdef17 varchar(101) null 
/*�Զ�����17*/,
vdef18 varchar(101) null 
/*�Զ�����18*/,
vdef19 varchar(101) null 
/*�Զ�����19*/,
vdef20 varchar(101) null 
/*�Զ�����20*/,
olcrate decimal(15,8) null 
/*��֯���һ���*/,
olcmoney decimal(28,8) null 
/*��֯���ҽ��*/,
glcrate decimal(15,8) null 
/*���ű��һ���*/,
glcmoney decimal(28,8) null 
/*���ű��ҽ��*/,
gllcrate decimal(15,8) null 
/*ȫ�ֱ��һ���*/,
gllmoney decimal(28,8) null 
/*ȫ�ֱ��ҽ��*/,
billmaker varchar(20) null default '~' 
/*�Ƶ���*/,
billmakedate char(19) null 
/*�Ƶ�����*/,
billmaketime char(19) null 
/*�Ƶ�ʱ��*/,
approver varchar(20) null default '~' 
/*������*/,
approvedate char(19) null 
/*��������*/,
approvenote varchar(1024) null 
/*��������*/,
creator varchar(20) null default '~' 
/*������*/,
creationtime char(19) null 
/*����ʱ��*/,
modifier varchar(20) null default '~' 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
pk_busitype varchar(20) null default '~' 
/*ҵ������*/,
pk_billtypeid varchar(50) null 
/*������������*/,
pk_billtypecode varchar(50) null 
/*��������*/,
issuebank varchar(20) null default '~' 
/*��������*/,
boundary integer null 
/*������*/,
banknetwork varchar(20) null default '~' 
/*��������*/,
billstatus integer null 
/*����״̬*/,
settleaccount varchar(20) null default '~' 
/*�����˻�*/,
invest varchar(20) null default '~' 
/*����˻�*/,
productcode varchar(50) null 
/*��Ʒ����*/,
productname varchar(50) null 
/*��Ʒ����*/,
money decimal(28,8) null 
/*��ƽ��*/,
eventype integer null 
/*��������*/,
risk integer null 
/*��������*/,
purchasedate char(19) null 
/*��������*/,
enddate char(19) null 
/*��������*/,
interestdate char(19) null 
/*��Ϣ����*/,
interestday varchar(50) null 
/*��������*/,
expectedrate decimal(28,8) null 
/*Ԥ��������*/,
expectedmoney decimal(28,8) null 
/*Ԥ������*/,
paytype integer null 
/*��Ϣ����*/,
payperiod integer null 
/*��Ϣ����*/,
settledate char(19) null 
/*��Ϣ��*/,
applynumber integer null 
/*�깺����*/,
unitnetvalue decimal(28,8) null 
/*��λ��ֵ*/,
capitalproject varchar(20) null default '~' 
/*�ʽ�ƻ���Ŀ*/,
investvariety varchar(20) null default '~' 
/*Ͷ��Ʒ��*/,
limitday integer null 
/*����*/,
holdmoney decimal(28,8) null 
/*���н��*/,
holdnumber integer null 
/*���з���*/,
 constraint pk_ifm_apply primary key (pk_apply),
 ts char(19) null,
dr smallint null default 0
)
;

