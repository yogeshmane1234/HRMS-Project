
package com.test;

import static org.testng.AssertJUnit.assertTrue;

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

public class ConfirmationSetupALL extends getExcelSheet{
	
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
    //public static Object[][] ShipmentData;
    public static HSSFRow Row;
    public static HSSFCell cell;
    public static String FilePath = "D:\\HRMS DATA\\Datasheet\\Employee Life Cycle\\ConfirmationSetup.xls";
    public static String SheetName1 = "Sheet1";
    public static HSSFSheet Sheet;
	
	  private WebDriver driver1;
	  private String baseUrl;
	  //define an Excel Work Book
	  HSSFWorkbook workbook1;
	  //define an Excel Work sheet
	  HSSFSheet sheet;
	  //define a test result data object
	  Map<String, Object[]> testresultdata;

	//public String sheet1;
	 private static Logger Log = Logger.getLogger(ConfirmationSetupALL.class.getName());
	public FileInputStream inputStream=null;
	BaseClass baseclass = new BaseClass();
	Excel dataXLS = new Excel();
	Properties prop = baseclass.PropertiesConfigurations();
	// Logger logger=baseclass.LogCongigurations();

	WebDriver driver = baseclass.DriverConfigurations();
	Workbook workbook = baseclass.DatasheetConfigurations("D:\\HRMS DATA\\Datasheet\\Login.xls");
	//String data[][] = dataXLS.CellData("Sheet1",0,8);
	String data[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\Login.xls", "Sheet1", 0, 11);
	String data1[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\Employee Life Cycle\\ConfirmationSetup.xls", "Sheet3", 0, 11);
	//String data2[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\BasicSalaryRangeData.xls", "Sheet1", 0, 11);
	
	WebDriverWait wait = new WebDriverWait(driver, 180);
	    
		// ****************Login to Application****************
	@BeforeTest
	public void baseLogin() throws BiffException, IOException, InterruptedException, Exception{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		baseclass.login(data[1][0], data[1][1], driver);
		//driver.getTitle().equalsIgnoreCase("Onex Software");
		if(driver.getTitle().equalsIgnoreCase("Onex Software"))
		{
		System.out.println("TEST 1 : Login successfully");
		
		
		Log.info("Login successfully ");
		}
		else{
			System.out.println("TEST 1 : page crashed");
			Log.info("page crashed ");
			
			
		}
		baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "confirmation_linktext", driver);
		System.out.println("Target Page open successfully ");
		Log.info("Target Page open successfully ");
	}
	
	// ----------------------> search functionality -------------------->
	
	@Test(priority=0)
	public void CONFTestSearchButton() throws Exception{
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("confdropdown_id"))));
	Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("confdropdown_id")))); 
	dropdown.selectByVisibleText(data1[3][0]);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("confsearchbox_id"))));
	driver.findElement(By.id(prop.getProperty("confsearchbox_id"))).sendKeys(data1[3][1]);	
  
	driver.findElement(By.id(prop.getProperty("confsearchbutton_id"))).click();
   // Assert.assertEquals(driver.getTitle(), "Question Master");
    boolean search = driver.getPageSource().contains(data1[3][1]);
    
       
    if(search)
    {
    System.out.println("*****search value is Present*********");
    //testresultdata.put("2", new Object[] {4d, "search functionality working", "search functionality is working properly","Pass"});
    System.out.println("TEST 2 : search functionality is working properly");
    
    Log.info("search functionality is working properly");
    }
    else
    {
    System.out.println("*****search value is not Present*********");
    //testresultdata.put("2", new Object[] {4d, "search functionality working", "search functionality is not working properly","Fail"});
    System.out.println("TEST 2 : search functionality is not working properly");
    
    Log.info("search functionality is not working properly");
    }	
    	
	}
		
	//--------------------> clear search button functionality ------------------> 

		@Test(priority=1)
		public void CONFTestClearSearchButton() throws Exception{
		Thread.sleep(2000);	
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String result = "";
        String exception = null;
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("confdropdown_id"))));
		Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("confdropdown_id")))); 
		dropdown.selectByVisibleText(data1[3][0]);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("confsearchbox_id"))));
		driver.findElement(By.id(prop.getProperty("confsearchbox_id"))).clear();
		driver.findElement(By.id(prop.getProperty("confsearchbox_id"))).sendKeys(data1[3][1]);	
	  
		driver.findElement(By.id(prop.getProperty("confsearchbutton_id"))).click();
		    
			Thread.sleep(2000);
			driver.findElement(By.id(prop.getProperty("confclearsearchbutton_id"))).click();
			Thread.sleep(1000);
			
			WebElement TxtBoxContent = driver.findElement(By.id(prop.getProperty("confsearchbox_id")));
			TxtBoxContent.getText();
		/*	try{
				Assert.assertEquals(TxtBoxContent.getText(),"");
	              result = TestLinkAPIResults.TEST_PASSED;
	              updateTestLinkResult("DOHA-17370", null, result);
			}
			catch(Exception ee){
				result = TestLinkAPIResults.TEST_FAILED;
	              exception = ee.getMessage();
	              updateTestLinkResult("DOHA-17370", exception, result);
			}*/
			
			 try {
	              Assert.assertEquals(TxtBoxContent.getText(),"");
	              result = TestLinkAPIResults.TEST_PASSED;
	              updateTestLinkResult("DOHA-17369", null, result);
	              System.out.println("TEST 3 : Functionality of clear button is working properly");
	              
	              Log.info("Functionality of clear button is working properly");
	              
	         } catch (AssertionError ex) {
	              result = TestLinkAPIResults.TEST_FAILED;
	              exception = ex.getMessage();
	              updateTestLinkResult("DOHA-17369", exception, result);
	              System.out.println("TEST 3 : Functionality of clear button is not working properly");
	              
	              Log.info("Functionality of clear button is not working properly");
	              Assert.fail();
	         }
	    
			
		}
		
