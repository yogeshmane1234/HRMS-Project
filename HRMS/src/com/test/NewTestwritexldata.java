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
public class NewTestwritexldata {
  private WebDriver browser;
  private String baseUrl;
  //define an Excel Work Book
  HSSFWorkbook workbook;
  //define an Excel Work sheet
  HSSFSheet sheet;
  //define a test result data object
  Map<String, Object[]> testresultdata;
  
  @Test(description="Launches the Selenium Master Test Application and Login")
  public void launchSiteAndLogin() throws InterruptedException{
    browser.get(baseUrl);
    /* for (int second = 0;; second++) {
          if (second >= 60) Assert.fail("timeout");
          try { if (isElementPresent(By.cssSelector("img[alt=\"Selenium Master\"]"))) 
            break; } catch (Exception e) {}
          Thread.sleep(1000);
        }*/
    browser.findElement(By.id("Login1_UserName")).clear();
    browser.findElement(By.id("Login1_UserName")).sendKeys("admin crm");
    browser.findElement(By.id("Login1_Password")).clear();
    browser.findElement(By.id("Login1_Password")).sendKeys("abcd"); //password is omitted
    browser.findElement(By.id("Login1_LoginButton")).click();
    try{
      assertEquals(browser.findElement(By.cssSelector("ul.cr > li > a")).getText(),"Test Selenium");
      //add pass entry to the excel sheet
      testresultdata.put("2", new Object[] {1d, "navigate to site and login", "site opens and login success","Pass"});
    }
    
    catch(Exception e)
    {
      //add fail entry to the excel sheet
      testresultdata.put("2", new Object[] {1d, "navigate to site and login", "site opens and login success","Fail"});
    }
  }
    
  @Test(description="Navigates to the User Settings page")
    public void openUserSettingPage() throws InterruptedException {
    browser.findElement(By.id("ImgBtn_HR")).click();
    
   /*  for (int second = 0;; second++) {
          if (second >= 60) Assert.fail("timeout");
          try { if (isElementPresent(By.id("login_login_username"))) 
            break; } catch (Exception e) {}
          Thread.sleep(1000);
        }
    
     browser.findElement(By.id("login_login_username")).clear();
     browser.findElement(By.id("login_login_username")).sendKeys("test");
     browser.findElement(By.id("login_login_password")).clear();
     browser.findElement(By.id("login_login_password")).sendKeys("XXXX");//password is omitted
     browser.findElement(By.id("login_submit")).click();
    
     for (int second = 0;; second++) {
          if (second >= 60) Assert.fail("timeout");
          try { if (isElementPresent(By.xpath("//input[@value='auth']"))) 
            break; } catch (Exception e) {}
          Thread.sleep(1000);
        }*/
    
     try{
       assertTrue(isElementPresent(By.xpath("//input[@value='auth']")));
      //add pass entry to the excel sheet
        testresultdata.put("3", new Object[] {2d, "navigate to User Settings Page", "Page Displayed","Pass"});
      }
      
      catch(Exception e)
      {
        //add fail entry to the excel sheet
        testresultdata.put("3", new Object[] {2d, "navigate to User Settings Page", "Page Not Displayed","Fail"});
      }
    
    
  }
    
/*  @Test(description="Change a User settings to add as a friends after authorization")
    public void ChangeUserSettings() {
    browser.findElement(By.xpath("//input[@value='auth']")).click();
    browser.findElement(By.id("accountprefs_submit")).click();
    try{
      assertEquals(browser.findElement(By.cssSelector("div.ok")).getText(), "Preferences saved");
      //add pass entry to the excel sheet
        testresultdata.put("4", new Object[] {3d, "User can change settings", "Settings changed","Pass"});
      }
      
      catch(Exception e)
      {
        //add fail entry to the excel sheet
        testresultdata.put("4", new Object[] {3d, "User can change settings", "Settings NOT changed","Fail"});
      }
    
  }*/
   
  @Test(description="Log out the system")
  public void Logout() throws InterruptedException {
   /* for (int second = 0;; second++) {
        if (second >= 60) Assert.fail("timeout");
        try { if (isElementPresent(By.linkText("Logout"))) 
          break; } catch (Exception e) {}
        Thread.sleep(1000);
      }*/
    browser.findElement(By.linkText("Logout")).click();
    Thread.sleep(2000);
    try{
      assertTrue(isElementPresent(By.id("Login1_UserName")));
      //add pass entry to the excel sheet
      testresultdata.put("5", new Object[] {4d, "User can logout", "Logout successfull","Pass"});
      }
      
      catch(Exception e)
      {
        //add fail entry to the excel sheet
        testresultdata.put("5", new Object[] {4d, "User can logout", "Logout successfull","Fail"});
      }
    
  }

  @BeforeClass(alwaysRun = true)
  public void setupBeforeSuite(ITestContext context) {
     baseUrl = "http://demo.osource.co.in/ERPONEX_INT_WEBAPP/Login.aspx";
     //create a new work book
      workbook = new HSSFWorkbook();
      //create a new work sheet
       sheet = workbook.createSheet("Test Result");
      testresultdata = new LinkedHashMap<String, Object[]>();
      //add test result excel file column header
      //write the header in the first row
      testresultdata.put("1", new Object[] {"Test Step Id", "Action", "Expected Result","Actual Result"});
      
    try {
      
     browser=new FirefoxDriver();
     browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     } catch (Exception e) {
      throw new IllegalStateException("Can't start Web Driver", e);
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
        FileOutputStream out =new FileOutputStream(new File("D:\\HRMS DATA\\WriteDataSheet\\YogeshnewData.xls"));
        workbook.write(out);
        out.close();
        System.out.println("Excel written successfully..");
         
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    //close the browser
    browser.close();
    browser.quit();
  }
  
    private boolean isElementPresent(By by) {
        try {
          browser.findElement(by);
          return true;
        } catch (NoSuchElementException e) {
          return false;
        }
      }
}