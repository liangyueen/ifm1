/* tablename: ifm_income */
create table ifm_income (pk_income char(20) not null 
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
olcmoeny number(28,8) null 
/*��֯���ҽ��*/,
glcrate number(15,8) null 
/*���ű��һ���*/,
glcmoeny number(28,8) null 
/*���ű��ҽ��*/,
gllcrate number(15,8) null 
/*ȫ�ֱ��һ���*/,
gllmoeny number(28,8) null 
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
productcode varchar2(20) default '~' null 
/*��Ʒ����*/,
productname varchar2(50) null 
/*��Ʒ����*/,
issuebank varchar2(20) default '~' null 
/*��������*/,
billstatus varchar2(50) null 
/*����״̬*/,
settleaccount varchar2(20) default '~' null 
/*�տ������˻�*/,
invest varchar2(20) default '~' null 
/*����˻�*/,
interestday int(10) null 
/*��Ϣ����*/,
expectedrate number(28,8) null 
/*Ԥ��������*/,
expectedmoney number(28,8) null 
/*Ԥ������*/,
enddate char(19) null 
/*��������*/,
actualmoeny number(28,8) null 
/*ʵ������*/,
incomerate number(28,8) null 
/*����˰��*/,
incomemoney number(28,8) null 
/*����˰��*/,
source integer null 
/*��Դ*/,
incomefundplanpro varchar2(40) default '~' null 
/*�����ʽ�ƻ���Ŀ*/,
taxfundplanpro varchar2(40) default '~' null 
/*˰���ʽ�ƻ���Ŀ*/,
investvariety varchar2(20) default '~' null 
/*Ͷ��Ʒ��*/,
incomedate char(19) null 
/*��������*/,
 constraint pk_ifm_income primary key (pk_income),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

