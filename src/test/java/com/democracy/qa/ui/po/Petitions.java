package com.democracy.qa.ui.po;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Petitions {

    private final Page page;

    public Petitions(Page page) {
        this.page = page;
    }

    Petitions open(String url) {
        log.info("Opening Petitions page" + url);
        page.navigate(url);
        return this;
    }

    Petitions submitIndividualPetition() {
        log.info("Click submit individual petition");
        Locator submitIndividualPetitionLocator = page.locator("#submitIndividualPetition");
        submitIndividualPetitionLocator.click();
        return this;
    }

    Petitions submitCollectivePetition() {
        log.info("Click submit individual petition");
        Locator submitCollectivePetitionLocator = page.locator("#initiateCollectivePetition");
        submitCollectivePetitionLocator.click();
        return this;
    }
}
