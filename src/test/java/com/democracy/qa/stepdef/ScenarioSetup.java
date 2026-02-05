package com.democracy.qa.stepdef;

import com.democracy.qa.TestConfig;
import com.democracy.qa.exceptions.TestResourceNotFoundException;
import com.democracy.qa.model.atf.ATFBrowserContext;
import com.democracy.qa.util.SCKey;
import io.cucumber.java.*;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import com.democracy.qa.exceptions.TestException;
import com.democracy.qa.util.ScenarioContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {TestConfig.class})
@CucumberContextConfiguration
public class ScenarioSetup {

    @Autowired
    private ScenarioContext scenarioContext;

    @Before
    public void before(Scenario scenario) throws TestException {
        log.info("==============================================================================");
        log.info(scenario.getName());
        log.info("==============================================================================");
        scenarioContext.clearScenarioContext();
    }

    @After()
    public void after(Scenario scenario) throws TestResourceNotFoundException {
        log.info("==============================================================================");
        log.info("Performing Scenario Tear-Down operations");

        if(scenarioContext.scenarioResourceExists(SCKey.BROWSER_CONTEXT)) {
            ATFBrowserContext browserContext = scenarioContext.getScenarioResource(SCKey.BROWSER_CONTEXT);
            browserContext.getBrowser().close();
        }
        log.info("==============================================================================");
    }


}
