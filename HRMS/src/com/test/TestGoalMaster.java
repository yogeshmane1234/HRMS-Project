package com.test;

import static org.testng.AssertJUnit.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
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
//import org.apache.log4j.PropertyConfigurator;
//import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;
import Excel.Excel;

import com.test.LoggerCourseMaster;




//import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;
import Excel.Excel;
//import HRMS_Recruitment.Login1;
import baseClass.BaseClass;

public class TestGoalMaster extends getExcelSheet{
	
	public final String DEV_KEY = "ea36783bf6276ac8f98cb2147888fde1";
    
    public static String SERVER_URL ="http://192.168.202.24/testlink/lib/api/xmlrpc.php";

      // Substitute your project name Here
    public final String PROJECT_NAME = "HRMS-Shangrila-Doha";

    // Substitute your test plan Here
    public final String PLAN_NAME = "CR_031016_Yogesh";

    // Substitute your build name
    public final String BUILD_NAME = "CR_v1.0_031016";
    
    public void updateTestLinkResult(String testCase, String exception, String result)    throws TestLinkAPIException {
        TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(DEV_KEY,
                               SERVER_URL);
        testlinkAPIClient.reportTestCaseResult(PROJECT_NAME, PLAN_NAME,
                               testCase, BUILD_NAME, exception, result);
        
    }
	
	public static Object[][] LoginData;
    public static HSSFRow Row;
    public static HSSFCell cell;
    public static String FilePath = "D:\\HRMS DATA\\GoalMaster.xls";
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
    private static Logger Log = Logger.getLogger(LoggerCourseMaster.class.getName());
	public FileInputStream inputStream=null;
	BaseClass baseclass = new BaseClass();
	Excel dataXLS = new Excel();
	//Properties prop = baseclass.PropertiesConfigurations();
	Properties prop = baseclass.PropertiesConfigurations(".//src//Properties//PMS&Training.GoalMaster.properties");
	// Logger logger=baseclass.LogCongigurations();

	WebDriver driver = baseclass.DriverConfigurations();
	Workbook workbook = baseclass.DatasheetConfigurations("D:\\HRMS DATA\\Datasheet\\Login.xls");
	//String data[][] = dataXLS.CellData("Sheet1",0,8);
	String data[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\Login.xls", "Sheet1", 0, 11);
	String data1[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\PMS and Training\\GoalMaster.xls", "Sheet3", 0, 11);
	//String data2[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\BasicSalaryRangeData.xls", "Sheet1", 0, 11);
	
	WebDriverWait wait = new WebDriverWait(driver, 180);

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
			baseclass.CommomSection2("HR_id", "HRsetup_linktext", driver);
							
			WebElement element2 = driver.findElement(By.xpath(prop
					.getProperty("PMSTraining_xpath")));
			action.moveToElement(element2).perform();

			Thread.sleep(1000);

			driver.findElement(By.linkText(prop.getProperty("goal_linktext"))).click();
			
			System.out.println("TEST 2 : Target Page open successfully");
			Log.info("Target Page open successfully ");
			Thread.sleep(1000);
	}	
			
		// ----------------------> search functionality -------------------->
	
	@Test(priority=1)
	public void goalSearchButton() throws Exception{
	Thread.sleep(2000);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("goaldropdown_id"))));
	Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("goaldropdown_id")))); 
	dropdown.selectByVisibleText(data1[3][0]);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("goalsearchbox_id"))));
	driver.findElement(By.id(prop.getProperty("goalsearchbox_id"))).sendKeys(data1[3][1]);	
  
	driver.findElement(By.id(prop.getProperty("goalsearchbutton_id"))).click();
   // Assert.assertEquals(driver.getTitle(), "Question Master");
   
	boolean search = driver.getPageSource().contains(data1[3][1]);
	    if(search)
    {
    System.out.println("search value is Present");
    System.out.println("TEST 3 : Functionality of search button is working properly");
    Log.info("search functionality is working properly");
    }
    
   
   else{
	   Alert alert=driver.switchTo().alert();
	   alert.accept();
	   System.out.println(" Data Not Found ");
	   System.out.println("TEST 3 :Functionality of search button is working properly");
	   Log.info("search functionality is not working properly");
   }
   }
    	
		//--------------------> clear search button functionality ------------------> 

		@Test(priority=2)
		public void goalClearButton() throws Exception{
		Thread.sleep(2000);	
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
		 String result = "";
        String exception = null;
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("goaldropdown_id"))));
		Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("goaldropdown_id")))); 
		dropdown.selectByVisibleText(data1[3][0]);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("goalsearchbox_id"))));
		driver.findElement(By.id(prop.getProperty("goalsearchbox_id"))).clear();
		driver.findElement(By.id(prop.getProperty("goalsearchbox_id"))).sendKeys(data1[3][1]);	
	  
		//driver.findElement(By.id(prop.getProperty("goalsearchbutton_id"))).click();
		    
			Thread.sleep(2000);
			driver.findElement(By.id(prop.getProperty("goalclearbutton_id"))).click();
			Thread.sleep(1000);
			
			WebElement TxtBoxContent = driver.findElement(By.id(prop.getProperty("goalsearchbox_id")));
			TxtBoxContent.getText();
			
			 try {
	              Assert.assertEquals(TxtBoxContent.getText(),"");
	              result = TestLinkAPIResults.TEST_PASSED;
	              updateTestLinkResult("DOHA-17369", null, result);
			System.out.println("TEST 4 : Functionality of clear button is working properly");
			 Log.info("Functionality of clear button is working properly");
			}
			catch (AssertionError ex) {
	              result = TestLinkAPIResults.TEST_FAILED;
	              exception = ex.getMessage();
	              updateTestLinkResult("DOHA-17369", exception, result);
	              System.out.println("TEST 3 : Functionality of clear button is not working properly");
	              
	              Log.info("Functionality of clear button is not working properly");
	              Assert.fail();
	         }
	    
			
		}
		
		//----------------------> edit functionality ------------------->	 	  
		@Test(priority=3)
		public void goalEdit() throws Exception{
		Thread.sleep(2000);
			driver.findElement(By.id(prop.getProperty("goaleditbutton_id"))).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(2000);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("goalremark_id"))));
			driver.findElement(By.id(prop.getProperty("goalremark_id"))).clear();
			driver.findElement(By.id(prop.getProperty("goalremark_id"))).sendKeys(data1[3][2]); 
						
			driver.findElement(By.id(prop.getProperty("goalokbutton_id"))).click();
			Thread.sleep(2000);
			Alert alert=driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();
			Thread.sleep(2000);

			try{
				
			driver.findElement(By.id(prop.getProperty("goaleditbutton_id")));
			//driver.findElement(By.id(prop.getProperty("goalremark_id"))).isDisplayed();
			
				System.out.println("TEST 5 : record updated successfully");
				Log.info("record updated successfully");
				
			}
			catch(Exception e){
				
				driver.findElement(By.id(prop.getProperty("goalcancelbutton_id"))).click();
				System.out.println("TEST 5 :record not updated ");
				Log.info("record not updated");
				Assert.fail();
			}
		}
		
