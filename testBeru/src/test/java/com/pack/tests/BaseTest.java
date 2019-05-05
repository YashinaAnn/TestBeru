package com.pack.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.pack.pageobjects.HomePage;
import com.pack.utils.*;
import com.pack.utils.listeners.EventListener;
import com.pack.utils.listeners.TestListener;

@Listeners({TestListener.class})
public class BaseTest {
	
	protected EventFiringWebDriver driver;
	protected WebDriverWait wait;
	
	@BeforeMethod
	public void setUpDriver() {
		System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
		driver = new EventFiringWebDriver(new ChromeDriver());
		driver.register(new EventListener());
		driver.manage().window().maximize();	
		wait = new WebDriverWait(driver, 10);
		driver.get(Constants.START_URL);
    }
	
	@AfterMethod
	public void teardown () {
		if (driver.getCurrentUrl().contains(Constants.START_URL)) {
			new HomePage(driver, wait).logout();
		}
		driver.quit();
	}
	
	public EventFiringWebDriver getDriver() {
		return driver;		
	}
}
