package com.pack.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pack.pageobjects.HomePage;
import com.pack.pageobjects.SignInPage;

import junit.framework.Assert;

public class NewCityTest extends BaseTest {
	
	@Test(priority = 1)
	@Parameters({"city", "userLogin", "userPassword"})
	public void changeCity(String city, String userLogin, String userPassword) {
				
		HomePage homePage = new HomePage(driver, wait);
		homePage.closeWindow();
		
		homePage.changeCity(city);	
		String newCity = homePage.getCity().trim();
		Assert.assertEquals("City doesn't correct", city, newCity);
	
		SignInPage signInPage = homePage.signInClick();
		signInPage.signIn(userLogin, userPassword);		
		newCity = homePage.changeSettings().getCity().trim();		
		Assert.assertEquals("Cities do not match", city, newCity);
	}
}

