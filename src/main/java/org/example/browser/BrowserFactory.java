package org.example.browser;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.example.utils.PropertyReader;

/**
 * BrowserFactory provides a minimal example of how to centralize browser
 * creation for UI tests using Microsoft Playwright. In a production framework
 * this class would manage configurations (browser type, headless mode, timeouts)
 * and lifecycle concerns.
 * <p>
 * This project is intended for educational purposes, so the implementation is
 * intentionally simple and focuses on demonstrating structure.
 *
 * @see <a href="https://playwright.dev/java/docs/intro">Playwright Documentation</a>
 */
public class BrowserFactory {

    private static final Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;

    static {
        playwright = Playwright.create();
    }

    // Prevent instantiation
    private BrowserFactory() {
    }

    /**
     * Create and launch a new Chromium browser instance.
     *
     * @return a launched Playwright Browser
     */
    public static Browser createBrowser() {

        if (browser != null) {
            return browser;
        }
        String browserName = PropertyReader.getInstance().getProperty("browser.type");
        boolean headless = PropertyReader.getInstance().getBooleanProperty("browser.headless");
        int slowMo = PropertyReader.getInstance().getIntProperty("browser.slow.mo");
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(headless);
        options.setSlowMo(slowMo);
        switch (browserName.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(options);
                break;
            case "webkit":
                browser = playwright.webkit().launch(options);
                break;
            case "chrome":
                options.setChannel("chrome");
                browser = playwright.chromium().launch(options);
                break;
            default:
                browser = playwright.chromium().launch(options);
                break;
        }
        return browser;
    }

    public static BrowserContext getNewContext() {
        if (browser == null) {
            browser = createBrowser();
        }

        int browserViewportSizeWidth = PropertyReader.getInstance().getIntProperty("browser.viewport.size.width");
        int browserViewportSizeHeight = PropertyReader.getInstance().getIntProperty("browser.viewport.size.height");
        int timeoutMs = PropertyReader.getInstance().getIntProperty("timeout.default.ms");

        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(browserViewportSizeWidth, browserViewportSizeHeight));
        context.setDefaultTimeout(timeoutMs);
        context.setDefaultNavigationTimeout(timeoutMs);
        return context;
    }

    public static void closeAll() {
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

}
