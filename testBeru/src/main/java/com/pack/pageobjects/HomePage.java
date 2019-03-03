package com.pack.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Page {
	
	private By signInBtn = By.cssSelector("div.header2-nav__user");
	private By loginBy = By.cssSelector("div.header2-user-menu__email");	
	private By cancelBy = By.xpath("//div[@class='modal__cell']/div/div");

	public HomePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	public void cancelWindow() {
		click(cancelBy);
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
	
}
