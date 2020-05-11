package appSteps;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import commonReusable.GetExcelData; 
import commonReusable.GetConfigVal;
import appPages.shopcluePage;
import commonReusable.Reports;
//import io.appium.java_client.android.nativekey.*;

public class shopClueSteps{
	
	String strSheetName;
	String TestName;
	int RowNo;
	public 	String WorkingDir = System.getProperty("user.dir");
	long millis = System.currentTimeMillis();
	Date date3 = new Date(millis);
	public String testdatafolder = "\\src\\test\\resources\\testData\\";
//	baseTestDriver driver1 = new baseTestDriver();
	AndroidDriver driver;
	shopcluePage scPage = new shopcluePage(driver);
	GetConfigVal config = new GetConfigVal();
	GetExcelData data = new GetExcelData();
	Reports	reports = new Reports();
	baseTestDriver bt= new baseTestDriver();
    
	String FileNamewithAbsolutePath = WorkingDir+testdatafolder+ config.getExcelFileName();

	
	/******************************************************************************
	 * 	Method Name: openshopclueapp --> This method will open the app in the mobile based on the capabilities defined in the excel test data file.
	 * 	Parameters - None.
	 *  
	 *  Created On:	3 May 2020
	 *  Created By: Murugan
	 *  Modification History with Dates/By:
	 *  1.	  
	 *  2.
	 *  3. 
	 *****************************************************************************/	

	public void openshopclueapp() throws Exception
	{
		try
		{
		String date1 = data.GetDateFormat();
		String TestName = "OpenApp" + "_" + date1;
		bt.openApp(strSheetName, 1, TestName);
		}
		catch(Exception e)
		{			
			System.out.println("Exception with openApp");
		}
	}
	
	
	
	/******************************************************************************
	 * 	Method Name: scSignIn --> sc Refers to Shop Clue application. SignIn refers to logging into the application using given credentials in the excel file.
	 * 	Parameters -1. String strSheetName	--> refers to the test data sheet name where the test data is maintained for this method.
	 * 				2. int RowNo			--> RowNo in the test data file pointing to the current test case being executed
	 * 				3. AndroidDriver driver	--> driver assigned to the Mobile Application under test 
	 *  Created On:	2 May 2020
	 *  Created By: Murugan
	 *  Modification History with Dates/By:
	 *  1.	By Murugan: On 9th May 2020: Incorporated the reporting changes  
	 *  2.
	 *  3. 
	 *****************************************************************************/	
	
public void scSignIn(String strSheetName,int RowNo, AndroidDriver driver) throws Exception {

	WebDriverWait wait20 = new WebDriverWait(driver, 20);
	String xl_EmailID= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "EmailID");
	String xl_Password= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "Password");

	if(driver.findElements(scPage.lp_Menu_tv_Account_xpath).size()>0)
	{
		reports.reportstep("Open Shopclues app and verify if Account menu is displayed", "Account menu field is displayed", "PASS", date3,driver);
		reports.WriteToExcelRpt("Open Shopclues app and verify if Account menu is displayed", "Account menu field is displayed", "PASS", System.getProperty("ScreenshotNameWithFilePath"), "");			
	}
	else
	{
		reports.reportstep("Open Shopclues app and verify if Account menu is displayed", "Account menu field is not displayed. Issue in opening app", "FAIL", date3,driver);
		reports.WriteToExcelRpt( "Open Shopclues app and verify if Account menu is displayed", "Account menu field is not displayed.Issue in opening app", "FAIL", System.getProperty("ScreenshotNameWithFilePath"), "Refer the screenshot");			
	}

	clickOnElement(driver,"Account in Home Page", scPage.lp_Menu_tv_Account_xpath,"Sign In in My Account Page", scPage.myAcct_btn_SignIn_xpath,true);							// click On Account in Home Page	//String clickOnAccountinHomePage = 											
	clickOnElement(driver,"SignIn in My Account",scPage.myAcct_btn_SignIn_xpath,"Email ID or Mobile in Sign In Page",scPage.signIn_et_EnterMobileOrEmail_xpath,true);			//	click On Sign-In in My Account	//String clickOnSignIninMyAccount = 
	EnterText(driver,"Email or Mobile in Sign-in Page", scPage.signIn_et_EnterMobileOrEmail_xpath,xl_EmailID,false);															// Sign-In Enter Your Password				//String EnterEmailIDinMobileOrEmailIDField = 
	clickOnElement(driver,"Login via Password", scPage.signIn_tv_LoginviaPassword_xpath,"Enter Your Password", scPage.signIn_et_Enteryourpassword_xpath, false);				//	click On Sign-In in Log in Via Password			//String clickOnLoginViaPassword = 			
	EnterText(driver,"Enter Your Password",scPage.signIn_et_Enteryourpassword_xpath,xl_Password,true); 																			// Sign-In Enter Your Password			//String SignInEnterYourPassword = 
	clickOnElement(driver,"Login Button in Sign-in Page",scPage.signIn_tv_LoginBtn_xpath,"Account Menu in Home page",scPage.lp_Menu_tv_Account_xpath,false);					//	Sign IN - Click On Login button			//String clickOnLoginButton = 
	clickOnElement(driver,"Account Menu in Home page",scPage.lp_Menu_tv_Account_xpath,"Signed in Email ID",scPage.signIn_tv_DisplayedEmail_xpath,true);							// To verify the login, check for Account and click		//	String clickOnAccount = 	
	
	wait20.until(ExpectedConditions.visibilityOfElementLocated(scPage.signIn_tv_DisplayedEmail_xpath));
	if(driver.findElement(scPage.signIn_tv_DisplayedEmail_xpath).isDisplayed())
	{	
		String strDisplayedEmailID = driver.findElement(scPage.signIn_tv_DisplayedEmail_xpath).getText();
		System.out.println("Step Result for SignIn - Sign-In is successful and the displayed email id is "+strDisplayedEmailID );
	}
	
	clickOnElement(driver,"Home Menu in home page",scPage.lp_Menu_tv_Home_xpath,"Search image button",scPage.lp_ib_SearchProduct_xpath,true);									// Click on Home		//String clickOnHome = 			

}		//End of Sign in Method	

