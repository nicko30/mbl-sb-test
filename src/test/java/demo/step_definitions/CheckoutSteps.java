package demo.step_definitions;

import demo.pages.page_object.CheckoutPage;
import io.cucumber.java8.En;

public class CheckoutSteps implements En {

    CheckoutPage checkoutPage = new CheckoutPage();

    public CheckoutSteps() {
        And("User on the checkout page", () -> checkoutPage.userOnTheCheckoutPage());

        And("^User input \"([^\"]*)\" as full name$", (String fullName) -> checkoutPage.inputTextField("FULL NAME", fullName));

        And("^User input \"([^\"]*)\" as address line 1$", (String address) -> checkoutPage.inputTextField("ADDRESS LINE 1", address));

        And("^User input \"([^\"]*)\" as city$", (String city) -> checkoutPage.inputTextField("CITY", city));

        And("^User input \"([^\"]*)\" as country$", (String country) -> checkoutPage.inputTextField("COUNTRY", country));

        And("^User input \"([^\"]*)\" as postal code$", (String postalCode) -> checkoutPage.inputTextField("POSTAL CODE", postalCode));

        And("User tap on to payment", () -> checkoutPage.tapOnToPayment());

        And("^User input \"([^\"]*)\" as full name on card$", (String cardFullName) -> checkoutPage.inputTextField("NAME ON CARD", cardFullName));

        And("^User input \"([^\"]*)\" as card number$", (String cardNumber) -> checkoutPage.inputTextField("CARD NUMBER", cardNumber));

        And("^User input \"([^\"]*)\" as expiry date$", (String expiryDate) -> checkoutPage.inputTextField("EXPIRY DATE", expiryDate));

        And("^User input \"([^\"]*)\" as security code$", (String securityCode) -> checkoutPage.inputTextField("SECURITY CODE", securityCode));

        And("User tap on review order", () -> checkoutPage.tapOnReviewOrder());

        Then("User verify order success", () -> checkoutPage.userVerifyOrderSuccess());

        And("User tap on place order", () -> checkoutPage.tapOnPlaceOrder());

        And("User validate total bills", () -> checkoutPage.validateTotalBills());
    }
}