//---------------> ADD NEW DATA USING DATA PROVIDER ---------------->	
		
	@Test(dataProvider = "confdata",priority=2)
	public void ConfirmationSetupNewData(String name, String remark, String Grade) throws Exception{
		//System.setProperty("webdriver.chrome.driver",""));
	//baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "stm_linktext", driver);
		String exception = null;
	driver.findElement(By.id(prop.getProperty("confaddnewbutton_id"))).click();
	Thread.sleep(1000);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("confdate_xpath"))));
	WebElement element =driver.findElement(By.xpath(prop.getProperty("confdate_xpath")));
	Thread.sleep(2000);
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("arguments[0].setAttribute('value','16-Dec-2016')",element);
			
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("conftestname_id"))));
	Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("conftestname_id")))); 
	try{
		dropdown.selectByVisibleText(name);
	}
	catch(Exception ex){
		exception = ex.getMessage();
	}
	Thread.sleep(1000);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("confremark_id"))));
	driver.findElement(By.id(prop.getProperty("confremark_id"))).sendKeys(remark);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("confgrade_id"))));
	Select dropdown1 = new Select(driver.findElement(By.id(prop.getProperty("confgrade_id")))); 
	
	try{
		dropdown1.selectByVisibleText(Grade);
	}
	catch(Exception el){
		exception = el.getMessage();
	}
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("confokbutton_id"))));
	driver.findElement(By.id(prop.getProperty("confokbutton_id"))).click();
		
	Thread.sleep(3000);
		
	try{
		//Assert.assertEquals(driver.getTitle(), "Confirmation Setup");		
		//assertTrue(isElementPresent(By.id(prop.getProperty("confaddnewbutton_id"))));
		driver.findElement(By.id(prop.getProperty("confaddnewbutton_id"))).isEnabled();
		System.out.println("TEST 5 : new data added successfully");
		
		testresultdata.put(remark, new Object[] {name, remark, Grade,"accept New data","new data accepted","Pass"});
		//testresultdata.put("5", new Object[] {4d, "add functionality working", "add functionality is working properly","Pass"});
		Log.info("new data added successfully");		
	}
	catch(Exception e){
		//System.out.println("TEST 5 :duplicate data or missing data : cancel add new process for this data");
		/*Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();*/
		Thread.sleep(2000);
		
		driver.findElement(By.id(prop.getProperty("confcancelbutton_id"))).click();
	
	System.out.println("TEST 5 :duplicate data or missing data : cancel add new process for this data");
	Log.info("duplicate data or missing data ");
	testresultdata.put(remark, new Object[] {name, remark, Grade,"accept New data","duplicate data or missing data","Fail"});
	
		//e.printStackTrace();
		Assert.fail();
		}
	
	}

	@DataProvider(name="confdata")
    public static Object[][] getLoginData() throws Exception{

        Sheet = DataSheet(FilePath, SheetName1);
        int rowCount = Sheet.getLastRowNum();
      //  System.out.println(rowCount);
        int colCount = Sheet.getRow(0).getLastCellNum();
       // System.out.println(colCount);

        LoginData = new Object[rowCount][colCount];

        for (int rCnt=1; rCnt<=rowCount;rCnt++){
            for (int cCnt=0; cCnt<colCount;cCnt++){
                LoginData[rCnt-1][cCnt] = getCellData(SheetName1, rCnt, cCnt);
               // System.out.println(LoginData[rCnt-1][cCnt]);
            }
        }

        return LoginData;
    }
    
   @Test
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
		public void abc(ITestContext context){
			//baseUrl = "http://demo.osource.co.in/ERPONEX_INT_WEBAPP/Login.aspx";
		     //create a new work book
		      workbook1 = new HSSFWorkbook();
		      //create a new work sheet
		       sheet = workbook1.createSheet("Test yogesh Result");
		      testresultdata = new LinkedHashMap<String, Object[]>();
		      //add test result excel file column header
		      //write the header in the first row
		     
		          
		      testresultdata.put("1", new Object[] {"Name","Remark","Grade","Expected Result","Actual Result","Status"});
		       
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
		         FileOutputStream out =new FileOutputStream(new File("D:\\HRMS DATA\\WriteDataSheet\\ConfirmationSetupData.xls"));
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
	


	




