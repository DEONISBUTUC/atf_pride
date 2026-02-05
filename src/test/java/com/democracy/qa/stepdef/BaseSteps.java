package com.democracy.qa.stepdef;

import com.democracy.qa.data.DataProvider;
import com.democracy.qa.service.RestClient;
import com.democracy.qa.util.ScenarioContext;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseSteps {
    @Autowired
    protected ScenarioContext scenarioContext;

    @Autowired
    protected DataProvider dataProvider;

    @Autowired
    protected RestClient restClient;
}
