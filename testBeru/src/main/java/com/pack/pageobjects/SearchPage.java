package com.pack.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends Page {
	
	private By searchInput = By.cssSelector("input#header-search");
	private By searchBtn = By.cssSelector("form.header2__search button");	
	
	private By minPriceBy = By.cssSelector("input#glpricefrom");
	private By maxPriceBy = By.cssSelector("input#glpriceto");
	
	private By resultMessage = By.xpath("//div[contains(text(),'Найдено')]");
	
	private By productsBy = By.cssSelector("div.n-snippet-list div.grid-snippet");
	private By buyProduct = By.xpath("//span[text()='В корзину']");
	private By priceBy = By.cssSelector("span._1u3j_pk1db.n2qB2SKKgz.AJD1X1j5bE span");
	private By productLinkBy = By.xpath("//span[text()='Перейти в корзину']");
	
	private double min;
	private double max;
	private List<WebElement> products;
	
	
	public SearchPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		max = 0;
		min = 1000000;
	}
	
	public void searchProduct(String product) {
		this.enterText(searchInput, product);
		this.click(searchBtn);
	}
	
	public void setMinPrice(String price) {
		this.enterText(minPriceBy, price);
		min = Double.parseDouble(price);
	}
	
	public void setMaxPrice(String price) {
		this.enterText(maxPriceBy, price);
		max = Double.parseDouble(price);
	}
	
	public boolean checkPrices() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(resultMessage));		
		products = driver.findElements(productsBy);
		for (WebElement element : products) {
			String priceStr = element.findElement(priceBy).getText().replaceAll("\\s","");
			double price = Double.parseDouble(priceStr);
			if ((price > max) || (price < min)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean buyProduct() {			
		if (products.isEmpty())
			return false;
		
		int i = products.size() - 2;			
		if (i < 0) {
			i = 0;
		}
		
		WebElement buyBtn = products.get(i).findElement(buyProduct);		
	    scrollToElement(buyBtn);
		wait.until(ExpectedConditions.elementToBeClickable(buyBtn));
		buyBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(productLinkBy));
		driver.findElement(productLinkBy).click();
		
		return true;
	}

}
