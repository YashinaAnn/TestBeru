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
	public void buyProduct(String product, String minPrice, 
						   String maxPrice, double price) {
				
		SearchPage searchPage = new SearchPage(driver, wait);
		searchPage.closeWindow();
		
		isPricesMatch(searchPage, product, minPrice, maxPrice);
		buyProduct(searchPage);
				
		ProductPage productPage = new ProductPage(driver, wait);
		isCorrectPrice(productPage);	
		
		boolean isEnough = productPage.addProduct(price);
		driver.navigate().refresh();	
		if (isEnough) {
			isFreeDelivery(productPage);
		}
		isCorrectPrice(productPage);
	}
	
	@Step
	public void isPricesMatch(SearchPage searchPage, String product, 
							  String minPrice, String maxPrice) {
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
	public void isCorrectPrice(ProductPage productPage) {
		System.out.println(productPage.freeDelivery());
		Assert.assertTrue("The total price is not equal to the sum of " 
		                  + "the delivery price and product price", 
		                  productPage.correctPrice());
	}
	
	@Step
	public void isFreeDelivery(ProductPage productPage){
		Assert.assertEquals("Delivery is not free", 
							0.0, 
						    productPage.freeDelivery());
	}
}