/******************************************************************************
 * 	Method Name: scSearchproduct --> sc Refers to Shop Clue application. Search in the shop clue application for the given product in the test data file.
 * 					Click on search button. Enter the product name. Filter by Discount, Thermometer Type, 
 * 	Parameters -1. String strSheetName	--> refers to the test data sheet name where the test data is maintained for this method.
 * 				2. int RowNo			--> RowNo in the test data file pointing to the current test case being executed
 * 				3. AndroidDriver driver	--> driver assigned to the Mobile Application under test 
 *  Created On:	3 May 2020
 *  Created By: Murugan
 *  Modification History with Dates/By:
 *  1.	By Murugan: on 10th May 2020: Incorporated the reporting changes  
 *  2.
 *  3. 
 *****************************************************************************/	

			//Search Product based on product rating, merchant rating, Thermometer Type
public void scSearchproduct(String strSheetName,int RowNo, AndroidDriver driver) throws Exception {
		
		//Declaration of Explicit wait 
	WebDriverWait wait20 = new WebDriverWait(driver, 20);

		//Declaration of Test Data File name with absolute path
	String FileNamewithAbsolutePath = WorkingDir+testdatafolder+ config.getExcelFileName();
		// Declare and read variables to read test data from excel
	String ProductName1= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "ProductName1");
		
	wait20.until(ExpectedConditions.visibilityOfElementLocated(scPage.lp_Menu_tv_Account_xpath));		// Wait for max of 20 sec for existance of Account field	

	clickOnElement(driver,"Search Product Image Button",scPage.lp_ib_SearchProduct_xpath,"Prduct search field",scPage.Search_et_SearchingFor_xpath,false);	//	Click on the Product Search Icon in the home page	
	
	EnterText(driver,"Product search field",scPage.Search_et_SearchingFor_xpath,ProductName1,false);		//	Enter Product name in Product Search text	
			
	driver.executeScript("mobile:performEditorAction",ImmutableMap.of("action","search"));		// Click on Search in mobile keyboard 

	try
	{
	wait20.until(ExpectedConditions.presenceOfElementLocated(scPage.Search_tv_Filter_xpath));
	}
	catch(Exception e)
	{
		System.out.println("Error in navigating to search results");
	}
	if(driver.findElements(scPage.Search_tv_Filter_xpath).size()>0)
	{
		reports.reportstep("Searching for the product", "list of products are displayed", "PASS", date3,driver);
		reports.WriteToExcelRpt("Searching for the product", "list of products are displayed", "PASS", System.getProperty("ScreenshotNameWithFilePath"), "");			
	}
	else
	{
		reports.reportstep("Searching for the product", "Product is not displayed or locator is changed", "FAIL", date3,driver);
		reports.WriteToExcelRpt("Searching for the product", "Product is not displayed or locator is changed", "FAIL", System.getProperty("ScreenshotNameWithFilePath"), "");
	}

	if(driver.findElements(scPage.Search_tv_FilterByCategoryThermometer).size()>0)
	{
		clickOnElement1(driver,scPage.Search_tv_FilterByCategoryThermometer);
	}
	
	clickOnElement(driver,"Filter in the product list",scPage.Search_tv_Filter_xpath,"Thermometer Type in the filters",scPage.Search_tv_FilterByThermometer_xpath,false);	
	
	String ClickOnThermometerType = clickOnElement(driver,"Thermometer Type",scPage.Search_tv_FilterByThermometer_xpath,"Infrared Thermomoter Type",scPage.Search_tv_ThermoType_IR_xpath,false);	// 	Specific filter conditions for Thermometer Type	
	if(ClickOnThermometerType.contains("PASS"))
	{
		System.out.println("Step Result of ClickOnThermometerType - "+ClickOnThermometerType);
		if(driver.findElements(scPage.Search_tv_ThermoType_IR_xpath).size()>0)
		{
			clickOnElement(driver,"Thermometer Type-Infrared",scPage.Search_tv_ThermoType_IR_xpath,"APPLY FILER in filters",scPage.Search_btn_APPLYFILTER,true);
		}
		else if(driver.findElements(scPage.Search_tv_ThermoType_Digital_xpath).size()>0)
		{			
			clickOnElement(driver,"Thermometer Type-Digital",scPage.Search_tv_ThermoType_Digital_xpath,"APPLY FILER in filters",scPage.Search_btn_APPLYFILTER,true);		
		}
		else if(driver.findElements(scPage.Search_tv_ThermoType_Mercury_xpath).size()>0)
		{
			clickOnElement(driver,"Thermometer Type-Digital",scPage.Search_tv_ThermoType_Mercury_xpath,"APPLY FILER in filters",scPage.Search_btn_APPLYFILTER,true);		
		}
	}
	else if(ClickOnThermometerType.contains("FAIL"))
	{
		System.out.println("Step Result of ClickOnThermometerType -"+ClickOnThermometerType);
	}
	
	clickOnElement(driver,"APPLY FILER in filters",scPage.Search_btn_APPLYFILTER,"FILTER in product list page",scPage.Search_tv_Filter_xpath,false);
	clickOnElement(driver,"FILTER in product list page",scPage.Search_tv_Filter_xpath,"DISCOUNT Filter option",scPage.Search_tv_FilterByDiscountRat_xpath,false);
	
	if(driver.findElements(scPage.Search_tv_FilterByDiscountRat_xpath).size()>0)
	{
	
		driver.findElement(scPage.Search_tv_FilterByDiscountRat_xpath).click();	
		System.out.println("Step Result of Click on Search_tv_FilterByDiscountRat_xpath is PASS");
		if(driver.findElements(scPage.Search_tv_Discount80_xpath).size() >0)
		{
			driver.findElement(scPage.Search_tv_Discount80_xpath).click();
			System.out.println("Step Result of Click on Search_tv_Discount80_xpath is PASS");
		}
		else if(driver.findElements(scPage.Search_tv_Discount60_xpath).size() >0)
		{
			driver.findElement(scPage.Search_tv_Discount60_xpath).click();
			System.out.println("Step Result of Click on Search_tv_Discount60_xpath is PASS");
		}
		else if(driver.findElements(scPage.Search_tv_Discount40_xpath).size() >0)
		{
			driver.findElement(scPage.Search_tv_Discount40_xpath).click();
			System.out.println("Step Result of Click on Search_tv_Discount40_xpath is PASS");
		}
		else if(driver.findElements(scPage.Search_tv_Discount20_xpath).size() >0)
		{
			driver.findElement(scPage.Search_tv_Discount20_xpath).click();
			System.out.println("Step Result of Click on Search_tv_Discount20_xpath is PASS");
		}
	}
	
	clickOnElement(driver,"APPLY FILER in filters",scPage.Search_btn_APPLYFILTER,"FILTER in product list page",scPage.Search_tv_Filter_xpath,false);		
	clickOnElement(driver,"FILTER in product list page",scPage.Search_tv_Filter_xpath,"DISCOUNT Filter option",scPage.Search_tv_FilterByDiscountRat_xpath,false);
	
		 
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);				// Change the default wait to 5 sec
	try
	{
		if(driver.findElements(scPage.Search_tv_FilterByProductRat_xpath).size()>0)
		{
			driver.findElement(scPage.Search_tv_FilterByProductRat_xpath).click();			
			System.out.println("Step Result of Clicked on Product1ProductRating is PASS.");
			SelectBestRating(driver);												// Call Rating click method
		}
		else
		{
			System.out.println("Step Result of Product1ProductRating is NA- Product1ProductRating is not displayed");
		}
	}
	catch(Exception e)
	{
		System.out.println("Step Result of Product1ProductRating is FAIL - Exception with Product Rating selection - "+e.getMessage());
	}
	
	try
	{
		if(driver.findElements(scPage.Search_tv_FilterByMerchantRat_xpath).size()>0)
		{
			driver.findElement(scPage.Search_tv_FilterByMerchantRat_xpath).click();			
			System.out.println("Step Result of Click on Search_tv_FilterByMerchantRat_xpath is PASS.");
			SelectBestRating(driver);								// Call Rating click method
		}
		else
		{			
			System.out.println("Step Result of Search_tv_FilterByMerchantRat_xpath is NA- Search_tv_FilterByMerchantRat_xpath is not displayed");
		}
	}
	catch(Exception e)
	{
		System.out.println("Step Result of Merchant Rating selection is FAIL - Exception with Merchant Rating selection - "+e.getMessage());		
	}
		// Revert back to 20 sec 
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	clickOnElement(driver,"APPLY FILER in filters",scPage.Search_btn_APPLYFILTER,"FILTER in product list page",scPage.Search_tv_Filter_xpath,false);
}

