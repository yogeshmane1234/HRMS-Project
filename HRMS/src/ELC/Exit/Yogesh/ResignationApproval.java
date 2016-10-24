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

public class ResignationApproval {
	//--------data write in excel sheet ----------- > 
	  private WebDriver driver1;
	  private String baseUrl;
	   HSSFWorkbook workbook1;
	   HSSFSheet sheet;
	   Map<String, Object[]> testresultdata;

	 
	 private static Logger Log = Logger.getLogger(ResignationApproval.class.getName());
	public FileInputStream inputStream=null;
	BaseClass baseclass = new BaseClass();
	Excel dataXLS = new Excel();
	Properties prop = baseclass.PropertiesConfigurations(".//src//Properties//ELC.Exit.ResignationApproval.properties");
	WebDriver driver = baseclass.DriverConfigurations();
	ReadExcelMapping REM = new ReadExcelMapping();
	
	
	WebDriverWait wait = new WebDriverWait(driver, 180);
	    
		// ****************Login to Application****************
	@BeforeTest
	public void baseLogin() throws BiffException, IOException, InterruptedException, Exception{
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<Map<String, String>> map = REM.ReadExcel("D:\\HRMS DATA\\Datasheet\\ELC Exit\\ResignationApproval.xls","Sheet1");
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

	driver.findElement(By.linkText(prop.getProperty("ELCResignationApproval_linktext"))).click();
		
		try{
			driver.getTitle().equals("Resignation Approval");
			
			System.out.println("Target Page open successfully ");
			Log.info("Target Page open successfully ");
			}
			catch(Exception e){
				System.out.println("Target Page is not open");
				Log.info("Target Page is not open ");
				Assert.fail();
			}
		}
		
//---------------> ADD NEW DATA USING Data Mapping ---------------->	
		
	@Test(priority=2)
	public void ResignationApprovalData() throws Exception{
		//System.setProperty("webdriver.chrome.driver",""));
	//baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "stm_linktext", driver);
		String exception = null;
		List<Map<String, String>> map = REM.ReadExcel("D:\\HRMS DATA\\Datasheet\\ELC Exit\\ResignationApproval.xls","Sheet1");
		for(int i=0;i<map.size()-1;i++){
			
			try{
				
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText(map.get(i).get("EmployeeName"))));
				driver.findElement(By.linkText(map.get(i).get("EmployeeName"))).click();
				System.out.println("resignation employee page opened successfully");
				Log.info("resignation employee page opened successfully");
			}
			catch(Exception e){
					System.out.println("resignation employee not found");
					Log.info("resignation employee not found");
					exception = e.getMessage();
			}
		
		
			try{
				driver.findElement(By.id(prop.getProperty("SaveButton_id"))).click();
			System.out.println("request approved successfully");
			Log.info("request approved successfully");
			}
			catch(Exception e ){
				exception = e.getMessage();
				
			}
			try{
				
				assertTrue(isElementPresent(By.id(prop.getProperty("searchbox_id"))));
				
				System.out.println("request approved or reject successfully");
				Log.info("request approved or reject successfully");				
				
				testresultdata.put(map.get(i).get("EmployeeName"), new Object[] {map.get(i).get("EmployeeName"),map.get(i).get("Status"),"employee request should be approve or reject ","employee request is approve or reject successfully","Passed"});
								
			}
			catch(AssertionError e){
				
				driver.findElement(By.id(prop.getProperty("cancel_id"))).click();
				System.out.println("employee neither approve nor reject");
				Log.info("employee neither approve nor reject");	
				testresultdata.put(map.get(i).get("EmployeeName"), new Object[] {map.get(i).get("EmployeeName"),map.get(i).get("Status"),"employee request should be approve or reject ","employee neither approve nor reject","Failed"});
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
				
				@BeforeClass
				public void datawritebefore(ITestContext context){
					//baseUrl = "http://demo.osource.co.in/ERPONEX_INT_WEBAPP/Login.aspx";
				     //create a new work book
				      workbook1 = new HSSFWorkbook();
				      //create a new work sheet
				       sheet = workbook1.createSheet("Test yogesh Result");
				      testresultdata = new LinkedHashMap<String, Object[]>();
				      //add test result excel file column header
				      //write the header in the first row
				               
				      testresultdata.put("1", new Object[] {"EmployeeName","Status","Expected Result","Actual Result","Status"});
				       
			}
				
				@AfterClass
				
				public void datawriteafter(){
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
				         FileOutputStream out =new FileOutputStream(new File("D:\\HRMS DATA\\WriteDataSheet\\ResignationapprovalstatusData.xls"));
				         workbook1.write(out);
				         out.close();
				         System.out.println("Excel written successfully..");
				          
				     } catch (FileNotFoundException e) {
				         e.printStackTrace();
				     } catch (IOException e) {
				         e.printStackTrace();
				     }
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
		
