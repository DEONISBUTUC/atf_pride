package com.democracy.qa.stepdef.ui;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.with;

@Slf4j
public class SSOAuthorizationPage {
    private final Page page;

    SSOAuthorizationPage(Page page) {
        this.page = page;
    }

    SSOAuthorizationPage open(String url) {
        log.info("Opening msign authorization page: " + url);
        page.navigate(url);
        return this;
    }

    SSOAuthorizationPage fillNumber(String number) {
        Locator numberInputLocator = page.locator("[id='PhoneNumber']");
        log.info("Filling costumer phone number: " + number);
        waitForElement(numberInputLocator);
        numberInputLocator.fill(number);
        return this;
    }

    SSOAuthorizationPage clickSubmitButton() {
        Locator submitButtonLocator = page.locator("//button[normalize-space()='Trimite cod de verificare']");
        log.info("Clicking next button");
        submitButtonLocator.click();
        return this;
    }


    SSOAuthorizationPage waitForRedirectTo(String redirectUrl) {
        log.info("Waiting for redirect url: " + redirectUrl);
        page.waitForURL(redirectUrl);
        log.debug("Waiting for redirect url: " + redirectUrl);
        return this;
    }

    SSOAuthorizationPage waitForVerificationCode() {
        Locator elementLocator = page.locator("//h4[normalize-space()='Cod de verificare:']");
        waitForElement(elementLocator);
        return this;
    }


    private Page locateRedirectedPage(String redirectUrl) {
        if (matchesRedirect(page.url(), redirectUrl)) {
            return page;
        }
        return page.context().pages().stream()
                .filter(candidate -> matchesRedirect(candidate.url(), redirectUrl))
                .findFirst()
                .orElse(null);
    }

    private boolean matchesRedirect(String url, String redirectUrl) {
        return url != null && (url.equals(redirectUrl) || url.startsWith(redirectUrl) || url.contains(redirectUrl));
    }

    void waitForElement(Locator elementLocator) {
        with().pollDelay(3, TimeUnit.SECONDS).and().pollInterval(1, TimeUnit.SECONDS)
                .alias("Failed to wait for element: " + elementLocator)
                .await().atMost(15, TimeUnit.SECONDS)
                .until(elementLocator::isEnabled);
    }
}