/******************************************************************************
 * 	Method Name:scSelectProductAddToCart-->	sc Refers to Shop Clue application. Select the product and Add the product to the CART. 
 * 											If Address is not maintained, the address will get added as per the test data in excel. 
 * 											Place Order and navigate till the payment methods.
 * 	Parameters -1. String strSheetName	--> refers to the test data sheet name where the test data is maintained for this method.
 * 				2. int RowNo			--> RowNo in the test data file pointing to the current test case being executed
 * 				3. AndroidDriver driver	--> driver assigned to the Mobile Application under test 
 *  Created On:	3 May 2020
 *  Created By: Murugan
 *  Modification History with Dates/By:
 *  1.	By Murugan: on 10th May 2020: Incorporated the reporting changes
 *  2.
 *  3. 
 *****************************************************************************/	

public void scSelectProductAddToCart(String strSheetName,int RowNo, AndroidDriver driver) throws Exception {
		
	WebDriverWait wait20 = new WebDriverWait(driver, 20);															//Declaration of Explicit wait
	
	String FileNamewithAbsolutePath = WorkingDir+testdatafolder+ config.getExcelFileName();							//Declaration of Test Data File name with absolute path		
	
	String Addr_Pincode= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "Addr_Pincode");			// Declare and read variables to read test data from excel
	String Addr_PhoneNumber= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "Addr_PhoneNumber");	
	String Addr_Address1= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "Addr_Address1");
	String Addr_Address2= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "Addr_Address2");
	String Addr_City= data.getCellValue(RowNo, FileNamewithAbsolutePath, strSheetName, "Addr_City");
	
		
	wait20.until(ExpectedConditions.visibilityOfElementLocated(scPage.psd_img_SelectprdByPrdImage));								// Wait for max of 20 sec for existance of Account field	
	
	clickOnElement(driver,"Product Image",scPage.psd_img_SelectprdByPrdImage,"ADD TO CART",scPage.psd_btn_AddToCart,true);			//	Click on the Product Search Icon in the home page		
	getTextFromElement(driver,scPage.psd_img_SelectprdByPrdImage);																	//	Click on the Product Search Icon in the home page
	clickOnElement(driver,"ADD TO CART",scPage.psd_btn_AddToCart,"GO TO CART",scPage.psd_btn_GoToCart,true);						//	Click on ADD TO CART	
	clickOnElement(driver,"GO TO CART",scPage.psd_btn_GoToCart,"PLACE ON ORDER",scPage.psd_tv_PlaceOrder,true);						//	Click on GO TO CART	
	
	String ClickonPlaceOrder = clickOnElement1(driver,scPage.psd_tv_PlaceOrder);													//	Click on Place Order	
	System.out.println("Step Output of ClickonPlaceOrder - "+ ClickonPlaceOrder);
	
	if(driver.findElements(scPage.psd_et_Addr_Pincode).size()>0)
	{
		EnterText(driver,"PIN CODE",scPage.psd_et_Addr_Pincode,Addr_Pincode,false);													//	Enter Pincode	
		EnterText(driver,"PHONE NUMBER",scPage.psd_et_Addr_PhoneNumber,Addr_PhoneNumber,false);										//	Enter Phone number
		EnterText(driver,"ADDRESS1",scPage.psd_et_Addr_Address1,Addr_Address1,false);												//	Enter ADDRESS1
		EnterText(driver,"ADDRESS2",scPage.psd_et_Addr_Address2,Addr_Address2,false);												//	Enter ADDRESS 2	
		EnterText(driver,"CITY",scPage.psd_et_Addr_City,Addr_City,true);															//	Enter CITY	
		String ClickonHomeAddress = clickOnElement1(driver,scPage.psd_et_Addr_TypeHome);											//	Click on ADD TO CART
		System.out.println("Step Output of ClickonHomeAddress - "+ ClickonHomeAddress);
		try																															// Scroll down to view Save & Continue														
		{
			String text = "Save & Continue";	
			String uiSelector = "new UiSelector().textMatches(\"" + text+ "\")";
			String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView("+uiSelector+");";
			driver.findElementByAndroidUIAutomator(command);
		}
		catch(Exception e)
		{
			System.out.println("Exception in driver.findElementByAndroidUIAutomator(command) is" + e.getMessage());
		}
		
		try
		{	
			clickOnElement(driver,"SAVE and CONTINUE in ADDRESS PAGE", scPage.psd_tv_Addr_SaveContinue,"",scPage.psd_tv_PlaceOrderAndPayNow,true);		//	Click on Save And Continue
		}
		catch(Exception e)
		{
			System.out.println("Exception in ClickonSaveAndContinue" + e.getMessage());
		}
	}
	clickOnElement(driver,"PLACE ORDER",scPage.psd_tv_PlaceOrderAndPayNow,"Payment Page",scPage.psd_tv_SelectPaymentMethod,true);						//	Click on SaveAnd Continue
}

