package com.pack.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.pack.tests.BaseTest;

public class TestListener extends TestListenerAdapter{
	
	@Override
	public void onTestFailure(ITestResult tr){	
		Object currentClass = tr.getInstance();
		WebDriver driver = ((BaseTest) currentClass).getDriver();
		HighlightElement.screenshot(driver);	
	}	
}
