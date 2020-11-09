package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestUtil {
	
	public static Object[][] getTestData(String sheetName){
	
	FileInputStream file = null;
	try {
		file = new FileInputStream("F:/Whizdom-Trainings/Tools/Selenium/Online Classes/Aug2016Weekend/SeleniumFramework/src/main/java/excelData/data.xlsx");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	XSSFWorkbook workbook = null;
	try {
		workbook = new XSSFWorkbook(file);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	XSSFSheet sheet = workbook.getSheet("Login");
	Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	int rowcount=sheet.getLastRowNum();
	int colcount=sheet.getRow(1).getLastCellNum();
	
	for(int i=0; i<sheet.getLastRowNum();i++){
		XSSFRow currentRow = sheet.getRow(i);
		for(int j=0;j<sheet.getRow(0).getLastCellNum();j++){
			data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			String value = sheet.getRow(i+1).getCell(j).toString();
		}
	}
	return data;
	
	}
}
