package com.test;

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






//import org.apache.log4j.PropertyConfigurator;
//import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;
import Excel.Excel;

import com.test.LoggerCourseMaster;






//import HRMS_Recruitment.Login1;
import baseClass.BaseClass;

public class LoggerCourseMaster extends getExcelSheet{
	
	public static Object[][] LoginData;
    public static Object[][] ShipmentData;
    public static HSSFRow Row;
    public static HSSFCell cell;
    public static String FilePath = "D:\\HRMS DATA\\Datasheet\\PMS and Training\\CourseMaster.xls";
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
	private static Logger Log = Logger.getLogger(LoggerCourseMaster.class.getName());
	public FileInputStream inputStream=null;
	BaseClass baseclass = new BaseClass();
	Excel dataXLS = new Excel();
	//Properties prop = baseclass.PropertiesConfigurations();
	Properties prop = baseclass.PropertiesConfigurations(".//src//Properties//PMS%Training.CourseMaster.properties");
	// Logger logger=baseclass.LogCongigurations();

	WebDriver driver = baseclass.DriverConfigurations();
	Workbook workbook = baseclass.DatasheetConfigurations("D:\\HRMS DATA\\Datasheet\\Login.xls");
	//String data[][] = dataXLS.CellData("Sheet1",0,8);
	String data[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\Login.xls", "Sheet1", 0, 11);
	String data1[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\PMS and Training\\CourseMaster.xls", "Sheet3", 0, 11);
	//String data2[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\BasicSalaryRangeData.xls", "Sheet1", 0, 11);
	
	WebDriverWait wait = new WebDriverWait(driver, 180);

		// ****************Login to Application****************
	@BeforeTest
	public void baseLogin() throws BiffException, IOException, InterruptedException, Exception{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Log.info("*****************" +LoggerCourseMaster.class.getName()+"********************");
		baseclass.login(data[1][0], data[1][1], driver);
		//PropertyConfigurator.configure("log4j.xml");
		
				
	}
	
		@Test(priority=0)
		public void Login() throws Exception {
			
			if(driver.getTitle().equalsIgnoreCase("Onex Software"))
		{
		System.out.println("TEST 1 : Login successfully");
		testresultdata.put("3", new Object[] {2d, "navigate to site and login", "site opens and login success","Pass"});
		Log.info("Login successfully");
		}
		else{
			
			System.out.println("TEST 1 : page crashed");
			testresultdata.put("3", new Object[] {2d, "navigate to site and login", "site not opens and login unsuccess","Fail"});
		}
			Actions action = new Actions(driver);
			baseclass.CommomSection2("HR_id", "HRsetup_linktext", driver);
							
			WebElement element2 = driver.findElement(By.xpath(prop
					.getProperty("PMSTraining_xpath")));
			action.moveToElement(element2).perform();

			Thread.sleep(1000);

			driver.findElement(By.linkText(prop.getProperty("course_linktext"))).click();
			
			System.out.println("TEST 2 : Target Page open successfully");
			
			Log.info("Target Page open successfully");
			Thread.sleep(1000);
	}	
			
		// ----------------------> search functionality -------------------->
/*	
	@Test(priority=1)
	public void courseSearchButton() throws Exception{
	Thread.sleep(2000);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("dropdown_id"))));
	Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("dropdown_id")))); 
	dropdown.selectByVisibleText(data1[3][0]);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("searchbox_id"))));
	driver.findElement(By.id(prop.getProperty("searchbox_id"))).sendKeys(data1[3][1]);	
  
	driver.findElement(By.id(prop.getProperty("searchbutton_id"))).click();
   // Assert.assertEquals(driver.getTitle(), "Question Master");
   
	boolean search = driver.getPageSource().contains(data1[3][1]);
	    if(search)
    {
    System.out.println("TEST 3 : search value is Present");
    Log.info("search value is Present");
    System.out.println("Functionality of search button is working properly");
    Log.info("Functionality of search button is working properly");
    testresultdata.put("3", new Object[] {2d, "search working ", "search functionality working properly","Pass"});
    }
    
   
   else{
	   Alert alert=driver.switchTo().alert();
	   alert.accept();
	   System.out.println("TEST 3 : Data Not Found ");
	   Log.info("Data Not Found");
	   System.out.println("Functionality of search button is working properly");
	   Log.info("Functionality of search button is working properly");
	   testresultdata.put("3", new Object[] {2d, "search working", "search functionality is not working properly","Fail"});
   }
   }
    	
		//--------------------> clear search button functionality ------------------> 

		@Test(priority=2)
		public void courseClearButton() throws Exception{
		Thread.sleep(2000);	
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("dropdown_id"))));
		Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("dropdown_id")))); 
		dropdown.selectByVisibleText(data1[3][0]);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("searchbox_id"))));
		driver.findElement(By.id(prop.getProperty("searchbox_id"))).clear();
		driver.findElement(By.id(prop.getProperty("searchbox_id"))).sendKeys(data1[3][1]);	
	  
		//driver.findElement(By.id(prop.getProperty("searchbutton_id"))).click();
		    
			Thread.sleep(2000);
			driver.findElement(By.id(prop.getProperty("clearbutton_id"))).click();
			Thread.sleep(1000);
			
			WebElement TxtBoxContent = driver.findElement(By.id(prop.getProperty("searchbox_id")));
			TxtBoxContent.getText();
			
			if(TxtBoxContent.getText().equalsIgnoreCase("")){
			System.out.println("TEST 4 : Functionality of clear button is working properly");
			Log.info("Functionality of clear button is working properly");
			testresultdata.put("4", new Object[] {3d, "clearsearch working", "clearsearch functionality is working properly","Pass"});
			}
			else{
				System.out.println("TEST 4 : Functionality of clear button is not working properly");
				Log.info("Functionality of clear button is not working properly");
				testresultdata.put("4", new Object[] {3d, "clearsearch working", "clearsearch functionality is not working properly","Fail"});
			}
			
		}*/
		
		//----------------------> edit functionality ------------------->	 	  
	/*	@Test(priority=3)
		public void courseEdit() throws Exception{
		Thread.sleep(2000);
			driver.findElement(By.id(prop.getProperty("editbutton_id"))).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(2000);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("name_id"))));
			driver.findElement(By.id(prop.getProperty("name_id"))).clear();
			driver.findElement(By.id(prop.getProperty("name_id"))).sendKeys(data1[3][2]); 
						
			driver.findElement(By.id(prop.getProperty("okbutton_id"))).click();
			Thread.sleep(2000);
			Alert alert=driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();
			Thread.sleep(1000);

			try{
				
				Assert.assertTrue(driver.findElement(By.id(prop.getProperty("name_id"))).isDisplayed());
				 Thread.sleep(2000);
				driver.findElement(By.id(prop.getProperty("cancelbutton_id"))).click();
				System.out.println("TEST 5 :record not updated ");
				Log.info("record not updated");
				testresultdata.put("5", new Object[] {4d, "edit working", "edit functionality is working properly","Pass"});
			}
			catch(AssertionError e){
				System.out.println("TEST 5 : record updated successfully");
				Log.info("record not updated");
				testresultdata.put("5", new Object[] {4d, "edit working", "edit functionality is not working properly","Fail"});
				Assert.fail();
			}
			}*/
			
					
//--------------------> delete functionality ------------------>
		
	/*	@Test(priority=4)
		public void courseDelete() throws Exception{
		Thread.sleep(2000);
			driver.findElement(By.id(prop.getProperty("deletebutton_id"))).click();
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(2000);
						
			Alert alert=driver.switchTo().alert();
			System.out.println(alert.getText());
			
			try{
				Assert.assertEquals(alert.getText(), "Record Deleted Successfully");
				alert.accept();
				System.out.println("TEST 5 : record deleted successfully");
				Log.info("record deleted successfully");
				testresultdata.put("6", new Object[] {5d, "delete working", "delete functionality is working properly","Pass"});
			}
			catch(AssertionError e ){
				alert.accept();
				System.out.println("TEST 5 : record not deleted successfully");
				Log.info("record not deleted successfully");
				testresultdata.put("6", new Object[] {5d, "delete working", "delete functionality is not working properly","Fail"});
				Assert.fail();
			}
									
		}
		*/
//---------------> ADD NEW DATA USING DATA PROVIDER ---------------->	
	
	@Test(dataProvider = "getLoginData",priority=4)
	public void courseMasterNewData(String type,String subtype, String code, String name,String tname, String ttype, 
			String trainee, String trainer, String fromtime, String totime, String hours) throws InterruptedException, IOException{
		//System.setProperty("webdriver.chrome.driver",""));
	//baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "stm_linktext", driver);
	
	driver.findElement(By.id(prop.getProperty("addnewbutton_id"))).click();
	Thread.sleep(1000);
				
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("type_id"))));
	Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("type_id")))); 
	dropdown.selectByVisibleText(type);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("subtype_id"))));
	Select dropdown1 = new Select(driver.findElement(By.id(prop.getProperty("subtype_id")))); 
	dropdown1.selectByVisibleText(subtype);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("code_id"))));
	driver.findElement(By.id(prop.getProperty("code_id"))).sendKeys(code);
		
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("name_id"))));
	driver.findElement(By.id(prop.getProperty("name_id"))).sendKeys(name);
		
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("tname_id"))));
	driver.findElement(By.id(prop.getProperty("tname_id"))).clear();
	driver.findElement(By.id(prop.getProperty("tname_id"))).sendKeys(tname);
	
	driver.findElement(By.id(prop.getProperty("tlabel"))).click();
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("ttype_id"))));
	Select dropdown2 = new Select(driver.findElement(By.id(prop.getProperty("ttype_id")))); 
	try{
	dropdown2.selectByVisibleText(ttype);
	}
	catch(Exception e){
		
	}
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("trainee_id"))));
	Select dropdown3 = new Select(driver.findElement(By.id(prop.getProperty("trainee_id")))); 
	try{
		dropdown3.selectByVisibleText(trainee);
	}
	catch(Exception ex){
		
	}
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("trainer_id"))));
	Select dropdown4 = new Select(driver.findElement(By.id(prop.getProperty("trainer_id")))); 
	try {
		dropdown4.selectByVisibleText(trainer);
	}
	catch(Exception el){
		
	}
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("applicable_id"))));
	driver.findElement(By.id(prop.getProperty("applicable_id"))).click();
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("starttime_id"))));
	driver.findElement(By.id(prop.getProperty("starttime_id"))).clear();
	driver.findElement(By.id(prop.getProperty("starttime_id"))).sendKeys(fromtime);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("totime_id"))));
	driver.findElement(By.id(prop.getProperty("totime_id"))).clear();
	driver.findElement(By.id(prop.getProperty("totime_id"))).sendKeys(totime);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("capture_id"))));
	Select dropdown5 = new Select(driver.findElement(By.id(prop.getProperty("capture_id")))); 
	dropdown5.selectByVisibleText(hours);
		
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("okbutton_id"))));
	driver.findElement(By.id(prop.getProperty("okbutton_id"))).click();
		
	Thread.sleep(1000);
	Alert alert=driver.switchTo().alert();
	System.out.println(alert.getText());
	Thread.sleep(2000);
	
	try{
		Assert.assertEquals(alert.getText(), "Record Added Successfully");
		alert.accept();
	
	Thread.sleep(1000);
	System.out.println("TEST 6 : new data added successfully");
	
	Log.info("duplicate data or missing data : cancel add new process for this data");
	testresultdata.put(type, new Object[] {4d, "add functionality working", "add functionality is working properly","Pass"});	
	}
	catch(AssertionError e){
		alert.accept();
		driver.findElement(By.id(prop.getProperty("cancelbutton_id"))).click();
		System.out.println("TEST 6 :duplicate data or missing data : cancel add new process for this data");
		Log.info("new data not added ");
		testresultdata.put(type, new Object[] {4d, "add functionality working", "add functionality is not working properly","Fail"});
		//e.printStackTrace();
		Assert.fail();
		}
	}

		@DataProvider(name="getLoginData")
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

  /*  @Test(dataProvider="getLoginData",priority=1)
    public void TC01_Verify_Login_Valid_Cred(String User, String Pass) throws InterruptedException{

  //  System.out.println(User + Pass); 
    
    driver.findElement(By.id("Login1_UserName")).sendKeys(User);
    driver.findElement(By.id("Login1_Password")).sendKeys(Pass);
    driver.findElement(By.id("Login1_LoginButton")).click();
    driver.findElement(By.id("lnkBtn_Logout")).click();
    Thread.sleep(2000);
    
    }*/
		
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
		Log.info("Screenshot taken : Test Case failed");
		}
		catch (Exception e)
		{
		 
		System.out.println("Exception while taking screenshot "+e.getMessage());
		Log.info("Exception while taking screenshot");
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
			System.out.println("logout successfully ");
			Log.info("logout successfully");
			
			
		 /*  private boolean isElementPresent(By by) {
			         try {
			           driver.findElement(by);
			           return true;
			         } catch (NoSuchElementException e) {
			           return false;
			         }
			       }*/
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
		      testresultdata.put("1", new Object[] {"Test Step Id","Expected Result", "Actual Result","Status"});
		      
		  /*  try {
		      
		     driver1=new FirefoxDriver();
		     driver1.manage().window().maximize();
		     driver1.get("http://demo.osource.co.in/ERPONEX_INT_WEBAPP/Login.aspx");
		    // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		     } catch (Exception e) {
		      throw new IllegalStateException("Can't start Web Driver", e);
		    }*/
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
		         FileOutputStream out =new FileOutputStream(new File("D:\\HRMS DATA\\WriteDataSheet\\YogeshData.xls"));
		         workbook1.write(out);
		         out.close();
		         System.out.println("Excel written successfully..");
		          
		     } catch (FileNotFoundException e) {
		         e.printStackTrace();
		     } catch (IOException e) {
		         e.printStackTrace();
		     }
			
		}
	}
	


	




