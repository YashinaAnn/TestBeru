package com.pack.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pack.utils.HighlightElement;

public class Page {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	private By cancelBy = By.xpath("//div[@class='modal__cell']/div/div");
	
	public Page(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	
	public void closeWindow() {
		try {
			click(cancelBy);
		} catch(Exception e) {
			System.out.println("Window did not appear");
		}
	}
	
	public WebElement findAndHighLight(By elementBy) {
		WebElement element = driver.findElement(elementBy);
		HighlightElement.highLight(element, driver);
		return element;
	}
	
	public String getText(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		HighlightElement.highLight(element, driver);
		return element.getText();
	}
	
	public String getText(By elementBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
		return getText(driver.findElement(elementBy));
	}
	
	public void enterText(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element));
	    element.clear();
	    element.sendKeys(text);
		HighlightElement.highLight(element, driver);     
	}
	
	public void enterText(By elementBy, String text) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
	    enterText(driver.findElement(elementBy), text);
	}
	
	public void click(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		HighlightElement.highLight(element, driver);
		element.click();
	}
	
	public void click(By elementBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
		click(driver.findElement(elementBy));
	}
	
	public void submit(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		HighlightElement.highLight(element, driver);
		element.submit();
	}
	
	public void submit(By elementBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
		submit(driver.findElement(elementBy));
	}
	
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView();", element);
	}
	
	public void scrollToElement(By elementBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
		scrollToElement(driver.findElement(elementBy));
	}

	public WebDriver getDriver() {
		return driver;
	}
}
