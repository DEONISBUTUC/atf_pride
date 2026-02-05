package com.democracy.qa.ui.po;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@Slf4j
public class ComplaintsPage {

    private final Page page;
    @Value("${reclamatii}")
    private String reclamatii;
    @Value("${phoneNumber}")
    private String phoneNumber;

    public ComplaintsPage(Page page) {
        this.page = page;
    }

    ComplaintsPage open(String url) {
        log.info("Opening Petitions page" + url);
        page.navigate(url);
        return this;
    }

    ComplaintsPage startComplaint() {
        log.info("Click submit individual petition");
        Locator startComplaint = page.locator("#startComplaint");
        startComplaint.click();
        return this;
    }

    ComplaintsPage submitPetition() {
        log.info("Click submit individual petition");
        Locator submitPetition = page.locator("#submitPetition");
        submitPetition.click();
        return this;
    }

    public ComplaintsPage open() {
        log.info("Opening Reclamtii page " + reclamatii);
        page.navigate(reclamatii);
        return this;
    }

    public ComplaintsPage clickComplaintsHeader() {
        log.info("Clicking Reclamtii page " + reclamatii);
        page.locator("#header").getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName("Reclamații")).click();
        return this;
    }

    public ComplaintsPage clickStartComplaint() {
        log.info("Clicking on 'Începe reclamația' button");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Începe reclamația")).click();
        return this;
    }

    public String getReclamtiiTitle() {
        log.info("Getting Reclamtii title");
        return page.title();
    }

    public ComplaintsPage fillPhoneNumber(String number) {
        log.info("Filling phone number: " + number);
        Locator phoneField = page.getByRole(AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("ex. +373"));
        phoneField.click();
        phoneField.fill(number);
        return this;
    }

    public ComplaintsPage clickAcceptTerms() {
        log.info("Accepting terms and conditions");
        page.getByText("I agree to the processing of").click();
        return this;
    }

    public ComplaintsPage fillIdentityEmail(String email) {
        Locator emailInput = page.getByRole(AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("ex. irina.ciobanu@gmail.com"));
        emailInput.click();
        emailInput.fill(email);
        return this;
    }

    public ComplaintsPage acceptPersonalDataProcessing() {
        page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Accept prelucrarea datelor"))
                .check();
        return this;
    }

    public ComplaintsPage clickContinueIdentityVerification() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuă"))
                .click();
        return this;
    }

    public ComplaintsPage fillCompanyName(String companyName) {
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("SRL Exemplu Com"))
                .fill(companyName);
        return this;
    }

    public ComplaintsPage fillCompanyAddress(String companyAddress) {
        Locator addressInput = page.getByRole(AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("str. Ștefan cel Mare 123, mun"));
        addressInput.click();
        addressInput.fill(companyAddress);
        return this;
    }

    public ComplaintsPage fillCompanyEmail(String companyEmail) {
        Locator emailInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("contact@exemplu.com"));
        emailInput.click();
        emailInput.fill(companyEmail);
        return this;
    }

    public ComplaintsPage clickContinueAboutCompany() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continuă"))
                .click();
        return this;
    }

    public ComplaintsPage aboutTheCompany() {
        return fillCompanyName("System Pride")
                .fillCompanyAddress("str. Ștefan cel Mare 123, mun")
                .fillCompanyEmail("dbutuc91@gmail.com")
                .clickContinueAboutCompany();
    }

    private void waitForPaymentFormToLoad() {
        if (page.frames().size() < 2) {
            log.info("Waiting for payment form to be loaded");
            await().atMost(10, TimeUnit.SECONDS).pollDelay(1, TimeUnit.SECONDS).until(this::pageFramesLoaded);
        }
    }

    private boolean pageFramesLoaded() {
        forceReloadFrames();
        return page.frames().size() == 2;
    }

    private void forceReloadFrames() {
        page.title();
    }
}
