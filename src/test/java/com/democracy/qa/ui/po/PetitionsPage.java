package com.democracy.qa.ui.po;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PetitionsPage {

    private final Page page;

    public PetitionsPage(Page page) {
        this.page = page;
    }

    PetitionsPage open(String url) {
        log.info("Opening Petitions page" + url);
        page.navigate(url);
        return this;
    }

    PetitionsPage submitIndividualPetition() {
        log.info("Click submit individual petition");
        Locator submitIndividualPetitionLocator = page.locator("#submitIndividualPetition");
        submitIndividualPetitionLocator.click();
        return this;
    }

    PetitionsPage submitCollectivePetition() {
        log.info("Click submit individual petition");
        Locator submitCollectivePetitionLocator = page.locator("#initiateCollectivePetition");
        submitCollectivePetitionLocator.click();
        return this;
    }
}
