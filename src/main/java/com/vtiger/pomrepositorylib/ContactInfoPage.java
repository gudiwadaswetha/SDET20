package com.vtiger.pomrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	WebDriver driver;
	public ContactInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//span[@class='dvHeaderText']/..")
	private WebElement actualSuccessText;
	
	@FindBy (xpath = "//td[contains(@id,'mouseArea_Reports')]")
	private WebElement reportsToContact;
	
	@FindBy (xpath = "//td[contains(@id,'mouseArea_Organization')]")
	private WebElement organizationName;
	
	@FindBy (xpath = "//td[contains(@id,'mouseArea_Lead')]")
	private WebElement LeadSource;
	
	@FindBy (xpath = "//img[@title='Contact Image']")
	private WebElement contactImage;
	
	public WebElement getActualSuccessText() {
		return actualSuccessText;
	}

	public WebElement getReportsToContact() {
		return reportsToContact;
	}

	public WebElement getContactImage() {
		return contactImage;
	}

	public WebElement getOrganizationName() {
		return organizationName;
	}

	public WebElement getLeadSource() {
		return LeadSource;
	}
}
