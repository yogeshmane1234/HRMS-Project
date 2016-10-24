package HRSetup.PMSandTraining.Yogesh;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
//import org.testng.Assert;
//import org.testng.Assert;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


//import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;
import Excel.Excel;
//import HRMS_Recruitment.Login1;
import baseClass.BaseClass;

public class CompetencyMaster {

	//public String sheet1;
	public FileInputStream inputStream=null;
	BaseClass baseclass = new BaseClass();
	Excel dataXLS = new Excel();
	//Properties prop = baseclass.PropertiesConfigurations();
	Properties prop = baseclass.PropertiesConfigurations(".//src//Properties//PMS&Training.CompetencyMaster.properties");
	// Logger logger=baseclass.LogCongigurations();

	WebDriver driver = baseclass.DriverConfigurations();
	Workbook workbook = baseclass.DatasheetConfigurations("D:\\HRMS DATA\\Datasheet\\Login.xls");
	//String data[][] = dataXLS.CellData("Sheet1",0,8);
	String data[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\Login.xls", "Sheet1", 0, 11);
	String data1[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\PMS and Training\\CompetencyMaster.xls", "Sheet3", 0, 11);
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
		}
		else{
			
			System.out.println("TEST 1 : page crashed");
		}
			Actions action = new Actions(driver);
			baseclass.CommomSection2("HR_id", "HRsetup_linktext", driver);
							
			WebElement element2 = driver.findElement(By.xpath(prop
					.getProperty("PMSTraining_xpath")));
			action.moveToElement(element2).perform();

			Thread.sleep(1000);

			driver.findElement(By.linkText(prop.getProperty("competency_linktext"))).click();
			
			System.out.println("TEST 2 : Target Page open successfully");
			Thread.sleep(1000);
	}	
			
		// ----------------------> search functionality -------------------->
	
	@Test(priority=1)
	public void compSearchButton() throws Exception{
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
    System.out.println("Functionality of search button is working properly");
    }
    
   
   else{
	   Alert alert=driver.switchTo().alert();
	   alert.accept();
	   System.out.println("TEST 3 : Data Not Found ");
	   System.out.println("Functionality of search button is working properly");
   }
   }
    	
		//--------------------> clear search button functionality ------------------> 

		@Test(priority=2)
		public void compClearButton() throws Exception{
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
			}
			else{
				System.out.println("TEST 4 : Functionality of clear button is not working properly");
				
			}
			
		}
		
		//----------------------> edit functionality ------------------->	 	  
		@Test(priority=3)
		public void compEdit() throws Exception{
		Thread.sleep(2000);
			driver.findElement(By.id(prop.getProperty("editbutton_id"))).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(2000);
			
			wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("details_id"))));
			driver.findElement(By.id(prop.getProperty("details_id"))).clear();
			driver.findElement(By.id(prop.getProperty("details_id"))).sendKeys(data1[3][2]); 
						
			driver.findElement(By.id(prop.getProperty("save_id"))).click();
			Thread.sleep(2000);
			Alert alert=driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();
			Thread.sleep(1000);

			try{
				
			if(driver.findElement(By.id(prop.getProperty("details_id"))).isDisplayed())
			{
				 Thread.sleep(2000);
				driver.findElement(By.id(prop.getProperty("back_id"))).click();
				System.out.println("TEST 5 :record not updated ");
			}
			else{
				System.out.println("TEST 5 : record updated successfully");
			}
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
			 
										
		}

//---------------> ADD NEW DATA USING DATA PROVIDER ---------------->	
	
	@Test(dataProvider = "compdata",priority=4)
	public void competencyMasterNewData(String description, String details) throws Exception{
		//System.setProperty("webdriver.chrome.driver",""));
	//baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "stm_linktext", driver);
	
	driver.findElement(By.id(prop.getProperty("addnewbutton_id"))).click();
	Thread.sleep(1000);
				
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("description_id"))));
	driver.findElement(By.id(prop.getProperty("description_id"))).sendKeys(description);
		
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("details_id"))));
	driver.findElement(By.id(prop.getProperty("details_id"))).sendKeys(details);
		
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("category_id"))));
	driver.findElement(By.id(prop.getProperty("category_id"))).click();
		
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("save_id"))));
	driver.findElement(By.id(prop.getProperty("save_id"))).click();
		
	Thread.sleep(1000);
	Alert alert=driver.switchTo().alert();
	System.out.println(alert.getText());
	alert.accept();
	Thread.sleep(1000);
	
	try
	{
	driver.findElement(By.id(prop.getProperty("details_id"))).isDisplayed();
	driver.findElement(By.id(prop.getProperty("back_id"))).click();
	Thread.sleep(1000);
	System.out.println("TEST 6 :duplicate data or missing data : cancel add new process for this data");
		
	}
	catch(Exception e){
		
		driver.getTitle().equalsIgnoreCase("Competency Master");
		System.out.println("TEST 6 : new data added successfully");
		//e.printStackTrace();
		}
	}

		@DataProvider(name="compdata")
		public Object[][] readExcel() throws BiffException, IOException{
			
			FileInputStream abc = new FileInputStream("D:\\HRMS DATA\\Datasheet\\PMS and Training\\CompetencyMaster.xls");
			//File f = new File("D:\\Login.xlsx");
			Workbook wb = Workbook.getWorkbook(abc);
			Sheet s = wb.getSheet("Sheet1");
			
			int rows = s.getRows();
			int columns = s.getColumns();
			//System.out.println(rows);
			//System.out.println(columns);
			
			String inputData[][] = new String[rows][columns];
			for (int i=0;i<rows;i++){
				for(int j=0; j<columns; j++){
					Cell c= s.getCell(j,i);
					inputData[i][j]=c.getContents();
					//System.out.println(inputData[i][j]);
				}
			}
			return inputData;
		
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
	}
	


	




