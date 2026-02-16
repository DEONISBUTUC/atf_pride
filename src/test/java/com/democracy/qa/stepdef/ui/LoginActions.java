package com.democracy.qa.stepdef.ui;

import com.democracy.qa.exceptions.TestException;
import com.democracy.qa.ui.BrowserInstance;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LoginActions {

    @Value("${oauthHost}")
    private String oauthHost;

    @Value("${oauthClientNumber}")
    private String oauthClientNumber;

    @Value("${redirectUri}")
    private String redirectUri;

    @Value("${app.url}")
    private String appUrl;

    @Value("${redirectUrll}")
    private String redirectUrl;

    @Autowired
    BrowserInstance browserInstance;


//    public String getAdminAuhCode() throws TestException {
//        Browser browser = browserInstance.getBrowser();
//        Page page = browser.newPage();
//        SSOAuthorizationPage authorizationPage = new SSOAuthorizationPage(page);
//        authorizationPage.open(oauthHost)
//                .fillNumber(oauthClientNumber)
//                .clickSubmitButton()
//                .waitForRedirectPage(redirectUri);
//        browser.close();
//        return authorizationPage.getAuthorizationCode();
//    }

    public void loginUserPorta() throws TestException {
        Browser browser = browserInstance.getBrowser();
        Page page = browser.newPage();
        page.navigate(appUrl);
        page.locator("//a[@title='Autentifică-te']").click();
        page.waitForURL(url -> url.contains(oauthHost));
        SSOAuthorizationPage authorizationPage = new SSOAuthorizationPage(page);
        authorizationPage.fillNumber(oauthClientNumber)
                .clickSubmitButton()
                .waitForVerificationCode()
                .waitForRedirectTo(appUrl);


    }
}
