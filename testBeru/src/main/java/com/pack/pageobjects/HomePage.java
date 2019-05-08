package com.pack.pageobjects;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.pack.utils.Constants;
import io.qameta.allure.Step;


public class HomePage extends Page {
	
	// Authorization locators
	private By signInBtn = By.cssSelector("div.header2-nav__user");
	private By loginBy = By.cssSelector("div.header2-user-menu__email");	
	private By logoutBy = By.xpath("//a[text()='Выход']");
	// Change city locators
	private By newCityLink = By.xpath("//span[text()='Регион: ']//span[@class='link__inner']");	
	// New city form
	private By inputCityBy = By.cssSelector("form.region-select-form input");
	private By submitCityBy = By.cssSelector("form.region-select-form button");
	private By cityListBy = By.className("region-suggest__list-item");
	// Settings 
	private By settingsBy = By.xpath("//a[text()='Настройки']");

	public HomePage(EventFiringWebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	// Click on sign in button.
	public SignInPage goToSignIn() {
		click(signInBtn);
		return new SignInPage(driver, wait);  
	}
	
	public void logout() {
		if (this.getUserBtnText().trim().equals(Constants.BUTTON_TEXT)) {
			Actions action = new Actions(driver);
			action.moveToElement(findAndHighLight(signInBtn)).perform();
			click(logoutBy);
		}
	}
	
	// Get text of sign in button.
	public String getUserBtnText() {
		return getText(signInBtn);
	}
	
	// Get user login after authorization.
	public String getUserLogin() {
		Actions action = new Actions(driver);
		action.moveToElement(find(signInBtn)).perform();
		return getText(loginBy);
	}
	
	// Change city.
	public void changeCity(String city) {
		click(newCityLink);		
		WebElement element = find(inputCityBy);
		enterText(element, city);
			
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cityListBy));
		element.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		submit(submitCityBy);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	// Get current city.
	public String getCity() {
		return getText(newCityLink);
	}
		
	// Go to settings page
	public SettingsPage changeSettings() {
		Actions action = new Actions(driver);
		action.moveToElement(findAndHighLight(signInBtn)).perform();	
		click(settingsBy);	
			
		ArrayList<String> windows = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(windows.get(1));
		return new SettingsPage(driver, wait);
	}
	
	
	
	@Step("Login")
	public void login() {
		SignInPage signInPage = goToSignIn();
		signInPage.signIn(Constants.LOGIN, Constants.PASSWORD);	
	}
	

	@Step("Check that the button text after authorization is " + Constants.BUTTON_TEXT)
	public void checkSignInButtonText() {
		Assert.assertEquals(getUserBtnText().trim(), 
				Constants.BUTTON_TEXT, "Text of sign in button doesn't correct");
	}
	
	@Step("Check login after authorization")
	public void checkLoginText() {
		Assert.assertEquals(getUserLogin().trim(), 
				Constants.LOGIN, "User login doesn't correct");	
	}
	
	
	@Step("Change city and check that it is changed on the home page")
	public void checkCityChanged(String city) {
		changeCity(city);
		Assert.assertEquals(getCity().trim(), city, "City doesn't correct");	
	}
	
}
