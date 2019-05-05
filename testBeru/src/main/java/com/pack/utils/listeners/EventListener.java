package com.pack.utils.listeners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import com.pack.utils.HighlightElement;
import com.pack.utils.ScreenshotTaker;

public class EventListener extends AbstractWebDriverEventListener {

	public void action(WebElement element, WebDriver driver) {
		HighlightElement.highLightOn(element, driver);
		ScreenshotTaker.screenshot(driver);
		HighlightElement.highLightOff(element, driver);
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		action(element, driver);
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		action(element, driver);
	}	
}
