package com.pack.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pack.pageobjects.HomePage;
import com.pack.pageobjects.SignInPage;

import io.qameta.allure.Step;
import junit.framework.Assert;

public class NewCityTest extends BaseTest {
	
	private final String login = "testBeruRu@yandex.ru";
	private final String password = "kulikandlake";
	
	@DataProvider(name = "city-data-provider")
	public Object[][] dataProviderMethod() {
		return new Object[][] { { "Хвалынск", login, password }, 
								{ "Самара", login, password }, 
								{ "Волгоград", login, password} };
	}
	
	@Test(dataProvider = "city-data-provider", dependsOnGroups= {"signInTest"})
	public void changeCityTest(String city, String userLogin, String userPassword) {
				
		HomePage homePage = new HomePage(driver, wait);
		homePage.closeWindow();
		
		cityChanged(homePage, city);
		cityMatched(homePage, userLogin, userPassword, city);
		homePage.logout();
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
		SignInPage signInPage = homePage.goToSignIn();
		signInPage.signIn(userLogin, userPassword);				
		Assert.assertEquals("Cities do not match", city, 
							homePage.changeSettings().getCity().trim());
	}	
}

