package baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
//import org.testng.ITestResult;

import Excel.Excel;

public class BaseClass {

	private static final TakesScreenshot driver = null;
	public Excel dataXLS;
	public String fileLocation;
	public String sheetName;
	public FileInputStream inputStream = null;
	public FileOutputStream outputStream = null;
	//public String browser = ".//browser//chromedriver.exe";
	
	
	//---------------------------------------------------->>

	//public String DATA_FILE_PATH = "D:\\HRMS DATA\\Datasheet\\Login.xls";
	public String WEBELEMENT_FILE_PATH = ".//src//Properties//Excel.properties";
	// public String CONSTANTS_FILR_PATH
	// =".//src//resource//propertyFiles//constants.Properties";
	// public String DRIVER_FILE_PATH
	// =".//jar//chromedriver_win32_2.3//chromedriver.exe";
	// New Changes
//	public String Chrome_Driver_Path = System.getProperty("user.dir")
			//+ "D:\\HRMS DATA\\Selenium Browser\\chromedriver.exe";
	public String IE_Driver_Path = System.getProperty("user.dir")
			+ "D:\\HRMS DATA\\Selenium Browser\\IEDriverServer.exe";

	public Properties PropertiesConfigurations() {
		File file = new File(WEBELEMENT_FILE_PATH);
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fileInput);
			return prop;
		} catch (IOException e) {
			return prop;

		}
	}

	/*
	 * public Logger LogCongigurations() { Logger
	 * logger=Logger.getLogger("DataXLS"); return logger; }
	 */

	public WebDriver DriverConfigurations() {
		// new changes
		Properties prop = PropertiesConfigurations();
		if (prop.getProperty("browser").equals("Chrome")) {
			// set

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			capabilities.setCapability("chrome.binary","");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver", "D:\\HRMS DATA\\Selenium Browser\\chromedriver.exe");
			WebDriver driver = new ChromeDriver(capabilities);

			System.setProperty("webdriver.chrome.driver", "D:\\HRMS DATA\\Selenium Browser\\chromedriver.exe");
		    //WebDriver driver=new ChromeDriver();

			return driver;
		} else if (prop.getProperty("browser").equals("Mozilla")) {

			// Firefox profiling
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile myprofile = profile.getProfile("default");
			WebDriver driver = new FirefoxDriver(myprofile);
			return driver;

		} else if (prop.getProperty("browser").equals("IE"))
			System.setProperty("webdriver.ie.driver", IE_Driver_Path);
		WebDriver driver = new InternetExplorerDriver();
		return driver;
	}

	// this is common method for using xl sheet from baseclass 
	public Workbook DatasheetConfigurations(String DATA_FILE_PATH) {
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(DATA_FILE_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Workbook workBook = null;
		try {
			workBook = Workbook.getWorkbook(inputStream);
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workBook;
	}
//-----x------------x--------------x------------x---------------x-------------x------------x
	public Boolean login(String userName, String password, WebDriver driver)
			throws BiffException, IOException, InterruptedException {

		Properties prop = PropertiesConfigurations();
		// Logger logger=LogCongigurations();
		// WebDriver driver =DriverConfigurations();

		// Login to application
		driver.manage().window().maximize();
		driver.get("http://demo.osource.co.in/ERPONEX_INT_WEBAPP/Login.aspx");
		//driver.get("http://demo.osource.co.in/ERPONEX_TESTING/Login.aspx");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// logger.info("User Name ::::"+userName);
		// logger.info("Password::::"+password);
		driver.findElement(By.id(prop.getProperty("Login_UserName_id")))
				.clear();
		driver.findElement(By.id(prop.getProperty("Login_UserName_id")))
				.sendKeys(userName);
		driver.findElement(By.id(prop.getProperty("Login_Password_id")))
				.clear();
		driver.findElement(By.id(prop.getProperty("Login_Password_id")))
				.sendKeys(password);
		// WebElement domainElement
		// =driver.findElement(By.xpath(prop.getProperty("Login_Domain_Xpath")));
		// Select domainSelect = new Select(domainElement);
		// domainSelect.selectByValue(domain);
		driver.findElement(By.id(prop.getProperty("Login_LoginButton_id")))
				.click();

		// Wait for 5000 Milliseconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Validating Home Page
		String url = driver.getCurrentUrl();
		if (url.endsWith("Home")) {
			return true;

		} else {
			return false;

		}
	}

	public void MouseHover(String Menu, String SubMenu, WebDriver driver)
			throws InterruptedException {

		Properties prop = PropertiesConfigurations();
		WebDriverWait wait = new WebDriverWait(driver, 25);

		Actions action = new Actions(driver);

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(prop
				.getProperty(Menu))));
		action.moveToElement(
				driver.findElement(By.linkText(prop.getProperty(Menu))))
				.build().perform();

		// Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText(prop
				.getProperty(SubMenu))));
		driver.findElement(By.linkText(prop.getProperty(SubMenu))).click();

		Thread.sleep(2000);
	}

	public void MouseHover(String Menu, WebDriver driver) {

		Properties prop = PropertiesConfigurations();
		WebDriverWait wait = new WebDriverWait(driver, 0);

		Actions action = new Actions(driver);

		wait.until(ExpectedConditions.elementToBeClickable(By.name(prop
				.getProperty(Menu))));
		action.moveToElement(
				driver.findElement(By.name(prop.getProperty(Menu)))).build()
				.perform();
		driver.findElement(By.name(prop.getProperty(Menu))).click();
	}

	public void logout(WebDriver driver) throws BiffException, IOException,
			InterruptedException {
		Properties prop = PropertiesConfigurations();
		// Logger logger=LogCongigurations();
		// WebDriver driver =DriverConfigurations();

		driver.findElement(By.id(prop.getProperty("Logout_Button_id"))).click();
		// logger.info("Application Logged Out");
	}
	//-------------> taking screenshot ----------------> 
	/*public void Screenshot(WebDriver driver)
	{	 
		ITestResult result = null;
		
	if(ITestResult.FAILURE==result.getStatus())
	{
	try
	{
	
	TakesScreenshot ts=(TakesScreenshot)driver;	
	File source=ts.getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(source, new File("D:\\HRMS DATA\\ErrorScreenshot"+result.getName()+".png"));
	 
	System.out.println("Screenshot taken");
	}
	catch (Exception e)
	{
	 System.out.println("Exception while taking screenshot "+e.getMessage());
	}
	}
	 
	 }*/

	public  void CommonSelection(String Locator, String ListValue,
			WebDriver driver) {
		Properties prop = PropertiesConfigurations();
		Select select = new Select(driver.findElement(By.id(prop.getProperty(Locator))));
		select.selectByVisibleText(ListValue);
		// Select Class for all related classes
	}

	
	/*
	 * public void CommomMenu(String Module,String s1,String s2,WebDriver
	 * driver) throws Exception{
	 * 
	 * WebDriverWait wait = new WebDriverWait(driver, 0); Properties prop
	 * =ProperiesCongigurations(); Actions action = new Actions(driver);
	 * 
	 * wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty(
	 * Module))));
	 * action.moveToElement(driver.findElement(By.id(prop.getProperty
	 * (Module)))).build().perform();
	 * driver.findElement(By.id(prop.getProperty(Module))).click();
	 * 
	 * 
	 * WebElement
	 * element1=driver.findElement(By.linkText(prop.getProperty(s1)));
	 * action.moveToElement(element1).perform();
	 * 
	 * Thread.sleep(2000);
	 * 
	 * WebElement
	 * element2=driver.findElement(By.linkText(prop.getProperty(s2)));
	 * action.moveToElement(element2).perform();
	 * 
	 * }
	 */

	public void CommomMenu(String Module, String SubModule, String PageName,
			String FormName, String buttonname, WebDriver driver) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 0);
		Properties prop = PropertiesConfigurations();
		Actions action = new Actions(driver);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop
				.getProperty(Module))));
		action.moveToElement(
				driver.findElement(By.id(prop.getProperty(Module)))).build()
				.perform();
		driver.findElement(By.id(prop.getProperty(Module))).click();

		WebElement element1 = driver.findElement(By.linkText(prop
				.getProperty(SubModule)));
		action.moveToElement(element1).perform();

		Thread.sleep(2000);

		WebElement element2 = driver.findElement(By.linkText(prop
				.getProperty(PageName)));
		action.moveToElement(element2).perform();

		Thread.sleep(1000);

		driver.findElement(By.linkText(prop.getProperty(FormName))).click();
		driver.findElement(By.id(prop.getProperty(buttonname))).click();

	}
	
	public void CommomSection(String Module, String SubModule, String PageName,
			String FormName,  WebDriver driver) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 0);
		Properties prop = PropertiesConfigurations();
		Actions action = new Actions(driver);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop
				.getProperty(Module))));
		action.moveToElement(
				driver.findElement(By.id(prop.getProperty(Module)))).build()
				.perform();
		driver.findElement(By.id(prop.getProperty(Module))).click();

		WebElement element1 = driver.findElement(By.linkText(prop
				.getProperty(SubModule)));
		action.moveToElement(element1).perform();

		Thread.sleep(2000);

		WebElement element2 = driver.findElement(By.linkText(prop
				.getProperty(PageName)));
		action.moveToElement(element2).perform();

		Thread.sleep(1000);

		driver.findElement(By.linkText(prop.getProperty(FormName))).click();
	}
	
	//--------------> created for page locator taking classname ------------------>
	public void CommomSection1(String Module, String SubModule, String PageName,
			String FormName,  WebDriver driver) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 0);
		Properties prop = PropertiesConfigurations();
		Actions action = new Actions(driver);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop
				.getProperty(Module))));
		action.moveToElement(
				driver.findElement(By.id(prop.getProperty(Module)))).build()
				.perform();
		driver.findElement(By.id(prop.getProperty(Module))).click();

		WebElement element1 = driver.findElement(By.linkText(prop
				.getProperty(SubModule)));
		action.moveToElement(element1).perform();

		Thread.sleep(2000);

		WebElement element2 = driver.findElement(By.xpath(prop
				.getProperty(PageName)));
		action.moveToElement(element2).perform();

		Thread.sleep(4000);

		driver.findElement(By.linkText(prop.getProperty(FormName))).click();
	}
	
	
	
	//--------------> till hr set up perform hover function ------------>
	
	public void CommomSection2(String Module, String SubModule, WebDriver driver) throws Exception {

		WebDriverWait wait = new WebDriverWait(driver, 0);
		Properties prop = PropertiesConfigurations();
		Actions action = new Actions(driver);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop
				.getProperty(Module))));
		action.moveToElement(
				driver.findElement(By.id(prop.getProperty(Module)))).build()
				.perform();
		driver.findElement(By.id(prop.getProperty(Module))).click();
		Thread.sleep(1000);
		WebElement element1 = driver.findElement(By.linkText(prop
				.getProperty(SubModule)));
		action.moveToElement(element1).perform();

		Thread.sleep(1000);

		
			}
	
	
	
	//--------------> created for page locator taking classname ------------------>
		public void CommomSection3(String Module,  WebDriver driver) throws Exception {

			WebDriverWait wait = new WebDriverWait(driver, 0);
			Properties prop = PropertiesConfigurations();
			Actions action = new Actions(driver);

			wait.until(ExpectedConditions.elementToBeClickable(By.id(prop
					.getProperty(Module))));
			action.moveToElement(
					driver.findElement(By.id(prop.getProperty(Module)))).build()
					.perform();
			driver.findElement(By.id(prop.getProperty(Module))).click();

			
		}
		
		
		
		
		
	/*public void EXwaitByid(Properties prop ,WebDriver driver)
	{
		//Properties prop = ProperiesCongigurations();
		WebDriverWait wait = new WebDriverWait(driver, 180);

	wait.until(ExpectedConditions.elementToBeClickable(By.id(prop
				.getProperty(prop))));
	}
	
	public void EXwaitBylinkText(String Locator,WebDriver driver)
	{
		Properties prop = ProperiesCongigurations();
		WebDriverWait wait = new WebDriverWait(driver, 180);

	wait.until(ExpectedConditions.elementToBeClickable(By.linkText(prop
				.getProperty(Locator))));
	}
	public void EXwaitByxpath(String Locator,WebDriver driver)
	{
		Properties prop = ProperiesCongigurations();
		WebDriverWait wait = new WebDriverWait(driver, 180);

	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop
				.getProperty(Locator))));
	}*/

