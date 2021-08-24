package com.wordpress.TestCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import com.wordpress.Utilities.readConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	readConfig readConfig =new readConfig();
	
	public String url = readConfig.getUrl();
	public String username = readConfig.getUsername();
	public String password = readConfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	public WebDriverWait wait;
	
	@BeforeClass
	public void setup() {
		
		logger = Logger.getLogger("Wordpress");
		PropertyConfigurator.configure("log4j.properties");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		logger.info("Opening url in Chrome");
		
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		logger.info("---Start---");
		
	}
	
	/*** Capture screenshot on test failure ***/
	@AfterMethod
	public void captureFailureScreenshot(ITestResult result) {
		
		if(result.getStatus() == ITestResult.FAILURE) {
			capture(driver, result.getName());
		}
	}
	
	@AfterClass
	public void teardown() {
		
		/*** Quit the browser ***/
		driver.quit();
		logger.info("---End---");
	}
	
	/*** Capture screenshot ***/
	public void capture(WebDriver driver, String className) {
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"//Screenshot//"+className+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("The error is - " + e.getMessage());
		}
	}
	
}
