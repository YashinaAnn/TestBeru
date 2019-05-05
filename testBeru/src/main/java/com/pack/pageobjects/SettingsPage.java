package com.pack.pageobjects;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SettingsPage extends Page {
	
	private By city = By.xpath("//h2[contains(text(),'Ваш город ')]/span/span");
	
	public SettingsPage(EventFiringWebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	public String getCity() {
		scrollToElement(city);
		String currentCity = getText(city);
		driver.close();
		ArrayList<String> windows = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(windows.get(0));
		return currentCity;
	}
}