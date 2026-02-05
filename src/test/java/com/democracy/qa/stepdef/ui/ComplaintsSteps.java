package com.democracy.qa.stepdef.ui;

import com.democracy.qa.exceptions.TestException;
import com.democracy.qa.stepdef.BaseSteps;
import com.democracy.qa.ui.po.ComplaintsActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;

public class ComplaintsSteps extends BaseSteps {

    @Autowired
    private ComplaintsActions complaintsActions;

    @And("utilizatorul completeaza pasul 1 cu datele:")
    public void userCompletesStep1Data(DataTable dataTable) throws TestException {
        scenarioContext.updateScenarioDataSet(dataTable.asMap());
        String phoneNumber = scenarioContext.getScenarioDataSet().get("phoneNumber");
        String email = scenarioContext.getScenarioDataSet().get("email");
        complaintsActions.completeVerifyIdentityStep(phoneNumber, email);

    }

    @And("utilizatorul completeaza pasul 2 cu datele:")
    public void userCompletesStep2Data(DataTable dataTable) throws TestException {
        scenarioContext.updateScenarioDataSet(dataTable.asMap());
        String companyName = scenarioContext.getScenarioDataSet().get("companyName");
        String companyAddress = scenarioContext.getScenarioDataSet().get("companyAddress");
        String companyEmail = scenarioContext.getScenarioDataSet().get("companyEmail");
        complaintsActions.completeAboutCompanyStep(companyName, companyAddress, companyEmail);
    }

    @And("utilizatorul completeaza pasul 3 cu datele:")
    public void userCompletesStep3Data(DataTable dataTable) throws TestException {
        scenarioContext.updateScenarioDataSet(dataTable.asMap());
        String product = scenarioContext.getScenarioDataSet().get("productName");
        
    }
}
