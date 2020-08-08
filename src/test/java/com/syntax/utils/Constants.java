package com.syntax.utils;

public class Constants { 
	//first we are creating constants for the path for chrome and gecko driver,and path for configuration file
	//public static final String CHROME_DRIVER_PATH=System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe";
	//public static final String GECKO_DRIVER_PATH=System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\geckodriver.exe";
	
	// here is for WINDOWS USER
	
	public static final String CONFIGURATION_FILEPATH=System.getProperty("user.dir")+"\\src\\test\\resources\\configs\\configs.properties"; 
	
	public static final String TEST_FILEPATH =System.getProperty("user.dir")+ "\\src\\test\\resources\\testData\\Book2.xlsx";
	public static final String REPORT_FILEPATH=System.getProperty("user.dir")+"\\target\\html-report\\HRMS.html";
	
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int EXPLICIT_WAIT_TIME=30;    
	
	public static final String SCREENSHOT_FILEPATH=System.getProperty("user.dir")+"\\screenshot";
	public static final String USER_NAME=System.getProperty("user.name");
	
	
	//here is FOR MAC USERS:
	
//	public static final String CHROME_DRIVER_PATH = System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver";
//	public static final String GECKO_DRIVER_PATH = System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver";
//	public static final String CONFIGURATION_FILEPATH = System.getProperty("user.dir")+ "/src/test/resources/configs/configs.properties";
//	public static final String TESTDATA_FILEPATH = System.getProperty("user.dir")+ "/src/test/resources/testdata/testDataHRM.xlsx";
//	public static final int IMPLICIT_WAIT_TIME = 15;
//	public static final int EXPLICIT_WAIT_TIME = 20;
//	public static final String REPORT_FILEPATH = System.getProperty("user.dir")+"/target/html-report/HRMS.html";
//	public static final String SCREENSHOT_FILEPATH=System.getProperty("user.dir")+"/screenshots/";
	

}
