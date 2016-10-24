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
import Excel.getExcelSheet;
//import HRMS_Recruitment.Login1;
import baseClass.BaseClass;

public class ScheduleMaster extends getExcelSheet{
		
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
    private static Logger Log = Logger.getLogger(ScheduleMaster.class.getName());
	public FileInputStream inputStream=null;
	BaseClass baseclass = new BaseClass();
	Excel dataXLS = new Excel();
	//Properties prop = baseclass.PropertiesConfigurations();
	Properties prop = baseclass.PropertiesConfigurations(".//src//Properties//Training.ScheduleMaster.properties");
	// Logger logger=baseclass.LogCongigurations();

	WebDriver driver = baseclass.DriverConfigurations();
	
	String data[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\Training\\TrainingLogin.xls", "HR", 0, 11);
	String data1[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\Training\\ScheduleMaster.xls", "Sheet2", 0, 11);
	
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

			driver.findElement(By.linkText(prop.getProperty("scheduleMaster_linktext"))).click();
			
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
	dropdown.selectByVisibleText(data1[3][0]);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("searchbox_id"))));
	driver.findElement(By.id(prop.getProperty("searchbox_id"))).sendKeys(data1[3][1]);	
  
	driver.findElement(By.id(prop.getProperty("searchbutton_id"))).click();
   // Assert.assertEquals(driver.getTitle(), "Question Master");
   Thread.sleep(2000);
	try{
		
	boolean search = driver.getPageSource().contains(data1[3][1]);
	
    System.out.println("search value is Present");
    System.out.println("TEST 3 : Functionality of search button is working properly");
    Log.info("search functionality is working properly");
    }
    
   catch(Exception e){
	 //  Alert alert=driver.switchTo().alert();
	  // alert.accept();
	   System.out.println(" Data Not Found ");
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("dropdown_id"))));
		Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("dropdown_id")))); 
		dropdown.selectByVisibleText(data1[3][0]);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("searchbox_id"))));
		driver.findElement(By.id(prop.getProperty("searchbox_id"))).clear();
		driver.findElement(By.id(prop.getProperty("searchbox_id"))).sendKeys(data1[3][1]);	
	  
		driver.findElement(By.id(prop.getProperty("searchbutton_id"))).click();
		    
			Thread.sleep(2000);
			driver.findElement(By.id(prop.getProperty("clearsearch_id"))).click();
			Thread.sleep(1000);
			
			WebElement TxtBoxContent = driver.findElement(By.id(prop.getProperty("searchbox_id")));
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
		
		//----------------------> edit functionality ------------------->	 	  
		@Test(priority=3)
		public void EditScheduleMaster() throws Exception{
		
		//driver.findElement(By.xpath(".//*"))).click();
			
		driver.findElement(By.id(prop.getProperty("edit_id"))).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
					
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("venue_id"))));
			driver.findElement(By.id(prop.getProperty("venue_id"))).clear();
			driver.findElement(By.id(prop.getProperty("venue_id"))).sendKeys(data1[3][2]); 
						
			driver.findElement(By.id(prop.getProperty("ok_id"))).click();
			Thread.sleep(2000);
			Alert alert=driver.switchTo().alert();
			System.out.println(alert.getText());
			
			Thread.sleep(2000);

			try{
				
			Assert.assertEquals(alert.getText(), "Schedule Updated Successfully");
			//driver.findElement(By.id(prop.getProperty("goalremark_id"))).isDisplayed();
			alert.accept();
				System.out.println("TEST 5 : record updated successfully");
				Log.info("record updated successfully");
				
			}
			catch(AssertionError e){
				alert.accept();
				driver.findElement(By.id(prop.getProperty("cancel_id"))).click();
				System.out.println("TEST 5 :record not updated ");
				Log.info("record not updated");
				Assert.fail();
			}
		}
		
//--------------------> delete functionality ------------------>
			@Test(priority=4)
		public void ScheduleMasterDelete() throws Exception{
		Thread.sleep(2000);
			driver.findElement(By.id(prop.getProperty("delete_id"))).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(2000);
			
			Alert alert=driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();
			
			try{
				
				Assert.assertEquals(driver.getTitle(), "Schedule Master");
				System.out.println("TEST 5 : record deleted successfully");
				Log.info("record deleted successfully");
				
			}
			catch(AssertionError e ){
				
				System.out.println("TEST 5 : record not deleted successfully");
				Log.info("record not deleted successfully");
				
				Assert.fail();
			}								
		}
	
