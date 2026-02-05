package com.democracy.qa.stepdef.ui;

import com.democracy.qa.data.generator.TemplateReader;
import com.democracy.qa.exceptions.TestException;
import com.democracy.qa.stepdef.BaseSteps;
import com.democracy.qa.ui.model.IssueMapperTemplate;
import com.democracy.qa.ui.po.ComplaintsActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URISyntaxException;
import java.util.Map;

public class ComplaintsSteps extends BaseSteps {

    @Autowired
    private ComplaintsActions complaintsActions;

    @Autowired
    private TemplateReader templateReader;

    @And("utilizatorul completeaza pasul 1 cu datele:")
    public void userCompletesStep1Data(DataTable dataTable) throws TestException {
        scenarioContext.updateScenarioDataSet(dataTable.asMap());
        Map<String, String> data = scenarioContext.getScenarioDataSet();
        complaintsActions.completeVerifyIdentityStep(
                data.get("phoneNumber"),
                data.get("email")
        );
    }

    @And("utilizatorul completeaza pasul 2 cu datele:")
    public void userCompletesStep2Data(DataTable dataTable) throws TestException {
        scenarioContext.updateScenarioDataSet(dataTable.asMap());
        Map<String, String> data = scenarioContext.getScenarioDataSet();
        complaintsActions.completeAboutCompanyStep(
                data.get("companyName"),
                data.get("companyAddress"),
                data.get("companyEmail")
        );
    }

    @And("utilizatorul completeaza pasul 3 cu datele:")
    public void userCompletesStep3Data(DataTable dataTable) throws TestException, URISyntaxException {
        scenarioContext.updateScenarioDataSet(dataTable.asMap());
        Map<String, String> data = scenarioContext.getScenarioDataSet();
        String templatePath = IssueMapperTemplate.getTemplate(data.get("typeOfIssue"));
        String templateText = templateReader.readTemplate(templatePath);
        complaintsActions.completeWhatHappenedStep(
                data.get("productName"),
                data.get("typeOfIssue"),
                templateText
        );
    }

    @And("utilizatorul completeaza pasul 4 cu datele:")
    public void utilizatorulCompleteazaPasulCuDatele(DataTable dataTable) throws TestException {
        scenarioContext.updateScenarioDataSet(dataTable.asMap());
        Map<String, String> data = scenarioContext.getScenarioDataSet();
        complaintsActions.verifyAndConfirm(
                data.get("email")
        );
    }
}
