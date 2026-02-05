package org.example.stepdef.ui.po;

import org.example.browser.BrowserInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class AdminLoginActions {
    @Value("${admin.openId.username}")
    private String username;

    @Value("${admin.openId.password}")
    private String password;

    @Value("${admin.openId.secret}")
    private String secret;

    @Value("${oauthHost}")
    private String host;

    @Value("${admin.client.id}")
    private String adminClientId;

    @Value("${response.type}")
    private String responseType;

    @Value("${redirect.uri}")
    private String redirectUri;

    @Value("${scope}")
    private String scope;

    @Autowired
    BrowserInstance browserInstance;
}
