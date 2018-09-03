package nc.tc.nc.vo.ifm.redeem;
import org.testng.*;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.io.Serializable;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.yonyou.uat.util.ExcelDataProvider;
import com.yonyou.uat.util.DBDataProvider;
import com.yonyou.uat.dbmanagement.DBManage;
import com.yonyou.uat.dbmanagement.QueryInfoVO;
import nc.vo.ifm.redeem.AggInvestRedeemVO;
import nc.bs.framework.common.NCLocator;
import com.yonyou.uat.framework.BaseTestCase;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.ifm.redeem.InvestRedeemVO;
public class AggInvestRedeemVOTest extends BaseTestCase {
  AggInvestRedeemVO aggInvestRedeemVO=null;
  DBManage dbManage=null;
  
  @BeforeClass 
  public void BeforeClass(){
    aggInvestRedeemVO=NCLocator.getInstance().lookup(AggInvestRedeemVO.class);
  }
  
  @AfterClass 
  public void AfterClass(){
  }
  
  @BeforeMethod 
  public void BeforeMethod(){
    List<String> tableList=new ArrayList<String>();
    tableList.add("pub_wfexptlog");
    dbManage=new DBManage();
    dbManage.setTableList(tableList);
    dbManage.tableExport();
  }
  
  @AfterMethod 
  public void AfterMethod(){
    dbManage.tableRollBack();
  }
  
  @Test(description="",dependsOnMethods={},groups="",timeOut=100000,dataProvider="dp") 
  public void getMetaData(  Map<String,ArrayList<String>> dp){
    
    //Construct method parameters
    
    //Invoke tested method
    IBillMeta retObj=null;
    retObj=aggInvestRedeemVO.getMetaData();
    
    //Verify result is ok
    
    //Verify Object1 == Object2
    Assert.assertNotNull(retObj);
    Assert.assertNotNull(retObj.getBusinessAttribute());
    Assert.assertEquals(retObj.getBusinessAttribute().size(),0);
    Assert.assertNotNull(retObj.getChildForeignKeys());
    Assert.assertNotNull(retObj.getChildren());
    Assert.assertNotNull(retObj.getComponentName());
    Assert.assertEquals(retObj.getComponentName(),"expectValue");
    Assert.assertNotNull(retObj.getParent());
    
    //Verify DB result is ok
    QueryInfoVO queryInfoVerify=new QueryInfoVO();
    queryInfoVerify.setDatasource("datasourceName");
    queryInfoVerify.setTableName("tableName");
    queryInfoVerify.setCondition("where condition");
    List<Object> actualObjects=super.getDBObjectClass(Object.class,queryInfoVerify);
    Object actualObject=(Object)actualObjects.get(0);
    Assert.assertEquals("actualObject.getxxx()",dp.get("colName").get(0));
    
    //Verify whether have exception information in log 
    super.verifyLog("Error key word");
  }
  
  @Test(description="",dependsOnMethods={},groups="",timeOut=100000,dataProvider="dp") 
  public void getParentVO(  Map<String,ArrayList<String>> dp){
    
    //Construct method parameters
    
    //Invoke tested method
    InvestRedeemVO retObj=new InvestRedeemVO();
    retObj=aggInvestRedeemVO.getParentVO();
    
    //Verify result is ok
    
    //Verify Object1 == Object2
    Assert.assertNotNull(retObj);
    Assert.assertNotNull(retObj.getBalance());
    Assert.assertEquals(retObj.getBalance(),"expectValue");
    Assert.assertNotNull(retObj.getBillstatus());
    Assert.assertEquals(retObj.getBillstatus(),Integer.valueOf("0"));
    Assert.assertNotNull(retObj.getExpectedrate());
    Assert.assertEquals(retObj.getExpectedrate(),"expectValue");
    Assert.assertNotNull(retObj.getGatheringaccount());
    Assert.assertEquals(retObj.getGatheringaccount(),"expectValue");
    Assert.assertNotNull(retObj.getGlcmoeny());
    Assert.assertEquals(retObj.getGlcmoeny(),"expectValue");
    Assert.assertNotNull(retObj.getGllmoeny());
    Assert.assertEquals(retObj.getGllmoeny(),"expectValue");
    Assert.assertNotNull(retObj.getIncomedate());
    Assert.assertNotNull(retObj.getIncomemoney());
    Assert.assertEquals(retObj.getIncomemoney(),"expectValue");
    Assert.assertNotNull(retObj.getIncomerate());
    Assert.assertEquals(retObj.getIncomerate(),"expectValue");
    Assert.assertNotNull(retObj.getInterestday());
    Assert.assertEquals(retObj.getInterestday(),Integer.valueOf("0"));
    Assert.assertNotNull(retObj.getInvestaccount());
    Assert.assertEquals(retObj.getInvestaccount(),"expectValue");
    Assert.assertNotNull(retObj.getIssuebank());
    Assert.assertEquals(retObj.getIssuebank(),"expectValue");
    Assert.assertNotNull(retObj.getLastdate());
    Assert.assertNotNull(retObj.getLastmoney());
    Assert.assertEquals(retObj.getLastmoney(),"expectValue");
    Assert.assertNotNull(retObj.getMetaData());
    Assert.assertNotNull(retObj.getOlcmoney());
    Assert.assertEquals(retObj.getOlcmoney(),"expectValue");
    Assert.assertNotNull(retObj.getRedeemdate());
    Assert.assertNotNull(retObj.getRedeemmoney());
    Assert.assertEquals(retObj.getRedeemmoney(),"expectValue");
    Assert.assertNotNull(retObj.getRedeemplan());
    Assert.assertEquals(retObj.getRedeemplan(),"expectValue");
    Assert.assertNotNull(retObj.getTs());
    Assert.assertNotNull(retObj.getVdef8());
    Assert.assertEquals(retObj.getVdef8(),"expectValue");
    Assert.assertNotNull(retObj.getVdef9());
    Assert.assertEquals(retObj.getVdef9(),"expectValue");
    
    //Verify Return or middle Object == expect Object(from object file)
    Object expectedObj=super.getExpectResultObject("caseName");
    if (expectedObj != null) {
      Assert.assertEquals(retObj,expectedObj);
    }
 else {
      super.saveResultObject((Serializable)retObj,"caseName");
    }
    
    //Verify DB result is ok
    QueryInfoVO queryInfoVerify=new QueryInfoVO();
    queryInfoVerify.setDatasource("datasourceName");
    queryInfoVerify.setTableName("tableName");
    queryInfoVerify.setCondition("where condition");
    List<Object> actualObjects=super.getDBObjectClass(Object.class,queryInfoVerify);
    Object actualObject=(Object)actualObjects.get(0);
    Assert.assertEquals("actualObject.getxxx()",dp.get("colName").get(0));
    
    //Verify whether have exception information in log 
    super.verifyLog("Error key word");
  }
}
