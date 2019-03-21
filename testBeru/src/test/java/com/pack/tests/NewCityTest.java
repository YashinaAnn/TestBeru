package com.pack.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pack.pageobjects.HomePage;
import com.pack.pageobjects.SignInPage;

import io.qameta.allure.Step;
import junit.framework.Assert;

public class NewCityTest extends BaseTest {
	
	@Test(priority = 1)
	@Parameters({"city", "userLogin", "userPassword"})
	public void changeCity(String city, String userLogin, String userPassword) {
				
		HomePage homePage = new HomePage(driver, wait);
		homePage.closeWindow();
		
		cityChanged(homePage, city);
		cityMatched(homePage, userLogin, userPassword, city);
	}
	
	@Step
	public void cityChanged(HomePage homePage, String city) {
		homePage.changeCity(city);
		Assert.assertEquals("City doesn't correct", city, 
				homePage.getCity().trim());	
	}
	
	@Step
	public void cityMatched(HomePage homePage, String userLogin, 
							String userPassword, String city) {
		SignInPage signInPage = homePage.signInClick();
		signInPage.signIn(userLogin, userPassword);				
		Assert.assertEquals("Cities do not match", city, 
							homePage.changeSettings().getCity().trim());
	}
	
}

