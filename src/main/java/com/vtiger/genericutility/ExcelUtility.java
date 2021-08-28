package com.vtiger.genericutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class is developed using Apache POi libraries, which is used to handle Microsoft Excel sheet
 * @author USM
 *
 */
public class ExcelUtility {

	/**
	 * This method is used to read the data from excel based on below arguments 
	 * @param sheet
	 * @param rowNum
	 * @param colNum
	 * @return
	 * @throws Throwable
	 */
	public String getDataFromExcel(String sheetName, int rowNum, int colNum) throws Throwable {
		FileInputStream fisData = new FileInputStream("./data/scriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fisData);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		String data = row.getCell(colNum).getStringCellValue();
		wb.close();
		return data;
	}
	
	/**
	 * This method is used to get the last used row number on specified Sheet
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 */
	public int getRowCount(String sheetName) throws Throwable {
		FileInputStream fisData = new FileInputStream("./data/scriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fisData);
		Sheet sh = wb.getSheet(sheetName);
		wb.close();
		return sh.getLastRowNum();
	}
	
	/**
	 * This method is used to write the data into the excel sheet based on below arguments 
	 * @param sheetName
	 * @param rowNum
	 * @param colNum
	 * @param data
	 * @throws Throwable
	 */
	public void setDataInExcel(String sheetName, int rowNum, int colNum, String data) throws Throwable {
		FileInputStream fisData = new FileInputStream("./data/scriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fisData);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cel = row.createCell(colNum);
		cel.setCellValue(data);
		FileOutputStream fos = new FileOutputStream("./data/scriptData.xlsx");
		wb.write(fos);
		wb.close();
	}
}
