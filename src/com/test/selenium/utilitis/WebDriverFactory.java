package com.test.selenium.utilitis;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.test.selenium.utilitis.CapabilitiesGenerator;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    private static final long IMPLICIT_WAIT_TIMEOUT = 10;
    private static Map<String, WebDriver> drivers;
    public static WebDriver getDriver(String browserName) throws MalformedURLException {
    	if (getWebDrivers().get(browserName) != null) 
            return getWebDrivers().get(browserName);
    	else{
            DesiredCapabilities cap;
            StringBuilder Node = new StringBuilder("http://" + gridParams.get("host_ip") + ":");
            Browser browser = Browser.getByName(browserName);
            switch (browser) {
                case FIREFOX:
                    Node.append(gridParams.get("firefox_port"));
                    cap = DesiredCapabilities.firefox();
                    cap.setBrowserName(browser.getBrowserName());
                    cap.setPlatform(Platform.getCurrent());
                	break;
                case CHROME:
                	cap = CapabilitiesGenerator.getDefaultCapabilities(browser);
                	cap.setCapability("platform", "WIN8_1");
                	cap.setBrowserName(browser.getBrowserName());
                    Node.append(gridParams.get("chrome_port"));
                	break;
                case IE11:
                	cap = CapabilitiesGenerator.getDefaultCapabilities(browser);
                	cap.setCapability("platform", "WIN8_1");
                	cap.setBrowserName(browser.getBrowserName());
                    Node.append(gridParams.get("ie_port"));
                    break;
                default:
                    throw new IllegalStateException("Unsupported browser type");
        	}
            Node.append("/wd/hub");
            getWebDrivers().put(browser.getBrowserName(),new RemoteWebDriver(new URL(Node.toString()), cap));
            Node = null;
            cap = null;
        	return getWebDrivers().get(browserName);
    	}
    }
    private static Map<String,String> gridParams;
    public static void startGrid(boolean isLocal, String browserName) throws MalformedURLException {
        	if (!isLocal) {
                gridParams = new HashMap<String, String>();
                String ipToSet = GetIpAddress.getLocalIp();
                gridParams.put("host_ip", ipToSet);
                gridParams.put("firefox_port", "5555");
                gridParams.put("ie_port", "5558");
                gridParams.put("chrome_port", "5557");
        		getWebDrivers().put(browserName, getDriver(browserName));
        		getWebDrivers().get(browserName).manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        	} 
    }
    public static Map<String,WebDriver> getWebDrivers(){
    	if (drivers == null) {
        	drivers = new HashMap<String, WebDriver>();
    	}
    	return drivers;
    }
    public static void finishBrowser(String browserName) {
        if (getWebDrivers().get(browserName) != null) {
        	getWebDrivers().get(browserName).close();
        	getWebDrivers().remove(browserName);
        }
    }
    public static void cleanBrowser() {
        if (getWebDrivers().isEmpty()){
        	//clean up - run taskkill/killall on geckodriver/chromedriver
        }
    }
    public static void takeScreenShot() {
        System.out.println("ScreenShot method called");
    }
}
