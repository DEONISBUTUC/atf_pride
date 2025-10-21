package org.example.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import jakarta.annotation.PreDestroy;
import org.example.config.FrameworkProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrowserFactory {

    private static final Playwright playwright = Playwright.create();
    private static Browser browser;
    private static BrowserContext context;
    @Autowired
    private FrameworkProperties properties;


    private Browser getBrowserType() {
        if (browser != null) {
            return browser;
        }
        BrowserType.LaunchOptions options = getBrowserLunchOptions();
        switch (properties.getBrowserType().toLowerCase()) {
            case "firefox" -> browser = playwright.firefox().launch(options);
            case "webkit" -> browser = playwright.webkit().launch(options);
            default -> browser = playwright.chromium().launch(options);
        }
        return browser;
    }

    private BrowserType.LaunchOptions getBrowserLunchOptions() {
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
        options
                .setHeadless(properties.isHeadless())
                .setSlowMo(properties.getSlowMoMs());
        return options;
    }

    public BrowserContext getNewContext() {
        if (browser == null) {
            browser = getBrowserType();
        }
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(properties.getViewportWidth(), properties.getViewportHeight()));
        return context;
    }

    public void closeAll() {
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    @PreDestroy
    public void preDestroy() {
        closeAll();
    }

}
