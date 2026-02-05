package com.democracy.qa.model.atf;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@ScenarioScope
@Data
@NoArgsConstructor
public class ATFBrowserContext<T> {
    private Browser browser;
    private Page page;
    private T pageObject;
}

