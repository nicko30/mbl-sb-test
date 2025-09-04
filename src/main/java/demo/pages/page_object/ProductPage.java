package demo.pages.page_object;

import demo.pages.base.BasePageObject;

import java.util.Objects;

public class ProductPage extends BasePageObject {

    public void isProductPage() {
        assertIsPresent("TEXT_LABEL_PAGE");
    }

    public void tapOnProduct(String productName) {
        tapOn("TEXT_LABEL_PRODUCT", productName);
    }

    public void tapOnColor(String color) {
        tapOn("RADIO_BUTTON_COLOR", color);
    }

    public void addQuantity(String quantity) {
        if (!Objects.equals(quantity, getText("TEXT_ITEM_QUANTITY"))) {
            tapOn("BUTTON_INCREASE_QUANTITY");
        }
    }

    public void tapOnAddToCartButton() {
        tapOn("BUTTON_ADD_TO_CART");
    }

    public void tapOnCartIcon() {
        tapOn("ICON_CART");
    }
}
