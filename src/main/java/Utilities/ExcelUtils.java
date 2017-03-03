package Utilities;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;
	

public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {   

   String[][] tabArray = null;

   try {

	   FileInputStream ExcelFile = new FileInputStream(FilePath);

	   // Access the required test data sheet

	   ExcelWBook = new XSSFWorkbook(ExcelFile);

	   ExcelWSheet = ExcelWBook.getSheet(SheetName);

	   int startRow = 0;

	   int startCol = 0;

	   int ci,cj;

	   int totalRows = ExcelWSheet.getLastRowNum()+1;

	   int totalCols = 2;

	   tabArray=new String[totalRows][totalCols];

	   ci=0;

	   for (int i=startRow;i<=totalRows-1;i++, ci++) {           	   

		  cj=0;

		   for (int j=startCol;j<=totalCols-1;j++, cj++){

			   tabArray[ci][cj]=getCellData(i,j);

			   System.out.println(tabArray[ci][cj]);  

				}

			}

		}

	catch (FileNotFoundException e){

		System.out.println("Could not read the Excel sheet");

		e.printStackTrace();

		}

	catch (IOException e){

		System.out.println("Could not read the Excel sheet");

		e.printStackTrace();

		}

	return(tabArray);

	}

public static String getCellData(int RowNum, int ColNum) throws Exception {
	String CellData =null;
	DataFormatter df = new DataFormatter();

	try{

		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		CellData = df.formatCellValue(Cell);

	/*	CellType dataType = Cell.getCellTypeEnum();

		if  (dataType == CellType.BLANK|| dataType==CellType.ERROR) {

			return "";

		}else if(dataType==CellType.NUMERIC)
		{

			 CellData = Integer.toString(Cell.getNumericCellValue());

		}
		else if(dataType==CellType.STRING)
		{

			 CellData = Cell.getStringCellValue();

		}*/
	}
	
	
	catch (Exception e){

		System.out.println(e.getMessage());

		throw (e);

		}
	return CellData;

	

}
}