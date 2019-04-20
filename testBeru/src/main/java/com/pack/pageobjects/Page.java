package com.pack.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pack.utils.HighlightElement;

public class Page {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	private By cancelBy = By.xpath("//div[@class='modal__cell']/div/div");
	
	public Page(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public WebDriverWait getWait() {
		return wait;
	}
	
	public void closeWindow() {
		try {
			click(cancelBy);
		} catch(Exception e) {
			System.out.println("Window did not appear");
		}
	}
	
	public WebElement find(By element) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		return driver.findElement(element);
	}
	
	public WebElement findAndHighLight(By elementBy) {
		WebElement element = find(elementBy);
		HighlightElement.highLight(element, driver);
		return element;
	}
	
	public String getText(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		HighlightElement.highLight(element, driver);
		return element.getText();
	}
	
	public String getText(By elementBy) {
		return getText(find(elementBy));
	}
	
	public void enterText(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element));
	    element.clear();
	    element.sendKeys(text);
		HighlightElement.highLight(element, driver);     
	}
	
	public void enterText(By elementBy, String text) {
	    enterText(find(elementBy), text);
	}
	
	public void click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		HighlightElement.highLight(element, driver);
		element.click();
	}
	
	public void click(By elementBy) {
		click(find(elementBy));
	}
	
	public void submit(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		HighlightElement.highLight(element, driver);
		element.submit();
	}
	
	public void submit(By elementBy) {
		submit(find(elementBy));
	}
	
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView();", element);
	}
	
	public void scrollToElement(By elementBy) {
		scrollToElement(find(elementBy));
	}
	
	public int getNumber(By element) {
		return getNumber(find(element));
	}
	
	public int getNumber(WebElement element) {	
		String num = getText(element).replaceAll("[^0-9]", "");
		if (num.isEmpty()) {
			return 0;
		}
		return Integer.parseInt(num);
	}
	
}
