package com.pack.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pack.pageobjects.HomePage;
import com.pack.pageobjects.SignInPage;
import com.pack.utils.Constants;

import io.qameta.allure.Step;

public class NewCityTest extends BaseTest {
	
	@DataProvider(name = "city-data-provider")
	public Object[][] dataProviderMethod() {
		return new Object[][] { 
			{ "Хвалынск" }, 
			{ "Самара" }, 
			{ "Волгоград" } };
	}
	
	@Test(dataProvider = "city-data-provider", dependsOnGroups = {"signInTest"})
	public void changeCityTest(String city) {
				
		HomePage homePage = new HomePage(driver, wait);
		homePage.closeWindow();
		
		cityChanged(homePage, city);
		login(homePage);
		cityMatched(homePage, city);
		homePage.logout();
	}
	
	@Step("Change city and check that it is changed on the home page")
	public void cityChanged(HomePage homePage, String city) {
		homePage.changeCity(city);
		Assert.assertEquals(homePage.getCity().trim(), 
				city, "City doesn't correct");	
	}
	
	@Step("Login")
	public void login(HomePage homePage) {
		SignInPage signInPage = homePage.goToSignIn();
		signInPage.signIn(Constants.LOGIN, Constants.PASSWORD);	
	}
	
	@Step("Check that the city on the settings page matches the city on the home page")
	public void cityMatched(HomePage homePage, String city) {			
		Assert.assertEquals(homePage.changeSettings().getCity().trim(), 
				city, "Cities do not match");
	}	
}

