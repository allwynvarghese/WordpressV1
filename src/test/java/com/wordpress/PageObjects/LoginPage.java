package com.wordpress.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/*** Page objects ***/
	
	//username textbox
	@FindBy(xpath="//input[@name='usernameOrEmail']")
	@CacheLookup
	WebElement username;
	
	//continue/login button
	@FindBy(xpath="//button[@type='submit']")
	@CacheLookup
	WebElement continueButton;
	
	//password textbox
	@FindBy(xpath="//input[@type='password']")
	@CacheLookup
	WebElement password;
	
	//logout button
	@FindBy(xpath="//button[@title='Log out of WordPress.com']")
	@CacheLookup
	public WebElement logoutButton;
	
	/*** Methods ***/
	
	//set username
	public void setUsername(String username) {
		this.username.click();
		this.username.sendKeys(username);
	}
	
	//click on the continue button
	public void clickContinue() {
		continueButton.click();
	}
	
	//set password
	public void setPassword(String password) {
		this.password.click();
		this.password.sendKeys(password);
	}
	
	//click on login button
	public void clickLogin() {
		continueButton.click();
	}

	

}
