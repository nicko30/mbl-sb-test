package demo.step_definitions;

import demo.pages.page_object.LoginPage;
import io.cucumber.java8.En;

public class LoginSteps implements En {

    LoginPage loginPage = new LoginPage();

    public LoginSteps() {
        And("User on the login page", () -> loginPage.isLoginPage());

        And("^User input \"([^\"]*)\" as username$", (String username) -> loginPage.inputUsername(username));

        And("^User input \"([^\"]*)\" as password$", (String password) -> loginPage.inputPassword(password));

        And("User tap on login button", () -> loginPage.tapOnLoginButton());
    }
}
