package com.pack.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import com.pack.utils.*;

@Listeners(TestListener.class)
public class BaseTest {
	
	private final String CHROME_DRIVER_PATH = "D:\\Drivers\\chromedriver.exe";
	private final String START_URL = "https://beru.ru";
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	@BeforeClass
	public void setUpDriver() {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		driver.manage().window().maximize();	
		wait = new WebDriverWait(driver, 10);
		driver.get(START_URL);
    }
    
    @AfterClass
    public void teardown () {
        driver.quit();
    }

	public WebDriver getDriver() {
		return driver;
	}
}
