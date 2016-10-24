
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
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

public class GroupMaster {

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
	String data1[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\Employee Life Cycle\\GroupMaster.xls", "Sheet3", 0, 11);
	//String data2[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\BasicSalaryRangeData.xls", "Sheet1", 0, 11);
	
	WebDriverWait wait = new WebDriverWait(driver, 180);

		// ****************Login to Application****************
	@BeforeTest
	public void Login() throws BiffException, IOException, InterruptedException, Exception{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		baseclass.login(data[1][0], data[1][1], driver);
		System.out.println("TEST 1 : Login successfully");
		baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "gm_linktext", driver);
	}
	// ----------------------> search functionality -------------------->
	
	

	@Test(priority=0)
	public void TestSearchButton() throws Exception{
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("gmsearchbox_id"))));
	driver.findElement(By.id(prop.getProperty("gmsearchbox_id"))).sendKeys(data1[3][1]);	
  
	driver.findElement(By.id(prop.getProperty("gmsearchbutton_id"))).click();
   // Assert.assertEquals(driver.getTitle(), "Question Master");
    boolean search = driver.getPageSource().contains(data1[3][1]);
    
       
    if(search)
    {
    System.out.println("TEST 2 :search value is Present");
    }
    else
    {
    System.out.println("TEST 2 :search value is not Present");
    }	
    	
	try{
		driver.getPageSource().contains(data1[3][1]);
		//driver.findElement(By.id(prop.getProperty("sdmsearchbutton_id"))).isSelected();
		System.out.println("TEST 2 :Functionality of search button is working properly");
	}
	
	//----------------take a screen shot ------------------------->
	catch(Exception e){
		System.out.println("TEST 2 :I'm in exception : search button not working");
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
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	   // Assert.assertEquals(driver.getTitle(), "Question Master");
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("gmsearchbox_id"))));
		driver.findElement(By.id(prop.getProperty("gmsearchbox_id"))).clear();
		driver.findElement(By.id(prop.getProperty("gmsearchbox_id"))).sendKeys(data1[3][1]);	
	  
		driver.findElement(By.id(prop.getProperty("gmsearchbutton_id"))).click();
		    
		try{
			Thread.sleep(2000);
			driver.findElement(By.id(prop.getProperty("gmclearbutton_id"))).click();
			Thread.sleep(2000);
			
			WebElement TxtBoxContent = driver.findElement(By.id(prop.getProperty("gmsearchbox_id")));
			TxtBoxContent.getText();
			
			//Assert.assertEquals(TxtBoxContent.getText(), "");
			if(TxtBoxContent.getText().equalsIgnoreCase("")){
			System.out.println("TEST 3 : Functionality of clear button is working properly");
			}
			else{
				System.out.println("TEST 3 : Functionality of clear button is not working properly");
			}
			
		}
		
		//----------------take a screen shot ------------------------->
		catch(Exception e){
			
			System.out.println("TEST 3 : I'm in exception : Clear button is not working");
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
				driver.findElement(By.id(prop.getProperty("gmedit_id"))).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Thread.sleep(2000);
				
				driver.findElement(By.id(prop.getProperty("gmfromdate_id"))).clear();
				driver.findElement(By.id(prop.getProperty("gmfromdate_id"))).sendKeys(data1[3][2]);
				Thread.sleep(2000);
				driver.findElement(By.id(prop.getProperty("gmholesave_id"))).click();
				Thread.sleep(2000);
				
				driver.getTitle().equalsIgnoreCase("Group Master");
				System.out.println("TEST 4 :record updated successfully");
				
												
			}
	
	@Test(dataProvider = "GMdata")
	public void groupmasterNewdata(String gname, String validfrom, String validto, String yem, 
			String first,String second, String third) throws Exception{
		//System.setProperty("webdriver.chrome.driver",""));
	//baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "stm_linktext", driver);
	
	driver.findElement(By.id(prop.getProperty("gmaddnew_id"))).click();
	Thread.sleep(1000);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("gmname_id"))));
	driver.findElement(By.id(prop.getProperty("gmname_id"))).sendKeys(gname);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("gmfromdate_id"))));
	driver.findElement(By.id(prop.getProperty("gmfromdate_id"))).sendKeys(validfrom);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("gmtodate_id"))));
	driver.findElement(By.id(prop.getProperty("gmtodate_id"))).sendKeys(validto);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("gmeffectivedatefrom_xpath"))));
	WebElement element =driver.findElement(By.xpath(prop.getProperty("gmeffectivedatefrom_xpath")));
	Thread.sleep(2000);
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("arguments[0].setAttribute('value','29-Sep-2016')",element);
	  
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("gmeffectivedateto_xpath"))));
	WebElement element1 =driver.findElement(By.xpath(prop.getProperty("gmeffectivedateto_xpath")));
	Thread.sleep(2000);
	JavascriptExecutor js1 = (JavascriptExecutor) driver;
	js1.executeScript("arguments[0].setAttribute('value','16-Dec-2016')",element1);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("gmyearend_id"))));
	Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("gmyearend_id")))); 
	dropdown.selectByVisibleText(yem);
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("gmfirst_id"))));
	Select dropdown1 = new Select(driver.findElement(By.id(prop.getProperty("gmfirst_id")))); 
	dropdown1.selectByVisibleText(first);
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("gmsecond_id"))));
	Select dropdown2 = new Select(driver.findElement(By.id(prop.getProperty("gmsecond_id")))); 
	dropdown2.selectByVisibleText(second);
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("gmthird_id"))));
	Select dropdown3 = new Select(driver.findElement(By.id(prop.getProperty("gmthird_id")))); 
	dropdown3.selectByVisibleText(third);
	Thread.sleep(1000);
	
	driver.findElement(By.id(prop.getProperty("gmset_id"))).click();
	Thread.sleep(1000);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("gmaddrecord_id"))));
	driver.findElement(By.id(prop.getProperty("gmaddrecord_id"))).click();
	Thread.sleep(1000);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("gmgrade_id"))));
	Select dropdown6 = new Select(driver.findElement(By.id(prop.getProperty("gmgrade_id")))); 
	dropdown6.selectByVisibleText(data1[8][0]);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("gmdesignation_id"))));
	Select dropdown5 = new Select(driver.findElement(By.id(prop.getProperty("gmdesignation_id")))); 
	dropdown5.selectByVisibleText(data1[8][1]);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("gmdepartment_id"))));
	Select dropdown7 = new Select(driver.findElement(By.id(prop.getProperty("gmdepartment_id")))); 
	dropdown7.selectByVisibleText(data1[8][2]);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("gmsave_id"))));
	driver.findElement(By.id(prop.getProperty("gmsave_id"))).click();
	Thread.sleep(2000);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("gmholesave_id"))));
	driver.findElement(By.id(prop.getProperty("gmholesave_id"))).click();
		
	Thread.sleep(2000);
		
	try{
		
		Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		Thread.sleep(2000);
		
	driver.findElement(By.id(prop.getProperty("gmname_id"))).isEnabled();
	driver.findElement(By.id(prop.getProperty("gmback_id"))).click();
	Thread.sleep(1000);
	System.out.println("TEST 5 :duplicate data : clicked cancel button");
		
	}
	catch(Exception e){
		System.out.println("TEST 5 :new data added successfully");
		e.printStackTrace();
	}
	}

		@DataProvider(name="GMdata")
		public Object[][] readExcel() throws BiffException, IOException{
			
			FileInputStream abc = new FileInputStream("D:\\HRMS DATA\\Datasheet\\Employee Life Cycle\\GroupMaster.xls");
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
		
		//----------------------> accrual config status edit ------------------->	 	  
	@Test(dataProvider = "actdata")
	public void Accrualconfig(String at, String atl, String applicable, String fd, 
			String td,String d, String carry, String accumulation, String encashday, String encaashpercent) throws Exception{
		//System.setProperty("webdriver.chrome.driver",""));
	//baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "stm_linktext", driver);
	
	driver.findElement(By.id(prop.getProperty("actedit_id"))).click();
	Thread.sleep(1000);
	driver.findElement(By.id(prop.getProperty("actinneredit_id"))).click();
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("acttype_id"))));
	Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("acttype_id")))); 
	dropdown.selectByVisibleText(at);
	Thread.sleep(2000);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("actmonthannualtotalleave_id"))));
	driver.findElement(By.id(prop.getProperty("actmonthannualtotalleave_id"))).click();
	driver.findElement(By.id(prop.getProperty("actmonthannualtotalleave_id"))).clear();
	Thread.sleep(1000);
	driver.findElement(By.id(prop.getProperty("actmonthannualtotalleave_id"))).sendKeys(atl);
	Thread.sleep(1000);
	driver.findElement(By.id(prop.getProperty("actlabel_id"))).click();
	Thread.sleep(1000);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("actapplicablefor_id"))));
	Select dropdown1 = new Select(driver.findElement(By.id(prop.getProperty("actapplicablefor_id")))); 
	dropdown1.selectByVisibleText(applicable);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("actfromday_id"))));
	driver.findElement(By.id(prop.getProperty("actfromday_id"))).clear();
	driver.findElement(By.id(prop.getProperty("actfromday_id"))).sendKeys(fd);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("acttoday_id"))));
	driver.findElement(By.id(prop.getProperty("acttoday_id"))).clear();
	driver.findElement(By.id(prop.getProperty("acttoday_id"))).sendKeys(td);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("actday_id"))));
	driver.findElement(By.id(prop.getProperty("actday_id"))).clear();
	driver.findElement(By.id(prop.getProperty("actday_id"))).sendKeys(d);
	
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	jse.executeScript("window.scrollBy(0,250)", "");
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("actmaxcarry_id"))));
	driver.findElement(By.id(prop.getProperty("actmaxcarry_id"))).clear();
	driver.findElement(By.id(prop.getProperty("actmaxcarry_id"))).sendKeys(carry);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("actmaxaccumulation_id"))));
	driver.findElement(By.id(prop.getProperty("actmaxaccumulation_id"))).clear();
	driver.findElement(By.id(prop.getProperty("actmaxaccumulation_id"))).sendKeys(accumulation);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("actmaxencashday_id"))));
	driver.findElement(By.id(prop.getProperty("actmaxencashday_id"))).clear();
	driver.findElement(By.id(prop.getProperty("actmaxencashday_id"))).sendKeys(encashday);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("actmaxencashment_id"))));
	driver.findElement(By.id(prop.getProperty("actmaxencashment_id"))).clear();
	driver.findElement(By.id(prop.getProperty("actmaxencashment_id"))).sendKeys(encaashpercent);
	
	
	driver.findElement(By.id(prop.getProperty("actsavebutton_id"))).click();
		
	Thread.sleep(2000);
		
	try{
		
		Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		Thread.sleep(2000);
		
	driver.findElement(By.id(prop.getProperty("actmaxencashment_id"))).isEnabled();
	driver.findElement(By.id(prop.getProperty("actcancelbutton_id"))).click();
	driver.findElement(By.id(prop.getProperty("actbackbutton_id"))).click();
	
	Thread.sleep(1000);
	System.out.println("TEST 6 :duplicate data or missing data : clicked cancel button");
		
	}
	catch(Exception e){
	
		driver.findElement(By.id(prop.getProperty("actbackbutton_id"))).click();
		System.out.println("TEST 6 :accrual config successfully");
		//e.printStackTrace();
	}
	}

		@DataProvider(name="actdata")
		public Object[][] readExcel1() throws BiffException, IOException{
			
			FileInputStream abc = new FileInputStream("D:\\HRMS DATA\\Datasheet\\Employee Life Cycle\\GroupMaster.xls");
			//File f = new File("D:\\Login.xlsx");
			Workbook wb = Workbook.getWorkbook(abc);
			Sheet s = wb.getSheet("Sheet4");
			
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

	@Test(dataProvider = "appdata")
	public void Appconfig(String at, String atl, String applicable, String fd, 
			String td,String d, String carry) throws Exception{
		//System.setProperty("webdriver.chrome.driver",""));
	//baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "stm_linktext", driver);
	
	driver.findElement(By.id(prop.getProperty("acsedit_id"))).click();
	Thread.sleep(1000);
	driver.findElement(By.id(prop.getProperty("acssubedit_id"))).click();
	
		
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("minl_id"))));
	driver.findElement(By.id(prop.getProperty("minl_id"))).clear();
	driver.findElement(By.id(prop.getProperty("minl_id"))).sendKeys(at);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("maxl_id"))));
	driver.findElement(By.id(prop.getProperty("maxl_id"))).clear();
	driver.findElement(By.id(prop.getProperty("maxl_id"))).sendKeys(atl);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("avail_id"))));
	driver.findElement(By.id(prop.getProperty("avail_id"))).clear();
	driver.findElement(By.id(prop.getProperty("avail_id"))).sendKeys(applicable);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("tenure_id"))));
	driver.findElement(By.id(prop.getProperty("tenure_id"))).clear();
	driver.findElement(By.id(prop.getProperty("tenure_id"))).sendKeys(fd);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("certi_id"))));
	driver.findElement(By.id(prop.getProperty("certi_id"))).clear();
	driver.findElement(By.id(prop.getProperty("certi_id"))).sendKeys(td);
	
	driver.findElement(By.linkText(prop.getProperty("advance_linktext"))).click();
	Thread.sleep(2000);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("allow_id"))));
	driver.findElement(By.id(prop.getProperty("allow_id"))).clear();
	driver.findElement(By.id(prop.getProperty("allow_id"))).sendKeys(d);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("months_id"))));
	driver.findElement(By.id(prop.getProperty("months_id"))).clear();
	driver.findElement(By.id(prop.getProperty("months_id"))).sendKeys(carry);
	driver.findElement(By.id(prop.getProperty("monthlabel_id"))).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath(prop.getProperty("save_xpath"))).click();
	Thread.sleep(2000);
		
	try{
		
		Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		Thread.sleep(2000);
		
	driver.findElement(By.id(prop.getProperty("months_id"))).isEnabled();
	driver.findElement(By.id(prop.getProperty("acscancel_id"))).click();
	
	
	Thread.sleep(1000);
	System.out.println("TEST 7 :duplicate data or missing data: clicked cancel button");
		
	}
	catch(Exception e){
		
		System.out.println("TEST 7 :app config successfully");
		//e.printStackTrace();
	}
	Thread.sleep(1000);
	driver.findElement(By.id(prop.getProperty("acsback_id"))).click();
	
	}

		@DataProvider(name="appdata")
		public Object[][] readExcel2() throws BiffException, IOException{
			
			FileInputStream abc = new FileInputStream("D:\\HRMS DATA\\Datasheet\\Employee Life Cycle\\GroupMaster.xls");
			//File f = new File("D:\\Login.xlsx");
			Workbook wb = Workbook.getWorkbook(abc);
			Sheet s = wb.getSheet("Sheet6");
			
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
			
			// ---------------> att config status ---------->
		
	@Test
	public void attconfigstatus() throws Exception{
		//System.setProperty("webdriver.chrome.driver",""));
	//baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "stm_linktext", driver);
	
	driver.findElement(By.id(prop.getProperty("editbutton_id"))).click();
	Thread.sleep(1000);
			
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("checkbox_id"))));
	driver.findElement(By.id(prop.getProperty("checkbox_id"))).click();
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("attsave_id"))));
	driver.findElement(By.id(prop.getProperty("attsave_id"))).click();
	
	
		Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		Thread.sleep(2000);
		
		Assert.assertEquals(driver.getTitle(), "Attendance Rule Configuration");
		driver.findElement(By.id(prop.getProperty("attcancel_id"))).click();
		System.out.println("TEST 8 :att config status updated successfully");
	
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

	


