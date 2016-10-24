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

public class NoticePeriodMaster {

	//public String sheet1;
	public FileInputStream inputStream=null;
	BaseClass baseclass = new BaseClass();
	BaseClass b = new BaseClass();
	
	Excel dataXLS = new Excel();
	Properties prop = baseclass.PropertiesConfigurations();
	// Logger logger=baseclass.LogCongigurations();

	WebDriver driver = baseclass.DriverConfigurations();
	Workbook workbook = baseclass.DatasheetConfigurations("D:\\HRMS DATA\\Datasheet\\QuestionMasterData.xls");
	//String data[][] = dataXLS.CellData("Sheet1",0,8);
	String data[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\QuestionMasterData.xls", "Sheet1", 0, 11);
	String data1[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\NoticePeriodMaster.xls", "Sheet3", 0, 11);
	//String data2[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\BasicSalaryRangeData.xls", "Sheet3", 0, 11);
	
	WebDriverWait wait = new WebDriverWait(driver, 180);

		// ****************Login to Application****************
	@BeforeTest
	public void Login() throws BiffException, IOException, InterruptedException, Exception{
			
		baseclass.login(data[1][0], data[1][1], driver);
	}
	
	@Test(priority=0)
	public void SearchButton() throws Exception{
	baseclass.CommomSection("HR_id", "HRsetup_linktext", "General_linktext", "NoticePeriodMaster_linktext", driver);		
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("NPdropDown_id"))));
	Select Searchdropdown = new Select(driver.findElement(By.id(prop.getProperty("NPdropDown_id")))); 
	Searchdropdown.selectByVisibleText(data1[2][0]);
	driver.findElement(By.id(prop.getProperty("NPSearchTextBox_id"))).sendKeys(data1[2][1]);	
  
	driver.findElement(By.id(prop.getProperty("NPSearchButton_id"))).click();
   // Assert.assertEquals(driver.getTitle(), "Question Master");
    boolean search = driver.getPageSource().contains(data1[2][1]);
    
    if(search)
    {
    System.out.println("search value is Present");
    }
    else
    {
    System.out.println("search value is not Present");
    }	
    
	
	try{
		
		driver.findElement(By.id(prop.getProperty("NPSearchButton_id"))).isSelected();
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
		public void ClearSearchButton() throws Exception{
			baseclass.CommomSection("HR_id", "HRsetup_linktext", "General_linktext", "NoticePeriodMaster_linktext", driver);
			
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("NPdropDown_id"))));
		Select Searchdropdown = new Select(driver.findElement(By.id(prop.getProperty("NPdropDown_id")))); 
		Searchdropdown.selectByVisibleText(data1[2][0]);
		
		driver.findElement(By.id(prop.getProperty("NPSearchTextBox_id"))).sendKeys(data1[2][1]);	
	  
		driver.findElement(By.id(prop.getProperty("NPClearSearchButton_id"))).click();
	   // Assert.assertEquals(driver.getTitle(), "Question Master");
		WebElement TxtBoxContent = driver.findElement(By.id(prop.getProperty("NPSearchTextBox_id")));
		TxtBoxContent.getText();
	      
		try{
			
			Assert.assertEquals(TxtBoxContent.getText(), "");
			System.out.println("Functionality of clear button is working properly");
		}
		
		//----------------take a screen shot ------------------------->
		catch(Exception e){
			
			System.out.println("I'm in exception : Clear button is  working");
			getscreenshot2(); 
				}
		}
			public void getscreenshot2() throws Exception 
			{
					 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					 FileUtils.copyFile(scrFile, new File("D:\\clearbutton.png"));
			}
	//----------------------> edit functionality ------------------->	 	  
			@Test(priority=2)
			public void Edit() throws Exception{
				baseclass.CommomSection("HR_id", "HRsetup_linktext", "General_linktext", "NoticePeriodMaster_linktext", driver);
				driver.findElement(By.id(prop.getProperty("EditButton_id"))).click();
				
				Thread.sleep(2000);
				driver.findElement(By.id(prop.getProperty("NoOfDays_id"))).clear();
				driver.findElement(By.id(prop.getProperty("NoOfDays_id"))).sendKeys(data1[2][2]);
				driver.findElement(By.id(prop.getProperty("OkButton_id"))).click();
				
				Alert alert=driver.switchTo().alert();
				System.out.println(alert.getText());
				alert.accept();
							
	}
	
	//------------------> delete functionality ----------------->		
			@Test(priority=3)
			public void Delete() throws Exception{
				baseclass.CommomSection("HR_id", "HRsetup_linktext", "General_linktext", "NoticePeriodMaster_linktext", driver);
				driver.findElement(By.id(prop.getProperty("DeleteButton_id"))).click();
				try{
					Assert.assertEquals(driver.getTitle(), "Notice Period Master");
					System.out.println("delete button functionality is working properly");
				}
				catch(Exception e){
					
					System.out.println("Delete button functionality is working properly");
					getscreenshot1(); 
						}
				}
	public void getscreenshot1() throws Exception 
	{
			 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			 FileUtils.copyFile(scrFile, new File("D:\\Deletebutton.png"));
			}
	
	
	
	@Test(dataProvider = "NPdata")
	public void adminLogin(String module, String grade, String days) throws Exception{
		//System.setProperty("webdriver.chrome.driver",""));
	baseclass.CommomMenu("HR_id", "HRsetup_linktext", "General_linktext", "NoticePeriodMaster_linktext","NPAddNew_id", driver);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("NPModule_id"))));
	Select dropdown = new Select(driver.findElement(By.id(prop.getProperty("NPModule_id")))); 
	dropdown.selectByVisibleText(module);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("NPGrade_id"))));
	Select dropdown1 = new Select(driver.findElement(By.id(prop.getProperty("NPGrade_id")))); 
	dropdown1.selectByVisibleText(grade);
	//Thread.sleep(2000);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("NoOfDays_id"))));
	driver.findElement(By.id(prop.getProperty("NoOfDays_id"))).sendKeys(days);
	
	driver.findElement(By.id(prop.getProperty("OkButton_id"))).click();;
	
	System.out.println("data submited successfully");
		
	Alert alert=driver.switchTo().alert();
	System.out.println(alert.getText());
	alert.accept();
	
				}

		@DataProvider(name="NPdata")
		public Object[][] readExcel() throws BiffException, IOException{
			
			FileInputStream abc = new FileInputStream("D:\\HRMS DATA\\Datasheet\\NoticePeriodMaster.xls");
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

	