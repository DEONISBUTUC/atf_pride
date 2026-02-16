package com.democracy.qa.model.atf;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ATFBrowserContext<T> {

    private Browser browser;
    private Page page;
    private T pageObject;
}
