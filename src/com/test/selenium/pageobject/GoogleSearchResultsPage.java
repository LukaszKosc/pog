package com.test.selenium.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

public class GoogleSearchResultsPage extends BasePage {
	public GoogleSearchResultsPage(boolean openPageByUrl, String browser) {
		super(openPageByUrl, browser);
	}
	@FindBy(id="resultStats")
	private WebElement numberOfResultsElement;
	@FindBy(id="ires")
	private WebElement searchResultsElement;
	
	@FindBy(how = How.CLASS_NAME, using = "ires")
	private WebElement resultListElement;
	
	@FindAll({
		//div[@class='rc']//h3/a/
		//div[@class='rc']/h3
		@FindBy(how = How.XPATH, using = "//div[@class='rc']/h3")
	})
	private List<WebElement> searchResultsListElement;

	@FindAll({
		//div[@class='rc']//h3/a/
		//div[@class='rc']/h3
		@FindBy(how = How.XPATH, using = "//div[@class='rc']/h3/a")
	})
	private List<WebElement> searchResultUrlsElement;
	
	@Override
	public boolean isPageOpened(){
		return (searchResultsElement != null) ? true : false;
	}
	@Override
	protected void openPage(){
	}
	public void showResultsNumber(){
		System.out.println("Results number: " + numberOfResultsElement.getText());
	}
	public void showFirstResult(){
		if (isPageOpened()){
			if (searchResultsListElement != null){
				if (searchResultsListElement.size() > 0){
					System.out.println("First result: " + searchResultsListElement.get(0));
					System.out.println("First result text: " + searchResultsListElement.get(0).getText());
				}else
				{
					System.out.println("size is 0");					
				}
			}else
			{
				System.out.println("searchResultsListElement is null");
			}
		}
	}
	public void collectUrlsFromResult(){
		if (isPageOpened()){
			if (searchResultUrlsElement != null){
				if (searchResultUrlsElement.size() > 0){
					System.out.println("size of urls list: " + searchResultUrlsElement.size());
//					for (int i = 0 ; i < searchResultUrlsElement.size(); i++){
//						System.out.println("url got: " + searchResultUrlsElement.get(i).getAttribute("href"));
//					}
				}else
				{
					System.out.println("no urls there");					
				}
			}else
			{
				System.out.println("searchResultsListElement is null");
			}
		}
	}
	@Override
	protected boolean isElementPresent(String elementLocator){
		return true;
	}
	@Override
	protected boolean isElementPresent(By elementLocator){
		return true;
	}
}
