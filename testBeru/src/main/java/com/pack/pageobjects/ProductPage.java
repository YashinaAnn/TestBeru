package com.pack.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.qameta.allure.Step;


public class ProductPage extends Page {
	
	private By addMoreBy = By.xpath("//span[text()='+']");
	// span "до бесплатной доставки осталось..."
	private By freeDeliveryBy = By.cssSelector("span.voCFmXKfcL");
	// Product price
	private By productPriceBy = By.xpath("//span[contains(text(),'Товары')]/following-sibling::span");
	// Delivery price
	private By deliveryPriceBy = By.xpath("//span[contains(text(),'Доставка')]/following-sibling::span");
	// Total price
	private By priceBy = By.xpath("//span[text()='Итого']/following-sibling::span");
	// Discount 
	private By discountBy = By.xpath("//span[contains(text(),'Скидка')]/following-sibling::span");
	// Count of products in basket.
	private By inBasketBy = By.xpath("//div[contains(text(), 'В корзине ')]");
	// Loader
	private By loaderBy = By.className("A2ZAPkIo1a");
	
	
	// Product price with discount.
	private double productPrice;
	// Total product price with delivery.
	private double totalPrice;
	
	public ProductPage(EventFiringWebDriver driver, WebDriverWait wait){
		super(driver, wait);
	}	
	
	@Step("Сheck that the total product price is equal to the sum of the product price with discount and delivery")
	public void checkProductPrice() {	
		// Product price.
		productPrice = getNumber(productPriceBy);
		System.out.println(String.format("Product price: %s", productPrice));
		// Delivery price.
		double delivery = getNumber(deliveryPriceBy);
		System.out.println(String.format("Delivery price: %s", delivery));	
		// Discount.
		List<WebElement> discount = driver.findElements(discountBy);
		if (!discount.isEmpty()) {
			double disc = getNumber(discount.get(0));
			productPrice -= disc;
			System.out.println(String.format("Discount: %s", disc));	
		}		
		// Total price.
		totalPrice = getNumber(priceBy);
		System.out.println(String.format("Total price: %s", totalPrice));		

		boolean priceCorrect = (totalPrice == (delivery + productPrice));
		Assert.assertTrue(priceCorrect, 
				"The total price is not equal to the sum of " 
				+ "the delivery price and product price");
	}
	
	public boolean addProduct(int price) {
		WebElement addBtn = find(addMoreBy); 		
		int count = (int) Math.ceil((price - totalPrice) / productPrice);
		for (int i = 0; i < count; i++) {
			click(addBtn);
		}			
		wait.until(ExpectedConditions.visibilityOfElementLocated(loaderBy));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderBy));	
		return (count + 1) == getNumber(inBasketBy);
	}
	
	@Step("Check delivery is free")
	public void isFreeDelivery() {
		boolean isFree = getText(deliveryPriceBy).contains("бесплатно");	
		Assert.assertTrue(isFree, "Delivery is not free");
	}
	
	public int beforeFreeDelivery() {
		return getNumber(freeDeliveryBy);	
	}
	
}
