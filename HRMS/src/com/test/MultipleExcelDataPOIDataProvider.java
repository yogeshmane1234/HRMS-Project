
package com.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class MultipleExcelDataPOIDataProvider extends getExcelSheet {
	
	WebDriver driver = new FirefoxDriver();
	
	@Test(priority=0)
	public void url(){

        
         driver.manage().window().maximize();
        driver.get("http://demo.osource.co.in/ERPONEX_INT_WEBAPP/Login.aspx"); 
       
	}
   /*         FileInputStream file = new FileInputStream("D:\\HRMS DATA\\Login.xls"); 
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            int noOfColumns = sheet.getRow(0).getLastCellNum();
            //System.out.println(noOfColumns);
            String[] Headers = new String[noOfColumns];
            for (int j=0;j<noOfColumns;j++){
                Headers[j] = sheet.getRow(0).getCell(j).getStringCellValue();
            }
                for (int a=0;a<noOfColumns;a++){
                    if(Headers[a].equals("Username")){
                        driver.findElement(By.id("Login1_UserName")).sendKeys(sheet.getRow(2).getCell(a).getStringCellValue());
                        break;
                        }
}
      workbook.close();
      file.close();
      driver.close();
      System.exit(0);
            }*/

    public static Object[][] LoginData;
    public static Object[][] ShipmentData;
    public static HSSFRow Row;
    public static HSSFCell cell;
    public static String FilePath = "D:\\HRMS DATA\\Login.xls";
    public static String SheetName1 = "Sheet1";
    public static String SheetName2 = "Shipment";
    public static HSSFSheet Sheet;
    
    
    @Test(dataProvider="getLoginData",priority=1)
    public void TC01_Verify_Login_Valid_Cred(String User, String Pass) throws InterruptedException{

  //  System.out.println(User + Pass); 
    
    driver.findElement(By.id("Login1_UserName")).sendKeys(User);
    driver.findElement(By.id("Login1_Password")).sendKeys(Pass);
    driver.findElement(By.id("Login1_LoginButton")).click();
    driver.findElement(By.id("lnkBtn_Logout")).click();
    Thread.sleep(2000);
    
    }

    @DataProvider(name="getLoginData")
    public static Object[][] getLoginData() throws Exception{

        Sheet = DataSheet(FilePath, SheetName1);
        int rowCount = Sheet.getLastRowNum();
        System.out.println(rowCount);
        int colCount = Sheet.getRow(0).getLastCellNum();
        System.out.println(colCount);

        LoginData = new Object[rowCount][colCount];

        for (int rCnt=1; rCnt<=rowCount;rCnt++){
            for (int cCnt=0; cCnt<colCount;cCnt++){
                LoginData[rCnt-1][cCnt] = getCellData(SheetName1, rCnt, cCnt);
                System.out.println(LoginData[rCnt-1][cCnt]);
            }
        }

        return LoginData;
    }
    
    // read data from excel sheet using poi
    
     
    public static String getCellData(String Sheet, int row, int col){

        try {

            int index = WBook.getSheetIndex(Sheet);


            WSheet = WBook.getSheetAt(index);
            Row = WSheet.getRow(row);
            if (Row == null)
            return "";

            cell = Row.getCell(col);
            if (cell == null)
            return "";

            switch (cell.getCellType())
            {
            case  Cell.CELL_TYPE_STRING:
            return cell.getStringCellValue();               

            case  Cell.CELL_TYPE_BOOLEAN:
            return String.valueOf(cell.getBooleanCellValue());          

            case  Cell.CELL_TYPE_BLANK:
            return "";      

            case  Cell.CELL_TYPE_ERROR:
            return cell.getStringCellValue();           

            case  Cell.CELL_TYPE_NUMERIC:
            return String.valueOf(cell.getNumericCellValue());          

            default:
            return "Cell not found";        

            }
        }
            catch (Exception e) {
            e.printStackTrace();
            return "row " + row + " or column " + col+ " does not exist in xls";
            }

    }

   

  /*  @Test(dataProvider="getShipmentData")
    public void TC02_Verify_Shipment_Data(String TestID,String Weight, String Account,String DeclaredValue,String Execute,String Status){

    System.out.println(TestID+Weight+Account+DeclaredValue+Execute+Status); 
    }*/
}