/******************************************************************************
 * 	Method Name: SelectBestRating		-->	Application specific. In the Filter options, Select the top most rating available in Product and Merchant rating.
 * 											  											
 * 	Parameters -1. AndroidDriver driver	--> driver assigned to the Mobile Application under test 
 *  Created On:	9 May 2020
 *  Created By: Murugan
 *  Modification History with Dates/By:
 *  1.	
 *  2.
 *  3. 
 *****************************************************************************/	


public void SelectBestRating(AndroidDriver driver)
{
	if(driver.findElements(scPage.Search_tv_FilterByRat4_xpath).size()>0)
	{
		driver.findElement(scPage.Search_tv_FilterByRat4_xpath).click();
		System.out.println("Clicked on Rating 4 and above");
	}
	else if(driver.findElements(scPage.Search_tv_FilterByRat3_xpath).size()>0)
	{
		driver.findElement(scPage.Search_tv_FilterByRat3_xpath).click();
		System.out.println("Clicked on Rating 3 and above");
	}
	else if(driver.findElements(scPage.Search_tv_FilterByRat2_xpath).size()>0)
	{
		driver.findElement(scPage.Search_tv_FilterByRat2_xpath).click();
		System.out.println("Clicked on Rating 2 and above");
	}
	else if(driver.findElements(scPage.Search_tv_FilterByRat1_xpath).size()>0)
	{
		driver.findElement(scPage.Search_tv_FilterByRat1_xpath).click();
		System.out.println("Clicked on Rating 1 and above");
	}
}

