package com.democracy.qa.data.generator;

import com.github.javafaker.Faker;
import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class DataGenerator {
    private Faker faker;

    @PostConstruct
    private void setFaker() {
        faker = new Faker();
    }

    public String generateRandomNumber(String length) {
        String gN = faker.number().digits(Integer.parseInt(length));
        return gN.indexOf("0") == 0 ? gN.replaceFirst("0", "1") : gN;
    }

    public String generateRandomString(String length) {
        return faker.lorem().characters(Integer.parseInt(length));
    }

    public String generateRandomCompanyName() {
        return faker.company().name();
    }

    public String generateRandomProductName() {
        return faker.commerce().productName();
    }

    public String generateRandomCompanyAddress() {
        return faker.address().streetAddress();
    }

    public String generateRandomName() {
        return faker.company().name();
    }

    public String generateRandomProviderName() {
        return sanitize(faker.company().name()).concat("Provider");
    }

    public String generateRandomEmail() {
        return faker.internet().safeEmailAddress();
    }

    public String generateRandomAlphanumeric(String length) {
        RandomStringGenerator rsd = new RandomStringGenerator.Builder().withinRange('0', 'z')
                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                .build();

        return rsd.generate(Integer.parseInt(length));
    }

    public String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }

    public String dateTimeNow() {
        return LocalDateTime.now().toString();
    }

    public String dateTimeNow(String pattern) {
        return dateTimeByPattern(pattern, null);
    }

    public String dateTimeNow(String pattern, String zone) {
        return dateTimeByPattern(pattern, zone);
    }

    public String dateTimeNowInMillis(String zone) {
        LocalDateTime local = LocalDateTime.now();
        return String.valueOf(local.atZone(ZoneId.of(zone)).toInstant().toEpochMilli());
    }

    public String generatePayPalPaymentId() {
        return "PAY-" + generateRandomAlphanumeric("24").toUpperCase();
    }

    public String generatePayPalCaptureId() {
        return UUID.randomUUID().toString();
    }

    public String generatePayPalInvoiceNumber() {
        return UUID.randomUUID().toString();
    }

    public String generatePayPalNotificationId() {
        return "WH-"
                + generateRandomAlphanumeric("17").toUpperCase()
                + "-"
                + generateRandomAlphanumeric("17").toUpperCase();
    }

    public String generatePayPalRefundId() {
        return generateRandomAlphanumeric("17").toUpperCase();
    }

    public String generateAdyenSessionId() {
        return generateRandomAlphanumeric("18").toUpperCase();
    }

    public String generateMerchantReference() {
        return "MerchantReference-" + generateRandomAlphanumeric("8").toUpperCase();
    }

    public String generateReferenceId() {
        return generateRandomAlphanumeric("5").toUpperCase();
    }

    public String generatePaypalBillingAgreementToken() {
        return "BA-" + generateRandomAlphanumeric("17").toUpperCase();
    }

    public String generatePaypalBillingAgreementId() {
        return "B-" + generateRandomAlphanumeric("17").toUpperCase();
    }

    public String generatePaymentOsId() {
        return "PAYUTEST-" + "ABCD-" + "1234-" + generateRandomAlphanumeric("16").toUpperCase();
    }

    public String generateAciTransactionId() {
        return generateRandomAlphanumeric("32");
    }

    public String generateAciMerchantTransactionId() {
        return generateRandomAlphanumeric("22");
    }

    public String generateTextDescription(String length) {
        return faker.lorem().characters(Integer.parseInt(length));
    }

    public String generateCarVIN() {
        return "WDDUG8DB0JA" + generateRandomNumber("5");
    }

    public String generateFiservTerminalId() {
        return "150" + generateRandomNumber("5");
    }

    private String dateTimeByPattern(String pattern, String zone) {
        LocalDateTime localDateTime;
        if (zone != null) {
            ZoneId zoneId = ZoneId.of(zone);
            localDateTime = LocalDateTime.now(zoneId);
        } else {
            localDateTime = LocalDateTime.now();
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    private String sanitize(String input) {
        return input.replaceAll("'", "")
                .replaceAll("\"", "")
                .replaceAll("-", "")
                .replaceAll("_", "")
                .replaceAll(" ", "")
                .replaceAll(",", "")
                .trim();
    }
}
