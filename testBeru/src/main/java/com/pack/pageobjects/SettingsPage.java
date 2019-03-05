package com.pack.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SettingsPage extends Page {
	
	private By city = By.xpath("//h2[text()='Ваш город ']/span/span");
	
	public SettingsPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	public String getCity() {
		this.scrollToElement(city);
		return this.getText(city);
	}
}