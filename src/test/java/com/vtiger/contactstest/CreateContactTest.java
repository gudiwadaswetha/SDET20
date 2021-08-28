package com.vtiger.contactstest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.vtiger.genericutility.BaseClass;
import com.vtiger.pomrepositorylib.ContactInfoPage;
import com.vtiger.pomrepositorylib.ContactsPage;
import com.vtiger.pomrepositorylib.CreateNewContactPage;
import com.vtiger.pomrepositorylib.HomePage;

@Listeners(com.vtiger.genericutility.ListenerImpClass.class)
public class CreateContactTest extends BaseClass{
	
	@Test(groups = {"smokeTest"})
	public void createContactWithReportsToTest() throws Throwable {
		/* Redirecting to contacts module by clicking on contacts link */
		HomePage homePage = new HomePage(driver);
		homePage.getContactsLink().click();
		
		/*Redirecting to create new contact page by clicking on "+" create contact */
		ContactsPage contactsPage = new ContactsPage(driver);
		contactsPage.getCreateContactIcon().click();
		
		/*Getting the test script data from the excel sheet*/
		String lastName = eLib.getDataFromExcel("Sheet1", 1, 2);
		String reportsTo = eLib.getDataFromExcel("Sheet1", 1, 3);
		String parentUrl = eLib.getDataFromExcel("Sheet1", 1, 5);
		String childUrl = eLib.getDataFromExcel("Sheet1", 1, 6);
		
		/* Creating contact with mandatory fields */
		CreateNewContactPage createContactPage = new CreateNewContactPage(driver);
		createContactPage.createContactWithMandatory(lastName);
		/*Verifying whether contact is created or not */
		ContactInfoPage infoPage = new ContactInfoPage(driver);
		String actSuccessText = infoPage.getActualSuccessText().getText();
		
		boolean contactStatus = actSuccessText.contains(lastName);
		Assert.assertTrue(contactStatus);
//		if (actSuccessText.contains(lastName)) {
//			System.out.println("Contact is created");
//		} else
//			System.out.println("Contact is not created");
				
		/*Creating the contact with reportsTo */
		contactsPage.getCreateContactIcon().click();
		createContactPage.createContactWithReportTo(lastName, reportsTo, parentUrl, childUrl);
		createContactPage.getSaveButton().click();
	
		/*Verifying whether selected reportsTo is selected and success message is displayed */
		String actReportsTo = infoPage.getReportsToContact().getText().trim();
		actSuccessText = infoPage.getActualSuccessText().getText();
		
		contactStatus = actSuccessText.contains(lastName);
		Assert.assertEquals(actReportsTo, reportsTo);
		Assert.assertTrue(contactStatus);
//		if (actReportsTo.equals(reportsTo) && actSuccessText.contains(lastName)) {
//			System.out.println("Contact is created and Information displayed is correct and updated");
//		} else
//			System.out.println("Contact Information displayed is incorrect and not updated");
	}
	
	@Test(groups = {"regressionTest"})
	public void createContactWithoutMandatoryTest() throws Throwable {
		/* Redirecting to contacts module by clicking on contacts link */
		HomePage homePage = new HomePage(driver);
		homePage.getContactsLink().click();
		
		/*Redirecting to create new contact page by clicking on "+" create contact */
		ContactsPage contactsPage = new ContactsPage(driver);
		contactsPage.getCreateContactIcon().click();
		
		/*Creating the contact without mandatory fields */
		CreateNewContactPage createContact = new CreateNewContactPage(driver);
		createContact.getSaveButton().click();
		
		/*Getting the test script data from the excel sheet*/
		String expAlertText = eLib.getDataFromExcel("Sheet1", 7, 4);
		
		String actualAlertText = driver.switchTo().alert().getText();
		Assert.assertEquals(actualAlertText, expAlertText);
//		if(actualAlertText.equals(expAlertText)) {
//			System.out.println("Pass : Alert text is proper");
//		} else {
//			System.out.println("Fail : Alert text is not proper");
//		}
		driver.switchTo().alert().accept();
	}
	
	@Test(groups = {"regressionTest"})
	public void createContactWithImageTest() throws Throwable {
		/* Redirecting to contacts module by clicking on contacts link */
		HomePage homePage = new HomePage(driver);
		homePage.getContactsLink().click();
		
		/*Redirecting to create new contact page by clicking on "+" create contact */
		ContactsPage contactsPage = new ContactsPage(driver);
		contactsPage.getCreateContactIcon().click();
//		String wid = driver.getWindowHandle();
		
		String lastName = eLib.getDataFromExcel("Sheet1", 10, 2);
		String imagePath = eLib.getDataFromExcel("Sheet1", 10, 3);
		
		/*Creating the contact with uploading the contact image by clicking on Choose File */
		CreateNewContactPage createContact = new CreateNewContactPage(driver);
		createContact.createContactWithImage(lastName, imagePath);
		
		/* Verifying whether selected image is present and success message is displayed */
		ContactInfoPage infoPage = new ContactInfoPage(driver);
		String actSuccessMsg = infoPage.getActualSuccessText().getText();
		boolean contactStatus = actSuccessMsg.contains(lastName);
		Assert.assertTrue(contactStatus);
		boolean imageStatus = infoPage.getContactImage().isDisplayed();
		Assert.assertTrue(imageStatus);
		
//		try {
//			if (infoPage.getContactImage().isDisplayed() && actSuccessMsg.contains(lastName)) {
//				System.out.println("Contact is created with image and Information displayed is correct");
//			}
//		} catch (Exception e) {
//			System.out.println("Contact is not created with image and Information displayed is incorrect");
//		}
	}
}
