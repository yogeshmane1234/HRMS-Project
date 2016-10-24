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

public class QuestionMaster {

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
	String data1[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\QuestionMasterData.xls", "Sheet2", 0, 11);
	String data2[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\QMData.xls", "Sheet1", 0, 11);
	WebDriverWait wait = new WebDriverWait(driver, 180);

	
	
	// ****************Login to Application****************
	@BeforeTest
	public void Login() throws BiffException, IOException, InterruptedException, Exception{
			
		baseclass.login(data[1][0], data[1][1], driver);
	}
	
	//-----------> test search button functionality ------------------->
	
	@Test(priority=0)
	public void TestSearchButton() throws Exception{
		baseclass.CommomSection("HR_id", "HRsetup_linktext", "General_linktext", "QuestionMastert_linktext", driver);
		
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("QMDropDownBox"))));
	Select Searchdropdown = new Select(driver.findElement(By.id(prop.getProperty("QMDropDownBox")))); 
	Searchdropdown.selectByVisibleText(data1[1][0]);
	driver.findElement(By.id(prop.getProperty("QMSearchTextBox_id"))).sendKeys(data1[1][1]);	
  
	driver.findElement(By.id(prop.getProperty("SearchButton"))).click();
   // Assert.assertEquals(driver.getTitle(), "Question Master");
    boolean search = driver.getPageSource().contains(data1[1][1]);
    
    if(search)
    {
    System.out.println("search value is Present");
    }
    else
    {
    System.out.println("search value is not Present");
    }	
    
	
	try{
		
		driver.findElement(By.id(prop.getProperty("SearchButton"))).isSelected();
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
				 FileUtils.copyFile(scrFile, new File("D:\\abcd123.png"));
		}
		
	//--------------------> clear search button functionality ------------------> 

