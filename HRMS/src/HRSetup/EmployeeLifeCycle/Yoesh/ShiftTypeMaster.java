



//////////follow this code only \\\\\\\\\\\



package HRSetup.EmployeeLifeCycle.Yoesh;

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
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class ShiftTypeMaster {

	//public String sheet1;
	public FileInputStream inputStream=null;
	BaseClass baseclass = new BaseClass();
	Excel dataXLS = new Excel();
	Properties prop = baseclass.PropertiesConfigurations();
	// Logger logger=baseclass.LogCongigurations();

	WebDriver driver = baseclass.DriverConfigurations();
	Workbook workbook = baseclass.DatasheetConfigurations("D:\\HRMS DATA\\Datasheet\\Login.xls");
	//String data[][] = dataXLS.CellData("Sheet1",0,8);
	String data[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\Login.xls", "Sheet1", 0, 11);
	String data1[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\Employee Life Cycle\\ShiftTypeMaster.xls", "Sheet3", 0, 11);
	//String data2[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\BasicSalaryRangeData.xls", "Sheet1", 0, 11);
	
	WebDriverWait wait = new WebDriverWait(driver, 180);

		// ****************Login to Application****************
	@BeforeTest
	public void Login() throws BiffException, IOException, InterruptedException, Exception{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		baseclass.login(data[1][0], data[1][1], driver);
		baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "stm_linktext", driver);
	}
	// ----------------------> search functionality -------------------->
	@Test(priority=0)
	public void TestSearchButton() throws Exception{
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("stmdropdown_id"))));
	Select Searchdropdown = new Select(driver.findElement(By.id(prop.getProperty("stmdropdown_id")))); 
	Searchdropdown.selectByVisibleText(data1[3][0]);
	driver.findElement(By.id(prop.getProperty("stmsearchbox_id"))).sendKeys(data1[3][1]);	
  
	driver.findElement(By.id(prop.getProperty("stmsearchbutton_id"))).click();
   // Assert.assertEquals(driver.getTitle(), "Question Master");
    boolean search = driver.getPageSource().contains(data1[3][1]);
       
    if(search)
    {
    System.out.println("search value is Present");
    }
    else
    {
    System.out.println("search value is not Present");
    }	
    	
	try{
		
		driver.findElement(By.id(prop.getProperty("HMSearchbutton_id"))).isSelected();
		System.out.println("Functionality of search button is working properly");
	}
	
	//----------------take a screen shot ------------------------->
	catch(Exception e){
		System.out.println("I'm in exception : button not working");
		getscreenshot();
			}
	}
		public void getscreenshot() throws Exception 
		{
				 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				 FileUtils.copyFile(scrFile, new File("D:\\search.png"));
		}
		
	//--------------------> clear search button functionality ------------------> 

		@Test(priority=1)
		public void TestClearSearchButton() throws Exception{
		Thread.sleep(2000);	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	   // Assert.assertEquals(driver.getTitle(), "Question Master");
		
	      
		try{
			driver.findElement(By.id(prop.getProperty("stmclearbutton_id"))).click();
			
			WebElement TxtBoxContent = driver.findElement(By.id(prop.getProperty("stmsearchbox_id")));
			TxtBoxContent.getText();
			
			//Assert.assertEquals(TxtBoxContent.getText(), "");
			if(TxtBoxContent.getText().equalsIgnoreCase("")){
			System.out.println("Functionality of clear button is working properly");}
			else{
				System.out.println("Functionality of clear button is not working properly");
			}
			
		}
		
		//----------------take a screen shot ------------------------->
		catch(Exception e){
			
			System.out.println("I'm in exception : Clear button is not working");
			getscreenshot1(); 
				}
		}
			public void getscreenshot1() throws Exception 
			{
					 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					 FileUtils.copyFile(scrFile, new File("D:\\clearbutton.png"));
			}
	//----------------------> edit functionality ------------------->	 	  
			@Test(priority=2)
			public void Edit() throws Exception{
			Thread.sleep(2000);
				driver.findElement(By.id(prop.getProperty("stmeditbutton_id"))).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Thread.sleep(2000);
				driver.findElement(By.id(prop.getProperty("stmname_id"))).clear();
				driver.findElement(By.id(prop.getProperty("stmname_id"))).sendKeys(data1[3][2]);
				driver.findElement(By.id(prop.getProperty("stmokbutton_id"))).click();
				
				Alert alert=driver.switchTo().alert();
				System.out.println(alert.getText());
				alert.accept();
												
			}
			
	@Test(dataProvider = "STMdata")
	public void ShiftTypeMasterdata(String scode, String sname, String active, String stimehh,
			String stimemm, String etimehh, String etimemm) throws Exception{
		//System.setProperty("webdriver.chrome.driver",""));
	//baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "stm_linktext", driver);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.findElement(By.id(prop.getProperty("stmaddnewbutton_id"))).click();
	Thread.sleep(1000);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("stmshiftcode_id"))));
	driver.findElement(By.id(prop.getProperty("stmshiftcode_id"))).sendKeys(scode);
		
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("stmname_id"))));
	driver.findElement(By.id(prop.getProperty("stmname_id"))).sendKeys(sname);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("stmactive_id"))));
	Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("stmactive_id")))); 
	dropdown.selectByVisibleText(active);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("stmstarttimehh_id"))));
	driver.findElement(By.id(prop.getProperty("stmstarttimehh_id"))).sendKeys(stimehh);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("stmstarttimemm_id"))));
	driver.findElement(By.id(prop.getProperty("stmstarttimemm_id"))).sendKeys(stimemm);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("stmendtimehh_id"))));
	driver.findElement(By.id(prop.getProperty("stmendtimehh_id"))).sendKeys(etimehh);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("stmtmendtimemm_id"))));
	driver.findElement(By.id(prop.getProperty("stmtmendtimemm_id"))).sendKeys(etimemm);
	
	driver.findElement(By.id(prop.getProperty("stmokbutton_id"))).click();
	Thread.sleep(2000);
	System.out.println(" data Accepted successfully");
		
	Alert alert=driver.switchTo().alert();
	System.out.println(alert.getText());
	alert.accept();
	Thread.sleep(2000);
		
	
	try{
		
	driver.findElement(By.id(prop.getProperty("stmactive_id"))).isEnabled();
	driver.findElement(By.id(prop.getProperty("stmcancelbutton_id"))).click();
	System.out.println("duplicate data : clicked cancel button");
		
	}
	catch(Exception e){
		System.out.println("new data added successfully");
	}
	}

		@DataProvider(name="STMdata")
		public Object[][] readExcel() throws BiffException, IOException{
			
			FileInputStream abc = new FileInputStream("D:\\HRMS DATA\\Datasheet\\Employee Life Cycle\\ShiftTypeMaster.xls");
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

	

