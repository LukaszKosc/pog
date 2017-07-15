package com.test.selenium.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.selenium.pageobject.GooglePage;
import com.test.selenium.pageobject.GoogleSearchResultsPage;
import com.test.selenium.utilitis.WebDriverFactory;

import java.net.MalformedURLException;

public class TestNGClass
{
   @Parameters("browser")
   @BeforeTest
   public void launchapp(String browser) throws MalformedURLException
   {
	   WebDriverFactory.startGrid(false, browser);
   }
   @Parameters("browser")
   @Test
   public void calculatepercent(String browser)
   {
		GooglePage gp = new GooglePage(browser);
		GoogleSearchResultsPage gsrp = gp.doSearch("slowo");
		gsrp.showFirstResult();
		gsrp.collectUrlsFromResult();
		gsrp.showResultsNumber();
   }
   @Parameters("browser")
   @AfterTest
   public void closeBrowser(String browser)
   {
	  WebDriverFactory.finishBrowser(browser);
   }
   @AfterSuite
   public void cleanBrowsers()
   {
	   if (WebDriverFactory.getWebDrivers().isEmpty()){
		   System.out.println("is empty? : " + WebDriverFactory.getWebDrivers().isEmpty());
		   WebDriverFactory.cleanBrowser();
		   System.out.println("here should null'ied drivers");
		   
	   }
//	   WebDriverFactory.cleanBrowser();
   }
}