//-----------------------> ScreenShot --------------------->

/*public static void getScreenShot(WebDriver driver) throws Exception {
	  
	String Successfull_message="Success";
	  try{
          //the below statement will throw an exception as the element is not found, Catch block will get executed and takes the screenshot.
		  Assert.assertEquals("Successfull_message", "Success");
           
             //if we remove the below comment, it will not return exception and screen shot method will not get executed.
		  //driver.findElement(By.id("gbqfq")).sendKeys("test");
	  }
	  catch (Exception e){
		  System.out.println("I'm in exception");
//calls the method to take the screenshot.
		  GSS();
	  }
}

public static void GSS() throws Exception 
{
		
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
     //The below method will save the screen shot in d drive with name "screenshot.png"
        FileUtils.copyFile(scrFile, new File("D:\\abc.png"));
}*/
	public void TestClearSearchButton(String dropdown, String dropdownDATA,String TextBox,String TextBoxDATA,String ClearButton, WebDriver driver) throws Exception{
	
		Properties prop = PropertiesConfigurations();
		WebDriverWait wait = new WebDriverWait(driver, 180);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty(dropdown))));
		Select Searchdropdown = new Select(driver.findElement(By.id(prop.getProperty(dropdown)))); 
		Searchdropdown.selectByVisibleText(dropdownDATA);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty(TextBox))));
		driver.findElement(By.id(prop.getProperty(TextBox))).clear();
		driver.findElement(By.id(prop.getProperty(TextBox))).sendKeys(TextBoxDATA);	
		//driver.findElement(By.id(prop.getProperty(Button))).click();
		Thread.sleep(2000);
		driver.findElement(By.id(prop.getProperty(ClearButton))).click();
		// Assert.assertEquals(driver.getTitle(), "Question Master");
		WebElement TxtBoxContent = driver.findElement(By.id(prop.getProperty(TextBox)));
		TxtBoxContent.getText();

try{
	
	Assert.assertEquals(TxtBoxContent.getText(),"");
	System.out.println("Functionality of clear button is working properly");
}
catch(Exception e){
		
		System.out.println("I'm in exception : Clear button is not working");
		getscreenshot1(); 
			}
	}
		public void getscreenshot1() throws Exception 
		{
							
				 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				 FileUtils.copyFile(scrFile, new File("D:\\clearerror.png" ));
		
}
		
		// for using multiple filepath(property files)
		
		public Properties PropertiesConfigurations(String PropertyFilePath) {
			File file = new File(PropertyFilePath);
			FileInputStream fileInput = null;
			try {
				fileInput = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Properties prop = new Properties();
			try {
				prop.load(fileInput);
				return prop;
			} catch (IOException e) {
				return prop;

			}
		}

	
	
}
