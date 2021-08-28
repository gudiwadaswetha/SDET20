package com.vtiger.genericutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.vtiger.pomrepositorylib.HomePage;
import com.vtiger.pomrepositorylib.LoginPage;

public class BaseClass {

	public WebDriver driver;
	public static WebDriver sDriver;
	/* Object creation for libraries */
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	
//	@Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	public void configBC() throws Throwable {
		
		/*Getting the data from the properties file */
		String BROWSER = fLib.getPropertyKeyValue("browser");
		
		/*Initializing the web driver */
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		sDriver = driver;
		wLib.maximizeWindow(driver);
		wLib.waitUntilPageLoad(driver);
		
	}
	
	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	public void configBM() throws Throwable {
		/*Getting the data from the properties file */
		String USERNAME = fLib.getPropertyKeyValue("username");
		String PASSWORD = fLib.getPropertyKeyValue("password");
		String URL = fLib.getPropertyKeyValue("url");
		
		driver.get(URL);

		/*Login to the application */
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(USERNAME, PASSWORD);
	}
	
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void configAM() {
		HomePage homePage = new HomePage(driver);
		homePage.signOut();
	}
	
	@AfterClass(groups = {"smokeTest","regressionTest"})
	public void configAC() {
		driver.quit();
	}
}
