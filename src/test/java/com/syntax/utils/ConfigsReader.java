package com.syntax.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigsReader {  
	
	 public static Properties prop;
	 /**
		 * This method will read properties
		 * @param key
		 * @return
		 */
	//what we need for reading configuration file ? 1. we need fileInputStream with filePath 
	// and we pass filePath in parameters so later it can be updated by user
	public static void readProperties(String filePath) {  
		try {
			FileInputStream fis=new FileInputStream(filePath); 
			prop=new Properties();
			prop.load(fis);
			fis.close();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace(); 
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	} 
	/** 
	 * this method will return value of specified key
	 * @param String key
	 * @return String value
	 */
	
	
  public static String getProperty(String key) {
	 return prop.getProperty(key);
  }
}
