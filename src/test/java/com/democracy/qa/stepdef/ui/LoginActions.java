package com.democracy.qa.stepdef.ui;

import com.democracy.qa.exceptions.TestException;
import com.democracy.qa.model.atf.ATFBrowserContext;
import com.democracy.qa.util.ScenarioContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LoginActions {

    @Autowired
    ATFBrowserContext<?> browserContext;

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
    private ScenarioContext scenarioContext;

    public void loginUserPortal() throws TestException {
        Page page = browserContext.getPage();

        page.navigate(appUrl);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Permite tot")).click();
        page.getByTitle("Autentifică-te").click();
        page.waitForURL(url -> url.contains(oauthHost));

        SSOAuthorizationPage authorizationPage = new SSOAuthorizationPage(page);
        authorizationPage.fillNumber(oauthClientNumber)
                .clickSubmitButton()
                .waitForVerificationCode()
                .waitForRedirectTo(appUrl);
    }
}
