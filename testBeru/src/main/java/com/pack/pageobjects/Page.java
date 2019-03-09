package com.pack.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	private By cancelBy = By.xpath("//div[@class='modal__cell']/div/div");
	
	public Page(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	
	public void closeWindow() {
		click(cancelBy);
	}

	public String getText(By elementBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
		return driver.findElement(elementBy).getText();
	}
	
	public void enterText(By elementBy, String text) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
	    WebElement element = driver.findElement(elementBy);
	    element.clear();
	    element.sendKeys(text);
	}
	
	public String getValue(By elementBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
		return driver.findElement(elementBy).getAttribute("value");
	}
	
	public void click(By elementBy) {
		wait.until(ExpectedConditions.elementToBeClickable(elementBy));
		driver.findElement(elementBy).click();
	}
	
	public void scrollToElement(By element) {
		((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView();", driver.findElement(element));
	}
	
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView();", element);
	}
}
