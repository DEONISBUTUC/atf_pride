package org.example.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BrowserFactoryTest {

    @AfterAll
    public static void tearDown() {
        BrowserFactory.closeAll();
    }

    @Test
    public void testCreateBrowser() {
        Browser browser = BrowserFactory.createBrowser();
        Assertions.assertNotNull(browser, "Browser should be initialized");
    }

    @Test
    public void testGetNewContext() {
        BrowserContext context = BrowserFactory.getNewContext();
        Assertions.assertNotNull(context, "BrowserContext should be created");
    }

    @Test
    public void testBrowserTypeChromium() {
        System.setProperty("browser.type", "chromium");
        Browser browser = BrowserFactory.createBrowser();
        Assertions.assertNotNull(browser);
    }


    @Test
    public void testHeadlessMode() {
        System.setProperty("browser.headless", "true");
        Browser browser = BrowserFactory.createBrowser();
        Assertions.assertNotNull(browser);
    }


    @Test
    public void testMultipleContexts() {
        BrowserContext context1 = BrowserFactory.getNewContext();
        BrowserContext context2 = BrowserFactory.getNewContext();
        Assertions.assertNotNull(context1);
        Assertions.assertNotNull(context2);
        Assertions.assertNotEquals(context1, context2, "Each call should create a new context");

    }

    @Test
    public void testBrowserIsSingleton() {
        Browser browser1 = BrowserFactory.createBrowser();
        Browser browser2 = BrowserFactory.createBrowser();
        Assertions.assertSame(browser1, browser2, "Browser instance should be reused");
    }
}
