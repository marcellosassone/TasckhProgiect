package it.al.ma.util;

import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap; 
import java.util.Iterator;
import java.util.List;
import java.util.Map; 
import java.util.Set; 
import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.ss.usermodel.Row; 
import org.apache.poi.xssf.usermodel.XSSFSheet; 
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.core.SubstringMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import it.al.ma.dao.DailyTimeDao;
import it.al.ma.dao.DailyTimeDaoImpl;
import it.al.ma.model.DailyTime;
import it.al.ma.model.User; 
/** * Sample Java program to read and write Excel file in Java using Apache POI * */ 

public class XLSXReaderWriter { 

	private static final String EXCEL_FILE_LOCATION = "WEB-INF/Timesheet_2017.xls";
	private static String EXCEL_FILE_OUTPUT = null;

	private XLSXReaderWriter() {
	}

	static{

		File excel = new File(EXCEL_FILE_LOCATION);
		EXCEL_FILE_OUTPUT = new String(excel.getParent() +"//"+ "Pippo_" + excel.getName());
	}

	public static void readXlsx(int mounth) { 

		try { 

			File excel = new File(EXCEL_FILE_OUTPUT);

			System.out.println(excel.getName());
			System.out.println(excel.getParent());

			FileInputStream fis = new FileInputStream(excel); 
			XSSFWorkbook book = new XSSFWorkbook(fis); 
			XSSFSheet sheet = book.getSheetAt(mounth); 
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

			System.out.println("Reading on Excel file Finished ..."); 
			// Close workbook, OutputStream and Excel file to prevent leak 
			book.close(); 
			fis.close(); } 
		catch (FileNotFoundException fe) { 
			fe.printStackTrace(); } 
		catch (IOException ie) { 
			ie.printStackTrace(); } 
	} 

	public static void writeXlsx(User user, int month) { 

		try { 

			File excel = new File(EXCEL_FILE_LOCATION);
			//
			//			System.out.println(excel.getName());
			//			System.out.println(excel.getParent());
			//
			//			EXCEL_FILE_OUTPUT = new String(excel.getParent() +"//"+ "Pippo_" + excel.getName());

			File excelOut= new File(EXCEL_FILE_OUTPUT);

			FileInputStream fis = new FileInputStream(excel); 
			XSSFWorkbook book = new XSSFWorkbook(fis); 
			XSSFSheet sheet = book.getSheetAt(month); 

			Calendar now = Calendar.getInstance();
			now.set(Calendar.MONTH,month);
			now.set(Calendar.DAY_OF_MONTH, 1);
			java.sql.Date start=new java.sql.Date(now.toInstant().toEpochMilli());	
			now.set(Calendar.DAY_OF_MONTH, now.getActualMaximum(Calendar.DAY_OF_MONTH));
			java.sql.Date end=new java.sql.Date(now.toInstant().toEpochMilli());

			DailyTimeDaoImpl dailyDao = new DailyTimeDaoImpl();
			List<DailyTime> listTime=new ArrayList<DailyTime>(dailyDao.findByIdUser(user, start, end));
			// writing data into XLSX file

			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			//int month = cal.get(Calendar.MONTH);

			int r=7;
			for(DailyTime dT:listTime){
				NEWELEMENT:
				for (r=7;r<40;r++){
					cal.setTime(dT.getData());
					Double day = (double) cal.get(Calendar.DAY_OF_MONTH);
					int c=1;
					Row row = sheet.getRow(r);

					Cell cell = row.getCell(c);
					System.out.println(day);
					System.out.println(cell.getNumericCellValue());
					if (day.equals(cell.getNumericCellValue()))
					{
						System.out.println("SONO NEL CICLO COLONNE");
						c++;
						cell = row.getCell(c);c++;
						System.out.println("TIPO - " + cell.getDateCellValue());
						System.out.println("TIPO - " + cell.getNumericCellValue());
						cell.setCellValue(dT.getFirstshiftstart().substring(0, 5));
						//cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell = row.getCell(c);c++;
						cell.setCellValue((dT.getFirstshiftstop().substring(0, 5)));
						//cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell = row.getCell(c);c++;
						cell.setCellValue((dT.getSecondshiftstart().substring(0, 5)));
						//cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						cell = row.getCell(c);c++;
						cell.setCellValue((dT.getSecondshiftstop().substring(0, 5)));
						//cell.setCellType(Cell.CELL_TYPE_NUMERIC);
						break NEWELEMENT;
					}
				}
			}

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
			//Scrivo il Cognome e il Nome nella cella indicata
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
			fis.close(); 
		} 
		catch (FileNotFoundException fe) { 
			fe.printStackTrace(); } 
		catch (IOException ie) { 
			ie.printStackTrace(); } 
	} 

}



