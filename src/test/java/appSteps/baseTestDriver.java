package appSteps;

import java.net.URL;
import java.util.Date;
import org.openqa.selenium.remote.DesiredCapabilities;

import commonReusable.GetConfigVal;
import commonReusable.GetExcelData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class baseTestDriver {
	String strSheetName;
	String TestName;
	int RowNo;
	public 	String WorkingDir = System.getProperty("user.dir");
	long millis = System.currentTimeMillis();
	Date date3 = new Date(millis);
	public AndroidDriver driver;
	GetConfigVal config = new GetConfigVal();
	
	/******************************************************************************
	 * 	Method Name:openApp-->	Open the application based on the capability values given in the test data excel file.
	 * 	Parameters -1. String strSheetName	--> refers to the test data sheet name where the test data is maintained for this method.
	 * 				2. int RowNo			--> RowNo in the test data file pointing to the current test case being executed
	 * 				3. String TestName		--> TestName refers to the name of the test case.
	 * 
	 *  Created On:	9 May 2020
	 *  Created By: Murugan
	 *  Modification History with Dates/By:
	 *  1.	
	 *  2.
	 *  3. 
	 *****************************************************************************/	
	
	public void openApp(String strSheetName,int RowNo, String TestName) throws Exception {
		
		GetConfigVal config = new GetConfigVal();
		GetExcelData data = new GetExcelData();
		String FileNamewithAbsolutePath = WorkingDir+"\\src\\test\\resources\\testData\\"+ config.getExcelFileName();		
		
		DesiredCapabilities dc = new DesiredCapabilities();		
		String sAUTOMATION_NAME= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "AUTOMATION_NAME");		
		String sPLATFORM_NAME= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "PLATFORM_NAME");
		String sPLATFORM_VERSION= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "PLATFORM_VERSION");
//		String sAppPath= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "AppPath");
		String sDEVICE_NAME= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "DEVICE_NAME");		
		String sURL= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "URL");	
		String sappPackage= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "appPackage");	
		String sappActivity= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "appActivity");	
		
		
		try
		{
			if(!sAUTOMATION_NAME.isEmpty() && sAUTOMATION_NAME != null)
			{
				dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,sAUTOMATION_NAME);
			}
			
			if(!sDEVICE_NAME.isEmpty() && sDEVICE_NAME != null)
			{
				dc.setCapability(MobileCapabilityType.DEVICE_NAME,sDEVICE_NAME);
			}
			
			if(!sPLATFORM_NAME.isEmpty() && sPLATFORM_NAME != null)
			{
				dc.setCapability(MobileCapabilityType.PLATFORM_NAME,sPLATFORM_NAME);
			}
			
			if(!sPLATFORM_VERSION.isEmpty() && sPLATFORM_VERSION != null)
			{
				dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,sPLATFORM_VERSION);
			}
			dc.setCapability("unicodeKeyboard", true);			
			if(!sappPackage.isEmpty() && sappPackage != null)
			{			
				dc.setCapability("appPackage",sappPackage);
			}
			dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 30000);
			
			if(!sappActivity.isEmpty() && sappActivity != null)
			{			
				dc.setCapability("appActivity",sappActivity);
			}
			
			try
			{
				URL url = new URL(sURL);
				driver = new AndroidDriver(url,dc);
				Thread.sleep(10000);
			}
			catch(Exception e)
			{
				System.out.println("Exception with openApp - " + e.getMessage());
				
			}
		}
		catch(Exception e)
		{			
			System.out.println("Exception in openApp method is "+e.getMessage());
		}
	}

	/******************************************************************************
	 * 	Method getDriver-->	This method is used to return the AndroidDriver driver . 
	 * 	Pre-Requisite 	--> OpenApp method should be executed and the application is opened in the simulator.
	 * 
	 *  Created On:	9 May 2020
	 *  Created By: Murugan
	 *  Modification History with Dates/By:
	 *  1.	
	 *  2.
	 *  3. 
	 *****************************************************************************/	
	
	public AndroidDriver getDriver() {
		return driver;	
	}
	
	/******************************************************************************
	 * 	Method installApp-->	Install the application based on the capability values given in the test data excel file.
	 * 	Parameters -1. String strSheetName	--> refers to the test data sheet name where the test data is maintained for this method.
	 * 				2. int RowNo			--> RowNo in the test data file pointing to the current test case being executed
	 * 				3. String TestName		--> TestName refers to the name of the test case.
	 * 
	 *  Created On:	9 May 2020
	 *  Created By: Murugan
	 *  Modification History with Dates/By:
	 *  1.	
	 *  2.
	 *  3. 
	 *****************************************************************************/	
	
	public void installApp(String strSheetName,int RowNo, String TestName) throws Exception {
		
		GetConfigVal config = new GetConfigVal();
		GetExcelData data = new GetExcelData();
		String FileNamewithAbsolutePath = WorkingDir+"\\src\\test\\resources\\testData\\"+ config.getExcelFileName();		
		
		DesiredCapabilities dc = new DesiredCapabilities();		
		String sAUTOMATION_NAME= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "AUTOMATION_NAME");		
		String sPLATFORM_NAME= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "PLATFORM_NAME");
		String sPLATFORM_VERSION= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "PLATFORM_VERSION");
		String sAppPath= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "AppPath");
		String sDEVICE_NAME= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "DEVICE_NAME");		
		String sURL= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "URL");	
				
		try
		{
			if(!sAUTOMATION_NAME.isEmpty() && sAUTOMATION_NAME != null)
			{
				dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,sAUTOMATION_NAME);
			}
			
			if(!sDEVICE_NAME.isEmpty() && sDEVICE_NAME != null)
			{
				dc.setCapability(MobileCapabilityType.DEVICE_NAME,sDEVICE_NAME);
			}
			
			if(!sPLATFORM_NAME.isEmpty() && sPLATFORM_NAME != null)
			{
				dc.setCapability(MobileCapabilityType.PLATFORM_NAME,sPLATFORM_NAME);
			}
			
			if(!sPLATFORM_VERSION.isEmpty() && sPLATFORM_VERSION != null)
			{
				dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,sPLATFORM_VERSION);
			}

			if(!sAppPath.isEmpty() && sAppPath != null)
			{
				dc.setCapability(MobileCapabilityType.APP,sAppPath);
			}
			
			try
			{
				URL url = new URL(sURL);
				driver = new AndroidDriver(url,dc);
			}
			catch(Exception e)
			{
				System.out.println("Exception with openApp - " + e.getMessage());
				
			}
		}
		catch(Exception e)
		{			
			System.out.println("Exception in openApp method is "+e.getMessage());
		}
	}

	

}
