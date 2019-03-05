package com.pack.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pack.pageobjects.HomePage;
import com.pack.pageobjects.SignInPage;

import junit.framework.Assert;

public class SignInTest extends BaseTest {
	
	@Test
	@Parameters({"userLogin", "userPassword"})
	public void validSignIn(String userLogin, String userPassword) {
				
		HomePage homePage = new HomePage(driver, wait);
		homePage.closeWindow();
		
		SignInPage signInPage = homePage.signInClick();
		signInPage.signIn(userLogin, userPassword);
		
		String text = homePage.getUserBtnText().trim();
		Assert.assertEquals("Text of sign in button doesn't correct", "Мой профиль", text);
		text = homePage.getUserLogin().trim();
		Assert.assertEquals("User login doesn't correct", userLogin, text);	
	}
}
