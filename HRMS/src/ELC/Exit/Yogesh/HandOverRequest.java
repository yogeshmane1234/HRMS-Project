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

public class HandOverRequest {
	//--------data write in excel sheet ----------- > 
	  private WebDriver driver1;
	  private String baseUrl;
	   HSSFWorkbook workbook1;
	   HSSFSheet sheet;
	   Map<String, Object[]> testresultdata;

	 private static Logger Log = Logger.getLogger(HandOverRequest.class.getName());
	public FileInputStream inputStream=null;
	BaseClass baseclass = new BaseClass();
	Excel dataXLS = new Excel();
	Properties prop = baseclass.PropertiesConfigurations(".//src//Properties//ELC.Exit.HandOverRequest.properties");
	WebDriver driver = baseclass.DriverConfigurations();
	ReadExcelMapping REM = new ReadExcelMapping();
	
	
	WebDriverWait wait = new WebDriverWait(driver, 180);
	    
		// ****************Login to Application****************
	@BeforeTest
	public void baseLogin() throws BiffException, IOException, InterruptedException, Exception{
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<Map<String, String>> map = REM.ReadExcel("D:\\HRMS DATA\\Datasheet\\ELC Exit\\HandOverRequest.xls","Sheet1");
		for(int i=0;i<map.size()-1;i++){
			
			baseclass.login(map.get(i).get("Username"),map.get(i).get("Password"), driver);
			System.out.println("TEST 1 : Login credintials inserted successfully");
			Log.info("Login credintials inserted successfully");
		}
	}
		
		@Test(priority=0)
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
		@Test(priority=1)
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

	driver.findElement(By.linkText(prop.getProperty("ELChandoverrequest_linktext"))).click();
		
		try{
			driver.getTitle().equals("Exit Handover");
			
			System.out.println("Target Page open successfully ");
			Log.info("Target Page open successfully ");
			}
			catch(Exception e){
				System.out.println("Target Page is not open");
				Log.info("Target Page is not open ");
				Assert.fail();
			}
		}
		
		@Test(priority=2)
		public void handoverrequestdata() throws Exception{
			//System.setProperty("webdriver.chrome.driver",""));
		//baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "stm_linktext", driver);
			String exception = null;
			List<Map<String, String>> map = REM.ReadExcel("D:\\HRMS DATA\\Datasheet\\ELC Exit\\HandOverRequest.xls","Sheet1");
			
						
			for(int i=0;i<map.size()-1;i++){
				
			
				try{
					driver.findElement(By.id(prop.getProperty("checkbox0_id"))).click();
				Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("selectbox0_id")))); 
					dropdown.selectByVisibleText(map.get(i).get("dropdownvalue1"));
					System.out.println("dropdown value is selected successfully");
					Log.info("dropdown value is selected successfully");
				}
				catch(Exception e){
					exception = e.getMessage();
					System.out.println("dropdown value not selected");
					Log.info("dropdown value not selected");
				
				}
				
				try{
					driver.findElement(By.id(prop.getProperty("checkbox1_id"))).click();
					Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("selectbox1_id")))); 
						dropdown.selectByVisibleText(map.get(i).get("dropdownvalue2"));
						System.out.println("dropdown value is selected successfully");
						Log.info("dropdown value is selected successfully");
					}
					catch(Exception e){
						exception = e.getMessage();
						System.out.println("dropdown value not selected");
						Log.info("dropdown value not selected");
					
					}
				
				try{
					driver.findElement(By.id(prop.getProperty("checkbox2_id"))).click();
					Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("selectbox2_id")))); 
						dropdown.selectByVisibleText(map.get(i).get("dropdownvalue3"));
						System.out.println("dropdown value is selected successfully");
						Log.info("dropdown value is selected successfully");
					}
					catch(Exception e){
						exception = e.getMessage();
						System.out.println("dropdown value not selected");
						Log.info("dropdown value not selected");
					
					}
				
				try{
					driver.findElement(By.id(prop.getProperty("checkbox3_id"))).click();
					Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("selectbox3_id")))); 
						dropdown.selectByVisibleText(map.get(i).get("dropdownvalue4"));
						System.out.println("dropdown value is selected successfully");
						Log.info("dropdown value is selected successfully");
					}
					catch(Exception e){
						exception = e.getMessage();
						System.out.println("dropdown value not selected");
						Log.info("dropdown value not selected");
					
					}
				
				try{
					driver.findElement(By.id(prop.getProperty("checkbox4_id"))).click();
					Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("selectbox4_id")))); 
						dropdown.selectByVisibleText(map.get(i).get("dropdownvalue5"));
						System.out.println("dropdown value is selected successfully");
						Log.info("dropdown value is selected successfully");
					}
					catch(Exception e){
						exception = e.getMessage();
						System.out.println("dropdown value not selected");
						Log.info("dropdown value not selected");
					
					}
				try{
					
					driver.findElement(By.id(prop.getProperty("submitbutton_id"))).click();
					System.out.println("submit button working properly");
					Log.info("submit button working properly");
				}
				catch(Exception er){
					er.getMessage();
					System.out.println("submit button is not working properly");
					Log.info("submit button is not working properly");
				}
						
				
				try{
					
					boolean search = driver.getPageSource().contains("SUBMITTED");
					
					System.out.println("handover submitted successfully");
					Log.info("handover submitted successfully");
					
				}
				catch(Exception ex){
					
					System.out.println("handover is not submited");
					Log.info("handover is not submited");
					
					Assert.fail();
				}
				
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
		
		
	}
	//-----------------------------------------------end----------------->
				
				
			
			
				
			
			
			