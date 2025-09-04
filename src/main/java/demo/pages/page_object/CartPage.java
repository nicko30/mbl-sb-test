package demo.pages.page_object;

import demo.data.Product;
import demo.pages.base.BasePageObject;

import static demo.utils.Utils.removeDollarSign;
import static org.junit.Assert.assertEquals;

public class CartPage extends BasePageObject {

    public void isCartPage() {
        assertIsPresent("TEXT_LABEL_CART_PAGE");
    }

    public void tapOnProceedToCheckout() {
        tapOn("BUTTON_PROCEED_TO_CHECKOUT");
    }

    public void validateTotalPrice() {
        String totalProductPrice = removeDollarSign(getText("TEXT_LABEL_PRODUCT_TOTAL_PRICE"));
        String productPrice = removeDollarSign(getText("TEXT_LABEL_PRODUCT_PRICE"));
        String quantity = getText("TEXT_ITEM_QUANTITY");
        assertEquals(String.valueOf(Double.parseDouble(productPrice) * Integer.parseInt(quantity)), totalProductPrice);
        Product.setTotalProductPrice(Double.parseDouble(totalProductPrice));
    }
}
