package demo.step_definitions;

import demo.pages.page_object.ProductPage;
import io.cucumber.java8.En;

public class ProductSteps implements En {

    ProductPage productPage = new ProductPage();

    public ProductSteps() {
        Given("User on the products page", () -> productPage.isProductPage());

        When("^User choose product \"([^\"]*)\"$", (String productName) -> productPage.tapOnProduct(productName));

        And("^User choose type \"([^\"]*)\"$", (String color) -> productPage.tapOnColor(color));

        And("^User add quantity to \"([^\"]*)\"$", (String quantity) -> productPage.addQuantity(quantity));

        And("User tap on add to cart", () -> productPage.tapOnAddToCartButton());

        And("User tap on cart icon", () -> productPage.tapOnCartIcon());
    }
}
