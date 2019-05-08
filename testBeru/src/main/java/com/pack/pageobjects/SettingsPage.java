package com.pack.pageobjects;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.qameta.allure.Step;


public class SettingsPage extends Page {
	
	private By city = By.xpath("//h2[contains(text(),'Ваш город ')]/span/span");
	
	public SettingsPage(EventFiringWebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	public String getCity() {
		scrollToElement(city);
		String currentCity = getText(city);
		return currentCity;
	}
	
	public void goToHomePage() {
		driver.close();
		ArrayList<String> windows = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(windows.get(0));
	}
	
	@Step("Check that the city on the settings page matches the city on the home page")
	public void checkCityMatch(String city) {			
		Assert.assertEquals(getCity().trim(), city, "Cities do not match");
	}	
}