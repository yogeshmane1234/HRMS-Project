package HRSetup.General.Yogesh;


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

public class TestSetup {

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
	String data1[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\TestSetup.xls", "Sheet1", 0, 11);
	String data2[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\TestSetup.xls", "Sheet3", 0, 11);
	
	WebDriverWait wait = new WebDriverWait(driver, 180);

		// ****************Login to Application****************
	@BeforeTest
	public void Login() throws BiffException, IOException, InterruptedException, Exception{
			
		baseclass.login(data[1][0], data[1][1], driver);
	}
	
	@Test(priority=0)
	public void TestSearchButton() throws Exception{
	baseclass.CommomSection("HR_id", "HRsetup_linktext", "General_linktext", "TestSetup_linktext", driver);
	Thread.sleep(1000);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("TSDropdown_id"))));
	Select Searchdropdown = new Select(driver.findElement(By.id(prop.getProperty("TSDropdown_id")))); 
	Searchdropdown.selectByVisibleText(data2[0][0]);
	driver.findElement(By.id(prop.getProperty("TSSearchTextbox_id"))).sendKeys(data2[0][1]);	
  
	driver.findElement(By.id(prop.getProperty("TSSearchButton_id"))).click();
   // Assert.assertEquals(driver.getTitle(), "Question Master");
    boolean search = driver.getPageSource().contains(data2[0][1]);
    
    if(search)
    {
    System.out.println("search value is Present");
    }
    else
    {
    System.out.println("search value is not Present");
    }	
    
	
	try{
		
		driver.findElement(By.id(prop.getProperty("TSSearchButton_id"))).isSelected();
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
				 FileUtils.copyFile(scrFile, new File("D:\\zzz.png"));
		}
		
	//--------------------> clear search button functionality ------------------> 

	@Test(priority=1)
		public void TestClearSearchButton() throws Exception{
			baseclass.CommomSection("HR_id", "HRsetup_linktext", "General_linktext", "TestSetup_linktext", driver);
			
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("TSDropdown_id"))));
		Select Searchdropdown = new Select(driver.findElement(By.id(prop.getProperty("TSDropdown_id")))); 
		Searchdropdown.selectByVisibleText(data2[0][0]);
		driver.findElement(By.id(prop.getProperty("TSSearchTextbox_id"))).sendKeys(data2[0][1]);	
		driver.findElement(By.id(prop.getProperty("TSSearchButton_id"))).click();
		Thread.sleep(2000);
		driver.findElement(By.id(prop.getProperty("TSClearButton_id"))).click();
	   // Assert.assertEquals(driver.getTitle(), "Question Master");
		WebElement TxtBoxContent = driver.findElement(By.id(prop.getProperty("TSSearchTextbox_id")));
		TxtBoxContent.getText();
	      
		try{
			
			Assert.assertEquals(TxtBoxContent.getText(),"");
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
				baseclass.CommomSection("HR_id", "HRsetup_linktext", "General_linktext", "TestSetup_linktext", driver);
				driver.findElement(By.linkText(prop.getProperty("EditRecord_linkText"))).click();
				
				Thread.sleep(2000);
				driver.findElement(By.id(prop.getProperty("TestName_id"))).clear();
				driver.findElement(By.id(prop.getProperty("TestName_id"))).sendKeys(data2[8][2]);
				driver.findElement(By.id(prop.getProperty("TSSaveButton_id"))).click();
				
				
				Alert alert=driver.switchTo().alert();
				System.out.println(alert.getText());
				alert.accept();
			}
					
			
	
	@Test(priority=4)
	public void SelectDeselectAllButton() throws Exception{
		baseclass.CommomMenu("HR_id", "HRsetup_linktext", "General_linktext", "TestSetup_linktext","TSAddNewButton_id", driver);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("TestCode_id"))));
		driver.findElement(By.id(prop.getProperty("TestCode_id"))).sendKeys(data1[0][0]);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("TestName_id"))));
		driver.findElement(By.id(prop.getProperty("TestName_id"))).sendKeys(data1[0][1]);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("TSSelectSubject_id"))));
		Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("TSSelectSubject_id")))); 
		dropdown.selectByVisibleText(data1[0][2]);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("TSQuestionType_id"))));
		Select dropdown1 = new Select(driver.findElement(By.id(prop.getProperty("TSQuestionType_id")))); 
		dropdown1.selectByVisibleText(data1[0][3]);
		Thread.sleep(2000);
		
		driver.findElement(By.id(prop.getProperty("TSSelectAllcheckbox_id"))).click();
		Thread.sleep(3000);
		if(driver.findElement(By.id(prop.getProperty("TSCheckbox_id"))).isSelected()){
			
			System.out.println("all checkbox is selected : selectall button is working properly");
		}
		else{
			System.out.println(" selectall button is not working properly");
		}
		
		driver.findElement(By.id(prop.getProperty("TSDeselectAllcheckbox_id"))).click();
		Thread.sleep(3000);
		if(driver.findElement(By.id(prop.getProperty("TSCheckbox_id"))).isEnabled()){
			
			System.out.println("all checkbox is Deselected : Deselectall button is working properly");
		}
		else{
			System.out.println("Deselectall button is not working properly");
		}
		
	}
	
	
	@Test(dataProvider = "TSdata")
	public void adminLogin(String testcode, String testname, String subject, String questiontype) throws Exception{
		//System.setProperty("webdriver.chrome.driver",""));
	baseclass.CommomMenu("HR_id", "HRsetup_linktext", "General_linktext", "TestSetup_linktext","TSAddNewButton_id", driver);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("TestCode_id"))));
	driver.findElement(By.id(prop.getProperty("TestCode_id"))).sendKeys(testcode);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("TestName_id"))));
	driver.findElement(By.id(prop.getProperty("TestName_id"))).sendKeys(testname);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("TSSelectSubject_id"))));
	Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("TSSelectSubject_id")))); 
	dropdown.selectByVisibleText(subject);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("TSQuestionType_id"))));
	Select dropdown1 = new Select(driver.findElement(By.id(prop.getProperty("TSQuestionType_id")))); 
	dropdown1.selectByVisibleText(questiontype);
	Thread.sleep(2000);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("TSCheckbox_id"))));
	driver.findElement(By.id(prop.getProperty("TSCheckbox_id"))).click();
	
	if(driver.findElement(By.id(prop.getProperty("TSCheckbox_id"))).isSelected())
	{
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("TSSaveButton_id"))));
		driver.findElement(By.id(prop.getProperty("TSSaveButton_id"))).click();
		
		System.out.println("check box is selected ");
	}
	else{
		System.out.println("check box is not selected ");
	}
	
	//wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("TSSaveButton_id"))));
	//driver.findElement(By.id(prop.getProperty("TSSaveButton_id"))).click();
		
	Alert alert=driver.switchTo().alert();
	System.out.println(alert.getText());
	alert.accept();
	
	
			}

		@DataProvider(name="TSdata")
		public Object[][] readExcel() throws BiffException, IOException{
			
			FileInputStream abc = new FileInputStream("D:\\HRMS DATA\\Datasheet\\TestSetup.xls");
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
					System.out.println(inputData[i][j]);
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

	