/******************************************************************************
 * 	Method Name: SelectRating		-->	Application specific. In the Filter options, Based on the rating entered in the test data file, Select the top most rating available in Product and Merchant rating
 * 											  											
 * 	Parameters -1. AndroidDriver driver	--> driver assigned to the Mobile Application under test 
 *  Created On:	9 May 2020
 *  Created By: Murugan
 *  Modification History with Dates/By:
 *  1.	
 *  2.
 *  3. 
 *****************************************************************************/	

public void SelectRating(AndroidDriver driver, int rating)
{
	switch(rating)
	{
	case 1:
		if(driver.findElement(scPage.Search_tv_FilterByRat1_xpath).isDisplayed())
		{
			driver.findElement(scPage.Search_tv_FilterByRat1_xpath).click();
			System.out.println("Clicked on Rating 1 and above");
			
		}
		else
		{
			System.out.println("Rating 1 and above is not displayed");
		}
		break;
		
	case 2:
		if(driver.findElement(scPage.Search_tv_FilterByRat2_xpath).isDisplayed())
		{
			driver.findElement(scPage.Search_tv_FilterByRat2_xpath).click();
			System.out.println("Clicked on Rating 2 and above");
			
		}
		else
		{
			System.out.println("Rating 2 and above is not displayed");
		}
		break;
	case 3:
		if(driver.findElement(scPage.Search_tv_FilterByRat3_xpath).isDisplayed())
		{
			driver.findElement(scPage.Search_tv_FilterByRat3_xpath).click();
			System.out.println("Clicked on Rating 3 and above");
			
		}
		else
		{
			System.out.println("Rating 3 and above is not displayed");
		}
		break;
	case 4:
		if(driver.findElement(scPage.Search_tv_FilterByRat4_xpath).isDisplayed())
		{
			driver.findElement(scPage.Search_tv_FilterByRat4_xpath).click();
			System.out.println("Clicked on Rating 4 and above");
		}
		else
		{
			System.out.println("Rating 4 and above is not displayed");
		}
		break;
	default:
		System.out.println("Condition - " +rating+ " is not matching with any case. so executing default.");
	}
}