//--------------------> delete functionality ------------------>
		
		@Test(priority=4)
		public void goalDelete() throws Exception{
		Thread.sleep(2000);
			driver.findElement(By.id(prop.getProperty("focusdelete_id"))).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(2000);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("focusremark_id"))));
			Select dropdown2 = new Select(driver.findElement(By.id(prop.getProperty("focusremark_id")))); 
			dropdown2.selectByVisibleText(data1[3][2]);
			
			driver.findElement(By.id(prop.getProperty("focusokbutton_id"))).click();
			Thread.sleep(2000);
			Alert alert=driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();
			
			//WebElement T = driver.findElement(By.id(prop.getProperty("exittest_id")));
			//T.getText();
			
			//if(T.getText().equalsIgnoreCase("Select"))
			if(driver.findElement(By.id(prop.getProperty("focusremark_id"))).isEnabled())
			{
				Thread.sleep(1000);
				driver.findElement(By.id(prop.getProperty("focuscancelbutton_id"))).click();
				System.out.println("TEST 5 :record not updated ");
			}
			else {
				System.out.println("TEST 5 : record updated successfully");
			}
											
		}
		
//---------------> ADD NEW DATA USING DATA PROVIDER ---------------->	
		@Test(dataProvider = "Goaldata",priority=4)
	public void GoalMasterNewData(String focus, String code, String goal,String remark) throws Exception{
		//System.setProperty("webdriver.chrome.driver",""));
	//baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "stm_linktext", driver);
	String exception = null;
	driver.findElement(By.id(prop.getProperty("goaladdnew_id"))).click();
	Thread.sleep(1000);
				
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("goalfocus_id"))));
	Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("goalfocus_id")))); 
	try{
		dropdown.selectByVisibleText(focus);
	}
	catch(Exception ea){
		exception = ea.getMessage();
	}
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("goalcode_id"))));
	try{
		driver.findElement(By.id(prop.getProperty("goalcode_id"))).clear();
		driver.findElement(By.id(prop.getProperty("goalcode_id"))).sendKeys(code);
	}
	catch(Exception es){
		exception = es.getMessage();
	}
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("goalgoal_id"))));
	try{
		driver.findElement(By.id(prop.getProperty("goalgoal_id"))).clear();
		driver.findElement(By.id(prop.getProperty("goalgoal_id"))).sendKeys(goal);
	}
	catch(Exception ed){
		exception = ed.getMessage();
	}
			
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("goalremark_id"))));
	try{
		driver.findElement(By.id(prop.getProperty("goalremark_id"))).clear();
		driver.findElement(By.id(prop.getProperty("goalremark_id"))).sendKeys(remark);
	}
	catch(Exception ef){
		exception = ef.getMessage();
	}
		
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("goalokbutton_id"))));
	try{
		
		driver.findElement(By.id(prop.getProperty("goalokbutton_id"))).click();
	}
	catch(Exception e){
		exception = e.getMessage();
	}
	
	Thread.sleep(4000);
	Alert alert=driver.switchTo().alert();
	System.out.println(alert.getText());
	alert.accept();
	Thread.sleep(2000);
	
		try{
		
			assertTrue(isElementPresent(By.id(prop.getProperty("goalgoal_id"))));
		//alert.getText().equals("Record Saved Successfully");
		//Assert.assertNotEquals(By.id("ContentPlaceHolder_lblHeader"), By.id("ContentPlaceHolder_lblHeader"));;
		//Assert.assertEquals(alert.getText(), "Record saved Successfully.");
		//alert.accept();
		//assertEquals("Record Saved Successfully.",alert.getText());
		//Assert.assertEquals(alert.getText(), "Record Saved Successfully.");
	//driver.findElement(By.id(prop.getProperty("goalsearchbox_id")));
		driver.findElement(By.id(prop.getProperty("goaladdnew_id"))).click();
		System.out.println("TEST 6 : new data added successfully");
		Log.info("new data added successfully");
		testresultdata.put(code, new Object[] {focus, code, goal, remark,"Accept New Data","new data added successfully","Pass"});	
		}
	catch(Exception e){
			
		//alert.accept();
		driver.findElement(By.id(prop.getProperty("goalcancelbutton_id"))).click();
		Thread.sleep(1000);
		System.out.println("TEST 6 :duplicate data or missing data : cancel add new process for this data");
		Log.info("duplicate data or missing data");
		testresultdata.put(code, new Object[] {focus,code,goal,remark,"Accept New Data","duplicate data or missing data","Fail"});
		//e.printStackTrace();
		Assert.fail();
	}
}

	 @DataProvider(name="Goaldata")
	    public static Object[][] getLoginData() throws Exception{

	        Sheet = DataSheet(FilePath, SheetName1);
	        int rowCount = Sheet.getLastRowNum();
	        System.out.println("total rows = " +rowCount);
	        int colCount = Sheet.getRow(0).getLastCellNum();
	        System.out.println("Total columns = "+colCount);

	        LoginData = new Object[rowCount][colCount];

	        for (int rCnt=1; rCnt<=rowCount;rCnt++){
	            for (int cCnt=0; cCnt<colCount;cCnt++){
	                LoginData[rCnt-1][cCnt] = getCellData(SheetName1, rCnt, cCnt);
	               // System.out.println(LoginData[rCnt-1][cCnt]);
	            }
	        }

	        return LoginData;
	    }
	 
	 public static String getCellData(String Sheet, int row, int col){

	        try {

	            int index = WBook.getSheetIndex(Sheet);


	            WSheet = WBook.getSheetAt(index);
	            Row = WSheet.getRow(row);
	            if (Row == null)
	            return "";

	            cell = Row.getCell(col);
	            if (cell == null)
	            return "";

	            switch (cell.getCellType())
	            {
	            case  Cell.CELL_TYPE_STRING:
	            return cell.getStringCellValue();               

	            case  Cell.CELL_TYPE_BOOLEAN:
	            return String.valueOf(cell.getBooleanCellValue());          

	            case  Cell.CELL_TYPE_BLANK:
	            return "";      

	            case  Cell.CELL_TYPE_ERROR:
	            return cell.getStringCellValue();           

	            case  Cell.CELL_TYPE_NUMERIC:
	            return String.valueOf(cell.getNumericCellValue());          

	            default:
	            return "Cell not found";        

	            }
	        }
	            catch (Exception e) {
	            e.printStackTrace();
	            return "row " + row + " or column " + col+ " does not exist in xls";
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
		
		
		@BeforeClass
		public void abc(ITestContext context){
			//baseUrl = "http://demo.osource.co.in/ERPONEX_INT_WEBAPP/Login.aspx";
		     //create a new work book
		      workbook1 = new HSSFWorkbook();
		      //create a new work sheet
		       sheet = workbook1.createSheet("Test yogesh Result");
		      testresultdata = new LinkedHashMap<String, Object[]>();
		      //add test result excel file column header
		      //write the header in the first row
		     
		          
		      testresultdata.put("1", new Object[] {"Focus","Code","Goal","Remark","Expected Result","Actual Result","Status"});
		       
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
		         FileOutputStream out =new FileOutputStream(new File("D:\\HRMS DATA\\WriteDataSheet\\GoalMasterWriteData.xls"));
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
	


	




