package com.democracy.qa.stepdef.ui;

import io.cucumber.java.en.Given;
import com.democracy.qa.model.atf.ATFBrowserContext;
import com.democracy.qa.ui.po.ComplaintsActions;
import org.springframework.beans.factory.annotation.Autowired;

public class ComplaintsSteps {

    @Autowired
    private ComplaintsActions complaintsActions;

    @Autowired
    private LoginActions loginActions;

    @Given("utilizatorul se logeaza pe edemocratie")
    public void utilizatorulSeAutentificaInPortal() throws com.democracy.qa.exceptions.TestException {
        loginActions.loginUserPorta();
    }

}
