package com.democracy.qa.ui.po;


import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import com.democracy.qa.model.atf.ATFBrowserContext;
import com.democracy.qa.ui.BrowserInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component

public class ComplaintsActions {
    @Autowired
    BrowserInstance browserInstance;

    public ATFBrowserContext<?> accessLink(String paymentLink) {
        Browser browser = browserInstance.getBrowser();
        Page page = browser.newPage();
        Complaints complaintsFormPage = new Complaints(page);
        complaintsFormPage.open(paymentLink);
        return new ATFBrowserContext<>(browser, page, complaintsFormPage);
    }
}
