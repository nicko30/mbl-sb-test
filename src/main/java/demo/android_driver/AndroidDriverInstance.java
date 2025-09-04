package demo.android_driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URI;

import static demo.data.Constants.USER_DIR;

public class AndroidDriverInstance {
    public static AndroidDriver androidDriver;

    public static void initialize() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("emulator-5554");
        options.setAutomationName("uiautomator2");
        options.setAppWaitActivity("*");
        options.setApp(USER_DIR + "/app/mda-1.0.13-15.apk");

        try {
            URI appiumUri = URI.create("http://127.0.0.1:4723");
            androidDriver = new AndroidDriver(appiumUri.toURL(), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void quit() {
        if (androidDriver != null) {
            androidDriver.quit();
            androidDriver = null;
        }
    }
}