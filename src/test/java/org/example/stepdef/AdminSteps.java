package org.example.stepdef;

import groovy.util.logging.Slf4j;
import io.cucumber.java.en.Given;
import org.example.exceptions.TestException;
import org.example.stepdef.ui.BaseSteps;

import java.io.IOException;

@Slf4j
public class AdminSteps extends BaseSteps {
    @Given("an admin user is authenticated")
    public void loginAdminSSO() throws TestException, IOException {
        String adminToken = authService.getAdminSessionToken();
        log.info("Admin session token generated: {}", adminToken);
        log.info("Admin session token is: {}", authService.getAdminSessionToken());
    }

    @Given("an integrator user is authenticated")
    public void loginIntegratorSSO() throws TestException, IOException {
        String integratorToken = authService.getIntegratorSessionToken();
        log.info("Integrator session token generated: {}", integratorToken);
        log.info("Integrator session token is: {}", authService.getIntegratorSessionToken());
    }

    @Given("{string} este authorizat")
    public void petitionarulEsteAuthorizat() throws TestException, IOException {
        String token = authService.getCustomUserSessionToken("petitioner");
        log.info("Petitioner session token generated: {}", token);
        log.info("Petitioner session token is: {}", authService.getCustomUserSessionToken("petitioner"));
    }
}