// To be moved to ApplicationEvents:

/**
 ****************************************************************************
 * 	Method Name: EnterText1 --> To enter the value <val> in the field By Element - Simple key-in in the given field
 * 	Parameters -1. driver is set at baseTestDriver --> refers to the AndroidDriver of application under test[Mobile App] based on the desired capabilities set at openApp.
 * 				2. Element 	--> By Element[xpath or id] as defined in the shopClues page
 * 				3. val		--> String value to entered in the field
 *  Created On:	2 May 2020
 *  Created By: Murugan
 *  Modification History with Dates/By:
 *  1.	
 *  2.
 *  3. 
 ****************************************************************************
 */	
public String EnterText1(AndroidDriver driver, By Element,String val)
{
	String msg = null;
	WebDriverWait wait20 = new WebDriverWait(driver, 20);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	wait20.until(ExpectedConditions.visibilityOfElementLocated((By) Element));
	
	try
	{		
		if(driver.findElements((By) Element).size()>0)
		{
			int TotEle = driver.findElements((By) Element).size();
			for(int i=0;i<TotEle;i++)
			{
				if(driver.findElement((By) Element).isDisplayed() & driver.findElement((By) Element).isEnabled())
				{
					driver.findElement((By) Element).sendKeys(val);					
					msg = "PASS.Entered the text - " +val+" in the field - "+Element;
					break;					
				}
			}

		}
	}
	catch(Exception e)
	{
		msg="FAIL.Exception while entering the text " + val+ " in the element "+Element+" is -"+e.getMessage();		
	}
	return msg;
}

/******************************************************************************
 * 	Method Name: EnterText --> To enter the value <val> in the field By CurrentElement, Call excel result generator. Update Word document report if WordDocReport is true 
 * 	Parameters -1. AndroidDriver driver --> driver is set at baseTestDriver --> refers to the AndroidDriver of application under test[Mobile App] based on the desired capabilities set at openApp.
 * 				2. String CurrentElementDesc --> String to describe and briefly define about the element under test
 * 				3. By CurrentElement 	--> By Element[xpath or id] as defined in the shopClues page
 * 				4. val		--> String value to entered in the field
 * 				5. Boolean WordDocReport	--> true to update Word document report with the result for the current step output. false - if word doc update is not needed.
 * 												Excel report will be updated irrespective of the flag set by WordDocReport
 *  Created On:	10 May 2020
 *  Created By: Murugan
 *  Modification History with Dates/By:
 *  1.		By Murugan: 10th May 2020: Similar of EnterText1. But Integrated Excel and Word doc report to reduce the number of lines of code in the main program and increase readability
 *  2.
 *  3. 
 *****************************************************************************/	

