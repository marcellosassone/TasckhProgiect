package it.al.ma.util;

import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.sql.Date; 
import java.util.HashMap; 
import java.util.Iterator; 
import java.util.Map; 
import java.util.Set; 
import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.ss.usermodel.Row; 
import org.apache.poi.xssf.usermodel.XSSFSheet; 
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import it.al.ma.model.User; 
/** * Sample Java program to read and write Excel file in Java using Apache POI * */ 

public class XLSXReaderWriter { 
	
	private static final String EXCEL_FILE_LOCATION = "WEB-INF/Timesheet_2017.xls";
	private static String EXCEL_FILE_OUTPUT = null;
	
	private XLSXReaderWriter() {
	}


	public static void readWriteXlsx(User user) { 

		try { 
			
			File excel = new File(EXCEL_FILE_LOCATION);
			
			System.out.println(excel.getName());
			System.out.println(excel.getParent());
			
			EXCEL_FILE_OUTPUT = new String(excel.getParent() +"//"+ "Pippo_" + excel.getName());
			
			File excelOut= new File(EXCEL_FILE_OUTPUT);
			
			FileInputStream fis = new FileInputStream(excel); 
			XSSFWorkbook book = new XSSFWorkbook(fis); 
			XSSFSheet sheet = book.getSheetAt(0); 
			Iterator<Row> itr = sheet.iterator(); 
			// Iterating over Excel file in Java 
			while (itr.hasNext()) 
			{ 
				Row row = itr.next(); 
				// Iterating over each column of Excel file 
				Iterator<Cell> cellIterator = row.cellIterator(); 
				while (cellIterator.hasNext()) { 
					Cell cell = cellIterator.next(); 
					switch (cell.getCellType()) { 
					case Cell.CELL_TYPE_STRING: 
						System.out.print(cell.getStringCellValue() + "\t"); 
					break; 
					case Cell.CELL_TYPE_NUMERIC: 
						System.out.print(cell.getNumericCellValue() + "\t"); 
					break; 
					case Cell.CELL_TYPE_BOOLEAN: 
						System.out.print(cell.getBooleanCellValue() + "\t"); 
					break; 
					default: } 
					} 
				System.out.println(""); 
				} 
			
			// writing data into XLSX file 
			Map<String, Object[]> newData = new HashMap<String, Object[]>(); 
			newData.put("7", new Object[] { 7d, "Sonya", "75K", "SALES", "Rupert" }); 
			newData.put("8", new Object[] { 8d, "Kris", "85K", "SALES", "Rupert" }); 
			newData.put("9", new Object[] { 9d, "Dave", "90K", "SALES", "Rupert" }); 
			Set<String> newRows = newData.keySet(); 
			int rownum = sheet.getLastRowNum(); 
			for (String key : newRows) { 
				Row row = sheet.createRow(rownum++); 
				Object[] objArr = newData.get(key); 
				int cellnum = 0; 
				for (Object obj : objArr) { 
					Cell cell = row.createCell(cellnum++); 
					if (obj instanceof String) { 
						cell.setCellValue((String) obj); } 
					else if (obj instanceof Boolean) { 
						cell.setCellValue((Boolean) obj); } 
					else if (obj instanceof Date) { 
						cell.setCellValue((Date) obj); } 
					else if (obj instanceof Double) { 
						cell.setCellValue((Double) obj); } 
					} 
				} 
			Row row = sheet.getRow(2);
		    Cell cell = row.getCell(11);
		    if (cell == null)
		        cell = row.createCell(11);
		    cell.setCellValue(user.getFirstname()+" "+ user.getLastname());
		    
			// open an OutputStream to save written data into Excel file 
			FileOutputStream os = new FileOutputStream(excelOut); 
			book.write(os); 
			System.out.println("Writing on Excel file Finished ..."); 
			// Close workbook, OutputStream and Excel file to prevent leak 
			os.close(); 
			book.close(); 
			fis.close(); } 
		catch (FileNotFoundException fe) { 
			fe.printStackTrace(); } 
		catch (IOException ie) { 
			ie.printStackTrace(); } 
	} 
}



