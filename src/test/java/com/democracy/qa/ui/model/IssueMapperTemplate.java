package com.democracy.qa.ui.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum IssueMapperTemplate {

    SERVICII_POSTALE("Servicii postale", "/templates/servicii_postale.txt"),
    PRODUSE_NEALIMENTARE("Produse nealimentare", "templates/produse_nealimentare.txt"),
    SIGURANTA_ALIMENTELOR("Siguranta alimentelor", "templates/siguranta_alimentelor.txt"),
    METROLOGIE("Metrologie", "templates/metrologie.txt"),
    TRANSPORT_AERIAN("Transport aerian", "templates/transport_aerian.txt");

    private final String operation;
    private final String requestTemplate;

    public static String getTemplate(String operation) {
        for (IssueMapperTemplate c : values()) {
            if (c.operation.equalsIgnoreCase(operation)) {
                return c.requestTemplate;
            }
        }
        return "not_found";
    }
}