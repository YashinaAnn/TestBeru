package com.pack.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Attachment;

public class ScreenshotTaker {
	
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
