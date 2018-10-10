/* tablename: ifm_redeem */
create table ifm_redeem (pk_redeem char(20) not null 
/*����*/,
vbillno varchar2(40) null 
/*�깺���*/,
vbillstatus integer null 
/*����״̬*/,
pk_group varchar2(20) default '~' null 
/*����*/,
pk_org varchar2(20) default '~' null 
/*������֯*/,
pk_org_v varchar2(20) default '~' null 
/*������֯�汾*/,
pk_currtype varchar2(20) default '~' null 
/*����*/,
pk_olccurr varchar2(20) default '~' null 
/*��֯��λ��*/,
remark varchar2(181) null 
/*��ע*/,
vdef1 varchar2(101) null 
/*�Զ�����1*/,
vdef2 varchar2(101) null 
/*�Զ�����2*/,
vdef3 varchar2(101) null 
/*�Զ�����3*/,
vdef4 varchar2(101) null 
/*�Զ�����4*/,
vdef5 varchar2(101) null 
/*�Զ�����5*/,
vdef6 varchar2(101) null 
/*�Զ�����6*/,
vdef7 varchar2(101) null 
/*�Զ�����7*/,
vdef8 varchar2(101) null 
/*�Զ�����8*/,
vdef9 varchar2(101) null 
/*�Զ�����9*/,
vdef10 varchar2(101) null 
/*�Զ�����10*/,
vdef11 varchar2(101) null 
/*�Զ�����11*/,
vdef12 varchar2(101) null 
/*�Զ�����12*/,
vdef13 varchar2(101) null 
/*�Զ�����13*/,
vdef14 varchar2(101) null 
/*�Զ�����14*/,
vdef15 varchar2(101) null 
/*�Զ�����15*/,
vdef16 varchar2(101) null 
/*�Զ�����16*/,
vdef17 varchar2(101) null 
/*�Զ�����17*/,
vdef18 varchar2(101) null 
/*�Զ�����18*/,
vdef19 varchar2(101) null 
/*�Զ�����19*/,
vdef20 varchar2(101) null 
/*�Զ�����20*/,
olcrate number(15,8) null 
/*��֯���һ���*/,
olcmoney number(28,8) null 
/*��֯���ҽ��*/,
glcrate number(15,8) null 
/*���ű��һ���*/,
glcmoeny number(28,8) null 
/*���ű��ҽ��*/,
gllcrate number(15,8) null 
/*ȫ�ֱ��һ���*/,
gllmoeny number(28,8) null 
/*ȫ�ֱ������*/,
billmaker varchar2(20) default '~' null 
/*�Ƶ���*/,
billmakedate char(19) null 
/*�Ƶ�����*/,
billmaketime char(19) null 
/*�Ƶ�ʱ��*/,
approver varchar2(20) default '~' null 
/*������*/,
approvedate char(19) null 
/*��������*/,
approvenote varchar2(1024) null 
/*��������*/,
creator varchar2(20) default '~' null 
/*������*/,
creationtime char(19) null 
/*����ʱ��*/,
modifier varchar2(20) default '~' null 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
pk_busitype varchar2(20) default '~' null 
/*ҵ������0*/,
pk_billtypeid varchar2(50) null 
/*������������*/,
pk_billtypecode varchar2(50) null 
/*��������*/,
issuebank varchar2(20) default '~' null 
/*��������*/,
billstatus integer null 
/*���״̬*/,
gatheringaccount varchar2(20) default '~' null 
/*�տ��˻�*/,
investaccount varchar2(20) default '~' null 
/*����˻�*/,
redeemdate char(19) null 
/*�������*/,
redeemmoney number(28,8) null 
/*��ؽ��*/,
interestday integer null 
/*��������*/,
expectedrate number(28,8) null 
/*Ԥ��������*/,
lastdate char(19) null 
/*�ϴ��������*/,
lastmoney number(28,8) null 
/*�ϴ�����*/,
redeemplan varchar2(50) null 
/*��ؼƻ�*/,
incomedate char(19) null 
/*��������*/,
incomerate number(28,8) null 
/*����˰��*/,
incomemoney number(28,8) null 
/*����˰��*/,
balance number(28,8) null 
/*ʵ����Ԥ�ڲ��*/,
productcode varchar2(20) default '~' null 
/*��Ʒ����*/,
productname varchar2(50) null 
/*��Ʒ����*/,
redeemnumber integer null 
/*��ط���*/,
redeemid varchar2(50) null 
/*��ر���*/,
vbilldate char(19) null 
/*�깺����*/,
unitnetvalue number(28,8) null 
/*��λ��ֵ*/,
capitalproject varchar2(50) null 
/*�ʽ�ƻ���Ŀ*/,
realreaning number(28,8) null 
/*ʵ������*/,
vbilltype integer null 
/*�깺״̬*/,
enddate char(19) null 
/*��������*/,
holdmoney number(28,8) null 
/*���н��*/,
 constraint pk_ifm_redeem primary key (pk_redeem),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

