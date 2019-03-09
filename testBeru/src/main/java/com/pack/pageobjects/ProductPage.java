package com.pack.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends Page {
	
	private By addMoreBy = By.xpath("//span[text()='+']");
	// span "до бесплатной доставки осталось"
	private By freeDeliveryBy = By.cssSelector("span.voCFmXKfcL");
	// Product price
	private By productPriceBy = By.xpath("//span[contains(text(),'Товары')]/following-sibling::span");
	// Delivery price
	private By deliveryPriceBy = By.xpath("//span[contains(text(),'Доставка')]/following-sibling::span");
	// Total price
	private By priceBy = By.xpath("//span[text()='Итого']/following-sibling::span");
	// Discount 
	private By discountBy = By.xpath("//span[contains(text(),'Скидка')]/following-sibling::span");
	
	private double productPrice;
	
	
	public ProductPage(WebDriver driver, WebDriverWait wait){
		super(driver, wait);
	}	
	
	public double getPrice(By element) {		
		String price = this.getText(element).replaceAll("[^0-9]", "");
		if (price.isEmpty()) {
			return 0;
		}
		return Double.parseDouble(price);
	}
	
	public boolean correctPrice() {	
		double price = getPrice(priceBy);
		System.out.println("Price: " + price);
		double delivery = getPrice(deliveryPriceBy);
		System.out.println("Delivery price: " + delivery);
		double product = getPrice(productPriceBy);
		System.out.println("Product price: " + product);
		double discount = 0;
		try {
			discount = getPrice(discountBy);
		} catch(Exception exp) {}	
		
		System.out.println("Discount: " + discount);
		productPrice = product - discount;
		return (price == (delivery + productPrice));
	}
	
	public void addProduct(double price) {
		System.out.println("Add product...");
		WebElement addBtn = driver.findElement(addMoreBy); 	
		
		int count = (int) Math.ceil((price - getPrice(priceBy)) / productPrice);
		for (int i = 0; i < count; i++) {
			wait.until(ExpectedConditions.elementToBeClickable(addMoreBy));
			addBtn.click();
		}	
	}
		
	public double freeDelivery() {
		System.out.println("Free delivery...");
		if (this.getText(deliveryPriceBy).contains("бесплатно"))
			return 0;
		return getPrice(freeDeliveryBy);	
	}
	
}
