package com.pack.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.pack.utils.*;

@Listeners(TestListener.class)
public class BaseTest {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	@BeforeMethod
	public void setUpDriver() {
		System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().window().maximize();	
		wait = new WebDriverWait(driver, 10);
		driver.get(Constants.START_URL);
    }
	
	@AfterMethod
	public void teardown () {
		driver.quit();
	}
	
	public WebDriver getDriver() {
		return driver;		
	}
}
