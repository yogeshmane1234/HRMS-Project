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

public class QualificationMaster {

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
	String data1[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\QualificationMaster.xls", "Sheet3", 0, 11);
	//String data2[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\BasicSalaryRangeData.xls", "Sheet1", 0, 11);
	
	WebDriverWait wait = new WebDriverWait(driver, 180);

		// ****************Login to Application****************
	@BeforeTest
	public void Login() throws BiffException, IOException, InterruptedException, Exception{
			
		baseclass.login(data[1][0], data[1][1], driver);
	}
	
	@Test(priority=0)
	public void TestSearchButton() throws Exception{
		baseclass.CommomSection1("HR_id", "HRsetup_linktext", "Recruitment_firepath", "QM_testlink", driver);
		
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("QMDropdown_id"))));
	Select Searchdropdown = new Select(driver.findElement(By.id(prop.getProperty("QMDropdown_id")))); 
	Searchdropdown.selectByVisibleText(data1[2][0]);
	driver.findElement(By.id(prop.getProperty("QMsearchbox_id"))).sendKeys(data1[2][1]);	
  
	driver.findElement(By.id(prop.getProperty("QMSearchButton_id"))).click();
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
		
		driver.findElement(By.id(prop.getProperty("QMSearchButton_id"))).isSelected();
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
			baseclass.CommomSection1("HR_id", "HRsetup_linktext", "Recruitment_firepath", "QM_testlink", driver);
			
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("QMDropdown_id"))));
		Select Searchdropdown = new Select(driver.findElement(By.id(prop.getProperty("QMDropdown_id")))); 
		Searchdropdown.selectByVisibleText(data1[2][0]);
		driver.findElement(By.id(prop.getProperty("QMsearchbox_id"))).sendKeys(data1[2][1]);
		
		driver.findElement(By.id(prop.getProperty("QMSearchButton_id"))).click();
		Thread.sleep(1000);
		driver.findElement(By.id(prop.getProperty("QMClearSearchButton_id"))).click();
	   // Assert.assertEquals(driver.getTitle(), "Question Master");
		WebElement TxtBoxContent = driver.findElement(By.id(prop.getProperty("QMsearchbox_id")));
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
				baseclass.CommomSection1("HR_id", "HRsetup_linktext", "Recruitment_firepath", "QM_testlink", driver);
				driver.findElement(By.id(prop.getProperty("QMEditButton_id"))).click();
				
				Thread.sleep(2000);
				//driver.findElement(By.id(prop.getProperty("Minimum_id"))).clear();
				driver.findElement(By.id(prop.getProperty("QMQualificationname_id"))).sendKeys(data1[2][2]);
				driver.findElement(By.id(prop.getProperty("QMOKButton_id"))).click();
				
				Alert alert=driver.switchTo().alert();
				System.out.println(alert.getText());
				alert.accept();
			}
	
	@Test(dataProvider = "QMdata")
	public void TestQM(String Code, String name, String description, String Bname) throws Exception{
		//System.setProperty("webdriver.chrome.driver",""));
	baseclass.CommomSection1("HR_id", "HRsetup_linktext", "Recruitment_firepath", "QM_testlink", driver);
	
	driver.findElement(By.id(prop.getProperty("QMAddNew_id"))).click();
	Thread.sleep(1000);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("QMQualificatncode_id"))));
	driver.findElement(By.id(prop.getProperty("QMQualificatncode_id"))).sendKeys(Code);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("QMQualificationname_id"))));
	driver.findElement(By.id(prop.getProperty("QMQualificationname_id"))).sendKeys(name);
			
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("QMDescription_id"))));
	driver.findElement(By.id(prop.getProperty("QMDescription_id"))).sendKeys(description);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("QMOKButton_id"))));
	driver.findElement(By.id(prop.getProperty("QMOKButton_id"))).click();
				
	System.out.println(" data Accepted successfully");
		
	
	
/*	try {
		
	if(Bname.equals("OK"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.id(prop
					.getProperty("QMOKButton_id"))));
		driver.findElement(By.id(prop.getProperty("QMOKButton_id"))).click();
		Alert alert=driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		}
		else if(driver.findElement(By.id(prop.getProperty("QMcancelButton"))).isEnabled()){
		driver.findElement(By.id(prop.getProperty("QMcancelButton"))).click();
		}
		
		 if(Bname.equals("CANCEL"))
		{
			wait.until(ExpectedConditions.elementToBeClickable(By.id(prop
					.getProperty("QMcancelButton"))));
			driver.findElement(By.id(prop.getProperty("QMcancelButton"))).click();
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
*/

	
	if(driver.findElement(By.id(prop.getProperty("QMDescription_id"))).isEnabled()){
		
		driver.findElement(By.id(prop.getProperty("QMcancelButton"))).click();
		
	}
	else{
		System.out.println("new data added successfully");
	}
	}

		@DataProvider(name="QMdata")
		public Object[][] readExcel() throws BiffException, IOException{
			
			FileInputStream abc = new FileInputStream("D:\\HRMS DATA\\Datasheet\\QualificationMaster.xls");
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

	
