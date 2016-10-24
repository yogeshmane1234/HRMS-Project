package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;


public class TestLinkProject {

    private WebDriver driver;
   
    // Substitute your Dev Key Here
    public final String DEV_KEY = "ea36783bf6276ac8f98cb2147888fde1";
    
    public static String SERVER_URL ="http://192.168.202.24/testlink/lib/api/xmlrpc.php";

      // Substitute your project name Here
    public final String PROJECT_NAME = "HRMS-Shangrila-Doha";

    // Substitute your test plan Here
    public final String PLAN_NAME = "CR_031016_Yogesh";

    // Substitute your build name
    public final String BUILD_NAME = "CR_v1.0_031016";

    @BeforeSuite
    public void setUp() throws Exception {
         driver = new FirefoxDriver();
         driver.manage().window().maximize();
    }
    
    public void updateTestLinkResult(String testCase, String exception, String result)    throws TestLinkAPIException {
        TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(DEV_KEY,
                               SERVER_URL);
        testlinkAPIClient.reportTestCaseResult(PROJECT_NAME, PLAN_NAME,
                               testCase, BUILD_NAME, exception, result);
    }

    @Test
    public void TestURLOpen() throws Exception {
         String result = "";
         String exception = null;
         try {
              driver.navigate().to("http://demo.osource.co.in/ERPONEX_INT_WEBAPP/Login.aspx");
              result = TestLinkAPIResults.TEST_PASSED;
              updateTestLinkResult("DOHA-17314", null, result);
         } catch (Exception ex) {
              result = TestLinkAPIResults.TEST_FAILED;
              exception = ex.getMessage();
              updateTestLinkResult("DOHA-17314", exception, result);
         }
    
         
         try {
              driver.findElement(By.id("Login1_UserName")).clear();
              driver.findElement(By.id("Login1_UserName")).sendKeys("admin crm");
              result = TestLinkAPIResults.TEST_PASSED;
              updateTestLinkResult("DOHA-17369", null, result);
         } catch (Exception ex) {
              result = TestLinkAPIResults.TEST_FAILED;
              exception = ex.getMessage();
              updateTestLinkResult("DOHA-17369", exception, result);
         }
    
         try {
        	 driver.findElement(By.id("Login1_Passwor")).clear();
             driver.findElement(By.id("Login1_Password")).sendKeys("abcd");
             
             result = TestLinkAPIResults.TEST_PASSED;
             exception = null;
             updateTestLinkResult("DOHA-17370", null, result);
         }
         catch (Exception ex) {
             result = TestLinkAPIResults.TEST_FAILED;
             exception = ex.getMessage();
             updateTestLinkResult("DOHA-17370", exception, result);
         }
         
         try {
        	 driver.findElement(By.id("Login1_LoginButton")).click();
             result = TestLinkAPIResults.TEST_PASSED;
             exception = null;
             updateTestLinkResult("DOHA-17371", null, result);
         }
         catch (Exception ex) {
             result = TestLinkAPIResults.TEST_FAILED;
             exception = ex.getMessage();
             updateTestLinkResult("DOHA-17371", exception, result);
         }
    }
       /*  String str = driver.findElement(
         By.xpath("//h1[@id='firstHeading']/span")).getText();                               
         Assert.assertTrue(str.contains("India"));
    }
*/

    @AfterSuite
    public void testEnd() throws Exception {
         driver.quit();                                                      
    }
   
    
    
}