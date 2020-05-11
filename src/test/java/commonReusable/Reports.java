package commonReusable;

import commonReusable.GetConfigVal;


import io.appium.java_client.android.AndroidDriver;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.apache.commons.io.FileUtils;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;




public class Reports {
	public String worddoc1 = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	public String filename1;
	public int ScreenshotNum = 1;
	public String currentDir = System.getProperty("user.dir");
	public String SQLQuery;
	public String bstatus[][];
	public String TestName;
	public String Keyword;	
	public int excelrptsno = 0;


	/******************************************************************************
	 * 	Method Name: WriteToExcelRpt --> To create, update the excel report at step level or when required
	 * 	Parameters:- 
	 * 				1. String ExpRes		--> refers to the Description / Expected result for the application under test 
	 * 				2. String ActualResult	--> refers to the Actual Results for the application under test
	 * 				3. String StepResult	--> refers to the Step Result EX. PASS or FAIL or Any user defined reference value like INFO, WARNING, or anything
	 * 				4. String Screenshot	--> System.getProperty("ScreenshotNameWithFilePath") holds the value of 
	 * 				5. String Comments		--> Any user defined comments like time stamp, etc
	 *  
	 *  Created On:	8 May 2020
	 *  Created By: Murugan
	 *  Modification History with Dates/By:
	 *  1.
	 *  2.
	 *  3. 
	 *****************************************************************************/	

	
	public void WriteToExcelRpt(String ExpRes, String ActualResult, String StepResult, String Screenshot, String Comments) throws Exception {
 
		String TestCaseName = System.getProperty("TCName");
		String FileNamewithAbsolutePath=System.getProperty("XlRptFile");
		String SheetName=System.getProperty("XlRptSheetName");				
		File file = new File(FileNamewithAbsolutePath);
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			workbook = new XSSFWorkbook();
		}
		
		XSSFSheet sheet = workbook.getSheet(SheetName);
		if (sheet == null) {
			sheet = workbook.createSheet(SheetName);
		}
		Row row = null;
		Cell cell = null;
		if (sheet.getLastRowNum() == 0) {
			row = sheet.createRow(0);
			cell = row.createCell(0, Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue("S.No");
			cell = row.createCell(1, Cell.CELL_TYPE_STRING);
			cell.setCellValue("TestCaseName");
			cell = row.createCell(2, Cell.CELL_TYPE_STRING);
			cell.setCellValue("ExpectedResult");
			cell = row.createCell(3, Cell.CELL_TYPE_STRING);
			cell.setCellValue("ActualResult");
			cell = row.createCell(4, Cell.CELL_TYPE_STRING);
			cell.setCellValue("StepResult");
			cell = row.createCell(5, Cell.CELL_TYPE_STRING);
			cell.setCellValue("Screenshot");
			cell = row.createCell(6, Cell.CELL_TYPE_STRING);
			cell.setCellValue("Comments");
		}
		int rows = sheet.getLastRowNum();
		int Newrow = rows + 1;
		row = sheet.createRow(Newrow);
		cell = row.createCell(0, Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(Newrow);
		cell = row.createCell(1, Cell.CELL_TYPE_STRING);
		cell.setCellValue(TestCaseName);
		cell = row.createCell(2, Cell.CELL_TYPE_STRING);
		cell.setCellValue(ExpRes);
		cell = row.createCell(3, Cell.CELL_TYPE_STRING);
		cell.setCellValue(ActualResult);
		cell = row.createCell(4, Cell.CELL_TYPE_STRING);
		cell.setCellValue(StepResult);
		cell = row.createCell(5, Cell.CELL_TYPE_STRING);
		cell.setCellValue(Screenshot);
		cell = row.createCell(6, Cell.CELL_TYPE_STRING);
		cell.setCellValue(Comments);
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		workbook.close();
		fos.close();
	}

	/******************************************************************************
	 * 	Method Name: reportstep --> Create word doc report. Update the report with Test Description, Actual Result, Status of the test, Date Timestamp, Android driver set at Open App method
	 * 	Parameters:- 
	 * 				1. String DESC		--> Refers to the test description of application under test
	 * 				2. String ACTUAL	-->	Refers to the Actual result as outcome of test
	 * 				3. String status	--> Test Status
	 * 				4. Date date		--> Timestamp of the date format
	 * 				5. AndroidDriver driver	-->	AndroidDriver instance created in OpenApp
	 *  
	 *  Created On:	8 May 2020
	 *  Created By: Murugan
	 *  Modification History with Dates/By:
	 *  1.
	 *  2.
	 *  3. 
	 *****************************************************************************/	

	public void reportstep(String DESC, String ACTUAL, String status, Date date, AndroidDriver driver) throws Exception {		
		GetConfigVal config = new GetConfigVal();
		String Filename = System.getProperty("TCName");
		String Filename1 = config.getReleaseName() +"_"+Filename;
		String FilePathForDoc = currentDir + "\\Results\\WordDocReport\\" + Filename1 + ".doc";
		String TCSubname = Filename.substring(0, 7) ;
		Path path = Paths.get(FilePathForDoc);
		if (Files.exists(path)) {			
			try {
				XWPFDocument document = new XWPFDocument(new FileInputStream(FilePathForDoc));
				XWPFParagraph p = document.createParagraph();
				XWPFRun r = p.createRun();				
				File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);				
				String keyword = config.getReleaseName();
				String dest = currentDir + "\\Results\\" + keyword + "\\Screenshots" + new SimpleDateFormat("yyyyMMddhhmmss").format(date) + "\\" + TCSubname+DESC + ".png";
				System.setProperty("ScreenshotNameWithFilePath", dest);
				File destination = new File(dest);
				FileUtils.copyFile(source, destination);
				String imgFile = dest;
				System.setProperty("ScreenshotNameWithFilePath", dest);
				int format = Document.PICTURE_TYPE_PNG;
				r.setText("DESCRIPTION :- " + DESC);
				r.addBreak();
				r.setText("ACTUAL RESULT :- " + ACTUAL);
				r.addBreak();
				r.setText("STATUS :- " + status);
				r.addBreak();
				r.addPicture(new FileInputStream(imgFile), format, imgFile, Units.toEMU(500), Units.toEMU(500));
				r.addBreak();
				r.addBreak(BreakType.PAGE);
				FileOutputStream out = new FileOutputStream(FilePathForDoc);
				document.write(out);
				out.flush();
			}

			catch (WebDriverException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

			try {
				XWPFDocument doc = new XWPFDocument();
				XWPFParagraph p = doc.createParagraph();
				XWPFRun r = p.createRun();
				File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);				
				String keyword = config.getReleaseName();
				String dest = currentDir + "\\Results\\" + keyword + "\\Screenshots"+ new SimpleDateFormat("yyyyMMddhhmmss").format(date) + "\\" + TCSubname+DESC + ".png";
				File destination = new File(dest);
				FileUtils.copyFile(source, destination);
				String imgFile = dest;
				int format = Document.PICTURE_TYPE_PNG;
				System.setProperty("ScreenshotNameWithFilePath", dest);
				r.setText("DESCRIPTION :- " + DESC);
				r.addBreak();
				r.setText("ACTUAL RESULT :- " + ACTUAL);
				r.addBreak();
				r.setText("STATUS :- " + status);
				r.addBreak();
				r.addPicture(new FileInputStream(imgFile), format, imgFile, Units.toEMU(500), Units.toEMU(500));				
				OutputStream out = new FileOutputStream(FilePathForDoc);
				doc.write(out);
				out.flush();
			}

			catch (WebDriverException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
}
