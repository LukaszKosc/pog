package com.test.selenium.tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.test.selenium.pageobject.GooglePage;
import com.test.selenium.pageobject.GoogleSearchResultsPage;
import com.test.selenium.utilitis.WebDriverFactory;

public class GoogleSearchTest {
//	@BeforeClass
//	public static void setup_config(){
//		try(  PrintWriter out = new PrintWriter( "D:\\projects\\myTestInSelenium\\src\\config.properties" )  ){
//		    out.println( "browser.name=firefox" );
//		    out.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//	@Before
//	public void setup(){
//		WebDriverFactory.startBrowser(true, "chrome");
//	}
//	@Test
//	public void test() {
////		fail("Not yet implemented");
//		GooglePage gp = new GooglePage("chrome");
////		GoogleSearchResultsPage gsrp = gp.doSearch("slowo");
////		gsrp.showFirstResult();
////		gsrp.collectUrlsFromResult();
//	}
////	@Test
////	public void testWyraz() {
//////		fail("Not yet implemented");
////		GooglePage gp = new GooglePage();
////		GoogleSearchResultsPage gsrp = gp.doSearch("wyraz");
////		gsrp.showFirstResult();
////		gsrp.collectUrlsFromResult();
////	}
//	@After
//	public void teardown(){
//		WebDriverFactory.finishBrowser();
//	}

}
