package com.pack.utils.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.pack.pageobjects.Page;
import com.pack.utils.ScreenshotTaker;

public class TestListener extends TestListenerAdapter{
	
	@Override
	public void onTestFailure(ITestResult tr){	
		WebDriver driver = Page.getDriver();
		ScreenshotTaker.screenshot(driver);	
	}	
}
