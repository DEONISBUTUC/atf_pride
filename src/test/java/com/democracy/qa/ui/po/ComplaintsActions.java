package com.democracy.qa.ui.po;


import com.democracy.qa.model.atf.ATFBrowserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ComplaintsActions {

    private final ATFBrowserContext<?> browserContext;

    @Autowired
    public ComplaintsActions(ATFBrowserContext<?> browserContext) {
        this.browserContext = browserContext;
    }

    public void initiateComplaintsProcess() {
        ComplaintsPage complaintsPage = new ComplaintsPage(browserContext.getPage());
        complaintsPage
                .clickComplaintsHeader()
                .clickStartComplaint();
    }

    public void completeVerifyIdentityStep(String phoneNumber, String email) {
        ComplaintsPage complaintsPage = new ComplaintsPage(browserContext.getPage());
        complaintsPage
                .fillPhoneNumber(phoneNumber)
                .fillIdentityEmail(email)
                .clickAcceptTerms()
                .clickContinueIdentityVerification();
    }

    public void completeAboutCompanyStep(String companyName, String companyAddress, String companyEmail) {
        ComplaintsPage complaintsPage = new ComplaintsPage(browserContext.getPage());
        complaintsPage
                .fillCompanyName(companyName)
                .fillCompanyAddress(companyAddress)
                .fillCompanyEmail(companyEmail)
                .clickContinueAboutCompany();
    }
}