//---------------> ADD NEW DATA USING DATA PROVIDER ---------------->	
		@Test(dataProvider = "schedulemasterdata",priority=4)
	public void schedulemasterdata(String type, String subtype, String name,String intime,String outtime,
			String seats, String venue, String budget) throws Exception{
		
	String exception = null;
	driver.findElement(By.id(prop.getProperty("addnew_id"))).click();
	Thread.sleep(1000);
				
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("coursetype_id"))));
	Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("coursetype_id")))); 
	try{
		dropdown.selectByVisibleText(type);
	}
	catch(Exception ea){
		exception = ea.getMessage();
	}
	
	Thread.sleep(2000);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("subtype_id"))));
	Select dropdown1 = new Select(driver.findElement(By.id(prop.getProperty("subtype_id")))); 
	try{
		dropdown1.selectByVisibleText(subtype);
	}
	catch(Exception ea){
		exception = ea.getMessage();
	}
	Thread.sleep(4000);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("coursename_id"))));
	Select dropdown2 = new Select(driver.findElement(By.id(prop.getProperty("coursename_id")))); 
	try{
		dropdown2.selectByVisibleText(name);
	}
	catch(Exception ea){
		exception = ea.getMessage();
	}
	
	try{
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("fromdate_id"))));
	WebElement element =driver.findElement(By.id(prop.getProperty("fromdate_id")));
	Thread.sleep(2000);
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("arguments[0].setAttribute('value','18-Oct-2016')",element);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
	try{
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("todate_id"))));
	WebElement element =driver.findElement(By.id(prop.getProperty("todate_id")));
	Thread.sleep(2000);
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("arguments[0].setAttribute('value','19-Oct-2016')",element);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("intime_id"))));
	try{
		driver.findElement(By.id(prop.getProperty("intime_id"))).clear();
		driver.findElement(By.id(prop.getProperty("intime_id"))).sendKeys(intime);
	}
	catch(Exception es){
		exception = es.getMessage();
	}
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("outtime_id"))));
	try{
		driver.findElement(By.id(prop.getProperty("outtime_id"))).clear();
		driver.findElement(By.id(prop.getProperty("outtime_id"))).sendKeys(outtime);
	}
	catch(Exception ed){
		exception = ed.getMessage();
	}
			
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("seat_id"))));
	try{
		driver.findElement(By.id(prop.getProperty("seat_id"))).clear();
		driver.findElement(By.id(prop.getProperty("seat_id"))).sendKeys(seats);
	}
	catch(Exception ef){
		exception = ef.getMessage();
	}
		
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("venue_id"))));
	try{
		
		driver.findElement(By.id(prop.getProperty("venue_id"))).clear();
		driver.findElement(By.id(prop.getProperty("venue_id"))).sendKeys(venue);
	}
	catch(Exception e){
		exception = e.getMessage();
	}
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("budget_id"))));
	try{
		
		driver.findElement(By.id(prop.getProperty("budget_id"))).clear();
		driver.findElement(By.id(prop.getProperty("budget_id"))).sendKeys(budget);
	}
	catch(Exception e){
		exception = e.getMessage();
	}
	
	driver.findElement(By.id(prop.getProperty("ok_id"))).click();
	
	Thread.sleep(4000);
	Alert alert=driver.switchTo().alert();
	System.out.println(alert.getText());
	//alert.accept();
	Thread.sleep(2000);
	
		try{
		
			Assert.assertEquals(alert.getText(), "Schedule Created Successfully");
			//assertTrue(isElementPresent(By.id(prop.getProperty("searchbutton_id"))));
		//alert.getText().equals("Record Saved Successfully");
		//Assert.assertNotEquals(By.id("ContentPlaceHolder_lblHeader"), By.id("ContentPlaceHolder_lblHeader"));;
		//Assert.assertEquals(alert.getText(), "Record saved Successfully.");
		alert.accept();
		//assertEquals("Record Saved Successfully.",alert.getText());
		//Assert.assertEquals(alert.getText(), "Record Saved Successfully.");
		//driver.findElement(By.id(prop.getProperty("goalsearchbox_id")));
		//driver.findElement(By.id(prop.getProperty("goaladdnew_id"))).click();
		System.out.println("TEST 6 : new data added successfully");
		Log.info("new data added successfully");
		testresultdata.put(name, new Object[] {type, subtype, name, intime, outtime, seats, venue, budget , "Accept New Data","new data added successfully","Pass"});	
		}
	catch(AssertionError e){
			
		alert.accept();
		driver.findElement(By.id(prop.getProperty("cancel_id"))).click();
		Thread.sleep(1000);
		System.out.println("TEST 6 :duplicate data or missing data : cancel add new process for this data");
		Log.info("duplicate data or missing data");
		testresultdata.put(name, new Object[] {type, subtype, name, intime, outtime, seats, venue, budget ,"Accept New Data","duplicate data or missing data","Fail"});
		//e.printStackTrace();
		Assert.fail();
	}
}

	 @DataProvider(name="schedulemasterdata")
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
	


	





