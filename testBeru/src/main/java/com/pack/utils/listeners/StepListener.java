package com.pack.utils.listeners;

import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.pack.pageobjects.Page;
import com.pack.utils.ScreenshotTaker;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;


public class StepListener implements StepLifecycleListener {
	
	@Override
	public void beforeStepStop(StepResult result) {
		EventFiringWebDriver driver = Page.getDriver();
		ScreenshotTaker.screenshot(driver);	
    }

}
