package org.example.hooks;

import com.microsoft.playwright.*;
import io.cucumber.java.*;
import org.example.browser.BrowserFactory;
import org.example.context.ScenarioContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ExampleHooks demonstrates how to use Cucumber lifecycle hooks within a
 * testing framework. Hooks can prepare test data, initialize drivers, or
 * perform cleanup after execution.
 */
public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    private ScenarioContext scenarioContext;
    private BrowserFactory browserFactory;


    @Before
    public void before (Scenario scenario) {
        logger.info("================================");
        logger.info(" Starting scenario: {}", scenario.getName());
        scenarioContext.clearScenarioContext();
    }

    @After
    public void tearDownScenario(Scenario scenario) {
        logger.info(" Tearing down after scenario: {}", scenario.getName());
        browserFactory.closeAll();
        logger.info("Scenario context cleared.");
    }

}
