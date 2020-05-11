package commonReusable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;


/**
 ****************************************************************************
 * HIGHLIGHTS:
 * > Methods used to get data from an excel file. 
 * > It returns the data as a string that can be saved on a variable.
 * This class requires the "org.apache.poi" libraries.
 ****************************************************************************
 */

public class GetExcelData {
	
	/******************************************************************************
	 * 	Method Name: GetRowNumber--> To get the row number matching the test case name in the excel file. 
	 * 	Parameters:- 
	 * 				1. String FileNamewithAbsolutePath - Test Data excel Filename with absolute path
	 * 				2. String strSheetName - Sheet Name in the test data file where the test case name and related test data is maintained
	 * 				3. String TestScriptName - Test case name matching the value of the cell in the excel vs the test case being executed.
	 * 
	 *  Created On:	2 May 2020
	 *  Created By: Murugan
	 *  Modification History with Dates/By:
	 *  1.		
	 *  2.
	 *  3. 
	 *****************************************************************************/	

	public int GetRowNumber(String FileNamewithAbsolutePath, String strSheetName, String TestScriptName)
			throws IOException {

		try {
			File fs = new File(FileNamewithAbsolutePath);
			FileInputStream fis = new FileInputStream(fs);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet excelSheet = wb.getSheet(strSheetName);
			int rowCount = excelSheet.getLastRowNum();

			for (int r = 0; r < rowCount; r++) {
				Row row = excelSheet.getRow(r);

				if (row != null) {
					String cell1 = row.getCell(1).toString().trim(); // getting // "TOBEEXECUTED"  // value
					if (cell1.equalsIgnoreCase(TestScriptName)) {
						System.out.println("Row No in the data file Matching the test case= r= " + r);
						wb.close();
						fis.close();
						return r;
					}
				}
			}
			wb.close();
			fis.close();
			return 10000;
		} catch (Exception e) {
			System.out.println("Exception in getting - GetRowNumber " + e.getMessage());
		}
		return 10000;
	}
	
	/******************************************************************************
	 * 	Method Name: GetDateFormat--> To get the date format to use it for reporting and the same will be returned.  
	 * 	Parameters:- 
	 * 	 * 
	 *  Created On:	2 May 2020
	 *  Created By: Murugan
	 *****************************************************************************/	

	
	public String GetDateFormat() {
		Date date = new Date();
		String date1 = String.valueOf(date);
		date1 = date1.replace("-", "_");
		date1 = date1.replace("/", "");
		date1 = date1.replace(" ", "");
		date1 = date1.replace(":", "");
		return date1;
	}
	
	/******************************************************************************
	 * 	Method Name: getCellValue--> This is to get the cell content based on the filename with absolute path, sheet name, row number and column name. 
	 * 					This is used to get the test data from the excel file for the given test case and field
	 * 	Parameters:- 
	 * 				1. int row	--> Refers to the row number in the test data sheet
	 * 				2. String strFilePath	--> refers to the excel test data file name with absolute path.
	 * 				3. String strSheetName	-->	refers to the excel sheet name
	 * 				4. String strColName	-->	refers to the column name or field name
	 * 
	 *  Created On:	2 May 2020
	 *  Created By: Murugan
	 *  Modification History with Dates/By:
	 *  1.		
	 *  2.
	 *  3. 
	 *****************************************************************************/	

	public String getCellValue(int row, String strFilePath,String strSheetName, String strColName) throws IOException{
		
		//Call reusable methods to open input stream and excel sheet
		FileInputStream fis = openInputStream(strFilePath);				
		XSSFSheet MySheet = openExcelSheet(fis, strSheetName);
		try
		{
		Row myRowCols = MySheet.getRow(0);
		String myDataValue = null;
		for (int j = 0; j < myRowCols.getLastCellNum(); j++) {
			String curColName = myRowCols.getCell(j).getStringCellValue();
			if (curColName.contentEquals(strColName)){
				Row myRow = MySheet.getRow(row);
				int intCellType = myRow.getCell(j).getCellType();
				if (intCellType==0){
				int myIntDataValue = (int) myRow.getCell(j).getNumericCellValue();
				myDataValue = Integer.toString(myIntDataValue);
			}else{myDataValue = myRow.getCell(j).getStringCellValue();}break;
			}
		}
		fis.close();
		return myDataValue;
		}
		catch(NullPointerException e)
		{
			return null;
		}
	}	
	
