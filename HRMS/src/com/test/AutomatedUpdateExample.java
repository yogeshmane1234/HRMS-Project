package com.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;


public class AutomatedUpdateExample {

 

public static String DEVKEY="cf028ac1cf42ad6f7b865e4db980c090";
public static String URL="http://192.168.202.24/testlink/index.php";
 

public static void reportResult(String TestProject,String TestPlan,String Testcase,String Build,String Notes,String Result) throws TestLinkAPIException{

TestLinkAPIClient api=new TestLinkAPIClient(DEVKEY, URL);

api.reportTestCaseResult(TestProject, TestPlan, Testcase, Build, Notes, Result);

}

 

@Test

public void Test1()throws Exception

{

AutomatedUpdateExample a=new AutomatedUpdateExample();

WebDriver driver=new FirefoxDriver();

WebDriverWait wait=new WebDriverWait(driver, 600);

String testProject="HRMS-Shangrila-Doha";

String testPlan="Issue_020916_yogesh";

String testCase="DOHA-4396";

String build="Issue_V1.0_020916";

String notes=null;

String result=null;

try{

driver.manage().window().maximize();

driver.get("http://demo.osource.co.in/ERPONEX_INT_WEBAPP/Login.aspx");

driver.findElement(By.id("Login1_UserName")).sendKeys("admin crm");

driver.findElement(By.id("Login1_Password")).sendKeys("abcd");

driver.findElement(By.id("Login1_LoginButton")).click();

driver.switchTo().defaultContent();

wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("+Testlink")));

result= TestLinkAPIResults.TEST_PASSED;

notes="Executed successfully";

}

catch(Exception e){

result=TestLinkAPIResults.TEST_FAILED;

notes="Execution failed";

}

finally{

 

a.reportResult(testProject, testPlan, testCase, build, notes, result);

driver.quit();

}

}

}
