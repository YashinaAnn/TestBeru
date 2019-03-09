package com.pack.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pack.pageobjects.ProductPage;
import com.pack.pageobjects.SearchPage;
import junit.framework.Assert;

public class BuyProductTest extends BaseTest {
	
	@Test
	@Parameters({"product", "minPrice", "maxPrice", "price"})
	public void buyProduct(String product, String minPrice, String maxPrice, double price) {
				
		SearchPage searchPage = new SearchPage(driver, wait);
		searchPage.closeWindow();
		
		searchPage.searchProduct(product);
		searchPage.setMinPrice(minPrice);
		searchPage.setMaxPrice(maxPrice);
		Assert.assertTrue("Prices do not match", searchPage.checkPrices());
		Assert.assertTrue("Product list is empty", searchPage.buyProduct());
		
		ProductPage productPage = new ProductPage(driver, wait);
		System.out.println(productPage.freeDelivery());
		Assert.assertTrue("The total price is not equal to the sum of " 
		                  + "the delivery price and product price", 
		                  productPage.correctPrice());
		
		productPage.addProduct(price);
		try {
			Thread.sleep(2000);
		} catch(Exception e) {}
		
		Assert.assertEquals("Delivery is not free", 0.0, productPage.freeDelivery());
		Assert.assertTrue("The total price is not equal to the sum of " 
                		  + "the delivery price and product price",  
                          productPage.correctPrice());
	}
}

