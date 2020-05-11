package commonReusable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/******************************************************************************
 * 	Config.properties file is placed inside src/test/resources/testData/config.properties.
 * 	Syntax for maintaining the file --> <key>=<value> Ex. TestDataFileName=TestData.xlsx
 * 	
 *****************************************************************************/	


public class GetConfigVal {
	protected Properties prop = null;
	protected InputStream input=GetConfigVal.class.getClassLoader().getResourceAsStream("testData/config.properties");
	
	
	
	public GetConfigVal() {
		prop = new Properties();
		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/******************************************************************************
	 * 	Method Name: getAUTOMATION_NAME --> 	 Read the sAUTOMATION_NAME from the config file
	 * 	Created By: Murugan
	 * 	Created On: May 9 2020 
	 *****************************************************************************/	
	
	
	public String getAUTOMATION_NAME() {
		return prop.getProperty("sAUTOMATION_NAME");
	}
	
	/******************************************************************************
	 * 	Method Name: getReleaseName --> 	 Read the ReleaseName from the Config file
	 * 	Created By: Murugan
	 * 	Created On: May 9 2020 
	 *****************************************************************************/		
	public String getReleaseName() {

		return prop.getProperty("ReleaseName");
	}
	
	/******************************************************************************
	 * 	Method Name: getExcelFileName --> 	 Read the TestDataFileName from the Config file
	 * 	Created By: Murugan
	 * 	Created On: May 9 2020 
	 *****************************************************************************/	
	public String getExcelFileName() {

		return prop.getProperty("TestDataFileName");
	}
	
	/******************************************************************************
	 * 	Method Name: getExcelResFileName --> 	 Read the ExcelResFile from the Config file
	 * 	Created By: Murugan
	 * 	Created On: May 9 2020 
	 *****************************************************************************/	
	public String getExcelResFileName() {

		return prop.getProperty("ExcelResFile");
	}

	/******************************************************************************
	 * 	Method Name: getExcelResSheetName --> 	 Read the ExcelResSheet from the Config file
	 * 	Created By: Murugan
	 * 	Created On: May 9 2020 
	 *****************************************************************************/	
	public String getExcelResSheetName() {

		return prop.getProperty("ExcelResSheet");
	}
	
	/******************************************************************************
	 * 	Method Name:  getExcelResFolder --> 	 Read ExcelResFolder from the Config file
	 * 	Created By: Murugan
	 * 	Created On: May 9 2020 
	 *****************************************************************************/	
	public String getExcelResFolder() {

		return prop.getProperty("ExcelResFolder");
	}
	

}
