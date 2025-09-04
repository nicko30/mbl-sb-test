package demo.step_definitions;

import demo.pages.page_object.CartPage;
import io.cucumber.java8.En;

public class CartSteps implements En {

    CartPage cartPage = new CartPage();

    public CartSteps() {
        And("User tap on proceed to checkout", () -> cartPage.tapOnProceedToCheckout());

        And("User on cart page", () -> cartPage.isCartPage());

        And("User validate total product price", () -> cartPage.validateTotalPrice());
    }
}
