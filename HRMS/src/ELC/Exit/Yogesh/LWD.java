package ELC.Exit.Yogesh;

import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import jxl.Cell;
//import jxl.Sheet;
//import jxl.Workbook;
//import jxl.read.biff.BiffException;
//import jxl.read.biff.BiffException;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
//import org.testng.Assert;
//import org.testng.Assert;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import org.apache.log4j.Logger;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

import org.apache.log4j.PropertyConfigurator;











//import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;
import Excel.Excel;

import com.test.LoggerCourseMaster;

import Excel.Excel;
import Excel.ReadExcelMapping;
//import HRMS_Recruitment.Login1;
import baseClass.BaseClass;

public class LWD {
	//--------data write in excel sheet ----------- > 
	  private WebDriver driver1;
	  private String baseUrl;
	   HSSFWorkbook workbook1;
	   HSSFSheet sheet;
	   Map<String, Object[]> testresultdata;

	 
	 private static Logger Log = Logger.getLogger(LWD.class.getName());
	public FileInputStream inputStream=null;
	BaseClass baseclass = new BaseClass();
	Excel dataXLS = new Excel();
	Properties prop = baseclass.PropertiesConfigurations(".//src//Properties//ELC.Exit.LWD.properties");
	WebDriver driver = baseclass.DriverConfigurations();
	ReadExcelMapping REM = new ReadExcelMapping();
	
	
	WebDriverWait wait = new WebDriverWait(driver, 180);
	    
		// ****************Login to Application****************
	@BeforeTest
	public void baseLogin() throws BiffException, IOException, InterruptedException, Exception{
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<Map<String, String>> map = REM.ReadExcel("D:\\HRMS DATA\\Datasheet\\ELC Exit\\LWD.xls","Sheet1");
		for(int i=0;i<map.size()-1;i++){
			
			baseclass.login(map.get(i).get("Username"),map.get(i).get("Password"), driver);
			System.out.println("TEST 1 : Login credintials inserted successfully");
			Log.info("Login credintials inserted successfully");
		}
	}
		
		@Test
		public void Login(){
		
		//driver.getTitle().equalsIgnoreCase("Onex Software");
		try{
			driver.getTitle().equalsIgnoreCase("Onex Software");
			System.out.println("TEST 1 : Login successfully");
			Log.info("Login successfully ");
		}
		catch(Exception e){
			System.out.println("TEST 1 : page crashed");
			Log.info("page crashed ");
			Assert.fail();
			
		}
	}
		@Test
		public void PageOpen() throws Exception{
			
			Actions action = new Actions(driver);
			baseclass.CommomSection3("HR_id",driver);

	
		WebElement element1 = driver.findElement(By.linkText(prop
			.getProperty("ELC_Linktext")));
		action.moveToElement(element1).perform();
			Thread.sleep(1000);
			
		WebElement element2 = driver.findElement(By.linkText(prop
				.getProperty("ELCExit_linktext")));
		action.moveToElement(element2).perform();

	driver.findElement(By.linkText(prop.getProperty("lwd_linktext"))).click();
		
		try{
			driver.getTitle().equals("LWD Updation");
			
			System.out.println("Target Page open successfully ");
			Log.info("Target Page open successfully ");
			}
			catch(Exception e){
				System.out.println("Target Page is not open");
				Log.info("Target Page is not open ");
				Assert.fail();
			}
		}
		
		@Test
		public void Search() throws IOException, InterruptedException{
			List<Map<String, String>> map = REM.ReadExcel("D:\\HRMS DATA\\Datasheet\\ELC Exit\\LWD.xls","Sheet1");
			wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("dropdown_id"))));
			Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("dropdown_id")))); 
			for(int i=0;i<map.size()-1;i++){
			try{
				dropdown.selectByVisibleText(map.get(i).get("dropdown value"));
				System.out.println("dropdown value selected successfully");
				Log.info("dropdown value selected successfully");
			}
			catch(Exception e){
				e.getMessage();
				System.out.println("dropdown value is not select");
				Log.info("dropdown value is not select");
			
			}
			try{
			
			driver.findElement(By.id(prop.getProperty("searchbox_id"))).sendKeys(map.get(i).get("EmployeeName"));
			driver.findElement(By.id(prop.getProperty("searchbutton_id"))).click();
			}
			catch(Exception e){
				e.getMessage();
			}
			
			Thread.sleep(2000);
			try{
			
			driver.findElement(By.linkText(map.get(i).get("EmployeeName"))).click();
			System.out.println("Resignation employee page open successfully");
			Log.info("Resignation employee page open successfully");
			}
			catch(Exception e){
				Alert alert=driver.switchTo().alert();
				System.out.println(alert.getText());
				Thread.sleep(2000);
				alert.accept();
				e.getMessage();
				System.out.println("Resignation employee page not opened");
				Log.info("Resignation employee page not opened");
				Assert.fail();
			}
		}	
	}		
			@Test
			public void Update() throws IOException, InterruptedException{
			
			driver.findElement(By.id(prop.getProperty("savebutton_id"))).click();
			Alert alert=driver.switchTo().alert();
			System.out.println(alert.getText());
			Thread.sleep(2000);
			alert.accept();
			
			try{
				
				assertTrue(isElementPresent(By.id(prop.getProperty("searchbutton_id"))));
				System.out.println("LWD Update successfully");
				Log.info("LWD Update successfully");
				}
				catch(AssertionError e){
					
					e.getMessage();
					System.out.println("LWD not Updated ");
					Log.info("LWD not Updated ");
					Assert.fail();
				}
			}
		
			
	


		//---------------> taking screenshot----------------->
		@AfterMethod
		public void ErroScreenshot(ITestResult result)
		{
			if(ITestResult.FAILURE==result.getStatus())
		{
		try
		{
				TakesScreenshot ts=(TakesScreenshot)driver;
		
		File source=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("D:\\HRMS DATA\\ErrorScreenshot\\"+result.getName()+".png"));
		 
		System.out.println("Screenshot taken : Test Case failed");
		Log.info("Screenshot taken:Test Case failed");
		}
		catch (Exception e)
		{
		 
		System.out.println("Exception while taking screenshot "+e.getMessage());
			}
		}
		}	
		
	// **************Logout and close Browser**********
		@AfterTest
		public void Logout() throws BiffException, IOException,
				InterruptedException {
			baseclass.logout(driver);
			Thread.sleep(2000);
			driver.close();
			Thread.sleep(2000);
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
	//-----------------------------------------------end----------------->
				
				
			
			
				
			
			
			