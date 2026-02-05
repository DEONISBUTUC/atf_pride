package com.democracy.qa.stepdef.ui;

import com.democracy.qa.stepdef.BaseSteps;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginPageSteps extends BaseSteps {

    private final Page page;

    LoginPageSteps(final Page page) {
        this.page = page;
    }

    LoginPageSteps open(String url) {
        log.info("Opening Form page " + url);
        page.navigate(url);
        return this;
    }

    LoginPageSteps clickLoginButton() {
        log.info("Clicking login button");
        Locator loginButon = page.locator("//a[@title='Autentifică-te']");
        loginButon.click();
        return this;
    }


}



