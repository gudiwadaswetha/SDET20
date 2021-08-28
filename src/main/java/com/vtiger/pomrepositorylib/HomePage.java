package com.vtiger.pomrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.genericutility.WebDriverUtility;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy (linkText = "Contacts")
	private WebElement contactsLink;
	
	@FindBy (linkText = "Organizations")
	private WebElement organizationLink;
	
	@FindBy (xpath = "//img[contains(@src,'images/user')]")
	private WebElement administratorImg;
	
	@FindBy (linkText = "Sign Out")
	private WebElement signoutLink;
	
	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	public WebElement getAdministratorImg() {
		return administratorImg;
	}

	public WebElement getSignoutLink() {
		return signoutLink;
	}
	
//	Action method to sign out from the application
	public void signOut() {
		WebDriverUtility wdu = new WebDriverUtility();
		wdu.mouseHover(driver, administratorImg);
//		Actions actions = new Actions(driver);
//		actions.moveToElement(administratorImg).perform();
		signoutLink.click();
	}
}
