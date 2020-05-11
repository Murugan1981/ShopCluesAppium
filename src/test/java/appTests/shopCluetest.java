package appTests;

import java.io.IOException;
//import java.lang.ExceptionInInitializerError;
//import java.lang.InterruptedException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.io.IOException;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;

import appSteps.shopClueSteps;
import commonReusable.GetExcelData;
import io.appium.java_client.android.AndroidDriver;
import commonReusable.GetConfigVal;
import appSteps.baseTestDriver;

//import commonReusable.Reports;

/**
 ****************************************************************************
 *HIGHLIGHTS:
 * > SCRIPT NAME:
 * > DESCRIPTION:
 * > DATA:
 ****************************************************************************
 */
public class shopCluetest
{		
	shopClueSteps steps=new shopClueSteps();
	GetExcelData data = new GetExcelData();
	GetConfigVal config = new GetConfigVal();
	baseTestDriver bt= new baseTestDriver();
	
	public AndroidDriver driver;
	public 	String WorkingDir = System.getProperty("user.dir");
	public 	String strSheetName = "AndroidApps";
	long millis = System.currentTimeMillis();
	Date date3 = new Date(millis);
	int RowNo;
	
	public String XlRptFile = WorkingDir+config.getExcelResFolder()+config.getReleaseName()+"_"+config.getExcelResFileName()+"_"+new SimpleDateFormat("yyyyMMddhhmmss").format(date3)+".xlsx";	
	public String XlRptSheetName = config.getExcelResSheetName();
	
	


	/******************************************************************************
	 * 	Method Name: sc001installApp --> Refers to the install App test case
	 * 	Steps to be executed:		
	 * 		1. bt.installApp(strSheetName, RowNo, TestName) --> calling the method in baseTestDriver class --> It will install given app in the test data file
	 * 
	 *  Created On:	9 May 2020
	 *  Created By: Murugan
	 *  Modification History with Dates/By:
	 *  1.
	 *  2.
	 *  3. 
	 *****************************************************************************/	
	
	
	@Test
	public void sc001installApp() throws Exception  
	{
		String TestScriptName =  "sc001installApp";
		System.setProperty("ExecutionRes", "");				
		System.setProperty("XlRptFile",XlRptFile);
		System.setProperty("XlRptSheetName",XlRptSheetName);		
		String date1 = data.GetDateFormat();
		String TestName = TestScriptName + "_" + date1;
		System.setProperty("TCName",TestName);
		System.out.println("...........................<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>....................\n\n" + TestName + " is running:");

		String FileNamewithAbsolutePath = WorkingDir+"\\src\\test\\resources\\testData\\"+ config.getExcelFileName();
		System.out.println("FileNamewithAbsolutePath- "+FileNamewithAbsolutePath);
		RowNo = data.GetRowNumber(FileNamewithAbsolutePath, strSheetName, TestScriptName);

		try
		{
		bt.installApp(strSheetName, RowNo, TestName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	/******************************************************************************
	 * 	Method Name: AddProductToCart --> Add a product to the CART and proceed to checkout in the Shop clue application
	 * 	Steps to be executed:		
	 * 	1. OpenApp --> This method will open the app in the mobile based on the capabilities defined in the excel test data file.
	 * 	2. GetDriver --> Get the active driver once the application is opened or active currently.
	 * 	3. scSignIn -->  SignIn to the Shop clue application based on the credentials mentioned in test data file
	 * 	4. scSearchproduct --> Search the product to be added to the cart using filter conditions like Thermometer Type, Discount, Product Rating, Merchant rating
	 * 	5. Select Product, Add Product to Cart, Go to Cart, Place Order and verify Payment Method page is displayed
	 *  
	 *  Created On:	10 May 2020
	 *  Created By: Murugan
	 *  Modification History with Dates/By:
	 *  1.
	 *  2.
	 *  3. 
	 *****************************************************************************/	

	
	@Test
	public void sc004AddProductToCart() throws Exception  
	{
		String TestScriptName =  "sc004AddProductToCart";
		System.setProperty("ExecutionRes", "");				
		System.setProperty("XlRptFile",XlRptFile);
		System.setProperty("XlRptSheetName",XlRptSheetName);		
		String date1 = data.GetDateFormat();
		String TestName = TestScriptName + "_" + date1;
		System.setProperty("TCName",TestName);
		System.out.println("...........................<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>....................\n\n" + TestName + " is running:");

		String FileNamewithAbsolutePath = WorkingDir+"\\src\\test\\resources\\testData\\"+ config.getExcelFileName();
		System.out.println("FileNamewithAbsolutePath- "+FileNamewithAbsolutePath);
		RowNo = data.GetRowNumber(FileNamewithAbsolutePath, strSheetName, TestScriptName);

		try
		{
		bt.openApp(strSheetName, RowNo, TestName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		try
		{
			driver = bt.getDriver();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception with getting the driver active on the Simulator "+e.getMessage());
		}
		try
		{
		steps.scSignIn(strSheetName, RowNo, driver);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		try
		{
		steps.scSearchproduct(strSheetName, RowNo, driver);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		try
		{
		steps.scSelectProductAddToCart(strSheetName, RowNo, driver);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	/******************************************************************************
	 * Dummy Test to demonstrate TestNG run method 
	 *****************************************************************************/		
	@Test
	public void DummyTest1()  
	{
		System.out.println("DummyTest1");
	}
	
	@Test
	public void DummyTest2() 
	{
		System.out.println("DummyTest2");
	}
	
	/******************************************************************************
	 * Dummy Test to demonstrate TestNG run method 
	 *****************************************************************************/	


}