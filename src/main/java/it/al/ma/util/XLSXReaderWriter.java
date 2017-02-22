package it.al.ma.util;

import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.ss.usermodel.Row; 
import org.apache.poi.xssf.usermodel.XSSFSheet; 
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import it.al.ma.dao.DailyTimeDaoImpl;
import it.al.ma.model.DailyTime;
import it.al.ma.model.User; 
/** * Sample Java program to read and write Excel file in Java using Apache POI * */ 

public class XLSXReaderWriter { 

	private static final String EXCEL_FILE_LOCATION = "WEB-INF/Timesheet_2017.xlsx";
	private static String EXCEL_FILE_OUTPUT = null;
	private XLSXReaderWriter() {
	}

	static{

		File excel = new File(EXCEL_FILE_LOCATION);
		Calendar dataFile = Calendar.getInstance();
		//cal.get(Calendar.DAY_OF_MONTH);
		Integer day = dataFile.get(Calendar.DAY_OF_MONTH);
		Integer mese = dataFile.get(Calendar.MONTH);
		Integer anno = dataFile.get(Calendar.YEAR);
		Integer ora = dataFile.get(Calendar.HOUR_OF_DAY);
		Integer minuti = dataFile.get(Calendar.MINUTE);
		Integer second = dataFile.get(Calendar.SECOND);
		String namedata= anno.toString() +mese.toString()+day.toString()+"_"+ora.toString()+minuti.toString()+second.toString();
		EXCEL_FILE_OUTPUT = new String(excel.getParent() +"//"+ namedata +"_"+ excel.getName());
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
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			Calendar oreLav = sdf.getCalendar();
			//int year = cal.get(Calendar.YEAR);
			//int month = cal.get(Calendar.MONTH);

			int r=7;
			for(DailyTime dT:listTime){
				NEWELEMENT:
				for (r=7;r<40;r++){
					cal.setTime(dT.getData());
					oreLav.setTime(dT.getData());
					Double day = (double) cal.get(Calendar.DAY_OF_MONTH);
					int col=1;
					Row row = sheet.getRow(r);

					Cell cell = row.getCell(col);
					//System.out.println(day);
					//System.out.println(cell.getNumericCellValue());
					if (day.equals(cell.getNumericCellValue()))
					{
						System.out.println("SONO NEL CICLO COLONNE");
						col++;
						cell = row.getCell(col);col++;
						//System.out.println(dT.getFirstshiftstart().substring(1, 2) + " FSooooSS "+dT.getSecondshiftstart().substring(0, 2));

						int ora = (Integer.parseInt(dT.getFirstshiftstart().substring(0, 2))>=10) ? Integer.parseInt(dT.getFirstshiftstart().substring(0, 2)) : Integer.parseInt(dT.getFirstshiftstart().substring(1, 2));						
						cal.set(Calendar.HOUR,ora);
						cal.set(Calendar.MINUTE,0);
						cal.set(Calendar.SECOND,0);	
						cell.setCellValue(cal.getTime());
						
						oreLav.set(Calendar.SECOND,0);
						oreLav.set(Calendar.MINUTE,0);
						oreLav.set(Calendar.HOUR,Integer.parseInt(dT.getFirstshiftstop().substring(0, 2)));					
						//System.out.println(Integer.parseInt(dT.getFirstshiftstop().substring(0, 2)));
						
						cell = row.getCell(col);col++;
						cell.setCellValue(oreLav.getTime());
						System.out.println(oreLav.getTime());
						
						oreLav.set(Calendar.HOUR,Integer.parseInt(dT.getSecondshiftstart().substring(0, 2))-12);
						//System.out.println(Integer.parseInt(dT.getSecondshiftstart().substring(0, 2)));
						cell = row.getCell(col);col++;
						//System.out.println(oreLav.getTime());
						cell.setCellValue(oreLav.getTime());

						
						oreLav.set(Calendar.HOUR,Integer.parseInt(dT.getSecondshiftstop().substring(0, 2))-12);
						//System.out.println(Integer.parseInt(dT.getSecondshiftstop().substring(0, 2)));
						cell = row.getCell(col);col++;
						//System.out.println(oreLav.getTime());
						cell.setCellValue(oreLav.getTime());

						cell = row.getCell(10);
						cell.setCellValue(dT.getCodpermesso());
						break NEWELEMENT;
					}
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
	
	
	public static OutputStream writeXlsx(InputStream is, OutputStream os,User user) { 

		try { 
			
			XSSFWorkbook book = new XSSFWorkbook(is); 
			//setto il foglio sul numero 2
			XSSFSheet sheet = book.getSheetAt(1); 

			Calendar now = Calendar.getInstance();
			now.set(Calendar.MONTH,0);
			now.set(Calendar.DAY_OF_MONTH, 1);
			java.sql.Date start=new java.sql.Date(now.toInstant().toEpochMilli());
			now.set(Calendar.MONTH,11);
			now.set(Calendar.DAY_OF_MONTH, now.getActualMaximum(Calendar.DAY_OF_MONTH));
			java.sql.Date end=new java.sql.Date(now.toInstant().toEpochMilli());

			
			DailyTimeDaoImpl dailyDao = new DailyTimeDaoImpl();
			List<DailyTime> listTime=new ArrayList<DailyTime>(dailyDao.findByIdUser(user, start, end));
			// writing data into XLSX file
			Calendar cal = Calendar.getInstance();
			
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			Calendar oreLav = sdf.getCalendar();

			int r=7;
			for(DailyTime dT:listTime){
				sheet = book.getSheetAt(dT.getData().getMonth()); 
				NEWELEMENT:
				for (r=7;r<40;r++){
					cal.setTime(dT.getData());
					oreLav.setTime(dT.getData());
					Double day = (double) cal.get(Calendar.DAY_OF_MONTH);
					int col=1;
					Row row = sheet.getRow(r);
					Cell cell = row.getCell(col);
					if (day.equals(cell.getNumericCellValue()))
					{
						System.out.println("SONO NEL CICLO COLONNE");
						col++;
						cell = row.getCell(col);col++;
						//System.out.println(dT.getFirstshiftstart().substring(1, 2) + " FSooooSS "+dT.getSecondshiftstart().substring(0, 2));

						int ora = (Integer.parseInt(dT.getFirstshiftstart().substring(0, 2))>=10) ? Integer.parseInt(dT.getFirstshiftstart().substring(0, 2)) : Integer.parseInt(dT.getFirstshiftstart().substring(1, 2));						
						cal.set(Calendar.HOUR,ora);
						cal.set(Calendar.MINUTE,0);
						cal.set(Calendar.SECOND,0);	
						cell.setCellValue(cal.getTime());
						
						oreLav.set(Calendar.SECOND,0);
						oreLav.set(Calendar.MINUTE,0);
						oreLav.set(Calendar.HOUR,Integer.parseInt(dT.getFirstshiftstop().substring(0, 2)));					
						//System.out.println(Integer.parseInt(dT.getFirstshiftstop().substring(0, 2)));
						
						cell = row.getCell(col);col++;
						cell.setCellValue(oreLav.getTime());
						
						oreLav.set(Calendar.HOUR,Integer.parseInt(dT.getSecondshiftstart().substring(0, 2))-12);
						cell = row.getCell(col);col++;
						cell.setCellValue(oreLav.getTime());
						
						oreLav.set(Calendar.HOUR,Integer.parseInt(dT.getSecondshiftstop().substring(0, 2))-12);
						cell = row.getCell(col);col++;
						cell.setCellValue(oreLav.getTime());

						cell = row.getCell(10);
						cell.setCellValue(dT.getCodpermesso());
						break NEWELEMENT;
					}
				}
				Row rowName = sheet.getRow(2);
				Cell cellName = rowName.getCell(11);
				if (cellName == null)
					cellName = rowName.createCell(11);
				cellName.setCellValue(user.getFirstname()+" "+ user.getLastname());
			}

			//Scrivo il Cognome e il Nome nella cella indicata
//			Row row = sheet.getRow(2);
//			Cell cell = row.getCell(11);
//			if (cell == null)
//				cell = row.createCell(11);
//			cell.setCellValue(user.getFirstname()+" "+ user.getLastname());

			// open an OutputStream to save written data into Excel file 
			//OutputStream Newos = os;
			
			book.write(os); 
			System.out.println("Writing on Excel file Finished ..."); 
			// Close workbook, OutputStream and Excel file to prevent leak 
			
			os.close(); 
			book.close(); 
			is.close();
			return os;
		} 
		catch (FileNotFoundException fe) { 
			fe.printStackTrace(); } 
		catch (IOException ie) { 
			ie.printStackTrace(); }
		return null; 
	} 
}





