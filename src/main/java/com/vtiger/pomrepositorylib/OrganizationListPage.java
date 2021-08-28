package com.vtiger.pomrepositorylib;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationListPage {
	WebDriver driver;
	public OrganizationListPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//a[contains(@onclick,'set_return_contact_address')]")
	private List<WebElement> organizationList;
	
	public List<WebElement> getOrganizationList() {
		return organizationList;
	}
	
	public void clickOnOrgListItem(String orgName) {
		for (WebElement org : organizationList) {
			if (org.getText().equalsIgnoreCase(orgName)) {
				System.out.println(org.getText());
				org.click();
				break;
			}
		}
	}
}