		@Test(priority=1)
		public void TestClearSearchButton() throws Exception{
			baseclass.CommomSection("HR_id", "HRsetup_linktext", "General_linktext", "QuestionMastert_linktext", driver);
			
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("QMDropDownBox"))));
		Select Searchdropdown = new Select(driver.findElement(By.id(prop.getProperty("QMDropDownBox")))); 
		Searchdropdown.selectByVisibleText(data1[1][0]);
		driver.findElement(By.id(prop.getProperty("QMSearchTextBox_id"))).sendKeys(data1[1][1]);	
	  
		driver.findElement(By.id(prop.getProperty("ClearSearchButton_id"))).click();
	   // Assert.assertEquals(driver.getTitle(), "Question Master");
		WebElement TxtBoxContent = driver.findElement(By.id(prop.getProperty("QMSearchTextBox_id")));
		TxtBoxContent.getText();
	      
		try{
			
			Assert.assertEquals(TxtBoxContent.getText(), "");
			System.out.println("Functionality of clear button is working properly");
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
				baseclass.CommomSection("HR_id", "HRsetup_linktext", "General_linktext", "QuestionMastert_linktext", driver);
				Thread.sleep(2000);
				
				wait.until(ExpectedConditions.elementToBeClickable(By.name(prop.getProperty("EditButton_name"))));
				driver.findElement(By.name(prop.getProperty("EditButton_name"))).click();
				driver.findElement(By.id(prop.getProperty("Question_id"))).clear();
				driver.findElement(By.id(prop.getProperty("Question_id"))).sendKeys(data2[0][2]);
				driver.findElement(By.id(prop.getProperty("savebutton_id"))).click();
				
				Alert alert=driver.switchTo().alert();
				System.out.println(alert.getText());
				alert.accept();
			}
	//------------------> delete functionality ----------------->		
			@Test(priority=3)
			public void Delete() throws Exception{
				baseclass.CommomSection("HR_id", "HRsetup_linktext", "General_linktext", "QuestionMastert_linktext", driver);
				Thread.sleep(2000);
				
				wait.until(ExpectedConditions.elementToBeClickable(By.name(prop.getProperty("DeleteButton_name"))));
				driver.findElement(By.name(prop.getProperty("DeleteButton_name"))).click();
				Alert alert=driver.switchTo().alert();
				System.out.println(alert.getText());
				alert.accept();
				Alert alert1=driver.switchTo().alert();
				System.out.println(alert1.getText());
				alert1.accept();
				
			}
		
	@Test(dataProvider = "QMobjdata",priority=4)
	public void objectivenewdata(String Questioncode, String selectsubject, String Question, String QuestionType,
			String NoOfOption, String Mark,String ExpectedAnswer, String Option1, String Option2) throws Exception{
		//System.setProperty("webdriver.chrome.driver",""));
	baseclass.CommomMenu("HR_id", "HRsetup_linktext", "General_linktext", "QuestionMastert_linktext","addnewbutton_id", driver);
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("Questioncode_id"))));
	driver.findElement(By.id(prop.getProperty("Questioncode_id"))).sendKeys(Questioncode);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("selectsubject_id"))));
	Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("selectsubject_id")))); 
	dropdown.selectByVisibleText(selectsubject);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("Question_id"))));
	driver.findElement(By.id(prop.getProperty("Question_id"))).sendKeys(Question);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("QuestionType_id"))));
	Select dropdown1 = new Select(driver.findElement(By.id(prop.getProperty("QuestionType_id")))); 
	dropdown1.selectByVisibleText(QuestionType);
	driver.findElement(By.className(prop.getProperty("PageHeading"))).click();
	Thread.sleep(2000);
	
	//----------Objective data----------->
		driver.findElement(By.id(prop.getProperty("NoOfOption_id"))).sendKeys(NoOfOption);
		driver.findElement(By.className(prop.getProperty("PageHeading"))).click();
		
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("Mark_id"))));
		driver.findElement(By.id(prop.getProperty("Mark_id"))).sendKeys(Mark);

		driver.findElement(By.className(prop.getProperty("PageHeading"))).click();
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("Option1_id"))));
		driver.findElement(By.id(prop.getProperty("Option1_id"))).sendKeys(Option1);
		
		driver.findElement(By.className(prop.getProperty("PageHeading"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("Option2_id"))));
		driver.findElement(By.id(prop.getProperty("Option2_id"))).sendKeys(Option2);
		driver.findElement(By.className(prop.getProperty("PageHeading"))).click();
		
		System.out.println("all Objective data Accepted");
	
	driver.findElement(By.id(prop.getProperty("savebutton_id"))).click();
	Thread.sleep(2000);
	//Assert.assertEquals(driver.getTitle(),"Question Master");
	
	Alert alert=driver.switchTo().alert();
	System.out.println(alert.getText());
	alert.accept();
			}

		@DataProvider(name="QMobjdata")
		public Object[][] readExcel() throws BiffException, IOException{
			
			FileInputStream abc = new FileInputStream("D:\\HRMS DATA\\Datasheet\\QMData.xls");
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
		
		@Test(dataProvider = "QMsubdata",priority=5)
		public void Subjectivenewdata(String Questioncode, String selectsubject, String Question, String QuestionType,
				String NoOfOption, String Mark,String ExpectedAnswer) throws Exception{
			//System.setProperty("webdriver.chrome.driver",""));
		baseclass.CommomMenu("HR_id", "HRsetup_linktext", "General_linktext", "QuestionMastert_linktext","addnewbutton_id", driver);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("Questioncode_id"))));
		driver.findElement(By.id(prop.getProperty("Questioncode_id"))).sendKeys(Questioncode);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("selectsubject_id"))));
		Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("selectsubject_id")))); 
		dropdown.selectByVisibleText(selectsubject);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("Question_id"))));
		driver.findElement(By.id(prop.getProperty("Question_id"))).sendKeys(Question);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("QuestionType_id"))));
		Select dropdown1 = new Select(driver.findElement(By.id(prop.getProperty("QuestionType_id")))); 
		dropdown1.selectByVisibleText(QuestionType);
		driver.findElement(By.className(prop.getProperty("PageHeading"))).click();
		Thread.sleep(2000);
		
		// ---------------> subjective data-------------->
				
				wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("Mark_id"))));
				driver.findElement(By.id(prop.getProperty("Mark_id"))).sendKeys(Mark);

				driver.findElement(By.className(prop.getProperty("PageHeading"))).click();
				Thread.sleep(2000);
				
				wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("ExpectedAnswer_id"))));
				driver.findElement(By.id(prop.getProperty("ExpectedAnswer_id"))).sendKeys(ExpectedAnswer);
				driver.findElement(By.className(prop.getProperty("PageHeading"))).click();
				
				System.out.println("all New subjective data accepted");
			
		driver.findElement(By.id(prop.getProperty("savebutton_id"))).click();
		Thread.sleep(2000);
		//Assert.assertEquals(driver.getTitle(),"Question Master");
		
		Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		
	//	driver.findElement(By.id(prop.getProperty("BackButton_id"))).click();
		//Thread.sleep(2000);
		
	//	Assert.assertEquals(driver.getTitle(), "Question Master");
				}

			@DataProvider(name="QMsubdata")
			public Object[][] readExcel1() throws BiffException, IOException{
				
				FileInputStream abc = new FileInputStream("D:\\HRMS DATA\\Datasheet\\QMData.xls");
				//File f = new File("D:\\Login.xlsx");
				Workbook wb = Workbook.getWorkbook(abc);
				Sheet s = wb.getSheet("Sheet2");
				
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
