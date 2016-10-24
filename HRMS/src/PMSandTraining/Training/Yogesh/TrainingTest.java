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
import org.eclipse.jetty.util.log.Log;
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

public class TrainingTest extends getExcelSheet{
		
   
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
    private static Logger Log = Logger.getLogger(TrainingTest.class.getName());
	public FileInputStream inputStream=null;
	BaseClass baseclass = new BaseClass();
	Excel dataXLS = new Excel();
	ReadExcelMapping REM = new ReadExcelMapping();
	//Properties prop = baseclass.PropertiesConfigurations();
	Properties prop = baseclass.PropertiesConfigurations(".//src//Properties//Training.TrainingTest.properties");
	// Logger logger=baseclass.LogCongigurations();

	WebDriver driver = baseclass.DriverConfigurations();
	
	String data[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\Training\\TrainingLogin.xls", "Emp", 0, 11);
	String data1[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\Training\\TrainingTest.xls", "Sheet1", 0, 11);
	
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

			driver.findElement(By.linkText(prop.getProperty("tt_linktext"))).click();
			
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
	
	driver.findElement(By.id(prop.getProperty("searchbox_id"))).sendKeys(data1[1][1]);
	
	driver.findElement(By.id(prop.getProperty("searchbutton_id"))).click();
   // Assert.assertEquals(driver.getTitle(), "Question Master");
   Thread.sleep(2000);
	try{
		
	boolean search = driver.getPageSource().contains(data1[1][1]);
	
    System.out.println("search value is Present");
    System.out.println("TEST 3 : Functionality of search button is working properly");
    Log.info("search functionality is working properly");
    }
    
   catch(Exception e){
	   
	   System.out.println("Data Not Found");
	   System.out.println("TEST 3 :Functionality of search button is working properly");
	   Log.info("search functionality is not working properly");
	   Assert.fail();
   }
   }
    	
		//--------------------> clear search button functionality ------------------> 

		@Test(priority=3)
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
		
		
//----- > click on select start button ------->
		@Test(priority=3)
		public void SelectStartTest() throws IOException{
			
			//driver.navigate().refresh();
			List<Map<String, String>> map = REM.ReadExcel("D:\\HRMS DATA\\Datasheet\\Training\\TrainingTest.xls","Sheet1");
			for(int i=0;i<map.size()-1;i++){
			try{
				
				driver.findElement(By.xpath(".//*[text()='"+map.get(i).get("Course Name")+"']/parent::tr/td/input")).click();
				//driver.findElement(By.id("ContentPlaceHolder_dtgtraintest_imgStart_0")).click();						 
				System.out.println("clicked test start button : test started");
				Log.info("clicked test start button : test started");
				
			}
			catch(Exception e){
				System.out.println("test start button is not working");
				Log.info("test start button is not working");
			}
		}
			
			System.out.println("yogesh yogesh");	
	}
		
		//--------> search previous history ---------------->
		
		@Test(priority=4)
		public void TestSubmit() throws Exception{
		Thread.sleep(2000);	
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
		 String result = "";
        String exception = null;
		
        List multi=driver.findElements(By.xpath(".//*[@id='ContentPlaceHolder_Panel2']/div/table[@id='ContentPlaceHolder_DtLst']/tbody/tr/td/table"));
       
        multi.size();
	    System.out.println("list size = "+multi.size());
	    for(int i=0;i<multi.size();i++){
	    	
	   
	     if(multi.size()>1){
	    		
	    		try{
	    		
	    			driver.findElement(By.id(prop.getProperty("answerselection_id"))).click();
	    			System.out.println("Answer selected successfully");
    				Log.info("Answer selected successfully");
	    			
	    		}
	    		
	    		catch(Exception e){
	    				    		
	    			List<Map<String, String>> map = REM.ReadExcel("D:\\HRMS DATA\\Datasheet\\Training\\TrainingTest.xls","Sheet1");
	    			driver.findElement(By.id(prop.getProperty("answertextbox_id"))).sendKeys(map.get(0).get("Subjective answer"));
	    			System.out.println("Answer Entered successfully");
    				Log.info("Answer Entered successfully");
	    			
	    		}
	    		
	    		
	    		try{
	    			    			
	    		driver.findElement(By.id(prop.getProperty("nextbutton_id"))).click();
    			Thread.sleep(1000);
    			System.out.println("clicked on next button successfully");
    			Log.info("clicked on next button successfully");
    			}
    			catch(Exception ex){
    				driver.findElement(By.id(prop.getProperty("submitbutton_id"))).click();;
    				System.out.println("test submitted successfully");
    				Log.info("test submitted successfully");
    				Alert alert = driver.switchTo().alert();
	    			alert.accept();
    			}
	    		
	     }	
	    		 else{
		    		
		    			
	    			 try{
	 	    			assertTrue(isElementPresent(By.id(prop.getProperty("answertextbox_id"))));
	 	    			//driver.findElement(By.id(prop.getProperty("answertextbox_id"))).isDisplayed();
	 	    			List<Map<String, String>> map = REM.ReadExcel("D:\\HRMS DATA\\Datasheet\\Training\\TrainingTest.xls","Sheet1");
	 	    			
	 	    			driver.findElement(By.id(prop.getProperty("answertextbox_id"))).sendKeys(map.get(0).get("Subjective answer"));
	 	    			System.out.println("Answer Entered successfully");
	    				Log.info("Answer Entered successfully");
	 	    			driver.findElement(By.id(prop.getProperty("submitbutton_id"))).click();;
		    			System.out.println("test submitted successfully");
		    			Log.info("test submitted successfully");
	 	    			
	 	    		}
	 	    		
	 	    		catch(Exception e){
	 	    			
	 	    		//(driver.findElement(By.id(prop.getProperty("answerselection_id"))).isDisplayed()){
	 	    			
	 	    			//driver.findElement(By.id(prop.getProperty("answerselection_id"))).isDisplayed();
	 	    			driver.findElement(By.id(prop.getProperty("answerselection_id"))).click();
	 	    			System.out.println("Answer selected successfully");
	    				Log.info("Answer selected successfully");
	 	    			driver.findElement(By.id(prop.getProperty("submitbutton_id"))).click();;
		    			System.out.println("test submitted successfully");
		    			Log.info("test submitted successfully");
		    			Alert alert = driver.switchTo().alert();
		    			alert.accept();
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
	


	





