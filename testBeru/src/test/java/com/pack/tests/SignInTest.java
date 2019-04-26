package com.pack.tests;

import org.testng.annotations.Test;

import com.pack.pageobjects.HomePage;
import com.pack.pageobjects.SignInPage;
import com.pack.utils.Constants;

import io.qameta.allure.Step;
import junit.framework.Assert;

public class SignInTest extends BaseTest {
	
	@Test(groups = {"signInTest"})
	public void signInTest() {			
		HomePage homePage = new HomePage(driver, wait);
		homePage.closeWindow();	
		
		login(homePage);
		checkButtonText(homePage);
		checkLoginText(homePage);	
		homePage.logout();
	}
	
	@Step("Login")
	public void login(HomePage homePage) {
		SignInPage signInPage = homePage.goToSignIn();
		signInPage.signIn(Constants.LOGIN, Constants.PASSWORD);	
	}
	
	@Step("Check that the button text after authorization is " + Constants.BUTTON_TEXT)
	public void checkButtonText(HomePage homePage) {
		Assert.assertEquals("Text of sign in button doesn't correct", 
				Constants.BUTTON_TEXT, homePage.getUserBtnText().trim());
	}
	
	@Step("Check login after authorization")
	public void checkLoginText(HomePage homePage) {
		Assert.assertEquals("User login doesn't correct", 
				Constants.LOGIN, homePage.getUserLogin().trim());	
	}
}
