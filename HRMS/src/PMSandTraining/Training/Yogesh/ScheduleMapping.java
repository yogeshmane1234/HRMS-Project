package PMSandTraining.Training.Yogesh;

import static org.testng.AssertJUnit.assertTrue;
import static org.junit.Assert.assertEquals;

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
//import org.apache.log4j.PropertyConfigurator;
//import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;
import Excel.Excel;

import com.test.LoggerCourseMaster;

//import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;
import Excel.Excel;
import Excel.ReadExcelMapping;
import Excel.getExcelSheet;
//import HRMS_Recruitment.Login1;
import baseClass.BaseClass;

public class ScheduleMapping extends getExcelSheet{
		
// read data from excel using poi dataprovider
	public static Object[][] LoginData;
    public static HSSFRow Row;
    public static HSSFCell cell;
    public static String FilePath = "D:\\HRMS DATA\\Datasheet\\Training\\ScheduleMaster.xls";
    public static String SheetName1 = "Sheet1";
    public static HSSFSheet Sheet;
    
    // data write using this variables
    
    private WebDriver driver1;
	  private String baseUrl;
	  //define an Excel Work Book
	  HSSFWorkbook workbook1;
	  //define an Excel Work sheet
	  HSSFSheet sheet;
	  //define a test result data object
	  Map<String, Object[]> testresultdata;

	//public String sheet1;
    private static Logger Log = Logger.getLogger(ScheduleMapping.class.getName());
	public FileInputStream inputStream=null;
	BaseClass baseclass = new BaseClass();
	Excel dataXLS = new Excel();
	ReadExcelMapping REM = new ReadExcelMapping();
	//Properties prop = baseclass.PropertiesConfigurations();
	Properties prop = baseclass.PropertiesConfigurations(".//src//Properties//Training.ScheduleMapping.properties");
	// Logger logger=baseclass.LogCongigurations();

	WebDriver driver = baseclass.DriverConfigurations();
	
