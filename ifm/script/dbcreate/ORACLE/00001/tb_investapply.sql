/* tablename: ifm_apply */
create table ifm_apply (pk_apply char(20) not null 
/*����*/,
vbillno varchar2(40) null 
/*�깺���*/,
vbillstatus varchar2(50) null 
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
glcmoney number(28,8) null 
/*���ű��ҽ��*/,
gllcrate number(15,8) null 
/*ȫ�ֱ��һ���*/,
gllmoney number(28,8) null 
/*ȫ�ֱ��ҽ��*/,
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
/*ҵ������*/,
pk_billtypeid varchar2(50) null 
/*������������*/,
pk_billtypecode varchar2(50) null 
/*��������*/,
issuebank varchar2(20) default '~' null 
/*���л���*/,
boundary integer null 
/*������*/,
banknetwork varchar2(20) default '~' null 
/*��������*/,
billstatus integer null 
/*����״̬*/,
settleaccount varchar2(20) default '~' null 
/*�����˻�*/,
invest varchar2(20) default '~' null 
/*����˻�*/,
productcode varchar2(50) null 
/*��Ʒ����*/,
productname varchar2(50) null 
/*��Ʒ����*/,
money number(28,8) null 
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
interestday varchar2(50) null 
/*��������*/,
expectedrate number(28,8) null 
/*Ԥ��������*/,
expectedmoney number(28,8) null 
/*Ԥ������*/,
paytype integer null 
/*��Ϣ����*/,
payperiod integer null 
/*��Ϣ����*/,
settledate varchar2(20) default '~' null 
/*��Ϣ��*/,
applynumber integer null 
/*�깺����*/,
capitalproject varchar2(20) default '~' null 
/*�ʽ�ƻ���Ŀ*/,
investvariety varchar2(20) default '~' null 
/*Ͷ��Ʒ��*/,
limitday integer null 
/*����*/,
holdmoney number(28,8) null 
/*���н��*/,
holdnumber integer null 
/*���з���*/,
unitnetvalue number(28,8) null 
/*��λ��ֵ*/,
 constraint pk_ifm_apply primary key (pk_apply),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

