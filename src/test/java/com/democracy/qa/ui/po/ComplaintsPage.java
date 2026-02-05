package com.democracy.qa.ui.po;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.awaitility.Awaitility.with;

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
        page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("I agree to the processing of")).check();
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

    public ComplaintsPage clickContinue() {
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


    public ComplaintsPage fillProductName(String product) {
        Locator productInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("ex. telefon mobil, bilet"));
        productInput.click();
        productInput.fill(product);
        return this;
    }

    public ComplaintsPage fillTypeOfIssue(String typeOfIssue) {
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Alege tipul problemei").setExact(true)).click();
        page.getByText(typeOfIssue).click();
        return this;
    }

    public ComplaintsPage selectFirstIssueCategory() {
        page.getByRole(AriaRole.TEXTBOX,
                        new Page.GetByRoleOptions().setName("Alege categoria").setExact(true))
                .click();
        Locator firstOption = page.locator("div.hoverable-option").first();
        firstOption.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        firstOption.click();

        return this;
    }

    public ComplaintsPage selectFirstIssue() {
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Alege problema"))
                .click();
        Locator firstOption = page.locator("div.hoverable-option").first();
        firstOption.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        firstOption.click();

        return this;
    }

    public ComplaintsPage selectRandomDesiredOutcome() {
        Locator desiredOutcomeDropdown = page.getByText("Alege una sau mai multe opț");
        desiredOutcomeDropdown.click();
        page.locator(".notranslate.rz-chkbox-box").first().click();
        return this;
    }

    public ComplaintsPage populateEditorTemplate(String templatePath) {
        String normalizedText = templatePath
                .replace("\r\n", "\n")
                .replace("\r", "\n");
        Locator editor = page.locator(".rz-html-editor-content");
        editor.fill(normalizedText);
        return this;
    }

    public ComplaintsPage uploadFile() throws URISyntaxException {
        var resource = getClass().getClassLoader().getResource("uploadFile.pdf");
        if (resource == null) {
            throw new IllegalStateException("uploadFile.pdf missing from resources");
        }

        Path filePath = Paths.get(resource.toURI());

        FileChooser chooser = page.waitForFileChooser(() -> {
            page.getByRole(AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName("Adaugă fișier")).click();
        });

        chooser.setFiles(filePath);

        return this;
    }

    public ComplaintsPage fillDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("EEEE, dd MMMM", new Locale("ro", "RO"));
        String formattedDate = today.format(formatter);
        page.getByRole(AriaRole.TEXTBOX,
                        new Page.GetByRoleOptions().setName("Selectează"))
                .click();
        page.getByRole(AriaRole.BUTTON,
                        new Page.GetByRoleOptions().setName(formattedDate))
                .click();

        return this;
    }

    public ComplaintsPage verifyEmail(String email) {
        Locator emailLocator = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("ex. irina.ciobanu@gmail.com"));
        emailLocator.click();
        emailLocator.fill(email);
        return this;
    }

    private void waitForElement(Locator elementLocator) {
        with().pollDelay(3, TimeUnit.SECONDS).and().pollInterval(1, TimeUnit.SECONDS)
                .alias("Failed to wait for element: " + elementLocator)
                .await().atMost(15, TimeUnit.SECONDS)
                .until(elementLocator::isEnabled);
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
