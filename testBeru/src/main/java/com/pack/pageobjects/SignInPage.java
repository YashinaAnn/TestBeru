package com.pack.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage extends Page {
	
	private final String loginId = "passp-field-login";
	private final String passwordId = "passp-field-passwd";
	
	private By passwordBy = By.id(passwordId);
	private By loginBy = By.id(loginId);
	private By submitBy = By.className("button2_type_submit");
	private By inputBy = By.cssSelector("div.passp-form-field__input input");
	
	public SignInPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	public void signIn(String userLogin, String userPassword) {	
		String id = find(inputBy).getAttribute("id");
		if (id.equals(loginId)) {
			enterText(loginBy, userLogin);
			click(submitBy);
		}	
		enterText(passwordBy, userPassword);
		click(submitBy);
	}
}
