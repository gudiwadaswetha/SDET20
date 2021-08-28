package com.vtiger.genericutility;

import java.util.Date;
import java.util.Random;

/**
 * This class contains java specific generic libraries
 * @author USM
 *
 */
public class JavaUtility {

	/**
	 * It is used to generate the integer random number within the specified boundary limit 0 to 10000
	 * @return randomNum
	 */
	public int getRandomNumber( ) {
		Random random = new Random();
		int randomNum = random.nextInt(10000);
		return randomNum;
	}
	
	/**
	 * It is used to get the system current date and time 
	 * @return systemDateAndTime
	 */
	public String getSystemDateAndTime() {
		Date date = new Date();
		String systemDateAndTime = date.toString();
		return systemDateAndTime;
	}
	
	/**
	 * It is used to get the current System date in YYYY-MM-DD format
	 * @return finalFormat
	 */
	public String getSystemDateYYYY_MM_DD() {
		Date date = new Date();
		String[] dateArr = date.toString().split(" ");
        String DD = dateArr[2];
        String YYYY = dateArr[5];   
        int MM = date.getMonth()+1;
        String finalFormat = YYYY+"-"+MM+"-"+DD;
		return finalFormat;
	}
}
