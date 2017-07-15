package com.test.selenium.utilitis;

public enum  Browser {
    FIREFOX("firefox"),
    CHROME("chrome"), 
    IE11("ie"),
//    SAFARI("safari")
    ;
    private String browserName;
    private Browser(String browserName) {
        this.browserName = browserName;
    }
    public String getBrowserName() {
        return browserName;
    }
    public static Browser getByName(String name){
        for(Browser browser : values()) {
            if(browser.getBrowserName().equalsIgnoreCase(name)) {
                return browser;
            }
        }
        return null;
    }
}
