package com.test.selenium.configuration;

import com.test.selenium.configuration.properties.PropertiesLoader;
import com.test.selenium.configuration.properties.Property;
import com.test.selenium.configuration.properties.PropertyFile;
import com.test.selenium.utilitis.Browser;

@PropertyFile("config.properties")
public class TestsConfig {
    private static TestsConfig config;
    public static TestsConfig getConfig() {
        if (config == null) {
            config = new TestsConfig();
        }
        return config;
    }
    public TestsConfig() {
        PropertiesLoader.populate(this);
    }
    @Property("browser.name")
    private String browser = "";
    @Property("browser.version")
    private String version = "";
    public Browser getBrowser() {
        System.out.println("Trying to get browser: " + browser);
        Browser browserForTests = Browser.getByName(browser);
        if (browserForTests != null) {
            return browserForTests;
        } else {
            throw new IllegalStateException("Browser name '" + browser + "' is not valid");
        }
    }
    public String getBrowserVersion() {
        return version;
    }
}
