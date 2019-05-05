package com.pack.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.pack.utils.Constants;
import io.qameta.allure.Step;


public class SearchPage extends Page {
	// Product search input.
	private By searchInput = By.cssSelector("input#header-search");
	// Search button.
	private By searchBtn = By.cssSelector("form.header2__search button");	
	// Minimum price input.
	private By minPriceInputBy = By.cssSelector("input#glpricefrom");
	// Maximum price input.
	private By maxPriceInputBy = By.cssSelector("input#glpriceto");
	// Div element of product. 
	private By productsBy = By.cssSelector("div.n-snippet-list div.grid-snippet");
	// Button 'В корзину'.
	private By buyProductBtn = By.tagName("button");
	// Product price.
	private By priceBy = By.cssSelector("span._1u3j_pk1db span");
	// Button 'Перейти в корзину'.
	private By productLinkBy = By.xpath("//span[text()='Перейти в корзину']");
	// Button 'Вперёд'
	private By forwardBtnBy = By.cssSelector("a.n-pager__button-next");
	// Loader
	private By loaderBy = By.className("preloadable__preloader_visibility_visible");
	
	// Minimum price value.
	private int min;
	// Maximum price value.
	private int max;
	// List of products.
	private List<WebElement> products;
	
	public SearchPage(EventFiringWebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		max = 0;
		min = Integer.MAX_VALUE;
	}
	
	// Search of product.
	public void searchProduct(String product) {
		enterText(searchInput, product);
		click(searchBtn);
	}
	
	// Setter of minimum price.
	public void setMinPrice(int price) {
		enterText(minPriceInputBy, Integer.toString(price));
		min = price;
	}
	
	// Setter of maximum price.
	public void setMaxPrice(int price) {
		enterText(maxPriceInputBy, Integer.toString(price));
		max = price;
	}
	
	// Products prices check.
	public boolean checkPrices() {			
		do {				
			wait.until(ExpectedConditions.visibilityOfElementLocated(loaderBy));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderBy));
			
			products = driver.findElements(productsBy);
			for (WebElement element : products) {
				double price = Double.parseDouble(element.findElement(priceBy)
						.getText().replaceAll("\\s",""));
				
				System.out.println("price: " + price);
				if ((price > max) || (price < min)) {
					return false;
				}
			}
			
			List<WebElement> nextButton = driver.findElements(forwardBtnBy);
			if (nextButton.isEmpty()) {
				break;
			}
			click(nextButton.get(0));
			
		} while(true);
		
		return true;
	}
	
	// Buy product method.
	public boolean buyProduct() {	
		if (products.isEmpty()) {
			return false;
		}	
		int i = products.size() == 1 ? 0 : (products.size() - 2);		
		
		// Put product in basket.
		wait.until(ExpectedConditions.visibilityOfElementLocated(buyProductBtn));
		WebElement buyBtn = products.get(i).findElement(buyProductBtn);
		scrollToElement(products.get(i));
		click(buyBtn);
		// Go to basket.
		click(productLinkBy);
		return true;
	}
	
	@Step("Check that the prices of products in the selected range")
	public void checkPricesStep() {
		searchProduct(Constants.PRODUCT);
		setMinPrice(Constants.MIN_PRICE);
		setMaxPrice(Constants.MAX_PRICE);
		Assert.assertTrue(checkPrices(), "Prices do not match");
	}
	
	@Step("Put product in the basket")
	public void buyProductStep() {
		Assert.assertTrue(buyProduct(), "Product list is empty");
	}
}
