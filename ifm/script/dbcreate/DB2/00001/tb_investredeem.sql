/* tablename: ifm_redeem */
create table ifm_redeem (pk_redeem char(20) not null 
/*����*/,
vbillno varchar(40) null 
/*�깺���*/,
vbillstatus integer null 
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
glcmoeny decimal(28,8) null 
/*���ű��ҽ��*/,
gllcrate decimal(15,8) null 
/*ȫ�ֱ��һ���*/,
gllmoeny decimal(28,8) null 
/*ȫ�ֱ������*/,
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
/*ҵ������0*/,
pk_billtypeid varchar(50) null 
/*������������*/,
pk_billtypecode varchar(50) null 
/*��������*/,
issuebank varchar(20) null default '~' 
/*��������*/,
billstatus integer null 
/*���״̬*/,
gatheringaccount varchar(20) null default '~' 
/*�տ��˻�*/,
investaccount varchar(20) null default '~' 
/*����˻�*/,
redeemdate char(19) null 
/*�������*/,
redeemmoney decimal(28,8) null 
/*��ؽ��*/,
interestday integer null 
/*��������*/,
expectedrate decimal(28,8) null 
/*Ԥ��������*/,
lastdate char(19) null 
/*�ϴ��������*/,
lastmoney decimal(28,8) null 
/*�ϴ�����*/,
redeemplan varchar(50) null 
/*��ؼƻ�*/,
incomedate char(19) null 
/*��������*/,
incomerate decimal(28,8) null 
/*����˰��*/,
incomemoney decimal(28,8) null 
/*����˰��*/,
balance decimal(28,8) null 
/*ʵ����Ԥ�ڲ��*/,
productcode varchar(20) null default '~' 
/*��Ʒ����*/,
productname varchar(50) null 
/*��Ʒ����*/,
redeemnumber integer null 
/*��ط���*/,
redeemid varchar(50) null 
/*��ر���*/,
vbilldate char(19) null 
/*�깺����*/,
unitnetvalue decimal(28,8) null 
/*��λ��ֵ*/,
capitalproject varchar(50) null 
/*�ʽ�ƻ���Ŀ*/,
realreaning decimal(28,8) null 
/*ʵ������*/,
vbilltype integer null 
/*�깺״̬*/,
enddate char(19) null 
/*��������*/,
holdmoney decimal(28,8) null 
/*���н��*/,
 constraint pk_ifm_redeem primary key (pk_redeem),
 ts char(19) null,
dr smallint null default 0
)
;

