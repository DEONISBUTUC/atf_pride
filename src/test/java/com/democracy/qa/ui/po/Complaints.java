package com.democracy.qa.ui.po;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Complaints {

    private final Page page;

    public Complaints(Page page) {
        this.page = page;
    }

    Complaints open(String url) {
        log.info("Opening Petitions page" + url);
        page.navigate(url);
        return this;
    }

    Complaints startComplaint() {
        log.info("Click submit individual petition");
        Locator startComplaint = page.locator("#startComplaint");
        startComplaint.click();
        return this;
    }

    Complaints submitPetition() {
        log.info("Click submit individual petition");
        Locator submitPetition = page.locator("#submitPetition");
        submitPetition.click();
        return this;
    }
}
