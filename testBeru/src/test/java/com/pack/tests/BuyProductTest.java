package com.pack.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pack.pageobjects.ProductPage;
import com.pack.pageobjects.SearchPage;

import io.qameta.allure.Step;
import junit.framework.Assert;

public class BuyProductTest extends BaseTest {
	@Test
	@Parameters({"product", "minPrice", "maxPrice", "price"})
	public void buyProductTest(String product, int minPrice, 
			int maxPrice, int price) {
				
		SearchPage searchPage = new SearchPage(driver, wait);
		searchPage.closeWindow();
		
		checkPrices(searchPage, product, minPrice, maxPrice);
		buyProduct(searchPage);
				
		ProductPage productPage = new ProductPage(driver, wait);
		checkProductPrice(productPage);	

		System.out.println(String.format("Before free delivery: %s", 
				productPage.beforeFreeDelivery()));	
		boolean isEnough = productPage.addProduct(price);
		driver.navigate().refresh();	
		if (isEnough) {
			isFreeDelivery(productPage);
		}
		checkProductPrice(productPage);
	}
	
	@Step
	public void checkPrices(SearchPage searchPage, String product, 
			int minPrice, int maxPrice) {
		searchPage.searchProduct(product);
		searchPage.setMinPrice(minPrice);
		searchPage.setMaxPrice(maxPrice);
		Assert.assertTrue("Prices do not match", searchPage.checkPrices());
	}
	
	@Step
	public void buyProduct(SearchPage searchPage) {
		Assert.assertTrue("Product list is empty", searchPage.buyProduct());
	}
	
	@Step
	public void checkProductPrice(ProductPage productPage) {
		Assert.assertTrue("The total price is not equal to the sum of " 
		                  + "the delivery price and product price", 
		                  productPage.correctPrice());
	}
	
	@Step
	public void isFreeDelivery(ProductPage productPage){
		Assert.assertTrue("Delivery is not free", 
				productPage.isFreeDelivery());
	}
}

