package com.test.selenium.utilitis;

//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class TimeUtils {
	public static void waitForSeconds(int seconds){
//		WebDriverWait wait = new WebDriverWait(driver, seconds);
		try{
			Thread.sleep(seconds * 1000);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
