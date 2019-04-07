package com.pack.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Attachment;

public class HighlightElement{
	
	public static void highLight(WebElement element, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, 
        		 "background-color: yellow; border: 2px solid red;");
		screenshot(driver);
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
	}
	
	@Attachment(value = "Page screenshot", type = "image/png") 
	public static byte[] saveScreenshot(byte[] screenShot) { 
		return screenShot; 
	} 
	
	public static void screenshot(WebDriver driver) {
		if (driver == null) { 
			System.out.println("Driver for screenshot not found"); 
			return; 
		} 
		saveScreenshot(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)); 
	} 
}