package com.wordpress.TestCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wordpress.Utilities.readConfig;
import com.wordpress.PageObjects.LoginPage;

public class TC001_LoginTest extends BaseClass {
	
	readConfig readConfig = new readConfig();
	private String title = readConfig.getLoginTitle();

	@Test
	public void loginTest() {
		LoginPage lp = new LoginPage(driver);
		logger.info("set username");
		lp.setUsername(username);
		logger.info("Click Continue");
		lp.clickContinue();
		logger.info("set password");
		lp.setPassword(password);
		logger.info("Click Login");
		lp.clickLogin();
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(lp.logoutButton));
		
		//validate login
		logger.info("Validate page title...");
		String pgTitle = driver.getTitle();
		
		if(pgTitle.contains(title)) {
			Assert.assertTrue(true);
			logger.info("Login successful!");
		}else {
			Assert.assertTrue(false);
			logger.info("Login failed!");
		}
		
	}
}
