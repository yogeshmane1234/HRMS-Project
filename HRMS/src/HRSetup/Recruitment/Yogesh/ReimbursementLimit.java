package HRSetup.Recruitment.Yogesh;

//import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;

import Excel.Excel;
//import HRMS_Recruitment.Login1;
import baseClass.BaseClass;

public class ReimbursementLimit {

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
	//String data1[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\TestSetup.xls", "Sheet1", 0, 11);
	//String data2[][]=dataXLS.CellData("D:\\HRMS DATA\\Datasheet\\TestSetup.xls", "Sheet3", 0, 11);
	
	WebDriverWait wait = new WebDriverWait(driver, 180);

		// ****************Login to Application****************
	@BeforeTest
	public void Login() throws BiffException, IOException, InterruptedException, Exception{
			
		baseclass.login(data[1][0], data[1][1], driver);
	}
		
	@Test(dataProvider = "RLdata")
	public void RLdata(String OUname, String employee, String AnnualLimit) throws Exception{
		//System.setProperty("webdriver.chrome.driver",""));
	baseclass.CommomSection1("HR_id", "HRsetup_linktext", "Recruitment_firepath", "Reimbursementlimit_linkText", driver);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("OUdropdown_id"))));
	Select Searchdropdown = new Select(driver.findElement(By.id(prop.getProperty("OUdropdown_id")))); 
	Searchdropdown.selectByVisibleText(OUname);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("SearchEmployee_id"))));
	driver.findElement(By.id(prop.getProperty("SearchEmployee_id"))).sendKeys(employee);
	Thread.sleep(3000);
	
	driver.findElement(By.id(prop.getProperty("Label_id"))).click();
	Thread.sleep(1000);
	driver.findElement(By.id(prop.getProperty("AddNewButton_id"))).click();
	Thread.sleep(2000);	
	
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("EffectiveDate_xpath"))));
	WebElement element =driver.findElement(By.xpath(prop.getProperty("EffectiveDate_xpath")));
	Thread.sleep(2000);
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("arguments[0].setAttribute('value','16-Sep-2016')",element);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("AnnualLimit_id"))));
	driver.findElement(By.id(prop.getProperty("AnnualLimit_id"))).clear();
	driver.findElement(By.id(prop.getProperty("AnnualLimit_id"))).sendKeys(AnnualLimit);
	
	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("OKButton_id"))));
	driver.findElement(By.id(prop.getProperty("OKButton_id"))).click();
	
	
	Alert alert=driver.switchTo().alert();
	System.out.println(alert.getText());
	alert.accept();
		
			}

		@DataProvider(name="RLdata")
		public Object[][] readExcel() throws BiffException, IOException{
			
			FileInputStream abc = new FileInputStream("D:\\HRMS DATA\\Datasheet\\ReimbursementLimit.xls");
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