public String EnterText(AndroidDriver driver, String CurrentElementDesc, By CurrentElement, String val,  Boolean WordDocReport) throws Exception
{
	WebDriverWait wait40 = new WebDriverWait(driver, 40);		
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	String TestDescriptionWord = "Enter -"+val+"-in the field- "+CurrentElementDesc;
	String TestDescriptionExcel = "Enter -"+val+"-in the field- "+CurrentElementDesc;
	String strResult="NA";
	String ActualResult="";
	wait40.until(ExpectedConditions.visibilityOfElementLocated((By) CurrentElement));
	
	try
	{		
		if(driver.findElements((By) CurrentElement).size()>0)
		{
			int TotEle = driver.findElements((By) CurrentElement).size();				
			for(int i=0;i<TotEle;i++)
			{
				if(driver.findElement((By) CurrentElement).isDisplayed() & driver.findElement((By) CurrentElement).isEnabled())
				{
					driver.findElement((By) CurrentElement).sendKeys(val);						
					ActualResult = "PASS.The field <"+CurrentElementDesc+"> is entered with the value - "+val;						
					strResult = "PASS";
					break;						
				}					
			}
			if(strResult.contains("NA"))
			{
				ActualResult = "FAIL.The element is not enabled and or displayed";
				strResult = "FAIL";
			}
		}
		else
		{
			ActualResult = "FAIL.Entering the value <"+val+"> in the field <"+CurrentElementDesc+"> failed as the element is not available";
			strResult = "FAIL";
		}
	}
	catch(Exception e)
	{
		ActualResult = "FAIL.Entering the value <"+val+"> in the field <"+CurrentElementDesc+"> failed  as exception encountered. The exception is" +e.getMessage();
		e.printStackTrace();
		strResult = "FAIL";			
	}	
		
	
	if(WordDocReport)	// BOTH WORD AND EXCEL REPORT WILL BE UPDATED
	{
		reports.reportstep(TestDescriptionWord, ActualResult, strResult, date3,driver);
		reports.WriteToExcelRpt(TestDescriptionExcel, ActualResult, strResult, System.getProperty("ScreenshotNameWithFilePath"), "");
	}
	else	// ONLY EXCEL REPORT WILL BE UPDATED
	{										
		reports.WriteToExcelRpt(TestDescriptionExcel, ActualResult, strResult, "NA", "");	//Screen shot is captured at page level inside word doc report
	}
	System.out.println("Step Result: "+ActualResult);
	return ActualResult;
}

/******************************************************************************
 * 	Method Name: clickOnElement1 --> To Click in the field By Element. Simple click if element is displayed and enabled.
 * 	Parameters -1. driver is set at baseTestDriver --> refers to the AndroidDriver of application under test[Mobile App] based on the desired capabilities set at openApp.
 * 				2. Element 	--> By Element[xpath or id] as defined in the shopClues page
 *  Created On:	10 May 2020
 *  Created By: Murugan
 *  Modification History with Dates/By:
 *  1.	
 *  2.
 *  3. 
 *****************************************************************************/	


public String clickOnElement1(AndroidDriver driver, By Element)
{
	String msg = null;
	WebDriverWait wait20 = new WebDriverWait(driver, 20);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	wait20.until(ExpectedConditions.visibilityOfElementLocated((By) Element));
	
	try
	{		
		if(driver.findElements((By) Element).size()>0)
		{
			int TotEle = driver.findElements((By) Element).size();
			for(int i=0;i<TotEle;i++)
			{
				if(driver.findElement((By) Element).isDisplayed() & driver.findElement((By) Element).isEnabled())
				{
					driver.findElement((By) Element).click();					
					msg = "PASS.Clicked on the element -"+Element;
					break;					
				}
			}

		}
	}
	catch(Exception e)
	{
		msg="FAIL.Exception while clicking the element"+Element+" is -"+e.getMessage();		
	}
	
	return msg;
	
}

/******************************************************************************
 * 	Method Name: clickOnElement--> To Click in the field By CurrentElement, Call excel result generator. Update Word document report if WordDocReport is true 
 * 	Parameters -1. AndroidDriver driver --> driver is set at baseTestDriver --> refers to the AndroidDriver of application under test[Mobile App] based on the desired capabilities set at openApp.
 * 				2. String CurrentElementDesc --> String to describe and briefly define about the element under test
 * 				3. By CurrentElement 	--> By Element[xpath or id] as defined in the shopClues page
 * 				4. String ExpectedElementDesc	--> Description and brief definition of next expected element once the current element is clicked
 * 				5. By ExpectedElement		-->	By Element[xpath or id] of next expected element as defined in the shopClues page
 * 				6. Boolean WordDocReport	--> true to update Word document report with the result for the current step output. false - if word doc update is not needed.
 * 												Excel report will be updated irrespective of the flag set by WordDocReport
 *  Created On:	8 May 2020
 *  Created By: Murugan
 *  Modification History with Dates/By:
 *  1.		By Murugan: 10th May 2020: Similar of EnterText1. But Integrated Excel and Word doc report to reduce the number of lines of code in the main program and increase readability
 *  2.
 *  3. 
 *****************************************************************************/	

