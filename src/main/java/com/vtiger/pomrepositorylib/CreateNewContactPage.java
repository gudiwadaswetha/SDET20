package com.vtiger.pomrepositorylib;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.genericutility.WebDriverUtility;

public class CreateNewContactPage {
	WebDriver driver;
	public CreateNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (name = "lastname")
	private WebElement lastNameField;
	
	@FindBy (xpath = "//input[@name='contact_id']/following-sibling::img[@title='Select']")
	private WebElement selectReportsTo;
	
	@FindBy (xpath = "//input[@name='contact_id']/following-sibling::input[@title='Clear']")
	private WebElement clearReportsTo; 
	
	@FindBy (xpath = "//input[contains(@value,'Save')]")
	private WebElement saveButton;
	
	@FindBy (xpath = "//input[@name = 'account_name']/following-sibling :: img[@title = 'Select']")
	private WebElement selectOrgnanizationName;
	
	@FindBy (name = "leadsource")
	private WebElement leadSourceDropDown;
	
	@FindBy (name = "imagename")
	private WebElement contactImageButton;
	
	public WebElement getLastNameField() {
		return lastNameField;
	}

	public WebElement getSelectReportsTo() {
		return selectReportsTo;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getSelectOrgnanizationName() {
		return selectOrgnanizationName;
	}

	public WebElement getContactImageButton() {
		return contactImageButton;
	}
	
	public WebElement getClearReportsTo() {
		return clearReportsTo;
	}
	
	public void createContactWithMandatory(String lastName) {
		lastNameField.sendKeys(lastName);
		saveButton.click();
	}

	/* Action method to save the contact entering mandatory fields and selecting reportsTo*/
	public void createContactWithReportTo(String lastName, String reportsTo, String parentUrl, String childUrl) throws Throwable {
		lastNameField.sendKeys(lastName);
		/*selecting the reportsTo option by clicking on "+" select option under reports to*/
		selectReportsTo.click();
//		List<String> widList = new ArrayList<String>(driver.getWindowHandles());
//		driver.switchTo().window(widList.get(1));
		WebDriverUtility webLib = new WebDriverUtility();
		webLib.switchToWindowOnUrl(driver, childUrl);
		ContactListPage clp = new ContactListPage(driver);
		clp.clickOnContactListItem(reportsTo);
//		driver.switchTo().window(widList.get(0));
		webLib.switchToWindowOnUrl(driver, parentUrl);
	}
	
	/* Action method to save the contact entering mandatory fields with image upload by clicking on Choose File*/
	public void createContactWithImage(String lastName, String imagePath) {
		lastNameField.sendKeys(lastName);
		/*clicking on contact image choose file button and loading the image */
		contactImageButton.sendKeys(imagePath);
		saveButton.click();
	}
	
	/* Action method to save the contact entering mandatory fields and selecting organization Name*/
	public void createContactWithOrg(String lastName, String orgName) {
		lastNameField.sendKeys(lastName);
		/*selecting the organization by clicking on "+" select option under Orgnaization Name*/
		selectOrgnanizationName.click();
		List<String> widList = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(widList.get(1));
		
		OrganizationListPage olp = new OrganizationListPage(driver);
		olp.clickOnOrgListItem(orgName);
		driver.switchTo().window(widList.get(0));
		saveButton.click();
	}
	
	public void createContactWithLead (String lastName, String lead) {
		lastNameField.sendKeys(lastName);
		WebDriverUtility wLib = new WebDriverUtility();
		wLib.select(leadSourceDropDown, lead);
		saveButton.click();
	}
}
