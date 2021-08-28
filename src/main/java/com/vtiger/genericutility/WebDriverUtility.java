package com.vtiger.genericutility;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class contains webdriver specific generic methods
 * @author USM
 *
 */
public class WebDriverUtility {
	/**
	 * This method is used to maximize the browser window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	/**
	 * This method waits for 15seconds for page loading 
	 * @param driver
	 */
	public void waitUntilPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	/**
	 * This method waits for the element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementVisibility(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * * This method waits for the element to be clicked, it's a custom wait created to avoid elemenInterAceptable Exception
	 * @param driver
	 * @param element
	 * @throws InterruptedException
	 */
	public void waitAndClick(WebDriver driver, WebElement element) throws InterruptedException {
		int count = 0;
		while (count<20) {
			try {
				element.click();
				break;
			} catch (Throwable e) {
				Thread.sleep(1000);
				count++;
			}
		}
	}
	
	/**
	 * This method will perform the mouse hover action
	 * @param driver
	 * @param element
	 */
	public void mouseHover(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	
	/**
	 * This method helps to switch from one window to another window
	 * @param driver
	 * @param partialWindTitle
	 */
	public void switchToWindow(WebDriver driver, String partialWindTitle) {
		Set<String> windowIds = driver.getWindowHandles();
		Iterator<String> it = windowIds.iterator();
		while (it.hasNext()) {
			String windId = it.next();
			String title = driver.switchTo().window(windId).getTitle();
			System.out.println("Title: "+title);
			if (title.contains(partialWindTitle)) {
				break;
			} 
		}
	}
	
	/**
	 * This method helps to switch from one window to another window using url
	 * @param driver
	 * @param url
	 */
	public void switchToWindowOnUrl(WebDriver driver, String url) {
		Set<String> windowIds = driver.getWindowHandles();
		Iterator<String> it = windowIds.iterator();
		while (it.hasNext()) {
			String windId = it.next();
			String currentUrl = driver.switchTo().window(windId).getCurrentUrl();
			System.out.println("url: "+currentUrl);
			if (currentUrl.contains(url)) {
				break;
			} 
		}
	}
	
	/**
	 * This method is used to accept the alert
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method is used to cancel the alert
	 * @param driver
	 */
	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method enables the user to handle the dropdown using visible text
	 * @param element
	 * @param option
	 */
	public void select (WebElement element, String option) {
		Select select = new Select(element);
		select.selectByVisibleText(option);
	}
	
	/**
	 * This method enables the user to handle the dropdown using index
	 * @param element
	 * @param index
	 */
	public void select (WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}
}
