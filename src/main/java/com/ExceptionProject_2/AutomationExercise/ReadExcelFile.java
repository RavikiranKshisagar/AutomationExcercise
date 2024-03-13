package com.ExceptionProject_2.AutomationExercise;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {
	static FileInputStream fis;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public static XSSFSheet readExcelData(String Sheet) {
		try {
			fis = new FileInputStream(".//ExcelData//AutomationExerciseData.xlsx");
		}catch (Exception e) {
			System.out.println("The Excel file doesnt Exist, Please Check again!!");
		}
		try {
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(Sheet);
		}catch (Exception e) {
			System.out.println("No Specified Workbook present");
			e.printStackTrace();
		}		
		return sheet;
	}
}