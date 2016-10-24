package HRSetup.Recruitment.Yogesh;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;


import Excel.Excel;
//import HRMS_Recruitment.Login1;
import baseClass.BaseClass;

public class CampusMaster {

	//public String sheet1;
	public FileInputStream inputStream=null;
	BaseClass baseclass = new BaseClass();
	Excel dataXLS = new Excel();
	Properties prop = baseclass.PropertiesConfigurations();
	// Logger logger=baseclass.LogCongigurations();

	WebDriver driver = baseclass.DriverConfigurations();
	Workbook workbook = baseclass.DatasheetConfigurations("D:\\HRMS DATA\\Datasheet\\QuestionMasterData.xls");
	//String data[][] = dataXLS.CellData("Sheet1",0,8);
	String data[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\QuestionMasterData.xls", "Sheet1", 0, 11);
	String data1[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\CampusMaster.xls", "Sheet3", 0, 11);
	//String data2[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\BasicSalaryRangeData.xls", "Sheet1", 0, 11);
	
	WebDriverWait wait = new WebDriverWait(driver, 180);

		// ****************Login to Application****************
	@BeforeTest
	public void Login() throws BiffException, IOException, InterruptedException, Exception{
			
		baseclass.login(data[1][0], data[1][1], driver);
	}
	
	@Test(priority=0)
	public void TestSearchButton() throws Exception{
	baseclass.CommomSection1("HR_id", "HRsetup_linktext", "Recruitment_firepath", "campusmaster_linktext", driver);
		
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("campdropdown_id"))));
	Select Searchdropdown = new Select(driver.findElement(By.id(prop.getProperty("campdropdown_id")))); 
	Searchdropdown.selectByVisibleText(data1[3][0]);
	driver.findElement(By.id(prop.getProperty("campsearchbox_id"))).sendKeys(data1[3][1]);	
  
	driver.findElement(By.id(prop.getProperty("campsearchbutton_id"))).click();
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
		
		driver.findElement(By.id(prop.getProperty("campsearchbutton_id"))).isSelected();
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
		baseclass.CommomSection1("HR_id", "HRsetup_linktext", "Recruitment_firepath", "campusmaster_linktext", driver);
			
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("campdropdown_id"))));
		Select Searchdropdown = new Select(driver.findElement(By.id(prop.getProperty("campdropdown_id")))); 
		Searchdropdown.selectByVisibleText(data1[3][0]);
		driver.findElement(By.id(prop.getProperty("campsearchbox_id"))).sendKeys(data1[3][1]);
		
		driver.findElement(By.id(prop.getProperty("campsearchbutton_id"))).click();
		Thread.sleep(1000);
		driver.findElement(By.id(prop.getProperty("campclearsearchbutton_id"))).click();
	   // Assert.assertEquals(driver.getTitle(), "Question Master");
		WebElement TxtBoxContent = driver.findElement(By.id(prop.getProperty("campsearchbox_id")));
		TxtBoxContent.getText();
	      
		try{
			
			Assert.assertEquals(TxtBoxContent.getText(), "");
			System.out.println("Functionality of clear button is working properly");
		}
		
		//----------------take a screen shot ------------------------->
		catch(Exception e){
			
			System.out.println("I'm in exception : Clear button is  working");
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
			baseclass.CommomSection1("HR_id", "HRsetup_linktext", "Recruitment_firepath", "campusmaster_linktext", driver);
				driver.findElement(By.id(prop.getProperty("campeditbutton_id"))).click();
				
				Thread.sleep(2000);
				driver.findElement(By.id(prop.getProperty("campbatch_id"))).clear();
				driver.findElement(By.id(prop.getProperty("campbatch_id"))).sendKeys(data1[3][2]);
				driver.findElement(By.id(prop.getProperty("campokbutton_id"))).click();
				
				Alert alert=driver.switchTo().alert();
				System.out.println(alert.getText());
				alert.accept();
			}
		
			@Test(priority=3)
			public void Delete() throws Exception{
			baseclass.CommomSection1("HR_id", "HRsetup_linktext", "Recruitment_firepath", "campusmaster_linktext", driver);
				driver.findElement(By.id(prop.getProperty("campdeletebutton_id"))).click();
				
				Alert alert=driver.switchTo().alert();
				System.out.println(alert.getText());
				alert.accept();
			
				System.out.print("functionality of delete button is working properly");
			}
	
	@Test(dataProvider = "CampusMdata")
	public void TestCampusM(String code, String name, String batch, String fromtime, String totime) throws Exception{
		//System.setProperty("webdriver.chrome.driver",""));
	baseclass.CommomSection1("HR_id", "HRsetup_linktext", "Recruitment_firepath", "campusmaster_linktext", driver);
	
	driver.findElement(By.id(prop.getProperty("campaddnewbutton_id"))).click();
	Thread.sleep(1000);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("campcampuscode_id"))));
	driver.findElement(By.id(prop.getProperty("campcampuscode_id"))).sendKeys(code);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("campname_id"))));
	driver.findElement(By.id(prop.getProperty("campname_id"))).sendKeys(name);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("campdate_xpath"))));
	WebElement element =driver.findElement(By.xpath(prop.getProperty("campdate_xpath")));
	Thread.sleep(2000);
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("arguments[0].setAttribute('value','16-Sep-2016')",element);
			
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("campbatch_id"))));
	driver.findElement(By.id(prop.getProperty("campbatch_id"))).sendKeys(batch);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("camptimefrom_id"))));
	driver.findElement(By.id(prop.getProperty("camptimefrom_id"))).clear();
	driver.findElement(By.id(prop.getProperty("camptimefrom_id"))).sendKeys(fromtime);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("camptimeto_id"))));
	driver.findElement(By.id(prop.getProperty("camptimeto_id"))).clear();
	driver.findElement(By.id(prop.getProperty("camptimeto_id"))).sendKeys(totime);
	
	driver.findElement(By.id(prop.getProperty("campokbutton_id"))).click();
	Thread.sleep(4000);
	System.out.println(" data Accepted successfully");
		
	Alert alert=driver.switchTo().alert();
	System.out.println(alert.getText());
	alert.accept();
	Thread.sleep(2000);
		
	//----> if added new data from excel then comment below if else condition --->
	if(driver.findElement(By.id(prop.getProperty("camptimeto_id"))).isEnabled()){
		
		driver.findElement(By.id(prop.getProperty("campcancelbutton_id"))).click();
		
	}
	else if(driver.getTitle().equals("cancel")){
		System.out.println("new data added successfully");
	}
	}

		@DataProvider(name="CampusMdata")
		public Object[][] readExcel() throws BiffException, IOException{
			
			FileInputStream abc = new FileInputStream("D:\\HRMS DATA\\Datasheet\\CampusMaster.xls");
			//File f = new File("D:\\Login.xlsx");
			Workbook wb = Workbook.getWorkbook(abc);
			Sheet s = wb.getSheet("Sheet1");
			
			int rows = s.getRows();
			int columns = s.getColumns();
			//System.out.println(rows);
			//System.out.println(columns);
			
			String inputData[][] = new String[rows][columns];
			for (int i=0; i<rows;i++){
				for(int j=0; j<columns;j++){
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

	
