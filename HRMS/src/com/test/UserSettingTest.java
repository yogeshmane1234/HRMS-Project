package com.test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class UserSettingTest {
  private WebDriver driver;
  private String baseUrl;
  //define an Excel Work Book
  HSSFWorkbook workbook;
  //define an Excel Work sheet
  HSSFSheet sheet;
  //define a test result data object
  Map<String, Object[]> testresultdata;
  
  @BeforeClass(alwaysRun = true)
  public void setupBeforeSuite(ITestContext context) {
     baseUrl = "http://demo.osource.co.in/ERPONEX_INT_WEBAPP/Login.aspx";
     //create a new work book
      workbook = new HSSFWorkbook();
      //create a new work sheet
       sheet = workbook.createSheet("Test yogesh Result");
      testresultdata = new LinkedHashMap<String, Object[]>();
      //add test result excel file column header
      //write the header in the first row
      testresultdata.put("1", new Object[] {"Test Step Id","Expected Result", "Actual Result","Status"});
      
    try {
      
     driver=new FirefoxDriver();
     driver.manage().window().maximize();
     driver.get("http://demo.osource.co.in/ERPONEX_INT_WEBAPP/Login.aspx");
    // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     } catch (Exception e) {
      throw new IllegalStateException("Can't start Web Driver", e);
    }
    
  }
  
  @Test(priority=0)
  public void launchSiteAndLogin() throws InterruptedException, IOException{
   
   /*  for (int second = 0;; second++) {
          if (second >= 60) Assert.fail("timeout");
          try { if (isElementPresent(By.id("Login1_UserName"))) 
            break; } catch (Exception e) {}
          Thread.sleep(1000);
        }*/
     driver.findElement(By.id("Login1_UserName")).clear();
     driver.findElement(By.id("Login1_UserName")).sendKeys("admin crm");
     driver.findElement(By.id("Login1_Password")).clear();
     driver.findElement(By.id("Login1_Password")).sendKeys("abcd"); //password is omitted
     driver.findElement(By.id("Login1_LoginButton")).click();
    try{
    	
     Assert.assertEquals(driver.getTitle(),"Onex Softwar");
      //add pass entry to the excel sheet
      testresultdata.put("2", new Object[] {1d,"navigate to site and login", "site opens and login success","Pass"});
      System.out.println("pass test case");
      }
    
    catch(AssertionError e)
    {
    	
      //add fail entry to the excel sheet
      testresultdata.put("2", new Object[] {1d, "navigate to site and login", "site not opens and login unsuccess","Fail"});
     System.out.println("catch value printed"); 
     Assert.fail();
    }
  }
    
 @Test(priority=1)
    public void openUserSettingPage() throws InterruptedException {
    driver.findElement(By.id("ImgBtn_HR")).click();
  /* 
     for (int second = 0;; second++) {
          if (second >= 60) Assert.fail("timeout");
          try { if (isElementPresent(By.linkText("PMS"))) 
            break; } catch (Exception e) {}
          Thread.sleep(1000);
        }
    */
    	
     try{
    	 Assert.assertEquals(driver.getTitle(),"Osource Demo");
     
            //add pass entry to the excel sheet
        testresultdata.put("3", new Object[] {3d, "navigate to User Settings Page", "Page Displayed","Pass"});
        System.out.println("pass test case");
      }
      
    
    catch(AssertionError e)
      {
    	 System.out.println("exception inbox");
    	  testresultdata.put("3", new Object[] {2d, "navigate to User Settings Page", "Page Not Displayed","Fail"});
    	  Assert.fail();
    	  
      }
    
    
  }
    
/*  @Test(description="Change a User settings to add as a friends after authorization")
    public void ChangeUserSettings() {
    driver.findElement(By.xpath("//input[@value='auth']")).click();
    driver.findElement(By.id("accountprefs_submit")).click();
    try{
      assertEquals(driver.findElement(By.cssSelector("div.ok")).getText(), "Preferences saved");
      //add pass entry to the excel sheet
        testresultdata.put("4", new Object[] {3d, "User can change settings", "Settings changed","Pass"});
      }
      
      catch(Exception e)
      {
        //add fail entry to the excel sheet
        testresultdata.put("4", new Object[] {3d, "User can change settings", "Settings NOT changed","Fail"});
      }
    
  }
   */
  @Test(priority=2)
  public void Logout() throws InterruptedException {
  /*  for (int second = 0;; second++) {
        if (second >= 60) Assert.fail("timeout");
        try { if (isElementPresent(By.linkText("Logout"))) 
          break; } catch (Exception e) {}
        Thread.sleep(1000);
      }*/
	  driver.findElement(By.linkText("Logout")).click();
    try{
    	System.out.println("start"); 
      assertTrue(isElementPresent(By.id("Login1_UserNam")));
    	//assertTrue(true);
    	//Assert
    	//driver.findElement(By.id("Login1_UserNam")).isDisplayed();
      //add pass entry to the excel sheet
      testresultdata.put("4", new Object[] {3d, "User can logout", "Logout successfull","Pass"});
      
    	//System.out.println("pass test case");
     
      }
      
      catch(AssertionError e)
      {
    	 
        //add fail entry to the excel sheet
        testresultdata.put("4", new Object[] {3d, "User can logout", "Logout unsuccessfull","Fail"});
        System.out.println("catch printed"); 
    	  Assert.fail();
      }
    
  }

   @AfterClass
  public void setupAfterSuite() {
    //write excel file and file name is TestResult.xls 
    Set<String> keyset = testresultdata.keySet();
    int rownum = 0;
    for (String key : keyset) {
        Row row = sheet.createRow(rownum++);
        Object [] objArr = testresultdata.get(key);
        int cellnum = 0;
        for (Object obj : objArr) {
            Cell cell = row.createCell(cellnum++);
            if(obj instanceof Date) 
                cell.setCellValue((Date)obj);
            else if(obj instanceof Boolean)
                cell.setCellValue((Boolean)obj);
            else if(obj instanceof String)
                cell.setCellValue((String)obj);
            else if(obj instanceof Double)
                cell.setCellValue((Double)obj);
        }
    }
    try {
        FileOutputStream out =new FileOutputStream(new File("D:\\HRMS DATA\\WriteDataSheet\\YogeshData.xls"));
        workbook.write(out);
        out.close();
        System.out.println("Excel written successfully..");
         
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    //close the browser
   
    //driver.quit();
    driver.close();
  }
  
    private boolean isElementPresent(By by) {
        try {
          driver.findElement(by);
          return true;
        } catch (NoSuchElementException e) {
          return false;
        }
      }
}