package demo.pages.page_object;

import demo.data.Product;
import demo.pages.base.BasePageObject;

import static demo.utils.Utils.removeDollarSign;
import static org.junit.Assert.assertEquals;

public class CheckoutPage extends BasePageObject {

    public void userOnTheCheckoutPage() {
        assertIsPresent("TEXT_LABEL_CHECKOUT_PAGE");
    }

    public void inputTextField(String fieldName, String value) {
        typeOn(
                switch (fieldName.toUpperCase()) {
                    case "FULL NAME" -> "TEXT_FIELD_FULL_NAME";
                    case "ADDRESS LINE 1" -> "TEXT_FIELD_ADDRESS_LINE_1";
                    case "CITY" -> "TEXT_FIELD_CITY";
                    case "COUNTRY" -> "TEXT_FIELD_COUNTRY";
                    case "POSTAL CODE" -> "TEXT_FIELD_POSTAL_CODE";
                    case "CARD NUMBER" -> "TEXT_FIELD_CARD_NUMBER";
                    case "EXPIRY DATE" -> "TEXT_FIELD_EXPIRY_DATE";
                    case "SECURITY CODE" -> "TEXT_FIELD_SECURITY_CODE";
                    case "NAME ON CARD" -> "TEXT_FIELD_NAME_ON_CARD";
                    default -> throw new IllegalArgumentException("Field name not recognized: " + fieldName.toUpperCase());
                }, value
        );
    }

    public void tapOnToPayment() {
        tapOn("BUTTON_TO_PAYMENT");
    }

    public void tapOnReviewOrder() {
        tapOn("BUTTON_REVIEW_ORDER");
    }

    public void tapOnPlaceOrder() {
        tapOn("BUTTON_PLACE_ORDER");
    }

    public void userVerifyOrderSuccess() {
        assertIsPresent("TEXT_CHECKOUT_COMPLETE");
    }

    public void validateTotalBills() {
        String actualTotalBills = removeDollarSign(getText("TEXT_TOTAL_BILLS"));
        String deliveryFee = removeDollarSign(getText("TEXT_DELIVERY_FEE"));

        assertEquals(String.valueOf(Double.parseDouble(deliveryFee) + Product.getTotalProductPrice()), actualTotalBills);
    }
}
