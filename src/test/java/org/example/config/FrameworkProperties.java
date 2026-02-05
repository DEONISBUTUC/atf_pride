package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FrameworkProperties {

    @Value("${env}")
    private String env;

    @Value("${app.url}")
    private String appUrl;

    @Value("${browser.type}")
    private String browserType;

    @Value("${browser.headless}")
    private boolean headless;

    @Value("${browser.viewport.size.width}")
    private int viewportWidth;

    @Value("${browser.viewport.size.height}")
    private int viewportHeight;

    @Value("${browser.slow.mo}")
    private int slowMoMs;

    @Value("${timeout.default.ms}")
    private int defaultTimeoutMs;

    @Value("${app.userphone}")
    private String userphone;

    public String getEnv() {
        return env;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public String getBrowserType() {
        return browserType;
    }

    public boolean isHeadless() {
        return headless;
    }

    public int getViewportWidth() {
        return viewportWidth;
    }

    public int getViewportHeight() {
        return viewportHeight;
    }

    public int getSlowMoMs() {
        return slowMoMs;
    }

    public String getUserphone() {return userphone;}

    public int getDefaultTimeoutMs() {
        return defaultTimeoutMs;
    }
}
