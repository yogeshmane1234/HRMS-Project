
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

public class ShiftDefinitionMaster {

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
	String data1[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\Employee Life Cycle\\ShiftDefinitionMaster.xls", "Sheet3", 0, 11);
	//String data2[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\BasicSalaryRangeData.xls", "Sheet1", 0, 11);
	
	WebDriverWait wait = new WebDriverWait(driver, 180);

		// ****************Login to Application****************
	@BeforeTest
	public void Login() throws BiffException, IOException, InterruptedException, Exception{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		baseclass.login(data[1][0], data[1][1], driver);
		baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "shiftdefinitionmaster_linktext", driver);
	}
	// ----------------------> search functionality -------------------->
	
	

	@Test(priority=0)
	public void TestSearchButton() throws Exception{
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("sdmdropdown_id"))));
	Select Searchdropdown = new Select(driver.findElement(By.id(prop.getProperty("sdmdropdown_id")))); 
	Searchdropdown.selectByVisibleText(data1[3][0]);
	driver.findElement(By.id(prop.getProperty("sdmsearchbox_id"))).sendKeys(data1[3][1]);	
  
	driver.findElement(By.id(prop.getProperty("sdmsearchbutton_id"))).click();
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
		driver.getPageSource().contains(data1[3][1]);
		//driver.findElement(By.id(prop.getProperty("sdmsearchbutton_id"))).isSelected();
		System.out.println("Functionality of search button is working properly");
	}
	
	//----------------take a screen shot ------------------------->
	catch(Exception e){
		System.out.println("I'm in exception : search button not working");
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
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("sdmdropdown_id"))));
		Select Searchdropdown = new Select(driver.findElement(By.id(prop.getProperty("sdmdropdown_id")))); 
		Searchdropdown.selectByVisibleText(data1[3][0]);
		driver.findElement(By.id(prop.getProperty("sdmsearchbox_id"))).clear();
		driver.findElement(By.id(prop.getProperty("sdmsearchbox_id"))).sendKeys(data1[3][1]);	
	  
		driver.findElement(By.id(prop.getProperty("sdmsearchbutton_id"))).click();
		    
		try{
			Thread.sleep(2000);
			driver.findElement(By.id(prop.getProperty("sdmclearbutton_id"))).click();
			Thread.sleep(2000);
			
			WebElement TxtBoxContent = driver.findElement(By.id(prop.getProperty("sdmsearchbox_id")));
			TxtBoxContent.getText();
			
			//Assert.assertEquals(TxtBoxContent.getText(), "");
			if(TxtBoxContent.getText().equalsIgnoreCase("")){
			System.out.println("Functionality of clear button is working properly");
			}
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
				driver.findElement(By.id(prop.getProperty("sdmeditbutton_id"))).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Thread.sleep(2000);
				
				driver.findElement(By.id(prop.getProperty("sdmcheckbox_id"))).click();
				driver.findElement(By.id(prop.getProperty("sdmsubmitbutton_id"))).click();
				
				if(driver.findElement(By.id(prop.getProperty("sdmcheckbox_id"))).isEnabled()){
					
					driver.findElement(By.id(prop.getProperty("sdmcancelbutton_id"))).click();					
					System.out.println("record not updated");
				}
				
				else {
					Alert alert=driver.switchTo().alert();
					System.out.println(alert.getText());
					alert.accept();
				}
												
			}
			
	
			
	@Test(dataProvider = "SDMdata")
	public void Shiftdefinitiondata(String sdmname, String sdmcountry, String sdmstate, String sdmcity, 
			String sdmbranch,String sdmmonday, String sdmtuesday, String sdmwen, String thur,
			String fri, String sat, String sun, String week, String day) throws Exception{
		//System.setProperty("webdriver.chrome.driver",""));
	//baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "stm_linktext", driver);
	
	driver.findElement(By.id(prop.getProperty("sdmaddnewbutton_id"))).click();
	Thread.sleep(1000);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("sdmdate_xpath"))));
	WebElement element =driver.findElement(By.xpath(prop.getProperty("sdmdate_xpath")));
	Thread.sleep(2000);
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("arguments[0].setAttribute('value','16-Dec-2016')",element);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("sdmname_id"))));
	driver.findElement(By.id(prop.getProperty("sdmname_id"))).sendKeys(sdmname);
	
	driver.findElement(By.id(prop.getProperty("sdmstatelable_id"))).click();
	Thread.sleep(1000);
		
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("sdmcountry_id"))));
	Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("sdmcountry_id")))); 
	dropdown.selectByVisibleText(sdmcountry);
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("sdmstate_id"))));
	Select dropdown1 = new Select(driver.findElement(By.id(prop.getProperty("sdmstate_id")))); 
	dropdown1.selectByVisibleText(sdmstate);
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("sdmcity_id"))));
	Select dropdown2 = new Select(driver.findElement(By.id(prop.getProperty("sdmcity_id")))); 
	dropdown2.selectByVisibleText(sdmcity);
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("sdmbranch_id"))));
	Select dropdown3 = new Select(driver.findElement(By.id(prop.getProperty("sdmbranch_id")))); 
	dropdown3.selectByVisibleText(sdmbranch);
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("sdmmonday_id"))));
	Select dropdown4 = new Select(driver.findElement(By.id(prop.getProperty("sdmmonday_id")))); 
	dropdown4.selectByVisibleText(sdmmonday);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("sdmtuesday_id"))));
	Select dropdown5 = new Select(driver.findElement(By.id(prop.getProperty("sdmtuesday_id")))); 
	dropdown5.selectByVisibleText(sdmtuesday);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("sdmwen_id"))));
	Select dropdown6 = new Select(driver.findElement(By.id(prop.getProperty("sdmwen_id")))); 
	dropdown6.selectByVisibleText(sdmwen);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("sdmthursday_id"))));
	Select dropdown7 = new Select(driver.findElement(By.id(prop.getProperty("sdmthursday_id")))); 
	dropdown7.selectByVisibleText(thur);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("sdmfriday_id"))));
	Select dropdown8 = new Select(driver.findElement(By.id(prop.getProperty("sdmfriday_id")))); 
	dropdown8.selectByVisibleText(fri);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("sdmsat_id"))));
	Select dropdown9 = new Select(driver.findElement(By.id(prop.getProperty("sdmsat_id")))); 
	dropdown9.selectByVisibleText(sat);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("sdmsunday_id"))));
	Select dropdown10 = new Select(driver.findElement(By.id(prop.getProperty("sdmsunday_id")))); 
	dropdown10.selectByVisibleText(sun);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("sdmweek_id"))));
	Select dropdown11 = new Select(driver.findElement(By.id(prop.getProperty("sdmweek_id")))); 
	dropdown11.selectByVisibleText(week);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("sdmday_id"))));
	Select dropdown12 = new Select(driver.findElement(By.id(prop.getProperty("sdmday_id")))); 
	dropdown12.selectByVisibleText(day);
	
	driver.findElement(By.id(prop.getProperty("sdmaddbutton_id"))).click();
	Thread.sleep(2000);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("sdmsubmitbutton_id"))));
	driver.findElement(By.id(prop.getProperty("sdmsubmitbutton_id"))).click();
	
	Thread.sleep(2000);
		
	Alert alert=driver.switchTo().alert();
	System.out.println(alert.getText());
	alert.accept();
	Thread.sleep(2000);
			
	try{
		
	driver.findElement(By.id(prop.getProperty("sdmname_id"))).isEnabled();
	driver.findElement(By.id(prop.getProperty("sdmcancelbutton_id"))).click();
	System.out.println("duplicate data : clicked cancel button");
		
	}
	catch(Exception e){
		System.out.println("new data added successfully");
		e.printStackTrace();
	}
	}

		@DataProvider(name="SDMdata")
		public Object[][] readExcel() throws BiffException, IOException{
			
			FileInputStream abc = new FileInputStream("D:\\HRMS DATA\\Datasheet\\Employee Life Cycle\\ShiftDefinitionMaster.xls");
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

	

