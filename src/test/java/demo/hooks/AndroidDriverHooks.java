package demo.hooks;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

import static demo.android_driver.AndroidDriverInstance.initialize;
import static demo.android_driver.AndroidDriverInstance.quit;
import static demo.utils.Utils.loadElementProperties;

public class AndroidDriverHooks {
    @BeforeAll
    public static void beforeAll() {
        loadElementProperties();
        initialize();
    }

    @AfterAll
    public static void afterAll() {
        quit();
    }
}
