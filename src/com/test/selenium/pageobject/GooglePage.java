package com.test.selenium.pageobject;

import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage extends BasePage {
	public GooglePage(String browser) {
		super(true, browser);
	}
	private static WebDriver driver;
	private static String URL = "https://www.google.pl";
	private WebElement btnElement;
	@FindBy(id="lst-ib")
	private WebElement searchInputElement;
	
	@Override
	public boolean isPageOpened(){
		return (searchInputElement != null) ? true : false;
	}
	@Override
	protected void openPage(){
		WebDriver myDriver;
		try {
			driver = getDriver();
			System.out.println("Driver is : " + driver);
			driver.get(URL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected boolean isElementPresent(String elementLocator){
		try{
			;
		}catch(NoSuchElementException e){
			System.out.println("there was NoSuchElementException caught");
		}
		return true;
	}
	@Override
	protected boolean isElementPresent(By elementLocator){
		try{
			System.out.println("Looking for an element - locator: " + elementLocator);
			WebElement element = driver.findElement(elementLocator);
		}catch(NoSuchElementException e){
			System.out.println("by elementLocator - there was NoSuchElementException caught");
		}
		return true;
	}
	public GoogleSearchResultsPage doSearch(String searchText){
		searchInputElement.sendKeys(searchText);;
		waitForElement(By.id("_fZl"));
		getElement(By.id("_fZl")).click();
		return new GoogleSearchResultsPage(false, getBrowser());
	}
}
