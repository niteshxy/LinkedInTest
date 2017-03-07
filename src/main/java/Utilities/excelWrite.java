package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelWrite {
	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	public static void writetoExcel(String FilePath, String Filename, String row) throws IOException {

		File src = new File("/src/test/resources/Reports/ExcelReport/TestReport.xlsx");

		// Load the file

		FileInputStream fis = new FileInputStream(src);

		// load the workbook

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		// get the sheet which you want to modify or create

		XSSFSheet sh1 = wb.getSheetAt(0);
		sh1.getRow(0).createCell(1).setCellValue("Verify URL is opened succesfully");

		sh1.getRow(1).createCell(2).setCellValue("Verify language is present and english is present");

		sh1.getRow(2).createCell(2).setCellValue("2.39");

	}

	public static void WriteTCNames() {

	}

}
