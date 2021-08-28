package com.vtiger.pomrepositorylib;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactListPage {
	WebDriver driver;
	public ContactListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//a[contains(@onclick,'set_return_contact_address')]")
	private List<WebElement> contactsList;
	
	public List<WebElement> getContactsList() {
		return contactsList;
	}
	
	@FindBy (xpath = "//td[@class='moduleName']")
	private WebElement contactsTitle;

	public WebElement getContactsTitle() {
		return contactsTitle;
	}

	public void clickOnContactListItem(String contactName) {
		for (WebElement contact : contactsList) {
			if (contact.getText().equalsIgnoreCase(contactName)) {
				System.out.println(contact.getText());
				contact.click();
				break;
			}
		}
	}
}
