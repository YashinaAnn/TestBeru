package com.pack.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Page {
	
	// Authorization locators
	private By signInBtn = By.cssSelector("div.header2-nav__user");
	private By loginBy = By.cssSelector("div.header2-user-menu__email");	
	
	// Change city locators
	private By newCityLink = By.xpath("//span[text()='Регион: ']//span[@class='link__inner']");	
	// New city form
	private By inputCityBy = By.cssSelector("form.region-select-form input");
	private By submitCityBy = By.cssSelector("form.region-select-form button");
	private By cityListBy = By.className("region-suggest__list-item");
	// Settings 
	private By settingsBy = By.className("header2-user-menu__item_type_settings");

	public HomePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	

	// Click on sign in button.
	public SignInPage signInClick() {
		click(signInBtn);
		return new SignInPage(driver, wait);  
	}
	
	// Get text of sign in button.
	public String getUserBtnText() {
		return getText(signInBtn);
	}
	
	// Get user login after authorization.
	public String getUserLogin() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(signInBtn)).perform();
		return this.getText(loginBy);
	}
	
	// Change city.
	public void changeCity(String city) {
		this.click(newCityLink);		
		WebElement element = driver.findElement(inputCityBy);
		element.sendKeys(city);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cityListBy));
		element.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		driver.findElement(submitCityBy).submit();
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	// Go to settings page
	public SettingsPage changeSettings() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(signInBtn));
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(signInBtn)).perform();	
		click(settingsBy);
		
		return new SettingsPage(driver, wait);
	}

	// Get current city.
	public String getCity() {
		return this.getText(newCityLink);
	}
	
}
