package demo.pages.page_object;

import demo.pages.base.BasePageObject;

public class LoginPage extends BasePageObject {

    public void isLoginPage() {
        assertIsPresent("TEXT_LABEL_LOGIN");
    }

    public void inputUsername(String email) {
        typeOn("TEXT_FIELD_USERNAME", email);
    }

    public void inputPassword(String password) {
        typeOn("TEXT_FIELD_PASSWORD", password);
    }

    public void tapOnLoginButton() {
        tapOn("BUTTON_LOGIN");
    }
}
