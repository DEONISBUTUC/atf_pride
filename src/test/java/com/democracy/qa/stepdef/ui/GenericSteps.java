package com.democracy.qa.stepdef.ui;

import com.democracy.qa.exceptions.TestException;
import com.democracy.qa.stepdef.BaseSteps;
import com.democracy.qa.ui.po.ComplaintsActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class GenericSteps extends BaseSteps {

    @Autowired
    private LoginActions loginActions;

    @Autowired
    private ComplaintsActions complaintsActions;

    @Given("utilizatorul se logeaza pe edemocratie")
    public void userLogsIntoEdemocracyPortal() throws TestException {
        loginActions.loginUserPortal();
    }

    @When("utilizatorul initiaza procesul de creare a unei {string}")
    public void userInitiatesCreationProcessFor(String type) throws TestException {
        switch (type) {
            case "PETITIE_INDIVIDUALA" -> {
            }
            case "PETITIE_COLECTIVA" -> {
            }
            case "CERERE_DE_INFORMATIE" -> {
            }
            case "Reclamatii" -> complaintsActions.initiateComplaintsProcess();
            case "PETITIE_CONSUMATOR" -> {
            }
        }
    }

}
