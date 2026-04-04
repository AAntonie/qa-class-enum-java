package com.anaantonie.homework;

public class TestConfigRunner {

    public static void main(String[] args) {

        // Create an array of BrowserConfig objects using different constructors and a factory method
        BrowserConfig[] configs = {
                new BrowserConfig(BrowserType.CHROME, "latest", true), // Constructor 1: all parameters (browser, version, headless)
                new BrowserConfig(BrowserType.FIREFOX, "80"),          // Constructor 2: browser + version (headless default)
                new BrowserConfig(BrowserType.EDGE),                   // Constructor 3: browser only (default values applied)

                // Static factory method - no need to create a BrowserConfig instance to call it
                // Internally uses Constructor 1
                BrowserConfig.createDefaultChromeConfig()
        };

        // Iterate through each configuration and display its details in the console
        for (BrowserConfig config : configs) {
            config.afiseazaConfig();
        }
    }
}