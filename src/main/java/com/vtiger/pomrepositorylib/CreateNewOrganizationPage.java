package com.vtiger.pomrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewOrganizationPage {
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy (name = "accountname")
	private WebElement organizationNameEdt;
	
	@FindBy (xpath = "//input[@title='Save [Alt+S]']")
	private WebElement orgSaveButton;
	
	public WebElement getOrganizationNameEdt() {
		return organizationNameEdt;
	}

	public WebElement getOrgSaveButton() {
		return orgSaveButton;
	}
	
}