	/******************************************************************************
	 * 	Method Name: getCellFormulaValue --> To get the value in the formula cell 
	 * 	Parameters:- 
	 * 				1. int row	--> Refers to the row number in the test data sheet
	 * 				2. String strFilePath	--> refers to the excel test data file name with absolute path.
	 * 				3. String strSheetName	-->	refers to the excel sheet name
	 * 				4. String strColName	-->	refers to the column name or field name
	 * 
	 *  Created On:	2 May 2020
	 *  Created By: Murugan
	 *  Modification History with Dates/By:
	 *  1.		
	 *  2.
	 *  3. 
	 *****************************************************************************/	
	public static String getCellFormulaValue(int row, String strFilePath,String strSheetName, String strColName) throws IOException{
		
		//Call reusable methods to open input stream and excel sheet
		FileInputStream fis = openInputStream(strFilePath);				
		XSSFSheet MySheet = openExcelSheet(fis, strSheetName);
		try
		{
		
				//System.out.println("Excel Value is  = " +val );
				Row myRowCols = MySheet.getRow(0);
				System.out.println("myRowCols = " +myRowCols );
				
				String FormulaCellValue = null;
				for (int j = 0; j < myRowCols.getLastCellNum(); j++) 
				{
				String curColName = myRowCols.getCell(j).getStringCellValue();

				if (curColName.contentEquals(strColName))
					{
						DataFormatter formatter = new DataFormatter();
						FormulaCellValue = formatter.formatCellValue(MySheet.getRow(row).getCell(j));
						System.out.println("");
					}
				else
					{
					System.out.println("column is not present");
					}
				}
				fis.close();
				return FormulaCellValue;
		}
		catch(NullPointerException e)
		{
			return null;
		}
	}	
	
	
	/**
	* OBJECTIVE: Get the total of iterations to be used on a loop. 
	* DESCRIPTION: Used it when the test flow is driven by an an excel file. Like the UFT data table.
	* INPUT: strFilePath: example "C://Automation//MyProjects//..//..//..//" + strFileName + ".xlsx";  
	* 		  strSheetName: name of the excel sheet to be checked. Like global and local data tables in UFT.
	* OUTPUT: string with the value from the cell.
	*/

	public static int getTotIterations(String strFilePath, String strSheetName) throws IOException{

		//Call reusable methods to open input stream and excel sheet			
		FileInputStream fis = openInputStream(strFilePath);				
		XSSFSheet excelSheet = openExcelSheet(fis, strSheetName);
		
		int count = excelSheet.getLastRowNum();			
		fis.close();
		
		return count;
	}	

	/**
	* OBJECTIVE: Open input stream. 
	* DESCRIPTION: Input stream is reusable among methods. Then use this to method to open the fis.
	* INPUT: strFilePath: example "C://accenture//Confidential//Automation//MyProjects//" + strFileName + ".xlsx";  
	* OUTPUT: file input stream
	*/
	public static FileInputStream openInputStream(String strFilePath) throws IOException{

		File fis = new File(strFilePath);				
		FileInputStream excelFile = new FileInputStream(fis);				
											
		return excelFile;		
	}

	/**
	* OBJECTIVE: Open excel sheet. 
	* DESCRIPTION: Code to open excel sheet is reusable, so use this code.
	* INPUT: file input stream, use the "openInputStream" to create it and provide sheet name.
	* OUTPUT: excel file object open
	*/
	public static XSSFSheet openExcelSheet(FileInputStream fileInputStream, String strSheetName) throws IOException{
			
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		    	
//		System.out.println("Workbook created");
				
		XSSFSheet excelSheet = workbook.getSheet(strSheetName);
				
//		System.out.println("Sheet created");
				
		return excelSheet;		
	}	

	/**
	* OBJECTIVE: Close the file input stream. 
	* DESCRIPTION: Code to close excel sheet is reusable, so use this code.
	* INPUT: file input stream, use the "openInputStream" to create it.
	* OUTPUT: None (file will be closed)
	*/
	public static void closeExcelSheet(FileInputStream strFilePath) throws IOException{
		strFilePath.close();	
	}	
		

}
