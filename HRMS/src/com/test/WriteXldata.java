package com.test;

import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WriteXldata {
	 private WebDriver driver;

	  @BeforeTest
	 public void setUp() throws Exception {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 }

	  @Test
	 public void test() throws Exception {
	  driver.get("http://demo.osource.co.in/ERPONEX_INT_WEBAPP/Login.aspx");
	  driver.findElement(By.id("Login1_UserName")).sendKeys("admin crm");
	  driver.findElement(By.id("Login1_Password")).sendKeys("abcd");
	  driver.findElement(By.id("Login1_LoginButton")).click();
	 // driver.findElement(By.linkText("Software testing - Wikipedia, the free encyclopedia")).click();
	  String s = driver.getTitle();
	  writereport(s);
	  
	 }

	  @AfterTest
	 public void tearDown() throws Exception {
	  driver.quit();
	 }


	public void writereport(String text) 
	       { 
	        try
	        {
	       FileOutputStream f = new FileOutputStream("D:\\HRMS DATA\\WriteDataSheet\\WriteData.xls",true);
	       WritableWorkbook book = Workbook.createWorkbook(f); 
	       WritableSheet sheet = book.createSheet("output", 2);
	       Label l = new Label(1, 1, text);
	       Label l1 = new Label(2, 2, text);
	       sheet.addCell(l);
	       sheet.addCell(l1);
	       book.write(); 
	       book.close(); 
	        }
	        catch (Exception e)
	        {
	         e.printStackTrace();
	        }
	     }
}
