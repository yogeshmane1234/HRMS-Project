package Excel;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.util.Arrays;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Excel {
	
	//public String fileLocation;
	//public String sheetName;
	public FileInputStream inputStream=null;
	public FileOutputStream outputStream=null;
	
	//String filelocation ="D:\\HRMS DATA\\Datasheet\\Login.xls";
	// Reading the data file with parameters FileLocation and Sheet Name
	
	
/*	public void Read_XLS(String sheetName)
	{
		
		//Logger logger=Logger.getLogger("DataXLS");
		//PropertyConfigurator.configure("log4j.properties");
		try
		{
		  FileInputStream inputStream = new FileInputStream(filelocation);
		  Workbook workBook = Workbook.getWorkbook(inputStream);
		  Sheet dataSheet = workBook.getSheet(sheetName);
		 // logger.info(dataSheet);
		//logger.info(workBook);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}*/
	
	// Counting the number of rows in the sheet --> [ Different set of data's ]
	
	public int retriveNoOfRows(String filelocation,String sheetName) throws BiffException, IOException 
	{
		//Logger logger=Logger.getLogger("DataXLS");
		//PropertyConfigurator.configure("log4j.properties");
		FileInputStream inputStream = new FileInputStream(filelocation);
		Workbook workBook = Workbook.getWorkbook(inputStream);
		Sheet dataSheet = workBook.getSheet(sheetName);
		
		int rowCount= dataSheet.getRows();
		return rowCount;
	}
	
	// Counting the number of columns in the sheet --> [ Number of parameters passed ] 
	
	public int retriveNoOfCols(String filelocation,String sheetName) throws BiffException, IOException
	{
		//Logger logger=Logger.getLogger("DataXLS");
		//PropertyConfigurator.configure("log4j.properties");
		FileInputStream inputStream = new FileInputStream(filelocation);
		Workbook workBook = Workbook.getWorkbook(inputStream);
		Sheet dataSheet = workBook.getSheet(sheetName);
		
		int columnCount = dataSheet.getColumns();
		return columnCount;
	}
	
	
	public String[][] CellData(String filelocation,String sheetName, int startValue, int endValue) 
	{
		//System.out.println("hi");
		//Logger logger=Logger.getLogger("DataXLS");
		//PropertyConfigurator.configure("log4j.properties");
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(filelocation);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Workbook workBook = null;
		try {
			workBook = Workbook.getWorkbook(inputStream);
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
		Sheet dataSheet = workBook.getSheet(sheetName);
				
		int totalRow = 0;
		try {
			totalRow = retriveNoOfRows(filelocation,sheetName);
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
		int totalColumn = 0;
		try {
			totalColumn = retriveNoOfCols(filelocation,sheetName);
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
		String[][] cellData = new String[totalRow][totalColumn];
		for (int i=0;i<totalRow;i++)
		{
			if(i >= startValue && i <= endValue)
			{
			for (int j=0;j<totalColumn;j++)
			{
				cellData[i][j] = dataSheet.getCell(j,i).getContents();
			}
			}
		//	logger.info("Data::::"+Arrays.toString(cellData[i]));
		}
		return cellData;
	}
	
}
		  
		
		

