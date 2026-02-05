package org.example.stepdef.ui;

import org.example.context.ScenarioContext;
import org.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseSteps {

    @Autowired
    protected ScenarioContext scenarioContext;

    @Autowired
    protected AuthService authService;


}
