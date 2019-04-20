package com.pack.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pack.pageobjects.HomePage;
import com.pack.pageobjects.SignInPage;

import io.qameta.allure.Step;
import junit.framework.Assert;

public class SignInTest extends BaseTest {
	
	private final String BUTTON_TEXT = "Мой профиль";
	
	@Test(groups = {"signInTest"})
	@Parameters({"userLogin", "userPassword"})
	public void signInTest(String userLogin, String userPassword) {			
		HomePage homePage = new HomePage(driver, wait);
		homePage.closeWindow();	
		
		SignInPage signInPage = homePage.goToSignIn();
		signInPage.signIn(userLogin, userPassword);	
		
		checkButtonText(homePage);
		checkLoginText(homePage, userLogin);	
		homePage.logout();
	}
	
	@Step
	public void checkButtonText(HomePage homePage) {
		Assert.assertEquals("Text of sign in button doesn't correct", 
							BUTTON_TEXT, 
							homePage.getUserBtnText().trim());
	}
	
	@Step
	public void checkLoginText(HomePage homePage, String userLogin) {
		Assert.assertEquals("User login doesn't correct", 
				            userLogin, 
				            homePage.getUserLogin().trim());	
	}
}
