package com.pack.tests;

import org.testng.annotations.Test;
import com.pack.pageobjects.HomePage;
import io.qameta.allure.Description;


public class SignInTest extends BaseTest {
	
	@Description("Test signin functionality")
	@Test(groups = {"signInTest"})
	public void signInTest() {			
		HomePage homePage = new HomePage(driver, wait);
		homePage.closeWindow();	
		homePage.login();
		homePage.checkSignInButtonText();
		homePage.checkLoginText();	
	}
}
