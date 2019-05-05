package com.pack.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pack.pageobjects.HomePage;

import io.qameta.allure.Description;

public class NewCityTest extends BaseTest {
	
	@DataProvider(name = "city-data-provider")
	public Object[][] dataProviderMethod() {
		return new Object[][] { 
			{ "Хвалынск" }, 
			{ "Самара" }, 
			{ "Волгоград" } };
	}
	
	@Description("Test city change")
	@Test(dataProvider = "city-data-provider", dependsOnGroups = {"signInTest"})
	public void changeCityTest(String city) {
				
		HomePage homePage = new HomePage(driver, wait);
		homePage.closeWindow();
		homePage.checkCityChanged(city);
		homePage.login();
		homePage.checkCityMatch(city);
	}
	
}

