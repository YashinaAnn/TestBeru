package com.pack.tests;

import org.testng.annotations.Test;
import com.pack.pageobjects.ProductPage;
import com.pack.pageobjects.SearchPage;
import com.pack.utils.Constants;
import io.qameta.allure.Description;


public class BuyProductTest extends BaseTest {
	
	@Description("Test buy product")
	@Test
	public void buyProductTest() {
				
		SearchPage searchPage = new SearchPage(driver, wait);
		searchPage.closeWindow();
		
		searchPage.checkPricesStep();
		searchPage.buyProductStep();
				
		ProductPage productPage = new ProductPage(driver, wait);
		productPage.checkProductPrice();	

		System.out.println(String.format("Before free delivery: %s", 
				productPage.beforeFreeDelivery()));	
		boolean isEnough = productPage.addProduct(Constants.PRICE);
		driver.navigate().refresh();	
		if (isEnough) {
			productPage.isFreeDelivery();
		}
		productPage.checkProductPrice();
	}
}

