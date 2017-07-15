package com.test.selenium.pageobject;

import com.test.selenium.utilitis.TimeUtils;
import com.test.selenium.utilitis.WebDriverFactory;
import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
	protected static final int DEFAULT_TIMEOUT = 5;
	protected static final int DEFAULT_ELEMENT_TIMEOUT = 2;
	protected static final int DEFAULT_ELEMENT_TRIES = 10;
	protected String browserName;
	protected void setBrowser(String browserNameInput){
		browserName = browserNameInput;
	}
	protected String getBrowser(){
		return browserName;
	}	
	protected BasePage(boolean openPageByUrl, String browser){
		try {
			setBrowser(browser);
			if (openPageByUrl){
				openPage();
			}
			PageFactory.initElements(getDriver(), this);
			waitForOpen();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	public abstract boolean isPageOpened();
	
	protected void waitForOpen(){
		int secondsCounter = 0;
		boolean isPageOpenedIndicator = isPageOpened();
		while(!isPageOpenedIndicator && secondsCounter < DEFAULT_TIMEOUT){
			TimeUtils.waitForSeconds(1);
			isPageOpenedIndicator = isPageOpened();
			secondsCounter++;	
		}
		if(!isPageOpenedIndicator) {
			throw new AssertionError("Page was not opened");
		}
	}
	protected void waitForElement(String elementLocator){
		int secondsCounter = 0;
		boolean isElementPresentIndicator = isElementPresent(elementLocator);
		while(!isElementPresentIndicator && secondsCounter < DEFAULT_ELEMENT_TRIES){
			TimeUtils.waitForSeconds(DEFAULT_ELEMENT_TIMEOUT);
			isElementPresentIndicator = isElementPresent(elementLocator);
			secondsCounter++;	
		}
		if(!isElementPresentIndicator) {
			throw new AssertionError("Element was not found");
		}
	}
	protected void waitForElement(By elementLocator){
		int secondsCounter = 0;
		boolean isElementPresentIndicator = isElementPresent(elementLocator);
		while(!isElementPresentIndicator && secondsCounter < DEFAULT_ELEMENT_TRIES){
			TimeUtils.waitForSeconds(DEFAULT_ELEMENT_TIMEOUT);
			openPage();
			isElementPresentIndicator = isElementPresent(elementLocator);
			secondsCounter++;	
		}
		if(!isElementPresentIndicator) {
			throw new AssertionError("Element was not found");
		}
	}
	protected WebElement getElement(String elementLocator){
		waitForElement(elementLocator);
		WebElement element = null;
		return element;
	}
	protected WebElement getElement(By elementLocator){
		WebElement element = null;
		try {			
			try {
				waitForElement(elementLocator);
					element = getDriver().findElement(elementLocator);
			} catch (NoSuchElementException e) {
				System.out.println("got exception for noSuchElement for element with locator: " + elementLocator);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return element;
	}
	protected abstract void openPage();
    protected WebDriver getDriver() throws MalformedURLException{
    	return WebDriverFactory.getDriver(getBrowser());
    }
	protected abstract boolean isElementPresent(String elementLocator) ;
	protected abstract boolean isElementPresent(By elementLocator) ;
}
