package com.democracy.qa.ui.core;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

/**
 * Shared base for all Page Objects.
 * Contains generic, readable actions based on accessibility roles.
 */
public abstract class BasePage {

    protected final Page page;

    protected BasePage(Page page) {
        this.page = page;
    }

    public void clickButtonByText(String text) {
        page.getByRole(AriaRole.BUTTON,
                        new Page.GetByRoleOptions().setName(text))
                .click();
    }

    public void clickLinkByText(String text) {
        page.getByRole(AriaRole.LINK,
                        new Page.GetByRoleOptions().setName(text))
                .click();
    }
}

