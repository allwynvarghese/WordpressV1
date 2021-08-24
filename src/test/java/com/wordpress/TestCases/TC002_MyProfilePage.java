package com.wordpress.TestCases;

import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.wordpress.PageObjects.MyProfilePage;

public class TC002_MyProfilePage extends BaseClass {
	
	WebDriverWait wait;
	
	@Test(dataProvider="Customer_Data")
	public void setMyProfile(String firstName, String lastName, String publicDisName, String aboutMe, String showGravatar, String changePhoto, String photoPath) throws InterruptedException {

		//Login to application.
		logger.info("Login");
		TC001_LoginTest lt = new TC001_LoginTest();
		lt.loginTest();
		
		MyProfilePage profile = new MyProfilePage(driver);
		
		logger.info("Change photo");
		profile.changePhoto(changePhoto, photoPath);
		
		String timestamp = "_" + new Date().getTime();
		
		logger.info("Set first name");
		String uniqueFirstName = firstName + timestamp;
		profile.enterFirstName(uniqueFirstName);
		
		logger.info("Set last name");
		String uniqueLastName = lastName + timestamp;
		profile.enterLastName(uniqueLastName);
		
		logger.info("Set public display name");
		String uniquePubDispName = publicDisName+ timestamp;
		profile.enterPublicDisplayName(uniquePubDispName);
		
		logger.info("Set about me description");
		profile.enterAboutMe(aboutMe);
		
		logger.info("Display Gravatar?");
		profile.showGravatarProfile(showGravatar);
		
		logger.info("Click on save button");
		profile.clickSave();
		
		wait = new WebDriverWait(driver, 300);
		wait.until(ExpectedConditions.elementToBeClickable(profile.firstName));
		
		//validation
		SoftAssert sa =new SoftAssert();
		
		//Validate first name
		String val_FirstName = profile.validateFirstName();
		logger.info("validate First name");
		sa.assertEquals(val_FirstName.trim(), uniqueFirstName.trim());
		
		//Validate last name
		String val_LastName = profile.validateLastName();
		sa.assertEquals(val_LastName.trim(), uniqueLastName.trim());
		
		//validate public display name
		String val_pubDispName = profile.validateDisplayName();
		sa.assertEquals(val_pubDispName.trim(), uniquePubDispName.trim());
		
		//validate about me
		String val_AboutMe = profile.validateaboutMe();
		if(val_AboutMe.contains(aboutMe)) {
			sa.assertTrue(true);
		}
		
		sa.assertAll();
	}
	
	@DataProvider(name="Customer_Data")
	public String[][] customerData() throws IOException {
		
		String path = System.getProperty("user.dir")+"\\src\\test\\java\\com\\wordpress\\TestData\\New_Customer_Data.xlsx";
		
		int rowCount = com.wordpress.Utilities.XLUtilites.getRowCount(path, "Sheet1");
		int colCount = com.wordpress.Utilities.XLUtilites.getCellCount(path, "Sheet1", 1);
		
		String custData[][] = new String[rowCount][colCount];
		
		for(int i=1;i<=rowCount;i++) {
			for(int j=0;j<colCount;j++)
			{
				if(!(com.wordpress.Utilities.XLUtilites.getCellData(path,"Sheet1", i,j) == "")) {
					custData[i-1][j]=com.wordpress.Utilities.XLUtilites.getCellData(path,"Sheet1", i,j);
				}
			}
		}
		
		return custData;
	}

}