public String clickOnElement(AndroidDriver driver, String CurrentElementDesc, By CurrentElement, String ExpectedElementDesc, By ExpectedElement, Boolean WordDocReport) throws Exception
{
	WebDriverWait wait40 = new WebDriverWait(driver, 40);		
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	String TestDescriptionWord = "Click on "+CurrentElementDesc;
	String TestDescriptionExcel = "Click on "+CurrentElementDesc+". Make sure that "+ ExpectedElementDesc+" is displayed.";
	String strResult="NA";
	String ActualResult="";
	wait40.until(ExpectedConditions.visibilityOfElementLocated((By) CurrentElement));
	
	try
	{		
		if(driver.findElements((By) CurrentElement).size()>0)
		{
			int TotEle = driver.findElements((By) CurrentElement).size();
			
			for(int i=0;i<TotEle;i++)
			{
				if(driver.findElement((By) CurrentElement).isDisplayed() & driver.findElement((By) CurrentElement).isEnabled())
				{
					driver.findElement((By) CurrentElement).click();						
					wait40.until(ExpectedConditions.visibilityOfElementLocated((By) ExpectedElement));						
					if(driver.findElements((By) ExpectedElement).size()>0)		
					{
						ActualResult = "PASS.Clicking on <"+CurrentElementDesc+"> of the element <"+CurrentElement +"> displayed <"+ExpectedElementDesc+"> of element <"+ExpectedElement+">.";							
						strResult = "PASS";
					}
					else
					{
						ActualResult = "FAIL.Clicking on <"+CurrentElementDesc+"> of the element <"+CurrentElement +"> did not display <"+ExpectedElementDesc+"> of element <"+ExpectedElement+">.";							
						strResult = "FAIL";
					}
					break;						
				}
			}
			if(strResult.contains("NA"))
			{
				ActualResult = "FAIL.Clicking on <"+CurrentElementDesc+"> of the element <"+CurrentElement +"> failed as the element is not enabled and or displayed";
				strResult = "FAIL";
			}
		}
		else
		{
			ActualResult = "FAIL.Clicking on <"+CurrentElementDesc+"> of the element <"+CurrentElement +"> failed as the element is not available";
			strResult = "FAIL";
		}
	}
	catch(Exception e)
	{
		ActualResult = "FAIL.Clicking on <"+CurrentElementDesc+"> of the element <"+CurrentElement +"> failed as exception encountered. The exception is" +e.getMessage();
		e.printStackTrace();
		strResult = "FAIL";			
	}	
		
	
	if(WordDocReport)	// BOTH WORD AND EXCEL REPORT WILL BE UPDATED
	{
		reports.reportstep(TestDescriptionWord, ActualResult, strResult, date3,driver);
		reports.WriteToExcelRpt(TestDescriptionExcel, ActualResult, strResult, System.getProperty("ScreenshotNameWithFilePath"), "");
	}
	else	// ONLY EXCEL REPORT WILL BE UPDATED
	{										
		reports.WriteToExcelRpt(TestDescriptionExcel, ActualResult, strResult, "NA", "");	//Screen shot is captured at page level inside word doc report
	}
	System.out.println("Step Result: "+ActualResult);
	return ActualResult;
}

/******************************************************************************
 * 	Method Name: getTextFromElement--> To read attached text of the element 
 * 	Parameters -1. AndroidDriver driver --> driver is set at baseTestDriver --> refers to the AndroidDriver of application under test[Mobile App] based on the desired capabilities set at openApp.
 * 				2. By Element 	--> By Element[xpath or id] as defined in the shopClues page
 * 												
 *  Created On:	7 May 2020
 *  Created By: Murugan
 *  Modification History with Dates/By:
 *  1.		
 *  2.
 *  3. 
 *****************************************************************************/	


public String getTextFromElement(AndroidDriver driver, By Element)
{
	String msg = null;
	WebDriverWait wait20 = new WebDriverWait(driver, 20);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	
	wait20.until(ExpectedConditions.visibilityOfElementLocated((By) Element));	
	try
	{		
		if(driver.findElements((By) Element).size()>0)
		{
			int TotEle = driver.findElements((By) Element).size();
			for(int i=0;i<TotEle;i++)
			{
				if(driver.findElement((By) Element).isDisplayed())
				{
					msg =driver.findElement((By) Element).getText();					
					break;					
				}
			}

		}
	}
	catch(Exception e)
	{
		msg="FAIL.Exception while clicking the element"+Element+" is -"+e.getMessage();		
	}	
	return msg;	
}



}

