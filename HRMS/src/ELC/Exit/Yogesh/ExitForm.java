package ELC.Exit.Yogesh;

//import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.IOException;
//import java.util.Date;
//import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
//import java.util.Set;
import java.util.concurrent.TimeUnit;


//import jxl.Cell;
//import jxl.Sheet;
//import jxl.Workbook;
//import jxl.read.biff.BiffException;
//import jxl.read.biff.BiffException;
//import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.ITestContext;
import org.testng.ITestResult;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
//import org.testng.Assert;
//import org.testng.Assert;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import org.apache.log4j.Logger;

import org.testng.Assert;
//import org.testng.ITestContext;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;

//import testlink.api.java.client.TestLinkAPIClient;
//import testlink.api.java.client.TestLinkAPIException;
///import testlink.api.java.client.TestLinkAPIResults;

//import org.apache.log4j.PropertyConfigurator;


//import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;
import Excel.Excel;

//import com.test.LoggerCourseMaster;

//import Excel.Excel;
import Excel.ReadExcelMapping;
//import HRMS_Recruitment.Login1;
import baseClass.BaseClass;

public class ExitForm {
	//--------data write in excel sheet ----------- > 
	//  private WebDriver driver1;
	//  private String baseUrl;
	   HSSFWorkbook workbook1;
	   HSSFSheet sheet;
	   Map<String, Object[]> testresultdata;

	 
	 private static Logger Log = Logger.getLogger(ExitForm.class.getName());
	public FileInputStream inputStream=null;
	BaseClass baseclass = new BaseClass();
	Excel dataXLS = new Excel();
	Properties prop = baseclass.PropertiesConfigurations(".//src//Properties//ELC.Exit.ExitForm.properties");
	WebDriver driver = baseclass.DriverConfigurations();
	ReadExcelMapping REM = new ReadExcelMapping();
	
	
	WebDriverWait wait = new WebDriverWait(driver, 180);
	    
		// ****************Login to Application****************
	@BeforeTest
	public void baseLogin() throws BiffException, IOException, InterruptedException, Exception{
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<Map<String, String>> map = REM.ReadExcel("D:\\HRMS DATA\\Datasheet\\ELC Exit\\ExitForm.xls","Sheet1");
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

	driver.findElement(By.linkText(prop.getProperty("Exitform_linktext"))).click();
	
		}
	
//---------------> ADD NEW DATA USING Data Mapping ---------------->	
		
	@Test(priority=2)
	public void ResignationData() throws Exception{
		
	//	String Exception = null;
		List<Map<String, String>> map = REM.ReadExcel("D:\\HRMS DATA\\Datasheet\\ELC Exit\\ExitForm.xls","Sheet2");
		
			
			try{

				Alert alert=driver.switchTo().alert();
				System.out.println(alert.getText());
				Thread.sleep(2000);
				alert.accept();
				
				driver.findElement(By.id(prop.getProperty("cancel_id"))).click();
				System.out.println("exit form already filled");
				Log.info("exit form already filled");
				
			}
			catch(Exception ex)
			{
				for(int i=0;i<map.size()-1;i++){		
			try{
				driver.findElement(By.id(prop.getProperty("1stquestion_id"))).sendKeys(map.get(i).get("1st question"));
				System.out.println("1st answer inserted successfully");
				Log.info("1st answer inserted successfully");
			}
			catch(Exception e){
					System.out.println("answer not inserted");
					Log.info("answer not inserted");
					e.getMessage();
			}
		
			try{
				
			wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("2ndquestion_id"))));
			driver.findElement(By.id(prop.getProperty("2ndquestion_id"))).clear();
			driver.findElement(By.id(prop.getProperty("2ndquestion_id"))).sendKeys(map.get(i).get("2nd question"));
			System.out.println("2nd answer inserted successfully");
			Log.info("2nd answer inserted successfully");
			}
			catch(Exception e){
				System.out.println("answer not inserted");
				Log.info("answer not inserted");
				e.getMessage();
				
			}
			try{
				driver.findElement(By.id(prop.getProperty("3rdquestion_id"))).sendKeys(map.get(i).get("3rd question"));
				System.out.println("3rd answer inserted successfully");
				Log.info("3rd answer inserted successfully");
			}
			catch(Exception e){
				e.getMessage();
				System.out.println("answer not inserted");
				Log.info("answer not inserted");
			
			}
			
			try{
				wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("submitButton_id"))));
			driver.findElement(By.id(prop.getProperty("submitButton_id"))).click();
			System.out.println("record submitted successfully");
			Log.info("record submitted successfully");
			}
			catch(Exception e){
				e.getMessage();
				System.out.println("record submitted successfully");
				Log.info("record submitted successfully");
				Assert.fail();
			}
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
				
				
			
			
				
			
			
			