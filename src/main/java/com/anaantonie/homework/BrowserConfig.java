package com.anaantonie.homework;

public class BrowserConfig {
    private final BrowserType browser;
    private final String version;
    private final boolean isHeadless;
    private String constructorName;

    // Main constructor: initializes all fields
    public BrowserConfig(BrowserType browser, String version, boolean isHeadless) {
        this.browser = browser;
        this.version = version;
        this.isHeadless = isHeadless;
        this.constructorName = "Constructor 1 (browser, version, headless)";
    }

    // Constructor with browser and version
    // Calls the main constructor, sets isHeadless to false
    public BrowserConfig(BrowserType browser, String version) {
        this(browser, version, false); // chaining to the main constructor
        this.constructorName = "Constructor 2 (browser, version)";
    }

    // Constructor with only browser
    // Calls the two-parameter constructor, sets version to "latest"
    // isHeadless is not set here; it is handled by the two-parameter constructor
    public BrowserConfig(BrowserType browser) {
        this(browser, "latest"); // chaining to the second constructor
        this.constructorName = "Constructor 3 (browser)";
    }

    // Factory method for creating a default Chrome configuration and internally uses Constructor 1
    // This is a static method, so we can call it without creating an instance of BrowserConfig
    public static BrowserConfig createDefaultChromeConfig() {
        BrowserConfig cfg = new BrowserConfig(BrowserType.CHROME, "default", true);
        cfg.constructorName = "Factory Method (createDefaultChromeConfig -> Constructor 1)";
        return cfg;
    }


    // Displays the browser configuration in a formatted table-like style
    public void afiseazaConfig() {
        System.out.printf("| Browser: %-7s | Version: %-7s | Headless: %-5s | Used: %s%n", browser, version, isHeadless, constructorName);
    }


}