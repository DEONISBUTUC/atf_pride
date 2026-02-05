package org.example.browser;

import com.microsoft.playwright.*;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.example.exceptions.TestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BrowserInstance {

    @Value("${browser.type}")
    private String browserType;

    @Value("${browser.headless}")
    private boolean headless;

    @Value("${browser.slowMo}")
    private int slowMo;

    private Playwright playwright;

    @PreDestroy
    private void preDestroy() {
        if (playwright != null) {
            playwright.close();
        }
    }

    public Browser getBrowser() throws TestException {
        log.info("Initiating Playwright browser instance");
        try {
            if (playwright == null) {
                playwright = Playwright.create();
            }
            BrowserType browserType = getBrowserType();
            BrowserType.LaunchOptions launchOptions = getLaunchOptions();
            return browserType.launch(launchOptions);
        } catch (PlaywrightException e) {
            throw new RuntimeException("Unable to start new playwright browser session", e);
        }
    }

    private BrowserType getBrowserType() {
        return switch (browserType) {
            case "firefox" -> playwright.firefox();
            case "chrome" -> playwright.chromium();
            default -> playwright.webkit();
        };
    }

    /**
     * Set all browser preferences from this method
     * @return LaunchOptions
     */
    private BrowserType.LaunchOptions getLaunchOptions() {
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions
                .setHeadless(headless);
        return launchOptions;
    }

}
