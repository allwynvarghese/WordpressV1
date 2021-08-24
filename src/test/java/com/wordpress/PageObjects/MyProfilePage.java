package com.wordpress.PageObjects;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyProfilePage {
	
	WebDriver driver;
	WebDriverWait wait;

	public MyProfilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*** Page objects ***/
	
	//My profile link on side bar
	@FindBy(xpath="//span[@data-e2e-sidebar='My Profile']")
	@CacheLookup
	WebElement myProfileLink;
	
	//Change photo
	@FindBy(xpath="//span[@class='edit-gravatar__label']")
	@CacheLookup
	WebElement editPhoto;
	
	//First name text box
	@FindBy(id="first_name")
	@CacheLookup
	public WebElement firstName;
	
	//Last name text box
	@FindBy(id="last_name")
	@CacheLookup
	WebElement lastName;
	
	//Public display name text box
	@FindBy(id="display_name")
	@CacheLookup
	WebElement publicDisplayName;
	
	//About me text area
	@FindBy(id="description")
	@CacheLookup
	WebElement aboutMe;
		
	//Gravatar profile toggle
	@FindBy(xpath="//input[contains(@id,'inspector-toggle-control')]/parent::span")
	@CacheLookup
	WebElement gravatarToggle;
	
	//Save button
	@FindBy(xpath="//button[@type='submit']")
	@CacheLookup
	WebElement saveButton;
	
	//edit photo submit
	@FindBy(xpath="//button[@data-e2e-button='done']")
	@CacheLookup
	WebElement editPhotoSubmit;
	
	//Setting saved notice dismiss button
	@FindBy(xpath="//button[@class='notice__dismiss']")
	@CacheLookup
	public WebElement noticeDismissButton;
	
	/*** Methods ***/
	
	//click on My profile link side bar
	public void clickMyProfileLink() {
		myProfileLink.click();
	}
	
	//Enter first name
	public void enterFirstName(String firstName) {
		this.firstName.click();
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
	}
	
	//Enter last name
	public void enterLastName(String lastName) {
		this.lastName.click();
		this.lastName.clear();
		this.lastName.sendKeys(lastName);
	}
	
	//Enter public display name
	public void enterPublicDisplayName(String publicDisplayName) {
		this.publicDisplayName.click();
		this.publicDisplayName.clear();
		this.publicDisplayName.sendKeys(publicDisplayName);
	}
	
	//Enter About me
	public void enterAboutMe(String aboutMe) {
		this.aboutMe.click();
		this.aboutMe.clear();
		this.aboutMe.sendKeys(aboutMe);
	}
	
	//hide gravatar profile
	public void showGravatarProfile(String state) {
		
		//show gravatarr 
		String onOff = gravatarToggle.getAttribute("class");
		if(onOff.contains("is-checked") && state.equalsIgnoreCase("No")) {
			gravatarToggle.click();
		}else if(!(onOff.contains("is-checked")) && state.equalsIgnoreCase("Yes")) {
			gravatarToggle.click();
		}
	}
	
	//Click on the Save button
	public void clickSave() throws InterruptedException {
		saveButton.click();
		Thread.sleep(10000);
		
	}
	
	//change photo
	public void changePhoto(String changePhoto, String photoPath) throws InterruptedException {
		
		if(changePhoto.equalsIgnoreCase("yes") && !(photoPath.isEmpty())) {
			editPhoto.click();
			Thread.sleep(5000);
			uploadFile(photoPath);
			Thread.sleep(5000);
			editPhotoSubmit.click();
			Thread.sleep(5000);
			
			wait = new WebDriverWait(driver, 150);
			wait.until(ExpectedConditions.elementToBeClickable(noticeDismissButton));
			noticeDismissButton.click();
		}
	}
	
	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	
	public static void uploadFile(String fileLocation) {
        try {
        	//Setting clipboard with file location
            setClipboardData(fileLocation);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();
	
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
    }
	
	/*** Validation methods ***/
	
	//validate first name
	public String validateFirstName() {
		firstName.click();
		String val_FirstName = firstName.getAttribute("value");
		return val_FirstName;
	}
	
	//validate last name
	public String validateLastName() {
		lastName.click();
		String val_LastName = lastName.getAttribute("value");
		return val_LastName;
	}
	
	//validate display name
	public String validateDisplayName() {
		publicDisplayName.click();
		String val_DisplayName = publicDisplayName.getAttribute("value");
		return val_DisplayName;
	}
	
	//validate about me
	public String validateaboutMe() {
		aboutMe.click();
		String val_AboutMe = aboutMe.getText();
		return val_AboutMe;
	}
}
