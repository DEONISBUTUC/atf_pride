package com.democracy.qa.service;




import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthService {

    @Value("${tokenHost}")
    private String tokenHost;

    @Value("${redirect.uri}")
    private String redirectUri;

    @Value("${admin.client.id}")
    private String adminClientId;

    @Value("${admin.client.secret}")
    private String adminClientSecret;

    @Value("${integrator.client.id}")
    private String integratorClientId;

    @Value("${integrator.client.secret}")
    private String integratorClientSecret;

//    @Autowired
//    AdminLoginActions adminLoginActions;
//
//    @Autowired
//    RestClient restClient;
//
//    @Autowired
//    JsonUtil jsonUtil;
//
//    @Autowired
//    StaticAuthFileUtil authFileUtil;

    @Getter
    private String providerSessionToken;

//    public void setProviderSessionToken(String providerUsername, String providerPassword) {
//        this.providerSessionToken = "Basic " + encodeLoginDetails(providerUsername, providerPassword);
//    }

    public void setProviderSessionToken(String token) {
        this.providerSessionToken = "Bearer " + token;
    }

    @Getter
    @Setter
    private String cdeAuthToken;

//    public String getAdminSessionToken() throws TestException, IOException {
//        if(authFileUtil.isAdminTokenValid()) {
//            log.info("Fetching access token for admin from file");
//            return "Bearer " + authFileUtil.getAdminToken();
//        } else {
//            log.info("Generating new access token for admin");
//            String tkn = generateAdminToken();
//            log.info("Writing new access token to static file for later use");
//            authFileUtil.setAdminToken(tkn);
//            return "Bearer " + tkn;
//        }
//    }


//    public String getIntegratorSessionToken() throws TestException, IOException {
//        if(authFileUtil.isIntegratorTokenValid()) {
//            log.info("Fetching access token for integrator from file");
//            return "Bearer " + authFileUtil.getIntegratorToken();
//        } else {
//            log.info("Generating new access token for admin");
//            String tkn = generateIntegratorToken();
//            log.info("Writing new access token to static file for later use");
//            authFileUtil.setIntegratorToken(tkn);
//            return "Bearer " + tkn;
//        }
//    }
//
//    private String generateAdminToken() throws TestException {
//        String code = adminLoginActions.getAdminAuthCode();
//        String tokenRes = requestAdminToken(code);
//        return jsonUtil.getJsonElementValue(tokenRes, "access_token");
//    }
//
//    private String generateIntegratorToken() throws TestException {
//        String tokenRes = requestIntegratorToken();
//        return jsonUtil.getJsonElementValue(tokenRes, "access_token");
//    }
//
//    private String requestAdminToken(String code) throws TestException {
//        HttpHeaders headers = createBasicAuthHeader(adminClientId, adminClientSecret);
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
//        form.add("grant_type", "authorization_code");
//        form.add("code", code);
//        form.add("redirect_uri", redirectUri);
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);
//        ResponseEntity<?> response = restClient.post(tokenHost, request, headers);
//
//        if(!response.getStatusCode().is2xxSuccessful()) {
//            throw new TestException("Failed to obtain access token");
//        }
//        return response.getBody().toString();
//    }
//
//    private String requestIntegratorToken() throws TestException {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
//        form.add("grant_type", "client_credentials");
//        form.add("client_id", integratorClientId);
//        form.add("client_secret", integratorClientSecret);
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);
//        ResponseEntity<?> response = restClient.post(tokenHost, request, headers);
//
//        if(!response.getStatusCode().is2xxSuccessful()) {
//            throw new TestException("Failed to obtain access token");
//        }
//        return response.getBody().toString();
//    }
//
//    private HttpHeaders createBasicAuthHeader(String username, String password){
//        return new HttpHeaders() {{
//            String authHeader = "Basic " + encodeLoginDetails(username, password);
//            set("Authorization", authHeader );
//        }};
//    }
//
//    private String encodeLoginDetails(String username, String password) {
//        String auth = username + ":" + password;
//        byte[] encodedAuth = Base64.encodeBase64(
//                auth.getBytes(Charset.forName("US-ASCII")) );
//        return new String( encodedAuth );
//    }
}