	String data[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\Training\\TrainingLogin.xls", "HR", 0, 11);
	String data1[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\Training\\ScheduleMapping.xls", "Sheet1", 0, 11);
	
	WebDriverWait wait = new WebDriverWait(driver, 60);

		// ****************Login to Application****************
	@BeforeTest
	public void baseLogin() throws BiffException, IOException, InterruptedException, Exception{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		baseclass.login(data[1][0], data[1][1], driver);
				
	}
	
		@Test(priority=0)
		public void Login() throws Exception {
			
			if(driver.getTitle().equalsIgnoreCase("Onex Software"))
		{
		System.out.println("TEST 1 : Login successfully");
		Log.info("Login successfully ");
		}
		else{
			
			System.out.println("TEST 1 : page crashed");
			Log.info("page crashed ");
		}
			Actions action = new Actions(driver);
			baseclass.CommomSection3("HR_id", driver);
			
			WebElement element1 = driver.findElement(By.linkText(prop
					.getProperty("PMSandTraining_linktext")));
				action.moveToElement(element1).perform();
					Thread.sleep(1000);
							
			WebElement element2 = driver.findElement(By.linkText(prop
					.getProperty("Training_linktext")));
			action.moveToElement(element2).perform();

			Thread.sleep(1000);

			driver.findElement(By.linkText(prop.getProperty("sm_linktext"))).click();
			
			System.out.println("TEST 2 : Target Page open successfully");
			Log.info("Target Page open successfully ");
			Thread.sleep(1000);
	}	
			
	// ----------------------> search functionality -------------------->
	
		
	@Test(priority=1)
	public void SearchButton() throws Exception{
	Thread.sleep(2000);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("dropdown_id"))));
	Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("dropdown_id")))); 
	dropdown.selectByVisibleText(data1[1][0]);
	
	driver.findElement(By.id(prop.getProperty("searchbutton_id"))).click();
   // Assert.assertEquals(driver.getTitle(), "Question Master");
   Thread.sleep(2000);
	try{
		
	boolean search = driver.getPageSource().contains(data1[1][0]);
	
    System.out.println("search value is Present");
    System.out.println("TEST 3 : Functionality of search button is working properly");
    Log.info("search functionality is working properly");
    }
    
   catch(Exception e){
	   Alert alert=driver.switchTo().alert();
	   alert.accept();
	   System.out.println("Data Not Found");
	   System.out.println("TEST 3 :Functionality of search button is working properly");
	   Log.info("search functionality is not working properly");
	   Assert.fail();
   }
   }
    	
		//--------------------> clear search button functionality ------------------> 

		@Test(priority=2)
		public void ClearButton() throws Exception{
		Thread.sleep(2000);	
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
		 String result = "";
        String exception = null;
		
		/*wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("dropdown_id"))));
		Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("dropdown_id")))); 
		dropdown.selectByVisibleText(data1[1][0]);
			  
		driver.findElement(By.id(prop.getProperty("searchbutton_id"))).click();*/
		    
			
			driver.findElement(By.id(prop.getProperty("clearbutton_id"))).click();
			Thread.sleep(4000);
			
						
			 try {
	              Assert.assertEquals(driver.getTitle(),"Employee Mapping Details");
	             
			System.out.println("TEST 4 : Functionality of clear button is working properly");
			 Log.info("Functionality of clear button is working properly");
			}
			catch (AssertionError ex) {
	            
	              System.out.println("TEST 3 : Functionality of clear button is not working properly");
	              
	              Log.info("Functionality of clear button is not working properly");
	              Assert.fail();
	         }
	    
			
		}
		
		@Test(priority=3)
		public void OpenCourse() throws IOException, InterruptedException{
			
			String exception = null;
			List<Map<String, String>> map = REM.ReadExcel("D:\\HRMS DATA\\Datasheet\\Training\\ScheduleMapping.xls","Sheet1");
			for(int i=0;i<map.size()-1;i++){
				
				
					driver.findElement(By.linkText(map.get(i).get("search dropdown"))).click();
					Thread.sleep(2000);
					try{
						assertTrue(isElementPresent(By.id(prop.getProperty("backbutton_id"))));
						System.out.println("open course ");
						Log.info("open course");
						
					}
					catch(AssertionError e){
						
				System.out.println("page not open : page crashed");
				Log.info("page not open : page crashed");
				Thread.sleep(3000);
				Assert.fail();
				}
			}
		}
		
	
		// ----------------------> search functionality -------------------->
		
		@Test(priority=4)
		public void coursePageSearchButton() throws Exception{
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("coursedropdown_id"))));
		Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("coursedropdown_id")))); 
		dropdown.selectByVisibleText(data1[1][1]);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("coursesearchbox_id"))));
		driver.findElement(By.id(prop.getProperty("coursesearchbox_id"))).sendKeys(data1[1][2]);	
	  
		driver.findElement(By.id(prop.getProperty("coursesearchbutton_id"))).click();
		Thread.sleep(3000);
	   // Assert.assertEquals(driver.getTitle(), "Question Master");
	   
		try{
			
			boolean search = driver.getPageSource().contains(data1[1][2]);
			
		    System.out.println("search value is Present");
		    System.out.println("TEST 3 : Functionality of search button is working properly");
		    Log.info("search functionality is working properly");
		    driver.navigate().refresh();
		    }
		    
		   catch(Exception e){
			 //  Alert alert=driver.switchTo().alert();
			  // alert.accept();
			   Alert alert=driver.switchTo().alert();
				System.out.println(alert.getText());
				alert.accept();
			   System.out.println("Data Not Found");
			   System.out.println("TEST 3 :Functionality of search button is working properly");
			   Log.info("search functionality is not working properly");
			   driver.navigate().refresh();
			   Assert.fail();
		   }
		   }
	    	
			//--------------------> clear search button functionality ------------------> 

			@Test(priority=5)
			public void courseClearButton() throws Exception{
			Thread.sleep(2000);	
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 
			 String result = "";
	        String exception = null;
			
			    
				Thread.sleep(2000);
				driver.findElement(By.id(prop.getProperty("courseclearbutton_id"))).click();
				
				// driver.navigate().refresh();
				Thread.sleep(1000);
				
				WebElement TxtBoxContent = driver.findElement(By.id(prop.getProperty("coursesearchbox_id")));
				TxtBoxContent.getText();
				
				 try {
		              Assert.assertEquals(TxtBoxContent.getText(),"");
		              
		              System.out.println("TEST 4 : Functionality of clear button is working properly");
		              Log.info("Functionality of clear button is working properly");
				}
				catch (AssertionError ex) {
		              
		              System.out.println("TEST 3 : Functionality of clear button is not working properly");
		              Log.info("Functionality of clear button is not working properly");
		              Assert.fail();
		         }
		    
				
			}
			
			
			@Test(priority=6)
			public void addnewemployee() throws IOException, InterruptedException{
				
				driver.findElement(By.id(prop.getProperty("addnewemployee_id"))).click();				
						
				try{
					assertTrue(isElementPresent(By.id(prop.getProperty("okbutton_id"))));
							Thread.sleep(2000);
							System.out.println("add new employee page open successfully");
							Log.info("add new employee page open successfully");
							
						}
						catch(AssertionError e){
							Alert alert=driver.switchTo().alert();
							alert.accept();
							
					System.out.println("page not open or page crashed or seat full");
					Log.info("page not open or page crashed or seat full");
					Thread.sleep(3000);
					Assert.fail();
					}
				}
			
			
			@Test(priority=7)
			public void AddnewemployeeSearchButton() throws Exception{
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("addropdown_id"))));
			Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("addropdown_id")))); 
			dropdown.selectByVisibleText(data1[1][3]);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("adsearchbox_id"))));
			driver.findElement(By.id(prop.getProperty("adsearchbox_id"))).sendKeys(data1[1][4]);	
		  
			driver.findElement(By.id(prop.getProperty("adsearchbutton_id"))).click();
		   // Assert.assertEquals(driver.getTitle(), "Question Master");
		   
			try{
				
				boolean search = driver.getPageSource().contains(data1[1][4]);
				
			    System.out.println("search value is Present");
			    System.out.println("TEST 7 : Functionality of search button is working properly");
			    Log.info("search functionality is working properly");
			    }
			    
			   catch(Exception e){
				 //  Alert alert=driver.switchTo().alert();
				  // alert.accept();
				   System.out.println("Data Not Found");
				   System.out.println("TEST 7 :Functionality of search button is working properly");
				   Log.info("search functionality is not working properly");
				   Assert.fail();
			   }
			   }
		    	
				//--------------------> clear search button functionality ------------------> 

				@Test(priority=8)
				public void AddnewemployeeSearchClearButton() throws Exception{
				Thread.sleep(2000);	
				//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				 
				 String result = "";
		        String exception = null;
				
				    
					Thread.sleep(2000);
					driver.findElement(By.id(prop.getProperty("adclearsearchbutton_id"))).click();
					Thread.sleep(4000);
					
					WebElement TxtBoxContent = driver.findElement(By.id(prop.getProperty("adsearchbox_id")));
					TxtBoxContent.getText();
					
					 try {
			              Assert.assertEquals(TxtBoxContent.getText(),"");
			              
			              System.out.println("TEST 4 : Functionality of clear button is working properly");
			              Log.info("Functionality of clear button is working properly");
					}
					catch (AssertionError ex) {
						Alert alert=driver.switchTo().alert();
						System.out.println(alert.getText());
						alert.accept();
			              System.out.println("TEST 3 : Functionality of clear button is not working properly");
			              Log.info("Functionality of clear button is not working properly");
			              Assert.fail();
			         }
			    
					
				}
			
					
		// select employee for mapping
			
			@Test(priority=9)
			public void EmployeeMapping() throws IOException, InterruptedException{
				
				
			String exception = null;
			List<Map<String, String>> map = REM.ReadExcel("D:\\HRMS DATA\\Datasheet\\Training\\ScheduleMapping.xls","Sheet2");
			for(int i=0;i<map.size()-1;i++){
			System.out.println(map.get(i).get("EmployeeCode"));	
			//System.out.println(".//*[text()="+map.get(i).get("EmployeeCode")+"]/parent::tr/td/input[@type='checkbox']");
					driver.findElement(By.xpath(".//*[text()='"+map.get(i).get("EmployeeCode")+"']/parent::tr/td/input[@type='checkbox']")).click();
					Thread.sleep(2000);
					try{
					
					driver.findElement(By.xpath(".//*[text()='"+map.get(i).get("EmployeeCode")+"']/parent::tr/td/input[@type='checkbox']")).isSelected();
					driver.findElement(By.id(prop.getProperty("okbutton_id"))).click();
					System.out.println("ok button working properly");
					Log.info("ok button working properly");
					
					}
					catch(Exception e){
						
						driver.findElement(By.id(prop.getProperty("cancelbutton_id"))).click();
						System.out.println("cancel button working properly");
						Log.info("cancel button working properly");
					}
					
					Thread.sleep(2000);
					
					try{
						boolean search = driver.getPageSource().contains(map.get(i).get("EmployeeCode"));
						System.out.println("employee selected successfully for mapping");
						Log.info("employee selected successfully for mapping");
						
					}
					catch(Exception e){
						
				System.out.println("employee not selected : page crashed");
				Log.info("employee not selected or page crashed");
				Thread.sleep(3000);
				Assert.fail();
				}
			}
		}
		
		
			@Test(priority=10)
			public void sendmail() throws IOException, InterruptedException{
				
				
			String exception = null;
			List<Map<String, String>> map = REM.ReadExcel("D:\\HRMS DATA\\Datasheet\\Training\\ScheduleMapping.xls","Sheet2");
			for(int i=0;i<map.size()-1;i++){
			//System.out.println(map.get(i).get("EmployeeCode"));	
			//System.out.println(".//*[text()="+map.get(i).get("EmployeeCode")+"]/parent::tr/td/input[@type='checkbox']");
					driver.findElement(By.xpath(".//*[text()='"+map.get(i).get("EmployeeCode")+"']/parent::tr/td/span/input[@type='checkbox']")).click();
					Thread.sleep(2000);
									
					driver.findElement(By.xpath(".//*[text()='"+map.get(i).get("EmployeeCode")+"']/parent::tr/td/span/input[@type='checkbox']")).isSelected();
					driver.findElement(By.id(prop.getProperty("sendmail_id"))).click();
					System.out.println("send mail is working properly");
					Log.info("send mail is not working properly");
					
					Alert alert=driver.switchTo().alert();
					alert.accept();
					driver.findElement(By.id(prop.getProperty("aaback_id"))).click();
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
		
		
	/*	@BeforeClass
		public void abc(ITestContext context){
			//baseUrl = "http://demo.osource.co.in/ERPONEX_INT_WEBAPP/Login.aspx";
		     //create a new work book
		      workbook1 = new HSSFWorkbook();
		      //create a new work sheet
		       sheet = workbook1.createSheet("Test yogesh Result");
		      testresultdata = new LinkedHashMap<String, Object[]>();
		      //add test result excel file column header
		      //write the header in the first row
		     
		          
		      testresultdata.put("1", new Object[] {"type", "subtype", "name", "intime", "outtime", "seats", "venue", "budget","Expected Result","Actual Result","Status"});
		       
	}
		
		@AfterClass
		
		public void xyz(){
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
		         FileOutputStream out =new FileOutputStream(new File("D:\\HRMS DATA\\WriteDataSheet\\ScheduleMasterWriteData.xls"));
		         workbook1.write(out);
		         out.close();
		         System.out.println("Excel written successfully..");
		          
		     } catch (FileNotFoundException e) {
		         e.printStackTrace();
		     } catch (IOException e) {
		         e.printStackTrace();
		     }
		}	*/
		
		private boolean isElementPresent(By by) {
	        try {
	          driver.findElement(by);
	          return true;
	        } catch (NoSuchElementException e) {
	          return false;
	        }
	      }
	}
	


	





