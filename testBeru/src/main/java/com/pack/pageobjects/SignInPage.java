package com.pack.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage extends Page {
	
	private By passwordBy = By.id("passp-field-passwd");
	private By loginBy = By.id("passp-field-login");
	private By submitBy = By.className("button2_type_submit");
	
	public SignInPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	public void signIn(String userLogin, String userPassword) {
		this.enterText(loginBy, userLogin);
		this.click(submitBy);
		this.enterText(passwordBy, userPassword);
		this.click(submitBy);
	}
}
