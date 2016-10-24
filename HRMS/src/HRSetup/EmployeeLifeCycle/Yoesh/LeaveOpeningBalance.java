package HRSetup.EmployeeLifeCycle.Yoesh;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import jxl.Cell;
//import jxl.Sheet;
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
import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;



import Excel.Excel;
//import HRMS_Recruitment.Login1;
import baseClass.BaseClass;

public class LeaveOpeningBalance {

	//public String sheet1;
	public FileInputStream inputStream=null;
	BaseClass baseclass = new BaseClass();
	Excel dataXLS = new Excel();
	Properties prop = baseclass.PropertiesConfigurations();
	// Logger logger=baseclass.LogCongigurations();

	WebDriver driver = baseclass.DriverConfigurations();
	Workbook workbook = baseclass.DatasheetConfigurations("D:\\HRMS DATA\\Datasheet\\QuestionMasterData.xls");
	//String data[][] = dataXLS.CellData("Sheet1",0,8);
	String data[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\Login.xls", "Sheet1", 0, 11);
	String data1[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\Employee Life Cycle\\LeaveOpeningBalance.xls", "Sheet1", 0, 11);
	//String data2[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\BasicSalaryRangeData.xls", "Sheet1", 0, 11);
	
	WebDriverWait wait = new WebDriverWait(driver, 180);

		// ****************Login to Application****************
	@BeforeTest
	public void Login() throws BiffException, IOException, InterruptedException, Exception{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		baseclass.login(data[1][0], data[1][1], driver);
	}
	
	@Test(priority=0)
	public void TestSearchButton() throws Exception{
	baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "LeaveOpeningBalance_linktext", driver);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("LOBdropdown_id"))));
	Select Searchdropdown = new Select(driver.findElement(By.id(prop.getProperty("LOBdropdown_id")))); 
	Searchdropdown.selectByVisibleText(data1[3][0]);
	driver.findElement(By.id(prop.getProperty("LOBSearchBox_id"))).sendKeys(data1[3][1]);	
  
	driver.findElement(By.id(prop.getProperty("LOBsearchButton_id"))).click();
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
		
		driver.findElement(By.id(prop.getProperty("LOBsearchButton_id"))).isSelected();
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
		baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "LeaveOpeningBalance_linktext", driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("LOBdropdown_id"))));
		Select Searchdropdown = new Select(driver.findElement(By.id(prop.getProperty("LOBdropdown_id")))); 
		Searchdropdown.selectByVisibleText(data1[3][0]);
		driver.findElement(By.id(prop.getProperty("LOBSearchBox_id"))).sendKeys(data1[3][1]);
		
		driver.findElement(By.id(prop.getProperty("LOBsearchButton_id"))).click();
		Thread.sleep(1000);
		driver.findElement(By.id(prop.getProperty("LOBClearButton_id"))).click();
	   // Assert.assertEquals(driver.getTitle(), "Question Master");
		WebElement TxtBoxContent = driver.findElement(By.id(prop.getProperty("LOBSearchBox_id")));
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
			baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "LeaveOpeningBalance_linktext", driver);
				driver.findElement(By.linkText(prop.getProperty("LOBedit_linkText"))).click();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Thread.sleep(2000);
				
				wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("LOBleavetype_id"))));
				Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("LOBleavetype_id")))); 
				dropdown.selectByVisibleText(data1[3][2]);
				
				driver.findElement(By.id(prop.getProperty("LOBleaveopeningbalance_id"))).clear();
				driver.findElement(By.id(prop.getProperty("LOBleaveopeningbalance_id"))).sendKeys(data1[3][3]);
				driver.findElement(By.id(prop.getProperty("LOBsaveButton_id"))).click();
				
				Alert alert=driver.switchTo().alert();
				System.out.println(alert.getText());
				alert.accept();
			}
	
	
/*	@Test(dataProvider = "LTMdata")
	public void TestLeaveTypeMaster(String leavecode, String leavetypename, String leaveshortname, 
		String payable,String specialType, String applicablefor, String Remark) throws Exception{
		//System.setProperty("webdriver.chrome.driver",""));
	baseclass.CommomSection1("HR_id", "HRsetup_linktext", "EmployeeLifeCycle_xpath", "LTMLeaveTypeMaster_linktext", driver);
	
	driver.findElement(By.id(prop.getProperty("LTMaddnewbutton_id"))).click();
	Thread.sleep(1000);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("LTMleavecode_id"))));
	driver.findElement(By.id(prop.getProperty("LTMleavecode_id"))).sendKeys(leavecode);
	
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("LTMleavetypename_id"))));
	driver.findElement(By.id(prop.getProperty("LTMleavetypename_id"))).click();
	Thread.sleep(2000);
	driver.findElement(By.id(prop.getProperty("LTMleavetypename_id"))).sendKeys(leavetypename);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("LTMleaveshortname_id"))));
	driver.findElement(By.id(prop.getProperty("LTMleaveshortname_id"))).sendKeys(leaveshortname);
				
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("LTMpayable_id"))));
	driver.findElement(By.id(prop.getProperty("LTMpayable_id"))).sendKeys(payable);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("LTMspeacialtype_id"))));
	driver.findElement(By.id(prop.getProperty("LTMspeacialtype_id"))).sendKeys(specialType);
	
	Thread.sleep(2000);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("LTMactivecheckbox_id"))));
	driver.findElement(By.id(prop.getProperty("LTMactivecheckbox_id"))).click();
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("LTMapplicablefor_id"))));
	driver.findElement(By.id(prop.getProperty("LTMapplicablefor_id"))).sendKeys(applicablefor);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("LTMremark_id"))));
	driver.findElement(By.id(prop.getProperty("LTMremark_id"))).sendKeys(Remark);
	
	driver.findElement(By.id(prop.getProperty("LTMokbutton_id"))).click();
	Thread.sleep(2000);
	System.out.println(" data Accepted successfully");
		
	Alert alert=driver.switchTo().alert();
	System.out.println(alert.getText());
	alert.accept();
	Thread.sleep(2000);
		
	//----> if added new data from excel then comment below if else condition --->
	if(driver.findElement(By.id(prop.getProperty("LTMactivecheckbox_id"))).isSelected()){
		
		driver.findElement(By.id(prop.getProperty("LTMcancelbutton_id"))).click();
		
	}
	else {
		System.out.println("new data added successfully");
	}
	}

		@DataProvider(name="LTMdata")
		public Object[][] readExcel() throws BiffException, IOException{
			
			FileInputStream abc = new FileInputStream("D:\\HRMS DATA\\Datasheet\\LeaveTypeMaster.xls");
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
		

		}*/
	
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

	


