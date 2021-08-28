package com.vtiger.genericutility;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * This class contains the methods to read Common Data from properties file
 * @author USM
 *
 */
public class FileUtility {

	/**
	 * This method is used to read the data from properties File based on Key which you pass as an argument
	 * @param key
	 * @return
	 * @throws Throwable
	 */
	public String getPropertyKeyValue (String key) throws Throwable {
		FileInputStream fis = new FileInputStream("./data/testData.properties");
		Properties propObj = new Properties();
		propObj.load(fis);
		String value = propObj.getProperty(key);
		return value;
	}
}
