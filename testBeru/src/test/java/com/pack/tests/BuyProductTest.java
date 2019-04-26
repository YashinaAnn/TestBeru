package com.pack.tests;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.pack.pageobjects.ProductPage;
import com.pack.pageobjects.SearchPage;
import com.pack.utils.Constants;

import io.qameta.allure.Step;

public class BuyProductTest extends BaseTest {
	
	@Test
	public void buyProductTest() {
				
		SearchPage searchPage = new SearchPage(driver, wait);
		searchPage.closeWindow();
		
		checkPrices(searchPage);
		buyProduct(searchPage);
				
		ProductPage productPage = new ProductPage(driver, wait);
		checkProductPrice(productPage);	

		System.out.println(String.format("Before free delivery: %s", 
				productPage.beforeFreeDelivery()));	
		boolean isEnough = productPage.addProduct(Constants.PRICE);
		driver.navigate().refresh();	
		if (isEnough) {
			isFreeDelivery(productPage);
		}
		checkProductPrice(productPage);
	}
	
	@Step("Check that the prices of products in the selected range")
	public void checkPrices(SearchPage searchPage) {
		searchPage.searchProduct(Constants.PRODUCT);
		searchPage.setMinPrice(Constants.MIN_PRICE);
		searchPage.setMaxPrice(Constants.MAX_PRICE);
		Assert.assertTrue(searchPage.checkPrices(), "Prices do not match");
	}
	
	@Step("Put product in the basket")
	public void buyProduct(SearchPage searchPage) {
		Assert.assertTrue(searchPage.buyProduct(), "Product list is empty");
	}
	
	@Step("Сheck that the total product price is equal to the sum of the product price with discount and delivery")
	public void checkProductPrice(ProductPage productPage) {
		Assert.assertTrue(productPage.correctPrice(), 
				"The total price is not equal to the sum of " 
				+ "the delivery price and product price");
	}
	
	@Step("Check delivery is free")
	public void isFreeDelivery(ProductPage productPage){
		Assert.assertTrue(productPage.isFreeDelivery(), "Delivery is not free");
	}
}

