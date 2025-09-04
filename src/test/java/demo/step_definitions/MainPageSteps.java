package demo.step_definitions;

import demo.pages.base.BasePageObject;
import io.cucumber.java8.En;

public class MainPageSteps implements En {

    BasePageObject basePageObject = new BasePageObject();

    public MainPageSteps() {
        And("^User swipe up \"([^\"]*)\" times$", (Integer count) -> basePageObject.swipeUpScreen(count));
    }
}
